package org.bitcoinj.utils;

import com.google.common.base.Preconditions;
import com.google.common.math.LongMath;
import java.math.RoundingMode;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Monetary;

public final class MonetaryFormat {
    public static final MonetaryFormat BTC = new MonetaryFormat().shift(0).minDecimals(2).repeatOptionalDecimals(2, 3);
    public static final String CODE_BTC = "BCH";
    public static final String CODE_MBTC = "mBCH";
    public static final String CODE_UBTC = "µBCH";
    private static final String DECIMALS_PADDING = "0000000000000000";
    public static final MonetaryFormat FIAT = new MonetaryFormat().shift(0).minDecimals(2).repeatOptionalDecimals(2, 1);
    public static final int MAX_DECIMALS = 8;
    public static final MonetaryFormat MBTC = new MonetaryFormat().shift(3).minDecimals(2).optionalDecimals(2);
    public static final MonetaryFormat UBTC = new MonetaryFormat().shift(6).minDecimals(0).optionalDecimals(2);
    private final boolean codePrefixed;
    private final char codeSeparator;
    private final String[] codes;
    private final List<Integer> decimalGroups;
    private final char decimalMark;
    private final int minDecimals;
    private final char negativeSign;
    private final char positiveSign;
    private final RoundingMode roundingMode;
    private final int shift;
    private final char zeroDigit;

