package p015io.grpc;

import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

@Internal
/* renamed from: io.grpc.InternalLogId */
public final class InternalLogId {
    private static final AtomicLong idAlloc = new AtomicLong();
    @Nullable
    private final String details;

    /* renamed from: id */
    private final long f677id;
    private final String typeName;

    public static InternalLogId allocate(Class<?> cls, @Nullable String str) {
        return allocate(getClassName(cls), str);
    }

    public static InternalLogId allocate(String str, @Nullable String str2) {
        return new InternalLogId(str, str2, getNextId());
    }

    static long getNextId() {
        return idAlloc.incrementAndGet();
    }

    InternalLogId(String str, String str2, long j) {
        Preconditions.checkNotNull(str, "typeName");
        Preconditions.checkArgument(!str.isEmpty(), "empty type");
        this.typeName = str;
        this.details = str2;
        this.f677id = j;
    }

    public String getTypeName() {
        return this.typeName;
    }

    @Nullable
    public String getDetails() {
        return this.details;
    }

    public long getId() {
        return this.f677id;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(shortName());
        if (this.details != null) {
            sb.append(": (");
            sb.append(this.details);
            sb.append(')');
        }
        return sb.toString();
    }

    private static String getClassName(Class<?> cls) {
        String simpleName = ((Class) Preconditions.checkNotNull(cls, "type")).getSimpleName();
        if (!simpleName.isEmpty()) {
            return simpleName;
        }
        return cls.getName().substring(cls.getPackage().getName().length() + 1);
    }

    public String shortName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.typeName);
        sb.append("<");
        sb.append(this.f677id);
        sb.append(">");
        return sb.toString();
    }
}
