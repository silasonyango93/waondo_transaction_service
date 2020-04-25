package com.silasonyango.transactionservice.controllers.session_management;

import com.silasonyango.transactionservice.repository.session_management.UserSessionActivitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usersessionactivities")
public class UserSessionActivitiesController {
    @Autowired
    UserSessionActivitiesRepository sessionLogsRepository;
}
