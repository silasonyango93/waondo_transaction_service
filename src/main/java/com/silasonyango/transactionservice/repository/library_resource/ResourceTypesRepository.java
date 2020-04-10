package com.silasonyango.transactionservice.repository.library_resource;


import com.silasonyango.transactionservice.models.library_resource.ResourceTypesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceTypesRepository extends JpaRepository<ResourceTypesModel, Long> {
}
