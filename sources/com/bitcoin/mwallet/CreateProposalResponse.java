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

public final class CreateProposalResponse extends GeneratedMessageLite<CreateProposalResponse, Builder> implements CreateProposalResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final CreateProposalResponse DEFAULT_INSTANCE = new CreateProposalResponse();
    private static volatile Parser<CreateProposalResponse> PARSER = null;
    public static final int PROPOSAL_ID_FIELD_NUMBER = 1;
    public static final int WALLET_ID_FIELD_NUMBER = 2;
    private String proposalId_;
    private String walletId_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<CreateProposalResponse, Builder> implements CreateProposalResponseOrBuilder {
        private Builder() {
            super(CreateProposalResponse.DEFAULT_INSTANCE);
        }

        public String getProposalId() {
            return ((CreateProposalResponse) this.instance).getProposalId();
        }

        public ByteString getProposalIdBytes() {
            return ((CreateProposalResponse) this.instance).getProposalIdBytes();
        }

        public Builder setProposalId(String str) {
            copyOnWrite();
            ((CreateProposalResponse) this.instance).setProposalId(str);
            return this;
        }

        public Builder clearProposalId() {
            copyOnWrite();
            ((CreateProposalResponse) this.instance).clearProposalId();
            return this;
        }

        public Builder setProposalIdBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateProposalResponse) this.instance).setProposalIdBytes(byteString);
            return this;
        }

        public String getWalletId() {
            return ((CreateProposalResponse) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((CreateProposalResponse) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((CreateProposalResponse) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((CreateProposalResponse) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateProposalResponse) this.instance).setWalletIdBytes(byteString);
            return this;
        }
    }

    private CreateProposalResponse() {
        String str = "";
        this.proposalId_ = str;
        this.walletId_ = str;
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
        if (!this.proposalId_.isEmpty()) {
            codedOutputStream.writeString(1, getProposalId());
        }
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(2, getWalletId());
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
        if (!this.walletId_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getWalletId());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static CreateProposalResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CreateProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static CreateProposalResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static CreateProposalResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CreateProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static CreateProposalResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static CreateProposalResponse parseFrom(InputStream inputStream) throws IOException {
        return (CreateProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateProposalResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateProposalResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CreateProposalResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateProposalResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateProposalResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateProposalResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CreateProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static CreateProposalResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CreateProposalResponse createProposalResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(createProposalResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new CreateProposalResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                CreateProposalResponse createProposalResponse = (CreateProposalResponse) obj2;
                this.proposalId_ = visitor.visitString(!this.proposalId_.isEmpty(), this.proposalId_, !createProposalResponse.proposalId_.isEmpty(), createProposalResponse.proposalId_);
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, true ^ createProposalResponse.walletId_.isEmpty(), createProposalResponse.walletId_);
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
                    synchronized (CreateProposalResponse.class) {
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

    public static CreateProposalResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateProposalResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
