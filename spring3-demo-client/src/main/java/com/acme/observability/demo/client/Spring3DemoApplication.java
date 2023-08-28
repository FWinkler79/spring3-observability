package com.acme.observability.demo.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.opentelemetry.exporter.otlp.http.logs.OtlpHttpLogRecordExporter;
import io.opentelemetry.exporter.otlp.http.logs.OtlpHttpLogRecordExporterBuilder;
import io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.OpenTelemetrySdkBuilder;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.SdkLoggerProviderBuilder;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessorBuilder;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.resources.ResourceBuilder;

@SpringBootApplication
public class Spring3DemoApplication {

  private static OpenTelemetrySdk sdk;
  private static Map<String, String> resourceAttributes;
  private static String endpoint = "http://localhost:4318/v1/logs";

  public static void main(String[] args) {
    initializeResourceAttributes();
    initializeOpenTelemetrySDK();

    OpenTelemetryAppender.install(sdk);

    SpringApplication.run(Spring3DemoApplication.class, args);
  }

  private static void initializeResourceAttributes() {
    resourceAttributes = new HashMap<>();
    resourceAttributes.put("service.name", "demo-client");
  }

  private static void initializeOpenTelemetrySDK() {

    // only instantiate SDK once.
    if (sdk != null) {
      return;
    }

    OpenTelemetrySdkBuilder sdkBuilder = OpenTelemetrySdk.builder();

    ResourceBuilder resourceBuilder = Resource.getDefault().toBuilder();
    for (Map.Entry<String, String> resourceAttribute : resourceAttributes.entrySet()) {
      resourceBuilder.put(resourceAttribute.getKey(), resourceAttribute.getValue());
    }

    OtlpHttpLogRecordExporterBuilder otlpHttpLogRecordExporterBuilder = OtlpHttpLogRecordExporter.builder();
    otlpHttpLogRecordExporterBuilder.setEndpoint(endpoint);

    BatchLogRecordProcessorBuilder batchLogRecordProcessorBuilder = BatchLogRecordProcessor.builder(otlpHttpLogRecordExporterBuilder.build());
    // LogRecordProcessor simpleLogRecordProcessor = SimpleLogRecordProcessor.create(otlpHttpLogRecordExporterBuilder.build());

    SdkLoggerProviderBuilder loggerProviderBuilder = SdkLoggerProvider.builder();
    loggerProviderBuilder.setResource(resourceBuilder.build());
    loggerProviderBuilder.addLogRecordProcessor(batchLogRecordProcessorBuilder.build());
    // loggerProviderBuilder.addLogRecordProcessor(simpleLogRecordProcessor);

    sdkBuilder.setLoggerProvider(loggerProviderBuilder.build());
    sdk = sdkBuilder.build();
  }
}
