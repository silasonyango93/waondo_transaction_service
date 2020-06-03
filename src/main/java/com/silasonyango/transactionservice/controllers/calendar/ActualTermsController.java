package com.silasonyango.transactionservice.controllers.calendar;

import com.silasonyango.transactionservice.entity_classes.calendar.ActualTermsEntity;
import com.silasonyango.transactionservice.entity_classes.calendar.ActualWeeksEntity;
import com.silasonyango.transactionservice.repository.calendar.ActualTermsRepository;
import com.silasonyango.transactionservice.repository.calendar.ActualWeeksRepository;
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

    @Autowired
    ActualWeeksRepository actualWeeksRepository;

    @Scheduled(cron="0 01 0 31 11 ?")
    public void createFirstTerm() {
        String currentYear = UtilityClass.getCurrentYear();
        int nextYear = Integer.parseInt(currentYear) + 1;
        ActualTermsEntity dbCreatedTerm = actualTermsRepository.save(new ActualTermsEntity(1,String.valueOf(nextYear)+"-01-01",String.valueOf(nextYear)+"-03-31",String.valueOf(nextYear)));

        createTermOneWeeks(dbCreatedTerm.getTermId(),String.valueOf(nextYear));

        System.out.println("");
    }




    @Scheduled(cron="0 01 0 31 03 ?")
    public void createSecondTerm() {
        String currentYear = UtilityClass.getCurrentYear();
        ActualTermsEntity dbCreatedTerm = actualTermsRepository.save(new ActualTermsEntity(2,currentYear+"-05-01",currentYear+"-07-31",currentYear));

        createTermTwoWeeks(dbCreatedTerm.getTermId(),currentYear);
    }




    @Scheduled(cron="0 01 0 31 07 ?")
    public void createThirdTerm() {
        String currentYear = UtilityClass.getCurrentYear();
        ActualTermsEntity dbCreatedTerm = actualTermsRepository.save(new ActualTermsEntity(3,currentYear+"-09-01",currentYear+"-011-30",currentYear));

        createTermThreeWeeks(dbCreatedTerm.getTermId(),currentYear);
    }




    public void createTermOneWeeks(int termId,String year) {

        actualWeeksRepository.save(new ActualWeeksEntity(termId,1,year+"-01-01",year+"-01-07"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,2,year+"-01-08",year+"-01-14"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,3,year+"-01-15",year+"-01-21"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,4,year+"-01-22",year+"-01-28"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,5,year+"-01-29",year+"-02-04"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,6,year+"-02-05",year+"-02-11"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,7,year+"-02-12",year+"-02-18"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,8,year+"-02-19",year+"-02-25"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,9,year+"-02-26",year+"-03-04"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,10,year+"-03-05",year+"-03-11"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,11,year+"-03-12",year+"-03-18"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,12,year+"-03-19",year+"-03-25"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,13,year+"-03-26",year+"-03-31"));
    }


    public void createTermTwoWeeks(int termId,String year) {

        actualWeeksRepository.save(new ActualWeeksEntity(termId,1,year+"-05-01",year+"-05-07"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,2,year+"-05-08",year+"-05-14"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,3,year+"-05-15",year+"-05-21"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,4,year+"-05-22",year+"-05-28"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,5,year+"-05-29",year+"-06-04"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,6,year+"-06-05",year+"-06-11"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,7,year+"-06-12",year+"-06-18"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,8,year+"-06-19",year+"-06-25"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,9,year+"-06-26",year+"-07-02"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,10,year+"-07-03",year+"-07-09"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,11,year+"-07-10",year+"-07-16"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,12,year+"-07-17",year+"-07-23"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,13,year+"-07-24",year+"-07-31"));
    }


    public void createTermThreeWeeks(int termId,String year) {

        actualWeeksRepository.save(new ActualWeeksEntity(termId,1,year+"-09-01",year+"-09-07"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,2,year+"-09-08",year+"-09-14"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,3,year+"-09-15",year+"-09-21"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,4,year+"-09-22",year+"-09-28"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,5,year+"-09-29",year+"-10-04"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,6,year+"-10-05",year+"-10-11"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,7,year+"-10-12",year+"-10-18"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,8,year+"-10-19",year+"-10-25"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,9,year+"-10-26",year+"-11-02"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,10,year+"-11-03",year+"-11-09"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,11,year+"-11-10",year+"-11-16"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,12,year+"-11-17",year+"-11-23"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId,13,year+"-11-24",year+"-11-30"));
    }

}
