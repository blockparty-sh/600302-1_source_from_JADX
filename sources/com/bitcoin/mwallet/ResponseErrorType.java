package com.bitcoin.mwallet;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;

public enum ResponseErrorType implements EnumLite {
    UNKNOWN(0),
    BAD_SIGNATURE(1),
    UNRECOGNIZED(-1);
    
    public static final int BAD_SIGNATURE_VALUE = 1;
    public static final int UNKNOWN_VALUE = 0;
    private static final EnumLiteMap<ResponseErrorType> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<ResponseErrorType>() {
            public ResponseErrorType findValueByNumber(int i) {
                return ResponseErrorType.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ResponseErrorType valueOf(int i) {
        return forNumber(i);
    }

    public static ResponseErrorType forNumber(int i) {
        if (i == 0) {
            return UNKNOWN;
        }
        if (i != 1) {
            return null;
        }
        return BAD_SIGNATURE;
    }

    public static EnumLiteMap<ResponseErrorType> internalGetValueMap() {
        return internalValueMap;
    }

    private ResponseErrorType(int i) {
        this.value = i;
    }
}
