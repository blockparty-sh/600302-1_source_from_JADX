package org.bitcoinj.utils;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.AttributedCharacterIterator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.cli.HelpFormatter;
import org.bitcoinj.core.Coin;
import org.bitcoinj.utils.BtcAutoFormat.Style;

public abstract class BtcFormat extends Format {
    private static final String COIN_CODE = "BTC";
    public static final int COIN_SCALE = 0;
    private static final String COIN_SYMBOL = "฿";
    protected static final String COIN_SYMBOL_ALT = "Ƀ";
    public static final int MICROCOIN_SCALE = 6;
    public static final int MILLICOIN_SCALE = 3;

    /* renamed from: ci */
    private volatile String f820ci = "(฿|Ƀ|B⃦|BTC|XBT)";
    private Pattern coinPattern;
    protected final List<Integer> decimalGroups;
    private volatile ScaleMatcher[] denoms;
    protected final int minimumFractionDigits;
    protected final DecimalFormat numberFormat;

    public static class Builder {
        private String code;
        /* access modifiers changed from: private */
        public int[] fractionGroups;
        /* access modifiers changed from: private */
        public Locale locale;
        private String localizedPattern;
        /* access modifiers changed from: private */
        public int minimumFractionDigits;
        private String pattern;
        /* access modifiers changed from: private */
        public int scale;
        /* access modifiers changed from: private */
        public Style style;
        private String symbol;
        private Variant variant;

        private enum Variant {
            AUTO {
                /* access modifiers changed from: 0000 */
                public BtcFormat newInstance(Builder builder) {
                    return BtcFormat.getInstance(builder.style, builder.locale, builder.minimumFractionDigits);
                }
            },
            FIXED,
            UNSET;

            /* access modifiers changed from: 0000 */
            public BtcFormat newInstance(Builder builder) {
                return BtcFormat.getInstance(builder.scale, builder.locale, builder.minimumFractionDigits, builder.fractionGroups);
            }
        }

        private Builder() {
            this.variant = Variant.UNSET;
            this.locale = BtcFormat.defaultLocale();
            this.minimumFractionDigits = 2;
            this.fractionGroups = new int[0];
            this.style = Style.CODE;
            this.scale = 0;
            String str = "";
            this.symbol = str;
            this.code = str;
            this.pattern = str;
            this.localizedPattern = str;
        }

        public Builder style(Style style2) {
            if (this.variant != Variant.FIXED) {
                this.variant = Variant.AUTO;
                this.style = style2;
                return this;
            }
            throw new IllegalStateException("You cannot invoke both style() and scale()");
        }

        public Builder fractionDigits(int i) {
            return minimumFractionDigits(i);
        }

        public Builder scale(int i) {
            if (this.variant != Variant.AUTO) {
                this.variant = Variant.FIXED;
                this.scale = i;
                return this;
            }
            throw new IllegalStateException("You cannot invoke both scale() and style()");
        }

        public Builder minimumFractionDigits(int i) {
            this.minimumFractionDigits = i;
            return this;
        }

        public Builder fractionGroups(int... iArr) {
            this.fractionGroups = iArr;
            return this;
        }

        public Builder locale(Locale locale2) {
            this.locale = locale2;
            return this;
        }

        public Builder symbol(String str) {
            this.symbol = str;
            return this;
        }

        public Builder code(String str) {
            this.code = str;
            return this;
        }

        public Builder pattern(String str) {
            if (this.localizedPattern == "") {
                this.pattern = str;
                return this;
            }
            throw new IllegalStateException("You cannot invoke both pattern() and localizedPattern()");
        }

        public Builder localizedPattern(String str) {
            if (this.pattern == "") {
                this.localizedPattern = str;
                return this;
            }
            throw new IllegalStateException("You cannot invoke both pattern() and localizedPattern().");
        }

