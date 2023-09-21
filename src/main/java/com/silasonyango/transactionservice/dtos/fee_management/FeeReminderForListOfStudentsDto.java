package com.silasonyango.transactionservice.dtos.fee_management;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FeeReminderForListOfStudentsDto {
    private List<Integer> studentIds;
    private String deadlineDate;
}
