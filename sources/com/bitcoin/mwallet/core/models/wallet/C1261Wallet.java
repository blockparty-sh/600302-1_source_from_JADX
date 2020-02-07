package com.bitcoin.mwallet.core.models.wallet;

import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.ExtendedPublicKey;
import com.bitcoin.bitcoink.Mnemonic;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.bitcoink.PrivateKey;
import com.bitcoin.mwallet.ApplicationClass;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.Copayers;
import com.bitcoin.mwallet.core.models.credential.Credential;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.credential.CredentialMnemonic;
import com.bitcoin.mwallet.core.models.credential.CredentialZion;
import com.bitcoin.mwallet.core.preferences.WalletPreference;
import com.bitcoin.mwallet.core.utils.signature.SignatureHelper;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import com.bitcoin.mwallet.zion.ZionId;
import com.bitcoin.mwallet.zion.ZionXPub;
import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 >2\u00020\u0001:\u0001>BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0014HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0006HÆ\u0003J\t\u00100\u001a\u00020\bHÆ\u0003J\t\u00101\u001a\u00020\nHÆ\u0003J\t\u00102\u001a\u00020\fHÆ\u0003J\t\u00103\u001a\u00020\u000eHÆ\u0003J\t\u00104\u001a\u00020\u0010HÆ\u0003J\t\u00105\u001a\u00020\u0012HÆ\u0003Jm\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014HÆ\u0001J\u0013\u00107\u001a\u00020\u001f2\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00109\u001a\u00020:HÖ\u0001J\u0006\u0010;\u001a\u00020<J\t\u0010=\u001a\u00020\u0006HÖ\u0001R\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0011\u001a\u00020\u00128\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010 R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001dR\u0016\u0010\u000f\u001a\u00020\u00108\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0016\u0010\u0013\u001a\u00020\u00148\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+¨\u0006?"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "", "pri_key", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "id", "name", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "network", "Lcom/bitcoin/bitcoink/Network;", "credentialId", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "path", "Lcom/bitcoin/bitcoink/DerivationPath;", "publicKey", "Lcom/bitcoin/bitcoink/ExtendedPublicKey;", "copayers", "Lcom/bitcoin/mwallet/core/models/Copayers;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/bitcoink/Network;Lcom/bitcoin/mwallet/core/models/credential/CredentialId;Lcom/bitcoin/bitcoink/DerivationPath;Lcom/bitcoin/bitcoink/ExtendedPublicKey;Lcom/bitcoin/mwallet/core/models/Copayers;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getCopayers", "()Lcom/bitcoin/mwallet/core/models/Copayers;", "getCredentialId", "()Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "getId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "isMultiSig", "", "()Z", "getName", "()Ljava/lang/String;", "getNetwork", "()Lcom/bitcoin/bitcoink/Network;", "getPath", "()Lcom/bitcoin/bitcoink/DerivationPath;", "getPri_key", "getPublicKey", "()Lcom/bitcoin/bitcoink/ExtendedPublicKey;", "getSigningKey", "()Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "preference", "Lcom/bitcoin/mwallet/core/preferences/WalletPreference;", "toString", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(tableName = "wallet")
/* renamed from: com.bitcoin.mwallet.core.models.wallet.Wallet */
/* compiled from: Wallet.kt */
public final class C1261Wallet {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final Network defaultNetwork = Network.MAIN;
    @ColumnInfo(index = true, name = "coin")
    @NotNull
    private final Coin coin;
    @NotNull
    @Embedded(prefix = "copayers_")
    private final Copayers copayers;
    @NotNull
    @Embedded(prefix = "credential_")
    private final CredentialId credentialId;
    @ColumnInfo(name = "id")
    @NotNull

