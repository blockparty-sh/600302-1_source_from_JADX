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

public final class ScanForFundsRequest extends GeneratedMessageLite<ScanForFundsRequest, Builder> implements ScanForFundsRequestOrBuilder {
    /* access modifiers changed from: private */
    public static final ScanForFundsRequest DEFAULT_INSTANCE = new ScanForFundsRequest();
    private static volatile Parser<ScanForFundsRequest> PARSER = null;
    public static final int WALLET_ID_FIELD_NUMBER = 1;
    private String walletId_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<ScanForFundsRequest, Builder> implements ScanForFundsRequestOrBuilder {
        private Builder() {
            super(ScanForFundsRequest.DEFAULT_INSTANCE);
        }

        public String getWalletId() {
            return ((ScanForFundsRequest) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((ScanForFundsRequest) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((ScanForFundsRequest) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((ScanForFundsRequest) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((ScanForFundsRequest) this.instance).setWalletIdBytes(byteString);
            return this;
        }
    }

    private ScanForFundsRequest() {
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

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(1, getWalletId());
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
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static ScanForFundsRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ScanForFundsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ScanForFundsRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ScanForFundsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ScanForFundsRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ScanForFundsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ScanForFundsRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ScanForFundsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ScanForFundsRequest parseFrom(InputStream inputStream) throws IOException {
        return (ScanForFundsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ScanForFundsRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ScanForFundsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ScanForFundsRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ScanForFundsRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ScanForFundsRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ScanForFundsRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ScanForFundsRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ScanForFundsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ScanForFundsRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ScanForFundsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ScanForFundsRequest scanForFundsRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(scanForFundsRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new ScanForFundsRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                ScanForFundsRequest scanForFundsRequest = (ScanForFundsRequest) obj2;
                this.walletId_ = ((Visitor) obj).visitString(!this.walletId_.isEmpty(), this.walletId_, true ^ scanForFundsRequest.walletId_.isEmpty(), scanForFundsRequest.walletId_);
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
                    synchronized (ScanForFundsRequest.class) {
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

    public static ScanForFundsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ScanForFundsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
