package com.silasonyango.transactionservice.controllers.fee_management;

import com.lowagie.text.DocumentException;
import com.silasonyango.transactionservice.dtos.api_response.SuccessFailureResponseDto;
import com.silasonyango.transactionservice.dtos.fee_management.ClassFeeBalanceRequestDto;
import com.silasonyango.transactionservice.dtos.fee_management.FeeBalanceListDto;
import com.silasonyango.transactionservice.dtos.fee_management.FeeBalanceRequestDto;
import com.silasonyango.transactionservice.dtos.fee_management.FeeReminderForListOfStudentsDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import com.silasonyango.transactionservice.services.academic_classes.AcademicClassesService;
import com.silasonyango.transactionservice.services.excel.ExcelService;
import com.silasonyango.transactionservice.services.fee_management.FeeReminderService;
import com.silasonyango.transactionservice.services.fee_management.FeeStatementService;
import com.silasonyango.transactionservice.services.pdf.FeeStatementPdfService;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import com.silasonyango.transactionservice.utility_classes.Utils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statements")
public class FeeStatementController {
    @Autowired
    FeeStatementRepository feeStatementRepository;

    @Autowired
    FeeStatementPdfService feeStatementPdfService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    FeeStatementService feeStatementService;

    @Autowired
    AcademicClassesService academicClassesService;

    @Autowired
    ExcelService excelService;

    @Autowired
    FeeReminderService feeReminderService;

    @PostMapping("/create_fee_statement")
    public SuccessFailureResponseDto createAFeeStatement(@Valid FeeStatementEntity feeStatementEntity) {

        feeStatementRepository.save(feeStatementEntity);

        return new SuccessFailureResponseDto(true, "Fee statement successfully created", "N/A");
    }


    @PostMapping("/get_all_students_with_minimum_term_balance")
    public List<FeeBalanceListDto> getAllStudentsWithAMinimumTermBalance(@Valid FeeBalanceRequestDto feeBalanceRequestDto) {
        List<FeeBalanceListDto> feeBalanceListDtoList = new ArrayList<>();

        List<Map<String, Object>> balancesMapList = feeStatementService.fetchFeeBalancesForEntireSchoolWithTermBalanceGreaterThanOrEqualProvidedAmount(
                feeBalanceRequestDto.getMinimumFeeBalance());

        for (Map<String, Object> map : balancesMapList) {
            feeBalanceListDtoList.add(new FeeBalanceListDto(
                    Integer.parseInt(String.valueOf(map.get("StudentId"))),
                    String.valueOf(map.get("AdmissionNo")),
                    String.valueOf(map.get("StudentName")),
                    String.valueOf(map.get("GenderDescription")),
                    String.format("%s%s", map.get("AcademicClassLevelName"), map.get("ClassStreamName")),
                    String.format("%s", map.get("StudentResidenceDescription")),
                    Integer.parseInt(String.valueOf(map.get("CurrentYearTotal"))),
                    Integer.parseInt(String.valueOf(map.get("CurrentTermBalance"))),
                    Integer.parseInt(String.valueOf(map.get("AnnualBalance")))
            ));
        }

        return feeBalanceListDtoList;
    }


    @PostMapping("/get_all_students_in_a_class_with_minimum_term_balance")
    public List<FeeBalanceListDto> getAllStudentsInAClassWithAMinimumTermBalance(@Valid ClassFeeBalanceRequestDto classFeeBalanceRequestDto) {
        List<FeeBalanceListDto> feeBalanceListDtoList = new ArrayList<>();

        List<Map<String, Object>> balancesMapList = feeStatementService.fetchFeeBalancesForASpecificClassWithTermBalanceGreaterThanOrEqualProvidedAmount(
                classFeeBalanceRequestDto.getClassId(), classFeeBalanceRequestDto.getMinimumFeeBalance());

        for (Map<String, Object> map : balancesMapList) {
            feeBalanceListDtoList.add(new FeeBalanceListDto(
                    Integer.parseInt(String.valueOf(map.get("StudentId"))),
                    String.valueOf(map.get("AdmissionNo")),
                    String.valueOf(map.get("StudentName")),
                    String.valueOf(map.get("GenderDescription")),
                    String.format("%s%s", map.get("AcademicClassLevelName"), map.get("ClassStreamName")),
                    String.format("%s", map.get("StudentResidenceDescription")),
                    Integer.parseInt(String.valueOf(map.get("CurrentYearTotal"))),
                    Integer.parseInt(String.valueOf(map.get("CurrentTermBalance"))),
                    Integer.parseInt(String.valueOf(map.get("AnnualBalance")))
            ));
        }

        return feeBalanceListDtoList;
    }


