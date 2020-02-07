package com.bitcoin.mwallet.core.services.address;

import com.bitcoin.mwallet.AddressPath;
import com.bitcoin.mwallet.AddressServiceGrpc.AddressServiceBlockingStub;
import com.bitcoin.mwallet.GetNewMaxAddressPathResponse;
import com.bitcoin.mwallet.GetNewMaxAddressPathResponse.ResponseCase;
import com.bitcoin.mwallet.ResponseError;
import com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse;
import com.bitcoin.mwallet.core.services.grpc.GrpcServiceBase;
import com.bitcoin.mwallet.core.services.grpc.ProtoConverterKt;
import com.google.protobuf.GeneratedMessageLite;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import org.jetbrains.annotations.NotNull;
import p015io.grpc.ManagedChannel;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u00012\u00020\u0002:\u0001\u0018B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J1\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J)\u0010\u0015\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/address/AddressServiceGrpc;", "Lcom/bitcoin/mwallet/core/services/grpc/GrpcServiceBase;", "Lcom/bitcoin/mwallet/core/services/address/AddressService;", "grpcDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "channel", "Lio/grpc/ManagedChannel;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lio/grpc/ManagedChannel;)V", "blockingStub", "Lcom/bitcoin/mwallet/AddressServiceGrpc$AddressServiceBlockingStub;", "getNewMaxAddressPath", "Lcom/bitcoin/mwallet/core/services/address/NewMaxAddressPathResponse;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "currentMaxPath", "Lcom/bitcoin/mwallet/core/services/address/AddressPath;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;Lcom/bitcoin/mwallet/core/services/address/AddressPath;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scanForFunds", "", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddressServiceGrpc.kt */
public final class AddressServiceGrpc extends GrpcServiceBase implements AddressService {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public final AddressServiceBlockingStub blockingStub;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0002J\f\u0010\u0003\u001a\u00020\u0006*\u00020\u0007H\u0002J\f\u0010\b\u001a\u00020\u0005*\u00020\u0004H\u0002¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/address/AddressServiceGrpc$Companion;", "", "()V", "toDomain", "Lcom/bitcoin/mwallet/core/services/address/AddressPath;", "Lcom/bitcoin/mwallet/AddressPath;", "Lcom/bitcoin/mwallet/core/services/address/NewMaxAddressPathResponse;", "Lcom/bitcoin/mwallet/GetNewMaxAddressPathResponse;", "toProto", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: AddressServiceGrpc.kt */
    public static final class Companion {

        @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ResponseCase.values().length];

            static {
                $EnumSwitchMapping$0[ResponseCase.NEW_MAX_PATH.ordinal()] = 1;
                $EnumSwitchMapping$0[ResponseCase.ERROR.ordinal()] = 2;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final AddressPath toProto(@NotNull AddressPath addressPath) {
            GeneratedMessageLite build = AddressPath.newBuilder().setX(addressPath.getX()).setY(addressPath.getY()).build();
            Intrinsics.checkExpressionValueIsNotNull(build, "com.bitcoin.mwallet.Addr…\n                .build()");
            return (AddressPath) build;
        }

        private final AddressPath toDomain(@NotNull AddressPath addressPath) {
            return new AddressPath(addressPath.getX(), addressPath.getY());
        }

        /* access modifiers changed from: private */
        public final NewMaxAddressPathResponse toDomain(@NotNull GetNewMaxAddressPathResponse getNewMaxAddressPathResponse) {
            ResponseCase responseCase = getNewMaxAddressPathResponse.getResponseCase();
            if (responseCase != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[responseCase.ordinal()];
                if (i == 1) {
                    Companion companion = this;
                    AddressPath newMaxPath = getNewMaxAddressPathResponse.getNewMaxPath();
                    Intrinsics.checkExpressionValueIsNotNull(newMaxPath, "this.newMaxPath");
                    return new NewMaxAddressPathResponse(companion.toDomain(newMaxPath));
                } else if (i == 2) {
                    ResponseError error = getNewMaxAddressPathResponse.getError();
                    Intrinsics.checkExpressionValueIsNotNull(error, "this.error");
                    return new NewMaxAddressPathResponse(ProtoConverterKt.toDomain(error));
                }
            }
            return new NewMaxAddressPathResponse(GrpcErrorResponse.Companion.unknown());
        }
    }

