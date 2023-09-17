package com.silasonyango.transactionservice.services.excel;

import com.silasonyango.transactionservice.services.excel.students.StudentFeeBalancesPerClassStreamExcelService;
import com.silasonyango.transactionservice.services.fee_statement.FeeStatementService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.silasonyango.transactionservice.common.config.UtilityConfigs.PER_STREAM_FEE_BALANCE_EXCEL_SHEET_NAME;

@Service
public class ExcelService {

    @Autowired
    StudentFeeBalancesPerClassStreamExcelService studentFeeBalancesPerClassStreamExcelService;

    @Autowired
    FeeStatementService feeStatementService;

    private XSSFWorkbook workbook;

    public void processData(int classId, String sheetTitle) {
        workbook.createSheet(PER_STREAM_FEE_BALANCE_EXCEL_SHEET_NAME);
        workbook = studentFeeBalancesPerClassStreamExcelService
                .processData(workbook, sheetTitle, feeStatementService
                        .fetchStudentFeeBalancesPerClassStream(classId).getStudentEntities());
    }

    public void export(HttpServletResponse response, int classId, String sheetTitle) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(classId, sheetTitle);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
