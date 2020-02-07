package com.bitcoin.mwallet.core.repositories;

import com.bitcoin.bitcoink.PrivateKey;
import com.bitcoin.mwallet.core.dao.CredentialDao;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J3\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/repositories/PrivateKeyRepository;", "", "credentialDao", "Lcom/bitcoin/mwallet/core/dao/CredentialDao;", "(Lcom/bitcoin/mwallet/core/dao/CredentialDao;)V", "walletPrivateKeys", "", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "Lcom/bitcoin/bitcoink/PrivateKey;", "getPrivateKey", "walletNetwork", "Lcom/bitcoin/bitcoink/Network;", "walletCredentialId", "walletPath", "Lcom/bitcoin/bitcoink/DerivationPath;", "addressPath", "(Lcom/bitcoin/bitcoink/Network;Lcom/bitcoin/mwallet/core/models/credential/CredentialId;Lcom/bitcoin/bitcoink/DerivationPath;Lcom/bitcoin/bitcoink/DerivationPath;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: PrivateKeyRepository.kt */
public final class PrivateKeyRepository {
    /* access modifiers changed from: private */
    public final CredentialDao credentialDao;
    /* access modifiers changed from: private */
    public final Map<CredentialId, PrivateKey> walletPrivateKeys = new HashMap();

    public PrivateKeyRepository(@NotNull CredentialDao credentialDao2) {
        Intrinsics.checkParameterIsNotNull(credentialDao2, "credentialDao");
        this.credentialDao = credentialDao2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00bd A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getPrivateKey(@org.jetbrains.annotations.NotNull com.bitcoin.bitcoink.Network r7, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.credential.CredentialId r8, @org.jetbrains.annotations.NotNull com.bitcoin.bitcoink.DerivationPath r9, @org.jetbrains.annotations.NotNull com.bitcoin.bitcoink.DerivationPath r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.bitcoink.PrivateKey> r11) {
        /*
            r6 = this;
            boolean r0 = r11 instanceof com.bitcoin.mwallet.core.repositories.PrivateKeyRepository$getPrivateKey$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            com.bitcoin.mwallet.core.repositories.PrivateKeyRepository$getPrivateKey$1 r0 = (com.bitcoin.mwallet.core.repositories.PrivateKeyRepository$getPrivateKey$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.repositories.PrivateKeyRepository$getPrivateKey$1 r0 = new com.bitcoin.mwallet.core.repositories.PrivateKeyRepository$getPrivateKey$1
            r0.<init>(r6, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0050
            if (r2 != r3) goto L_0x0048
            java.lang.Object r7 = r0.L$6
            kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref.ObjectRef) r7
            java.lang.Object r8 = r0.L$5
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r9 = r0.L$4
            r10 = r9
            com.bitcoin.bitcoink.DerivationPath r10 = (com.bitcoin.bitcoink.DerivationPath) r10
            java.lang.Object r9 = r0.L$3
            com.bitcoin.bitcoink.DerivationPath r9 = (com.bitcoin.bitcoink.DerivationPath) r9
            java.lang.Object r1 = r0.L$2
            com.bitcoin.mwallet.core.models.credential.CredentialId r1 = (com.bitcoin.mwallet.core.models.credential.CredentialId) r1
            java.lang.Object r2 = r0.L$1
            com.bitcoin.bitcoink.Network r2 = (com.bitcoin.bitcoink.Network) r2
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.repositories.PrivateKeyRepository r0 = (com.bitcoin.mwallet.core.repositories.PrivateKeyRepository) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x008b
        L_0x0048:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0050:
            kotlin.ResultKt.throwOnFailure(r11)
            com.bitcoin.mwallet.core.models.credential.CredentialType r11 = r8.getType()
            com.bitcoin.mwallet.core.models.credential.CredentialType r2 = com.bitcoin.mwallet.core.models.credential.CredentialType.MNEMONIC_AND_PRIVATE_KEY
            if (r11 == r2) goto L_0x005c
            return r4
        L_0x005c:
            kotlin.jvm.internal.Ref$ObjectRef r11 = new kotlin.jvm.internal.Ref$ObjectRef
            r11.<init>()
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            com.bitcoin.mwallet.core.repositories.PrivateKeyRepository$getPrivateKey$walletPrivateKey$1 r5 = new com.bitcoin.mwallet.core.repositories.PrivateKeyRepository$getPrivateKey$walletPrivateKey$1
            r5.<init>(r6, r8, r4)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            r0.L$3 = r9
            r0.L$4 = r10
            r0.L$5 = r11
            r0.L$6 = r11
            r0.label = r3
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r2, r5, r0)
            if (r0 != r1) goto L_0x0085
            return r1
        L_0x0085:
            r2 = r7
            r1 = r8
            r7 = r11
            r8 = r7
            r11 = r0
            r0 = r6
        L_0x008b:
            com.bitcoin.bitcoink.PrivateKey r11 = (com.bitcoin.bitcoink.PrivateKey) r11
            if (r11 == 0) goto L_0x00bd
            r7.element = r11
            java.util.Map<com.bitcoin.mwallet.core.models.credential.CredentialId, com.bitcoin.bitcoink.PrivateKey> r7 = r0.walletPrivateKeys
            java.lang.Object r11 = r7.get(r1)
            if (r11 != 0) goto L_0x00ab
            T r11 = r8.element
            com.bitcoin.bitcoink.PrivateKey r11 = (com.bitcoin.bitcoink.PrivateKey) r11
            kotlin.Pair r9 = r11.deriveChild(r2, r9)
            java.lang.Object r9 = r9.getFirst()
            r11 = r9
            com.bitcoin.bitcoink.PrivateKey r11 = (com.bitcoin.bitcoink.PrivateKey) r11
            r7.put(r1, r11)
        L_0x00ab:
            com.bitcoin.bitcoink.PrivateKey r11 = (com.bitcoin.bitcoink.PrivateKey) r11
            r8.element = r11
            com.bitcoin.mwallet.core.repositories.PrivateKeyRepository$getPrivateKey$3 r7 = new com.bitcoin.mwallet.core.repositories.PrivateKeyRepository$getPrivateKey$3
            r7.<init>(r8, r2, r10)
            kotlin.jvm.functions.Function0 r7 = (kotlin.jvm.functions.Function0) r7
            java.lang.String r8 = "walletPrivateKey deriveChild"
            java.lang.Object r7 = com.bitcoin.mwallet.core.utils.MeasureKt.logDuration(r8, r7)
            return r7
        L_0x00bd:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.repositories.PrivateKeyRepository.getPrivateKey(com.bitcoin.bitcoink.Network, com.bitcoin.mwallet.core.models.credential.CredentialId, com.bitcoin.bitcoink.DerivationPath, com.bitcoin.bitcoink.DerivationPath, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
