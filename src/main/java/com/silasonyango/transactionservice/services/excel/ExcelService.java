package com.silasonyango.transactionservice.services.excel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class ExcelService {

    private XSSFWorkbook workbook;

    public void processData(int countyId, int wealthGroupId) {

    }

    public void export(HttpServletResponse response, int countyId, int wealthGroupId) throws IOException {
        this.workbook = new XSSFWorkbook();
        processData(countyId,wealthGroupId);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