        public BtcFormat build() {
            String str;
            String str2;
            BtcFormat newInstance = this.variant.newInstance(this);
            if (!(this.symbol == "" && this.code == "")) {
                synchronized (newInstance.numberFormat) {
                    DecimalFormatSymbols decimalFormatSymbols = newInstance.numberFormat.getDecimalFormatSymbols();
                    DecimalFormat decimalFormat = newInstance.numberFormat;
                    if (this.symbol != "") {
                        str = this.symbol;
                    } else {
                        str = decimalFormatSymbols.getCurrencySymbol();
                    }
                    if (this.code != "") {
                        str2 = this.code;
                    } else {
                        str2 = decimalFormatSymbols.getInternationalCurrencySymbol();
                    }
                    BtcFormat.setSymbolAndCode(decimalFormat, str, str2);
                }
            }
            if (!(this.localizedPattern == "" && this.pattern == "")) {
                int minimumFractionDigits2 = newInstance.numberFormat.getMinimumFractionDigits();
                if (this.localizedPattern != "") {
                    newInstance.numberFormat.applyLocalizedPattern(BtcFormat.negify(this.localizedPattern));
                } else {
                    newInstance.numberFormat.applyPattern(BtcFormat.negify(this.pattern));
                }
                newInstance.numberFormat.setMinimumFractionDigits(minimumFractionDigits2);
                newInstance.numberFormat.setMaximumFractionDigits(minimumFractionDigits2);
            }
            return newInstance;
        }
    }

    private class ScaleMatcher {
        public Pattern pattern;
        public int scale;

        ScaleMatcher(Pattern pattern2, int i) {
            this.pattern = pattern2;
            this.scale = i;
        }
    }

    private static int offSatoshis(int i) {
        return 8 - i;
    }

    /* access modifiers changed from: protected */
    public abstract int scale();

    /* access modifiers changed from: protected */
    public abstract int scale(BigInteger bigInteger, int i);

    /* access modifiers changed from: private */
    public static Locale defaultLocale() {
        return Locale.getDefault();
    }

    public static Builder builder() {
        return new Builder();
    }

    protected BtcFormat(DecimalFormat decimalFormat, int i, List<Integer> list) {
        Preconditions.checkArgument(i >= 0, "There can be no fewer than zero fractional decimal places");
        this.numberFormat = decimalFormat;
        this.numberFormat.setParseBigDecimal(true);
        this.numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        this.minimumFractionDigits = i;
        this.numberFormat.setMinimumFractionDigits(this.minimumFractionDigits);
        this.numberFormat.setMaximumFractionDigits(this.minimumFractionDigits);
        this.decimalGroups = list;
        synchronized (this.numberFormat) {
            setSymbolAndCode(this.numberFormat, this.numberFormat.getDecimalFormatSymbols().getCurrencySymbol().contains(COIN_SYMBOL) ? COIN_SYMBOL_ALT : COIN_SYMBOL, COIN_CODE);
        }
    }

    public static BtcFormat getInstance() {
        return getInstance(defaultLocale());
    }

    public static BtcFormat getSymbolInstance() {
        return getSymbolInstance(defaultLocale());
    }

    public static BtcFormat getCodeInstance() {
        return getCodeInstance(defaultLocale());
    }

    public static BtcFormat getSymbolInstance(int i) {
        return getSymbolInstance(defaultLocale(), i);
    }

    public static BtcFormat getCodeInstance(int i) {
        return getCodeInstance(defaultLocale(), i);
    }

    public static BtcFormat getInstance(Locale locale) {
        return getCodeInstance(locale);
    }

    public static BtcFormat getCodeInstance(Locale locale) {
        return getInstance(Style.CODE, locale);
    }

    public static BtcFormat getInstance(Locale locale, int i) {
        return getCodeInstance(locale, i);
    }

    public static BtcFormat getCodeInstance(Locale locale, int i) {
        return getInstance(Style.CODE, locale, i);
    }

    public static BtcFormat getSymbolInstance(Locale locale) {
        return getInstance(Style.SYMBOL, locale);
    }

    public static BtcFormat getSymbolInstance(Locale locale, int i) {
        return getInstance(Style.SYMBOL, locale, i);
    }

    public static BtcFormat getInstance(Style style) {
        return getInstance(style, defaultLocale());
    }

    public static BtcFormat getInstance(Style style, int i) {
        return getInstance(style, defaultLocale(), i);
    }

    public static BtcFormat getInstance(Style style, Locale locale) {
        return getInstance(style, locale, 2);
    }

    public static BtcFormat getInstance(Style style, Locale locale, int i) {
        return new BtcAutoFormat(locale, style, i);
    }

    public static BtcFormat getCoinInstance() {
        return getCoinInstance(defaultLocale());
    }

