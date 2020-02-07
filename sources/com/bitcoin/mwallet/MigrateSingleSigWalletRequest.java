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

public final class MigrateSingleSigWalletRequest extends GeneratedMessageLite<MigrateSingleSigWalletRequest, Builder> implements MigrateSingleSigWalletRequestOrBuilder {
    public static final int COIN_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final MigrateSingleSigWalletRequest DEFAULT_INSTANCE = new MigrateSingleSigWalletRequest();
    public static final int MULTI_SIG_FIELD_NUMBER = 5;
    public static final int NETWORK_FIELD_NUMBER = 3;
    public static final int OLD_WALLET_ID_FIELD_NUMBER = 1;
    private static volatile Parser<MigrateSingleSigWalletRequest> PARSER = null;
    public static final int SIGNING_PUB_KEY_FIELD_NUMBER = 6;
    public static final int X_PUB_KEY_FIELD_NUMBER = 2;
    private int coin_;
    private boolean multiSig_;
    private int network_;
    private String oldWalletId_;
    private String signingPubKey_;
    private String xPubKey_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<MigrateSingleSigWalletRequest, Builder> implements MigrateSingleSigWalletRequestOrBuilder {
        private Builder() {
            super(MigrateSingleSigWalletRequest.DEFAULT_INSTANCE);
        }

        public String getOldWalletId() {
            return ((MigrateSingleSigWalletRequest) this.instance).getOldWalletId();
        }

        public ByteString getOldWalletIdBytes() {
            return ((MigrateSingleSigWalletRequest) this.instance).getOldWalletIdBytes();
        }

        public Builder setOldWalletId(String str) {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).setOldWalletId(str);
            return this;
        }

