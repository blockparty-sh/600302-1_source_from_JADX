package com.bitcoin.mwallet.core.services.eventstream;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bitcoin.mwallet.ApplicationClass;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.Token;
import com.bitcoin.mwallet.TxHistory;
import com.bitcoin.mwallet.Utxo;
import com.bitcoin.mwallet.WalletUtxos;
import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.p009tx.TxAction;
import com.bitcoin.mwallet.core.models.p009tx.history.HistoricTransaction;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxo;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.SlpRepository;
import com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.services.AnalyticsService;
import com.bitcoin.mwallet.core.services.eventstream.EventStreamServiceGrpc.EventStreamSubscriber;
import com.bitcoin.mwallet.core.services.grpc.ProtoConverterKt;
import com.tapadoo.alerter.Alerter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.bitcoinj.uri.BitcoinURI;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$H\u0016J\u0006\u0010%\u001a\u00020\u001fJ\u0010\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020(H\u0002R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006)"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamHandler;", "Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamServiceGrpc$EventStreamSubscriber;", "eventStreamService", "Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamServiceGrpc;", "txHistoryRepository", "Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;", "utxoRepository", "Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "slpRepository", "Lcom/bitcoin/mwallet/core/repositories/SlpRepository;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "application", "Landroid/app/Application;", "analyticsService", "Lcom/bitcoin/mwallet/core/services/AnalyticsService;", "verifiedTokenInteractor", "Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;", "(Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamServiceGrpc;Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;Lcom/bitcoin/mwallet/core/repositories/SlpRepository;Lcom/bitcoin/mwallet/core/repositories/WalletRepository;Landroid/app/Application;Lcom/bitcoin/mwallet/core/services/AnalyticsService;Lcom/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor;)V", "txHistorySubscription", "Ljava/util/concurrent/atomic/AtomicReference;", "Lcom/bitcoin/mwallet/core/services/eventstream/EventStreamReceivedTxSubscriber;", "getTxHistorySubscription", "()Ljava/util/concurrent/atomic/AtomicReference;", "wallets", "", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "getWallets", "()Ljava/util/Map;", "onTxHistory", "", "txHistory", "Lcom/bitcoin/mwallet/TxHistory;", "onWalletUtxos", "walletUtxos", "Lcom/bitcoin/mwallet/WalletUtxos;", "restartStream", "showNotification", "tx", "Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: EventStreamHandler.kt */
public class EventStreamHandler implements EventStreamSubscriber {
    private final AnalyticsService analyticsService;
    private final Application application;
    /* access modifiers changed from: private */
    public final EventStreamServiceGrpc eventStreamService;
    /* access modifiers changed from: private */
    public final SlpRepository slpRepository;
    /* access modifiers changed from: private */
    public final TransactionHistoryRepository txHistoryRepository;
    @NotNull
    private final AtomicReference<EventStreamReceivedTxSubscriber> txHistorySubscription = new AtomicReference<>();
    /* access modifiers changed from: private */
    public final UtxoRepository utxoRepository;
    /* access modifiers changed from: private */
    public final VerifiedTokenInteractor verifiedTokenInteractor;
    /* access modifiers changed from: private */
    public final WalletRepository walletRepository;
    @NotNull
    private final Map<WalletId, C1261Wallet> wallets = new HashMap();

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
        }
    }

    public EventStreamHandler(@NotNull EventStreamServiceGrpc eventStreamServiceGrpc, @NotNull TransactionHistoryRepository transactionHistoryRepository, @NotNull UtxoRepository utxoRepository2, @NotNull SlpRepository slpRepository2, @NotNull WalletRepository walletRepository2, @NotNull Application application2, @NotNull AnalyticsService analyticsService2, @NotNull VerifiedTokenInteractor verifiedTokenInteractor2) {
        Intrinsics.checkParameterIsNotNull(eventStreamServiceGrpc, "eventStreamService");
        Intrinsics.checkParameterIsNotNull(transactionHistoryRepository, "txHistoryRepository");
        Intrinsics.checkParameterIsNotNull(utxoRepository2, "utxoRepository");
        Intrinsics.checkParameterIsNotNull(slpRepository2, "slpRepository");
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        Intrinsics.checkParameterIsNotNull(application2, "application");
        Intrinsics.checkParameterIsNotNull(analyticsService2, "analyticsService");
        Intrinsics.checkParameterIsNotNull(verifiedTokenInteractor2, "verifiedTokenInteractor");
        this.eventStreamService = eventStreamServiceGrpc;
        this.txHistoryRepository = transactionHistoryRepository;
        this.utxoRepository = utxoRepository2;
        this.slpRepository = slpRepository2;
        this.walletRepository = walletRepository2;
        this.application = application2;
        this.analyticsService = analyticsService2;
        this.verifiedTokenInteractor = verifiedTokenInteractor2;
        restartStream();
    }

    @NotNull
    public final Map<WalletId, C1261Wallet> getWallets() {
        return this.wallets;
    }

    @NotNull
    public final AtomicReference<EventStreamReceivedTxSubscriber> getTxHistorySubscription() {
        return this.txHistorySubscription;
    }

    public final void restartStream() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EventStreamHandler$restartStream$1(this, null), 3, null);
    }

    public void onTxHistory(@NotNull TxHistory txHistory) {
        Intrinsics.checkParameterIsNotNull(txHistory, "txHistory");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new EventStreamHandler$onTxHistory$1(this, txHistory, null), 3, null);
    }

    /* access modifiers changed from: private */
    public final void showNotification(HistoricTransaction historicTransaction) {
        String str;
        BigDecimal bigDecimal;
        String str2;
        ImageView imageView = null;
        if (((HistoricTransaction) BuildersKt__BuildersKt.runBlocking$default(null, new EventStreamHandler$showNotification$existingTxHistory$1(this, historicTransaction, null), 1, null)) == null && historicTransaction.getAction() == TxAction.RECEIVED) {
            boolean z = false;
            if (historicTransaction.getTokenId().getId().length() > 0) {
                str = "SLP";
            } else {
                str = historicTransaction.getCoin().getTicker();
            }
            if (historicTransaction.getTokenId().getId().length() > 0) {
                Slp slp = (Slp) BuildersKt__BuildersKt.runBlocking$default(null, new EventStreamHandler$showNotification$amount$token$1(this, historicTransaction, null), 1, null);
                bigDecimal = BigDecimal.valueOf(historicTransaction.getTokenAmount());
                Intrinsics.checkExpressionValueIsNotNull(bigDecimal, "BigDecimal.valueOf(this)");
                str2 = slp != null ? slp.toTickerAmount(bigDecimal) : null;
            } else {
                bigDecimal = historicTransaction.getSatoshis().getCoins();
                str2 = BigDecimalKt.toCoinString(historicTransaction.getSatoshis().getCoins(), historicTransaction.getCoin().getTicker());
            }
            if (str2 == null) {
                str2 = "";
            }
            String str3 = str2;
            this.analyticsService.track("transfer_success", MapsKt.mapOf(TuplesKt.m309to("coin", str), TuplesKt.m309to("type", "incoming"), TuplesKt.m309to(BitcoinURI.FIELD_AMOUNT, bigDecimal)));
            Application application2 = this.application;
            if (application2 != null) {
                Activity activityContext = ((ApplicationClass) application2).getActivityContext();
                Alerter duration = Alerter.Companion.create(activityContext, C1018R.layout.notification_alert).setBackgroundColorRes(C1018R.C1019color.zxing_transparent).setOnClickListener(new EventStreamHandler$showNotification$1(activityContext, historicTransaction)).enableSwipeToDismiss().setDuration(5000);
                View layoutContainer = duration.getLayoutContainer();
                TextView textView = layoutContainer != null ? (TextView) layoutContainer.findViewById(C1018R.C1021id.amountText) : null;
                if (textView != null) {
                    textView.setText(str3);
                }
                View layoutContainer2 = duration.getLayoutContainer();
                if (layoutContainer2 != null) {
                    imageView = (ImageView) layoutContainer2.findViewById(C1018R.C1021id.notificationIcon);
                }
                ImageView imageView2 = imageView;
                if (historicTransaction.getTokenId().getId().length() > 0) {
                    z = true;
                }
                if (z) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    EventStreamHandler$showNotification$$inlined$also$lambda$1 eventStreamHandler$showNotification$$inlined$also$lambda$1 = new EventStreamHandler$showNotification$$inlined$also$lambda$1(imageView2, this, str3, historicTransaction, activityContext);
                    handler.post(eventStreamHandler$showNotification$$inlined$also$lambda$1);
                } else {
                    int i = WhenMappings.$EnumSwitchMapping$0[historicTransaction.getCoin().ordinal()];
                    if (i != 1) {
                        if (i == 2 && imageView2 != null) {
                            imageView2.setImageResource(C1018R.C1020drawable.ic_qr_btc);
                        }
                    } else if (imageView2 != null) {
                        imageView2.setImageResource(C1018R.C1020drawable.ic_qr_bch);
                    }
                }
                duration.show();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.ApplicationClass");
        }
    }

    public void onWalletUtxos(@NotNull WalletUtxos walletUtxos) {
        String str;
        String str2;
        String str3;
        Intrinsics.checkParameterIsNotNull(walletUtxos, "walletUtxos");
        String walletId = walletUtxos.getWalletId();
        Intrinsics.checkExpressionValueIsNotNull(walletId, "walletUtxos.walletId");
        WalletId walletId2 = new WalletId(walletId);
        C1261Wallet wallet = (C1261Wallet) this.wallets.get(walletId2);
        if (wallet != null) {
            List utxoList = walletUtxos.getUtxoList();
            String str4 = "walletUtxos.utxoList";
            Intrinsics.checkExpressionValueIsNotNull(utxoList, str4);
            Iterable iterable = utxoList;
            Collection arrayList = new ArrayList();
            Iterator it = iterable.iterator();
            while (true) {
                boolean z = true;
                str = "it.token.tokenId";
                str2 = "it";
                str3 = "it.token";
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                Utxo utxo = (Utxo) next;
                Intrinsics.checkExpressionValueIsNotNull(utxo, str2);
                Token token = utxo.getToken();
                Intrinsics.checkExpressionValueIsNotNull(token, str3);
                String tokenId = token.getTokenId();
                Intrinsics.checkExpressionValueIsNotNull(tokenId, str);
                if (tokenId.length() != 0) {
                    z = false;
                }
                if (z) {
                    arrayList.add(next);
                }
            }
            Iterable<Utxo> iterable2 = (List) arrayList;
            Collection arrayList2 = new ArrayList();
            for (Utxo domain : iterable2) {
                com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo domain2 = ProtoConverterKt.toDomain(domain, wallet);
                if (domain2 != null) {
                    arrayList2.add(domain2);
                }
            }
            List list = (List) arrayList2;
            List utxoList2 = walletUtxos.getUtxoList();
            Intrinsics.checkExpressionValueIsNotNull(utxoList2, str4);
            Iterable iterable3 = utxoList2;
            Collection arrayList3 = new ArrayList();
            for (Object next2 : iterable3) {
                Utxo utxo2 = (Utxo) next2;
                Intrinsics.checkExpressionValueIsNotNull(utxo2, str2);
                Token token2 = utxo2.getToken();
                Intrinsics.checkExpressionValueIsNotNull(token2, str3);
                String tokenId2 = token2.getTokenId();
                Intrinsics.checkExpressionValueIsNotNull(tokenId2, str);
                if (tokenId2.length() > 0) {
                    arrayList3.add(next2);
                }
            }
            Iterable<Utxo> iterable4 = (List) arrayList3;
            Collection arrayList4 = new ArrayList();
            for (Utxo slpDomain : iterable4) {
                SlpUtxo slpDomain2 = ProtoConverterKt.toSlpDomain(slpDomain, wallet);
                if (slpDomain2 != null) {
                    arrayList4.add(slpDomain2);
                }
            }
            List list2 = (List) arrayList4;
            List utxoList3 = walletUtxos.getUtxoList();
            Intrinsics.checkExpressionValueIsNotNull(utxoList3, str4);
            Iterable iterable5 = utxoList3;
            Collection arrayList5 = new ArrayList();
            for (Object next3 : iterable5) {
                Utxo utxo3 = (Utxo) next3;
                Intrinsics.checkExpressionValueIsNotNull(utxo3, str2);
                Token token3 = utxo3.getToken();
                Intrinsics.checkExpressionValueIsNotNull(token3, str3);
                String tokenId3 = token3.getTokenId();
                Intrinsics.checkExpressionValueIsNotNull(tokenId3, str);
                if (tokenId3.length() > 0) {
                    arrayList5.add(next3);
                }
            }
            Iterable<Utxo> iterable6 = (List) arrayList5;
            Collection arrayList6 = new ArrayList();
            for (Utxo utxo4 : iterable6) {
                Intrinsics.checkExpressionValueIsNotNull(utxo4, str2);
                Token token4 = utxo4.getToken();
                Intrinsics.checkExpressionValueIsNotNull(token4, str3);
                String tokenId4 = token4.getTokenId();
                Intrinsics.checkExpressionValueIsNotNull(tokenId4, str);
                SlpId slpId = new SlpId(tokenId4);
                Token token5 = utxo4.getToken();
                Intrinsics.checkExpressionValueIsNotNull(token5, str3);
                String tokenTicker = token5.getTokenTicker();
                Intrinsics.checkExpressionValueIsNotNull(tokenTicker, "it.token.tokenTicker");
                Token token6 = utxo4.getToken();
                Intrinsics.checkExpressionValueIsNotNull(token6, str3);
                String tokenName = token6.getTokenName();
                Intrinsics.checkExpressionValueIsNotNull(tokenName, "it.token.tokenName");
                Token token7 = utxo4.getToken();
                Intrinsics.checkExpressionValueIsNotNull(token7, str3);
                Slp slp = r1;
                Slp slp2 = new Slp(slpId, tokenTicker, tokenName, walletId2, token7.getTokenDecimals());
                arrayList6.add(slp);
            }
            List list3 = (List) arrayList6;
            StringBuilder sb = new StringBuilder();
            sb.append("EventStream Utxos: ");
            sb.append(list);
            sb.append(",  Tokens: ");
            sb.append(list2);
            Timber.m426d(sb.toString(), new Object[0]);
            CoroutineScope coroutineScope = GlobalScope.INSTANCE;
            EventStreamHandler$onWalletUtxos$$inlined$let$lambda$1 eventStreamHandler$onWalletUtxos$$inlined$let$lambda$1 = new EventStreamHandler$onWalletUtxos$$inlined$let$lambda$1(list, list2, list3, null, this, walletUtxos, walletId2);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, eventStreamHandler$onWalletUtxos$$inlined$let$lambda$1, 3, null);
        }
    }
}
