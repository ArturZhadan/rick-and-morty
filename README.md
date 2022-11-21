#  ***Rick & Morty***
___
### :pushpin: ***Project description*** :pushpin:
***Web-application that supports parsing data from another API and save it to DB***
___
### :bookmark: ***Features:*** :bookmark:
+ :notebook: ***save a movie-character***
+ :notebook: ***read random movie-character***
+ :notebook: ***read a movie-character by name***
___
### :open_file_folder: ***Structure:*** :open_file_folder:
+ ***Controller - accepts requests from the client, passes them to the service layer***
+ ***Service - accepts requests from the controller, passes them to the DAO layer and performs all business logic***
+ ***DAO - accepts requests from the service, passes them to the DB***
___
### :page_with_curl: ***Technologies:*** :page_with_curl:
+ ***[Maven](https://maven.apache.org/download.cgi)***
+ ***[PostgreSQL](https://www.postgresql.org/download/)***
+ ***SpringBoot 2.7.5***
+ ***[Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)***
___
### :question: ***How to run this project: :question:***
- [x] ***Clone the project***
- [x] ***Write your properties to application.properties file***
- [x] ***Run the project***    
___
###  ***Example GET requests***
```java
        http://localhost:8090/movie-characters/random
        http://localhost:8090/movie-characters/by-name?name=Rick
```
