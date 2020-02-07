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

public final class ExchangeRate extends GeneratedMessageLite<ExchangeRate, Builder> implements ExchangeRateOrBuilder {
    /* access modifiers changed from: private */
    public static final ExchangeRate DEFAULT_INSTANCE = new ExchangeRate();
    public static final int NAME_FIELD_NUMBER = 2;
    private static volatile Parser<ExchangeRate> PARSER = null;
    public static final int RATE_FIELD_NUMBER = 3;
    public static final int TICKER_FIELD_NUMBER = 1;
    private String name_;
    private String rate_;
    private String ticker_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<ExchangeRate, Builder> implements ExchangeRateOrBuilder {
        private Builder() {
            super(ExchangeRate.DEFAULT_INSTANCE);
        }

        public String getTicker() {
            return ((ExchangeRate) this.instance).getTicker();
        }

        public ByteString getTickerBytes() {
            return ((ExchangeRate) this.instance).getTickerBytes();
        }

        public Builder setTicker(String str) {
            copyOnWrite();
            ((ExchangeRate) this.instance).setTicker(str);
            return this;
        }

        public Builder clearTicker() {
            copyOnWrite();
            ((ExchangeRate) this.instance).clearTicker();
            return this;
        }

        public Builder setTickerBytes(ByteString byteString) {
            copyOnWrite();
            ((ExchangeRate) this.instance).setTickerBytes(byteString);
            return this;
        }

        public String getName() {
            return ((ExchangeRate) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((ExchangeRate) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((ExchangeRate) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((ExchangeRate) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((ExchangeRate) this.instance).setNameBytes(byteString);
            return this;
        }

        public String getRate() {
            return ((ExchangeRate) this.instance).getRate();
        }

        public ByteString getRateBytes() {
            return ((ExchangeRate) this.instance).getRateBytes();
        }

        public Builder setRate(String str) {
            copyOnWrite();
            ((ExchangeRate) this.instance).setRate(str);
            return this;
        }

        public Builder clearRate() {
            copyOnWrite();
            ((ExchangeRate) this.instance).clearRate();
            return this;
        }

        public Builder setRateBytes(ByteString byteString) {
            copyOnWrite();
            ((ExchangeRate) this.instance).setRateBytes(byteString);
            return this;
        }
    }

    private ExchangeRate() {
        String str = "";
        this.ticker_ = str;
        this.name_ = str;
        this.rate_ = str;
    }

    public String getTicker() {
        return this.ticker_;
    }

    public ByteString getTickerBytes() {
        return ByteString.copyFromUtf8(this.ticker_);
    }

    /* access modifiers changed from: private */
    public void setTicker(String str) {
        if (str != null) {
            this.ticker_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearTicker() {
        this.ticker_ = getDefaultInstance().getTicker();
    }

    /* access modifiers changed from: private */
    public void setTickerBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.ticker_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getName() {
        return this.name_;
    }

    public ByteString getNameBytes() {
        return ByteString.copyFromUtf8(this.name_);
    }

    /* access modifiers changed from: private */
    public void setName(String str) {
        if (str != null) {
            this.name_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearName() {
        this.name_ = getDefaultInstance().getName();
    }

    /* access modifiers changed from: private */
    public void setNameBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.name_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getRate() {
        return this.rate_;
    }

    public ByteString getRateBytes() {
        return ByteString.copyFromUtf8(this.rate_);
    }

    /* access modifiers changed from: private */
    public void setRate(String str) {
        if (str != null) {
            this.rate_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearRate() {
        this.rate_ = getDefaultInstance().getRate();
    }

    /* access modifiers changed from: private */
    public void setRateBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.rate_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.ticker_.isEmpty()) {
            codedOutputStream.writeString(1, getTicker());
        }
        if (!this.name_.isEmpty()) {
            codedOutputStream.writeString(2, getName());
        }
        if (!this.rate_.isEmpty()) {
            codedOutputStream.writeString(3, getRate());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.ticker_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getTicker());
        }
        if (!this.name_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getName());
        }
        if (!this.rate_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(3, getRate());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static ExchangeRate parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ExchangeRate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ExchangeRate parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExchangeRate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ExchangeRate parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ExchangeRate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ExchangeRate parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExchangeRate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ExchangeRate parseFrom(InputStream inputStream) throws IOException {
        return (ExchangeRate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ExchangeRate parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExchangeRate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ExchangeRate parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ExchangeRate) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ExchangeRate parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExchangeRate) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ExchangeRate parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ExchangeRate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ExchangeRate parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExchangeRate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ExchangeRate exchangeRate) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(exchangeRate);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new ExchangeRate();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                ExchangeRate exchangeRate = (ExchangeRate) obj2;
                this.ticker_ = visitor.visitString(!this.ticker_.isEmpty(), this.ticker_, !exchangeRate.ticker_.isEmpty(), exchangeRate.ticker_);
                this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !exchangeRate.name_.isEmpty(), exchangeRate.name_);
                this.rate_ = visitor.visitString(!this.rate_.isEmpty(), this.rate_, true ^ exchangeRate.rate_.isEmpty(), exchangeRate.rate_);
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
                                this.ticker_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.rate_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (ExchangeRate.class) {
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

    public static ExchangeRate getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ExchangeRate> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
