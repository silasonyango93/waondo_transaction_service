package com.silasonyango.transactionservice.services.sms;

import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdvantaSmsService {

  @Qualifier("customOkhttp")
  @Autowired
  OkHttpClient okHttpClient;

  public boolean sendSms(String recipientPhoneNo, String textMessage) {
    String url = "https://quicksms.advantasms.com/api/services/sendsms/";
    AdvantaSmsServiceModel payload = new AdvantaSmsServiceModel(
        System.getenv("ADVANTA_API_KEY"),
        "9427",
        textMessage,
        "WAONDO-SEC",
        recipientPhoneNo
    );

    RequestBody requestBody = RequestBody.create(payload.toString(),
        MediaType.parse("application/json"));
    Request request = new Request.Builder()
        .url(url)
        .post(requestBody)
        .build();
    try (Response response = okHttpClient.newCall(request).execute()) {
      log.info(
          String.format("sms status for recipient %s is: status code -> %s; response body -> %s",
              recipientPhoneNo, response.code(), response.body()));
      return response.isSuccessful();
    } catch (Exception e) {
      log.info(String.format("sms error message for recipient %s is -> %s", recipientPhoneNo,
          e.getMessage()));
      e.printStackTrace();
      return false;
    }
  }
}
