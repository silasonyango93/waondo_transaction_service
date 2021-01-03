package com.silasonyango.transactionservice.ingestor.controllers;

import com.silasonyango.transactionservice.common.config.EndPoints;
import com.silasonyango.transactionservice.controllers.student_management.StudentController;
import com.silasonyango.transactionservice.dtos.api_response.SuccessFailureResponseDto;
import com.silasonyango.transactionservice.dtos.student_management.StudentRegistrationDto;
import com.silasonyango.transactionservice.entity_classes.academic_classes.ClassStreamsEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentResidenceEntity;
import com.silasonyango.transactionservice.entity_classes.system_initialization.gender.GenderEntity;
import com.silasonyango.transactionservice.ingestor.models.IngStudentModel;
import com.silasonyango.transactionservice.ingestor.services.IngestorService;
import com.silasonyango.transactionservice.repository.academic_classes.ClassStreamsRepository;
import com.silasonyango.transactionservice.repository.academic_classes.ClassesRepository;
import com.silasonyango.transactionservice.repository.session_management.SessionLogsRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentResidenceRepository;
import com.silasonyango.transactionservice.repository.system_initialization.gender.GenderRepository;
import com.silasonyango.transactionservice.services.retrofit.RetrofitClientInstance;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.academic_class_management.AcademicClassResponseDto;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.academic_class_management.AcademicClassService;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.fee_structure.ClassFeeStructureServiceModel;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.fee_structure.FeeStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

@RestController
@RequestMapping("/ingestor")
public class IngestorController {
    @Autowired
    StudentController studentController;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    StudentResidenceRepository studentResidenceRepository;

    @Autowired
    ClassesRepository classesRepository;

    @Autowired
    ClassStreamsRepository classStreamsRepository;

    @Autowired
    SessionLogsRepository sessionLogsRepository;

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/ingest")
    public void ingest() {
        List<IngStudentModel> allStudents = getAllStudents();

        for (IngStudentModel ingestedStudent : allStudents) {

            SuccessFailureResponseDto successFailureResponseDto = studentController.createAStudent(new StudentRegistrationDto(
                    ingestedStudent.getAdmissionNo(),
                    ingestedStudent.getStudentName(),
                    determineGenderId(ingestedStudent.getStudentGender()),
                    "2021-01-01",
                    determineStudentResidenceId(ingestedStudent.getStudentType()),
                    determineClassId(ingestedStudent.getClassId(), ingestedStudent.getCompletedSchool()),
                    "2021-01-01",
                    ingestedStudent.getStudentGender().equals("Male") ? "male_student.png" : "female_student.png",
                    sessionLogsRepository.findByIsAdminSessionLog(1).get(0).getSessionLogId()
            ));

            StudentEntity savedStudent = studentRepository.findByStudentId(Integer.parseInt(successFailureResponseDto.getReturnValue())).get(0);

        }
    }

    public List<IngStudentModel> getAllStudents() {
        IngestorService ingestorService = RetrofitClientInstance.getRetrofitInstance(EndPoints.WAONDO_INGESTOR_BASE_URL + "/").create(IngestorService.class);
        Call<List<IngStudentModel>> callSync = ingestorService.getAllStudents();
        try {
            Response<List<IngStudentModel>> response = callSync.execute();
            return response.body();
        } catch (Exception ex) {
        }

        return null;
    }

    public int determineGenderId(String genderDescription) {
        GenderEntity gender = genderRepository.findByGenderCode(genderDescription.equals("Male") ? 1 : 2).get(0);
        return gender.getGenderId();
    }

    public int determineStudentResidenceId(String residenceDescription) {
        StudentResidenceEntity studentResidence = studentResidenceRepository.findByStudentResidenceCode(residenceDescription.equals("Border") ? 1 : 2).get(0);
        return studentResidence.getStudentResidenceId();
    }

    public int determineClassId(int ingestedClassId, int completedSchool) {
        if (completedSchool == 0) {
            if (ingestedClassId == 2) {
                // 1G
                AcademicClassResponseDto academicClass = retrieveClassFromClassLevelAndStreamName(10, "G");
                return academicClass.getClassId();
            } else if (ingestedClassId == 3) {
                // 1Y
                AcademicClassResponseDto academicClass = retrieveClassFromClassLevelAndStreamName(10, "Y");
                return academicClass.getClassId();
            } else if (ingestedClassId == 4) {
                // 1W
                AcademicClassResponseDto academicClass = retrieveClassFromClassLevelAndStreamName(10, "W");
                return academicClass.getClassId();
            } else if (ingestedClassId == 5) {
                // 2G
                AcademicClassResponseDto academicClass = retrieveClassFromClassLevelAndStreamName(11, "G");
                return academicClass.getClassId();
            } else if (ingestedClassId == 6) {
                // 2W
                AcademicClassResponseDto academicClass = retrieveClassFromClassLevelAndStreamName(11, "W");
                return academicClass.getClassId();
            } else if (ingestedClassId == 7) {
                // 2Y
                AcademicClassResponseDto academicClass = retrieveClassFromClassLevelAndStreamName(11, "Y");
                return academicClass.getClassId();
            } else if (ingestedClassId == 8) {
                // 3G
                AcademicClassResponseDto academicClass = retrieveClassFromClassLevelAndStreamName(12, "G");
                return academicClass.getClassId();
            } else if (ingestedClassId == 9) {
                // 3W
                AcademicClassResponseDto academicClass = retrieveClassFromClassLevelAndStreamName(12, "W");
                return academicClass.getClassId();
            } else if (ingestedClassId == 10) {
                // 3Y
                AcademicClassResponseDto academicClass = retrieveClassFromClassLevelAndStreamName(12, "Y");
                return academicClass.getClassId();
            } else if (ingestedClassId == 11) {
                // 4G
                AcademicClassResponseDto academicClass = retrieveClassFromClassLevelAndStreamName(13, "G");
                return academicClass.getClassId();
            } else if (ingestedClassId == 12) {
                // 4W
                AcademicClassResponseDto academicClass = retrieveClassFromClassLevelAndStreamName(13, "W");
                return academicClass.getClassId();
            } else if (ingestedClassId == 13) {
                // 4Y
                AcademicClassResponseDto academicClass = retrieveClassFromClassLevelAndStreamName(13, "Y");
                return academicClass.getClassId();
            }
        } else {
            if (ingestedClassId == 11) {
                // 4G
                return 20;
            } else if (ingestedClassId == 12) {
                // 4W
                return 22;
            } else if (ingestedClassId == 13) {
                // 4Y
                return 21;
            }
        }
        return 0;
    }

    public AcademicClassResponseDto retrieveClassFromClassLevelAndStreamName(int academicClassLevelId, String streamName) {
        AcademicClassService academicClassService = RetrofitClientInstance.getRetrofitInstance(EndPoints.WAONDO_NODE_BASE_URL + "/").create(AcademicClassService.class);
        Call<AcademicClassResponseDto> callSync = academicClassService.retrieveClassByAcademicClassLevelIdAndStreamName(academicClassLevelId, streamName);
        try {
            Response<AcademicClassResponseDto> response = callSync.execute();
            return response.body();
        } catch (Exception ex) {
        }

        return null;
    }
}
