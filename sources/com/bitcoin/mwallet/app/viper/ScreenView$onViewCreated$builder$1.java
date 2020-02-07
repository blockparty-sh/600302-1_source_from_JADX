package com.bitcoin.mwallet.app.viper;

import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\u0010\b\u0001\u0010\u0003*\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, mo37405d2 = {"<anonymous>", "BUILDER", "Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;", "PRESENTER", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "invoke", "()Lcom/bitcoin/mwallet/app/viper/ScreenBuilderBase;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ScreenView.kt */
final class ScreenView$onViewCreated$builder$1 extends Lambda implements Function0<BUILDER> {
    final /* synthetic */ ViewModelProvider $viewModelProvider;
    final /* synthetic */ ScreenView this$0;

    ScreenView$onViewCreated$builder$1(ScreenView screenView, ViewModelProvider viewModelProvider) {
        this.this$0 = screenView;
        this.$viewModelProvider = viewModelProvider;
        super(0);
    }

    @NotNull
    public final BUILDER invoke() {
        BUILDER builder = this.$viewModelProvider.get(JvmClassMappingKt.getJavaClass(this.this$0.builderClass));
        Intrinsics.checkExpressionValueIsNotNull(builder, "viewModelProvider.get(builderClass.java)");
        return (ScreenBuilderBase) builder;
    }
}
