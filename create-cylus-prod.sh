#!/usr/bin/env bash

docker-machine create --driver virtualbox --engine-insecure-registry stefan-desktop:5000 production
docker-machine start production
eval $(docker-machine env production)
docker-machine ssh production "docker swarm init --advertise-addr `docker-machine ip production`"
docker-machine ssh production -f -N -L 0.0.0.0:8000:localhost:8080
docker stack deploy --prune -c docker-stack.yml cylus
docker ps
