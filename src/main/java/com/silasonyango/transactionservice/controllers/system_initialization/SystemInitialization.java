package com.silasonyango.transactionservice.controllers.system_initialization;

import com.silasonyango.transactionservice.common.config.EndPoints;
import com.silasonyango.transactionservice.common.config.UtilityConfigs;
import com.silasonyango.transactionservice.entity_classes.academic_classes.*;
import com.silasonyango.transactionservice.entity_classes.fee_management.CarryForwardsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeCorrectionsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.TransactionDescriptionsEntity;
import com.silasonyango.transactionservice.entity_classes.session_management.SessionActivitiesEntity;
import com.silasonyango.transactionservice.entity_classes.session_management.SessionLogsEntity;
import com.silasonyango.transactionservice.entity_classes.session_management.UserSessionActivitiesEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentResidenceEntity;
import com.silasonyango.transactionservice.entity_classes.system_initialization.correction_descriptions.CorrectionDescriptionsEntity;
import com.silasonyango.transactionservice.entity_classes.system_initialization.gender.GenderEntity;
import com.silasonyango.transactionservice.entity_classes.system_initialization.system_configuration.SystemConfigurationEntity;
import com.silasonyango.transactionservice.entity_classes.user_management.AccessPrivilegesEntity;
import com.silasonyango.transactionservice.entity_classes.user_management.RolesEntity;
import com.silasonyango.transactionservice.entity_classes.user_management.UsersEntity;
import com.silasonyango.transactionservice.repository.academic_classes.*;
import com.silasonyango.transactionservice.repository.fee_management.CarryForwardsRepository;
import com.silasonyango.transactionservice.repository.fee_management.FeeCorrectionsRepository;
import com.silasonyango.transactionservice.repository.fee_management.InstallmentRepository;
import com.silasonyango.transactionservice.repository.fee_management.TransactionDescriptionsRepository;
import com.silasonyango.transactionservice.repository.session_management.SessionActivitiesRepository;
import com.silasonyango.transactionservice.repository.session_management.SessionLogsRepository;
import com.silasonyango.transactionservice.repository.session_management.UserSessionActivitiesRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentResidenceRepository;
import com.silasonyango.transactionservice.repository.system_initialization.correction_descriptions.CorrectionDescriptionsRepository;
import com.silasonyango.transactionservice.repository.system_initialization.gender.GenderRepository;
import com.silasonyango.transactionservice.repository.system_initialization.system_configuration.SystemConfigurationRepository;
import com.silasonyango.transactionservice.repository.user_management.AccessPrivilegesRepository;
import com.silasonyango.transactionservice.repository.user_management.RolesRepository;
import com.silasonyango.transactionservice.repository.user_management.UsersRepository;
import com.silasonyango.transactionservice.services.retrofit.RetrofitClientInstance;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.user_management.AllUsersResponse;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.user_management.UsersService;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

@RestController
@RequestMapping("/system_initialization")
public class SystemInitialization {
    @Autowired
    SystemConfigurationRepository systemConfigurationRepository;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    AccessPrivilegesRepository accessPrivilegesRepository;

    @Autowired
    CorrectionDescriptionsRepository correctionDescriptionsRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    SessionActivitiesRepository sessionActivitiesRepository;

    @Autowired
    StudentResidenceRepository studentResidenceRepository;

    @Autowired
    TransactionDescriptionsRepository transactionDescriptionsRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AcademicClassLevelsRepository academicClassLevelsRepository;

    @Autowired
    LotDescriptionsRepository lotDescriptionsRepository;

    @Autowired
    LotsRepository lotsRepository;

    @Autowired
    ClassStreamsRepository classStreamsRepository;

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    SessionLogsRepository sessionLogsRepository;

    @Autowired
    UserSessionActivitiesRepository userSessionActivitiesRepository;

    @Autowired
    InstallmentRepository installmentRepository;

    @Autowired
    CarryForwardsRepository carryForwardsRepository;

    @Autowired
    FeeCorrectionsRepository feeCorrectionsRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void checkIfSystemIsAlreadyConfigured() {

        if(systemConfigurationRepository.findAll().size() == 0) {
            configureGender();
            configureAccessPrivileges();
            configureCorrectionDescriptions();
            configureRoles();
            configureSessionActivities();
            configureStudentResidence();
            configureTransactionDescriptions();
            configureAdminAcademicClassLevel();
            createAdminUser();

        } else {
            System.out.println("System is configured ready for use");
            //fetchAllUsers();
        }

    }

