package com.silasonyango.transactionservice.controllers.fee_management;

import com.silasonyango.transactionservice.dtos.fee_management.fee_structure.FeeStructureCreationRequestModel;
import com.silasonyango.transactionservice.dtos.fee_management.fee_structure.FeeStructureCreationResponseModel;
import com.silasonyango.transactionservice.dtos.fee_management.fee_structure.FeeStructureRequestDto;
import com.silasonyango.transactionservice.entity_classes.academic_classes.AcademicClassLevelsEntity;
import com.silasonyango.transactionservice.entity_classes.calendar.TermIterationsEntity;
import com.silasonyango.transactionservice.entity_classes.fee_management.*;
import com.silasonyango.transactionservice.entity_classes.student_management.StudentResidenceEntity;
import com.silasonyango.transactionservice.repository.academic_classes.AcademicClassLevelsRepository;
import com.silasonyango.transactionservice.repository.calendar.TermIterationsRepository;
import com.silasonyango.transactionservice.repository.fee_management.*;
import com.silasonyango.transactionservice.repository.student_management.StudentResidenceRepository;
import com.silasonyango.transactionservice.utility_classes.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fee_structure")
public class FeeStructureController {
    @Autowired
    FeeStructureRepository feeStructureRepository;

    @Autowired
    AcademicClassLevelsRepository academicClassLevelsRepository;

    @Autowired
    ClassFeeStructureRepository classFeeStructureRepository;

    @Autowired
    StudentResidenceRepository studentResidenceRepository;

    @Autowired
    TermIterationsRepository termIterationsRepository;

    @Autowired
    ClassFeeStructureBreakDownRepository classFeeStructureBreakDownRepository;

    @Autowired
    FeeComponentRepository feeComponentRepository;

    @Autowired
    ClassFeeStructureComponentRepository classFeeStructureComponentRepository;

    @Autowired
    ClassFeeStructureController classFeeStructureController;

    @PostMapping("/create_a_fee_structure")
    public FeeStructureCreationResponseModel createFeeStructure(@Valid FeeStructureCreationRequestModel feeStructureCreationRequestModel) {
        FeeStructureEntity createdFeeStructure = feeStructureRepository.save(new FeeStructureEntity(
                feeStructureCreationRequestModel.getUserId(),
                feeStructureCreationRequestModel.getFeeStructureDescription(),
                UtilityClass.getToday(),
                0,
                0
        ));

        List<AcademicClassLevelsEntity> academicClassLevelsEntityList = academicClassLevelsRepository.findAll();

        for (AcademicClassLevelsEntity currentClassLevel : academicClassLevelsEntityList) {

            ClassFeeStructuresEntity classFeeStructuresEntity = classFeeStructureRepository.save(new ClassFeeStructuresEntity(
                    createdFeeStructure.getFeeStructureId(),
                    currentClassLevel.getAcademicClassLevelId()
            ));

            createFeeBreakDownForAClassLevel(classFeeStructuresEntity.getClassFeeStructureId());
            createFeeComponentsForAClassLevel(classFeeStructuresEntity.getClassFeeStructureId());

        }

        return returnCreatedFeeStructure(createdFeeStructure);
    }


    public void createFeeBreakDownForAClassLevel(int classFeeStructureId) {
        List<StudentResidenceEntity> studentResidenceEntityList = studentResidenceRepository.findAll();
        List<TermIterationsEntity> termIterationsEntityList = termIterationsRepository.findAll();

        for (StudentResidenceEntity currentResidenceType : studentResidenceEntityList) {
            for (TermIterationsEntity currentTermIteration : termIterationsEntityList) {
                classFeeStructureBreakDownRepository.save(new ClassFeeStructureBreakDownEntity(
                        classFeeStructureId,
                        currentResidenceType.getStudentResidenceId(),
                        currentTermIteration.getTermIterationId(),
                        0
                ));
            }
        }
    }


    public void createFeeComponentsForAClassLevel(int classFeeStructureId) {
        List<FeeComponentsEntity> feeComponentsEntityList = feeComponentRepository.findAll();

        for (FeeComponentsEntity currentFeeComponent : feeComponentsEntityList) {
            classFeeStructureComponentRepository.save(new ClassFeeStructureComponentEntity(
                    classFeeStructureId,
                    currentFeeComponent.getFeeComponentId(),
                    0.0
            ));
        }
    }


    public FeeStructureCreationResponseModel returnCreatedFeeStructure(FeeStructureEntity feeStructureEntity) {
        return new FeeStructureCreationResponseModel(
                feeStructureEntity.getFeeStructureId(),
                feeStructureEntity.getUserId(),
                feeStructureEntity.getFeeStructureDescription(),
                feeStructureEntity.getDateCreated(),
                feeStructureEntity.getIsCurrentFeeStructure(),
                feeStructureEntity.getIsProspect(),
                classFeeStructureController.retrieveClassFeeStructuresOfAFeeStructure(new FeeStructureRequestDto(feeStructureEntity.getFeeStructureId()))
        );
    }
}
