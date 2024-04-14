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
@Table(name = "Region",
        uniqueConstraints = @UniqueConstraint(columnNames = {"region_name"}))
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Region extends AbstractEntity {

    @Column(name = "region_name")
    private String name;

    @Column(name = "fk_id_fd")
    private Long fkIdFd;
}