    /* renamed from: id */
    private final WalletId f376id;
    @ColumnInfo(name = "name")
    @NotNull
    private final String name;
    @ColumnInfo(name = "network")
    @NotNull
    private final Network network;
    @ColumnInfo(name = "path")
    @NotNull
    private final DerivationPath path;
    @ColumnInfo(name = "pri_key")
    @NotNull
    @PrimaryKey
    private final WalletId pri_key;
    @ColumnInfo(name = "public_key")
    @NotNull
    private final ExtendedPublicKey publicKey;
    @ColumnInfo(name = "signing_key")
    @NotNull
    private final SigningKey signingKey;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bJ\"\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\"\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00100\u00062\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/wallet/Wallet$Companion;", "", "()V", "defaultNetwork", "Lcom/bitcoin/bitcoink/Network;", "createFromMnemonic", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "Lcom/bitcoin/mwallet/core/models/credential/CredentialMnemonic;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "walletName", "", "credentialMnemonic", "createNew", "createNewZion", "Lcom/bitcoin/mwallet/core/models/credential/CredentialZion;", "zionXPub", "Lcom/bitcoin/mwallet/zion/ZionXPub;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: com.bitcoin.mwallet.core.models.wallet.Wallet$Companion */
    /* compiled from: Wallet.kt */
    public static final class Companion {

        @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
        /* renamed from: com.bitcoin.mwallet.core.models.wallet.Wallet$Companion$WhenMappings */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];
            public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[Coin.values().length];

            static {
                $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
                $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
                $EnumSwitchMapping$1[Coin.BCH.ordinal()] = 1;
                $EnumSwitchMapping$1[Coin.BTC.ordinal()] = 2;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Pair<C1261Wallet, CredentialZion> createNewZion(@NotNull ZionXPub zionXPub, @NotNull String str) {
            Intrinsics.checkParameterIsNotNull(zionXPub, "zionXPub");
            Intrinsics.checkParameterIsNotNull(str, "walletName");
            ZionId zionId = zionXPub.getZionId();
            Network access$getDefaultNetwork$cp = C1261Wallet.defaultNetwork;
            Coin coin = zionXPub.getCoin();
            ExtendedPublicKey xPub = zionXPub.getXPub();
            WalletId localTempId = WalletId.Companion.localTempId();
            Copayers createSingleSig = Copayers.Companion.createSingleSig(CopayerId.Companion.fromXPubKey(coin, xPub.base58(access$getDefaultNetwork$cp)));
            CredentialZion from = Credential.Companion.from(zionId);
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
            WalletId walletId = new WalletId(uuid);
            CredentialId id = from.getId();
            DerivationPath derivationPath = zionXPub.getDerivationPath();
            String str2 = "";
            C1261Wallet wallet = new C1261Wallet(walletId, localTempId, str, coin, access$getDefaultNetwork$cp, id, derivationPath, xPub, createSingleSig, new SigningKey(str2, str2));
            return new Pair<>(wallet, from);
        }

        @NotNull
        public final Pair<C1261Wallet, CredentialMnemonic> createNew(@NotNull Coin coin, @NotNull String str) {
            DerivationPath derivationPath;
            Intrinsics.checkParameterIsNotNull(coin, "coin");
            Intrinsics.checkParameterIsNotNull(str, "walletName");
            WalletId localTempId = WalletId.Companion.localTempId();
            int i = WhenMappings.$EnumSwitchMapping$0[coin.ordinal()];
            String str2 = "m/44'/0'/0'";
            if (i == 1) {
                derivationPath = DerivationPath.Companion.parse(str2);
            } else if (i == 2) {
                derivationPath = DerivationPath.Companion.parse(str2);
            } else {
                throw new NoWhenBranchMatchedException();
            }
            DerivationPath derivationPath2 = derivationPath;
            CredentialMnemonic from$default = com.bitcoin.mwallet.core.models.credential.Credential.Companion.from$default(Credential.Companion, Mnemonic.Companion.generate(), false, 2, null);
            ExtendedPublicKey pubKey = ((PrivateKey) from$default.getMasterPrivKey().deriveChild(C1261Wallet.defaultNetwork, derivationPath2).getFirst()).toPubKey();
            Copayers createSingleSig = Copayers.Companion.createSingleSig(CopayerId.Companion.fromXPubKey(coin, pubKey.base58(C1261Wallet.defaultNetwork)));
            SigningKey signaturePrivPubKey = SignatureHelper.Signature.getSignaturePrivPubKey(from$default.getMasterPrivKey().base58(C1261Wallet.defaultNetwork));
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
            C1261Wallet wallet = new C1261Wallet(new WalletId(uuid), localTempId, str, coin, C1261Wallet.defaultNetwork, from$default.getId(), derivationPath2, pubKey, createSingleSig, signaturePrivPubKey);
            return new Pair<>(wallet, from$default);
        }

        @NotNull
        public final Pair<C1261Wallet, CredentialMnemonic> createFromMnemonic(@NotNull Coin coin, @NotNull String str, @NotNull CredentialMnemonic credentialMnemonic) {
            DerivationPath derivationPath;
            Intrinsics.checkParameterIsNotNull(coin, "coin");
            Intrinsics.checkParameterIsNotNull(str, "walletName");
            Intrinsics.checkParameterIsNotNull(credentialMnemonic, "credentialMnemonic");
            WalletId localTempId = WalletId.Companion.localTempId();
            int i = WhenMappings.$EnumSwitchMapping$1[coin.ordinal()];
            String str2 = "m/44'/0'/0'";
            if (i == 1) {
                derivationPath = DerivationPath.Companion.parse(str2);
            } else if (i == 2) {
                derivationPath = DerivationPath.Companion.parse(str2);
            } else {
                throw new NoWhenBranchMatchedException();
            }
            DerivationPath derivationPath2 = derivationPath;
            ExtendedPublicKey pubKey = ((PrivateKey) credentialMnemonic.getMasterPrivKey().deriveChild(C1261Wallet.defaultNetwork, derivationPath2).getFirst()).toPubKey();
            Copayers createSingleSig = Copayers.Companion.createSingleSig(CopayerId.Companion.fromXPubKey(coin, pubKey.base58(C1261Wallet.defaultNetwork)));
            SigningKey signaturePrivPubKey = SignatureHelper.Signature.getSignaturePrivPubKey(credentialMnemonic.getMasterPrivKey().base58(C1261Wallet.defaultNetwork));
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
            C1261Wallet wallet = new C1261Wallet(new WalletId(uuid), localTempId, str, coin, C1261Wallet.defaultNetwork, credentialMnemonic.getId(), derivationPath2, pubKey, createSingleSig, signaturePrivPubKey);
            return new Pair<>(wallet, credentialMnemonic);
        }
    }

