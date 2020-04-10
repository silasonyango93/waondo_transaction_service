package com.silasonyango.transactionservice.repository.library_partitions;

import com.silasonyango.transactionservice.models.library_partitions.LibraryPartionsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryPartionsRepository  extends JpaRepository<LibraryPartionsModel, Long> {
}