        public Builder clearOldWalletId() {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).clearOldWalletId();
            return this;
        }

        public Builder setOldWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).setOldWalletIdBytes(byteString);
            return this;
        }

        public String getXPubKey() {
            return ((MigrateSingleSigWalletRequest) this.instance).getXPubKey();
        }

        public ByteString getXPubKeyBytes() {
            return ((MigrateSingleSigWalletRequest) this.instance).getXPubKeyBytes();
        }

        public Builder setXPubKey(String str) {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).setXPubKey(str);
            return this;
        }

        public Builder clearXPubKey() {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).clearXPubKey();
            return this;
        }

        public Builder setXPubKeyBytes(ByteString byteString) {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).setXPubKeyBytes(byteString);
            return this;
        }

        public int getNetworkValue() {
            return ((MigrateSingleSigWalletRequest) this.instance).getNetworkValue();
        }

        public Builder setNetworkValue(int i) {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).setNetworkValue(i);
            return this;
        }

        public Network getNetwork() {
            return ((MigrateSingleSigWalletRequest) this.instance).getNetwork();
        }

        public Builder setNetwork(Network network) {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).setNetwork(network);
            return this;
        }

        public Builder clearNetwork() {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).clearNetwork();
            return this;
        }

        public int getCoinValue() {
            return ((MigrateSingleSigWalletRequest) this.instance).getCoinValue();
        }

        public Builder setCoinValue(int i) {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).setCoinValue(i);
            return this;
        }

        public Coin getCoin() {
            return ((MigrateSingleSigWalletRequest) this.instance).getCoin();
        }

        public Builder setCoin(Coin coin) {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).setCoin(coin);
            return this;
        }

        public Builder clearCoin() {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).clearCoin();
            return this;
        }

        public boolean getMultiSig() {
            return ((MigrateSingleSigWalletRequest) this.instance).getMultiSig();
        }

        public Builder setMultiSig(boolean z) {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).setMultiSig(z);
            return this;
        }

        public Builder clearMultiSig() {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).clearMultiSig();
            return this;
        }

        public String getSigningPubKey() {
            return ((MigrateSingleSigWalletRequest) this.instance).getSigningPubKey();
        }

        public ByteString getSigningPubKeyBytes() {
            return ((MigrateSingleSigWalletRequest) this.instance).getSigningPubKeyBytes();
        }

        public Builder setSigningPubKey(String str) {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).setSigningPubKey(str);
            return this;
        }

        public Builder clearSigningPubKey() {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).clearSigningPubKey();
            return this;
        }

        public Builder setSigningPubKeyBytes(ByteString byteString) {
            copyOnWrite();
            ((MigrateSingleSigWalletRequest) this.instance).setSigningPubKeyBytes(byteString);
            return this;
        }
    }

    private MigrateSingleSigWalletRequest() {
        String str = "";
        this.oldWalletId_ = str;
        this.xPubKey_ = str;
        this.signingPubKey_ = str;
    }

    public String getOldWalletId() {
        return this.oldWalletId_;
    }

    public ByteString getOldWalletIdBytes() {
        return ByteString.copyFromUtf8(this.oldWalletId_);
    }

    /* access modifiers changed from: private */
    public void setOldWalletId(String str) {
        if (str != null) {
            this.oldWalletId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearOldWalletId() {
        this.oldWalletId_ = getDefaultInstance().getOldWalletId();
    }

    /* access modifiers changed from: private */
    public void setOldWalletIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.oldWalletId_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
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

    public boolean getMultiSig() {
        return this.multiSig_;
    }

    /* access modifiers changed from: private */
    public void setMultiSig(boolean z) {
        this.multiSig_ = z;
    }

    /* access modifiers changed from: private */
    public void clearMultiSig() {
        this.multiSig_ = false;
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
        if (!this.oldWalletId_.isEmpty()) {
            codedOutputStream.writeString(1, getOldWalletId());
        }
        if (!this.xPubKey_.isEmpty()) {
            codedOutputStream.writeString(2, getXPubKey());
        }
        if (this.network_ != Network.NETWORK_NOT_SET.getNumber()) {
            codedOutputStream.writeEnum(3, this.network_);
        }
        if (this.coin_ != Coin.COIN_NOT_SET.getNumber()) {
            codedOutputStream.writeEnum(4, this.coin_);
        }
        boolean z = this.multiSig_;
        if (z) {
            codedOutputStream.writeBool(5, z);
        }
        if (!this.signingPubKey_.isEmpty()) {
            codedOutputStream.writeString(6, getSigningPubKey());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.oldWalletId_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getOldWalletId());
        }
        if (!this.xPubKey_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getXPubKey());
        }
        if (this.network_ != Network.NETWORK_NOT_SET.getNumber()) {
            i2 += CodedOutputStream.computeEnumSize(3, this.network_);
        }
        if (this.coin_ != Coin.COIN_NOT_SET.getNumber()) {
            i2 += CodedOutputStream.computeEnumSize(4, this.coin_);
        }
        boolean z = this.multiSig_;
        if (z) {
            i2 += CodedOutputStream.computeBoolSize(5, z);
        }
        if (!this.signingPubKey_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(6, getSigningPubKey());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static MigrateSingleSigWalletRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MigrateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static MigrateSingleSigWalletRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MigrateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MigrateSingleSigWalletRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MigrateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MigrateSingleSigWalletRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MigrateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MigrateSingleSigWalletRequest parseFrom(InputStream inputStream) throws IOException {
        return (MigrateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MigrateSingleSigWalletRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MigrateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MigrateSingleSigWalletRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MigrateSingleSigWalletRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MigrateSingleSigWalletRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MigrateSingleSigWalletRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MigrateSingleSigWalletRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MigrateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MigrateSingleSigWalletRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MigrateSingleSigWalletRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MigrateSingleSigWalletRequest migrateSingleSigWalletRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(migrateSingleSigWalletRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new MigrateSingleSigWalletRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                MigrateSingleSigWalletRequest migrateSingleSigWalletRequest = (MigrateSingleSigWalletRequest) obj2;
                this.oldWalletId_ = visitor.visitString(!this.oldWalletId_.isEmpty(), this.oldWalletId_, !migrateSingleSigWalletRequest.oldWalletId_.isEmpty(), migrateSingleSigWalletRequest.oldWalletId_);
                this.xPubKey_ = visitor.visitString(!this.xPubKey_.isEmpty(), this.xPubKey_, !migrateSingleSigWalletRequest.xPubKey_.isEmpty(), migrateSingleSigWalletRequest.xPubKey_);
                this.network_ = visitor.visitInt(this.network_ != 0, this.network_, migrateSingleSigWalletRequest.network_ != 0, migrateSingleSigWalletRequest.network_);
                boolean z2 = this.coin_ != 0;
                int i = this.coin_;
                if (migrateSingleSigWalletRequest.coin_ != 0) {
                    z = true;
                }
                this.coin_ = visitor.visitInt(z2, i, z, migrateSingleSigWalletRequest.coin_);
                boolean z3 = this.multiSig_;
                boolean z4 = migrateSingleSigWalletRequest.multiSig_;
                this.multiSig_ = visitor.visitBoolean(z3, z3, z4, z4);
                this.signingPubKey_ = visitor.visitString(!this.signingPubKey_.isEmpty(), this.signingPubKey_, !migrateSingleSigWalletRequest.signingPubKey_.isEmpty(), migrateSingleSigWalletRequest.signingPubKey_);
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
                                this.oldWalletId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.xPubKey_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 24) {
                                this.network_ = codedInputStream.readEnum();
                            } else if (readTag == 32) {
                                this.coin_ = codedInputStream.readEnum();
                            } else if (readTag == 40) {
                                this.multiSig_ = codedInputStream.readBool();
                            } else if (readTag == 50) {
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
                    synchronized (MigrateSingleSigWalletRequest.class) {
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

    public static MigrateSingleSigWalletRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MigrateSingleSigWalletRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
