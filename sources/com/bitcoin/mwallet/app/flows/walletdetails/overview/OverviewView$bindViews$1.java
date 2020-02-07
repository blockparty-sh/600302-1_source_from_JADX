package com.bitcoin.mwallet.app.flows.walletdetails.overview;

import androidx.viewpager.widget.ViewPager;
import com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.HistoryAssetsPagerAdapter;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.google.android.material.tabs.TabLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.walletdetails.overview.OverviewView$bindViews$1", mo38000f = "OverviewView.kt", mo38001i = {}, mo38002l = {49}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: OverviewView.kt */
final class OverviewView$bindViews$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HistoryAssetsPagerAdapter $adapter;
    final /* synthetic */ TabLayout $tabLayout;
    final /* synthetic */ ViewPager $viewPager;
    final /* synthetic */ WalletId $walletId;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f324p$;
    final /* synthetic */ OverviewView this$0;

    OverviewView$bindViews$1(OverviewView overviewView, HistoryAssetsPagerAdapter historyAssetsPagerAdapter, WalletId walletId, ViewPager viewPager, TabLayout tabLayout, Continuation continuation) {
        this.this$0 = overviewView;
        this.$adapter = historyAssetsPagerAdapter;
        this.$walletId = walletId;
        this.$viewPager = viewPager;
        this.$tabLayout = tabLayout;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        OverviewView$bindViews$1 overviewView$bindViews$1 = new OverviewView$bindViews$1(this.this$0, this.$adapter, this.$walletId, this.$viewPager, this.$tabLayout, continuation);
        overviewView$bindViews$1.f324p$ = (CoroutineScope) obj;
        return overviewView$bindViews$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((OverviewView$bindViews$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        HistoryAssetsPagerAdapter historyAssetsPagerAdapter;
        Unit unit;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f324p$;
            this.$adapter.setWalletId(this.$walletId);
            HistoryAssetsPagerAdapter historyAssetsPagerAdapter2 = this.$adapter;
            OverviewPresenter overviewPresenter = (OverviewPresenter) this.this$0.getPresenter();
            this.L$0 = historyAssetsPagerAdapter2;
            this.label = 1;
            Object coin = overviewPresenter.getCoin(this);
            if (coin == coroutine_suspended) {
                return coroutine_suspended;
            }
            historyAssetsPagerAdapter = historyAssetsPagerAdapter2;
            obj = coin;
        } else if (i == 1) {
            historyAssetsPagerAdapter = (HistoryAssetsPagerAdapter) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        historyAssetsPagerAdapter.setCoin((Coin) obj);
        ViewPager viewPager = this.$viewPager;
        if (viewPager != null) {
            viewPager.setAdapter(this.$adapter);
        }
        ViewPager viewPager2 = this.$viewPager;
        if (viewPager2 != null) {
            viewPager2.setOffscreenPageLimit(2);
        }
        TabLayout tabLayout = this.$tabLayout;
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(this.$viewPager);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        return unit;
    }
}
