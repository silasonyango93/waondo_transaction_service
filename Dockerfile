FROM openjdk:8-jdk-alpine
ENV TWILIO_ACCOUNT_SID AC6da5488fc8824e3c654359900fdc33ab
ENV TWILIO_AUTH_TOKEN 46e87d62642b4dc7a14beac3123c683c
ENV TWILIO_PHONE_NUMBER +15179956869
ENV ADVANTA_API_KEY de8d4d1e223c0d21e685eee3cf0162b6
ENV ADVANTA_APP_TOKEN iGwQWm5OdlLPsd2YT2AQ8CAVMh8Khkia
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]