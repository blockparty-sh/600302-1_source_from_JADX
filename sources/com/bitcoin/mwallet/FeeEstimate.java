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

public final class FeeEstimate extends GeneratedMessageLite<FeeEstimate, Builder> implements FeeEstimateOrBuilder {
    public static final int COIN_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final FeeEstimate DEFAULT_INSTANCE = new FeeEstimate();
    public static final int LEVEL_FIELD_NUMBER = 2;
    private static volatile Parser<FeeEstimate> PARSER = null;
    public static final int SATOSHI_PER_BYTE_FIELD_NUMBER = 3;
    private int coin_;
    private int level_;
    private String satoshiPerByte_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<FeeEstimate, Builder> implements FeeEstimateOrBuilder {
        private Builder() {
            super(FeeEstimate.DEFAULT_INSTANCE);
        }

        public int getCoinValue() {
            return ((FeeEstimate) this.instance).getCoinValue();
        }

        public Builder setCoinValue(int i) {
            copyOnWrite();
            ((FeeEstimate) this.instance).setCoinValue(i);
            return this;
        }

        public Coin getCoin() {
            return ((FeeEstimate) this.instance).getCoin();
        }

        public Builder setCoin(Coin coin) {
            copyOnWrite();
            ((FeeEstimate) this.instance).setCoin(coin);
            return this;
        }

        public Builder clearCoin() {
            copyOnWrite();
            ((FeeEstimate) this.instance).clearCoin();
            return this;
        }

        public int getLevelValue() {
            return ((FeeEstimate) this.instance).getLevelValue();
        }

        public Builder setLevelValue(int i) {
            copyOnWrite();
            ((FeeEstimate) this.instance).setLevelValue(i);
            return this;
        }

        public FeeLevel getLevel() {
            return ((FeeEstimate) this.instance).getLevel();
        }

        public Builder setLevel(FeeLevel feeLevel) {
            copyOnWrite();
            ((FeeEstimate) this.instance).setLevel(feeLevel);
            return this;
        }

        public Builder clearLevel() {
            copyOnWrite();
            ((FeeEstimate) this.instance).clearLevel();
            return this;
        }

        public String getSatoshiPerByte() {
            return ((FeeEstimate) this.instance).getSatoshiPerByte();
        }

        public ByteString getSatoshiPerByteBytes() {
            return ((FeeEstimate) this.instance).getSatoshiPerByteBytes();
        }

        public Builder setSatoshiPerByte(String str) {
            copyOnWrite();
            ((FeeEstimate) this.instance).setSatoshiPerByte(str);
            return this;
        }

        public Builder clearSatoshiPerByte() {
            copyOnWrite();
            ((FeeEstimate) this.instance).clearSatoshiPerByte();
            return this;
        }

        public Builder setSatoshiPerByteBytes(ByteString byteString) {
            copyOnWrite();
            ((FeeEstimate) this.instance).setSatoshiPerByteBytes(byteString);
            return this;
        }
    }

    private FeeEstimate() {
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

    public int getLevelValue() {
        return this.level_;
    }

    public FeeLevel getLevel() {
        FeeLevel forNumber = FeeLevel.forNumber(this.level_);
        return forNumber == null ? FeeLevel.UNRECOGNIZED : forNumber;
    }

    /* access modifiers changed from: private */
    public void setLevelValue(int i) {
        this.level_ = i;
    }

    /* access modifiers changed from: private */
    public void setLevel(FeeLevel feeLevel) {
        if (feeLevel != null) {
            this.level_ = feeLevel.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearLevel() {
        this.level_ = 0;
    }

    public String getSatoshiPerByte() {
        return this.satoshiPerByte_;
    }

    public ByteString getSatoshiPerByteBytes() {
        return ByteString.copyFromUtf8(this.satoshiPerByte_);
    }

    /* access modifiers changed from: private */
    public void setSatoshiPerByte(String str) {
        if (str != null) {
            this.satoshiPerByte_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearSatoshiPerByte() {
        this.satoshiPerByte_ = getDefaultInstance().getSatoshiPerByte();
    }

    /* access modifiers changed from: private */
    public void setSatoshiPerByteBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.satoshiPerByte_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.coin_ != Coin.COIN_NOT_SET.getNumber()) {
            codedOutputStream.writeEnum(1, this.coin_);
        }
        if (this.level_ != FeeLevel.UNKNOWN_FEE_LEVEL.getNumber()) {
            codedOutputStream.writeEnum(2, this.level_);
        }
        if (!this.satoshiPerByte_.isEmpty()) {
            codedOutputStream.writeString(3, getSatoshiPerByte());
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
        if (this.level_ != FeeLevel.UNKNOWN_FEE_LEVEL.getNumber()) {
            i2 += CodedOutputStream.computeEnumSize(2, this.level_);
        }
        if (!this.satoshiPerByte_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(3, getSatoshiPerByte());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static FeeEstimate parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (FeeEstimate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static FeeEstimate parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FeeEstimate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static FeeEstimate parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (FeeEstimate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static FeeEstimate parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FeeEstimate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static FeeEstimate parseFrom(InputStream inputStream) throws IOException {
        return (FeeEstimate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FeeEstimate parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FeeEstimate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static FeeEstimate parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (FeeEstimate) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FeeEstimate parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FeeEstimate) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static FeeEstimate parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (FeeEstimate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static FeeEstimate parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FeeEstimate) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(FeeEstimate feeEstimate) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(feeEstimate);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new FeeEstimate();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                FeeEstimate feeEstimate = (FeeEstimate) obj2;
                this.coin_ = visitor.visitInt(this.coin_ != 0, this.coin_, feeEstimate.coin_ != 0, feeEstimate.coin_);
                boolean z2 = this.level_ != 0;
                int i = this.level_;
                if (feeEstimate.level_ != 0) {
                    z = true;
                }
                this.level_ = visitor.visitInt(z2, i, z, feeEstimate.level_);
                this.satoshiPerByte_ = visitor.visitString(!this.satoshiPerByte_.isEmpty(), this.satoshiPerByte_, !feeEstimate.satoshiPerByte_.isEmpty(), feeEstimate.satoshiPerByte_);
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
                                this.level_ = codedInputStream.readEnum();
                            } else if (readTag == 26) {
                                this.satoshiPerByte_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (FeeEstimate.class) {
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

    public static FeeEstimate getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FeeEstimate> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
