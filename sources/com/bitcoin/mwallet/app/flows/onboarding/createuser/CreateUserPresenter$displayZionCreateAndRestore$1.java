package com.bitcoin.mwallet.app.flows.onboarding.createuser;

import androidx.arch.core.util.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0003*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "kotlin.jvm.PlatformType", "apply", "(Ljava/lang/Boolean;)Z"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: CreateUserPresenter.kt */
final class CreateUserPresenter$displayZionCreateAndRestore$1<I, O> implements Function<X, Y> {
    final /* synthetic */ CreateUserPresenter this$0;

    CreateUserPresenter$displayZionCreateAndRestore$1(CreateUserPresenter createUserPresenter) {
        this.this$0 = createUserPresenter;
    }

    public /* bridge */ /* synthetic */ Object apply(Object obj) {
        return Boolean.valueOf(apply((Boolean) obj));
    }

    public final boolean apply(Boolean bool) {
        Intrinsics.checkExpressionValueIsNotNull(bool, "it");
        return bool.booleanValue() && this.this$0.hasZion;
    }
}
