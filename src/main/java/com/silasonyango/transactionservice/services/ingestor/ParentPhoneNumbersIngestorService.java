package com.silasonyango.transactionservice.services.ingestor;

import com.silasonyango.transactionservice.entity_classes.student_management.StudentEntity;
import com.silasonyango.transactionservice.ingestor.excel.ParentPhoneNumbersIngestorHelper;
import com.silasonyango.transactionservice.repository.student_management.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParentPhoneNumbersIngestorService {
    @Autowired
    StudentRepository studentRepository;

    public void updateParentPhoneNumbers(MultipartFile file) {
        try {
            List<StudentEntity> updatedStudentsList = new ArrayList<>();
            List<StudentEntity> ingestedRows = ParentPhoneNumbersIngestorHelper
                    .parseExcelRowsToPojo(file.getInputStream());
            for (StudentEntity row : ingestedRows) {
                if (!row.getAdmissionNo().isEmpty() && !row.getParentPhoneNumber().isEmpty()
                        && !row.getParentPhoneNumber().equals("0")) {
                    List<StudentEntity> studentsList = studentRepository.findByAdmissionNo(row.getAdmissionNo());
                    if (!studentsList.isEmpty()) {
                        StudentEntity student = studentsList.get(0);
                        student.setParentPhoneNumber(String.format("+254%s", row.getParentPhoneNumber()));
                        updatedStudentsList.add(student);
                    }
                }
            }
            studentRepository.saveAll(updatedStudentsList);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
