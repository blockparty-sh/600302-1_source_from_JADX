package com.bitcoin.mwallet.zion;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8FX\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionWalletName;", "", "prefix", "Lcom/bitcoin/mwallet/zion/ZionNamePrefix;", "index", "", "(Lcom/bitcoin/mwallet/zion/ZionNamePrefix;I)V", "getIndex", "()I", "name", "", "getName", "()Ljava/lang/String;", "name$delegate", "Lkotlin/Lazy;", "getPrefix", "()Lcom/bitcoin/mwallet/zion/ZionNamePrefix;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ZionWalletName.kt */
public final class ZionWalletName {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ZionWalletName.class), "name", "getName()Ljava/lang/String;"))};
    private final int index;
    @NotNull
    private final Lazy name$delegate = LazyKt.lazy(new ZionWalletName$name$2(this));
    @NotNull
    private final ZionNamePrefix prefix;

    @NotNull
    public static /* synthetic */ ZionWalletName copy$default(ZionWalletName zionWalletName, ZionNamePrefix zionNamePrefix, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            zionNamePrefix = zionWalletName.prefix;
        }
        if ((i2 & 2) != 0) {
            i = zionWalletName.index;
        }
        return zionWalletName.copy(zionNamePrefix, i);
    }

    @NotNull
    public final ZionNamePrefix component1() {
        return this.prefix;
    }

    public final int component2() {
        return this.index;
    }

    @NotNull
    public final ZionWalletName copy(@NotNull ZionNamePrefix zionNamePrefix, int i) {
        Intrinsics.checkParameterIsNotNull(zionNamePrefix, "prefix");
        return new ZionWalletName(zionNamePrefix, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ZionWalletName) {
                ZionWalletName zionWalletName = (ZionWalletName) obj;
                if (Intrinsics.areEqual((Object) this.prefix, (Object) zionWalletName.prefix)) {
                    if (this.index == zionWalletName.index) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String getName() {
        Lazy lazy = this.name$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (String) lazy.getValue();
    }

    public int hashCode() {
        ZionNamePrefix zionNamePrefix = this.prefix;
        return ((zionNamePrefix != null ? zionNamePrefix.hashCode() : 0) * 31) + this.index;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ZionWalletName(prefix=");
        sb.append(this.prefix);
        sb.append(", index=");
        sb.append(this.index);
        sb.append(")");
        return sb.toString();
    }

    public ZionWalletName(@NotNull ZionNamePrefix zionNamePrefix, int i) {
        Intrinsics.checkParameterIsNotNull(zionNamePrefix, "prefix");
        this.prefix = zionNamePrefix;
        this.index = i;
    }

    @NotNull
    public final ZionNamePrefix getPrefix() {
        return this.prefix;
    }

    public final int getIndex() {
        return this.index;
    }
}
