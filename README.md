# Spring Boot 3 Observability with OpenTelemetry

This project provides a sample setup of two Spring Boot 3.x applications that use Spring Boot 3's new observability features in combination with OpenTelemetry.
Since Spring Boot 3 observability only covers exposure of metrics and traces using OTLP, but not logs, this project also shows how to easily code a custom Logback Appender to intercept logs and export them as OTLP traffic. This appender is inspired by [OpenTelemetry's Logback Appender](https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main/instrumentation/logback/logback-appender-1.0/library) and reuses some of its code, yet removes the lifecycle issues that it comes with.


## References

* [Spring Boot 3.x](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
  * [Observability with Spring Boot 3](https://spring.io/blog/2022/10/12/observability-with-spring-boot-3) | [Accompanying Code Repository](https://github.com/marcingrzejszczak/observability-boot-blog-post/tree/main)
  * [OpenTelemetryAutoConfiguration](https://github.com/spring-projects/spring-boot/blob/v3.1.3/spring-boot-project/spring-boot-actuator-autoconfigure/src/main/java/org/springframework/boot/actuate/autoconfigure/tracing/OpenTelemetryAutoConfiguration.java)
  * [OtlpAutoConfiguration](https://github.com/spring-projects/spring-boot/blob/v3.1.3/spring-boot-project/spring-boot-actuator-autoconfigure/src/main/java/org/springframework/boot/actuate/autoconfigure/tracing/otlp/OtlpAutoConfiguration.java)
  * [OtlpMetricsExportAutoConfiguration](https://github.com/spring-projects/spring-boot/blob/v3.1.3/spring-boot-project/spring-boot-actuator-autoconfigure/src/main/java/org/springframework/boot/actuate/autoconfigure/metrics/export/otlp/OtlpMetricsExportAutoConfiguration.java)
  * [ObservationAutoConfiguration](https://github.com/spring-projects/spring-boot/blob/v3.1.3/spring-boot-project/spring-boot-actuator-autoconfigure/src/main/java/org/springframework/boot/actuate/autoconfigure/observation/ObservationAutoConfiguration.java)
* [OpenTelemetry](https://opentelemetry.io/)
  * [OpenTelemetry Java Instrumentation](https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main) | [List of Supported Instrumentations](https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main/instrumentation)
  * [OpenTelemetry Java Instrumentation - Logback Appender](https://github.com/open-telemetry/opentelemetry-java-instrumentation/tree/main/instrumentation/logback/logback-appender-1.0/library)
  * [OpenTelemetry Java Examples - Log Appender](https://github.com/open-telemetry/opentelemetry-java-examples/tree/main/log-appender)
* [Micrometer](https://micrometer.io/docs)
  * [Micrometer Tracing](https://micrometer.io/docs/tracing)
  * [Micrometer Metrics (OTLP)](https://micrometer.io/docs/registry/otlp)
