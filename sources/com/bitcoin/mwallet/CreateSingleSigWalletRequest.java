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

public final class CreateSingleSigWalletRequest extends GeneratedMessageLite<CreateSingleSigWalletRequest, Builder> implements CreateSingleSigWalletRequestOrBuilder {
    public static final int COIN_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final CreateSingleSigWalletRequest DEFAULT_INSTANCE = new CreateSingleSigWalletRequest();
    public static final int NAME_FIELD_NUMBER = 4;
    public static final int NETWORK_FIELD_NUMBER = 2;
    private static volatile Parser<CreateSingleSigWalletRequest> PARSER = null;
    public static final int SIGNING_PUB_KEY_FIELD_NUMBER = 5;
    public static final int X_PUB_KEY_FIELD_NUMBER = 1;
    private int coin_;
    private String name_;
    private int network_;
    private String signingPubKey_;
    private String xPubKey_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<CreateSingleSigWalletRequest, Builder> implements CreateSingleSigWalletRequestOrBuilder {
        private Builder() {
            super(CreateSingleSigWalletRequest.DEFAULT_INSTANCE);
        }

        public String getXPubKey() {
            return ((CreateSingleSigWalletRequest) this.instance).getXPubKey();
        }

        public ByteString getXPubKeyBytes() {
            return ((CreateSingleSigWalletRequest) this.instance).getXPubKeyBytes();
        }

        public Builder setXPubKey(String str) {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).setXPubKey(str);
            return this;
        }

