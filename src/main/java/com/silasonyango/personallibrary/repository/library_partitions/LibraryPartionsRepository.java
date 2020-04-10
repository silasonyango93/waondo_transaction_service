package com.silasonyango.personallibrary.repository.library_partitions;

import com.silasonyango.personallibrary.models.library_partitions.LibraryPartionsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryPartionsRepository  extends JpaRepository<LibraryPartionsModel, Long> {
}
