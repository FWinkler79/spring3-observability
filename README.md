# Issue Branch

This branch shows some of the problems that come with using the current OTEL Logback Appender.

- [How to Run](#how-to-run)
- [Issue No.1: No logs](#issue-no1-no-logs)
- [Issue No.2: API Dependencies](#issue-no2-api-dependencies)
- [Issue No.3: Where to get the SDK from for injection?](#issue-no3-where-to-get-the-sdk-from-for-injection)

## How to Run

* Start docker container with OTEL collector: 
  * `cd docker`
  * `docker-compose up`

* Start sample app:
  * `cd spring3-demo-client`
  * `mvn spring-boot:run`

## Issue No.1: No logs 

With the current setup, no logs are flowing to the OTEL collector. It is not immediately clear why.

## Issue No.2: API Dependencies

Spring Boot 3 (stable version, August 2023) is using version 1.25.0 of the OTEL SDK, APIs and implementations.
For the static ingestion of an OTEL SDK the OTEL Logback Appender of version 1.29.0 needs to be used.
This means updating versions of dependencies this package pulls along as well.
It is not sure, if this may cause dangerous side-effects.

## Issue No.3: Where to get the SDK from for injection?

Even if the current software design of the OTEL Logback Appender were working as expected, the question would still be where to get the SDK instance from.
Spring instantiates the SDK internally (for use with there observability stack). While it is possible to retrieve the SDK from the Spring Framework as an injected bean, finding the right moment in the application lifecycle to do that is not straight-forward and may be fragile. Unless the Logback Appender for OTLP logs is a managed part of the Spring Framework, for which it can be assured that log output via the Appender uses the right SDK and is properly set up, plugging in the OTEL Logback Appender is not straight forward.

