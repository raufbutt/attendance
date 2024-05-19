# Attendance API

## Description
To retrieve classroom details and allow checkin for an available Activity for a given classroom

## Getting Started

### Requirements
* Springboot project
* Java 17
* Gradle 8.5 or later
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

## Swagger UI

* Run the service
* Type in browser: http://localhost:8080/swagger-ui/index.html

## Security and Integrity

* To avoid forgery and to maintain security, we would implement solutions such as Authentication via Single SignOn, Custom Request Header, CSRF token

## Misuse of QR code

* To avoid misusing the QR code, we can look into the possibility of generating dynamic QR code and take in student geolocation along with the other security credentials.