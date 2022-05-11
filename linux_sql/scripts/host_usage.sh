#! /bin/sh

#Get the CLI arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

#Check the number of args
if [ $# -ne  5 ]; then
  echo 'Usage: ./host_usage.sh psql_host psql_port db_name psql_user psql_password'
  exit 1
fi

#save machine information to variables for later
meminfo=$(cat /proc/meminfo)
vmstat_t=$(vmstat -t --unit M)
hostname=$(hostname -f)

#Get the specific hardware specification variables
memory_free=$(echo "${vmstat_t}"  | awk '{print $4}' | xargs | awk '{print $NF}')
cpu_idle=$(echo "${vmstat_t}"  | awk '{print $15}' | xargs | awk '{print $NF}')
cpu_kernel=$(echo "${vmstat_t}"  | awk '{print $14}' | xargs | awk '{print $NF}')
disk_io=$(echo "$(vmstat -d)"  | awk '{print $9}' | xargs | awk '{print $2}')
disk_available=$(echo "$(df -BM /)"  | awk '{print $4}' | xargs | cut -d"M" -f 1 | awk '{print $NF}')

#Current time in `2019-11-26 14:40:19` UTC format
timestamp=$(echo "${vmstat_t}"  | awk '{print $18, $19}' | xargs | awk '{print $2, $3}')

#Subquery to find matching id in host_info table
host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";

#PSQL command: Inserts server usage data into host_usage table
#Note: be careful with double and single quotes
insert_stmt="INSERT INTO host_usage(\"timestamp\", host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available) VALUES('$timestamp', ..."

#set up env var for pql cmd
export PGPASSWORD=$psql_password
#Insert date into a database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?