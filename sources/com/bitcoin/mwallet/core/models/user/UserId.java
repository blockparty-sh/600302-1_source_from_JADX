package com.bitcoin.mwallet.core.models.user;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/user/UserId;", "", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: UserId.kt */
public final class UserId {
    @Nullable

    /* renamed from: id */
    private final String f374id;

    @NotNull
    public static /* synthetic */ UserId copy$default(UserId userId, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = userId.f374id;
        }
        return userId.copy(str);
    }

    @Nullable
    public final String component1() {
        return this.f374id;
    }

    @NotNull
    public final UserId copy(@Nullable String str) {
        return new UserId(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.f374id, (java.lang.Object) ((com.bitcoin.mwallet.core.models.user.UserId) r2).f374id) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.bitcoin.mwallet.core.models.user.UserId
            if (r0 == 0) goto L_0x0013
            com.bitcoin.mwallet.core.models.user.UserId r2 = (com.bitcoin.mwallet.core.models.user.UserId) r2
            java.lang.String r0 = r1.f374id
            java.lang.String r2 = r2.f374id
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.user.UserId.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.f374id;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public UserId(@Nullable String str) {
        this.f374id = str;
    }

    @Nullable
    public final String getId() {
        return this.f374id;
    }

    @NotNull
    public String toString() {
        return String.valueOf(this.f374id);
    }
}
