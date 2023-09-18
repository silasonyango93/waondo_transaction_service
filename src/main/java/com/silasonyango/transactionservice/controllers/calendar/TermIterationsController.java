package com.silasonyango.transactionservice.controllers.calendar;

import com.silasonyango.transactionservice.dtos.daos.calendar.TermIterationsDao;
import com.silasonyango.transactionservice.entity_classes.calendar.TermIterationsEntity;
import com.silasonyango.transactionservice.repository.calendar.TermIterationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/term_iterations")
public class TermIterationsController {
    @Autowired
    TermIterationsRepository termIterationsRepository;

    @Autowired
    TermIterationsDao termIterationsDao;

    //@Scheduled(cron="*/02 * * * * *")
    public void testActualTermsDao() {

        List<TermIterationsEntity> termIterationsEntityList = termIterationsDao.findActualTermByTermIterationId(2);

        System.out.println("");
    }
}
