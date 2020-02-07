package com.bitcoin.mwallet.zion;

import com.bitcoin.mwallet.core.models.Coin;
import java.util.EnumSet;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionNamePrefix;", "", "prefix", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getPrefix", "()Ljava/lang/String;", "toCoins", "Ljava/util/EnumSet;", "Lcom/bitcoin/mwallet/core/models/Coin;", "BCH", "BTC", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ZionNamePrefix.kt */
public enum ZionNamePrefix {
    BCH("bch"),
    BTC("btc");
    
    public static final Companion Companion = null;
    @NotNull
    private final String prefix;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionNamePrefix$Companion;", "", "()V", "from", "Lcom/bitcoin/mwallet/zion/ZionNamePrefix;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ZionNamePrefix.kt */
    public static final class Companion {

        @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            static {
                $EnumSwitchMapping$0 = new int[Coin.values().length];
                $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
                $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ZionNamePrefix from(@NotNull Coin coin) {
            Intrinsics.checkParameterIsNotNull(coin, "coin");
            int i = WhenMappings.$EnumSwitchMapping$0[coin.ordinal()];
            if (i == 1) {
                return ZionNamePrefix.BCH;
            }
            if (i == 2) {
                return ZionNamePrefix.BTC;
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            $EnumSwitchMapping$0 = new int[ZionNamePrefix.values().length];
            $EnumSwitchMapping$0[ZionNamePrefix.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[ZionNamePrefix.BTC.ordinal()] = 2;
        }
    }

    private ZionNamePrefix(String str) {
        this.prefix = str;
    }

    @NotNull
    public final String getPrefix() {
        return this.prefix;
    }

    static {
        Companion = new Companion(null);
    }

    @NotNull
    public final EnumSet<Coin> toCoins() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            EnumSet<Coin> of = EnumSet.of(Coin.BCH);
            Intrinsics.checkExpressionValueIsNotNull(of, "EnumSet.of(Coin.BCH)");
            return of;
        } else if (i == 2) {
            EnumSet<Coin> of2 = EnumSet.of(Coin.BTC);
            Intrinsics.checkExpressionValueIsNotNull(of2, "EnumSet.of(Coin.BTC)");
            return of2;
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }
}
