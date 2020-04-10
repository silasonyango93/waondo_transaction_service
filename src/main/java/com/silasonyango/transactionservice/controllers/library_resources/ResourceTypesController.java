package com.silasonyango.transactionservice.controllers.library_resources;


import com.silasonyango.transactionservice.models.library_resource.ResourceTypesModel;
import com.silasonyango.transactionservice.repository.library_resource.ResourceTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@RequestMapping("/resource_types")
public class ResourceTypesController {
    @Autowired
    ResourceTypesRepository resourceTypesRepository;


    @PostMapping("/create_resource_type")
    public ResourceTypesModel createResourceType(@Valid ResourceTypesModel resourceType) {

        return resourceTypesRepository.save(resourceType);
    }
}
