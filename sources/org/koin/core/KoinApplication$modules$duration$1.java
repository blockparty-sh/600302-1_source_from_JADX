package org.koin.core;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: KoinApplication.kt */
final class KoinApplication$modules$duration$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Iterable $modules;
    final /* synthetic */ KoinApplication this$0;

    KoinApplication$modules$duration$1(KoinApplication koinApplication, Iterable iterable) {
        this.this$0 = koinApplication;
        this.$modules = iterable;
        super(0);
    }

    public final void invoke() {
        this.this$0.loadModulesAndScopes(this.$modules);
    }
}
