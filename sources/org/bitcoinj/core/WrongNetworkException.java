package org.bitcoinj.core;

import java.util.Arrays;

public class WrongNetworkException extends AddressFormatException {
    public int[] acceptableVersions;
    public int verCode;

    public WrongNetworkException(int i, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("Version code of address did not match acceptable versions for network: ");
        sb.append(i);
        sb.append(" not in ");
        sb.append(Arrays.toString(iArr));
        super(sb.toString());
        this.verCode = i;
        this.acceptableVersions = iArr;
    }
}
