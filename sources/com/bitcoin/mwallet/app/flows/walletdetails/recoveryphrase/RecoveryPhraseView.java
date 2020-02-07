package com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.navigation.NavArgsLazy;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ScreenView;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/recoveryphrase/RecoveryPhraseView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/recoveryphrase/recoveryphraseBuilder;", "Lcom/bitcoin/mwallet/app/flows/walletdetails/recoveryphrase/RecoveryPhrasePresenter;", "()V", "navArgs", "Lcom/bitcoin/mwallet/app/flows/walletdetails/recoveryphrase/RecoveryPhraseViewArgs;", "getNavArgs", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/recoveryphrase/RecoveryPhraseViewArgs;", "navArgs$delegate", "Landroidx/navigation/NavArgsLazy;", "bindData", "", "customizeText", "Landroid/text/SpannableStringBuilder;", "word", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: RecoveryPhraseView.kt */
public final class RecoveryPhraseView extends ScreenView<recoveryphraseBuilder, RecoveryPhrasePresenter> {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(RecoveryPhraseView.class), "navArgs", "getNavArgs()Lcom/bitcoin/mwallet/app/flows/walletdetails/recoveryphrase/RecoveryPhraseViewArgs;"))};
    private HashMap _$_findViewCache;
    @NotNull
    private final NavArgsLazy navArgs$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(RecoveryPhraseViewArgs.class), new RecoveryPhraseView$$special$$inlined$navArgs$1(this));

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

    @NotNull
    public final RecoveryPhraseViewArgs getNavArgs() {
        Lazy lazy = this.navArgs$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (RecoveryPhraseViewArgs) lazy.getValue();
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public RecoveryPhraseView() {
        super(C1018R.layout.fragment_screen_walletdetails_recoveryphrase, Reflection.getOrCreateKotlinClass(recoveryphraseBuilder.class));
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001b  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityCreated(@org.jetbrains.annotations.Nullable android.os.Bundle r4) {
        /*
            r3 = this;
            super.onActivityCreated(r4)
            androidx.fragment.app.FragmentActivity r4 = r3.getActivity()
            if (r4 == 0) goto L_0x0016
            android.content.Intent r4 = r4.getIntent()
            if (r4 == 0) goto L_0x0016
            java.lang.String r0 = "walletId"
            java.io.Serializable r4 = r4.getSerializableExtra(r0)
            goto L_0x0017
        L_0x0016:
            r4 = 0
        L_0x0017:
            com.bitcoin.mwallet.core.models.wallet.WalletId r4 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r4
            if (r4 == 0) goto L_0x0025
            com.bitcoin.mwallet.app.viper.ScreenPresenter r0 = r3.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhrasePresenter r0 = (com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhrasePresenter) r0
            r0.setWalletId(r4)
            goto L_0x0047
        L_0x0025:
            com.bitcoin.mwallet.app.viper.ScreenPresenter r4 = r3.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhrasePresenter r4 = (com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhrasePresenter) r4
            com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhraseViewArgs r0 = r3.getNavArgs()
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r0.getWalletId()
            r4.setWalletId(r0)
            com.bitcoin.mwallet.app.viper.ScreenPresenter r4 = r3.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhrasePresenter r4 = (com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhrasePresenter) r4
            com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhraseViewArgs r0 = r3.getNavArgs()
            java.lang.String r0 = r0.getWalletTicker()
            r4.setWalletTicker(r0)
        L_0x0047:
            r3.bindData()
            com.bitcoin.mwallet.app.viper.ScreenPresenter r4 = r3.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhrasePresenter r4 = (com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhrasePresenter) r4
            java.lang.String r4 = r4.getWalletTicker()
            com.bitcoin.mwallet.core.models.Coin r0 = com.bitcoin.mwallet.core.models.Coin.BCH
            java.lang.String r0 = r0.getTicker()
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
            if (r4 == 0) goto L_0x0076
            com.bitcoin.mwallet.app.components.warningdialog.WarningView r4 = new com.bitcoin.mwallet.app.components.warningdialog.WarningView
            r0 = 2131952064(0x7f1301c0, float:1.954056E38)
            r1 = 2131952063(0x7f1301bf, float:1.9540558E38)
            java.lang.String r2 = "https://support.bitcoin.com/en/articles/3565510-how-can-i-avoid-accidentally-losing-or-burning-slp-tokens"
            r4.<init>(r0, r1, r2)
            androidx.fragment.app.FragmentManager r0 = r3.getChildFragmentManager()
            java.lang.String r1 = "slp_warning"
            r4.show(r0, r1)
        L_0x0076:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase.RecoveryPhraseView.onActivityCreated(android.os.Bundle):void");
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        if (context != null) {
            context.setTheme(C1018R.C1026style.AppThemeMaterialComponents);
        }
    }

    @NotNull
    public final SpannableStringBuilder customizeText(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "word");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(-7829368), 0, 3, 33);
        return spannableStringBuilder;
    }

    private final void bindData() {
        List mutableList = CollectionsKt.toMutableList((Collection) ((RecoveryPhrasePresenter) getPresenter()).getMnemonicWords());
        Collection collection = mutableList;
        int size = collection.size();
        TableLayout tableLayout = (TableLayout) _$_findCachedViewById(C1018R.C1021id.mnemonicTable);
        Intrinsics.checkExpressionValueIsNotNull(tableLayout, "mnemonicTable");
        int childCount = tableLayout.getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = ((TableLayout) _$_findCachedViewById(C1018R.C1021id.mnemonicTable)).getChildAt(i);
            if (childAt != null) {
                TableRow tableRow = (TableRow) childAt;
                int childCount2 = tableRow.getChildCount();
                int i2 = 0;
                while (i2 < childCount2) {
                    View childAt2 = tableRow.getChildAt(i2);
                    if (childAt2 != null) {
                        LinearLayout linearLayout = (LinearLayout) childAt2;
                        if (!collection.isEmpty()) {
                            int size2 = (size + 1) - collection.size();
                            View childAt3 = linearLayout.getChildAt(0);
                            if (childAt3 != null) {
                                TextView textView = (TextView) childAt3;
                                StringBuilder sb = new StringBuilder();
                                sb.append(StringsKt.padStart(String.valueOf(size2), 2, (char) Typography.nbsp));
                                sb.append(". ");
                                sb.append((String) mutableList.remove(0));
                                textView.setText(customizeText(sb.toString()));
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
                            }
                        } else {
                            linearLayout.setVisibility(8);
                        }
                        i2++;
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
                    }
                }
                i++;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TableRow");
            }
        }
        ((ImageView) _$_findCachedViewById(C1018R.C1021id.qrCodeImage)).setImageBitmap(((RecoveryPhrasePresenter) getPresenter()).getMnemonicQrCode());
        ((RadioButton) _$_findCachedViewById(C1018R.C1021id.recoveryTextSelectorRadio)).setOnCheckedChangeListener(new RecoveryPhraseView$bindData$1(this));
        ((RadioButton) _$_findCachedViewById(C1018R.C1021id.recoveryQrSelectorRadio)).setOnCheckedChangeListener(new RecoveryPhraseView$bindData$2(this));
    }
}
