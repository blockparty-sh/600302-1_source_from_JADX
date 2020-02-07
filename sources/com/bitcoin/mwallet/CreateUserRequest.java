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

public final class CreateUserRequest extends GeneratedMessageLite<CreateUserRequest, Builder> implements CreateUserRequestOrBuilder {
    /* access modifiers changed from: private */
    public static final CreateUserRequest DEFAULT_INSTANCE = new CreateUserRequest();
    private static volatile Parser<CreateUserRequest> PARSER = null;
    public static final int REQUEST_X_PUB_KEY_FIELD_NUMBER = 1;
    public static final int SIGNATURE_FIELD_NUMBER = 2;
    private String requestXPubKey_ = "";
    private RequestSignature signature_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<CreateUserRequest, Builder> implements CreateUserRequestOrBuilder {
        private Builder() {
            super(CreateUserRequest.DEFAULT_INSTANCE);
        }

        public String getRequestXPubKey() {
            return ((CreateUserRequest) this.instance).getRequestXPubKey();
        }

        public ByteString getRequestXPubKeyBytes() {
            return ((CreateUserRequest) this.instance).getRequestXPubKeyBytes();
        }

        public Builder setRequestXPubKey(String str) {
            copyOnWrite();
            ((CreateUserRequest) this.instance).setRequestXPubKey(str);
            return this;
        }

        public Builder clearRequestXPubKey() {
            copyOnWrite();
            ((CreateUserRequest) this.instance).clearRequestXPubKey();
            return this;
        }

        public Builder setRequestXPubKeyBytes(ByteString byteString) {
            copyOnWrite();
            ((CreateUserRequest) this.instance).setRequestXPubKeyBytes(byteString);
            return this;
        }

        public boolean hasSignature() {
            return ((CreateUserRequest) this.instance).hasSignature();
        }

        public RequestSignature getSignature() {
            return ((CreateUserRequest) this.instance).getSignature();
        }

        public Builder setSignature(RequestSignature requestSignature) {
            copyOnWrite();
            ((CreateUserRequest) this.instance).setSignature(requestSignature);
            return this;
        }

        public Builder setSignature(com.bitcoin.mwallet.RequestSignature.Builder builder) {
            copyOnWrite();
            ((CreateUserRequest) this.instance).setSignature(builder);
            return this;
        }

        public Builder mergeSignature(RequestSignature requestSignature) {
            copyOnWrite();
            ((CreateUserRequest) this.instance).mergeSignature(requestSignature);
            return this;
        }

        public Builder clearSignature() {
            copyOnWrite();
            ((CreateUserRequest) this.instance).clearSignature();
            return this;
        }
    }

    private CreateUserRequest() {
    }

    public String getRequestXPubKey() {
        return this.requestXPubKey_;
    }

    public ByteString getRequestXPubKeyBytes() {
        return ByteString.copyFromUtf8(this.requestXPubKey_);
    }

    /* access modifiers changed from: private */
    public void setRequestXPubKey(String str) {
        if (str != null) {
            this.requestXPubKey_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearRequestXPubKey() {
        this.requestXPubKey_ = getDefaultInstance().getRequestXPubKey();
    }

    /* access modifiers changed from: private */
    public void setRequestXPubKeyBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.requestXPubKey_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public boolean hasSignature() {
        return this.signature_ != null;
    }

    public RequestSignature getSignature() {
        RequestSignature requestSignature = this.signature_;
        return requestSignature == null ? RequestSignature.getDefaultInstance() : requestSignature;
    }

    /* access modifiers changed from: private */
    public void setSignature(RequestSignature requestSignature) {
        if (requestSignature != null) {
            this.signature_ = requestSignature;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setSignature(com.bitcoin.mwallet.RequestSignature.Builder builder) {
        this.signature_ = (RequestSignature) builder.build();
    }

    /* access modifiers changed from: private */
    public void mergeSignature(RequestSignature requestSignature) {
        RequestSignature requestSignature2 = this.signature_;
        if (requestSignature2 == null || requestSignature2 == RequestSignature.getDefaultInstance()) {
            this.signature_ = requestSignature;
        } else {
            this.signature_ = (RequestSignature) ((com.bitcoin.mwallet.RequestSignature.Builder) RequestSignature.newBuilder(this.signature_).mergeFrom(requestSignature)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearSignature() {
        this.signature_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.requestXPubKey_.isEmpty()) {
            codedOutputStream.writeString(1, getRequestXPubKey());
        }
        if (this.signature_ != null) {
            codedOutputStream.writeMessage(2, getSignature());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.requestXPubKey_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getRequestXPubKey());
        }
        if (this.signature_ != null) {
            i2 += CodedOutputStream.computeMessageSize(2, getSignature());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static CreateUserRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CreateUserRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static CreateUserRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateUserRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static CreateUserRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CreateUserRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static CreateUserRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CreateUserRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static CreateUserRequest parseFrom(InputStream inputStream) throws IOException {
        return (CreateUserRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateUserRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateUserRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateUserRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CreateUserRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CreateUserRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateUserRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CreateUserRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CreateUserRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static CreateUserRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CreateUserRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CreateUserRequest createUserRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(createUserRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new CreateUserRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                CreateUserRequest createUserRequest = (CreateUserRequest) obj2;
                this.requestXPubKey_ = visitor.visitString(!this.requestXPubKey_.isEmpty(), this.requestXPubKey_, true ^ createUserRequest.requestXPubKey_.isEmpty(), createUserRequest.requestXPubKey_);
                this.signature_ = (RequestSignature) visitor.visitMessage(this.signature_, createUserRequest.signature_);
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
                                this.requestXPubKey_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                com.bitcoin.mwallet.RequestSignature.Builder builder = this.signature_ != null ? (com.bitcoin.mwallet.RequestSignature.Builder) this.signature_.toBuilder() : null;
                                this.signature_ = (RequestSignature) codedInputStream.readMessage(RequestSignature.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.signature_);
                                    this.signature_ = (RequestSignature) builder.buildPartial();
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
                    synchronized (CreateUserRequest.class) {
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

    public static CreateUserRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CreateUserRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
