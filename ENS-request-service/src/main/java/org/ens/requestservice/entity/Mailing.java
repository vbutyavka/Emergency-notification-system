package org.ens.requestservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class Mailing extends AbstractEntity {

    @Column(name = "mail_text")
    private String text;

    @Column(name = "fk_id_sender")
    private Long fkIdSender;
}