package org.ens.requestservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Mailing")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Mailing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mailing")
    private Long id;

    @Column(name = "mail_text")
    private String text;

    @Column(name = "fk_id_sender")
    private Long fkIdSender;
}