package com.bitcoin.mwallet.core.models.address;

import com.bitcoin.bitcoink.Network;
import com.bitcoin.bitcoink.address.Address;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/address/AddressInvalid;", "Lcom/bitcoin/bitcoink/address/Address;", "()V", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddressAndOiginalText.kt */
public final class AddressInvalid extends Address {
    public AddressInvalid() {
        super(Network.TEST, -1, new byte[]{0});
    }
}
