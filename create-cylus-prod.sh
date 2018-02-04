#!/usr/bin/env bash

docker-machine create --driver virtualbox --engine-insecure-registry stefan-desktop:5000 production
docker-machine start production
eval $(docker-machine env production)
docker-machine ssh production "docker swarm init --advertise-addr `docker-machine ip production`"
docker-machine ssh production -f -N -L 0.0.0.0:8000:localhost:8080


cylusPwId=`docker secret ls -q -f name=cylus-master-pwd`

if [ -z "$cylusPwId" ]; then
    read -s -p "Enter Cylus Master Password: " cylus_master_pw
    echo "$cylus_master_pw" | docker secret create cylus-master-pwd -
fi

docker stack deploy --prune -c docker-stack.yml cylus
docker ps
