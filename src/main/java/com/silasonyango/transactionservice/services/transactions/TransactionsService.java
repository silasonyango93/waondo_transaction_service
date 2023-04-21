package com.silasonyango.transactionservice.services.transactions;

import com.silasonyango.transactionservice.repository.fee_management.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {

    @Autowired
    TransactionsRepository transactionsRepository;

    public boolean deleteTransactionsByStudentId(int studentId) {
        try {
            transactionsRepository.deleteByStudentId(studentId);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
