package com.silasonyango.transactionservice.common.config;

import okhttp3.OkHttpClient;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClass {
  @Bean("customOkhttp")
  public OkHttpClient okHttpClient(){
    return new OkHttpClient();
  }
}
