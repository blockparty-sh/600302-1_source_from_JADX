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

public final class GetOldTxNotesRequest extends GeneratedMessageLite<GetOldTxNotesRequest, Builder> implements GetOldTxNotesRequestOrBuilder {
    public static final int COPAYERID_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final GetOldTxNotesRequest DEFAULT_INSTANCE = new GetOldTxNotesRequest();
    private static volatile Parser<GetOldTxNotesRequest> PARSER = null;
    public static final int WALLETID_FIELD_NUMBER = 1;
    private String copayerId_;
    private String walletId_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<GetOldTxNotesRequest, Builder> implements GetOldTxNotesRequestOrBuilder {
        private Builder() {
            super(GetOldTxNotesRequest.DEFAULT_INSTANCE);
        }

        public String getWalletId() {
            return ((GetOldTxNotesRequest) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((GetOldTxNotesRequest) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((GetOldTxNotesRequest) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((GetOldTxNotesRequest) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((GetOldTxNotesRequest) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public String getCopayerId() {
            return ((GetOldTxNotesRequest) this.instance).getCopayerId();
        }

        public ByteString getCopayerIdBytes() {
            return ((GetOldTxNotesRequest) this.instance).getCopayerIdBytes();
        }

        public Builder setCopayerId(String str) {
            copyOnWrite();
            ((GetOldTxNotesRequest) this.instance).setCopayerId(str);
            return this;
        }

        public Builder clearCopayerId() {
            copyOnWrite();
            ((GetOldTxNotesRequest) this.instance).clearCopayerId();
            return this;
        }

        public Builder setCopayerIdBytes(ByteString byteString) {
            copyOnWrite();
            ((GetOldTxNotesRequest) this.instance).setCopayerIdBytes(byteString);
            return this;
        }
    }

    private GetOldTxNotesRequest() {
        String str = "";
        this.walletId_ = str;
        this.copayerId_ = str;
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

    public String getCopayerId() {
        return this.copayerId_;
    }

    public ByteString getCopayerIdBytes() {
        return ByteString.copyFromUtf8(this.copayerId_);
    }

    /* access modifiers changed from: private */
    public void setCopayerId(String str) {
        if (str != null) {
            this.copayerId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearCopayerId() {
        this.copayerId_ = getDefaultInstance().getCopayerId();
    }

    /* access modifiers changed from: private */
    public void setCopayerIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.copayerId_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(1, getWalletId());
        }
        if (!this.copayerId_.isEmpty()) {
            codedOutputStream.writeString(2, getCopayerId());
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
        if (!this.copayerId_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getCopayerId());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static GetOldTxNotesRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (GetOldTxNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static GetOldTxNotesRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetOldTxNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static GetOldTxNotesRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (GetOldTxNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static GetOldTxNotesRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetOldTxNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static GetOldTxNotesRequest parseFrom(InputStream inputStream) throws IOException {
        return (GetOldTxNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetOldTxNotesRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetOldTxNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetOldTxNotesRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (GetOldTxNotesRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetOldTxNotesRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetOldTxNotesRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetOldTxNotesRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (GetOldTxNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static GetOldTxNotesRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetOldTxNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GetOldTxNotesRequest getOldTxNotesRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(getOldTxNotesRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new GetOldTxNotesRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                GetOldTxNotesRequest getOldTxNotesRequest = (GetOldTxNotesRequest) obj2;
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, !getOldTxNotesRequest.walletId_.isEmpty(), getOldTxNotesRequest.walletId_);
                this.copayerId_ = visitor.visitString(!this.copayerId_.isEmpty(), this.copayerId_, true ^ getOldTxNotesRequest.copayerId_.isEmpty(), getOldTxNotesRequest.copayerId_);
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
                                this.copayerId_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (GetOldTxNotesRequest.class) {
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

    public static GetOldTxNotesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GetOldTxNotesRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
