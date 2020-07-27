#FROM java:8
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
#ADD docker-wait-for-it.sh docker-wait-for-it.sh
#RUN bash -c 'chmod +x /docker-wait-for-it.sh'
#RUN ls -la
#ENTRYPOINT ["/bin/bash", "/docker-wait-for-it.sh"]

FROM maven:3.6-jdk-11 AS build

WORKDIR /usr/src/app

COPY pom.xml /usr/src/app

RUN mvn dependency:go-offline

COPY src /usr/src/app/src
RUN mvn clean package

FROM openjdk:11
ADD docker-wait-for-it.sh docker-wait-for-it.sh
RUN bash -c 'chmod +x /docker-wait-for-it.sh'
COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar
EXPOSE 8080
ENTRYPOINT ["/bin/bash", "/docker-wait-for-it.sh"]
