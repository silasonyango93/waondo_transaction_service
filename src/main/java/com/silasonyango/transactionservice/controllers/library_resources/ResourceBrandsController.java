package com.silasonyango.transactionservice.controllers.library_resources;

import com.silasonyango.transactionservice.models.library_resource.ResourceBrandsModel;
import com.silasonyango.transactionservice.repository.library_resource.ResourceBrandsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/resource_brands")
public class ResourceBrandsController {
    @Autowired
    ResourceBrandsRepository myResourceBrandsRepository;

    @PostMapping("/create_resource_brand")
    public ResourceBrandsModel createResourceType(@Valid ResourceBrandsModel myResourceBrandsObject) {

        return myResourceBrandsRepository.save(myResourceBrandsObject);
    }

}
