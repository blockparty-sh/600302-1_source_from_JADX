package com.bitcoin.mwallet.core.services.wallet;

import com.bitcoin.bitcoink.ExtendedPublicKey;
import com.bitcoin.bitcoink.Network;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J9\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\rJ9\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J9\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/services/wallet/WalletService;", "", "createSingleSigWallet", "Lcom/bitcoin/mwallet/core/services/wallet/CreateWalletResponse;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "network", "Lcom/bitcoin/bitcoink/Network;", "publicKey", "Lcom/bitcoin/bitcoink/ExtendedPublicKey;", "name", "", "signingPubKey", "(Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/bitcoink/Network;Lcom/bitcoin/bitcoink/ExtendedPublicKey;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "migrateExisitingWallet", "oldWalletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/bitcoink/Network;Lcom/bitcoin/bitcoink/ExtendedPublicKey;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recoverSingleSigWallet", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletService.kt */
public interface WalletService {
    @Nullable
    Object createSingleSigWallet(@NotNull Coin coin, @NotNull Network network, @NotNull ExtendedPublicKey extendedPublicKey, @NotNull String str, @NotNull String str2, @NotNull Continuation<? super CreateWalletResponse> continuation);

    @Nullable
    Object migrateExisitingWallet(@NotNull WalletId walletId, @NotNull Coin coin, @NotNull Network network, @NotNull ExtendedPublicKey extendedPublicKey, @NotNull String str, @NotNull Continuation<? super CreateWalletResponse> continuation);

    @Nullable
    Object recoverSingleSigWallet(@NotNull Coin coin, @NotNull Network network, @NotNull ExtendedPublicKey extendedPublicKey, @NotNull String str, @NotNull String str2, @NotNull Continuation<? super CreateWalletResponse> continuation);
}
