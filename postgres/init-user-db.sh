#!/bin/bash

set -e

CYLUS_PW=$(</run/secrets/postgres-super-pwd)

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER cylus PASSWORD '${CYLUS_PW}';
    CREATE DATABASE cylusdb;
    GRANT ALL PRIVILEGES ON DATABASE cylusdb TO cylus;
EOSQL