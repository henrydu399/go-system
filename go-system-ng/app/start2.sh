#!/bin/bash
docker build -t home-angular:master .
docker run -p 80:80 home-angular:master &

