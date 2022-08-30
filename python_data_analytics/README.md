# Introduction
- Describe the business context of this project (please do not copy-paste text from the project kick-off ticket)
- Also, describe how LGS would use your analytic result (hint: see kick-off ticket)
- Describe your work and technologies used (e.g. Jupyter Notebook, Python libraries, data analytics, data warehouse, etc.)

# Implementaion
## Project Architecture
- The LGS web app consists of a frontend which communicates with the backend API to persist the data collected by the frontend, and both stacks are hosted by Azure's cloud services
- Transaction data from 2009-2011 was dumped from the SQL server into an SQL file, and during the ETL process, client's personal information was removed.
- This file, `retail.sql` is then loaded into the PostgreSQL Data Warehouse, which the Jupyter notebook queries the data from (i.e. Data Wrangling), and performs its analysis (i.e. Data Analytics)

![python_arch](https://user-images.githubusercontent.com/56552567/187535369-f5ef7082-5c34-4e57-8758-f5d38c4937fd.png)


## Data Analytics and Wrangling
- Create a link that points to your Jupyter notebook (use the relative path `./retail_data_analytics_wrangling.ipynb`)
- Discuss how would you use the data to help LGS to increase their revenue (e.g. design a new marketing strategy with data you provided)

# Improvements
- List three improvements that you want to do if you got more time
