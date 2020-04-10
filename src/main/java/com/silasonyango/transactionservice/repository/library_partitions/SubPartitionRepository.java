package com.silasonyango.transactionservice.repository.library_partitions;

import com.silasonyango.transactionservice.models.library_partitions.SubPartitionsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubPartitionRepository  extends JpaRepository<SubPartitionsModel, Long> {
}
