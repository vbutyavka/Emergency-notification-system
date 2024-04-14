package org.ens.requestservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ens.requestservice.enums.MailStatus;

@Entity
@Table(name = "Mail")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Mail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mail")
    private Long id;

    @Column(name = "fk_id_recipient")
    private Long fkIdRecipient;

    @Column(name = "fk_id_mailing")
    private Long fkIdMailing;

    @Column(name = "mail_status")
    @Enumerated(EnumType.STRING)
    private MailStatus status;//TODO: временно для теста
}
