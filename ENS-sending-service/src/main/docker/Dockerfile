FROM openjdk:17-jdk-alpine

ENV APP_HOME /app
RUN mkdir -p $APP_HOME
WORKDIR $APP_HOME

ARG JAR_FILE=target/sending-service-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8771
EXPOSE 15432
EXPOSE 5672
