package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.common.config.EndPoints;
import com.silasonyango.transactionservice.dtos.fee_management.fee_structure.*;
import com.silasonyango.transactionservice.services.retrofit.RetrofitClientInstance;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.fee_structure.ClassFeeStructureServiceModel;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.fee_management.fee_structure.FeeStructureService;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.user_management.AllUsersResponse;
import com.silasonyango.transactionservice.services.retrofit.waondo_node.user_management.UsersService;
import org.apache.tomcat.util.codec.binary.Base64;
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
    public List<ClassFeeStructureModel> retrieveClassFeeStructuresOfAFeeStructure(@Valid FeeStructureRequestDto feeStructureRequestDto) {
        List<ClassFeeStructureModel> classFeeStructureModelList = new ArrayList<>();
        List<ClassFeeStructureServiceModel> classFeeStructureServiceModelList = getClassFeeStructuresOfAParticularFeeStructure(feeStructureRequestDto.getFeeStructureId());

        for (ClassFeeStructureServiceModel currentServiceModel : classFeeStructureServiceModelList) {
            ClassFeeStructureModel currentClassFeeStructure = new ClassFeeStructureModel(
                    currentServiceModel.getFeeStructureId(),
                    currentServiceModel.getUserId(),
                    currentServiceModel.getFeeStructureDescription(),
                    currentServiceModel.getDateCreated(),
                    currentServiceModel.getIsCurrentFeeStructure(),
                    currentServiceModel.getIsProspect(),
                    currentServiceModel.getClassFeeStructureId(),
                    currentServiceModel.getAcademicClassLevelId(),
                    currentServiceModel.getAcademicClassLevelName(),
                    currentServiceModel.getHierachyCode(),
                    currentServiceModel.getIsAdminClassLevel()
            );
            currentClassFeeStructure.setClassFeeStructureBreakDown(getFeeBreakDownOfAParticularClassFeeStructure(currentServiceModel.getClassFeeStructureId()));
            currentClassFeeStructure.setClassFeeStructureComponents(getFeeComponentsOfAParticularClassFeeStructure(currentServiceModel.getClassFeeStructureId()));
            classFeeStructureModelList.add(currentClassFeeStructure);
        }
        return classFeeStructureModelList;
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

    public List<ClassFeeStructureBreakDownModel> getFeeBreakDownOfAParticularClassFeeStructure(int classFeeStructureId) {
        FeeStructureService feeStructureService = RetrofitClientInstance.getRetrofitInstance(EndPoints.WAONDO_NODE_BASE_URL+"/").create(FeeStructureService.class);
        Call<List<ClassFeeStructureBreakDownModel>> callSync = feeStructureService.getFeeBreakDownOfAParticularClassFeeStructure(classFeeStructureId);
        try {
            Response<List<ClassFeeStructureBreakDownModel>> response = callSync.execute();
            return response.body();
        } catch (Exception ex) {}

        return null;
    }

    public List<ClassFeeStructureComponentModel> getFeeComponentsOfAParticularClassFeeStructure(int classFeeStructureId) {
        FeeStructureService feeStructureService = RetrofitClientInstance.getRetrofitInstance(EndPoints.WAONDO_NODE_BASE_URL+"/").create(FeeStructureService.class);
        Call<List<ClassFeeStructureComponentModel>> callSync = feeStructureService.getFeeComponentsOfAParticularClassFeeStructure(classFeeStructureId);
        try {
            Response<List<ClassFeeStructureComponentModel>> response = callSync.execute();
            return response.body();
        } catch (Exception ex) {}

        return null;
    }

    @PostMapping("/retrieve_class_fee_structures_using_encoded_fee_structure_id")
    public List<ClassFeeStructureModel> retrieveClassFeeStructuresUsingEncodedFeeStructureId(EncodedFeeStructureIdClassFeeStructureRequest encodedFeeStructureIdClassFeeStructureRequest) {
        byte[] valueDecoded = Base64.decodeBase64(encodedFeeStructureIdClassFeeStructureRequest.getEncodedFeeStructureId());
        return retrieveClassFeeStructuresOfAFeeStructure(new FeeStructureRequestDto(Integer.parseInt(new String(valueDecoded))));
    }
}
