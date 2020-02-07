package com.bitcoin.mwallet;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;

public enum ProposalCreationError implements EnumLite {
    UNKNOWN_CREATION_ERROR(0),
    CREATION_INSUFFICIENT_FUNDS(1),
    UNRECOGNIZED(-1);
    
    public static final int CREATION_INSUFFICIENT_FUNDS_VALUE = 1;
    public static final int UNKNOWN_CREATION_ERROR_VALUE = 0;
    private static final EnumLiteMap<ProposalCreationError> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<ProposalCreationError>() {
            public ProposalCreationError findValueByNumber(int i) {
                return ProposalCreationError.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static ProposalCreationError valueOf(int i) {
        return forNumber(i);
    }

    public static ProposalCreationError forNumber(int i) {
        if (i == 0) {
            return UNKNOWN_CREATION_ERROR;
        }
        if (i != 1) {
            return null;
        }
        return CREATION_INSUFFICIENT_FUNDS;
    }

    public static EnumLiteMap<ProposalCreationError> internalGetValueMap() {
        return internalValueMap;
    }

    private ProposalCreationError(int i) {
        this.value = i;
    }
}
