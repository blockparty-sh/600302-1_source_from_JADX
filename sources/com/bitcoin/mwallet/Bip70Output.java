package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class Bip70Output extends GeneratedMessageLite<Bip70Output, Builder> implements Bip70OutputOrBuilder {
    public static final int ADDRESS_FIELD_NUMBER = 3;
    public static final int AMOUNT_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final Bip70Output DEFAULT_INSTANCE = new Bip70Output();
    private static volatile Parser<Bip70Output> PARSER = null;
    public static final int SCRIPT_HEX_FIELD_NUMBER = 1;
    private String address_;
    private long amount_;
    private String scriptHex_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<Bip70Output, Builder> implements Bip70OutputOrBuilder {
        private Builder() {
            super(Bip70Output.DEFAULT_INSTANCE);
        }

        public String getScriptHex() {
            return ((Bip70Output) this.instance).getScriptHex();
        }

        public ByteString getScriptHexBytes() {
            return ((Bip70Output) this.instance).getScriptHexBytes();
        }

        public Builder setScriptHex(String str) {
            copyOnWrite();
            ((Bip70Output) this.instance).setScriptHex(str);
            return this;
        }

        public Builder clearScriptHex() {
            copyOnWrite();
            ((Bip70Output) this.instance).clearScriptHex();
            return this;
        }

        public Builder setScriptHexBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70Output) this.instance).setScriptHexBytes(byteString);
            return this;
        }

        public long getAmount() {
            return ((Bip70Output) this.instance).getAmount();
        }

        public Builder setAmount(long j) {
            copyOnWrite();
            ((Bip70Output) this.instance).setAmount(j);
            return this;
        }

        public Builder clearAmount() {
            copyOnWrite();
            ((Bip70Output) this.instance).clearAmount();
            return this;
        }

        public String getAddress() {
            return ((Bip70Output) this.instance).getAddress();
        }

        public ByteString getAddressBytes() {
            return ((Bip70Output) this.instance).getAddressBytes();
        }

        public Builder setAddress(String str) {
            copyOnWrite();
            ((Bip70Output) this.instance).setAddress(str);
            return this;
        }

        public Builder clearAddress() {
            copyOnWrite();
            ((Bip70Output) this.instance).clearAddress();
            return this;
        }

        public Builder setAddressBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70Output) this.instance).setAddressBytes(byteString);
            return this;
        }
    }

    private Bip70Output() {
        String str = "";
        this.scriptHex_ = str;
        this.address_ = str;
    }

    public String getScriptHex() {
        return this.scriptHex_;
    }

    public ByteString getScriptHexBytes() {
        return ByteString.copyFromUtf8(this.scriptHex_);
    }

    /* access modifiers changed from: private */
    public void setScriptHex(String str) {
        if (str != null) {
            this.scriptHex_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearScriptHex() {
        this.scriptHex_ = getDefaultInstance().getScriptHex();
    }

    /* access modifiers changed from: private */
    public void setScriptHexBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.scriptHex_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public long getAmount() {
        return this.amount_;
    }

    /* access modifiers changed from: private */
    public void setAmount(long j) {
        this.amount_ = j;
    }

    /* access modifiers changed from: private */
    public void clearAmount() {
        this.amount_ = 0;
    }

    public String getAddress() {
        return this.address_;
    }

    public ByteString getAddressBytes() {
        return ByteString.copyFromUtf8(this.address_);
    }

    /* access modifiers changed from: private */
    public void setAddress(String str) {
        if (str != null) {
            this.address_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearAddress() {
        this.address_ = getDefaultInstance().getAddress();
    }

    /* access modifiers changed from: private */
    public void setAddressBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.address_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.scriptHex_.isEmpty()) {
            codedOutputStream.writeString(1, getScriptHex());
        }
        long j = this.amount_;
        if (j != 0) {
            codedOutputStream.writeInt64(2, j);
        }
        if (!this.address_.isEmpty()) {
            codedOutputStream.writeString(3, getAddress());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.scriptHex_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getScriptHex());
        }
        long j = this.amount_;
        if (j != 0) {
            i2 += CodedOutputStream.computeInt64Size(2, j);
        }
        if (!this.address_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(3, getAddress());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static Bip70Output parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Bip70Output) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Bip70Output parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Bip70Output) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Bip70Output parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Bip70Output) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Bip70Output parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Bip70Output) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Bip70Output parseFrom(InputStream inputStream) throws IOException {
        return (Bip70Output) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Bip70Output parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Bip70Output) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Bip70Output parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Bip70Output) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Bip70Output parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Bip70Output) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Bip70Output parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Bip70Output) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Bip70Output parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Bip70Output) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Bip70Output bip70Output) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(bip70Output);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new Bip70Output();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                Bip70Output bip70Output = (Bip70Output) obj2;
                this.scriptHex_ = visitor.visitString(!this.scriptHex_.isEmpty(), this.scriptHex_, !bip70Output.scriptHex_.isEmpty(), bip70Output.scriptHex_);
                boolean z2 = this.amount_ != 0;
                long j = this.amount_;
                if (bip70Output.amount_ != 0) {
                    z = true;
                }
                this.amount_ = visitor.visitLong(z2, j, z, bip70Output.amount_);
                this.address_ = visitor.visitString(!this.address_.isEmpty(), this.address_, !bip70Output.address_.isEmpty(), bip70Output.address_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.scriptHex_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 16) {
                                this.amount_ = codedInputStream.readInt64();
                            } else if (readTag == 26) {
                                this.address_ = codedInputStream.readStringRequireUtf8();
                            } else if (!codedInputStream.skipField(readTag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw new RuntimeException(e.setUnfinishedMessage(this));
                    } catch (IOException e2) {
                        throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                    }
                }
                break;
            case GET_DEFAULT_INSTANCE:
                break;
            case GET_PARSER:
                if (PARSER == null) {
                    synchronized (Bip70Output.class) {
                        if (PARSER == null) {
                            PARSER = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        }
                    }
                }
                return PARSER;
            default:
                throw new UnsupportedOperationException();
        }
        return DEFAULT_INSTANCE;
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public static Bip70Output getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Bip70Output> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
