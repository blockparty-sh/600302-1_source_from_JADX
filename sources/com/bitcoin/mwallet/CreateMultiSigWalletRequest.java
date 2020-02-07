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

public final class CreateMultiSigWalletRequest extends GeneratedMessageLite<CreateMultiSigWalletRequest, Builder> implements CreateMultiSigWalletRequestOrBuilder {
    public static final int COIN_FIELD_NUMBER = 3;
    /* access modifiers changed from: private */
    public static final CreateMultiSigWalletRequest DEFAULT_INSTANCE = new CreateMultiSigWalletRequest();
    public static final int NAME_FIELD_NUMBER = 7;
    public static final int NETWORK_FIELD_NUMBER = 2;
    public static final int NUM_COPAYERS_FIELD_NUMBER = 5;
    private static volatile Parser<CreateMultiSigWalletRequest> PARSER = null;
    public static final int REQUIRED_SIGNATURES_FIELD_NUMBER = 4;
    public static final int X_PUB_KEY_FIELD_NUMBER = 1;
    public static final int YOUR_NAME_FIELD_NUMBER = 6;
    private int coin_;
    private String name_;
    private int network_;
    private int numCopayers_;
    private int requiredSignatures_;
    private String xPubKey_;
    private String yourName_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<CreateMultiSigWalletRequest, Builder> implements CreateMultiSigWalletRequestOrBuilder {
        private Builder() {
            super(CreateMultiSigWalletRequest.DEFAULT_INSTANCE);
        }

        public String getXPubKey() {
            return ((CreateMultiSigWalletRequest) this.instance).getXPubKey();
        }

        public ByteString getXPubKeyBytes() {
            return ((CreateMultiSigWalletRequest) this.instance).getXPubKeyBytes();
        }

        public Builder setXPubKey(String str) {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).setXPubKey(str);
            return this;
        }

