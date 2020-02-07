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

public final class SweepUtxo extends GeneratedMessageLite<SweepUtxo, Builder> implements SweepUtxoOrBuilder {
    public static final int AMOUNT_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final SweepUtxo DEFAULT_INSTANCE = new SweepUtxo();
    private static volatile Parser<SweepUtxo> PARSER = null;
    public static final int SCRIPTPUBKEY_FIELD_NUMBER = 1;
    public static final int TX_ID_FIELD_NUMBER = 4;
    public static final int UTXOINDEX_FIELD_NUMBER = 3;
    private String amount_;
    private String scriptPubkey_;
    private String txId_;
    private int utxoIndex_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<SweepUtxo, Builder> implements SweepUtxoOrBuilder {
        private Builder() {
            super(SweepUtxo.DEFAULT_INSTANCE);
        }

        public String getScriptPubkey() {
            return ((SweepUtxo) this.instance).getScriptPubkey();
        }

        public ByteString getScriptPubkeyBytes() {
            return ((SweepUtxo) this.instance).getScriptPubkeyBytes();
        }

        public Builder setScriptPubkey(String str) {
            copyOnWrite();
            ((SweepUtxo) this.instance).setScriptPubkey(str);
            return this;
        }

        public Builder clearScriptPubkey() {
            copyOnWrite();
            ((SweepUtxo) this.instance).clearScriptPubkey();
            return this;
        }

        public Builder setScriptPubkeyBytes(ByteString byteString) {
            copyOnWrite();
            ((SweepUtxo) this.instance).setScriptPubkeyBytes(byteString);
            return this;
        }

        public String getAmount() {
            return ((SweepUtxo) this.instance).getAmount();
        }

        public ByteString getAmountBytes() {
            return ((SweepUtxo) this.instance).getAmountBytes();
        }

        public Builder setAmount(String str) {
            copyOnWrite();
            ((SweepUtxo) this.instance).setAmount(str);
            return this;
        }

        public Builder clearAmount() {
            copyOnWrite();
            ((SweepUtxo) this.instance).clearAmount();
            return this;
        }

        public Builder setAmountBytes(ByteString byteString) {
            copyOnWrite();
            ((SweepUtxo) this.instance).setAmountBytes(byteString);
            return this;
        }

        public String getTxId() {
            return ((SweepUtxo) this.instance).getTxId();
        }

        public ByteString getTxIdBytes() {
            return ((SweepUtxo) this.instance).getTxIdBytes();
        }

        public Builder setTxId(String str) {
            copyOnWrite();
            ((SweepUtxo) this.instance).setTxId(str);
            return this;
        }

        public Builder clearTxId() {
            copyOnWrite();
            ((SweepUtxo) this.instance).clearTxId();
            return this;
        }

        public Builder setTxIdBytes(ByteString byteString) {
            copyOnWrite();
            ((SweepUtxo) this.instance).setTxIdBytes(byteString);
            return this;
        }

        public int getUtxoIndex() {
            return ((SweepUtxo) this.instance).getUtxoIndex();
        }

        public Builder setUtxoIndex(int i) {
            copyOnWrite();
            ((SweepUtxo) this.instance).setUtxoIndex(i);
            return this;
        }

        public Builder clearUtxoIndex() {
            copyOnWrite();
            ((SweepUtxo) this.instance).clearUtxoIndex();
            return this;
        }
    }

    private SweepUtxo() {
        String str = "";
        this.scriptPubkey_ = str;
        this.amount_ = str;
        this.txId_ = str;
    }

    public String getScriptPubkey() {
        return this.scriptPubkey_;
    }

    public ByteString getScriptPubkeyBytes() {
        return ByteString.copyFromUtf8(this.scriptPubkey_);
    }

    /* access modifiers changed from: private */
    public void setScriptPubkey(String str) {
        if (str != null) {
            this.scriptPubkey_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearScriptPubkey() {
        this.scriptPubkey_ = getDefaultInstance().getScriptPubkey();
    }

    /* access modifiers changed from: private */
    public void setScriptPubkeyBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.scriptPubkey_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getAmount() {
        return this.amount_;
    }

    public ByteString getAmountBytes() {
        return ByteString.copyFromUtf8(this.amount_);
    }

    /* access modifiers changed from: private */
    public void setAmount(String str) {
        if (str != null) {
            this.amount_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearAmount() {
        this.amount_ = getDefaultInstance().getAmount();
    }

    /* access modifiers changed from: private */
    public void setAmountBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.amount_ = byteString.toStringUtf8();
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

    public int getUtxoIndex() {
        return this.utxoIndex_;
    }

    /* access modifiers changed from: private */
    public void setUtxoIndex(int i) {
        this.utxoIndex_ = i;
    }

    /* access modifiers changed from: private */
    public void clearUtxoIndex() {
        this.utxoIndex_ = 0;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.scriptPubkey_.isEmpty()) {
            codedOutputStream.writeString(1, getScriptPubkey());
        }
        if (!this.amount_.isEmpty()) {
            codedOutputStream.writeString(2, getAmount());
        }
        int i = this.utxoIndex_;
        if (i != 0) {
            codedOutputStream.writeInt32(3, i);
        }
        if (!this.txId_.isEmpty()) {
            codedOutputStream.writeString(4, getTxId());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.scriptPubkey_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getScriptPubkey());
        }
        if (!this.amount_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getAmount());
        }
        int i3 = this.utxoIndex_;
        if (i3 != 0) {
            i2 += CodedOutputStream.computeInt32Size(3, i3);
        }
        if (!this.txId_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(4, getTxId());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static SweepUtxo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (SweepUtxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static SweepUtxo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SweepUtxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static SweepUtxo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (SweepUtxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static SweepUtxo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (SweepUtxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static SweepUtxo parseFrom(InputStream inputStream) throws IOException {
        return (SweepUtxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SweepUtxo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SweepUtxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SweepUtxo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (SweepUtxo) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static SweepUtxo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SweepUtxo) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static SweepUtxo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (SweepUtxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static SweepUtxo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (SweepUtxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(SweepUtxo sweepUtxo) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(sweepUtxo);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new SweepUtxo();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                SweepUtxo sweepUtxo = (SweepUtxo) obj2;
                this.scriptPubkey_ = visitor.visitString(!this.scriptPubkey_.isEmpty(), this.scriptPubkey_, !sweepUtxo.scriptPubkey_.isEmpty(), sweepUtxo.scriptPubkey_);
                this.amount_ = visitor.visitString(!this.amount_.isEmpty(), this.amount_, !sweepUtxo.amount_.isEmpty(), sweepUtxo.amount_);
                this.txId_ = visitor.visitString(!this.txId_.isEmpty(), this.txId_, !sweepUtxo.txId_.isEmpty(), sweepUtxo.txId_);
                boolean z2 = this.utxoIndex_ != 0;
                int i = this.utxoIndex_;
                if (sweepUtxo.utxoIndex_ != 0) {
                    z = true;
                }
                this.utxoIndex_ = visitor.visitInt(z2, i, z, sweepUtxo.utxoIndex_);
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
                                this.scriptPubkey_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.amount_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 24) {
                                this.utxoIndex_ = codedInputStream.readInt32();
                            } else if (readTag == 34) {
                                this.txId_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (SweepUtxo.class) {
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

    public static SweepUtxo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<SweepUtxo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
