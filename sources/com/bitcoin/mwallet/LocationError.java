package com.bitcoin.mwallet;

import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;

public enum LocationError implements EnumLite {
    UNKNOWN_LOCATION_ERROR(0),
    BUY_BITCOIN_ERROR(1),
    GEOLOCATION_API_ERROR(2),
    UNRECOGNIZED(-1);
    
    public static final int BUY_BITCOIN_ERROR_VALUE = 1;
    public static final int GEOLOCATION_API_ERROR_VALUE = 2;
    public static final int UNKNOWN_LOCATION_ERROR_VALUE = 0;
    private static final EnumLiteMap<LocationError> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new EnumLiteMap<LocationError>() {
            public LocationError findValueByNumber(int i) {
                return LocationError.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static LocationError valueOf(int i) {
        return forNumber(i);
    }

    public static LocationError forNumber(int i) {
        if (i == 0) {
            return UNKNOWN_LOCATION_ERROR;
        }
        if (i == 1) {
            return BUY_BITCOIN_ERROR;
        }
        if (i != 2) {
            return null;
        }
        return GEOLOCATION_API_ERROR;
    }

    public static EnumLiteMap<LocationError> internalGetValueMap() {
        return internalValueMap;
    }

    private LocationError(int i) {
        this.value = i;
    }
}