    public MonetaryFormat negativeSign(char c) {
        boolean z = true;
        Preconditions.checkArgument(!Character.isDigit(c));
        if (c <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (c == this.negativeSign) {
            return this;
        }
        MonetaryFormat monetaryFormat = new MonetaryFormat(c, this.positiveSign, this.zeroDigit, this.decimalMark, this.minDecimals, this.decimalGroups, this.shift, this.roundingMode, this.codes, this.codeSeparator, this.codePrefixed);
        return monetaryFormat;
    }

    public MonetaryFormat positiveSign(char c) {
        Preconditions.checkArgument(!Character.isDigit(c));
        if (c == this.positiveSign) {
            return this;
        }
        MonetaryFormat monetaryFormat = new MonetaryFormat(this.negativeSign, c, this.zeroDigit, this.decimalMark, this.minDecimals, this.decimalGroups, this.shift, this.roundingMode, this.codes, this.codeSeparator, this.codePrefixed);
        return monetaryFormat;
    }

    public MonetaryFormat digits(char c) {
        if (c == this.zeroDigit) {
            return this;
        }
        MonetaryFormat monetaryFormat = new MonetaryFormat(this.negativeSign, this.positiveSign, c, this.decimalMark, this.minDecimals, this.decimalGroups, this.shift, this.roundingMode, this.codes, this.codeSeparator, this.codePrefixed);
        return monetaryFormat;
    }

    public MonetaryFormat decimalMark(char c) {
        boolean z = true;
        Preconditions.checkArgument(!Character.isDigit(c));
        if (c <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (c == this.decimalMark) {
            return this;
        }
        MonetaryFormat monetaryFormat = new MonetaryFormat(this.negativeSign, this.positiveSign, this.zeroDigit, c, this.minDecimals, this.decimalGroups, this.shift, this.roundingMode, this.codes, this.codeSeparator, this.codePrefixed);
        return monetaryFormat;
    }

    public MonetaryFormat minDecimals(int i) {
        if (i == this.minDecimals) {
            return this;
        }
        MonetaryFormat monetaryFormat = new MonetaryFormat(this.negativeSign, this.positiveSign, this.zeroDigit, this.decimalMark, i, this.decimalGroups, this.shift, this.roundingMode, this.codes, this.codeSeparator, this.codePrefixed);
        return monetaryFormat;
    }

    public MonetaryFormat optionalDecimals(int... iArr) {
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int valueOf : iArr) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        MonetaryFormat monetaryFormat = new MonetaryFormat(this.negativeSign, this.positiveSign, this.zeroDigit, this.decimalMark, this.minDecimals, arrayList, this.shift, this.roundingMode, this.codes, this.codeSeparator, this.codePrefixed);
        return monetaryFormat;
    }

    public MonetaryFormat repeatOptionalDecimals(int i, int i2) {
        int i3 = i2;
        Preconditions.checkArgument(i3 >= 0);
        ArrayList arrayList = new ArrayList(i3);
        for (int i4 = 0; i4 < i3; i4++) {
            arrayList.add(Integer.valueOf(i));
        }
        MonetaryFormat monetaryFormat = new MonetaryFormat(this.negativeSign, this.positiveSign, this.zeroDigit, this.decimalMark, this.minDecimals, arrayList, this.shift, this.roundingMode, this.codes, this.codeSeparator, this.codePrefixed);
        return monetaryFormat;
    }

    public MonetaryFormat shift(int i) {
        if (i == this.shift) {
            return this;
        }
        MonetaryFormat monetaryFormat = new MonetaryFormat(this.negativeSign, this.positiveSign, this.zeroDigit, this.decimalMark, this.minDecimals, this.decimalGroups, i, this.roundingMode, this.codes, this.codeSeparator, this.codePrefixed);
        return monetaryFormat;
    }

    public MonetaryFormat roundingMode(RoundingMode roundingMode2) {
        if (roundingMode2 == this.roundingMode) {
            return this;
        }
        MonetaryFormat monetaryFormat = new MonetaryFormat(this.negativeSign, this.positiveSign, this.zeroDigit, this.decimalMark, this.minDecimals, this.decimalGroups, this.shift, roundingMode2, this.codes, this.codeSeparator, this.codePrefixed);
        return monetaryFormat;
    }

    public MonetaryFormat noCode() {
        if (this.codes == null) {
            return this;
        }
        MonetaryFormat monetaryFormat = new MonetaryFormat(this.negativeSign, this.positiveSign, this.zeroDigit, this.decimalMark, this.minDecimals, this.decimalGroups, this.shift, this.roundingMode, null, this.codeSeparator, this.codePrefixed);
        return monetaryFormat;
    }

    public MonetaryFormat code(int i, String str) {
        String[] strArr;
        Preconditions.checkArgument(i >= 0);
        String[] strArr2 = this.codes;
        if (strArr2 == null) {
            strArr = new String[8];
        } else {
            strArr = (String[]) Arrays.copyOf(strArr2, strArr2.length);
        }
        String[] strArr3 = strArr;
        strArr3[i] = str;
        MonetaryFormat monetaryFormat = new MonetaryFormat(this.negativeSign, this.positiveSign, this.zeroDigit, this.decimalMark, this.minDecimals, this.decimalGroups, this.shift, this.roundingMode, strArr3, this.codeSeparator, this.codePrefixed);
        return monetaryFormat;
    }

    public MonetaryFormat codeSeparator(char c) {
        boolean z = true;
        Preconditions.checkArgument(!Character.isDigit(c));
        if (c <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (c == this.codeSeparator) {
            return this;
        }
        MonetaryFormat monetaryFormat = new MonetaryFormat(this.negativeSign, this.positiveSign, this.zeroDigit, this.decimalMark, this.minDecimals, this.decimalGroups, this.shift, this.roundingMode, this.codes, c, this.codePrefixed);
        return monetaryFormat;
    }

    public MonetaryFormat prefixCode() {
        if (this.codePrefixed) {
            return this;
        }
        MonetaryFormat monetaryFormat = new MonetaryFormat(this.negativeSign, this.positiveSign, this.zeroDigit, this.decimalMark, this.minDecimals, this.decimalGroups, this.shift, this.roundingMode, this.codes, this.codeSeparator, true);
        return monetaryFormat;
    }

    public MonetaryFormat postfixCode() {
        if (!this.codePrefixed) {
            return this;
        }
        MonetaryFormat monetaryFormat = new MonetaryFormat(this.negativeSign, this.positiveSign, this.zeroDigit, this.decimalMark, this.minDecimals, this.decimalGroups, this.shift, this.roundingMode, this.codes, this.codeSeparator, false);
        return monetaryFormat;
    }

    public MonetaryFormat withLocale(Locale locale) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(locale);
        MonetaryFormat monetaryFormat = new MonetaryFormat(decimalFormatSymbols.getMinusSign(), this.positiveSign, decimalFormatSymbols.getZeroDigit(), decimalFormatSymbols.getMonetaryDecimalSeparator(), this.minDecimals, this.decimalGroups, this.shift, this.roundingMode, this.codes, this.codeSeparator, this.codePrefixed);
        return monetaryFormat;
    }

    public MonetaryFormat() {
        this.negativeSign = '-';
        this.positiveSign = 0;
        this.zeroDigit = '0';
        this.decimalMark = '.';
        this.minDecimals = 2;
        this.decimalGroups = null;
        this.shift = 0;
        this.roundingMode = RoundingMode.HALF_UP;
        this.codes = new String[8];
        String[] strArr = this.codes;
        strArr[0] = CODE_BTC;
        strArr[3] = CODE_MBTC;
        strArr[6] = CODE_UBTC;
        this.codeSeparator = ' ';
        this.codePrefixed = true;
    }

    private MonetaryFormat(char c, char c2, char c3, char c4, int i, List<Integer> list, int i2, RoundingMode roundingMode2, String[] strArr, char c5, boolean z) {
        this.negativeSign = c;
        this.positiveSign = c2;
        this.zeroDigit = c3;
        this.decimalMark = c4;
        this.minDecimals = i;
        this.decimalGroups = list;
        this.shift = i2;
        this.roundingMode = roundingMode2;
        this.codes = strArr;
        this.codeSeparator = c5;
        this.codePrefixed = z;
    }

    public CharSequence format(Monetary monetary) {
        int i = this.minDecimals;
        List<Integer> list = this.decimalGroups;
        if (list != null) {
            for (Integer intValue : list) {
                i += intValue.intValue();
            }
        }
        int smallestUnitExponent = monetary.smallestUnitExponent();
        Preconditions.checkState(i <= smallestUnitExponent, "The maximum possible number of decimals (%s) cannot exceed %s.", Integer.valueOf(i), Integer.valueOf(smallestUnitExponent));
        long abs = Math.abs(monetary.getValue());
        long checkedPow = LongMath.checkedPow(10, (smallestUnitExponent - this.shift) - i);
        long checkedMultiply = LongMath.checkedMultiply(LongMath.divide(abs, checkedPow, this.roundingMode), checkedPow);
        long checkedPow2 = LongMath.checkedPow(10, smallestUnitExponent - this.shift);
        long j = checkedMultiply / checkedPow2;
        long j2 = checkedMultiply % checkedPow2;
        Locale locale = Locale.US;
        StringBuilder sb = new StringBuilder();
        sb.append("%0");
        sb.append(smallestUnitExponent - this.shift);
        sb.append("d");
        StringBuilder sb2 = new StringBuilder(String.format(locale, sb.toString(), new Object[]{Long.valueOf(j2)}));
        while (sb2.length() > this.minDecimals && sb2.charAt(sb2.length() - 1) == '0') {
            sb2.setLength(sb2.length() - 1);
        }
        int i2 = this.minDecimals;
        List<Integer> list2 = this.decimalGroups;
        if (list2 != null) {
            Iterator it = list2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                int intValue2 = ((Integer) it.next()).intValue();
                if (sb2.length() > i2) {
                    int i3 = i2 + intValue2;
                    if (sb2.length() < i3) {
                        while (sb2.length() < i3) {
                            sb2.append('0');
                        }
                    }
                }
                i2 += intValue2;
            }
        }
        if (sb2.length() > 0) {
            sb2.insert(0, this.decimalMark);
        }
        sb2.insert(0, j);
        if (monetary.getValue() < 0) {
            sb2.insert(0, this.negativeSign);
        } else {
            char c = this.positiveSign;
            if (c != 0) {
                sb2.insert(0, c);
            }
        }
        if (this.codes != null) {
            if (this.codePrefixed) {
                sb2.insert(0, this.codeSeparator);
                sb2.insert(0, code());
            } else {
                sb2.append(this.codeSeparator);
                sb2.append(code());
            }
        }
        char c2 = this.zeroDigit;
        if (c2 != '0') {
            int i4 = c2 - '0';
            for (int i5 = 0; i5 < sb2.length(); i5++) {
                char charAt = sb2.charAt(i5);
                if (Character.isDigit(charAt)) {
                    sb2.setCharAt(i5, (char) (charAt + i4));
                }
            }
        }
        return sb2;
    }

