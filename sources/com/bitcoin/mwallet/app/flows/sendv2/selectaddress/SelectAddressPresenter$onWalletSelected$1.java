package com.bitcoin.mwallet.app.flows.sendv2.selectaddress;

import com.bitcoin.mwallet.core.entity.WalletType;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
@DebugMetadata(mo37999c = "com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$onWalletSelected$1", mo38000f = "SelectAddressPresenter.kt", mo38001i = {}, mo38002l = {159}, mo38003m = "invokeSuspend", mo38004n = {}, mo38005s = {})
/* compiled from: SelectAddressPresenter.kt */
final class SelectAddressPresenter$onWalletSelected$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WalletId $walletId;
    final /* synthetic */ WalletType $walletType;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f295p$;
    final /* synthetic */ SelectAddressPresenter this$0;

    SelectAddressPresenter$onWalletSelected$1(SelectAddressPresenter selectAddressPresenter, WalletId walletId, WalletType walletType, Continuation continuation) {
        this.this$0 = selectAddressPresenter;
        this.$walletId = walletId;
        this.$walletType = walletType;
        super(2, continuation);
    }

    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, "completion");
        SelectAddressPresenter$onWalletSelected$1 selectAddressPresenter$onWalletSelected$1 = new SelectAddressPresenter$onWalletSelected$1(this.this$0, this.$walletId, this.$walletType, continuation);
        selectAddressPresenter$onWalletSelected$1.f295p$ = (CoroutineScope) obj;
        return selectAddressPresenter$onWalletSelected$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SelectAddressPresenter$onWalletSelected$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r1v4, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r1v5, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r1v6, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r1v7, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r1v8, types: [com.bitcoin.bitcoink.address.AddressSLP] */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v2
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], java.lang.String, com.bitcoin.bitcoink.address.AddressSLP]
      uses: [java.lang.String, java.lang.Object]
      mth insns count: 60
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 4 */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(@org.jetbrains.annotations.NotNull java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L_0x0017
            if (r1 != r2) goto L_0x000f
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x002f
        L_0x000f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x0017:
            kotlin.ResultKt.throwOnFailure(r5)
            kotlinx.coroutines.CoroutineScope r5 = r4.f295p$
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r5 = r4.this$0
            com.bitcoin.mwallet.core.interactors.GetAddressInteractor r5 = r5.addressInteractor
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r4.$walletId
            com.bitcoin.mwallet.core.models.address.AddressPurpose r3 = com.bitcoin.mwallet.core.models.address.AddressPurpose.RECEIVE
            r4.label = r2
            java.lang.Object r5 = r5.getNextAvailableAddress(r1, r3, r4)
            if (r5 != r0) goto L_0x002f
            return r0
        L_0x002f:
            com.bitcoin.mwallet.core.models.address.AddressAndPath r5 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r5
            com.bitcoin.mwallet.core.entity.WalletType r0 = r4.$walletType
            int[] r1 = com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter.WhenMappings.$EnumSwitchMapping$1
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            if (r0 == r2) goto L_0x0079
            r2 = 2
            if (r0 == r2) goto L_0x005b
            r2 = 3
            if (r0 != r2) goto L_0x0055
            if (r5 == 0) goto L_0x0050
            com.bitcoin.bitcoink.address.Address r5 = r5.getAddress()
            if (r5 == 0) goto L_0x0050
            com.bitcoin.bitcoink.address.AddressSLP r1 = r5.toSlp()
        L_0x0050:
            java.lang.String r5 = java.lang.String.valueOf(r1)
            goto L_0x0096
        L_0x0055:
            kotlin.NoWhenBranchMatchedException r5 = new kotlin.NoWhenBranchMatchedException
            r5.<init>()
            throw r5
        L_0x005b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "bitcoin:"
            r0.append(r2)
            if (r5 == 0) goto L_0x0071
            com.bitcoin.bitcoink.address.Address r5 = r5.getAddress()
            if (r5 == 0) goto L_0x0071
            java.lang.String r1 = r5.toString()
        L_0x0071:
            r0.append(r1)
            java.lang.String r5 = r0.toString()
            goto L_0x0096
        L_0x0079:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "bitcoincash:"
            r0.append(r2)
            if (r5 == 0) goto L_0x008f
            com.bitcoin.bitcoink.address.Address r5 = r5.getAddress()
            if (r5 == 0) goto L_0x008f
            java.lang.String r1 = r5.toString()
        L_0x008f:
            r0.append(r1)
            java.lang.String r5 = r0.toString()
        L_0x0096:
            com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter r0 = r4.this$0
            androidx.lifecycle.MutableLiveData r0 = r0.getRawInput()
            r0.postValue(r5)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.selectaddress.SelectAddressPresenter$onWalletSelected$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
