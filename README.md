# Attendance API

## Description
To allow checking for an available Activity for a given classroom and check-in for the activity

## Getting Started

### Requirements

* Java 17
* Gradle 8.5 or later
*
* PostgreSQL 13.5

## Running Locally

### Start Local Service Containers

Start Docker:

```shell
docker compose up
```

Which will:

* Create a container for local Postgres database
* Run Flyway scripts to import initial data