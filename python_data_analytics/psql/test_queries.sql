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
SELECT AVG(a.val) FROM (SELECT SUM(unit_price * quantity) as val FROM retail GROUP BY invoice_no) AS a WHERE a.val > 0;

-- Q7
SELECT SUM(unit_price * quantity) FROM retail; 

-- Q8
SELECT (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date)) as yyyymm, SUM(unit_price * quantity) as sum
FROM retail
GROUP BY (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date))
ORDER BY (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date)) ASC; 

--
SELECT (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date)) as year_month, COUNT(*)
FROM retail
WHERE invoice_no ILIKE 'C%'
GROUP BY (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date)); 


SELECT (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date)) as year_month, COUNT(*)
FROM retail
WHERE invoice_no ~ '^[0-9]'
GROUP BY (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date));

--
SELECT (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date)) as InvoiceYearMonth, SUM(quantity) AS Cancellation
FROM retail
WHERE invoice_no ~ '^[0-9]'
GROUP BY (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date))
ORDER BY (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date)) ASC 
LIMIT 5;


--

SELECT (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date)) as InvoiceYearMonth, COUNT(DISTINCT customer_id) AS cnt
FROM retail
GROUP BY (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date))
ORDER BY (EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date)) ASC;

--

SELECT a.InvoiceYearMonth, COUNT(a.customer_id)
FROM (SELECT MIN(EXTRACT(YEAR FROM invoice_date) * 100 + EXTRACT(MONTH FROM invoice_date)) AS InvoiceYearMonth, customer_id 
FROM retail 
GROUP BY customer_id
ORDER BY customer_id ASC) AS a 
GROUP BY a.InvoiceYearMonth
ORDER BY a.InvoiceYearMonth ASC;