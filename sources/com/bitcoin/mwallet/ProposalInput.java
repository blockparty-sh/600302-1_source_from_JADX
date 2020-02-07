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

public final class ProposalInput extends GeneratedMessageLite<ProposalInput, Builder> implements ProposalInputOrBuilder {
    /* access modifiers changed from: private */
    public static final ProposalInput DEFAULT_INSTANCE = new ProposalInput();
    private static volatile Parser<ProposalInput> PARSER = null;
    public static final int SIGNATURE_FIELD_NUMBER = 2;
    public static final int UTXO_FIELD_NUMBER = 1;
    private String signature_ = "";
    private Utxo utxo_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<ProposalInput, Builder> implements ProposalInputOrBuilder {
        private Builder() {
            super(ProposalInput.DEFAULT_INSTANCE);
        }

        public boolean hasUtxo() {
            return ((ProposalInput) this.instance).hasUtxo();
        }

        public Utxo getUtxo() {
            return ((ProposalInput) this.instance).getUtxo();
        }

        public Builder setUtxo(Utxo utxo) {
            copyOnWrite();
            ((ProposalInput) this.instance).setUtxo(utxo);
            return this;
        }

        public Builder setUtxo(com.bitcoin.mwallet.Utxo.Builder builder) {
            copyOnWrite();
            ((ProposalInput) this.instance).setUtxo(builder);
            return this;
        }

        public Builder mergeUtxo(Utxo utxo) {
            copyOnWrite();
            ((ProposalInput) this.instance).mergeUtxo(utxo);
            return this;
        }

        public Builder clearUtxo() {
            copyOnWrite();
            ((ProposalInput) this.instance).clearUtxo();
            return this;
        }

        public String getSignature() {
            return ((ProposalInput) this.instance).getSignature();
        }

        public ByteString getSignatureBytes() {
            return ((ProposalInput) this.instance).getSignatureBytes();
        }

        public Builder setSignature(String str) {
            copyOnWrite();
            ((ProposalInput) this.instance).setSignature(str);
            return this;
        }

        public Builder clearSignature() {
            copyOnWrite();
            ((ProposalInput) this.instance).clearSignature();
            return this;
        }

        public Builder setSignatureBytes(ByteString byteString) {
            copyOnWrite();
            ((ProposalInput) this.instance).setSignatureBytes(byteString);
            return this;
        }
    }

    private ProposalInput() {
    }

    public boolean hasUtxo() {
        return this.utxo_ != null;
    }

    public Utxo getUtxo() {
        Utxo utxo = this.utxo_;
        return utxo == null ? Utxo.getDefaultInstance() : utxo;
    }

    /* access modifiers changed from: private */
    public void setUtxo(Utxo utxo) {
        if (utxo != null) {
            this.utxo_ = utxo;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setUtxo(com.bitcoin.mwallet.Utxo.Builder builder) {
        this.utxo_ = (Utxo) builder.build();
    }

    /* access modifiers changed from: private */
    public void mergeUtxo(Utxo utxo) {
        Utxo utxo2 = this.utxo_;
        if (utxo2 == null || utxo2 == Utxo.getDefaultInstance()) {
            this.utxo_ = utxo;
        } else {
            this.utxo_ = (Utxo) ((com.bitcoin.mwallet.Utxo.Builder) Utxo.newBuilder(this.utxo_).mergeFrom(utxo)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearUtxo() {
        this.utxo_ = null;
    }

    public String getSignature() {
        return this.signature_;
    }

    public ByteString getSignatureBytes() {
        return ByteString.copyFromUtf8(this.signature_);
    }

    /* access modifiers changed from: private */
    public void setSignature(String str) {
        if (str != null) {
            this.signature_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearSignature() {
        this.signature_ = getDefaultInstance().getSignature();
    }

    /* access modifiers changed from: private */
    public void setSignatureBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.signature_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.utxo_ != null) {
            codedOutputStream.writeMessage(1, getUtxo());
        }
        if (!this.signature_.isEmpty()) {
            codedOutputStream.writeString(2, getSignature());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.utxo_ != null) {
            i2 = 0 + CodedOutputStream.computeMessageSize(1, getUtxo());
        }
        if (!this.signature_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getSignature());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static ProposalInput parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ProposalInput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ProposalInput parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProposalInput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ProposalInput parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ProposalInput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ProposalInput parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ProposalInput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ProposalInput parseFrom(InputStream inputStream) throws IOException {
        return (ProposalInput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ProposalInput parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProposalInput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ProposalInput parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ProposalInput) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ProposalInput parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProposalInput) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ProposalInput parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ProposalInput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ProposalInput parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ProposalInput) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ProposalInput proposalInput) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(proposalInput);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new ProposalInput();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                ProposalInput proposalInput = (ProposalInput) obj2;
                this.utxo_ = (Utxo) visitor.visitMessage(this.utxo_, proposalInput.utxo_);
                this.signature_ = visitor.visitString(!this.signature_.isEmpty(), this.signature_, true ^ proposalInput.signature_.isEmpty(), proposalInput.signature_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
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
                                com.bitcoin.mwallet.Utxo.Builder builder = this.utxo_ != null ? (com.bitcoin.mwallet.Utxo.Builder) this.utxo_.toBuilder() : null;
                                this.utxo_ = (Utxo) codedInputStream.readMessage(Utxo.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.utxo_);
                                    this.utxo_ = (Utxo) builder.buildPartial();
                                }
                            } else if (readTag == 18) {
                                this.signature_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (ProposalInput.class) {
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

    public static ProposalInput getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ProposalInput> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
