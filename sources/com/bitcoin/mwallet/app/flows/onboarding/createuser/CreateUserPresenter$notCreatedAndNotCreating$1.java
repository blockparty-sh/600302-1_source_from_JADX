package com.bitcoin.mwallet.app.flows.onboarding.createuser;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, mo37405d2 = {"<anonymous>", "", "creating", "searching", "invoke", "(Ljava/lang/Boolean;Ljava/lang/Boolean;)Z"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: CreateUserPresenter.kt */
final class CreateUserPresenter$notCreatedAndNotCreating$1 extends Lambda implements Function2<Boolean, Boolean, Boolean> {
    public static final CreateUserPresenter$notCreatedAndNotCreating$1 INSTANCE = new CreateUserPresenter$notCreatedAndNotCreating$1();

    CreateUserPresenter$notCreatedAndNotCreating$1() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return Boolean.valueOf(invoke((Boolean) obj, (Boolean) obj2));
    }

    public final boolean invoke(@Nullable Boolean bool, @Nullable Boolean bool2) {
        if (bool == null || bool2 == null) {
            return true;
        }
        return !bool.booleanValue() && !bool2.booleanValue();
    }
}
