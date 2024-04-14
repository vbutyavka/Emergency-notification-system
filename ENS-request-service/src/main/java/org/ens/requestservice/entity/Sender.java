package org.ens.requestservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Sender",
       uniqueConstraints = @UniqueConstraint(columnNames = {"login"}))
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Sender extends AbstractEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "pword")
    private String pword;
}
