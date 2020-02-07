package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view.ImportWalletDialogView.WhenMappings;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult;
import com.bitcoin.mwallet.core.models.user.User;
import com.bitcoin.mwallet.core.repositories.UserRepository;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "result", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor$CreateWalletResult;", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ImportWalletDialogView.kt */
final class ImportWalletDialogView$onCreateView$1<T> implements Observer<CreateWalletResult> {
    final /* synthetic */ ImportWalletDialogView this$0;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/user/User;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view.ImportWalletDialogView$onCreateView$1$1", mo38000f = "ImportWalletDialogView.kt", mo38001i = {}, mo38002l = {49}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view.ImportWalletDialogView$onCreateView$1$1 */
    /* compiled from: ImportWalletDialogView.kt */
    static final class C10901 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super User>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f243p$;
        final /* synthetic */ ImportWalletDialogView$onCreateView$1 this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C10901 r0 = new C10901(this.this$0, continuation);
            r0.f243p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C10901) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            User user;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f243p$;
                ImportWalletPresenter presenter = this.this$0.this$0.getPresenter();
                if (presenter != null) {
                    UserRepository userRepository = presenter.getUserRepository();
                    if (userRepository != null) {
                        this.label = 1;
                        obj = userRepository.getOrCreateUser(this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
                user = null;
                return user;
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            user = (User) obj;
            return user;
        }
    }

    ImportWalletDialogView$onCreateView$1(ImportWalletDialogView importWalletDialogView) {
        this.this$0 = importWalletDialogView;
    }

    public final void onChanged(@Nullable CreateWalletResult createWalletResult) {
        if (createWalletResult != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[createWalletResult.ordinal()];
            String str = null;
            if (i == 1) {
                BuildersKt__BuildersKt.runBlocking$default(null, new C10901(this, null), 1, null);
                this.this$0.dismiss();
            } else if (i != 2) {
                Toast.makeText(this.this$0.getContext(), createWalletResult.toString(), 0).show();
            } else {
                Context context = this.this$0.getContext();
                if (context != null) {
                    str = context.getString(C1018R.string.createwallet_failed_zion_max);
                }
                Toast.makeText(this.this$0.getContext(), str, 0).show();
            }
        }
    }
}
