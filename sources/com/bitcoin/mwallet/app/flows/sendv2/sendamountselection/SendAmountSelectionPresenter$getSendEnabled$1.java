package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "", "hasEnoughBalance", "error", "", "invoke", "(Ljava/lang/Boolean;Ljava/lang/String;)Z"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionPresenter.kt */
final class SendAmountSelectionPresenter$getSendEnabled$1 extends Lambda implements Function2<Boolean, String, Boolean> {
    public static final SendAmountSelectionPresenter$getSendEnabled$1 INSTANCE = new SendAmountSelectionPresenter$getSendEnabled$1();

    SendAmountSelectionPresenter$getSendEnabled$1() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return Boolean.valueOf(invoke((Boolean) obj, (String) obj2));
    }

    public final boolean invoke(@Nullable Boolean bool, @Nullable String str) {
        if (str == null || bool == null || !bool.booleanValue()) {
            return false;
        }
        return str.length() == 0;
    }
}
