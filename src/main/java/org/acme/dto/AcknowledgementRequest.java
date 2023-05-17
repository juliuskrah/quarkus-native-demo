package org.acme.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Julius Krah
 */
public record AcknowledgementRequest(
    String function,
    Payload payload
) {
    public record Payload(
        Credentials credentials,
        @JsonProperty("packet")
        List<Packet> packets
    ) {

    }

    record Credentials(
        String username,
        String password
    ) {

    }

    public record Packet(
        String receiptNumber,
        @JsonProperty("beepTransactionID")
        Long beepTransactionId,
        Integer statusCode,
        String statusDescription,
        Double amountExpected,
        Byte isTokenService,
        @JsonProperty("payerTransactionID")
        String payerTransactionId
    ) {

    }
}
