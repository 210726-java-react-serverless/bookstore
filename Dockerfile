FROM maven:3.8.2-adoptopenjdk-8 AS build

ARG PROFILE=local
ARG DB_HOST
ARG DB_PORT
ARG DB_USERNAME
ARG DB_PASSWORD

ENV db_host=${DB_HOST}
ENV db_port=${DB_PORT}
ENV db_username=${DB_USERNAME}
ENV db_password=${DB_PASSWORD}

WORKDIR /home/app
COPY src ./src
COPY pom.xml ./pom.xml
RUN mvn clean package
RUN cp ./target/*.jar ./app.jar

EXPOSE 5000
EXPOSE 8080

RUN echo "java -jar -Dspring.profiles.active=${PROFILE} app.jar" >> ./entry.sh
ENTRYPOINT ["sh", "entry.sh"]
