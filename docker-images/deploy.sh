#!/usr/bin/env bash

docker images | awk '{print $1":"$2 }' | xargs -I {} docker push