    @GetMapping("/fee-balance/get_all_students_in_a_lot_with_minimum_term_balance")
    public List<FeeBalanceListDto> getAllStudentsInALotWithAMinimumTermBalance(@RequestParam("lotId") int lotId
            , @RequestParam("minimumFeeBalance") int minimumFeeBalance) {
        List<FeeBalanceListDto> feeBalanceListDtoList = new ArrayList<>();

        List<Map<String, Object>> balancesMapList = feeStatementService
                .fetchFeeBalancesForASpecificLotWithTermBalanceGreaterThanOrEqualProvidedAmount(
                        lotId, minimumFeeBalance);

        for (Map<String, Object> map : balancesMapList) {
            feeBalanceListDtoList.add(new FeeBalanceListDto(
                    Integer.parseInt(String.valueOf(map.get("StudentId"))),
                    String.valueOf(map.get("AdmissionNo")),
                    String.valueOf(map.get("StudentName")),
                    String.valueOf(map.get("GenderDescription")),
                    String.format("%s%s", map.get("AcademicClassLevelName"), map.get("ClassStreamName")),
                    String.format("%s", map.get("StudentResidenceDescription")),
                    Integer.parseInt(String.valueOf(map.get("CurrentYearTotal"))),
                    Integer.parseInt(String.valueOf(map.get("CurrentTermBalance"))),
                    Integer.parseInt(String.valueOf(map.get("AnnualBalance")))
            ));
        }

        return feeBalanceListDtoList;
    }


    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response, @RequestParam("studentId") int studentId) throws DocumentException, IOException, URISyntaxException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());

        String fileName = studentRepository.findByStudentId(studentId).get(0).getStudentName() + " " + currentDateTime + " Fee Statement";

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + fileName + ".pdf";
        response.setHeader(headerKey, headerValue);

        feeStatementPdfService.export(response, studentId);

    }


    @GetMapping(value = "/fee-balances-per-class/{classId}")
    public Map<String, Object> testGet(@PathVariable int classId) {
        return academicClassesService.fetchClassByItsFullName(classId);
    }


    @GetMapping("/excel/fee-balances-per-class/{classId}")
    public void exportFeeBalancesPerClassExcel(HttpServletResponse response, @PathVariable int classId) throws IOException {
        try {

            Map<String, Object> fullClassNameMap = academicClassesService.fetchClassByItsFullName(classId);
            String fileName = String.format("FORM %s%s STUDENT FEE BALANCES.", fullClassNameMap.get("AcademicClassLevelName")
                    , fullClassNameMap.get("ClassStreamName"));
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + fileName + ".xlsx";
            response.setHeader(headerKey, headerValue);

            excelService.exportFeeBalancesPerClassExcel(response, classId, fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/excel/fee-balances-per-lot/{lotId}")
    public void exportFeeBalancesPerLotExcel(HttpServletResponse response, @PathVariable int lotId) throws IOException {
        try {
            Map<String, Object> fullLotNameMap = academicClassesService.fetchLotByItsFullName(lotId);
            String fileName = String.format("FORM %s STUDENT FEE BALANCES.", fullLotNameMap.get("AcademicClassLevelName"));
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + fileName + ".xlsx";
            response.setHeader(headerKey, headerValue);

            excelService.exportFeeBalancesPerLotExcel(response, lotId, fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/excel/fee-balances-per-lot-with-term-threshold")
    public void exportFeeBalancesPerLotWithTermThresholdExcel(HttpServletResponse response
            , @RequestParam("lotId") int lotId, @RequestParam("termBalanceThresholdAmount") int termBalanceThresholdAmount) throws IOException {
        try {
            Map<String, Object> fullLotNameMap = academicClassesService.fetchLotByItsFullName(lotId);
            String fileName = String.format("FORM %s STUDENTS WITH FEE BALANCES OF KES %s AND ABOVE."
                    , fullLotNameMap.get("AcademicClassLevelName"), Utils.formatIntegerToCommaSeperatedValue(termBalanceThresholdAmount));
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + fileName + ".xlsx";
            response.setHeader(headerKey, headerValue);
            excelService.exportFeeBalancesPerLotWithTermThresholdExcel(response, lotId, fileName, termBalanceThresholdAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/excel/fee-balances-per-class-stream-with-term-threshold")
    public void exportFeeBalancesPerClassStramWithTermThresholdExcel(HttpServletResponse response
            , @RequestParam("classId") int classId, @RequestParam("termBalanceThresholdAmount") int termBalanceThresholdAmount) throws IOException {
        try {
            Map<String, Object> fullClassNameMap = academicClassesService.fetchClassByItsFullName(classId);
            String fileName = String.format("FORM %s%s STUDENTS WITH FEE BALANCES OF KES %s AND ABOVE.", fullClassNameMap.get("AcademicClassLevelName")
                    , fullClassNameMap.get("ClassStreamName"), Utils.formatIntegerToCommaSeperatedValue(termBalanceThresholdAmount));
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=" + fileName + ".xlsx";
            response.setHeader(headerKey, headerValue);
            excelService.exportFeeBalancesPerClassStreamWithTermThresholdExcel(response, classId, fileName, termBalanceThresholdAmount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/sms/send-fee-reminder-to-specific-lot-with-threshold")
    public ResponseEntity<String> sendFeeReminderToSpecificLotWithThreshold(@RequestParam("lotId") int lotId
            , @RequestParam("feeBalanceThreshold") int feeBalanceThreshold
            , @RequestParam("paymentDeadlineDate") String paymentDeadlineDate) {
        if (feeReminderService.sendSmsFeeReminderForSpecificLot(lotId, feeBalanceThreshold, paymentDeadlineDate)) {
            return new ResponseEntity<String>("Fee reminder sent successfully to all concerned parties"
                    , HttpStatus.valueOf(200));
        }
        return new ResponseEntity<String>("Problem encountered while sending the messages"
                , HttpStatus.valueOf(500));
    }

    @GetMapping("/sms/send-fee-reminder-to-specific-class-stream-with-threshold")
    public ResponseEntity<String> sendFeeReminderToSpecificClassStreamWithThreshold(@RequestParam("classId") int classId
            , @RequestParam("feeBalanceThreshold") int feeBalanceThreshold
            , @RequestParam("paymentDeadlineDate") String paymentDeadlineDate) {
        if (feeReminderService.sendSmsFeeReminderForSpecificClassStream(classId, feeBalanceThreshold, paymentDeadlineDate)) {
            return new ResponseEntity<String>("Fee reminder sent successfully to all concerned parties"
                    , HttpStatus.valueOf(200));
        }
        return new ResponseEntity<String>("Problem encountered while sending the messages"
                , HttpStatus.valueOf(500));
    }

    @PostMapping("/sms/send-fee-reminder-to-specific-list-of-students-with-threshold")
    public ResponseEntity<String> sendFeeReminderToSpecificListOfStudentsWithThreshold(
            @RequestBody FeeReminderForListOfStudentsDto feeBalanceThreshold) {
        if (feeReminderService.sendSmsFeeReminderForSpecificListOfStudents(feeBalanceThreshold.getStudentIds()
                , feeBalanceThreshold.getDeadlineDate())) {
            return new ResponseEntity<String>("Fee reminder sent successfully to all concerned parties"
                    , HttpStatus.valueOf(200));
        }
        return new ResponseEntity<String>("Problem encountered while sending the messages"
                , HttpStatus.valueOf(500));
    }
}
