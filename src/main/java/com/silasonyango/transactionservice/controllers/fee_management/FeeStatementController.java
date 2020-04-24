package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.dtos.api_response.SuccessFailureResponseDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/statements")
public class FeeStatementController {
    @Autowired
    FeeStatementRepository feeStatementRepository;

    @PostMapping("/create_fee_statement")
    public SuccessFailureResponseDto createAFeeStatement(@Valid FeeStatementEntity feeStatementEntity) {

        feeStatementRepository.save(feeStatementEntity);

        return new SuccessFailureResponseDto(true,"Fee statement successfully created","N/A");
    }
}
