package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class TxHistory extends GeneratedMessageLite<TxHistory, Builder> implements TxHistoryOrBuilder {
    public static final int CONFIRMED_FIELD_NUMBER = 8;
    /* access modifiers changed from: private */
    public static final TxHistory DEFAULT_INSTANCE = new TxHistory();
    public static final int FEE_FIELD_NUMBER = 4;
    public static final int LEGACY_ADDRESS_FIELD_NUMBER = 6;
    public static final int NOTE_FIELD_NUMBER = 10;
    private static volatile Parser<TxHistory> PARSER = null;
    public static final int SATOSHIS_FIELD_NUMBER = 5;
    public static final int TIMESTAMP_FIELD_NUMBER = 3;
    public static final int TOKEN_FIELD_NUMBER = 9;
    public static final int TX_ID_FIELD_NUMBER = 2;
    public static final int TX_TYPE_FIELD_NUMBER = 7;
    public static final int WALLET_ID_FIELD_NUMBER = 1;
    private boolean confirmed_;
    private long fee_;
    private String legacyAddress_;
    private String note_;
    private long satoshis_;
    private long timestamp_;
    private Token token_;
    private String txId_;
    private int txType_;
    private String walletId_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<TxHistory, Builder> implements TxHistoryOrBuilder {
        private Builder() {
            super(TxHistory.DEFAULT_INSTANCE);
        }

        public String getWalletId() {
            return ((TxHistory) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((TxHistory) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((TxHistory) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((TxHistory) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((TxHistory) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public String getTxId() {
            return ((TxHistory) this.instance).getTxId();
        }

        public ByteString getTxIdBytes() {
            return ((TxHistory) this.instance).getTxIdBytes();
        }

        public Builder setTxId(String str) {
            copyOnWrite();
            ((TxHistory) this.instance).setTxId(str);
            return this;
        }

        public Builder clearTxId() {
            copyOnWrite();
            ((TxHistory) this.instance).clearTxId();
            return this;
        }

        public Builder setTxIdBytes(ByteString byteString) {
            copyOnWrite();
            ((TxHistory) this.instance).setTxIdBytes(byteString);
            return this;
        }

        public long getTimestamp() {
            return ((TxHistory) this.instance).getTimestamp();
        }

        public Builder setTimestamp(long j) {
            copyOnWrite();
            ((TxHistory) this.instance).setTimestamp(j);
            return this;
        }

        public Builder clearTimestamp() {
            copyOnWrite();
            ((TxHistory) this.instance).clearTimestamp();
            return this;
        }

        public long getFee() {
            return ((TxHistory) this.instance).getFee();
        }

        public Builder setFee(long j) {
            copyOnWrite();
            ((TxHistory) this.instance).setFee(j);
            return this;
        }

        public Builder clearFee() {
            copyOnWrite();
            ((TxHistory) this.instance).clearFee();
            return this;
        }

        public long getSatoshis() {
            return ((TxHistory) this.instance).getSatoshis();
        }

        public Builder setSatoshis(long j) {
            copyOnWrite();
            ((TxHistory) this.instance).setSatoshis(j);
            return this;
        }

        public Builder clearSatoshis() {
            copyOnWrite();
            ((TxHistory) this.instance).clearSatoshis();
            return this;
        }

        public String getLegacyAddress() {
            return ((TxHistory) this.instance).getLegacyAddress();
        }

        public ByteString getLegacyAddressBytes() {
            return ((TxHistory) this.instance).getLegacyAddressBytes();
        }

        public Builder setLegacyAddress(String str) {
            copyOnWrite();
            ((TxHistory) this.instance).setLegacyAddress(str);
            return this;
        }

        public Builder clearLegacyAddress() {
            copyOnWrite();
            ((TxHistory) this.instance).clearLegacyAddress();
            return this;
        }

        public Builder setLegacyAddressBytes(ByteString byteString) {
            copyOnWrite();
            ((TxHistory) this.instance).setLegacyAddressBytes(byteString);
            return this;
        }

        public int getTxTypeValue() {
            return ((TxHistory) this.instance).getTxTypeValue();
        }

        public Builder setTxTypeValue(int i) {
            copyOnWrite();
            ((TxHistory) this.instance).setTxTypeValue(i);
            return this;
        }

        public TxType getTxType() {
            return ((TxHistory) this.instance).getTxType();
        }

        public Builder setTxType(TxType txType) {
            copyOnWrite();
            ((TxHistory) this.instance).setTxType(txType);
            return this;
        }

        public Builder clearTxType() {
            copyOnWrite();
            ((TxHistory) this.instance).clearTxType();
            return this;
        }

        public boolean getConfirmed() {
            return ((TxHistory) this.instance).getConfirmed();
        }

        public Builder setConfirmed(boolean z) {
            copyOnWrite();
            ((TxHistory) this.instance).setConfirmed(z);
            return this;
        }

        public Builder clearConfirmed() {
            copyOnWrite();
            ((TxHistory) this.instance).clearConfirmed();
            return this;
        }

        public boolean hasToken() {
            return ((TxHistory) this.instance).hasToken();
        }

        public Token getToken() {
            return ((TxHistory) this.instance).getToken();
        }

        public Builder setToken(Token token) {
            copyOnWrite();
            ((TxHistory) this.instance).setToken(token);
            return this;
        }

        public Builder setToken(com.bitcoin.mwallet.Token.Builder builder) {
            copyOnWrite();
            ((TxHistory) this.instance).setToken(builder);
            return this;
        }

        public Builder mergeToken(Token token) {
            copyOnWrite();
            ((TxHistory) this.instance).mergeToken(token);
            return this;
        }

        public Builder clearToken() {
            copyOnWrite();
            ((TxHistory) this.instance).clearToken();
            return this;
        }

        public String getNote() {
            return ((TxHistory) this.instance).getNote();
        }

        public ByteString getNoteBytes() {
            return ((TxHistory) this.instance).getNoteBytes();
        }

        public Builder setNote(String str) {
            copyOnWrite();
            ((TxHistory) this.instance).setNote(str);
            return this;
        }

        public Builder clearNote() {
            copyOnWrite();
            ((TxHistory) this.instance).clearNote();
            return this;
        }

        public Builder setNoteBytes(ByteString byteString) {
            copyOnWrite();
            ((TxHistory) this.instance).setNoteBytes(byteString);
            return this;
        }
    }

    public enum TxType implements EnumLite {
        TX_TYPE_UNKNOWN(0),
        TX_TYPE_SENT(1),
        TX_TYPE_RECEIVED(2),
        TX_TYPE_MOVED(3),
        UNRECOGNIZED(-1);
        
        public static final int TX_TYPE_MOVED_VALUE = 3;
        public static final int TX_TYPE_RECEIVED_VALUE = 2;
        public static final int TX_TYPE_SENT_VALUE = 1;
        public static final int TX_TYPE_UNKNOWN_VALUE = 0;
        private static final EnumLiteMap<TxType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new EnumLiteMap<TxType>() {
                public TxType findValueByNumber(int i) {
                    return TxType.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static TxType valueOf(int i) {
            return forNumber(i);
        }

        public static TxType forNumber(int i) {
            if (i == 0) {
                return TX_TYPE_UNKNOWN;
            }
            if (i == 1) {
                return TX_TYPE_SENT;
            }
            if (i == 2) {
                return TX_TYPE_RECEIVED;
            }
            if (i != 3) {
                return null;
            }
            return TX_TYPE_MOVED;
        }

        public static EnumLiteMap<TxType> internalGetValueMap() {
            return internalValueMap;
        }

        private TxType(int i) {
            this.value = i;
        }
    }

    private TxHistory() {
        String str = "";
        this.walletId_ = str;
        this.txId_ = str;
        this.legacyAddress_ = str;
        this.note_ = str;
    }

    public String getWalletId() {
        return this.walletId_;
    }

    public ByteString getWalletIdBytes() {
        return ByteString.copyFromUtf8(this.walletId_);
    }

    /* access modifiers changed from: private */
    public void setWalletId(String str) {
        if (str != null) {
            this.walletId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearWalletId() {
        this.walletId_ = getDefaultInstance().getWalletId();
    }

    /* access modifiers changed from: private */
    public void setWalletIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.walletId_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getTxId() {
        return this.txId_;
    }

    public ByteString getTxIdBytes() {
        return ByteString.copyFromUtf8(this.txId_);
    }

    /* access modifiers changed from: private */
    public void setTxId(String str) {
        if (str != null) {
            this.txId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearTxId() {
        this.txId_ = getDefaultInstance().getTxId();
    }

    /* access modifiers changed from: private */
    public void setTxIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.txId_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public long getTimestamp() {
        return this.timestamp_;
    }

    /* access modifiers changed from: private */
    public void setTimestamp(long j) {
        this.timestamp_ = j;
    }

    /* access modifiers changed from: private */
    public void clearTimestamp() {
        this.timestamp_ = 0;
    }

    public long getFee() {
        return this.fee_;
    }

    /* access modifiers changed from: private */
    public void setFee(long j) {
        this.fee_ = j;
    }

    /* access modifiers changed from: private */
    public void clearFee() {
        this.fee_ = 0;
    }

    public long getSatoshis() {
        return this.satoshis_;
    }

    /* access modifiers changed from: private */
    public void setSatoshis(long j) {
        this.satoshis_ = j;
    }

    /* access modifiers changed from: private */
    public void clearSatoshis() {
        this.satoshis_ = 0;
    }

    public String getLegacyAddress() {
        return this.legacyAddress_;
    }

    public ByteString getLegacyAddressBytes() {
        return ByteString.copyFromUtf8(this.legacyAddress_);
    }

    /* access modifiers changed from: private */
    public void setLegacyAddress(String str) {
        if (str != null) {
            this.legacyAddress_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearLegacyAddress() {
        this.legacyAddress_ = getDefaultInstance().getLegacyAddress();
    }

    /* access modifiers changed from: private */
    public void setLegacyAddressBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.legacyAddress_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public int getTxTypeValue() {
        return this.txType_;
    }

    public TxType getTxType() {
        TxType forNumber = TxType.forNumber(this.txType_);
        return forNumber == null ? TxType.UNRECOGNIZED : forNumber;
    }

    /* access modifiers changed from: private */
    public void setTxTypeValue(int i) {
        this.txType_ = i;
    }

    /* access modifiers changed from: private */
    public void setTxType(TxType txType) {
        if (txType != null) {
            this.txType_ = txType.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearTxType() {
        this.txType_ = 0;
    }

    public boolean getConfirmed() {
        return this.confirmed_;
    }

    /* access modifiers changed from: private */
    public void setConfirmed(boolean z) {
        this.confirmed_ = z;
    }

    /* access modifiers changed from: private */
    public void clearConfirmed() {
        this.confirmed_ = false;
    }

    public boolean hasToken() {
        return this.token_ != null;
    }

    public Token getToken() {
        Token token = this.token_;
        return token == null ? Token.getDefaultInstance() : token;
    }

    /* access modifiers changed from: private */
    public void setToken(Token token) {
        if (token != null) {
            this.token_ = token;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setToken(com.bitcoin.mwallet.Token.Builder builder) {
        this.token_ = (Token) builder.build();
    }

    /* access modifiers changed from: private */
    public void mergeToken(Token token) {
        Token token2 = this.token_;
        if (token2 == null || token2 == Token.getDefaultInstance()) {
            this.token_ = token;
        } else {
            this.token_ = (Token) ((com.bitcoin.mwallet.Token.Builder) Token.newBuilder(this.token_).mergeFrom(token)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearToken() {
        this.token_ = null;
    }

    public String getNote() {
        return this.note_;
    }

    public ByteString getNoteBytes() {
        return ByteString.copyFromUtf8(this.note_);
    }

    /* access modifiers changed from: private */
    public void setNote(String str) {
        if (str != null) {
            this.note_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearNote() {
        this.note_ = getDefaultInstance().getNote();
    }

    /* access modifiers changed from: private */
    public void setNoteBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.note_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(1, getWalletId());
        }
        if (!this.txId_.isEmpty()) {
            codedOutputStream.writeString(2, getTxId());
        }
        long j = this.timestamp_;
        if (j != 0) {
            codedOutputStream.writeUInt64(3, j);
        }
        long j2 = this.fee_;
        if (j2 != 0) {
            codedOutputStream.writeUInt64(4, j2);
        }
        long j3 = this.satoshis_;
        if (j3 != 0) {
            codedOutputStream.writeUInt64(5, j3);
        }
        if (!this.legacyAddress_.isEmpty()) {
            codedOutputStream.writeString(6, getLegacyAddress());
        }
        if (this.txType_ != TxType.TX_TYPE_UNKNOWN.getNumber()) {
            codedOutputStream.writeEnum(7, this.txType_);
        }
        boolean z = this.confirmed_;
        if (z) {
            codedOutputStream.writeBool(8, z);
        }
        if (this.token_ != null) {
            codedOutputStream.writeMessage(9, getToken());
        }
        if (!this.note_.isEmpty()) {
            codedOutputStream.writeString(10, getNote());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.walletId_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getWalletId());
        }
        if (!this.txId_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getTxId());
        }
        long j = this.timestamp_;
        if (j != 0) {
            i2 += CodedOutputStream.computeUInt64Size(3, j);
        }
        long j2 = this.fee_;
        if (j2 != 0) {
            i2 += CodedOutputStream.computeUInt64Size(4, j2);
        }
        long j3 = this.satoshis_;
        if (j3 != 0) {
            i2 += CodedOutputStream.computeUInt64Size(5, j3);
        }
        if (!this.legacyAddress_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(6, getLegacyAddress());
        }
        if (this.txType_ != TxType.TX_TYPE_UNKNOWN.getNumber()) {
            i2 += CodedOutputStream.computeEnumSize(7, this.txType_);
        }
        boolean z = this.confirmed_;
        if (z) {
            i2 += CodedOutputStream.computeBoolSize(8, z);
        }
        if (this.token_ != null) {
            i2 += CodedOutputStream.computeMessageSize(9, getToken());
        }
        if (!this.note_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(10, getNote());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static TxHistory parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TxHistory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TxHistory parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TxHistory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TxHistory parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TxHistory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TxHistory parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TxHistory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TxHistory parseFrom(InputStream inputStream) throws IOException {
        return (TxHistory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TxHistory parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TxHistory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TxHistory parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (TxHistory) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TxHistory parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TxHistory) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TxHistory parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (TxHistory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TxHistory parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TxHistory) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(TxHistory txHistory) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(txHistory);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new TxHistory();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                TxHistory txHistory = (TxHistory) obj2;
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, !txHistory.walletId_.isEmpty(), txHistory.walletId_);
                this.txId_ = visitor.visitString(!this.txId_.isEmpty(), this.txId_, !txHistory.txId_.isEmpty(), txHistory.txId_);
                this.timestamp_ = visitor.visitLong(this.timestamp_ != 0, this.timestamp_, txHistory.timestamp_ != 0, txHistory.timestamp_);
                this.fee_ = visitor.visitLong(this.fee_ != 0, this.fee_, txHistory.fee_ != 0, txHistory.fee_);
                this.satoshis_ = visitor.visitLong(this.satoshis_ != 0, this.satoshis_, txHistory.satoshis_ != 0, txHistory.satoshis_);
                this.legacyAddress_ = visitor.visitString(!this.legacyAddress_.isEmpty(), this.legacyAddress_, !txHistory.legacyAddress_.isEmpty(), txHistory.legacyAddress_);
                boolean z2 = this.txType_ != 0;
                int i = this.txType_;
                if (txHistory.txType_ != 0) {
                    z = true;
                }
                this.txType_ = visitor.visitInt(z2, i, z, txHistory.txType_);
                boolean z3 = this.confirmed_;
                boolean z4 = txHistory.confirmed_;
                this.confirmed_ = visitor.visitBoolean(z3, z3, z4, z4);
                this.token_ = (Token) visitor.visitMessage(this.token_, txHistory.token_);
                this.note_ = visitor.visitString(!this.note_.isEmpty(), this.note_, !txHistory.note_.isEmpty(), txHistory.note_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z = true;
                                break;
                            case 10:
                                this.walletId_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 18:
                                this.txId_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 24:
                                this.timestamp_ = codedInputStream.readUInt64();
                                break;
                            case 32:
                                this.fee_ = codedInputStream.readUInt64();
                                break;
                            case 40:
                                this.satoshis_ = codedInputStream.readUInt64();
                                break;
                            case 50:
                                this.legacyAddress_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 56:
                                this.txType_ = codedInputStream.readEnum();
                                break;
                            case 64:
                                this.confirmed_ = codedInputStream.readBool();
                                break;
                            case 74:
                                com.bitcoin.mwallet.Token.Builder builder = this.token_ != null ? (com.bitcoin.mwallet.Token.Builder) this.token_.toBuilder() : null;
                                this.token_ = (Token) codedInputStream.readMessage(Token.parser(), extensionRegistryLite);
                                if (builder == null) {
                                    break;
                                } else {
                                    builder.mergeFrom(this.token_);
                                    this.token_ = (Token) builder.buildPartial();
                                    break;
                                }
                            case 82:
                                this.note_ = codedInputStream.readStringRequireUtf8();
                                break;
                            default:
                                if (codedInputStream.skipField(readTag)) {
                                    break;
                                }
                                z = true;
                                break;
                        }
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
                    synchronized (TxHistory.class) {
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

    public static TxHistory getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TxHistory> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
