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

* JPA layer and Entity Beans
    * package: edu/qu/auction/domain
* Data access layer using EJB
    * package: edu/qu/auction/dao/
* Restful Webservice:
    * package: edu/qu/auction/ws/
* JSF Front End (for CRUD UI)
    * package: edu/qu/auction/jsf/

### Run application:
* CRUD Application UI: http://localhost:8080/AuctionWeb/
* Test WS: http://localhost:8080/AuctionWeb/TestWS/test-resbeans.html
* Test Post Bid WS : http://localhost:8080/AuctionWeb/TestPost.html

### Action items:

* Create a data access layer for Redis
* Expose and Create more WS for both DBs
* Create the UI 
* Think about web sockets or 
