package com.bitcoin.mwallet.zion;

import com.bitcoin.bitcoink.Hex.Companion;
import com.google.gson.JsonObject;
import com.htc.htcwalletsdk.Export.HtcWalletSdkManager;
import com.htc.htcwalletsdk.Native.Type.ByteArrayHolder;
import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.spongycastle.util.encoders.Hex;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/zion/ZionResponse;", "", "invoke", "com/bitcoin/mwallet/zion/ZionRepositoryZKMA$signMessage$2$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ZionRepositoryZKMA.kt */
final class ZionRepositoryZKMA$signMessage$$inlined$let$lambda$1 extends Lambda implements Function0<ZionResponse<byte[]>> {
    final /* synthetic */ Continuation $continuation$inlined;
    final /* synthetic */ HtcWalletSdkManager $it;
    final /* synthetic */ String $message$inlined;
    final /* synthetic */ ZionId $zionId$inlined;

    ZionRepositoryZKMA$signMessage$$inlined$let$lambda$1(HtcWalletSdkManager htcWalletSdkManager, Continuation continuation, ZionId zionId, String str) {
        this.$it = htcWalletSdkManager;
        this.$continuation$inlined = continuation;
        this.$zionId$inlined = zionId;
        this.$message$inlined = str;
        super(0);
    }

    @NotNull
    public final ZionResponse<byte[]> invoke() {
        ByteArrayHolder byteArrayHolder = new ByteArrayHolder();
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("version", "45");
        String str = this.$message$inlined;
        Charset charset = Charsets.UTF_8;
        if (str != null) {
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            jsonObject2.addProperty("data", Hex.toHexString(bytes));
            jsonObject.add("message", jsonObject2);
            jsonObject.addProperty(JsonDataKey_signMessage.path, "m/44'/60'/0'/0/0");
            int signMessage = this.$it.signMessage(this.$zionId$inlined.getId(), 60, this.$message$inlined, byteArrayHolder);
            ZionError fromResult = ZionError.Companion.fromResult(signMessage);
            if (fromResult != null) {
                return new ZionResponse<>(null, fromResult);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("signingMessage result=");
            sb.append(signMessage);
            sb.append(" receivedLength=");
            sb.append(byteArrayHolder.receivedLength);
            sb.append(" rawHex=");
            Companion companion = com.bitcoin.bitcoink.Hex.Companion;
            byte[] bArr = byteArrayHolder.byteArray;
            Intrinsics.checkExpressionValueIsNotNull(bArr, "signatureHolder.byteArray");
            sb.append(companion.encode(bArr));
            Timber.m426d(sb.toString(), new Object[0]);
            return new ZionResponse<>(byteArrayHolder.byteArray, null);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
