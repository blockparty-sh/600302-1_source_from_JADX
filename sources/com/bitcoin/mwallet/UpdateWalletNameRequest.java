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

public final class UpdateWalletNameRequest extends GeneratedMessageLite<UpdateWalletNameRequest, Builder> implements UpdateWalletNameRequestOrBuilder {
    /* access modifiers changed from: private */
    public static final UpdateWalletNameRequest DEFAULT_INSTANCE = new UpdateWalletNameRequest();
    private static volatile Parser<UpdateWalletNameRequest> PARSER = null;
    public static final int WALLET_ID_FIELD_NUMBER = 1;
    public static final int WALLET_NAME_FIELD_NUMBER = 2;
    private String walletId_;
    private String walletName_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<UpdateWalletNameRequest, Builder> implements UpdateWalletNameRequestOrBuilder {
        private Builder() {
            super(UpdateWalletNameRequest.DEFAULT_INSTANCE);
        }

        public String getWalletId() {
            return ((UpdateWalletNameRequest) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((UpdateWalletNameRequest) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((UpdateWalletNameRequest) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((UpdateWalletNameRequest) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((UpdateWalletNameRequest) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public String getWalletName() {
            return ((UpdateWalletNameRequest) this.instance).getWalletName();
        }

        public ByteString getWalletNameBytes() {
            return ((UpdateWalletNameRequest) this.instance).getWalletNameBytes();
        }

        public Builder setWalletName(String str) {
            copyOnWrite();
            ((UpdateWalletNameRequest) this.instance).setWalletName(str);
            return this;
        }

        public Builder clearWalletName() {
            copyOnWrite();
            ((UpdateWalletNameRequest) this.instance).clearWalletName();
            return this;
        }

        public Builder setWalletNameBytes(ByteString byteString) {
            copyOnWrite();
            ((UpdateWalletNameRequest) this.instance).setWalletNameBytes(byteString);
            return this;
        }
    }

    private UpdateWalletNameRequest() {
        String str = "";
        this.walletId_ = str;
        this.walletName_ = str;
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

    public String getWalletName() {
        return this.walletName_;
    }

    public ByteString getWalletNameBytes() {
        return ByteString.copyFromUtf8(this.walletName_);
    }

    /* access modifiers changed from: private */
    public void setWalletName(String str) {
        if (str != null) {
            this.walletName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearWalletName() {
        this.walletName_ = getDefaultInstance().getWalletName();
    }

    /* access modifiers changed from: private */
    public void setWalletNameBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.walletName_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(1, getWalletId());
        }
        if (!this.walletName_.isEmpty()) {
            codedOutputStream.writeString(2, getWalletName());
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
        if (!this.walletName_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getWalletName());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static UpdateWalletNameRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (UpdateWalletNameRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static UpdateWalletNameRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpdateWalletNameRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static UpdateWalletNameRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (UpdateWalletNameRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static UpdateWalletNameRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpdateWalletNameRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static UpdateWalletNameRequest parseFrom(InputStream inputStream) throws IOException {
        return (UpdateWalletNameRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UpdateWalletNameRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateWalletNameRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UpdateWalletNameRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (UpdateWalletNameRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UpdateWalletNameRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateWalletNameRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UpdateWalletNameRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (UpdateWalletNameRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static UpdateWalletNameRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateWalletNameRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UpdateWalletNameRequest updateWalletNameRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(updateWalletNameRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new UpdateWalletNameRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                UpdateWalletNameRequest updateWalletNameRequest = (UpdateWalletNameRequest) obj2;
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, !updateWalletNameRequest.walletId_.isEmpty(), updateWalletNameRequest.walletId_);
                this.walletName_ = visitor.visitString(!this.walletName_.isEmpty(), this.walletName_, true ^ updateWalletNameRequest.walletName_.isEmpty(), updateWalletNameRequest.walletName_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.walletId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.walletName_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (UpdateWalletNameRequest.class) {
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

    public static UpdateWalletNameRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpdateWalletNameRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
