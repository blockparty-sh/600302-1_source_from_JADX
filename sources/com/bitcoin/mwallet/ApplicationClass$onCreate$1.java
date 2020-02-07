package com.bitcoin.mwallet;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.koin.android.ext.koin.KoinExtKt;
import org.koin.core.KoinApplication;
import org.koin.core.context.GlobalContextKt;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "Lorg/koin/core/KoinApplication;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ApplicationClass.kt */
final class ApplicationClass$onCreate$1 extends Lambda implements Function0<KoinApplication> {
    final /* synthetic */ ApplicationClass this$0;

    ApplicationClass$onCreate$1(ApplicationClass applicationClass) {
        this.this$0 = applicationClass;
        super(0);
    }

    @NotNull
    public final KoinApplication invoke() {
        return GlobalContextKt.startKoin(new Function1<KoinApplication, Unit>(this) {
            final /* synthetic */ ApplicationClass$onCreate$1 this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((KoinApplication) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull KoinApplication koinApplication) {
                Intrinsics.checkParameterIsNotNull(koinApplication, "$receiver");
                KoinExtKt.androidContext(koinApplication, this.this$0.this$0);
                koinApplication.modules(KoinConfig.INSTANCE.getDatabaseModule(), KoinConfig.INSTANCE.getGrpcModule(), KoinConfig.INSTANCE.getAnalyticsModule(), KoinConfig.INSTANCE.getGlobalModule(), KoinConfig.INSTANCE.getRepositoryModule(), KoinConfig.INSTANCE.getCoreInteractors(), KoinConfig.INSTANCE.getWorkManagerModule());
            }
        });
    }
}
