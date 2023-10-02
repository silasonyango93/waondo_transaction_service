package com.silasonyango.transactionservice.ingestor.excel;

import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParentPhoneNumbersIngestorHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "phoneNumbers";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<StudentEntity> parseExcelRowsToPojo(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<StudentEntity> studentEntityList = new ArrayList<StudentEntity>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                StudentEntity studentEntity = new StudentEntity();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 1:
                            studentEntity.setAdmissionNo(String.format("%s", (int) currentCell.getNumericCellValue()));
                            break;

                        case 2:
                            studentEntity.setStudentName(currentCell.getStringCellValue());
                            break;
                        case 5:
                            studentEntity.setParentPhoneNumber(String.format("%s", (int) currentCell.getNumericCellValue()).trim());
                            break;

                        default:
                            break;
                    }
                    cellIdx++;
                }
                studentEntityList.add(studentEntity);
            }
            workbook.close();
            return studentEntityList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
