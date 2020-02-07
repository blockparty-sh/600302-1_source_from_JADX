package com.bitcoin.mwallet.app.flows.onboarding.createuser;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.bitcoin.mwallet.app.android.LiveDataKt;
import com.bitcoin.mwallet.app.viper.PresenterCloseOnBackHandler;
import com.bitcoin.mwallet.app.viper.PresenterWithCloseOnBackHandler;
import com.bitcoin.mwallet.app.viper.ScreenPresenter;
import com.bitcoin.mwallet.core.models.user.User;
import com.bitcoin.mwallet.core.repositories.UserRepository;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B-\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0002¢\u0006\u0002\u0010\rJ\u0006\u0010\u001e\u001a\u00020\u001fJ\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0017J\u0006\u0010\"\u001a\u00020\u001fJ\u000e\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020%R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u0017X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserPresenter;", "Lcom/bitcoin/mwallet/app/viper/ScreenPresenter;", "Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserRouter;", "Lcom/bitcoin/mwallet/app/viper/PresenterWithCloseOnBackHandler;", "context", "Landroid/content/Context;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "userRepository", "Lcom/bitcoin/mwallet/core/repositories/UserRepository;", "interactor", "Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserInteractor;", "router", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/bitcoin/mwallet/core/repositories/UserRepository;Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserInteractor;Lcom/bitcoin/mwallet/app/flows/onboarding/createuser/CreateUserRouter;)V", "_searchingZion", "Landroidx/lifecycle/MutableLiveData;", "", "backHandler", "Lcom/bitcoin/mwallet/app/viper/PresenterCloseOnBackHandler;", "getBackHandler", "()Lcom/bitcoin/mwallet/app/viper/PresenterCloseOnBackHandler;", "creatingWallet", "displayZionCreateAndRestore", "Landroidx/lifecycle/LiveData;", "getDisplayZionCreateAndRestore", "()Landroidx/lifecycle/LiveData;", "hasZion", "notCreatedAndNotCreating", "searchingZion", "getSearchingZion", "createWallet", "", "getUsers", "Lcom/bitcoin/mwallet/core/models/user/User;", "goToHome", "toCreateWallet", "fm", "Landroidx/fragment/app/FragmentManager;", "toImportExistingWallet", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CreateUserPresenter.kt */
public final class CreateUserPresenter extends ScreenPresenter<CreateUserRouter> implements PresenterWithCloseOnBackHandler {
    /* access modifiers changed from: private */
    public final MutableLiveData<Boolean> _searchingZion = new MutableLiveData<>(Boolean.valueOf(this.hasZion));
    @NotNull
    private final PresenterCloseOnBackHandler backHandler = new PresenterCloseOnBackHandler();
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public final MutableLiveData<Boolean> creatingWallet = new MutableLiveData<>(Boolean.valueOf(false));
    @NotNull
    private final LiveData<Boolean> displayZionCreateAndRestore;
    /* access modifiers changed from: private */
    public final boolean hasZion = ((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new CreateUserPresenter$hasZion$1(this, null), 1, null)).booleanValue();
    /* access modifiers changed from: private */
    public final CreateUserInteractor interactor;
    private final LiveData<Boolean> notCreatedAndNotCreating;
    @NotNull
    private final LiveData<Boolean> searchingZion;
    private final UserRepository userRepository;
    private final CoroutineScope viewModelScope;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
    @DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter$1", mo38000f = "CreateUserPresenter.kt", mo38001i = {2, 3, 3}, mo38002l = {60, 62, 69, 79}, mo38003m = "invokeSuspend", mo38004n = {"userExists", "userExists", "oldWalletFileExists"}, mo38005s = {"Z$0", "Z$0", "Z$1"})
    /* renamed from: com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter$1 */
    /* compiled from: CreateUserPresenter.kt */
    static final class C10961 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        boolean Z$0;
        boolean Z$1;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f248p$;
        final /* synthetic */ CreateUserPresenter this$0;

