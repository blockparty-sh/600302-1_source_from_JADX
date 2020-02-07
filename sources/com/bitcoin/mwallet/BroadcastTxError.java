package com.bitcoin.mwallet;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;

public enum BroadcastTxError implements EnumLite {
    BROADCAST_UNKNOWN_ERROR(0),
    TOO_LONG_MEMPOOL_CHAIN(1),
    UNRECOGNIZED(-1);
    
    public static final int BROADCAST_UNKNOWN_ERROR_VALUE = 0;
    public static final int TOO_LONG_MEMPOOL_CHAIN_VALUE = 1;
    private static final EnumLiteMap<BroadcastTxError> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<BroadcastTxError>() {
            public BroadcastTxError findValueByNumber(int i) {
                return BroadcastTxError.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static BroadcastTxError valueOf(int i) {
        return forNumber(i);
    }

    public static BroadcastTxError forNumber(int i) {
        if (i == 0) {
            return BROADCAST_UNKNOWN_ERROR;
        }
        if (i != 1) {
            return null;
        }
        return TOO_LONG_MEMPOOL_CHAIN;
    }

    public static EnumLiteMap<BroadcastTxError> internalGetValueMap() {
        return internalValueMap;
    }

    private BroadcastTxError(int i) {
        this.value = i;
    }
}
