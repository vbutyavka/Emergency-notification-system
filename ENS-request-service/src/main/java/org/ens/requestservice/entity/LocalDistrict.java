package org.ens.requestservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class LocalDistrict extends AbstractEntity {

    @Column(name = "ld_name")
    private String name;

    @Column(name = "fk_id_region")
    private Long fkIdRegion;
}
