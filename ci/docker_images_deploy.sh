#!/usr/bin/env bash -e

echo "# Build Selenoid browser images"

echo "### Build 'vnc_chrome_70' Docker image..."
docker build -f ./../docker-images/selenoid/chrome/Dockerfile:vnc_chome_70 -t aafrikanov/selenoid_images_wth_remote_java_robot:vnc_chrome_70 ./../

echo "# Login to DockerHub"
echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USER_NAME --password-stdin

echo "# Push 'aafrikanov/selenoid_images_wth_remote_java_robot:vnc_chrome_70' image ..."
docker push aafrikanov/selenoid_images_wth_remote_java_robot:vnc_chrome_70
