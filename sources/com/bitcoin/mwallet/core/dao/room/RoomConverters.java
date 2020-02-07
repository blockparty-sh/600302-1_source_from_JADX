package com.bitcoin.mwallet.core.dao.room;

import androidx.core.p003os.EnvironmentCompat;
import androidx.room.TypeConverter;
import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.ExtendedPublicKey;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.bitcoink.PrivateKey;
import com.bitcoin.bitcoink.address.Address;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.bitcoink.util.ByteUtils.Hex;
import com.bitcoin.mwallet.core.dao.EncryptedAtRestList;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.address.AddressPurpose;
import com.bitcoin.mwallet.core.models.contact.ContactId;
import com.bitcoin.mwallet.core.models.credential.CredentialType;
import com.bitcoin.mwallet.core.models.networkfee.NetworkFeeLevel;
import com.bitcoin.mwallet.core.models.p009tx.TxAction;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.user.UserId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import com.bitcoin.mwallet.zion.ZionId;
import com.bitcoin.securepreferences.HexKt;
import com.bitcoin.securepreferences.SecureStringEncrypter;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Moshi.Builder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.bitcoinj.core.NetworkParameters;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.koin.core.Koin;
import org.koin.core.KoinComponent;
import org.koin.core.KoinComponent.DefaultImpls;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\u0018\u00002\u00020\u0001:\u0002RSB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\u001bH\u0007J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0016\u0010 \u001a\b\u0012\u0004\u0012\u00020\f0!2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010$\u001a\u00020%2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010&\u001a\u00020'2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010(\u001a\u00020)2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010*\u001a\u00020+2\u0006\u0010\u000b\u001a\u00020\u001bH\u0007J\u0010\u0010,\u001a\u00020-2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010.\u001a\u00020/2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u00100\u001a\u0002012\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u00102\u001a\u0002032\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u00104\u001a\u0002052\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u00106\u001a\u0002072\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u00108\u001a\u0002092\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0010\u0010:\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0007J\u0010\u0010;\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u000eH\u0007J\u0010\u0010<\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0010H\u0007J\u0010\u0010=\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0012H\u0007J\u0010\u0010>\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0014H\u0007J\u0010\u0010?\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u0016H\u0007J\u0010\u0010@\u001a\u00020\f2\u0006\u0010A\u001a\u00020\u0018H\u0007J\u0010\u0010B\u001a\u00020\u001b2\u0006\u0010\u000b\u001a\u00020\u001aH\u0007J\u0010\u0010C\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u001dH\u0007J\u0010\u0010D\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\u001fH\u0007J\u0016\u0010E\u001a\u00020\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0!H\u0007J\u0010\u0010F\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020#H\u0007J\u0010\u0010G\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020%H\u0007J\u0010\u0010H\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020'H\u0007J\u0010\u0010I\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020)H\u0007J\u0010\u0010J\u001a\u00020\u001b2\u0006\u0010\u000b\u001a\u00020+H\u0007J\u0010\u0010K\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020-H\u0007J\u0010\u0010L\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020/H\u0007J\u0010\u0010M\u001a\u00020\f2\u0006\u0010\u000b\u001a\u000209H\u0007J\u0010\u0010N\u001a\u00020\f2\u0006\u0010\u000b\u001a\u000201H\u0007J\u0010\u0010O\u001a\u00020\f2\u0006\u0010\u000b\u001a\u000203H\u0007J\u0010\u0010P\u001a\u00020\f2\u0006\u0010\u000b\u001a\u000205H\u0007J\u0010\u0010Q\u001a\u00020\f2\u0006\u0010\u000b\u001a\u000207H\u0007R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006T"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/room/RoomConverters;", "Lorg/koin/core/KoinComponent;", "()V", "stringEncrypter", "Lcom/bitcoin/securepreferences/SecureStringEncrypter;", "getStringEncrypter", "()Lcom/bitcoin/securepreferences/SecureStringEncrypter;", "stringEncrypter$delegate", "Lkotlin/Lazy;", "readAddress", "Lcom/bitcoin/bitcoink/address/Address;", "value", "", "readAddressPurpose", "Lcom/bitcoin/mwallet/core/models/address/AddressPurpose;", "readBigDecimal", "Ljava/math/BigDecimal;", "readCoin", "Lcom/bitcoin/mwallet/core/models/Coin;", "readContactId", "Lcom/bitcoin/mwallet/core/models/contact/ContactId;", "readCopayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "readCredentialType", "Lcom/bitcoin/mwallet/core/models/credential/CredentialType;", "readDate", "Ljava/util/Date;", "", "readDerivationPath", "Lcom/bitcoin/bitcoink/DerivationPath;", "readEncryptedAtRestList", "Lcom/bitcoin/mwallet/core/dao/EncryptedAtRestList;", "readListOfStrings", "", "readNetwork", "Lcom/bitcoin/bitcoink/Network;", "readNetworkFeeLevel", "Lcom/bitcoin/mwallet/core/models/networkfee/NetworkFeeLevel;", "readPrivateKey", "Lcom/bitcoin/bitcoink/PrivateKey;", "readPublicKey", "Lcom/bitcoin/bitcoink/ExtendedPublicKey;", "readSatoshis", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "readSigningKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "readSlpId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "readTxId", "Lcom/bitcoin/bitcoink/tx/TxId;", "readUserId", "Lcom/bitcoin/mwallet/core/models/user/UserId;", "readWalletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "readZionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "sendTxAction", "Lcom/bitcoin/mwallet/core/models/tx/TxAction;", "writeAddress", "writeAddressPurpose", "writeBigDecimal", "writeCoin", "writeContactId", "writeCopayerId", "writeCredentialType", "type", "writeDate", "writeDerivationPath", "writeEncryptedAtRestList", "writeListOfStrings", "writeNetwork", "writeNetworkFeeLevel", "writePrivateKey", "writePublicKey", "writeSatoshis", "writeSigningKey", "writeSlpId", "writeTxAction", "writeTxId", "writeUserId", "writeWalletId", "writeZionId", "PrivateKeyJson", "SigningKeyJson", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: RoomConverters.kt */
public final class RoomConverters implements KoinComponent {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(RoomConverters.class), "stringEncrypter", "getStringEncrypter()Lcom/bitcoin/securepreferences/SecureStringEncrypter;"))};
    private final Lazy stringEncrypter$delegate = LazyKt.lazy(new RoomConverters$$special$$inlined$inject$1(getKoin().getRootScope(), null, null));

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/room/RoomConverters$PrivateKeyJson;", "", "chainCodeHex", "", "privateKeyHex", "(Ljava/lang/String;Ljava/lang/String;)V", "getChainCodeHex", "()Ljava/lang/String;", "getPrivateKeyHex", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: RoomConverters.kt */
    private static final class PrivateKeyJson {
        @NotNull
        private final String chainCodeHex;
        @NotNull
        private final String privateKeyHex;

        @NotNull
        public static /* synthetic */ PrivateKeyJson copy$default(PrivateKeyJson privateKeyJson, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = privateKeyJson.chainCodeHex;
            }
            if ((i & 2) != 0) {
                str2 = privateKeyJson.privateKeyHex;
            }
            return privateKeyJson.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.chainCodeHex;
        }

        @NotNull
        public final String component2() {
            return this.privateKeyHex;
        }

        @NotNull
        public final PrivateKeyJson copy(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "chainCodeHex");
            Intrinsics.checkParameterIsNotNull(str2, "privateKeyHex");
            return new PrivateKeyJson(str, str2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.privateKeyHex, (java.lang.Object) r3.privateKeyHex) != false) goto L_0x001f;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x001f
                boolean r0 = r3 instanceof com.bitcoin.mwallet.core.dao.room.RoomConverters.PrivateKeyJson
                if (r0 == 0) goto L_0x001d
                com.bitcoin.mwallet.core.dao.room.RoomConverters$PrivateKeyJson r3 = (com.bitcoin.mwallet.core.dao.room.RoomConverters.PrivateKeyJson) r3
                java.lang.String r0 = r2.chainCodeHex
                java.lang.String r1 = r3.chainCodeHex
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x001d
                java.lang.String r0 = r2.privateKeyHex
                java.lang.String r3 = r3.privateKeyHex
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
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.dao.room.RoomConverters.PrivateKeyJson.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            String str = this.chainCodeHex;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.privateKeyHex;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode + i;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("PrivateKeyJson(chainCodeHex=");
            sb.append(this.chainCodeHex);
            sb.append(", privateKeyHex=");
            sb.append(this.privateKeyHex);
            sb.append(")");
            return sb.toString();
        }

        public PrivateKeyJson(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "chainCodeHex");
            Intrinsics.checkParameterIsNotNull(str2, "privateKeyHex");
            this.chainCodeHex = str;
            this.privateKeyHex = str2;
        }

        @NotNull
        public final String getChainCodeHex() {
            return this.chainCodeHex;
        }

        @NotNull
        public final String getPrivateKeyHex() {
            return this.privateKeyHex;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/room/RoomConverters$SigningKeyJson;", "", "privateKey", "", "pubKey", "(Ljava/lang/String;Ljava/lang/String;)V", "getPrivateKey", "()Ljava/lang/String;", "getPubKey", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: RoomConverters.kt */
    private static final class SigningKeyJson {
        @NotNull
        private final String privateKey;
        @NotNull
        private final String pubKey;

        @NotNull
        public static /* synthetic */ SigningKeyJson copy$default(SigningKeyJson signingKeyJson, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = signingKeyJson.privateKey;
            }
            if ((i & 2) != 0) {
                str2 = signingKeyJson.pubKey;
            }
            return signingKeyJson.copy(str, str2);
        }

        @NotNull
        public final String component1() {
            return this.privateKey;
        }

        @NotNull
        public final String component2() {
            return this.pubKey;
        }

        @NotNull
        public final SigningKeyJson copy(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "privateKey");
            Intrinsics.checkParameterIsNotNull(str2, "pubKey");
            return new SigningKeyJson(str, str2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.pubKey, (java.lang.Object) r3.pubKey) != false) goto L_0x001f;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x001f
                boolean r0 = r3 instanceof com.bitcoin.mwallet.core.dao.room.RoomConverters.SigningKeyJson
                if (r0 == 0) goto L_0x001d
                com.bitcoin.mwallet.core.dao.room.RoomConverters$SigningKeyJson r3 = (com.bitcoin.mwallet.core.dao.room.RoomConverters.SigningKeyJson) r3
                java.lang.String r0 = r2.privateKey
                java.lang.String r1 = r3.privateKey
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x001d
                java.lang.String r0 = r2.pubKey
                java.lang.String r3 = r3.pubKey
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
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.dao.room.RoomConverters.SigningKeyJson.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            String str = this.privateKey;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.pubKey;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode + i;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("SigningKeyJson(privateKey=");
            sb.append(this.privateKey);
            sb.append(", pubKey=");
            sb.append(this.pubKey);
            sb.append(")");
            return sb.toString();
        }

        public SigningKeyJson(@NotNull String str, @NotNull String str2) {
            Intrinsics.checkParameterIsNotNull(str, "privateKey");
            Intrinsics.checkParameterIsNotNull(str2, "pubKey");
            this.privateKey = str;
            this.pubKey = str2;
        }

        @NotNull
        public final String getPrivateKey() {
            return this.privateKey;
        }

        @NotNull
        public final String getPubKey() {
            return this.pubKey;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[AddressPurpose.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[Coin.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[CredentialType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$3 = new int[Network.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$4 = new int[TxAction.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$5 = new int[NetworkFeeLevel.values().length];

        static {
            $EnumSwitchMapping$0[AddressPurpose.RECEIVE.ordinal()] = 1;
            $EnumSwitchMapping$0[AddressPurpose.CHANGE.ordinal()] = 2;
            $EnumSwitchMapping$1[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$1[Coin.BTC.ordinal()] = 2;
            $EnumSwitchMapping$2[CredentialType.MNEMONIC_AND_PROTECTED.ordinal()] = 1;
            $EnumSwitchMapping$2[CredentialType.MNEMONIC_AND_PRIVATE_KEY.ordinal()] = 2;
            $EnumSwitchMapping$2[CredentialType.ZION_VAULT.ordinal()] = 3;
            $EnumSwitchMapping$2[CredentialType.ENCRYPTED_MNEMONIC.ordinal()] = 4;
            $EnumSwitchMapping$3[Network.MAIN.ordinal()] = 1;
            $EnumSwitchMapping$3[Network.TEST.ordinal()] = 2;
            $EnumSwitchMapping$4[TxAction.SENT.ordinal()] = 1;
            $EnumSwitchMapping$4[TxAction.RECEIVED.ordinal()] = 2;
            $EnumSwitchMapping$4[TxAction.MOVED.ordinal()] = 3;
            $EnumSwitchMapping$4[TxAction.UNKNOWN.ordinal()] = 4;
            $EnumSwitchMapping$5[NetworkFeeLevel.PRIORITY.ordinal()] = 1;
            $EnumSwitchMapping$5[NetworkFeeLevel.NORMAL.ordinal()] = 2;
            $EnumSwitchMapping$5[NetworkFeeLevel.ECONOMY.ordinal()] = 3;
        }
    }

    private final SecureStringEncrypter getStringEncrypter() {
        Lazy lazy = this.stringEncrypter$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (SecureStringEncrypter) lazy.getValue();
    }

    @NotNull
    public Koin getKoin() {
        return DefaultImpls.getKoin(this);
    }

    @TypeConverter
    @NotNull
    public final String writeAddress(@NotNull Address address) {
        Intrinsics.checkParameterIsNotNull(address, "value");
        return address.toBase58();
    }

    @TypeConverter
    @Nullable
    public final Address readAddress(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return Address.Companion.tryParse(Network.MAIN, str);
    }

    @TypeConverter
    @NotNull
    public final AddressPurpose readAddressPurpose(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        int hashCode = str.hashCode();
        if (hashCode != -1361636432) {
            if (hashCode == 1082290915 && str.equals("receive")) {
                return AddressPurpose.RECEIVE;
            }
        } else if (str.equals("change")) {
            return AddressPurpose.CHANGE;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("AddressPurpose not deserializable from ");
        sb.append(this);
        throw new IllegalArgumentException(sb.toString());
    }

    @TypeConverter
    @NotNull
    public final String writeAddressPurpose(@NotNull AddressPurpose addressPurpose) {
        Intrinsics.checkParameterIsNotNull(addressPurpose, "value");
        int i = WhenMappings.$EnumSwitchMapping$0[addressPurpose.ordinal()];
        if (i == 1) {
            return "receive";
        }
        if (i == 2) {
            return "change";
        }
        throw new NoWhenBranchMatchedException();
    }

    @TypeConverter
    @NotNull
    public final String writeBigDecimal(@NotNull BigDecimal bigDecimal) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "value");
        String bigDecimal2 = bigDecimal.toString();
        Intrinsics.checkExpressionValueIsNotNull(bigDecimal2, "value.toString()");
        return bigDecimal2;
    }

    @TypeConverter
    @NotNull
    public final BigDecimal readBigDecimal(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return new BigDecimal(str);
    }

    @TypeConverter
    @NotNull
    public final String writeCoin(@NotNull Coin coin) {
        Intrinsics.checkParameterIsNotNull(coin, "value");
        int i = WhenMappings.$EnumSwitchMapping$1[coin.ordinal()];
        if (i == 1) {
            return "bch";
        }
        if (i == 2) {
            return "btc";
        }
        throw new NoWhenBranchMatchedException();
    }

    @TypeConverter
    @NotNull
    public final Coin readCoin(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        int hashCode = str.hashCode();
        if (hashCode != 97351) {
            if (hashCode == 97873 && str.equals("btc")) {
                return Coin.BTC;
            }
        } else if (str.equals("bch")) {
            return Coin.BCH;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Coin not deserializable from ");
        sb.append(this);
        throw new IllegalArgumentException(sb.toString());
    }

    @TypeConverter
    @NotNull
    public final String writeCopayerId(@NotNull CopayerId copayerId) {
        Intrinsics.checkParameterIsNotNull(copayerId, "value");
        return copayerId.toString();
    }

    @TypeConverter
    @NotNull
    public final CopayerId readCopayerId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return new CopayerId(str);
    }

    @TypeConverter
    @NotNull
    public final CredentialType readCredentialType(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        switch (str.hashCode()) {
            case -1452642303:
                if (str.equals("zion_vault")) {
                    return CredentialType.ZION_VAULT;
                }
                break;
            case -991568292:
                if (str.equals("mem_and_protected")) {
                    return CredentialType.MNEMONIC_AND_PROTECTED;
                }
                break;
            case -723797183:
                if (str.equals("mem_and_priv")) {
                    return CredentialType.MNEMONIC_AND_PRIVATE_KEY;
                }
                break;
            case -436544291:
                if (str.equals("encrypted_mnemonic")) {
                    return CredentialType.ENCRYPTED_MNEMONIC;
                }
                break;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("CredentialType not deserializable from ");
        sb.append(this);
        throw new IllegalArgumentException(sb.toString());
    }

    @TypeConverter
    @NotNull
    public final String writeCredentialType(@NotNull CredentialType credentialType) {
        Intrinsics.checkParameterIsNotNull(credentialType, "type");
        int i = WhenMappings.$EnumSwitchMapping$2[credentialType.ordinal()];
        if (i == 1) {
            return "mem_and_protected";
        }
        if (i == 2) {
            return "mem_and_priv";
        }
        if (i == 3) {
            return "zion_vault";
        }
        if (i == 4) {
            return "encrypted_mnemonic";
        }
        throw new NoWhenBranchMatchedException();
    }

    @TypeConverter
    public final long writeDate(@NotNull Date date) {
        Intrinsics.checkParameterIsNotNull(date, "value");
        return date.getTime();
    }

    @TypeConverter
    @NotNull
    public final Date readDate(long j) {
        return new Date(j);
    }

    @TypeConverter
    @NotNull
    public final String writeDerivationPath(@NotNull DerivationPath derivationPath) {
        Intrinsics.checkParameterIsNotNull(derivationPath, "value");
        return DerivationPath.asString$default(derivationPath, false, 1, null);
    }

    @TypeConverter
    @NotNull
    public final DerivationPath readDerivationPath(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return DerivationPath.Companion.parse(str);
    }

    @TypeConverter
    @NotNull
    public final String writeEncryptedAtRestList(@NotNull EncryptedAtRestList encryptedAtRestList) {
        Intrinsics.checkParameterIsNotNull(encryptedAtRestList, "value");
        return getStringEncrypter().encryptString(CollectionsKt.joinToString$default(encryptedAtRestList, " ", null, null, 0, null, null, 62, null));
    }

    @TypeConverter
    @NotNull
    public final EncryptedAtRestList readEncryptedAtRestList(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return new EncryptedAtRestList(StringsKt.split$default((CharSequence) getStringEncrypter().decryptString(str), new String[]{" "}, false, 0, 6, (Object) null));
    }

    @TypeConverter
    @NotNull
    public final String writeListOfStrings(@NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "value");
        String jSONArray = new JSONArray(list).toString();
        Intrinsics.checkExpressionValueIsNotNull(jSONArray, "JSONArray(value).toString()");
        return jSONArray;
    }

    @TypeConverter
    @NotNull
    public final List<String> readListOfStrings(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        List<String> arrayList = new ArrayList<>();
        JSONArray jSONArray = new JSONArray(str);
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            String string = jSONArray.getString(i);
            Intrinsics.checkExpressionValueIsNotNull(string, "array.getString(i)");
            arrayList.add(string);
        }
        return arrayList;
    }

    @TypeConverter
    @NotNull
    public final String writeNetwork(@NotNull Network network) {
        Intrinsics.checkParameterIsNotNull(network, "value");
        int i = WhenMappings.$EnumSwitchMapping$3[network.ordinal()];
        if (i == 1) {
            return NetworkParameters.PAYMENT_PROTOCOL_ID_MAINNET;
        }
        if (i == 2) {
            return NetworkParameters.PAYMENT_PROTOCOL_ID_TESTNET;
        }
        throw new NoWhenBranchMatchedException();
    }

    @TypeConverter
    @NotNull
    public final Network readNetwork(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        int hashCode = str.hashCode();
        if (hashCode != 3343801) {
            if (hashCode == 3556498 && str.equals(NetworkParameters.PAYMENT_PROTOCOL_ID_TESTNET)) {
                return Network.TEST;
            }
        } else if (str.equals(NetworkParameters.PAYMENT_PROTOCOL_ID_MAINNET)) {
            return Network.MAIN;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Network not deserializable from ");
        sb.append(this);
        throw new IllegalArgumentException(sb.toString());
    }

    @TypeConverter
    @NotNull
    public final String writePrivateKey(@NotNull PrivateKey privateKey) {
        Intrinsics.checkParameterIsNotNull(privateKey, "value");
        PrivateKeyJson privateKeyJson = new PrivateKeyJson(HexKt.toHexString(privateKey.getChainCode()), privateKey.getHex().getHex());
        Moshi build = new Builder().build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Moshi.Builder().build()");
        JsonAdapter adapter = build.adapter(PrivateKeyJson.class);
        Intrinsics.checkExpressionValueIsNotNull(adapter, "moshi.adapter(\n         …son::class.java\n        )");
        String json = adapter.toJson(privateKeyJson);
        Intrinsics.checkExpressionValueIsNotNull(json, "jsonAdapter.toJson(privateKeyJson)");
        return getStringEncrypter().encryptString(json);
    }

    @TypeConverter
    @NotNull
    public final PrivateKey readPrivateKey(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        String decryptString = getStringEncrypter().decryptString(str);
        Moshi build = new Builder().build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Moshi.Builder().build()");
        JsonAdapter adapter = build.adapter(PrivateKeyJson.class);
        Intrinsics.checkExpressionValueIsNotNull(adapter, "moshi.adapter(\n         …son::class.java\n        )");
        PrivateKeyJson privateKeyJson = (PrivateKeyJson) adapter.fromJson(decryptString);
        if (privateKeyJson != null) {
            byte[] decode = Hex.INSTANCE.decode(privateKeyJson.getChainCodeHex());
            return PrivateKey.Companion.masterFromPrivateKeyBytesAndChainCode(Hex.INSTANCE.decode(privateKeyJson.getPrivateKeyHex()), decode);
        }
        throw new Exception("Failed to decode private key.");
    }

    @TypeConverter
    @NotNull
    public final String writePublicKey(@NotNull ExtendedPublicKey extendedPublicKey) {
        Intrinsics.checkParameterIsNotNull(extendedPublicKey, "value");
        return extendedPublicKey.base58(Network.MAIN);
    }

    @TypeConverter
    @NotNull
    public final ExtendedPublicKey readPublicKey(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return ExtendedPublicKey.Companion.fromBase58(str, Network.MAIN);
    }

    @TypeConverter
    public final long writeSatoshis(@NotNull Satoshis satoshis) {
        Intrinsics.checkParameterIsNotNull(satoshis, "value");
        return satoshis.getSatoshis();
    }

    @TypeConverter
    @NotNull
    public final Satoshis readSatoshis(long j) {
        return new Satoshis(j);
    }

    @TypeConverter
    @NotNull
    public final String writeUserId(@NotNull UserId userId) {
        Intrinsics.checkParameterIsNotNull(userId, "value");
        return String.valueOf(userId.getId());
    }

    @TypeConverter
    @NotNull
    public final UserId readUserId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return new UserId(str);
    }

    @TypeConverter
    @NotNull
    public final String writeWalletId(@NotNull WalletId walletId) {
        Intrinsics.checkParameterIsNotNull(walletId, "value");
        return walletId.getId();
    }

    @TypeConverter
    @NotNull
    public final WalletId readWalletId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return new WalletId(str);
    }

    @TypeConverter
    @NotNull
    public final String writeSlpId(@NotNull SlpId slpId) {
        Intrinsics.checkParameterIsNotNull(slpId, "value");
        return slpId.getId();
    }

    @TypeConverter
    @NotNull
    public final SlpId readSlpId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return new SlpId(str);
    }

    @TypeConverter
    @NotNull
    public final String writeSigningKey(@NotNull SigningKey signingKey) {
        Intrinsics.checkParameterIsNotNull(signingKey, "value");
        SigningKeyJson signingKeyJson = new SigningKeyJson(signingKey.getPrivateKey(), signingKey.getPublicKey());
        Moshi build = new Builder().build();
        Intrinsics.checkExpressionValueIsNotNull(build, "Moshi.Builder().build()");
        JsonAdapter adapter = build.adapter(SigningKeyJson.class);
        Intrinsics.checkExpressionValueIsNotNull(adapter, "moshi.adapter(\n         …son::class.java\n        )");
        String json = adapter.toJson(signingKeyJson);
        Intrinsics.checkExpressionValueIsNotNull(json, "jsonAdapter.toJson(signingKeyJson)");
        return getStringEncrypter().encryptString(json);
    }

    @TypeConverter
    @NotNull
    public final SigningKey readSigningKey(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        String str2 = "";
        if (str.length() == 0) {
            return new SigningKey(str2, str2);
        }
        try {
            String decryptString = getStringEncrypter().decryptString(str);
            Moshi build = new Builder().build();
            Intrinsics.checkExpressionValueIsNotNull(build, "Moshi.Builder().build()");
            JsonAdapter adapter = build.adapter(SigningKeyJson.class);
            Intrinsics.checkExpressionValueIsNotNull(adapter, "moshi.adapter(\n         …:class.java\n            )");
            SigningKeyJson signingKeyJson = (SigningKeyJson) adapter.fromJson(decryptString);
            if (signingKeyJson != null) {
                return new SigningKey(signingKeyJson.getPrivateKey(), signingKeyJson.getPubKey());
            }
            throw new Exception("Failed to decode private key.");
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Reading signing key failed ");
            sb.append(e);
            Timber.m426d(sb.toString(), new Object[0]);
            return new SigningKey(str2, str2);
        }
    }

    @TypeConverter
    @NotNull
    public final String writeContactId(@NotNull ContactId contactId) {
        Intrinsics.checkParameterIsNotNull(contactId, "value");
        return contactId.getId();
    }

    @TypeConverter
    @NotNull
    public final ContactId readContactId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return new ContactId(str);
    }

    @TypeConverter
    @NotNull
    public final String writeTxId(@NotNull TxId txId) {
        Intrinsics.checkParameterIsNotNull(txId, "value");
        return txId.getId();
    }

    @TypeConverter
    @NotNull
    public final TxId readTxId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return new TxId(str);
    }

    @TypeConverter
    @NotNull
    public final String writeZionId(@NotNull ZionId zionId) {
        Intrinsics.checkParameterIsNotNull(zionId, "value");
        return String.valueOf(zionId.getId());
    }

    @TypeConverter
    @NotNull
    public final ZionId readZionId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        return new ZionId(Long.parseLong(str));
    }

    @TypeConverter
    @NotNull
    public final String writeTxAction(@NotNull TxAction txAction) {
        Intrinsics.checkParameterIsNotNull(txAction, "value");
        int i = WhenMappings.$EnumSwitchMapping$4[txAction.ordinal()];
        if (i == 1) {
            return "sent";
        }
        if (i == 2) {
            return "received";
        }
        if (i == 3) {
            return "moved";
        }
        if (i == 4) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        throw new NoWhenBranchMatchedException();
    }

    @TypeConverter
    @NotNull
    public final TxAction sendTxAction(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        switch (str.hashCode()) {
            case -808719903:
                if (str.equals("received")) {
                    return TxAction.RECEIVED;
                }
                break;
            case -284840886:
                if (str.equals(EnvironmentCompat.MEDIA_UNKNOWN)) {
                    return TxAction.UNKNOWN;
                }
                break;
            case 3526552:
                if (str.equals("sent")) {
                    return TxAction.SENT;
                }
                break;
            case 104087219:
                if (str.equals("moved")) {
                    return TxAction.MOVED;
                }
                break;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("TxAction not deserializable from ");
        sb.append(this);
        throw new IllegalArgumentException(sb.toString());
    }

    @TypeConverter
    @NotNull
    public final String writeNetworkFeeLevel(@NotNull NetworkFeeLevel networkFeeLevel) {
        Intrinsics.checkParameterIsNotNull(networkFeeLevel, "value");
        int i = WhenMappings.$EnumSwitchMapping$5[networkFeeLevel.ordinal()];
        if (i == 1) {
            return "priority";
        }
        if (i == 2) {
            return "normal";
        }
        if (i == 3) {
            return "economy";
        }
        throw new NoWhenBranchMatchedException();
    }

    @TypeConverter
    @NotNull
    public final NetworkFeeLevel readNetworkFeeLevel(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "value");
        int hashCode = str.hashCode();
        if (hashCode != -1911224770) {
            if (hashCode != -1165461084) {
                if (hashCode == -1039745817 && str.equals("normal")) {
                    return NetworkFeeLevel.NORMAL;
                }
            } else if (str.equals("priority")) {
                return NetworkFeeLevel.PRIORITY;
            }
        } else if (str.equals("economy")) {
            return NetworkFeeLevel.ECONOMY;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("NetworkFeeLevel not deserializable from ");
        sb.append(this);
        throw new IllegalArgumentException(sb.toString());
    }
}
