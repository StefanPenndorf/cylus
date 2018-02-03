#!/usr/bin/env bash

eval $(docker-machine env production)

docker pull stefan-desktop:5000/cyphoria/app3:latest
docker-compose build
docker-compose push
docker stack deploy --prune -c docker-compose.yml cylus
