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

public final class ResponseError extends GeneratedMessageLite<ResponseError, Builder> implements ResponseErrorOrBuilder {
    /* access modifiers changed from: private */
    public static final ResponseError DEFAULT_INSTANCE = new ResponseError();
    public static final int ERROR_MESSAGE_FIELD_NUMBER = 2;
    private static volatile Parser<ResponseError> PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 1;
    private String errorMessage_ = "";
    private int type_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<ResponseError, Builder> implements ResponseErrorOrBuilder {
        private Builder() {
            super(ResponseError.DEFAULT_INSTANCE);
        }

        public int getTypeValue() {
            return ((ResponseError) this.instance).getTypeValue();
        }

        public Builder setTypeValue(int i) {
            copyOnWrite();
            ((ResponseError) this.instance).setTypeValue(i);
            return this;
        }

        public ResponseErrorType getType() {
            return ((ResponseError) this.instance).getType();
        }

        public Builder setType(ResponseErrorType responseErrorType) {
            copyOnWrite();
            ((ResponseError) this.instance).setType(responseErrorType);
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((ResponseError) this.instance).clearType();
            return this;
        }

        public String getErrorMessage() {
            return ((ResponseError) this.instance).getErrorMessage();
        }

        public ByteString getErrorMessageBytes() {
            return ((ResponseError) this.instance).getErrorMessageBytes();
        }

        public Builder setErrorMessage(String str) {
            copyOnWrite();
            ((ResponseError) this.instance).setErrorMessage(str);
            return this;
        }

        public Builder clearErrorMessage() {
            copyOnWrite();
            ((ResponseError) this.instance).clearErrorMessage();
            return this;
        }

        public Builder setErrorMessageBytes(ByteString byteString) {
            copyOnWrite();
            ((ResponseError) this.instance).setErrorMessageBytes(byteString);
            return this;
        }
    }

    private ResponseError() {
    }

    public int getTypeValue() {
        return this.type_;
    }

    public ResponseErrorType getType() {
        ResponseErrorType forNumber = ResponseErrorType.forNumber(this.type_);
        return forNumber == null ? ResponseErrorType.UNRECOGNIZED : forNumber;
    }

    /* access modifiers changed from: private */
    public void setTypeValue(int i) {
        this.type_ = i;
    }

    /* access modifiers changed from: private */
    public void setType(ResponseErrorType responseErrorType) {
        if (responseErrorType != null) {
            this.type_ = responseErrorType.getNumber();
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearType() {
        this.type_ = 0;
    }

    public String getErrorMessage() {
        return this.errorMessage_;
    }

    public ByteString getErrorMessageBytes() {
        return ByteString.copyFromUtf8(this.errorMessage_);
    }

    /* access modifiers changed from: private */
    public void setErrorMessage(String str) {
        if (str != null) {
            this.errorMessage_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearErrorMessage() {
        this.errorMessage_ = getDefaultInstance().getErrorMessage();
    }

    /* access modifiers changed from: private */
    public void setErrorMessageBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.errorMessage_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.type_ != ResponseErrorType.UNKNOWN.getNumber()) {
            codedOutputStream.writeEnum(1, this.type_);
        }
        if (!this.errorMessage_.isEmpty()) {
            codedOutputStream.writeString(2, getErrorMessage());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.type_ != ResponseErrorType.UNKNOWN.getNumber()) {
            i2 = 0 + CodedOutputStream.computeEnumSize(1, this.type_);
        }
        if (!this.errorMessage_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getErrorMessage());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static ResponseError parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ResponseError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ResponseError parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResponseError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ResponseError parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ResponseError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ResponseError parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResponseError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ResponseError parseFrom(InputStream inputStream) throws IOException {
        return (ResponseError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ResponseError parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResponseError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ResponseError parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ResponseError) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ResponseError parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResponseError) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ResponseError parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ResponseError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ResponseError parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResponseError) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ResponseError responseError) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(responseError);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new ResponseError();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                ResponseError responseError = (ResponseError) obj2;
                boolean z2 = this.type_ != 0;
                int i = this.type_;
                if (responseError.type_ != 0) {
                    z = true;
                }
                this.type_ = visitor.visitInt(z2, i, z, responseError.type_);
                this.errorMessage_ = visitor.visitString(!this.errorMessage_.isEmpty(), this.errorMessage_, !responseError.errorMessage_.isEmpty(), responseError.errorMessage_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 8) {
                                this.type_ = codedInputStream.readEnum();
                            } else if (readTag == 18) {
                                this.errorMessage_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (ResponseError.class) {
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

    public static ResponseError getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ResponseError> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
