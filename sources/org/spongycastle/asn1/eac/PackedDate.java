package org.spongycastle.asn1.eac;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import org.spongycastle.util.Arrays;

public class PackedDate {
    private byte[] time;

    public PackedDate(String str) {
        this.time = convert(str);
    }

    public PackedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = convert(simpleDateFormat.format(date));
    }

    public PackedDate(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = convert(simpleDateFormat.format(date));
    }

    private byte[] convert(String str) {
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[6];
        for (int i = 0; i != 6; i++) {
            bArr[i] = (byte) (charArray[i] - '0');
        }
        return bArr;
    }

    PackedDate(byte[] bArr) {
        this.time = bArr;
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        StringBuilder sb = new StringBuilder();
        sb.append("20");
        sb.append(toString());
        return simpleDateFormat.parse(sb.toString());
    }

    public int hashCode() {
        return Arrays.hashCode(this.time);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PackedDate)) {
            return false;
        }
        return Arrays.areEqual(this.time, ((PackedDate) obj).time);
    }

    public String toString() {
        char[] cArr = new char[this.time.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) ((this.time[i] & 255) + 48);
        }
        return new String(cArr);
    }

    public byte[] getEncoding() {
        return this.time;
    }
}
