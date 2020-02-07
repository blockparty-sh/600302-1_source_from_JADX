package org.bitcoinj.core;

import com.facebook.stetho.common.Utf8Charset;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Arrays;
import org.bitcoinj.core.NetworkParameters.ProtocolVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Message {
    public static final int MAX_SIZE = 33554432;
    private static final boolean SELF_CHECK = false;
    public static final int UNKNOWN_LENGTH = Integer.MIN_VALUE;
    private static final Logger log = LoggerFactory.getLogger(Message.class);
    protected int cursor;
    protected int length;
    protected int offset;
    protected NetworkParameters params;
    protected byte[] payload;
    protected int protocolVersion;
    protected boolean recached;
    protected MessageSerializer serializer;

    /* access modifiers changed from: protected */
    public abstract void parse() throws ProtocolException;

    protected Message() {
        this.length = Integer.MIN_VALUE;
        this.recached = false;
        this.serializer = DummySerializer.DEFAULT;
    }

    protected Message(NetworkParameters networkParameters) {
        this.length = Integer.MIN_VALUE;
        this.recached = false;
        this.params = networkParameters;
        this.serializer = networkParameters.getDefaultSerializer();
    }

    protected Message(NetworkParameters networkParameters, byte[] bArr, int i, int i2) throws ProtocolException {
        this(networkParameters, bArr, i, i2, networkParameters.getDefaultSerializer(), Integer.MIN_VALUE);
    }

    protected Message(NetworkParameters networkParameters, byte[] bArr, int i, int i2, MessageSerializer messageSerializer, int i3) throws ProtocolException {
        this.length = Integer.MIN_VALUE;
        this.recached = false;
        this.serializer = messageSerializer;
        this.protocolVersion = i2;
        this.params = networkParameters;
        this.payload = bArr;
        this.offset = i;
        this.cursor = i;
        this.length = i3;
        parse();
        if (this.length == Integer.MIN_VALUE) {
            Preconditions.checkState(false, "Length field has not been set in constructor for %s after parse.", getClass().getSimpleName());
        }
        if (!messageSerializer.isParseRetainMode()) {
            this.payload = null;
        }
    }

    private void selfCheck(byte[] bArr, int i) {
        if (!(this instanceof VersionMessage)) {
            int i2 = this.cursor;
            byte[] bArr2 = new byte[(i2 - i)];
            System.arraycopy(bArr, i, bArr2, 0, i2 - i);
            byte[] bitcoinSerialize = bitcoinSerialize();
            if (!Arrays.equals(bitcoinSerialize, bArr2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Serialization is wrong: \n");
                sb.append(C3447Utils.HEX.encode(bitcoinSerialize));
                sb.append(" vs \n");
                sb.append(C3447Utils.HEX.encode(bArr2));
                throw new RuntimeException(sb.toString());
            }
        }
    }

    protected Message(NetworkParameters networkParameters, byte[] bArr, int i) throws ProtocolException {
        this(networkParameters, bArr, i, networkParameters.getProtocolVersionNum(ProtocolVersion.CURRENT), networkParameters.getDefaultSerializer(), Integer.MIN_VALUE);
    }

    protected Message(NetworkParameters networkParameters, byte[] bArr, int i, MessageSerializer messageSerializer, int i2) throws ProtocolException {
        this(networkParameters, bArr, i, networkParameters.getProtocolVersionNum(ProtocolVersion.CURRENT), messageSerializer, i2);
    }

    /* access modifiers changed from: protected */
    public void unCache() {
        this.payload = null;
        this.recached = false;
    }

    /* access modifiers changed from: protected */
    public void adjustLength(int i, int i2) {
        int i3 = this.length;
        if (i3 != Integer.MIN_VALUE) {
            if (i2 == Integer.MIN_VALUE) {
                this.length = Integer.MIN_VALUE;
                return;
            }
            this.length = i3 + i2;
            if (i == 1) {
                this.length++;
            } else if (i != 0) {
                this.length += VarInt.sizeOf((long) i) - VarInt.sizeOf((long) (i - 1));
            }
        }
    }

    public boolean isCached() {
        return this.payload != null;
    }

    public boolean isRecached() {
        return this.recached;
    }

    public byte[] bitcoinSerialize() {
        byte[] unsafeBitcoinSerialize = unsafeBitcoinSerialize();
        byte[] bArr = new byte[unsafeBitcoinSerialize.length];
        System.arraycopy(unsafeBitcoinSerialize, 0, bArr, 0, unsafeBitcoinSerialize.length);
        return bArr;
    }

    public byte[] unsafeBitcoinSerialize() {
        byte[] bArr = this.payload;
        if (bArr == null) {
            int i = this.length;
            int i2 = 32;
            if (i >= 32) {
                i2 = 32 + i;
            }
            UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream(i2);
            try {
                bitcoinSerializeToStream(unsafeByteArrayOutputStream);
            } catch (IOException unused) {
            }
            if (this.serializer.isParseRetainMode()) {
                this.payload = unsafeByteArrayOutputStream.toByteArray();
                this.cursor -= this.offset;
                this.offset = 0;
                this.recached = true;
                byte[] bArr2 = this.payload;
                this.length = bArr2.length;
                return bArr2;
            }
            byte[] byteArray = unsafeByteArrayOutputStream.toByteArray();
            this.length = byteArray.length;
            return byteArray;
        } else if (this.offset == 0 && this.length == bArr.length) {
            return bArr;
        } else {
            int i3 = this.length;
            byte[] bArr3 = new byte[i3];
            System.arraycopy(this.payload, this.offset, bArr3, 0, i3);
            return bArr3;
        }
    }

    public final void bitcoinSerialize(OutputStream outputStream) throws IOException {
        byte[] bArr = this.payload;
        if (bArr != null) {
            int i = this.length;
            if (i != Integer.MIN_VALUE) {
                outputStream.write(bArr, this.offset, i);
                return;
            }
        }
        bitcoinSerializeToStream(outputStream);
    }

    /* access modifiers changed from: protected */
    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        log.error("Error: {} class has not implemented bitcoinSerializeToStream method.  Generating message with no payload", (Object) getClass());
    }

    public Sha256Hash getHash() {
        throw new UnsupportedOperationException();
    }

    public final int getMessageSize() {
        if (this.length == Integer.MIN_VALUE) {
            Preconditions.checkState(false, "Length field has not been set in %s.", getClass().getSimpleName());
        }
        return this.length;
    }

    /* access modifiers changed from: protected */
    public long readUint32() throws ProtocolException {
        try {
            long readUint32 = C3447Utils.readUint32(this.payload, this.cursor);
            this.cursor += 4;
            return readUint32;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ProtocolException((Exception) e);
        }
    }

    /* access modifiers changed from: protected */
    public long readInt64() throws ProtocolException {
        try {
            long readInt64 = C3447Utils.readInt64(this.payload, this.cursor);
            this.cursor += 8;
            return readInt64;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ProtocolException((Exception) e);
        }
    }

    /* access modifiers changed from: protected */
    public BigInteger readUint64() throws ProtocolException {
        return new BigInteger(C3447Utils.reverseBytes(readBytes(8)));
    }

    /* access modifiers changed from: protected */
    public long readVarInt() throws ProtocolException {
        return readVarInt(0);
    }

    /* access modifiers changed from: protected */
    public long readVarInt(int i) throws ProtocolException {
        try {
            VarInt varInt = new VarInt(this.payload, this.cursor + i);
            this.cursor += i + varInt.getOriginalSizeInBytes();
            return varInt.value;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ProtocolException((Exception) e);
        }
    }

    /* access modifiers changed from: protected */
    public byte[] readBytes(int i) throws ProtocolException {
        if (i <= 33554432) {
            try {
                byte[] bArr = new byte[i];
                System.arraycopy(this.payload, this.cursor, bArr, 0, i);
                this.cursor += i;
                return bArr;
            } catch (IndexOutOfBoundsException e) {
                throw new ProtocolException((Exception) e);
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Claimed value length too large: ");
            sb.append(i);
            throw new ProtocolException(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] readByteArray() throws ProtocolException {
        return readBytes((int) readVarInt());
    }

    /* access modifiers changed from: protected */
    public String readStr() throws ProtocolException {
        long readVarInt = readVarInt();
        if (readVarInt == 0) {
            return "";
        }
        return C3447Utils.toString(readBytes((int) readVarInt), Utf8Charset.NAME);
    }

    /* access modifiers changed from: protected */
    public Sha256Hash readHash() throws ProtocolException {
        return Sha256Hash.wrapReversed(readBytes(32));
    }

    /* access modifiers changed from: protected */
    public boolean hasMoreBytes() {
        return this.cursor < this.payload.length;
    }

    public NetworkParameters getParams() {
        return this.params;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        NetworkParameters networkParameters = this.params;
        if (networkParameters != null) {
            this.serializer = networkParameters.getDefaultSerializer();
        }
    }
}
