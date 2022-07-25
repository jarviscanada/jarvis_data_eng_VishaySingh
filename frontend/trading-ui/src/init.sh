#! /bin/bash
# npm install
# npm install --save react-router-dom@5
# npm install --save react-router@5
cmd=$1
if [ $cmd -eq 0 ]; then
    echo 'Starting backend containers'
    ./psql_docker start trading-psql-dev
    ./psql_docker start trading-app-dev
    exit 0
fi
if [ $cmd -eq 1 ]; then
    echo 'Stopping backend containers'
    ./psql_docker stop trading-psql-dev
    ./psql_docker stop trading-app-dev
    exit 0
fi
echo 'USAGE: ./init.sh 0 to start containers'
echo 'USAGE: ./init.sh 1 to stop containers'