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

public final class MigrateResponse extends GeneratedMessageLite<MigrateResponse, Builder> implements MigrateResponseOrBuilder {
    public static final int CURRENT_WALLET_ID_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final MigrateResponse DEFAULT_INSTANCE = new MigrateResponse();
    private static volatile Parser<MigrateResponse> PARSER;
    private String currentWalletId_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<MigrateResponse, Builder> implements MigrateResponseOrBuilder {
        private Builder() {
            super(MigrateResponse.DEFAULT_INSTANCE);
        }

        public String getCurrentWalletId() {
            return ((MigrateResponse) this.instance).getCurrentWalletId();
        }

        public ByteString getCurrentWalletIdBytes() {
            return ((MigrateResponse) this.instance).getCurrentWalletIdBytes();
        }

        public Builder setCurrentWalletId(String str) {
            copyOnWrite();
            ((MigrateResponse) this.instance).setCurrentWalletId(str);
            return this;
        }

        public Builder clearCurrentWalletId() {
            copyOnWrite();
            ((MigrateResponse) this.instance).clearCurrentWalletId();
            return this;
        }

        public Builder setCurrentWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((MigrateResponse) this.instance).setCurrentWalletIdBytes(byteString);
            return this;
        }
    }

    private MigrateResponse() {
    }

    public String getCurrentWalletId() {
        return this.currentWalletId_;
    }

    public ByteString getCurrentWalletIdBytes() {
        return ByteString.copyFromUtf8(this.currentWalletId_);
    }

    /* access modifiers changed from: private */
    public void setCurrentWalletId(String str) {
        if (str != null) {
            this.currentWalletId_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearCurrentWalletId() {
        this.currentWalletId_ = getDefaultInstance().getCurrentWalletId();
    }

    /* access modifiers changed from: private */
    public void setCurrentWalletIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.currentWalletId_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.currentWalletId_.isEmpty()) {
            codedOutputStream.writeString(1, getCurrentWalletId());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.currentWalletId_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getCurrentWalletId());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static MigrateResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MigrateResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static MigrateResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MigrateResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MigrateResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MigrateResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MigrateResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MigrateResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MigrateResponse parseFrom(InputStream inputStream) throws IOException {
        return (MigrateResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MigrateResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MigrateResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MigrateResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MigrateResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MigrateResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MigrateResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MigrateResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MigrateResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MigrateResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MigrateResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MigrateResponse migrateResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(migrateResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new MigrateResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                MigrateResponse migrateResponse = (MigrateResponse) obj2;
                this.currentWalletId_ = ((Visitor) obj).visitString(!this.currentWalletId_.isEmpty(), this.currentWalletId_, true ^ migrateResponse.currentWalletId_.isEmpty(), migrateResponse.currentWalletId_);
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
                                this.currentWalletId_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (MigrateResponse.class) {
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

    public static MigrateResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MigrateResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
