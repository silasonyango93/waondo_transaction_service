package com.silasonyango.transactionservice.controllers.student_management;

import com.silasonyango.transactionservice.common.config.EndPoints;
import com.silasonyango.transactionservice.common.config.SessionActivitiesConfig;
import com.silasonyango.transactionservice.dtos.api_response.SuccessFailureResponseDto;
import com.silasonyango.transactionservice.dtos.fee_management.FeeComponentsResponseDto;
import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;
import com.silasonyango.transactionservice.dtos.fee_management.InstallmentsResponseDto;
import com.silasonyango.transactionservice.dtos.student_management.*;
import com.silasonyango.transactionservice.entity_classes.fee_management.ClassFeeStructureComponentEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.StudentFeeComponentEntity;
import com.silasonyango.transactionservice.entity_classes.session_management.UserSessionActivitiesEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentRegistrationEntity;
import com.silasonyango.transactionservice.repository.fee_management.ClassFeeStructureComponentRepository;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.repository.fee_management.InstallmentRepository;
import com.silasonyango.transactionservice.repository.fee_management.StudentFeeComponentRepository;
import com.silasonyango.transactionservice.repository.session_management.SessionActivitiesRepository;
import com.silasonyango.transactionservice.repository.session_management.UserSessionActivitiesRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRegistrationRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentResidenceRepository;
import com.silasonyango.transactionservice.repository.system_initialization.gender.GenderRepository;
import com.silasonyango.transactionservice.services.academic_classes.AcademicClassesService;
import com.silasonyango.transactionservice.services.excel.ExcelService;
import com.silasonyango.transactionservice.services.ingestor.ParentPhoneNumbersIngestorService;
import com.silasonyango.transactionservice.services.student.StudentsService;
import com.silasonyango.transactionservice.utility_classes.CustomOkHttp;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.silasonyango.transactionservice.utility_classes.UtilityClass.getTermDetailsByDate;

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

  @Autowired
  GenderRepository genderRepository;

  @Autowired
  SessionActivitiesRepository sessionActivitiesRepository;

  @Autowired
  StudentResidenceRepository studentResidenceRepository;

  @Autowired
  InstallmentRepository installmentRepository;

  @Autowired
  StudentsService studentsService;

  @Autowired
  AcademicClassesService academicClassesService;

  @Autowired
  ExcelService excelService;

  @Autowired
  ParentPhoneNumbersIngestorService parentPhoneNumbersIngestorService;

  @PostMapping("/create_student")
  public SuccessFailureResponseDto createAStudent(
      @Valid StudentRegistrationDto studentRegistrationDto) {
    SuccessFailureResponseDto successFailureResponseDto = new SuccessFailureResponseDto();

    List<StudentEntity> existingStudentsArrayList = studentRepository.findByAdmissionNo(
        studentRegistrationDto.getAdmissionNo());

    if (existingStudentsArrayList.size() > 0) {
      successFailureResponseDto.setSuccessStatus(false);
      successFailureResponseDto.setResponseMessage(
          "A student already exists by this admission number");
      successFailureResponseDto.setReturnValue("N/A");
    } else {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      LocalDateTime now = LocalDateTime.now();

      StudentEntity student = new StudentEntity();
      student.setAdmissionNo(studentRegistrationDto.getAdmissionNo());
      student.setStudentName(studentRegistrationDto.getStudentName());
      student.setGenderId(studentRegistrationDto.getGenderCode());
      student.setClassId(studentRegistrationDto.getClassId());
      student.setStudentDob(studentRegistrationDto.getStudentDob());
      student.setProfPicName(studentRegistrationDto.getProfPicName());
      student.setStudentResidenceId(studentResidenceRepository.findByStudentResidenceCode(
          studentRegistrationDto.getStudentResidenceCode()).get(0).getStudentResidenceId());
      student.setAdmissionDate(dtf.format(now));
      student.setGenderId(
          genderRepository.findByGenderCode(studentRegistrationDto.getGenderCode()).get(0)
              .getGenderId());
      student.setParentPhoneNumber(studentRegistrationDto.getParentPhoneNumber());

      StudentEntity dbSavedStudent = studentRepository.save(student);

      createAFeeStatement(dbSavedStudent.getStudentId(), dbSavedStudent.getStudentResidenceId());

      prepareStudentFeeComponent(dbSavedStudent.getStudentId());

      UserSessionActivitiesEntity userSessionActivity = userSessionActivitiesRepository.save(
          new UserSessionActivitiesEntity(studentRegistrationDto.getRegistrationSessionId(),
              sessionActivitiesRepository.findBySessionActivityCode(
                      SessionActivitiesConfig.REGISTER_A_STUDENT_SESSION_ACTIVITY).get(0)
                  .getSessionActivityId(), dtf.format(now), 0));
      studentRegistrationRepository.save(
          new StudentRegistrationEntity(studentRegistrationDto.getRegistrationSessionId(),
              userSessionActivity.getUserSessionActivityId(), dbSavedStudent.getStudentId(),
              dtf.format(now)));

      successFailureResponseDto.setSuccessStatus(true);
      successFailureResponseDto.setResponseMessage("Student successfully registered");
      successFailureResponseDto.setReturnValue(String.valueOf(dbSavedStudent.getStudentId()));
    }

    return successFailureResponseDto;
  }

  public FeeStatementEntity createAFeeStatement(int studentId, int studentResidenceId) {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime now = LocalDateTime.now();

    int currentTermTermBalance = getCurrentFeeStructureCurrentTermFee(
        UtilityClass.getTermDetailsByDate(dtf.format(now)).getInt("TermIterationId"),
        UtilityClass.getAStudentClassDetails(studentId).getInt("AcademicClassLevelId"),
        studentResidenceId);
    int annualBalance = UtilityClass.getAStudentAnnualBalanceFromTermBalance(studentId,
        currentTermTermBalance, studentResidenceId);

    return feeStatementRepository.save(
        new FeeStatementEntity(studentId, 0, 0, currentTermTermBalance, annualBalance, 0));

  }

  public void prepareStudentFeeComponent(int studentId) {
    List<ClassFeeStructureComponentEntity> classFeeComponentList = classFeeStructureComponentRepository.findAll();
    for (int i = 0; i < classFeeComponentList.size(); i++) {
      studentFeeComponentRepository.save(new StudentFeeComponentEntity(studentId,
          classFeeComponentList.get(i).getClassFeeStructureComponentId(), 0));
    }
  }

  public int getCurrentFeeStructureCurrentTermFee(int termIterationId, int academicClassLevelId,
      int studentResidenceId) {

    int feeAmount = 0;
    CustomOkHttp customOkHttp = new CustomOkHttp();

    RequestBody formBody = new FormBody.Builder()
        .add("termIterationId", String.valueOf(termIterationId))
        .add("academicClassLevelId", String.valueOf(academicClassLevelId))
        .add("studentResidenceId", String.valueOf(studentResidenceId))
        .build();

    try {
      String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL
          + "/get_fee_structure_for_particular_student_for_particular_term", formBody);
      JSONObject object = new JSONObject(responseString);
      JSONArray jsonArray = object.getJSONArray("results");
      JSONObject dataObject = jsonArray.getJSONObject(0);
      feeAmount = dataObject.getInt("FeeAmount");
    } catch (Exception e) {
      e.printStackTrace();
    }

    return feeAmount;
  }

  @GetMapping("/fetch_all_students")
  public List<StudentsListDto> fetchAllStudents(
      @RequestParam("areContinuingStudents") int areContinuingStudents) {
    List<StudentsListDto> studentsList = new ArrayList<>();
    List<Map<String, Object>> studentsMapList = null;
    if (areContinuingStudents == 1) {
      studentsMapList = studentsService.fetchAllStudentsInEntireSchoolNotCompletedSchool();
    } else {
      studentsMapList = studentsService.fetchAllStudentsInEntireSchoolWhoHaveCompletedSchool();
    }
    for (Map<String, Object> map : studentsMapList) {
      studentsList.add(new StudentsListDto(
          Integer.parseInt(String.valueOf(map.get("StudentId"))),
          String.valueOf(map.get("AdmissionNo")),
          String.valueOf(map.get("StudentName")),
          String.valueOf(map.get("GenderDescription")),
          String.format("%s%s", map.get("AcademicClassLevelName"), map.get("ClassStreamName")),
          String.format("%s", map.get("StudentResidenceDescription"))
      ));
    }

    return studentsList;
  }

  @PostMapping("/get_a_student_fee_statement")
  public FeeStatementResponseDto getAStudentFeeStatement(
      @Valid StudentRequestByAdmissionNoDto studentRequestByAdmissionNoDto) {

    int studentId = studentRepository.findByAdmissionNo(
        studentRequestByAdmissionNoDto.getAdmissionNumber()).get(0).getStudentId();

    FeeStatementResponseDto feeStatementResponseDto = new FeeStatementResponseDto();
    StudentEntity studentPersonalDetails = studentRepository.findByStudentId(studentId).get(0);
    JSONObject classDetails = UtilityClass.getAStudentClassDetails(studentId);
    JSONObject residenceDetails = UtilityClass.getAStudentResidenceDetails(studentId);
    FeeStatementEntity feeStatementEntity = feeStatementRepository.findFeeStatementByStudentId(
        studentId).get(0);
    List<InstallmentsEntity> feeInstallmentsList = installmentRepository.findInstallmentsNotSoftDeleted(
        studentId);

    feeStatementResponseDto.setStudentId(studentId);
    feeStatementResponseDto.setProfPicName(studentPersonalDetails.getProfPicName());
    feeStatementResponseDto.setAdmissionNumber(studentPersonalDetails.getAdmissionNo());
    feeStatementResponseDto.setStudentName(studentPersonalDetails.getStudentName());
    feeStatementResponseDto.setGender(
        genderRepository.findByGenderId(studentPersonalDetails.getGenderId()).get(0).getGenderCode()
            == 1 ? "Male" : "Female");
    feeStatementResponseDto.setClassDetails(
        classDetails.getString("AcademicClassLevelName") + " " + classDetails.getString(
            "ClassStreamName"));
    feeStatementResponseDto.setResidenceDetails(
        residenceDetails.getString("StudentResidenceDescription"));
    feeStatementResponseDto.setTermBalance(feeStatementEntity.getCurrentTermBalance());
    feeStatementResponseDto.setAnnualBalance(feeStatementEntity.getAnnualBalance());
    feeStatementResponseDto.setCurrentyearTotal(feeStatementEntity.getCurrentYearTotal());

    List<InstallmentsResponseDto> installmentsResponseDtoArrayList = new ArrayList<>();

    for (int i = 0; i < feeInstallmentsList.size(); i++) {

      String installmentDate = feeInstallmentsList.get(i).getInstallmentDate()
          .replaceAll(" .+$", "");
      InstallmentsResponseDto installmentsResponseDto = new InstallmentsResponseDto(
          feeInstallmentsList.get(i).getStudentId(),
          feeInstallmentsList.get(i).getInstallmentAmount(),
          installmentDate, feeInstallmentsList.get(i).getIsCarryForward(),
          feeInstallmentsList.get(i).getSessionLogId(),
          feeInstallmentsList.get(i).getUserSessionActivityId(),
          feeInstallmentsList.get(i).getInstallmentYear(),
          UtilityClass.getAUserByASessionLogId(feeInstallmentsList.get(i).getSessionLogId())
              .getString("Name"),
          getTermDetailsByDate(installmentDate).getString("TermIterationDescription"));
      installmentsResponseDto.setInstallmentId(feeInstallmentsList.get(i).getInstallmentId());
      installmentsResponseDtoArrayList.add(installmentsResponseDto);

    }

    feeStatementResponseDto.setInstallmentsResponseArray(installmentsResponseDtoArrayList);

    JSONArray feeComponentsArray = UtilityClass.getAStudentFeeComponents(studentId);
    List<FeeComponentsResponseDto> feeComponentsResponseDtoList = new ArrayList<>();

    for (int i = 0; i < feeComponentsArray.length(); i++) {

      String feeComponentDescription = feeComponentsArray.getJSONObject(i)
          .getString("FeeComponentDescription");
      double componentFeeAmount = feeComponentsArray.getJSONObject(i)
          .getDouble("ComponentFeeAmount");

      feeComponentsResponseDtoList.add(
          new FeeComponentsResponseDto(feeComponentDescription, componentFeeAmount));

    }

    feeStatementResponseDto.setFeeComponentsResponseDtoList(feeComponentsResponseDtoList);
    feeStatementResponseDto.setFeeStatementProcessedSuccessfully(true);

    return feeStatementResponseDto;
  }


  @PostMapping("/get_a_student_personal_details")
  public StudentPersonalDetailsResponseDto getAStudentPersonalDetails(
      @Valid StudentRequestByAdmissionNoDto studentRequestByAdmissionNoDto) {
    List<StudentEntity> studentEntityList = studentRepository.findByAdmissionNo(
        studentRequestByAdmissionNoDto.getAdmissionNumber());

    StudentPersonalDetailsResponseDto studentPersonalDetailsResponseDto = new StudentPersonalDetailsResponseDto();

    if (studentEntityList.size() > 0) {
      StudentEntity studentEntity = studentEntityList.get(0);
      studentPersonalDetailsResponseDto = new StudentPersonalDetailsResponseDto(true,
          studentEntity.getStudentId(), studentEntity.getAdmissionNo(),
          studentEntity.getStudentName(),
          genderRepository.findByGenderId(studentEntity.getGenderId()).get(0).getGenderCode(),
          studentEntity.getStudentDob());
    } else {
      studentPersonalDetailsResponseDto.setStudentDetailsAvailable(false);
    }

    return studentPersonalDetailsResponseDto;
  }


  @PostMapping("/update_a_student_personal_details")
  public boolean updateAStudentPersonalDetails(
      @Valid UpdateStudentBasicDetailsRequestDto updateStudentBasicDetailsRequestDto) {
    StudentEntity studentEntity = studentRepository.findByStudentId(
        updateStudentBasicDetailsRequestDto.getStudentId()).get(0);
    studentEntity.setStudentName(updateStudentBasicDetailsRequestDto.getStudentName());
    studentEntity.setStudentDob(updateStudentBasicDetailsRequestDto.getStudentDateOfBirth());
    studentEntity.setGenderId(
        genderRepository.findByGenderCode(updateStudentBasicDetailsRequestDto.getGenderCode())
            .get(0).getGenderId());

    return studentRepository.save(studentEntity) instanceof StudentEntity;
  }

  @PostMapping("/delete")
  public boolean deleteAStudent(
      @org.springframework.web.bind.annotation.RequestBody StudentRequestByAdmissionNoDto studentRequestByAdmissionNoDto) {
    List<StudentEntity> studentEntityList = studentRepository.findByAdmissionNo(
        studentRequestByAdmissionNoDto.getAdmissionNumber());
    if (studentEntityList.isEmpty()) {
      return false;
    }
    StudentEntity studentEntity = studentEntityList.get(0);
    return studentsService.deleteStudentByStudentId(studentEntity.getStudentId());
  }


  @GetMapping("/excel/students-per-lot/{lotId}")
  public void exportStudentsPerLotExcel(HttpServletResponse response, @PathVariable int lotId)
      throws IOException {
    try {
      Map<String, Object> fullLotNameMap = academicClassesService.fetchLotByItsFullName(lotId);
      String fileName = String.format("FORM %s STUDENTS.",
          fullLotNameMap.get("AcademicClassLevelName"));
      response.setContentType("application/octet-stream");
      String headerKey = "Content-Disposition";
      String headerValue = "attachment; filename=" + fileName + ".xlsx";
      response.setHeader(headerKey, headerValue);
      excelService.processStudentsPerLotExcelExport(response, lotId, fileName);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GetMapping("/excel/students-per-class-stream/{classId}")
  public void exportStudentsPerClassStreamExcel(HttpServletResponse response,
      @PathVariable int classId) throws IOException {
    try {
      Map<String, Object> fullClassNameMap = academicClassesService.fetchClassByItsFullName(
          classId);
      String fileName = String.format("FORM %s%s STUDENTS.",
          fullClassNameMap.get("AcademicClassLevelName")
          , fullClassNameMap.get("ClassStreamName"));
      response.setContentType("application/octet-stream");
      String headerKey = "Content-Disposition";
      String headerValue = "attachment; filename=" + fileName + ".xlsx";
      response.setHeader(headerKey, headerValue);
      excelService.processStudentsPerClassStreamExcelExport(response, classId, fileName);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @GetMapping("/excel/students-per-lot-with-phone-number/{lotId}")
  public void exportStudentsPerLotWithPhoneNumberExcel(HttpServletResponse response,
      @PathVariable int lotId) throws IOException {
    try {
      Map<String, Object> fullLotNameMap = academicClassesService.fetchLotByItsFullName(lotId);
      String fileName = String.format("FORM %s PARENTS PHONE NUMBERS FORM.",
          fullLotNameMap.get("AcademicClassLevelName"));
      response.setContentType("application/octet-stream");
      String headerKey = "Content-Disposition";
      String headerValue = "attachment; filename=" + fileName + ".xlsx";
      response.setHeader(headerKey, headerValue);
      excelService.processStudentsPerLotWithPhoneNoColumnExcelExport(response, lotId, fileName);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @PostMapping("/parent-phone-numbers/upload")
  public ResponseEntity<String> uploadParentPhoneNumbers(@RequestParam("file") MultipartFile file) {
    try {
      parentPhoneNumbersIngestorService.updateParentPhoneNumbers(file);
      return ResponseEntity.status(HttpStatus.OK).body("Upload and update was successful");
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Error encountered while uploading the excel file");
    }
  }
}
