package com.bitcoin.mwallet.core.interactors;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxo;
import com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection;
import com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo;
import com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection;
import com.bitcoin.mwallet.core.models.p009tx.utxo.UtxoSelection.Error;
import com.bitcoin.mwallet.core.models.slp.Slp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.UnsignedKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.bitcoinj.core.TransactionInput;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import timber.log.Timber;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 %2\u00020\u0001:\u0005%&'()B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJK\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010\u001bJ:\u0010\u000b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00102\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u0017J\u0014\u0010#\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010$\u001a\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00060\u0007R\u00020\u0000X\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/UtxoSelector;", "", "()V", "sendMaxSelector", "Lcom/bitcoin/mwallet/core/interactors/UtxoSelector$Selector;", "smallestUtxoFirstSelector", "tokenUtxoSelector", "Lcom/bitcoin/mwallet/core/interactors/UtxoSelector$SlpTokenUtxoSelector;", "opReturnSizeInBytes", "", "numQuantities", "select", "Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "utxos", "", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "sendAmount", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "sendMax", "", "satoshiPerByte", "Ljava/math/BigDecimal;", "outputs", "extraFee", "", "(Lcom/bitcoin/mwallet/core/models/Coin;Ljava/util/List;Lcom/bitcoin/bitcoink/tx/Satoshis;ZLjava/math/BigDecimal;Ljava/lang/Integer;J)Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;", "walletUtxos", "slpUtxos", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxo;", "token", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "sendTokenAmount", "times", "value", "Companion", "Selector", "SendMaxUtxoSelector", "SlpTokenUtxoSelector", "SmallestUtxoFirstSelector", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: UtxoSelector.kt */
public final class UtxoSelector {
    public static final Companion Companion = new Companion(null);
    private static final int OP_RETURN_NUM_BYTES_BASE = 55;
    private static final int QUANTITY_NUM_BYTES = 9;
    private static final long SATOSHI_DUST_LIMIT = 546;
    private static final long SATOSHI_PER_INPUT = 148;
    private static final long SATOSHI_PER_OUTPUT = 34;
    private static final long SATOSHI_PROPAGATION_EXTRA = 50;
    private final Selector sendMaxSelector = new SendMaxUtxoSelector();
    private final Selector smallestUtxoFirstSelector = new SmallestUtxoFirstSelector();
    private final SlpTokenUtxoSelector tokenUtxoSelector = new SlpTokenUtxoSelector();

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/UtxoSelector$Companion;", "", "()V", "OP_RETURN_NUM_BYTES_BASE", "", "QUANTITY_NUM_BYTES", "SATOSHI_DUST_LIMIT", "", "SATOSHI_PER_INPUT", "SATOSHI_PER_OUTPUT", "SATOSHI_PROPAGATION_EXTRA", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: UtxoSelector.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bb\u0018\u00002\u00020\u0001JE\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/UtxoSelector$Selector;", "", "select", "Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "utxos", "", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "sendAmount", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "satoshiPerByte", "Ljava/math/BigDecimal;", "outputs", "", "extraFee", "", "(Lcom/bitcoin/mwallet/core/models/Coin;Ljava/util/List;Lcom/bitcoin/bitcoink/tx/Satoshis;Ljava/math/BigDecimal;Ljava/lang/Integer;J)Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: UtxoSelector.kt */
    private interface Selector {
        @NotNull
        UtxoSelection select(@NotNull Coin coin, @NotNull List<Utxo> list, @NotNull Satoshis satoshis, @NotNull BigDecimal bigDecimal, @Nullable Integer num, long j);
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JE\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/UtxoSelector$SendMaxUtxoSelector;", "Lcom/bitcoin/mwallet/core/interactors/UtxoSelector$Selector;", "(Lcom/bitcoin/mwallet/core/interactors/UtxoSelector;)V", "select", "Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "utxos", "", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "sendAmount", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "satoshiPerByte", "Ljava/math/BigDecimal;", "outputs", "", "extraFee", "", "(Lcom/bitcoin/mwallet/core/models/Coin;Ljava/util/List;Lcom/bitcoin/bitcoink/tx/Satoshis;Ljava/math/BigDecimal;Ljava/lang/Integer;J)Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: UtxoSelector.kt */
    private final class SendMaxUtxoSelector implements Selector {
        public SendMaxUtxoSelector() {
        }

