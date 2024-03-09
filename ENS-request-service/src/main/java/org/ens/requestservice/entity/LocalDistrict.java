package org.ens.requestservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Local_District")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LocalDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ld")
    private Long id;

    @Column(name = "ld_name")
    private String name;

    @Column(name = "fk_id_region")
    private Long fkIdRegion;
}
