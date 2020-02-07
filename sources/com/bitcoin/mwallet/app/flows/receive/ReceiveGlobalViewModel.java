package com.bitcoin.mwallet.app.flows.receive;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.bitcoin.mwallet.app.flows.receive.receive.entity.ReceiveAmount;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0005R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/ReceiveGlobalViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "amount", "Landroidx/lifecycle/MutableLiveData;", "Lcom/bitcoin/mwallet/app/flows/receive/receive/entity/ReceiveAmount;", "getAmount", "()Landroidx/lifecycle/MutableLiveData;", "setAmount", "(Landroidx/lifecycle/MutableLiveData;)V", "setAmountValue", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveGlobalViewModel.kt */
public final class ReceiveGlobalViewModel extends ViewModel {
    @NotNull
    private MutableLiveData<ReceiveAmount> amount = new MutableLiveData<>();

    @NotNull
    public final MutableLiveData<ReceiveAmount> getAmount() {
        return this.amount;
    }

    public final void setAmount(@NotNull MutableLiveData<ReceiveAmount> mutableLiveData) {
        Intrinsics.checkParameterIsNotNull(mutableLiveData, "<set-?>");
        this.amount = mutableLiveData;
    }

    public final void setAmountValue(@Nullable ReceiveAmount receiveAmount) {
        this.amount.setValue(receiveAmount);
    }
}
