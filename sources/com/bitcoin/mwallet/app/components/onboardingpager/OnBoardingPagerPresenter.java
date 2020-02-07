package com.bitcoin.mwallet.app.components.onboardingpager;

import android.content.Context;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.PresenterBase;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/onboardingpager/OnBoardingPagerPresenter;", "Lcom/bitcoin/mwallet/app/viper/PresenterBase;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;)V", "onBoardElementList", "", "Lcom/bitcoin/mwallet/app/components/onboardingpager/OnBoardElement;", "getOnBoardElementList", "()Ljava/util/List;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: OnBoardingPagerPresenter.kt */
public final class OnBoardingPagerPresenter extends PresenterBase {
    @NotNull
    private final List<OnBoardElement> onBoardElementList = CollectionsKt.listOf(new OnBoardElement(C1018R.C1020drawable.onboard_wallet, C1018R.string.createuser_onboard_wallet), new OnBoardElement(C1018R.C1020drawable.onboard_success, C1018R.string.createuser_onboard_success), new OnBoardElement(C1018R.C1020drawable.onboard_mobiletick, C1018R.string.createuser_onboard_mobiletick), new OnBoardElement(C1018R.C1020drawable.onboard_stores, C1018R.string.createuser_onboard_stores), new OnBoardElement(C1018R.C1020drawable.onboard_explore, C1018R.string.createuser_onboard_explore));

    public OnBoardingPagerPresenter(@NotNull Context context, @NotNull CoroutineScope coroutineScope) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
    }

    @NotNull
    public final List<OnBoardElement> getOnBoardElementList() {
        return this.onBoardElementList;
    }
}
