#!/usr/bin/env bash

echo "### Build and deploy docker-images ###"

export IMAGE_NAME=selenoid_with_remote_java_robot

echo "### Login to DockerHub ###"
echo $DOCKER_HUB_PASSWORD | docker login -u $DOCKER_HUB_USER_NAME --password-stdin


BROWSER=chrome TAG=vnc_chrome_70 docker-images/build_and_deploy_image.sh


# 'awk' don't work in Travis :(
#docker images | grep $IMAGE_NAME | awk '{print $1":"$2 }' | xargs -I {} docker push
