package com.bitcoin.mwallet;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;

public enum MigrationError implements EnumLite {
    UNKNOWN_MIGRATION_ERROR(0),
    ERROR_IMPORTING_FROM_OLD(1),
    NON_EXISTING_WALLET_ID(2),
    UNRECOGNIZED(-1);
    
    public static final int ERROR_IMPORTING_FROM_OLD_VALUE = 1;
    public static final int NON_EXISTING_WALLET_ID_VALUE = 2;
    public static final int UNKNOWN_MIGRATION_ERROR_VALUE = 0;
    private static final EnumLiteMap<MigrationError> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<MigrationError>() {
            public MigrationError findValueByNumber(int i) {
                return MigrationError.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static MigrationError valueOf(int i) {
        return forNumber(i);
    }

    public static MigrationError forNumber(int i) {
        if (i == 0) {
            return UNKNOWN_MIGRATION_ERROR;
        }
        if (i == 1) {
            return ERROR_IMPORTING_FROM_OLD;
        }
        if (i != 2) {
            return null;
        }
        return NON_EXISTING_WALLET_ID;
    }

    public static EnumLiteMap<MigrationError> internalGetValueMap() {
        return internalValueMap;
    }

    private MigrationError(int i) {
        this.value = i;
    }
}
