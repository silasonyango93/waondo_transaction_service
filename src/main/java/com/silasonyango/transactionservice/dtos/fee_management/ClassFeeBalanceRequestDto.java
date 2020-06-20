package com.silasonyango.transactionservice.dtos.fee_management;

public class ClassFeeBalanceRequestDto extends FeeBalanceRequestDto {
    private int classId;
    public ClassFeeBalanceRequestDto(int classId, int minimumFeeBalance) {
        super(minimumFeeBalance);
        this.classId = classId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
