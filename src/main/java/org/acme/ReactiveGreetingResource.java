package org.acme;

import java.util.ArrayList;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.acme.dto.AcknowledgementRequest;
import org.acme.dto.AcknowledgementResponse;
import org.acme.dto.Person;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.Timer;
import io.smallrye.mutiny.Uni;

/**
 * @author Julius Krah
 */
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactiveGreetingResource {
    private static final Logger log = LoggerFactory.getLogger(ReactiveGreetingResource.class);
    @ConfigProperty(name = "quarkus.application.name")
    String applicationName;
    private final MeterRegistry registry;

    ReactiveGreetingResource(MeterRegistry registry) {
        this.registry = registry;
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {
        log.info("Application name: {}", applicationName);
        Timer timer = registry.timer("native.testing.hello", Tags.empty());
        return timer.record(() -> 
            Uni.createFrom().item("Hello RESTEasy Reactive")
        );
    }

    @POST
    @Path("/acknowledge")
    @Timed
    public Uni<AcknowledgementResponse> acknowledgement(AcknowledgementRequest acknowledge) {
        log.info("Acknowlegment received: {}", acknowledge);

        var results = new ArrayList<AcknowledgementResponse.Result>();
        var response = new AcknowledgementResponse();
        var authStatus = new AcknowledgementResponse.AuthStatus();
        authStatus.setAuthStatusCode(131);
        authStatus.setAuthStatusDescription("Authentication was successful");
        var result = new AcknowledgementResponse.Result();
        result.setBeepTransactionID(acknowledge.payload().packets().get(0).beepTransactionId());
        if(1234567890 == acknowledge.payload().packets().get(0).beepTransactionId()) {
            result.setStatusCode(174);
            result.setStatusDescription("Payment not found");
        } else {
            result.setStatusCode(152);
            result.setStatusDescription("The acknowledgement was successful.");
        }
        results.add(result);
        response.setAuthStatus(authStatus);
        response.setResults(results);

        return Uni.createFrom().item(response);
    }

    @POST
    @Path("/calculate")
    @Timed
    public Uni<Person> person(@Valid Person person) {
        log.info("Person {}", person.getFirstName());
        return Uni.createFrom().item(person);
    }
}