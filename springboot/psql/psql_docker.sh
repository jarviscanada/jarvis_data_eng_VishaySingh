#! /bin/sh

#Get the CLI arguments
cmd=$1
container_name=$2
db_username=$3
db_password=$4


#Start docker if not already running
sudo systemctl status docker || sudo systemctl start docker

#Check the container status
docker container inspect $container_name
container_status=$?

#Switch case to handle create|stop|start commands
case $cmd in
  -h|--help)
  echo '========================================================'
  echo 'SCRIPT USAGE:'
  echo './psql_docker.sh [COMMAND] [ARGUMENTS]'
  echo '========================================================'
  echo 'EXAMPLE:'
  echo './psql_docker.sh create container_name username password'
  echo './psql_docker.sh start container_name'
  echo './psql_docker.sh stop container_name'
  echo '========================================================'
  ;;
  create)

  #Check if the container is already exists
  if [ $container_status -eq 0 ]; then
		echo 'Container already exists'
		exit 1
	fi

  #Check the number of CLI arguments
  if [ $# -ne 4 ]; then
    echo 'Create requires container_name, username, and password'
    exit 1
  fi

  #Create the container
	docker volume create pgdata
	docker run --name $container_name -e POSTGRES_PASSWORD=$db_password -e POSTGRES_USER=$db_username -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
	exit $?
	;;

  start|stop)
  #Check the instance status and exit 1 if container does not exist
  if [ $container_status -eq 1 ]; then
    echo 'Container has not been created'
    exit 1
  fi

  #Check the number of CLI arguments
  if [ $# -ne 2 ]; then
    echo 'start|stop requires container name'
    exit 1
  fi

  #Start/stop the container
	docker container $cmd $container_name
	exit $?
	;;

  *)
	echo 'Illegal command'
	echo 'Commands: start|stop|create'
	echo 'Type -h or --help for help'
	exit 1
	;;
esac