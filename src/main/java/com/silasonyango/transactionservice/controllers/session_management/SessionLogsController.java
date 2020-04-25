package com.silasonyango.transactionservice.controllers.session_management;

import com.silasonyango.transactionservice.dtos.api_response.SuccessFailureResponseDto;
import com.silasonyango.transactionservice.entity_classes.session_management.SessionLogsEntity;
import com.silasonyango.transactionservice.repository.session_management.SessionLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sessionlogs")
public class SessionLogsController {
    @Autowired
    SessionLogsRepository sessionLogsRepository;

    @PostMapping("/create_session_log")
    public SuccessFailureResponseDto createAFeeStatement(@Valid SessionLogsEntity sessionLogsEntity) {

        sessionLogsRepository.save(sessionLogsEntity);

        return new SuccessFailureResponseDto(true,"Session log successfully created","N/A");
    }
}
