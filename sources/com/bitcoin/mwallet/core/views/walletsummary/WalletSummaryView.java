package com.bitcoin.mwallet.core.views.walletsummary;

import android.content.Context;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.credential.CredentialType;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.leanplum.internal.Constants.Kinds;
import java.math.BigDecimal;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 B2\u00020\u0001:\u0001BBi\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0014HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0007HÆ\u0003J\t\u00103\u001a\u00020\tHÆ\u0003J\t\u00104\u001a\u00020\u0007HÆ\u0003J\t\u00105\u001a\u00020\fHÆ\u0003J\t\u00106\u001a\u00020\u0007HÆ\u0003J\t\u00107\u001a\u00020\u0007HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0010HÆ\u0003J}\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÆ\u0001J\u0013\u0010:\u001a\u00020\f2\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u000e\u0010<\u001a\u00020\u00072\u0006\u0010=\u001a\u00020>J\t\u0010?\u001a\u00020@HÖ\u0001J\t\u0010A\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\r\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u001eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-¨\u0006C"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryView;", "", "credentialType", "Lcom/bitcoin/mwallet/core/models/credential/CredentialType;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "name", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "color", "isMultiSig", "", "balancePrimary", "balanceSecondary", "walletType", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "slpInfo", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "tokenBalance", "Ljava/math/BigDecimal;", "(Lcom/bitcoin/mwallet/core/models/credential/CredentialType;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/Coin;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lcom/bitcoin/mwallet/core/entity/WalletType;Lcom/bitcoin/mwallet/core/models/slp/Slp;Ljava/math/BigDecimal;)V", "getBalancePrimary", "()Ljava/lang/String;", "getBalanceSecondary", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getColor", "getCredentialType", "()Lcom/bitcoin/mwallet/core/models/credential/CredentialType;", "()Z", "getName", "getSlpInfo", "()Lcom/bitcoin/mwallet/core/models/slp/Slp;", "setSlpInfo", "(Lcom/bitcoin/mwallet/core/models/slp/Slp;)V", "getTokenBalance", "()Ljava/math/BigDecimal;", "setTokenBalance", "(Ljava/math/BigDecimal;)V", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletType", "()Lcom/bitcoin/mwallet/core/entity/WalletType;", "setWalletType", "(Lcom/bitcoin/mwallet/core/entity/WalletType;)V", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "getSubtitle", "context", "Landroid/content/Context;", "hashCode", "", "toString", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSummaryView.kt */
public final class WalletSummaryView {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String balancePrimary;
    @NotNull
    private final String balanceSecondary;
    @NotNull
    private final Coin coin;
    @NotNull
    private final String color;
    @NotNull
    private final CredentialType credentialType;
    private final boolean isMultiSig;
    @NotNull
    private final String name;
    @Nullable
    private Slp slpInfo;
    @Nullable
    private BigDecimal tokenBalance;
    @NotNull
    private final WalletId walletId;
    @Nullable
    private WalletType walletType;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryView$Companion;", "", "()V", "fromWalletBalance", "Lkotlin/Function1;", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryView;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletSummaryView.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Function1<WalletInfoBalance, WalletSummaryView> fromWalletBalance() {
            return WalletSummaryView$Companion$fromWalletBalance$1.INSTANCE;
        }
    }

    @NotNull
    public static /* synthetic */ WalletSummaryView copy$default(WalletSummaryView walletSummaryView, CredentialType credentialType2, WalletId walletId2, String str, Coin coin2, String str2, boolean z, String str3, String str4, WalletType walletType2, Slp slp, BigDecimal bigDecimal, int i, Object obj) {
        WalletSummaryView walletSummaryView2 = walletSummaryView;
        int i2 = i;
        return walletSummaryView.copy((i2 & 1) != 0 ? walletSummaryView2.credentialType : credentialType2, (i2 & 2) != 0 ? walletSummaryView2.walletId : walletId2, (i2 & 4) != 0 ? walletSummaryView2.name : str, (i2 & 8) != 0 ? walletSummaryView2.coin : coin2, (i2 & 16) != 0 ? walletSummaryView2.color : str2, (i2 & 32) != 0 ? walletSummaryView2.isMultiSig : z, (i2 & 64) != 0 ? walletSummaryView2.balancePrimary : str3, (i2 & 128) != 0 ? walletSummaryView2.balanceSecondary : str4, (i2 & 256) != 0 ? walletSummaryView2.walletType : walletType2, (i2 & 512) != 0 ? walletSummaryView2.slpInfo : slp, (i2 & 1024) != 0 ? walletSummaryView2.tokenBalance : bigDecimal);
    }

    @NotNull
    public final CredentialType component1() {
        return this.credentialType;
    }

    @Nullable
    public final Slp component10() {
        return this.slpInfo;
    }

    @Nullable
    public final BigDecimal component11() {
        return this.tokenBalance;
    }

    @NotNull
    public final WalletId component2() {
        return this.walletId;
    }

    @NotNull
    public final String component3() {
        return this.name;
    }

    @NotNull
    public final Coin component4() {
        return this.coin;
    }

    @NotNull
    public final String component5() {
        return this.color;
    }

    public final boolean component6() {
        return this.isMultiSig;
    }

    @NotNull
    public final String component7() {
        return this.balancePrimary;
    }

    @NotNull
    public final String component8() {
        return this.balanceSecondary;
    }

    @Nullable
    public final WalletType component9() {
        return this.walletType;
    }

    @NotNull
    public final WalletSummaryView copy(@NotNull CredentialType credentialType2, @NotNull WalletId walletId2, @NotNull String str, @NotNull Coin coin2, @NotNull String str2, boolean z, @NotNull String str3, @NotNull String str4, @Nullable WalletType walletType2, @Nullable Slp slp, @Nullable BigDecimal bigDecimal) {
        CredentialType credentialType3 = credentialType2;
        Intrinsics.checkParameterIsNotNull(credentialType2, "credentialType");
        WalletId walletId3 = walletId2;
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        String str5 = str;
        Intrinsics.checkParameterIsNotNull(str5, "name");
        Coin coin3 = coin2;
        Intrinsics.checkParameterIsNotNull(coin3, "coin");
        String str6 = str2;
        Intrinsics.checkParameterIsNotNull(str6, Kinds.COLOR);
        String str7 = str3;
        Intrinsics.checkParameterIsNotNull(str7, "balancePrimary");
        String str8 = str4;
        Intrinsics.checkParameterIsNotNull(str8, "balanceSecondary");
        WalletSummaryView walletSummaryView = new WalletSummaryView(credentialType3, walletId3, str5, coin3, str6, z, str7, str8, walletType2, slp, bigDecimal);
        return walletSummaryView;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof WalletSummaryView) {
                WalletSummaryView walletSummaryView = (WalletSummaryView) obj;
                if (Intrinsics.areEqual((Object) this.credentialType, (Object) walletSummaryView.credentialType) && Intrinsics.areEqual((Object) this.walletId, (Object) walletSummaryView.walletId) && Intrinsics.areEqual((Object) this.name, (Object) walletSummaryView.name) && Intrinsics.areEqual((Object) this.coin, (Object) walletSummaryView.coin) && Intrinsics.areEqual((Object) this.color, (Object) walletSummaryView.color)) {
                    if (!(this.isMultiSig == walletSummaryView.isMultiSig) || !Intrinsics.areEqual((Object) this.balancePrimary, (Object) walletSummaryView.balancePrimary) || !Intrinsics.areEqual((Object) this.balanceSecondary, (Object) walletSummaryView.balanceSecondary) || !Intrinsics.areEqual((Object) this.walletType, (Object) walletSummaryView.walletType) || !Intrinsics.areEqual((Object) this.slpInfo, (Object) walletSummaryView.slpInfo) || !Intrinsics.areEqual((Object) this.tokenBalance, (Object) walletSummaryView.tokenBalance)) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        CredentialType credentialType2 = this.credentialType;
        int i = 0;
        int hashCode = (credentialType2 != null ? credentialType2.hashCode() : 0) * 31;
        WalletId walletId2 = this.walletId;
        int hashCode2 = (hashCode + (walletId2 != null ? walletId2.hashCode() : 0)) * 31;
        String str = this.name;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        Coin coin2 = this.coin;
        int hashCode4 = (hashCode3 + (coin2 != null ? coin2.hashCode() : 0)) * 31;
        String str2 = this.color;
        int hashCode5 = (hashCode4 + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.isMultiSig;
        if (z) {
            z = true;
        }
        int i2 = (hashCode5 + (z ? 1 : 0)) * 31;
        String str3 = this.balancePrimary;
        int hashCode6 = (i2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.balanceSecondary;
        int hashCode7 = (hashCode6 + (str4 != null ? str4.hashCode() : 0)) * 31;
        WalletType walletType2 = this.walletType;
        int hashCode8 = (hashCode7 + (walletType2 != null ? walletType2.hashCode() : 0)) * 31;
        Slp slp = this.slpInfo;
        int hashCode9 = (hashCode8 + (slp != null ? slp.hashCode() : 0)) * 31;
        BigDecimal bigDecimal = this.tokenBalance;
        if (bigDecimal != null) {
            i = bigDecimal.hashCode();
        }
        return hashCode9 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WalletSummaryView(credentialType=");
        sb.append(this.credentialType);
        sb.append(", walletId=");
        sb.append(this.walletId);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(", color=");
        sb.append(this.color);
        sb.append(", isMultiSig=");
        sb.append(this.isMultiSig);
        sb.append(", balancePrimary=");
        sb.append(this.balancePrimary);
        sb.append(", balanceSecondary=");
        sb.append(this.balanceSecondary);
        sb.append(", walletType=");
        sb.append(this.walletType);
        sb.append(", slpInfo=");
        sb.append(this.slpInfo);
        sb.append(", tokenBalance=");
        sb.append(this.tokenBalance);
        sb.append(")");
        return sb.toString();
    }

    public WalletSummaryView(@NotNull CredentialType credentialType2, @NotNull WalletId walletId2, @NotNull String str, @NotNull Coin coin2, @NotNull String str2, boolean z, @NotNull String str3, @NotNull String str4, @Nullable WalletType walletType2, @Nullable Slp slp, @Nullable BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(credentialType2, "credentialType");
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(str2, Kinds.COLOR);
        Intrinsics.checkParameterIsNotNull(str3, "balancePrimary");
        Intrinsics.checkParameterIsNotNull(str4, "balanceSecondary");
        this.credentialType = credentialType2;
        this.walletId = walletId2;
        this.name = str;
        this.coin = coin2;
        this.color = str2;
        this.isMultiSig = z;
        this.balancePrimary = str3;
        this.balanceSecondary = str4;
        this.walletType = walletType2;
        this.slpInfo = slp;
        this.tokenBalance = bigDecimal;
    }

    @NotNull
    public final CredentialType getCredentialType() {
        return this.credentialType;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    @NotNull
    public final String getColor() {
        return this.color;
    }

    public final boolean isMultiSig() {
        return this.isMultiSig;
    }

    @NotNull
    public final String getBalancePrimary() {
        return this.balancePrimary;
    }

    @NotNull
    public final String getBalanceSecondary() {
        return this.balanceSecondary;
    }

    public /* synthetic */ WalletSummaryView(CredentialType credentialType2, WalletId walletId2, String str, Coin coin2, String str2, boolean z, String str3, String str4, WalletType walletType2, Slp slp, BigDecimal bigDecimal, int i, DefaultConstructorMarker defaultConstructorMarker) {
        int i2 = i;
        this(credentialType2, walletId2, str, coin2, str2, z, str3, str4, (i2 & 256) != 0 ? null : walletType2, (i2 & 512) != 0 ? null : slp, (i2 & 1024) != 0 ? null : bigDecimal);
    }

    @Nullable
    public final WalletType getWalletType() {
        return this.walletType;
    }

    public final void setWalletType(@Nullable WalletType walletType2) {
        this.walletType = walletType2;
    }

    @Nullable
    public final Slp getSlpInfo() {
        return this.slpInfo;
    }

    public final void setSlpInfo(@Nullable Slp slp) {
        this.slpInfo = slp;
    }

    @Nullable
    public final BigDecimal getTokenBalance() {
        return this.tokenBalance;
    }

    public final void setTokenBalance(@Nullable BigDecimal bigDecimal) {
        this.tokenBalance = bigDecimal;
    }

    @NotNull
    public final String getSubtitle(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        String string = context.getResources().getString(this.coin.getPresentableNameResId());
        Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getStr…oin.presentableNameResId)");
        if (!this.isMultiSig) {
            return string;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string2 = context.getResources().getString(C1018R.string.walletsummary_multisig_format);
        Intrinsics.checkExpressionValueIsNotNull(string2, "context.resources.getStr…tsummary_multisig_format)");
        Object[] objArr = {string};
        String format = String.format(string2, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        return format;
    }
}
