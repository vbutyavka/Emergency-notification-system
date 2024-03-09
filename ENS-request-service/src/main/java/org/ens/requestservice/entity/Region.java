package org.ens.requestservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Region",
        uniqueConstraints = @UniqueConstraint(columnNames = {"region_name"}))
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_region")
    private Long id;

    @Column(name = "region_name")
    private String name;

    @Column(name = "fk_id_fd")
    private Long fkIdFd;
}
