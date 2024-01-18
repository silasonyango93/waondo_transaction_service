package com.silasonyango.transactionservice.services.sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class AdvantaSmsServiceModel {
  private String apikey;
  private String partnerID;
  private String message;
  private String shortcode;
  private String mobile;
}
