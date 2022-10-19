FROM openjdk:14-jdk-alpine

RUN apk add --no-cache tzdata
ENV TZ America/Bogota
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /administrador-usuarios-microservice

RUN mkdir ./log
RUN mkdir ./config

COPY ./target/administradorUsers.jar ./
#COPY ./target/salud-citas-externos.jar ./
#COPY ./config/produccion.properties ./config/
#COPY ./config/desarrollo.properties ./config/
