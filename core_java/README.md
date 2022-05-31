# Introduction
This project aims at developing skills/knowledge using the JDBC library, and the DAO and repository design patterns. The app was built using Docker for hosting the PostgreSQL database, IntelliJ as the IDE, DBeaver client for database visualization and ERD diagram, and core java libraries. The result is an app that leverages the DAO design pattern to CRUD (Create, Read, Update, and Delete) relevant information stored in the Docker PostgreSQL database. 

# Implementation
## ER Diagram
Here is the ER diagram for this project’s database:

![image](https://user-images.githubusercontent.com/56552567/171041446-4bf0b29d-df89-40a6-ba2d-85af5fd6e205.png)

## Design Patterns
The DAO (Database Access Object) design pattern was used for the implementation of this project, which is an abstraction of data persistence, lower-level (i.e. close to the database), acts as a DAL (Data Access Layer), and cannot be implemented using a repository design pattern. In the implementation, this means that a single object interacted with the database to CRUD data. For example, the `CustomerDAO` class was used to CRUD values from the `customer` table. Comparatively, the repository design pattern was not used here, since it’s an abstraction of a collection of objects, much higher-level (i.e. closer to the domain objects), and it more so acts as a layer between domains and DALs. What this means is that for future improvements, this project can be expanded to use the repository design pattern to provide that aforementioned layer between domains and DALs, making it more business-friendly. For example, making a `CustomerRepository` interface and implementation with methods such as `get`, `add`, `update`, and `remove` that call the corresponding CRUD methods in the `CustomerDAO` class.

# Testing
## Database Setup
To set up the database, use the newly modified `psql_docker.sh` script. Note that the only change made to this script is the addition of the `container_name` argument, and the `-h` or `--help` option:

```bash
$ ./psql_docker.sh --help
========================================================
SCRIPT USAGE:
./psql_docker.sh [COMMAND] [ARGUMENTS]
========================================================
EXAMPLE:
./psql_docker.sh create container_name username password
./psql_docker.sh start container_name
./psql_docker.sh stop container_name
========================================================
```
## Test Data Setup
Next, we can run the scripts in the `/sql` directory to set up the database, create the tables, and fill them with data. Please see this directory for more information.

## Query Results
Now that the tables have been created and filled with data, testing is done by first running the SQL queries in the psql client, to check that they are working as intended. For example, here is the query made in the psql shell:

```psql
hplussport=# SELECT
hplussport-#   c.first_name, c.last_name, c.email, o.order_id,
hplussport-#   o.creation_date, o.total_due, o.status,
hplussport-#   s.first_name, s.last_name, s.email,
hplussport-#   ol.quantity,
hplussport-#   p.code, p.name, p.size, p.variety, p.price
hplussport-# from orders o
hplussport-#   join customer c on o.customer_id = c.customer_id
hplussport-#   join salesperson s on o.salesperson_id=s.salesperson_id
hplussport-#   join order_item ol on ol.order_id=o.order_id
hplussport-#   join product p on ol.product_id = p.product_id
hplussport-# where o.order_id = 1000;
 first_name | last_name |        email        | order_id |    creation_date    | total_due | status | first_name | last_name |          email          | quantity |  code   |     name      | size |  variety   | price 
------------+-----------+---------------------+----------+---------------------+-----------+--------+------------+-----------+-------------------------+----------+---------+---------------+------+------------+-------
 Angela     | Crawford  | acrawford8p@com.com |     1000 | 2016-05-14 00:00:00 |    118.22 | paid   | Edward     | Kelley    | ekelleyu@hplussport.com |       31 | MWCRA20 | Mineral Water |   20 | Cranberry  |  1.79
 Angela     | Crawford  | acrawford8p@com.com |     1000 | 2016-05-14 00:00:00 |    118.22 | paid   | Edward     | Kelley    | ekelleyu@hplussport.com |       17 | MWLEM32 | Mineral Water |   32 | Lemon-Lime |  3.69
(2 rows)
```

Here is the output of the app running the `orderDAO.findById(1000)` method:

```bash
Order{id=1000, customerFirstName='Angela', customerLastLane='Crawford', customerEmail='acrawford8p@com.com', creationDate=Sat May 14 00:00:00 UTC 2016, totalDue=118.22, status='paid', salespersonFirstName='Edward', salespersonLastName='Kelley', salespersonEmail='ekelleyu@hplussport.com', orderLines=[OrderLine{quantity=31, productCode='MWCRA20', productName='Mineral Water', productSize=20, productVariety='Cranberry', productPrice=1.79}, OrderLine{quantity=17, productCode='MWLEM32', productName='Mineral Water', productSize=32, productVariety='Lemon-Lime', productPrice=3.69}]}

Process finished with exit code 0
```

The app generated the DTO (Data Transfer Object) using the DAO design pattern, and the DTO fields match what was expected. This is how the app was tested during development.
