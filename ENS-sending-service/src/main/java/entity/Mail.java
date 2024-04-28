package entity;

import enums.MailStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Table(name = "Mail")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Mail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fk_id_recipient")
    private Long fkIdRecipient;

    @Column(name = "fk_id_mailing")
    private Long fkIdMailing;

    @Column(name = "mail_status")
    @Enumerated(EnumType.STRING)
    private MailStatus status;
}
