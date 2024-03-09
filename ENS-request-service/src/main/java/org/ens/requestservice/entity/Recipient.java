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
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recipient")
    private Long id;

    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "recipient_status")
    private RecipientStatus status;

    @Column(name = "fk_id_ld")
    private Long fkIdLd;
}
