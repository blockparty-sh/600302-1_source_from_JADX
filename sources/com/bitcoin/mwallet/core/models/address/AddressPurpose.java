package com.bitcoin.mwallet.core.models.address;

import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/address/AddressPurpose;", "", "pathX", "", "(Ljava/lang/String;II)V", "getPathX", "()I", "RECEIVE", "CHANGE", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddressPurpose.kt */
public enum AddressPurpose {
    RECEIVE(0),
    CHANGE(1);
    
    private final int pathX;

    private AddressPurpose(int i) {
        this.pathX = i;
    }

    public final int getPathX() {
        return this.pathX;
    }
}
