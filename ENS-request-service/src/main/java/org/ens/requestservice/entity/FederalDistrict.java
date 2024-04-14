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
@Table(name = "Federal_District",
        uniqueConstraints = @UniqueConstraint(columnNames = {"fd_name"}))
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class FederalDistrict extends AbstractEntity {

    @Column(name = "fd_name")
    private String name;
}
