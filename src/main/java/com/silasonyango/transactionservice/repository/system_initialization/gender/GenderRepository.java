package com.silasonyango.transactionservice.repository.system_initialization.gender;

import com.silasonyango.transactionservice.entity_classes.system_initialization.gender.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenderRepository extends JpaRepository<GenderEntity, Long> {
    public List<GenderEntity> findByGenderCode(@Param("GenderCode") int genderCode);
    public List<GenderEntity> findByGenderId(@Param("GenderId") int genderId);
}
