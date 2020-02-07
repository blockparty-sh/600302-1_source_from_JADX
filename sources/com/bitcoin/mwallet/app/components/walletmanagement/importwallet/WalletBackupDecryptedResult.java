package com.bitcoin.mwallet.app.components.walletmanagement.importwallet;

import com.bitcoin.mwallet.core.models.Coin;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\b¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/WalletBackupDecryptedResult;", "", "coin", "", "walletName", "mnemonic", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCoin", "()Ljava/lang/String;", "coinFound", "Lcom/bitcoin/mwallet/core/models/Coin;", "getCoinFound", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getMnemonic", "getWalletName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletBackupDecryptedResult.kt */
public final class WalletBackupDecryptedResult {
    @NotNull
    private final String coin;
    @NotNull
    private final String mnemonic;
    @NotNull
    private final String walletName;

    @NotNull
    public static /* synthetic */ WalletBackupDecryptedResult copy$default(WalletBackupDecryptedResult walletBackupDecryptedResult, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = walletBackupDecryptedResult.coin;
        }
        if ((i & 2) != 0) {
            str2 = walletBackupDecryptedResult.walletName;
        }
        if ((i & 4) != 0) {
            str3 = walletBackupDecryptedResult.mnemonic;
        }
        return walletBackupDecryptedResult.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.coin;
    }

    @NotNull
    public final String component2() {
        return this.walletName;
    }

    @NotNull
    public final String component3() {
        return this.mnemonic;
    }

    @NotNull
    public final WalletBackupDecryptedResult copy(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "coin");
        Intrinsics.checkParameterIsNotNull(str2, "walletName");
        Intrinsics.checkParameterIsNotNull(str3, "mnemonic");
        return new WalletBackupDecryptedResult(str, str2, str3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.mnemonic, (java.lang.Object) r3.mnemonic) != false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0029
            boolean r0 = r3 instanceof com.bitcoin.mwallet.app.components.walletmanagement.importwallet.WalletBackupDecryptedResult
            if (r0 == 0) goto L_0x0027
            com.bitcoin.mwallet.app.components.walletmanagement.importwallet.WalletBackupDecryptedResult r3 = (com.bitcoin.mwallet.app.components.walletmanagement.importwallet.WalletBackupDecryptedResult) r3
            java.lang.String r0 = r2.coin
            java.lang.String r1 = r3.coin
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            java.lang.String r0 = r2.walletName
            java.lang.String r1 = r3.walletName
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            java.lang.String r0 = r2.mnemonic
            java.lang.String r3 = r3.mnemonic
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r3 = 0
            return r3
        L_0x0029:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.walletmanagement.importwallet.WalletBackupDecryptedResult.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.coin;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.walletName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.mnemonic;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WalletBackupDecryptedResult(coin=");
        sb.append(this.coin);
        sb.append(", walletName=");
        sb.append(this.walletName);
        sb.append(", mnemonic=");
        sb.append(this.mnemonic);
        sb.append(")");
        return sb.toString();
    }

    public WalletBackupDecryptedResult(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "coin");
        Intrinsics.checkParameterIsNotNull(str2, "walletName");
        Intrinsics.checkParameterIsNotNull(str3, "mnemonic");
        this.coin = str;
        this.walletName = str2;
        this.mnemonic = str3;
    }

    @NotNull
    public final String getCoin() {
        return this.coin;
    }

    @NotNull
    public final String getWalletName() {
        return this.walletName;
    }

    @NotNull
    public final String getMnemonic() {
        return this.mnemonic;
    }

    @NotNull
    public final Coin getCoinFound() {
        String str = this.coin;
        String str2 = "null cannot be cast to non-null type java.lang.String";
        if (str != null) {
            String lowerCase = str.toLowerCase();
            String str3 = "(this as java.lang.String).toLowerCase()";
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, str3);
            String ticker = Coin.BCH.getTicker();
            if (ticker != null) {
                String lowerCase2 = ticker.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase2, str3);
                return Intrinsics.areEqual((Object) lowerCase, (Object) lowerCase2) ? Coin.BCH : Coin.BTC;
            }
            throw new TypeCastException(str2);
        }
        throw new TypeCastException(str2);
    }
}
