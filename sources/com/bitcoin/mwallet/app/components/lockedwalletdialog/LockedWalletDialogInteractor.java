package com.bitcoin.mwallet.app.components.lockedwalletdialog;

import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor;
import com.bitcoin.mwallet.core.interactors.GetCredentialInteractor;
import com.bitcoin.mwallet.core.interactors.GetWalletInteractor;
import com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ#\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J!\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/lockedwalletdialog/LockedWalletDialogInteractor;", "", "credentialInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetCredentialInteractor;", "walletInteractor", "Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;", "createWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;", "modifyWalletInteractor", "Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;", "(Lcom/bitcoin/mwallet/core/interactors/GetCredentialInteractor;Lcom/bitcoin/mwallet/core/interactors/GetWalletInteractor;Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor;Lcom/bitcoin/mwallet/core/interactors/ModifyWalletInteractor;)V", "decryptMnemonic", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "password", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unlockWallet", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor$CreateWalletResult;", "mnemonicString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LockedWalletDialogInteractor.kt */
public final class LockedWalletDialogInteractor {
    private final CreateWalletInteractor createWalletInteractor;
    private final GetCredentialInteractor credentialInteractor;
    private final ModifyWalletInteractor modifyWalletInteractor;
    private final GetWalletInteractor walletInteractor;

