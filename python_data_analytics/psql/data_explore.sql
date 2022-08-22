-- Show table schema 
set search_path to public;
\d+ retail;

-- Show first 10 rows
SELECT * FROM retail limit 10;

-- Check # of records
SELECT COUNT(*) FROM retail;

-- number of clients (e.g. unique client ID)
SELECT COUNT(*) FROM (SELECT customer_id FROM retail GROUP BY customer_id) AS a;

-- Q4
SELECT MAX(invoice_date) as max, MIN(invoice_date) as min FROM retail;

-- Q5
SELECT COUNT(*) FROM (SELECT stock_code FROM retail GROUP BY stock_code) AS a;

-- Q6
SELECT AVG(unit_price) FROM retail WHERE unit_price >= 0;

-- Q7
SELECT SUM(unit_price * quantity) FROM retail; 

-- Q9
SELECT (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date)) as yyyymm, SUM(unit_price * quantity) as sum
FROM retail
GROUP BY (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date)); 
