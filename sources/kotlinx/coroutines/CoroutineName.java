package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext.C2916Key;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, mo37405d2 = {"Lkotlinx/coroutines/CoroutineName;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Key", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CoroutineName.kt */
public final class CoroutineName extends AbstractCoroutineContextElement {
    public static final C3159Key Key = new C3159Key(null);
    @NotNull
    private final String name;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, mo37405d2 = {"Lkotlinx/coroutines/CoroutineName$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlinx/coroutines/CoroutineName;", "()V", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: kotlinx.coroutines.CoroutineName$Key */
    /* compiled from: CoroutineName.kt */
    public static final class C3159Key implements C2916Key<CoroutineName> {
        private C3159Key() {
        }

        public /* synthetic */ C3159Key(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    public static /* synthetic */ CoroutineName copy$default(CoroutineName coroutineName, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = coroutineName.name;
        }
        return coroutineName.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final CoroutineName copy(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        return new CoroutineName(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.name, (java.lang.Object) ((kotlinx.coroutines.CoroutineName) r2).name) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof kotlinx.coroutines.CoroutineName
            if (r0 == 0) goto L_0x0013
            kotlinx.coroutines.CoroutineName r2 = (kotlinx.coroutines.CoroutineName) r2
            java.lang.String r0 = r1.name
            java.lang.String r2 = r2.name
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 0
            return r2
        L_0x0015:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CoroutineName.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.name;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public CoroutineName(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        super(Key);
        this.name = str;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CoroutineName(");
        sb.append(this.name);
        sb.append(')');
        return sb.toString();
    }
}
