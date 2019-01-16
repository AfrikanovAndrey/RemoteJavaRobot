#!/usr/bin/env bash

#cd docker-images/selenoid/$BROWSER

echo "### Build '"$TAG"' Docker image..."
docker build -f docker-images/selenoid/$BROWSER/Dockerfile.$TAG -t $DOCKER_HUB_USER_NAME/$IMAGE_NAME:$TAG .
