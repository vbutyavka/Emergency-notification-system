package org.ens.requestservice.repository;

import org.ens.requestservice.entity.Recipient;
import org.ens.requestservice.repository.crud.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipientRepository extends CrudRepository<Recipient> {

    @Query("SELECT r FROM Recipient r WHERE r.id > :from AND r.id <= :to")
    List<Recipient> getBatch(@Param("from") Long from, @Param("to") Long to);

    @Query("SELECT r FROM Recipient r WHERE r.phoneNumber LIKE :phoneNumber")
    Recipient getByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT r FROM Recipient r " +
            "INNER JOIN LocalDistrict ld ON ld.id = r.fkIdLd " +
            "INNER JOIN Region rg ON rg.id = ld.fkIdRegion " +
            "INNER JOIN FederalDistrict fd ON fd.id = rg.fkIdFd " +
            "WHERE r.id > :from AND r.id <= :to " +
            "AND fd.id = :federalDistrictId")
    List<Recipient> getBatchByFederalDistrict(@Param("federalDistrictId") Long federalDistrictId,
                                              @Param("from") Long from,
                                              @Param("to") Long to);

    @Query("SELECT r FROM Recipient r " +
            "INNER JOIN LocalDistrict ld ON ld.id = r.fkIdLd " +
            "INNER JOIN Region rg ON rg.id = ld.fkIdRegion " +
            "WHERE r.id > :from AND r.id <= :to " +
            "AND rg.id = :regionId")
    List<Recipient> getBatchByRegion(@Param("regionId") Long regionId,
                                     @Param("from") Long from,
                                     @Param("to") Long to);

    @Query("SELECT r FROM Recipient r " +
            "INNER JOIN LocalDistrict ld ON ld.id = r.fkIdLd " +
            "WHERE r.id > :from AND r.id <= :to " +
            "AND ld.id = :localDistrictId")
    List<Recipient> getBatchByLocalDistrict(@Param("localDistrictId") Long localDistrictId,
                                            @Param("from") Long from,
                                            @Param("to") Long to);

    @Query("SELECT MAX(r.id) FROM Recipient r")
    Long getMaxId();

    @Query("SELECT MIN(r.id) FROM Recipient r")
    Long getMinId();
}
