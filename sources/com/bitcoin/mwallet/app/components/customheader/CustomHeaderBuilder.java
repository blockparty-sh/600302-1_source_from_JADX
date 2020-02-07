package com.bitcoin.mwallet.app.components.customheader;

import android.app.Application;
import androidx.lifecycle.ViewModelKt;
import com.bitcoin.mwallet.app.viper.ComponentBuilderBase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/customheader/CustomHeaderBuilder;", "Lcom/bitcoin/mwallet/app/viper/ComponentBuilderBase;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "presenter", "Lcom/bitcoin/mwallet/app/components/customheader/CustomHeaderPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/customheader/CustomHeaderPresenter;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CustomHeaderBuilder.kt */
public final class CustomHeaderBuilder extends ComponentBuilderBase {
    @NotNull
    private final CustomHeaderPresenter presenter;

    public CustomHeaderBuilder(@NotNull Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        super(application);
        this.presenter = new CustomHeaderPresenter(application, ViewModelKt.getViewModelScope(this));
    }

    @NotNull
    public CustomHeaderPresenter getPresenter() {
        return this.presenter;
    }
}
