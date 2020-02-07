package p015io.opencensus.trace.export;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import p015io.opencensus.common.Timestamp;
import p015io.opencensus.internal.C2865Utils;
import p015io.opencensus.trace.Annotation;
import p015io.opencensus.trace.AttributeValue;
import p015io.opencensus.trace.BaseMessageEvent;
import p015io.opencensus.trace.Link;
import p015io.opencensus.trace.MessageEvent;
import p015io.opencensus.trace.NetworkEvent;
import p015io.opencensus.trace.Span.Kind;
import p015io.opencensus.trace.SpanContext;
import p015io.opencensus.trace.SpanId;
import p015io.opencensus.trace.Status;
import p015io.opencensus.trace.internal.BaseMessageEventUtils;

@Immutable
/* renamed from: io.opencensus.trace.export.SpanData */
public abstract class SpanData {

    @Immutable
    /* renamed from: io.opencensus.trace.export.SpanData$Attributes */
    public static abstract class Attributes {
        public abstract Map<String, AttributeValue> getAttributeMap();

        public abstract int getDroppedAttributesCount();

        public static Attributes create(Map<String, AttributeValue> map, int i) {
            return new AutoValue_SpanData_Attributes(Collections.unmodifiableMap(new HashMap((Map) C2865Utils.checkNotNull(map, "attributeMap"))), i);
        }

        Attributes() {
        }
    }

    @Immutable
    /* renamed from: io.opencensus.trace.export.SpanData$Links */
    public static abstract class Links {
        public abstract int getDroppedLinksCount();

        public abstract List<Link> getLinks();

        public static Links create(List<Link> list, int i) {
            return new AutoValue_SpanData_Links(Collections.unmodifiableList(new ArrayList((Collection) C2865Utils.checkNotNull(list, "links"))), i);
        }

        Links() {
        }
    }

    @Immutable
    /* renamed from: io.opencensus.trace.export.SpanData$TimedEvent */
    public static abstract class TimedEvent<T> {
        public abstract T getEvent();

        public abstract Timestamp getTimestamp();

        public static <T> TimedEvent<T> create(Timestamp timestamp, T t) {
            return new AutoValue_SpanData_TimedEvent(timestamp, t);
        }

        TimedEvent() {
        }
    }

    @Immutable
    /* renamed from: io.opencensus.trace.export.SpanData$TimedEvents */
    public static abstract class TimedEvents<T> {
        public abstract int getDroppedEventsCount();

        public abstract List<TimedEvent<T>> getEvents();

        public static <T> TimedEvents<T> create(List<TimedEvent<T>> list, int i) {
            return new AutoValue_SpanData_TimedEvents(Collections.unmodifiableList(new ArrayList((Collection) C2865Utils.checkNotNull(list, "events"))), i);
        }

        TimedEvents() {
        }
    }

    public abstract TimedEvents<Annotation> getAnnotations();

    public abstract Attributes getAttributes();

    @Nullable
    public abstract Integer getChildSpanCount();

    public abstract SpanContext getContext();

    @Nullable
    public abstract Timestamp getEndTimestamp();

    @Nullable
    public abstract Boolean getHasRemoteParent();

    @Nullable
    public abstract Kind getKind();

    public abstract Links getLinks();

    public abstract TimedEvents<MessageEvent> getMessageEvents();

    public abstract String getName();

    @Nullable
    public abstract SpanId getParentSpanId();

    public abstract Timestamp getStartTimestamp();

    @Nullable
    public abstract Status getStatus();

    @Deprecated
    public static SpanData create(SpanContext spanContext, @Nullable SpanId spanId, @Nullable Boolean bool, String str, Timestamp timestamp, Attributes attributes, TimedEvents<Annotation> timedEvents, TimedEvents<? extends BaseMessageEvent> timedEvents2, Links links, @Nullable Integer num, @Nullable Status status, @Nullable Timestamp timestamp2) {
        return create(spanContext, spanId, bool, str, null, timestamp, attributes, timedEvents, timedEvents2, links, num, status, timestamp2);
    }

    public static SpanData create(SpanContext spanContext, @Nullable SpanId spanId, @Nullable Boolean bool, String str, @Nullable Kind kind, Timestamp timestamp, Attributes attributes, TimedEvents<Annotation> timedEvents, TimedEvents<? extends BaseMessageEvent> timedEvents2, Links links, @Nullable Integer num, @Nullable Status status, @Nullable Timestamp timestamp2) {
        C2865Utils.checkNotNull(timedEvents2, "messageOrNetworkEvents");
        ArrayList arrayList = new ArrayList();
        for (TimedEvent timedEvent : timedEvents2.getEvents()) {
            BaseMessageEvent baseMessageEvent = (BaseMessageEvent) timedEvent.getEvent();
            if (baseMessageEvent instanceof MessageEvent) {
                arrayList.add(timedEvent);
            } else {
                arrayList.add(TimedEvent.create(timedEvent.getTimestamp(), BaseMessageEventUtils.asMessageEvent(baseMessageEvent)));
            }
        }
        AutoValue_SpanData autoValue_SpanData = new AutoValue_SpanData(spanContext, spanId, bool, str, kind, timestamp, attributes, timedEvents, TimedEvents.create(arrayList, timedEvents2.getDroppedEventsCount()), links, num, status, timestamp2);
        return autoValue_SpanData;
    }

    @Deprecated
    public TimedEvents<NetworkEvent> getNetworkEvents() {
        TimedEvents messageEvents = getMessageEvents();
        ArrayList arrayList = new ArrayList();
        for (TimedEvent timedEvent : messageEvents.getEvents()) {
            arrayList.add(TimedEvent.create(timedEvent.getTimestamp(), BaseMessageEventUtils.asNetworkEvent((BaseMessageEvent) timedEvent.getEvent())));
        }
        return TimedEvents.create(arrayList, messageEvents.getDroppedEventsCount());
    }

    SpanData() {
    }
}
