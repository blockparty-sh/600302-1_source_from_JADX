package org.bitcoinj.utils;

import com.google.common.collect.ImmutableList;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public final class BtcAutoFormat extends BtcFormat {

    public enum Style {
        CODE {
            /* access modifiers changed from: 0000 */
            public void apply(DecimalFormat decimalFormat) {
                decimalFormat.applyPattern(BtcFormat.negify(decimalFormat.toPattern()).replaceAll("¤", "¤¤").replaceAll("([#0.,E-])¤¤", "$1 ¤¤").replaceAll("¤¤([0#.,E-])", "¤¤ $1"));
            }
        },
        SYMBOL {
            /* access modifiers changed from: 0000 */
            public void apply(DecimalFormat decimalFormat) {
                decimalFormat.applyPattern(BtcFormat.negify(decimalFormat.toPattern()).replaceAll("¤¤", "¤"));
            }
        };

        /* access modifiers changed from: 0000 */
        public abstract void apply(DecimalFormat decimalFormat);
    }

    /* access modifiers changed from: protected */
    public int scale() {
        return 0;
    }

    protected BtcAutoFormat(Locale locale, Style style, int i) {
        super((DecimalFormat) NumberFormat.getCurrencyInstance(locale), i, ImmutableList.m126of());
        style.apply(this.numberFormat);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x008d, code lost:
        if (r8.compareTo(r9) < 0) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0094, code lost:
        if (r1.compareTo(r9) < 0) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int scale(java.math.BigInteger r8, int r9) {
        /*
            r7 = this;
            int r9 = 8 - r9
            r0 = 0
            int r9 = java.lang.Math.max(r9, r0)
            java.math.BigDecimal r1 = new java.math.BigDecimal
            r1.<init>(r8)
            java.math.BigDecimal r8 = r1.movePointLeft(r9)
            java.math.BigDecimal r1 = java.math.BigDecimal.ONE
            java.math.BigDecimal r1 = r8.remainder(r1)
            java.math.BigDecimal r2 = java.math.BigDecimal.ZERO
            int r1 = r1.compareTo(r2)
            r2 = 3
            r3 = 6
            if (r1 != 0) goto L_0x0023
        L_0x0020:
            r3 = 0
            goto L_0x0097
        L_0x0023:
            java.math.BigDecimal r1 = r8.movePointRight(r2)
            java.math.BigDecimal r4 = java.math.BigDecimal.ONE
            java.math.BigDecimal r4 = r1.remainder(r4)
            java.math.BigDecimal r5 = java.math.BigDecimal.ZERO
            int r4 = r4.compareTo(r5)
            if (r4 != 0) goto L_0x0037
        L_0x0035:
            r3 = 3
            goto L_0x0097
        L_0x0037:
            java.math.BigDecimal r4 = r8.movePointRight(r3)
            java.math.BigDecimal r5 = java.math.BigDecimal.ONE
            java.math.BigDecimal r5 = r4.remainder(r5)
            java.math.BigDecimal r6 = java.math.BigDecimal.ZERO
            int r5 = r5.compareTo(r6)
            if (r5 != 0) goto L_0x004a
            goto L_0x0097
        L_0x004a:
            java.math.RoundingMode r5 = java.math.RoundingMode.HALF_UP
            java.math.BigDecimal r5 = r8.setScale(r0, r5)
            java.math.BigDecimal r8 = r8.subtract(r5)
            java.math.BigDecimal r8 = r8.movePointRight(r9)
            java.math.BigDecimal r8 = r8.abs()
            java.math.RoundingMode r5 = java.math.RoundingMode.HALF_UP
            java.math.BigDecimal r5 = r1.setScale(r0, r5)
            java.math.BigDecimal r1 = r1.subtract(r5)
            int r5 = r9 + -3
            java.math.BigDecimal r1 = r1.movePointRight(r5)
            java.math.BigDecimal r1 = r1.abs()
            java.math.RoundingMode r5 = java.math.RoundingMode.HALF_UP
            java.math.BigDecimal r5 = r4.setScale(r0, r5)
            java.math.BigDecimal r4 = r4.subtract(r5)
            int r9 = r9 - r3
            java.math.BigDecimal r9 = r4.movePointRight(r9)
            java.math.BigDecimal r9 = r9.abs()
            int r4 = r8.compareTo(r1)
            if (r4 >= 0) goto L_0x0090
            int r8 = r8.compareTo(r9)
            if (r8 >= 0) goto L_0x0097
            goto L_0x0020
        L_0x0090:
            int r8 = r1.compareTo(r9)
            if (r8 >= 0) goto L_0x0097
            goto L_0x0035
        L_0x0097:
            java.text.DecimalFormat r8 = r7.numberFormat
            prefixUnitsIndicator(r8, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.utils.BtcAutoFormat.scale(java.math.BigInteger, int):int");
    }

    public int fractionPlaces() {
        return this.minimumFractionDigits;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BtcAutoFormat)) {
            return false;
        }
        return super.equals(obj);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Auto-format ");
        sb.append(pattern());
        return sb.toString();
    }
}