    public Coin parse(String str) throws NumberFormatException {
        return Coin.valueOf(parseValue(str, 8));
    }

    public Fiat parseFiat(String str, String str2) throws NumberFormatException {
        return Fiat.valueOf(str, parseValue(str2, 4));
    }

    private long parseValue(String str, int i) {
        int i2 = 0;
        Preconditions.checkState(16 >= i);
        if (!str.isEmpty()) {
            char charAt = str.charAt(0);
            if (charAt == this.negativeSign || charAt == this.positiveSign) {
                str = str.substring(1);
            }
            int indexOf = str.indexOf(this.decimalMark);
            String str2 = DECIMALS_PADDING;
            if (indexOf != -1) {
                String substring = str.substring(0, indexOf);
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(str2);
                str2 = sb.toString().substring(indexOf + 1);
                if (str2.indexOf(this.decimalMark) == -1) {
                    str = substring;
                } else {
                    throw new NumberFormatException("more than one decimal mark");
                }
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(str2.substring(0, i - this.shift));
            String sb3 = sb2.toString();
            char[] charArray = sb3.toCharArray();
            int length = charArray.length;
            while (i2 < length) {
                char c = charArray[i2];
                if (Character.isDigit(c)) {
                    i2++;
                } else {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("illegal character: ");
                    sb4.append(c);
                    throw new NumberFormatException(sb4.toString());
                }
            }
            long parseLong = Long.parseLong(sb3);
            return charAt == this.negativeSign ? -parseLong : parseLong;
        }
        throw new NumberFormatException("empty string");
    }

    public String code() {
        String[] strArr = this.codes;
        if (strArr == null) {
            return null;
        }
        int i = this.shift;
        if (strArr[i] != null) {
            return strArr[i];
        }
        StringBuilder sb = new StringBuilder();
        sb.append("missing code for shift: ");
        sb.append(this.shift);
        throw new NumberFormatException(sb.toString());
    }
}
