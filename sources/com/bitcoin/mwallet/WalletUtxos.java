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
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class WalletUtxos extends GeneratedMessageLite<WalletUtxos, Builder> implements WalletUtxosOrBuilder {
    /* access modifiers changed from: private */
    public static final WalletUtxos DEFAULT_INSTANCE = new WalletUtxos();
    private static volatile Parser<WalletUtxos> PARSER = null;
    public static final int UTXO_FIELD_NUMBER = 2;
    public static final int WALLET_ID_FIELD_NUMBER = 1;
    private int bitField0_;
    private ProtobufList<Utxo> utxo_ = emptyProtobufList();
    private String walletId_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<WalletUtxos, Builder> implements WalletUtxosOrBuilder {
        private Builder() {
            super(WalletUtxos.DEFAULT_INSTANCE);
        }

        public String getWalletId() {
            return ((WalletUtxos) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((WalletUtxos) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((WalletUtxos) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((WalletUtxos) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((WalletUtxos) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public List<Utxo> getUtxoList() {
            return Collections.unmodifiableList(((WalletUtxos) this.instance).getUtxoList());
        }

        public int getUtxoCount() {
            return ((WalletUtxos) this.instance).getUtxoCount();
        }

        public Utxo getUtxo(int i) {
            return ((WalletUtxos) this.instance).getUtxo(i);
        }

        public Builder setUtxo(int i, Utxo utxo) {
            copyOnWrite();
            ((WalletUtxos) this.instance).setUtxo(i, utxo);
            return this;
        }

        public Builder setUtxo(int i, com.bitcoin.mwallet.Utxo.Builder builder) {
            copyOnWrite();
            ((WalletUtxos) this.instance).setUtxo(i, builder);
            return this;
        }

        public Builder addUtxo(Utxo utxo) {
            copyOnWrite();
            ((WalletUtxos) this.instance).addUtxo(utxo);
            return this;
        }

        public Builder addUtxo(int i, Utxo utxo) {
            copyOnWrite();
            ((WalletUtxos) this.instance).addUtxo(i, utxo);
            return this;
        }

        public Builder addUtxo(com.bitcoin.mwallet.Utxo.Builder builder) {
            copyOnWrite();
            ((WalletUtxos) this.instance).addUtxo(builder);
            return this;
        }

        public Builder addUtxo(int i, com.bitcoin.mwallet.Utxo.Builder builder) {
            copyOnWrite();
            ((WalletUtxos) this.instance).addUtxo(i, builder);
            return this;
        }

        public Builder addAllUtxo(Iterable<? extends Utxo> iterable) {
            copyOnWrite();
            ((WalletUtxos) this.instance).addAllUtxo(iterable);
            return this;
        }

        public Builder clearUtxo() {
            copyOnWrite();
            ((WalletUtxos) this.instance).clearUtxo();
            return this;
        }

        public Builder removeUtxo(int i) {
            copyOnWrite();
            ((WalletUtxos) this.instance).removeUtxo(i);
            return this;
        }
    }

    private WalletUtxos() {
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

    public List<Utxo> getUtxoList() {
        return this.utxo_;
    }

    public List<? extends UtxoOrBuilder> getUtxoOrBuilderList() {
        return this.utxo_;
    }

    public int getUtxoCount() {
        return this.utxo_.size();
    }

    public Utxo getUtxo(int i) {
        return (Utxo) this.utxo_.get(i);
    }

    public UtxoOrBuilder getUtxoOrBuilder(int i) {
        return (UtxoOrBuilder) this.utxo_.get(i);
    }

    private void ensureUtxoIsMutable() {
        if (!this.utxo_.isModifiable()) {
            this.utxo_ = GeneratedMessageLite.mutableCopy(this.utxo_);
        }
    }

    /* access modifiers changed from: private */
    public void setUtxo(int i, Utxo utxo) {
        if (utxo != null) {
            ensureUtxoIsMutable();
            this.utxo_.set(i, utxo);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setUtxo(int i, com.bitcoin.mwallet.Utxo.Builder builder) {
        ensureUtxoIsMutable();
        this.utxo_.set(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addUtxo(Utxo utxo) {
        if (utxo != null) {
            ensureUtxoIsMutable();
            this.utxo_.add(utxo);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addUtxo(int i, Utxo utxo) {
        if (utxo != null) {
            ensureUtxoIsMutable();
            this.utxo_.add(i, utxo);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addUtxo(com.bitcoin.mwallet.Utxo.Builder builder) {
        ensureUtxoIsMutable();
        this.utxo_.add(builder.build());
    }

    /* access modifiers changed from: private */
    public void addUtxo(int i, com.bitcoin.mwallet.Utxo.Builder builder) {
        ensureUtxoIsMutable();
        this.utxo_.add(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllUtxo(Iterable<? extends Utxo> iterable) {
        ensureUtxoIsMutable();
        AbstractMessageLite.addAll(iterable, this.utxo_);
    }

    /* access modifiers changed from: private */
    public void clearUtxo() {
        this.utxo_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeUtxo(int i) {
        ensureUtxoIsMutable();
        this.utxo_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(1, getWalletId());
        }
        for (int i = 0; i < this.utxo_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.utxo_.get(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !this.walletId_.isEmpty() ? CodedOutputStream.computeStringSize(1, getWalletId()) + 0 : 0;
        for (int i2 = 0; i2 < this.utxo_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, (MessageLite) this.utxo_.get(i2));
        }
        this.memoizedSerializedSize = computeStringSize;
        return computeStringSize;
    }

    public static WalletUtxos parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (WalletUtxos) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static WalletUtxos parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WalletUtxos) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static WalletUtxos parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (WalletUtxos) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static WalletUtxos parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (WalletUtxos) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static WalletUtxos parseFrom(InputStream inputStream) throws IOException {
        return (WalletUtxos) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WalletUtxos parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WalletUtxos) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WalletUtxos parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (WalletUtxos) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static WalletUtxos parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WalletUtxos) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static WalletUtxos parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (WalletUtxos) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static WalletUtxos parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (WalletUtxos) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(WalletUtxos walletUtxos) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(walletUtxos);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new WalletUtxos();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.utxo_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                WalletUtxos walletUtxos = (WalletUtxos) obj2;
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, true ^ walletUtxos.walletId_.isEmpty(), walletUtxos.walletId_);
                this.utxo_ = visitor.visitList(this.utxo_, walletUtxos.utxo_);
                if (visitor == MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= walletUtxos.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.walletId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                if (!this.utxo_.isModifiable()) {
                                    this.utxo_ = GeneratedMessageLite.mutableCopy(this.utxo_);
                                }
                                this.utxo_.add(codedInputStream.readMessage(Utxo.parser(), extensionRegistryLite));
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
                    synchronized (WalletUtxos.class) {
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

    public static WalletUtxos getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<WalletUtxos> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
