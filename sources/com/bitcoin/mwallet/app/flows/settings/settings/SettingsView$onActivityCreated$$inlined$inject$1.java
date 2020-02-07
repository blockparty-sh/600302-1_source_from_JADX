package com.bitcoin.mwallet.app.flows.settings.settings;

import android.content.ComponentCallbacks;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.koin.core.qualifier.Qualifier;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, mo37405d2 = {"<anonymous>", "T", "", "invoke", "()Ljava/lang/Object;", "org/koin/android/ext/android/ComponentCallbackExtKt$inject$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ComponentCallbackExt.kt */
public final class SettingsView$onActivityCreated$$inlined$inject$1 extends Lambda implements Function0<AnalyticsService> {
    final /* synthetic */ Function0 $parameters;
    final /* synthetic */ Qualifier $qualifier;
    final /* synthetic */ ComponentCallbacks $this_inject;

    public SettingsView$onActivityCreated$$inlined$inject$1(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0 function0) {
        this.$this_inject = componentCallbacks;
        this.$qualifier = qualifier;
        this.$parameters = function0;
        super(0);
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [com.bitcoin.mwallet.core.services.AnalyticsService, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v3, types: [com.bitcoin.mwallet.core.services.AnalyticsService, java.lang.Object]
      assigns: [java.lang.Object]
      uses: [com.bitcoin.mwallet.core.services.AnalyticsService]
      mth insns count: 9
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.bitcoin.mwallet.core.services.AnalyticsService invoke() {
        /*
            r4 = this;
            android.content.ComponentCallbacks r0 = r4.$this_inject
            org.koin.core.qualifier.Qualifier r1 = r4.$qualifier
            kotlin.jvm.functions.Function0 r2 = r4.$parameters
            org.koin.core.Koin r0 = org.koin.android.ext.android.ComponentCallbackExtKt.getKoin(r0)
            org.koin.core.scope.Scope r0 = r0.getRootScope()
            java.lang.Class<com.bitcoin.mwallet.core.services.AnalyticsService> r3 = com.bitcoin.mwallet.core.services.AnalyticsService.class
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            java.lang.Object r0 = r0.get(r3, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.settings.settings.SettingsView$onActivityCreated$$inlined$inject$1.invoke():java.lang.Object");
    }
}
