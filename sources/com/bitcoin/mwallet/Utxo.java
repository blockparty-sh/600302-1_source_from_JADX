package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

public final class Utxo extends GeneratedMessageLite<Utxo, Builder> implements UtxoOrBuilder {
    public static final int ADDRESS_FIELD_NUMBER = 5;
    /* access modifiers changed from: private */
    public static final Utxo DEFAULT_INSTANCE = new Utxo();
    public static final int OUTPUT_INDEX_FIELD_NUMBER = 2;
    private static volatile Parser<Utxo> PARSER = null;
    public static final int SATOSHIS_FIELD_NUMBER = 3;
    public static final int SCRIPT_FIELD_NUMBER = 4;
    public static final int TOKEN_FIELD_NUMBER = 6;
    public static final int TX_ID_FIELD_NUMBER = 1;
    private UtxoAddress address_;
    private int outputIndex_;
    private long satoshis_;
    private ByteString script_ = ByteString.EMPTY;
    private Token token_;
    private String txId_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<Utxo, Builder> implements UtxoOrBuilder {
        private Builder() {
            super(Utxo.DEFAULT_INSTANCE);
        }

        public String getTxId() {
            return ((Utxo) this.instance).getTxId();
        }

        public ByteString getTxIdBytes() {
            return ((Utxo) this.instance).getTxIdBytes();
        }

        public Builder setTxId(String str) {
            copyOnWrite();
            ((Utxo) this.instance).setTxId(str);
            return this;
        }

        public Builder clearTxId() {
            copyOnWrite();
            ((Utxo) this.instance).clearTxId();
            return this;
        }

        public Builder setTxIdBytes(ByteString byteString) {
            copyOnWrite();
            ((Utxo) this.instance).setTxIdBytes(byteString);
            return this;
        }

        public int getOutputIndex() {
            return ((Utxo) this.instance).getOutputIndex();
        }

        public Builder setOutputIndex(int i) {
            copyOnWrite();
            ((Utxo) this.instance).setOutputIndex(i);
            return this;
        }

        public Builder clearOutputIndex() {
            copyOnWrite();
            ((Utxo) this.instance).clearOutputIndex();
            return this;
        }

        public long getSatoshis() {
            return ((Utxo) this.instance).getSatoshis();
        }

        public Builder setSatoshis(long j) {
            copyOnWrite();
            ((Utxo) this.instance).setSatoshis(j);
            return this;
        }

        public Builder clearSatoshis() {
            copyOnWrite();
            ((Utxo) this.instance).clearSatoshis();
            return this;
        }

        public ByteString getScript() {
            return ((Utxo) this.instance).getScript();
        }

        public Builder setScript(ByteString byteString) {
            copyOnWrite();
            ((Utxo) this.instance).setScript(byteString);
            return this;
        }

        public Builder clearScript() {
            copyOnWrite();
            ((Utxo) this.instance).clearScript();
            return this;
        }

        public boolean hasAddress() {
            return ((Utxo) this.instance).hasAddress();
        }

        public UtxoAddress getAddress() {
            return ((Utxo) this.instance).getAddress();
        }

        public Builder setAddress(UtxoAddress utxoAddress) {
            copyOnWrite();
            ((Utxo) this.instance).setAddress(utxoAddress);
            return this;
        }

        public Builder setAddress(Builder builder) {
            copyOnWrite();
            ((Utxo) this.instance).setAddress(builder);
            return this;
        }

        public Builder mergeAddress(UtxoAddress utxoAddress) {
            copyOnWrite();
            ((Utxo) this.instance).mergeAddress(utxoAddress);
            return this;
        }

        public Builder clearAddress() {
            copyOnWrite();
            ((Utxo) this.instance).clearAddress();
            return this;
        }

        public boolean hasToken() {
            return ((Utxo) this.instance).hasToken();
        }

        public Token getToken() {
            return ((Utxo) this.instance).getToken();
        }

        public Builder setToken(Token token) {
            copyOnWrite();
            ((Utxo) this.instance).setToken(token);
            return this;
        }

        public Builder setToken(com.bitcoin.mwallet.Token.Builder builder) {
            copyOnWrite();
            ((Utxo) this.instance).setToken(builder);
            return this;
        }

        public Builder mergeToken(Token token) {
            copyOnWrite();
            ((Utxo) this.instance).mergeToken(token);
            return this;
        }

        public Builder clearToken() {
            copyOnWrite();
            ((Utxo) this.instance).clearToken();
            return this;
        }
    }

