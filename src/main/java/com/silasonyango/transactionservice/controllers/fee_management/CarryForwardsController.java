package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.repository.fee_management.CarryForwardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carry_forwards")
public class CarryForwardsController {
    @Autowired
    CarryForwardsRepository carryForwardsRepository;
}
