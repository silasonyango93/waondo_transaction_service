package com.silasonyango.transactionservice.controllers.calendar;

import com.silasonyango.transactionservice.common.config.SessionActivitiesConfig;
import com.silasonyango.transactionservice.common.config.TransactionDescriptionsConfig;
import com.silasonyango.transactionservice.daos.calendar.ActualTermsDao;
import com.silasonyango.transactionservice.entity_classes.academic_classes.AcademicClassLevelsEntity;
import com.silasonyango.transactionservice.entity_classes.academic_classes.LotsEntity;
import com.silasonyango.transactionservice.entity_classes.calendar.ActualTermsEntity;
import com.silasonyango.transactionservice.entity_classes.calendar.ActualWeeksEntity;
import com.silasonyango.transactionservice.entity_classes.calendar.TermIterationsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.CarryForwardsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.TransactionsEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.repository.academic_classes.AcademicClassLevelsRepository;
import com.silasonyango.transactionservice.repository.academic_classes.LotsRepository;
import com.silasonyango.transactionservice.repository.calendar.ActualTermsRepository;
import com.silasonyango.transactionservice.repository.calendar.ActualWeeksRepository;
import com.silasonyango.transactionservice.repository.calendar.TermIterationsRepository;
import com.silasonyango.transactionservice.repository.fee_management.CarryForwardsRepository;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.repository.fee_management.InstallmentRepository;
import com.silasonyango.transactionservice.repository.fee_management.TransactionsRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.json.JSONArray;
import org.json.JSONObject;
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

    @Autowired
    LotsRepository lotsRepository;

    @Autowired
    AcademicClassLevelsRepository academicClassLevelsRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private ActualTermsDao actualTermsDao;

    @Autowired
    private FeeStatementRepository feeStatementRepository;

    @Autowired
    private CarryForwardsRepository carryForwardsRepository;

    @Autowired
    private InstallmentRepository installmentRepository;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private TermIterationsRepository termIterationsRepository;

   //ki @Scheduled(cron="*/02 * * * * *")

    @Scheduled(cron="0 1 0 28 11 ?")
    public void createFirstTerm() {
        String currentYear = UtilityClass.getCurrentYear();
        int nextYear = Integer.parseInt(currentYear) + 1;
        ActualTermsEntity dbCreatedTerm = actualTermsRepository.save(new ActualTermsEntity(1,String.valueOf(nextYear)+"-01-01",String.valueOf(nextYear)+"-03-31",String.valueOf(nextYear)));

        createTermOneWeeks(dbCreatedTerm.getTermId(),String.valueOf(nextYear));
        elevateLotsToNextClass();
        transitionFee(String.valueOf(nextYear),String.valueOf(nextYear)+"-01-01",String.valueOf(nextYear));
    }




    @Scheduled(cron="0 1 0 28 3 ?")
    public void createSecondTerm() {
        String currentYear = UtilityClass.getCurrentYear();
        ActualTermsEntity dbCreatedTerm = actualTermsRepository.save(new ActualTermsEntity(2,currentYear+"-05-01",currentYear+"-07-31",currentYear));

        createTermTwoWeeks(dbCreatedTerm.getTermId(),currentYear);

        transitionFee(currentYear,currentYear+"-05-01",currentYear);
    }




    @Scheduled(cron="0 1 0 28 7 ?")
    public void createThirdTerm() {
        String currentYear = UtilityClass.getCurrentYear();
        ActualTermsEntity dbCreatedTerm = actualTermsRepository.save(new ActualTermsEntity(3,currentYear+"-09-01",currentYear+"-011-30",currentYear));

        createTermThreeWeeks(dbCreatedTerm.getTermId(),currentYear);
        transitionFee(currentYear,currentYear+"-09-01",currentYear);
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


    public void elevateLotsToNextClass() {
        List<LotsEntity> lotsList = lotsRepository.findAll();

        for (int i = 0;i < lotsList.size();i++) {

            int currentAcademicClassLevelId = lotsList.get(i).getAcademicClassLevelId();


            List<AcademicClassLevelsEntity> classLevelsList = academicClassLevelsRepository.findByAcademicClassLevelId(currentAcademicClassLevelId + 1);

            if(classLevelsList.size() > 0) {
                lotsList.get(i).setAcademicClassLevelId(currentAcademicClassLevelId + 1);
                lotsRepository.save(lotsList.get(i));
            }

        }
    }


    public void transitionFee(String year,String carryForwardInstallmentDate,String carryForwardInstallmentYear) {
        List<StudentEntity> students = studentRepository.findAll();

        for (int i = 0; i < students.size(); i++) {

            JSONObject classDetailsObject = UtilityClass.getAStudentClassDetails(students.get(i).getStudentId());

            JSONArray feeStructureBreakDownArray = UtilityClass.getFeeStructureForParticularClassLevel(classDetailsObject.getInt("AcademicClassLevelId"),students.get(i).getStudentResidenceId());
            int probableNextTermIterationId = UtilityClass.getTermDetailsByDate(UtilityClass.getToday()).getInt("TermIterationId") + 1;

            List<ActualTermsEntity> actualTermsEntityList = actualTermsDao.findActualTermByTermIterationIdAndYear(probableNextTermIterationId,year);

            if(actualTermsEntityList.size() > 0) {

                for (int j = 0;j < feeStructureBreakDownArray.length();j++) {

                    if(feeStructureBreakDownArray.getJSONObject(i).getInt("TermIterationId") == probableNextTermIterationId) {

                        updateFeeStatements(students.get(i).getStudentId(),feeStructureBreakDownArray.getJSONObject(i).getInt("FeeAmount"),students.get(i).getStudentResidenceId(),carryForwardInstallmentDate,carryForwardInstallmentYear);

                    }

                }

            } else {

                TermIterationsEntity topTermIteration = termIterationsRepository.getAllTermIterationsInAscendingOrder().get(0);

                for (int j = 0;j < feeStructureBreakDownArray.length();j++) {

                    if(feeStructureBreakDownArray.getJSONObject(i).getInt("TermIterationId") == topTermIteration.getTermIterationId()) {

                        updateFeeStatements(students.get(i).getStudentId(),feeStructureBreakDownArray.getJSONObject(i).getInt("FeeAmount"),students.get(i).getStudentResidenceId(),carryForwardInstallmentDate,carryForwardInstallmentYear);

                    }

                }

            }

        }
    }


    public void updateFeeStatements(int studentId,int nextTermFee,int studentResidenceId,String carryForwardInstallmentDate,String carryForwardInstallmentYear) {

        FeeStatementEntity studentFeeStatement = feeStatementRepository.findFeeStatementByStudentId(studentId).get(0);

        int previousTermBalance = studentFeeStatement.getCurrentTermBalance();
        int previousAnnualBalance = studentFeeStatement.getAnnualBalance();
        int previousTotal = studentFeeStatement.getCurrentYearTotal();

        studentFeeStatement.setCurrentTermBalance(studentFeeStatement.getCurrentTermBalance() + nextTermFee);
        studentFeeStatement.setAnnualBalance(UtilityClass.getAStudentAnnualBalanceFromTermBalance(studentId,studentFeeStatement.getCurrentTermBalance(),studentResidenceId));
        studentFeeStatement.setCurrentYearTotal(previousTermBalance * -1);
        studentFeeStatement.setStudentWorth(0);
        studentFeeStatement.setAlternateTotal(previousTermBalance * -1);

        feeStatementRepository.save(studentFeeStatement);

        processCarryForward(studentId,previousTermBalance,previousAnnualBalance,previousTotal,studentFeeStatement.getCurrentTermBalance(),studentFeeStatement.getAnnualBalance(),previousTermBalance * -1,carryForwardInstallmentDate,carryForwardInstallmentYear);

    }

    public void processCarryForward(int studentId,int previousTermBalance,int previousAnnualBalance,int previousTotal,int nextTermBalance,int nextAnnualBalance,int nextTotal,String carryForwardInstallmentDate,String carryForwardInstallmentYear) {

       CarryForwardsEntity dbSavedCarryForward = carryForwardsRepository.save(new CarryForwardsEntity(studentId,previousTermBalance,UtilityClass.getNow()));
       InstallmentsEntity dbSavedInstallment = installmentRepository.save(new InstallmentsEntity(studentId,previousTermBalance * -1,carryForwardInstallmentDate,1,0, SessionActivitiesConfig.SYSTEM_CARRY_FORWARD_INSTALLMENT,carryForwardInstallmentYear));

        transactionsRepository.save(new TransactionsEntity(0,SessionActivitiesConfig.SYSTEM_CARRY_FORWARD_INSTALLMENT, TransactionDescriptionsConfig.SYSTEM_CARRY_FORWARD_INSTALLMENT,studentId,dbSavedInstallment.getInstallmentId(),dbSavedCarryForward.getCarryFowardId(),0,previousTermBalance,previousAnnualBalance,previousTotal,nextTermBalance,nextAnnualBalance,nextTotal,UtilityClass.getNow()));

    }


}