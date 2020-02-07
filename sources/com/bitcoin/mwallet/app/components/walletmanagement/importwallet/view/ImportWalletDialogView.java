package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.builder.ImportWalletBuilder;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.views.FullScreenRoundedBottomSheetDialogFragment;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongycastle.i18n.MessageBundle;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\"B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J&\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0010\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!H\u0002R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u0004R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006#"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/view/ImportWalletDialogView;", "Lcom/bitcoin/mwallet/core/views/FullScreenRoundedBottomSheetDialogFragment;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/core/models/Coin;)V", "builder", "Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/builder/ImportWalletBuilder;", "getBuilder", "()Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/builder/ImportWalletBuilder;", "setBuilder", "(Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/builder/ImportWalletBuilder;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "setCoin", "presenter", "Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/presenter/ImportWalletPresenter;", "getPresenter", "()Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/presenter/ImportWalletPresenter;", "setPresenter", "(Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/presenter/ImportWalletPresenter;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setupViewPager", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "ViewPagerAdapter", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ImportWalletDialogView.kt */
public final class ImportWalletDialogView extends FullScreenRoundedBottomSheetDialogFragment {
    private HashMap _$_findViewCache;
    @Nullable
    private ImportWalletBuilder builder;
    @Nullable
    private Coin coin;
    @Nullable
    private ImportWalletPresenter presenter;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\nJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0010H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u0010H\u0016R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0006j\b\u0012\u0004\u0012\u00020\n`\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/view/ImportWalletDialogView$ViewPagerAdapter;", "Landroidx/fragment/app/FragmentPagerAdapter;", "manager", "Landroidx/fragment/app/FragmentManager;", "(Lcom/bitcoin/mwallet/app/components/walletmanagement/importwallet/view/ImportWalletDialogView;Landroidx/fragment/app/FragmentManager;)V", "mFragmentList", "Ljava/util/ArrayList;", "Landroidx/fragment/app/Fragment;", "Lkotlin/collections/ArrayList;", "mFragmentTitleList", "", "addFragment", "", "fragment", "title", "getCount", "", "getItem", "position", "getPageTitle", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ImportWalletDialogView.kt */
    public final class ViewPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
        private final ArrayList<String> mFragmentTitleList = new ArrayList<>();
        final /* synthetic */ ImportWalletDialogView this$0;

        public ViewPagerAdapter(@NotNull ImportWalletDialogView importWalletDialogView, FragmentManager fragmentManager) {
            Intrinsics.checkParameterIsNotNull(fragmentManager, "manager");
            this.this$0 = importWalletDialogView;
            super(fragmentManager);
        }

        @NotNull
        public Fragment getItem(int i) {
            Object obj = this.mFragmentList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(obj, "mFragmentList[position]");
            return (Fragment) obj;
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        public final void addFragment(@NotNull Fragment fragment, @NotNull String str) {
            Intrinsics.checkParameterIsNotNull(fragment, "fragment");
            Intrinsics.checkParameterIsNotNull(str, MessageBundle.TITLE_ENTRY);
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        @NotNull
        public CharSequence getPageTitle(int i) {
            Object obj = this.mFragmentTitleList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(obj, "mFragmentTitleList[position]");
            return (CharSequence) obj;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CreateWalletResult.values().length];

        static {
            $EnumSwitchMapping$0[CreateWalletResult.SUCCESS.ordinal()] = 1;
            $EnumSwitchMapping$0[CreateWalletResult.FAILED_ZION_MAX.ordinal()] = 2;
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

    @Nullable
    public final Coin getCoin() {
        return this.coin;
    }

    public final void setCoin(@Nullable Coin coin2) {
        this.coin = coin2;
    }

    public ImportWalletDialogView(@Nullable Coin coin2) {
        this.coin = coin2;
    }

    @Nullable
    public final ImportWalletBuilder getBuilder() {
        return this.builder;
    }

    public final void setBuilder(@Nullable ImportWalletBuilder importWalletBuilder) {
        this.builder = importWalletBuilder;
    }

    @Nullable
    public final ImportWalletPresenter getPresenter() {
        return this.presenter;
    }

    public final void setPresenter(@Nullable ImportWalletPresenter importWalletPresenter) {
        this.presenter = importWalletPresenter;
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        ImportWalletPresenter importWalletPresenter = null;
        View inflate = getLayoutInflater().inflate(C1018R.layout.fragment_component_importexistingwallet, null);
        this.builder = (ImportWalletBuilder) ViewModelProviders.m16of((Fragment) this).get(ImportWalletBuilder.class);
        ImportWalletBuilder importWalletBuilder = this.builder;
        if (importWalletBuilder != null) {
            importWalletPresenter = importWalletBuilder.getPresenter();
        }
        this.presenter = importWalletPresenter;
        ImportWalletPresenter importWalletPresenter2 = this.presenter;
        if (importWalletPresenter2 != null) {
            Coin coin2 = this.coin;
            if (coin2 == null) {
                coin2 = Coin.BCH;
            } else if (coin2 == null) {
                Intrinsics.throwNpe();
            }
            importWalletPresenter2.onCoinSelected(coin2);
        }
        ImportWalletPresenter importWalletPresenter3 = this.presenter;
        if (importWalletPresenter3 != null) {
            LiveData processResult = importWalletPresenter3.getProcessResult();
            if (processResult != null) {
                processResult.observe(getViewLifecycleOwner(), new ImportWalletDialogView$onCreateView$1(this));
            }
        }
        View findViewById = inflate.findViewById(C1018R.C1021id.viewpager);
        if (findViewById != null) {
            ViewPager viewPager = (ViewPager) findViewById;
            setupViewPager(viewPager);
            View findViewById2 = inflate.findViewById(C1018R.C1021id.tabs);
            if (findViewById2 != null) {
                ((TabLayout) findViewById2).setupWithViewPager(viewPager);
                return inflate;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.google.android.material.tabs.TabLayout");
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.viewpager.widget.ViewPager");
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, bundle);
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null) {
                window.setSoftInputMode(16);
            }
        }
    }

    private final void setupViewPager(ViewPager viewPager) {
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, childFragmentManager);
        viewPagerAdapter.addFragment(new MnemonicTabView(this.presenter), "Recovery Phrase");
        ImportWalletPresenter importWalletPresenter = this.presenter;
        Boolean valueOf = importWalletPresenter != null ? Boolean.valueOf(importWalletPresenter.getHasZion()) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        if (!valueOf.booleanValue()) {
            viewPagerAdapter.addFragment(new BackupFileTabView(this.presenter), "Backup File/Text");
        }
        viewPager.setAdapter(viewPagerAdapter);
    }
}
