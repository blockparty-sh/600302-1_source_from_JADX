package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity;

import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.Mnemonic;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.bitcoink.PrivateKey;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.Copayers;
import com.bitcoin.mwallet.core.models.credential.Credential;
import com.bitcoin.mwallet.core.models.credential.CredentialMnemonic;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.utils.signature.SignatureHelper;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b6\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\b\u0018\u0000 ^2\u00020\u0001:\u0003^_`BÃ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u0012\u0006\u0010\u0016\u001a\u00020\u0005\u0012\u0006\u0010\u0017\u001a\u00020\u0005\u0012\u0006\u0010\u0018\u001a\u00020\u0005\u0012\u0006\u0010\u0019\u001a\u00020\u0005\u0012\u0006\u0010\u001a\u001a\u00020\u0005\u0012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\u0002\u0010\u001eJ\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0005HÆ\u0003J\t\u0010=\u001a\u00020\bHÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\u0003HÆ\u0003J\t\u0010@\u001a\u00020\u0005HÆ\u0003J\t\u0010A\u001a\u00020\u0005HÆ\u0003J\t\u0010B\u001a\u00020\u0005HÆ\u0003J\t\u0010C\u001a\u00020\u0005HÆ\u0003J\t\u0010D\u001a\u00020\u0005HÆ\u0003J\t\u0010E\u001a\u00020\u0005HÆ\u0003J\t\u0010F\u001a\u00020\u0005HÆ\u0003J\t\u0010G\u001a\u00020\u0005HÆ\u0003J\t\u0010H\u001a\u00020\u0005HÆ\u0003J\t\u0010I\u001a\u00020\u0005HÆ\u0003J\u000f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cHÆ\u0003J\t\u0010K\u001a\u00020\u0005HÆ\u0003J\t\u0010L\u001a\u00020\bHÆ\u0003J\t\u0010M\u001a\u00020\u0005HÆ\u0003J\t\u0010N\u001a\u00020\u0005HÆ\u0003J\t\u0010O\u001a\u00020\u0005HÆ\u0003J\t\u0010P\u001a\u00020\u0005HÆ\u0003J\t\u0010Q\u001a\u00020\u0005HÆ\u0003Jõ\u0001\u0010R\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u00052\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cHÆ\u0001J\b\u0010S\u001a\u00020TH\u0002J\u0013\u0010U\u001a\u00020\b2\b\u0010V\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\"\u0010W\u001a\u0004\u0018\u00010X2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\bH\u0002J\t\u0010Y\u001a\u00020\u0003HÖ\u0001J\u0012\u0010Z\u001a\u000e\u0012\u0004\u0012\u00020\\\u0012\u0004\u0012\u00020X0[J\t\u0010]\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\"R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\"R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\"R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\"R\u0011\u0010*\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b*\u0010%R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010 R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\"R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\"R\u0011\u0010\u000f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b.\u0010%R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u0010 R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\"R\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\"R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\"R\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\"R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\"R\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\"R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\"R\u0011\u0010\u0019\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\"R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\"\u0002\u0004\n\u0002\b\u0019¨\u0006a"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet;", "", "account", "", "addressType", "", "coin", "compliantDerivation", "", "copayerId", "copayerName", "derivationStrategy", "entropySource", "mnemonic", "mnemonicEncrypted", "mnemonicHasPassphrase", "m", "n", "network", "personalEncryptingKey", "requestPrivKey", "requestPubKey", "sharedEncryptingKey", "walletId", "walletName", "xPrivKey", "xPubKey", "publicKeyRing", "", "Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$Copayer;", "(ILjava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getAccount", "()I", "getAddressType", "()Ljava/lang/String;", "getCoin", "getCompliantDerivation", "()Z", "getCopayerId", "getCopayerName", "getDerivationStrategy", "getEntropySource", "isValidWallet", "getM", "getMnemonic", "getMnemonicEncrypted", "getMnemonicHasPassphrase", "getN", "getNetwork", "getPersonalEncryptingKey", "getPublicKeyRing", "()Ljava/util/List;", "getRequestPrivKey", "getRequestPubKey", "getSharedEncryptingKey", "getWalletId", "getWalletName", "getXPrivKey", "getXPubKey", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "createCopayers", "Lcom/bitcoin/mwallet/core/models/Copayers;", "equals", "other", "getCredentials", "Lcom/bitcoin/mwallet/core/models/credential/Credential;", "hashCode", "toNewWallet", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "toString", "Companion", "Copayer", "OldWalletsJsonSource", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: OldWallet.kt */
public final class OldWallet {
    private static final String COIN_BCH = "bch";
    private static final String COIN_BTC = "btc";
    public static final Companion Companion = new Companion(null);
    private static final String DERIVATION_STRATEGY_BIP44 = "BIP44";
    private static final String NETWORK_MAINNET = "livenet";
    private static final String NETWORK_TESTNET = "testnet";
    private final int account;
    @NotNull
    private final String addressType;
    @NotNull
    private final String coin;
    private final boolean compliantDerivation;
    @NotNull
    private final String copayerId;
    @NotNull
    private final String copayerName;
    @NotNull
    private final String derivationStrategy;
    @NotNull
    private final String entropySource;

    /* renamed from: m */
    private final int f257m;
    @NotNull
    private final String mnemonic;
    @NotNull
    private final String mnemonicEncrypted;
    private final boolean mnemonicHasPassphrase;

    /* renamed from: n */
    private final int f258n;
    @NotNull
    private final String network;
    @NotNull
    private final String personalEncryptingKey;
    @NotNull
    private final List<Copayer> publicKeyRing;
    @NotNull
    private final String requestPrivKey;
    @NotNull
    private final String requestPubKey;
    @NotNull
    private final String sharedEncryptingKey;
    @NotNull
    private final String walletId;
    @NotNull
    private final String walletName;
    @NotNull
    private final String xPrivKey;
    @NotNull
    private final String xPubKey;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$Companion;", "", "()V", "COIN_BCH", "", "COIN_BTC", "DERIVATION_STRATEGY_BIP44", "NETWORK_MAINNET", "NETWORK_TESTNET", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: OldWallet.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$Copayer;", "", "xPubKey", "", "copayerName", "(Ljava/lang/String;Ljava/lang/String;)V", "getCopayerName", "()Ljava/lang/String;", "getXPubKey", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: OldWallet.kt */
    public static final class Copayer {
        @NotNull
        private final String copayerName;
        @NotNull
        private final String xPubKey;

        @NotNull
        public static /* synthetic */ Copayer copy$default(Copayer copayer, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = copayer.xPubKey;
            }
            if ((i & 2) != 0) {
                str2 = copayer.copayerName;
            }
            return copayer.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.xPubKey;
        }

        @NotNull
        public final String component2() {
            return this.copayerName;
        }

        @NotNull
        public final Copayer copy(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "xPubKey");
            Intrinsics.checkParameterIsNotNull(str2, "copayerName");
            return new Copayer(str, str2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.copayerName, (java.lang.Object) r3.copayerName) != false) goto L_0x001f;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x001f
                boolean r0 = r3 instanceof com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet.Copayer
                if (r0 == 0) goto L_0x001d
                com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet$Copayer r3 = (com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet.Copayer) r3
                java.lang.String r0 = r2.xPubKey
                java.lang.String r1 = r3.xPubKey
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x001d
                java.lang.String r0 = r2.copayerName
                java.lang.String r3 = r3.copayerName
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
                if (r3 == 0) goto L_0x001d
                goto L_0x001f
            L_0x001d:
                r3 = 0
                return r3
            L_0x001f:
                r3 = 1
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet.Copayer.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            String str = this.xPubKey;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.copayerName;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode + i;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Copayer(xPubKey=");
            sb.append(this.xPubKey);
            sb.append(", copayerName=");
            sb.append(this.copayerName);
            sb.append(")");
            return sb.toString();
        }

        public Copayer(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "xPubKey");
            Intrinsics.checkParameterIsNotNull(str2, "copayerName");
            this.xPubKey = str;
            this.copayerName = str2;
        }

        @NotNull
        public final String getCopayerName() {
            return this.copayerName;
        }

        @NotNull
        public final String getXPubKey() {
            return this.xPubKey;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/migrateprofile/entity/OldWallet$OldWalletsJsonSource;", "", "fileExists", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "read", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: OldWallet.kt */
    public interface OldWalletsJsonSource {
        @Nullable
        Object fileExists(@NotNull Continuation<? super Boolean> continuation);

        @Nullable
        Object read(@NotNull Continuation<? super String> continuation);
    }

    @NotNull
    public static /* synthetic */ OldWallet copy$default(OldWallet oldWallet, int i, String str, String str2, boolean z, String str3, String str4, String str5, String str6, String str7, String str8, boolean z2, int i2, int i3, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, List list, int i4, Object obj) {
        String str18;
        String str19;
        String str20;
        String str21;
        String str22;
        String str23;
        String str24;
        String str25;
        String str26;
        String str27;
        String str28;
        String str29;
        String str30;
        String str31;
        OldWallet oldWallet2 = oldWallet;
        int i5 = i4;
        int i6 = (i5 & 1) != 0 ? oldWallet2.account : i;
        String str32 = (i5 & 2) != 0 ? oldWallet2.addressType : str;
        String str33 = (i5 & 4) != 0 ? oldWallet2.coin : str2;
        boolean z3 = (i5 & 8) != 0 ? oldWallet2.compliantDerivation : z;
        String str34 = (i5 & 16) != 0 ? oldWallet2.copayerId : str3;
        String str35 = (i5 & 32) != 0 ? oldWallet2.copayerName : str4;
        String str36 = (i5 & 64) != 0 ? oldWallet2.derivationStrategy : str5;
        String str37 = (i5 & 128) != 0 ? oldWallet2.entropySource : str6;
        String str38 = (i5 & 256) != 0 ? oldWallet2.mnemonic : str7;
        String str39 = (i5 & 512) != 0 ? oldWallet2.mnemonicEncrypted : str8;
        boolean z4 = (i5 & 1024) != 0 ? oldWallet2.mnemonicHasPassphrase : z2;
        int i7 = (i5 & 2048) != 0 ? oldWallet2.f257m : i2;
        int i8 = (i5 & 4096) != 0 ? oldWallet2.f258n : i3;
        String str40 = (i5 & 8192) != 0 ? oldWallet2.network : str9;
        String str41 = (i5 & 16384) != 0 ? oldWallet2.personalEncryptingKey : str10;
        if ((i5 & 32768) != 0) {
            str18 = str41;
            str19 = oldWallet2.requestPrivKey;
        } else {
            str18 = str41;
            str19 = str11;
        }
        if ((i5 & 65536) != 0) {
            str20 = str19;
            str21 = oldWallet2.requestPubKey;
        } else {
            str20 = str19;
            str21 = str12;
        }
        if ((i5 & 131072) != 0) {
            str22 = str21;
            str23 = oldWallet2.sharedEncryptingKey;
        } else {
            str22 = str21;
            str23 = str13;
        }
        if ((i5 & 262144) != 0) {
            str24 = str23;
            str25 = oldWallet2.walletId;
        } else {
            str24 = str23;
            str25 = str14;
        }
        if ((i5 & 524288) != 0) {
            str26 = str25;
            str27 = oldWallet2.walletName;
        } else {
            str26 = str25;
            str27 = str15;
        }
        if ((i5 & 1048576) != 0) {
            str28 = str27;
            str29 = oldWallet2.xPrivKey;
        } else {
            str28 = str27;
            str29 = str16;
        }
        if ((i5 & 2097152) != 0) {
            str30 = str29;
            str31 = oldWallet2.xPubKey;
        } else {
            str30 = str29;
            str31 = str17;
        }
        return oldWallet.copy(i6, str32, str33, z3, str34, str35, str36, str37, str38, str39, z4, i7, i8, str40, str18, str20, str22, str24, str26, str28, str30, str31, (i5 & 4194304) != 0 ? oldWallet2.publicKeyRing : list);
    }

    public final int component1() {
        return this.account;
    }

    @NotNull
    public final String component10() {
        return this.mnemonicEncrypted;
    }

    public final boolean component11() {
        return this.mnemonicHasPassphrase;
    }

    public final int component12() {
        return this.f257m;
    }

    public final int component13() {
        return this.f258n;
    }

    @NotNull
    public final String component14() {
        return this.network;
    }

    @NotNull
    public final String component15() {
        return this.personalEncryptingKey;
    }

    @NotNull
    public final String component16() {
        return this.requestPrivKey;
    }

    @NotNull
    public final String component17() {
        return this.requestPubKey;
    }

    @NotNull
    public final String component18() {
        return this.sharedEncryptingKey;
    }

    @NotNull
    public final String component19() {
        return this.walletId;
    }

    @NotNull
    public final String component2() {
        return this.addressType;
    }

    @NotNull
    public final String component20() {
        return this.walletName;
    }

    @NotNull
    public final String component21() {
        return this.xPrivKey;
    }

    @NotNull
    public final String component22() {
        return this.xPubKey;
    }

    @NotNull
    public final List<Copayer> component23() {
        return this.publicKeyRing;
    }

    @NotNull
    public final String component3() {
        return this.coin;
    }

    public final boolean component4() {
        return this.compliantDerivation;
    }

    @NotNull
    public final String component5() {
        return this.copayerId;
    }

    @NotNull
    public final String component6() {
        return this.copayerName;
    }

    @NotNull
    public final String component7() {
        return this.derivationStrategy;
    }

    @NotNull
    public final String component8() {
        return this.entropySource;
    }

    @NotNull
    public final String component9() {
        return this.mnemonic;
    }

    @NotNull
    public final OldWallet copy(int i, @NotNull String str, @NotNull String str2, boolean z, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8, boolean z2, int i2, int i3, @NotNull String str9, @NotNull String str10, @NotNull String str11, @NotNull String str12, @NotNull String str13, @NotNull String str14, @NotNull String str15, @NotNull String str16, @NotNull String str17, @NotNull List<Copayer> list) {
        int i4 = i;
        String str18 = str;
        String str19 = str2;
        boolean z3 = z;
        String str20 = str3;
        String str21 = str4;
        String str22 = str5;
        String str23 = str6;
        String str24 = str7;
        String str25 = str8;
        boolean z4 = z2;
        int i5 = i2;
        int i6 = i3;
        String str26 = str9;
        String str27 = str10;
        String str28 = str11;
        String str29 = str12;
        String str30 = str13;
        String str31 = str14;
        String str32 = str15;
        String str33 = str16;
        String str34 = str17;
        List<Copayer> list2 = list;
        Intrinsics.checkParameterIsNotNull(str, "addressType");
        Intrinsics.checkParameterIsNotNull(str2, "coin");
        Intrinsics.checkParameterIsNotNull(str3, "copayerId");
        Intrinsics.checkParameterIsNotNull(str4, "copayerName");
        Intrinsics.checkParameterIsNotNull(str5, "derivationStrategy");
        Intrinsics.checkParameterIsNotNull(str6, "entropySource");
        Intrinsics.checkParameterIsNotNull(str7, "mnemonic");
        Intrinsics.checkParameterIsNotNull(str8, "mnemonicEncrypted");
        Intrinsics.checkParameterIsNotNull(str9, "network");
        Intrinsics.checkParameterIsNotNull(str10, "personalEncryptingKey");
        Intrinsics.checkParameterIsNotNull(str11, "requestPrivKey");
        Intrinsics.checkParameterIsNotNull(str12, "requestPubKey");
        Intrinsics.checkParameterIsNotNull(str13, "sharedEncryptingKey");
        Intrinsics.checkParameterIsNotNull(str14, "walletId");
        Intrinsics.checkParameterIsNotNull(str15, "walletName");
        Intrinsics.checkParameterIsNotNull(str16, "xPrivKey");
        Intrinsics.checkParameterIsNotNull(str17, "xPubKey");
        Intrinsics.checkParameterIsNotNull(list, "publicKeyRing");
        OldWallet oldWallet = new OldWallet(i, str18, str19, z3, str20, str21, str22, str23, str24, str25, z4, i5, i6, str26, str27, str28, str29, str30, str31, str32, str33, str34, list2);
        return oldWallet;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof OldWallet) {
                OldWallet oldWallet = (OldWallet) obj;
                if ((this.account == oldWallet.account) && Intrinsics.areEqual((Object) this.addressType, (Object) oldWallet.addressType) && Intrinsics.areEqual((Object) this.coin, (Object) oldWallet.coin)) {
                    if ((this.compliantDerivation == oldWallet.compliantDerivation) && Intrinsics.areEqual((Object) this.copayerId, (Object) oldWallet.copayerId) && Intrinsics.areEqual((Object) this.copayerName, (Object) oldWallet.copayerName) && Intrinsics.areEqual((Object) this.derivationStrategy, (Object) oldWallet.derivationStrategy) && Intrinsics.areEqual((Object) this.entropySource, (Object) oldWallet.entropySource) && Intrinsics.areEqual((Object) this.mnemonic, (Object) oldWallet.mnemonic) && Intrinsics.areEqual((Object) this.mnemonicEncrypted, (Object) oldWallet.mnemonicEncrypted)) {
                        if (this.mnemonicHasPassphrase == oldWallet.mnemonicHasPassphrase) {
                            if (this.f257m == oldWallet.f257m) {
                                if (!(this.f258n == oldWallet.f258n) || !Intrinsics.areEqual((Object) this.network, (Object) oldWallet.network) || !Intrinsics.areEqual((Object) this.personalEncryptingKey, (Object) oldWallet.personalEncryptingKey) || !Intrinsics.areEqual((Object) this.requestPrivKey, (Object) oldWallet.requestPrivKey) || !Intrinsics.areEqual((Object) this.requestPubKey, (Object) oldWallet.requestPubKey) || !Intrinsics.areEqual((Object) this.sharedEncryptingKey, (Object) oldWallet.sharedEncryptingKey) || !Intrinsics.areEqual((Object) this.walletId, (Object) oldWallet.walletId) || !Intrinsics.areEqual((Object) this.walletName, (Object) oldWallet.walletName) || !Intrinsics.areEqual((Object) this.xPrivKey, (Object) oldWallet.xPrivKey) || !Intrinsics.areEqual((Object) this.xPubKey, (Object) oldWallet.xPubKey) || !Intrinsics.areEqual((Object) this.publicKeyRing, (Object) oldWallet.publicKeyRing)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.account * 31;
        String str = this.addressType;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.coin;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z = this.compliantDerivation;
        if (z) {
            z = true;
        }
        int i3 = (hashCode2 + (z ? 1 : 0)) * 31;
        String str3 = this.copayerId;
        int hashCode3 = (i3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.copayerName;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.derivationStrategy;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.entropySource;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.mnemonic;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.mnemonicEncrypted;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        boolean z2 = this.mnemonicHasPassphrase;
        if (z2) {
            z2 = true;
        }
        int i4 = (((((hashCode8 + (z2 ? 1 : 0)) * 31) + this.f257m) * 31) + this.f258n) * 31;
        String str9 = this.network;
        int hashCode9 = (i4 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.personalEncryptingKey;
        int hashCode10 = (hashCode9 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.requestPrivKey;
        int hashCode11 = (hashCode10 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.requestPubKey;
        int hashCode12 = (hashCode11 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.sharedEncryptingKey;
        int hashCode13 = (hashCode12 + (str13 != null ? str13.hashCode() : 0)) * 31;
        String str14 = this.walletId;
        int hashCode14 = (hashCode13 + (str14 != null ? str14.hashCode() : 0)) * 31;
        String str15 = this.walletName;
        int hashCode15 = (hashCode14 + (str15 != null ? str15.hashCode() : 0)) * 31;
        String str16 = this.xPrivKey;
        int hashCode16 = (hashCode15 + (str16 != null ? str16.hashCode() : 0)) * 31;
        String str17 = this.xPubKey;
        int hashCode17 = (hashCode16 + (str17 != null ? str17.hashCode() : 0)) * 31;
        List<Copayer> list = this.publicKeyRing;
        if (list != null) {
            i2 = list.hashCode();
        }
        return hashCode17 + i2;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OldWallet(account=");
        sb.append(this.account);
        sb.append(", addressType=");
        sb.append(this.addressType);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(", compliantDerivation=");
        sb.append(this.compliantDerivation);
        sb.append(", copayerId=");
        sb.append(this.copayerId);
        sb.append(", copayerName=");
        sb.append(this.copayerName);
        sb.append(", derivationStrategy=");
        sb.append(this.derivationStrategy);
        sb.append(", entropySource=");
        sb.append(this.entropySource);
        sb.append(", mnemonic=");
        sb.append(this.mnemonic);
        sb.append(", mnemonicEncrypted=");
        sb.append(this.mnemonicEncrypted);
        sb.append(", mnemonicHasPassphrase=");
        sb.append(this.mnemonicHasPassphrase);
        sb.append(", m=");
        sb.append(this.f257m);
        sb.append(", n=");
        sb.append(this.f258n);
        sb.append(", network=");
        sb.append(this.network);
        sb.append(", personalEncryptingKey=");
        sb.append(this.personalEncryptingKey);
        sb.append(", requestPrivKey=");
        sb.append(this.requestPrivKey);
        sb.append(", requestPubKey=");
        sb.append(this.requestPubKey);
        sb.append(", sharedEncryptingKey=");
        sb.append(this.sharedEncryptingKey);
        sb.append(", walletId=");
        sb.append(this.walletId);
        sb.append(", walletName=");
        sb.append(this.walletName);
        sb.append(", xPrivKey=");
        sb.append(this.xPrivKey);
        sb.append(", xPubKey=");
        sb.append(this.xPubKey);
        sb.append(", publicKeyRing=");
        sb.append(this.publicKeyRing);
        sb.append(")");
        return sb.toString();
    }

    public OldWallet(int i, @NotNull String str, @NotNull String str2, boolean z, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8, boolean z2, int i2, int i3, @NotNull String str9, @NotNull String str10, @NotNull String str11, @NotNull String str12, @NotNull String str13, @NotNull String str14, @NotNull String str15, @NotNull String str16, @NotNull String str17, @NotNull List<Copayer> list) {
        String str18 = str;
        String str19 = str2;
        String str20 = str3;
        String str21 = str4;
        String str22 = str5;
        String str23 = str6;
        String str24 = str7;
        String str25 = str8;
        String str26 = str9;
        String str27 = str10;
        String str28 = str11;
        String str29 = str12;
        String str30 = str13;
        String str31 = str14;
        String str32 = str15;
        String str33 = str16;
        Intrinsics.checkParameterIsNotNull(str18, "addressType");
        Intrinsics.checkParameterIsNotNull(str19, "coin");
        Intrinsics.checkParameterIsNotNull(str20, "copayerId");
        Intrinsics.checkParameterIsNotNull(str21, "copayerName");
        Intrinsics.checkParameterIsNotNull(str22, "derivationStrategy");
        Intrinsics.checkParameterIsNotNull(str23, "entropySource");
        Intrinsics.checkParameterIsNotNull(str24, "mnemonic");
        Intrinsics.checkParameterIsNotNull(str25, "mnemonicEncrypted");
        Intrinsics.checkParameterIsNotNull(str26, "network");
        Intrinsics.checkParameterIsNotNull(str27, "personalEncryptingKey");
        Intrinsics.checkParameterIsNotNull(str28, "requestPrivKey");
        Intrinsics.checkParameterIsNotNull(str29, "requestPubKey");
        Intrinsics.checkParameterIsNotNull(str30, "sharedEncryptingKey");
        Intrinsics.checkParameterIsNotNull(str31, "walletId");
        Intrinsics.checkParameterIsNotNull(str32, "walletName");
        Intrinsics.checkParameterIsNotNull(str16, "xPrivKey");
        Intrinsics.checkParameterIsNotNull(str17, "xPubKey");
        Intrinsics.checkParameterIsNotNull(list, "publicKeyRing");
        this.account = i;
        this.addressType = str18;
        this.coin = str19;
        this.compliantDerivation = z;
        this.copayerId = str20;
        this.copayerName = str21;
        this.derivationStrategy = str22;
        this.entropySource = str23;
        this.mnemonic = str24;
        this.mnemonicEncrypted = str25;
        this.mnemonicHasPassphrase = z2;
        this.f257m = i2;
        this.f258n = i3;
        this.network = str26;
        this.personalEncryptingKey = str27;
        this.requestPrivKey = str28;
        this.requestPubKey = str29;
        this.sharedEncryptingKey = str30;
        this.walletId = str31;
        this.walletName = str15;
        this.xPrivKey = str16;
        List<Copayer> list2 = list;
        this.xPubKey = str17;
        this.publicKeyRing = list2;
    }

    public final int getAccount() {
        return this.account;
    }

    @NotNull
    public final String getAddressType() {
        return this.addressType;
    }

    @NotNull
    public final String getCoin() {
        return this.coin;
    }

    public final boolean getCompliantDerivation() {
        return this.compliantDerivation;
    }

    @NotNull
    public final String getCopayerId() {
        return this.copayerId;
    }

    @NotNull
    public final String getCopayerName() {
        return this.copayerName;
    }

    @NotNull
    public final String getDerivationStrategy() {
        return this.derivationStrategy;
    }

    @NotNull
    public final String getEntropySource() {
        return this.entropySource;
    }

    @NotNull
    public final String getMnemonic() {
        return this.mnemonic;
    }

    @NotNull
    public final String getMnemonicEncrypted() {
        return this.mnemonicEncrypted;
    }

    public final boolean getMnemonicHasPassphrase() {
        return this.mnemonicHasPassphrase;
    }

    public final int getM() {
        return this.f257m;
    }

    public final int getN() {
        return this.f258n;
    }

    @NotNull
    public final String getNetwork() {
        return this.network;
    }

    @NotNull
    public final String getPersonalEncryptingKey() {
        return this.personalEncryptingKey;
    }

    @NotNull
    public final String getRequestPrivKey() {
        return this.requestPrivKey;
    }

    @NotNull
    public final String getRequestPubKey() {
        return this.requestPubKey;
    }

    @NotNull
    public final String getSharedEncryptingKey() {
        return this.sharedEncryptingKey;
    }

    @NotNull
    public final String getWalletId() {
        return this.walletId;
    }

    @NotNull
    public final String getWalletName() {
        return this.walletName;
    }

    @NotNull
    public final String getXPrivKey() {
        return this.xPrivKey;
    }

    @NotNull
    public final String getXPubKey() {
        return this.xPubKey;
    }

    @NotNull
    public final List<Copayer> getPublicKeyRing() {
        return this.publicKeyRing;
    }

    public final boolean isValidWallet() {
        return this.compliantDerivation && Intrinsics.areEqual((Object) this.derivationStrategy, (Object) DERIVATION_STRATEGY_BIP44);
    }

    @NotNull
    public final Pair<C1261Wallet, Credential> toNewWallet() {
        Coin coin2;
        Network network2;
        C1261Wallet wallet;
        String str = this.coin;
        int hashCode = str.hashCode();
        if (hashCode != 97351) {
            if (hashCode == 97873 && str.equals(COIN_BTC)) {
                coin2 = Coin.BTC;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("coin=");
            sb.append(this.coin);
            throw new IllegalArgumentException(sb.toString());
        }
        if (str.equals(COIN_BCH)) {
            coin2 = Coin.BCH;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("coin=");
        sb2.append(this.coin);
        throw new IllegalArgumentException(sb2.toString());
        Coin coin3 = coin2;
        String str2 = this.network;
        int hashCode2 = str2.hashCode();
        if (hashCode2 != -1422441525) {
            if (hashCode2 == 184303921 && str2.equals(NETWORK_MAINNET)) {
                network2 = Network.MAIN;
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("network=");
            sb3.append(this.network);
            throw new IllegalArgumentException(sb3.toString());
        }
        if (str2.equals(NETWORK_TESTNET)) {
            network2 = Network.TEST;
        }
        StringBuilder sb32 = new StringBuilder();
        sb32.append("network=");
        sb32.append(this.network);
        throw new IllegalArgumentException(sb32.toString());
        Network network3 = network2;
        Copayers createCopayers = createCopayers();
        Credential credentials = getCredentials(this.mnemonic, this.mnemonicEncrypted, this.mnemonicHasPassphrase);
        com.bitcoin.bitcoink.DerivationPath.Companion companion = DerivationPath.Companion;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("m/44'/0'/");
        sb4.append(this.account);
        sb4.append('\'');
        DerivationPath parse = companion.parse(sb4.toString());
        if (credentials instanceof CredentialMnemonic) {
            CredentialMnemonic credentialMnemonic = (CredentialMnemonic) credentials;
            PrivateKey privateKey = (PrivateKey) credentialMnemonic.getMasterPrivKey().deriveChild(network3, parse).getFirst();
            SigningKey signaturePrivPubKey = SignatureHelper.Signature.getSignaturePrivPubKey(credentialMnemonic.getMasterPrivKey().base58(network3));
            WalletId localTempId = WalletId.Companion.localTempId();
            StringBuilder sb5 = new StringBuilder();
            sb5.append(WalletId.Companion.localPrefix(coin3));
            sb5.append(this.walletId);
            wallet = new C1261Wallet(localTempId, new WalletId(sb5.toString()), this.walletName, coin3, network3, credentials.getId(), parse, privateKey.toPubKey(), createCopayers, signaturePrivPubKey);
        } else {
            WalletId localTempId2 = WalletId.Companion.localTempId();
            StringBuilder sb6 = new StringBuilder();
            sb6.append(WalletId.Companion.localPrefix(coin3));
            sb6.append(this.walletId);
            WalletId walletId2 = new WalletId(sb6.toString());
            String str3 = this.walletName;
            if (credentials == null) {
                Intrinsics.throwNpe();
            }
            String str4 = "";
            wallet = new C1261Wallet(localTempId2, walletId2, str3, coin3, network3, credentials.getId(), parse, PrivateKey.Companion.dummyKey(), createCopayers, new SigningKey(str4, str4));
        }
        return new Pair<>(wallet, credentials);
    }

    private final Credential getCredentials(String str, String str2, boolean z) {
        CharSequence charSequence = str2;
        if (!(charSequence == null || charSequence.length() == 0)) {
            return Credential.Companion.from(str2);
        }
        if (str != null) {
            return Credential.Companion.from(new Mnemonic(str), z);
        }
        return null;
    }

    private final Copayers createCopayers() {
        List list;
        if (this.publicKeyRing.size() > 1) {
            Iterable iterable = this.publicKeyRing;
            Collection arrayList = new ArrayList();
            for (Object next : iterable) {
                if (!Intrinsics.areEqual((Object) ((Copayer) next).getXPubKey(), (Object) this.xPubKey)) {
                    arrayList.add(next);
                }
            }
            Iterable<Copayer> iterable2 = (List) arrayList;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            for (Copayer copayerName2 : iterable2) {
                arrayList2.add(copayerName2.getCopayerName());
            }
            list = (List) arrayList2;
        } else {
            list = CollectionsKt.emptyList();
        }
        Copayers copayers = new Copayers(new CopayerId(this.copayerId), this.copayerName, this.f258n, this.f257m, list);
        return copayers;
    }
}
