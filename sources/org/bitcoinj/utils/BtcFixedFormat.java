package org.bitcoinj.utils;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public final class BtcFixedFormat extends BtcFormat {
    public static final int[] REPEATING_DOUBLETS = {2, 2, 2, 2, 2, 2, 2};
    public static final int[] REPEATING_PLACES = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    public static final int[] REPEATING_TRIPLETS = {3, 3, 3, 3, 3};
    private final int scale;

    protected BtcFixedFormat(Locale locale, int i, int i2, List<Integer> list) {
        super((DecimalFormat) NumberFormat.getInstance(locale), i2, list);
        boolean z = i <= 8;
        StringBuilder sb = new StringBuilder();
        sb.append("decimal cannot be shifted ");
        sb.append(String.valueOf(i));
        sb.append(" places");
        Preconditions.checkArgument(z, sb.toString());
        this.scale = i;
    }

    /* access modifiers changed from: protected */
    public int scale(BigInteger bigInteger, int i) {
        prefixUnitsIndicator(this.numberFormat, this.scale);
        return this.scale;
    }

    public int scale() {
        return this.scale;
    }

    public String code() {
        return prefixCode(coinCode(), this.scale);
    }

    public String symbol() {
        return prefixSymbol(coinSymbol(), this.scale);
    }

    public int[] fractionPlaceGroups() {
        Object[] array = this.decimalGroups.toArray();
        int length = array.length + 1;
        int[] iArr = new int[length];
        iArr[0] = this.minimumFractionDigits;
        for (int i = 1; i < length; i++) {
            iArr[i] = ((Integer) array[i - 1]).intValue();
        }
        return iArr;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BtcFixedFormat)) {
            return false;
        }
        BtcFixedFormat btcFixedFormat = (BtcFixedFormat) obj;
        if (!super.equals(btcFixedFormat) || btcFixedFormat.scale() != scale() || !btcFixedFormat.decimalGroups.equals(this.decimalGroups)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), Integer.valueOf(this.scale));
    }

    private static String prefixLabel(int i) {
        if (i == -6) {
            return "Megacoin-";
        }
        if (i == 6) {
            return "Microcoin-";
        }
        switch (i) {
            case -3:
                return "Kilocoin-";
            case -2:
                return "Hectocoin-";
            case -1:
                return "Dekacoin-";
            case 0:
                return "Coin-";
            case 1:
                return "Decicoin-";
            case 2:
                return "Centicoin-";
            case 3:
                return "Millicoin-";
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Fixed (");
                sb.append(String.valueOf(i));
                sb.append(") ");
                return sb.toString();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(prefixLabel(this.scale));
        sb.append("format ");
        sb.append(pattern());
        return sb.toString();
    }
}
