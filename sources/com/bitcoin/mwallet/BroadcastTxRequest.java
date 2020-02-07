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

public final class BroadcastTxRequest extends GeneratedMessageLite<BroadcastTxRequest, Builder> implements BroadcastTxRequestOrBuilder {
    public static final int COIN_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final BroadcastTxRequest DEFAULT_INSTANCE = new BroadcastTxRequest();
    public static final int NETWORK_FIELD_NUMBER = 2;
    private static volatile Parser<BroadcastTxRequest> PARSER = null;
    public static final int TX_RAW_FIELD_NUMBER = 3;
    private int coin_;
    private int network_;
    private ByteString txRaw_ = ByteString.EMPTY;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<BroadcastTxRequest, Builder> implements BroadcastTxRequestOrBuilder {
        private Builder() {
            super(BroadcastTxRequest.DEFAULT_INSTANCE);
        }

        public int getCoinValue() {
            return ((BroadcastTxRequest) this.instance).getCoinValue();
        }

        public Builder setCoinValue(int i) {
            copyOnWrite();
            ((BroadcastTxRequest) this.instance).setCoinValue(i);
            return this;
        }

        public Coin getCoin() {
            return ((BroadcastTxRequest) this.instance).getCoin();
        }

        public Builder setCoin(Coin coin) {
            copyOnWrite();
            ((BroadcastTxRequest) this.instance).setCoin(coin);
            return this;
        }

        public Builder clearCoin() {
            copyOnWrite();
            ((BroadcastTxRequest) this.instance).clearCoin();
            return this;
        }

        public int getNetworkValue() {
            return ((BroadcastTxRequest) this.instance).getNetworkValue();
        }

        public Builder setNetworkValue(int i) {
            copyOnWrite();
            ((BroadcastTxRequest) this.instance).setNetworkValue(i);
            return this;
        }

        public Network getNetwork() {
            return ((BroadcastTxRequest) this.instance).getNetwork();
        }

        public Builder setNetwork(Network network) {
            copyOnWrite();
            ((BroadcastTxRequest) this.instance).setNetwork(network);
            return this;
        }

        public Builder clearNetwork() {
            copyOnWrite();
            ((BroadcastTxRequest) this.instance).clearNetwork();
            return this;
        }

        public ByteString getTxRaw() {
            return ((BroadcastTxRequest) this.instance).getTxRaw();
        }

        public Builder setTxRaw(ByteString byteString) {
            copyOnWrite();
            ((BroadcastTxRequest) this.instance).setTxRaw(byteString);
            return this;
        }

        public Builder clearTxRaw() {
            copyOnWrite();
            ((BroadcastTxRequest) this.instance).clearTxRaw();
            return this;
        }
    }

    private BroadcastTxRequest() {
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

    public int getNetworkValue() {
        return this.network_;
    }

    public Network getNetwork() {
        Network forNumber = Network.forNumber(this.network_);
        return forNumber == null ? Network.UNRECOGNIZED : forNumber;
    }

    /* access modifiers changed from: private */
    public void setNetworkValue(int i) {
        this.network_ = i;
    }

    /* access modifiers changed from: private */
    public void setNetwork(Network network) {
        if (network != null) {
            this.network_ = network.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearNetwork() {
        this.network_ = 0;
    }

    public ByteString getTxRaw() {
        return this.txRaw_;
    }

    /* access modifiers changed from: private */
    public void setTxRaw(ByteString byteString) {
        if (byteString != null) {
            this.txRaw_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearTxRaw() {
        this.txRaw_ = getDefaultInstance().getTxRaw();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.coin_ != Coin.COIN_NOT_SET.getNumber()) {
            codedOutputStream.writeEnum(1, this.coin_);
        }
        if (this.network_ != Network.NETWORK_NOT_SET.getNumber()) {
            codedOutputStream.writeEnum(2, this.network_);
        }
        if (!this.txRaw_.isEmpty()) {
            codedOutputStream.writeBytes(3, this.txRaw_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.coin_ != Coin.COIN_NOT_SET.getNumber()) {
            i2 = 0 + CodedOutputStream.computeEnumSize(1, this.coin_);
        }
        if (this.network_ != Network.NETWORK_NOT_SET.getNumber()) {
            i2 += CodedOutputStream.computeEnumSize(2, this.network_);
        }
        if (!this.txRaw_.isEmpty()) {
            i2 += CodedOutputStream.computeBytesSize(3, this.txRaw_);
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static BroadcastTxRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BroadcastTxRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BroadcastTxRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BroadcastTxRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BroadcastTxRequest parseFrom(InputStream inputStream) throws IOException {
        return (BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BroadcastTxRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BroadcastTxRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BroadcastTxRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BroadcastTxRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BroadcastTxRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BroadcastTxRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BroadcastTxRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BroadcastTxRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BroadcastTxRequest broadcastTxRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(broadcastTxRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new BroadcastTxRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                BroadcastTxRequest broadcastTxRequest = (BroadcastTxRequest) obj2;
                this.coin_ = visitor.visitInt(this.coin_ != 0, this.coin_, broadcastTxRequest.coin_ != 0, broadcastTxRequest.coin_);
                this.network_ = visitor.visitInt(this.network_ != 0, this.network_, broadcastTxRequest.network_ != 0, broadcastTxRequest.network_);
                boolean z2 = this.txRaw_ != ByteString.EMPTY;
                ByteString byteString = this.txRaw_;
                if (broadcastTxRequest.txRaw_ != ByteString.EMPTY) {
                    z = true;
                }
                this.txRaw_ = visitor.visitByteString(z2, byteString, z, broadcastTxRequest.txRaw_);
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
                                this.coin_ = codedInputStream.readEnum();
                            } else if (readTag == 16) {
                                this.network_ = codedInputStream.readEnum();
                            } else if (readTag == 26) {
                                this.txRaw_ = codedInputStream.readBytes();
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
                    synchronized (BroadcastTxRequest.class) {
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

    public static BroadcastTxRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BroadcastTxRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