    private static List<Integer> boxAsList(int[] iArr) throws IllegalArgumentException {
        ArrayList arrayList = new ArrayList(iArr.length);
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = iArr[i];
            Preconditions.checkArgument(i2 > 0, "Size of decimal group must be at least one.");
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }

    public static BtcFormat getCoinInstance(int i, int... iArr) {
        return getInstance(0, defaultLocale(), i, boxAsList(iArr));
    }

    public static BtcFormat getCoinInstance(Locale locale) {
        return getInstance(0, locale, 2, new int[0]);
    }

    public static BtcFormat getCoinInstance(Locale locale, int i, int... iArr) {
        return getInstance(0, locale, i, boxAsList(iArr));
    }

    public static BtcFormat getMilliInstance() {
        return getMilliInstance(defaultLocale());
    }

    public static BtcFormat getMilliInstance(Locale locale) {
        return getInstance(3, locale, 2, new int[0]);
    }

    public static BtcFormat getMilliInstance(int i, int... iArr) {
        return getInstance(3, defaultLocale(), i, boxAsList(iArr));
    }

    public static BtcFormat getMilliInstance(Locale locale, int i, int... iArr) {
        return getInstance(3, locale, i, boxAsList(iArr));
    }

    public static BtcFormat getMicroInstance() {
        return getMicroInstance(defaultLocale());
    }

    public static BtcFormat getMicroInstance(Locale locale) {
        return getInstance(6, locale);
    }

    public static BtcFormat getMicroInstance(int i, int... iArr) {
        return getInstance(6, defaultLocale(), i, boxAsList(iArr));
    }

    public static BtcFormat getMicroInstance(Locale locale, int i, int... iArr) {
        return getInstance(6, locale, i, boxAsList(iArr));
    }

    public static BtcFormat getInstance(int i, int i2, int... iArr) {
        return getInstance(i, defaultLocale(), i2, boxAsList(iArr));
    }

    public static BtcFormat getInstance(int i) {
        return getInstance(i, defaultLocale());
    }

    public static BtcFormat getInstance(int i, Locale locale) {
        return getInstance(i, locale, 2, new int[0]);
    }

    public static BtcFormat getInstance(int i, Locale locale, int i2, int... iArr) {
        return getInstance(i, locale, i2, boxAsList(iArr));
    }

