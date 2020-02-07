package org.bitcoinj.protocols.channels;

public class PaymentChannelCloseException extends Exception {
    private final CloseReason error;

    public enum CloseReason {
        NO_ACCEPTABLE_VERSION,
        TIME_WINDOW_UNACCEPTABLE,
        SERVER_REQUESTED_TOO_MUCH_VALUE,
        CHANNEL_EXHAUSTED,
        CLIENT_REQUESTED_CLOSE,
        SERVER_REQUESTED_CLOSE,
        REMOTE_SENT_ERROR,
        REMOTE_SENT_INVALID_MESSAGE,
        CONNECTION_CLOSED,
        UPDATE_PAYMENT_FAILED
    }

    public PaymentChannelCloseException(String str, CloseReason closeReason) {
        super(str);
        this.error = closeReason;
    }

    public CloseReason getCloseReason() {
        return this.error;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PaymentChannelCloseException for reason ");
        sb.append(getCloseReason());
        return sb.toString();
    }
}
