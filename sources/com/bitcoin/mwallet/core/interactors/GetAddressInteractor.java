package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.bitcoink.Network;
import com.bitcoin.mwallet.core.models.address.AddressAndPath;
import com.bitcoin.mwallet.core.models.address.AddressPurpose;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J!\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ#\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/GetAddressInteractor;", "", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "(Lcom/bitcoin/mwallet/core/repositories/WalletRepository;)V", "getCurrentAddress", "Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "purpose", "Lcom/bitcoin/mwallet/core/models/address/AddressPurpose;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/address/AddressPurpose;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNextAvailableAddress", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: GetAddressInteractor.kt */
public final class GetAddressInteractor {
    private final WalletRepository walletRepository;

    public GetAddressInteractor(@NotNull WalletRepository walletRepository2) {
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        this.walletRepository = walletRepository2;
    }

    @Nullable
    public final Object getNextAvailableAddress(@NotNull WalletId walletId, @NotNull AddressPurpose addressPurpose, @NotNull Continuation<? super AddressAndPath> continuation) {
        return this.walletRepository.getNextAddress(walletId, Network.MAIN, addressPurpose, continuation);
    }

    @Nullable
    public final Object getCurrentAddress(@NotNull WalletId walletId, @NotNull AddressPurpose addressPurpose, @NotNull Continuation<? super AddressAndPath> continuation) {
        return this.walletRepository.getCurrentAddress(walletId, Network.MAIN, addressPurpose, continuation);
    }
}
