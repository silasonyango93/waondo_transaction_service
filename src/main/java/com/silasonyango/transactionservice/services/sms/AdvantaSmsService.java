package com.silasonyango.transactionservice.services.sms;

import java.io.IOException;
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
    Request request = new Request.Builder()
        .url(String.format(
            "https://quicksms.advantasms.com/api/services/sendsms/?apikey=de8d4d1e223c0d21e685eee3cf0162b6"
                + "&partnerID=9427&message=%s&shortcode=WAONDO-SEC&mobile=%s", textMessage,
            recipientPhoneNo))
        .build();

    try (Response response = okHttpClient.newCall(request).execute()) {
      log.info(
          String.format("sms status for recipient %s is: status code -> %s; response body -> %s",
              recipientPhoneNo, response.code(),
              response.body() != null ? response.body().toString() : null));
      return true;
    } catch (Exception e) {
      log.info(String.format("sms error message for recipient %s is -> %s", recipientPhoneNo,
          e.getMessage()));
      e.printStackTrace();
      return false;
    }
  }

  public boolean postSendSms(String recipientPhoneNo, String textMessage) {
    log.info("Initiate sms send");
    log.info(System.getenv("ADVANTA_API_KEY"));
    String url = "https://quicksms.advantasms.com/api/services/sendsms/";
    AdvantaSmsServiceModel payload = new AdvantaSmsServiceModel(
        "de8d4d1e223c0d21e685eee3cf0162b6",
        "9427",
        textMessage,
        "WAONDO-SEC",
        recipientPhoneNo
    );

    RequestBody requestBody = RequestBody.create(payload.toString(),
        MediaType.parse("application/json"));
    log.info(requestBody.toString());
    Request request = new Request.Builder()
        .url(url)
        .post(requestBody)
        .build();
    try (Response response = okHttpClient.newCall(request).execute()) {
      log.info(
          String.format("sms status for recipient %s is: status code -> %s; response body -> %s",
              recipientPhoneNo, response.code(),
              response.body() != null ? response.body().toString() : null));
      return response.isSuccessful();
    } catch (Exception e) {
      log.info(String.format("sms error message for recipient %s is -> %s", recipientPhoneNo,
          e.getMessage()));
      e.printStackTrace();
      return false;
    }
  }
}
