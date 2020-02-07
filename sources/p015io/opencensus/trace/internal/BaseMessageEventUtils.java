package p015io.opencensus.trace.internal;

import p015io.opencensus.internal.C2865Utils;
import p015io.opencensus.trace.BaseMessageEvent;
import p015io.opencensus.trace.MessageEvent;
import p015io.opencensus.trace.MessageEvent.C2900Type;
import p015io.opencensus.trace.NetworkEvent;
import p015io.opencensus.trace.NetworkEvent.C2901Type;

/* renamed from: io.opencensus.trace.internal.BaseMessageEventUtils */
public final class BaseMessageEventUtils {
    public static MessageEvent asMessageEvent(BaseMessageEvent baseMessageEvent) {
        C2900Type type;
        C2865Utils.checkNotNull(baseMessageEvent, "event");
        if (baseMessageEvent instanceof MessageEvent) {
            return (MessageEvent) baseMessageEvent;
        }
        NetworkEvent networkEvent = (NetworkEvent) baseMessageEvent;
        if (networkEvent.getType() == C2901Type.RECV) {
            type = C2900Type.RECEIVED;
        } else {
            type = C2900Type.SENT;
        }
        return MessageEvent.builder(type, networkEvent.getMessageId()).setUncompressedMessageSize(networkEvent.getUncompressedMessageSize()).setCompressedMessageSize(networkEvent.getCompressedMessageSize()).build();
    }

    public static NetworkEvent asNetworkEvent(BaseMessageEvent baseMessageEvent) {
        C2901Type type;
        C2865Utils.checkNotNull(baseMessageEvent, "event");
        if (baseMessageEvent instanceof NetworkEvent) {
            return (NetworkEvent) baseMessageEvent;
        }
        MessageEvent messageEvent = (MessageEvent) baseMessageEvent;
        if (messageEvent.getType() == C2900Type.RECEIVED) {
            type = C2901Type.RECV;
        } else {
            type = C2901Type.SENT;
        }
        return NetworkEvent.builder(type, messageEvent.getMessageId()).setUncompressedMessageSize(messageEvent.getUncompressedMessageSize()).setCompressedMessageSize(messageEvent.getCompressedMessageSize()).build();
    }

    private BaseMessageEventUtils() {
    }
}
