#!/usr/bin/env bash

eval $(docker-machine env production)

docker pull stefan-desktop:5000/cyphoria/app3:latest
docker stack deploy --prune -c docker-stack.yml cylus
