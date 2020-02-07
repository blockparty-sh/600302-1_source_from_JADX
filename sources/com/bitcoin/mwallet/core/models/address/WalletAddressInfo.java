package com.bitcoin.mwallet.core.models.address;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\b\u0018\u0000 \u00192\u00020\u0001:\u0002\u0019\u001aB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/address/WalletAddressInfo;", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "pathX", "", "pathY", "Lcom/bitcoin/mwallet/core/models/address/WalletAddressInfo$PathY;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;ILcom/bitcoin/mwallet/core/models/address/WalletAddressInfo$PathY;)V", "getPathX", "()I", "getPathY", "()Lcom/bitcoin/mwallet/core/models/address/WalletAddressInfo$PathY;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "PathY", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(primaryKeys = {"wallet_id"}, tableName = "wallet_address_info")
/* compiled from: WalletAddressInfo.kt */
public final class WalletAddressInfo {
    public static final Companion Companion = new Companion(null);
    @ColumnInfo(name = "path_x")
    private final int pathX;
    @NotNull
    @Embedded(prefix = "path_y_")
    private final PathY pathY;
    @ColumnInfo(name = "wallet_id")
    @NotNull
    private final WalletId walletId;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/address/WalletAddressInfo$Companion;", "", "()V", "default", "Lcom/bitcoin/mwallet/core/models/address/WalletAddressInfo;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "purpose", "Lcom/bitcoin/mwallet/core/models/address/AddressPurpose;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletAddressInfo.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        /* renamed from: default reason: not valid java name */
        public final WalletAddressInfo m474default(@NotNull WalletId walletId, @NotNull AddressPurpose addressPurpose) {
            Intrinsics.checkParameterIsNotNull(walletId, "walletId");
            Intrinsics.checkParameterIsNotNull(addressPurpose, "purpose");
            return new WalletAddressInfo(walletId, addressPurpose.getPathX(), new PathY(0, 0, 3, null));
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÂ\u0003J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\r\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000fJ\u0006\u0010\u0010\u001a\u00020\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\u0006\u0010\u0012\u001a\u00020\u0000J\u0006\u0010\u0013\u001a\u00020\fJ\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\u0002\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/address/WalletAddressInfo$PathY;", "", "next", "", "max", "(II)V", "getMax", "()I", "component1", "component2", "copy", "equals", "", "other", "getNext", "()Ljava/lang/Integer;", "getNextOrMax", "hashCode", "increment", "needMore", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletAddressInfo.kt */
    public static final class PathY {
        @ColumnInfo(name = "max")
        private final int max;
        @ColumnInfo(name = "next")
        private final int next;

        public PathY() {
            this(0, 0, 3, null);
        }

        private final int component1() {
            return this.next;
        }

        @NotNull
        public static /* synthetic */ PathY copy$default(PathY pathY, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = pathY.next;
            }
            if ((i3 & 2) != 0) {
                i2 = pathY.max;
            }
            return pathY.copy(i, i2);
        }

        public final int component2() {
            return this.max;
        }

        @NotNull
        public final PathY copy(int i, int i2) {
            return new PathY(i, i2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof PathY) {
                    PathY pathY = (PathY) obj;
                    if (this.next == pathY.next) {
                        if (this.max == pathY.max) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.next * 31) + this.max;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("PathY(next=");
            sb.append(this.next);
            sb.append(", max=");
            sb.append(this.max);
            sb.append(")");
            return sb.toString();
        }

        public PathY(int i, int i2) {
            this.next = i;
            this.max = i2;
        }

        public /* synthetic */ PathY(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i3 & 1) != 0) {
                i = 0;
            }
            if ((i3 & 2) != 0) {
                i2 = 30;
            }
            this(i, i2);
        }

        public final int getMax() {
            return this.max;
        }

        @NotNull
        public final PathY increment() {
            int i = this.next;
            if (i < 0) {
                return this;
            }
            if (i < this.max) {
                return copy$default(this, i + 1, 0, 2, null);
            }
            return copy$default(this, -1, 0, 2, null);
        }

        @Nullable
        public final Integer getNext() {
            int i = this.next;
            if (i >= 0) {
                return Integer.valueOf(i);
            }
            return null;
        }

        public final int getNextOrMax() {
            Integer next2 = getNext();
            return next2 != null ? next2.intValue() : this.max;
        }

        public final boolean needMore() {
            return getNext() == null || this.max - this.next < 10;
        }
    }

    @NotNull
    public static /* synthetic */ WalletAddressInfo copy$default(WalletAddressInfo walletAddressInfo, WalletId walletId2, int i, PathY pathY2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            walletId2 = walletAddressInfo.walletId;
        }
        if ((i2 & 2) != 0) {
            i = walletAddressInfo.pathX;
        }
        if ((i2 & 4) != 0) {
            pathY2 = walletAddressInfo.pathY;
        }
        return walletAddressInfo.copy(walletId2, i, pathY2);
    }

    @NotNull
    public final WalletId component1() {
        return this.walletId;
    }

    public final int component2() {
        return this.pathX;
    }

    @NotNull
    public final PathY component3() {
        return this.pathY;
    }

    @NotNull
    public final WalletAddressInfo copy(@NotNull WalletId walletId2, int i, @NotNull PathY pathY2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(pathY2, "pathY");
        return new WalletAddressInfo(walletId2, i, pathY2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof WalletAddressInfo) {
                WalletAddressInfo walletAddressInfo = (WalletAddressInfo) obj;
                if (Intrinsics.areEqual((Object) this.walletId, (Object) walletAddressInfo.walletId)) {
                    if (!(this.pathX == walletAddressInfo.pathX) || !Intrinsics.areEqual((Object) this.pathY, (Object) walletAddressInfo.pathY)) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        WalletId walletId2 = this.walletId;
        int i = 0;
        int hashCode = (((walletId2 != null ? walletId2.hashCode() : 0) * 31) + this.pathX) * 31;
        PathY pathY2 = this.pathY;
        if (pathY2 != null) {
            i = pathY2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WalletAddressInfo(walletId=");
        sb.append(this.walletId);
        sb.append(", pathX=");
        sb.append(this.pathX);
        sb.append(", pathY=");
        sb.append(this.pathY);
        sb.append(")");
        return sb.toString();
    }

    public WalletAddressInfo(@NotNull WalletId walletId2, int i, @NotNull PathY pathY2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(pathY2, "pathY");
        this.walletId = walletId2;
        this.pathX = i;
        this.pathY = pathY2;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    public final int getPathX() {
        return this.pathX;
    }

    @NotNull
    public final PathY getPathY() {
        return this.pathY;
    }
}
