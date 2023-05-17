package org.acme.otel;

import static io.opentelemetry.semconv.resource.attributes.ResourceAttributes.SERVICE_INSTANCE_ID;

import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.sdk.resources.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Julius Krah
 * 
 * @see https://quarkus.io/guides/opentelemetry#resource
 * @see https://github.com/newrelic/newrelic-opentelemetry-examples/blob/main/java/agent-nr-config/otel-initializer/src/main/java/com/newrelic/otel/initializer/ResourceConfigurer.java
 * @see https://github.com/open-telemetry/opentelemetry-java/tree/main/sdk-extensions
 */
@ApplicationScoped
public class ResourceConfiguration {
    private static final Logger log = LoggerFactory.getLogger(ResourceConfiguration.class);
    /**
     * There's a known issue reporting traces to New Relic
     * @see https://github.com/quarkusio/quarkus/issues/22815
     * @return Resource with {@code service.instance.id} attribute
     */
    @Produces
    public Resource newRelicResource() {
        log.info("Adding custom resources {}", ResourceConfiguration.class.getName());
        return Resource.create(
            Attributes.builder().put(SERVICE_INSTANCE_ID, UUID.randomUUID().toString()).build());
    }
}
