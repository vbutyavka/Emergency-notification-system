package org.ens.requestservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Federal_District",
        uniqueConstraints = @UniqueConstraint(columnNames = {"fd_name"}))
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class FederalDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fd")
    private Long id;

    @Column(name = "fd_name")
    private String name;
}