    public static final class UtxoAddress extends GeneratedMessageLite<UtxoAddress, Builder> implements UtxoAddressOrBuilder {
        /* access modifiers changed from: private */
        public static final UtxoAddress DEFAULT_INSTANCE = new UtxoAddress();
        public static final int LEGACY_ADDRESS_FIELD_NUMBER = 1;
        private static volatile Parser<UtxoAddress> PARSER = null;
        public static final int PATH_X_FIELD_NUMBER = 2;
        public static final int PATH_Y_FIELD_NUMBER = 3;
        private String legacyAddress_ = "";
        private int pathX_;
        private int pathY_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<UtxoAddress, Builder> implements UtxoAddressOrBuilder {
            private Builder() {
                super(UtxoAddress.DEFAULT_INSTANCE);
            }

            public String getLegacyAddress() {
                return ((UtxoAddress) this.instance).getLegacyAddress();
            }

            public ByteString getLegacyAddressBytes() {
                return ((UtxoAddress) this.instance).getLegacyAddressBytes();
            }

            public Builder setLegacyAddress(String str) {
                copyOnWrite();
                ((UtxoAddress) this.instance).setLegacyAddress(str);
                return this;
            }

            public Builder clearLegacyAddress() {
                copyOnWrite();
                ((UtxoAddress) this.instance).clearLegacyAddress();
                return this;
            }

            public Builder setLegacyAddressBytes(ByteString byteString) {
                copyOnWrite();
                ((UtxoAddress) this.instance).setLegacyAddressBytes(byteString);
                return this;
            }

            public int getPathX() {
                return ((UtxoAddress) this.instance).getPathX();
            }

            public Builder setPathX(int i) {
                copyOnWrite();
                ((UtxoAddress) this.instance).setPathX(i);
                return this;
            }

            public Builder clearPathX() {
                copyOnWrite();
                ((UtxoAddress) this.instance).clearPathX();
                return this;
            }

            public int getPathY() {
                return ((UtxoAddress) this.instance).getPathY();
            }

            public Builder setPathY(int i) {
                copyOnWrite();
                ((UtxoAddress) this.instance).setPathY(i);
                return this;
            }

            public Builder clearPathY() {
                copyOnWrite();
                ((UtxoAddress) this.instance).clearPathY();
                return this;
            }
        }

