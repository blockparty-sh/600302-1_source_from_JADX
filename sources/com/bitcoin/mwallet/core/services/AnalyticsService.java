package com.bitcoin.mwallet.core.services;

import android.app.Application;
import com.bitcoin.mwallet.BuildConfig;
import com.bitcoin.mwallet.core.utils.MeasureKt;
import com.leanplum.Leanplum;
import com.leanplum.annotations.Parser;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "generalParameters", "", "", "track", "", "event", "specialParams", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AnalyticsService.kt */
public class AnalyticsService {
    private final Map<String, String> generalParameters = MapsKt.mapOf(TuplesKt.m309to("platform", "Android"));

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.core.services.AnalyticsService$1", mo38000f = "AnalyticsService.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.core.services.AnalyticsService$1 */
    /* compiled from: AnalyticsService.kt */
    static final class C12741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f426p$;

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C12741 r0 = new C12741(application, continuation);
            r0.f426p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C12741) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f426p$;
                MeasureKt.logDuration("Leanplum.start", new Function0<Unit>(this) {
                    final /* synthetic */ C12741 this$0;

                    {
                        this.this$0 = r1;
                    }

                    public final void invoke() {
                        Leanplum.start(application);
                    }
                });
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public AnalyticsService(@NotNull final Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        Leanplum.setApplicationContext(application);
        Parser.parseVariables(application);
        Leanplum.setAppIdForProductionMode(BuildConfig.LEANPLUM_APP_ID, BuildConfig.LEANPLUM_PROD_ACCESS_KEY);
        Leanplum.trackAllAppScreens();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C12741(null), 3, null);
    }

    public final void track(@NotNull String str, @NotNull Map<String, ? extends Object> map) {
        Intrinsics.checkParameterIsNotNull(str, "event");
        Intrinsics.checkParameterIsNotNull(map, "specialParams");
        Map linkedHashMap = new LinkedHashMap();
        for (Entry entry : this.generalParameters.entrySet()) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        for (Entry entry2 : map.entrySet()) {
            linkedHashMap.put(entry2.getKey(), entry2.getValue());
        }
        try {
            Leanplum.track(str, linkedHashMap);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Leanplum error: ");
            sb.append(e);
            Timber.m426d(sb.toString(), new Object[0]);
        }
    }
}
