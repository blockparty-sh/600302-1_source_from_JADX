package com.bitcoin.mwallet.core.views.transaction;

import android.content.Context;
import com.bitcoin.mwallet.core.models.p009tx.TxAction;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.leanplum.internal.Constants.Params;
import java.util.Date;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0005\b\b\u0018\u0000 72\u00020\u0001:\u00017Bi\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\f\u001a\u00020\b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0013J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\bHÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010/\u001a\u00020\bHÆ\u0003J\t\u00100\u001a\u00020\u000eHÆ\u0003J\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÆ\u0001J\u0013\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u00020\bHÖ\u0001J\t\u00106\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019¨\u00068"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/views/transaction/TransactionSummaryView;", "", "txId", "", "action", "Lcom/bitcoin/mwallet/core/models/tx/TxAction;", "counterparty", "iconResourceId", "", "iconContentDescription", "description", "memo", "colorId", "time", "Ljava/util/Date;", "primaryValue", "secondaryValue", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "(Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/tx/TxAction;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/util/Date;Ljava/lang/String;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/slp/SlpId;)V", "getAction", "()Lcom/bitcoin/mwallet/core/models/tx/TxAction;", "getColorId", "()I", "getCounterparty", "()Ljava/lang/String;", "getDescription", "getIconContentDescription", "getIconResourceId", "getMemo", "getPrimaryValue", "getSecondaryValue", "getTime", "()Ljava/util/Date;", "getTokenId", "()Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "getTxId", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TransactionSummaryView.kt */
public final class TransactionSummaryView {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final TxAction action;
    private final int colorId;
    @NotNull
    private final String counterparty;
    @NotNull
    private final String description;
    @NotNull
    private final String iconContentDescription;
    private final int iconResourceId;
    @Nullable
    private final String memo;
    @NotNull
    private final String primaryValue;
    @NotNull
    private final String secondaryValue;
    @NotNull
    private final Date time;
    @Nullable
    private final SlpId tokenId;
    @NotNull
    private final String txId;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JH\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\u0006\u0010\f\u001a\u00020\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0004¨\u0006\u0010"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/views/transaction/TransactionSummaryView$Companion;", "", "()V", "transactionSummaries", "", "Lcom/bitcoin/mwallet/core/views/transaction/TransactionSummaryView;", "fiatExchangeRates", "", "Lcom/bitcoin/mwallet/core/models/Coin;", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "txs", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "context", "Landroid/content/Context;", "tokens", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: TransactionSummaryView.kt */
    public static final class Companion {

        @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TxAction.values().length];

