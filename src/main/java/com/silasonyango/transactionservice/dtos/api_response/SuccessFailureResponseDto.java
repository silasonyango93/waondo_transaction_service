package com.silasonyango.transactionservice.dtos.api_response;

public class SuccessFailureResponseDto {
    private boolean successStatus;
    private String responseMessage;
    private String returnValue;

    public boolean isSuccessStatus() {
        return successStatus;
    }

    public void setSuccessStatus(boolean successStatus) {
        this.successStatus = successStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }
}
