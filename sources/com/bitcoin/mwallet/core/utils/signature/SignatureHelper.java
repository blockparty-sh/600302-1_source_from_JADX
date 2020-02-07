package com.bitcoin.mwallet.core.utils.signature;

import com.bitcoin.bitcoink.util.ByteUtils;
import com.bitcoin.bitcoink.util.Sha256Hash;
import com.bitcoin.mwallet.core.utils.slp.ByteUtils.Hex;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import walletutils.Keypair;
import walletutils.Walletutils;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/utils/signature/SignatureHelper;", "", "()V", "Signature", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SignatureHelper.kt */
public final class SignatureHelper {
    public static final Signature Signature = new Signature(null);

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/utils/signature/SignatureHelper$Signature;", "", "()V", "getSignaturePrivPubKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "xPriv", "", "signMessage", "request", "", "timestamp", "", "signingKey", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SignatureHelper.kt */
    public static final class Signature {
        private Signature() {
        }

        public /* synthetic */ Signature(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SigningKey getSignaturePrivPubKey(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "xPriv");
            byte[] signDeterministicWithXPrv = Walletutils.signDeterministicWithXPrv(str, "bitcoin.com");
            Sha256Hash sha256Hash = Sha256Hash.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(signDeterministicWithXPrv, "signingDeterministic");
            Keypair newKeypairFromPrivateKey = Walletutils.newKeypairFromPrivateKey(sha256Hash.hashOnce(signDeterministicWithXPrv));
            Intrinsics.checkExpressionValueIsNotNull(newKeypairFromPrivateKey, "walletutils.Walletutils.…y(hashedDeterministicKey)");
            Hex hex = Hex.INSTANCE;
            byte[] privateKey = newKeypairFromPrivateKey.getPrivateKey();
            Intrinsics.checkExpressionValueIsNotNull(privateKey, "keyPair.privateKey");
            String encode = hex.encode(privateKey);
            Hex hex2 = Hex.INSTANCE;
            byte[] publicKey = newKeypairFromPrivateKey.getPublicKey();
            Intrinsics.checkExpressionValueIsNotNull(publicKey, "keyPair.publicKey");
            return new SigningKey(encode, hex2.encode(publicKey));
        }

        @NotNull
        public final String signMessage(@NotNull byte[] bArr, long j, @NotNull SigningKey signingKey) {
            Intrinsics.checkParameterIsNotNull(bArr, "request");
            Intrinsics.checkParameterIsNotNull(signingKey, "signingKey");
            StringBuilder sb = new StringBuilder();
            sb.append(Hex.INSTANCE.encode(bArr));
            sb.append(j);
            String signWithPrivateKey = Walletutils.signWithPrivateKey(ByteUtils.Hex.INSTANCE.decode(signingKey.getPrivateKey()), sb.toString());
            Intrinsics.checkExpressionValueIsNotNull(signWithPrivateKey, "walletutils.Walletutils.…Key.privateKey), message)");
            return signWithPrivateKey;
        }
    }
}
