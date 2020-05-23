package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.repository.fee_management.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    TransactionsRepository transactionsRepository;
}
