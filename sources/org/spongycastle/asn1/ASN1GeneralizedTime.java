package org.spongycastle.asn1;

import com.leanplum.core.BuildConfig;
import com.microsoft.appcenter.Constants;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Marker;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public class ASN1GeneralizedTime extends ASN1Primitive {
    private byte[] time;

    /* access modifiers changed from: 0000 */
    public boolean isConstructed() {
        return false;
    }

    public static ASN1GeneralizedTime getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1GeneralizedTime)) {
            return (ASN1GeneralizedTime) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1GeneralizedTime) fromByteArray((byte[]) obj);
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("encoding error in getInstance: ");
                sb.append(e.toString());
                throw new IllegalArgumentException(sb.toString());
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("illegal object in getInstance: ");
            sb2.append(obj.getClass().getName());
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public static ASN1GeneralizedTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (z || (object instanceof ASN1GeneralizedTime)) {
            return getInstance(object);
        }
        return new ASN1GeneralizedTime(((ASN1OctetString) object).getOctets());
    }

    public ASN1GeneralizedTime(String str) {
        this.time = Strings.toByteArray(str);
        try {
            getDate();
        } catch (ParseException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("invalid date string: ");
            sb.append(e.getMessage());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public ASN1GeneralizedTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    public ASN1GeneralizedTime(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    ASN1GeneralizedTime(byte[] bArr) {
        this.time = bArr;
    }

    public String getTimeString() {
        return Strings.fromByteArray(this.time);
    }

    public String getTime() {
        String fromByteArray = Strings.fromByteArray(this.time);
        if (fromByteArray.charAt(fromByteArray.length() - 1) == 'Z') {
            StringBuilder sb = new StringBuilder();
            sb.append(fromByteArray.substring(0, fromByteArray.length() - 1));
            sb.append("GMT+00:00");
            return sb.toString();
        }
        int length = fromByteArray.length() - 5;
        char charAt = fromByteArray.charAt(length);
        String str = "GMT";
        if (charAt == '-' || charAt == '+') {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(fromByteArray.substring(0, length));
            sb2.append(str);
            int i = length + 3;
            sb2.append(fromByteArray.substring(length, i));
            sb2.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            sb2.append(fromByteArray.substring(i));
            return sb2.toString();
        }
        int length2 = fromByteArray.length() - 3;
        char charAt2 = fromByteArray.charAt(length2);
        if (charAt2 == '-' || charAt2 == '+') {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(fromByteArray.substring(0, length2));
            sb3.append(str);
            sb3.append(fromByteArray.substring(length2));
            sb3.append(":00");
            return sb3.toString();
        }
        StringBuilder sb4 = new StringBuilder();
        sb4.append(fromByteArray);
        sb4.append(calculateGMTOffset());
        return sb4.toString();
    }

    private String calculateGMTOffset() {
        String str;
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        String str2 = Marker.ANY_NON_NULL_MARKER;
        if (rawOffset < 0) {
            rawOffset = -rawOffset;
            str = HelpFormatter.DEFAULT_OPT_PREFIX;
        } else {
            str = str2;
        }
        int i = rawOffset / 3600000;
        int i2 = (rawOffset - (((i * 60) * 60) * 1000)) / 60000;
        try {
            if (timeZone.useDaylightTime() && timeZone.inDaylightTime(getDate())) {
                i += str.equals(str2) ? 1 : -1;
            }
        } catch (ParseException unused) {
        }
        StringBuilder sb = new StringBuilder();
        sb.append("GMT");
        sb.append(str);
        sb.append(convert(i));
        sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        sb.append(convert(i2));
        return sb.toString();
    }

    private String convert(int i) {
        if (i >= 10) {
            return Integer.toString(i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(BuildConfig.BUILD_NUMBER);
        sb.append(i);
        return sb.toString();
    }

    public Date getDate() throws ParseException {
        SimpleDateFormat simpleDateFormat;
        SimpleDateFormat simpleDateFormat2;
        SimpleDateFormat simpleDateFormat3;
        String fromByteArray = Strings.fromByteArray(this.time);
        String str = "Z";
        if (fromByteArray.endsWith(str)) {
            if (hasFractionalSeconds()) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSS'Z'");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, str));
        } else if (fromByteArray.indexOf(45) > 0 || fromByteArray.indexOf(43) > 0) {
            fromByteArray = getTime();
            if (hasFractionalSeconds()) {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmss.SSSz");
            } else {
                simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmmssz");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, str));
        } else {
            if (hasFractionalSeconds()) {
                simpleDateFormat3 = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
            } else {
                simpleDateFormat3 = new SimpleDateFormat("yyyyMMddHHmmss");
            }
            simpleDateFormat = simpleDateFormat3;
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
        }
        if (hasFractionalSeconds()) {
            String substring = fromByteArray.substring(14);
            int i = 1;
            while (i < substring.length()) {
                char charAt = substring.charAt(i);
                if ('0' > charAt || charAt > '9') {
                    break;
                }
                i++;
            }
            int i2 = i - 1;
            if (i2 > 3) {
                StringBuilder sb = new StringBuilder();
                sb.append(substring.substring(0, 4));
                sb.append(substring.substring(i));
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(fromByteArray.substring(0, 14));
                sb3.append(sb2);
                fromByteArray = sb3.toString();
            } else if (i2 == 1) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(substring.substring(0, i));
                sb4.append("00");
                sb4.append(substring.substring(i));
                String sb5 = sb4.toString();
                StringBuilder sb6 = new StringBuilder();
                sb6.append(fromByteArray.substring(0, 14));
                sb6.append(sb5);
                fromByteArray = sb6.toString();
            } else if (i2 == 2) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append(substring.substring(0, i));
                sb7.append(BuildConfig.BUILD_NUMBER);
                sb7.append(substring.substring(i));
                String sb8 = sb7.toString();
                StringBuilder sb9 = new StringBuilder();
                sb9.append(fromByteArray.substring(0, 14));
                sb9.append(sb8);
                fromByteArray = sb9.toString();
            }
        }
        return simpleDateFormat.parse(fromByteArray);
    }

    private boolean hasFractionalSeconds() {
        int i = 0;
        while (true) {
            byte[] bArr = this.time;
            if (i == bArr.length) {
                return false;
            }
            if (bArr[i] == 46 && i == 14) {
                return true;
            }
            i++;
        }
    }

    /* access modifiers changed from: 0000 */
    public int encodedLength() {
        int length = this.time.length;
        return StreamUtil.calculateBodyLength(length) + 1 + length;
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(24, this.time);
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1GeneralizedTime)) {
            return false;
        }
        return Arrays.areEqual(this.time, ((ASN1GeneralizedTime) aSN1Primitive).time);
    }

    public int hashCode() {
        return Arrays.hashCode(this.time);
    }
}
