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
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class GetUtxosRequest extends GeneratedMessageLite<GetUtxosRequest, Builder> implements GetUtxosRequestOrBuilder {
    /* access modifiers changed from: private */
    public static final GetUtxosRequest DEFAULT_INSTANCE = new GetUtxosRequest();
    private static volatile Parser<GetUtxosRequest> PARSER = null;
    public static final int WALLET_ID_FIELD_NUMBER = 1;
    private ProtobufList<String> walletId_ = GeneratedMessageLite.emptyProtobufList();

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<GetUtxosRequest, Builder> implements GetUtxosRequestOrBuilder {
        private Builder() {
            super(GetUtxosRequest.DEFAULT_INSTANCE);
        }

        public List<String> getWalletIdList() {
            return Collections.unmodifiableList(((GetUtxosRequest) this.instance).getWalletIdList());
        }

        public int getWalletIdCount() {
            return ((GetUtxosRequest) this.instance).getWalletIdCount();
        }

        public String getWalletId(int i) {
            return ((GetUtxosRequest) this.instance).getWalletId(i);
        }

        public ByteString getWalletIdBytes(int i) {
            return ((GetUtxosRequest) this.instance).getWalletIdBytes(i);
        }

        public Builder setWalletId(int i, String str) {
            copyOnWrite();
            ((GetUtxosRequest) this.instance).setWalletId(i, str);
            return this;
        }

        public Builder addWalletId(String str) {
            copyOnWrite();
            ((GetUtxosRequest) this.instance).addWalletId(str);
            return this;
        }

        public Builder addAllWalletId(Iterable<String> iterable) {
            copyOnWrite();
            ((GetUtxosRequest) this.instance).addAllWalletId(iterable);
            return this;
        }

        public Builder clearWalletId() {
            copyOnWrite();
            ((GetUtxosRequest) this.instance).clearWalletId();
            return this;
        }

        public Builder addWalletIdBytes(ByteString byteString) {
            copyOnWrite();
            ((GetUtxosRequest) this.instance).addWalletIdBytes(byteString);
            return this;
        }
    }

    private GetUtxosRequest() {
    }

    public List<String> getWalletIdList() {
        return this.walletId_;
    }

    public int getWalletIdCount() {
        return this.walletId_.size();
    }

    public String getWalletId(int i) {
        return (String) this.walletId_.get(i);
    }

    public ByteString getWalletIdBytes(int i) {
        return ByteString.copyFromUtf8((String) this.walletId_.get(i));
    }

    private void ensureWalletIdIsMutable() {
        if (!this.walletId_.isModifiable()) {
            this.walletId_ = GeneratedMessageLite.mutableCopy(this.walletId_);
        }
    }

    /* access modifiers changed from: private */
    public void setWalletId(int i, String str) {
        if (str != null) {
            ensureWalletIdIsMutable();
            this.walletId_.set(i, str);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addWalletId(String str) {
        if (str != null) {
            ensureWalletIdIsMutable();
            this.walletId_.add(str);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addAllWalletId(Iterable<String> iterable) {
        ensureWalletIdIsMutable();
        AbstractMessageLite.addAll(iterable, this.walletId_);
    }

    /* access modifiers changed from: private */
    public void clearWalletId() {
        this.walletId_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addWalletIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            ensureWalletIdIsMutable();
            this.walletId_.add(byteString.toStringUtf8());
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.walletId_.size(); i++) {
            codedOutputStream.writeString(1, (String) this.walletId_.get(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.walletId_.size(); i3++) {
            i2 += CodedOutputStream.computeStringSizeNoTag((String) this.walletId_.get(i3));
        }
        int size = 0 + i2 + (getWalletIdList().size() * 1);
        this.memoizedSerializedSize = size;
        return size;
    }

    public static GetUtxosRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (GetUtxosRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static GetUtxosRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetUtxosRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static GetUtxosRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (GetUtxosRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static GetUtxosRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetUtxosRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static GetUtxosRequest parseFrom(InputStream inputStream) throws IOException {
        return (GetUtxosRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetUtxosRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetUtxosRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetUtxosRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (GetUtxosRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetUtxosRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetUtxosRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetUtxosRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (GetUtxosRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static GetUtxosRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetUtxosRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GetUtxosRequest getUtxosRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(getUtxosRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new GetUtxosRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.walletId_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.walletId_ = ((Visitor) obj).visitList(this.walletId_, ((GetUtxosRequest) obj2).walletId_);
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
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if (!this.walletId_.isModifiable()) {
                                    this.walletId_ = GeneratedMessageLite.mutableCopy(this.walletId_);
                                }
                                this.walletId_.add(readStringRequireUtf8);
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
                    synchronized (GetUtxosRequest.class) {
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

    public static GetUtxosRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GetUtxosRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
