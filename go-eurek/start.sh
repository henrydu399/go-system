#!/bin/bash
mvn install
docker stop eurek-api
docker rm eurek-api
docker build -t eurek-api:master .
docker run -p 8081:8081 -d -v /media/henry/DATA/LOGS/eurek-api:/log --name eurek-api eurek-api:master &


