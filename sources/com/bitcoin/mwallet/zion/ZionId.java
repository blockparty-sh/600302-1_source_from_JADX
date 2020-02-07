package com.bitcoin.mwallet.zion;

import java.io.Serializable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionId;", "Ljava/io/Serializable;", "id", "", "(J)V", "getId", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ZionId.kt */
public final class ZionId implements Serializable {

    /* renamed from: id */
    private final long f454id;

    @NotNull
    public static /* synthetic */ ZionId copy$default(ZionId zionId, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = zionId.f454id;
        }
        return zionId.copy(j);
    }

    public final long component1() {
        return this.f454id;
    }

    @NotNull
    public final ZionId copy(long j) {
        return new ZionId(j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ZionId) {
                if (this.f454id == ((ZionId) obj).f454id) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.f454id;
        return (int) (j ^ (j >>> 32));
    }

    public ZionId(long j) {
        this.f454id = j;
    }

    public final long getId() {
        return this.f454id;
    }

    @NotNull
    public String toString() {
        return String.valueOf(this.f454id);
    }
}
