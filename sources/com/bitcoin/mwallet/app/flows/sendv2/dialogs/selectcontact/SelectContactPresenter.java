package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcontact;

import android.content.Context;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0002\u0010\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactRouter;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcontact/SelectContactRouter;)V", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectContactPresenter.kt */
public final class SelectContactPresenter extends ScreenPresenter<SelectContactRouter> {
    public SelectContactPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull SelectContactRouter selectContactRouter) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(selectContactRouter, "router");
        super(context, selectContactRouter);
    }
}
