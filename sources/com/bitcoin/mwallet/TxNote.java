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

public final class TxNote extends GeneratedMessageLite<TxNote, Builder> implements TxNoteOrBuilder {
    /* access modifiers changed from: private */
    public static final TxNote DEFAULT_INSTANCE = new TxNote();
    public static final int NOTE_FIELD_NUMBER = 2;
    private static volatile Parser<TxNote> PARSER = null;
    public static final int TXID_FIELD_NUMBER = 1;
    private String note_;
    private String txId_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<TxNote, Builder> implements TxNoteOrBuilder {
        private Builder() {
            super(TxNote.DEFAULT_INSTANCE);
        }

        public String getTxId() {
            return ((TxNote) this.instance).getTxId();
        }

        public ByteString getTxIdBytes() {
            return ((TxNote) this.instance).getTxIdBytes();
        }

        public Builder setTxId(String str) {
            copyOnWrite();
            ((TxNote) this.instance).setTxId(str);
            return this;
        }

        public Builder clearTxId() {
            copyOnWrite();
            ((TxNote) this.instance).clearTxId();
            return this;
        }

        public Builder setTxIdBytes(ByteString byteString) {
            copyOnWrite();
            ((TxNote) this.instance).setTxIdBytes(byteString);
            return this;
        }

        public String getNote() {
            return ((TxNote) this.instance).getNote();
        }

        public ByteString getNoteBytes() {
            return ((TxNote) this.instance).getNoteBytes();
        }

        public Builder setNote(String str) {
            copyOnWrite();
            ((TxNote) this.instance).setNote(str);
            return this;
        }

        public Builder clearNote() {
            copyOnWrite();
            ((TxNote) this.instance).clearNote();
            return this;
        }

        public Builder setNoteBytes(ByteString byteString) {
            copyOnWrite();
            ((TxNote) this.instance).setNoteBytes(byteString);
            return this;
        }
    }

    private TxNote() {
        String str = "";
        this.txId_ = str;
        this.note_ = str;
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
        if (!this.txId_.isEmpty()) {
            codedOutputStream.writeString(1, getTxId());
        }
        if (!this.note_.isEmpty()) {
            codedOutputStream.writeString(2, getNote());
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
        if (!this.note_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getNote());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static TxNote parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TxNote) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static TxNote parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TxNote) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static TxNote parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TxNote) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static TxNote parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TxNote) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static TxNote parseFrom(InputStream inputStream) throws IOException {
        return (TxNote) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TxNote parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TxNote) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TxNote parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (TxNote) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static TxNote parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TxNote) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static TxNote parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (TxNote) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static TxNote parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (TxNote) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(TxNote txNote) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(txNote);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new TxNote();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                TxNote txNote = (TxNote) obj2;
                this.txId_ = visitor.visitString(!this.txId_.isEmpty(), this.txId_, !txNote.txId_.isEmpty(), txNote.txId_);
                this.note_ = visitor.visitString(!this.note_.isEmpty(), this.note_, true ^ txNote.note_.isEmpty(), txNote.note_);
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
                                this.txId_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.note_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (TxNote.class) {
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

    public static TxNote getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TxNote> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
