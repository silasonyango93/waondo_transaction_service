package com.silasonyango.transactionservice.dtos.api_response;

public class SuccessFailureResponseDto {
    private boolean successStatus;
    private String responseMessage;
    private String returnValue;

    public SuccessFailureResponseDto(boolean successStatus, String responseMessage, String returnValue) {
        this.successStatus = successStatus;
        this.responseMessage = responseMessage;
        this.returnValue = returnValue;
    }

    public SuccessFailureResponseDto() {

    }

    public boolean getSuccessStatus() {
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