    @NotNull
    public static /* synthetic */ C1261Wallet copy$default(C1261Wallet wallet, WalletId walletId, WalletId walletId2, String str, Coin coin2, Network network2, CredentialId credentialId2, DerivationPath derivationPath, ExtendedPublicKey extendedPublicKey, Copayers copayers2, SigningKey signingKey2, int i, Object obj) {
        C1261Wallet wallet2 = wallet;
        int i2 = i;
        return wallet.copy((i2 & 1) != 0 ? wallet2.pri_key : walletId, (i2 & 2) != 0 ? wallet2.f376id : walletId2, (i2 & 4) != 0 ? wallet2.name : str, (i2 & 8) != 0 ? wallet2.coin : coin2, (i2 & 16) != 0 ? wallet2.network : network2, (i2 & 32) != 0 ? wallet2.credentialId : credentialId2, (i2 & 64) != 0 ? wallet2.path : derivationPath, (i2 & 128) != 0 ? wallet2.publicKey : extendedPublicKey, (i2 & 256) != 0 ? wallet2.copayers : copayers2, (i2 & 512) != 0 ? wallet2.signingKey : signingKey2);
    }

    @NotNull
    public final WalletId component1() {
        return this.pri_key;
    }

    @NotNull
    public final SigningKey component10() {
        return this.signingKey;
    }

