# Linux Cluster Monitoring Agent
This project is under development. Since this project follows GitFlow, the final work will be merged to the main branch after Team Code Review.
# Introduction
The Linux Cluster Monitoring Agent is used to allow users to monitor server node usage in a Linux cluster by tracking relevant information (e.g. current free memory, cpu utilization, etc.) for each host. This could be used by companies that would like to know whether or not their Linux cluster is evenly utilizing system resources, so that they can optimize server usage allocation. For example, if ¾ nodes are running close to 90% cpu utilization, while ¼ nodes are only around 20% on average, then the company can allocate more resources to be run on the latter. This project used bash scripting and crontab to automate host usage information, database setup, etc. Data was persisted using a postgreSQL instance on Docker, and GitFlow branching methodology was used to ensure smooth releases.   

# Quick Start
1. First we need to initialize the database. Let's use the ```psql_docker.sh``` script:
```bash
./scripts/psql_docker.sh create [db_username][db_password]
```
```db_username``` and ```db_password``` refers to the databases' username and password respectively. Once this line is run, we can move on to the next step (if you get an error here, please check the error message for information on what went wrong).

Next, we need to start the database:
```bash
./scripts/psql_docker.sh start
```
The database should be running on Docker now, and to stop the container, simply use the following command:
```bash
./scripts/psql_docker.sh stop
```

2. Now, we need to create the tables in the database using the ```ddl.sql``` script:
```bash
psql -h localhost -U postgres -d host_agent -f sql/ddl.sql
```

3. Next, we insert a node's hardware information into the database using ```host_info.sh```. This script is only run once on each node, as we only need to record this information once:
```bash
./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
```
```psql_host```, ```psql_port```, ```db_name```, ```psql_user```, ```psql_password``` refers to the postgres host, postgres port, database name, postgres username, and postgres password respectively.

4. This script (```host_usage.sh```) records a snapshot of the current node's usage. It can be run manually from the CLI to record hardware usage data into the database:
```bash
./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
```
Where all arguments represent the same thing as in the previous script.

5. Finally, we can automate our usage script's updates using Crontab. Here is how to set this up:
```bash
#First, cd into the script directory
cd script/

#Copy the output of pwd
pwd

#Then, edit your crontab jobs
crontab -e

#Add the following line to the file, replacing PATH_COPIED with the path that you copied from before
* * * * * bash PATH_COPIED/host_usage.sh localhost 5432 host_agent postgres password > /tmp/host_usage.log

#Check that the crontab job shows up here:
crontab -l

#Finally, login to the database and check that the results show (see below)
psql -h psql_host -d db_name -U postgres -W
```
```sql
SELECT * FROM host_usage;
```

# Implemenation
Discuss how you implement the project.
## Architecture
Draw a cluster diagram with three Linux hosts, a DB, and agents (use draw.io website). Image must be saved to the `assets` directory.
![architecture_diagram](https://user-images.githubusercontent.com/56552567/167919933-f9cf27e1-ea48-4f65-a63f-db1d23cab192.png)


## Scripts
Shell script description and usage (use markdown code block for script usage)
- psql_docker.sh
- host_info.sh
- host_usage.sh
- crontab
- queries.sql (describe what business problem you are trying to resolve)

## Database Modeling
Describe the schema of each table using markdown table syntax (do not put any sql code)
- `host_info`
- `host_usage`

# Test
How did you test your bash scripts and SQL queries? What was the result?

# Deployment
How did you deploy your app? (e.g. Github, crontab, docker)

# Improvements
Write at least three things you want to improve 
e.g. 
- handle hardware update 
- blah
- blah
