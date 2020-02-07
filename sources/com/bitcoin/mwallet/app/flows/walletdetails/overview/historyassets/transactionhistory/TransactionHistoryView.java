package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ComponentView;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.views.transaction.TransactionClickedListener;
import com.bitcoin.mwallet.core.views.transaction.TransactionSummaryListAdapter;
import java.text.MessageFormat;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.IntRef;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0010"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/TransactionHistoryView;", "Lcom/bitcoin/mwallet/app/viper/ComponentView;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/TransactionHistoryBuilder;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/TransactionHistoryPresenter;", "()V", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "initBchFilterLayout", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TransactionHistoryView.kt */
public final class TransactionHistoryView extends ComponentView<TransactionHistoryBuilder, TransactionHistoryPresenter> {
    private HashMap _$_findViewCache;
    @NotNull
    public WalletId walletId;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 1;
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ TransactionHistoryPresenter access$getPresenter$p(TransactionHistoryView transactionHistoryView) {
        return (TransactionHistoryPresenter) transactionHistoryView.getPresenter();
    }

    public TransactionHistoryView() {
        super(C1018R.layout.fragment_component_transaction_history, Reflection.getOrCreateKotlinClass(TransactionHistoryBuilder.class));
    }

    @NotNull
    public final WalletId getWalletId() {
        WalletId walletId2 = this.walletId;
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("walletId");
        }
        return walletId2;
    }

    public final void setWalletId(@NotNull WalletId walletId2) {
        Intrinsics.checkParameterIsNotNull(walletId2, "<set-?>");
        this.walletId = walletId2;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        TransactionSummaryListAdapter transactionSummaryListAdapter = new TransactionSummaryListAdapter((TransactionHistoryPresenter) getPresenter());
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C1018R.C1021id.txList);
        String str = "txList";
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, str);
        recyclerView.setAdapter(transactionSummaryListAdapter);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(C1018R.C1021id.txList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, str);
        RecyclerView recyclerView3 = null;
        recyclerView2.setItemAnimator(null);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), 1);
        Drawable drawable = ContextCompat.getDrawable(requireContext(), C1018R.C1020drawable.background_basic_divider);
        if (drawable == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(drawable, "ContextCompat.getDrawabl…ckground_basic_divider)!!");
        int dimensionPixelSize = getResources().getDimensionPixelSize(C1018R.dimen.divider_margin);
        InsetDrawable insetDrawable = new InsetDrawable(drawable, dimensionPixelSize, 0, dimensionPixelSize, 0);
        IntRef intRef = new IntRef();
        intRef.element = 0;
        dividerItemDecoration.setDrawable(insetDrawable);
        ((RecyclerView) _$_findCachedViewById(C1018R.C1021id.txList)).addItemDecoration(dividerItemDecoration);
        TransactionHistoryPresenter transactionHistoryPresenter = (TransactionHistoryPresenter) getPresenter();
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            transactionHistoryPresenter.setListener((TransactionClickedListener) parentFragment);
            View view = getView();
            if (view != null) {
                recyclerView3 = (RecyclerView) view.findViewById(C1018R.C1021id.txList);
            }
            View view2 = getView();
            if (view2 != null) {
                TextView textView = (TextView) view2.findViewById(C1018R.C1021id.firstOptionTextView);
                if (textView != null) {
                    textView.setText(MessageFormat.format(getResources().getString(C1018R.string.tx_history_empty_option_first), new Object[]{getResources().getString(C1018R.string.general_bitcoin_cash)}));
                }
            }
            ((TransactionHistoryPresenter) getPresenter()).getAsset().observe(getViewLifecycleOwner(), new TransactionHistoryView$onActivityCreated$2(this));
            View view3 = getView();
            if (view3 != null) {
                LinearLayout linearLayout = (LinearLayout) view3.findViewById(C1018R.C1021id.historyEmptyView);
                if (linearLayout != null) {
                    linearLayout.setOnClickListener(new TransactionHistoryView$onActivityCreated$3(this));
                }
            }
            TransactionHistoryPresenter transactionHistoryPresenter2 = (TransactionHistoryPresenter) getPresenter();
            WalletId walletId2 = this.walletId;
            if (walletId2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("walletId");
            }
            transactionHistoryPresenter2.initWallet(walletId2);
            initBchFilterLayout();
            ((TransactionHistoryPresenter) getPresenter()).transactionSummaries().observe(getViewLifecycleOwner(), new TransactionHistoryView$onActivityCreated$4(this, recyclerView3, transactionSummaryListAdapter, intRef));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.core.views.transaction.TransactionClickedListener");
    }

    public final void initBchFilterLayout() {
        C1261Wallet wallet = ((TransactionHistoryPresenter) getPresenter()).getWallet();
        String str = null;
        if ((wallet != null ? wallet.getCoin() : null) == Coin.BCH) {
            View view = getView();
            if (view != null) {
                LinearLayout linearLayout = (LinearLayout) view.findViewById(C1018R.C1021id.bchFilterLayout);
                if (linearLayout != null) {
                    linearLayout.setVisibility(0);
                }
            }
            View view2 = getView();
            if (view2 != null) {
                TextView textView = (TextView) view2.findViewById(C1018R.C1021id.walletTableHeaderTextView);
                if (textView != null) {
                    View view3 = getView();
                    if (view3 != null) {
                        Context context = view3.getContext();
                        if (context != null) {
                            Resources resources = context.getResources();
                            if (resources != null) {
                                str = resources.getString(C1018R.string.settings_bch_tx_only);
                            }
                        }
                    }
                    textView.setText(str);
                }
            }
            View view4 = getView();
            if (view4 != null) {
                Switch switchR = (Switch) view4.findViewById(C1018R.C1021id.walletTableHeaderSwitch);
                if (switchR != null) {
                    C1261Wallet wallet2 = ((TransactionHistoryPresenter) getPresenter()).getWallet();
                    if (wallet2 == null) {
                        Intrinsics.throwNpe();
                    }
                    switchR.setChecked(!wallet2.preference().getShowSlpTransaction());
                }
            }
            View view5 = getView();
            if (view5 != null) {
                Switch switchR2 = (Switch) view5.findViewById(C1018R.C1021id.walletTableHeaderSwitch);
                if (switchR2 != null) {
                    switchR2.setOnCheckedChangeListener(new TransactionHistoryView$initBchFilterLayout$1(this));
                }
            }
        }
    }
}
