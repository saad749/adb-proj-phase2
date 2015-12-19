# adb-proj-phase2

## Requirements:
* NetBeans IDE 8.1 (Java EE edition)
* GlassFish application server "glassfish-4.1.1" (Part of the netbeans installation)
* MySQL 
* Git

## Setup:
1) Install netbeans and glassfish
2) Clone this repo to your local machine
3) Create the database using db script [AuctionWeb/DBScripts/AuctionDB.sql]
4) Open web project "AuctionWeb" in netbeans
5) Click Run

## The Architecture of the application:

1) JPA layer and Entity Beans
    * package: edu/qu/auction/domain
2) Data access layer using EJB
    * package: edu/qu/auction/dao/
3) Restful Webservice:
    * package: edu/qu/auction/ws/
4) JSF Front End (for CRUD UI)
    * package: edu/qu/auction/jsf/

### Run application:
1) CRUD Application UI: http://localhost:8080/AuctionWeb/
2) Test WS: http://localhost:8080/AuctionWeb/TestWS/test-resbeans.html
3) Test Post Bid WS : http://localhost:8080/AuctionWeb/TestPost.html

### Action items:
1) Create a data access layer for Redis
2) Expose and Create more WS for both DBs
3) Create the UI 
3) Think about web sockets or 