    @NotNull
    public final WalletId component2() {
        return this.f376id;
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
    public final Network component5() {
        return this.network;
    }

    @NotNull
    public final CredentialId component6() {
        return this.credentialId;
    }

    @NotNull
    public final DerivationPath component7() {
        return this.path;
    }

    @NotNull
    public final ExtendedPublicKey component8() {
        return this.publicKey;
    }

    @NotNull
    public final Copayers component9() {
        return this.copayers;
    }

    @NotNull
    public final C1261Wallet copy(@NotNull WalletId walletId, @NotNull WalletId walletId2, @NotNull String str, @NotNull Coin coin2, @NotNull Network network2, @NotNull CredentialId credentialId2, @NotNull DerivationPath derivationPath, @NotNull ExtendedPublicKey extendedPublicKey, @NotNull Copayers copayers2, @NotNull SigningKey signingKey2) {
        WalletId walletId3 = walletId;
        Intrinsics.checkParameterIsNotNull(walletId, "pri_key");
        WalletId walletId4 = walletId2;
        Intrinsics.checkParameterIsNotNull(walletId2, CommonProperties.f657ID);
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str, "name");
        Coin coin3 = coin2;
        Intrinsics.checkParameterIsNotNull(coin3, "coin");
        Network network3 = network2;
        Intrinsics.checkParameterIsNotNull(network3, "network");
        CredentialId credentialId3 = credentialId2;
        Intrinsics.checkParameterIsNotNull(credentialId3, "credentialId");
        DerivationPath derivationPath2 = derivationPath;
        Intrinsics.checkParameterIsNotNull(derivationPath2, JsonDataKey_signMessage.path);
        ExtendedPublicKey extendedPublicKey2 = extendedPublicKey;
        Intrinsics.checkParameterIsNotNull(extendedPublicKey2, "publicKey");
        Copayers copayers3 = copayers2;
        Intrinsics.checkParameterIsNotNull(copayers3, "copayers");
        SigningKey signingKey3 = signingKey2;
        Intrinsics.checkParameterIsNotNull(signingKey3, "signingKey");
        C1261Wallet wallet = new C1261Wallet(walletId3, walletId4, str2, coin3, network3, credentialId3, derivationPath2, extendedPublicKey2, copayers3, signingKey3);
        return wallet;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.signingKey, (java.lang.Object) r3.signingKey) != false) goto L_0x006f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x006f
            boolean r0 = r3 instanceof com.bitcoin.mwallet.core.models.wallet.C1261Wallet
            if (r0 == 0) goto L_0x006d
            com.bitcoin.mwallet.core.models.wallet.Wallet r3 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r3
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r2.pri_key
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r3.pri_key
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x006d
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r2.f376id
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r3.f376id
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x006d
            java.lang.String r0 = r2.name
            java.lang.String r1 = r3.name
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x006d
            com.bitcoin.mwallet.core.models.Coin r0 = r2.coin
            com.bitcoin.mwallet.core.models.Coin r1 = r3.coin
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x006d
            com.bitcoin.bitcoink.Network r0 = r2.network
            com.bitcoin.bitcoink.Network r1 = r3.network
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x006d
            com.bitcoin.mwallet.core.models.credential.CredentialId r0 = r2.credentialId
            com.bitcoin.mwallet.core.models.credential.CredentialId r1 = r3.credentialId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x006d
            com.bitcoin.bitcoink.DerivationPath r0 = r2.path
            com.bitcoin.bitcoink.DerivationPath r1 = r3.path
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x006d
            com.bitcoin.bitcoink.ExtendedPublicKey r0 = r2.publicKey
            com.bitcoin.bitcoink.ExtendedPublicKey r1 = r3.publicKey
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x006d
            com.bitcoin.mwallet.core.models.Copayers r0 = r2.copayers
            com.bitcoin.mwallet.core.models.Copayers r1 = r3.copayers
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x006d
            com.bitcoin.mwallet.core.utils.signature.SigningKey r0 = r2.signingKey
            com.bitcoin.mwallet.core.utils.signature.SigningKey r3 = r3.signingKey
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x006d
            goto L_0x006f
        L_0x006d:
            r3 = 0
            return r3
        L_0x006f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.wallet.C1261Wallet.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        WalletId walletId = this.pri_key;
        int i = 0;
        int hashCode = (walletId != null ? walletId.hashCode() : 0) * 31;
        WalletId walletId2 = this.f376id;
        int hashCode2 = (hashCode + (walletId2 != null ? walletId2.hashCode() : 0)) * 31;
        String str = this.name;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        Coin coin2 = this.coin;
        int hashCode4 = (hashCode3 + (coin2 != null ? coin2.hashCode() : 0)) * 31;
        Network network2 = this.network;
        int hashCode5 = (hashCode4 + (network2 != null ? network2.hashCode() : 0)) * 31;
        CredentialId credentialId2 = this.credentialId;
        int hashCode6 = (hashCode5 + (credentialId2 != null ? credentialId2.hashCode() : 0)) * 31;
        DerivationPath derivationPath = this.path;
        int hashCode7 = (hashCode6 + (derivationPath != null ? derivationPath.hashCode() : 0)) * 31;
        ExtendedPublicKey extendedPublicKey = this.publicKey;
        int hashCode8 = (hashCode7 + (extendedPublicKey != null ? extendedPublicKey.hashCode() : 0)) * 31;
        Copayers copayers2 = this.copayers;
        int hashCode9 = (hashCode8 + (copayers2 != null ? copayers2.hashCode() : 0)) * 31;
        SigningKey signingKey2 = this.signingKey;
        if (signingKey2 != null) {
            i = signingKey2.hashCode();
        }
        return hashCode9 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Wallet(pri_key=");
        sb.append(this.pri_key);
        sb.append(", id=");
        sb.append(this.f376id);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(", network=");
        sb.append(this.network);
        sb.append(", credentialId=");
        sb.append(this.credentialId);
        sb.append(", path=");
        sb.append(this.path);
        sb.append(", publicKey=");
        sb.append(this.publicKey);
        sb.append(", copayers=");
        sb.append(this.copayers);
        sb.append(", signingKey=");
        sb.append(this.signingKey);
        sb.append(")");
        return sb.toString();
    }

    public C1261Wallet(@NotNull WalletId walletId, @NotNull WalletId walletId2, @NotNull String str, @NotNull Coin coin2, @NotNull Network network2, @NotNull CredentialId credentialId2, @NotNull DerivationPath derivationPath, @NotNull ExtendedPublicKey extendedPublicKey, @NotNull Copayers copayers2, @NotNull SigningKey signingKey2) {
        Intrinsics.checkParameterIsNotNull(walletId, "pri_key");
        Intrinsics.checkParameterIsNotNull(walletId2, CommonProperties.f657ID);
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(network2, "network");
        Intrinsics.checkParameterIsNotNull(credentialId2, "credentialId");
        Intrinsics.checkParameterIsNotNull(derivationPath, JsonDataKey_signMessage.path);
        Intrinsics.checkParameterIsNotNull(extendedPublicKey, "publicKey");
        Intrinsics.checkParameterIsNotNull(copayers2, "copayers");
        Intrinsics.checkParameterIsNotNull(signingKey2, "signingKey");
        this.pri_key = walletId;
        this.f376id = walletId2;
        this.name = str;
        this.coin = coin2;
        this.network = network2;
        this.credentialId = credentialId2;
        this.path = derivationPath;
        this.publicKey = extendedPublicKey;
        this.copayers = copayers2;
        this.signingKey = signingKey2;
    }

    @NotNull
    public final WalletId getPri_key() {
        return this.pri_key;
    }

    @NotNull
    public final WalletId getId() {
        return this.f376id;
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
    public final Network getNetwork() {
        return this.network;
    }

    @NotNull
    public final CredentialId getCredentialId() {
        return this.credentialId;
    }

    @NotNull
    public final DerivationPath getPath() {
        return this.path;
    }

    @NotNull
    public final ExtendedPublicKey getPublicKey() {
        return this.publicKey;
    }

    @NotNull
    public final Copayers getCopayers() {
        return this.copayers;
    }

    @NotNull
    public final SigningKey getSigningKey() {
        return this.signingKey;
    }

    public final boolean isMultiSig() {
        return this.copayers.getNumCopayers() > 1;
    }

    @NotNull
    public final WalletPreference preference() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(ApplicationClass.Companion.getCurrentApplicationContext());
        WalletId walletId = this.f376id;
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "sharedPreferences");
        return new WalletPreference(walletId, defaultSharedPreferences);
    }
}
