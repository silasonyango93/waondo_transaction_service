package com.silasonyango.transactionservice.services.excel;

import com.silasonyango.transactionservice.services.excel.students.StudentFeeBalancesPerClassStreamExcelService;
import com.silasonyango.transactionservice.services.excel.students.StudentFeeBalancesPerLotExcelService;
import com.silasonyango.transactionservice.services.excel.students.StudentsPerClassStreamExcelExportService;
import com.silasonyango.transactionservice.services.excel.students.StudentsPerLotExcelExportService;
import com.silasonyango.transactionservice.services.fee_management.FeeInstallmentsService;
import com.silasonyango.transactionservice.services.fee_management.FeeStatementService;
import com.silasonyango.transactionservice.services.student.StudentsService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.silasonyango.transactionservice.common.config.UtilityConfigs.*;

@Service
public class ExcelService {

    @Autowired
    StudentFeeBalancesPerClassStreamExcelService studentFeeBalancesPerClassStreamExcelService;

    @Autowired
    StudentFeeBalancesPerLotExcelService studentFeeBalancesPerLotExcelService;

    @Autowired
    StudentsPerLotExcelExportService studentsPerLotExcelExportService;

    @Autowired
    StudentsPerClassStreamExcelExportService studentsPerClassStreamExcelExportService;

    @Autowired
    FeeStatementService feeStatementService;

    @Autowired
    StudentsService studentsService;

    @Autowired
    FeeInstallmentsService feeInstallmentsService;

    private XSSFWorkbook workbook;

    public void processData(int fetchKey, String sheetTitle, ExcelServiceEnum excelServiceEnum
            , boolean hasABlankSucceedingColumnToBeFilled, int thresholdAmount, String startDate, String endDate) {
        switch (excelServiceEnum) {
            case FEE_BALANCES_PER_CLASS:
                workbook.createSheet(PER_STREAM_FEE_BALANCE_EXCEL_SHEET_NAME);
                workbook = studentFeeBalancesPerClassStreamExcelService
                        .processData(workbook, sheetTitle, feeStatementService
                                .fetchStudentFeeBalancesPerClassStream(fetchKey));
                break;
            case FEE_BALANCES_PER_LOT:
                workbook.createSheet(PER_LOT_FEE_BALANCE_EXCEL_SHEET_NAME);
                workbook = studentFeeBalancesPerLotExcelService.processData(workbook, sheetTitle,
                        feeStatementService.fetchStudentFeeBalancesForASpecificLot(fetchKey));
                break;
            case STUDENTS_PER_LOT:
                workbook.createSheet(STUDENTS_PER_LOT_EXCEL_SHEET_NAME);
                workbook = studentsPerLotExcelExportService.processData(workbook, sheetTitle
                        , studentsService.fetchStudentsOfAParticularLot(fetchKey), hasABlankSucceedingColumnToBeFilled);
                break;
            case STUDENT_PER_CLASS_STREAM:
                workbook.createSheet(STUDENTS_PER_CLASS_STREAM_EXCEL_SHEET_NAME);
                workbook = studentsPerClassStreamExcelExportService.processData(workbook, sheetTitle
                        , studentsService.fetchStudentsOfAParticularClassStream(fetchKey));
                break;
            case LOT_FEE_BALANCE_WITH_TERM_BALANCE_THRESHOLD:
                workbook.createSheet(PER_LOT_FEE_BALANCE_EXCEL_SHEET_NAME);
                workbook = studentFeeBalancesPerLotExcelService.processData(workbook, sheetTitle,
                        feeStatementService
                                .fetchFeeBalancesForASpecificLotWithTermBalanceGreaterThanOrEqualProvidedAmount(fetchKey, thresholdAmount));
                break;
            case CLASS_STREAM_FEE_BALANCE_WITH_TERM_BALANCE_THRESHOLD:
                workbook.createSheet(PER_STREAM_FEE_BALANCE_EXCEL_SHEET_NAME);
                workbook = studentFeeBalancesPerClassStreamExcelService
                        .processData(workbook, sheetTitle, feeStatementService
                                .fetchFeeBalancesForASpecificClassWithTermBalanceGreaterThanOrEqualProvidedAmount(fetchKey, thresholdAmount));
                break;
            case FEE_INSTALLMENTS_MADE_TODAY:
                workbook.createSheet(PER_LOT_FEE_BALANCE_EXCEL_SHEET_NAME);
                workbook = studentFeeBalancesPerLotExcelService.processData(workbook, sheetTitle,
                        feeInstallmentsService.fetchFeeInstallmentsMadeToday());
                break;
            case FEE_INSTALLMENTS_MADE_BETWEEN_TWO_DATES:
                workbook.createSheet(PER_LOT_FEE_BALANCE_EXCEL_SHEET_NAME);
                workbook = studentFeeBalancesPerLotExcelService.processData(workbook, sheetTitle,
                        feeInstallmentsService.fetchFeeInstallmentsMadeBetweenTwoDates(startDate, endDate));
                break;
            case FEE_INSTALLMENTS_MADE_ON_A_SPECIFIC_DATE:
                workbook.createSheet(PER_LOT_FEE_BALANCE_EXCEL_SHEET_NAME);
                workbook = studentFeeBalancesPerLotExcelService.processData(workbook, sheetTitle,
                        feeInstallmentsService.fetchFeeInstallmentsMadeOnASpecificDate(startDate));
                break;

        }
    }

