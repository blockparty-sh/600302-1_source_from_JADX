package com.bitcoin.mwallet;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;

public enum Network implements EnumLite {
    NETWORK_NOT_SET(0),
    LIVE(1),
    TEST(2),
    UNRECOGNIZED(-1);
    
    public static final int LIVE_VALUE = 1;
    public static final int NETWORK_NOT_SET_VALUE = 0;
    public static final int TEST_VALUE = 2;
    private static final EnumLiteMap<Network> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<Network>() {
            public Network findValueByNumber(int i) {
                return Network.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static Network valueOf(int i) {
        return forNumber(i);
    }

    public static Network forNumber(int i) {
        if (i == 0) {
            return NETWORK_NOT_SET;
        }
        if (i == 1) {
            return LIVE;
        }
        if (i != 2) {
            return null;
        }
        return TEST;
    }

    public static EnumLiteMap<Network> internalGetValueMap() {
        return internalValueMap;
    }

    private Network(int i) {
        this.value = i;
    }
}
