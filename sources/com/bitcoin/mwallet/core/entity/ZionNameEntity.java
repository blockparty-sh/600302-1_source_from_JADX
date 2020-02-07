package com.bitcoin.mwallet.core.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.bitcoin.mwallet.zion.ZionId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/entity/ZionNameEntity;", "", "index", "", "zionID", "Lcom/bitcoin/mwallet/zion/ZionId;", "(ILcom/bitcoin/mwallet/zion/ZionId;)V", "getIndex", "()I", "getZionID", "()Lcom/bitcoin/mwallet/zion/ZionId;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(tableName = "zion_name")
/* compiled from: ZionNameEntity.kt */
public final class ZionNameEntity {
    @ColumnInfo(name = "index")
    @PrimaryKey
    private final int index;
    @ColumnInfo(name = "zion_id")
    @Nullable
    private final ZionId zionID;

    @NotNull
    public static /* synthetic */ ZionNameEntity copy$default(ZionNameEntity zionNameEntity, int i, ZionId zionId, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = zionNameEntity.index;
        }
        if ((i2 & 2) != 0) {
            zionId = zionNameEntity.zionID;
        }
        return zionNameEntity.copy(i, zionId);
    }

    public final int component1() {
        return this.index;
    }

    @Nullable
    public final ZionId component2() {
        return this.zionID;
    }

    @NotNull
    public final ZionNameEntity copy(int i, @Nullable ZionId zionId) {
        return new ZionNameEntity(i, zionId);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ZionNameEntity) {
                ZionNameEntity zionNameEntity = (ZionNameEntity) obj;
                if (!(this.index == zionNameEntity.index) || !Intrinsics.areEqual((Object) this.zionID, (Object) zionNameEntity.zionID)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.index * 31;
        ZionId zionId = this.zionID;
        return i + (zionId != null ? zionId.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ZionNameEntity(index=");
        sb.append(this.index);
        sb.append(", zionID=");
        sb.append(this.zionID);
        sb.append(")");
        return sb.toString();
    }

    public ZionNameEntity(int i, @Nullable ZionId zionId) {
        this.index = i;
        this.zionID = zionId;
    }

    public final int getIndex() {
        return this.index;
    }

    @Nullable
    public final ZionId getZionID() {
        return this.zionID;
    }
}
