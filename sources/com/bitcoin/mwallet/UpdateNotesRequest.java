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

public final class UpdateNotesRequest extends GeneratedMessageLite<UpdateNotesRequest, Builder> implements UpdateNotesRequestOrBuilder {
    public static final int COPAYERID_FIELD_NUMBER = 2;
    /* access modifiers changed from: private */
    public static final UpdateNotesRequest DEFAULT_INSTANCE = new UpdateNotesRequest();
    private static volatile Parser<UpdateNotesRequest> PARSER = null;
    public static final int TXNOTE_FIELD_NUMBER = 3;
    public static final int WALLETID_FIELD_NUMBER = 1;
    private int bitField0_;
    private String copayerId_;
    private ProtobufList<TxNote> txNote_ = emptyProtobufList();
    private String walletId_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<UpdateNotesRequest, Builder> implements UpdateNotesRequestOrBuilder {
        private Builder() {
            super(UpdateNotesRequest.DEFAULT_INSTANCE);
        }

        public String getWalletId() {
            return ((UpdateNotesRequest) this.instance).getWalletId();
        }

        public ByteString getWalletIdBytes() {
            return ((UpdateNotesRequest) this.instance).getWalletIdBytes();
        }

        public Builder setWalletId(String str) {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).setWalletId(str);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).clearWalletId();
            return this;
        }

        public Builder setWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).setWalletIdBytes(byteString);
            return this;
        }

        public String getCopayerId() {
            return ((UpdateNotesRequest) this.instance).getCopayerId();
        }

        public ByteString getCopayerIdBytes() {
            return ((UpdateNotesRequest) this.instance).getCopayerIdBytes();
        }

        public Builder setCopayerId(String str) {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).setCopayerId(str);
            return this;
        }

        public Builder clearCopayerId() {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).clearCopayerId();
            return this;
        }

        public Builder setCopayerIdBytes(ByteString byteString) {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).setCopayerIdBytes(byteString);
            return this;
        }

        public List<TxNote> getTxNoteList() {
            return Collections.unmodifiableList(((UpdateNotesRequest) this.instance).getTxNoteList());
        }

        public int getTxNoteCount() {
            return ((UpdateNotesRequest) this.instance).getTxNoteCount();
        }

        public TxNote getTxNote(int i) {
            return ((UpdateNotesRequest) this.instance).getTxNote(i);
        }

        public Builder setTxNote(int i, TxNote txNote) {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).setTxNote(i, txNote);
            return this;
        }

        public Builder setTxNote(int i, com.bitcoin.mwallet.TxNote.Builder builder) {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).setTxNote(i, builder);
            return this;
        }

        public Builder addTxNote(TxNote txNote) {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).addTxNote(txNote);
            return this;
        }

        public Builder addTxNote(int i, TxNote txNote) {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).addTxNote(i, txNote);
            return this;
        }

        public Builder addTxNote(com.bitcoin.mwallet.TxNote.Builder builder) {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).addTxNote(builder);
            return this;
        }

        public Builder addTxNote(int i, com.bitcoin.mwallet.TxNote.Builder builder) {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).addTxNote(i, builder);
            return this;
        }

        public Builder addAllTxNote(Iterable<? extends TxNote> iterable) {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).addAllTxNote(iterable);
            return this;
        }

        public Builder clearTxNote() {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).clearTxNote();
            return this;
        }

        public Builder removeTxNote(int i) {
            copyOnWrite();
            ((UpdateNotesRequest) this.instance).removeTxNote(i);
            return this;
        }
    }

    private UpdateNotesRequest() {
        String str = "";
        this.walletId_ = str;
        this.copayerId_ = str;
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

    public String getCopayerId() {
        return this.copayerId_;
    }

    public ByteString getCopayerIdBytes() {
        return ByteString.copyFromUtf8(this.copayerId_);
    }

    /* access modifiers changed from: private */
    public void setCopayerId(String str) {
        if (str != null) {
            this.copayerId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearCopayerId() {
        this.copayerId_ = getDefaultInstance().getCopayerId();
    }

    /* access modifiers changed from: private */
    public void setCopayerIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.copayerId_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public List<TxNote> getTxNoteList() {
        return this.txNote_;
    }

    public List<? extends TxNoteOrBuilder> getTxNoteOrBuilderList() {
        return this.txNote_;
    }

    public int getTxNoteCount() {
        return this.txNote_.size();
    }

    public TxNote getTxNote(int i) {
        return (TxNote) this.txNote_.get(i);
    }

    public TxNoteOrBuilder getTxNoteOrBuilder(int i) {
        return (TxNoteOrBuilder) this.txNote_.get(i);
    }

    private void ensureTxNoteIsMutable() {
        if (!this.txNote_.isModifiable()) {
            this.txNote_ = GeneratedMessageLite.mutableCopy(this.txNote_);
        }
    }

    /* access modifiers changed from: private */
    public void setTxNote(int i, TxNote txNote) {
        if (txNote != null) {
            ensureTxNoteIsMutable();
            this.txNote_.set(i, txNote);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setTxNote(int i, com.bitcoin.mwallet.TxNote.Builder builder) {
        ensureTxNoteIsMutable();
        this.txNote_.set(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addTxNote(TxNote txNote) {
        if (txNote != null) {
            ensureTxNoteIsMutable();
            this.txNote_.add(txNote);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addTxNote(int i, TxNote txNote) {
        if (txNote != null) {
            ensureTxNoteIsMutable();
            this.txNote_.add(i, txNote);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addTxNote(com.bitcoin.mwallet.TxNote.Builder builder) {
        ensureTxNoteIsMutable();
        this.txNote_.add(builder.build());
    }

    /* access modifiers changed from: private */
    public void addTxNote(int i, com.bitcoin.mwallet.TxNote.Builder builder) {
        ensureTxNoteIsMutable();
        this.txNote_.add(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllTxNote(Iterable<? extends TxNote> iterable) {
        ensureTxNoteIsMutable();
        AbstractMessageLite.addAll(iterable, this.txNote_);
    }

    /* access modifiers changed from: private */
    public void clearTxNote() {
        this.txNote_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeTxNote(int i) {
        ensureTxNoteIsMutable();
        this.txNote_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.walletId_.isEmpty()) {
            codedOutputStream.writeString(1, getWalletId());
        }
        if (!this.copayerId_.isEmpty()) {
            codedOutputStream.writeString(2, getCopayerId());
        }
        for (int i = 0; i < this.txNote_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.txNote_.get(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !this.walletId_.isEmpty() ? CodedOutputStream.computeStringSize(1, getWalletId()) + 0 : 0;
        if (!this.copayerId_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(2, getCopayerId());
        }
        for (int i2 = 0; i2 < this.txNote_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, (MessageLite) this.txNote_.get(i2));
        }
        this.memoizedSerializedSize = computeStringSize;
        return computeStringSize;
    }

    public static UpdateNotesRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (UpdateNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static UpdateNotesRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpdateNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static UpdateNotesRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (UpdateNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static UpdateNotesRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (UpdateNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static UpdateNotesRequest parseFrom(InputStream inputStream) throws IOException {
        return (UpdateNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UpdateNotesRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UpdateNotesRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (UpdateNotesRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static UpdateNotesRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateNotesRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static UpdateNotesRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (UpdateNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static UpdateNotesRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (UpdateNotesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(UpdateNotesRequest updateNotesRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(updateNotesRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new UpdateNotesRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.txNote_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                UpdateNotesRequest updateNotesRequest = (UpdateNotesRequest) obj2;
                this.walletId_ = visitor.visitString(!this.walletId_.isEmpty(), this.walletId_, !updateNotesRequest.walletId_.isEmpty(), updateNotesRequest.walletId_);
                this.copayerId_ = visitor.visitString(!this.copayerId_.isEmpty(), this.copayerId_, true ^ updateNotesRequest.copayerId_.isEmpty(), updateNotesRequest.copayerId_);
                this.txNote_ = visitor.visitList(this.txNote_, updateNotesRequest.txNote_);
                if (visitor == MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= updateNotesRequest.bitField0_;
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
                                this.copayerId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                if (!this.txNote_.isModifiable()) {
                                    this.txNote_ = GeneratedMessageLite.mutableCopy(this.txNote_);
                                }
                                this.txNote_.add(codedInputStream.readMessage(TxNote.parser(), extensionRegistryLite));
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
                    synchronized (UpdateNotesRequest.class) {
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

    public static UpdateNotesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<UpdateNotesRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