    public void fetchAllUsers() {
        UsersService usersService = RetrofitClientInstance.getRetrofitInstance(EndPoints.WAONDO_NODE_BASE_URL+"/").create(UsersService.class);
        Call<AllUsersResponse> callSync = usersService.getAllUsers();
        try {
            Response<AllUsersResponse> response = callSync.execute();
            AllUsersResponse user = response.body();
        } catch (Exception ex) { }
    }


    public void configureGender() {
        genderRepository.save(new GenderEntity("Male",1));
        genderRepository.save(new GenderEntity("Female",2));
    }

    public void configureAccessPrivileges() {
        accessPrivilegesRepository.save(new AccessPrivilegesEntity("Login",1));
        accessPrivilegesRepository.save(new AccessPrivilegesEntity("Register a student",2));
        accessPrivilegesRepository.save(new AccessPrivilegesEntity("Correct a student's personal details",3));
        accessPrivilegesRepository.save(new AccessPrivilegesEntity("Register a fee installment",4));
        accessPrivilegesRepository.save(new AccessPrivilegesEntity("Correct a fee payment",5));
        accessPrivilegesRepository.save(new AccessPrivilegesEntity("Delete a student",6));
        accessPrivilegesRepository.save(new AccessPrivilegesEntity("Change a student residence",7));
    }

    public void configureCorrectionDescriptions() {
        correctionDescriptionsRepository.save(new CorrectionDescriptionsEntity("ADMIN CORRECTION",0));
        correctionDescriptionsRepository.save(new CorrectionDescriptionsEntity("Wrong student paid for",1));
    }

    public void configureRoles() {
        rolesRepository.save(new RolesEntity("Admin",1));
        rolesRepository.save(new RolesEntity("Bursar",2));
    }

    public void configureSessionActivities() {
        sessionActivitiesRepository.save(new SessionActivitiesEntity("SYSTEM_CARRY_FORWARD_INSTALLMENT",0));
        sessionActivitiesRepository.save(new SessionActivitiesEntity("Logged into the system",1));
        sessionActivitiesRepository.save(new SessionActivitiesEntity("Registered a student",2));
        sessionActivitiesRepository.save(new SessionActivitiesEntity("Corrected a student's personal details",3));
        sessionActivitiesRepository.save(new SessionActivitiesEntity("Registered a fee installment",4));
        sessionActivitiesRepository.save(new SessionActivitiesEntity("Corrected a fee payment",5));
        sessionActivitiesRepository.save(new SessionActivitiesEntity("Deleted a student",6));
    }

    public void configureStudentResidence() {
        studentResidenceRepository.save(new StudentResidenceEntity("Boarder",1));
        studentResidenceRepository.save(new StudentResidenceEntity("Day Scholar",2));
    }

    public void configureTransactionDescriptions() {
        transactionDescriptionsRepository.save(new TransactionDescriptionsEntity("SYSTEM_CARRY_FORWARD_INSTALLMENT",0));
        transactionDescriptionsRepository.save(new TransactionDescriptionsEntity("Register a fee installment",1));
        transactionDescriptionsRepository.save(new TransactionDescriptionsEntity("Fee correction",2));
        transactionDescriptionsRepository.save(new TransactionDescriptionsEntity("An end of term carry forward",3));
        transactionDescriptionsRepository.save(new TransactionDescriptionsEntity("An end of year carry forward",4));
    }

    public void configureAdminAcademicClassLevel() {
        AcademicClassLevelsEntity dbAdminAcademicClassLevel = academicClassLevelsRepository.save(new AcademicClassLevelsEntity("Class Zero",0,1));
        configureAdminLotDescription(dbAdminAcademicClassLevel.getAcademicClassLevelId());
    }

    public void configureAdminLotDescription(int academicClassLevelId) {
        LotDescriptionsEntity dbAdminLotDescription = lotDescriptionsRepository.save(new LotDescriptionsEntity("Admin Lot",1));
        configureAdminLot(dbAdminLotDescription.getLotDescriptionId(),academicClassLevelId);
    }

    public void configureAdminLot(int lotDescriptionId,int academicClassLevelId) {
        LotsEntity dbSavedAdminLot = lotsRepository.save(new LotsEntity(lotDescriptionId,academicClassLevelId, UtilityClass.getNow(),1));
        configureAdminClassStream(dbSavedAdminLot.getLotId());
    }

