package com.bitcoin.bitcoink;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.crypto.MnemonicCode;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/bitcoink/PrivateKey;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Mnemonic.kt */
final class Mnemonic$masterKey$2 extends Lambda implements Function0<PrivateKey> {
    final /* synthetic */ Mnemonic this$0;

    Mnemonic$masterKey$2(Mnemonic mnemonic) {
        this.this$0 = mnemonic;
        super(0);
    }

    @NotNull
    public final PrivateKey invoke() {
        DeterministicKey createMasterPrivateKey = HDKeyDerivation.createMasterPrivateKey(MnemonicCode.toSeed(this.this$0.normalizedMnemonic, ""));
        Intrinsics.checkExpressionValueIsNotNull(createMasterPrivateKey, "HDKeyDerivation.createMa…(normalizedMnemonic, \"\"))");
        return new PrivateKey(createMasterPrivateKey);
    }
}
