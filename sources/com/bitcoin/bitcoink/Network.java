package com.bitcoin.bitcoink;

import com.leanplum.internal.Constants.Params;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.bitcoinj.core.NetworkParameters;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u00020\b8@X\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/Network;", "", "(Ljava/lang/String;I)V", "acceptableAddressCodes", "", "getAcceptableAddressCodes$bitcoink", "()[I", "params", "Lorg/bitcoinj/core/NetworkParameters;", "getParams$bitcoink", "()Lorg/bitcoinj/core/NetworkParameters;", "params$delegate", "Lkotlin/Lazy;", "MAIN", "TEST", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Network.kt */
public enum Network {
    MAIN,
    TEST;
    
    @NotNull
    private final int[] acceptableAddressCodes;
    @NotNull
    private final Lazy params$delegate;

    static {
        $$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Network.class), Params.PARAMS, "getParams$bitcoink()Lorg/bitcoinj/core/NetworkParameters;"))};
    }

    @NotNull
    public final NetworkParameters getParams$bitcoink() {
        Lazy lazy = this.params$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (NetworkParameters) lazy.getValue();
    }

    @NotNull
    public final int[] getAcceptableAddressCodes$bitcoink() {
        return this.acceptableAddressCodes;
    }
}
