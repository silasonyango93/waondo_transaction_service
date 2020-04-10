package com.silasonyango.personallibrary.controllers.library_resources;

import com.silasonyango.personallibrary.models.library_resource.LibraryFieldsModel;
import com.silasonyango.personallibrary.repository.library_resource.LibraryFieldsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/library_fields")

public class LibraryFieldsController {
    @Autowired
    LibraryFieldsRepository myLibraryFieldsRepository;

    @PostMapping("/create_library_field")
    public LibraryFieldsModel createResourceType(@Valid LibraryFieldsModel libraryFieldsObject) {

        return myLibraryFieldsRepository.save(libraryFieldsObject);
    }
}
