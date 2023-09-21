package com.silasonyango.transactionservice.dtos.sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SendSmsAnnouncementRequestDto {
    private int referenceId;
    private String announcementMessage;
    private List<Integer> referenceList;
}
