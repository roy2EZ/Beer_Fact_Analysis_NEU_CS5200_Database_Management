**Beer Fun Fact Analysis** project for *NEU 2019 Fall CS5200 database management* course (Sep. - Dec. 2019)

This project designed and implemented an database for analyze rating and reviews of 350000+ beer brands in US:
- Implementd a database with MySQL of beer rating and reviews record from following open data sources:  
  [*Beers bereweries and reviews* data from www.kaggle.com](https://www.kaggle.com/ehallmar/beers-breweries-and-beer-reviews)
- Developed a web application to query the database and display results using Java JDBC/JSP:
  - Used JDBC to build the data access layer for all classes
  - Used JSP to build the web application
  - Run on Tomcat server
  - Used web application to perform create, read, update, delete (CRUD) operations on database
- Data Warehousing
  Used CloverDX for data warehousing for analysis of relationship between beer taste and temperature.
  Two External Data Source for data warehousing:
  - External data source 1:
    [Federal Holidays U.S.A. 1966-2020](https://www.kaggle.com/gsnehaa21/federal-holidays-usa-19662020)
    To see relationship between holidays and amount of users reviews. 
  - External data source 2:
    [Average Annual Temperature for Each US State](https://www.currentresults.com/Weather/US/average-annual-state-temperatures.php)
    To see relationship between Brewing State average temperature and Beer taste.
  - [Report slide](https://docs.google.com/presentation/d/1a8XW-jS5uj_Fbt9anVP3elJJ6PADMVZprq4D70GWzoM/edit?usp=sharing)

Presentation Video Links:
1. Project Proposition & Delivered Results:
  [link](https://youtu.be/ETBDbZqRzj8)
2. Database Building(UML & Data source processing): 
  [link1](https://youtu.be/VZcZVlUyxUE)
  [link2](https://youtu.be/0OsO17gEgJg)
3. SQL Query: 
  [link](https://youtu.be/nuDrmWccY20)
4. Web App Development-JDBC/JSP: 
  [link](https://youtu.be/7dPIPf3mk9k)
5. Data Warehousing (ETL), What went well & Future Improvement:
  [link](https://youtu.be/ls3PDhJcIf0)

