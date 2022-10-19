#!/bin/bash
mvn install
docker build -t conciliaciones-eureka-api:master .
docker run -p 8082:8082 conciliaciones-eureka-api:master &

