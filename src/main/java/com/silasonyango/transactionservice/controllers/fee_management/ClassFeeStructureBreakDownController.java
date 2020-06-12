package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.repository.fee_management.ClassFeeStructureBreakDownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class_fee_structure_breakdown")
public class ClassFeeStructureBreakDownController {
    @Autowired
    ClassFeeStructureBreakDownRepository classFeeStructureBreakDownRepository;
}
