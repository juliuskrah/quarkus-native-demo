package org.acme.dto;

import java.util.List;

/**
 * @author Julius Krah
 */
public class AcknowledgementResponse {
    private AuthStatus authStatus;
    private List<Result> results;

    public AuthStatus getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(AuthStatus authStatus) {
        this.authStatus = authStatus;
    }
    
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }


    public static class AuthStatus {
        private Integer authStatusCode;
        private String authStatusDescription;

        public Integer getAuthStatusCode() {
            return authStatusCode;
        }

        public void setAuthStatusCode(Integer authStatusCode) {
            this.authStatusCode = authStatusCode;
        }

        public String getAuthStatusDescription() {
            return authStatusDescription;
        }

        public void setAuthStatusDescription(String authStatusDescription) {
            this.authStatusDescription = authStatusDescription;
        }

        @Override
        public String toString() {
            return "AuthStatus [authStatusCode=" + authStatusCode + ", authStatusDescription="
                    + authStatusDescription + "]";
        }

    }

    public static class Result {
        Integer statusCode;
        String statusDescription;
        String invoiceNumber = "";
        Long beepTransactionID;

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusDescription() {
            return statusDescription;
        }

        public void setStatusDescription(String statusDescription) {
            this.statusDescription = statusDescription;
        }

        public String getInvoiceNumber() {
            return invoiceNumber;
        }

        public void setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }

        public Long getBeepTransactionID() {
            return beepTransactionID;
        }

        public void setBeepTransactionID(Long beepTransactionID) {
            this.beepTransactionID = beepTransactionID;
        }

        @Override
        public String toString() {
            return "Result [beepTransactionID=" + beepTransactionID + ", invoiceNumber="
                    + invoiceNumber + ", statusCode=" + statusCode + ", statusDescription="
                    + statusDescription + "]";
        }

    }

    @Override
    public String toString() {
        return "AcknowledgementResponse [authStatus=" + authStatus + ", results=" + results + "]";
    }

}
