package com.bitcoin.mwallet;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;

public enum Coin implements EnumLite {
    COIN_NOT_SET(0),
    BCH(1),
    BTC(2),
    UNRECOGNIZED(-1);
    
    public static final int BCH_VALUE = 1;
    public static final int BTC_VALUE = 2;
    public static final int COIN_NOT_SET_VALUE = 0;
    private static final EnumLiteMap<Coin> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<Coin>() {
            public Coin findValueByNumber(int i) {
                return Coin.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static Coin valueOf(int i) {
        return forNumber(i);
    }

    public static Coin forNumber(int i) {
        if (i == 0) {
            return COIN_NOT_SET;
        }
        if (i == 1) {
            return BCH;
        }
        if (i != 2) {
            return null;
        }
        return BTC;
    }

    public static EnumLiteMap<Coin> internalGetValueMap() {
        return internalValueMap;
    }

    private Coin(int i) {
        this.value = i;
    }
}
