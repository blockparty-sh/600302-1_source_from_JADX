package p015io.grpc;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1764")
@Immutable
/* renamed from: io.grpc.Attributes */
public final class Attributes {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Attributes EMPTY = new Attributes(Collections.emptyMap());
    /* access modifiers changed from: private */
    public final Map<C2669Key<?>, Object> data;

    /* renamed from: io.grpc.Attributes$Builder */
    public static final class Builder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Attributes base;
        private Map<C2669Key<?>, Object> newdata;

        static {
            Class<Attributes> cls = Attributes.class;
        }

        private Builder(Attributes attributes) {
            this.base = attributes;
        }

        private Map<C2669Key<?>, Object> data(int i) {
            if (this.newdata == null) {
                this.newdata = new IdentityHashMap(i);
            }
            return this.newdata;
        }

        public <T> Builder set(C2669Key<T> key, T t) {
            data(1).put(key, t);
            return this;
        }

        public <T> Builder setAll(Attributes attributes) {
            data(attributes.data.size()).putAll(attributes.data);
            return this;
        }

        public Attributes build() {
            if (this.newdata != null) {
                for (Entry entry : this.base.data.entrySet()) {
                    if (!this.newdata.containsKey(entry.getKey())) {
                        this.newdata.put(entry.getKey(), entry.getValue());
                    }
                }
                this.base = new Attributes(this.newdata);
                this.newdata = null;
            }
            return this.base;
        }
    }

    @Immutable
    /* renamed from: io.grpc.Attributes$Key */
    public static final class C2669Key<T> {
        private final String debugString;

        private C2669Key(String str) {
            this.debugString = str;
        }

        public String toString() {
            return this.debugString;
        }

        @Deprecated
        /* renamed from: of */
        public static <T> C2669Key<T> m292of(String str) {
            return new C2669Key<>(str);
        }

        public static <T> C2669Key<T> create(String str) {
            return new C2669Key<>(str);
        }
    }

    private Attributes(Map<C2669Key<?>, Object> map) {
        this.data = map;
    }

    @Nullable
    public <T> T get(C2669Key<T> key) {
        return this.data.get(key);
    }

    @Deprecated
    public Set<C2669Key<?>> keys() {
        return Collections.unmodifiableSet(this.data.keySet());
    }

    /* access modifiers changed from: 0000 */
    public Set<C2669Key<?>> keysForTest() {
        return Collections.unmodifiableSet(this.data.keySet());
    }

    @Deprecated
    public static Builder newBuilder(Attributes attributes) {
        Preconditions.checkNotNull(attributes, "base");
        return new Builder();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder();
    }

    public String toString() {
        return this.data.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Attributes attributes = (Attributes) obj;
        if (this.data.size() != attributes.data.size()) {
            return false;
        }
        for (Entry entry : this.data.entrySet()) {
            if (!attributes.data.containsKey(entry.getKey())) {
                return false;
            }
            if (!Objects.equal(entry.getValue(), attributes.data.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        for (Entry entry : this.data.entrySet()) {
            i += Objects.hashCode(entry.getKey(), entry.getValue());
        }
        return i;
    }
}