        private UtxoAddress() {
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

        public int getPathX() {
            return this.pathX_;
        }

        /* access modifiers changed from: private */
        public void setPathX(int i) {
            this.pathX_ = i;
        }

        /* access modifiers changed from: private */
        public void clearPathX() {
            this.pathX_ = 0;
        }

        public int getPathY() {
            return this.pathY_;
        }

        /* access modifiers changed from: private */
        public void setPathY(int i) {
            this.pathY_ = i;
        }

        /* access modifiers changed from: private */
        public void clearPathY() {
            this.pathY_ = 0;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.legacyAddress_.isEmpty()) {
                codedOutputStream.writeString(1, getLegacyAddress());
            }
            int i = this.pathX_;
            if (i != 0) {
                codedOutputStream.writeUInt32(2, i);
            }
            int i2 = this.pathY_;
            if (i2 != 0) {
                codedOutputStream.writeUInt32(3, i2);
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.legacyAddress_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getLegacyAddress());
            }
            int i3 = this.pathX_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(2, i3);
            }
            int i4 = this.pathY_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeUInt32Size(3, i4);
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static UtxoAddress parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UtxoAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static UtxoAddress parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UtxoAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static UtxoAddress parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UtxoAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static UtxoAddress parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UtxoAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static UtxoAddress parseFrom(InputStream inputStream) throws IOException {
            return (UtxoAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UtxoAddress parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UtxoAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UtxoAddress parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UtxoAddress) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static UtxoAddress parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UtxoAddress) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static UtxoAddress parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UtxoAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static UtxoAddress parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UtxoAddress) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(UtxoAddress utxoAddress) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(utxoAddress);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new UtxoAddress();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder();
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    UtxoAddress utxoAddress = (UtxoAddress) obj2;
                    this.legacyAddress_ = visitor.visitString(!this.legacyAddress_.isEmpty(), this.legacyAddress_, !utxoAddress.legacyAddress_.isEmpty(), utxoAddress.legacyAddress_);
                    this.pathX_ = visitor.visitInt(this.pathX_ != 0, this.pathX_, utxoAddress.pathX_ != 0, utxoAddress.pathX_);
                    boolean z2 = this.pathY_ != 0;
                    int i = this.pathY_;
                    if (utxoAddress.pathY_ != 0) {
                        z = true;
                    }
                    this.pathY_ = visitor.visitInt(z2, i, z, utxoAddress.pathY_);
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
                                    this.legacyAddress_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 16) {
                                    this.pathX_ = codedInputStream.readUInt32();
                                } else if (readTag == 24) {
                                    this.pathY_ = codedInputStream.readUInt32();
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
                        synchronized (UtxoAddress.class) {
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

        public static UtxoAddress getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<UtxoAddress> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface UtxoAddressOrBuilder extends MessageLiteOrBuilder {
        String getLegacyAddress();

        ByteString getLegacyAddressBytes();

        int getPathX();

        int getPathY();
    }

    private Utxo() {
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

    public int getOutputIndex() {
        return this.outputIndex_;
    }

    /* access modifiers changed from: private */
    public void setOutputIndex(int i) {
        this.outputIndex_ = i;
    }

    /* access modifiers changed from: private */
    public void clearOutputIndex() {
        this.outputIndex_ = 0;
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

    public ByteString getScript() {
        return this.script_;
    }

    /* access modifiers changed from: private */
    public void setScript(ByteString byteString) {
        if (byteString != null) {
            this.script_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearScript() {
        this.script_ = getDefaultInstance().getScript();
    }

    public boolean hasAddress() {
        return this.address_ != null;
    }

    public UtxoAddress getAddress() {
        UtxoAddress utxoAddress = this.address_;
        return utxoAddress == null ? UtxoAddress.getDefaultInstance() : utxoAddress;
    }

    /* access modifiers changed from: private */
    public void setAddress(UtxoAddress utxoAddress) {
        if (utxoAddress != null) {
            this.address_ = utxoAddress;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setAddress(Builder builder) {
        this.address_ = (UtxoAddress) builder.build();
    }

    /* access modifiers changed from: private */
    public void mergeAddress(UtxoAddress utxoAddress) {
        UtxoAddress utxoAddress2 = this.address_;
        if (utxoAddress2 == null || utxoAddress2 == UtxoAddress.getDefaultInstance()) {
            this.address_ = utxoAddress;
        } else {
            this.address_ = (UtxoAddress) ((Builder) UtxoAddress.newBuilder(this.address_).mergeFrom(utxoAddress)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearAddress() {
        this.address_ = null;
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

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.txId_.isEmpty()) {
            codedOutputStream.writeString(1, getTxId());
        }
        int i = this.outputIndex_;
        if (i != 0) {
            codedOutputStream.writeUInt32(2, i);
        }
        long j = this.satoshis_;
        if (j != 0) {
            codedOutputStream.writeUInt64(3, j);
        }
        if (!this.script_.isEmpty()) {
            codedOutputStream.writeBytes(4, this.script_);
        }
        if (this.address_ != null) {
            codedOutputStream.writeMessage(5, getAddress());
        }
        if (this.token_ != null) {
            codedOutputStream.writeMessage(6, getToken());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.txId_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getTxId());
        }
        int i3 = this.outputIndex_;
        if (i3 != 0) {
            i2 += CodedOutputStream.computeUInt32Size(2, i3);
        }
        long j = this.satoshis_;
        if (j != 0) {
            i2 += CodedOutputStream.computeUInt64Size(3, j);
        }
        if (!this.script_.isEmpty()) {
            i2 += CodedOutputStream.computeBytesSize(4, this.script_);
        }
        if (this.address_ != null) {
            i2 += CodedOutputStream.computeMessageSize(5, getAddress());
        }
        if (this.token_ != null) {
            i2 += CodedOutputStream.computeMessageSize(6, getToken());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static Utxo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Utxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Utxo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Utxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Utxo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Utxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Utxo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Utxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Utxo parseFrom(InputStream inputStream) throws IOException {
        return (Utxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Utxo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Utxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Utxo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Utxo) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Utxo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Utxo) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Utxo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Utxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Utxo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Utxo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Utxo utxo) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(utxo);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new Utxo();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                Utxo utxo = (Utxo) obj2;
                this.txId_ = visitor.visitString(!this.txId_.isEmpty(), this.txId_, !utxo.txId_.isEmpty(), utxo.txId_);
                this.outputIndex_ = visitor.visitInt(this.outputIndex_ != 0, this.outputIndex_, utxo.outputIndex_ != 0, utxo.outputIndex_);
                this.satoshis_ = visitor.visitLong(this.satoshis_ != 0, this.satoshis_, utxo.satoshis_ != 0, utxo.satoshis_);
                boolean z2 = this.script_ != ByteString.EMPTY;
                ByteString byteString = this.script_;
                if (utxo.script_ != ByteString.EMPTY) {
                    z = true;
                }
                this.script_ = visitor.visitByteString(z2, byteString, z, utxo.script_);
                this.address_ = (UtxoAddress) visitor.visitMessage(this.address_, utxo.address_);
                this.token_ = (Token) visitor.visitMessage(this.token_, utxo.token_);
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
                                this.txId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 16) {
                                this.outputIndex_ = codedInputStream.readUInt32();
                            } else if (readTag == 24) {
                                this.satoshis_ = codedInputStream.readUInt64();
                            } else if (readTag == 34) {
                                this.script_ = codedInputStream.readBytes();
                            } else if (readTag == 42) {
                                Builder builder = this.address_ != null ? (Builder) this.address_.toBuilder() : null;
                                this.address_ = (UtxoAddress) codedInputStream.readMessage(UtxoAddress.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.address_);
                                    this.address_ = (UtxoAddress) builder.buildPartial();
                                }
                            } else if (readTag == 50) {
                                com.bitcoin.mwallet.Token.Builder builder2 = this.token_ != null ? (com.bitcoin.mwallet.Token.Builder) this.token_.toBuilder() : null;
                                this.token_ = (Token) codedInputStream.readMessage(Token.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom(this.token_);
                                    this.token_ = (Token) builder2.buildPartial();
                                }
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
                    synchronized (Utxo.class) {
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

    public static Utxo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Utxo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
