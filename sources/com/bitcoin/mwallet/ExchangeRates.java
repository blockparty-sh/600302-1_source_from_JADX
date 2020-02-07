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

public final class ExchangeRates extends GeneratedMessageLite<ExchangeRates, Builder> implements ExchangeRatesOrBuilder {
    /* access modifiers changed from: private */
    public static final ExchangeRates DEFAULT_INSTANCE = new ExchangeRates();
    private static volatile Parser<ExchangeRates> PARSER = null;
    public static final int RATES_FIELD_NUMBER = 2;
    public static final int UPDATED_EPOCH_MILLIS_FIELD_NUMBER = 1;
    private int bitField0_;
    private ProtobufList<ExchangeRate> rates_ = emptyProtobufList();
    private long updatedEpochMillis_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<ExchangeRates, Builder> implements ExchangeRatesOrBuilder {
        private Builder() {
            super(ExchangeRates.DEFAULT_INSTANCE);
        }

        public long getUpdatedEpochMillis() {
            return ((ExchangeRates) this.instance).getUpdatedEpochMillis();
        }

        public Builder setUpdatedEpochMillis(long j) {
            copyOnWrite();
            ((ExchangeRates) this.instance).setUpdatedEpochMillis(j);
            return this;
        }

        public Builder clearUpdatedEpochMillis() {
            copyOnWrite();
            ((ExchangeRates) this.instance).clearUpdatedEpochMillis();
            return this;
        }

        public List<ExchangeRate> getRatesList() {
            return Collections.unmodifiableList(((ExchangeRates) this.instance).getRatesList());
        }

        public int getRatesCount() {
            return ((ExchangeRates) this.instance).getRatesCount();
        }

        public ExchangeRate getRates(int i) {
            return ((ExchangeRates) this.instance).getRates(i);
        }

        public Builder setRates(int i, ExchangeRate exchangeRate) {
            copyOnWrite();
            ((ExchangeRates) this.instance).setRates(i, exchangeRate);
            return this;
        }

        public Builder setRates(int i, com.bitcoin.mwallet.ExchangeRate.Builder builder) {
            copyOnWrite();
            ((ExchangeRates) this.instance).setRates(i, builder);
            return this;
        }

        public Builder addRates(ExchangeRate exchangeRate) {
            copyOnWrite();
            ((ExchangeRates) this.instance).addRates(exchangeRate);
            return this;
        }

        public Builder addRates(int i, ExchangeRate exchangeRate) {
            copyOnWrite();
            ((ExchangeRates) this.instance).addRates(i, exchangeRate);
            return this;
        }

        public Builder addRates(com.bitcoin.mwallet.ExchangeRate.Builder builder) {
            copyOnWrite();
            ((ExchangeRates) this.instance).addRates(builder);
            return this;
        }

        public Builder addRates(int i, com.bitcoin.mwallet.ExchangeRate.Builder builder) {
            copyOnWrite();
            ((ExchangeRates) this.instance).addRates(i, builder);
            return this;
        }

        public Builder addAllRates(Iterable<? extends ExchangeRate> iterable) {
            copyOnWrite();
            ((ExchangeRates) this.instance).addAllRates(iterable);
            return this;
        }

        public Builder clearRates() {
            copyOnWrite();
            ((ExchangeRates) this.instance).clearRates();
            return this;
        }

        public Builder removeRates(int i) {
            copyOnWrite();
            ((ExchangeRates) this.instance).removeRates(i);
            return this;
        }
    }

    private ExchangeRates() {
    }

    public long getUpdatedEpochMillis() {
        return this.updatedEpochMillis_;
    }

    /* access modifiers changed from: private */
    public void setUpdatedEpochMillis(long j) {
        this.updatedEpochMillis_ = j;
    }

    /* access modifiers changed from: private */
    public void clearUpdatedEpochMillis() {
        this.updatedEpochMillis_ = 0;
    }

    public List<ExchangeRate> getRatesList() {
        return this.rates_;
    }

    public List<? extends ExchangeRateOrBuilder> getRatesOrBuilderList() {
        return this.rates_;
    }

