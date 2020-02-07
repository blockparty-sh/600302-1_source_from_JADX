package com.bitcoin.bitcoink.p008tx;

import com.bitcoin.bitcoink.BitcoinFork;
import com.bitcoin.bitcoink.PrivateKey;
import com.bitcoin.bitcoink.address.Address;
import com.bitcoin.bitcoink.address.MainNetParamsFork;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Transaction.SigHash;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutPoint;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.script.ScriptChunk;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 %2\u00020\u0001:\u0002%&B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J.\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u001aJ\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u0016\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u001aJ(\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J \u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0006\u0010\"\u001a\u00020\u0011J\u0006\u0010#\u001a\u00020$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/tx/TxBuilder;", "", "fork", "Lcom/bitcoin/bitcoink/BitcoinFork;", "(Lcom/bitcoin/bitcoink/BitcoinFork;)V", "inputs", "Ljava/util/ArrayList;", "Lcom/bitcoin/bitcoink/tx/TxBuilder$InputAndPrivateKey;", "Lkotlin/collections/ArrayList;", "tx", "Lorg/bitcoinj/core/Transaction;", "addInput", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "index", "", "script", "", "satoshis", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "privateKey", "Lcom/bitcoin/bitcoink/PrivateKey;", "addOutput", "Lcom/bitcoin/bitcoink/tx/TxOutput;", "address", "Lcom/bitcoin/bitcoink/address/Address;", "Lorg/bitcoinj/script/Script;", "satoshi", "", "createInput", "Lorg/bitcoinj/core/TransactionInput;", "inputSignature", "inputIndex", "input", "serialize", "txDebugString", "", "Companion", "InputAndPrivateKey", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.bitcoink.tx.TxBuilder */
/* compiled from: TxBuilder.kt */
public final class TxBuilder {
    public static final Companion Companion = new Companion(null);
    public static final long DUST_LIMIT = 546;
    private final BitcoinFork fork;
    private final ArrayList<InputAndPrivateKey> inputs = new ArrayList<>();

    /* renamed from: tx */
    private final Transaction f98tx = new Transaction(MainNetParamsFork.get());

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/tx/TxBuilder$Companion;", "", "()V", "DUST_LIMIT", "", "outputFee", "numOutputs", "", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: com.bitcoin.bitcoink.tx.TxBuilder$Companion */
    /* compiled from: TxBuilder.kt */
    public static final class Companion {
        public final long outputFee(int i) {
            return ((long) i) * ((long) 34);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/tx/TxBuilder$InputAndPrivateKey;", "", "input", "Lorg/bitcoinj/core/TransactionInput;", "privateKey", "Lcom/bitcoin/bitcoink/PrivateKey;", "(Lorg/bitcoinj/core/TransactionInput;Lcom/bitcoin/bitcoink/PrivateKey;)V", "getInput", "()Lorg/bitcoinj/core/TransactionInput;", "getPrivateKey", "()Lcom/bitcoin/bitcoink/PrivateKey;", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: com.bitcoin.bitcoink.tx.TxBuilder$InputAndPrivateKey */
    /* compiled from: TxBuilder.kt */
    private static final class InputAndPrivateKey {
        @NotNull
        private final TransactionInput input;
        @NotNull
        private final PrivateKey privateKey;

        public InputAndPrivateKey(@NotNull TransactionInput transactionInput, @NotNull PrivateKey privateKey2) {
            Intrinsics.checkParameterIsNotNull(transactionInput, "input");
            Intrinsics.checkParameterIsNotNull(privateKey2, "privateKey");
            this.input = transactionInput;
            this.privateKey = privateKey2;
        }

        @NotNull
        public final TransactionInput getInput() {
            return this.input;
        }

        @NotNull
        public final PrivateKey getPrivateKey() {
            return this.privateKey;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    /* renamed from: com.bitcoin.bitcoink.tx.TxBuilder$WhenMappings */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[BitcoinFork.values().length];

        static {
            $EnumSwitchMapping$0[BitcoinFork.BTC.ordinal()] = 1;
            $EnumSwitchMapping$0[BitcoinFork.BCH.ordinal()] = 2;
        }
    }

    public TxBuilder(@NotNull BitcoinFork bitcoinFork) {
        Intrinsics.checkParameterIsNotNull(bitcoinFork, "fork");
        this.fork = bitcoinFork;
        this.f98tx.setVersion(2);
    }

    @NotNull
    public final String txDebugString() {
        String transaction = this.f98tx.toString();
        Intrinsics.checkExpressionValueIsNotNull(transaction, "tx.toString()");
        return transaction;
    }

    @NotNull
    public final byte[] serialize() {
        for (InputAndPrivateKey input : this.inputs) {
            this.f98tx.addInput(input.getInput());
        }
        int i = 0;
        for (Object next : this.inputs) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            InputAndPrivateKey inputAndPrivateKey = (InputAndPrivateKey) next;
            TransactionInput input2 = this.f98tx.getInput((long) i);
            Intrinsics.checkExpressionValueIsNotNull(input2, "tx.getInput(index.toLong())");
            input2.setScriptSig(inputSignature(i, inputAndPrivateKey.getInput(), inputAndPrivateKey.getPrivateKey()));
            i = i2;
        }
        this.f98tx.verify();
        byte[] bitcoinSerialize = this.f98tx.bitcoinSerialize();
        Intrinsics.checkExpressionValueIsNotNull(bitcoinSerialize, "tx.bitcoinSerialize()");
        return bitcoinSerialize;
    }

    @NotNull
    public final TxBuilder addInput(@NotNull TxId txId, int i, @NotNull byte[] bArr, @NotNull Satoshis satoshis, @NotNull PrivateKey privateKey) {
        Intrinsics.checkParameterIsNotNull(txId, "txId");
        Intrinsics.checkParameterIsNotNull(bArr, "script");
        Intrinsics.checkParameterIsNotNull(satoshis, "satoshis");
        Intrinsics.checkParameterIsNotNull(privateKey, "privateKey");
        this.inputs.add(new InputAndPrivateKey(createInput(txId, i, bArr, satoshis), privateKey));
        return this;
    }

    @NotNull
    public final TxOutput addOutput(@NotNull Satoshis satoshis, @NotNull Address address) {
        Intrinsics.checkParameterIsNotNull(satoshis, "satoshi");
        Intrinsics.checkParameterIsNotNull(address, "address");
        TransactionOutput addOutput = this.f98tx.addOutput(Coin.valueOf(satoshis.getSatoshis()), org.bitcoinj.core.Address.fromBase58(this.f98tx.getParams(), address.toBase58()));
        Intrinsics.checkExpressionValueIsNotNull(addOutput, "tx.addOutput(\n          …ess.toBase58())\n        )");
        byte[] scriptBytes = addOutput.getScriptBytes();
        Intrinsics.checkExpressionValueIsNotNull(scriptBytes, "transactionOutput.scriptBytes");
        return new TxOutput(satoshis, address, scriptBytes);
    }

    @NotNull
    public final TxOutput addOutput(@NotNull Address address, @NotNull Script script) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(script, "script");
        TransactionOutput addOutput = this.f98tx.addOutput(Coin.ZERO, script);
        Intrinsics.checkExpressionValueIsNotNull(addOutput, "tx.addOutput(Coin.ZERO, script)");
        Satoshis zero = Satoshis.Companion.getZERO();
        byte[] scriptBytes = addOutput.getScriptBytes();
        Intrinsics.checkExpressionValueIsNotNull(scriptBytes, "transactionOutput.scriptBytes");
        return new TxOutput(zero, address, scriptBytes);
    }

    public final void addOutput(@NotNull Satoshis satoshis, @NotNull Script script) {
        Intrinsics.checkParameterIsNotNull(satoshis, "satoshis");
        Intrinsics.checkParameterIsNotNull(script, "script");
        this.f98tx.addOutput(Coin.valueOf(satoshis.getSatoshis()), script);
    }

    private final Script inputSignature(int i, TransactionInput transactionInput, PrivateKey privateKey) {
        TransactionSignature transactionSignature;
        Script scriptSig = transactionInput.getScriptSig();
        Intrinsics.checkExpressionValueIsNotNull(scriptSig, "input.scriptSig");
        byte[] bArr = ((ScriptChunk) scriptSig.getChunks().get(0)).data;
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.fork.ordinal()];
        if (i2 == 1) {
            transactionSignature = this.f98tx.calculateSignature(i, (ECKey) privateKey.getKey(), bArr, SigHash.ALL, false);
            Intrinsics.checkExpressionValueIsNotNull(transactionSignature, "tx.calculateSignature(\n …nyoneCanPay\n            )");
        } else if (i2 == 2) {
            transactionSignature = this.f98tx.calculateWitnessSignature(i, (ECKey) privateKey.getKey(), bArr, transactionInput.getValue(), SigHash.ALL, false);
            Intrinsics.checkExpressionValueIsNotNull(transactionSignature, "tx.calculateWitnessSigna…nyoneCanPay\n            )");
        } else {
            throw new NoWhenBranchMatchedException();
        }
        Script build = new ScriptBuilder().data(transactionSignature.encodeToBitcoin()).data(privateKey.getKey().getPubKeyPoint().getEncoded(privateKey.getKey().isCompressed())).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "ScriptBuilder()\n        …ed))\n            .build()");
        return build;
    }

    private final TransactionInput createInput(TxId txId, int i, byte[] bArr, Satoshis satoshis) {
        Script build = new ScriptBuilder().data(bArr).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "ScriptBuilder()\n        …ipt)\n            .build()");
        byte[] program = build.getProgram();
        Intrinsics.checkExpressionValueIsNotNull(program, "ScriptBuilder()\n        …         .build().program");
        NetworkParameters params = this.f98tx.getParams();
        Transaction transaction = this.f98tx;
        TransactionInput transactionInput = new TransactionInput(params, transaction, program, new TransactionOutPoint(transaction.getParams(), (long) i, Sha256Hash.wrap(txId.getId())), Coin.valueOf(satoshis.getSatoshis()));
        return transactionInput;
    }
}
