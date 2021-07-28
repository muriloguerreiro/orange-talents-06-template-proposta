FROM openjdk:11-jdk
MAINTAINER Murilo Guerreiro
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} proposta.jar
ENTRYPOINT ["java", "-jar", "/proposta.jar"]