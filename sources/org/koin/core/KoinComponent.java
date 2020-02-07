package org.koin.core;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.koin.core.context.GlobalContext;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, mo37405d2 = {"Lorg/koin/core/KoinComponent;", "", "getKoin", "Lorg/koin/core/Koin;", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: KoinComponent.kt */
public interface KoinComponent {

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    /* compiled from: KoinComponent.kt */
    public static final class DefaultImpls {
        @NotNull
        public static Koin getKoin(KoinComponent koinComponent) {
            return GlobalContext.get().getKoin();
        }
    }

    @NotNull
    Koin getKoin();
}
