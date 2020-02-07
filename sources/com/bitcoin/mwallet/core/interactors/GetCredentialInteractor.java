package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.mwallet.core.models.credential.Credential;
import com.bitcoin.mwallet.core.models.credential.CredentialId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u001b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0007\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/GetCredentialInteractor;", "", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "(Lcom/bitcoin/mwallet/core/repositories/WalletRepository;)V", "getCredentialId", "Lcom/bitcoin/mwallet/core/models/credential/CredentialId;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWalletCredential", "Lcom/bitcoin/mwallet/core/models/credential/Credential;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GetCredentialInteractor.kt */
public final class GetCredentialInteractor {
    private final WalletRepository walletRepository;

    public GetCredentialInteractor(@NotNull WalletRepository walletRepository2) {
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        this.walletRepository = walletRepository2;
    }

    @Nullable
    public final Object getWalletCredential(@NotNull WalletId walletId, @NotNull Continuation<? super Credential> continuation) {
        return this.walletRepository.getCredential(walletId, continuation);
    }

    @Nullable
    public final Object getCredentialId(@NotNull WalletId walletId, @NotNull Continuation<? super CredentialId> continuation) {
        return this.walletRepository.getCredentialId(walletId, continuation);
    }
}
