package com.bitcoin.mwallet.app.android;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0017\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/android/LiveDataChangeListener;", "T", "", "onValueSet", "", "value", "(Ljava/lang/Object;)V", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LiveDataChangeListener.kt */
public interface LiveDataChangeListener<T> {
    void onValueSet(@Nullable T t);
}
