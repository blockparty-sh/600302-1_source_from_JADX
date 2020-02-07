package com.bitcoin.mwallet;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;

public enum FeeLevel implements EnumLite {
    UNKNOWN_FEE_LEVEL(0),
    FASTEST_FEE(1),
    HALF_HOUR_FEE(2),
    ONE_HOUR_FEE(3),
    UNRECOGNIZED(-1);
    
    public static final int FASTEST_FEE_VALUE = 1;
    public static final int HALF_HOUR_FEE_VALUE = 2;
    public static final int ONE_HOUR_FEE_VALUE = 3;
    public static final int UNKNOWN_FEE_LEVEL_VALUE = 0;
    private static final EnumLiteMap<FeeLevel> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<FeeLevel>() {
            public FeeLevel findValueByNumber(int i) {
                return FeeLevel.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static FeeLevel valueOf(int i) {
        return forNumber(i);
    }

    public static FeeLevel forNumber(int i) {
        if (i == 0) {
            return UNKNOWN_FEE_LEVEL;
        }
        if (i == 1) {
            return FASTEST_FEE;
        }
        if (i == 2) {
            return HALF_HOUR_FEE;
        }
        if (i != 3) {
            return null;
        }
        return ONE_HOUR_FEE;
    }

    public static EnumLiteMap<FeeLevel> internalGetValueMap() {
        return internalValueMap;
    }

    private FeeLevel(int i) {
        this.value = i;
    }
}
