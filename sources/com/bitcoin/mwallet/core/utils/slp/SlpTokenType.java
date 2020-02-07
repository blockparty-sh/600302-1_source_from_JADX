package com.bitcoin.mwallet.core.utils.slp;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\b\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000j\u0002\b\u000b¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/utils/slp/SlpTokenType;", "", "value", "", "(Ljava/lang/String;II)V", "bytes", "", "getBytes", "()[B", "bytes$delegate", "Lkotlin/Lazy;", "PERMISSIONLESS", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SlpTokenType.kt */
public enum SlpTokenType {
    PERMISSIONLESS(1);
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public static final Map<Integer, SlpTokenType> deserializer = null;
    @NotNull
    private final Lazy bytes$delegate;
    /* access modifiers changed from: private */
    public final int value;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/utils/slp/SlpTokenType$Companion;", "", "()V", "deserializer", "", "", "Lcom/bitcoin/mwallet/core/utils/slp/SlpTokenType;", "tryParse", "bytes", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SlpTokenType.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final SlpTokenType tryParse(@NotNull byte[] bArr) {
            Intrinsics.checkParameterIsNotNull(bArr, "bytes");
            if ((bArr.length == 0) || bArr.length > 2) {
                return null;
            }
            return (SlpTokenType) SlpTokenType.deserializer.get(Integer.valueOf(ByteUtils.INSTANCE.toInt(bArr)));
        }
    }

    @NotNull
    public final byte[] getBytes() {
        Lazy lazy = this.bytes$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (byte[]) lazy.getValue();
    }

    private SlpTokenType(int i) {
        this.value = i;
        this.bytes$delegate = LazyKt.lazy(new SlpTokenType$bytes$2(this));
    }

    static {
        int i;
        $$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SlpTokenType.class), "bytes", "getBytes()[B"))};
        Companion = new Companion(null);
        SlpTokenType[] values = values();
        Map<Integer, SlpTokenType> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(values.length), 16));
        for (SlpTokenType slpTokenType : values) {
            linkedHashMap.put(Integer.valueOf(slpTokenType.value), slpTokenType);
        }
        deserializer = linkedHashMap;
    }
}
