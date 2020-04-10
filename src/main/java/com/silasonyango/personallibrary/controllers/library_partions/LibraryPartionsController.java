package com.silasonyango.personallibrary.controllers.library_partions;

import com.silasonyango.personallibrary.models.library_partitions.LibraryPartionsModel;
import com.silasonyango.personallibrary.repository.library_partitions.LibraryPartionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library_partitions")

public class LibraryPartionsController {
    @Autowired
    LibraryPartionsRepository myLibraryPartionRepository;

    @PostMapping("/create_library_partition")
    public LibraryPartionsModel createLibraryPartition(@Valid LibraryPartionsModel myLibraryPartitionObject) {

        return myLibraryPartionRepository.save(myLibraryPartitionObject);
    }

    @PostMapping("/get_all_partitions")
    public List<LibraryPartionsModel> getAllLibraryPartitions() {
        return myLibraryPartionRepository.findAll();
    }
}
