package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "walletBalance", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionView.kt */
final class SendAmountSelectionView$bindDataObservers$7<T> implements Observer<WalletInfoBalance> {
    final /* synthetic */ SendAmountSelectionView this$0;

    SendAmountSelectionView$bindDataObservers$7(SendAmountSelectionView sendAmountSelectionView) {
        this.this$0 = sendAmountSelectionView;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0069, code lost:
        if (r9 != null) goto L_0x006e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onChanged(com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance r13) {
        /*
            r12 = this;
            if (r13 == 0) goto L_0x01a6
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r0 = r12.this$0
            com.bitcoin.mwallet.app.viper.ScreenPresenter r0 = r0.getPresenter()
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r0 = (com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter) r0
            r0.validateAmount()
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r0 = r12.this$0
            com.bitcoin.mwallet.app.viper.ScreenPresenter r0 = r0.getPresenter()
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r0 = (com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter) r0
            androidx.lifecycle.MutableLiveData r0 = r0.getPrimaryEntryType()
            java.lang.Object r0 = r0.getValue()
            com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase$EntryType r0 = (com.bitcoin.mwallet.app.components.amountselection.AmountSelectionPresenterBase.EntryType) r0
            r1 = 2131362546(0x7f0a02f2, float:1.8344876E38)
            r2 = 2131362545(0x7f0a02f1, float:1.8344874E38)
            r3 = 2131951936(0x7f130140, float:1.95403E38)
            r4 = 0
            r5 = 2
            r6 = 0
            r7 = 1
            if (r0 != 0) goto L_0x002f
            goto L_0x0039
        L_0x002f:
            int[] r8 = com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView.WhenMappings.$EnumSwitchMapping$1
            int r0 = r0.ordinal()
            r0 = r8[r0]
            if (r0 == r7) goto L_0x00d1
        L_0x0039:
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r0 = r12.this$0
            android.view.View r0 = r0.getView()
            if (r0 == 0) goto L_0x0088
            android.view.View r0 = r0.findViewById(r2)
            android.widget.TextView r0 = (android.widget.TextView) r0
            if (r0 == 0) goto L_0x0088
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r2 = r12.this$0
            android.content.res.Resources r2 = r2.getResources()
            java.lang.Object[] r8 = new java.lang.Object[r5]
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r9 = r12.this$0
            com.bitcoin.mwallet.app.viper.ScreenPresenter r9 = r9.getPresenter()
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r9 = (com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter) r9
            androidx.lifecycle.MutableLiveData r9 = r9.getDisplayCurrency()
            java.lang.Object r9 = r9.getValue()
            java.util.Currency r9 = (java.util.Currency) r9
            if (r9 == 0) goto L_0x006c
            java.lang.String r9 = r9.getSymbol()
            if (r9 == 0) goto L_0x006c
            goto L_0x006e
        L_0x006c:
            java.lang.String r9 = ""
        L_0x006e:
            r8[r6] = r9
            java.math.BigDecimal r9 = r13.getFiatBalance()
            if (r9 == 0) goto L_0x007c
            r10 = 4
            java.math.BigDecimal r9 = r9.setScale(r5, r10)
            goto L_0x007d
        L_0x007c:
            r9 = r4
        L_0x007d:
            r8[r7] = r9
            java.lang.String r2 = r2.getString(r3, r8)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r0.setText(r2)
        L_0x0088:
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r0 = r12.this$0
            android.view.View r0 = r0.getView()
            if (r0 == 0) goto L_0x00b9
            android.view.View r0 = r0.findViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            if (r0 == 0) goto L_0x00b9
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r1 = r12.this$0
            android.content.res.Resources r1 = r1.getResources()
            java.lang.Object[] r2 = new java.lang.Object[r5]
            com.bitcoin.bitcoink.tx.Satoshis r5 = r13.getSatoshis()
            java.lang.String r4 = com.bitcoin.mwallet.core.extensions.SatoshisKt.toCoinString$default(r5, r4, r7, r4)
            r2[r6] = r4
            com.bitcoin.mwallet.core.models.Coin r4 = r13.getCoin()
            r2[r7] = r4
            java.lang.String r1 = r1.getString(r3, r2)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
        L_0x00b9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WALLET BALANCE: "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r1 = new java.lang.Object[r6]
            timber.log.Timber.m426d(r0, r1)
            goto L_0x0166
        L_0x00d1:
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r0 = r12.this$0
            android.view.View r0 = r0.getView()
            if (r0 == 0) goto L_0x011f
            android.view.View r0 = r0.findViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            if (r0 == 0) goto L_0x011f
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r1 = r12.this$0
            android.content.res.Resources r1 = r1.getResources()
            java.lang.Object[] r8 = new java.lang.Object[r5]
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r9 = r12.this$0
            com.bitcoin.mwallet.app.viper.ScreenPresenter r9 = r9.getPresenter()
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r9 = (com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter) r9
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r10 = r12.this$0
            com.bitcoin.mwallet.app.viper.ScreenPresenter r10 = r10.getPresenter()
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r10 = (com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter) r10
            java.math.BigDecimal r10 = r10.getTargetWalletTokenBalance()
            java.lang.String r11 = "presenter.targetWalletTokenBalance"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r11)
            java.lang.String r9 = r9.convertBigDecimalToString(r10)
            r8[r6] = r9
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r9 = r12.this$0
            com.bitcoin.mwallet.app.viper.ScreenPresenter r9 = r9.getPresenter()
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter r9 = (com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionPresenter) r9
            java.lang.String r9 = r9.getTokenTicker()
            r8[r7] = r9
            java.lang.String r1 = r1.getString(r3, r8)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
        L_0x011f:
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r0 = r12.this$0
            android.view.View r0 = r0.getView()
            if (r0 == 0) goto L_0x0150
            android.view.View r0 = r0.findViewById(r2)
            android.widget.TextView r0 = (android.widget.TextView) r0
            if (r0 == 0) goto L_0x0150
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r1 = r12.this$0
            android.content.res.Resources r1 = r1.getResources()
            java.lang.Object[] r2 = new java.lang.Object[r5]
            com.bitcoin.bitcoink.tx.Satoshis r5 = r13.getSatoshis()
            java.lang.String r4 = com.bitcoin.mwallet.core.extensions.SatoshisKt.toCoinString$default(r5, r4, r7, r4)
            r2[r6] = r4
            com.bitcoin.mwallet.core.models.Coin r4 = r13.getCoin()
            r2[r7] = r4
            java.lang.String r1 = r1.getString(r3, r2)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
        L_0x0150:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "WALLET Token BALANCE: "
            r0.append(r1)
            r0.append(r13)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r1 = new java.lang.Object[r6]
            timber.log.Timber.m426d(r0, r1)
        L_0x0166:
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r0 = r12.this$0
            android.view.View r0 = r0.getView()
            if (r0 == 0) goto L_0x0182
            r1 = 2131362544(0x7f0a02f0, float:1.8344872E38)
            android.view.View r0 = r0.findViewById(r1)
            android.widget.TextView r0 = (android.widget.TextView) r0
            if (r0 == 0) goto L_0x0182
            java.lang.String r1 = r13.getName()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
        L_0x0182:
            com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView r0 = r12.this$0
            android.view.View r0 = r0.getView()
            if (r0 == 0) goto L_0x01a6
            r1 = 2131362765(0x7f0a03cd, float:1.834532E38)
            android.view.View r0 = r0.findViewById(r1)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            if (r0 == 0) goto L_0x01a6
            com.bitcoin.mwallet.core.preferences.WalletPreference r13 = r13.getWalletPreference()
            java.lang.String r13 = r13.getColor()
            int r13 = android.graphics.Color.parseColor(r13)
            android.graphics.PorterDuff$Mode r1 = android.graphics.PorterDuff.Mode.MULTIPLY
            r0.setColorFilter(r13, r1)
        L_0x01a6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.sendamountselection.SendAmountSelectionView$bindDataObservers$7.onChanged(com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance):void");
    }
}
