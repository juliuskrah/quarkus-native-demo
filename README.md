# code-with-quarkus Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Sending Traces to New Relic

Use the following environment variable at build time

```
QUARKUS_OPENTELEMETRY_TRACER_EXPORTER_OTLP_HEADERS=api-key=<new relic license key>
QUARKUS_APPLICATION_NAME=<name of deployment>
QUARKUS_OPENTELEMETRY_TRACER_RESOURCE_ATTRIBUTES=\
    service.instance.id=${quarkus.uuid},\
    service.namespace=<k8s namespace>,\
    service.version=${quarkus.application.version}
```

## Building JDK 17 container image

- <https://developers.redhat.com/articles/2021/12/14/explore-java-17-language-features-quarkus#generate_container_images_using_jib>

## Creating a native docker image

You can create a native docker image using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true -Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-native-image:21.3-java17
```

See
- <https://quarkus.io/guides/building-native-image#using-the-container-image-extensions>
- <https://quarkus.io/guides/container-image>

## Creating docker image

You can create a docker image using: 
```shell script
./mvnw clean package -Dquarkus.container-image.build=true -Dquarkus.jib.base-jvm-image=registry.access.redhat.com/ubi8/openjdk-17-runtime:1.15
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw clean package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw clean package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-native-image-1.0.1-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): Reactive implementation of JAX-RS with additional features. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
