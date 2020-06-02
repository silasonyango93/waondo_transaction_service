package com.silasonyango.transactionservice.controllers.calendar;

import com.silasonyango.transactionservice.entity_classes.calendar.ActualTermsEntity;
import com.silasonyango.transactionservice.repository.calendar.ActualTermsRepository;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actual_terms")
public class ActualTermsController {
    @Autowired
    ActualTermsRepository actualTermsRepository;

    @Scheduled(cron="0 01 0 31 11 ?")
    public void createFirstTerm() {
        String currentYear = UtilityClass.getCurrentYear();
        int nextYear = Integer.parseInt(currentYear) + 1;
        actualTermsRepository.save(new ActualTermsEntity(1,String.valueOf(nextYear)+"-01-01",String.valueOf(nextYear)+"-03-31",String.valueOf(nextYear)));
    }

    @Scheduled(cron="0 01 0 31 03 ?")
    public void createSecondTerm() {
        String currentYear = UtilityClass.getCurrentYear();
        actualTermsRepository.save(new ActualTermsEntity(2,currentYear+"-05-01",currentYear+"-07-31",currentYear));
    }

    @Scheduled(cron="0 01 0 31 07 ?")
    public void createThirdTerm() {
        String currentYear = UtilityClass.getCurrentYear();
        actualTermsRepository.save(new ActualTermsEntity(3,currentYear+"-09-01",currentYear+"-011-30",currentYear));
    }

}
