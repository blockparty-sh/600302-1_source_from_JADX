package com.squareup.moshi.kotlin.reflect;

import java.lang.annotation.Annotation;
import kotlin.Metadata;
import kotlin.TypeCastException;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo37405d2 = {"ABSENT_VALUE", "", "KOTLIN_METADATA", "Ljava/lang/Class;", "", "moshi-kotlin"}, mo37406k = 2, mo37407mv = {1, 1, 11})
/* compiled from: KotlinJsonAdapter.kt */
public final class KotlinJsonAdapterKt {
    /* access modifiers changed from: private */
    public static final Object ABSENT_VALUE = new Object();
    /* access modifiers changed from: private */
    public static final Class<? extends Annotation> KOTLIN_METADATA;

    static {
        Class<? extends Annotation> cls = Class.forName("kotlin.Metadata");
        if (cls != null) {
            KOTLIN_METADATA = cls;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<out kotlin.Annotation>");
    }
}
