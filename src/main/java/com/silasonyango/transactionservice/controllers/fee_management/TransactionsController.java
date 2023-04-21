package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.common.config.EndPoints;
import com.silasonyango.transactionservice.dtos.fee_management.transactions.TransactionByDateRequestDto;
import com.silasonyango.transactionservice.dtos.fee_management.transactions.TransactionsByDateRangeRequestDto;
import com.silasonyango.transactionservice.repository.fee_management.TransactionsRepository;
import com.silasonyango.transactionservice.services.retrofit.RetrofitClientInstance;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.fee_structure.ClassFeeStructureServiceModel;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.fee_structure.FeeStructureService;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.transactions.TransactionsResponseDto;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.transactions.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @PostMapping("/transactions_by_date")
    public List<TransactionsResponseDto> fetchTransactionsByDate(@RequestBody TransactionByDateRequestDto transactionByDateRequestDto) {
        TransactionsService transactionsService = RetrofitClientInstance.getRetrofitInstance(EndPoints.WAONDO_NODE_BASE_URL+"/").create(TransactionsService.class);
        Call<List<TransactionsResponseDto>> callSync = transactionsService.fetchTransactionsByDate(transactionByDateRequestDto.getTransactionDate());
        try {
            Response<List<TransactionsResponseDto>> response = callSync.execute();
            return response.body();
        } catch (Exception ex) {}

        return null;
    }

    @PostMapping("/transactions_by_date_range")
    public List<TransactionsResponseDto> fetchTransactionsByDateRange(@RequestBody TransactionsByDateRangeRequestDto transactionsByDateRangeRequestDto) {
        TransactionsService transactionsService = RetrofitClientInstance.getRetrofitInstance(EndPoints.WAONDO_NODE_BASE_URL+"/").create(TransactionsService.class);
        Call<List<TransactionsResponseDto>> callSync = transactionsService.fetchTransactionsByDateRange(transactionsByDateRangeRequestDto.getTransactionsStartDate(),transactionsByDateRangeRequestDto.getTransactionsEndDate());
        try {
            Response<List<TransactionsResponseDto>> response = callSync.execute();
            return response.body();
        } catch (Exception ex) {}

        return null;
    }
}
