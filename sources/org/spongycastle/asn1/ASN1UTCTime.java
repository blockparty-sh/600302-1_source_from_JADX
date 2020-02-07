package org.spongycastle.asn1;

import com.microsoft.appcenter.Constants;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public class ASN1UTCTime extends ASN1Primitive {
    private byte[] time;

    /* access modifiers changed from: 0000 */
    public boolean isConstructed() {
        return false;
    }

    public static ASN1UTCTime getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1UTCTime)) {
            return (ASN1UTCTime) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1UTCTime) fromByteArray((byte[]) obj);
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

    public static ASN1UTCTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (z || (object instanceof ASN1UTCTime)) {
            return getInstance(object);
        }
        return new ASN1UTCTime(((ASN1OctetString) object).getOctets());
    }

    public ASN1UTCTime(String str) {
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

    public ASN1UTCTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    public ASN1UTCTime(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = Strings.toByteArray(simpleDateFormat.format(date));
    }

    ASN1UTCTime(byte[] bArr) {
        this.time = bArr;
    }

    public Date getDate() throws ParseException {
        return new SimpleDateFormat("yyMMddHHmmssz").parse(getTime());
    }

    public Date getAdjustedDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        return simpleDateFormat.parse(getAdjustedTime());
    }

    public String getTime() {
        String fromByteArray = Strings.fromByteArray(this.time);
        if (fromByteArray.indexOf(45) >= 0 || fromByteArray.indexOf(43) >= 0) {
            int indexOf = fromByteArray.indexOf(45);
            if (indexOf < 0) {
                indexOf = fromByteArray.indexOf(43);
            }
            if (indexOf == fromByteArray.length() - 3) {
                StringBuilder sb = new StringBuilder();
                sb.append(fromByteArray);
                sb.append("00");
                fromByteArray = sb.toString();
            }
            String str = Constants.COMMON_SCHEMA_PREFIX_SEPARATOR;
            if (indexOf == 10) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(fromByteArray.substring(0, 10));
                sb2.append("00GMT");
                sb2.append(fromByteArray.substring(10, 13));
                sb2.append(str);
                sb2.append(fromByteArray.substring(13, 15));
                return sb2.toString();
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append(fromByteArray.substring(0, 12));
            sb3.append("GMT");
            sb3.append(fromByteArray.substring(12, 15));
            sb3.append(str);
            sb3.append(fromByteArray.substring(15, 17));
            return sb3.toString();
        } else if (fromByteArray.length() == 11) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(fromByteArray.substring(0, 10));
            sb4.append("00GMT+00:00");
            return sb4.toString();
        } else {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(fromByteArray.substring(0, 12));
            sb5.append("GMT+00:00");
            return sb5.toString();
        }
    }

    public String getAdjustedTime() {
        String time2 = getTime();
        if (time2.charAt(0) < '5') {
            StringBuilder sb = new StringBuilder();
            sb.append("20");
            sb.append(time2);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("19");
        sb2.append(time2);
        return sb2.toString();
    }

    /* access modifiers changed from: 0000 */
    public int encodedLength() {
        int length = this.time.length;
        return StreamUtil.calculateBodyLength(length) + 1 + length;
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.write(23);
        int length = this.time.length;
        aSN1OutputStream.writeLength(length);
        for (int i = 0; i != length; i++) {
            aSN1OutputStream.write((int) this.time[i]);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1UTCTime)) {
            return false;
        }
        return Arrays.areEqual(this.time, ((ASN1UTCTime) aSN1Primitive).time);
    }

    public int hashCode() {
        return Arrays.hashCode(this.time);
    }

    public String toString() {
        return Strings.fromByteArray(this.time);
    }
}
