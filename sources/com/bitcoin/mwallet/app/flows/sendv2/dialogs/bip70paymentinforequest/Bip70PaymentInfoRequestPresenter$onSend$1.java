package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.app.flows.sendv2.entity.SendWalletData;
import com.bitcoin.mwallet.core.interactors.CreateTxInteractor;
import com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentOutput;
import com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection;
import com.bitcoin.mwallet.core.services.p010tx.Bip70BroadcastTxResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.LongRef;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest.Bip70PaymentInfoRequestPresenter$onSend$1", mo38000f = "Bip70PaymentInfoRequestPresenter.kt", mo38001i = {0, 0, 0, 1, 1, 1, 1}, mo38002l = {118, 132}, mo38003m = "invokeSuspend", mo38004n = {"extraFee", "satoshisPerOutput", "longScripts", "extraFee", "satoshisPerOutput", "longScripts", "utxoSelection"}, mo38005s = {"L$0", "J$0", "L$1", "L$0", "J$0", "L$1", "L$2"})
/* compiled from: Bip70PaymentInfoRequestPresenter.kt */
final class Bip70PaymentInfoRequestPresenter$onSend$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f273p$;
    final /* synthetic */ Bip70PaymentInfoRequestPresenter this$0;

    Bip70PaymentInfoRequestPresenter$onSend$1(Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter, Continuation continuation) {
        this.this$0 = bip70PaymentInfoRequestPresenter;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        Bip70PaymentInfoRequestPresenter$onSend$1 bip70PaymentInfoRequestPresenter$onSend$1 = new Bip70PaymentInfoRequestPresenter$onSend$1(this.this$0, continuation);
        bip70PaymentInfoRequestPresenter$onSend$1.f273p$ = (CoroutineScope) obj;
        return bip70PaymentInfoRequestPresenter$onSend$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((Bip70PaymentInfoRequestPresenter$onSend$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object obj2;
        long j;
        LongRef longRef;
        Object obj3;
        List<Bip70PaymentOutput> list;
        UtxoSelection utxoSelection;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f273p$;
            if (this.this$0.getWalletData() == null || this.this$0.getBip70Url() == null || this.this$0.getPaymentTransactionInfo() == null) {
                return Unit.INSTANCE;
            }
            longRef = new LongRef();
            longRef.element = 0;
            j = 34;
            List paymentTransactionInfo = this.this$0.getPaymentTransactionInfo();
            if (paymentTransactionInfo == null) {
                Intrinsics.throwNpe();
            }
            Iterable iterable = paymentTransactionInfo;
            Collection arrayList = new ArrayList();
            for (Object next : iterable) {
                if (Boxing.boxBoolean(((Bip70PaymentOutput) next).getScript().length() > 100).booleanValue()) {
                    arrayList.add(next);
                }
            }
            list = (List) arrayList;
            for (Bip70PaymentOutput script : list) {
                longRef.element += ((long) (script.getScript().length() / 80)) * 34;
            }
            Bip70PaymentInfoRequestPresenter bip70PaymentInfoRequestPresenter = this.this$0;
            this.L$0 = longRef;
            this.J$0 = 34;
            this.L$1 = list;
            this.label = 1;
            obj3 = bip70PaymentInfoRequestPresenter.getSatoshiPerByte(this);
            if (obj3 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            list = (List) this.L$1;
            long j2 = this.J$0;
            longRef = (LongRef) this.L$0;
            ResultKt.throwOnFailure(obj);
            j = j2;
            obj3 = obj;
        } else if (i == 2) {
            UtxoSelection utxoSelection2 = (UtxoSelection) this.L$2;
            List list2 = (List) this.L$1;
            long j3 = this.J$0;
            LongRef longRef2 = (LongRef) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj2 = obj;
            Bip70BroadcastTxResponse bip70BroadcastTxResponse = (Bip70BroadcastTxResponse) obj2;
            StringBuilder sb = new StringBuilder();
            sb.append("Bip70 send result: ");
            sb.append(bip70BroadcastTxResponse.isSuccess());
            sb.append(" TxId: ");
            sb.append(bip70BroadcastTxResponse.getTxId());
            Timber.m426d(sb.toString(), new Object[0]);
            this.this$0.handleSendResult(bip70BroadcastTxResponse.isSuccess(), bip70BroadcastTxResponse.getTxId());
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        BigDecimal bigDecimal = (BigDecimal) obj3;
        if (bigDecimal != null) {
            CreateTxInteractor access$getCreateTxInteractor$p = this.this$0.createTxInteractor;
            SendWalletData walletData = this.this$0.getWalletData();
            if (walletData == null) {
                Intrinsics.throwNpe();
            }
            Satoshis invoiceCoinAmount = this.this$0.getInvoiceCoinAmount();
            List paymentTransactionInfo2 = this.this$0.getPaymentTransactionInfo();
            if (paymentTransactionInfo2 == null) {
                Intrinsics.throwNpe();
            }
            utxoSelection = access$getCreateTxInteractor$p.selectUtxos(walletData, invoiceCoinAmount, bigDecimal, Boxing.boxInt(paymentTransactionInfo2.size()), longRef.element);
        } else {
            utxoSelection = null;
        }
        if (utxoSelection == null) {
            Timber.m426d("Illegal state", new Object[0]);
            return Unit.INSTANCE;
        }
        Timber.m426d(utxoSelection.toString(), new Object[0]);
        CreateTxInteractor access$getCreateTxInteractor$p2 = this.this$0.createTxInteractor;
        SendWalletData walletData2 = this.this$0.getWalletData();
        if (walletData2 == null) {
            Intrinsics.throwNpe();
        }
        String bip70Url = this.this$0.getBip70Url();
        if (bip70Url == null) {
            Intrinsics.throwNpe();
        }
        List paymentTransactionInfo3 = this.this$0.getPaymentTransactionInfo();
        if (paymentTransactionInfo3 == null) {
            Intrinsics.throwNpe();
        }
        this.L$0 = longRef;
        this.J$0 = j;
        this.L$1 = list;
        this.L$2 = utxoSelection;
        this.label = 2;
        obj2 = access$getCreateTxInteractor$p2.sendBip70(walletData2, utxoSelection, bip70Url, paymentTransactionInfo3, this);
        if (obj2 == coroutine_suspended) {
            return coroutine_suspended;
        }
        Bip70BroadcastTxResponse bip70BroadcastTxResponse2 = (Bip70BroadcastTxResponse) obj2;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Bip70 send result: ");
        sb2.append(bip70BroadcastTxResponse2.isSuccess());
        sb2.append(" TxId: ");
        sb2.append(bip70BroadcastTxResponse2.getTxId());
        Timber.m426d(sb2.toString(), new Object[0]);
        this.this$0.handleSendResult(bip70BroadcastTxResponse2.isSuccess(), bip70BroadcastTxResponse2.getTxId());
        return Unit.INSTANCE;
    }
}