    public AddressServiceGrpc(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull ManagedChannel managedChannel) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "grpcDispatcher");
        Intrinsics.checkParameterIsNotNull(managedChannel, "channel");
        super(coroutineDispatcher);
        AddressServiceBlockingStub newBlockingStub = com.bitcoin.mwallet.AddressServiceGrpc.newBlockingStub(managedChannel);
        Intrinsics.checkExpressionValueIsNotNull(newBlockingStub, "AddressServiceGrpc.newBlockingStub(channel)");
        this.blockingStub = newBlockingStub;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getNewMaxAddressPath(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r14, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.CopayerId r15, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.utils.signature.SigningKey r16, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.services.address.AddressPath r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.services.address.NewMaxAddressPathResponse> r18) {
        /*
            r13 = this;
            r8 = r13
            r0 = r18
            boolean r1 = r0 instanceof com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$getNewMaxAddressPath$1
            if (r1 == 0) goto L_0x0017
            r1 = r0
            com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$getNewMaxAddressPath$1 r1 = (com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$getNewMaxAddressPath$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0017
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001c
        L_0x0017:
            com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$getNewMaxAddressPath$1 r1 = new com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$getNewMaxAddressPath$1
            r1.<init>(r13, r0)
        L_0x001c:
            r0 = r1
            java.lang.Object r1 = r0.result
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r10 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 != r10) goto L_0x0042
            java.lang.Object r2 = r0.L$4
            com.bitcoin.mwallet.core.services.address.AddressPath r2 = (com.bitcoin.mwallet.core.services.address.AddressPath) r2
            java.lang.Object r2 = r0.L$3
            com.bitcoin.mwallet.core.utils.signature.SigningKey r2 = (com.bitcoin.mwallet.core.utils.signature.SigningKey) r2
            java.lang.Object r2 = r0.L$2
            com.bitcoin.mwallet.core.models.CopayerId r2 = (com.bitcoin.mwallet.core.models.CopayerId) r2
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r2
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.services.address.AddressServiceGrpc r0 = (com.bitcoin.mwallet.core.services.address.AddressServiceGrpc) r0
            kotlin.ResultKt.throwOnFailure(r1)     // Catch:{ RuntimeException -> 0x007b }
            goto L_0x0078
        L_0x0042:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x004a:
            kotlin.ResultKt.throwOnFailure(r1)
            java.lang.String r11 = "getNewMaxAddressPath"
            com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$getNewMaxAddressPath$2 r12 = new com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$getNewMaxAddressPath$2     // Catch:{ RuntimeException -> 0x007b }
            r7 = 0
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r17
            r5 = r15
            r6 = r16
            r1.<init>(r2, r3, r4, r5, r6, r7)     // Catch:{ RuntimeException -> 0x007b }
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12     // Catch:{ RuntimeException -> 0x007b }
            r0.L$0 = r8     // Catch:{ RuntimeException -> 0x007b }
            r1 = r14
            r0.L$1 = r1     // Catch:{ RuntimeException -> 0x007b }
            r1 = r15
            r0.L$2 = r1     // Catch:{ RuntimeException -> 0x007b }
            r1 = r16
            r0.L$3 = r1     // Catch:{ RuntimeException -> 0x007b }
            r1 = r17
            r0.L$4 = r1     // Catch:{ RuntimeException -> 0x007b }
            r0.label = r10     // Catch:{ RuntimeException -> 0x007b }
            java.lang.Object r1 = r13.logDuration(r11, r12, r0)     // Catch:{ RuntimeException -> 0x007b }
            if (r1 != r9) goto L_0x0078
            return r9
        L_0x0078:
            com.bitcoin.mwallet.core.services.address.NewMaxAddressPathResponse r1 = (com.bitcoin.mwallet.core.services.address.NewMaxAddressPathResponse) r1     // Catch:{ RuntimeException -> 0x007b }
            goto L_0x0081
        L_0x007b:
            r0 = move-exception
            com.bitcoin.mwallet.core.services.address.NewMaxAddressPathResponse r1 = new com.bitcoin.mwallet.core.services.address.NewMaxAddressPathResponse
            r1.<init>(r0)
        L_0x0081:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.address.AddressServiceGrpc.getNewMaxAddressPath(com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.mwallet.core.models.CopayerId, com.bitcoin.mwallet.core.utils.signature.SigningKey, com.bitcoin.mwallet.core.services.address.AddressPath, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object scanForFunds(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r11, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.CopayerId r12, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.utils.signature.SigningKey r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$scanForFunds$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$scanForFunds$1 r0 = (com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$scanForFunds$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$scanForFunds$1 r0 = new com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$scanForFunds$1
            r0.<init>(r10, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0042
            if (r2 != r3) goto L_0x003a
            java.lang.Object r11 = r0.L$3
            com.bitcoin.mwallet.core.utils.signature.SigningKey r11 = (com.bitcoin.mwallet.core.utils.signature.SigningKey) r11
            java.lang.Object r11 = r0.L$2
            com.bitcoin.mwallet.core.models.CopayerId r11 = (com.bitcoin.mwallet.core.models.CopayerId) r11
            java.lang.Object r11 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r11 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r11
            java.lang.Object r11 = r0.L$0
            com.bitcoin.mwallet.core.services.address.AddressServiceGrpc r11 = (com.bitcoin.mwallet.core.services.address.AddressServiceGrpc) r11
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ RuntimeException -> 0x0069 }
            goto L_0x0074
        L_0x003a:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlinx.coroutines.CoroutineDispatcher r14 = r10.getGrpcDispatcher()     // Catch:{ RuntimeException -> 0x0069 }
            kotlin.coroutines.CoroutineContext r14 = (kotlin.coroutines.CoroutineContext) r14     // Catch:{ RuntimeException -> 0x0069 }
            com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$scanForFunds$2 r2 = new com.bitcoin.mwallet.core.services.address.AddressServiceGrpc$scanForFunds$2     // Catch:{ RuntimeException -> 0x0069 }
            r9 = 0
            r4 = r2
            r5 = r10
            r6 = r11
            r7 = r12
            r8 = r13
            r4.<init>(r5, r6, r7, r8, r9)     // Catch:{ RuntimeException -> 0x0069 }
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2     // Catch:{ RuntimeException -> 0x0069 }
            r0.L$0 = r10     // Catch:{ RuntimeException -> 0x0069 }
            r0.L$1 = r11     // Catch:{ RuntimeException -> 0x0069 }
            r0.L$2 = r12     // Catch:{ RuntimeException -> 0x0069 }
            r0.L$3 = r13     // Catch:{ RuntimeException -> 0x0069 }
            r0.label = r3     // Catch:{ RuntimeException -> 0x0069 }
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r14, r2, r0)     // Catch:{ RuntimeException -> 0x0069 }
            if (r11 != r1) goto L_0x0074
            return r1
        L_0x0069:
            r11 = move-exception
            java.lang.Object[] r12 = new java.lang.Object[r3]
            r13 = 0
            r12[r13] = r11
            java.lang.String r11 = "Sorry not scanning for you"
            timber.log.Timber.m429e(r11, r12)
        L_0x0074:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.services.address.AddressServiceGrpc.scanForFunds(com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.mwallet.core.models.CopayerId, com.bitcoin.mwallet.core.utils.signature.SigningKey, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
