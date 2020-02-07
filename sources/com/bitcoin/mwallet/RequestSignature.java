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

public final class RequestSignature extends GeneratedMessageLite<RequestSignature, Builder> implements RequestSignatureOrBuilder {
    /* access modifiers changed from: private */
    public static final RequestSignature DEFAULT_INSTANCE = new RequestSignature();
    private static volatile Parser<RequestSignature> PARSER = null;
    public static final int REQUEST_X_PUB_KEY_FIELD_NUMBER = 1;
    public static final int SIGNATURE_FIELD_NUMBER = 2;
    private ByteString requestXPubKey_ = ByteString.EMPTY;
    private ByteString signature_ = ByteString.EMPTY;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<RequestSignature, Builder> implements RequestSignatureOrBuilder {
        private Builder() {
            super(RequestSignature.DEFAULT_INSTANCE);
        }

        public ByteString getRequestXPubKey() {
            return ((RequestSignature) this.instance).getRequestXPubKey();
        }

        public Builder setRequestXPubKey(ByteString byteString) {
            copyOnWrite();
            ((RequestSignature) this.instance).setRequestXPubKey(byteString);
            return this;
        }

        public Builder clearRequestXPubKey() {
            copyOnWrite();
            ((RequestSignature) this.instance).clearRequestXPubKey();
            return this;
        }

        public ByteString getSignature() {
            return ((RequestSignature) this.instance).getSignature();
        }

        public Builder setSignature(ByteString byteString) {
            copyOnWrite();
            ((RequestSignature) this.instance).setSignature(byteString);
            return this;
        }

        public Builder clearSignature() {
            copyOnWrite();
            ((RequestSignature) this.instance).clearSignature();
            return this;
        }
    }

    private RequestSignature() {
    }

    public ByteString getRequestXPubKey() {
        return this.requestXPubKey_;
    }

    /* access modifiers changed from: private */
    public void setRequestXPubKey(ByteString byteString) {
        if (byteString != null) {
            this.requestXPubKey_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearRequestXPubKey() {
        this.requestXPubKey_ = getDefaultInstance().getRequestXPubKey();
    }

    public ByteString getSignature() {
        return this.signature_;
    }

    /* access modifiers changed from: private */
    public void setSignature(ByteString byteString) {
        if (byteString != null) {
            this.signature_ = byteString;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearSignature() {
        this.signature_ = getDefaultInstance().getSignature();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.requestXPubKey_.isEmpty()) {
            codedOutputStream.writeBytes(1, this.requestXPubKey_);
        }
        if (!this.signature_.isEmpty()) {
            codedOutputStream.writeBytes(2, this.signature_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.requestXPubKey_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeBytesSize(1, this.requestXPubKey_);
        }
        if (!this.signature_.isEmpty()) {
            i2 += CodedOutputStream.computeBytesSize(2, this.signature_);
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static RequestSignature parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RequestSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RequestSignature parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RequestSignature parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RequestSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RequestSignature parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RequestSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RequestSignature parseFrom(InputStream inputStream) throws IOException {
        return (RequestSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RequestSignature parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RequestSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RequestSignature parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RequestSignature) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RequestSignature parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RequestSignature) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RequestSignature parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RequestSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RequestSignature parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RequestSignature) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RequestSignature requestSignature) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(requestSignature);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new RequestSignature();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                RequestSignature requestSignature = (RequestSignature) obj2;
                this.requestXPubKey_ = visitor.visitByteString(this.requestXPubKey_ != ByteString.EMPTY, this.requestXPubKey_, requestSignature.requestXPubKey_ != ByteString.EMPTY, requestSignature.requestXPubKey_);
                boolean z2 = this.signature_ != ByteString.EMPTY;
                ByteString byteString = this.signature_;
                if (requestSignature.signature_ != ByteString.EMPTY) {
                    z = true;
                }
                this.signature_ = visitor.visitByteString(z2, byteString, z, requestSignature.signature_);
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
                                this.requestXPubKey_ = codedInputStream.readBytes();
                            } else if (readTag == 18) {
                                this.signature_ = codedInputStream.readBytes();
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
                    synchronized (RequestSignature.class) {
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

    public static RequestSignature getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RequestSignature> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
