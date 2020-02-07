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

public final class GetNewMaxAddressPathRequest extends GeneratedMessageLite<GetNewMaxAddressPathRequest, Builder> implements GetNewMaxAddressPathRequestOrBuilder {
    public static final int CURRENT_MAX_PATH_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final GetNewMaxAddressPathRequest DEFAULT_INSTANCE = new GetNewMaxAddressPathRequest();
    private static volatile Parser<GetNewMaxAddressPathRequest> PARSER = null;
    public static final int WALLET_ID_FIELD_NUMBER = 1;
    private AddressPath currentMaxPath_;
    private String walletId_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<GetNewMaxAddressPathRequest, Builder> implements GetNewMaxAddressPathRequestOrBuilder {
        private Builder() {
            super(GetNewMaxAddressPathRequest.DEFAULT_INSTANCE);
        }

        public String getWalletId() {
            return ((GetNewMaxAddressPathRequest) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((GetNewMaxAddressPathRequest) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((GetNewMaxAddressPathRequest) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((GetNewMaxAddressPathRequest) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((GetNewMaxAddressPathRequest) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public boolean hasCurrentMaxPath() {
            return ((GetNewMaxAddressPathRequest) this.instance).hasCurrentMaxPath();
        }

        public AddressPath getCurrentMaxPath() {
            return ((GetNewMaxAddressPathRequest) this.instance).getCurrentMaxPath();
        }

        public Builder setCurrentMaxPath(AddressPath addressPath) {
            copyOnWrite();
            ((GetNewMaxAddressPathRequest) this.instance).setCurrentMaxPath(addressPath);
            return this;
        }

        public Builder setCurrentMaxPath(com.bitcoin.mwallet.AddressPath.Builder builder) {
            copyOnWrite();
            ((GetNewMaxAddressPathRequest) this.instance).setCurrentMaxPath(builder);
            return this;
        }

        public Builder mergeCurrentMaxPath(AddressPath addressPath) {
            copyOnWrite();
            ((GetNewMaxAddressPathRequest) this.instance).mergeCurrentMaxPath(addressPath);
            return this;
        }

        public Builder clearCurrentMaxPath() {
            copyOnWrite();
            ((GetNewMaxAddressPathRequest) this.instance).clearCurrentMaxPath();
            return this;
        }
    }

    private GetNewMaxAddressPathRequest() {
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

    public boolean hasCurrentMaxPath() {
        return this.currentMaxPath_ != null;
    }

    public AddressPath getCurrentMaxPath() {
        AddressPath addressPath = this.currentMaxPath_;
        return addressPath == null ? AddressPath.getDefaultInstance() : addressPath;
    }

    /* access modifiers changed from: private */
    public void setCurrentMaxPath(AddressPath addressPath) {
        if (addressPath != null) {
            this.currentMaxPath_ = addressPath;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setCurrentMaxPath(com.bitcoin.mwallet.AddressPath.Builder builder) {
        this.currentMaxPath_ = (AddressPath) builder.build();
    }

    /* access modifiers changed from: private */
    public void mergeCurrentMaxPath(AddressPath addressPath) {
        AddressPath addressPath2 = this.currentMaxPath_;
        if (addressPath2 == null || addressPath2 == AddressPath.getDefaultInstance()) {
            this.currentMaxPath_ = addressPath;
        } else {
            this.currentMaxPath_ = (AddressPath) ((com.bitcoin.mwallet.AddressPath.Builder) AddressPath.newBuilder(this.currentMaxPath_).mergeFrom(addressPath)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearCurrentMaxPath() {
        this.currentMaxPath_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(1, getWalletId());
        }
        if (this.currentMaxPath_ != null) {
            codedOutputStream.writeMessage(2, getCurrentMaxPath());
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
        if (this.currentMaxPath_ != null) {
            i2 += CodedOutputStream.computeMessageSize(2, getCurrentMaxPath());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static GetNewMaxAddressPathRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (GetNewMaxAddressPathRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static GetNewMaxAddressPathRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetNewMaxAddressPathRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static GetNewMaxAddressPathRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (GetNewMaxAddressPathRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static GetNewMaxAddressPathRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetNewMaxAddressPathRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static GetNewMaxAddressPathRequest parseFrom(InputStream inputStream) throws IOException {
        return (GetNewMaxAddressPathRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetNewMaxAddressPathRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetNewMaxAddressPathRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetNewMaxAddressPathRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (GetNewMaxAddressPathRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetNewMaxAddressPathRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetNewMaxAddressPathRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetNewMaxAddressPathRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (GetNewMaxAddressPathRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static GetNewMaxAddressPathRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetNewMaxAddressPathRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GetNewMaxAddressPathRequest getNewMaxAddressPathRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(getNewMaxAddressPathRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new GetNewMaxAddressPathRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                GetNewMaxAddressPathRequest getNewMaxAddressPathRequest = (GetNewMaxAddressPathRequest) obj2;
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, true ^ getNewMaxAddressPathRequest.walletId_.isEmpty(), getNewMaxAddressPathRequest.walletId_);
                this.currentMaxPath_ = (AddressPath) visitor.visitMessage(this.currentMaxPath_, getNewMaxAddressPathRequest.currentMaxPath_);
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
                                com.bitcoin.mwallet.AddressPath.Builder builder = this.currentMaxPath_ != null ? (com.bitcoin.mwallet.AddressPath.Builder) this.currentMaxPath_.toBuilder() : null;
                                this.currentMaxPath_ = (AddressPath) codedInputStream.readMessage(AddressPath.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.currentMaxPath_);
                                    this.currentMaxPath_ = (AddressPath) builder.buildPartial();
                                }
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
                    synchronized (GetNewMaxAddressPathRequest.class) {
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

    public static GetNewMaxAddressPathRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GetNewMaxAddressPathRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