        public Builder clearXPubKey() {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).clearXPubKey();
            return this;
        }

        public Builder setXPubKeyBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).setXPubKeyBytes(byteString);
            return this;
        }

        public int getNetworkValue() {
            return ((CreateMultiSigWalletRequest) this.instance).getNetworkValue();
        }

        public Builder setNetworkValue(int i) {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).setNetworkValue(i);
            return this;
        }

        public Network getNetwork() {
            return ((CreateMultiSigWalletRequest) this.instance).getNetwork();
        }

        public Builder setNetwork(Network network) {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).setNetwork(network);
            return this;
        }

        public Builder clearNetwork() {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).clearNetwork();
            return this;
        }

        public int getCoinValue() {
            return ((CreateMultiSigWalletRequest) this.instance).getCoinValue();
        }

        public Builder setCoinValue(int i) {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).setCoinValue(i);
            return this;
        }

        public Coin getCoin() {
            return ((CreateMultiSigWalletRequest) this.instance).getCoin();
        }

        public Builder setCoin(Coin coin) {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).setCoin(coin);
            return this;
        }

        public Builder clearCoin() {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).clearCoin();
            return this;
        }

        public int getRequiredSignatures() {
            return ((CreateMultiSigWalletRequest) this.instance).getRequiredSignatures();
        }

        public Builder setRequiredSignatures(int i) {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).setRequiredSignatures(i);
            return this;
        }

        public Builder clearRequiredSignatures() {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).clearRequiredSignatures();
            return this;
        }

        public int getNumCopayers() {
            return ((CreateMultiSigWalletRequest) this.instance).getNumCopayers();
        }

        public Builder setNumCopayers(int i) {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).setNumCopayers(i);
            return this;
        }

        public Builder clearNumCopayers() {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).clearNumCopayers();
            return this;
        }

        public String getYourName() {
            return ((CreateMultiSigWalletRequest) this.instance).getYourName();
        }

        public ByteString getYourNameBytes() {
            return ((CreateMultiSigWalletRequest) this.instance).getYourNameBytes();
        }

        public Builder setYourName(String str) {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).setYourName(str);
            return this;
        }

        public Builder clearYourName() {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).clearYourName();
            return this;
        }

        public Builder setYourNameBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).setYourNameBytes(byteString);
            return this;
        }

        public String getName() {
            return ((CreateMultiSigWalletRequest) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((CreateMultiSigWalletRequest) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateMultiSigWalletRequest) this.instance).setNameBytes(byteString);
            return this;
        }
    }

    private CreateMultiSigWalletRequest() {
        String str = "";
        this.xPubKey_ = str;
        this.yourName_ = str;
        this.name_ = str;
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

    public int getRequiredSignatures() {
        return this.requiredSignatures_;
    }

    /* access modifiers changed from: private */
    public void setRequiredSignatures(int i) {
        this.requiredSignatures_ = i;
    }

    /* access modifiers changed from: private */
    public void clearRequiredSignatures() {
        this.requiredSignatures_ = 0;
    }

    public int getNumCopayers() {
        return this.numCopayers_;
    }

    /* access modifiers changed from: private */
    public void setNumCopayers(int i) {
        this.numCopayers_ = i;
    }

    /* access modifiers changed from: private */
    public void clearNumCopayers() {
        this.numCopayers_ = 0;
    }

    public String getYourName() {
        return this.yourName_;
    }

    public ByteString getYourNameBytes() {
        return ByteString.copyFromUtf8(this.yourName_);
    }

    /* access modifiers changed from: private */
    public void setYourName(String str) {
        if (str != null) {
            this.yourName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearYourName() {
        this.yourName_ = getDefaultInstance().getYourName();
    }

    /* access modifiers changed from: private */
    public void setYourNameBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.yourName_ = byteString.toStringUtf8();
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
        int i = this.requiredSignatures_;
        if (i != 0) {
            codedOutputStream.writeInt32(4, i);
        }
        int i2 = this.numCopayers_;
        if (i2 != 0) {
            codedOutputStream.writeInt32(5, i2);
        }
        if (!this.yourName_.isEmpty()) {
            codedOutputStream.writeString(6, getYourName());
        }
        if (!this.name_.isEmpty()) {
            codedOutputStream.writeString(7, getName());
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
        int i3 = this.requiredSignatures_;
        if (i3 != 0) {
            i2 += CodedOutputStream.computeInt32Size(4, i3);
        }
        int i4 = this.numCopayers_;
        if (i4 != 0) {
            i2 += CodedOutputStream.computeInt32Size(5, i4);
        }
        if (!this.yourName_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(6, getYourName());
        }
        if (!this.name_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(7, getName());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static CreateMultiSigWalletRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CreateMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static CreateMultiSigWalletRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static CreateMultiSigWalletRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CreateMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static CreateMultiSigWalletRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static CreateMultiSigWalletRequest parseFrom(InputStream inputStream) throws IOException {
        return (CreateMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateMultiSigWalletRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateMultiSigWalletRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CreateMultiSigWalletRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateMultiSigWalletRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateMultiSigWalletRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateMultiSigWalletRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CreateMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static CreateMultiSigWalletRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateMultiSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CreateMultiSigWalletRequest createMultiSigWalletRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(createMultiSigWalletRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new CreateMultiSigWalletRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                CreateMultiSigWalletRequest createMultiSigWalletRequest = (CreateMultiSigWalletRequest) obj2;
                this.xPubKey_ = visitor.visitString(!this.xPubKey_.isEmpty(), this.xPubKey_, !createMultiSigWalletRequest.xPubKey_.isEmpty(), createMultiSigWalletRequest.xPubKey_);
                this.network_ = visitor.visitInt(this.network_ != 0, this.network_, createMultiSigWalletRequest.network_ != 0, createMultiSigWalletRequest.network_);
                this.coin_ = visitor.visitInt(this.coin_ != 0, this.coin_, createMultiSigWalletRequest.coin_ != 0, createMultiSigWalletRequest.coin_);
                this.requiredSignatures_ = visitor.visitInt(this.requiredSignatures_ != 0, this.requiredSignatures_, createMultiSigWalletRequest.requiredSignatures_ != 0, createMultiSigWalletRequest.requiredSignatures_);
                boolean z2 = this.numCopayers_ != 0;
                int i = this.numCopayers_;
                if (createMultiSigWalletRequest.numCopayers_ != 0) {
                    z = true;
                }
                this.numCopayers_ = visitor.visitInt(z2, i, z, createMultiSigWalletRequest.numCopayers_);
                this.yourName_ = visitor.visitString(!this.yourName_.isEmpty(), this.yourName_, !createMultiSigWalletRequest.yourName_.isEmpty(), createMultiSigWalletRequest.yourName_);
                this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !createMultiSigWalletRequest.name_.isEmpty(), createMultiSigWalletRequest.name_);
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
                            } else if (readTag == 32) {
                                this.requiredSignatures_ = codedInputStream.readInt32();
                            } else if (readTag == 40) {
                                this.numCopayers_ = codedInputStream.readInt32();
                            } else if (readTag == 50) {
                                this.yourName_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 58) {
                                this.name_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (CreateMultiSigWalletRequest.class) {
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

    public static CreateMultiSigWalletRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateMultiSigWalletRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
