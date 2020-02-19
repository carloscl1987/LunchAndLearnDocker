FROM maven:latest

ENV ftp_proxy "http://proxy.example.com:8080/"
ENV http_proxy "http://proxy.example.com:8080/"
ENV https_proxy "http://proxy.example.com:8080/"

COPY ./settings-docker.xml /usr/share/maven/conf/settings.xml

WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ ./src

RUN mvn package

CMD ["java","-jar","/app/docker_project-0.0.1-SNAPSHOT-all.jar","0.0.0.0"]
