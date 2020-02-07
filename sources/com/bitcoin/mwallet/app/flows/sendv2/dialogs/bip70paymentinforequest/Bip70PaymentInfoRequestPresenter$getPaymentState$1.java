package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import com.bitcoin.mwallet.Bip70PaymentErrorStatus;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter.PaymentState;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\n¢\u0006\u0004\b\t\u0010\n"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestPresenter$PaymentState;", "_dataLoaded", "", "_errorState", "Lcom/bitcoin/mwallet/Bip70PaymentErrorStatus;", "_broadcastSuccess", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "invoke", "(Ljava/lang/Boolean;Lcom/bitcoin/mwallet/Bip70PaymentErrorStatus;Ljava/lang/Boolean;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/bip70paymentinforequest/Bip70PaymentInfoRequestPresenter$PaymentState;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentInfoRequestPresenter.kt */
final class Bip70PaymentInfoRequestPresenter$getPaymentState$1 extends Lambda implements Function4<Boolean, Bip70PaymentErrorStatus, Boolean, WalletId, PaymentState> {
    public static final Bip70PaymentInfoRequestPresenter$getPaymentState$1 INSTANCE = new Bip70PaymentInfoRequestPresenter$getPaymentState$1();

    Bip70PaymentInfoRequestPresenter$getPaymentState$1() {
        super(4);
    }

    @NotNull
    public final PaymentState invoke(@Nullable Boolean bool, @Nullable Bip70PaymentErrorStatus bip70PaymentErrorStatus, @Nullable Boolean bool2, @Nullable WalletId walletId) {
        if (walletId == null) {
            return PaymentState.NO_WALLET;
        }
        if (Intrinsics.areEqual((Object) bool, (Object) Boolean.valueOf(false))) {
            return PaymentState.LOADING;
        }
        if (bip70PaymentErrorStatus == Bip70PaymentErrorStatus.EXPIRED_PAYMENT) {
            return PaymentState.EXPIRED;
        }
        if (bip70PaymentErrorStatus != null) {
            return PaymentState.INVALID;
        }
        if (Intrinsics.areEqual((Object) bool2, (Object) Boolean.valueOf(false))) {
            return PaymentState.BROADCAST_FAILED;
        }
        return PaymentState.PAYMENT;
    }
}