            static {
                $EnumSwitchMapping$0[TxAction.SENT.ordinal()] = 1;
                $EnumSwitchMapping$0[TxAction.RECEIVED.ordinal()] = 2;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public static /* synthetic */ List transactionSummaries$default(Companion companion, Map map, List list, Context context, List list2, int i, Object obj) {
            if ((i & 8) != 0) {
                list2 = null;
            }
            return companion.transactionSummaries(map, list, context, list2);
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x00d3  */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x00d6  */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x0132  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0141  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x0195  */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x0198  */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x019d  */
        @org.jetbrains.annotations.NotNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.util.List<com.bitcoin.mwallet.core.views.transaction.TransactionSummaryView> transactionSummaries(@org.jetbrains.annotations.NotNull java.util.Map<com.bitcoin.mwallet.core.models.Coin, com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate> r25, @org.jetbrains.annotations.NotNull java.util.List<com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction> r26, @org.jetbrains.annotations.NotNull android.content.Context r27, @org.jetbrains.annotations.Nullable java.util.List<com.bitcoin.mwallet.core.models.slp.Slp> r28) {
            /*
                r24 = this;
                r0 = r25
                r1 = r26
                java.lang.String r2 = "fiatExchangeRates"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r2)
                java.lang.String r2 = "txs"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r2)
                java.lang.String r2 = "context"
                r3 = r27
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r2)
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                java.util.ArrayList r2 = new java.util.ArrayList
                r4 = 10
                int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r1, r4)
                r2.<init>(r4)
                java.util.Collection r2 = (java.util.Collection) r2
                java.util.Iterator r1 = r1.iterator()
            L_0x0028:
                boolean r4 = r1.hasNext()
                if (r4 == 0) goto L_0x01cb
                java.lang.Object r4 = r1.next()
                com.bitcoin.mwallet.core.models.tx.history.HistoricTransaction r4 = (com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction) r4
                com.bitcoin.mwallet.core.models.tx.TxAction r5 = r4.getAction()
                int[] r6 = com.bitcoin.mwallet.core.views.transaction.TransactionSummaryView.Companion.WhenMappings.$EnumSwitchMapping$0
                int r5 = r5.ordinal()
                r5 = r6[r5]
                r6 = 2131099920(0x7f060110, float:1.7812207E38)
                r7 = 2
                r8 = 1
                if (r5 == r8) goto L_0x00a4
                if (r5 == r7) goto L_0x0075
                android.content.res.Resources r5 = r27.getResources()
                r9 = 2131951821(0x7f1300cd, float:1.9540067E38)
                java.lang.String r5 = r5.getString(r9)
                java.lang.String r9 = "context.resources.getStr…tion_moved_amount_format)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r9)
                android.content.res.Resources r9 = r27.getResources()
                r10 = 2131951819(0x7f1300cb, float:1.9540063E38)
                java.lang.String r9 = r9.getString(r10)
                java.lang.String r10 = "context.resources.getStr…moved_action_description)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r10)
                r10 = 2131231148(0x7f0801ac, float:1.8078369E38)
                r16 = r9
                r15 = 2131231148(0x7f0801ac, float:1.8078369E38)
            L_0x0071:
                r19 = 2131099920(0x7f060110, float:1.7812207E38)
                goto L_0x00cd
            L_0x0075:
                android.content.res.Resources r5 = r27.getResources()
                r6 = 2131951824(0x7f1300d0, float:1.9540073E38)
                java.lang.String r5 = r5.getString(r6)
                java.lang.String r6 = "context.resources.getStr…n_received_amount_format)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r6)
                android.content.res.Resources r6 = r27.getResources()
                r9 = 2131951822(0x7f1300ce, float:1.954007E38)
                java.lang.String r9 = r6.getString(r9)
                java.lang.String r6 = "context.resources.getStr…eived_action_description)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r6)
                r10 = 2131231149(0x7f0801ad, float:1.807837E38)
                r6 = 2131099919(0x7f06010f, float:1.7812205E38)
                r16 = r9
                r15 = 2131231149(0x7f0801ad, float:1.807837E38)
                r19 = 2131099919(0x7f06010f, float:1.7812205E38)
                goto L_0x00cd
            L_0x00a4:
                android.content.res.Resources r5 = r27.getResources()
                r9 = 2131951827(0x7f1300d3, float:1.954008E38)
                java.lang.String r5 = r5.getString(r9)
                java.lang.String r9 = "context.resources.getStr…ction_sent_amount_format)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r9)
                android.content.res.Resources r9 = r27.getResources()
                r10 = 2131951825(0x7f1300d1, float:1.9540075E38)
                java.lang.String r9 = r9.getString(r10)
                java.lang.String r10 = "context.resources.getStr…_sent_action_description)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r10)
                r10 = 2131231151(0x7f0801af, float:1.8078375E38)
                r16 = r9
                r15 = 2131231151(0x7f0801af, float:1.8078375E38)
                goto L_0x0071
            L_0x00cd:
                java.lang.String r6 = r4.getNote()
                if (r6 == 0) goto L_0x00d6
                r17 = r6
                goto L_0x00d8
            L_0x00d6:
                r17 = r16
            L_0x00d8:
                com.bitcoin.bitcoink.tx.Satoshis r6 = r4.getSatoshis()
                java.math.BigDecimal r6 = r6.getCoins()
                com.bitcoin.mwallet.core.models.Coin r9 = r4.getCoin()
                java.lang.Object r9 = r0.get(r9)
                com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate r9 = (com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate) r9
                r10 = 0
                r12 = 0
                r13 = 0
                if (r9 == 0) goto L_0x0126
                long r20 = r4.getTokenAmount()
                int r14 = (r20 > r10 ? 1 : (r20 == r10 ? 0 : -1))
                if (r14 != 0) goto L_0x0126
                java.math.BigDecimal r14 = r9.getRate()
                java.math.BigDecimal r14 = r6.multiply(r14)
                java.math.BigDecimal r14 = r14.abs()
                java.lang.String r10 = "coinAmount.multiply(fiatExchangeRate.rate).abs()"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r14, r10)
                java.util.Currency r9 = r9.getFiatCurrency()
                java.lang.String r7 = com.bitcoin.mwallet.core.extensions.BigDecimalKt.toFiatString$default(r14, r9, r13, r7, r12)
                kotlin.jvm.internal.StringCompanionObject r9 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
                java.lang.Object[] r9 = new java.lang.Object[r8]
                r9[r13] = r7
                int r7 = r9.length
                java.lang.Object[] r7 = java.util.Arrays.copyOf(r9, r7)
                java.lang.String r5 = java.lang.String.format(r5, r7)
                java.lang.String r7 = "java.lang.String.format(format, *args)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r7)
                goto L_0x0128
            L_0x0126:
                java.lang.String r5 = "--"
            L_0x0128:
                long r9 = r4.getTokenAmount()
                r20 = 0
                int r7 = (r9 > r20 ? 1 : (r9 == r20 ? 0 : -1))
                if (r7 != 0) goto L_0x0141
                com.bitcoin.mwallet.core.models.Coin r7 = r4.getCoin()
                java.lang.String r7 = r7.getTicker()
                java.lang.String r6 = com.bitcoin.mwallet.core.extensions.BigDecimalKt.toCoinString(r6, r7)
            L_0x013e:
                r22 = r6
                goto L_0x0181
            L_0x0141:
                if (r28 == 0) goto L_0x017e
                r6 = r28
                java.lang.Iterable r6 = (java.lang.Iterable) r6
                java.util.Iterator r6 = r6.iterator()
            L_0x014b:
                boolean r7 = r6.hasNext()
                if (r7 == 0) goto L_0x0167
                java.lang.Object r7 = r6.next()
                r9 = r7
                com.bitcoin.mwallet.core.models.slp.Slp r9 = (com.bitcoin.mwallet.core.models.slp.Slp) r9
                com.bitcoin.mwallet.core.models.slp.SlpId r9 = r9.getTokenId()
                com.bitcoin.mwallet.core.models.slp.SlpId r10 = r4.getTokenId()
                boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r10)
                if (r9 == 0) goto L_0x014b
                goto L_0x0168
            L_0x0167:
                r7 = r12
            L_0x0168:
                com.bitcoin.mwallet.core.models.slp.Slp r7 = (com.bitcoin.mwallet.core.models.slp.Slp) r7
                if (r7 == 0) goto L_0x017e
                long r9 = r4.getTokenAmount()
                java.math.BigDecimal r6 = java.math.BigDecimal.valueOf(r9)
                java.lang.String r9 = "BigDecimal.valueOf(this)"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r9)
                java.lang.String r6 = r7.toTickerAmount(r6)
                goto L_0x013e
            L_0x017e:
                java.lang.String r6 = ""
                goto L_0x013e
            L_0x0181:
                com.bitcoin.mwallet.core.models.slp.SlpId r6 = r4.getTokenId()
                com.bitcoin.mwallet.core.models.slp.SlpId r7 = r4.getTokenId()
                java.lang.String r7 = r7.toString()
                java.lang.CharSequence r7 = (java.lang.CharSequence) r7
                int r7 = r7.length()
                if (r7 != 0) goto L_0x0196
                r13 = 1
            L_0x0196:
                if (r13 == 0) goto L_0x019d
                com.bitcoin.mwallet.core.models.slp.SlpId r12 = (com.bitcoin.mwallet.core.models.slp.SlpId) r12
                r23 = r12
                goto L_0x019f
            L_0x019d:
                r23 = r6
            L_0x019f:
                com.bitcoin.mwallet.core.views.transaction.TransactionSummaryView r6 = new com.bitcoin.mwallet.core.views.transaction.TransactionSummaryView
                com.bitcoin.bitcoink.tx.TxId r7 = r4.getTxId()
                java.lang.String r12 = r7.getId()
                com.bitcoin.mwallet.core.models.tx.TxAction r13 = r4.getAction()
                java.lang.String r14 = r4.getToAddress()
                java.lang.String r18 = r4.getNote()
                java.util.Date r7 = new java.util.Date
                long r8 = r4.getTimestamp()
                r7.<init>(r8)
                r11 = r6
                r20 = r7
                r21 = r5
                r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
                r2.add(r6)
                goto L_0x0028
            L_0x01cb:
                java.util.List r2 = (java.util.List) r2
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.views.transaction.TransactionSummaryView.Companion.transactionSummaries(java.util.Map, java.util.List, android.content.Context, java.util.List):java.util.List");
        }
    }

    @NotNull
    public static /* synthetic */ TransactionSummaryView copy$default(TransactionSummaryView transactionSummaryView, String str, TxAction txAction, String str2, int i, String str3, String str4, String str5, int i2, Date date, String str6, String str7, SlpId slpId, int i3, Object obj) {
        TransactionSummaryView transactionSummaryView2 = transactionSummaryView;
        int i4 = i3;
        return transactionSummaryView.copy((i4 & 1) != 0 ? transactionSummaryView2.txId : str, (i4 & 2) != 0 ? transactionSummaryView2.action : txAction, (i4 & 4) != 0 ? transactionSummaryView2.counterparty : str2, (i4 & 8) != 0 ? transactionSummaryView2.iconResourceId : i, (i4 & 16) != 0 ? transactionSummaryView2.iconContentDescription : str3, (i4 & 32) != 0 ? transactionSummaryView2.description : str4, (i4 & 64) != 0 ? transactionSummaryView2.memo : str5, (i4 & 128) != 0 ? transactionSummaryView2.colorId : i2, (i4 & 256) != 0 ? transactionSummaryView2.time : date, (i4 & 512) != 0 ? transactionSummaryView2.primaryValue : str6, (i4 & 1024) != 0 ? transactionSummaryView2.secondaryValue : str7, (i4 & 2048) != 0 ? transactionSummaryView2.tokenId : slpId);
    }

    @NotNull
    public final String component1() {
        return this.txId;
    }

    @NotNull
    public final String component10() {
        return this.primaryValue;
    }

    @NotNull
    public final String component11() {
        return this.secondaryValue;
    }

    @Nullable
    public final SlpId component12() {
        return this.tokenId;
    }

    @NotNull
    public final TxAction component2() {
        return this.action;
    }

    @NotNull
    public final String component3() {
        return this.counterparty;
    }

    public final int component4() {
        return this.iconResourceId;
    }

    @NotNull
    public final String component5() {
        return this.iconContentDescription;
    }

    @NotNull
    public final String component6() {
        return this.description;
    }

    @Nullable
    public final String component7() {
        return this.memo;
    }

    public final int component8() {
        return this.colorId;
    }

    @NotNull
    public final Date component9() {
        return this.time;
    }

    @NotNull
    public final TransactionSummaryView copy(@NotNull String str, @NotNull TxAction txAction, @NotNull String str2, int i, @NotNull String str3, @NotNull String str4, @Nullable String str5, int i2, @NotNull Date date, @NotNull String str6, @NotNull String str7, @Nullable SlpId slpId) {
        String str8 = str;
        Intrinsics.checkParameterIsNotNull(str, "txId");
        TxAction txAction2 = txAction;
        Intrinsics.checkParameterIsNotNull(txAction2, "action");
        String str9 = str2;
        Intrinsics.checkParameterIsNotNull(str9, "counterparty");
        String str10 = str3;
        Intrinsics.checkParameterIsNotNull(str10, "iconContentDescription");
        String str11 = str4;
        Intrinsics.checkParameterIsNotNull(str11, "description");
        Date date2 = date;
        Intrinsics.checkParameterIsNotNull(date2, Params.TIME);
        String str12 = str6;
        Intrinsics.checkParameterIsNotNull(str12, "primaryValue");
        String str13 = str7;
        Intrinsics.checkParameterIsNotNull(str13, "secondaryValue");
        TransactionSummaryView transactionSummaryView = new TransactionSummaryView(str8, txAction2, str9, i, str10, str11, str5, i2, date2, str12, str13, slpId);
        return transactionSummaryView;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof TransactionSummaryView) {
                TransactionSummaryView transactionSummaryView = (TransactionSummaryView) obj;
                if (Intrinsics.areEqual((Object) this.txId, (Object) transactionSummaryView.txId) && Intrinsics.areEqual((Object) this.action, (Object) transactionSummaryView.action) && Intrinsics.areEqual((Object) this.counterparty, (Object) transactionSummaryView.counterparty)) {
                    if ((this.iconResourceId == transactionSummaryView.iconResourceId) && Intrinsics.areEqual((Object) this.iconContentDescription, (Object) transactionSummaryView.iconContentDescription) && Intrinsics.areEqual((Object) this.description, (Object) transactionSummaryView.description) && Intrinsics.areEqual((Object) this.memo, (Object) transactionSummaryView.memo)) {
                        if (!(this.colorId == transactionSummaryView.colorId) || !Intrinsics.areEqual((Object) this.time, (Object) transactionSummaryView.time) || !Intrinsics.areEqual((Object) this.primaryValue, (Object) transactionSummaryView.primaryValue) || !Intrinsics.areEqual((Object) this.secondaryValue, (Object) transactionSummaryView.secondaryValue) || !Intrinsics.areEqual((Object) this.tokenId, (Object) transactionSummaryView.tokenId)) {
                            return false;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.txId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        TxAction txAction = this.action;
        int hashCode2 = (hashCode + (txAction != null ? txAction.hashCode() : 0)) * 31;
        String str2 = this.counterparty;
        int hashCode3 = (((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.iconResourceId) * 31;
        String str3 = this.iconContentDescription;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.description;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.memo;
        int hashCode6 = (((hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31) + this.colorId) * 31;
        Date date = this.time;
        int hashCode7 = (hashCode6 + (date != null ? date.hashCode() : 0)) * 31;
        String str6 = this.primaryValue;
        int hashCode8 = (hashCode7 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.secondaryValue;
        int hashCode9 = (hashCode8 + (str7 != null ? str7.hashCode() : 0)) * 31;
        SlpId slpId = this.tokenId;
        if (slpId != null) {
            i = slpId.hashCode();
        }
        return hashCode9 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TransactionSummaryView(txId=");
        sb.append(this.txId);
        sb.append(", action=");
        sb.append(this.action);
        sb.append(", counterparty=");
        sb.append(this.counterparty);
        sb.append(", iconResourceId=");
        sb.append(this.iconResourceId);
        sb.append(", iconContentDescription=");
        sb.append(this.iconContentDescription);
        sb.append(", description=");
        sb.append(this.description);
        sb.append(", memo=");
        sb.append(this.memo);
        sb.append(", colorId=");
        sb.append(this.colorId);
        sb.append(", time=");
        sb.append(this.time);
        sb.append(", primaryValue=");
        sb.append(this.primaryValue);
        sb.append(", secondaryValue=");
        sb.append(this.secondaryValue);
        sb.append(", tokenId=");
        sb.append(this.tokenId);
        sb.append(")");
        return sb.toString();
    }

    public TransactionSummaryView(@NotNull String str, @NotNull TxAction txAction, @NotNull String str2, int i, @NotNull String str3, @NotNull String str4, @Nullable String str5, int i2, @NotNull Date date, @NotNull String str6, @NotNull String str7, @Nullable SlpId slpId) {
        Intrinsics.checkParameterIsNotNull(str, "txId");
        Intrinsics.checkParameterIsNotNull(txAction, "action");
        Intrinsics.checkParameterIsNotNull(str2, "counterparty");
        Intrinsics.checkParameterIsNotNull(str3, "iconContentDescription");
        Intrinsics.checkParameterIsNotNull(str4, "description");
        Intrinsics.checkParameterIsNotNull(date, Params.TIME);
        Intrinsics.checkParameterIsNotNull(str6, "primaryValue");
        Intrinsics.checkParameterIsNotNull(str7, "secondaryValue");
        this.txId = str;
        this.action = txAction;
        this.counterparty = str2;
        this.iconResourceId = i;
        this.iconContentDescription = str3;
        this.description = str4;
        this.memo = str5;
        this.colorId = i2;
        this.time = date;
        this.primaryValue = str6;
        this.secondaryValue = str7;
        this.tokenId = slpId;
    }

    @NotNull
    public final String getTxId() {
        return this.txId;
    }

    @NotNull
    public final TxAction getAction() {
        return this.action;
    }

    @NotNull
    public final String getCounterparty() {
        return this.counterparty;
    }

    public final int getIconResourceId() {
        return this.iconResourceId;
    }

    @NotNull
    public final String getIconContentDescription() {
        return this.iconContentDescription;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final String getMemo() {
        return this.memo;
    }

    public final int getColorId() {
        return this.colorId;
    }

    @NotNull
    public final Date getTime() {
        return this.time;
    }

    @NotNull
    public final String getPrimaryValue() {
        return this.primaryValue;
    }

    @NotNull
    public final String getSecondaryValue() {
        return this.secondaryValue;
    }

    @Nullable
    public final SlpId getTokenId() {
        return this.tokenId;
    }
}
