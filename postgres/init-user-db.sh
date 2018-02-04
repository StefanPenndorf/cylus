#!/bin/bash

set -e

CYLUS_PW=$(</run/secrets/postgres-super-pwd)

echo "Erstelle Datenbank Cylus"

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE USER cylus WITH LOGIN PASSWORD '${CYLUS_PW}';
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE "cylusdb"
    WITH
    OWNER = cylus
    ENCODING = 'UTF8'
    LC_COLLATE = 'de_DE.utf8'
    LC_CTYPE = 'de_DE.utf8'
    CONNECTION LIMIT = -1;
EOSQL

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    GRANT ALL PRIVILEGES ON DATABASE cylusdb TO cylus;
EOSQL
