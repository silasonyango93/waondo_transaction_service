package com.silasonyango.personallibrary.repository.library_partitions;

import com.silasonyango.personallibrary.models.library_partitions.SubPartitionsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubPartitionRepository  extends JpaRepository<SubPartitionsModel, Long> {
}
