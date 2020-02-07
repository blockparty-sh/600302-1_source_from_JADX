package com.bitcoin.mwallet.core.utils;

import androidx.core.app.NotificationCompat;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.utils.signature.SignatureHelper;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.htc.htcwalletsdk.Utils.TableParser.C2278Key;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import p015io.grpc.Metadata.C2689Key;
import p015io.grpc.stub.AbstractStub;
import p015io.grpc.stub.MetadataUtils;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00032\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/utils/SignedStub;", "", "()V", "Companion", "SignedRequest", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SignedStub.kt */
public final class SignedStub {
    public static final Companion Companion = new Companion(null);

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JC\u0010\u0003\u001a\u0002H\u0004\"\u000e\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001a\u0002H\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ9\u0010\u0003\u001a\u0002H\u0004\"\u000e\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001a\u0002H\u00042\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/utils/SignedStub$Companion;", "", "()V", "signed", "T", "Lio/grpc/stub/AbstractStub;", "service", "request", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "(Lio/grpc/stub/AbstractStub;[BLcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;)Lio/grpc/stub/AbstractStub;", "signedRequestData", "", "Lcom/bitcoin/mwallet/core/utils/SignedStub$SignedRequest;", "(Lio/grpc/stub/AbstractStub;[BLjava/util/Set;)Lio/grpc/stub/AbstractStub;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SignedStub.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final <T extends AbstractStub<T>> T signed(@NotNull T t, @NotNull byte[] bArr, @NotNull WalletId walletId, @NotNull CopayerId copayerId, @NotNull SigningKey signingKey) {
            Intrinsics.checkParameterIsNotNull(t, NotificationCompat.CATEGORY_SERVICE);
            Intrinsics.checkParameterIsNotNull(bArr, "request");
            Intrinsics.checkParameterIsNotNull(walletId, "walletId");
            Intrinsics.checkParameterIsNotNull(copayerId, "copayerId");
            Intrinsics.checkParameterIsNotNull(signingKey, "signingKey");
            return signed(t, bArr, SetsKt.setOf(new SignedRequest(walletId, copayerId, signingKey)));
        }

        @NotNull
        public final <T extends AbstractStub<T>> T signed(@NotNull T t, @NotNull byte[] bArr, @NotNull Set<SignedRequest> set) {
            Intrinsics.checkParameterIsNotNull(t, NotificationCompat.CATEGORY_SERVICE);
            Intrinsics.checkParameterIsNotNull(bArr, "request");
            Intrinsics.checkParameterIsNotNull(set, "signedRequestData");
            p015io.grpc.Metadata metadata = new p015io.grpc.Metadata();
            C2689Key of = C2689Key.m295of("wallets", p015io.grpc.Metadata.ASCII_STRING_MARSHALLER);
            C2689Key of2 = C2689Key.m295of("timestamp", p015io.grpc.Metadata.ASCII_STRING_MARSHALLER);
            JsonArray jsonArray = new JsonArray();
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            for (SignedRequest signedRequest : set) {
                if (!StringsKt.isBlank(signedRequest.getSigningKey().getPrivateKey())) {
                    JsonObject jsonObject = new JsonObject();
                    String signMessage = SignatureHelper.Signature.signMessage(bArr, currentTimeMillis, signedRequest.getSigningKey());
                    jsonObject.addProperty("walletId", signedRequest.getWalletId().getId());
                    jsonObject.addProperty("copayerId", signedRequest.getCopayerId().getId().getHex());
                    jsonObject.addProperty(C2278Key.table_signature, signMessage);
                    jsonArray.add((JsonElement) jsonObject);
                }
            }
            metadata.put(of2, String.valueOf(currentTimeMillis));
            metadata.put(of, jsonArray.toString());
            T attachHeaders = MetadataUtils.attachHeaders(t, metadata);
            Intrinsics.checkExpressionValueIsNotNull(attachHeaders, "MetadataUtils.attachHeaders(service, header)");
            return attachHeaders;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/utils/SignedStub$SignedRequest;", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;)V", "getCopayerId", "()Lcom/bitcoin/mwallet/core/models/CopayerId;", "getSigningKey", "()Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SignedStub.kt */
    public static final class SignedRequest {
        @NotNull
        private final CopayerId copayerId;
        @NotNull
        private final SigningKey signingKey;
        @NotNull
        private final WalletId walletId;

        @NotNull
        public static /* synthetic */ SignedRequest copy$default(SignedRequest signedRequest, WalletId walletId2, CopayerId copayerId2, SigningKey signingKey2, int i, Object obj) {
            if ((i & 1) != 0) {
                walletId2 = signedRequest.walletId;
            }
            if ((i & 2) != 0) {
                copayerId2 = signedRequest.copayerId;
            }
            if ((i & 4) != 0) {
                signingKey2 = signedRequest.signingKey;
            }
            return signedRequest.copy(walletId2, copayerId2, signingKey2);
        }

        @NotNull
        public final WalletId component1() {
            return this.walletId;
        }

        @NotNull
        public final CopayerId component2() {
            return this.copayerId;
        }

        @NotNull
        public final SigningKey component3() {
            return this.signingKey;
        }

        @NotNull
        public final SignedRequest copy(@NotNull WalletId walletId2, @NotNull CopayerId copayerId2, @NotNull SigningKey signingKey2) {
            Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
            Intrinsics.checkParameterIsNotNull(copayerId2, "copayerId");
            Intrinsics.checkParameterIsNotNull(signingKey2, "signingKey");
            return new SignedRequest(walletId2, copayerId2, signingKey2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.signingKey, (java.lang.Object) r3.signingKey) != false) goto L_0x0029;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x0029
                boolean r0 = r3 instanceof com.bitcoin.mwallet.core.utils.SignedStub.SignedRequest
                if (r0 == 0) goto L_0x0027
                com.bitcoin.mwallet.core.utils.SignedStub$SignedRequest r3 = (com.bitcoin.mwallet.core.utils.SignedStub.SignedRequest) r3
                com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r2.walletId
                com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r3.walletId
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0027
                com.bitcoin.mwallet.core.models.CopayerId r0 = r2.copayerId
                com.bitcoin.mwallet.core.models.CopayerId r1 = r3.copayerId
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0027
                com.bitcoin.mwallet.core.utils.signature.SigningKey r0 = r2.signingKey
                com.bitcoin.mwallet.core.utils.signature.SigningKey r3 = r3.signingKey
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
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.utils.SignedStub.SignedRequest.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            WalletId walletId2 = this.walletId;
            int i = 0;
            int hashCode = (walletId2 != null ? walletId2.hashCode() : 0) * 31;
            CopayerId copayerId2 = this.copayerId;
            int hashCode2 = (hashCode + (copayerId2 != null ? copayerId2.hashCode() : 0)) * 31;
            SigningKey signingKey2 = this.signingKey;
            if (signingKey2 != null) {
                i = signingKey2.hashCode();
            }
            return hashCode2 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("SignedRequest(walletId=");
            sb.append(this.walletId);
            sb.append(", copayerId=");
            sb.append(this.copayerId);
            sb.append(", signingKey=");
            sb.append(this.signingKey);
            sb.append(")");
            return sb.toString();
        }

        public SignedRequest(@NotNull WalletId walletId2, @NotNull CopayerId copayerId2, @NotNull SigningKey signingKey2) {
            Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
            Intrinsics.checkParameterIsNotNull(copayerId2, "copayerId");
            Intrinsics.checkParameterIsNotNull(signingKey2, "signingKey");
            this.walletId = walletId2;
            this.copayerId = copayerId2;
            this.signingKey = signingKey2;
        }

        @NotNull
        public final CopayerId getCopayerId() {
            return this.copayerId;
        }

        @NotNull
        public final SigningKey getSigningKey() {
            return this.signingKey;
        }

        @NotNull
        public final WalletId getWalletId() {
            return this.walletId;
        }
    }
}
