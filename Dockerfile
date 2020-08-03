FROM maven:3.6-jdk-11 AS build

WORKDIR /usr/src/app

COPY pom.xml /usr/src/app

RUN mvn dependency:go-offline

COPY src /usr/src/app/src
RUN mvn clean package -DskipTests=true

FROM openjdk:11

# Configure timezone
RUN echo "Europe/Paris" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata

# Copy wait-4-it & jar
ADD docker-wait-for-it.sh docker-wait-for-it.sh
RUN bash -c 'chmod +x /docker-wait-for-it.sh'
COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar

EXPOSE 8080
ENTRYPOINT ["/bin/bash", "/docker-wait-for-it.sh"]
