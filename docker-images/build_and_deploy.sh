#!/usr/bin/env bash

echo "# Build Selenoid browser images"

export IMAGE_NAME=selenoid_with_remote_java_robot

BROWSER=chrome TAG=vnc_chrome_70 docker-images/build.sh

echo "# Login to DockerHub"
echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USER_NAME --password-stdin
#
#echo "# Push 'aafrikanov/selenoid_images_wth_remote_java_robot:vnc_chrome_70' image ..."
#docker push $DOCKER_HUB_USER_NAME/$IMAGE_NAME:vnc_chrome_70

docker-images/deploy.sh
