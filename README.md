# Rick-and-Morty
___
## Description
Simple web application that parse data from api and save it to DB
___
## Features
+ Save a movie character to DB
+ Read a random movie character
+ Read a movie character by name part
+ Download actual data from api every day at 8.00 am
___
## Technologies
+ Maven
+ Java 17
+ PostgreSQL
+ SpringBoot
+ Docker
___
## Quickstart
1. Clone repository
2. Download images from Docker 'docker pull mihael0204/postgres' and 'docker pull mihael0204/rick-and-morty'
3. Set your own postgres properties in files application.properties and .env
4. Run project with command 'docker-compose up'
___
## Example of requests
```java
    GET: /movie-characters/random
    http://localhost:8081/movie-characters/random
    GET: /movie-characters/by-name
    http://localhost:8081/movie-characters/?name=namepart
```