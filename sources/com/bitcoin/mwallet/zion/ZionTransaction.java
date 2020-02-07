package com.bitcoin.mwallet.zion;

import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.Hex;
import com.bitcoin.bitcoink.address.Address;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import org.bitcoinj.script.ScriptOpCodes;
import org.bitcoinj.uri.BitcoinURI;
import org.jetbrains.annotations.NotNull;

@JsonClass(generateAdapter = true)
@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0015\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0004\u001c\u001d\u001e\u001fB1\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0002\u0010\tBg\u0012\b\b\u0003\u0010\n\u001a\u00020\u000b\u0012\b\b\u0001\u0010\f\u001a\u00020\u000b\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0001\u0010\r\u001a\u00020\u000b\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\b\b\u0001\u0010\u000e\u001a\u00020\u000b\u0012\u000e\b\u0001\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\b\b\u0003\u0010\u0010\u001a\u00020\u000b¢\u0006\u0002\u0010\u0011R\u0011\u0010\u000e\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0010\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\r\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013¨\u0006 "}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionTransaction;", "", "inputs", "", "Lcom/bitcoin/mwallet/zion/ZionTransaction$Input;", "outputs", "Lcom/bitcoin/mwallet/zion/ZionTransaction$Output;", "change", "Lcom/bitcoin/mwallet/zion/ZionTransaction$Change;", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "version", "", "inputCount", "outputCount", "changeCount", "changes", "lockTime", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getChangeCount", "()Ljava/lang/String;", "getChanges", "()Ljava/util/List;", "getInputCount", "getInputs", "getLockTime", "getOutputCount", "getOutputs", "getVersion", "Change", "Companion", "Input", "Output", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ZionTransaction.kt */
public final class ZionTransaction {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String changeCount;
    @NotNull
    private final List<Change> changes;
    @NotNull
    private final String inputCount;
    @NotNull
    private final List<Input> inputs;
    @NotNull
    private final String lockTime;
    @NotNull
    private final String outputCount;
    @NotNull
    private final List<Output> outputs;
    @NotNull
    private final String version;

    @JsonClass(generateAdapter = true)
    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0019\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0007\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionTransaction$Change;", "", "path", "Lcom/bitcoin/bitcoink/DerivationPath;", "amount", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "(Lcom/bitcoin/bitcoink/DerivationPath;Lcom/bitcoin/bitcoink/tx/Satoshis;)V", "", "(Ljava/lang/String;Ljava/lang/String;)V", "getAmount", "()Ljava/lang/String;", "getPath", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ZionTransaction.kt */
    public static final class Change {
        @NotNull
        private final String amount;
        @NotNull
        private final String path;

        public Change(@NotNull @Json(name = "path") String str, @NotNull @Json(name = "amount") String str2) {
            Intrinsics.checkParameterIsNotNull(str, JsonDataKey_signMessage.path);
            Intrinsics.checkParameterIsNotNull(str2, BitcoinURI.FIELD_AMOUNT);
            this.path = str;
            this.amount = str2;
        }

        @NotNull
        public final String getPath() {
            return this.path;
        }

        @NotNull
        public final String getAmount() {
            return this.amount;
        }

