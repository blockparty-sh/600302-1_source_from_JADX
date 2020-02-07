package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory;

import android.view.View;
import android.widget.LinearLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.views.transaction.TransactionSummaryListAdapter;
import com.bitcoin.mwallet.core.views.transaction.TransactionSummaryView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.IntRef;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "txSummaries", "", "Lcom/bitcoin/mwallet/core/views/transaction/TransactionSummaryView;", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: TransactionHistoryView.kt */
final class TransactionHistoryView$onActivityCreated$4<T> implements Observer<List<? extends TransactionSummaryView>> {
    final /* synthetic */ RecyclerView $recyclerView;
    final /* synthetic */ IntRef $totalItems;
    final /* synthetic */ TransactionSummaryListAdapter $txAdapter;
    final /* synthetic */ TransactionHistoryView this$0;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory.TransactionHistoryView$onActivityCreated$4$1", mo38000f = "TransactionHistoryView.kt", mo38001i = {}, mo38002l = {}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory.TransactionHistoryView$onActivityCreated$4$1 */
    /* compiled from: TransactionHistoryView.kt */
    static final class C11141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f331p$;
        final /* synthetic */ TransactionHistoryView$onActivityCreated$4 this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C11141 r0 = new C11141(this.this$0, continuation);
            r0.f331p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C11141) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f331p$;
                RecyclerView recyclerView = this.this$0.$recyclerView;
                if (recyclerView == null) {
                    return null;
                }
                recyclerView.smoothScrollToPosition(0);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    TransactionHistoryView$onActivityCreated$4(TransactionHistoryView transactionHistoryView, RecyclerView recyclerView, TransactionSummaryListAdapter transactionSummaryListAdapter, IntRef intRef) {
        this.this$0 = transactionHistoryView;
        this.$recyclerView = recyclerView;
        this.$txAdapter = transactionSummaryListAdapter;
        this.$totalItems = intRef;
    }

    public final void onChanged(@NotNull List<TransactionSummaryView> list) {
        Intrinsics.checkParameterIsNotNull(list, "txSummaries");
        boolean z = !list.isEmpty();
        Iterable iterable = list;
        Collection arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (true) {
            boolean z2 = false;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            TransactionSummaryView transactionSummaryView = (TransactionSummaryView) next;
            Object value = TransactionHistoryView.access$getPresenter$p(this.this$0).getHideSlpTransaction().getValue();
            if (value == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(value, "presenter.hideSlpTransaction.value!!");
            if (!((Boolean) value).booleanValue() || transactionSummaryView.getTokenId() == null) {
                z2 = true;
            }
            if (z2) {
                arrayList.add(next);
            }
        }
        List list2 = (List) arrayList;
        RecyclerView recyclerView = this.$recyclerView;
        if (recyclerView != null) {
            recyclerView.setVisibility(0);
        }
        this.$txAdapter.submitList(list2);
        Collection collection = list2;
        if (this.$totalItems.element != collection.size()) {
            this.$totalItems.element = collection.size();
            BuildersKt__BuildersKt.runBlocking$default(null, new C11141(this, null), 1, null);
        }
        if (!z) {
            RecyclerView recyclerView2 = this.$recyclerView;
            if (recyclerView2 != null) {
                recyclerView2.setVisibility(8);
            }
            View view = this.this$0.getView();
            if (view != null) {
                LinearLayout linearLayout = (LinearLayout) view.findViewById(C1018R.C1021id.bchFilterLayout);
                if (linearLayout != null) {
                    linearLayout.setVisibility(8);
                }
            }
            View view2 = this.this$0.getView();
            if (view2 != null) {
                LinearLayout linearLayout2 = (LinearLayout) view2.findViewById(C1018R.C1021id.historyEmptyView);
                if (linearLayout2 != null) {
                    linearLayout2.setVisibility(0);
                    return;
                }
                return;
            }
            return;
        }
        RecyclerView recyclerView3 = this.$recyclerView;
        if (recyclerView3 != null) {
            recyclerView3.setVisibility(0);
        }
        View view3 = this.this$0.getView();
        if (view3 != null) {
            LinearLayout linearLayout3 = (LinearLayout) view3.findViewById(C1018R.C1021id.historyEmptyView);
            if (linearLayout3 != null) {
                linearLayout3.setVisibility(8);
            }
        }
    }
}
