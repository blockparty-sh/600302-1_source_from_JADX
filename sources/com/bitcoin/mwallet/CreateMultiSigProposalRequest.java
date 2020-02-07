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

public final class CreateMultiSigProposalRequest extends GeneratedMessageLite<CreateMultiSigProposalRequest, Builder> implements CreateMultiSigProposalRequestOrBuilder {
    public static final int COPAYER_ID_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final CreateMultiSigProposalRequest DEFAULT_INSTANCE = new CreateMultiSigProposalRequest();
    public static final int INPUTS_FIELD_NUMBER = 4;
    public static final int MESSAGE_FIELD_NUMBER = 3;
    public static final int OUTPUTS_FIELD_NUMBER = 5;
    private static volatile Parser<CreateMultiSigProposalRequest> PARSER = null;
    public static final int WALLET_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private String copayerId_;
    private ProtobufList<ProposalInput> inputs_ = emptyProtobufList();
    private String message_;
    private ProtobufList<ProposalOutput> outputs_ = emptyProtobufList();
    private String walletId_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<CreateMultiSigProposalRequest, Builder> implements CreateMultiSigProposalRequestOrBuilder {
        private Builder() {
            super(CreateMultiSigProposalRequest.DEFAULT_INSTANCE);
        }

        public String getWalletId() {
            return ((CreateMultiSigProposalRequest) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((CreateMultiSigProposalRequest) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public String getCopayerId() {
            return ((CreateMultiSigProposalRequest) this.instance).getCopayerId();
        }

        public ByteString getCopayerIdBytes() {
            return ((CreateMultiSigProposalRequest) this.instance).getCopayerIdBytes();
        }

        public Builder setCopayerId(String str) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).setCopayerId(str);
            return this;
        }

        public Builder clearCopayerId() {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).clearCopayerId();
            return this;
        }

        public Builder setCopayerIdBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).setCopayerIdBytes(byteString);
            return this;
        }

        public String getMessage() {
            return ((CreateMultiSigProposalRequest) this.instance).getMessage();
        }

        public ByteString getMessageBytes() {
            return ((CreateMultiSigProposalRequest) this.instance).getMessageBytes();
        }

        public Builder setMessage(String str) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).setMessage(str);
            return this;
        }

        public Builder clearMessage() {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).clearMessage();
            return this;
        }

        public Builder setMessageBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).setMessageBytes(byteString);
            return this;
        }

        public List<ProposalInput> getInputsList() {
            return Collections.unmodifiableList(((CreateMultiSigProposalRequest) this.instance).getInputsList());
        }

        public int getInputsCount() {
            return ((CreateMultiSigProposalRequest) this.instance).getInputsCount();
        }

        public ProposalInput getInputs(int i) {
            return ((CreateMultiSigProposalRequest) this.instance).getInputs(i);
        }

        public Builder setInputs(int i, ProposalInput proposalInput) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).setInputs(i, proposalInput);
            return this;
        }

        public Builder setInputs(int i, com.bitcoin.mwallet.ProposalInput.Builder builder) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).setInputs(i, builder);
            return this;
        }

        public Builder addInputs(ProposalInput proposalInput) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).addInputs(proposalInput);
            return this;
        }

        public Builder addInputs(int i, ProposalInput proposalInput) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).addInputs(i, proposalInput);
            return this;
        }

        public Builder addInputs(com.bitcoin.mwallet.ProposalInput.Builder builder) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).addInputs(builder);
            return this;
        }

        public Builder addInputs(int i, com.bitcoin.mwallet.ProposalInput.Builder builder) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).addInputs(i, builder);
            return this;
        }

        public Builder addAllInputs(Iterable<? extends ProposalInput> iterable) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).addAllInputs(iterable);
            return this;
        }

        public Builder clearInputs() {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).clearInputs();
            return this;
        }

        public Builder removeInputs(int i) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).removeInputs(i);
            return this;
        }

        public List<ProposalOutput> getOutputsList() {
            return Collections.unmodifiableList(((CreateMultiSigProposalRequest) this.instance).getOutputsList());
        }

        public int getOutputsCount() {
            return ((CreateMultiSigProposalRequest) this.instance).getOutputsCount();
        }

        public ProposalOutput getOutputs(int i) {
            return ((CreateMultiSigProposalRequest) this.instance).getOutputs(i);
        }

        public Builder setOutputs(int i, ProposalOutput proposalOutput) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).setOutputs(i, proposalOutput);
            return this;
        }

        public Builder setOutputs(int i, com.bitcoin.mwallet.ProposalOutput.Builder builder) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).setOutputs(i, builder);
            return this;
        }

        public Builder addOutputs(ProposalOutput proposalOutput) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).addOutputs(proposalOutput);
            return this;
        }

        public Builder addOutputs(int i, ProposalOutput proposalOutput) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).addOutputs(i, proposalOutput);
            return this;
        }

        public Builder addOutputs(com.bitcoin.mwallet.ProposalOutput.Builder builder) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).addOutputs(builder);
            return this;
        }

        public Builder addOutputs(int i, com.bitcoin.mwallet.ProposalOutput.Builder builder) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).addOutputs(i, builder);
            return this;
        }

        public Builder addAllOutputs(Iterable<? extends ProposalOutput> iterable) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).addAllOutputs(iterable);
            return this;
        }

        public Builder clearOutputs() {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).clearOutputs();
            return this;
        }

        public Builder removeOutputs(int i) {
            copyOnWrite();
            ((CreateMultiSigProposalRequest) this.instance).removeOutputs(i);
            return this;
        }
    }

    private CreateMultiSigProposalRequest() {
        String str = "";
        this.walletId_ = str;
        this.copayerId_ = str;
        this.message_ = str;
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

    public String getMessage() {
        return this.message_;
    }

    public ByteString getMessageBytes() {
        return ByteString.copyFromUtf8(this.message_);
    }

    /* access modifiers changed from: private */
    public void setMessage(String str) {
        if (str != null) {
            this.message_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearMessage() {
        this.message_ = getDefaultInstance().getMessage();
    }

    /* access modifiers changed from: private */
    public void setMessageBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.message_ = byteString.toStringUtf8();
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
        if (!this.message_.isEmpty()) {
            codedOutputStream.writeString(3, getMessage());
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
        if (!this.message_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(3, getMessage());
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

    public static CreateMultiSigProposalRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CreateMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static CreateMultiSigProposalRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static CreateMultiSigProposalRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CreateMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static CreateMultiSigProposalRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static CreateMultiSigProposalRequest parseFrom(InputStream inputStream) throws IOException {
        return (CreateMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateMultiSigProposalRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateMultiSigProposalRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CreateMultiSigProposalRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateMultiSigProposalRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateMultiSigProposalRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateMultiSigProposalRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CreateMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static CreateMultiSigProposalRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateMultiSigProposalRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CreateMultiSigProposalRequest createMultiSigProposalRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(createMultiSigProposalRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new CreateMultiSigProposalRequest();
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
                CreateMultiSigProposalRequest createMultiSigProposalRequest = (CreateMultiSigProposalRequest) obj2;
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, !createMultiSigProposalRequest.walletId_.isEmpty(), createMultiSigProposalRequest.walletId_);
                this.copayerId_ = visitor.visitString(!this.copayerId_.isEmpty(), this.copayerId_, !createMultiSigProposalRequest.copayerId_.isEmpty(), createMultiSigProposalRequest.copayerId_);
                this.message_ = visitor.visitString(!this.message_.isEmpty(), this.message_, true ^ createMultiSigProposalRequest.message_.isEmpty(), createMultiSigProposalRequest.message_);
                this.inputs_ = visitor.visitList(this.inputs_, createMultiSigProposalRequest.inputs_);
                this.outputs_ = visitor.visitList(this.outputs_, createMultiSigProposalRequest.outputs_);
                if (visitor == MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= createMultiSigProposalRequest.bitField0_;
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
                                this.message_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (CreateMultiSigProposalRequest.class) {
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

    public static CreateMultiSigProposalRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateMultiSigProposalRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
