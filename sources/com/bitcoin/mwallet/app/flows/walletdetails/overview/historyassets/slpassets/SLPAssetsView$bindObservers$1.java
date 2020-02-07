package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, mo37405d2 = {"<anonymous>", "", "slpTokenList", "", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SLPAssetsView.kt */
final class SLPAssetsView$bindObservers$1<T> implements Observer<List<? extends Slp>> {
    final /* synthetic */ SLPAssetsAdapter $adapter;
    final /* synthetic */ SLPAssetsView this$0;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView$bindObservers$1$1", mo38000f = "SLPAssetsView.kt", mo38001i = {0, 0, 0, 0, 0}, mo38002l = {84}, mo38003m = "invokeSuspend", mo38004n = {"$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "slp"}, mo38005s = {"L$0", "L$1", "L$2", "L$4", "L$5"})
    /* renamed from: com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView$bindObservers$1$1 */
    /* compiled from: SLPAssetsView.kt */
    static final class C11121 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f328p$;
        final /* synthetic */ SLPAssetsView$bindObservers$1 this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C11121 r0 = new C11121(this.this$0, list2, continuation);
            r0.f328p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C11121) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:22:0x00d6, code lost:
            if (r9 != null) goto L_0x00db;
         */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x00ab  */
        /* JADX WARNING: Removed duplicated region for block: B:21:0x00d2  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x00cd A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:9:0x005a  */
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r15) {
            /*
                r14 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r14.label
                r2 = 1
                if (r1 == 0) goto L_0x0036
                if (r1 != r2) goto L_0x002e
                java.lang.Object r1 = r14.L$6
                java.util.Collection r1 = (java.util.Collection) r1
                java.lang.Object r3 = r14.L$5
                com.bitcoin.mwallet.core.models.slp.Slp r3 = (com.bitcoin.mwallet.core.models.slp.Slp) r3
                java.lang.Object r4 = r14.L$4
                java.lang.Object r4 = r14.L$3
                java.util.Iterator r4 = (java.util.Iterator) r4
                java.lang.Object r5 = r14.L$2
                java.util.Collection r5 = (java.util.Collection) r5
                java.lang.Object r6 = r14.L$1
                java.lang.Iterable r6 = (java.lang.Iterable) r6
                java.lang.Object r7 = r14.L$0
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                kotlin.ResultKt.throwOnFailure(r15)
                r8 = r0
                r0 = r14
                r13 = r5
                r5 = r1
                r1 = r13
                goto L_0x0091
            L_0x002e:
                java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r15.<init>(r0)
                throw r15
            L_0x0036:
                kotlin.ResultKt.throwOnFailure(r15)
                kotlinx.coroutines.CoroutineScope r15 = r14.f328p$
                java.util.List r15 = r0
                java.lang.Iterable r15 = (java.lang.Iterable) r15
                java.util.ArrayList r1 = new java.util.ArrayList
                r3 = 10
                int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r15, r3)
                r1.<init>(r3)
                java.util.Collection r1 = (java.util.Collection) r1
                java.util.Iterator r3 = r15.iterator()
                r6 = r15
                r7 = r6
                r4 = r3
                r15 = r14
            L_0x0054:
                boolean r3 = r4.hasNext()
                if (r3 == 0) goto L_0x00e7
                java.lang.Object r3 = r4.next()
                r5 = r3
                com.bitcoin.mwallet.core.models.slp.Slp r5 = (com.bitcoin.mwallet.core.models.slp.Slp) r5
                com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView$bindObservers$1 r8 = r15.this$0
                com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView r8 = r8.this$0
                com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsPresenter r8 = com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView.access$getPresenter$p(r8)
                com.bitcoin.mwallet.core.repositories.SlpRepository r8 = r8.getSlpRepository()
                com.bitcoin.mwallet.core.models.wallet.WalletId r9 = r5.getWalletId()
                com.bitcoin.mwallet.core.models.slp.SlpId r10 = r5.getTokenId()
                r15.L$0 = r7
                r15.L$1 = r6
                r15.L$2 = r1
                r15.L$3 = r4
                r15.L$4 = r3
                r15.L$5 = r5
                r15.L$6 = r1
                r15.label = r2
                java.lang.Object r3 = r8.getTokenAmount(r9, r10, r15)
                if (r3 != r0) goto L_0x008c
                return r0
            L_0x008c:
                r8 = r0
                r0 = r15
                r15 = r3
                r3 = r5
                r5 = r1
            L_0x0091:
                java.math.BigDecimal r15 = (java.math.BigDecimal) r15
                com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView$bindObservers$1 r9 = r0.this$0
                com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView r9 = r9.this$0
                com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsPresenter r9 = com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView.access$getPresenter$p(r9)
                java.util.List r9 = r9.getVerifiedTokens()
                java.lang.Iterable r9 = (java.lang.Iterable) r9
                java.util.Iterator r9 = r9.iterator()
            L_0x00a5:
                boolean r10 = r9.hasNext()
                if (r10 == 0) goto L_0x00cd
                java.lang.Object r10 = r9.next()
                r11 = r10
                com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken r11 = (com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken) r11
                java.lang.String r11 = r11.getId()
                com.bitcoin.mwallet.core.models.slp.SlpId r12 = r3.getTokenId()
                java.lang.String r12 = r12.getId()
                boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r12)
                java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11)
                boolean r11 = r11.booleanValue()
                if (r11 == 0) goto L_0x00a5
                goto L_0x00ce
            L_0x00cd:
                r10 = 0
            L_0x00ce:
                com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken r10 = (com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken) r10
                if (r10 == 0) goto L_0x00d9
                java.lang.String r9 = r10.getIconImageUrl()
                if (r9 == 0) goto L_0x00d9
                goto L_0x00db
            L_0x00d9:
                java.lang.String r9 = ""
            L_0x00db:
                com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetRow r10 = new com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetRow
                r10.<init>(r15, r3, r9)
                r5.add(r10)
                r15 = r0
                r0 = r8
                goto L_0x0054
            L_0x00e7:
                java.util.List r1 = (java.util.List) r1
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView$bindObservers$1$1$invokeSuspend$$inlined$sortedBy$1 r0 = new com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView$bindObservers$1$1$invokeSuspend$$inlined$sortedBy$1
                r0.<init>()
                java.util.Comparator r0 = (java.util.Comparator) r0
                java.util.List r0 = kotlin.collections.CollectionsKt.sortedWith(r1, r0)
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                java.util.Collection r1 = (java.util.Collection) r1
                java.util.Iterator r0 = r0.iterator()
            L_0x0103:
                boolean r3 = r0.hasNext()
                if (r3 == 0) goto L_0x012d
                java.lang.Object r3 = r0.next()
                r4 = r3
                com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetRow r4 = (com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetRow) r4
                java.math.BigDecimal r4 = r4.getBalance()
                java.math.BigDecimal r5 = java.math.BigDecimal.ZERO
                int r4 = r4.compareTo(r5)
                if (r4 <= 0) goto L_0x011e
                r4 = 1
                goto L_0x011f
            L_0x011e:
                r4 = 0
            L_0x011f:
                java.lang.Boolean r4 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
                boolean r4 = r4.booleanValue()
                if (r4 == 0) goto L_0x0103
                r1.add(r3)
                goto L_0x0103
            L_0x012d:
                java.util.List r1 = (java.util.List) r1
                com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView$bindObservers$1 r15 = r15.this$0
                com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsAdapter r15 = r15.$adapter
                r15.submitList(r1)
                kotlin.Unit r15 = kotlin.Unit.INSTANCE
                return r15
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetsView$bindObservers$1.C11121.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    SLPAssetsView$bindObservers$1(SLPAssetsView sLPAssetsView, SLPAssetsAdapter sLPAssetsAdapter) {
        this.this$0 = sLPAssetsView;
        this.$adapter = sLPAssetsAdapter;
    }

    public final void onChanged(List<Slp> list) {
        SLPAssetsView sLPAssetsView = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(list, "slpTokenList");
        sLPAssetsView.setEmptyLayout(!list.isEmpty());
        Iterable iterable = list;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            Slp slp = (Slp) next;
            Object value = SLPAssetsView.access$getPresenter$p(this.this$0).getShowVerifed().getValue();
            if (value == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(value, "presenter.showVerifed.value!!");
            boolean z = false;
            if (((Boolean) value).booleanValue()) {
                Iterable verifiedTokens = SLPAssetsView.access$getPresenter$p(this.this$0).getVerifiedTokens();
                if (!(verifiedTokens instanceof Collection) || !((Collection) verifiedTokens).isEmpty()) {
                    Iterator it = verifiedTokens.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (Intrinsics.areEqual((Object) ((VerifiedToken) it.next()).getId(), (Object) slp.getTokenId().toString())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    z = true;
                }
            } else {
                z = true;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        final List list2 = (List) arrayList;
        BuildersKt__Builders_commonKt.launch$default(SLPAssetsView.access$getPresenter$p(this.this$0).getViewModelScope(), null, null, new C11121(this, null), 3, null);
    }
}
