package com.bitcoin.mwallet.app.components.walletassetsrecycler;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.CoinBalance;
import com.bitcoin.mwallet.core.models.credential.CredentialType;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.lijiankun24.shadowlayout.ShadowLayout;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u000245B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ \u0010(\u001a\u0014\u0012\u0004\u0012\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0 2\u0006\u0010)\u001a\u00020\u000bJ\b\u0010*\u001a\u00020\u000bH\u0016J\u0010\u0010+\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u000bH\u0016J\u0018\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u000bH\u0016J\u0018\u0010/\u001a\u00020\u00022\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u000bH\u0016J&\u00103\u001a\u00020-2\u001e\u0010\u001e\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0 0\u001fR\u0014\u0010\n\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000bXD¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR&\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR2\u0010\u001e\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\"0 0\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u00066"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "currency", "Ljava/util/Currency;", "listener", "Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerListener;", "presenter", "Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;", "(Ljava/util/Currency;Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerListener;Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;)V", "MULITWALLET_VIEW_TYPE", "", "getMULITWALLET_VIEW_TYPE", "()I", "SINGLEWALLET_VIEW_TYPE", "getSINGLEWALLET_VIEW_TYPE", "collapsedMap", "", "Lcom/bitcoin/mwallet/core/models/Coin;", "", "getCollapsedMap", "()Ljava/util/Map;", "setCollapsedMap", "(Ljava/util/Map;)V", "getCurrency", "()Ljava/util/Currency;", "getListener", "()Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerListener;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;", "wallets", "", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/CoinBalance;", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "getWallets", "()Ljava/util/List;", "setWallets", "(Ljava/util/List;)V", "getItem", "position", "getItemCount", "getItemViewType", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitNewData", "MultipleWalletsAssetsViewHolder", "SingleWalletAssetViewHolder", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletAssetsRecyclerAdapter.kt */
public final class WalletAssetsRecyclerAdapter extends Adapter<ViewHolder> {
    private final int MULITWALLET_VIEW_TYPE;
    private final int SINGLEWALLET_VIEW_TYPE;
    @NotNull
    private Map<Coin, Boolean> collapsedMap;
    @NotNull
    private final Currency currency;
    @NotNull
    private final WalletAssetsRecyclerListener listener;
    @NotNull
    private final WalletAssetsRecyclerPresenter presenter;
    @NotNull
    private List<Pair<CoinBalance, List<WalletInfoBalance>>> wallets = new ArrayList();

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001JB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ(\u0010<\u001a\u00020=2\u0018\u0010>\u001a\u0014\u0012\u0004\u0012\u00020@\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0A0?2\u0006\u0010C\u001a\u00020DJ\u000e\u0010E\u001a\u00020=2\u0006\u0010F\u001a\u00020GJ\u0006\u0010H\u001a\u00020=J\u0006\u0010I\u001a\u00020=R\u0019\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\"\u0010\u0014\u001a\n \u000f*\u0004\u0018\u00010\u00150\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u001a\u001a\n \u000f*\u0004\u0018\u00010\u00150\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u0019\u0010\u001d\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0011R\"\u0010\u001f\u001a\n \u000f*\u0004\u0018\u00010\u00150\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019R\"\u0010\"\u001a\n \u000f*\u0004\u0018\u00010#0#X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\"\u0010*\u001a\n \u000f*\u0004\u0018\u00010+0+X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0019\u00106\u001a\n \u000f*\u0004\u0018\u00010707¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;¨\u0006K"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerAdapter$MultipleWalletsAssetsViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "listener", "Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerListener;", "parentAdapter", "Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerAdapter;", "context", "Landroid/content/Context;", "presenter", "Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;", "(Landroid/view/View;Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerListener;Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerAdapter;Landroid/content/Context;Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;)V", "backupRequiredIndicator", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", "getBackupRequiredIndicator", "()Landroid/widget/ImageView;", "balanceHiddenImageView", "getBalanceHiddenImageView", "chainBalanceTextView", "Landroid/widget/TextView;", "getChainBalanceTextView", "()Landroid/widget/TextView;", "setChainBalanceTextView", "(Landroid/widget/TextView;)V", "chainFiatBalanceTextView", "getChainFiatBalanceTextView", "setChainFiatBalanceTextView", "chainLogoImageView", "getChainLogoImageView", "chainNameTextView", "getChainNameTextView", "setChainNameTextView", "componentFooter", "Landroid/widget/LinearLayout;", "getComponentFooter", "()Landroid/widget/LinearLayout;", "setComponentFooter", "(Landroid/widget/LinearLayout;)V", "getContext", "()Landroid/content/Context;", "headerContainer", "Lcom/lijiankun24/shadowlayout/ShadowLayout;", "getHeaderContainer", "()Lcom/lijiankun24/shadowlayout/ShadowLayout;", "setHeaderContainer", "(Lcom/lijiankun24/shadowlayout/ShadowLayout;)V", "getListener", "()Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerListener;", "getParentAdapter", "()Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerAdapter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "getView", "()Landroid/view/View;", "bind", "", "chainAndBalances", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/CoinBalance;", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "currency", "Ljava/util/Currency;", "collapseExpandDropdown", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "setCollapsedFooter", "setExpandedFooter", "WalletAssetsSingleAdapter", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletAssetsRecyclerAdapter.kt */
    public static final class MultipleWalletsAssetsViewHolder extends ViewHolder {
        private final ImageView backupRequiredIndicator = ((ImageView) this.view.findViewById(C1018R.C1021id.backupRequiredIndicator));
        private final ImageView balanceHiddenImageView = ((ImageView) this.view.findViewById(C1018R.C1021id.balanceHiddenImageView));
        private TextView chainBalanceTextView = ((TextView) this.view.findViewById(C1018R.C1021id.chainBalanceTextView));
        private TextView chainFiatBalanceTextView = ((TextView) this.view.findViewById(C1018R.C1021id.chainFiatBalanceTextView));
        private final ImageView chainLogoImageView = ((ImageView) this.view.findViewById(C1018R.C1021id.chainLogoImageView));
        private TextView chainNameTextView = ((TextView) this.view.findViewById(C1018R.C1021id.chainNameTextView));
        private LinearLayout componentFooter = ((LinearLayout) this.view.findViewById(C1018R.C1021id.componentFooter));
        @NotNull
        private final Context context;
        private ShadowLayout headerContainer = ((ShadowLayout) this.view.findViewById(C1018R.C1021id.headerCardShadow));
        @NotNull
        private final WalletAssetsRecyclerListener listener;
        @NotNull
        private final WalletAssetsRecyclerAdapter parentAdapter;
        @NotNull
        private final WalletAssetsRecyclerPresenter presenter;
        private final RecyclerView recyclerView = ((RecyclerView) this.view.findViewById(C1018R.C1021id.walletAssetSingleRecyclerView));
        @NotNull
        private final View view;

        @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001$B#\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\fJ\b\u0010\u001b\u001a\u00020\fH\u0016J\u0010\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\fH\u0016J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\fH\u0016J\u0018\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\fH\u0016R\u0014\u0010\u000b\u001a\u00020\fXD¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\fXD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\fXD¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006%"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerAdapter$MultipleWalletsAssetsViewHolder$WalletAssetsSingleAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "wallets", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "currency", "Ljava/util/Currency;", "presenter", "Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;", "(Ljava/util/List;Ljava/util/Currency;Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;)V", "FIRST", "", "getFIRST", "()I", "LAST", "getLAST", "OTHER", "getOTHER", "getCurrency", "()Ljava/util/Currency;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;", "getWallets", "()Ljava/util/List;", "getItem", "position", "getItemCount", "getItemViewType", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "WalletAssetsSingleViewHolder", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
        /* compiled from: WalletAssetsRecyclerAdapter.kt */
        public static final class WalletAssetsSingleAdapter extends Adapter<ViewHolder> {
            private final int FIRST;
            private final int LAST = 2;
            private final int OTHER = 1;
            @NotNull
            private final Currency currency;
            @NotNull
            private final WalletAssetsRecyclerPresenter presenter;
            @NotNull
            private final List<WalletInfoBalance> wallets;

            @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#R\u0019\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\f\u001a\n \t*\u0004\u0018\u00010\b0\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0019\u0010\u000e\u001a\n \t*\u0004\u0018\u00010\b0\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0014\u001a\n \t*\u0004\u0018\u00010\b0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000bR\u0019\u0010\u0016\u001a\n \t*\u0004\u0018\u00010\u00170\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u001a\u001a\n \t*\u0004\u0018\u00010\u00170\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0019\u0010\u001c\u001a\n \t*\u0004\u0018\u00010\u00170\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019¨\u0006$"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerAdapter$MultipleWalletsAssetsViewHolder$WalletAssetsSingleAdapter$WalletAssetsSingleViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "presenter", "Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;", "(Landroid/view/View;Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;)V", "backupRequiredIndicator", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", "getBackupRequiredIndicator", "()Landroid/widget/ImageView;", "balanceHiddenImageView", "getBalanceHiddenImageView", "lockedImageView", "getLockedImageView", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;", "getView", "()Landroid/view/View;", "walletColorDot", "getWalletColorDot", "walletCryptoBalanceTextView", "Landroid/widget/TextView;", "getWalletCryptoBalanceTextView", "()Landroid/widget/TextView;", "walletFiatBalanceTextView", "getWalletFiatBalanceTextView", "walletNameTextView", "getWalletNameTextView", "bind", "", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "currency", "Ljava/util/Currency;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
            /* compiled from: WalletAssetsRecyclerAdapter.kt */
            public static final class WalletAssetsSingleViewHolder extends ViewHolder {
                private final ImageView backupRequiredIndicator = ((ImageView) this.view.findViewById(C1018R.C1021id.backupRequiredIndicator));
                private final ImageView balanceHiddenImageView = ((ImageView) this.view.findViewById(C1018R.C1021id.balanceHiddenImageView));
                private final ImageView lockedImageView = ((ImageView) this.view.findViewById(C1018R.C1021id.lockedImageView));
                @NotNull
                private final WalletAssetsRecyclerPresenter presenter;
                @NotNull
                private final View view;
                private final ImageView walletColorDot = ((ImageView) this.view.findViewById(C1018R.C1021id.walletColorDot));
                private final TextView walletCryptoBalanceTextView = ((TextView) this.view.findViewById(C1018R.C1021id.coinBalanceTextView));
                private final TextView walletFiatBalanceTextView = ((TextView) this.view.findViewById(C1018R.C1021id.fiatBalanceTextView));
                private final TextView walletNameTextView = ((TextView) this.view.findViewById(C1018R.C1021id.walletNameTextView));

                public WalletAssetsSingleViewHolder(@NotNull View view2, @NotNull WalletAssetsRecyclerPresenter walletAssetsRecyclerPresenter) {
                    Intrinsics.checkParameterIsNotNull(view2, "view");
                    Intrinsics.checkParameterIsNotNull(walletAssetsRecyclerPresenter, "presenter");
                    super(view2);
                    this.view = view2;
                    this.presenter = walletAssetsRecyclerPresenter;
                }

                @NotNull
                public final WalletAssetsRecyclerPresenter getPresenter() {
                    return this.presenter;
                }

                @NotNull
                public final View getView() {
                    return this.view;
                }

                public final TextView getWalletNameTextView() {
                    return this.walletNameTextView;
                }

                public final TextView getWalletCryptoBalanceTextView() {
                    return this.walletCryptoBalanceTextView;
                }

                public final TextView getWalletFiatBalanceTextView() {
                    return this.walletFiatBalanceTextView;
                }

                public final ImageView getWalletColorDot() {
                    return this.walletColorDot;
                }

                public final ImageView getBalanceHiddenImageView() {
                    return this.balanceHiddenImageView;
                }

                public final ImageView getBackupRequiredIndicator() {
                    return this.backupRequiredIndicator;
                }

                public final ImageView getLockedImageView() {
                    return this.lockedImageView;
                }

                public final void bind(@NotNull WalletInfoBalance walletInfoBalance, @NotNull Currency currency) {
                    Intrinsics.checkParameterIsNotNull(walletInfoBalance, "wallet");
                    Intrinsics.checkParameterIsNotNull(currency, Param.CURRENCY);
                    TextView textView = this.walletNameTextView;
                    Intrinsics.checkExpressionValueIsNotNull(textView, "walletNameTextView");
                    textView.setText(walletInfoBalance.getName());
                    TextView textView2 = this.walletCryptoBalanceTextView;
                    String str = "walletCryptoBalanceTextView";
                    Intrinsics.checkExpressionValueIsNotNull(textView2, str);
                    textView2.setText(BigDecimalKt.toCoinString(walletInfoBalance.getSatoshis().getCoins(), walletInfoBalance.getCoin().getTicker()));
                    List list = (List) BuildersKt__BuildersKt.runBlocking$default(null, new C1079x2c8940e(this, walletInfoBalance, null), 1, null);
                    StringBuilder sb = new StringBuilder();
                    sb.append(BigDecimalKt.toCoinString(walletInfoBalance.getSatoshis().getCoins(), walletInfoBalance.getCoin().getTicker()));
                    sb.append(" (+ ");
                    Collection collection = list;
                    sb.append(collection.size());
                    sb.append(' ');
                    sb.append(this.view.getContext().getString(C1018R.string.token_amount_suffix));
                    sb.append(')');
                    String sb2 = sb.toString();
                    if (collection.size() > 0) {
                        TextView textView3 = this.walletCryptoBalanceTextView;
                        Intrinsics.checkExpressionValueIsNotNull(textView3, str);
                        if (!Intrinsics.areEqual((Object) textView3.getText(), (Object) sb2)) {
                            TextView textView4 = this.walletCryptoBalanceTextView;
                            Intrinsics.checkExpressionValueIsNotNull(textView4, str);
                            textView4.setText(sb2);
                        }
                    }
                    TextView textView5 = this.walletFiatBalanceTextView;
                    String str2 = "walletFiatBalanceTextView";
                    Intrinsics.checkExpressionValueIsNotNull(textView5, str2);
                    textView5.setText(BigDecimalKt.toFiatString$default(walletInfoBalance.getFiatBalance(), currency, false, 2, null));
                    this.walletColorDot.setColorFilter(Color.parseColor(walletInfoBalance.getWalletPreference().getColor()), Mode.MULTIPLY);
                    this.view.setOnClickListener(new C1078xfa528daa(this, walletInfoBalance));
                    String str3 = "backupRequiredIndicator";
                    if (walletInfoBalance.getWalletPreference().getHasBackedUp()) {
                        ImageView imageView = this.backupRequiredIndicator;
                        Intrinsics.checkExpressionValueIsNotNull(imageView, str3);
                        imageView.setVisibility(8);
                    } else {
                        this.backupRequiredIndicator.setColorFilter(Color.parseColor("#FF0000"), Mode.MULTIPLY);
                        ImageView imageView2 = this.backupRequiredIndicator;
                        Intrinsics.checkExpressionValueIsNotNull(imageView2, str3);
                        imageView2.setVisibility(0);
                    }
                    if (walletInfoBalance.getMultiSig() || walletInfoBalance.getCredentialType() == CredentialType.ENCRYPTED_MNEMONIC || walletInfoBalance.getCredentialType() == CredentialType.MNEMONIC_AND_PROTECTED) {
                        ImageView imageView3 = this.lockedImageView;
                        Intrinsics.checkExpressionValueIsNotNull(imageView3, "lockedImageView");
                        imageView3.setVisibility(0);
                        if (walletInfoBalance.getMultiSig()) {
                            this.walletCryptoBalanceTextView.setText(C1018R.string.muttisig_wallet_name);
                        } else {
                            this.walletCryptoBalanceTextView.setText(C1018R.string.general_locked);
                        }
                        TextView textView6 = this.walletFiatBalanceTextView;
                        Intrinsics.checkExpressionValueIsNotNull(textView6, str2);
                        textView6.setVisibility(4);
                        return;
                    }
                    String str4 = "balanceHiddenImageView";
                    if (walletInfoBalance.getWalletPreference().getHideBalance()) {
                        ImageView imageView4 = this.balanceHiddenImageView;
                        Intrinsics.checkExpressionValueIsNotNull(imageView4, str4);
                        imageView4.setVisibility(0);
                        TextView textView7 = this.walletCryptoBalanceTextView;
                        Intrinsics.checkExpressionValueIsNotNull(textView7, str);
                        textView7.setVisibility(8);
                        TextView textView8 = this.walletFiatBalanceTextView;
                        Intrinsics.checkExpressionValueIsNotNull(textView8, str2);
                        textView8.setVisibility(4);
                        return;
                    }
                    ImageView imageView5 = this.balanceHiddenImageView;
                    Intrinsics.checkExpressionValueIsNotNull(imageView5, str4);
                    imageView5.setVisibility(4);
                    TextView textView9 = this.walletCryptoBalanceTextView;
                    Intrinsics.checkExpressionValueIsNotNull(textView9, str);
                    textView9.setVisibility(0);
                    TextView textView10 = this.walletFiatBalanceTextView;
                    Intrinsics.checkExpressionValueIsNotNull(textView10, str2);
                    textView10.setVisibility(0);
                }
            }

            public WalletAssetsSingleAdapter(@NotNull List<WalletInfoBalance> list, @NotNull Currency currency2, @NotNull WalletAssetsRecyclerPresenter walletAssetsRecyclerPresenter) {
                Intrinsics.checkParameterIsNotNull(list, "wallets");
                Intrinsics.checkParameterIsNotNull(currency2, Param.CURRENCY);
                Intrinsics.checkParameterIsNotNull(walletAssetsRecyclerPresenter, "presenter");
                this.wallets = list;
                this.currency = currency2;
                this.presenter = walletAssetsRecyclerPresenter;
            }

            @NotNull
            public final Currency getCurrency() {
                return this.currency;
            }

            @NotNull
            public final WalletAssetsRecyclerPresenter getPresenter() {
                return this.presenter;
            }

            @NotNull
            public final List<WalletInfoBalance> getWallets() {
                return this.wallets;
            }

            public final int getFIRST() {
                return this.FIRST;
            }

            public final int getOTHER() {
                return this.OTHER;
            }

            public final int getLAST() {
                return this.LAST;
            }

            @NotNull
            public final WalletInfoBalance getItem(int i) {
                return (WalletInfoBalance) this.wallets.get(i);
            }

            public int getItemCount() {
                return this.wallets.size();
            }

            public int getItemViewType(int i) {
                if (i == 0) {
                    return this.FIRST;
                }
                if (i == this.wallets.size() - 1) {
                    return this.LAST;
                }
                return this.OTHER;
            }

            public void onBindViewHolder(@NotNull ViewHolder viewHolder, int i) {
                Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
                ((WalletAssetsSingleViewHolder) viewHolder).bind(getItem(i), this.currency);
            }

            @NotNull
            public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
                Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
                View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.layout_wallet_assets_recycler_single_cell, viewGroup, false);
                Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(pare…ngle_cell, parent, false)");
                if (i == this.FIRST) {
                    inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.layout_wallet_assets_recycler_single_first_cell, viewGroup, false);
                    Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(pare…irst_cell, parent, false)");
                }
                if (i == this.LAST) {
                    LinearLayout linearLayout = (LinearLayout) inflate.findViewById(C1018R.C1021id.captionLayout);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout, "captionLayout");
                    LayoutParams layoutParams = linearLayout.getLayoutParams();
                    if (layoutParams != null) {
                        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                        layoutParams2.setMargins(layoutParams2.leftMargin, layoutParams2.topMargin, layoutParams2.rightMargin, layoutParams2.bottomMargin - 5);
                        linearLayout.setLayoutParams(layoutParams2);
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                    }
                }
                return new WalletAssetsSingleViewHolder(inflate, this.presenter);
            }
        }

        @NotNull
        public final View getView() {
            return this.view;
        }

        @NotNull
        public final WalletAssetsRecyclerListener getListener() {
            return this.listener;
        }

        @NotNull
        public final Context getContext() {
            return this.context;
        }

        @NotNull
        public final WalletAssetsRecyclerAdapter getParentAdapter() {
            return this.parentAdapter;
        }

        @NotNull
        public final WalletAssetsRecyclerPresenter getPresenter() {
            return this.presenter;
        }

        public MultipleWalletsAssetsViewHolder(@NotNull View view2, @NotNull WalletAssetsRecyclerListener walletAssetsRecyclerListener, @NotNull WalletAssetsRecyclerAdapter walletAssetsRecyclerAdapter, @NotNull Context context2, @NotNull WalletAssetsRecyclerPresenter walletAssetsRecyclerPresenter) {
            Intrinsics.checkParameterIsNotNull(view2, "view");
            Intrinsics.checkParameterIsNotNull(walletAssetsRecyclerListener, CastExtraArgs.LISTENER);
            Intrinsics.checkParameterIsNotNull(walletAssetsRecyclerAdapter, "parentAdapter");
            Intrinsics.checkParameterIsNotNull(context2, "context");
            Intrinsics.checkParameterIsNotNull(walletAssetsRecyclerPresenter, "presenter");
            super(view2);
            this.view = view2;
            this.listener = walletAssetsRecyclerListener;
            this.parentAdapter = walletAssetsRecyclerAdapter;
            this.context = context2;
            this.presenter = walletAssetsRecyclerPresenter;
        }

        public final ImageView getChainLogoImageView() {
            return this.chainLogoImageView;
        }

        public final TextView getChainNameTextView() {
            return this.chainNameTextView;
        }

        public final void setChainNameTextView(TextView textView) {
            this.chainNameTextView = textView;
        }

        public final TextView getChainBalanceTextView() {
            return this.chainBalanceTextView;
        }

        public final void setChainBalanceTextView(TextView textView) {
            this.chainBalanceTextView = textView;
        }

        public final TextView getChainFiatBalanceTextView() {
            return this.chainFiatBalanceTextView;
        }

        public final void setChainFiatBalanceTextView(TextView textView) {
            this.chainFiatBalanceTextView = textView;
        }

        public final RecyclerView getRecyclerView() {
            return this.recyclerView;
        }

        public final LinearLayout getComponentFooter() {
            return this.componentFooter;
        }

        public final void setComponentFooter(LinearLayout linearLayout) {
            this.componentFooter = linearLayout;
        }

        public final ShadowLayout getHeaderContainer() {
            return this.headerContainer;
        }

        public final void setHeaderContainer(ShadowLayout shadowLayout) {
            this.headerContainer = shadowLayout;
        }

        public final ImageView getBackupRequiredIndicator() {
            return this.backupRequiredIndicator;
        }

        public final ImageView getBalanceHiddenImageView() {
            return this.balanceHiddenImageView;
        }

        public final void setExpandedFooter() {
            Context context2 = this.view.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "view.context");
            Resources resources = context2.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "view.context.resources");
            float applyDimension = TypedValue.applyDimension(1, 10.0f, resources.getDisplayMetrics());
            LinearLayout linearLayout = this.componentFooter;
            String str = "componentFooter";
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, str);
            LayoutParams layoutParams = linearLayout.getLayoutParams();
            layoutParams.height = (int) applyDimension;
            LinearLayout linearLayout2 = this.componentFooter;
            Intrinsics.checkExpressionValueIsNotNull(linearLayout2, str);
            linearLayout2.setLayoutParams(layoutParams);
            this.componentFooter.requestLayout();
        }

        public final void setCollapsedFooter() {
            Context context2 = this.view.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context2, "view.context");
            Resources resources = context2.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "view.context.resources");
            float applyDimension = TypedValue.applyDimension(1, 0.0f, resources.getDisplayMetrics());
            LinearLayout linearLayout = this.componentFooter;
            String str = "componentFooter";
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, str);
            LayoutParams layoutParams = linearLayout.getLayoutParams();
            layoutParams.height = (int) applyDimension;
            LinearLayout linearLayout2 = this.componentFooter;
            Intrinsics.checkExpressionValueIsNotNull(linearLayout2, str);
            linearLayout2.setLayoutParams(layoutParams);
            this.componentFooter.requestLayout();
        }

        public final void collapseExpandDropdown(@NotNull Coin coin) {
            Intrinsics.checkParameterIsNotNull(coin, "coin");
            Boolean bool = (Boolean) this.parentAdapter.getCollapsedMap().get(coin);
            Boolean valueOf = Boolean.valueOf(false);
            String str = "recyclerView";
            if (Intrinsics.areEqual((Object) bool, (Object) valueOf)) {
                setCollapsedFooter();
                RecyclerView recyclerView2 = this.recyclerView;
                Intrinsics.checkExpressionValueIsNotNull(recyclerView2, str);
                recyclerView2.setVisibility(8);
                this.parentAdapter.getCollapsedMap().put(coin, Boolean.valueOf(true));
                return;
            }
            setExpandedFooter();
            RecyclerView recyclerView3 = this.recyclerView;
            Intrinsics.checkExpressionValueIsNotNull(recyclerView3, str);
            recyclerView3.setVisibility(0);
            this.parentAdapter.getCollapsedMap().put(coin, valueOf);
        }

        public final void bind(@NotNull Pair<CoinBalance, ? extends List<WalletInfoBalance>> pair, @NotNull Currency currency) {
            Pair<CoinBalance, ? extends List<WalletInfoBalance>> pair2 = pair;
            Currency currency2 = currency;
            Intrinsics.checkParameterIsNotNull(pair2, "chainAndBalances");
            Intrinsics.checkParameterIsNotNull(currency2, Param.CURRENCY);
            CoinBalance coinBalance = (CoinBalance) pair.getFirst();
            List list = (List) pair.getSecond();
            Iterable iterable = list;
            Collection arrayList = new ArrayList();
            for (Object next : iterable) {
                if (((WalletInfoBalance) next).getWalletPreference().getHideBalance()) {
                    arrayList.add(next);
                }
            }
            List list2 = (List) arrayList;
            String str = "balanceHiddenImageView";
            String str2 = "chainFiatBalanceTextView";
            String str3 = "chainBalanceTextView";
            if (list2.size() == list.size()) {
                ImageView imageView = this.balanceHiddenImageView;
                Intrinsics.checkExpressionValueIsNotNull(imageView, str);
                imageView.setVisibility(0);
                TextView textView = this.chainBalanceTextView;
                Intrinsics.checkExpressionValueIsNotNull(textView, str3);
                textView.setVisibility(8);
                TextView textView2 = this.chainFiatBalanceTextView;
                Intrinsics.checkExpressionValueIsNotNull(textView2, str2);
                textView2.setVisibility(4);
            } else {
                ImageView imageView2 = this.balanceHiddenImageView;
                Intrinsics.checkExpressionValueIsNotNull(imageView2, str);
                imageView2.setVisibility(8);
                TextView textView3 = this.chainBalanceTextView;
                Intrinsics.checkExpressionValueIsNotNull(textView3, str3);
                textView3.setVisibility(0);
                TextView textView4 = this.chainFiatBalanceTextView;
                Intrinsics.checkExpressionValueIsNotNull(textView4, str2);
                textView4.setVisibility(0);
            }
            this.chainLogoImageView.setImageDrawable(ContextCompat.getDrawable(this.view.getContext(), coinBalance.getCoin().getIconResId()));
            TextView textView5 = this.chainNameTextView;
            Intrinsics.checkExpressionValueIsNotNull(textView5, "chainNameTextView");
            Context context2 = this.view.getContext();
            String str4 = "view.context";
            Intrinsics.checkExpressionValueIsNotNull(context2, str4);
            textView5.setText(context2.getResources().getString(coinBalance.getCoin().getPresentableNameResId()));
            BigDecimal fiat = coinBalance.getFiat();
            BigDecimal coins = coinBalance.getSatoshis().getCoins();
            Iterator it = list2.iterator();
            while (true) {
                BigDecimal bigDecimal = null;
                if (!it.hasNext()) {
                    break;
                }
                WalletInfoBalance walletInfoBalance = (WalletInfoBalance) it.next();
                String str5 = "this.subtract(other)";
                if (walletInfoBalance.getFiatBalance() != null) {
                    if (fiat != null) {
                        BigDecimal fiatBalance = walletInfoBalance.getFiatBalance();
                        if (fiatBalance == null) {
                            Intrinsics.throwNpe();
                        }
                        bigDecimal = fiat.subtract(fiatBalance);
                        Intrinsics.checkExpressionValueIsNotNull(bigDecimal, str5);
                    }
                    fiat = bigDecimal;
                }
                if (walletInfoBalance.getSatoshis().getCoins() != null) {
                    coins = coins.subtract(walletInfoBalance.getSatoshis().getCoins());
                    Intrinsics.checkExpressionValueIsNotNull(coins, str5);
                }
            }
            TextView textView6 = this.chainFiatBalanceTextView;
            Intrinsics.checkExpressionValueIsNotNull(textView6, str2);
            textView6.setText(BigDecimalKt.toFiatString$default(fiat, currency2, false, 2, null));
            TextView textView7 = this.chainBalanceTextView;
            Intrinsics.checkExpressionValueIsNotNull(textView7, str3);
            textView7.setText(BigDecimalKt.toCoinString(coins, coinBalance.getCoin().getTicker()));
            Collection arrayList2 = new ArrayList();
            for (Object next2 : iterable) {
                if (true ^ ((WalletInfoBalance) next2).getWalletPreference().getHasBackedUp()) {
                    arrayList2.add(next2);
                }
            }
            String str6 = "backupRequiredIndicator";
            if (!((List) arrayList2).isEmpty()) {
                this.backupRequiredIndicator.setColorFilter(Color.parseColor("#FF0000"), Mode.MULTIPLY);
                ImageView imageView3 = this.backupRequiredIndicator;
                Intrinsics.checkExpressionValueIsNotNull(imageView3, str6);
                imageView3.setVisibility(0);
            } else {
                ImageView imageView4 = this.backupRequiredIndicator;
                Intrinsics.checkExpressionValueIsNotNull(imageView4, str6);
                imageView4.setVisibility(8);
            }
            C1081xa3c454a4 walletAssetsRecyclerAdapter$MultipleWalletsAssetsViewHolder$bind$itemDecorator$1 = new C1081xa3c454a4(this, this.view.getContext(), 1);
            this.headerContainer.setOnClickListener(new C1080x24a43622(this, pair2));
            Drawable drawable = ContextCompat.getDrawable(this.view.getContext(), C1018R.C1020drawable.background_basic_divider);
            if (drawable == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(drawable, "ContextCompat.getDrawabl…ckground_basic_divider)!!");
            Context context3 = this.view.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context3, str4);
            int dimensionPixelSize = context3.getResources().getDimensionPixelSize(C1018R.dimen.divider_margin);
            InsetDrawable insetDrawable = new InsetDrawable(drawable, dimensionPixelSize, 0, dimensionPixelSize, 0);
            walletAssetsRecyclerAdapter$MultipleWalletsAssetsViewHolder$bind$itemDecorator$1.setDrawable(insetDrawable);
            this.recyclerView.addItemDecoration(walletAssetsRecyclerAdapter$MultipleWalletsAssetsViewHolder$bind$itemDecorator$1);
            RecyclerView recyclerView2 = this.recyclerView;
            String str7 = "recyclerView";
            Intrinsics.checkExpressionValueIsNotNull(recyclerView2, str7);
            recyclerView2.setAdapter(new WalletAssetsSingleAdapter((List) pair.getSecond(), currency2, this.presenter));
            if (Intrinsics.areEqual((Object) (Boolean) this.parentAdapter.getCollapsedMap().get(coinBalance.getCoin()), (Object) Boolean.valueOf(true))) {
                RecyclerView recyclerView3 = this.recyclerView;
                Intrinsics.checkExpressionValueIsNotNull(recyclerView3, str7);
                recyclerView3.setVisibility(8);
                setCollapsedFooter();
                return;
            }
            RecyclerView recyclerView4 = this.recyclerView;
            Intrinsics.checkExpressionValueIsNotNull(recyclerView4, str7);
            recyclerView4.setVisibility(0);
            setExpandedFooter();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ(\u0010$\u001a\u00020%2\u0018\u0010&\u001a\u0014\u0012\u0004\u0012\u00020(\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0'2\u0006\u0010+\u001a\u00020,R\u0019\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\n \u000b*\u0004\u0018\u00010\n0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\"\u0010\u0010\u001a\n \u000b*\u0004\u0018\u00010\u00110\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\n \u000b*\u0004\u0018\u00010\u00110\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u0019\u0010\u0019\u001a\n \u000b*\u0004\u0018\u00010\n0\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\rR\"\u0010\u001b\u001a\n \u000b*\u0004\u0018\u00010\u00110\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u0006-"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerAdapter$SingleWalletAssetViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "listener", "Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerListener;", "presenter", "Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;", "(Landroid/view/View;Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerListener;Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;)V", "backupRequiredIndicator", "Landroid/widget/ImageView;", "kotlin.jvm.PlatformType", "getBackupRequiredIndicator", "()Landroid/widget/ImageView;", "balanceHiddenImageView", "getBalanceHiddenImageView", "chainBalanceTextView", "Landroid/widget/TextView;", "getChainBalanceTextView", "()Landroid/widget/TextView;", "setChainBalanceTextView", "(Landroid/widget/TextView;)V", "chainFiatBalanceTextView", "getChainFiatBalanceTextView", "setChainFiatBalanceTextView", "chainLogoImageView", "getChainLogoImageView", "chainNameTextView", "getChainNameTextView", "setChainNameTextView", "getListener", "()Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerListener;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/walletassetsrecycler/WalletAssetsRecyclerPresenter;", "getView", "()Landroid/view/View;", "bind", "", "chainAndBalances", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/CoinBalance;", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "currency", "Ljava/util/Currency;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletAssetsRecyclerAdapter.kt */
    public static final class SingleWalletAssetViewHolder extends ViewHolder {
        private final ImageView backupRequiredIndicator = ((ImageView) this.view.findViewById(C1018R.C1021id.backupRequiredIndicator));
        private final ImageView balanceHiddenImageView = ((ImageView) this.view.findViewById(C1018R.C1021id.balanceHiddenImageView));
        private TextView chainBalanceTextView = ((TextView) this.view.findViewById(C1018R.C1021id.chainBalanceTextView));
        private TextView chainFiatBalanceTextView = ((TextView) this.view.findViewById(C1018R.C1021id.chainFiatBalanceTextView));
        private final ImageView chainLogoImageView = ((ImageView) this.view.findViewById(C1018R.C1021id.chainLogoImageView));
        private TextView chainNameTextView = ((TextView) this.view.findViewById(C1018R.C1021id.chainNameTextView));
        @NotNull
        private final WalletAssetsRecyclerListener listener;
        @NotNull
        private final WalletAssetsRecyclerPresenter presenter;
        @NotNull
        private final View view;

        @NotNull
        public final WalletAssetsRecyclerListener getListener() {
            return this.listener;
        }

        @NotNull
        public final WalletAssetsRecyclerPresenter getPresenter() {
            return this.presenter;
        }

        @NotNull
        public final View getView() {
            return this.view;
        }

        public SingleWalletAssetViewHolder(@NotNull View view2, @NotNull WalletAssetsRecyclerListener walletAssetsRecyclerListener, @NotNull WalletAssetsRecyclerPresenter walletAssetsRecyclerPresenter) {
            Intrinsics.checkParameterIsNotNull(view2, "view");
            Intrinsics.checkParameterIsNotNull(walletAssetsRecyclerListener, CastExtraArgs.LISTENER);
            Intrinsics.checkParameterIsNotNull(walletAssetsRecyclerPresenter, "presenter");
            super(view2);
            this.view = view2;
            this.listener = walletAssetsRecyclerListener;
            this.presenter = walletAssetsRecyclerPresenter;
        }

        public final ImageView getChainLogoImageView() {
            return this.chainLogoImageView;
        }

        public final TextView getChainNameTextView() {
            return this.chainNameTextView;
        }

        public final void setChainNameTextView(TextView textView) {
            this.chainNameTextView = textView;
        }

        public final TextView getChainBalanceTextView() {
            return this.chainBalanceTextView;
        }

        public final void setChainBalanceTextView(TextView textView) {
            this.chainBalanceTextView = textView;
        }

        public final TextView getChainFiatBalanceTextView() {
            return this.chainFiatBalanceTextView;
        }

        public final void setChainFiatBalanceTextView(TextView textView) {
            this.chainFiatBalanceTextView = textView;
        }

        public final ImageView getBackupRequiredIndicator() {
            return this.backupRequiredIndicator;
        }

        public final ImageView getBalanceHiddenImageView() {
            return this.balanceHiddenImageView;
        }

        public final void bind(@NotNull Pair<CoinBalance, ? extends List<WalletInfoBalance>> pair, @NotNull Currency currency) {
            Intrinsics.checkParameterIsNotNull(pair, "chainAndBalances");
            Intrinsics.checkParameterIsNotNull(currency, Param.CURRENCY);
            CoinBalance coinBalance = (CoinBalance) pair.getFirst();
            WalletInfoBalance walletInfoBalance = (WalletInfoBalance) CollectionsKt.first((List) pair.getSecond());
            ImageView imageView = (ImageView) this.view.findViewById(C1018R.C1021id.lockedImageView);
            this.chainLogoImageView.setImageDrawable(ContextCompat.getDrawable(this.view.getContext(), coinBalance.getCoin().getIconResId()));
            TextView textView = this.chainNameTextView;
            Intrinsics.checkExpressionValueIsNotNull(textView, "chainNameTextView");
            textView.setText(walletInfoBalance.getName());
            TextView textView2 = this.chainBalanceTextView;
            String str = "chainBalanceTextView";
            Intrinsics.checkExpressionValueIsNotNull(textView2, str);
            textView2.setText(BigDecimalKt.toCoinString(coinBalance.getSatoshis().getCoins(), coinBalance.getCoin().getTicker()));
            List list = (List) BuildersKt__BuildersKt.runBlocking$default(null, new C1082xb6ac1a5a(this, walletInfoBalance, null), 1, null);
            StringBuilder sb = new StringBuilder();
            sb.append(BigDecimalKt.toCoinString(coinBalance.getSatoshis().getCoins(), coinBalance.getCoin().getTicker()));
            sb.append(" (+ ");
            Collection collection = list;
            sb.append(collection.size());
            sb.append(' ');
            sb.append(this.view.getContext().getString(C1018R.string.token_amount_suffix));
            sb.append(')');
            String sb2 = sb.toString();
            if (collection.size() > 0) {
                TextView textView3 = this.chainBalanceTextView;
                Intrinsics.checkExpressionValueIsNotNull(textView3, str);
                if (!Intrinsics.areEqual((Object) textView3.getText(), (Object) sb2)) {
                    TextView textView4 = this.chainBalanceTextView;
                    Intrinsics.checkExpressionValueIsNotNull(textView4, str);
                    textView4.setText(sb2);
                }
            }
            TextView textView5 = this.chainFiatBalanceTextView;
            String str2 = "chainFiatBalanceTextView";
            Intrinsics.checkExpressionValueIsNotNull(textView5, str2);
            textView5.setText(BigDecimalKt.toFiatString$default(coinBalance.getFiat(), currency, false, 2, null));
            Iterable iterable = (List) pair.getSecond();
            Collection arrayList = new ArrayList();
            for (Object next : iterable) {
                if (!((WalletInfoBalance) next).getWalletPreference().getHasBackedUp()) {
                    arrayList.add(next);
                }
            }
            String str3 = "backupRequiredIndicator";
            if (!((List) arrayList).isEmpty()) {
                this.backupRequiredIndicator.setColorFilter(Color.parseColor("#FF0000"), Mode.MULTIPLY);
                ImageView imageView2 = this.backupRequiredIndicator;
                Intrinsics.checkExpressionValueIsNotNull(imageView2, str3);
                imageView2.setVisibility(0);
            } else {
                ImageView imageView3 = this.backupRequiredIndicator;
                Intrinsics.checkExpressionValueIsNotNull(imageView3, str3);
                imageView3.setVisibility(8);
            }
            if (walletInfoBalance.getMultiSig() || walletInfoBalance.getCredentialType() == CredentialType.ENCRYPTED_MNEMONIC || walletInfoBalance.getCredentialType() == CredentialType.MNEMONIC_AND_PROTECTED) {
                Intrinsics.checkExpressionValueIsNotNull(imageView, "lockedImageView");
                imageView.setVisibility(0);
                if (walletInfoBalance.getMultiSig()) {
                    this.chainBalanceTextView.setText(C1018R.string.muttisig_wallet_name);
                } else {
                    this.chainBalanceTextView.setText(C1018R.string.general_locked);
                }
                TextView textView6 = this.chainFiatBalanceTextView;
                Intrinsics.checkExpressionValueIsNotNull(textView6, str2);
                textView6.setVisibility(4);
            } else {
                String str4 = "balanceHiddenImageView";
                if (walletInfoBalance.getWalletPreference().getHideBalance()) {
                    ImageView imageView4 = this.balanceHiddenImageView;
                    Intrinsics.checkExpressionValueIsNotNull(imageView4, str4);
                    imageView4.setVisibility(0);
                    TextView textView7 = this.chainBalanceTextView;
                    Intrinsics.checkExpressionValueIsNotNull(textView7, str);
                    textView7.setVisibility(8);
                    TextView textView8 = this.chainFiatBalanceTextView;
                    Intrinsics.checkExpressionValueIsNotNull(textView8, str2);
                    textView8.setVisibility(4);
                } else {
                    ImageView imageView5 = this.balanceHiddenImageView;
                    Intrinsics.checkExpressionValueIsNotNull(imageView5, str4);
                    imageView5.setVisibility(4);
                    TextView textView9 = this.chainBalanceTextView;
                    Intrinsics.checkExpressionValueIsNotNull(textView9, str);
                    textView9.setVisibility(0);
                    TextView textView10 = this.chainFiatBalanceTextView;
                    Intrinsics.checkExpressionValueIsNotNull(textView10, str2);
                    textView10.setVisibility(0);
                }
            }
            this.view.setOnClickListener(new WalletAssetsRecyclerAdapter$SingleWalletAssetViewHolder$bind$2(this, walletInfoBalance));
        }
    }

    @NotNull
    public final Currency getCurrency() {
        return this.currency;
    }

    @NotNull
    public final WalletAssetsRecyclerListener getListener() {
        return this.listener;
    }

    @NotNull
    public final WalletAssetsRecyclerPresenter getPresenter() {
        return this.presenter;
    }

    public WalletAssetsRecyclerAdapter(@NotNull Currency currency2, @NotNull WalletAssetsRecyclerListener walletAssetsRecyclerListener, @NotNull WalletAssetsRecyclerPresenter walletAssetsRecyclerPresenter) {
        Intrinsics.checkParameterIsNotNull(currency2, Param.CURRENCY);
        Intrinsics.checkParameterIsNotNull(walletAssetsRecyclerListener, CastExtraArgs.LISTENER);
        Intrinsics.checkParameterIsNotNull(walletAssetsRecyclerPresenter, "presenter");
        this.currency = currency2;
        this.listener = walletAssetsRecyclerListener;
        this.presenter = walletAssetsRecyclerPresenter;
        Coin coin = Coin.BCH;
        Boolean valueOf = Boolean.valueOf(false);
        this.collapsedMap = MapsKt.mutableMapOf(TuplesKt.m309to(coin, valueOf), TuplesKt.m309to(Coin.BTC, valueOf));
        this.SINGLEWALLET_VIEW_TYPE = 1;
    }

    @NotNull
    public final List<Pair<CoinBalance, List<WalletInfoBalance>>> getWallets() {
        return this.wallets;
    }

    public final void setWallets(@NotNull List<Pair<CoinBalance, List<WalletInfoBalance>>> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.wallets = list;
    }

    @NotNull
    public final Map<Coin, Boolean> getCollapsedMap() {
        return this.collapsedMap;
    }

    public final void setCollapsedMap(@NotNull Map<Coin, Boolean> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.collapsedMap = map;
    }

    public final int getMULITWALLET_VIEW_TYPE() {
        return this.MULITWALLET_VIEW_TYPE;
    }

    public final int getSINGLEWALLET_VIEW_TYPE() {
        return this.SINGLEWALLET_VIEW_TYPE;
    }

    public final void submitNewData(@NotNull List<Pair<CoinBalance, List<WalletInfoBalance>>> list) {
        Intrinsics.checkParameterIsNotNull(list, "wallets");
        this.wallets = list;
        notifyDataSetChanged();
    }

    @NotNull
    public final Pair<CoinBalance, List<WalletInfoBalance>> getItem(int i) {
        return (Pair) this.wallets.get(i);
    }

    public int getItemCount() {
        return this.wallets.size();
    }

    public int getItemViewType(int i) {
        if (((Collection) ((Pair) this.wallets.get(i)).getSecond()).size() > 1) {
            return this.MULITWALLET_VIEW_TYPE;
        }
        return this.SINGLEWALLET_VIEW_TYPE;
    }

    public void onBindViewHolder(@NotNull ViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        Pair item = getItem(i);
        if (viewHolder instanceof MultipleWalletsAssetsViewHolder) {
            ((MultipleWalletsAssetsViewHolder) viewHolder).bind(item, this.currency);
            viewHolder.setIsRecyclable(false);
            return;
        }
        ((SingleWalletAssetViewHolder) viewHolder).bind(item, this.currency);
        viewHolder.setIsRecyclable(false);
    }

    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        String str = "LayoutInflater.from(pare…cler_cell, parent, false)";
        if (i == this.MULITWALLET_VIEW_TYPE) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.layout_multiwallet_assets_recycler_cell, viewGroup, false);
            Intrinsics.checkExpressionValueIsNotNull(inflate, str);
            WalletAssetsRecyclerListener walletAssetsRecyclerListener = this.listener;
            Context context = viewGroup.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "parent.context");
            MultipleWalletsAssetsViewHolder multipleWalletsAssetsViewHolder = new MultipleWalletsAssetsViewHolder(inflate, walletAssetsRecyclerListener, this, context, this.presenter);
            return multipleWalletsAssetsViewHolder;
        }
        View inflate2 = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.layout_single_wallet_assets_recycler_cell, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate2, str);
        return new SingleWalletAssetViewHolder(inflate2, this.listener, this.presenter);
    }
}
