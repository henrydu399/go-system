#!/bin/bash
mvn install
docker stop gateway-api
docker rm gateway-api
docker build -t gateway-api:master .
docker run -p 8080:8080 -d -v /media/henry/DATA/LOGS/gateway-api:/log --name gateway-api gateway-api:master &


