package com.bitcoin.mwallet;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.Internal.ProtobufList;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class SignMultiSigProposalRequest extends GeneratedMessageLite<SignMultiSigProposalRequest, Builder> implements SignMultiSigProposalRequestOrBuilder {
    public static final int COPAYER_ID_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final SignMultiSigProposalRequest DEFAULT_INSTANCE = new SignMultiSigProposalRequest();
    public static final int INPUTS_FIELD_NUMBER = 4;
    public static final int OUTPUTS_FIELD_NUMBER = 5;
    private static volatile Parser<SignMultiSigProposalRequest> PARSER = null;
    public static final int PROPOSAL_ID_FIELD_NUMBER = 3;
    public static final int WALLET_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private String copayerId_;
    private ProtobufList<ProposalInput> inputs_ = emptyProtobufList();
    private ProtobufList<ProposalOutput> outputs_ = emptyProtobufList();
    private String proposalId_;
    private String walletId_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<SignMultiSigProposalRequest, Builder> implements SignMultiSigProposalRequestOrBuilder {
        private Builder() {
            super(SignMultiSigProposalRequest.DEFAULT_INSTANCE);
        }

        public String getWalletId() {
            return ((SignMultiSigProposalRequest) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((SignMultiSigProposalRequest) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public String getCopayerId() {
            return ((SignMultiSigProposalRequest) this.instance).getCopayerId();
        }

        public ByteString getCopayerIdBytes() {
            return ((SignMultiSigProposalRequest) this.instance).getCopayerIdBytes();
        }

        public Builder setCopayerId(String str) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).setCopayerId(str);
            return this;
        }

        public Builder clearCopayerId() {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).clearCopayerId();
            return this;
        }

        public Builder setCopayerIdBytes(ByteString byteString) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).setCopayerIdBytes(byteString);
            return this;
        }

        public String getProposalId() {
            return ((SignMultiSigProposalRequest) this.instance).getProposalId();
        }

        public ByteString getProposalIdBytes() {
            return ((SignMultiSigProposalRequest) this.instance).getProposalIdBytes();
        }

        public Builder setProposalId(String str) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).setProposalId(str);
            return this;
        }

        public Builder clearProposalId() {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).clearProposalId();
            return this;
        }

        public Builder setProposalIdBytes(ByteString byteString) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).setProposalIdBytes(byteString);
            return this;
        }

        public List<ProposalInput> getInputsList() {
            return Collections.unmodifiableList(((SignMultiSigProposalRequest) this.instance).getInputsList());
        }

        public int getInputsCount() {
            return ((SignMultiSigProposalRequest) this.instance).getInputsCount();
        }

        public ProposalInput getInputs(int i) {
            return ((SignMultiSigProposalRequest) this.instance).getInputs(i);
        }

        public Builder setInputs(int i, ProposalInput proposalInput) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).setInputs(i, proposalInput);
            return this;
        }

        public Builder setInputs(int i, com.bitcoin.mwallet.ProposalInput.Builder builder) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).setInputs(i, builder);
            return this;
        }

        public Builder addInputs(ProposalInput proposalInput) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).addInputs(proposalInput);
            return this;
        }

        public Builder addInputs(int i, ProposalInput proposalInput) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).addInputs(i, proposalInput);
            return this;
        }

        public Builder addInputs(com.bitcoin.mwallet.ProposalInput.Builder builder) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).addInputs(builder);
            return this;
        }

        public Builder addInputs(int i, com.bitcoin.mwallet.ProposalInput.Builder builder) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).addInputs(i, builder);
            return this;
        }

        public Builder addAllInputs(Iterable<? extends ProposalInput> iterable) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).addAllInputs(iterable);
            return this;
        }

        public Builder clearInputs() {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).clearInputs();
            return this;
        }

        public Builder removeInputs(int i) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).removeInputs(i);
            return this;
        }

        public List<ProposalOutput> getOutputsList() {
            return Collections.unmodifiableList(((SignMultiSigProposalRequest) this.instance).getOutputsList());
        }

        public int getOutputsCount() {
            return ((SignMultiSigProposalRequest) this.instance).getOutputsCount();
        }

        public ProposalOutput getOutputs(int i) {
            return ((SignMultiSigProposalRequest) this.instance).getOutputs(i);
        }

        public Builder setOutputs(int i, ProposalOutput proposalOutput) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).setOutputs(i, proposalOutput);
            return this;
        }

        public Builder setOutputs(int i, com.bitcoin.mwallet.ProposalOutput.Builder builder) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).setOutputs(i, builder);
            return this;
        }

        public Builder addOutputs(ProposalOutput proposalOutput) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).addOutputs(proposalOutput);
            return this;
        }

        public Builder addOutputs(int i, ProposalOutput proposalOutput) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).addOutputs(i, proposalOutput);
            return this;
        }

        public Builder addOutputs(com.bitcoin.mwallet.ProposalOutput.Builder builder) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).addOutputs(builder);
            return this;
        }

        public Builder addOutputs(int i, com.bitcoin.mwallet.ProposalOutput.Builder builder) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).addOutputs(i, builder);
            return this;
        }

        public Builder addAllOutputs(Iterable<? extends ProposalOutput> iterable) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).addAllOutputs(iterable);
            return this;
        }

        public Builder clearOutputs() {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).clearOutputs();
            return this;
        }

        public Builder removeOutputs(int i) {
            copyOnWrite();
            ((SignMultiSigProposalRequest) this.instance).removeOutputs(i);
            return this;
        }
    }

    private SignMultiSigProposalRequest() {
        String str = "";
        this.walletId_ = str;
        this.copayerId_ = str;
        this.proposalId_ = str;
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

    public List<ProposalInput> getInputsList() {
        return this.inputs_;
    }

    public List<? extends ProposalInputOrBuilder> getInputsOrBuilderList() {
        return this.inputs_;
    }

    public int getInputsCount() {
        return this.inputs_.size();
    }

    public ProposalInput getInputs(int i) {
        return (ProposalInput) this.inputs_.get(i);
    }

    public ProposalInputOrBuilder getInputsOrBuilder(int i) {
        return (ProposalInputOrBuilder) this.inputs_.get(i);
    }

    private void ensureInputsIsMutable() {
        if (!this.inputs_.isModifiable()) {
            this.inputs_ = GeneratedMessageLite.mutableCopy(this.inputs_);
        }
    }

    /* access modifiers changed from: private */
    public void setInputs(int i, ProposalInput proposalInput) {
        if (proposalInput != null) {
            ensureInputsIsMutable();
            this.inputs_.set(i, proposalInput);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setInputs(int i, com.bitcoin.mwallet.ProposalInput.Builder builder) {
        ensureInputsIsMutable();
        this.inputs_.set(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addInputs(ProposalInput proposalInput) {
        if (proposalInput != null) {
            ensureInputsIsMutable();
            this.inputs_.add(proposalInput);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addInputs(int i, ProposalInput proposalInput) {
        if (proposalInput != null) {
            ensureInputsIsMutable();
            this.inputs_.add(i, proposalInput);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addInputs(com.bitcoin.mwallet.ProposalInput.Builder builder) {
        ensureInputsIsMutable();
        this.inputs_.add(builder.build());
    }

    /* access modifiers changed from: private */
    public void addInputs(int i, com.bitcoin.mwallet.ProposalInput.Builder builder) {
        ensureInputsIsMutable();
        this.inputs_.add(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllInputs(Iterable<? extends ProposalInput> iterable) {
        ensureInputsIsMutable();
        AbstractMessageLite.addAll(iterable, this.inputs_);
    }

    /* access modifiers changed from: private */
    public void clearInputs() {
        this.inputs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeInputs(int i) {
        ensureInputsIsMutable();
        this.inputs_.remove(i);
    }

    public List<ProposalOutput> getOutputsList() {
        return this.outputs_;
    }

    public List<? extends ProposalOutputOrBuilder> getOutputsOrBuilderList() {
        return this.outputs_;
    }

    public int getOutputsCount() {
        return this.outputs_.size();
    }

    public ProposalOutput getOutputs(int i) {
        return (ProposalOutput) this.outputs_.get(i);
    }

    public ProposalOutputOrBuilder getOutputsOrBuilder(int i) {
        return (ProposalOutputOrBuilder) this.outputs_.get(i);
    }

    private void ensureOutputsIsMutable() {
        if (!this.outputs_.isModifiable()) {
            this.outputs_ = GeneratedMessageLite.mutableCopy(this.outputs_);
        }
    }

    /* access modifiers changed from: private */
    public void setOutputs(int i, ProposalOutput proposalOutput) {
        if (proposalOutput != null) {
            ensureOutputsIsMutable();
            this.outputs_.set(i, proposalOutput);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setOutputs(int i, com.bitcoin.mwallet.ProposalOutput.Builder builder) {
        ensureOutputsIsMutable();
        this.outputs_.set(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addOutputs(ProposalOutput proposalOutput) {
        if (proposalOutput != null) {
            ensureOutputsIsMutable();
            this.outputs_.add(proposalOutput);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addOutputs(int i, ProposalOutput proposalOutput) {
        if (proposalOutput != null) {
            ensureOutputsIsMutable();
            this.outputs_.add(i, proposalOutput);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addOutputs(com.bitcoin.mwallet.ProposalOutput.Builder builder) {
        ensureOutputsIsMutable();
        this.outputs_.add(builder.build());
    }

    /* access modifiers changed from: private */
    public void addOutputs(int i, com.bitcoin.mwallet.ProposalOutput.Builder builder) {
        ensureOutputsIsMutable();
        this.outputs_.add(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllOutputs(Iterable<? extends ProposalOutput> iterable) {
        ensureOutputsIsMutable();
        AbstractMessageLite.addAll(iterable, this.outputs_);
    }

    /* access modifiers changed from: private */
    public void clearOutputs() {
        this.outputs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeOutputs(int i) {
        ensureOutputsIsMutable();
        this.outputs_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(1, getWalletId());
        }
        if (!this.copayerId_.isEmpty()) {
            codedOutputStream.writeString(2, getCopayerId());
        }
        if (!this.proposalId_.isEmpty()) {
            codedOutputStream.writeString(3, getProposalId());
        }
        for (int i = 0; i < this.inputs_.size(); i++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.inputs_.get(i));
        }
        for (int i2 = 0; i2 < this.outputs_.size(); i2++) {
            codedOutputStream.writeMessage(5, (MessageLite) this.outputs_.get(i2));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !this.walletId_.isEmpty() ? CodedOutputStream.computeStringSize(1, getWalletId()) + 0 : 0;
        if (!this.copayerId_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(2, getCopayerId());
        }
        if (!this.proposalId_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(3, getProposalId());
        }
        int i2 = computeStringSize;
        for (int i3 = 0; i3 < this.inputs_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.inputs_.get(i3));
        }
        for (int i4 = 0; i4 < this.outputs_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(5, (MessageLite) this.outputs_.get(i4));
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static SignMultiSigProposalRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SignMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static SignMultiSigProposalRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SignMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static SignMultiSigProposalRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SignMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static SignMultiSigProposalRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SignMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static SignMultiSigProposalRequest parseFrom(InputStream inputStream) throws IOException {
        return (SignMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SignMultiSigProposalRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SignMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SignMultiSigProposalRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SignMultiSigProposalRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SignMultiSigProposalRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SignMultiSigProposalRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SignMultiSigProposalRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SignMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static SignMultiSigProposalRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SignMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SignMultiSigProposalRequest signMultiSigProposalRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(signMultiSigProposalRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new SignMultiSigProposalRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.inputs_.makeImmutable();
                this.outputs_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                SignMultiSigProposalRequest signMultiSigProposalRequest = (SignMultiSigProposalRequest) obj2;
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, !signMultiSigProposalRequest.walletId_.isEmpty(), signMultiSigProposalRequest.walletId_);
                this.copayerId_ = visitor.visitString(!this.copayerId_.isEmpty(), this.copayerId_, !signMultiSigProposalRequest.copayerId_.isEmpty(), signMultiSigProposalRequest.copayerId_);
                this.proposalId_ = visitor.visitString(!this.proposalId_.isEmpty(), this.proposalId_, true ^ signMultiSigProposalRequest.proposalId_.isEmpty(), signMultiSigProposalRequest.proposalId_);
                this.inputs_ = visitor.visitList(this.inputs_, signMultiSigProposalRequest.inputs_);
                this.outputs_ = visitor.visitList(this.outputs_, signMultiSigProposalRequest.outputs_);
                if (visitor == MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= signMultiSigProposalRequest.bitField0_;
                }
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
                            } else if (readTag == 26) {
                                this.proposalId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 34) {
                                if (!this.inputs_.isModifiable()) {
                                    this.inputs_ = GeneratedMessageLite.mutableCopy(this.inputs_);
                                }
                                this.inputs_.add(codedInputStream.readMessage(ProposalInput.parser(), extensionRegistryLite));
                            } else if (readTag == 42) {
                                if (!this.outputs_.isModifiable()) {
                                    this.outputs_ = GeneratedMessageLite.mutableCopy(this.outputs_);
                                }
                                this.outputs_.add(codedInputStream.readMessage(ProposalOutput.parser(), extensionRegistryLite));
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
                    synchronized (SignMultiSigProposalRequest.class) {
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

    public static SignMultiSigProposalRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SignMultiSigProposalRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
