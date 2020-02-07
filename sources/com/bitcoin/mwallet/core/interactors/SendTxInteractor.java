package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.bitcoink.BitcoinFork;
import com.bitcoin.bitcoink.DerivationPath;
import com.bitcoin.bitcoink.p008tx.TxBuilder;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.bitcoink.p008tx.TxOutput;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.credential.CredentialType;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection;
import com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo;
import com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.repositories.PrivateKeyRepository;
import com.bitcoin.mwallet.core.repositories.UtxoRepository;
import com.bitcoin.mwallet.core.repositories.WalletRepository;
import com.bitcoin.mwallet.core.services.p010tx.TxService;
import com.bitcoin.mwallet.core.utils.slp.ByteUtils.Hex;
import com.bitcoin.mwallet.core.utils.slp.SlpTokenType;
import com.bitcoin.mwallet.core.utils.slp.SlpTransactionType;
import com.bitcoin.mwallet.zion.ZionRepository;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.ULong;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.IntRef;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.script.ScriptChunk;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J'\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u00122\u0006\u0010\u0015\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J/\u0010\u0018\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u00192\u0006\u0010\u0015\u001a\u00020\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ/\u0010\u001c\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u00192\u0006\u0010\u0015\u001a\u00020\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ1\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%H\u0002ø\u0001\u0000J\u001f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00130(2\u0006\u0010\u0015\u001a\u00020\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0002J!\u0010-\u001a\u00020.2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010/\u001a\u000200H@ø\u0001\u0000¢\u0006\u0002\u00101J\u0019\u00102\u001a\u0002032\u0006\u0010\u0015\u001a\u00020\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJG\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092\u001a\u0010:\u001a\u0016\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020<\u0018\u00010\u00192\b\u0010=\u001a\u0004\u0018\u00010>H@ø\u0001\u0000¢\u0006\u0002\u0010?Ji\u0010@\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u0010A\u001a\u00020B2\b\u00108\u001a\u0004\u0018\u0001092\u001a\u0010C\u001a\u0016\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020<\u0018\u00010\u00192\u001a\u0010D\u001a\u0016\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020<\u0018\u00010\u00192\b\u0010=\u001a\u0004\u0018\u00010>H\u0002¢\u0006\u0002\u0010ER\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006F"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/SendTxInteractor;", "", "privateKeyRepository", "Lcom/bitcoin/mwallet/core/repositories/PrivateKeyRepository;", "txService", "Lcom/bitcoin/mwallet/core/services/tx/TxService;", "utxoRepository", "Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;", "zionRepository", "Lcom/bitcoin/mwallet/zion/ZionRepository;", "walletRepository", "Lcom/bitcoin/mwallet/core/repositories/WalletRepository;", "(Lcom/bitcoin/mwallet/core/repositories/PrivateKeyRepository;Lcom/bitcoin/mwallet/core/services/tx/TxService;Lcom/bitcoin/mwallet/core/repositories/UtxoRepository;Lcom/bitcoin/mwallet/zion/ZionRepository;Lcom/bitcoin/mwallet/core/repositories/WalletRepository;)V", "convertBip70Script", "Lorg/bitcoinj/script/Script;", "script", "", "createBip70RawRx", "Lkotlin/Pair;", "", "Lcom/bitcoin/bitcoink/tx/TxOutput;", "request", "Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendBip70TxRequest;", "(Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendBip70TxRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createNonZionRawTx", "Lkotlin/Triple;", "Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendTxRequest;", "(Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendTxRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createSlpRawTx", "createSlpScript", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "tokenType", "Lcom/bitcoin/mwallet/core/utils/slp/SlpTokenType;", "transactionType", "Lcom/bitcoin/mwallet/core/utils/slp/SlpTransactionType;", "quantities", "", "Lkotlin/ULong;", "createZionRawTx", "Lcom/bitcoin/mwallet/zion/ZionResponse;", "getTxBuilder", "Lcom/bitcoin/bitcoink/tx/TxBuilder;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "sendBip70Tx", "Lcom/bitcoin/mwallet/core/services/tx/Bip70BroadcastTxResponse;", "depositAddress", "Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;", "(Lcom/bitcoin/mwallet/app/flows/sendv2/entity/SendBip70TxRequest;Lcom/bitcoin/mwallet/core/models/address/AddressAndPath;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendTx", "Lcom/bitcoin/mwallet/core/services/tx/BroadcastTxResponse;", "updateSlpChangeUtxos", "", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "slpUtxoSelection", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;", "change", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "Lcom/bitcoin/bitcoink/DerivationPath;", "changeOutputIndex", "", "(Lcom/bitcoin/bitcoink/tx/TxId;Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;Lkotlin/Triple;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUtxos", "utxoSelection", "Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "coinChange", "tokenChange", "(Lcom/bitcoin/bitcoink/tx/TxId;Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;Lkotlin/Triple;Lkotlin/Triple;Ljava/lang/Integer;)V", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SendTxInteractor.kt */
public final class SendTxInteractor {
    private final PrivateKeyRepository privateKeyRepository;
    private final TxService txService;
    /* access modifiers changed from: private */
    public final UtxoRepository utxoRepository;
    private final WalletRepository walletRepository;
    private final ZionRepository zionRepository;

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CredentialType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = new int[CredentialType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$2 = new int[Coin.values().length];

        static {
            $EnumSwitchMapping$0[CredentialType.MNEMONIC_AND_PRIVATE_KEY.ordinal()] = 1;
            $EnumSwitchMapping$1[CredentialType.MNEMONIC_AND_PRIVATE_KEY.ordinal()] = 1;
            $EnumSwitchMapping$1[CredentialType.ZION_VAULT.ordinal()] = 2;
            $EnumSwitchMapping$2[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$2[Coin.BTC.ordinal()] = 2;
        }
    }

    public SendTxInteractor(@NotNull PrivateKeyRepository privateKeyRepository2, @NotNull TxService txService2, @NotNull UtxoRepository utxoRepository2, @NotNull ZionRepository zionRepository2, @NotNull WalletRepository walletRepository2) {
        Intrinsics.checkParameterIsNotNull(privateKeyRepository2, "privateKeyRepository");
        Intrinsics.checkParameterIsNotNull(txService2, "txService");
        Intrinsics.checkParameterIsNotNull(utxoRepository2, "utxoRepository");
        Intrinsics.checkParameterIsNotNull(zionRepository2, "zionRepository");
        Intrinsics.checkParameterIsNotNull(walletRepository2, "walletRepository");
        this.privateKeyRepository = privateKeyRepository2;
        this.txService = txService2;
        this.utxoRepository = utxoRepository2;
        this.zionRepository = zionRepository2;
        this.walletRepository = walletRepository2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x011b A[Catch:{ RuntimeException -> 0x0164 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x011c A[Catch:{ RuntimeException -> 0x0164 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0126 A[Catch:{ RuntimeException -> 0x0164 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object sendBip70Tx(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest r12, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.address.AddressAndPath r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.services.p010tx.Bip70BroadcastTxResponse> r14) {
        /*
            r11 = this;
            java.lang.String r0 = ", "
            boolean r1 = r14 instanceof com.bitcoin.mwallet.core.interactors.SendTxInteractor$sendBip70Tx$1
            if (r1 == 0) goto L_0x0016
            r1 = r14
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$sendBip70Tx$1 r1 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor$sendBip70Tx$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r14 = r1.label
            int r14 = r14 - r3
            r1.label = r14
            goto L_0x001b
        L_0x0016:
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$sendBip70Tx$1 r1 = new com.bitcoin.mwallet.core.interactors.SendTxInteractor$sendBip70Tx$1
            r1.<init>(r11, r14)
        L_0x001b:
            r7 = r1
            java.lang.Object r14 = r7.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r7.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0066
            if (r2 == r4) goto L_0x0051
            if (r2 != r3) goto L_0x0049
            java.lang.Object r12 = r7.L$5
            kotlin.Pair r12 = (kotlin.Pair) r12
            java.lang.Object r12 = r7.L$4
            com.bitcoin.bitcoink.tx.TxOutput r12 = (com.bitcoin.bitcoink.p008tx.TxOutput) r12
            java.lang.Object r13 = r7.L$3
            byte[] r13 = (byte[]) r13
            java.lang.Object r13 = r7.L$2
            com.bitcoin.mwallet.core.models.address.AddressAndPath r13 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r13
            java.lang.Object r13 = r7.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest r13 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest) r13
            java.lang.Object r0 = r7.L$0
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r0 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor) r0
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ RuntimeException -> 0x0164 }
            goto L_0x011e
        L_0x0049:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0051:
            java.lang.Object r12 = r7.L$2
            r13 = r12
            com.bitcoin.mwallet.core.models.address.AddressAndPath r13 = (com.bitcoin.mwallet.core.models.address.AddressAndPath) r13
            java.lang.Object r12 = r7.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest r12 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest) r12
            java.lang.Object r2 = r7.L$0
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r2 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor) r2
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ RuntimeException -> 0x0164 }
            r8 = r2
        L_0x0062:
            r10 = r13
            r13 = r12
            r12 = r10
            goto L_0x0099
        L_0x0066:
            kotlin.ResultKt.throwOnFailure(r14)
            com.bitcoin.mwallet.core.models.credential.CredentialId r14 = r12.getWalletCredentialId()     // Catch:{ RuntimeException -> 0x0164 }
            com.bitcoin.mwallet.core.models.credential.CredentialType r14 = r14.getType()     // Catch:{ RuntimeException -> 0x0164 }
            int[] r2 = com.bitcoin.mwallet.core.interactors.SendTxInteractor.WhenMappings.$EnumSwitchMapping$0     // Catch:{ RuntimeException -> 0x0164 }
            int r14 = r14.ordinal()     // Catch:{ RuntimeException -> 0x0164 }
            r14 = r2[r14]     // Catch:{ RuntimeException -> 0x0164 }
            if (r14 == r4) goto L_0x0088
            com.bitcoin.mwallet.core.services.tx.Bip70BroadcastTxResponse r12 = new com.bitcoin.mwallet.core.services.tx.Bip70BroadcastTxResponse     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.RuntimeException r13 = new java.lang.RuntimeException     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.String r14 = "Cannot Broadcast with Encrypted Mnemonic"
            r13.<init>(r14)     // Catch:{ RuntimeException -> 0x0164 }
            r12.<init>(r13)     // Catch:{ RuntimeException -> 0x0164 }
            return r12
        L_0x0088:
            r7.L$0 = r11     // Catch:{ RuntimeException -> 0x0164 }
            r7.L$1 = r12     // Catch:{ RuntimeException -> 0x0164 }
            r7.L$2 = r13     // Catch:{ RuntimeException -> 0x0164 }
            r7.label = r4     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.Object r14 = r11.createBip70RawRx(r12, r7)     // Catch:{ RuntimeException -> 0x0164 }
            if (r14 != r1) goto L_0x0097
            return r1
        L_0x0097:
            r8 = r11
            goto L_0x0062
        L_0x0099:
            kotlin.Pair r14 = (kotlin.Pair) r14     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.Object r2 = r14.getFirst()     // Catch:{ RuntimeException -> 0x0164 }
            r6 = r2
            byte[] r6 = (byte[]) r6     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0164 }
            r2.<init>()     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.String r4 = "RawTx = "
            r2.append(r4)     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.String r4 = com.bitcoin.securepreferences.HexKt.toHexString(r6)     // Catch:{ RuntimeException -> 0x0164 }
            r2.append(r4)     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.String r2 = r2.toString()     // Catch:{ RuntimeException -> 0x0164 }
            r4 = 0
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ RuntimeException -> 0x0164 }
            timber.log.Timber.m426d(r2, r5)     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.Object r2 = r14.getSecond()     // Catch:{ RuntimeException -> 0x0164 }
            r9 = r2
            com.bitcoin.bitcoink.tx.TxOutput r9 = (com.bitcoin.bitcoink.p008tx.TxOutput) r9     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0164 }
            r2.<init>()     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.String r5 = "BroadCasting walletid: "
            r2.append(r5)     // Catch:{ RuntimeException -> 0x0164 }
            com.bitcoin.mwallet.core.models.wallet.WalletId r5 = r13.getWalletId()     // Catch:{ RuntimeException -> 0x0164 }
            r2.append(r5)     // Catch:{ RuntimeException -> 0x0164 }
            r2.append(r0)     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.String r5 = r13.getBip70Url()     // Catch:{ RuntimeException -> 0x0164 }
            r2.append(r5)     // Catch:{ RuntimeException -> 0x0164 }
            r2.append(r0)     // Catch:{ RuntimeException -> 0x0164 }
            r2.append(r12)     // Catch:{ RuntimeException -> 0x0164 }
            r2.append(r0)     // Catch:{ RuntimeException -> 0x0164 }
            r2.append(r6)     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.String r0 = r2.toString()     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.Object[] r2 = new java.lang.Object[r4]     // Catch:{ RuntimeException -> 0x0164 }
            timber.log.Timber.m426d(r0, r2)     // Catch:{ RuntimeException -> 0x0164 }
            com.bitcoin.mwallet.core.services.tx.TxService r2 = r8.txService     // Catch:{ RuntimeException -> 0x0164 }
            com.bitcoin.mwallet.core.models.wallet.WalletId r0 = r13.getWalletId()     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.String r4 = r13.getBip70Url()     // Catch:{ RuntimeException -> 0x0164 }
            com.bitcoin.bitcoink.address.Address r5 = r12.getAddress()     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.String r5 = r5.toString()     // Catch:{ RuntimeException -> 0x0164 }
            r7.L$0 = r8     // Catch:{ RuntimeException -> 0x0164 }
            r7.L$1 = r13     // Catch:{ RuntimeException -> 0x0164 }
            r7.L$2 = r12     // Catch:{ RuntimeException -> 0x0164 }
            r7.L$3 = r6     // Catch:{ RuntimeException -> 0x0164 }
            r7.L$4 = r9     // Catch:{ RuntimeException -> 0x0164 }
            r7.L$5 = r14     // Catch:{ RuntimeException -> 0x0164 }
            r7.label = r3     // Catch:{ RuntimeException -> 0x0164 }
            r3 = r0
            java.lang.Object r14 = r2.broadcastBip70Tx(r3, r4, r5, r6, r7)     // Catch:{ RuntimeException -> 0x0164 }
            if (r14 != r1) goto L_0x011c
            return r1
        L_0x011c:
            r0 = r8
            r12 = r9
        L_0x011e:
            com.bitcoin.mwallet.core.services.tx.Bip70BroadcastTxResponse r14 = (com.bitcoin.mwallet.core.services.p010tx.Bip70BroadcastTxResponse) r14     // Catch:{ RuntimeException -> 0x0164 }
            boolean r1 = r14.isSuccess()     // Catch:{ RuntimeException -> 0x0164 }
            if (r1 == 0) goto L_0x0163
            r1 = 0
            if (r12 == 0) goto L_0x013c
            kotlin.Triple r2 = new kotlin.Triple     // Catch:{ RuntimeException -> 0x0164 }
            com.bitcoin.mwallet.core.models.wallet.WalletId r3 = r13.getWalletId()     // Catch:{ RuntimeException -> 0x0164 }
            com.bitcoin.mwallet.core.models.address.AddressAndPath r4 = r13.getChangeAddress()     // Catch:{ RuntimeException -> 0x0164 }
            com.bitcoin.bitcoink.DerivationPath r4 = r4.getPath()     // Catch:{ RuntimeException -> 0x0164 }
            r2.<init>(r3, r12, r4)     // Catch:{ RuntimeException -> 0x0164 }
            r4 = r2
            goto L_0x013d
        L_0x013c:
            r4 = r1
        L_0x013d:
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ RuntimeException -> 0x0164 }
            com.bitcoin.bitcoink.tx.TxId r2 = r14.getTxId()     // Catch:{ RuntimeException -> 0x0164 }
            if (r2 == 0) goto L_0x0163
            if (r12 == 0) goto L_0x0155
            java.util.List r12 = r13.getBip70PaymentOutputs()     // Catch:{ RuntimeException -> 0x0164 }
            int r12 = r12.size()     // Catch:{ RuntimeException -> 0x0164 }
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r12)     // Catch:{ RuntimeException -> 0x0164 }
            r6 = r12
            goto L_0x0156
        L_0x0155:
            r6 = r1
        L_0x0156:
            com.bitcoin.bitcoink.tx.TxId r1 = r14.getTxId()     // Catch:{ RuntimeException -> 0x0164 }
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r2 = r13.getUtxoSelection()     // Catch:{ RuntimeException -> 0x0164 }
            r3 = 0
            r5 = 0
            r0.updateUtxos(r1, r2, r3, r4, r5, r6)     // Catch:{ RuntimeException -> 0x0164 }
        L_0x0163:
            return r14
        L_0x0164:
            r12 = move-exception
            r13 = r12
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            timber.log.Timber.m430e(r13)
            com.bitcoin.mwallet.core.services.tx.Bip70BroadcastTxResponse r13 = new com.bitcoin.mwallet.core.services.tx.Bip70BroadcastTxResponse
            r13.<init>(r12)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.SendTxInteractor.sendBip70Tx(com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest, com.bitcoin.mwallet.core.models.address.AddressAndPath, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ee A[Catch:{ RuntimeException -> 0x0216 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00f6 A[Catch:{ RuntimeException -> 0x0216 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x011d A[Catch:{ RuntimeException -> 0x0216 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x012e A[Catch:{ RuntimeException -> 0x0216 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01ab A[Catch:{ RuntimeException -> 0x0216 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01ac A[Catch:{ RuntimeException -> 0x0216 }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01b5 A[Catch:{ RuntimeException -> 0x0216 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object sendTx(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r12, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse> r13) {
        /*
            r11 = this;
            boolean r0 = r13 instanceof com.bitcoin.mwallet.core.interactors.SendTxInteractor$sendTx$1
            if (r0 == 0) goto L_0x0014
            r0 = r13
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$sendTx$1 r0 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor$sendTx$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L_0x0019
        L_0x0014:
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$sendTx$1 r0 = new com.bitcoin.mwallet.core.interactors.SendTxInteractor$sendTx$1
            r0.<init>(r11, r13)
        L_0x0019:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L_0x00a4
            if (r2 == r7) goto L_0x0093
            if (r2 == r6) goto L_0x0082
            if (r2 == r5) goto L_0x0064
            if (r2 == r4) goto L_0x0053
            if (r2 != r3) goto L_0x004b
            java.lang.Object r12 = r0.L$4
            byte[] r12 = (byte[]) r12
            java.lang.Object r12 = r0.L$3
            com.bitcoin.mwallet.zion.ZionResponse r12 = (com.bitcoin.mwallet.zion.ZionResponse) r12
            java.lang.Object r12 = r0.L$2
            com.bitcoin.mwallet.core.models.Coin r12 = (com.bitcoin.mwallet.core.models.Coin) r12
            java.lang.Object r12 = r0.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r12 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest) r12
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r0 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor) r0
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ RuntimeException -> 0x0216 }
            goto L_0x0115
        L_0x004b:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L_0x0053:
            java.lang.Object r12 = r0.L$2
            com.bitcoin.mwallet.core.models.Coin r12 = (com.bitcoin.mwallet.core.models.Coin) r12
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r2 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest) r2
            java.lang.Object r4 = r0.L$0
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r4 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor) r4
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ RuntimeException -> 0x0216 }
            goto L_0x00e4
        L_0x0064:
            java.lang.Object r12 = r0.L$5
            kotlin.Triple r12 = (kotlin.Triple) r12
            java.lang.Object r1 = r0.L$4
            com.bitcoin.bitcoink.tx.TxOutput r1 = (com.bitcoin.bitcoink.p008tx.TxOutput) r1
            java.lang.Object r2 = r0.L$3
            byte[] r2 = (byte[]) r2
            java.lang.Object r2 = r0.L$2
            com.bitcoin.mwallet.core.models.Coin r2 = (com.bitcoin.mwallet.core.models.Coin) r2
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r2 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest) r2
            java.lang.Object r0 = r0.L$0
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r0 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor) r0
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ RuntimeException -> 0x0216 }
            r3 = r0
            goto L_0x01ad
        L_0x0082:
            java.lang.Object r12 = r0.L$2
            com.bitcoin.mwallet.core.models.Coin r12 = (com.bitcoin.mwallet.core.models.Coin) r12
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r2 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest) r2
            java.lang.Object r3 = r0.L$0
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r3 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor) r3
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ RuntimeException -> 0x0216 }
            goto L_0x016d
        L_0x0093:
            java.lang.Object r12 = r0.L$2
            com.bitcoin.mwallet.core.models.Coin r12 = (com.bitcoin.mwallet.core.models.Coin) r12
            java.lang.Object r2 = r0.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r2 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest) r2
            java.lang.Object r3 = r0.L$0
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r3 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor) r3
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ RuntimeException -> 0x0216 }
            goto L_0x016d
        L_0x00a4:
            kotlin.ResultKt.throwOnFailure(r13)
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r13 = r12.getUtxoSelection()
            com.bitcoin.mwallet.core.models.Coin r13 = r13.getCoin()
            com.bitcoin.mwallet.core.models.credential.CredentialId r2 = r12.getWalletCredentialId()     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.mwallet.core.models.credential.CredentialType r2 = r2.getType()     // Catch:{ RuntimeException -> 0x0216 }
            int[] r8 = com.bitcoin.mwallet.core.interactors.SendTxInteractor.WhenMappings.$EnumSwitchMapping$1     // Catch:{ RuntimeException -> 0x0216 }
            int r2 = r2.ordinal()     // Catch:{ RuntimeException -> 0x0216 }
            r2 = r8[r2]     // Catch:{ RuntimeException -> 0x0216 }
            if (r2 == r7) goto L_0x013e
            if (r2 == r6) goto L_0x00d0
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse r12 = new com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.RuntimeException r13 = new java.lang.RuntimeException     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.String r0 = "Cannot Broadcast with Encrypted Mnemonic"
            r13.<init>(r0)     // Catch:{ RuntimeException -> 0x0216 }
            r12.<init>(r13)     // Catch:{ RuntimeException -> 0x0216 }
            return r12
        L_0x00d0:
            r0.L$0 = r11     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$1 = r12     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$2 = r13     // Catch:{ RuntimeException -> 0x0216 }
            r0.label = r4     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.Object r2 = r11.createZionRawTx(r12, r0)     // Catch:{ RuntimeException -> 0x0216 }
            if (r2 != r1) goto L_0x00df
            return r1
        L_0x00df:
            r4 = r11
            r10 = r2
            r2 = r12
            r12 = r13
            r13 = r10
        L_0x00e4:
            com.bitcoin.mwallet.zion.ZionResponse r13 = (com.bitcoin.mwallet.zion.ZionResponse) r13     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.mwallet.zion.ZionError r5 = r13.getError()     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.mwallet.zion.ZionError r6 = com.bitcoin.mwallet.zion.ZionError.USER_CANCEL     // Catch:{ RuntimeException -> 0x0216 }
            if (r5 != r6) goto L_0x00f6
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse r12 = new com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse$ErrorType r13 = com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse.ErrorType.ZION_CANCELLED     // Catch:{ RuntimeException -> 0x0216 }
            r12.<init>(r13)     // Catch:{ RuntimeException -> 0x0216 }
            return r12
        L_0x00f6:
            java.lang.Object r5 = r13.getResult()     // Catch:{ RuntimeException -> 0x0216 }
            byte[] r5 = (byte[]) r5     // Catch:{ RuntimeException -> 0x0216 }
            if (r5 == 0) goto L_0x0130
            com.bitcoin.mwallet.core.services.tx.TxService r6 = r4.txService     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$0 = r4     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$1 = r2     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$2 = r12     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$3 = r13     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$4 = r5     // Catch:{ RuntimeException -> 0x0216 }
            r0.label = r3     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.Object r13 = r6.broadcastTx(r12, r5, r0)     // Catch:{ RuntimeException -> 0x0216 }
            if (r13 != r1) goto L_0x0113
            return r1
        L_0x0113:
            r12 = r2
            r0 = r4
        L_0x0115:
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse r13 = (com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse) r13     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.bitcoink.tx.TxId r1 = r13.getTxId()     // Catch:{ RuntimeException -> 0x0216 }
            if (r1 == 0) goto L_0x012c
            com.bitcoin.bitcoink.tx.TxId r1 = r13.getTxId()     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r2 = r12.getUtxoSelection()     // Catch:{ RuntimeException -> 0x0216 }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r0.updateUtxos(r1, r2, r3, r4, r5, r6)     // Catch:{ RuntimeException -> 0x0216 }
        L_0x012c:
            if (r13 == 0) goto L_0x0130
            goto L_0x0215
        L_0x0130:
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse r13 = new com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.RuntimeException r12 = new java.lang.RuntimeException     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.String r0 = "Failed to sign transaction"
            r12.<init>(r0)     // Catch:{ RuntimeException -> 0x0216 }
            r13.<init>(r12)     // Catch:{ RuntimeException -> 0x0216 }
            goto L_0x0215
        L_0x013e:
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r2 = r12.getSlpUtxoSelection()     // Catch:{ RuntimeException -> 0x0216 }
            if (r2 == 0) goto L_0x0159
            r0.L$0 = r11     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$1 = r12     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$2 = r13     // Catch:{ RuntimeException -> 0x0216 }
            r0.label = r7     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.Object r2 = r11.createSlpRawTx(r12, r0)     // Catch:{ RuntimeException -> 0x0216 }
            if (r2 != r1) goto L_0x0153
            return r1
        L_0x0153:
            r3 = r11
            r10 = r2
            r2 = r12
            r12 = r13
            r13 = r10
            goto L_0x016d
        L_0x0159:
            r0.L$0 = r11     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$1 = r12     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$2 = r13     // Catch:{ RuntimeException -> 0x0216 }
            r0.label = r6     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.Object r2 = r11.createNonZionRawTx(r12, r0)     // Catch:{ RuntimeException -> 0x0216 }
            if (r2 != r1) goto L_0x0168
            return r1
        L_0x0168:
            r3 = r11
            r10 = r2
            r2 = r12
            r12 = r13
            r13 = r10
        L_0x016d:
            kotlin.Triple r13 = (kotlin.Triple) r13     // Catch:{ RuntimeException -> 0x0216 }
            r10 = r13
            r13 = r12
            r12 = r10
            java.lang.Object r4 = r12.getFirst()     // Catch:{ RuntimeException -> 0x0216 }
            byte[] r4 = (byte[]) r4     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x0216 }
            r6.<init>()     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.String r7 = "RawTx = "
            r6.append(r7)     // Catch:{ RuntimeException -> 0x0216 }
            r6.append(r4)     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.String r6 = r6.toString()     // Catch:{ RuntimeException -> 0x0216 }
            r7 = 0
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ RuntimeException -> 0x0216 }
            timber.log.Timber.m426d(r6, r7)     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.Object r6 = r12.getSecond()     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.bitcoink.tx.TxOutput r6 = (com.bitcoin.bitcoink.p008tx.TxOutput) r6     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.mwallet.core.services.tx.TxService r7 = r3.txService     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$0 = r3     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$1 = r2     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$2 = r13     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$3 = r4     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$4 = r6     // Catch:{ RuntimeException -> 0x0216 }
            r0.L$5 = r12     // Catch:{ RuntimeException -> 0x0216 }
            r0.label = r5     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.Object r13 = r7.broadcastTx(r13, r4, r0)     // Catch:{ RuntimeException -> 0x0216 }
            if (r13 != r1) goto L_0x01ac
            return r1
        L_0x01ac:
            r1 = r6
        L_0x01ad:
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse r13 = (com.bitcoin.mwallet.core.services.p010tx.BroadcastTxResponse) r13     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.bitcoink.tx.TxId r0 = r13.getTxId()     // Catch:{ RuntimeException -> 0x0216 }
            if (r0 == 0) goto L_0x0215
            r0 = 0
            if (r1 == 0) goto L_0x01cb
            kotlin.Triple r4 = new kotlin.Triple     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.mwallet.core.models.wallet.WalletId r5 = r2.getWalletId()     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.mwallet.core.models.address.AddressAndPath r6 = r2.getChangeAddress()     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.bitcoink.DerivationPath r6 = r6.getPath()     // Catch:{ RuntimeException -> 0x0216 }
            r4.<init>(r5, r1, r6)     // Catch:{ RuntimeException -> 0x0216 }
            r7 = r4
            goto L_0x01cc
        L_0x01cb:
            r7 = r0
        L_0x01cc:
            r1 = r0
            kotlin.Triple r1 = (kotlin.Triple) r1     // Catch:{ RuntimeException -> 0x0216 }
            java.lang.Object r4 = r12.getThird()     // Catch:{ RuntimeException -> 0x0216 }
            if (r4 == 0) goto L_0x0204
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r4 = r2.getSlpUtxoSelection()     // Catch:{ RuntimeException -> 0x0216 }
            if (r4 == 0) goto L_0x0204
            com.bitcoin.mwallet.core.models.address.AddressAndPath r4 = r2.getSlpChangeAddress()     // Catch:{ RuntimeException -> 0x0216 }
            if (r4 == 0) goto L_0x01e6
            com.bitcoin.bitcoink.DerivationPath r4 = r4.getPath()     // Catch:{ RuntimeException -> 0x0216 }
            goto L_0x01e7
        L_0x01e6:
            r4 = r0
        L_0x01e7:
            if (r4 == 0) goto L_0x0204
            java.lang.Object r12 = r12.getThird()     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.bitcoink.tx.TxOutput r12 = (com.bitcoin.bitcoink.p008tx.TxOutput) r12     // Catch:{ RuntimeException -> 0x0216 }
            if (r12 == 0) goto L_0x0202
            kotlin.Triple r0 = new kotlin.Triple     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = r2.getWalletId()     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.mwallet.core.models.address.AddressAndPath r4 = r2.getSlpChangeAddress()     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.bitcoink.DerivationPath r4 = r4.getPath()     // Catch:{ RuntimeException -> 0x0216 }
            r0.<init>(r1, r12, r4)     // Catch:{ RuntimeException -> 0x0216 }
        L_0x0202:
            r8 = r0
            goto L_0x0205
        L_0x0204:
            r8 = r1
        L_0x0205:
            com.bitcoin.bitcoink.tx.TxId r4 = r13.getTxId()     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r5 = r2.getUtxoSelection()     // Catch:{ RuntimeException -> 0x0216 }
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r6 = r2.getSlpUtxoSelection()     // Catch:{ RuntimeException -> 0x0216 }
            r9 = 0
            r3.updateUtxos(r4, r5, r6, r7, r8, r9)     // Catch:{ RuntimeException -> 0x0216 }
        L_0x0215:
            return r13
        L_0x0216:
            r12 = move-exception
            r13 = r12
            java.lang.Throwable r13 = (java.lang.Throwable) r13
            timber.log.Timber.m430e(r13)
            com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse r13 = new com.bitcoin.mwallet.core.services.tx.BroadcastTxResponse
            r13.<init>(r12)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.SendTxInteractor.sendTx(com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createZionRawTx(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.bitcoin.mwallet.zion.ZionResponse<byte[]>> r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            boolean r2 = r1 instanceof com.bitcoin.mwallet.core.interactors.SendTxInteractor$createZionRawTx$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$createZionRawTx$1 r2 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor$createZionRawTx$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$createZionRawTx$1 r2 = new com.bitcoin.mwallet.core.interactors.SendTxInteractor$createZionRawTx$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L_0x0073
            if (r4 == r6) goto L_0x0056
            if (r4 != r5) goto L_0x004e
            java.lang.Object r3 = r2.L$6
            com.bitcoin.mwallet.core.models.credential.CredentialZion r3 = (com.bitcoin.mwallet.core.models.credential.CredentialZion) r3
            java.lang.Object r3 = r2.L$5
            com.bitcoin.mwallet.zion.ZionTransaction r3 = (com.bitcoin.mwallet.zion.ZionTransaction) r3
            java.lang.Object r3 = r2.L$4
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r3 = r2.L$3
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r3 = r2.L$2
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r3 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r3 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest) r3
            java.lang.Object r2 = r2.L$0
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r2 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor) r2
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x015a
        L_0x004e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0056:
            java.lang.Object r4 = r2.L$5
            com.bitcoin.mwallet.zion.ZionTransaction r4 = (com.bitcoin.mwallet.zion.ZionTransaction) r4
            java.lang.Object r6 = r2.L$4
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r2.L$3
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r8 = r2.L$2
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r9 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r9 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest) r9
            java.lang.Object r10 = r2.L$0
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r10 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor) r10
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x0130
        L_0x0073:
            kotlin.ResultKt.throwOnFailure(r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r8 = r1
            java.util.List r8 = (java.util.List) r8
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r7 = r1
            java.util.List r7 = (java.util.List) r7
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.List r1 = (java.util.List) r1
            com.bitcoin.mwallet.zion.ZionTransaction$Output r4 = new com.bitcoin.mwallet.zion.ZionTransaction$Output
            com.bitcoin.bitcoink.address.Address r9 = r17.getToAddress()
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r10 = r17.getUtxoSelection()
            com.bitcoin.bitcoink.tx.Satoshis r10 = r10.getSendAmount()
            r4.<init>(r9, r10)
            r8.add(r4)
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r4 = r17.getUtxoSelection()
            com.bitcoin.bitcoink.tx.Satoshis r4 = r4.getChange()
            if (r4 == 0) goto L_0x00c7
            com.bitcoin.mwallet.zion.ZionTransaction$Change r9 = new com.bitcoin.mwallet.zion.ZionTransaction$Change
            com.bitcoin.bitcoink.DerivationPath r10 = r17.getWalletPath()
            com.bitcoin.mwallet.core.models.address.AddressAndPath r11 = r17.getChangeAddress()
            com.bitcoin.bitcoink.DerivationPath r11 = r11.getPath()
            com.bitcoin.bitcoink.DerivationPath r10 = r10.append(r11)
            r9.<init>(r10, r4)
            boolean r4 = r1.add(r9)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
        L_0x00c7:
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r4 = r17.getUtxoSelection()
            java.util.List r4 = r4.getUtxos()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r4 = r4.iterator()
        L_0x00d5:
            boolean r9 = r4.hasNext()
            if (r9 == 0) goto L_0x010a
            java.lang.Object r9 = r4.next()
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo r9 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo) r9
            com.bitcoin.mwallet.zion.ZionTransaction$Input r15 = new com.bitcoin.mwallet.zion.ZionTransaction$Input
            com.bitcoin.bitcoink.DerivationPath r10 = r17.getWalletPath()
            com.bitcoin.bitcoink.DerivationPath r11 = r9.getAddressPath()
            com.bitcoin.bitcoink.DerivationPath r11 = r10.append(r11)
            com.bitcoin.bitcoink.tx.TxId r12 = r9.getTxId()
            int r13 = r9.getOutputIndex()
            byte[] r14 = r9.getScript()
            com.bitcoin.bitcoink.tx.Satoshis r9 = r9.getSatoshis()
            r10 = r15
            r5 = r15
            r15 = r9
            r10.<init>(r11, r12, r13, r14, r15)
            r7.add(r5)
            r5 = 2
            goto L_0x00d5
        L_0x010a:
            com.bitcoin.mwallet.zion.ZionTransaction r4 = new com.bitcoin.mwallet.zion.ZionTransaction
            r4.<init>(r7, r8, r1)
            com.bitcoin.mwallet.core.repositories.WalletRepository r5 = r0.walletRepository
            com.bitcoin.mwallet.core.models.credential.CredentialId r9 = r17.getWalletCredentialId()
            r2.L$0 = r0
            r10 = r17
            r2.L$1 = r10
            r2.L$2 = r8
            r2.L$3 = r7
            r2.L$4 = r1
            r2.L$5 = r4
            r2.label = r6
            java.lang.Object r5 = r5.getCredential(r9, r2)
            if (r5 != r3) goto L_0x012c
            return r3
        L_0x012c:
            r6 = r1
            r1 = r5
            r9 = r10
            r10 = r0
        L_0x0130:
            if (r1 == 0) goto L_0x015b
            com.bitcoin.mwallet.core.models.credential.CredentialZion r1 = (com.bitcoin.mwallet.core.models.credential.CredentialZion) r1
            com.bitcoin.mwallet.zion.ZionRepository r5 = r10.zionRepository
            com.bitcoin.mwallet.zion.ZionId r11 = r1.getZionId()
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r12 = r9.getUtxoSelection()
            com.bitcoin.mwallet.core.models.Coin r12 = r12.getCoin()
            r2.L$0 = r10
            r2.L$1 = r9
            r2.L$2 = r8
            r2.L$3 = r7
            r2.L$4 = r6
            r2.L$5 = r4
            r2.L$6 = r1
            r1 = 2
            r2.label = r1
            java.lang.Object r1 = r5.signTransaction(r11, r12, r4, r2)
            if (r1 != r3) goto L_0x015a
            return r3
        L_0x015a:
            return r1
        L_0x015b:
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type com.bitcoin.mwallet.core.models.credential.CredentialZion"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.SendTxInteractor.createZionRawTx(com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Script createSlpScript(SlpId slpId, SlpTokenType slpTokenType, SlpTransactionType slpTransactionType, List<ULong> list) {
        ScriptBuilder data = new ScriptBuilder().mo46573op(106).data(new byte[]{83, 76, 80, 0}).addChunk(new ScriptChunk(slpTokenType.getBytes().length, slpTokenType.getBytes())).data(slpTransactionType.getBytes()).data(slpId.getBytes());
        for (ULong r6 : list) {
            data.data(ByteBuffer.allocate(8).putLong(r6.m676unboximpl()).array());
        }
        Script build = data.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "builder.build()");
        return build;
    }

    private final Script convertBip70Script(String str) {
        return new Script(Hex.INSTANCE.decode(str));
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createBip70RawRx(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest r20, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<byte[], com.bitcoin.bitcoink.p008tx.TxOutput>> r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r21
            boolean r2 = r1 instanceof com.bitcoin.mwallet.core.interactors.SendTxInteractor$createBip70RawRx$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$createBip70RawRx$1 r2 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor$createBip70RawRx$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$createBip70RawRx$1 r2 = new com.bitcoin.mwallet.core.interactors.SendTxInteractor$createBip70RawRx$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L_0x0060
            if (r4 != r5) goto L_0x0058
            java.lang.Object r4 = r2.L$7
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo r4 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo) r4
            java.lang.Object r6 = r2.L$6
            java.lang.Object r6 = r2.L$5
            java.util.Iterator r6 = (java.util.Iterator) r6
            java.lang.Object r7 = r2.L$4
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.lang.Object r8 = r2.L$3
            com.bitcoin.bitcoink.tx.TxOutput r8 = (com.bitcoin.bitcoink.p008tx.TxOutput) r8
            java.lang.Object r9 = r2.L$2
            com.bitcoin.bitcoink.tx.TxBuilder r9 = (com.bitcoin.bitcoink.p008tx.TxBuilder) r9
            java.lang.Object r10 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest r10 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest) r10
            java.lang.Object r11 = r2.L$0
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r11 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor) r11
            kotlin.ResultKt.throwOnFailure(r1)
            r14 = r2
            r17 = r3
            r16 = r4
            r3 = r6
            r15 = r7
            r4 = r8
            r2 = r9
            r13 = r10
            r12 = r11
            goto L_0x013b
        L_0x0058:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0060:
            kotlin.ResultKt.throwOnFailure(r1)
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r1 = r20.getUtxoSelection()
            com.bitcoin.mwallet.core.models.Coin r1 = r1.getCoin()
            com.bitcoin.bitcoink.tx.TxBuilder r1 = r0.getTxBuilder(r1)
            java.util.List r4 = r20.getBip70PaymentOutputs()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r4 = r4.iterator()
        L_0x0079:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x00b5
            java.lang.Object r6 = r4.next()
            com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentOutput r6 = (com.bitcoin.mwallet.core.models.bip70payment.Bip70PaymentOutput) r6
            com.bitcoin.mwallet.core.models.address.AddressAndOriginalText r7 = r6.getAddress()
            if (r7 == 0) goto L_0x00a0
            com.bitcoin.bitcoink.tx.Satoshis r7 = new com.bitcoin.bitcoink.tx.Satoshis
            long r8 = r6.getAmount()
            r7.<init>(r8)
            com.bitcoin.mwallet.core.models.address.AddressAndOriginalText r6 = r6.getAddress()
            com.bitcoin.bitcoink.address.Address r6 = r6.getParsed()
            r1.addOutput(r7, r6)
            goto L_0x0079
        L_0x00a0:
            com.bitcoin.bitcoink.tx.Satoshis r7 = new com.bitcoin.bitcoink.tx.Satoshis
            long r8 = r6.getAmount()
            r7.<init>(r8)
            java.lang.String r6 = r6.getScript()
            org.bitcoinj.script.Script r6 = r0.convertBip70Script(r6)
            r1.addOutput(r7, r6)
            goto L_0x0079
        L_0x00b5:
            r4 = 0
            com.bitcoin.bitcoink.tx.TxOutput r4 = (com.bitcoin.bitcoink.p008tx.TxOutput) r4
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r6 = r20.getUtxoSelection()
            com.bitcoin.bitcoink.tx.Satoshis r6 = r6.getChange()
            if (r6 == 0) goto L_0x00d6
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r4 = r20.getUtxoSelection()
            com.bitcoin.bitcoink.tx.Satoshis r4 = r4.getChange()
            com.bitcoin.mwallet.core.models.address.AddressAndPath r6 = r20.getChangeAddress()
            com.bitcoin.bitcoink.address.Address r6 = r6.getAddress()
            com.bitcoin.bitcoink.tx.TxOutput r4 = r1.addOutput(r4, r6)
        L_0x00d6:
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r6 = r20.getUtxoSelection()
            java.util.List r6 = r6.getUtxos()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r7 = r6.iterator()
            r9 = r1
            r8 = r4
            r1 = r0
            r4 = r3
            r3 = r2
            r2 = r20
            r18 = r7
            r7 = r6
            r6 = r18
        L_0x00f0:
            boolean r10 = r6.hasNext()
            if (r10 == 0) goto L_0x0161
            java.lang.Object r10 = r6.next()
            r15 = r10
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo r15 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo) r15
            com.bitcoin.mwallet.core.repositories.PrivateKeyRepository r11 = r1.privateKeyRepository
            com.bitcoin.bitcoink.Network r12 = r2.getWalletNetwork()
            com.bitcoin.mwallet.core.models.credential.CredentialId r13 = r2.getWalletCredentialId()
            com.bitcoin.bitcoink.DerivationPath r14 = r2.getWalletPath()
            com.bitcoin.bitcoink.DerivationPath r16 = r15.getAddressPath()
            r3.L$0 = r1
            r3.L$1 = r2
            r3.L$2 = r9
            r3.L$3 = r8
            r3.L$4 = r7
            r3.L$5 = r6
            r3.L$6 = r10
            r3.L$7 = r15
            r3.label = r5
            r10 = r11
            r11 = r12
            r12 = r13
            r13 = r14
            r14 = r16
            r16 = r15
            r15 = r3
            java.lang.Object r10 = r10.getPrivateKey(r11, r12, r13, r14, r15)
            if (r10 != r4) goto L_0x0131
            return r4
        L_0x0131:
            r12 = r1
            r13 = r2
            r14 = r3
            r17 = r4
            r3 = r6
            r15 = r7
            r4 = r8
            r2 = r9
            r1 = r10
        L_0x013b:
            if (r1 != 0) goto L_0x0140
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0140:
            r11 = r1
            com.bitcoin.bitcoink.PrivateKey r11 = (com.bitcoin.bitcoink.PrivateKey) r11
            com.bitcoin.bitcoink.tx.TxId r7 = r16.getTxId()
            int r8 = r16.getOutputIndex()
            byte[] r9 = r16.getScript()
            com.bitcoin.bitcoink.tx.Satoshis r10 = r16.getSatoshis()
            r6 = r2
            r6.addInput(r7, r8, r9, r10, r11)
            r9 = r2
            r6 = r3
            r8 = r4
            r1 = r12
            r2 = r13
            r3 = r14
            r7 = r15
            r4 = r17
            goto L_0x00f0
        L_0x0161:
            byte[] r1 = r9.serialize()
            kotlin.Pair r2 = new kotlin.Pair
            r2.<init>(r1, r8)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.SendTxInteractor.createBip70RawRx(com.bitcoin.mwallet.app.flows.sendv2.entity.SendBip70TxRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0195  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createSlpRawTx(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Triple<byte[], com.bitcoin.bitcoink.p008tx.TxOutput, com.bitcoin.bitcoink.p008tx.TxOutput>> r24) {
        /*
            r22 = this;
            r0 = r22
            r1 = r24
            boolean r2 = r1 instanceof com.bitcoin.mwallet.core.interactors.SendTxInteractor$createSlpRawTx$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$createSlpRawTx$1 r2 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor$createSlpRawTx$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$createSlpRawTx$1 r2 = new com.bitcoin.mwallet.core.interactors.SendTxInteractor$createSlpRawTx$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L_0x006a
            if (r4 != r5) goto L_0x0062
            java.lang.Object r4 = r2.L$10
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo r4 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo) r4
            java.lang.Object r6 = r2.L$9
            java.lang.Object r6 = r2.L$8
            java.util.Iterator r6 = (java.util.Iterator) r6
            java.lang.Object r7 = r2.L$7
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.lang.Object r8 = r2.L$6
            com.bitcoin.bitcoink.tx.TxBuilder r8 = (com.bitcoin.bitcoink.p008tx.TxBuilder) r8
            java.lang.Object r9 = r2.L$5
            com.bitcoin.bitcoink.tx.TxOutput r9 = (com.bitcoin.bitcoink.p008tx.TxOutput) r9
            java.lang.Object r10 = r2.L$4
            com.bitcoin.bitcoink.tx.TxOutput r10 = (com.bitcoin.bitcoink.p008tx.TxOutput) r10
            java.lang.Object r11 = r2.L$3
            java.util.List r11 = (java.util.List) r11
            java.lang.Object r12 = r2.L$2
            com.bitcoin.mwallet.core.models.slp.Slp r12 = (com.bitcoin.mwallet.core.models.slp.Slp) r12
            java.lang.Object r13 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r13 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest) r13
            java.lang.Object r14 = r2.L$0
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r14 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor) r14
            kotlin.ResultKt.throwOnFailure(r1)
            r18 = r6
            r17 = r7
            r16 = r9
            r15 = r10
            r9 = r2
            r10 = r3
            goto L_0x0193
        L_0x0062:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x006a:
            kotlin.ResultKt.throwOnFailure(r1)
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r1 = r23.getSlpUtxoSelection()
            r4 = 0
            if (r1 == 0) goto L_0x0079
            com.bitcoin.mwallet.core.models.slp.Slp r1 = r1.getToken()
            goto L_0x007a
        L_0x0079:
            r1 = r4
        L_0x007a:
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r6 = r23.getSlpUtxoSelection()
            if (r6 == 0) goto L_0x0085
            java.util.List r6 = r6.getQuantities()
            goto L_0x0086
        L_0x0085:
            r6 = r4
        L_0x0086:
            r7 = r4
            com.bitcoin.bitcoink.tx.TxOutput r7 = (com.bitcoin.bitcoink.p008tx.TxOutput) r7
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r8 = r23.getUtxoSelection()
            com.bitcoin.mwallet.core.models.Coin r8 = r8.getCoin()
            com.bitcoin.bitcoink.tx.TxBuilder r8 = r0.getTxBuilder(r8)
            if (r6 == 0) goto L_0x00b4
            if (r1 == 0) goto L_0x009e
            com.bitcoin.mwallet.core.models.slp.SlpId r9 = r1.getTokenId()
            goto L_0x009f
        L_0x009e:
            r9 = r4
        L_0x009f:
            if (r9 == 0) goto L_0x00b4
            com.bitcoin.mwallet.core.models.slp.SlpId r9 = r1.getTokenId()
            com.bitcoin.mwallet.core.utils.slp.SlpTokenType r10 = com.bitcoin.mwallet.core.utils.slp.SlpTokenType.PERMISSIONLESS
            com.bitcoin.mwallet.core.utils.slp.SlpTransactionType r11 = com.bitcoin.mwallet.core.utils.slp.SlpTransactionType.SEND
            org.bitcoinj.script.Script r9 = r0.createSlpScript(r9, r10, r11, r6)
            com.bitcoin.bitcoink.address.Address r10 = r23.getToAddress()
            r8.addOutput(r10, r9)
        L_0x00b4:
            com.bitcoin.bitcoink.tx.Satoshis r9 = new com.bitcoin.bitcoink.tx.Satoshis
            r10 = 546(0x222, double:2.7E-321)
            r9.<init>(r10)
            com.bitcoin.bitcoink.address.Address r12 = r23.getToAddress()
            r8.addOutput(r9, r12)
            if (r6 == 0) goto L_0x00e3
            int r9 = r6.size()
            r12 = 2
            if (r9 != r12) goto L_0x00e3
            com.bitcoin.mwallet.core.models.address.AddressAndPath r9 = r23.getSlpChangeAddress()
            if (r9 == 0) goto L_0x00e1
            com.bitcoin.bitcoink.address.Address r9 = r9.getAddress()
            if (r9 == 0) goto L_0x00e1
            com.bitcoin.bitcoink.tx.Satoshis r12 = new com.bitcoin.bitcoink.tx.Satoshis
            r12.<init>(r10)
            com.bitcoin.bitcoink.tx.TxOutput r9 = r8.addOutput(r12, r9)
            goto L_0x00e4
        L_0x00e1:
            r9 = r4
            goto L_0x00e4
        L_0x00e3:
            r9 = r7
        L_0x00e4:
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r12 = r23.getSlpUtxoSelection()
            if (r12 == 0) goto L_0x00f8
            com.bitcoin.bitcoink.tx.Satoshis r12 = r12.getChange()
            if (r12 == 0) goto L_0x00f8
            long r12 = r12.getSatoshis()
            java.lang.Long r4 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r12)
        L_0x00f8:
            if (r4 != 0) goto L_0x00fd
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00fd:
            long r12 = r4.longValue()
            int r4 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r4 < 0) goto L_0x0119
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r4 = r23.getSlpUtxoSelection()
            com.bitcoin.bitcoink.tx.Satoshis r4 = r4.getChange()
            com.bitcoin.mwallet.core.models.address.AddressAndPath r7 = r23.getChangeAddress()
            com.bitcoin.bitcoink.address.Address r7 = r7.getAddress()
            com.bitcoin.bitcoink.tx.TxOutput r7 = r8.addOutput(r4, r7)
        L_0x0119:
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r4 = r23.getSlpUtxoSelection()
            java.util.List r4 = r4.getUtxos()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r10 = r4.iterator()
            r14 = r3
            r15 = r4
            r4 = r1
            r3 = r2
            r2 = r23
            r1 = r0
            r21 = r7
            r7 = r6
            r6 = r10
            r10 = r9
            r9 = r21
        L_0x0135:
            boolean r11 = r6.hasNext()
            if (r11 == 0) goto L_0x01c0
            java.lang.Object r11 = r6.next()
            r13 = r11
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo r13 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo) r13
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo$Companion r12 = com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo.Companion
            com.bitcoin.mwallet.core.repositories.PrivateKeyRepository r12 = r1.privateKeyRepository
            com.bitcoin.bitcoink.Network r16 = r2.getWalletNetwork()
            com.bitcoin.mwallet.core.models.credential.CredentialId r17 = r2.getWalletCredentialId()
            com.bitcoin.bitcoink.DerivationPath r18 = r2.getWalletPath()
            com.bitcoin.bitcoink.DerivationPath r19 = r13.getAddressPath()
            r3.L$0 = r1
            r3.L$1 = r2
            r3.L$2 = r4
            r3.L$3 = r7
            r3.L$4 = r10
            r3.L$5 = r9
            r3.L$6 = r8
            r3.L$7 = r15
            r3.L$8 = r6
            r3.L$9 = r11
            r3.L$10 = r13
            r3.label = r5
            r11 = r12
            r12 = r16
            r20 = r13
            r13 = r17
            r5 = r14
            r14 = r18
            r17 = r15
            r15 = r19
            r16 = r3
            java.lang.Object r11 = r11.getPrivateKey(r12, r13, r14, r15, r16)
            if (r11 != r5) goto L_0x0185
            return r5
        L_0x0185:
            r14 = r1
            r13 = r2
            r12 = r4
            r18 = r6
            r16 = r9
            r15 = r10
            r1 = r11
            r4 = r20
            r9 = r3
            r10 = r5
            r11 = r7
        L_0x0193:
            if (r1 != 0) goto L_0x0198
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0198:
            r7 = r1
            com.bitcoin.bitcoink.PrivateKey r7 = (com.bitcoin.bitcoink.PrivateKey) r7
            com.bitcoin.bitcoink.tx.TxId r3 = r4.getTxId()
            int r1 = r4.getOutputIndex()
            byte[] r5 = r4.getScript()
            com.bitcoin.bitcoink.tx.Satoshis r6 = r4.getSatoshis()
            r2 = r8
            r4 = r1
            r2.addInput(r3, r4, r5, r6, r7)
            r3 = r9
            r7 = r11
            r4 = r12
            r2 = r13
            r1 = r14
            r9 = r16
            r6 = r18
            r5 = 1
            r14 = r10
            r10 = r15
            r15 = r17
            goto L_0x0135
        L_0x01c0:
            byte[] r1 = r8.serialize()
            kotlin.Triple r2 = new kotlin.Triple
            r2.<init>(r1, r9, r10)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.SendTxInteractor.createSlpRawTx(com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createNonZionRawTx(@org.jetbrains.annotations.NotNull com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Triple<byte[], com.bitcoin.bitcoink.p008tx.TxOutput, com.bitcoin.bitcoink.p008tx.TxOutput>> r22) {
        /*
            r20 = this;
            r0 = r20
            r1 = r22
            boolean r2 = r1 instanceof com.bitcoin.mwallet.core.interactors.SendTxInteractor$createNonZionRawTx$1
            if (r2 == 0) goto L_0x0018
            r2 = r1
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$createNonZionRawTx$1 r2 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor$createNonZionRawTx$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$createNonZionRawTx$1 r2 = new com.bitcoin.mwallet.core.interactors.SendTxInteractor$createNonZionRawTx$1
            r2.<init>(r0, r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L_0x0062
            if (r4 != r6) goto L_0x005a
            java.lang.Object r4 = r2.L$7
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo r4 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo) r4
            java.lang.Object r7 = r2.L$6
            java.lang.Object r7 = r2.L$5
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r8 = r2.L$4
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.lang.Object r9 = r2.L$3
            com.bitcoin.bitcoink.tx.TxOutput r9 = (com.bitcoin.bitcoink.p008tx.TxOutput) r9
            java.lang.Object r10 = r2.L$2
            com.bitcoin.bitcoink.tx.TxBuilder r10 = (com.bitcoin.bitcoink.p008tx.TxBuilder) r10
            java.lang.Object r11 = r2.L$1
            com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest r11 = (com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest) r11
            java.lang.Object r12 = r2.L$0
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r12 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor) r12
            kotlin.ResultKt.throwOnFailure(r1)
            r15 = r2
            r17 = r3
            r18 = r4
            r3 = r7
            r16 = r8
            r4 = r9
            r2 = r10
            r14 = r11
            r13 = r12
            goto L_0x0109
        L_0x005a:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0062:
            kotlin.ResultKt.throwOnFailure(r1)
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r1 = r21.getUtxoSelection()
            com.bitcoin.mwallet.core.models.Coin r1 = r1.getCoin()
            com.bitcoin.bitcoink.tx.TxBuilder r1 = r0.getTxBuilder(r1)
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r4 = r21.getUtxoSelection()
            com.bitcoin.bitcoink.tx.Satoshis r4 = r4.getSendAmount()
            com.bitcoin.bitcoink.address.Address r7 = r21.getToAddress()
            r1.addOutput(r4, r7)
            r4 = r5
            com.bitcoin.bitcoink.tx.TxOutput r4 = (com.bitcoin.bitcoink.p008tx.TxOutput) r4
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r7 = r21.getUtxoSelection()
            com.bitcoin.bitcoink.tx.Satoshis r7 = r7.getChange()
            if (r7 == 0) goto L_0x00a1
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r4 = r21.getUtxoSelection()
            com.bitcoin.bitcoink.tx.Satoshis r4 = r4.getChange()
            com.bitcoin.mwallet.core.models.address.AddressAndPath r7 = r21.getChangeAddress()
            com.bitcoin.bitcoink.address.Address r7 = r7.getAddress()
            com.bitcoin.bitcoink.tx.TxOutput r4 = r1.addOutput(r4, r7)
        L_0x00a1:
            com.bitcoin.mwallet.core.models.tx.utxo.UtxoSelection r7 = r21.getUtxoSelection()
            java.util.List r7 = r7.getUtxos()
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.Iterator r8 = r7.iterator()
            r10 = r1
            r9 = r4
            r1 = r0
            r4 = r3
            r3 = r2
            r2 = r21
            r19 = r8
            r8 = r7
            r7 = r19
        L_0x00bb:
            boolean r11 = r7.hasNext()
            if (r11 == 0) goto L_0x0130
            java.lang.Object r11 = r7.next()
            r15 = r11
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo r15 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo) r15
            com.bitcoin.mwallet.core.repositories.PrivateKeyRepository r12 = r1.privateKeyRepository
            com.bitcoin.bitcoink.Network r13 = r2.getWalletNetwork()
            com.bitcoin.mwallet.core.models.credential.CredentialId r14 = r2.getWalletCredentialId()
            com.bitcoin.bitcoink.DerivationPath r16 = r2.getWalletPath()
            com.bitcoin.bitcoink.DerivationPath r17 = r15.getAddressPath()
            r3.L$0 = r1
            r3.L$1 = r2
            r3.L$2 = r10
            r3.L$3 = r9
            r3.L$4 = r8
            r3.L$5 = r7
            r3.L$6 = r11
            r3.L$7 = r15
            r3.label = r6
            r11 = r12
            r12 = r13
            r13 = r14
            r14 = r16
            r18 = r15
            r15 = r17
            r16 = r3
            java.lang.Object r11 = r11.getPrivateKey(r12, r13, r14, r15, r16)
            if (r11 != r4) goto L_0x00fe
            return r4
        L_0x00fe:
            r13 = r1
            r14 = r2
            r15 = r3
            r17 = r4
            r3 = r7
            r16 = r8
            r4 = r9
            r2 = r10
            r1 = r11
        L_0x0109:
            if (r1 != 0) goto L_0x010e
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x010e:
            r12 = r1
            com.bitcoin.bitcoink.PrivateKey r12 = (com.bitcoin.bitcoink.PrivateKey) r12
            com.bitcoin.bitcoink.tx.TxId r8 = r18.getTxId()
            int r9 = r18.getOutputIndex()
            byte[] r10 = r18.getScript()
            com.bitcoin.bitcoink.tx.Satoshis r11 = r18.getSatoshis()
            r7 = r2
            r7.addInput(r8, r9, r10, r11, r12)
            r10 = r2
            r7 = r3
            r9 = r4
            r1 = r13
            r2 = r14
            r3 = r15
            r8 = r16
            r4 = r17
            goto L_0x00bb
        L_0x0130:
            byte[] r1 = r10.serialize()
            kotlin.Triple r2 = new kotlin.Triple
            r2.<init>(r1, r9, r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.SendTxInteractor.createNonZionRawTx(com.bitcoin.mwallet.app.flows.sendv2.entity.SendTxRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final TxBuilder getTxBuilder(Coin coin) {
        int i = WhenMappings.$EnumSwitchMapping$2[coin.ordinal()];
        if (i == 1) {
            return new TxBuilder(BitcoinFork.BCH);
        }
        if (i == 2) {
            return new TxBuilder(BitcoinFork.BTC);
        }
        throw new NoWhenBranchMatchedException();
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object updateSlpChangeUtxos(@org.jetbrains.annotations.NotNull com.bitcoin.bitcoink.p008tx.TxId r20, @org.jetbrains.annotations.NotNull com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection r21, @org.jetbrains.annotations.Nullable kotlin.Triple<com.bitcoin.mwallet.core.models.wallet.WalletId, com.bitcoin.bitcoink.p008tx.TxOutput, com.bitcoin.bitcoink.DerivationPath> r22, @org.jetbrains.annotations.Nullable java.lang.Integer r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r24) {
        /*
            r19 = this;
            r0 = r19
            r1 = r22
            r2 = r23
            r3 = r24
            boolean r4 = r3 instanceof com.bitcoin.mwallet.core.interactors.SendTxInteractor$updateSlpChangeUtxos$1
            if (r4 == 0) goto L_0x001c
            r4 = r3
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$updateSlpChangeUtxos$1 r4 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor$updateSlpChangeUtxos$1) r4
            int r5 = r4.label
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r5 & r6
            if (r5 == 0) goto L_0x001c
            int r3 = r4.label
            int r3 = r3 - r6
            r4.label = r3
            goto L_0x0021
        L_0x001c:
            com.bitcoin.mwallet.core.interactors.SendTxInteractor$updateSlpChangeUtxos$1 r4 = new com.bitcoin.mwallet.core.interactors.SendTxInteractor$updateSlpChangeUtxos$1
            r4.<init>(r0, r3)
        L_0x0021:
            java.lang.Object r3 = r4.result
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r6 = r4.label
            r7 = 1
            if (r6 == 0) goto L_0x0067
            if (r6 != r7) goto L_0x005f
            java.lang.Object r1 = r4.L$10
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxo r1 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxo) r1
            java.lang.Object r1 = r4.L$9
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo r1 = (com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo) r1
            java.lang.Object r1 = r4.L$8
            com.bitcoin.bitcoink.DerivationPath r1 = (com.bitcoin.bitcoink.DerivationPath) r1
            java.lang.Object r1 = r4.L$7
            com.bitcoin.bitcoink.tx.TxOutput r1 = (com.bitcoin.bitcoink.p008tx.TxOutput) r1
            java.lang.Object r1 = r4.L$6
            com.bitcoin.mwallet.core.models.wallet.WalletId r1 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r1
            java.lang.Object r1 = r4.L$5
            kotlin.Triple r1 = (kotlin.Triple) r1
            java.lang.Object r1 = r4.L$4
            java.lang.Integer r1 = (java.lang.Integer) r1
            java.lang.Object r1 = r4.L$3
            kotlin.Triple r1 = (kotlin.Triple) r1
            java.lang.Object r1 = r4.L$2
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r1 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection) r1
            java.lang.Object r1 = r4.L$1
            com.bitcoin.bitcoink.tx.TxId r1 = (com.bitcoin.bitcoink.p008tx.TxId) r1
            java.lang.Object r1 = r4.L$0
            com.bitcoin.mwallet.core.interactors.SendTxInteractor r1 = (com.bitcoin.mwallet.core.interactors.SendTxInteractor) r1
            kotlin.ResultKt.throwOnFailure(r3)
            goto L_0x0118
        L_0x005f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0067:
            kotlin.ResultKt.throwOnFailure(r3)
            if (r2 != 0) goto L_0x006f
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x006f:
            if (r1 == 0) goto L_0x0118
            java.lang.Object r3 = r22.component1()
            com.bitcoin.mwallet.core.models.wallet.WalletId r3 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r3
            java.lang.Object r6 = r22.component2()
            com.bitcoin.bitcoink.tx.TxOutput r6 = (com.bitcoin.bitcoink.p008tx.TxOutput) r6
            java.lang.Object r8 = r22.component3()
            r15 = r8
            com.bitcoin.bitcoink.DerivationPath r15 = (com.bitcoin.bitcoink.DerivationPath) r15
            com.bitcoin.mwallet.core.models.tx.utxo.Utxo r14 = new com.bitcoin.mwallet.core.models.tx.utxo.Utxo
            int r11 = r23.intValue()
            com.bitcoin.bitcoink.address.Address r12 = r6.getAddress()
            byte[] r16 = r6.getScript()
            com.bitcoin.bitcoink.tx.Satoshis r17 = r6.getSatoshis()
            com.bitcoin.mwallet.core.models.Coin r18 = com.bitcoin.mwallet.core.models.Coin.BCH
            r8 = r14
            r9 = r3
            r10 = r20
            r13 = r15
            r24 = r14
            r14 = r16
            r7 = r15
            r15 = r17
            r16 = r18
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16)
            com.bitcoin.mwallet.core.models.slp.Slp r8 = r21.getToken()
            com.bitcoin.mwallet.core.models.slp.SlpId r9 = r8.getTokenId()
            java.util.List r8 = r21.getQuantities()
            java.lang.Object r8 = kotlin.collections.CollectionsKt.last(r8)
            kotlin.ULong r8 = (kotlin.ULong) r8
            long r10 = r8.m676unboximpl()
            com.bitcoin.mwallet.core.utils.slp.SlpTokenType r8 = com.bitcoin.mwallet.core.utils.slp.SlpTokenType.PERMISSIONLESS
            java.lang.String r12 = r8.toString()
            com.bitcoin.mwallet.core.utils.slp.SlpTransactionType r8 = com.bitcoin.mwallet.core.utils.slp.SlpTransactionType.SEND
            java.lang.String r13 = r8.toString()
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxo r15 = new com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxo
            r8 = r15
            r14 = r24
            r8.<init>(r9, r10, r12, r13, r14)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Inserting change utxo "
            r8.append(r9)
            r9 = r24
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r10 = 0
            java.lang.Object[] r10 = new java.lang.Object[r10]
            timber.log.Timber.m426d(r8, r10)
            com.bitcoin.mwallet.core.repositories.UtxoRepository r8 = r0.utxoRepository
            java.util.List r10 = kotlin.collections.CollectionsKt.listOf(r15)
            java.util.Collection r10 = (java.util.Collection) r10
            r4.L$0 = r0
            r11 = r20
            r4.L$1 = r11
            r11 = r21
            r4.L$2 = r11
            r4.L$3 = r1
            r4.L$4 = r2
            r4.L$5 = r1
            r4.L$6 = r3
            r4.L$7 = r6
            r4.L$8 = r7
            r4.L$9 = r9
            r4.L$10 = r15
            r1 = 1
            r4.label = r1
            java.lang.Object r1 = r8.slpUtxoUpsert(r10, r4)
            if (r1 != r5) goto L_0x0118
            return r5
        L_0x0118:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.interactors.SendTxInteractor.updateSlpChangeUtxos(com.bitcoin.bitcoink.tx.TxId, com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection, kotlin.Triple, java.lang.Integer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void updateUtxos(TxId txId, UtxoSelection utxoSelection, SlpUtxoSelection slpUtxoSelection, Triple<WalletId, TxOutput, DerivationPath> triple, Triple<WalletId, TxOutput, DerivationPath> triple2, Integer num) {
        Triple<WalletId, TxOutput, DerivationPath> triple3 = triple2;
        String str = "Setting sent utxos ";
        if (slpUtxoSelection != null) {
            Iterable<Utxo> utxos = slpUtxoSelection.getUtxos();
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(utxos, 10));
            for (Utxo utxo : utxos) {
                arrayList.add(new Pair(utxo.getTxId(), Integer.valueOf(utxo.getOutputIndex())));
            }
            List list = (List) arrayList;
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SendTxInteractor$updateUtxos$1(this, list, null), 3, null);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(list);
            Timber.m426d(sb.toString(), new Object[0]);
            if (slpUtxoSelection.getChange() != null) {
                IntRef intRef = new IntRef();
                intRef.element = 3;
                if (triple3 == null) {
                    intRef.element = 2;
                }
                if (triple != null) {
                    TxOutput txOutput = (TxOutput) triple.component2();
                    Utxo utxo2 = new Utxo((WalletId) triple.component1(), txId, intRef.element, txOutput.getAddress(), (DerivationPath) triple.component3(), txOutput.getScript(), slpUtxoSelection.getChange(), utxoSelection.getCoin());
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Inserting change SLP's BCH utxo ");
                    sb2.append(utxo2);
                    Timber.m426d(sb2.toString(), new Object[0]);
                    CoroutineScope coroutineScope = GlobalScope.INSTANCE;
                    SendTxInteractor$updateUtxos$$inlined$let$lambda$1 sendTxInteractor$updateUtxos$$inlined$let$lambda$1 = new SendTxInteractor$updateUtxos$$inlined$let$lambda$1(utxo2, null, this, txId, intRef, slpUtxoSelection, utxoSelection);
                    BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, sendTxInteractor$updateUtxos$$inlined$let$lambda$1, 3, null);
                }
            }
            if (triple3 != null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Inserting Token Change utxo ");
                sb3.append(triple3);
                Timber.m426d(sb3.toString(), new Object[0]);
                CoroutineScope coroutineScope2 = GlobalScope.INSTANCE;
                SendTxInteractor$updateUtxos$3 sendTxInteractor$updateUtxos$3 = new SendTxInteractor$updateUtxos$3(this, txId, slpUtxoSelection, triple2, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, sendTxInteractor$updateUtxos$3, 3, null);
                return;
            }
            return;
        }
        Iterable<Utxo> utxos2 = utxoSelection.getUtxos();
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(utxos2, 10));
        for (Utxo utxo3 : utxos2) {
            arrayList2.add(new Pair(utxo3.getTxId(), Integer.valueOf(utxo3.getOutputIndex())));
        }
        List list2 = (List) arrayList2;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new SendTxInteractor$updateUtxos$4(this, list2, null), 3, null);
        StringBuilder sb4 = new StringBuilder();
        sb4.append(str);
        sb4.append(list2);
        Timber.m426d(sb4.toString(), new Object[0]);
        int intValue = num != null ? num.intValue() : 1;
        if (triple != null) {
            TxOutput txOutput2 = (TxOutput) triple.component2();
            Utxo utxo4 = new Utxo((WalletId) triple.component1(), txId, intValue, txOutput2.getAddress(), (DerivationPath) triple.component3(), txOutput2.getScript(), txOutput2.getSatoshis(), utxoSelection.getCoin());
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Inserting change utxo ");
            sb5.append(utxo4);
            Timber.m426d(sb5.toString(), new Object[0]);
            CoroutineScope coroutineScope3 = GlobalScope.INSTANCE;
            SendTxInteractor$updateUtxos$$inlined$let$lambda$2 sendTxInteractor$updateUtxos$$inlined$let$lambda$2 = new SendTxInteractor$updateUtxos$$inlined$let$lambda$2(utxo4, null, this, txId, intValue, utxoSelection);
            BuildersKt__Builders_commonKt.launch$default(coroutineScope3, null, null, sendTxInteractor$updateUtxos$$inlined$let$lambda$2, 3, null);
        }
    }
}
