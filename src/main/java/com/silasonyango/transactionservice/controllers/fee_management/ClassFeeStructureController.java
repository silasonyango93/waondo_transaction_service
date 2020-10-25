package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.common.config.EndPoints;
import com.silasonyango.transactionservice.dtos.fee_management.fee_structure.ClassFeeStructureModel;
import com.silasonyango.transactionservice.dtos.fee_management.fee_structure.FeeStructureRequestDto;
import com.silasonyango.transactionservice.services.retrofit.RetrofitClientInstance;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.fee_structure.ClassFeeStructureServiceModel;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.fee_structure.FeeStructureService;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.user_management.AllUsersResponse;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.user_management.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/class_fee_structure")
public class ClassFeeStructureController {

    @PostMapping("/retrieve_class_fee_structures_of_a_fee_structure")
    public void retrieveClassFeeStructuresOfAFeeStructure(@Valid FeeStructureRequestDto feeStructureRequestDto) {
        List<ClassFeeStructureModel> classFeeStructureModelList = new ArrayList<>();
        List<ClassFeeStructureServiceModel> classFeeStructureServiceModelList = getClassFeeStructuresOfAParticularFeeStructure(feeStructureRequestDto.getFeeStructureId());
        System.out.println();
    }

    public List<ClassFeeStructureServiceModel> getClassFeeStructuresOfAParticularFeeStructure(int feeStructureId) {
        FeeStructureService feeStructureService = RetrofitClientInstance.getRetrofitInstance(EndPoints.WAONDO_NODE_BASE_URL+"/").create(FeeStructureService.class);
        Call<List<ClassFeeStructureServiceModel>> callSync = feeStructureService.getClassFeeStructuresOfAParticularFeeStructure(feeStructureId);
        try {
            Response<List<ClassFeeStructureServiceModel>> response = callSync.execute();
            return response.body();
        } catch (Exception ex) {}

        return null;
    }
}
