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

public final class GetTxHistoryRequest extends GeneratedMessageLite<GetTxHistoryRequest, Builder> implements GetTxHistoryRequestOrBuilder {
    public static final int COPAYER_ID_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final GetTxHistoryRequest DEFAULT_INSTANCE = new GetTxHistoryRequest();
    public static final int EPOCH_MILLIS_START_FIELD_NUMBER = 2;
    private static volatile Parser<GetTxHistoryRequest> PARSER = null;
    public static final int WALLET_ID_FIELD_NUMBER = 1;
    private String copayerId_;
    private long epochMillisStart_;
    private String walletId_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<GetTxHistoryRequest, Builder> implements GetTxHistoryRequestOrBuilder {
        private Builder() {
            super(GetTxHistoryRequest.DEFAULT_INSTANCE);
        }

        public String getWalletId() {
            return ((GetTxHistoryRequest) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((GetTxHistoryRequest) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((GetTxHistoryRequest) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((GetTxHistoryRequest) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((GetTxHistoryRequest) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public long getEpochMillisStart() {
            return ((GetTxHistoryRequest) this.instance).getEpochMillisStart();
        }

        public Builder setEpochMillisStart(long j) {
            copyOnWrite();
            ((GetTxHistoryRequest) this.instance).setEpochMillisStart(j);
            return this;
        }

        public Builder clearEpochMillisStart() {
            copyOnWrite();
            ((GetTxHistoryRequest) this.instance).clearEpochMillisStart();
            return this;
        }

        public String getCopayerId() {
            return ((GetTxHistoryRequest) this.instance).getCopayerId();
        }

        public ByteString getCopayerIdBytes() {
            return ((GetTxHistoryRequest) this.instance).getCopayerIdBytes();
        }

        public Builder setCopayerId(String str) {
            copyOnWrite();
            ((GetTxHistoryRequest) this.instance).setCopayerId(str);
            return this;
        }

        public Builder clearCopayerId() {
            copyOnWrite();
            ((GetTxHistoryRequest) this.instance).clearCopayerId();
            return this;
        }

        public Builder setCopayerIdBytes(ByteString byteString) {
            copyOnWrite();
            ((GetTxHistoryRequest) this.instance).setCopayerIdBytes(byteString);
            return this;
        }
    }

    private GetTxHistoryRequest() {
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

    public long getEpochMillisStart() {
        return this.epochMillisStart_;
    }

    /* access modifiers changed from: private */
    public void setEpochMillisStart(long j) {
        this.epochMillisStart_ = j;
    }

    /* access modifiers changed from: private */
    public void clearEpochMillisStart() {
        this.epochMillisStart_ = 0;
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
        long j = this.epochMillisStart_;
        if (j != 0) {
            codedOutputStream.writeUInt64(2, j);
        }
        if (!this.copayerId_.isEmpty()) {
            codedOutputStream.writeString(3, getCopayerId());
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
        long j = this.epochMillisStart_;
        if (j != 0) {
            i2 += CodedOutputStream.computeUInt64Size(2, j);
        }
        if (!this.copayerId_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(3, getCopayerId());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static GetTxHistoryRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (GetTxHistoryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static GetTxHistoryRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetTxHistoryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static GetTxHistoryRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (GetTxHistoryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static GetTxHistoryRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetTxHistoryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static GetTxHistoryRequest parseFrom(InputStream inputStream) throws IOException {
        return (GetTxHistoryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetTxHistoryRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetTxHistoryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetTxHistoryRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (GetTxHistoryRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetTxHistoryRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetTxHistoryRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetTxHistoryRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (GetTxHistoryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static GetTxHistoryRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetTxHistoryRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GetTxHistoryRequest getTxHistoryRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(getTxHistoryRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new GetTxHistoryRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                GetTxHistoryRequest getTxHistoryRequest = (GetTxHistoryRequest) obj2;
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, !getTxHistoryRequest.walletId_.isEmpty(), getTxHistoryRequest.walletId_);
                boolean z2 = this.epochMillisStart_ != 0;
                long j = this.epochMillisStart_;
                if (getTxHistoryRequest.epochMillisStart_ != 0) {
                    z = true;
                }
                this.epochMillisStart_ = visitor.visitLong(z2, j, z, getTxHistoryRequest.epochMillisStart_);
                this.copayerId_ = visitor.visitString(!this.copayerId_.isEmpty(), this.copayerId_, !getTxHistoryRequest.copayerId_.isEmpty(), getTxHistoryRequest.copayerId_);
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
                                this.walletId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 16) {
                                this.epochMillisStart_ = codedInputStream.readUInt64();
                            } else if (readTag == 26) {
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
                    synchronized (GetTxHistoryRequest.class) {
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

    public static GetTxHistoryRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GetTxHistoryRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