    public int getRatesCount() {
        return this.rates_.size();
    }

    public ExchangeRate getRates(int i) {
        return (ExchangeRate) this.rates_.get(i);
    }

    public ExchangeRateOrBuilder getRatesOrBuilder(int i) {
        return (ExchangeRateOrBuilder) this.rates_.get(i);
    }

    private void ensureRatesIsMutable() {
        if (!this.rates_.isModifiable()) {
            this.rates_ = GeneratedMessageLite.mutableCopy(this.rates_);
        }
    }

    /* access modifiers changed from: private */
    public void setRates(int i, ExchangeRate exchangeRate) {
        if (exchangeRate != null) {
            ensureRatesIsMutable();
            this.rates_.set(i, exchangeRate);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setRates(int i, com.bitcoin.mwallet.ExchangeRate.Builder builder) {
        ensureRatesIsMutable();
        this.rates_.set(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addRates(ExchangeRate exchangeRate) {
        if (exchangeRate != null) {
            ensureRatesIsMutable();
            this.rates_.add(exchangeRate);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addRates(int i, ExchangeRate exchangeRate) {
        if (exchangeRate != null) {
            ensureRatesIsMutable();
            this.rates_.add(i, exchangeRate);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addRates(com.bitcoin.mwallet.ExchangeRate.Builder builder) {
        ensureRatesIsMutable();
        this.rates_.add(builder.build());
    }

    /* access modifiers changed from: private */
    public void addRates(int i, com.bitcoin.mwallet.ExchangeRate.Builder builder) {
        ensureRatesIsMutable();
        this.rates_.add(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllRates(Iterable<? extends ExchangeRate> iterable) {
        ensureRatesIsMutable();
        AbstractMessageLite.addAll(iterable, this.rates_);
    }

    /* access modifiers changed from: private */
    public void clearRates() {
        this.rates_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeRates(int i) {
        ensureRatesIsMutable();
        this.rates_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        long j = this.updatedEpochMillis_;
        if (j != 0) {
            codedOutputStream.writeUInt64(1, j);
        }
        for (int i = 0; i < this.rates_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.rates_.get(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        long j = this.updatedEpochMillis_;
        int computeUInt64Size = j != 0 ? CodedOutputStream.computeUInt64Size(1, j) + 0 : 0;
        for (int i2 = 0; i2 < this.rates_.size(); i2++) {
            computeUInt64Size += CodedOutputStream.computeMessageSize(2, (MessageLite) this.rates_.get(i2));
        }
        this.memoizedSerializedSize = computeUInt64Size;
        return computeUInt64Size;
    }

    public static ExchangeRates parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ExchangeRates) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ExchangeRates parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExchangeRates) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ExchangeRates parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ExchangeRates) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ExchangeRates parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExchangeRates) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ExchangeRates parseFrom(InputStream inputStream) throws IOException {
        return (ExchangeRates) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ExchangeRates parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExchangeRates) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ExchangeRates parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ExchangeRates) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ExchangeRates parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExchangeRates) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ExchangeRates parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ExchangeRates) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ExchangeRates parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExchangeRates) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ExchangeRates exchangeRates) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(exchangeRates);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new ExchangeRates();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.rates_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                ExchangeRates exchangeRates = (ExchangeRates) obj2;
                this.updatedEpochMillis_ = visitor.visitLong(this.updatedEpochMillis_ != 0, this.updatedEpochMillis_, exchangeRates.updatedEpochMillis_ != 0, exchangeRates.updatedEpochMillis_);
                this.rates_ = visitor.visitList(this.rates_, exchangeRates.rates_);
                if (visitor == MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= exchangeRates.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.updatedEpochMillis_ = codedInputStream.readUInt64();
                            } else if (readTag == 18) {
                                if (!this.rates_.isModifiable()) {
                                    this.rates_ = GeneratedMessageLite.mutableCopy(this.rates_);
                                }
                                this.rates_.add(codedInputStream.readMessage(ExchangeRate.parser(), extensionRegistryLite));
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
                    synchronized (ExchangeRates.class) {
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

    public static ExchangeRates getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ExchangeRates> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
