package com.bitcoin.bitcoink;

import java.security.SecureRandom;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "Ljava/security/SecureRandom;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Mnemonic.kt */
final class Mnemonic$Companion$random$2 extends Lambda implements Function0<SecureRandom> {
    public static final Mnemonic$Companion$random$2 INSTANCE = new Mnemonic$Companion$random$2();

    Mnemonic$Companion$random$2() {
        super(0);
    }

    @NotNull
    public final SecureRandom invoke() {
        return new SecureRandom();
    }
}
