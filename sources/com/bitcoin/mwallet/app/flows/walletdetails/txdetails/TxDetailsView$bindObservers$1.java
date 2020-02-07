package com.bitcoin.mwallet.app.flows.walletdetails.txdetails;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import kotlin.Metadata;
import kotlin.Pair;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005 \u0006*\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, mo37405d2 = {"<anonymous>", "", "details", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: TxDetailsView.kt */
final class TxDetailsView$bindObservers$1<T> implements Observer<Pair<? extends HistoricTransaction, ? extends C1261Wallet>> {
    final /* synthetic */ TxDetailsView this$0;

    TxDetailsView$bindObservers$1(TxDetailsView txDetailsView) {
        this.this$0 = txDetailsView;
    }

    /* JADX WARNING: Removed duplicated region for block: B:200:0x043d A[Catch:{ Exception -> 0x044e }] */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x045c  */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x0487  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x049c  */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x049e  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x04a1  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x04d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onChanged(kotlin.Pair<com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction, com.bitcoin.mwallet.core.models.wallet.C1261Wallet> r17) {
        /*
            r16 = this;
            r1 = r16
            java.lang.Object r0 = r17.getFirst()
            r2 = r0
            com.bitcoin.mwallet.core.models.tx.history.HistoricTransaction r2 = (com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction) r2
            java.lang.Object r0 = r17.getSecond()
            com.bitcoin.mwallet.core.models.wallet.Wallet r0 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r0
            r3 = 0
            r4 = r3
            com.bitcoin.mwallet.core.models.slp.Slp r4 = (com.bitcoin.mwallet.core.models.slp.Slp) r4
            com.bitcoin.mwallet.core.models.slp.SlpId r5 = r2.getTokenId()
            java.lang.String r5 = r5.getId()
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            int r5 = r5.length()
            r6 = 1
            r7 = 0
            if (r5 <= 0) goto L_0x0027
            r5 = 1
            goto L_0x0028
        L_0x0027:
            r5 = 0
        L_0x0028:
            if (r5 == 0) goto L_0x0037
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView$bindObservers$1$1 r4 = new com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView$bindObservers$1$1
            r4.<init>(r1, r2, r3)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            java.lang.Object r4 = kotlinx.coroutines.BuildersKt__BuildersKt.runBlocking$default(r3, r4, r6, r3)
            com.bitcoin.mwallet.core.models.slp.Slp r4 = (com.bitcoin.mwallet.core.models.slp.Slp) r4
        L_0x0037:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0057
            r8 = 2131362715(0x7f0a039b, float:1.8345218E38)
            android.view.View r5 = r5.findViewById(r8)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x0057
            com.bitcoin.mwallet.core.models.tx.TxAction r8 = r2.getAction()
            java.lang.String r8 = r8.name()
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r5.setText(r8)
        L_0x0057:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0075
            r8 = 2131362714(0x7f0a039a, float:1.8345216E38)
            android.view.View r5 = r5.findViewById(r8)
            android.widget.ImageView r5 = (android.widget.ImageView) r5
            if (r5 == 0) goto L_0x0075
            com.bitcoin.mwallet.core.models.tx.TxAction r8 = r2.getAction()
            int r8 = r8.getIconResourceId()
            r5.setImageResource(r8)
        L_0x0075:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            r8 = 2131362707(0x7f0a0393, float:1.8345202E38)
            if (r5 == 0) goto L_0x0093
            android.view.View r5 = r5.findViewById(r8)
            android.widget.ImageView r5 = (android.widget.ImageView) r5
            if (r5 == 0) goto L_0x0093
            com.bitcoin.mwallet.core.models.Coin r9 = r0.getCoin()
            int r9 = r9.getIconResId()
            r5.setImageResource(r9)
        L_0x0093:
            int r5 = r2.getConfirmations()
            r9 = 2131361996(0x7f0a00cc, float:1.834376E38)
            r10 = 2131361997(0x7f0a00cd, float:1.8343762E38)
            if (r5 <= 0) goto L_0x00f8
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x00c1
            android.view.View r5 = r5.findViewById(r10)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x00c1
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r11 = r1.this$0
            android.content.res.Resources r11 = r11.getResources()
            r12 = 2131952123(0x7f1301fb, float:1.954068E38)
            java.lang.String r11 = r11.getString(r12)
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            r5.setText(r11)
        L_0x00c1:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x00e1
            android.view.View r5 = r5.findViewById(r10)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x00e1
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r10 = r1.this$0
            android.content.Context r10 = r10.requireContext()
            r11 = 2131099886(0x7f0600ee, float:1.7812138E38)
            int r10 = androidx.core.content.ContextCompat.getColor(r10, r11)
            r5.setTextColor(r10)
        L_0x00e1:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0150
            android.view.View r5 = r5.findViewById(r9)
            android.widget.ImageView r5 = (android.widget.ImageView) r5
            if (r5 == 0) goto L_0x0150
            r9 = 2131231146(0x7f0801aa, float:1.8078365E38)
            r5.setImageResource(r9)
            goto L_0x0150
        L_0x00f8:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0118
            android.view.View r5 = r5.findViewById(r10)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x0118
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r11 = r1.this$0
            android.content.Context r11 = r11.requireContext()
            r12 = 2131099825(0x7f0600b1, float:1.7812014E38)
            int r11 = androidx.core.content.ContextCompat.getColor(r11, r12)
            r5.setTextColor(r11)
        L_0x0118:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x013a
            android.view.View r5 = r5.findViewById(r10)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x013a
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r10 = r1.this$0
            android.content.res.Resources r10 = r10.getResources()
            r11 = 2131952132(0x7f130204, float:1.9540698E38)
            java.lang.String r10 = r10.getString(r11)
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10
            r5.setText(r10)
        L_0x013a:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0150
            android.view.View r5 = r5.findViewById(r9)
            android.widget.ImageView r5 = (android.widget.ImageView) r5
            if (r5 == 0) goto L_0x0150
            r9 = 2131231152(0x7f0801b0, float:1.8078377E38)
            r5.setImageResource(r9)
        L_0x0150:
            com.bitcoin.bitcoink.tx.Satoshis r5 = r2.getFees()
            java.math.BigDecimal r5 = r5.getCoins()
            double r9 = r5.doubleValue()
            r11 = 0
            r5 = 2131362094(0x7f0a012e, float:1.8343959E38)
            r13 = 2131362093(0x7f0a012d, float:1.8343957E38)
            r14 = 8
            int r15 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r15 != 0) goto L_0x018f
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r9 = r1.this$0
            android.view.View r9 = r9.getView()
            if (r9 == 0) goto L_0x017d
            android.view.View r9 = r9.findViewById(r13)
            android.widget.LinearLayout r9 = (android.widget.LinearLayout) r9
            if (r9 == 0) goto L_0x017d
            r9.setVisibility(r14)
        L_0x017d:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r9 = r1.this$0
            android.view.View r9 = r9.getView()
            if (r9 == 0) goto L_0x01b3
            android.view.View r5 = r9.findViewById(r5)
            if (r5 == 0) goto L_0x01b3
            r5.setVisibility(r14)
            goto L_0x01b3
        L_0x018f:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r9 = r1.this$0
            android.view.View r9 = r9.getView()
            if (r9 == 0) goto L_0x01a2
            android.view.View r9 = r9.findViewById(r13)
            android.widget.LinearLayout r9 = (android.widget.LinearLayout) r9
            if (r9 == 0) goto L_0x01a2
            r9.setVisibility(r7)
        L_0x01a2:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r9 = r1.this$0
            android.view.View r9 = r9.getView()
            if (r9 == 0) goto L_0x01b3
            android.view.View r5 = r9.findViewById(r5)
            if (r5 == 0) goto L_0x01b3
            r5.setVisibility(r7)
        L_0x01b3:
            com.bitcoin.mwallet.core.models.slp.SlpId r5 = r2.getTokenId()
            java.lang.String r5 = r5.getId()
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            int r5 = r5.length()
            if (r5 != 0) goto L_0x01c5
            r5 = 1
            goto L_0x01c6
        L_0x01c5:
            r5 = 0
        L_0x01c6:
            r9 = 2131362706(0x7f0a0392, float:1.83452E38)
            r10 = 2131362711(0x7f0a0397, float:1.834521E38)
            if (r5 == 0) goto L_0x021e
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x01f7
            android.view.View r5 = r5.findViewById(r9)
            androidx.appcompat.widget.AppCompatTextView r5 = (androidx.appcompat.widget.AppCompatTextView) r5
            if (r5 == 0) goto L_0x01f7
            com.bitcoin.bitcoink.tx.Satoshis r11 = r2.getSatoshis()
            java.math.BigDecimal r11 = r11.getCoins()
            com.bitcoin.mwallet.core.models.Coin r12 = r2.getCoin()
            java.lang.String r12 = r12.getTicker()
            java.lang.String r11 = com.bitcoin.mwallet.core.extensions.BigDecimalKt.toCoinString(r11, r12)
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            r5.setText(r11)
        L_0x01f7:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x020a
            android.view.View r5 = r5.findViewById(r9)
            androidx.appcompat.widget.AppCompatTextView r5 = (androidx.appcompat.widget.AppCompatTextView) r5
            if (r5 == 0) goto L_0x020a
            r5.setVisibility(r7)
        L_0x020a:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0231
            android.view.View r5 = r5.findViewById(r10)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x0231
            r5.setVisibility(r7)
            goto L_0x0231
        L_0x021e:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0231
            android.view.View r5 = r5.findViewById(r10)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x0231
            r5.setVisibility(r14)
        L_0x0231:
            com.bitcoin.mwallet.core.models.tx.TxAction r5 = r2.getAction()
            int[] r11 = com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView.WhenMappings.$EnumSwitchMapping$0
            int r5 = r5.ordinal()
            r5 = r11[r5]
            java.lang.String r11 = "(this as java.lang.String).toUpperCase()"
            if (r5 == r6) goto L_0x0265
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.content.res.Resources r5 = r5.getResources()
            r12 = 2131952126(0x7f1301fe, float:1.9540686E38)
            java.lang.String r5 = r5.getString(r12)
            java.lang.String r12 = "resources.getString(R.string.tx_details_from)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r12)
            if (r5 == 0) goto L_0x025d
            java.lang.String r5 = r5.toUpperCase()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r11)
            goto L_0x0280
        L_0x025d:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type java.lang.String"
            r0.<init>(r2)
            throw r0
        L_0x0265:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.content.res.Resources r5 = r5.getResources()
            r12 = 2131952131(0x7f130203, float:1.9540696E38)
            java.lang.String r5 = r5.getString(r12)
            java.lang.String r12 = "resources.getString(R.string.tx_details_to)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r12)
            if (r5 == 0) goto L_0x056d
            java.lang.String r5 = r5.toUpperCase()
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r11)
        L_0x0280:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r11 = r1.this$0
            android.view.View r11 = r11.getView()
            r12 = 2131362547(0x7f0a02f3, float:1.8344878E38)
            if (r11 == 0) goto L_0x029c
            android.view.View r11 = r11.findViewById(r12)
            android.widget.TextView r11 = (android.widget.TextView) r11
            if (r11 == 0) goto L_0x029c
            java.lang.String r13 = r2.getToAddress()
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            r11.setText(r13)
        L_0x029c:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r11 = r1.this$0
            android.view.View r11 = r11.getView()
            if (r11 == 0) goto L_0x02b4
            r13 = 2131362016(0x7f0a00e0, float:1.83438E38)
            android.view.View r11 = r11.findViewById(r13)
            android.widget.TextView r11 = (android.widget.TextView) r11
            if (r11 == 0) goto L_0x02b4
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r11.setText(r5)
        L_0x02b4:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            r11 = 2131362718(0x7f0a039e, float:1.8345224E38)
            if (r5 == 0) goto L_0x02d0
            android.view.View r5 = r5.findViewById(r11)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x02d0
            java.lang.String r13 = r0.getName()
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            r5.setText(r13)
        L_0x02d0:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            r13 = 2131362765(0x7f0a03cd, float:1.834532E38)
            if (r5 == 0) goto L_0x02f4
            android.view.View r5 = r5.findViewById(r13)
            android.widget.ImageView r5 = (android.widget.ImageView) r5
            if (r5 == 0) goto L_0x02f4
            com.bitcoin.mwallet.core.preferences.WalletPreference r15 = r0.preference()
            java.lang.String r15 = r15.getColor()
            int r15 = android.graphics.Color.parseColor(r15)
            android.graphics.PorterDuff$Mode r6 = android.graphics.PorterDuff.Mode.MULTIPLY
            r5.setColorFilter(r15, r6)
        L_0x02f4:
            com.bitcoin.mwallet.core.models.tx.TxAction r5 = r2.getAction()
            com.bitcoin.mwallet.core.models.tx.TxAction r6 = com.bitcoin.mwallet.core.models.p009tx.TxAction.SENT
            if (r5 == r6) goto L_0x033f
            com.bitcoin.mwallet.core.models.tx.TxAction r5 = r2.getAction()
            com.bitcoin.mwallet.core.models.tx.TxAction r6 = com.bitcoin.mwallet.core.models.p009tx.TxAction.RECEIVED
            if (r5 != r6) goto L_0x0305
            goto L_0x033f
        L_0x0305:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0318
            android.view.View r5 = r5.findViewById(r12)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x0318
            r5.setVisibility(r14)
        L_0x0318:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x032b
            android.view.View r5 = r5.findViewById(r11)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x032b
            r5.setVisibility(r7)
        L_0x032b:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0392
            android.view.View r5 = r5.findViewById(r13)
            android.widget.ImageView r5 = (android.widget.ImageView) r5
            if (r5 == 0) goto L_0x0392
            r5.setVisibility(r7)
            goto L_0x0392
        L_0x033f:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0352
            android.view.View r5 = r5.findViewById(r12)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x0352
            r5.setVisibility(r7)
        L_0x0352:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0365
            android.view.View r5 = r5.findViewById(r11)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x0365
            r5.setVisibility(r14)
        L_0x0365:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0378
            android.view.View r5 = r5.findViewById(r13)
            android.widget.ImageView r5 = (android.widget.ImageView) r5
            if (r5 == 0) goto L_0x0378
            r5.setVisibility(r14)
        L_0x0378:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0392
            android.view.View r5 = r5.findViewById(r12)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x0392
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView$bindObservers$1$2 r6 = new com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView$bindObservers$1$2
            r6.<init>(r1, r2)
            android.view.View$OnClickListener r6 = (android.view.View.OnClickListener) r6
            r5.setOnClickListener(r6)
        L_0x0392:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x03ae
            r6 = 2131362033(0x7f0a00f1, float:1.8343835E38)
            android.view.View r5 = r5.findViewById(r6)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x03ae
            java.lang.String r6 = r2.toLocalTimeString()
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r5.setText(r6)
        L_0x03ae:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x03da
            r6 = 2131362096(0x7f0a0130, float:1.8343963E38)
            android.view.View r5 = r5.findViewById(r6)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x03da
            com.bitcoin.bitcoink.tx.Satoshis r6 = r2.getFees()
            java.math.BigDecimal r6 = r6.getCoins()
            com.bitcoin.mwallet.core.models.Coin r11 = r2.getCoin()
            java.lang.String r11 = r11.getTicker()
            java.lang.String r6 = com.bitcoin.mwallet.core.extensions.BigDecimalKt.toCoinString(r6, r11)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r5.setText(r6)
        L_0x03da:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0408
            android.view.View r5 = r5.findViewById(r10)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x0408
            com.bitcoin.bitcoink.tx.Satoshis r6 = r2.getSatoshis()
            java.math.BigDecimal r6 = r6.getCoins()
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r11 = r1.this$0
            com.bitcoin.mwallet.app.viper.ScreenPresenter r11 = r11.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter r11 = (com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter) r11
            java.util.Currency r11 = r11.getDefaultCurrency()
            r12 = 2
            java.lang.String r6 = com.bitcoin.mwallet.core.extensions.BigDecimalKt.toFiatString$default(r6, r11, r7, r12, r3)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r5.setText(r6)
        L_0x0408:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 == 0) goto L_0x0425
            r6 = 2131362753(0x7f0a03c1, float:1.8345295E38)
            android.view.View r5 = r5.findViewById(r6)
            android.widget.TextView r5 = (android.widget.TextView) r5
            if (r5 == 0) goto L_0x0425
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView$bindObservers$1$3 r6 = new com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView$bindObservers$1$3
            r6.<init>(r1, r2)
            android.view.View$OnClickListener r6 = (android.view.View.OnClickListener) r6
            r5.setOnClickListener(r6)
        L_0x0425:
            java.lang.String r5 = r2.getNote()
            java.lang.String r6 = r2.getNote()     // Catch:{ Exception -> 0x044e }
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ Exception -> 0x044e }
            if (r6 == 0) goto L_0x043a
            int r6 = r6.length()     // Catch:{ Exception -> 0x044e }
            if (r6 != 0) goto L_0x0438
            goto L_0x043a
        L_0x0438:
            r6 = 0
            goto L_0x043b
        L_0x043a:
            r6 = 1
        L_0x043b:
            if (r6 != 0) goto L_0x0454
            com.bitcoin.mwallet.core.utils.signature.SigningKey r0 = r0.getSigningKey()     // Catch:{ Exception -> 0x044e }
            java.lang.String r0 = r0.getPrivateKey()     // Catch:{ Exception -> 0x044e }
            java.lang.String r6 = r2.getNote()     // Catch:{ Exception -> 0x044e }
            java.lang.String r5 = walletutils.Walletutils.decrypt(r0, r6)     // Catch:{ Exception -> 0x044e }
            goto L_0x0454
        L_0x044e:
            r0 = move-exception
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            timber.log.Timber.m427d(r0)
        L_0x0454:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r0 = r1.this$0
            android.view.View r0 = r0.getView()
            if (r0 == 0) goto L_0x0478
            r6 = 2131362246(0x7f0a01c6, float:1.8344267E38)
            android.view.View r0 = r0.findViewById(r6)
            android.widget.TextView r0 = (android.widget.TextView) r0
            if (r0 == 0) goto L_0x0478
            android.text.SpannableStringBuilder r6 = new android.text.SpannableStringBuilder
            if (r5 == 0) goto L_0x046c
            goto L_0x046e
        L_0x046c:
            java.lang.String r5 = ""
        L_0x046e:
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r6.<init>(r5)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r0.setText(r6)
        L_0x0478:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r0 = r1.this$0
            com.bitcoin.mwallet.app.viper.ScreenPresenter r0 = r0.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter r0 = (com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter) r0
            java.lang.String r5 = r2.getNote()
            if (r5 == 0) goto L_0x0487
            goto L_0x0489
        L_0x0487:
            java.lang.String r5 = ""
        L_0x0489:
            r0.setOriginalNoteText(r5)
            com.bitcoin.mwallet.core.models.slp.SlpId r0 = r2.getTokenId()
            java.lang.String r0 = r0.getId()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L_0x049e
            r0 = 1
            goto L_0x049f
        L_0x049e:
            r0 = 0
        L_0x049f:
            if (r0 == 0) goto L_0x04d9
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r0 = r1.this$0
            com.bitcoin.mwallet.app.viper.ScreenPresenter r0 = r0.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter r0 = (com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter) r0
            com.bitcoin.mwallet.core.models.Coin r4 = r2.getCoin()
            com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate r0 = r0.getFiatBalance(r4)
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r4 = r1.this$0
            android.view.View r4 = r4.getView()
            if (r4 == 0) goto L_0x056c
            android.view.View r4 = r4.findViewById(r10)
            android.widget.TextView r4 = (android.widget.TextView) r4
            if (r4 == 0) goto L_0x056c
            com.bitcoin.bitcoink.tx.Satoshis r2 = r2.getSatoshis()
            java.math.BigDecimal r2 = r0.toFiat(r2)
            java.util.Currency r0 = r0.getFiatCurrency()
            r5 = 2
            java.lang.String r0 = com.bitcoin.mwallet.core.extensions.BigDecimalKt.toFiatString$default(r2, r0, r7, r5, r3)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r4.setText(r0)
            goto L_0x056c
        L_0x04d9:
            if (r4 == 0) goto L_0x056c
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r0 = r1.this$0
            android.view.View r0 = r0.getView()
            if (r0 == 0) goto L_0x04fd
            android.view.View r0 = r0.findViewById(r9)
            androidx.appcompat.widget.AppCompatTextView r0 = (androidx.appcompat.widget.AppCompatTextView) r0
            if (r0 == 0) goto L_0x04fd
            java.math.BigDecimal r5 = new java.math.BigDecimal
            long r6 = r2.getTokenAmount()
            r5.<init>(r6)
            java.lang.String r2 = r4.toTickerAmount(r5)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r0.setText(r2)
        L_0x04fd:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r0 = r1.this$0
            android.view.View r0 = r0.getView()
            if (r0 == 0) goto L_0x050c
            android.view.View r0 = r0.findViewById(r8)
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            goto L_0x050d
        L_0x050c:
            r0 = r3
        L_0x050d:
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r2 = r1.this$0
            com.bitcoin.mwallet.app.viper.ScreenPresenter r2 = r2.getPresenter()
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter r2 = (com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsPresenter) r2
            com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor r2 = r2.getVerifiedTokenInteractor()
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r5 = r1.this$0
            android.view.View r5 = r5.getView()
            if (r5 != 0) goto L_0x0524
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0524:
            java.lang.String r6 = "view!!"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            android.content.res.Resources r5 = r5.getResources()
            java.lang.String r6 = "view!!.resources"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
            if (r0 != 0) goto L_0x0537
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0537:
            com.bitcoin.mwallet.core.models.slp.SlpId r4 = r4.getTokenId()
            boolean r2 = r2.trySetVerifiedTokenImage(r5, r0, r4)
            if (r2 != 0) goto L_0x056c
            r2 = 2131231142(0x7f0801a6, float:1.8078357E38)
            r0.setImageResource(r2)
            com.bitcoin.mwallet.core.bitmap.BitmapUtils$Companion r2 = com.bitcoin.mwallet.core.bitmap.BitmapUtils.Companion
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r4 = r1.this$0
            android.content.Context r4 = r4.getContext()
            if (r4 == 0) goto L_0x0558
            r3 = 2131231142(0x7f0801a6, float:1.8078357E38)
            android.graphics.drawable.Drawable r3 = r4.getDrawable(r3)
        L_0x0558:
            if (r3 != 0) goto L_0x055d
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x055d:
            android.graphics.Bitmap r2 = r2.drawableToBitmap(r3)
            com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView r3 = r1.this$0
            androidx.core.graphics.drawable.RoundedBitmapDrawable r2 = r3.getImageDrawable(r2)
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
            r0.setImageDrawable(r2)
        L_0x056c:
            return
        L_0x056d:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type java.lang.String"
            r0.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.txdetails.TxDetailsView$bindObservers$1.onChanged(kotlin.Pair):void");
    }
}
