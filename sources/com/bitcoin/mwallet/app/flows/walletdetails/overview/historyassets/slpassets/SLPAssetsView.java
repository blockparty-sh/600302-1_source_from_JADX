package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ComponentView;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u000e\u001a\u00020\fJ\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u000e\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014R\u001a\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetsView;", "Lcom/bitcoin/mwallet/app/viper/ComponentView;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetsBuilder;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetsPresenter;", "()V", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "setWalletId", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;)V", "bindActions", "", "bindObservers", "initVerifiedTokenFilterLayout", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "setEmptyLayout", "hasTokens", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SLPAssetsView.kt */
public final class SLPAssetsView extends ComponentView<SLPAssetsBuilder, SLPAssetsPresenter> {
    private HashMap _$_findViewCache;
    @NotNull
    public WalletId walletId;

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

    public static final /* synthetic */ SLPAssetsPresenter access$getPresenter$p(SLPAssetsView sLPAssetsView) {
        return (SLPAssetsPresenter) sLPAssetsView.getPresenter();
    }

    public SLPAssetsView() {
        super(C1018R.layout.fragment_component_chain_assets, Reflection.getOrCreateKotlinClass(SLPAssetsBuilder.class));
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
        SLPAssetsPresenter sLPAssetsPresenter = (SLPAssetsPresenter) getPresenter();
        WalletId walletId2 = this.walletId;
        String str = "walletId";
        if (walletId2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        sLPAssetsPresenter.setWalletId(walletId2);
        SLPAssetsPresenter sLPAssetsPresenter2 = (SLPAssetsPresenter) getPresenter();
        WalletId walletId3 = this.walletId;
        if (walletId3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str);
        }
        sLPAssetsPresenter2.initWallet(walletId3);
        bindObservers();
        bindActions();
        initVerifiedTokenFilterLayout();
    }

    public final void setEmptyLayout(boolean z) {
        if (!z) {
            View view = getView();
            if (view != null) {
                LinearLayout linearLayout = (LinearLayout) view.findViewById(C1018R.C1021id.assetsEmptyView);
                if (linearLayout != null) {
                    linearLayout.setVisibility(0);
                }
            }
            View view2 = getView();
            if (view2 != null) {
                RecyclerView recyclerView = (RecyclerView) view2.findViewById(C1018R.C1021id.slpAssetList);
                if (recyclerView != null) {
                    recyclerView.setVisibility(8);
                    return;
                }
                return;
            }
            return;
        }
        View view3 = getView();
        if (view3 != null) {
            LinearLayout linearLayout2 = (LinearLayout) view3.findViewById(C1018R.C1021id.assetsEmptyView);
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(8);
            }
        }
        View view4 = getView();
        if (view4 != null) {
            RecyclerView recyclerView2 = (RecyclerView) view4.findViewById(C1018R.C1021id.slpAssetList);
            if (recyclerView2 != null) {
                recyclerView2.setVisibility(0);
            }
        }
    }

    public final void initVerifiedTokenFilterLayout() {
        String str;
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.walletTableHeaderTextView);
            if (textView != null) {
                View view2 = getView();
                if (view2 != null) {
                    Context context = view2.getContext();
                    if (context != null) {
                        Resources resources = context.getResources();
                        if (resources != null) {
                            str = resources.getString(C1018R.string.settings_verified_tokens_only);
                            textView.setText(str);
                        }
                    }
                }
                str = null;
                textView.setText(str);
            }
        }
        View view3 = getView();
        if (view3 != null) {
            Switch switchR = (Switch) view3.findViewById(C1018R.C1021id.walletTableHeaderSwitch);
            if (switchR != null) {
                C1261Wallet wallet = ((SLPAssetsPresenter) getPresenter()).getWallet();
                if (wallet == null) {
                    Intrinsics.throwNpe();
                }
                switchR.setChecked(!wallet.preference().getShowAllTokens());
            }
        }
        View view4 = getView();
        if (view4 != null) {
            Switch switchR2 = (Switch) view4.findViewById(C1018R.C1021id.walletTableHeaderSwitch);
            if (switchR2 != null) {
                switchR2.setOnCheckedChangeListener(new SLPAssetsView$initVerifiedTokenFilterLayout$1(this));
            }
        }
    }

    public final void bindActions() {
        View view = getView();
        if (view != null) {
            TextView textView = (TextView) view.findViewById(C1018R.C1021id.learnMoreSLPTextView);
            if (textView != null) {
                textView.setOnClickListener(new SLPAssetsView$bindActions$1(this));
            }
        }
    }

    public final void bindObservers() {
        Context context = getContext();
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
        SLPAssetsAdapter sLPAssetsAdapter = new SLPAssetsAdapter(context, childFragmentManager, ((SLPAssetsPresenter) getPresenter()).getWalletId(), ((SLPAssetsPresenter) getPresenter()).getVerifiedTokenInteractor());
        View view = getView();
        if (view != null) {
            RecyclerView recyclerView = (RecyclerView) view.findViewById(C1018R.C1021id.slpAssetList);
            if (recyclerView != null) {
                recyclerView.setAdapter(sLPAssetsAdapter);
            }
        }
        ((SLPAssetsPresenter) getPresenter()).getTokens().observe(getViewLifecycleOwner(), new SLPAssetsView$bindObservers$1(this, sLPAssetsAdapter));
    }
}
