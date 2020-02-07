package p015io.opencensus.trace;

import java.util.List;
import p015io.opencensus.trace.Tracestate.Entry;

/* renamed from: io.opencensus.trace.AutoValue_Tracestate */
final class AutoValue_Tracestate extends Tracestate {
    private final List<Entry> entries;

    AutoValue_Tracestate(List<Entry> list) {
        if (list != null) {
            this.entries = list;
            return;
        }
        throw new NullPointerException("Null entries");
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tracestate{entries=");
        sb.append(this.entries);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Tracestate)) {
            return false;
        }
        return this.entries.equals(((Tracestate) obj).getEntries());
    }

    public int hashCode() {
        return this.entries.hashCode() ^ 1000003;
    }
}
