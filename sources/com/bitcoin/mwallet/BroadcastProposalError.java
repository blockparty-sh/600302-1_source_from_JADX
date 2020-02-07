package com.bitcoin.mwallet;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;

public enum BroadcastProposalError implements EnumLite {
    BROADCAST_PROPOSAL_UNKNOWN_ERROR(0),
    TOO_LONG_MEMPOOL_CHAIN_ERROR(1),
    UNRECOGNIZED(-1);
    
    public static final int BROADCAST_PROPOSAL_UNKNOWN_ERROR_VALUE = 0;
    public static final int TOO_LONG_MEMPOOL_CHAIN_ERROR_VALUE = 1;
    private static final EnumLiteMap<BroadcastProposalError> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<BroadcastProposalError>() {
            public BroadcastProposalError findValueByNumber(int i) {
                return BroadcastProposalError.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static BroadcastProposalError valueOf(int i) {
        return forNumber(i);
    }

    public static BroadcastProposalError forNumber(int i) {
        if (i == 0) {
            return BROADCAST_PROPOSAL_UNKNOWN_ERROR;
        }
        if (i != 1) {
            return null;
        }
        return TOO_LONG_MEMPOOL_CHAIN_ERROR;
    }

    public static EnumLiteMap<BroadcastProposalError> internalGetValueMap() {
        return internalValueMap;
    }

    private BroadcastProposalError(int i) {
        this.value = i;
    }
}
