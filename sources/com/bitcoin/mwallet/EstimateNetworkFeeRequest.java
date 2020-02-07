package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.Internal.IntList;
import com.google.protobuf.Internal.ListAdapter;
import com.google.protobuf.Internal.ListAdapter.Converter;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class EstimateNetworkFeeRequest extends GeneratedMessageLite<EstimateNetworkFeeRequest, Builder> implements EstimateNetworkFeeRequestOrBuilder {
    public static final int COIN_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final EstimateNetworkFeeRequest DEFAULT_INSTANCE = new EstimateNetworkFeeRequest();
    private static volatile Parser<EstimateNetworkFeeRequest> PARSER;
    private static final Converter<Integer, Coin> coin_converter_ = new Converter<Integer, Coin>() {
        public Coin convert(Integer num) {
            Coin forNumber = Coin.forNumber(num.intValue());
            return forNumber == null ? Coin.UNRECOGNIZED : forNumber;
        }
    };
    private IntList coin_ = emptyIntList();

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<EstimateNetworkFeeRequest, Builder> implements EstimateNetworkFeeRequestOrBuilder {
        private Builder() {
            super(EstimateNetworkFeeRequest.DEFAULT_INSTANCE);
        }

        public List<Coin> getCoinList() {
            return ((EstimateNetworkFeeRequest) this.instance).getCoinList();
        }

        public int getCoinCount() {
            return ((EstimateNetworkFeeRequest) this.instance).getCoinCount();
        }

        public Coin getCoin(int i) {
            return ((EstimateNetworkFeeRequest) this.instance).getCoin(i);
        }

        public Builder setCoin(int i, Coin coin) {
            copyOnWrite();
            ((EstimateNetworkFeeRequest) this.instance).setCoin(i, coin);
            return this;
        }

        public Builder addCoin(Coin coin) {
            copyOnWrite();
            ((EstimateNetworkFeeRequest) this.instance).addCoin(coin);
            return this;
        }

        public Builder addAllCoin(Iterable<? extends Coin> iterable) {
            copyOnWrite();
            ((EstimateNetworkFeeRequest) this.instance).addAllCoin(iterable);
            return this;
        }

        public Builder clearCoin() {
            copyOnWrite();
            ((EstimateNetworkFeeRequest) this.instance).clearCoin();
            return this;
        }

        public List<Integer> getCoinValueList() {
            return Collections.unmodifiableList(((EstimateNetworkFeeRequest) this.instance).getCoinValueList());
        }

        public int getCoinValue(int i) {
            return ((EstimateNetworkFeeRequest) this.instance).getCoinValue(i);
        }

        public Builder setCoinValue(int i, int i2) {
            copyOnWrite();
            ((EstimateNetworkFeeRequest) this.instance).setCoinValue(i, i2);
            return this;
        }

        public Builder addCoinValue(int i) {
            ((EstimateNetworkFeeRequest) this.instance).addCoinValue(i);
            return this;
        }

        public Builder addAllCoinValue(Iterable<Integer> iterable) {
            copyOnWrite();
            ((EstimateNetworkFeeRequest) this.instance).addAllCoinValue(iterable);
            return this;
        }
    }

    private EstimateNetworkFeeRequest() {
    }

    static {
        DEFAULT_INSTANCE.makeImmutable();
    }

    public List<Coin> getCoinList() {
        return new ListAdapter(this.coin_, coin_converter_);
    }

    public int getCoinCount() {
        return this.coin_.size();
    }

    public Coin getCoin(int i) {
        return (Coin) coin_converter_.convert(Integer.valueOf(this.coin_.getInt(i)));
    }

    public List<Integer> getCoinValueList() {
        return this.coin_;
    }

    public int getCoinValue(int i) {
        return this.coin_.getInt(i);
    }

    private void ensureCoinIsMutable() {
        if (!this.coin_.isModifiable()) {
            this.coin_ = GeneratedMessageLite.mutableCopy(this.coin_);
        }
    }

    /* access modifiers changed from: private */
    public void setCoin(int i, Coin coin) {
        if (coin != null) {
            ensureCoinIsMutable();
            this.coin_.setInt(i, coin.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addCoin(Coin coin) {
        if (coin != null) {
            ensureCoinIsMutable();
            this.coin_.addInt(coin.getNumber());
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addAllCoin(Iterable<? extends Coin> iterable) {
        ensureCoinIsMutable();
        for (Coin number : iterable) {
            this.coin_.addInt(number.getNumber());
        }
    }

    /* access modifiers changed from: private */
    public void clearCoin() {
        this.coin_ = emptyIntList();
    }

    /* access modifiers changed from: private */
    public void setCoinValue(int i, int i2) {
        ensureCoinIsMutable();
        this.coin_.setInt(i, i2);
    }

    /* access modifiers changed from: private */
    public void addCoinValue(int i) {
        ensureCoinIsMutable();
        this.coin_.addInt(i);
    }

    /* access modifiers changed from: private */
    public void addAllCoinValue(Iterable<Integer> iterable) {
        ensureCoinIsMutable();
        for (Integer intValue : iterable) {
            this.coin_.addInt(intValue.intValue());
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        getSerializedSize();
        for (int i = 0; i < this.coin_.size(); i++) {
            codedOutputStream.writeEnum(1, this.coin_.getInt(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.coin_.size(); i3++) {
            i2 += CodedOutputStream.computeEnumSizeNoTag(this.coin_.getInt(i3));
        }
        int size = 0 + i2 + (this.coin_.size() * 1);
        this.memoizedSerializedSize = size;
        return size;
    }

    public static EstimateNetworkFeeRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (EstimateNetworkFeeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static EstimateNetworkFeeRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EstimateNetworkFeeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static EstimateNetworkFeeRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (EstimateNetworkFeeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static EstimateNetworkFeeRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (EstimateNetworkFeeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static EstimateNetworkFeeRequest parseFrom(InputStream inputStream) throws IOException {
        return (EstimateNetworkFeeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EstimateNetworkFeeRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EstimateNetworkFeeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EstimateNetworkFeeRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (EstimateNetworkFeeRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static EstimateNetworkFeeRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EstimateNetworkFeeRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static EstimateNetworkFeeRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (EstimateNetworkFeeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static EstimateNetworkFeeRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (EstimateNetworkFeeRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(EstimateNetworkFeeRequest estimateNetworkFeeRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(estimateNetworkFeeRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new EstimateNetworkFeeRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.coin_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.coin_ = ((Visitor) obj).visitIntList(this.coin_, ((EstimateNetworkFeeRequest) obj2).coin_);
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
                            if (readTag == 8) {
                                if (!this.coin_.isModifiable()) {
                                    this.coin_ = GeneratedMessageLite.mutableCopy(this.coin_);
                                }
                                this.coin_.addInt(codedInputStream.readEnum());
                            } else if (readTag == 10) {
                                if (!this.coin_.isModifiable()) {
                                    this.coin_ = GeneratedMessageLite.mutableCopy(this.coin_);
                                }
                                int pushLimit = codedInputStream.pushLimit(codedInputStream.readRawVarint32());
                                while (codedInputStream.getBytesUntilLimit() > 0) {
                                    this.coin_.addInt(codedInputStream.readEnum());
                                }
                                codedInputStream.popLimit(pushLimit);
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
                    synchronized (EstimateNetworkFeeRequest.class) {
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

    public static EstimateNetworkFeeRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<EstimateNetworkFeeRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
