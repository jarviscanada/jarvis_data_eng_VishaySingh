#! /bin/sh

#Get the CLI arguments
cmd=$1
db_username=$2
db_password=$3

#Start docker if not already running
sudo systemctl status docker || sudo systemctl start docker

#Check the container status
docker container inspect jrvs-psql
container_status=$?

#Switch case to handle create|stop|start commands
case $cmd in
  create)

  #Check if the container is already exists
  if [ $container_status -eq 0 ]; then
		echo 'Container already exists'
		exit 1
	fi

  #Check the number of CLI arguments
  if [ $# -ne 3 ]; then
    echo 'Create requires username and password'
    exit 1
  fi

  #Create the container
	docker volume create pgdata
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=$db_password -e POSTGRES_USER=$db_username -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres:9.6-alpine
	exit $?
	;;

  start|stop)
  #Check the instance status and exit 1 if container does not exist
  if [ $container_status -eq 1 ]; then
    echo 'Container has not been created'
    exit 1
  fi

  #Start/stop the container
	docker container $cmd jrvs-psql
	exit $?
	;;

  *)
	echo 'Illegal command'
	echo 'Commands: start|stop|create'
	exit 1
	;;
esac