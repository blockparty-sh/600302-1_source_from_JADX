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

public final class RecoverResponse extends GeneratedMessageLite<RecoverResponse, Builder> implements RecoverResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final RecoverResponse DEFAULT_INSTANCE = new RecoverResponse();
    public static final int NAME_FIELD_NUMBER = 3;
    private static volatile Parser<RecoverResponse> PARSER = null;
    public static final int SCANNING_FIELD_NUMBER = 2;
    public static final int WALLET_ID_FIELD_NUMBER = 1;
    private String name_;
    private boolean scanning_;
    private String walletId_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<RecoverResponse, Builder> implements RecoverResponseOrBuilder {
        private Builder() {
            super(RecoverResponse.DEFAULT_INSTANCE);
        }

        public String getWalletId() {
            return ((RecoverResponse) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((RecoverResponse) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((RecoverResponse) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((RecoverResponse) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((RecoverResponse) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public boolean getScanning() {
            return ((RecoverResponse) this.instance).getScanning();
        }

        public Builder setScanning(boolean z) {
            copyOnWrite();
            ((RecoverResponse) this.instance).setScanning(z);
            return this;
        }

        public Builder clearScanning() {
            copyOnWrite();
            ((RecoverResponse) this.instance).clearScanning();
            return this;
        }

        public String getName() {
            return ((RecoverResponse) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((RecoverResponse) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((RecoverResponse) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((RecoverResponse) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((RecoverResponse) this.instance).setNameBytes(byteString);
            return this;
        }
    }

    private RecoverResponse() {
        String str = "";
        this.walletId_ = str;
        this.name_ = str;
    }

    public String getWalletId() {
        return this.walletId_;
    }

    public ByteString getWalletIdBytes() {
        return ByteString.copyFromUtf8(this.walletId_);
    }

    /* access modifiers changed from: private */
    public void setWalletId(String str) {
        if (str != null) {
            this.walletId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearWalletId() {
        this.walletId_ = getDefaultInstance().getWalletId();
    }

    /* access modifiers changed from: private */
    public void setWalletIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.walletId_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public boolean getScanning() {
        return this.scanning_;
    }

    /* access modifiers changed from: private */
    public void setScanning(boolean z) {
        this.scanning_ = z;
    }

    /* access modifiers changed from: private */
    public void clearScanning() {
        this.scanning_ = false;
    }

    public String getName() {
        return this.name_;
    }

    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    public void setName(String str) {
        if (str != null) {
            this.name_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearName() {
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.name_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(1, getWalletId());
        }
        boolean z = this.scanning_;
        if (z) {
            codedOutputStream.writeBool(2, z);
        }
        if (!this.name_.isEmpty()) {
            codedOutputStream.writeString(3, getName());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.walletId_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getWalletId());
        }
        boolean z = this.scanning_;
        if (z) {
            i2 += CodedOutputStream.computeBoolSize(2, z);
        }
        if (!this.name_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(3, getName());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static RecoverResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RecoverResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RecoverResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RecoverResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RecoverResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RecoverResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RecoverResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RecoverResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RecoverResponse parseFrom(InputStream inputStream) throws IOException {
        return (RecoverResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RecoverResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RecoverResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RecoverResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RecoverResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RecoverResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RecoverResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RecoverResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RecoverResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RecoverResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RecoverResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RecoverResponse recoverResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(recoverResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new RecoverResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                RecoverResponse recoverResponse = (RecoverResponse) obj2;
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, !recoverResponse.walletId_.isEmpty(), recoverResponse.walletId_);
                boolean z = this.scanning_;
                boolean z2 = recoverResponse.scanning_;
                this.scanning_ = visitor.visitBoolean(z, z, z2, z2);
                this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, true ^ recoverResponse.name_.isEmpty(), recoverResponse.name_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z3 = false;
                while (!z3) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.walletId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 16) {
                                this.scanning_ = codedInputStream.readBool();
                            } else if (readTag == 26) {
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (!codedInputStream.skipField(readTag)) {
                            }
                        }
                        z3 = true;
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
                    synchronized (RecoverResponse.class) {
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

    public static RecoverResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RecoverResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
