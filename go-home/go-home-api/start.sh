#!/bin/bash
if [ -z "$1" ]; then
tag=master
else
tag=$1
fi
dockerBuild="docker build -t administradorUsuarios:$tag ."
dockerComposeUp="docker-compose up -d --build"
dockerComposeDown="docker-compose down"
if [ -f docker-compose.yml ]; then
	echo $dockerComposeDown
	execCmd=$($dockerComposeDown 2>&1)
fi
echo $dockerBuild
execCmd=$($dockerBuild 2>&1)
cat > .env << EOF
TAG=$tag
EOF
echo $dockerComposeUp
execCmd=$($dockerComposeUp 2>&1)

