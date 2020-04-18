package com.silasonyango.transactionservice.dtos.user_management;

public class AuthenticationRequestDto {
    private String attemptedEmail;
    private String attemptedPassword;
    private int attemtedRoleCode;

    public String getAttemptedEmail() {
        return attemptedEmail;
    }

    public void setAttemptedEmail(String attemptedEmail) {
        this.attemptedEmail = attemptedEmail;
    }

    public String getAttemptedPassword() {
        return attemptedPassword;
    }

    public void setAttemptedPassword(String attemptedPassword) {
        this.attemptedPassword = attemptedPassword;
    }

    public int getAttemtedRoleCode() {
        return attemtedRoleCode;
    }

    public void setAttemtedRoleCode(int attemtedRoleCode) {
        this.attemtedRoleCode = attemtedRoleCode;
    }
}