        public Builder clearXPubKey() {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).clearXPubKey();
            return this;
        }

        public Builder setXPubKeyBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).setXPubKeyBytes(byteString);
            return this;
        }

        public int getNetworkValue() {
            return ((CreateSingleSigWalletRequest) this.instance).getNetworkValue();
        }

        public Builder setNetworkValue(int i) {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).setNetworkValue(i);
            return this;
        }

        public Network getNetwork() {
            return ((CreateSingleSigWalletRequest) this.instance).getNetwork();
        }

        public Builder setNetwork(Network network) {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).setNetwork(network);
            return this;
        }

        public Builder clearNetwork() {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).clearNetwork();
            return this;
        }

        public int getCoinValue() {
            return ((CreateSingleSigWalletRequest) this.instance).getCoinValue();
        }

        public Builder setCoinValue(int i) {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).setCoinValue(i);
            return this;
        }

        public Coin getCoin() {
            return ((CreateSingleSigWalletRequest) this.instance).getCoin();
        }

        public Builder setCoin(Coin coin) {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).setCoin(coin);
            return this;
        }

        public Builder clearCoin() {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).clearCoin();
            return this;
        }

        public String getName() {
            return ((CreateSingleSigWalletRequest) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((CreateSingleSigWalletRequest) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).setNameBytes(byteString);
            return this;
        }

        public String getSigningPubKey() {
            return ((CreateSingleSigWalletRequest) this.instance).getSigningPubKey();
        }

        public ByteString getSigningPubKeyBytes() {
            return ((CreateSingleSigWalletRequest) this.instance).getSigningPubKeyBytes();
        }

        public Builder setSigningPubKey(String str) {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).setSigningPubKey(str);
            return this;
        }

        public Builder clearSigningPubKey() {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).clearSigningPubKey();
            return this;
        }

        public Builder setSigningPubKeyBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateSingleSigWalletRequest) this.instance).setSigningPubKeyBytes(byteString);
            return this;
        }
    }

    private CreateSingleSigWalletRequest() {
        String str = "";
        this.xPubKey_ = str;
        this.name_ = str;
        this.signingPubKey_ = str;
    }

    public String getXPubKey() {
        return this.xPubKey_;
    }

    public ByteString getXPubKeyBytes() {
        return ByteString.copyFromUtf8(this.xPubKey_);
    }

    /* access modifiers changed from: private */
    public void setXPubKey(String str) {
        if (str != null) {
            this.xPubKey_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearXPubKey() {
        this.xPubKey_ = getDefaultInstance().getXPubKey();
    }

    /* access modifiers changed from: private */
    public void setXPubKeyBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.xPubKey_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
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

    public String getSigningPubKey() {
        return this.signingPubKey_;
    }

    public ByteString getSigningPubKeyBytes() {
        return ByteString.copyFromUtf8(this.signingPubKey_);
    }

    /* access modifiers changed from: private */
    public void setSigningPubKey(String str) {
        if (str != null) {
            this.signingPubKey_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearSigningPubKey() {
        this.signingPubKey_ = getDefaultInstance().getSigningPubKey();
    }

    /* access modifiers changed from: private */
    public void setSigningPubKeyBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.signingPubKey_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.xPubKey_.isEmpty()) {
            codedOutputStream.writeString(1, getXPubKey());
        }
        if (this.network_ != Network.NETWORK_NOT_SET.getNumber()) {
            codedOutputStream.writeEnum(2, this.network_);
        }
        if (this.coin_ != Coin.COIN_NOT_SET.getNumber()) {
            codedOutputStream.writeEnum(3, this.coin_);
        }
        if (!this.name_.isEmpty()) {
            codedOutputStream.writeString(4, getName());
        }
        if (!this.signingPubKey_.isEmpty()) {
            codedOutputStream.writeString(5, getSigningPubKey());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.xPubKey_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getXPubKey());
        }
        if (this.network_ != Network.NETWORK_NOT_SET.getNumber()) {
            i2 += CodedOutputStream.computeEnumSize(2, this.network_);
        }
        if (this.coin_ != Coin.COIN_NOT_SET.getNumber()) {
            i2 += CodedOutputStream.computeEnumSize(3, this.coin_);
        }
        if (!this.name_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(4, getName());
        }
        if (!this.signingPubKey_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(5, getSigningPubKey());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static CreateSingleSigWalletRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CreateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static CreateSingleSigWalletRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static CreateSingleSigWalletRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CreateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static CreateSingleSigWalletRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static CreateSingleSigWalletRequest parseFrom(InputStream inputStream) throws IOException {
        return (CreateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateSingleSigWalletRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateSingleSigWalletRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CreateSingleSigWalletRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateSingleSigWalletRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateSingleSigWalletRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateSingleSigWalletRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CreateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static CreateSingleSigWalletRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CreateSingleSigWalletRequest createSingleSigWalletRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(createSingleSigWalletRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new CreateSingleSigWalletRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                CreateSingleSigWalletRequest createSingleSigWalletRequest = (CreateSingleSigWalletRequest) obj2;
                this.xPubKey_ = visitor.visitString(!this.xPubKey_.isEmpty(), this.xPubKey_, !createSingleSigWalletRequest.xPubKey_.isEmpty(), createSingleSigWalletRequest.xPubKey_);
                this.network_ = visitor.visitInt(this.network_ != 0, this.network_, createSingleSigWalletRequest.network_ != 0, createSingleSigWalletRequest.network_);
                boolean z2 = this.coin_ != 0;
                int i = this.coin_;
                if (createSingleSigWalletRequest.coin_ != 0) {
                    z = true;
                }
                this.coin_ = visitor.visitInt(z2, i, z, createSingleSigWalletRequest.coin_);
                this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !createSingleSigWalletRequest.name_.isEmpty(), createSingleSigWalletRequest.name_);
                this.signingPubKey_ = visitor.visitString(!this.signingPubKey_.isEmpty(), this.signingPubKey_, !createSingleSigWalletRequest.signingPubKey_.isEmpty(), createSingleSigWalletRequest.signingPubKey_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.xPubKey_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 16) {
                                this.network_ = codedInputStream.readEnum();
                            } else if (readTag == 24) {
                                this.coin_ = codedInputStream.readEnum();
                            } else if (readTag == 34) {
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 42) {
                                this.signingPubKey_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (CreateSingleSigWalletRequest.class) {
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

    public static CreateSingleSigWalletRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateSingleSigWalletRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
