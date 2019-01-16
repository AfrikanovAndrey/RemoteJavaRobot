#!/usr/bin/env bash

echo "# Build Selenoid browser images"

echo "### Build 'vnc_chrome_70' Docker image..."
docker build -f docker-images/selenoid/chrome/Dockerfile.vnc_chrome_70 -t $$DOCKER_HUB_USER_NAME/selenoid_images_wth_remote_java_robot:vnc_chrome_70 .

echo "# Login to DockerHub"
echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USER_NAME --password-stdin

echo "# Push 'aafrikanov/selenoid_images_wth_remote_java_robot:vnc_chrome_70' image ..."
docker push aafrikanov/selenoid_images_wth_remote_java_robot:vnc_chrome_70
