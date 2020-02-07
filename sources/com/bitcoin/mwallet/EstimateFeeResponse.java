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

public final class EstimateFeeResponse extends GeneratedMessageLite<EstimateFeeResponse, Builder> implements EstimateFeeResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final EstimateFeeResponse DEFAULT_INSTANCE = new EstimateFeeResponse();
    public static final int ESTIMATE_FIELD_NUMBER = 1;
    private static volatile Parser<EstimateFeeResponse> PARSER;
    private ProtobufList<FeeEstimate> estimate_ = emptyProtobufList();

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<EstimateFeeResponse, Builder> implements EstimateFeeResponseOrBuilder {
        private Builder() {
            super(EstimateFeeResponse.DEFAULT_INSTANCE);
        }

        public List<FeeEstimate> getEstimateList() {
            return Collections.unmodifiableList(((EstimateFeeResponse) this.instance).getEstimateList());
        }

        public int getEstimateCount() {
            return ((EstimateFeeResponse) this.instance).getEstimateCount();
        }

        public FeeEstimate getEstimate(int i) {
            return ((EstimateFeeResponse) this.instance).getEstimate(i);
        }

        public Builder setEstimate(int i, FeeEstimate feeEstimate) {
            copyOnWrite();
            ((EstimateFeeResponse) this.instance).setEstimate(i, feeEstimate);
            return this;
        }

        public Builder setEstimate(int i, com.bitcoin.mwallet.FeeEstimate.Builder builder) {
            copyOnWrite();
            ((EstimateFeeResponse) this.instance).setEstimate(i, builder);
            return this;
        }

        public Builder addEstimate(FeeEstimate feeEstimate) {
            copyOnWrite();
            ((EstimateFeeResponse) this.instance).addEstimate(feeEstimate);
            return this;
        }

        public Builder addEstimate(int i, FeeEstimate feeEstimate) {
            copyOnWrite();
            ((EstimateFeeResponse) this.instance).addEstimate(i, feeEstimate);
            return this;
        }

        public Builder addEstimate(com.bitcoin.mwallet.FeeEstimate.Builder builder) {
            copyOnWrite();
            ((EstimateFeeResponse) this.instance).addEstimate(builder);
            return this;
        }

        public Builder addEstimate(int i, com.bitcoin.mwallet.FeeEstimate.Builder builder) {
            copyOnWrite();
            ((EstimateFeeResponse) this.instance).addEstimate(i, builder);
            return this;
        }

        public Builder addAllEstimate(Iterable<? extends FeeEstimate> iterable) {
            copyOnWrite();
            ((EstimateFeeResponse) this.instance).addAllEstimate(iterable);
            return this;
        }

        public Builder clearEstimate() {
            copyOnWrite();
            ((EstimateFeeResponse) this.instance).clearEstimate();
            return this;
        }

        public Builder removeEstimate(int i) {
            copyOnWrite();
            ((EstimateFeeResponse) this.instance).removeEstimate(i);
            return this;
        }
    }

    private EstimateFeeResponse() {
    }

    public List<FeeEstimate> getEstimateList() {
        return this.estimate_;
    }

    public List<? extends FeeEstimateOrBuilder> getEstimateOrBuilderList() {
        return this.estimate_;
    }

    public int getEstimateCount() {
        return this.estimate_.size();
    }

    public FeeEstimate getEstimate(int i) {
        return (FeeEstimate) this.estimate_.get(i);
    }

    public FeeEstimateOrBuilder getEstimateOrBuilder(int i) {
        return (FeeEstimateOrBuilder) this.estimate_.get(i);
    }

    private void ensureEstimateIsMutable() {
        if (!this.estimate_.isModifiable()) {
            this.estimate_ = GeneratedMessageLite.mutableCopy(this.estimate_);
        }
    }

    /* access modifiers changed from: private */
    public void setEstimate(int i, FeeEstimate feeEstimate) {
        if (feeEstimate != null) {
            ensureEstimateIsMutable();
            this.estimate_.set(i, feeEstimate);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setEstimate(int i, com.bitcoin.mwallet.FeeEstimate.Builder builder) {
        ensureEstimateIsMutable();
        this.estimate_.set(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addEstimate(FeeEstimate feeEstimate) {
        if (feeEstimate != null) {
            ensureEstimateIsMutable();
            this.estimate_.add(feeEstimate);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addEstimate(int i, FeeEstimate feeEstimate) {
        if (feeEstimate != null) {
            ensureEstimateIsMutable();
            this.estimate_.add(i, feeEstimate);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addEstimate(com.bitcoin.mwallet.FeeEstimate.Builder builder) {
        ensureEstimateIsMutable();
        this.estimate_.add(builder.build());
    }

    /* access modifiers changed from: private */
    public void addEstimate(int i, com.bitcoin.mwallet.FeeEstimate.Builder builder) {
        ensureEstimateIsMutable();
        this.estimate_.add(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllEstimate(Iterable<? extends FeeEstimate> iterable) {
        ensureEstimateIsMutable();
        AbstractMessageLite.addAll(iterable, this.estimate_);
    }

    /* access modifiers changed from: private */
    public void clearEstimate() {
        this.estimate_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeEstimate(int i) {
        ensureEstimateIsMutable();
        this.estimate_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.estimate_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.estimate_.get(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.estimate_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.estimate_.get(i3));
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static EstimateFeeResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EstimateFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EstimateFeeResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EstimateFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EstimateFeeResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EstimateFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EstimateFeeResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EstimateFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static EstimateFeeResponse parseFrom(InputStream inputStream) throws IOException {
        return (EstimateFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EstimateFeeResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EstimateFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EstimateFeeResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EstimateFeeResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EstimateFeeResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EstimateFeeResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EstimateFeeResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EstimateFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EstimateFeeResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EstimateFeeResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EstimateFeeResponse estimateFeeResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(estimateFeeResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new EstimateFeeResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.estimate_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.estimate_ = ((Visitor) obj).visitList(this.estimate_, ((EstimateFeeResponse) obj2).estimate_);
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
                                if (!this.estimate_.isModifiable()) {
                                    this.estimate_ = GeneratedMessageLite.mutableCopy(this.estimate_);
                                }
                                this.estimate_.add(codedInputStream.readMessage(FeeEstimate.parser(), extensionRegistryLite));
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
                    synchronized (EstimateFeeResponse.class) {
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

    public static EstimateFeeResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EstimateFeeResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