    public void configureAdminClassStream(int lotId) {
        ClassStreamsEntity dbSavedAdminClassSttream = classStreamsRepository.save(new ClassStreamsEntity("Admin Class Stream",1));
        configureAdminClass(lotId,dbSavedAdminClassSttream.getClassStreamId());
    }

    public void configureAdminClass(int lotId,int classStreamId) {
        ClassesEntity dbSavedAdminClass = classesRepository.save(new ClassesEntity(lotId,classStreamId,UtilityClass.getNow(),1));
        configureAdminStudent(dbSavedAdminClass.getClassId());
    }


    public void configureAdminStudent(int classId) {
        int genderId = genderRepository.findByGenderCode(1).get(0).getGenderId();
        int studentResidenceId = studentResidenceRepository.findByStudentResidenceCode(1).get(0).getStudentResidenceId();
        StudentEntity dbSavedAdminStudent = studentRepository.save(new StudentEntity("0000","Admin Student",genderId,UtilityClass.getToday(),studentResidenceId,classId,UtilityClass.getNow(), UtilityConfigs.MALE_PROF_PIC_NAME,1));
    }

    public void createAdminUser() {
        int genderId = genderRepository.findByGenderCode(1).get(0).getGenderId();
        UsersEntity dbSavedAdminUser = usersRepository.save(new UsersEntity("Admin User","admin email",genderId,"password",UtilityClass.getNow(),1));
        createAdminSessionLog(dbSavedAdminUser.getUserId());
    }

    public void createAdminSessionLog(int userId) {
        SessionLogsEntity dbSavedAdminSessionLog = sessionLogsRepository.save(new SessionLogsEntity(userId,UtilityClass.getNow(),UtilityClass.getNow(),1));
        createAdminUserSessionActivity(dbSavedAdminSessionLog.getSessionLogId());
    }

    public void createAdminUserSessionActivity(int sessionLogId) {
        int sessionActivityId = sessionActivitiesRepository.findBySessionActivityCode(0).get(0).getSessionActivityId();
        UserSessionActivitiesEntity dbSavedAdminUserSessionActivity = userSessionActivitiesRepository.save(new UserSessionActivitiesEntity(sessionLogId,sessionActivityId,UtilityClass.getNow(),1));
        configureAdminInstallment(sessionLogId,dbSavedAdminUserSessionActivity.getUserSessionActivityId());
    }

    public void configureAdminInstallment(int sessionLogId,int userSessionActivityId) {
        int studentId = studentRepository.findByIsAnAdminStudent(1).get(0).getStudentId();
        InstallmentsEntity dbSavedAdminInstallment = installmentRepository.save(new InstallmentsEntity(studentId,0,UtilityClass.getNow(),0,sessionLogId,userSessionActivityId,UtilityClass.getCurrentYear(),1));
        configureAdminCarryForward();
    }

    public void configureAdminCarryForward() {
        int studentId = studentRepository.findByIsAnAdminStudent(1).get(0).getStudentId();
        carryForwardsRepository.save(new CarryForwardsEntity(studentId,0,UtilityClass.getNow(),1));
        configureAdminFeeCorrection();
    }

    public void configureAdminFeeCorrection() {
        int sessionLogId = sessionLogsRepository.findByIsAdminSessionLog(1).get(0).getSessionLogId();
        int userSessionActivityId = userSessionActivitiesRepository.findByIsAdminUserSessionActivity(1).get(0).getUserSessionActivityId();
        int correctionDescriptionId = correctionDescriptionsRepository.findByCorrectionDescriptionCode(0).get(0).getCorrectionDescriptionId();
        int studentId = studentRepository.findByIsAnAdminStudent(1).get(0).getStudentId();
        feeCorrectionsRepository.save(new FeeCorrectionsEntity(sessionLogId,userSessionActivityId,correctionDescriptionId,studentId,0,0,0,0,0,0,UtilityClass.getNow(),1));
        assertSystemSuccessfullyInitialized();
    }

    public void assertSystemSuccessfullyInitialized() {
        systemConfigurationRepository.save(new SystemConfigurationEntity(UtilityConfigs.SYSTEM_CONFIGURATION_DESCRIPTION,UtilityConfigs.SYSTEM_CONFIGURATION_CODE));
    }
}
