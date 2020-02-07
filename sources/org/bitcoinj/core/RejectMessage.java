package org.bitcoinj.core;

import com.facebook.stetho.common.Utf8Charset;
import com.google.common.base.Ascii;
import com.google.common.base.Objects;
import com.google.common.primitives.SignedBytes;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

public class RejectMessage extends Message {
    private RejectCode code;
    private String message;
    private Sha256Hash messageHash;
    private String reason;

    public enum RejectCode {
        MALFORMED(1),
        INVALID(16),
        OBSOLETE(17),
        DUPLICATE(Ascii.DC2),
        NONSTANDARD(SignedBytes.MAX_POWER_OF_TWO),
        DUST(65),
        INSUFFICIENTFEE(66),
        CHECKPOINT(67),
        OTHER(-1);
        
        byte code;

        private RejectCode(byte b) {
            this.code = b;
        }

        static RejectCode fromCode(byte b) {
            RejectCode[] values;
            for (RejectCode rejectCode : values()) {
                if (rejectCode.code == b) {
                    return rejectCode;
                }
            }
            return OTHER;
        }
    }

    public RejectMessage(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0);
    }

    public RejectMessage(NetworkParameters networkParameters, RejectCode rejectCode, Sha256Hash sha256Hash, String str, String str2) throws ProtocolException {
        super(networkParameters);
        this.code = rejectCode;
        this.messageHash = sha256Hash;
        this.message = str;
        this.reason = str2;
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        this.message = readStr();
        this.code = RejectCode.fromCode(readBytes(1)[0]);
        this.reason = readStr();
        if (this.message.equals("block") || this.message.equals("tx")) {
            this.messageHash = readHash();
        }
        this.length = this.cursor - this.offset;
    }

    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        String str = this.message;
        String str2 = Utf8Charset.NAME;
        byte[] bytes = str.getBytes(str2);
        outputStream.write(new VarInt((long) bytes.length).encode());
        outputStream.write(bytes);
        outputStream.write(this.code.code);
        byte[] bytes2 = this.reason.getBytes(str2);
        outputStream.write(new VarInt((long) bytes2.length).encode());
        outputStream.write(bytes2);
        if (!"block".equals(this.message)) {
            if (!"tx".equals(this.message)) {
                return;
            }
        }
        outputStream.write(this.messageHash.getReversedBytes());
    }

    public String getRejectedMessage() {
        return this.message;
    }

    public Sha256Hash getRejectedObjectHash() {
        return this.messageHash;
    }

    public RejectCode getReasonCode() {
        return this.code;
    }

    public String getReasonString() {
        return this.reason;
    }

    public String toString() {
        Object rejectedObjectHash = getRejectedObjectHash();
        Locale locale = Locale.US;
        Object[] objArr = new Object[4];
        objArr[0] = getRejectedMessage();
        if (rejectedObjectHash == null) {
            rejectedObjectHash = "";
        }
        objArr[1] = rejectedObjectHash;
        objArr[2] = getReasonString();
        objArr[3] = Byte.valueOf(getReasonCode().code);
        return String.format(locale, "Reject: %s %s for reason '%s' (%d)", objArr);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RejectMessage rejectMessage = (RejectMessage) obj;
        if (!this.message.equals(rejectMessage.message) || !this.code.equals(rejectMessage.code) || !this.reason.equals(rejectMessage.reason) || !this.messageHash.equals(rejectMessage.messageHash)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(this.message, this.code, this.reason, this.messageHash);
    }
}
