#! /bin/bash
# npm install
# npm install --save react-router-dom@5
# npm install --save react-router@5
cmd=$1
if [ $cmd = "start" ]; then
    echo 'Starting backend containers'
    bash ./psql_docker.sh start trading-psql-dev
    bash ./psql_docker.sh start trading-app-dev
    exit 0
fi
if [ $cmd = "stop" ]; then
    echo 'Stopping backend containers'
    bash ./psql_docker.sh stop trading-psql-dev
    bash ./psql_docker.sh stop trading-app-dev
    exit 0
fi
echo 'USAGE: ./init.sh [start|stop] to start/stop backend'