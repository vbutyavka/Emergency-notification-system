package org.ens.requestservice.entity;

import jakarta.persistence.*;
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
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sender")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "pword")
    private String pword;
}
