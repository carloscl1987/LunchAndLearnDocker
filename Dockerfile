FROM maven:latest

RUN apt-get update -y

WORKDIR /usr/src/app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src/ ./src

RUN mvn package

CMD ["java","-jar","/app/docker_project-0.0.1-SNAPSHOT-all.jar","0.0.0.0"]
