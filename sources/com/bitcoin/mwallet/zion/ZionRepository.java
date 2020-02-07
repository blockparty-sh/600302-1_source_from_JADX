package com.bitcoin.mwallet.zion;

import com.bitcoin.mwallet.core.entity.ZionNameEntity;
import com.bitcoin.mwallet.core.models.Coin;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J#\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0019\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001b\u0010\u0012\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0013\u001a\u00020\u0014H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J'\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001aH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u0011\u0010\u001c\u001a\u00020\u001dH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u0019\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020!H¦@ø\u0001\u0000¢\u0006\u0002\u0010\"J\u001f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050\u00172\u0006\u0010$\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010%J#\u0010&\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ#\u0010'\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140)0(H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u001b\u0010*\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J/\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u00172\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020.H¦@ø\u0001\u0000¢\u0006\u0002\u0010/J/\u00100\u001a\b\u0012\u0004\u0012\u00020,0\u00172\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u00101\u001a\u000202H¦@ø\u0001\u0000¢\u0006\u0002\u00103J!\u00104\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u00065"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionRepository;", "", "clearWalletSeed", "Lcom/bitcoin/mwallet/zion/ZionError;", "zionId", "Lcom/bitcoin/mwallet/zion/ZionId;", "(Lcom/bitcoin/mwallet/zion/ZionId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "confirmPin", "createWalletSeed", "walletName", "Lcom/bitcoin/mwallet/zion/ZionWalletName;", "(Lcom/bitcoin/mwallet/zion/ZionId;Lcom/bitcoin/mwallet/zion/ZionWalletName;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "freeWalletNameByIndex", "", "walletNameIndex", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "freeWalletNameByZionId", "getNextAvailableWalletName", "prefix", "Lcom/bitcoin/mwallet/zion/ZionNamePrefix;", "(Lcom/bitcoin/mwallet/zion/ZionNamePrefix;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWalletXPub", "Lcom/bitcoin/mwallet/zion/ZionResponse;", "Lcom/bitcoin/mwallet/zion/ZionXPub;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "(Lcom/bitcoin/mwallet/zion/ZionId;Lcom/bitcoin/mwallet/core/models/Coin;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "hasZion", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertZionName", "zionName", "Lcom/bitcoin/mwallet/core/entity/ZionNameEntity;", "(Lcom/bitcoin/mwallet/core/entity/ZionNameEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerWallet", "zionWalletName", "(Lcom/bitcoin/mwallet/zion/ZionWalletName;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restoreWalletSeed", "scanAndRestoreZionWallets", "", "Lkotlin/Pair;", "showWalletSeed", "signMessage", "", "message", "", "(Lcom/bitcoin/mwallet/zion/ZionId;Lcom/bitcoin/mwallet/core/models/Coin;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "signTransaction", "zionTransasction", "Lcom/bitcoin/mwallet/zion/ZionTransaction;", "(Lcom/bitcoin/mwallet/zion/ZionId;Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/mwallet/zion/ZionTransaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "unregisterWallet", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ZionRepository.kt */
public interface ZionRepository {
    @Nullable
    Object clearWalletSeed(@NotNull ZionId zionId, @NotNull Continuation<? super ZionError> continuation);

    @Nullable
    Object confirmPin(@NotNull ZionId zionId, @NotNull Continuation<? super ZionError> continuation);

    @Nullable
    Object createWalletSeed(@NotNull ZionId zionId, @NotNull ZionWalletName zionWalletName, @NotNull Continuation<? super ZionError> continuation);

    @Nullable
    Object freeWalletNameByIndex(int i, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object freeWalletNameByZionId(@NotNull ZionId zionId, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object getNextAvailableWalletName(@NotNull ZionNamePrefix zionNamePrefix, @NotNull Continuation<? super ZionWalletName> continuation);

    @Nullable
    Object getWalletXPub(@NotNull ZionId zionId, @NotNull Coin coin, @NotNull Continuation<? super ZionResponse<ZionXPub>> continuation);

    @Nullable
    Object hasZion(@NotNull Continuation<? super Boolean> continuation);

    @Nullable
    Object insertZionName(@NotNull ZionNameEntity zionNameEntity, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object registerWallet(@NotNull ZionWalletName zionWalletName, @NotNull Continuation<? super ZionResponse<ZionId>> continuation);

    @Nullable
    Object restoreWalletSeed(@NotNull ZionId zionId, @NotNull ZionWalletName zionWalletName, @NotNull Continuation<? super ZionError> continuation);

    @Nullable
    Object scanAndRestoreZionWallets(@NotNull Continuation<? super List<? extends Pair<ZionId, ? extends ZionNamePrefix>>> continuation);

    @Nullable
    Object showWalletSeed(@NotNull ZionId zionId, @NotNull Continuation<? super ZionError> continuation);

    @Nullable
    Object signMessage(@NotNull ZionId zionId, @NotNull Coin coin, @NotNull String str, @NotNull Continuation<? super ZionResponse<byte[]>> continuation);

    @Nullable
    Object signTransaction(@NotNull ZionId zionId, @NotNull Coin coin, @NotNull ZionTransaction zionTransaction, @NotNull Continuation<? super ZionResponse<byte[]>> continuation);

    @Nullable
    Object unregisterWallet(@NotNull ZionId zionId, @NotNull ZionWalletName zionWalletName, @NotNull Continuation<? super Unit> continuation);
}
