package com.bitcoin.mwallet.app.components.walletselector;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.views.walletsummary.WalletSummaryView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Lcom/bitcoin/mwallet/core/entity/WalletType;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletSelectorDialogView.kt */
final class WalletSelectorDialogView$bindObservers$1<T> implements Observer<WalletType> {
    final /* synthetic */ WalletSelectorPresenter $presenter;
    final /* synthetic */ WalletSelectorDialogView this$0;

    WalletSelectorDialogView$bindObservers$1(WalletSelectorDialogView walletSelectorDialogView, WalletSelectorPresenter walletSelectorPresenter) {
        this.this$0 = walletSelectorDialogView;
        this.$presenter = walletSelectorPresenter;
    }

    public final void onChanged(WalletType walletType) {
        WalletSelectorPresenter walletSelectorPresenter = this.$presenter;
        Intrinsics.checkExpressionValueIsNotNull(walletType, "it");
        LiveData walletSummary = walletSelectorPresenter.getWalletSummary(walletType);
        if (this.$presenter.getCurrentWalletSummaries() != null) {
            MediatorLiveData walletSummary2 = this.$presenter.getWalletSummary();
            LiveData currentWalletSummaries = this.$presenter.getCurrentWalletSummaries();
            if (currentWalletSummaries == null) {
                Intrinsics.throwNpe();
            }
            walletSummary2.removeSource(currentWalletSummaries);
        }
        this.$presenter.getWalletSummary().addSource(walletSummary, new Observer<S>(this) {
            final /* synthetic */ WalletSelectorDialogView$bindObservers$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void onChanged(List<WalletSummaryView> list) {
                Intrinsics.checkExpressionValueIsNotNull(list, "summaries");
                Iterable<WalletSummaryView> iterable = list;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (WalletSummaryView walletSummaryView : iterable) {
                    walletSummaryView.setWalletType((WalletType) this.this$0.$presenter.getSelectedCoin().getValue());
                    if (this.this$0.this$0.getTokenId() != null) {
                        walletSummaryView.setSlpInfo(this.this$0.$presenter.getToken(walletSummaryView.getWalletId(), this.this$0.this$0.getTokenId()));
                        walletSummaryView.setTokenBalance(this.this$0.$presenter.getTokenBalance(walletSummaryView.getWalletId(), this.this$0.this$0.getTokenId()));
                    }
                    arrayList.add(walletSummaryView);
                }
                List list2 = (List) arrayList;
                if (this.this$0.this$0.getTokenId() != null) {
                    Iterable iterable2 = list2;
                    Collection arrayList2 = new ArrayList();
                    for (Object next : iterable2) {
                        Slp slpInfo = ((WalletSummaryView) next).getSlpInfo();
                        if (Intrinsics.areEqual((Object) slpInfo != null ? slpInfo.getTokenId() : null, (Object) this.this$0.this$0.getTokenId())) {
                            arrayList2.add(next);
                        }
                    }
                    list2 = (List) arrayList2;
                }
                this.this$0.$presenter.getShowEmptyLayout().postValue(Boolean.valueOf(list.isEmpty()));
                this.this$0.$presenter.getWalletSummary().setValue(list2);
            }
        });
        this.$presenter.setCurrentWalletSummaries(walletSummary);
    }
}
