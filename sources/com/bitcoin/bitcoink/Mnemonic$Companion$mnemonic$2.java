package com.bitcoin.bitcoink;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.bitcoinj.crypto.MnemonicCode;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "Lorg/bitcoinj/crypto/MnemonicCode;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Mnemonic.kt */
final class Mnemonic$Companion$mnemonic$2 extends Lambda implements Function0<MnemonicCode> {
    public static final Mnemonic$Companion$mnemonic$2 INSTANCE = new Mnemonic$Companion$mnemonic$2();

    Mnemonic$Companion$mnemonic$2() {
        super(0);
    }

    @NotNull
    public final MnemonicCode invoke() {
        return new MnemonicCode();
    }
}
