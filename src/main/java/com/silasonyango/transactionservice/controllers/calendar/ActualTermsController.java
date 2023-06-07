package com.silasonyango.transactionservice.controllers.calendar;

import com.silasonyango.transactionservice.common.config.EndPoints;
import com.silasonyango.transactionservice.common.config.SessionActivitiesConfig;
import com.silasonyango.transactionservice.common.config.TransactionDescriptionsConfig;
import com.silasonyango.transactionservice.daos.calendar.ActualTermsDao;
import com.silasonyango.transactionservice.dtos.calendar.RequestTermByTermId;
import com.silasonyango.transactionservice.dtos.fee_management.ManualFeeTransitionRequestDto;
import com.silasonyango.transactionservice.dtos.student_management.StudentRegistrationDto;
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
import com.silasonyango.transactionservice.repository.fee_management.*;
import com.silasonyango.transactionservice.repository.session_management.SessionLogsRepository;
import com.silasonyango.transactionservice.repository.session_management.UserSessionActivitiesRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.services.retrofit.RetrofitClientInstance;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.students.StudentsListResponseDto;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.students.StudentsService;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import javax.validation.Valid;
import java.util.ArrayList;
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
    ActualTermsDao actualTermsDao;

    @Autowired
    FeeStatementRepository feeStatementRepository;

    @Autowired
    CarryForwardsRepository carryForwardsRepository;

    @Autowired
    InstallmentRepository installmentRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    @Autowired
    TermIterationsRepository termIterationsRepository;

    @Autowired
    FeeCorrectionsRepository feeCorrectionsRepository;

    @Autowired
    UserSessionActivitiesRepository userSessionActivitiesRepository;

    @Autowired
    TransactionDescriptionsRepository transactionDescriptionsRepository;

    @Autowired
    SessionLogsRepository sessionLogsRepository;

    //ki @Scheduled(cron="*/02 * * * * *")

    //@Scheduled(cron="0 1 0 28 11 ?")
    public void createFirstTerm() {
        String currentYear = UtilityClass.getCurrentYear();
        int nextYear = Integer.parseInt(currentYear) + 1;
        ActualTermsEntity dbCreatedTerm = actualTermsRepository.save(new ActualTermsEntity(1, String.valueOf(nextYear) + "-01-01", String.valueOf(nextYear) + "-03-31", String.valueOf(nextYear)));

        createTermOneWeeks(dbCreatedTerm.getTermId(), String.valueOf(nextYear));
        elevateLotsToNextClass();
        transitionFee(String.valueOf(nextYear), String.valueOf(nextYear) + "-01-01", String.valueOf(nextYear));
    }


    //@Scheduled(cron="0 1 0 28 3 ?")
    public void createSecondTerm() {
        String currentYear = UtilityClass.getCurrentYear();
        ActualTermsEntity dbCreatedTerm = actualTermsRepository.save(new ActualTermsEntity(2, currentYear + "-05-01", currentYear + "-07-31", currentYear));

        createTermTwoWeeks(dbCreatedTerm.getTermId(), currentYear);

        transitionFee(currentYear, currentYear + "-05-01", currentYear);
    }


    //@Scheduled(cron="0 1 0 28 7 ?")
    public void createThirdTerm() {
        String currentYear = UtilityClass.getCurrentYear();
        ActualTermsEntity dbCreatedTerm = actualTermsRepository.save(new ActualTermsEntity(3, currentYear + "-09-01", currentYear + "-011-30", currentYear));

        createTermThreeWeeks(dbCreatedTerm.getTermId(), currentYear);
        transitionFee(currentYear, currentYear + "-09-01", currentYear);
    }


    public void createTermOneWeeks(int termId, String year) {

        actualWeeksRepository.save(new ActualWeeksEntity(termId, 1, year + "-01-01", year + "-01-07"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 2, year + "-01-08", year + "-01-14"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 3, year + "-01-15", year + "-01-21"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 4, year + "-01-22", year + "-01-28"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 5, year + "-01-29", year + "-02-04"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 6, year + "-02-05", year + "-02-11"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 7, year + "-02-12", year + "-02-18"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 8, year + "-02-19", year + "-02-25"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 9, year + "-02-26", year + "-03-04"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 10, year + "-03-05", year + "-03-11"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 11, year + "-03-12", year + "-03-18"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 12, year + "-03-19", year + "-03-25"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 13, year + "-03-26", year + "-03-31"));
    }


    public void createTermTwoWeeks(int termId, String year) {

        actualWeeksRepository.save(new ActualWeeksEntity(termId, 1, year + "-05-01", year + "-05-07"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 2, year + "-05-08", year + "-05-14"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 3, year + "-05-15", year + "-05-21"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 4, year + "-05-22", year + "-05-28"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 5, year + "-05-29", year + "-06-04"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 6, year + "-06-05", year + "-06-11"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 7, year + "-06-12", year + "-06-18"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 8, year + "-06-19", year + "-06-25"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 9, year + "-06-26", year + "-07-02"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 10, year + "-07-03", year + "-07-09"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 11, year + "-07-10", year + "-07-16"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 12, year + "-07-17", year + "-07-23"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 13, year + "-07-24", year + "-07-31"));
    }


    public void createTermThreeWeeks(int termId, String year) {

        actualWeeksRepository.save(new ActualWeeksEntity(termId, 1, year + "-09-01", year + "-09-07"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 2, year + "-09-08", year + "-09-14"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 3, year + "-09-15", year + "-09-21"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 4, year + "-09-22", year + "-09-28"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 5, year + "-09-29", year + "-10-04"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 6, year + "-10-05", year + "-10-11"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 7, year + "-10-12", year + "-10-18"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 8, year + "-10-19", year + "-10-25"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 9, year + "-10-26", year + "-11-02"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 10, year + "-11-03", year + "-11-09"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 11, year + "-11-10", year + "-11-16"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 12, year + "-11-17", year + "-11-23"));
        actualWeeksRepository.save(new ActualWeeksEntity(termId, 13, year + "-11-24", year + "-11-30"));
    }


    public void elevateLotsToNextClass() {
        List<LotsEntity> lotsList = lotsRepository.findAll();

        for (int i = 0; i < lotsList.size(); i++) {

            int currentAcademicClassLevelId = lotsList.get(i).getAcademicClassLevelId();


            List<AcademicClassLevelsEntity> classLevelsList = academicClassLevelsRepository.findByAcademicClassLevelId(currentAcademicClassLevelId + 1);

            if (classLevelsList.size() > 0) {
                lotsList.get(i).setAcademicClassLevelId(currentAcademicClassLevelId + 1);
                lotsRepository.save(lotsList.get(i));
            }

        }
    }


    public void transitionFee(String year, String carryForwardInstallmentDate, String carryForwardInstallmentYear) {
        List<StudentEntity> students = fetchAllStudentsNotCompletedSchool();

        for (int i = 0; i < students.size(); i++) {

            JSONObject classDetailsObject = UtilityClass.getAStudentClassDetails(students.get(i).getStudentId());

            JSONArray feeStructureBreakDownArray = UtilityClass.getFeeStructureForParticularClassLevel(classDetailsObject.getInt("AcademicClassLevelId"), students.get(i).getStudentResidenceId());
            int probableNextTermIterationId = UtilityClass.getTermDetailsByDate(UtilityClass.getToday()).getInt("TermIterationId") + 1;

            List<ActualTermsEntity> actualTermsEntityList = actualTermsDao.findActualTermByTermIterationIdAndYear(probableNextTermIterationId, year);

            if (actualTermsEntityList.size() > 0) {

                for (int j = 0; j < feeStructureBreakDownArray.length(); j++) {

                    if (feeStructureBreakDownArray.getJSONObject(j).getInt("TermIterationId") == probableNextTermIterationId) {

                        updateFeeStatements(students.get(i).getStudentId(), feeStructureBreakDownArray.getJSONObject(j).getInt("FeeAmount"), students.get(i).getStudentResidenceId(), carryForwardInstallmentDate, carryForwardInstallmentYear);

                    }

                }

            } else {

                TermIterationsEntity topTermIteration = termIterationsRepository.getAllTermIterationsInAscendingOrder().get(0);

                for (int j = 0; j < feeStructureBreakDownArray.length(); j++) {

                    if (feeStructureBreakDownArray.getJSONObject(j).getInt("TermIterationId") == topTermIteration.getTermIterationId()) {

                        updateFeeStatements(students.get(i).getStudentId(), feeStructureBreakDownArray.getJSONObject(j).getInt("FeeAmount"), students.get(i).getStudentResidenceId(), carryForwardInstallmentDate, carryForwardInstallmentYear);

                    }

                }

            }

        }
    }


    public void updateFeeStatements(int studentId, int nextTermFee, int studentResidenceId, String carryForwardInstallmentDate, String carryForwardInstallmentYear) {

        FeeStatementEntity studentFeeStatement = feeStatementRepository.findFeeStatementByStudentId(studentId).get(0);

        int previousTermBalance = studentFeeStatement.getCurrentTermBalance();
        int previousAnnualBalance = studentFeeStatement.getAnnualBalance();
        int previousTotal = studentFeeStatement.getCurrentYearTotal();

        studentFeeStatement.setCurrentTermBalance(studentFeeStatement.getCurrentTermBalance() + nextTermFee);
        studentFeeStatement.setAnnualBalance(UtilityClass.getAStudentAnnualBalanceFromTermBalance(studentId, studentFeeStatement.getCurrentTermBalance(), studentResidenceId));
        studentFeeStatement.setCurrentYearTotal(previousTermBalance * -1);
        studentFeeStatement.setStudentWorth(0);
        studentFeeStatement.setAlternateTotal(previousTermBalance * -1);

        feeStatementRepository.save(studentFeeStatement);

        processCarryForward(studentId, previousTermBalance, previousAnnualBalance, previousTotal, studentFeeStatement.getCurrentTermBalance(), studentFeeStatement.getAnnualBalance(), previousTermBalance * -1, carryForwardInstallmentDate, carryForwardInstallmentYear);

    }

    public void processCarryForward(int studentId, int previousTermBalance, int previousAnnualBalance, int previousTotal, int nextTermBalance, int nextAnnualBalance, int nextTotal, String carryForwardInstallmentDate, String carryForwardInstallmentYear) {

        CarryForwardsEntity dbSavedCarryForward = carryForwardsRepository.save(new CarryForwardsEntity(studentId, previousTermBalance, UtilityClass.getNow(), 0));
        InstallmentsEntity dbSavedInstallment = installmentRepository.save(new InstallmentsEntity(studentId, previousTermBalance * -1, carryForwardInstallmentDate, 1, sessionLogsRepository.findByIsAdminSessionLog(1).get(0).getSessionLogId(), userSessionActivitiesRepository.findByIsAdminUserSessionActivity(1).get(0).getUserSessionActivityId(), carryForwardInstallmentYear, 0));

        transactionsRepository.save(new TransactionsEntity(sessionLogsRepository.findByIsAdminSessionLog(1).get(0).getSessionLogId(), userSessionActivitiesRepository.findByIsAdminUserSessionActivity(1).get(0).getUserSessionActivityId(), transactionDescriptionsRepository.findByTransactionDescriptionCode(isItEndOfTheYear() ? 4 : 3).get(0).getTransactionDescriptionId(), studentId, dbSavedInstallment.getInstallmentId(), dbSavedCarryForward.getCarryFowardId(), feeCorrectionsRepository.findByIsAdminFeeCorrection(1).get(0).getFeeCorrectionId(), previousTermBalance, previousAnnualBalance, previousTotal, nextTermBalance, nextAnnualBalance, nextTotal, UtilityClass.getNow()));

    }


    public boolean isItEndOfTheYear() {
        int probableNextTermIterationId = UtilityClass.getTermDetailsByDate(UtilityClass.getToday()).getInt("TermIterationId") + 1;

        return actualTermsDao.findActualTermByTermIterationIdAndYear(probableNextTermIterationId, UtilityClass.getCurrentYear()).size() == 0;
    }


    @PostMapping("/manual_fee_transition")
    public boolean manualFeeTransition() {

        List<StudentEntity> studentsNotCompletedSchool = fetchAllStudentsNotCompletedSchool();

        for (StudentEntity currentStudent : studentsNotCompletedSchool) {
            if (currentStudent.getStudentResidenceId() == 9) {
                double carryForwardAmount = 0.0;
                FeeStatementEntity boarderStatement = null;
                List<FeeStatementEntity> boarderStatementsList = feeStatementRepository.findFeeStatementByStudentId(currentStudent.getStudentId());
                if (boarderStatementsList.isEmpty()) {
                    continue;
                } else {
                    boarderStatement = boarderStatementsList.get(0);
                }
                double currentTermBalance = boarderStatement.getCurrentTermBalance();
                double currentAnnualBalance = boarderStatement.getAnnualBalance();
                double termTwoBoardingFee = 12000;
                carryForwardAmount = currentTermBalance * -1;
                double nextTermBalance = currentTermBalance + termTwoBoardingFee;
                double nextAnnualBalance = nextTermBalance + 8000;
                boarderStatement.setCurrentTermBalance((int) nextTermBalance);
                boarderStatement.setAnnualBalance((int) nextAnnualBalance);
                //boarderStatement.setCurrentYearTotal((int)carryForwardAmount);
                feeStatementRepository.save(boarderStatement);
                CarryForwardsEntity carryForwardsEntity = carryForwardsRepository.save(new CarryForwardsEntity(
                        currentStudent.getStudentId(),
                        (int) carryForwardAmount,
                        UtilityClass.getNow(),
                        0
                ));
                transactionsRepository.save(new TransactionsEntity(
                        sessionLogsRepository.findByIsAdminSessionLog(1).get(0).getSessionLogId(),
                        userSessionActivitiesRepository.findByIsAdminUserSessionActivity(1).get(0).getUserSessionActivityId(),
                        transactionDescriptionsRepository.findByTransactionDescriptionCode(TransactionDescriptionsConfig.END_OF_TERM_CARRY_FORWARD_TRANSACTION_DESCRIPTION).get(0).getTransactionDescriptionId(),
                        currentStudent.getStudentId(),
                        installmentRepository.findInstallmentByIsAdminInstallment(1).get(0).getInstallmentId(),
                        carryForwardsEntity.getCarryFowardId(),
                        feeCorrectionsRepository.findByIsAdminFeeCorrection(1).get(0).getFeeCorrectionId(),
                        (int) currentTermBalance,
                        (int) currentAnnualBalance,
                        (int) carryForwardAmount,
                        (int) nextTermBalance,
                        (int) nextAnnualBalance,
                        (int) carryForwardAmount,
                        UtilityClass.getNow()
                ));
            }

            if (currentStudent.getStudentResidenceId() == 10) {
                double carryForwardAmount = 0.0;
                FeeStatementEntity dayScholarStatement = null;
                List<FeeStatementEntity> dayScholarStatementsList = feeStatementRepository.findFeeStatementByStudentId(currentStudent.getStudentId());
                if (dayScholarStatementsList.isEmpty()) {
                    continue;
                } else {
                    dayScholarStatement = dayScholarStatementsList.get(0);
                }
                double currentTermBalance = dayScholarStatement.getCurrentTermBalance();
                double currentAnnualBalance = dayScholarStatement.getAnnualBalance();
                double termTwoDayScholarFee = 3600;
                carryForwardAmount = currentTermBalance * -1;
                double nextTermBalance = currentTermBalance + termTwoDayScholarFee;
                double nextAnnualBalance = nextTermBalance + 2400;
                dayScholarStatement.setCurrentTermBalance((int) nextTermBalance);
                dayScholarStatement.setAnnualBalance((int) nextAnnualBalance);
                //dayScholarStatement.setCurrentYearTotal((int)carryForwardAmount);
                feeStatementRepository.save(dayScholarStatement);

                CarryForwardsEntity carryForwardsEntity = carryForwardsRepository.save(new CarryForwardsEntity(
                        currentStudent.getStudentId(),
                        (int) carryForwardAmount,
                        UtilityClass.getNow(),
                        0
                ));
                transactionsRepository.save(new TransactionsEntity(
                        sessionLogsRepository.findByIsAdminSessionLog(1).get(0).getSessionLogId(),
                        userSessionActivitiesRepository.findByIsAdminUserSessionActivity(1).get(0).getUserSessionActivityId(),
                        transactionDescriptionsRepository.findByTransactionDescriptionCode(TransactionDescriptionsConfig.END_OF_TERM_CARRY_FORWARD_TRANSACTION_DESCRIPTION).get(0).getTransactionDescriptionId(),
                        currentStudent.getStudentId(),
                        installmentRepository.findInstallmentByIsAdminInstallment(1).get(0).getInstallmentId(),
                        carryForwardsEntity.getCarryFowardId(),
                        feeCorrectionsRepository.findByIsAdminFeeCorrection(1).get(0).getFeeCorrectionId(),
                        (int) currentTermBalance,
                        (int) currentAnnualBalance,
                        dayScholarStatement.getCurrentYearTotal(),
                        (int) nextTermBalance,
                        (int) nextAnnualBalance,
                        dayScholarStatement.getCurrentYearTotal(),
                        UtilityClass.getNow()
                ));
            }
        }

        return true;
    }

    @PostMapping("/manual_fee_transition_correction")
    public boolean correctManualFeeTransition() {

        List<StudentEntity> studentsNotCompletedSchool = fetchAllStudentsNotCompletedSchool();

        for (StudentEntity currentStudent : studentsNotCompletedSchool) {
            if (currentStudent.getStudentResidenceId() == 9) {
                FeeStatementEntity boarderStatement = feeStatementRepository.findFeeStatementByStudentId(currentStudent.getStudentId()).get(0);
                double currentTermBalance = boarderStatement.getCurrentTermBalance();
                currentTermBalance = currentTermBalance - 20000;
                double currentAnnualBalance = currentTermBalance + 10000 + 7150;
                boarderStatement.setCurrentTermBalance((int) currentTermBalance);
                boarderStatement.setAnnualBalance((int) currentAnnualBalance);
                feeStatementRepository.save(boarderStatement);
            }
            if (currentStudent.getStudentResidenceId() == 10) {
                FeeStatementEntity dayScholarStatement = feeStatementRepository.findFeeStatementByStudentId(currentStudent.getStudentId()).get(0);
                double currentTermBalance = dayScholarStatement.getCurrentTermBalance();
                currentTermBalance = currentTermBalance - 6000;
                double currentAnnualBalance = currentTermBalance + 3600 + 2400;
                dayScholarStatement.setCurrentTermBalance((int) currentTermBalance);
                dayScholarStatement.setAnnualBalance((int) currentAnnualBalance);
                feeStatementRepository.save(dayScholarStatement);
            }
        }

        return true;
    }


    @PostMapping("/create_a_terms_weeks")
    public boolean createATermsWeeks(@Valid RequestTermByTermId requestTermByTermId) {
        boolean weeksAreCreated = true;
        JSONObject termDetails = UtilityClass.getTermDetailsByTermId(requestTermByTermId.getTermId());

        if (termDetails.getInt("TermIterationCode") == 1) {

            if (actualWeeksRepository.findByTermId(requestTermByTermId.getTermId()).size() == 0) {
                createTermOneWeeks(requestTermByTermId.getTermId(), String.valueOf(termDetails.getInt("Year")));
            } else {
                weeksAreCreated = false;
            }

        } else if (termDetails.getInt("TermIterationCode") == 2) {
            if (actualWeeksRepository.findByTermId(requestTermByTermId.getTermId()).size() == 0) {
                createTermTwoWeeks(requestTermByTermId.getTermId(), String.valueOf(termDetails.getInt("Year")));
            } else {
                weeksAreCreated = false;
            }
        } else if (termDetails.getInt("TermIterationCode") == 3) {
            if (actualWeeksRepository.findByTermId(requestTermByTermId.getTermId()).size() == 0) {
                createTermThreeWeeks(requestTermByTermId.getTermId(), String.valueOf(termDetails.getInt("Year")));
            } else {
                weeksAreCreated = false;
            }
        }

        return weeksAreCreated;
    }


    public List<StudentEntity> fetchAllStudentsNotCompletedSchool() {
        StudentsService studentsService = RetrofitClientInstance.getRetrofitInstance(EndPoints.WAONDO_NODE_BASE_URL + "/").create(StudentsService.class);
        Call<List<StudentsListResponseDto>> callSync = studentsService.getAllStudentsNotCompletedSchool();
        try {
            Response<List<StudentsListResponseDto>> response = callSync.execute();
//            if (response != null && response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
//
//            }
            List<StudentEntity> studentsEntityList = new ArrayList<>();
            for (StudentsListResponseDto currentDto : response.body()) {
                studentsEntityList.add(new StudentEntity(
                        currentDto.getStudentId(),
                        currentDto.getAdmissionNo(),
                        currentDto.getStudentName(),
                        currentDto.getGenderId(),
                        currentDto.getStudentDOB(),
                        currentDto.getStudentResidenceId(),
                        currentDto.getClassId(),
                        currentDto.getAdmissionDate(),
                        currentDto.getProfPicName(),
                        currentDto.getIsAnAdminStudent()
                ));
            }
            return studentsEntityList;
        } catch (Exception ex) {
        }

        return null;
    }


}
