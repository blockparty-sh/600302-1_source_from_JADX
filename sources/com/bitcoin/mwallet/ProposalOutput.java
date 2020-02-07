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

public final class ProposalOutput extends GeneratedMessageLite<ProposalOutput, Builder> implements ProposalOutputOrBuilder {
    public static final int AMOUNT_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final ProposalOutput DEFAULT_INSTANCE = new ProposalOutput();
    private static volatile Parser<ProposalOutput> PARSER = null;
    public static final int TO_ADDRESS_FIELD_NUMBER = 2;
    private long amount_;
    private String toAddress_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<ProposalOutput, Builder> implements ProposalOutputOrBuilder {
        private Builder() {
            super(ProposalOutput.DEFAULT_INSTANCE);
        }

        public long getAmount() {
            return ((ProposalOutput) this.instance).getAmount();
        }

        public Builder setAmount(long j) {
            copyOnWrite();
            ((ProposalOutput) this.instance).setAmount(j);
            return this;
        }

        public Builder clearAmount() {
            copyOnWrite();
            ((ProposalOutput) this.instance).clearAmount();
            return this;
        }

        public String getToAddress() {
            return ((ProposalOutput) this.instance).getToAddress();
        }

        public ByteString getToAddressBytes() {
            return ((ProposalOutput) this.instance).getToAddressBytes();
        }

        public Builder setToAddress(String str) {
            copyOnWrite();
            ((ProposalOutput) this.instance).setToAddress(str);
            return this;
        }

        public Builder clearToAddress() {
            copyOnWrite();
            ((ProposalOutput) this.instance).clearToAddress();
            return this;
        }

        public Builder setToAddressBytes(ByteString byteString) {
            copyOnWrite();
            ((ProposalOutput) this.instance).setToAddressBytes(byteString);
            return this;
        }
    }

    private ProposalOutput() {
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

    public String getToAddress() {
        return this.toAddress_;
    }

    public ByteString getToAddressBytes() {
        return ByteString.copyFromUtf8(this.toAddress_);
    }

    /* access modifiers changed from: private */
    public void setToAddress(String str) {
        if (str != null) {
            this.toAddress_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearToAddress() {
        this.toAddress_ = getDefaultInstance().getToAddress();
    }

    /* access modifiers changed from: private */
    public void setToAddressBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.toAddress_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        long j = this.amount_;
        if (j != 0) {
            codedOutputStream.writeInt64(1, j);
        }
        if (!this.toAddress_.isEmpty()) {
            codedOutputStream.writeString(2, getToAddress());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        long j = this.amount_;
        if (j != 0) {
            i2 = 0 + CodedOutputStream.computeInt64Size(1, j);
        }
        if (!this.toAddress_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getToAddress());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static ProposalOutput parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ProposalOutput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ProposalOutput parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProposalOutput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ProposalOutput parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ProposalOutput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ProposalOutput parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProposalOutput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ProposalOutput parseFrom(InputStream inputStream) throws IOException {
        return (ProposalOutput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ProposalOutput parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProposalOutput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ProposalOutput parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ProposalOutput) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ProposalOutput parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProposalOutput) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ProposalOutput parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ProposalOutput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ProposalOutput parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProposalOutput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProposalOutput proposalOutput) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(proposalOutput);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new ProposalOutput();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                ProposalOutput proposalOutput = (ProposalOutput) obj2;
                boolean z2 = this.amount_ != 0;
                long j = this.amount_;
                if (proposalOutput.amount_ != 0) {
                    z = true;
                }
                this.amount_ = visitor.visitLong(z2, j, z, proposalOutput.amount_);
                this.toAddress_ = visitor.visitString(!this.toAddress_.isEmpty(), this.toAddress_, !proposalOutput.toAddress_.isEmpty(), proposalOutput.toAddress_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.amount_ = codedInputStream.readInt64();
                            } else if (readTag == 18) {
                                this.toAddress_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (ProposalOutput.class) {
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

    public static ProposalOutput getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProposalOutput> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
