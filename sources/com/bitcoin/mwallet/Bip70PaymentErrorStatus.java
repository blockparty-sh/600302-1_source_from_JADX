package com.bitcoin.mwallet;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;

public enum Bip70PaymentErrorStatus implements EnumLite {
    UNKNOWN_PAYMENT_STATUS(0),
    INVALID_PAYMENT(1),
    EXPIRED_PAYMENT(2),
    UNRECOGNIZED(-1);
    
    public static final int EXPIRED_PAYMENT_VALUE = 2;
    public static final int INVALID_PAYMENT_VALUE = 1;
    public static final int UNKNOWN_PAYMENT_STATUS_VALUE = 0;
    private static final EnumLiteMap<Bip70PaymentErrorStatus> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<Bip70PaymentErrorStatus>() {
            public Bip70PaymentErrorStatus findValueByNumber(int i) {
                return Bip70PaymentErrorStatus.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static Bip70PaymentErrorStatus valueOf(int i) {
        return forNumber(i);
    }

    public static Bip70PaymentErrorStatus forNumber(int i) {
        if (i == 0) {
            return UNKNOWN_PAYMENT_STATUS;
        }
        if (i == 1) {
            return INVALID_PAYMENT;
        }
        if (i != 2) {
            return null;
        }
        return EXPIRED_PAYMENT;
    }

    public static EnumLiteMap<Bip70PaymentErrorStatus> internalGetValueMap() {
        return internalValueMap;
    }

    private Bip70PaymentErrorStatus(int i) {
        this.value = i;
    }
}
