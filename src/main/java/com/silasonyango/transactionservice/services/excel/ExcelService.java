package com.silasonyango.transactionservice.services.excel;

import com.silasonyango.transactionservice.services.excel.students.StudentFeeBalancesPerClassStreamExcelService;
import com.silasonyango.transactionservice.services.excel.students.StudentFeeBalancesPerLotExcelService;
import com.silasonyango.transactionservice.services.fee_statement.FeeStatementService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.silasonyango.transactionservice.common.config.UtilityConfigs.PER_LOT_FEE_BALANCE_EXCEL_SHEET_NAME;
import static com.silasonyango.transactionservice.common.config.UtilityConfigs.PER_STREAM_FEE_BALANCE_EXCEL_SHEET_NAME;

@Service
public class ExcelService {

    @Autowired
    StudentFeeBalancesPerClassStreamExcelService studentFeeBalancesPerClassStreamExcelService;

    @Autowired
    StudentFeeBalancesPerLotExcelService studentFeeBalancesPerLotExcelService;

    @Autowired
    FeeStatementService feeStatementService;

    private XSSFWorkbook workbook;

    public void processData(int fetchKey, String sheetTitle, ExcelServiceEnum excelServiceEnum) {
        switch (excelServiceEnum) {
            case FEE_BALANCES_PER_CLASS:
                workbook.createSheet(PER_STREAM_FEE_BALANCE_EXCEL_SHEET_NAME);
                workbook = studentFeeBalancesPerClassStreamExcelService
                        .processData(workbook, sheetTitle, feeStatementService
                                .fetchStudentFeeBalancesPerClassStream(fetchKey).getStudentEntities());
                break;
            case FEE_BALANCES_PER_LOT:
                workbook.createSheet(PER_LOT_FEE_BALANCE_EXCEL_SHEET_NAME);
                workbook = studentFeeBalancesPerLotExcelService.processData(workbook, sheetTitle,
                        feeStatementService.fetchStudentFeeBalancesForASpecificLot(fetchKey));
                break;

        }
    }

    public void exportFeeBalancesPerClassExcel(HttpServletResponse response, int classId, String sheetTitle) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(classId, sheetTitle, ExcelServiceEnum.FEE_BALANCES_PER_CLASS);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void exportFeeBalancesPerLotExcel(HttpServletResponse response, int lotId, String sheetTitle) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(lotId, sheetTitle, ExcelServiceEnum.FEE_BALANCES_PER_LOT);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
