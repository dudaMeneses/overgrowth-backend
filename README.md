[![CircleCI](https://circleci.com/gh/dudaMeneses/overgrowth-backend.svg?style=shield)](https://circleci.com/gh/dudaMeneses/overgrowth-backend/master)
[![CodeFactor](https://www.codefactor.io/repository/github/dudameneses/overgrowth-backend/badge)](https://www.codefactor.io/repository/github/dudameneses/overgrowth-backend)

# Overgrowth
Application to support recommend training and support its progression.

## Pre-requirements
- [jdk14](https://jdk.java.net/14/)
- [maven](https://maven.apache.org/download.cgi)
- [docker-compose](https://docs.docker.com/compose/install/)
- [make](http://gnuwin32.sourceforge.net/packages/make.htm) (for Windows)

## How to

### Configure
- Add environment variable to runner, docker or even local machine:
```
MONGODB_URI=?
```

### Run
- Local: `make local-run`
- Docker: `make docker-run`

### Test and Report
- Test and generate report: `make test`
- SonarQube report: `make sonar`

>Important to remember that SonarQube step will work only if docker-run is running before

## Tech Stack
- Java 14
- Spring-Boot (webflux and mongodb)
- Maven
- MongoDB