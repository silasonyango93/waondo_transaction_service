package com.silasonyango.transactionservice.services.excel.students;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.silasonyango.transactionservice.common.config.UtilityConfigs.STUDENTS_PER_LOT_EXCEL_SHEET_NAME;

@Service
public class StudentsPerLotExcelExportService {
    private XSSFWorkbook writeHeaderLine(int rowNum, String sheetTitle, XSSFWorkbook workbook, String sheetName
            , boolean hasABlankSucceedingColumnToBeFilled) {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Row titleRow = sheet.createRow(rowNum);
        Row tableHeaderRow = sheet.createRow(rowNum + 3);
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 10000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 4000);
        if (hasABlankSucceedingColumnToBeFilled) {
            sheet.setColumnWidth(5, 10000);
        }

        CellStyle titleStyle = workbook.createCellStyle();
        XSSFFont titleFont = workbook.createFont();
        titleFont.setFontHeight(18);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);

        CellStyle tableHeaderStyle = workbook.createCellStyle();
        XSSFFont tableHeaderFont = workbook.createFont();
        tableHeaderFont.setFontHeight(16);
        tableHeaderFont.setBold(true);
        tableHeaderStyle.setFont(tableHeaderFont);

        createCell(titleRow, 0, sheetTitle.toUpperCase(), titleStyle);
        createCell(tableHeaderRow, 0, "#", tableHeaderStyle);
        createCell(tableHeaderRow, 1, "Adm No.", tableHeaderStyle);
        createCell(tableHeaderRow, 2, "Name", tableHeaderStyle);
        createCell(tableHeaderRow, 3, "Gender", tableHeaderStyle);
        createCell(tableHeaderRow, 4, "Stream", tableHeaderStyle);
        if (hasABlankSucceedingColumnToBeFilled) {
            createCell(tableHeaderRow, 5, "Parent Phone No.", tableHeaderStyle);
        }
        return workbook;
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        //sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private XSSFWorkbook writeDataLines(List<Map<String, Object>> dataList, int rowCount, XSSFWorkbook workbook
            , String sheetName, boolean hasABlankSucceedingColumnToBeFilled) {

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int counter = 1;
        for (Map<String, Object> map : dataList) {
            Row sheetRow = sheet.createRow(rowCount++);
            createCell(sheetRow, 0, counter, style);
            createCell(sheetRow, 1, map.get("AdmissionNo"), style);
            createCell(sheetRow, 2, map.get("StudentName").toString().toUpperCase(), style);
            createCell(sheetRow, 3, map.get("GenderDescription").toString().toUpperCase(), style);
            createCell(sheetRow, 4, String.format("%s%s", map.get("AcademicClassLevelName")
                    , map.get("ClassStreamName")), style);
            if (hasABlankSucceedingColumnToBeFilled) {
                createCell(sheetRow, 5, "0", style);
            }
            counter++;
        }


        return workbook;
    }


    public XSSFWorkbook processData(XSSFWorkbook workbook, String sheetTitle, List<Map<String, Object>> dataList
            , boolean hasABlankSucceedingColumnToBeFilled) {
        XSSFSheet sheet = workbook.getSheet(STUDENTS_PER_LOT_EXCEL_SHEET_NAME);
        workbook = writeHeaderLine(sheet.getLastRowNum() + 2, sheetTitle, workbook
                , STUDENTS_PER_LOT_EXCEL_SHEET_NAME, hasABlankSucceedingColumnToBeFilled);
        workbook = writeDataLines(dataList, sheet.getLastRowNum() + 1, workbook
                , STUDENTS_PER_LOT_EXCEL_SHEET_NAME, hasABlankSucceedingColumnToBeFilled);
        return workbook;
    }
}
