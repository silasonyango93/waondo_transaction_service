package com.silasonyango.transactionservice.controllers.student_management;

import com.silasonyango.transactionservice.common.config.EndPoints;
import com.silasonyango.transactionservice.common.config.SessionActivitiesConfig;
import com.silasonyango.transactionservice.dtos.api_response.SuccessFailureResponseDto;
import com.silasonyango.transactionservice.dtos.student_management.StudentRegistrationDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.ClassFeeStructureComponentEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.StudentFeeComponentEntity;
import com.silasonyango.transactionservice.entity_classes.session_management.UserSessionActivitiesEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentRegistrationEntity;
import com.silasonyango.transactionservice.repository.fee_management.ClassFeeStructureComponentRepository;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.repository.fee_management.StudentFeeComponentRepository;
import com.silasonyango.transactionservice.repository.session_management.UserSessionActivitiesRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRegistrationRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.utility_classes.CustomOkHttp;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FeeStatementRepository feeStatementRepository;

    @Autowired
    UserSessionActivitiesRepository userSessionActivitiesRepository;

    @Autowired
    ClassFeeStructureComponentRepository classFeeStructureComponentRepository;

    @Autowired
    StudentFeeComponentRepository studentFeeComponentRepository;

    @Autowired
    StudentRegistrationRepository studentRegistrationRepository;

    @PostMapping("/create_student")
    public SuccessFailureResponseDto createAStudent(@Valid StudentRegistrationDto studentRegistrationDto) {
        SuccessFailureResponseDto successFailureResponseDto = new SuccessFailureResponseDto();

        List<StudentEntity> existingStudentsArrayList = studentRepository.findByAdmissionNo(studentRegistrationDto.getAdmissionNo());

        if(existingStudentsArrayList.size() > 0) {
            successFailureResponseDto.setSuccessStatus(false);
            successFailureResponseDto.setResponseMessage("A student already exists by this admission number");
            successFailureResponseDto.setReturnValue("N/A");
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            StudentEntity student = new StudentEntity();
            student.setAdmissionNo(studentRegistrationDto.getAdmissionNo());
            student.setStudentName(studentRegistrationDto.getStudentName());
            student.setGenderId(studentRegistrationDto.getGenderId());
            student.setClassId(studentRegistrationDto.getClassId());
            student.setStudentDob(studentRegistrationDto.getStudentDob());
            student.setProfPicName(studentRegistrationDto.getProfPicName());
            student.setStudentResidenceId(studentRegistrationDto.getStudentResidenceId());
            student.setAdmissionDate(dtf.format(now));

            StudentEntity dbSavedStudent = studentRepository.save(student);

            createAFeeStatement(dbSavedStudent.getStudentId(), dbSavedStudent.getStudentResidenceId());

            prepareStudentFeeComponent(dbSavedStudent.getStudentId());

            UserSessionActivitiesEntity userSessionActivity = userSessionActivitiesRepository.save(new UserSessionActivitiesEntity(studentRegistrationDto.getRegistrationSessionId(), SessionActivitiesConfig.REGISTER_A_STUDENT_SESSION_ACTIVITY, dtf.format(now)));
            studentRegistrationRepository.save(new StudentRegistrationEntity(studentRegistrationDto.getRegistrationSessionId(),userSessionActivity.getUserSessionActivityId(),dbSavedStudent.getStudentId(),dtf.format(now)));

            successFailureResponseDto.setSuccessStatus(true);
            successFailureResponseDto.setResponseMessage("Student successfully registered");
            successFailureResponseDto.setReturnValue("N/A");
        }



        return successFailureResponseDto;
    }

    public FeeStatementEntity createAFeeStatement(int studentId, int studentResidenceId) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        int currentTermTermBalance = getCurrentFeeStructureCurrentTermFee(UtilityClass.getTermDetailsByDate(dtf.format(now)).getInt("TermIterationId"), UtilityClass.getAStudentClassDetails(studentId).getInt("AcademicClassLevelId"), studentResidenceId);
        int annualBalance = UtilityClass.getAStudentAnnualBalanceFromTermBalance(studentId,currentTermTermBalance,studentResidenceId);

        return feeStatementRepository.save(new FeeStatementEntity(studentId,0,0,currentTermTermBalance,annualBalance,0));

    }

    public void prepareStudentFeeComponent(int studentId) {
        List<ClassFeeStructureComponentEntity> classFeeComponentList = classFeeStructureComponentRepository.findAll();
        for(int i=0;i<classFeeComponentList.size();i++) {
            studentFeeComponentRepository.save(new StudentFeeComponentEntity(studentId,classFeeComponentList.get(i).getClassFeeStructureComponentId(),0));
        }
    }

    public int getCurrentFeeStructureCurrentTermFee(int termIterationId, int academicClassLevelId, int studentResidenceId) {

        int feeAmount = 0;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("termIterationId", String.valueOf(termIterationId))
                .add("academicClassLevelId", String.valueOf(academicClassLevelId))
                .add("studentResidenceId", String.valueOf(studentResidenceId))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_fee_structure_for_particular_student_for_particular_term",formBody);
            JSONObject object = new JSONObject(responseString);
            JSONArray jsonArray = object.getJSONArray("results");
            JSONObject dataObject = jsonArray.getJSONObject(0);
            feeAmount = dataObject.getInt("FeeAmount");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return feeAmount;
    }
}
