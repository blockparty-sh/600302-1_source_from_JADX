package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.mwallet.core.services.p010tx.GetBip70PaymentResponse;
import com.bitcoin.mwallet.core.services.p010tx.TxService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/Bip70PaymentInteractor;", "", "txService", "Lcom/bitcoin/mwallet/core/services/tx/TxService;", "(Lcom/bitcoin/mwallet/core/services/tx/TxService;)V", "constructBip70Transaction", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBip70PaymentInfo", "Lcom/bitcoin/mwallet/core/services/tx/GetBip70PaymentResponse;", "url", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentInteractor.kt */
public final class Bip70PaymentInteractor {
    private final TxService txService;

    public Bip70PaymentInteractor(@NotNull TxService txService2) {
        Intrinsics.checkParameterIsNotNull(txService2, "txService");
        this.txService = txService2;
    }

    @Nullable
    public final Object getBip70PaymentInfo(@NotNull String str, @NotNull Continuation<? super GetBip70PaymentResponse> continuation) {
        return this.txService.getBip70PaymentInfo(str, continuation);
    }

    @Nullable
    public final Object constructBip70Transaction(@NotNull Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }
}
