package com.silasonyango.transactionservice.services.residence;

import com.silasonyango.transactionservice.repository.student_residence.ResidenceSwapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResidenceSwapService {

    @Autowired
    ResidenceSwapRepository residenceSwapRepository;

    public boolean deleteResidenceSwapByStudentId(int studentId) {
        try {
            residenceSwapRepository.deleteByStudentId(studentId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
