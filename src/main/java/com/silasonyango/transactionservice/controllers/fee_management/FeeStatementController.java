package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.dtos.api_response.SuccessFailureResponseDto;
import com.silasonyango.transactionservice.dtos.fee_management.ClassFeeBalanceRequestDto;
import com.silasonyango.transactionservice.dtos.fee_management.FeeBalanceListDto;
import com.silasonyango.transactionservice.dtos.fee_management.FeeBalanceRequestDto;
import com.silasonyango.transactionservice.entity_classes.fee_management.FeeStatementEntity;
import com.silasonyango.transactionservice.repository.fee_management.FeeStatementRepository;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/statements")
public class FeeStatementController {
    @Autowired
    FeeStatementRepository feeStatementRepository;

    @PostMapping("/create_fee_statement")
    public SuccessFailureResponseDto createAFeeStatement(@Valid FeeStatementEntity feeStatementEntity) {

        feeStatementRepository.save(feeStatementEntity);

        return new SuccessFailureResponseDto(true,"Fee statement successfully created","N/A");
    }


    @PostMapping("/get_all_students_with_minimum_term_balance")
    public List<FeeBalanceListDto> getAllStudentsWithAMinimumTermBalance(@Valid FeeBalanceRequestDto feeBalanceRequestDto) {

        JSONArray dataArray = UtilityClass.getAllStudentsWithAMinimumTermBalance(feeBalanceRequestDto.getMinimumFeeBalance());

        List<FeeBalanceListDto> feeBalanceListDtoList = new ArrayList<>();

        for (int i = 0; i < dataArray.length(); i++) {
            feeBalanceListDtoList.add(new FeeBalanceListDto(dataArray.getJSONObject(i).getInt("StudentId"), dataArray.getJSONObject(i).getString("AdmissionNo"), dataArray.getJSONObject(i).getString("StudentName"), dataArray.getJSONObject(i).getString("GenderDescription"), dataArray.getJSONObject(i).getString("AcademicClassLevelName") +" "+dataArray.getJSONObject(i).getString("ClassStreamName"), dataArray.getJSONObject(i).getString("StudentResidenceDescription"), dataArray.getJSONObject(i).getInt("CurrentYearTotal"), dataArray.getJSONObject(i).getInt("CurrentTermBalance"), dataArray.getJSONObject(i).getInt("AnnualBalance")));
        }

        return feeBalanceListDtoList;
    }


    @PostMapping("/get_all_students_in_a_class_with_minimum_term_balance")
    public List<FeeBalanceListDto> getAllStudentsInAClassWithAMinimumTermBalance(@Valid ClassFeeBalanceRequestDto classFeeBalanceRequestDto) {

        JSONArray dataArray = UtilityClass.getAllStudentsInAClassWithAMinimumTermBalance(classFeeBalanceRequestDto.getClassId(),classFeeBalanceRequestDto.getMinimumFeeBalance());

        List<FeeBalanceListDto> feeBalanceListDtoList = new ArrayList<>();

        for (int i = 0; i < dataArray.length(); i++) {
            feeBalanceListDtoList.add(new FeeBalanceListDto(dataArray.getJSONObject(i).getInt("StudentId"), dataArray.getJSONObject(i).getString("AdmissionNo"), dataArray.getJSONObject(i).getString("StudentName"), dataArray.getJSONObject(i).getString("GenderDescription"), dataArray.getJSONObject(i).getString("AcademicClassLevelName") +" "+dataArray.getJSONObject(i).getString("ClassStreamName"), dataArray.getJSONObject(i).getString("StudentResidenceDescription"), dataArray.getJSONObject(i).getInt("CurrentYearTotal"), dataArray.getJSONObject(i).getInt("CurrentTermBalance"), dataArray.getJSONObject(i).getInt("AnnualBalance")));
        }

        return feeBalanceListDtoList;
    }
}
