package com.bitcoin.mwallet.app.flows.walletdetails.scanfortransactions;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.models.CopayerId;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ScanForTransactionsView.kt */
final class ScanForTransactionsView$enableAction$1 implements OnClickListener {
    final /* synthetic */ ScanForTransactionsView this$0;

    ScanForTransactionsView$enableAction$1(ScanForTransactionsView scanForTransactionsView) {
        this.this$0 = scanForTransactionsView;
    }

    public final void onClick(View view) {
        ((ScanForTransactionsPresenter) this.this$0.getPresenter()).getWallet().observe(this.this$0.getViewLifecycleOwner(), new Observer<C1261Wallet>(this) {
            final /* synthetic */ ScanForTransactionsView$enableAction$1 this$0;

            @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
            @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.walletdetails.scanfortransactions.ScanForTransactionsView$enableAction$1$1$1", mo38000f = "ScanForTransactionsView.kt", mo38001i = {}, mo38002l = {76}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
            /* renamed from: com.bitcoin.mwallet.app.flows.walletdetails.scanfortransactions.ScanForTransactionsView$enableAction$1$1$1 */
            /* compiled from: ScanForTransactionsView.kt */
            static final class C11161 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;

                /* renamed from: p$ */
                private CoroutineScope f334p$;
                final /* synthetic */ C11151 this$0;

                {
                    this.this$0 = r1;
                }

                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    Intrinsics.checkParameterIsNotNull(continuation, "completion");
                    C11161 r0 = new C11161(this.this$0, wallet, continuation);
                    r0.f334p$ = (CoroutineScope) obj;
                    return r0;
                }

                public final Object invoke(Object obj, Object obj2) {
                    return ((C11161) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Nullable
                public final Object invokeSuspend(@NotNull Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = this.f334p$;
                        wallet.preference().setLastTxHistoryUpdate(0);
                        ScanForTransactionsPresenter scanForTransactionsPresenter = (ScanForTransactionsPresenter) this.this$0.this$0.this$0.getPresenter();
                        WalletId id = wallet.getId();
                        CopayerId walletCopayerId = wallet.getCopayers().getWalletCopayerId();
                        SigningKey signingKey = wallet.getSigningKey();
                        this.label = 1;
                        if (scanForTransactionsPresenter.scanForFunds(id, walletCopayerId, signingKey, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i == 1) {
                        ResultKt.throwOnFailure(obj);
                    } else {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    return Unit.INSTANCE;
                }
            }

            {
                this.this$0 = r1;
            }

            public final void onChanged(final C1261Wallet wallet) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C11161(this, null), 3, null);
                this.this$0.this$0.disbleAction(((ScanForTransactionsPresenter) this.this$0.this$0.getPresenter()).getEnableAfter());
            }
        });
    }
}
