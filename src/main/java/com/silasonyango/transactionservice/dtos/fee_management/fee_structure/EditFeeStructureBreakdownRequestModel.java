package com.silasonyango.transactionservice.dtos.fee_management.fee_structure;

import java.util.List;

public class EditFeeStructureBreakdownRequestModel {
    private List<BreakDownEditRequestModel> requestedChanges;

    public List<BreakDownEditRequestModel> getRequestedChanges() {
        return requestedChanges;
    }

    public void setRequestedChanges(List<BreakDownEditRequestModel> requestedChanges) {
        this.requestedChanges = requestedChanges;
    }
}
