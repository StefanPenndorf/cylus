#/bin/bash

docker-machine create --driver virtualbox --engine-insecure-registry stefan-desktop:5000 production
docker-machine start production
eval $(docker-machine env production)
docker-machine ssh production -f -N -L 0.0.0.0:8000:localhost:8080
docker-compose up -d
docker ps
