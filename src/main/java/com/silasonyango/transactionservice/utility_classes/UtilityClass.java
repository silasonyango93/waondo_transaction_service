package com.silasonyango.transactionservice.utility_classes;

import com.silasonyango.transactionservice.common.config.EndPoints;
import com.silasonyango.transactionservice.dtos.fee_management.FeeStatementResponseDto;
import com.silasonyango.transactionservice.dtos.fee_management.InstallmentsResponseDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.InstallmentsEntity;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.repository.fee_management.InstallmentRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UtilityClass {

    @Autowired
    public static StudentRepository studentRepository;

    @Autowired
    public static FeeStatementRepository feeStatementRepository;

    @Autowired
    public static InstallmentRepository installmentRepository;



    public static JSONObject getTermDetailsByDate(String searchDate) {
        JSONObject dataObject = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("searchDate", String.valueOf(searchDate))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_term_by_date",formBody);
            JSONObject object = new JSONObject(responseString);
            JSONArray jsonArray = object.getJSONArray("results");
            dataObject  = jsonArray.getJSONObject(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataObject;
    }






    public static JSONObject getAStudentClassDetails(int studentId) {
        JSONObject dataObject = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("studentId", String.valueOf(studentId))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_a_student_class_details",formBody);
            JSONObject object = new JSONObject(responseString);
            JSONArray jsonArray = object.getJSONArray("results");
            dataObject  = jsonArray.getJSONObject(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataObject;
    }






    public static JSONArray getFeeStructureForParticularClassLevel(int academicClassLevelId, int studentResidenceId) {
        JSONArray dataArray = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("academicClassLevelId", String.valueOf(academicClassLevelId))
                .add("studentResidenceId", String.valueOf(studentResidenceId))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_fee_structure_for_particular_class_level",formBody);
            JSONObject object = new JSONObject(responseString);
            dataArray = object.getJSONArray("results");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataArray;
    }






    public static int getAStudentAnnualBalanceFromTermBalance(int studentId, int termBalance, int studentResidenceId) {
        int annualBalance = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        JSONObject classDetailsObject = getAStudentClassDetails(studentId);
        JSONArray feeStructureBreakDownArray = getFeeStructureForParticularClassLevel(classDetailsObject.getInt("AcademicClassLevelId"), studentResidenceId);

        for (int i = 0;i<feeStructureBreakDownArray.length();i++) {
            if(getTermDetailsByDate(dtf.format(now)).getInt("TermIterationId") != feeStructureBreakDownArray.getJSONObject(i).getInt("TermIterationId")) {

                if(getTermDetailsByDate(dtf.format(now)).getInt("TermIterationId") < feeStructureBreakDownArray.getJSONObject(i).getInt("TermIterationId")) {
                    annualBalance = annualBalance + feeStructureBreakDownArray.getJSONObject(i).getInt("FeeAmount");
                }

            }
        }

        return annualBalance + termBalance;
    }






    public static String getNow() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }






    public static String getToday() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }






    public static String getCurrentYear() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }






    public static JSONArray getInstallmentsBetweenACertainPeriod(String startDate, String endDate) {
        JSONArray dataArray = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("startDate", String.valueOf(startDate))
                .add("endDate", String.valueOf(endDate))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_installment_between_certain_period",formBody);
            JSONObject object = new JSONObject(responseString);
            dataArray = object.getJSONArray("results");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataArray;
    }








    public static JSONArray getInstallmentsForParticularStudentBetweenACertainPeriod(int studentId,String startDate, String endDate) {
        JSONArray dataArray = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("studentId", String.valueOf(studentId))
                .add("startDate", String.valueOf(startDate))
                .add("endDate", String.valueOf(endDate))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_installment_for_particular_student_between_certain_period",formBody);
            JSONObject object = new JSONObject(responseString);
            dataArray = object.getJSONArray("results");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataArray;
    }







    public static JSONObject getAStudentResidenceDetails(int studentId) {
        JSONObject dataObject = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("studentId", String.valueOf(studentId))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_a_student_residence_details",formBody);
            JSONObject object = new JSONObject(responseString);
            JSONArray jsonArray = object.getJSONArray("results");
            dataObject  = jsonArray.getJSONObject(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataObject;
    }




    public static FeeStatementResponseDto getAStudentFeeStatementForCurrentYear(int studentId) {

        FeeStatementResponseDto feeStatementResponseDto = new FeeStatementResponseDto();

        StudentEntity studentPersonalDetails = studentRepository.findByStudentId(studentId).get(0);
        JSONObject classDetails = getAStudentClassDetails(studentId);
        JSONObject residenceDetails = getAStudentResidenceDetails(studentId);
        FeeStatementEntity feeStatementEntity = feeStatementRepository.findFeeStatementByStudentId(studentId).get(0);
        List<InstallmentsEntity> feeInstallmentsList = installmentRepository.findInstallmentsByStudentId(studentId);


        feeStatementResponseDto.setStudentId(studentId);
        feeStatementResponseDto.setAdmissionNumber(studentPersonalDetails.getAdmissionNo());
        feeStatementResponseDto.setStudentName(studentPersonalDetails.getStudentName());
        feeStatementResponseDto.setGender(studentPersonalDetails.getGenderId() == 1 ? "Male" : "Female");
        feeStatementResponseDto.setClassDetails(classDetails.getString("AcademicClassLevelName") +" "+classDetails.getString("ClassStreamName"));
        feeStatementResponseDto.setResidenceDetails(residenceDetails.getString("StudentResidenceDescription"));
        feeStatementResponseDto.setTermBalance(feeStatementEntity.getCurrentTermBalance());
        feeStatementResponseDto.setAnnualBalance(feeStatementEntity.getAnnualBalance());
        feeStatementResponseDto.setCurrentyearTotal(feeStatementEntity.getCurrentYearTotal());

        List<InstallmentsResponseDto> installmentsResponseDtoArrayList = new ArrayList<>();

        for(int i = 0;i<feeInstallmentsList.size();i++) {
            if(feeInstallmentsList.get(i).getInstallmentYear().equals(getCurrentYear())) {

                installmentsResponseDtoArrayList.add(new InstallmentsResponseDto(feeInstallmentsList.get(i).getStudentId(),feeInstallmentsList.get(i).getInstallmentAmount(),feeInstallmentsList.get(i).getInstallmentDate(),feeInstallmentsList.get(i).getIsCarryForward(),feeInstallmentsList.get(i).getSessionLogId(),feeInstallmentsList.get(i).getUserSessionActivityId(),feeInstallmentsList.get(i).getInstallmentYear(),getAUserByASessionLogId(feeInstallmentsList.get(i).getSessionLogId()).getString("Name"),getTermDetailsByDate(feeInstallmentsList.get(i).getInstallmentDate()).getString("TermIterationDescription")));

            }
        }

        feeStatementResponseDto.setInstallmentsResponseArray(installmentsResponseDtoArrayList);

        return feeStatementResponseDto;
    }


    public static JSONObject getAUserByASessionLogId(int sessionLogId) {
        JSONObject dataObject = null;
        CustomOkHttp customOkHttp = new CustomOkHttp();

        RequestBody formBody = new FormBody.Builder()
                .add("sessionLogId", String.valueOf(sessionLogId))
                .build();

        try {
            String responseString = customOkHttp.okHttpPostPassingParams(EndPoints.WAONDO_NODE_BASE_URL + "/get_user_by_session_log",formBody);
            JSONObject object = new JSONObject(responseString);
            JSONArray jsonArray = object.getJSONArray("results");
            dataObject  = jsonArray.getJSONObject(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataObject;
    }
}
