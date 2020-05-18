package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.repository.fee_management.ClassFeeStructureComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class-fee-structure-component")
public class ClassFeeStructureComponentController {
    @Autowired
    ClassFeeStructureComponentRepository feeStatementRepository;
}
