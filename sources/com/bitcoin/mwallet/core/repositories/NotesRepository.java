package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.mwallet.core.services.p010tx.TxService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J1\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/NotesRepository;", "", "txServiceGrpc", "Lcom/bitcoin/mwallet/core/services/tx/TxService;", "historyRepository", "Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;", "(Lcom/bitcoin/mwallet/core/services/tx/TxService;Lcom/bitcoin/mwallet/core/repositories/TransactionHistoryRepository;)V", "setTxNote", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "copayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "signingKey", "Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;", "note", "Lcom/bitcoin/mwallet/TxNote;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/CopayerId;Lcom/bitcoin/mwallet/core/utils/signature/SigningKey;Lcom/bitcoin/mwallet/TxNote;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: NotesRepository.kt */
public final class NotesRepository {
    private final TransactionHistoryRepository historyRepository;
    private final TxService txServiceGrpc;

    public NotesRepository(@NotNull TxService txService, @NotNull TransactionHistoryRepository transactionHistoryRepository) {
        Intrinsics.checkParameterIsNotNull(txService, "txServiceGrpc");
        Intrinsics.checkParameterIsNotNull(transactionHistoryRepository, "historyRepository");
        this.txServiceGrpc = txService;
        this.historyRepository = transactionHistoryRepository;
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x010d A[Catch:{ Exception -> 0x0168 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0158 A[Catch:{ Exception -> 0x0168 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01bf A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01c0 A[PHI: r0 
      PHI: (r0v2 java.lang.Object) = (r0v4 java.lang.Object), (r0v1 java.lang.Object) binds: [B:65:0x01bd, B:11:0x0030] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object setTxNote(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r17, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.CopayerId r18, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.utils.signature.SigningKey r19, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.TxNote r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            r16 = this;
            r1 = r16
            r0 = r21
            boolean r2 = r0 instanceof com.bitcoin.mwallet.core.repositories.NotesRepository$setTxNote$1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            com.bitcoin.mwallet.core.repositories.NotesRepository$setTxNote$1 r2 = (com.bitcoin.mwallet.core.repositories.NotesRepository$setTxNote$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L_0x001d
        L_0x0018:
            com.bitcoin.mwallet.core.repositories.NotesRepository$setTxNote$1 r2 = new com.bitcoin.mwallet.core.repositories.NotesRepository$setTxNote$1
            r2.<init>(r1, r0)
        L_0x001d:
            java.lang.Object r0 = r2.result
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r2.label
            r10 = 3
            r11 = 2
            r4 = 1
            if (r3 == 0) goto L_0x00ab
            if (r3 == r4) goto L_0x0082
            if (r3 == r11) goto L_0x0055
            if (r3 != r10) goto L_0x004d
            java.lang.Object r3 = r2.L$5
            java.lang.Exception r3 = (java.lang.Exception) r3
            java.lang.Object r3 = r2.L$4
            com.bitcoin.mwallet.TxNote r3 = (com.bitcoin.mwallet.TxNote) r3
            java.lang.Object r3 = r2.L$3
            com.bitcoin.mwallet.core.utils.signature.SigningKey r3 = (com.bitcoin.mwallet.core.utils.signature.SigningKey) r3
            java.lang.Object r3 = r2.L$2
            com.bitcoin.mwallet.core.models.CopayerId r3 = (com.bitcoin.mwallet.core.models.CopayerId) r3
            java.lang.Object r3 = r2.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r3 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r3
            java.lang.Object r2 = r2.L$0
            com.bitcoin.mwallet.core.repositories.NotesRepository r2 = (com.bitcoin.mwallet.core.repositories.NotesRepository) r2
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x01c0
        L_0x004d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0055:
            java.lang.Object r3 = r2.L$7
            com.bitcoin.mwallet.core.services.tx.UpdateNotesResponse r3 = (com.bitcoin.mwallet.core.services.p010tx.UpdateNotesResponse) r3
            java.lang.Object r3 = r2.L$6
            com.bitcoin.mwallet.TxNote r3 = (com.bitcoin.mwallet.TxNote) r3
            java.lang.Object r3 = r2.L$5
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r2.L$4
            com.bitcoin.mwallet.TxNote r3 = (com.bitcoin.mwallet.TxNote) r3
            java.lang.Object r4 = r2.L$3
            com.bitcoin.mwallet.core.utils.signature.SigningKey r4 = (com.bitcoin.mwallet.core.utils.signature.SigningKey) r4
            java.lang.Object r5 = r2.L$2
            com.bitcoin.mwallet.core.models.CopayerId r5 = (com.bitcoin.mwallet.core.models.CopayerId) r5
            java.lang.Object r6 = r2.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r6 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r6
            java.lang.Object r7 = r2.L$0
            com.bitcoin.mwallet.core.repositories.NotesRepository r7 = (com.bitcoin.mwallet.core.repositories.NotesRepository) r7
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x007a }
            goto L_0x0145
        L_0x007a:
            r0 = move-exception
            r15 = r4
            r14 = r5
            r13 = r6
            r12 = r7
            r5 = r3
            goto L_0x017a
        L_0x0082:
            java.lang.Object r3 = r2.L$6
            com.bitcoin.mwallet.TxNote r3 = (com.bitcoin.mwallet.TxNote) r3
            java.lang.Object r4 = r2.L$5
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r5 = r2.L$4
            com.bitcoin.mwallet.TxNote r5 = (com.bitcoin.mwallet.TxNote) r5
            java.lang.Object r6 = r2.L$3
            com.bitcoin.mwallet.core.utils.signature.SigningKey r6 = (com.bitcoin.mwallet.core.utils.signature.SigningKey) r6
            java.lang.Object r7 = r2.L$2
            com.bitcoin.mwallet.core.models.CopayerId r7 = (com.bitcoin.mwallet.core.models.CopayerId) r7
            java.lang.Object r8 = r2.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r8 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r8
            java.lang.Object r12 = r2.L$0
            com.bitcoin.mwallet.core.repositories.NotesRepository r12 = (com.bitcoin.mwallet.core.repositories.NotesRepository) r12
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ Exception -> 0x00a5 }
            r15 = r6
            r14 = r7
            r13 = r8
            goto L_0x0105
        L_0x00a5:
            r0 = move-exception
            r15 = r6
            r14 = r7
            r13 = r8
            goto L_0x017a
        L_0x00ab:
            kotlin.ResultKt.throwOnFailure(r0)
            java.lang.String r0 = r19.getPrivateKey()     // Catch:{ Exception -> 0x0170 }
            java.lang.String r3 = r20.getNote()     // Catch:{ Exception -> 0x0170 }
            java.lang.String r0 = walletutils.Walletutils.encrypt(r0, r3)     // Catch:{ Exception -> 0x0170 }
            com.bitcoin.mwallet.TxNote$Builder r3 = com.bitcoin.mwallet.TxNote.newBuilder()     // Catch:{ Exception -> 0x0170 }
            com.bitcoin.mwallet.TxNote$Builder r3 = r3.setNote(r0)     // Catch:{ Exception -> 0x0170 }
            java.lang.String r5 = r20.getTxId()     // Catch:{ Exception -> 0x0170 }
            com.bitcoin.mwallet.TxNote$Builder r3 = r3.setTxId(r5)     // Catch:{ Exception -> 0x0170 }
            com.google.protobuf.GeneratedMessageLite r3 = r3.build()     // Catch:{ Exception -> 0x0170 }
            java.lang.String r5 = "TxNote.newBuilder()\n    …\n                .build()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r5)     // Catch:{ Exception -> 0x0170 }
            r12 = r3
            com.bitcoin.mwallet.TxNote r12 = (com.bitcoin.mwallet.TxNote) r12     // Catch:{ Exception -> 0x0170 }
            com.bitcoin.mwallet.core.services.tx.TxService r3 = r1.txServiceGrpc     // Catch:{ Exception -> 0x0170 }
            r2.L$0 = r1     // Catch:{ Exception -> 0x0170 }
            r13 = r17
            r2.L$1 = r13     // Catch:{ Exception -> 0x016e }
            r14 = r18
            r2.L$2 = r14     // Catch:{ Exception -> 0x016c }
            r15 = r19
            r2.L$3 = r15     // Catch:{ Exception -> 0x016a }
            r8 = r20
            r2.L$4 = r8     // Catch:{ Exception -> 0x016a }
            r2.L$5 = r0     // Catch:{ Exception -> 0x016a }
            r2.L$6 = r12     // Catch:{ Exception -> 0x016a }
            r2.label = r4     // Catch:{ Exception -> 0x016a }
            r4 = r17
            r5 = r18
            r6 = r19
            r7 = r12
            r8 = r2
            java.lang.Object r3 = r3.updateNotes(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x016a }
            if (r3 != r9) goto L_0x00ff
            return r9
        L_0x00ff:
            r5 = r20
            r4 = r0
            r0 = r3
            r3 = r12
            r12 = r1
        L_0x0105:
            com.bitcoin.mwallet.core.services.tx.UpdateNotesResponse r0 = (com.bitcoin.mwallet.core.services.p010tx.UpdateNotesResponse) r0     // Catch:{ Exception -> 0x0168 }
            com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse r6 = r0.getGrpcError()     // Catch:{ Exception -> 0x0168 }
            if (r6 != 0) goto L_0x0158
            java.lang.RuntimeException r6 = r0.getError()     // Catch:{ Exception -> 0x0168 }
            if (r6 != 0) goto L_0x0148
            com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository r6 = r12.historyRepository     // Catch:{ Exception -> 0x0168 }
            com.bitcoin.bitcoink.tx.TxId r7 = new com.bitcoin.bitcoink.tx.TxId     // Catch:{ Exception -> 0x0168 }
            java.lang.String r8 = r3.getTxId()     // Catch:{ Exception -> 0x0168 }
            java.lang.String r10 = "encryptedTxNote.txId"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r10)     // Catch:{ Exception -> 0x0168 }
            r7.<init>(r8)     // Catch:{ Exception -> 0x0168 }
            java.lang.String r8 = r3.getNote()     // Catch:{ Exception -> 0x0168 }
            java.lang.String r10 = "encryptedTxNote.note"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r10)     // Catch:{ Exception -> 0x0168 }
            r2.L$0 = r12     // Catch:{ Exception -> 0x0168 }
            r2.L$1 = r13     // Catch:{ Exception -> 0x0168 }
            r2.L$2 = r14     // Catch:{ Exception -> 0x0168 }
            r2.L$3 = r15     // Catch:{ Exception -> 0x0168 }
            r2.L$4 = r5     // Catch:{ Exception -> 0x0168 }
            r2.L$5 = r4     // Catch:{ Exception -> 0x0168 }
            r2.L$6 = r3     // Catch:{ Exception -> 0x0168 }
            r2.L$7 = r0     // Catch:{ Exception -> 0x0168 }
            r2.label = r11     // Catch:{ Exception -> 0x0168 }
            java.lang.Object r0 = r6.setTransactionNote(r7, r8, r13, r2)     // Catch:{ Exception -> 0x0168 }
            if (r0 != r9) goto L_0x0145
            return r9
        L_0x0145:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0148:
            java.lang.Exception r3 = new java.lang.Exception     // Catch:{ Exception -> 0x0168 }
            java.lang.RuntimeException r0 = r0.getError()     // Catch:{ Exception -> 0x0168 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0168 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0168 }
            java.lang.Throwable r3 = (java.lang.Throwable) r3     // Catch:{ Exception -> 0x0168 }
            throw r3     // Catch:{ Exception -> 0x0168 }
        L_0x0158:
            java.lang.Exception r3 = new java.lang.Exception     // Catch:{ Exception -> 0x0168 }
            com.bitcoin.mwallet.core.services.grpc.GrpcErrorResponse r0 = r0.getGrpcError()     // Catch:{ Exception -> 0x0168 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Exception -> 0x0168 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0168 }
            java.lang.Throwable r3 = (java.lang.Throwable) r3     // Catch:{ Exception -> 0x0168 }
            throw r3     // Catch:{ Exception -> 0x0168 }
        L_0x0168:
            r0 = move-exception
            goto L_0x017a
        L_0x016a:
            r0 = move-exception
            goto L_0x0177
        L_0x016c:
            r0 = move-exception
            goto L_0x0175
        L_0x016e:
            r0 = move-exception
            goto L_0x0173
        L_0x0170:
            r0 = move-exception
            r13 = r17
        L_0x0173:
            r14 = r18
        L_0x0175:
            r15 = r19
        L_0x0177:
            r5 = r20
            r12 = r1
        L_0x017a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Encryption Failed :"
            r3.append(r4)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]
            timber.log.Timber.m426d(r3, r4)
            com.bitcoin.mwallet.core.repositories.TransactionHistoryRepository r3 = r12.historyRepository
            com.bitcoin.bitcoink.tx.TxId r4 = new com.bitcoin.bitcoink.tx.TxId
            java.lang.String r6 = r5.getTxId()
            java.lang.String r7 = "note.txId"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            r4.<init>(r6)
            java.lang.String r6 = r5.getNote()
            java.lang.String r7 = "note.note"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            r2.L$0 = r12
            r2.L$1 = r13
            r2.L$2 = r14
            r2.L$3 = r15
            r2.L$4 = r5
            r2.L$5 = r0
            r5 = 3
            r2.label = r5
            java.lang.Object r0 = r3.setTransactionNote(r4, r6, r13, r2)
            if (r0 != r9) goto L_0x01c0
            return r9
        L_0x01c0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.NotesRepository.setTxNote(com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.mwallet.core.models.CopayerId, com.bitcoin.mwallet.core.utils.signature.SigningKey, com.bitcoin.mwallet.TxNote, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