        {
            this.this$0 = r1;
        }

        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            Intrinsics.checkParameterIsNotNull(continuation, "completion");
            C10961 r0 = new C10961(this.this$0, createUserRouter, continuation);
            r0.f248p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C10961) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Removed duplicated region for block: B:23:0x0062  */
        /* JADX WARNING: Removed duplicated region for block: B:25:0x0070  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0091  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x0097  */
        @org.jetbrains.annotations.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 0
                r3 = 4
                r4 = 3
                r5 = 2
                r6 = 1
                if (r1 == 0) goto L_0x0037
                if (r1 == r6) goto L_0x0033
                if (r1 == r5) goto L_0x002f
                if (r1 == r4) goto L_0x0029
                if (r1 != r3) goto L_0x0021
                boolean r0 = r7.Z$1
                boolean r0 = r7.Z$0
                kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x001e }
                goto L_0x00b2
            L_0x001e:
                r8 = move-exception
                goto L_0x00c5
            L_0x0021:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L_0x0029:
                boolean r1 = r7.Z$0
                kotlin.ResultKt.throwOnFailure(r8)
                goto L_0x0089
            L_0x002f:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L_0x005a
            L_0x0033:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L_0x004b
            L_0x0037:
                kotlin.ResultKt.throwOnFailure(r8)
                kotlinx.coroutines.CoroutineScope r8 = r7.f248p$
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter r8 = r7.this$0
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor r8 = r8.interactor
                r7.label = r6
                java.lang.Object r8 = r8.registerRegionNotification(r7)
                if (r8 != r0) goto L_0x004b
                return r0
            L_0x004b:
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter r8 = r7.this$0
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor r8 = r8.interactor
                r7.label = r5
                java.lang.Object r8 = r8.userExists(r7)
                if (r8 != r0) goto L_0x005a
                return r0
            L_0x005a:
                java.lang.Boolean r8 = (java.lang.Boolean) r8
                boolean r1 = r8.booleanValue()
                if (r1 == 0) goto L_0x0070
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserRouter r8 = r11
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter r0 = r7.this$0
                android.content.Context r0 = r0.context
                r8.toHome(r0)
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            L_0x0070:
                kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
                kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter$1$oldWalletFileExists$1 r5 = new com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter$1$oldWalletFileExists$1
                r6 = 0
                r5.<init>(r7, r6)
                kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
                r7.Z$0 = r1
                r7.label = r4
                java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r5, r7)
                if (r8 != r0) goto L_0x0089
                return r0
            L_0x0089:
                java.lang.Boolean r8 = (java.lang.Boolean) r8
                boolean r8 = r8.booleanValue()
                if (r8 == 0) goto L_0x0097
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserRouter r8 = r11
                r8.toMigration()
                goto L_0x00d8
            L_0x0097:
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter r4 = r7.this$0
                boolean r4 = r4.hasZion
                if (r4 == 0) goto L_0x00d3
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter r4 = r7.this$0     // Catch:{ all -> 0x001e }
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserInteractor r4 = r4.interactor     // Catch:{ all -> 0x001e }
                r7.Z$0 = r1     // Catch:{ all -> 0x001e }
                r7.Z$1 = r8     // Catch:{ all -> 0x001e }
                r7.label = r3     // Catch:{ all -> 0x001e }
                java.lang.Object r8 = r4.scanAndRestoreZionWallets(r7)     // Catch:{ all -> 0x001e }
                if (r8 != r0) goto L_0x00b2
                return r0
            L_0x00b2:
                java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ all -> 0x001e }
                r8.booleanValue()     // Catch:{ all -> 0x001e }
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter r8 = r7.this$0
                androidx.lifecycle.MutableLiveData r8 = r8._searchingZion
                java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
                r8.setValue(r0)
                goto L_0x00d8
            L_0x00c5:
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter r0 = r7.this$0
                androidx.lifecycle.MutableLiveData r0 = r0._searchingZion
                java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
                r0.setValue(r1)
                throw r8
            L_0x00d3:
                com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter r8 = r7.this$0
                r8.createWallet()
            L_0x00d8:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.onboarding.createuser.CreateUserPresenter.C10961.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public CreateUserPresenter(@NotNull Context context2, @NotNull CoroutineScope coroutineScope, @NotNull UserRepository userRepository2, @NotNull CreateUserInteractor createUserInteractor, @NotNull final CreateUserRouter createUserRouter) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "viewModelScope");
        Intrinsics.checkParameterIsNotNull(userRepository2, "userRepository");
        Intrinsics.checkParameterIsNotNull(createUserInteractor, "interactor");
        Intrinsics.checkParameterIsNotNull(createUserRouter, "router");
        super(context2, createUserRouter);
        this.context = context2;
        this.viewModelScope = coroutineScope;
        this.userRepository = userRepository2;
        this.interactor = createUserInteractor;
        MutableLiveData<Boolean> mutableLiveData = this._searchingZion;
        this.searchingZion = mutableLiveData;
        this.notCreatedAndNotCreating = LiveDataKt.combineLatest(this.creatingWallet, mutableLiveData, CreateUserPresenter$notCreatedAndNotCreating$1.INSTANCE);
        LiveData<Boolean> map = Transformations.map(this.notCreatedAndNotCreating, new CreateUserPresenter$displayZionCreateAndRestore$1(this));
        Intrinsics.checkExpressionValueIsNotNull(map, "Transformations.map(notC…eating) { it && hasZion }");
        this.displayZionCreateAndRestore = map;
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new C10961(this, null), 3, null);
    }

    @NotNull
    public PresenterCloseOnBackHandler getBackHandler() {
        return this.backHandler;
    }

    @NotNull
    public final LiveData<Boolean> getSearchingZion() {
        return this.searchingZion;
    }

    @NotNull
    public final LiveData<Boolean> getDisplayZionCreateAndRestore() {
        return this.displayZionCreateAndRestore;
    }

    @NotNull
    public final LiveData<User> getUsers() {
        return this.userRepository.getAllUsers();
    }

    public final void goToHome() {
        ((CreateUserRouter) getRouter()).toHome(this.context);
    }

    public final void createWallet() {
        this.creatingWallet.setValue(Boolean.valueOf(true));
        BuildersKt__Builders_commonKt.launch$default(this.viewModelScope, null, null, new CreateUserPresenter$createWallet$1(this, null), 3, null);
    }

    public final void toCreateWallet(@NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        ((CreateUserRouter) getRouter()).toCreateWallet(fragmentManager);
    }

    public final void toImportExistingWallet(@NotNull FragmentManager fragmentManager) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        ((CreateUserRouter) getRouter()).toImportExistingWallet(fragmentManager, this.hasZion);
    }
}
