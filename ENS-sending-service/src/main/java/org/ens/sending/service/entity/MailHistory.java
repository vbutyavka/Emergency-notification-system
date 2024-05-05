package org.ens.sending.service.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.ens.sending.service.enums.MailStatus;

@Entity
@Table(name = "Mail_History")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MailHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "recipient_phone")
    private String recipientPhone;

    @Column(name = "id_mailing")
    private Long idMailing;

    @Column(name = "mail_status")
    @Enumerated(EnumType.STRING)
    private MailStatus status;
}