        @NotNull
        public UtxoSelection select(@NotNull Coin coin, @NotNull List<Utxo> list, @NotNull Satoshis satoshis, @NotNull BigDecimal bigDecimal, @Nullable Integer num, long j) {
            Intrinsics.checkParameterIsNotNull(coin, "coin");
            Intrinsics.checkParameterIsNotNull(list, "utxos");
            Intrinsics.checkParameterIsNotNull(satoshis, "sendAmount");
            Intrinsics.checkParameterIsNotNull(bigDecimal, "satoshiPerByte");
            long access$times = UtxoSelector.this.times((((long) list.size()) * UtxoSelector.SATOSHI_PER_INPUT) + UtxoSelector.SATOSHI_PER_OUTPUT + UtxoSelector.SATOSHI_PROPAGATION_EXTRA + j, bigDecimal);
            long j2 = 0;
            for (Utxo satoshis2 : list) {
                j2 += satoshis2.getSatoshis().getSatoshis();
            }
            Timber.m426d("SendMaxUtxoSelector satoshiPerByte=%s feeSatoshi=%d totalBalanceSatoshis=%d", bigDecimal, Long.valueOf(access$times), Long.valueOf(j2));
            if (j2 < access$times) {
                return UtxoSelection.Companion.error(coin, satoshis, Error.INSUFFICIENT_FUNDS);
            }
            UtxoSelection utxoSelection = new UtxoSelection(coin, satoshis.minus(new Satoshis(access$times)), new Satoshis(access$times), null, list, null);
            return utxoSelection;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/UtxoSelector$SlpTokenUtxoSelector;", "", "(Lcom/bitcoin/mwallet/core/interactors/UtxoSelector;)V", "select", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;", "walletUtxos", "", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "slpUtxos", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxo;", "token", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "sendNumToken", "Ljava/math/BigDecimal;", "satoshiPerByte", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: UtxoSelector.kt */
    private final class SlpTokenUtxoSelector {
        public SlpTokenUtxoSelector() {
        }

        @NotNull
        public final SlpUtxoSelection select(@NotNull List<Utxo> list, @NotNull List<SlpUtxo> list2, @NotNull Slp slp, @NotNull BigDecimal bigDecimal, @NotNull BigDecimal bigDecimal2) {
            long j;
            List<Utxo> list3 = list;
            List<SlpUtxo> list4 = list2;
            Slp slp2 = slp;
            BigDecimal bigDecimal3 = bigDecimal2;
            Intrinsics.checkParameterIsNotNull(list3, "walletUtxos");
            Intrinsics.checkParameterIsNotNull(list4, "slpUtxos");
            Intrinsics.checkParameterIsNotNull(slp2, "token");
            Intrinsics.checkParameterIsNotNull(bigDecimal, "sendNumToken");
            Intrinsics.checkParameterIsNotNull(bigDecimal3, "satoshiPerByte");
            long rawAmount = slp.toRawAmount(bigDecimal);
            Iterable iterable = list4;
            Collection arrayList = new ArrayList();
            for (Object next : iterable) {
                if (Intrinsics.areEqual((Object) ((SlpUtxo) next).getTokenId(), (Object) slp.getTokenId())) {
                    arrayList.add(next);
                }
            }
            Iterable sortedWith = CollectionsKt.sortedWith((List) arrayList, new UtxoSelector$SlpTokenUtxoSelector$select$$inlined$sortedBy$1());
            ArrayList arrayList2 = new ArrayList();
            long j2 = 0;
            long j3 = 0;
            for (Object next2 : sortedWith) {
                SlpUtxo slpUtxo = (SlpUtxo) next2;
                boolean z = UnsignedKt.ulongCompare(j2, rawAmount) < 0;
                if (z) {
                    j2 = ULong.m633constructorimpl(j2 + ULong.m633constructorimpl(slpUtxo.getTokenAmount()));
                    j3 += slpUtxo.getUtxo().getSatoshis().getSatoshis() - ((long) 148);
                }
                if (!z) {
                    break;
                }
                arrayList2.add(next2);
            }
            Iterable<SlpUtxo> iterable2 = arrayList2;
            Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            for (SlpUtxo utxo : iterable2) {
                arrayList3.add(utxo.getUtxo());
            }
            List mutableList = CollectionsKt.toMutableList((Collection) (List) arrayList3);
            if (UnsignedKt.ulongCompare(j2, rawAmount) < 0) {
                return SlpUtxoSelection.Companion.error(slp2, rawAmount, SlpUtxoSelection.Error.INSUFFICIENT_FUNDS);
            }
            long j4 = UnsignedKt.ulongCompare(j2, rawAmount) > 0 ? 1092 : 546;
            UtxoSelector utxoSelector = UtxoSelector.this;
            long j5 = j3;
            long j6 = j2;
            long access$times = utxoSelector.times((((long) 3) * UtxoSelector.SATOSHI_PER_OUTPUT) + ((long) utxoSelector.opReturnSizeInBytes(2)) + UtxoSelector.SATOSHI_PROPAGATION_EXTRA, bigDecimal3);
            Iterable sortedWith2 = CollectionsKt.sortedWith(list3, new UtxoSelector$SlpTokenUtxoSelector$select$$inlined$sortedBy$2());
            ArrayList arrayList4 = new ArrayList();
            Iterator it = sortedWith2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    j = rawAmount;
                    break;
                }
                Object next3 = it.next();
                Utxo utxo2 = (Utxo) next3;
                boolean z2 = j5 <= j4 + access$times;
                if (z2) {
                    j = rawAmount;
                    j5 += utxo2.getSatoshis().getSatoshis() - ((long) 148);
                } else {
                    j = rawAmount;
                }
                if (!z2) {
                    break;
                }
                arrayList4.add(next3);
                rawAmount = j;
            }
            mutableList.addAll(arrayList4);
            long j7 = (j5 - j4) - access$times;
            if (j7 < 0) {
                return SlpUtxoSelection.Companion.error(slp2, j, SlpUtxoSelection.Error.INSUFFICIENT_FUNDS);
            }
            long j8 = j;
            List mutableListOf = CollectionsKt.mutableListOf(ULong.m627boximpl(j8));
            long r3 = ULong.m633constructorimpl(j6 - j8);
            if (UnsignedKt.ulongCompare(r3, ULong.m633constructorimpl(((long) 0) & TransactionInput.NO_SEQUENCE)) > 0) {
                mutableListOf.add(ULong.m627boximpl(r3));
            }
            SlpUtxoSelection slpUtxoSelection = new SlpUtxoSelection(slp, mutableListOf, new Satoshis(access$times), new Satoshis(j7), mutableList, null);
            return slpUtxoSelection;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JE\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/interactors/UtxoSelector$SmallestUtxoFirstSelector;", "Lcom/bitcoin/mwallet/core/interactors/UtxoSelector$Selector;", "(Lcom/bitcoin/mwallet/core/interactors/UtxoSelector;)V", "select", "Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "utxos", "", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "sendAmount", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "satoshiPerByte", "Ljava/math/BigDecimal;", "outputs", "", "extraFee", "", "(Lcom/bitcoin/mwallet/core/models/Coin;Ljava/util/List;Lcom/bitcoin/bitcoink/tx/Satoshis;Ljava/math/BigDecimal;Ljava/lang/Integer;J)Lcom/bitcoin/mwallet/core/models/tx/utxo/UtxoSelection;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: UtxoSelector.kt */
    private final class SmallestUtxoFirstSelector implements Selector {
        public SmallestUtxoFirstSelector() {
        }

        @NotNull
        public UtxoSelection select(@NotNull Coin coin, @NotNull List<Utxo> list, @NotNull Satoshis satoshis, @NotNull BigDecimal bigDecimal, @Nullable Integer num, long j) {
            long j2;
            Coin coin2 = coin;
            List<Utxo> list2 = list;
            Satoshis satoshis2 = satoshis;
            BigDecimal bigDecimal2 = bigDecimal;
            Intrinsics.checkParameterIsNotNull(coin2, "coin");
            Intrinsics.checkParameterIsNotNull(list2, "utxos");
            Intrinsics.checkParameterIsNotNull(satoshis2, "sendAmount");
            Intrinsics.checkParameterIsNotNull(bigDecimal2, "satoshiPerByte");
            long access$times = UtxoSelector.this.times((((long) ((num != null ? num.intValue() : 1) + 1)) * UtxoSelector.SATOSHI_PER_OUTPUT) + UtxoSelector.SATOSHI_PROPAGATION_EXTRA + j, bigDecimal2);
            Iterable sortedWith = CollectionsKt.sortedWith(list2, Utxo.Companion.getSmallestFirst());
            ArrayList arrayList = new ArrayList();
            long j3 = 0;
            for (Object next : sortedWith) {
                Utxo utxo = (Utxo) next;
                boolean z = j3 < (satoshis.getSatoshis() + access$times) + 546;
                if (z) {
                    j3 += utxo.getSatoshis().getSatoshis();
                    access$times += UtxoSelector.this.times(UtxoSelector.SATOSHI_PER_INPUT, bigDecimal2);
                }
                if (!z) {
                    break;
                }
                arrayList.add(next);
            }
            List list3 = arrayList;
            if (j3 < satoshis.getSatoshis() + access$times) {
                return UtxoSelection.Companion.error(coin2, satoshis2, Error.INSUFFICIENT_FUNDS);
            }
            long satoshis3 = (j3 - satoshis.getSatoshis()) - access$times;
            if (satoshis3 < 546) {
                access$times += satoshis3;
                StringBuilder sb = new StringBuilder();
                sb.append("changeSatoshi=");
                sb.append(satoshis3);
                sb.append(" less than dust limit. Donating as fee");
                Timber.m426d(sb.toString(), new Object[0]);
                j2 = 0;
                satoshis3 = 0;
            } else {
                j2 = 0;
            }
            UtxoSelection utxoSelection = new UtxoSelection(coin, satoshis, new Satoshis(access$times), satoshis3 > j2 ? new Satoshis(satoshis3) : null, list3, null);
            return utxoSelection;
        }
    }

    public final int opReturnSizeInBytes(int i) {
        return (i * 9) + 55;
    }

    @NotNull
    public final SlpUtxoSelection select(@NotNull List<Utxo> list, @NotNull List<SlpUtxo> list2, @NotNull Slp slp, @NotNull BigDecimal bigDecimal, @NotNull BigDecimal bigDecimal2) {
        Intrinsics.checkParameterIsNotNull(list, "walletUtxos");
        Intrinsics.checkParameterIsNotNull(list2, "slpUtxos");
        Intrinsics.checkParameterIsNotNull(slp, "token");
        Intrinsics.checkParameterIsNotNull(bigDecimal, "sendTokenAmount");
        Intrinsics.checkParameterIsNotNull(bigDecimal2, "satoshiPerByte");
        return this.tokenUtxoSelector.select(list, list2, slp, bigDecimal, bigDecimal2);
    }

    @NotNull
    public final UtxoSelection select(@NotNull Coin coin, @NotNull List<Utxo> list, @NotNull Satoshis satoshis, boolean z, @NotNull BigDecimal bigDecimal, @Nullable Integer num, long j) {
        Coin coin2 = coin;
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        List<Utxo> list2 = list;
        Intrinsics.checkParameterIsNotNull(list, "utxos");
        Satoshis satoshis2 = satoshis;
        Intrinsics.checkParameterIsNotNull(satoshis, "sendAmount");
        BigDecimal bigDecimal2 = bigDecimal;
        Intrinsics.checkParameterIsNotNull(bigDecimal, "satoshiPerByte");
        if (z) {
            return this.sendMaxSelector.select(coin, list, satoshis, bigDecimal, num, j);
        }
        return this.smallestUtxoFirstSelector.select(coin, list, satoshis, bigDecimal, num, j);
    }

    /* access modifiers changed from: private */
    public final long times(long j, BigDecimal bigDecimal) {
        return j * bigDecimal.longValue();
    }
}