    public static BtcFormat getInstance(int i, Locale locale, int i2, List<Integer> list) {
        return new BtcFixedFormat(locale, i, i2, list);
    }

    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        AttributedCharacterIterator formatToCharacterIterator;
        synchronized (this.numberFormat) {
            DecimalFormatSymbols decimalFormatSymbols = this.numberFormat.getDecimalFormatSymbols();
            BigDecimal denominateAndRound = denominateAndRound(inSatoshis(obj), this.minimumFractionDigits, this.decimalGroups);
            ImmutableList formatterDigits = setFormatterDigits(this.numberFormat, denominateAndRound.scale(), denominateAndRound.scale());
            formatToCharacterIterator = this.numberFormat.formatToCharacterIterator(denominateAndRound);
            this.numberFormat.setDecimalFormatSymbols(decimalFormatSymbols);
            setFormatterDigits(this.numberFormat, ((Integer) formatterDigits.get(0)).intValue(), ((Integer) formatterDigits.get(1)).intValue());
        }
        return formatToCharacterIterator;
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return format(obj, stringBuffer, fieldPosition, this.minimumFractionDigits, this.decimalGroups);
    }

    public String format(Object obj, int i, int... iArr) {
        return format(obj, new StringBuffer(), new FieldPosition(0), i, boxAsList(iArr)).toString();
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition, int i, int... iArr) {
        return format(obj, stringBuffer, fieldPosition, i, boxAsList(iArr));
    }

    private StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition, int i, List<Integer> list) {
        StringBuffer format;
        Preconditions.checkArgument(i >= 0, "There can be no fewer than zero fractional decimal places");
        synchronized (this.numberFormat) {
            DecimalFormatSymbols decimalFormatSymbols = this.numberFormat.getDecimalFormatSymbols();
            BigDecimal denominateAndRound = denominateAndRound(inSatoshis(obj), i, list);
            ImmutableList formatterDigits = setFormatterDigits(this.numberFormat, denominateAndRound.scale(), denominateAndRound.scale());
            format = this.numberFormat.format(denominateAndRound, stringBuffer, fieldPosition);
            this.numberFormat.setDecimalFormatSymbols(decimalFormatSymbols);
            setFormatterDigits(this.numberFormat, ((Integer) formatterDigits.get(0)).intValue(), ((Integer) formatterDigits.get(1)).intValue());
        }
        return format;
    }

    private BigDecimal denominateAndRound(BigInteger bigInteger, int i, List<Integer> list) {
        int scale = scale(bigInteger, i);
        BigDecimal movePointLeft = new BigDecimal(bigInteger).movePointLeft(offSatoshis(scale));
        return movePointLeft.setScale(calculateFractionPlaces(movePointLeft, scale, i, list), RoundingMode.HALF_UP);
    }

    private static ImmutableList<Integer> setFormatterDigits(DecimalFormat decimalFormat, int i, int i2) {
        ImmutableList<Integer> of = ImmutableList.m128of(Integer.valueOf(decimalFormat.getMinimumFractionDigits()), Integer.valueOf(decimalFormat.getMaximumFractionDigits()));
        decimalFormat.setMinimumFractionDigits(i);
        decimalFormat.setMaximumFractionDigits(i2);
        return of;
    }

    private static int calculateFractionPlaces(BigDecimal bigDecimal, int i, int i2, List<Integer> list) {
        int i3 = i2;
        for (Integer intValue : list) {
            i3 += intValue.intValue();
        }
        int min = Math.min(i3, offSatoshis(i));
        int min2 = Math.min(i2, min);
        for (Integer intValue2 : list) {
            int intValue3 = intValue2.intValue();
            if (bigDecimal.setScale(min2, RoundingMode.HALF_UP).compareTo(bigDecimal.setScale(min, RoundingMode.HALF_UP)) == 0) {
                break;
            }
            min2 += intValue3;
            if (min2 > min) {
                min2 = min;
            }
        }
        return min2;
    }

    private static BigInteger inSatoshis(Object obj) {
        if ((obj instanceof Long) || (obj instanceof Integer)) {
            return BigInteger.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).movePointRight(8).setScale(0, 4).unscaledValue();
        }
        if (obj instanceof Coin) {
            return BigInteger.valueOf(((Coin) obj).value);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot format a ");
        sb.append(obj.getClass().getSimpleName());
        sb.append(" as a Bicoin value");
        throw new IllegalArgumentException(sb.toString());
    }

    public final Object parseObject(String str, ParsePosition parsePosition) {
        return parse(str, parsePosition);
    }

    /* access modifiers changed from: 0000 */
    public ScaleMatcher[] denomMatchers() {
        ScaleMatcher[] scaleMatcherArr = this.denoms;
        if (scaleMatcherArr == null) {
            synchronized (this) {
                scaleMatcherArr = this.denoms;
                if (scaleMatcherArr == null) {
                    if (!coinSymbol().matches(this.f820ci)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("(");
                        sb.append(coinSymbol());
                        sb.append("|");
                        this.f820ci = this.f820ci.replaceFirst("\\(", sb.toString());
                    }
                    if (!coinCode().matches(this.f820ci)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("|");
                        sb2.append(coinCode());
                        sb2.append(")");
                        this.f820ci = this.f820ci.replaceFirst("\\)", sb2.toString());
                    }
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(this.f820ci);
                    sb3.append("?");
                    this.coinPattern = Pattern.compile(sb3.toString());
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("¢");
                    sb4.append(this.f820ci);
                    sb4.append("?|c");
                    sb4.append(this.f820ci);
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("₥");
                    sb5.append(this.f820ci);
                    sb5.append("?|m");
                    sb5.append(this.f820ci);
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("([µu]");
                    sb6.append(this.f820ci);
                    sb6.append(")");
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("(da");
                    sb7.append(this.f820ci);
                    sb7.append(")");
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append("(h");
                    sb8.append(this.f820ci);
                    sb8.append(")");
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append("(k");
                    sb9.append(this.f820ci);
                    sb9.append(")");
                    StringBuilder sb10 = new StringBuilder();
                    sb10.append("(M");
                    sb10.append(this.f820ci);
                    sb10.append(")");
                    scaleMatcherArr = new ScaleMatcher[]{new ScaleMatcher(Pattern.compile(sb4.toString()), 2), new ScaleMatcher(Pattern.compile(sb5.toString()), 3), new ScaleMatcher(Pattern.compile(sb6.toString()), 6), new ScaleMatcher(Pattern.compile(sb7.toString()), -1), new ScaleMatcher(Pattern.compile(sb8.toString()), -2), new ScaleMatcher(Pattern.compile(sb9.toString()), -3), new ScaleMatcher(Pattern.compile(sb10.toString()), -6)};
                    this.denoms = scaleMatcherArr;
                }
            }
        }
        return scaleMatcherArr;
    }

    private static DecimalFormatSymbols setSymbolAndCode(DecimalFormat decimalFormat, String str) {
        return setSymbolAndCode(decimalFormat, str, str);
    }

    /* access modifiers changed from: private */
    public static DecimalFormatSymbols setSymbolAndCode(DecimalFormat decimalFormat, String str, String str2) {
        Preconditions.checkState(Thread.holdsLock(decimalFormat));
        DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();
        DecimalFormatSymbols decimalFormatSymbols2 = (DecimalFormatSymbols) decimalFormatSymbols.clone();
        decimalFormatSymbols.setInternationalCurrencySymbol(str2);
        decimalFormatSymbols.setCurrencySymbol(str);
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        return decimalFormatSymbols2;
    }

    protected static void prefixUnitsIndicator(DecimalFormat decimalFormat, int i) {
        Preconditions.checkState(Thread.holdsLock(decimalFormat));
        DecimalFormatSymbols decimalFormatSymbols = decimalFormat.getDecimalFormatSymbols();
        setSymbolAndCode(decimalFormat, prefixSymbol(decimalFormatSymbols.getCurrencySymbol(), i), prefixCode(decimalFormatSymbols.getInternationalCurrencySymbol(), i));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:19|20) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r11.setIndex(0);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0077 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.bitcoinj.core.Coin parse(java.lang.String r10, java.text.ParsePosition r11) {
        /*
            r9 = this;
            java.text.DecimalFormat r0 = r9.numberFormat
            monitor-enter(r0)
            java.text.DecimalFormat r1 = r9.numberFormat     // Catch:{ all -> 0x0083 }
            java.lang.String r1 = r1.toPattern()     // Catch:{ all -> 0x0083 }
            java.lang.String r2 = "¤"
            boolean r1 = r1.contains(r2)     // Catch:{ all -> 0x0083 }
            r2 = 0
            r3 = 0
            if (r1 == 0) goto L_0x0051
            org.bitcoinj.utils.BtcFormat$ScaleMatcher[] r1 = r9.denomMatchers()     // Catch:{ all -> 0x0083 }
            int r4 = r1.length     // Catch:{ all -> 0x0083 }
            r5 = 0
        L_0x0019:
            if (r5 >= r4) goto L_0x0039
            r6 = r1[r5]     // Catch:{ all -> 0x0083 }
            java.util.regex.Pattern r7 = r6.pattern     // Catch:{ all -> 0x0083 }
            java.util.regex.Matcher r7 = r7.matcher(r10)     // Catch:{ all -> 0x0083 }
            boolean r8 = r7.find()     // Catch:{ all -> 0x0083 }
            if (r8 == 0) goto L_0x0036
            java.text.DecimalFormat r1 = r9.numberFormat     // Catch:{ all -> 0x0083 }
            java.lang.String r4 = r7.group()     // Catch:{ all -> 0x0083 }
            java.text.DecimalFormatSymbols r1 = setSymbolAndCode(r1, r4)     // Catch:{ all -> 0x0083 }
            int r4 = r6.scale     // Catch:{ all -> 0x0083 }
            goto L_0x003b
        L_0x0036:
            int r5 = r5 + 1
            goto L_0x0019
        L_0x0039:
            r1 = r2
            r4 = 0
        L_0x003b:
            if (r4 != 0) goto L_0x0056
            java.util.regex.Pattern r1 = r9.coinPattern     // Catch:{ all -> 0x0083 }
            java.util.regex.Matcher r1 = r1.matcher(r10)     // Catch:{ all -> 0x0083 }
            r1.find()     // Catch:{ all -> 0x0083 }
            java.text.DecimalFormat r5 = r9.numberFormat     // Catch:{ all -> 0x0083 }
            java.lang.String r1 = r1.group()     // Catch:{ all -> 0x0083 }
            java.text.DecimalFormatSymbols r1 = setSymbolAndCode(r5, r1)     // Catch:{ all -> 0x0083 }
            goto L_0x0056
        L_0x0051:
            int r4 = r9.scale()     // Catch:{ all -> 0x0083 }
            r1 = r2
        L_0x0056:
            java.text.DecimalFormat r5 = r9.numberFormat     // Catch:{ all -> 0x0083 }
            java.lang.Number r10 = r5.parse(r10, r11)     // Catch:{ all -> 0x0083 }
            if (r10 == 0) goto L_0x007a
            java.math.BigDecimal r10 = (java.math.BigDecimal) r10     // Catch:{ IllegalArgumentException -> 0x0077 }
            int r4 = offSatoshis(r4)     // Catch:{ IllegalArgumentException -> 0x0077 }
            java.math.BigDecimal r10 = r10.movePointRight(r4)     // Catch:{ IllegalArgumentException -> 0x0077 }
            java.math.RoundingMode r4 = java.math.RoundingMode.HALF_UP     // Catch:{ IllegalArgumentException -> 0x0077 }
            java.math.BigDecimal r10 = r10.setScale(r3, r4)     // Catch:{ IllegalArgumentException -> 0x0077 }
            long r4 = r10.longValue()     // Catch:{ IllegalArgumentException -> 0x0077 }
            org.bitcoinj.core.Coin r2 = org.bitcoinj.core.Coin.valueOf(r4)     // Catch:{ IllegalArgumentException -> 0x0077 }
            goto L_0x007a
        L_0x0077:
            r11.setIndex(r3)     // Catch:{ all -> 0x0083 }
        L_0x007a:
            if (r1 == 0) goto L_0x0081
            java.text.DecimalFormat r10 = r9.numberFormat     // Catch:{ all -> 0x0083 }
            r10.setDecimalFormatSymbols(r1)     // Catch:{ all -> 0x0083 }
        L_0x0081:
            monitor-exit(r0)     // Catch:{ all -> 0x0083 }
            return r2
        L_0x0083:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0083 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.utils.BtcFormat.parse(java.lang.String, java.text.ParsePosition):org.bitcoinj.core.Coin");
    }

    public Coin parse(String str) throws ParseException {
        return (Coin) parseObject(str);
    }

    protected static String prefixCode(String str, int i) {
        if (i == -6) {
            StringBuilder sb = new StringBuilder();
            sb.append("M");
            sb.append(str);
            return sb.toString();
        } else if (i != 6) {
            switch (i) {
                case -3:
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("k");
                    sb2.append(str);
                    return sb2.toString();
                case -2:
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("h");
                    sb3.append(str);
                    return sb3.toString();
                case -1:
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("da");
                    sb4.append(str);
                    return sb4.toString();
                case 0:
                    break;
                case 1:
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("d");
                    sb5.append(str);
                    str = sb5.toString();
                    break;
                case 2:
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("c");
                    sb6.append(str);
                    return sb6.toString();
                case 3:
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("m");
                    sb7.append(str);
                    return sb7.toString();
                default:
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append("No known prefix for scale ");
                    sb8.append(String.valueOf(i));
                    throw new IllegalStateException(sb8.toString());
            }
            return str;
        } else {
            StringBuilder sb9 = new StringBuilder();
            sb9.append("µ");
            sb9.append(str);
            return sb9.toString();
        }
    }

    protected static String prefixSymbol(String str, int i) {
        if (i == -6) {
            StringBuilder sb = new StringBuilder();
            sb.append("M");
            sb.append(str);
            return sb.toString();
        } else if (i != 6) {
            switch (i) {
                case -3:
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("k");
                    sb2.append(str);
                    return sb2.toString();
                case -2:
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("h");
                    sb3.append(str);
                    return sb3.toString();
                case -1:
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("da");
                    sb4.append(str);
                    return sb4.toString();
                case 0:
                    break;
                case 1:
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("d");
                    sb5.append(str);
                    str = sb5.toString();
                    break;
                case 2:
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("¢");
                    sb6.append(str);
                    return sb6.toString();
                case 3:
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("₥");
                    sb7.append(str);
                    return sb7.toString();
                default:
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append("No known prefix for scale ");
                    sb8.append(String.valueOf(i));
                    throw new IllegalStateException(sb8.toString());
            }
            return str;
        } else {
            StringBuilder sb9 = new StringBuilder();
            sb9.append("µ");
            sb9.append(str);
            return sb9.toString();
        }
    }

    protected static String negify(String str) {
        String str2 = ";";
        if (str.contains(str2)) {
            return str;
        }
        if (!str.contains(HelpFormatter.DEFAULT_OPT_PREFIX)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str2);
            sb.append(str.replaceFirst("^([^#0,.']*('[^']*')?)*", "$0-"));
            return sb.toString();
        }
        throw new IllegalStateException("Positive pattern contains negative sign");
    }

    public static Locale[] getAvailableLocales() {
        return NumberFormat.getAvailableLocales();
    }

    public String coinSymbol() {
        String currencySymbol;
        synchronized (this.numberFormat) {
            currencySymbol = this.numberFormat.getDecimalFormatSymbols().getCurrencySymbol();
        }
        return currencySymbol;
    }

    public String coinCode() {
        String internationalCurrencySymbol;
        synchronized (this.numberFormat) {
            internationalCurrencySymbol = this.numberFormat.getDecimalFormatSymbols().getInternationalCurrencySymbol();
        }
        return internationalCurrencySymbol;
    }

    public String pattern() {
        String replaceAll;
        synchronized (this.numberFormat) {
            StringBuilder sb = new StringBuilder();
            for (Integer intValue : this.decimalGroups) {
                int intValue2 = intValue.intValue();
                sb.append("(");
                sb.append(Strings.repeat("#", intValue2));
                sb.append(")");
            }
            DecimalFormatSymbols decimalFormatSymbols = this.numberFormat.getDecimalFormatSymbols();
            String valueOf = String.valueOf(decimalFormatSymbols.getDigit());
            String exponentSeparator = decimalFormatSymbols.getExponentSeparator();
            String valueOf2 = String.valueOf(decimalFormatSymbols.getGroupingSeparator());
            String valueOf3 = String.valueOf(decimalFormatSymbols.getMonetaryDecimalSeparator());
            String valueOf4 = String.valueOf(decimalFormatSymbols.getZeroDigit());
            String valueOf5 = String.valueOf(decimalFormatSymbols.getPatternSeparator());
            String.valueOf(decimalFormatSymbols.getMinusSign());
            String valueOf6 = String.valueOf(decimalFormatSymbols.getDecimalSeparator());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("(^|");
            sb2.append(valueOf5);
            sb2.append(")([^");
            StringBuilder sb3 = new StringBuilder();
            sb3.append(valueOf);
            sb3.append(valueOf4);
            sb3.append(valueOf2);
            sb3.append(valueOf6);
            sb3.append(valueOf3);
            sb2.append(Matcher.quoteReplacement(sb3.toString()));
            sb2.append("']*('[^']*')?)*[");
            StringBuilder sb4 = new StringBuilder();
            sb4.append(valueOf);
            sb4.append(valueOf4);
            sb4.append(valueOf2);
            sb4.append(valueOf6);
            sb4.append(valueOf3);
            sb4.append(exponentSeparator);
            sb2.append(Matcher.quoteReplacement(sb4.toString()));
            sb2.append("]+");
            String sb5 = sb2.toString();
            String localizedPattern = this.numberFormat.toLocalizedPattern();
            StringBuilder sb6 = new StringBuilder();
            sb6.append("$0");
            sb6.append(sb.toString());
            replaceAll = localizedPattern.replaceAll(sb5, sb6.toString()).replaceAll("¤¤", Matcher.quoteReplacement(coinCode())).replaceAll("¤", Matcher.quoteReplacement(coinSymbol()));
        }
        return replaceAll;
    }

    public DecimalFormatSymbols symbols() {
        DecimalFormatSymbols decimalFormatSymbols;
        synchronized (this.numberFormat) {
            decimalFormatSymbols = this.numberFormat.getDecimalFormatSymbols();
        }
        return decimalFormatSymbols;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BtcFormat)) {
            return false;
        }
        BtcFormat btcFormat = (BtcFormat) obj;
        if (!btcFormat.pattern().equals(pattern()) || !btcFormat.symbols().equals(symbols()) || btcFormat.minimumFractionDigits != this.minimumFractionDigits) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(pattern(), symbols(), Integer.valueOf(this.minimumFractionDigits), this.decimalGroups);
    }
}
