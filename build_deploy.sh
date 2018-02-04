#!/usr/bin/env bash

eval $(docker-machine env -u)

(cd ./app3/ && mvn install dockerfile:push)

./deploy-cylus-prod.sh

#Debug
#eval $(docker-machine env production)
#docker service logs --since 1m --follow cylus_cylus

