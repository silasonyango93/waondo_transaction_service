package com.silasonyango.transactionservice.controllers.library_partions;

import com.silasonyango.transactionservice.models.library_partitions.SubPartitionsModel;
import com.silasonyango.transactionservice.repository.library_partitions.SubPartitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/subpartitions")
public class SubPartitionsController {

    @Autowired
    SubPartitionRepository mySubPartitionRepository;

    @PostMapping("/create_subpartition")
    public SubPartitionsModel createLibraryPartition(@Valid SubPartitionsModel mySubPartitionObject) {

        return mySubPartitionRepository.save(mySubPartitionObject);
    }
}
