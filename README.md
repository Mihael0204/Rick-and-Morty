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
## Quickstart
1. Clone repository
2. Download image with Postgres from Docker 'docker pull mihael0204/postgres'
3. Download image with Project 'Rick and Morty' from Docker 'docker pull mihael0204/rick-and-morty'
4. Run project with command 'docker-compose up'
___
## Example of requests
```java
    GET: /characters/random
    http://localhost:8081/characters/random
    GET: /characters/by-name
    http://localhost:8081/characters/?name=namepart
```