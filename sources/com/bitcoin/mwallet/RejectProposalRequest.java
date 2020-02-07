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

public final class RejectProposalRequest extends GeneratedMessageLite<RejectProposalRequest, Builder> implements RejectProposalRequestOrBuilder {
    public static final int COPAYER_ID_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final RejectProposalRequest DEFAULT_INSTANCE = new RejectProposalRequest();
    private static volatile Parser<RejectProposalRequest> PARSER = null;
    public static final int PROPOSAL_ID_FIELD_NUMBER = 1;
    public static final int REASON_FIELD_NUMBER = 4;
    public static final int WALLET_ID_FIELD_NUMBER = 3;
    private String copayerId_;
    private String proposalId_;
    private String reason_;
    private String walletId_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<RejectProposalRequest, Builder> implements RejectProposalRequestOrBuilder {
        private Builder() {
            super(RejectProposalRequest.DEFAULT_INSTANCE);
        }

        public String getProposalId() {
            return ((RejectProposalRequest) this.instance).getProposalId();
        }

        public ByteString getProposalIdBytes() {
            return ((RejectProposalRequest) this.instance).getProposalIdBytes();
        }

        public Builder setProposalId(String str) {
            copyOnWrite();
            ((RejectProposalRequest) this.instance).setProposalId(str);
            return this;
        }

        public Builder clearProposalId() {
            copyOnWrite();
            ((RejectProposalRequest) this.instance).clearProposalId();
            return this;
        }

        public Builder setProposalIdBytes(ByteString byteString) {
            copyOnWrite();
            ((RejectProposalRequest) this.instance).setProposalIdBytes(byteString);
            return this;
        }

        public String getCopayerId() {
            return ((RejectProposalRequest) this.instance).getCopayerId();
        }

        public ByteString getCopayerIdBytes() {
            return ((RejectProposalRequest) this.instance).getCopayerIdBytes();
        }

        public Builder setCopayerId(String str) {
            copyOnWrite();
            ((RejectProposalRequest) this.instance).setCopayerId(str);
            return this;
        }

        public Builder clearCopayerId() {
            copyOnWrite();
            ((RejectProposalRequest) this.instance).clearCopayerId();
            return this;
        }

        public Builder setCopayerIdBytes(ByteString byteString) {
            copyOnWrite();
            ((RejectProposalRequest) this.instance).setCopayerIdBytes(byteString);
            return this;
        }

        public String getWalletId() {
            return ((RejectProposalRequest) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((RejectProposalRequest) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((RejectProposalRequest) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((RejectProposalRequest) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((RejectProposalRequest) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public String getReason() {
            return ((RejectProposalRequest) this.instance).getReason();
        }

        public ByteString getReasonBytes() {
            return ((RejectProposalRequest) this.instance).getReasonBytes();
        }

        public Builder setReason(String str) {
            copyOnWrite();
            ((RejectProposalRequest) this.instance).setReason(str);
            return this;
        }

        public Builder clearReason() {
            copyOnWrite();
            ((RejectProposalRequest) this.instance).clearReason();
            return this;
        }

        public Builder setReasonBytes(ByteString byteString) {
            copyOnWrite();
            ((RejectProposalRequest) this.instance).setReasonBytes(byteString);
            return this;
        }
    }

    private RejectProposalRequest() {
        String str = "";
        this.proposalId_ = str;
        this.copayerId_ = str;
        this.walletId_ = str;
        this.reason_ = str;
    }

    public String getProposalId() {
        return this.proposalId_;
    }

    public ByteString getProposalIdBytes() {
        return ByteString.copyFromUtf8(this.proposalId_);
    }

    /* access modifiers changed from: private */
    public void setProposalId(String str) {
        if (str != null) {
            this.proposalId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearProposalId() {
        this.proposalId_ = getDefaultInstance().getProposalId();
    }

    /* access modifiers changed from: private */
    public void setProposalIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.proposalId_ = byteString.toStringUtf8();
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

    public String getReason() {
        return this.reason_;
    }

    public ByteString getReasonBytes() {
        return ByteString.copyFromUtf8(this.reason_);
    }

    /* access modifiers changed from: private */
    public void setReason(String str) {
        if (str != null) {
            this.reason_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearReason() {
        this.reason_ = getDefaultInstance().getReason();
    }

    /* access modifiers changed from: private */
    public void setReasonBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.reason_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.proposalId_.isEmpty()) {
            codedOutputStream.writeString(1, getProposalId());
        }
        if (!this.copayerId_.isEmpty()) {
            codedOutputStream.writeString(2, getCopayerId());
        }
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(3, getWalletId());
        }
        if (!this.reason_.isEmpty()) {
            codedOutputStream.writeString(4, getReason());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.proposalId_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getProposalId());
        }
        if (!this.copayerId_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getCopayerId());
        }
        if (!this.walletId_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(3, getWalletId());
        }
        if (!this.reason_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(4, getReason());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static RejectProposalRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RejectProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RejectProposalRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RejectProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RejectProposalRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RejectProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RejectProposalRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RejectProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RejectProposalRequest parseFrom(InputStream inputStream) throws IOException {
        return (RejectProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RejectProposalRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RejectProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RejectProposalRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RejectProposalRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RejectProposalRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RejectProposalRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RejectProposalRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RejectProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RejectProposalRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RejectProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RejectProposalRequest rejectProposalRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(rejectProposalRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new RejectProposalRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                RejectProposalRequest rejectProposalRequest = (RejectProposalRequest) obj2;
                this.proposalId_ = visitor.visitString(!this.proposalId_.isEmpty(), this.proposalId_, !rejectProposalRequest.proposalId_.isEmpty(), rejectProposalRequest.proposalId_);
                this.copayerId_ = visitor.visitString(!this.copayerId_.isEmpty(), this.copayerId_, !rejectProposalRequest.copayerId_.isEmpty(), rejectProposalRequest.copayerId_);
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, !rejectProposalRequest.walletId_.isEmpty(), rejectProposalRequest.walletId_);
                this.reason_ = visitor.visitString(!this.reason_.isEmpty(), this.reason_, true ^ rejectProposalRequest.reason_.isEmpty(), rejectProposalRequest.reason_);
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
                                this.proposalId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.copayerId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.walletId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 34) {
                                this.reason_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (RejectProposalRequest.class) {
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

    public static RejectProposalRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RejectProposalRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