        public Change(@NotNull DerivationPath derivationPath, @NotNull Satoshis satoshis) {
            Intrinsics.checkParameterIsNotNull(derivationPath, JsonDataKey_signMessage.path);
            Intrinsics.checkParameterIsNotNull(satoshis, BitcoinURI.FIELD_AMOUNT);
            this(derivationPath.asString(true), ZionTransaction.Companion.littleEndianHex(satoshis));
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0002J\f\u0010\u0006\u001a\u00020\u0004*\u00020\u0007H\u0002¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionTransaction$Companion;", "", "()V", "littleEndianHex", "", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "zionIntegerPad", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ZionTransaction.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final String zionIntegerPad(int i) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Integer.valueOf(i)};
            String format = String.format("%02d", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            return format;
        }

        /* access modifiers changed from: private */
        public final String littleEndianHex(@NotNull Satoshis satoshis) {
            String l = Long.toString(satoshis.getSatoshis(), CharsKt.checkRadix(16));
            Intrinsics.checkExpressionValueIsNotNull(l, "java.lang.Long.toString(this, checkRadix(radix))");
            if (l.length() % 2 != 0) {
                StringBuilder sb = new StringBuilder();
                sb.append('0');
                sb.append(l);
                l = sb.toString();
            }
            StringBuilder sb2 = new StringBuilder();
            IntProgression step = RangesKt.step(RangesKt.downTo(l.length() - 2, 0), 2);
            int first = step.getFirst();
            int last = step.getLast();
            int step2 = step.getStep();
            if (step2 < 0 ? first >= last : first <= last) {
                while (true) {
                    int i = first + 2;
                    if (l != null) {
                        String substring = l.substring(first, i);
                        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        sb2.append(substring);
                        if (first == last) {
                            break;
                        }
                        first += step2;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                }
            }
            String sb3 = sb2.toString();
            Intrinsics.checkExpressionValueIsNotNull(sb3, "sb.toString()");
            return sb3;
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B/\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fBA\u0012\b\b\u0001\u0010\u0002\u001a\u00020\r\u0012\b\b\u0001\u0010\u0004\u001a\u00020\r\u0012\b\b\u0001\u0010\u0006\u001a\u00020\r\u0012\b\b\u0001\u0010\b\u001a\u00020\r\u0012\b\b\u0003\u0010\u000e\u001a\u00020\r\u0012\b\b\u0001\u0010\n\u001a\u00020\r¢\u0006\u0002\u0010\u000fR\u0011\u0010\n\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\b\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011¨\u0006\u0017"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionTransaction$Input;", "", "path", "Lcom/bitcoin/bitcoink/DerivationPath;", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "txIndex", "", "scriptSig", "", "amount", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "(Lcom/bitcoin/bitcoink/DerivationPath;Lcom/bitcoin/bitcoink/tx/TxId;I[BLcom/bitcoin/bitcoink/tx/Satoshis;)V", "", "sequence", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAmount", "()Ljava/lang/String;", "getPath", "getScriptSig", "getSequence", "getTxId", "getTxIndex", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ZionTransaction.kt */
    public static final class Input {
        @NotNull
        private final String amount;
        @NotNull
        private final String path;
        @NotNull
        private final String scriptSig;
        @NotNull
        private final String sequence;
        @NotNull
        private final String txId;
        @NotNull
        private final String txIndex;

        public Input(@NotNull @Json(name = "path") String str, @NotNull @Json(name = "tx_id") String str2, @NotNull @Json(name = "tx_index") String str3, @NotNull @Json(name = "scriptSig") String str4, @NotNull @Json(name = "sequence") String str5, @NotNull @Json(name = "amount") String str6) {
            Intrinsics.checkParameterIsNotNull(str, JsonDataKey_signMessage.path);
            Intrinsics.checkParameterIsNotNull(str2, "txId");
            Intrinsics.checkParameterIsNotNull(str3, "txIndex");
            Intrinsics.checkParameterIsNotNull(str4, "scriptSig");
            Intrinsics.checkParameterIsNotNull(str5, "sequence");
            Intrinsics.checkParameterIsNotNull(str6, BitcoinURI.FIELD_AMOUNT);
            this.path = str;
            this.txId = str2;
            this.txIndex = str3;
            this.scriptSig = str4;
            this.sequence = str5;
            this.amount = str6;
        }

        @NotNull
        public final String getPath() {
            return this.path;
        }

        @NotNull
        public final String getTxId() {
            return this.txId;
        }

        @NotNull
        public final String getTxIndex() {
            return this.txIndex;
        }

        @NotNull
        public final String getScriptSig() {
            return this.scriptSig;
        }

        public /* synthetic */ Input(String str, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 16) != 0) {
                str5 = "ffffffff";
            }
            this(str, str2, str3, str4, str5, str6);
        }

        @NotNull
        public final String getSequence() {
            return this.sequence;
        }

        @NotNull
        public final String getAmount() {
            return this.amount;
        }

        public Input(@NotNull DerivationPath derivationPath, @NotNull TxId txId2, int i, @NotNull byte[] bArr, @NotNull Satoshis satoshis) {
            Intrinsics.checkParameterIsNotNull(derivationPath, JsonDataKey_signMessage.path);
            Intrinsics.checkParameterIsNotNull(txId2, "txId");
            Intrinsics.checkParameterIsNotNull(bArr, "scriptSig");
            Intrinsics.checkParameterIsNotNull(satoshis, BitcoinURI.FIELD_AMOUNT);
            this(derivationPath.asString(true), txId2.getId(), ZionTransaction.Companion.zionIntegerPad(i), Hex.Companion.encode(bArr).getHex(), null, ZionTransaction.Companion.littleEndianHex(satoshis), 16, null);
        }
    }

    @JsonClass(generateAdapter = true)
    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0019\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionTransaction$Output;", "", "address", "Lcom/bitcoin/bitcoink/address/Address;", "amount", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "(Lcom/bitcoin/bitcoink/address/Address;Lcom/bitcoin/bitcoink/tx/Satoshis;)V", "", "(Ljava/lang/String;Ljava/lang/String;)V", "getAddress", "()Ljava/lang/String;", "getAmount", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ZionTransaction.kt */
    public static final class Output {
        @NotNull
        private final String address;
        @NotNull
        private final String amount;

        public Output(@NotNull @Json(name = "amount") String str, @NotNull @Json(name = "address") String str2) {
            Intrinsics.checkParameterIsNotNull(str, BitcoinURI.FIELD_AMOUNT);
            Intrinsics.checkParameterIsNotNull(str2, "address");
            this.amount = str;
            this.address = str2;
        }

        @NotNull
        public final String getAmount() {
            return this.amount;
        }

        @NotNull
        public final String getAddress() {
            return this.address;
        }

        public Output(@NotNull Address address2, @NotNull Satoshis satoshis) {
            Intrinsics.checkParameterIsNotNull(address2, "address");
            Intrinsics.checkParameterIsNotNull(satoshis, BitcoinURI.FIELD_AMOUNT);
            this(ZionTransaction.Companion.littleEndianHex(satoshis), address2.toCash().toString());
        }
    }

    public ZionTransaction(@NotNull @Json(name = "tx_version") String str, @NotNull @Json(name = "tx_inputs_count") String str2, @NotNull @Json(name = "tx_inputs") List<Input> list, @NotNull @Json(name = "tx_outputs_count") String str3, @NotNull @Json(name = "tx_outputs") List<Output> list2, @NotNull @Json(name = "tx_changes_count") String str4, @NotNull @Json(name = "tx_changes") List<Change> list3, @NotNull @Json(name = "lock_time") String str5) {
        Intrinsics.checkParameterIsNotNull(str, "version");
        Intrinsics.checkParameterIsNotNull(str2, "inputCount");
        Intrinsics.checkParameterIsNotNull(list, "inputs");
        Intrinsics.checkParameterIsNotNull(str3, "outputCount");
        Intrinsics.checkParameterIsNotNull(list2, "outputs");
        Intrinsics.checkParameterIsNotNull(str4, "changeCount");
        Intrinsics.checkParameterIsNotNull(list3, "changes");
        Intrinsics.checkParameterIsNotNull(str5, "lockTime");
        this.version = str;
        this.inputCount = str2;
        this.inputs = list;
        this.outputCount = str3;
        this.outputs = list2;
        this.changeCount = str4;
        this.changes = list3;
        this.lockTime = str5;
    }

    @NotNull
    public final String getVersion() {
        return this.version;
    }

    @NotNull
    public final String getInputCount() {
        return this.inputCount;
    }

    @NotNull
    public final List<Input> getInputs() {
        return this.inputs;
    }

    @NotNull
    public final String getOutputCount() {
        return this.outputCount;
    }

    @NotNull
    public final List<Output> getOutputs() {
        return this.outputs;
    }

    @NotNull
    public final String getChangeCount() {
        return this.changeCount;
    }

    @NotNull
    public final List<Change> getChanges() {
        return this.changes;
    }

    public /* synthetic */ ZionTransaction(String str, String str2, List list, String str3, List list2, String str4, List list3, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        int i2 = i;
        this((i2 & 1) != 0 ? "02" : str, str2, list, str3, list2, str4, list3, (i2 & 128) != 0 ? "00" : str5);
    }

    @NotNull
    public final String getLockTime() {
        return this.lockTime;
    }

    public ZionTransaction(@NotNull List<Input> list, @NotNull List<Output> list2, @NotNull List<Change> list3) {
        Intrinsics.checkParameterIsNotNull(list, "inputs");
        Intrinsics.checkParameterIsNotNull(list2, "outputs");
        Intrinsics.checkParameterIsNotNull(list3, "change");
        this(null, Companion.zionIntegerPad(list.size()), list, Companion.zionIntegerPad(list2.size()), list2, Companion.zionIntegerPad(list3.size()), list3, null, ScriptOpCodes.OP_RIGHT, null);
    }
}
