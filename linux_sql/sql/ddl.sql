--Assume the database is already created
--\c host_agent;
CREATE TABLE IF NOT EXISTS PUBLIC.host_info
(
    id               SERIAL NOT NULL,
    hostname         VARCHAR NOT NULL,
    -- add more columns
    cpu_number INT NOT NULL,
    cpu_architecture VARCHAR NOT NULL,
    cpu_model VARCHAR NOT NULL,
    cpu_mhz REAL NOT NULL,
    L2_cache INT NOT NULL,
    total_mem bigint NOT NULL,
    "timestamp" TIMESTAMP NOT NULL,
    -- primary key constraint
    PRIMARY KEY (id),
    -- unique hostname constraint
    UNIQUE (hostname)
);

CREATE TABLE IF NOT EXISTS PUBLIC.host_usage
(
    "timestamp"    TIMESTAMP NOT NULL,
    host_id        SERIAL NOT NULL,
    -- add more columns
    memory_free INT NOT NULL,
    cpu_idle INT NOT NULL,
    cpu_kernel INT NOT NULL,
    disk_io bigint NOT NULL,
    disk_available bigint NOT NULL,
    -- add foreign key constraint
    FOREIGN KEY (host_id) REFERENCES PUBLIC.host_info(id)
);
