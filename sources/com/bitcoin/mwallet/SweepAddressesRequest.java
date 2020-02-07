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
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class SweepAddressesRequest extends GeneratedMessageLite<SweepAddressesRequest, Builder> implements SweepAddressesRequestOrBuilder {
    public static final int ADDRESSES_FIELD_NUMBER = 1;
    public static final int COIN_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final SweepAddressesRequest DEFAULT_INSTANCE = new SweepAddressesRequest();
    private static volatile Parser<SweepAddressesRequest> PARSER;
    private ProtobufList<String> addresses_ = GeneratedMessageLite.emptyProtobufList();
    private int bitField0_;
    private int coin_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<SweepAddressesRequest, Builder> implements SweepAddressesRequestOrBuilder {
        private Builder() {
            super(SweepAddressesRequest.DEFAULT_INSTANCE);
        }

        public List<String> getAddressesList() {
            return Collections.unmodifiableList(((SweepAddressesRequest) this.instance).getAddressesList());
        }

        public int getAddressesCount() {
            return ((SweepAddressesRequest) this.instance).getAddressesCount();
        }

        public String getAddresses(int i) {
            return ((SweepAddressesRequest) this.instance).getAddresses(i);
        }

        public ByteString getAddressesBytes(int i) {
            return ((SweepAddressesRequest) this.instance).getAddressesBytes(i);
        }

        public Builder setAddresses(int i, String str) {
            copyOnWrite();
            ((SweepAddressesRequest) this.instance).setAddresses(i, str);
            return this;
        }

        public Builder addAddresses(String str) {
            copyOnWrite();
            ((SweepAddressesRequest) this.instance).addAddresses(str);
            return this;
        }

        public Builder addAllAddresses(Iterable<String> iterable) {
            copyOnWrite();
            ((SweepAddressesRequest) this.instance).addAllAddresses(iterable);
            return this;
        }

        public Builder clearAddresses() {
            copyOnWrite();
            ((SweepAddressesRequest) this.instance).clearAddresses();
            return this;
        }

        public Builder addAddressesBytes(ByteString byteString) {
            copyOnWrite();
            ((SweepAddressesRequest) this.instance).addAddressesBytes(byteString);
            return this;
        }

        public int getCoinValue() {
            return ((SweepAddressesRequest) this.instance).getCoinValue();
        }

        public Builder setCoinValue(int i) {
            copyOnWrite();
            ((SweepAddressesRequest) this.instance).setCoinValue(i);
            return this;
        }

        public Coin getCoin() {
            return ((SweepAddressesRequest) this.instance).getCoin();
        }

        public Builder setCoin(Coin coin) {
            copyOnWrite();
            ((SweepAddressesRequest) this.instance).setCoin(coin);
            return this;
        }

        public Builder clearCoin() {
            copyOnWrite();
            ((SweepAddressesRequest) this.instance).clearCoin();
            return this;
        }
    }

    private SweepAddressesRequest() {
    }

    public List<String> getAddressesList() {
        return this.addresses_;
    }

    public int getAddressesCount() {
        return this.addresses_.size();
    }

    public String getAddresses(int i) {
        return (String) this.addresses_.get(i);
    }

    public ByteString getAddressesBytes(int i) {
        return ByteString.copyFromUtf8((String) this.addresses_.get(i));
    }

    private void ensureAddressesIsMutable() {
        if (!this.addresses_.isModifiable()) {
            this.addresses_ = GeneratedMessageLite.mutableCopy(this.addresses_);
        }
    }

    /* access modifiers changed from: private */
    public void setAddresses(int i, String str) {
        if (str != null) {
            ensureAddressesIsMutable();
            this.addresses_.set(i, str);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addAddresses(String str) {
        if (str != null) {
            ensureAddressesIsMutable();
            this.addresses_.add(str);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addAllAddresses(Iterable<String> iterable) {
        ensureAddressesIsMutable();
        AbstractMessageLite.addAll(iterable, this.addresses_);
    }

    /* access modifiers changed from: private */
    public void clearAddresses() {
        this.addresses_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addAddressesBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            ensureAddressesIsMutable();
            this.addresses_.add(byteString.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    public int getCoinValue() {
        return this.coin_;
    }

    public Coin getCoin() {
        Coin forNumber = Coin.forNumber(this.coin_);
        return forNumber == null ? Coin.UNRECOGNIZED : forNumber;
    }

    /* access modifiers changed from: private */
    public void setCoinValue(int i) {
        this.coin_ = i;
    }

    /* access modifiers changed from: private */
    public void setCoin(Coin coin) {
        if (coin != null) {
            this.coin_ = coin.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearCoin() {
        this.coin_ = 0;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.addresses_.size(); i++) {
            codedOutputStream.writeString(1, (String) this.addresses_.get(i));
        }
        if (this.coin_ != Coin.COIN_NOT_SET.getNumber()) {
            codedOutputStream.writeEnum(2, this.coin_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.addresses_.size(); i3++) {
            i2 += CodedOutputStream.computeStringSizeNoTag((String) this.addresses_.get(i3));
        }
        int size = 0 + i2 + (getAddressesList().size() * 1);
        if (this.coin_ != Coin.COIN_NOT_SET.getNumber()) {
            size += CodedOutputStream.computeEnumSize(2, this.coin_);
        }
        this.memoizedSerializedSize = size;
        return size;
    }

    public static SweepAddressesRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SweepAddressesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static SweepAddressesRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SweepAddressesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static SweepAddressesRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SweepAddressesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static SweepAddressesRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SweepAddressesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static SweepAddressesRequest parseFrom(InputStream inputStream) throws IOException {
        return (SweepAddressesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SweepAddressesRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SweepAddressesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SweepAddressesRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SweepAddressesRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SweepAddressesRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SweepAddressesRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SweepAddressesRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SweepAddressesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static SweepAddressesRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SweepAddressesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SweepAddressesRequest sweepAddressesRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(sweepAddressesRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new SweepAddressesRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.addresses_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                SweepAddressesRequest sweepAddressesRequest = (SweepAddressesRequest) obj2;
                this.addresses_ = visitor.visitList(this.addresses_, sweepAddressesRequest.addresses_);
                boolean z2 = this.coin_ != 0;
                int i = this.coin_;
                if (sweepAddressesRequest.coin_ != 0) {
                    z = true;
                }
                this.coin_ = visitor.visitInt(z2, i, z, sweepAddressesRequest.coin_);
                if (visitor == MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= sweepAddressesRequest.bitField0_;
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
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if (!this.addresses_.isModifiable()) {
                                    this.addresses_ = GeneratedMessageLite.mutableCopy(this.addresses_);
                                }
                                this.addresses_.add(readStringRequireUtf8);
                            } else if (readTag == 16) {
                                this.coin_ = codedInputStream.readEnum();
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
                    synchronized (SweepAddressesRequest.class) {
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

    public static SweepAddressesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SweepAddressesRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
