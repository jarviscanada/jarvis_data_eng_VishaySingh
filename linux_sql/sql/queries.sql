-- 1. Group hosts by hardware info
SELECT cpu_number, id, total_mem
FROM host_info
ORDER BY MAX(cpu_number) OVER (
    PARTITION BY cpu_number
    ORDER BY total_mem DESC
    );

-- 2. Average memory usage
-- Function is taken from SQL hint
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

CREATE TEMP TABLE host_usage_rounded AS
SELECT host_id, memory_free, timestamp, round5(timestamp) as rounded_time
FROM host_usage;

SELECT hur.host_id, hin.hostname, hur.rounded_time, AVG((hin.total_mem - hur.memory_free) / hin.total_mem) AS avg_used_mem_percentage
FROM host_info hin INNER JOIN host_usage_rounded hur ON hin.id = hur.host_id
GROUP BY hur.host_id, hin.hostname, hur.rounded_time;


SELECT hur.host_id, hin.hostname, hur.rounded_time, hin.total_mem, hur.memory_free,
       AVG((hin.total_mem - hur.memory_free)) OVER (
           PARTITION BY hur.rounded_time, hur.host_id
           ) AS avg_used_mem_percentage
FROM host_info hin INNER JOIN host_usage_rounded hur ON hin.id = hur.host_id;

