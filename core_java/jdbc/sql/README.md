# Important Note
This is intended for Linux/MacOS.

## Prerequisites
* Docker is installed
* psql client is installed
* `psql_docker.sh` was ran (create, and start)

### Login to PostgreSQL
* `psql -h localhost -U postgres -d hplussport`

### Running the scripts
1. `psql -h localhost -U postgres -f database.sql`
2. `psql -h localhost -U postgres -d hplussport -f customer.sql`
3. `psql -h localhost -U postgres -d hplussport -f product.sql`
4. `psql -h localhost -U postgres -d hplussport -f salesperson.sql`
5. `psql -h localhost -U postgres -d hplussport -f orders.sql`
