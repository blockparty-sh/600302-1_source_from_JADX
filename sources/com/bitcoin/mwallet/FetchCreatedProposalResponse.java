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

public final class FetchCreatedProposalResponse extends GeneratedMessageLite<FetchCreatedProposalResponse, Builder> implements FetchCreatedProposalResponseOrBuilder {
    public static final int CREATOR_COPAYER_ID_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final FetchCreatedProposalResponse DEFAULT_INSTANCE = new FetchCreatedProposalResponse();
    public static final int INPUTS_FIELD_NUMBER = 6;
    public static final int MESSAGE_FIELD_NUMBER = 4;
    public static final int OUTPUTS_FIELD_NUMBER = 7;
    private static volatile Parser<FetchCreatedProposalResponse> PARSER = null;
    public static final int PROPOSAL_ID_FIELD_NUMBER = 1;
    public static final int UNIX_TIMESTAMP_FIELD_NUMBER = 5;
    public static final int WALLET_ID_FIELD_NUMBER = 2;
    private int bitField0_;
    private String creatorCopayerId_;
    private ProtobufList<Utxo> inputs_ = emptyProtobufList();
    private String message_;
    private ProtobufList<ProposalOutput> outputs_ = emptyProtobufList();
    private String proposalId_;
    private long unixTimestamp_;
    private String walletId_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<FetchCreatedProposalResponse, Builder> implements FetchCreatedProposalResponseOrBuilder {
        private Builder() {
            super(FetchCreatedProposalResponse.DEFAULT_INSTANCE);
        }

        public String getProposalId() {
            return ((FetchCreatedProposalResponse) this.instance).getProposalId();
        }

        public ByteString getProposalIdBytes() {
            return ((FetchCreatedProposalResponse) this.instance).getProposalIdBytes();
        }

        public Builder setProposalId(String str) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).setProposalId(str);
            return this;
        }

        public Builder clearProposalId() {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).clearProposalId();
            return this;
        }

        public Builder setProposalIdBytes(ByteString byteString) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).setProposalIdBytes(byteString);
            return this;
        }

        public String getWalletId() {
            return ((FetchCreatedProposalResponse) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((FetchCreatedProposalResponse) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public String getCreatorCopayerId() {
            return ((FetchCreatedProposalResponse) this.instance).getCreatorCopayerId();
        }

        public ByteString getCreatorCopayerIdBytes() {
            return ((FetchCreatedProposalResponse) this.instance).getCreatorCopayerIdBytes();
        }

        public Builder setCreatorCopayerId(String str) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).setCreatorCopayerId(str);
            return this;
        }

        public Builder clearCreatorCopayerId() {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).clearCreatorCopayerId();
            return this;
        }

        public Builder setCreatorCopayerIdBytes(ByteString byteString) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).setCreatorCopayerIdBytes(byteString);
            return this;
        }

        public String getMessage() {
            return ((FetchCreatedProposalResponse) this.instance).getMessage();
        }

        public ByteString getMessageBytes() {
            return ((FetchCreatedProposalResponse) this.instance).getMessageBytes();
        }

        public Builder setMessage(String str) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).setMessage(str);
            return this;
        }

        public Builder clearMessage() {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).clearMessage();
            return this;
        }

        public Builder setMessageBytes(ByteString byteString) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).setMessageBytes(byteString);
            return this;
        }

        public long getUnixTimestamp() {
            return ((FetchCreatedProposalResponse) this.instance).getUnixTimestamp();
        }

        public Builder setUnixTimestamp(long j) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).setUnixTimestamp(j);
            return this;
        }

        public Builder clearUnixTimestamp() {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).clearUnixTimestamp();
            return this;
        }

        public List<Utxo> getInputsList() {
            return Collections.unmodifiableList(((FetchCreatedProposalResponse) this.instance).getInputsList());
        }

        public int getInputsCount() {
            return ((FetchCreatedProposalResponse) this.instance).getInputsCount();
        }

        public Utxo getInputs(int i) {
            return ((FetchCreatedProposalResponse) this.instance).getInputs(i);
        }

        public Builder setInputs(int i, Utxo utxo) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).setInputs(i, utxo);
            return this;
        }

        public Builder setInputs(int i, com.bitcoin.mwallet.Utxo.Builder builder) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).setInputs(i, builder);
            return this;
        }

        public Builder addInputs(Utxo utxo) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).addInputs(utxo);
            return this;
        }

        public Builder addInputs(int i, Utxo utxo) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).addInputs(i, utxo);
            return this;
        }

        public Builder addInputs(com.bitcoin.mwallet.Utxo.Builder builder) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).addInputs(builder);
            return this;
        }

        public Builder addInputs(int i, com.bitcoin.mwallet.Utxo.Builder builder) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).addInputs(i, builder);
            return this;
        }

        public Builder addAllInputs(Iterable<? extends Utxo> iterable) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).addAllInputs(iterable);
            return this;
        }

        public Builder clearInputs() {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).clearInputs();
            return this;
        }

        public Builder removeInputs(int i) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).removeInputs(i);
            return this;
        }

        public List<ProposalOutput> getOutputsList() {
            return Collections.unmodifiableList(((FetchCreatedProposalResponse) this.instance).getOutputsList());
        }

        public int getOutputsCount() {
            return ((FetchCreatedProposalResponse) this.instance).getOutputsCount();
        }

        public ProposalOutput getOutputs(int i) {
            return ((FetchCreatedProposalResponse) this.instance).getOutputs(i);
        }

        public Builder setOutputs(int i, ProposalOutput proposalOutput) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).setOutputs(i, proposalOutput);
            return this;
        }

        public Builder setOutputs(int i, com.bitcoin.mwallet.ProposalOutput.Builder builder) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).setOutputs(i, builder);
            return this;
        }

        public Builder addOutputs(ProposalOutput proposalOutput) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).addOutputs(proposalOutput);
            return this;
        }

        public Builder addOutputs(int i, ProposalOutput proposalOutput) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).addOutputs(i, proposalOutput);
            return this;
        }

        public Builder addOutputs(com.bitcoin.mwallet.ProposalOutput.Builder builder) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).addOutputs(builder);
            return this;
        }

        public Builder addOutputs(int i, com.bitcoin.mwallet.ProposalOutput.Builder builder) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).addOutputs(i, builder);
            return this;
        }

        public Builder addAllOutputs(Iterable<? extends ProposalOutput> iterable) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).addAllOutputs(iterable);
            return this;
        }

        public Builder clearOutputs() {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).clearOutputs();
            return this;
        }

        public Builder removeOutputs(int i) {
            copyOnWrite();
            ((FetchCreatedProposalResponse) this.instance).removeOutputs(i);
            return this;
        }
    }

    private FetchCreatedProposalResponse() {
        String str = "";
        this.proposalId_ = str;
        this.walletId_ = str;
        this.creatorCopayerId_ = str;
        this.message_ = str;
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

    public String getCreatorCopayerId() {
        return this.creatorCopayerId_;
    }

    public ByteString getCreatorCopayerIdBytes() {
        return ByteString.copyFromUtf8(this.creatorCopayerId_);
    }

    /* access modifiers changed from: private */
    public void setCreatorCopayerId(String str) {
        if (str != null) {
            this.creatorCopayerId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearCreatorCopayerId() {
        this.creatorCopayerId_ = getDefaultInstance().getCreatorCopayerId();
    }

    /* access modifiers changed from: private */
    public void setCreatorCopayerIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.creatorCopayerId_ = byteString.toStringUtf8();
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

    public long getUnixTimestamp() {
        return this.unixTimestamp_;
    }

    /* access modifiers changed from: private */
    public void setUnixTimestamp(long j) {
        this.unixTimestamp_ = j;
    }

    /* access modifiers changed from: private */
    public void clearUnixTimestamp() {
        this.unixTimestamp_ = 0;
    }

    public List<Utxo> getInputsList() {
        return this.inputs_;
    }

    public List<? extends UtxoOrBuilder> getInputsOrBuilderList() {
        return this.inputs_;
    }

    public int getInputsCount() {
        return this.inputs_.size();
    }

    public Utxo getInputs(int i) {
        return (Utxo) this.inputs_.get(i);
    }

    public UtxoOrBuilder getInputsOrBuilder(int i) {
        return (UtxoOrBuilder) this.inputs_.get(i);
    }

    private void ensureInputsIsMutable() {
        if (!this.inputs_.isModifiable()) {
            this.inputs_ = GeneratedMessageLite.mutableCopy(this.inputs_);
        }
    }

    /* access modifiers changed from: private */
    public void setInputs(int i, Utxo utxo) {
        if (utxo != null) {
            ensureInputsIsMutable();
            this.inputs_.set(i, utxo);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setInputs(int i, com.bitcoin.mwallet.Utxo.Builder builder) {
        ensureInputsIsMutable();
        this.inputs_.set(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addInputs(Utxo utxo) {
        if (utxo != null) {
            ensureInputsIsMutable();
            this.inputs_.add(utxo);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addInputs(int i, Utxo utxo) {
        if (utxo != null) {
            ensureInputsIsMutable();
            this.inputs_.add(i, utxo);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addInputs(com.bitcoin.mwallet.Utxo.Builder builder) {
        ensureInputsIsMutable();
        this.inputs_.add(builder.build());
    }

    /* access modifiers changed from: private */
    public void addInputs(int i, com.bitcoin.mwallet.Utxo.Builder builder) {
        ensureInputsIsMutable();
        this.inputs_.add(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllInputs(Iterable<? extends Utxo> iterable) {
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
        if (!this.proposalId_.isEmpty()) {
            codedOutputStream.writeString(1, getProposalId());
        }
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(2, getWalletId());
        }
        if (!this.creatorCopayerId_.isEmpty()) {
            codedOutputStream.writeString(3, getCreatorCopayerId());
        }
        if (!this.message_.isEmpty()) {
            codedOutputStream.writeString(4, getMessage());
        }
        long j = this.unixTimestamp_;
        if (j != 0) {
            codedOutputStream.writeInt64(5, j);
        }
        for (int i = 0; i < this.inputs_.size(); i++) {
            codedOutputStream.writeMessage(6, (MessageLite) this.inputs_.get(i));
        }
        for (int i2 = 0; i2 < this.outputs_.size(); i2++) {
            codedOutputStream.writeMessage(7, (MessageLite) this.outputs_.get(i2));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !this.proposalId_.isEmpty() ? CodedOutputStream.computeStringSize(1, getProposalId()) + 0 : 0;
        if (!this.walletId_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(2, getWalletId());
        }
        if (!this.creatorCopayerId_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(3, getCreatorCopayerId());
        }
        if (!this.message_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(4, getMessage());
        }
        long j = this.unixTimestamp_;
        if (j != 0) {
            computeStringSize += CodedOutputStream.computeInt64Size(5, j);
        }
        int i2 = computeStringSize;
        for (int i3 = 0; i3 < this.inputs_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(6, (MessageLite) this.inputs_.get(i3));
        }
        for (int i4 = 0; i4 < this.outputs_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(7, (MessageLite) this.outputs_.get(i4));
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static FetchCreatedProposalResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (FetchCreatedProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static FetchCreatedProposalResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FetchCreatedProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static FetchCreatedProposalResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (FetchCreatedProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static FetchCreatedProposalResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FetchCreatedProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static FetchCreatedProposalResponse parseFrom(InputStream inputStream) throws IOException {
        return (FetchCreatedProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FetchCreatedProposalResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FetchCreatedProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static FetchCreatedProposalResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (FetchCreatedProposalResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FetchCreatedProposalResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FetchCreatedProposalResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static FetchCreatedProposalResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (FetchCreatedProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static FetchCreatedProposalResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FetchCreatedProposalResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(FetchCreatedProposalResponse fetchCreatedProposalResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(fetchCreatedProposalResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new FetchCreatedProposalResponse();
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
                FetchCreatedProposalResponse fetchCreatedProposalResponse = (FetchCreatedProposalResponse) obj2;
                this.proposalId_ = visitor.visitString(!this.proposalId_.isEmpty(), this.proposalId_, !fetchCreatedProposalResponse.proposalId_.isEmpty(), fetchCreatedProposalResponse.proposalId_);
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, !fetchCreatedProposalResponse.walletId_.isEmpty(), fetchCreatedProposalResponse.walletId_);
                this.creatorCopayerId_ = visitor.visitString(!this.creatorCopayerId_.isEmpty(), this.creatorCopayerId_, !fetchCreatedProposalResponse.creatorCopayerId_.isEmpty(), fetchCreatedProposalResponse.creatorCopayerId_);
                this.message_ = visitor.visitString(!this.message_.isEmpty(), this.message_, !fetchCreatedProposalResponse.message_.isEmpty(), fetchCreatedProposalResponse.message_);
                this.unixTimestamp_ = visitor.visitLong(this.unixTimestamp_ != 0, this.unixTimestamp_, fetchCreatedProposalResponse.unixTimestamp_ != 0, fetchCreatedProposalResponse.unixTimestamp_);
                this.inputs_ = visitor.visitList(this.inputs_, fetchCreatedProposalResponse.inputs_);
                this.outputs_ = visitor.visitList(this.outputs_, fetchCreatedProposalResponse.outputs_);
                if (visitor == MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= fetchCreatedProposalResponse.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.proposalId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.walletId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.creatorCopayerId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 34) {
                                this.message_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 40) {
                                this.unixTimestamp_ = codedInputStream.readInt64();
                            } else if (readTag == 50) {
                                if (!this.inputs_.isModifiable()) {
                                    this.inputs_ = GeneratedMessageLite.mutableCopy(this.inputs_);
                                }
                                this.inputs_.add(codedInputStream.readMessage(Utxo.parser(), extensionRegistryLite));
                            } else if (readTag == 58) {
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
                    synchronized (FetchCreatedProposalResponse.class) {
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

    public static FetchCreatedProposalResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FetchCreatedProposalResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