    public LockedWalletDialogInteractor(@NotNull GetCredentialInteractor getCredentialInteractor, @NotNull GetWalletInteractor getWalletInteractor, @NotNull CreateWalletInteractor createWalletInteractor2, @NotNull ModifyWalletInteractor modifyWalletInteractor2) {
        Intrinsics.checkParameterIsNotNull(getCredentialInteractor, "credentialInteractor");
        Intrinsics.checkParameterIsNotNull(getWalletInteractor, "walletInteractor");
        Intrinsics.checkParameterIsNotNull(createWalletInteractor2, "createWalletInteractor");
        Intrinsics.checkParameterIsNotNull(modifyWalletInteractor2, "modifyWalletInteractor");
        this.credentialInteractor = getCredentialInteractor;
        this.walletInteractor = getWalletInteractor;
        this.createWalletInteractor = createWalletInteractor2;
        this.modifyWalletInteractor = modifyWalletInteractor2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005a A[SYNTHETIC, Splitter:B:17:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object decryptMnemonic(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r5, @org.jetbrains.annotations.NotNull java.lang.String r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor$decryptMnemonic$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor$decryptMnemonic$1 r0 = (com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor$decryptMnemonic$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor$decryptMnemonic$1 r0 = new com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor$decryptMnemonic$1
            r0.<init>(r4, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003f
            if (r2 != r3) goto L_0x0037
            java.lang.Object r5 = r0.L$2
            r6 = r5
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r5 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r5 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r5
            java.lang.Object r5 = r0.L$0
            com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor r5 = (com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0053
        L_0x0037:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r7)
            com.bitcoin.mwallet.core.interactors.GetCredentialInteractor r7 = r4.credentialInteractor
            r0.L$0 = r4
            r0.L$1 = r5
            r0.L$2 = r6
            r0.label = r3
            java.lang.Object r7 = r7.getWalletCredential(r5, r0)
            if (r7 != r1) goto L_0x0053
            return r1
        L_0x0053:
            com.bitcoin.mwallet.core.models.credential.Credential r7 = (com.bitcoin.mwallet.core.models.credential.Credential) r7
            boolean r5 = r7 instanceof com.bitcoin.mwallet.core.models.credential.CredentialEncrypted
            r0 = 0
            if (r5 == 0) goto L_0x0065
            com.bitcoin.mwallet.core.models.credential.CredentialEncrypted r7 = (com.bitcoin.mwallet.core.models.credential.CredentialEncrypted) r7     // Catch:{ Exception -> 0x0065 }
            java.lang.String r5 = r7.getEncryptedMnemonic()     // Catch:{ Exception -> 0x0065 }
            java.lang.String r5 = walletutils.Walletutils.decryptSJCLJsonString(r5, r6)     // Catch:{ Exception -> 0x0065 }
            return r5
        L_0x0065:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor.decryptMnemonic(com.bitcoin.mwallet.core.models.wallet.WalletId, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object unlockWallet(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.wallet.WalletId r11, @org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult> r13) {
        /*
            r10 = this;
            boolean r0 = r13 instanceof com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor$unlockWallet$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor$unlockWallet$1 r0 = (com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor$unlockWallet$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor$unlockWallet$1 r0 = new com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor$unlockWallet$1
            r0.<init>(r10, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 1
            r5 = 2
            if (r2 == 0) goto L_0x0081
            if (r2 == r4) goto L_0x006d
            if (r2 == r5) goto L_0x0052
            if (r2 != r3) goto L_0x004a
            java.lang.Object r11 = r0.L$5
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r11 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult) r11
            java.lang.Object r12 = r0.L$4
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r12 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r12
            java.lang.Object r12 = r0.L$3
            com.bitcoin.mwallet.core.models.wallet.Wallet r12 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r12
            java.lang.Object r12 = r0.L$2
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r12 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r12 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r12
            java.lang.Object r12 = r0.L$0
            com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor r12 = (com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor) r12
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r11
            goto L_0x00e7
        L_0x004a:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0052:
            java.lang.Object r11 = r0.L$4
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r11 = (com.bitcoin.mwallet.core.models.credential.CredentialMnemonic) r11
            java.lang.Object r12 = r0.L$3
            com.bitcoin.mwallet.core.models.wallet.Wallet r12 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r12
            java.lang.Object r2 = r0.L$2
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r4 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r4 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r4
            java.lang.Object r5 = r0.L$0
            com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor r5 = (com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor) r5
            kotlin.ResultKt.throwOnFailure(r13)
            r9 = r5
            r5 = r11
            r11 = r9
            goto L_0x00ca
        L_0x006d:
            java.lang.Object r11 = r0.L$2
            r12 = r11
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r11 = r0.L$1
            com.bitcoin.mwallet.core.models.wallet.WalletId r11 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r11
            java.lang.Object r2 = r0.L$0
            com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor r2 = (com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor) r2
            kotlin.ResultKt.throwOnFailure(r13)
            r4 = r11
            r11 = r2
            r2 = r12
            goto L_0x0098
        L_0x0081:
            kotlin.ResultKt.throwOnFailure(r13)
            com.bitcoin.mwallet.core.interactors.GetWalletInteractor r13 = r10.walletInteractor
            r0.L$0 = r10
            r0.L$1 = r11
            r0.L$2 = r12
            r0.label = r4
            java.lang.Object r13 = r13.getWallet(r11, r0)
            if (r13 != r1) goto L_0x0095
            return r1
        L_0x0095:
            r4 = r11
            r2 = r12
            r11 = r10
        L_0x0098:
            r12 = r13
            com.bitcoin.mwallet.core.models.wallet.Wallet r12 = (com.bitcoin.mwallet.core.models.wallet.C1261Wallet) r12
            if (r12 == 0) goto L_0x00e8
            com.bitcoin.mwallet.core.models.credential.Credential$Companion r13 = com.bitcoin.mwallet.core.models.credential.Credential.Companion
            com.bitcoin.bitcoink.Mnemonic r6 = new com.bitcoin.bitcoink.Mnemonic
            r6.<init>(r2)
            r7 = 0
            r8 = 0
            com.bitcoin.mwallet.core.models.credential.CredentialMnemonic r13 = com.bitcoin.mwallet.core.models.credential.Credential.Companion.from$default(r13, r6, r7, r5, r8)
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor r6 = r11.createWalletInteractor
            com.bitcoin.mwallet.core.models.Coin r7 = r12.getCoin()
            java.lang.String r8 = r12.getName()
            r0.L$0 = r11
            r0.L$1 = r4
            r0.L$2 = r2
            r0.L$3 = r12
            r0.L$4 = r13
            r0.label = r5
            java.lang.Object r5 = r6.recoverSoftwareWallet(r7, r8, r13, r0)
            if (r5 != r1) goto L_0x00c7
            return r1
        L_0x00c7:
            r9 = r5
            r5 = r13
            r13 = r9
        L_0x00ca:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r13 = (com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult) r13
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r6 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.SUCCESS
            if (r13 != r6) goto L_0x00e7
            com.bitcoin.mwallet.core.interactors.ModifyWalletInteractor r6 = r11.modifyWalletInteractor
            r0.L$0 = r11
            r0.L$1 = r4
            r0.L$2 = r2
            r0.L$3 = r12
            r0.L$4 = r5
            r0.L$5 = r13
            r0.label = r3
            java.lang.Object r11 = r6.deleteWallet(r4, r0)
            if (r11 != r1) goto L_0x00e7
            return r1
        L_0x00e7:
            return r13
        L_0x00e8:
            com.bitcoin.mwallet.core.interactors.CreateWalletInteractor$CreateWalletResult r11 = com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult.FAILED
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.components.lockedwalletdialog.LockedWalletDialogInteractor.unlockWallet(com.bitcoin.mwallet.core.models.wallet.WalletId, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
