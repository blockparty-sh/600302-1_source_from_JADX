package org.bitcoinj.protocols.payments;

import java.security.cert.X509Certificate;
import java.util.List;

public class PaymentProtocolException extends Exception {

    public static class Expired extends PaymentProtocolException {
        public Expired(String str) {
            super(str);
        }
    }

    public static class InvalidNetwork extends PaymentProtocolException {
        public InvalidNetwork(String str) {
            super(str);
        }
    }

    public static class InvalidOutputs extends PaymentProtocolException {
        public InvalidOutputs(String str) {
            super(str);
        }
    }

    public static class InvalidPaymentRequestURL extends PaymentProtocolException {
        public InvalidPaymentRequestURL(String str) {
            super(str);
        }

        public InvalidPaymentRequestURL(Exception exc) {
            super(exc);
        }
    }

    public static class InvalidPaymentURL extends PaymentProtocolException {
        public InvalidPaymentURL(Exception exc) {
            super(exc);
        }

        public InvalidPaymentURL(String str) {
            super(str);
        }
    }

    public static class InvalidPkiData extends PaymentProtocolException {
        public InvalidPkiData(String str) {
            super(str);
        }

        public InvalidPkiData(Exception exc) {
            super(exc);
        }
    }

    public static class InvalidPkiType extends PaymentProtocolException {
        public InvalidPkiType(String str) {
            super(str);
        }
    }

    public static class InvalidVersion extends PaymentProtocolException {
        public InvalidVersion(String str) {
            super(str);
        }
    }

    public static class PkiVerificationException extends PaymentProtocolException {
        public List<X509Certificate> certificates;

        public PkiVerificationException(String str) {
            super(str);
        }

        public PkiVerificationException(Exception exc) {
            super(exc);
        }

        public PkiVerificationException(Exception exc, List<X509Certificate> list) {
            super(exc);
            this.certificates = list;
        }
    }

    public PaymentProtocolException(String str) {
        super(str);
    }

    public PaymentProtocolException(Exception exc) {
        super(exc);
    }
}
