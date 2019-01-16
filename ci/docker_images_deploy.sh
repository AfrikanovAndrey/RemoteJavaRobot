#!/usr/bin/env bash

echo "# Build Selenoid browser images"

echo "### Build 'vnc_chrome_70' Docker image..."
cd ../docker-images/selenoid/chrome/70
docker build -t aafrikanov/selenoid_images_wth_remote_java_robot:vnc_chrome_70 .

echo "# Login to DockerHub"
echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USER_NAME --password-stdin

echo "# Push 'aafrikanov/selenoid_images_wth_remote_java_robot:vnc_chrome_70' image ..."
docker push aafrikanov/selenoid_images_wth_remote_java_robot:vnc_chrome_70