    public void exportFeeBalancesPerClassExcel(HttpServletResponse response, int classId, String sheetTitle) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(classId, sheetTitle, ExcelServiceEnum.FEE_BALANCES_PER_CLASS, false, 0, null, null);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void exportFeeBalancesPerLotExcel(HttpServletResponse response, int lotId, String sheetTitle) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(lotId, sheetTitle, ExcelServiceEnum.FEE_BALANCES_PER_LOT, false, 0, null, null);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void processStudentsPerLotExcelExport(HttpServletResponse response, int lotId, String sheetTitle) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(lotId, sheetTitle, ExcelServiceEnum.STUDENTS_PER_LOT, false, 0, null, null);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void processStudentsPerClassStreamExcelExport(HttpServletResponse response, int classId, String sheetTitle) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(classId, sheetTitle, ExcelServiceEnum.STUDENT_PER_CLASS_STREAM, false, 0, null, null);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void processStudentsPerLotWithPhoneNoColumnExcelExport(HttpServletResponse response, int lotId, String sheetTitle) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(lotId, sheetTitle, ExcelServiceEnum.STUDENTS_PER_LOT, true, 0, null, null);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void exportFeeBalancesPerLotWithTermThresholdExcel(HttpServletResponse response, int lotId, String sheetTitle, int thresholdAmount) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(lotId, sheetTitle, ExcelServiceEnum.LOT_FEE_BALANCE_WITH_TERM_BALANCE_THRESHOLD, false, thresholdAmount, null, null);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void exportFeeBalancesPerClassStreamWithTermThresholdExcel(HttpServletResponse response, int lotId, String sheetTitle, int thresholdAmount) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(lotId, sheetTitle, ExcelServiceEnum.CLASS_STREAM_FEE_BALANCE_WITH_TERM_BALANCE_THRESHOLD, false, thresholdAmount, null, null);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


    public void exportInstallmentsMadeTodayExcel(HttpServletResponse response, String sheetTitle) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(0, sheetTitle, ExcelServiceEnum.FEE_INSTALLMENTS_MADE_TODAY, false, 0, null, null);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void exportInstallmentsMadeBetweenTwoDatesExcel(HttpServletResponse response, String sheetTitle
            , String startDate, String endDate) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(0, sheetTitle, ExcelServiceEnum.FEE_INSTALLMENTS_MADE_BETWEEN_TWO_DATES
                , false, 0, startDate, endDate);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void exportInstallmentsMadeOnASpecificDateExcel(HttpServletResponse response, String sheetTitle
            , String installmentDate) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(0, sheetTitle, ExcelServiceEnum.FEE_INSTALLMENTS_MADE_ON_A_SPECIFIC_DATE
                , false, 0, installmentDate, null);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
