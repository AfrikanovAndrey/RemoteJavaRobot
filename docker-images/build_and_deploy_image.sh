#!/usr/bin/env bash

echo "### Build '"$TAG"' Docker image..."
docker build -f docker-images/selenoid/$BROWSER/Dockerfile.$TAG -t $DOCKER_HUB_USER_NAME/$IMAGE_NAME:$TAG .

echo "### Deploy '"$DOCKER_HUB_USER_NAME/$IMAGE_NAME":"$TAG"' Docker image..."
docker push $DOCKER_HUB_USER_NAME/$IMAGE_NAME:$TAG
