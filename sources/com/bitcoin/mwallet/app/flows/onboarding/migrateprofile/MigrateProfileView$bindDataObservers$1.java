package com.bitcoin.mwallet.app.flows.onboarding.migrateprofile;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.entity.OldWallet.OldWalletsJsonSource;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: MigrateProfileView.kt */
final class MigrateProfileView$bindDataObservers$1 implements OnClickListener {
    final /* synthetic */ MigrateProfileView this$0;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileView$bindDataObservers$1$1", mo38000f = "MigrateProfileView.kt", mo38001i = {}, mo38002l = {56}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
    /* renamed from: com.bitcoin.mwallet.app.flows.onboarding.migrateprofile.MigrateProfileView$bindDataObservers$1$1 */
    /* compiled from: MigrateProfileView.kt */
    static final class C10971 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f256p$;
        final /* synthetic */ MigrateProfileView$bindDataObservers$1 this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C10971 r0 = new C10971(this.this$0, continuation);
            r0.f256p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C10971) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f256p$;
                OldWalletsJsonSource oldWallet = ((MigrateProfilePresenter) this.this$0.this$0.getPresenter()).getOldWallet();
                this.label = 1;
                obj = oldWallet.read(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return obj;
        }
    }

    MigrateProfileView$bindDataObservers$1(MigrateProfileView migrateProfileView) {
        this.this$0 = migrateProfileView;
    }

    public final void onClick(View view) {
        if (this.this$0.getMigrateThreshold() > 6) {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/json");
            intent.putExtra("android.intent.extra.TEXT", (String) BuildersKt__BuildersKt.runBlocking$default(null, new C10971(this, null), 1, null));
            this.this$0.startActivity(Intent.createChooser(intent, "export"));
        }
        MigrateProfileView migrateProfileView = this.this$0;
        migrateProfileView.setMigrateThreshold(migrateProfileView.getMigrateThreshold() + 1);
    }
}
