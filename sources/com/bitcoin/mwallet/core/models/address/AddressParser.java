package com.bitcoin.mwallet.core.models.address;

import com.bitcoin.bitcoink.Network;
import com.bitcoin.bitcoink.address.Address;
import com.bitcoin.bitcoink.address.AddressCash;
import com.bitcoin.bitcoink.address.AddressLegacy;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.core.extensions.StringKt;
import com.bitcoin.mwallet.core.models.Coin;
import java.math.BigDecimal;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00042\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/address/AddressParser;", "", "()V", "parseOrNull", "Lkotlin/Pair;", "Lcom/bitcoin/bitcoink/address/Address;", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "value", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddressParser.kt */
public final class AddressParser {
    public static final AddressParser INSTANCE = new AddressParser();

    @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];

        static {
            $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
            $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
        }
    }

    private AddressParser() {
    }

    @Nullable
    public final Pair<Address, Satoshis> parseOrNull(@NotNull Coin coin, @Nullable String str) {
        Address address;
        Intrinsics.checkParameterIsNotNull(coin, "coin");
        if (str != null) {
            Satoshis satoshis = null;
            List split$default = StringsKt.split$default((CharSequence) str, new String[]{"?amount="}, false, 0, 6, (Object) null);
            if (split$default.size() == 2) {
                BigDecimal tryParseBigDecimal = StringKt.tryParseBigDecimal((String) split$default.get(1));
                if (tryParseBigDecimal == null) {
                    return null;
                }
                satoshis = Satoshis.Companion.fromCoins(tryParseBigDecimal);
                str = (String) split$default.get(0);
            }
            int i = WhenMappings.$EnumSwitchMapping$0[coin.ordinal()];
            if (i == 1) {
                AddressCash tryParse = AddressCash.Companion.tryParse(Network.MAIN, str);
                if (tryParse != null) {
                    address = tryParse;
                } else {
                    address = AddressLegacy.Companion.parse(Network.MAIN, str);
                }
            } else if (i == 2) {
                address = AddressLegacy.Companion.parse(Network.MAIN, str);
            } else {
                throw new NoWhenBranchMatchedException();
            }
            if (address != null) {
                return new Pair<>(address, satoshis);
            }
        }
        return null;
    }
}
