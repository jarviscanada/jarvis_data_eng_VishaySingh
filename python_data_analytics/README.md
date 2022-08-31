# Introduction
- LGS would like us to analyze customer sales data retrieved from their website, in order to develop better sales/marketing techniques, and bolster profits. 
- This project is a POC that aims to fulfill the businesses needs by wrangling the data from the data warehouse, and performing analytics using Jupyter Notebook, and Python libraries such as Numpy, Pandas, Matplotlib, etc.

# Implementation
## Project Architecture
- The LGS web app consists of a frontend which communicates with the backend API to persist the data collected by the frontend, and both stacks are hosted by Azure's cloud services
- Transaction data from 2009-2011 was dumped from the SQL server into an SQL file, and during the ETL process, client's personal information was removed.
- This file, `retail.sql` is then loaded into the PostgreSQL Data Warehouse, which the Jupyter notebook queries the data from (i.e. Data Wrangling), and performs its analysis (i.e. Data Analytics)

![python_arch](https://user-images.githubusercontent.com/56552567/187535369-f5ef7082-5c34-4e57-8758-f5d38c4937fd.png)


## Data Analytics and Wrangling
- [Notebook](./python_data_wrangling/retail_data_analytics_wrangling.ipynb)
- Can't Lose Segment:
  - Customers in this segment have not made a purchase recently. Therefore, we need to market discounts to this segment to encourage them to make new purchases.
- Hibernating Segment:
  - Customers here have not made a purchase in a long time, so we must entice them to spend again with new and exciting deals.
- Champions Segment:
  - Customers here make up the largest percentage of revenue, so they must be catered to with deals centered around their loyalty, like a loyalty rewards program perhaps. Giving a continuous stream of discounts to this group via a loyalty rewards program could help foster our relationship with this segment, leading to long-term profits.

# Improvements
1. Expand statistical analysis - the current analysis is limited, albeit helpful, so expanding the analysis could reveal greater insights into the data.
2. Implement an ML predictive model - a basic predictive model could help us to predict when specific customer segments make the most purchases, so that we can target sales/discounts strategically.
3. Retrieve newer data - the current data is fairly outdated being greater than a decade old at worst, so a new dump of data would be much more accurate.
