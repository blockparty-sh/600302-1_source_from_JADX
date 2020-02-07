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

public final class RegisterRegionResponse extends GeneratedMessageLite<RegisterRegionResponse, Builder> implements RegisterRegionResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final RegisterRegionResponse DEFAULT_INSTANCE = new RegisterRegionResponse();
    public static final int ERROR_FIELD_NUMBER = 1;
    private static volatile Parser<RegisterRegionResponse> PARSER;
    private ResponseError error_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<RegisterRegionResponse, Builder> implements RegisterRegionResponseOrBuilder {
        private Builder() {
            super(RegisterRegionResponse.DEFAULT_INSTANCE);
        }

        public boolean hasError() {
            return ((RegisterRegionResponse) this.instance).hasError();
        }

        public ResponseError getError() {
            return ((RegisterRegionResponse) this.instance).getError();
        }

        public Builder setError(ResponseError responseError) {
            copyOnWrite();
            ((RegisterRegionResponse) this.instance).setError(responseError);
            return this;
        }

        public Builder setError(com.bitcoin.mwallet.ResponseError.Builder builder) {
            copyOnWrite();
            ((RegisterRegionResponse) this.instance).setError(builder);
            return this;
        }

        public Builder mergeError(ResponseError responseError) {
            copyOnWrite();
            ((RegisterRegionResponse) this.instance).mergeError(responseError);
            return this;
        }

        public Builder clearError() {
            copyOnWrite();
            ((RegisterRegionResponse) this.instance).clearError();
            return this;
        }
    }

    private RegisterRegionResponse() {
    }

    public boolean hasError() {
        return this.error_ != null;
    }

    public ResponseError getError() {
        ResponseError responseError = this.error_;
        return responseError == null ? ResponseError.getDefaultInstance() : responseError;
    }

    /* access modifiers changed from: private */
    public void setError(ResponseError responseError) {
        if (responseError != null) {
            this.error_ = responseError;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setError(com.bitcoin.mwallet.ResponseError.Builder builder) {
        this.error_ = (ResponseError) builder.build();
    }

    /* access modifiers changed from: private */
    public void mergeError(ResponseError responseError) {
        ResponseError responseError2 = this.error_;
        if (responseError2 == null || responseError2 == ResponseError.getDefaultInstance()) {
            this.error_ = responseError;
        } else {
            this.error_ = (ResponseError) ((com.bitcoin.mwallet.ResponseError.Builder) ResponseError.newBuilder(this.error_).mergeFrom(responseError)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearError() {
        this.error_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.error_ != null) {
            codedOutputStream.writeMessage(1, getError());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.error_ != null) {
            i2 = 0 + CodedOutputStream.computeMessageSize(1, getError());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static RegisterRegionResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RegisterRegionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RegisterRegionResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RegisterRegionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RegisterRegionResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RegisterRegionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RegisterRegionResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RegisterRegionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RegisterRegionResponse parseFrom(InputStream inputStream) throws IOException {
        return (RegisterRegionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RegisterRegionResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RegisterRegionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RegisterRegionResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RegisterRegionResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RegisterRegionResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RegisterRegionResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RegisterRegionResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RegisterRegionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RegisterRegionResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RegisterRegionResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RegisterRegionResponse registerRegionResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(registerRegionResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new RegisterRegionResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                this.error_ = (ResponseError) ((Visitor) obj).visitMessage(this.error_, ((RegisterRegionResponse) obj2).error_);
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
                                com.bitcoin.mwallet.ResponseError.Builder builder = this.error_ != null ? (com.bitcoin.mwallet.ResponseError.Builder) this.error_.toBuilder() : null;
                                this.error_ = (ResponseError) codedInputStream.readMessage(ResponseError.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom(this.error_);
                                    this.error_ = (ResponseError) builder.buildPartial();
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
                    synchronized (RegisterRegionResponse.class) {
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

    public static RegisterRegionResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RegisterRegionResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
