package org.ens.requestservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ens.requestservice.enums.RecipientStatus;

@Entity
@Table(name = "Recipient",
        uniqueConstraints = @UniqueConstraint(columnNames = {"phone_number"}))
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Recipient extends AbstractEntity {

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "recipient_status")
    @Enumerated(EnumType.STRING)
    private RecipientStatus status;

    @Column(name = "fk_id_ld")
    private Long fkIdLd;
}
