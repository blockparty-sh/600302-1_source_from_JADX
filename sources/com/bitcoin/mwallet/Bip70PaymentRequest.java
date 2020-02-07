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

public final class Bip70PaymentRequest extends GeneratedMessageLite<Bip70PaymentRequest, Builder> implements Bip70PaymentRequestOrBuilder {
    public static final int BIP_70_URL_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final Bip70PaymentRequest DEFAULT_INSTANCE = new Bip70PaymentRequest();
    private static volatile Parser<Bip70PaymentRequest> PARSER;
    private String bip70Url_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<Bip70PaymentRequest, Builder> implements Bip70PaymentRequestOrBuilder {
        private Builder() {
            super(Bip70PaymentRequest.DEFAULT_INSTANCE);
        }

        public String getBip70Url() {
            return ((Bip70PaymentRequest) this.instance).getBip70Url();
        }

        public ByteString getBip70UrlBytes() {
            return ((Bip70PaymentRequest) this.instance).getBip70UrlBytes();
        }

        public Builder setBip70Url(String str) {
            copyOnWrite();
            ((Bip70PaymentRequest) this.instance).setBip70Url(str);
            return this;
        }

        public Builder clearBip70Url() {
            copyOnWrite();
            ((Bip70PaymentRequest) this.instance).clearBip70Url();
            return this;
        }

        public Builder setBip70UrlBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70PaymentRequest) this.instance).setBip70UrlBytes(byteString);
            return this;
        }
    }

    private Bip70PaymentRequest() {
    }

    public String getBip70Url() {
        return this.bip70Url_;
    }

    public ByteString getBip70UrlBytes() {
        return ByteString.copyFromUtf8(this.bip70Url_);
    }

    /* access modifiers changed from: private */
    public void setBip70Url(String str) {
        if (str != null) {
            this.bip70Url_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearBip70Url() {
        this.bip70Url_ = getDefaultInstance().getBip70Url();
    }

    /* access modifiers changed from: private */
    public void setBip70UrlBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.bip70Url_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.bip70Url_.isEmpty()) {
            codedOutputStream.writeString(1, getBip70Url());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.bip70Url_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getBip70Url());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static Bip70PaymentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Bip70PaymentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Bip70PaymentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Bip70PaymentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Bip70PaymentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Bip70PaymentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Bip70PaymentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Bip70PaymentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Bip70PaymentRequest parseFrom(InputStream inputStream) throws IOException {
        return (Bip70PaymentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Bip70PaymentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Bip70PaymentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Bip70PaymentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Bip70PaymentRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Bip70PaymentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Bip70PaymentRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Bip70PaymentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Bip70PaymentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Bip70PaymentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Bip70PaymentRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Bip70PaymentRequest bip70PaymentRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(bip70PaymentRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new Bip70PaymentRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Bip70PaymentRequest bip70PaymentRequest = (Bip70PaymentRequest) obj2;
                this.bip70Url_ = ((Visitor) obj).visitString(!this.bip70Url_.isEmpty(), this.bip70Url_, true ^ bip70PaymentRequest.bip70Url_.isEmpty(), bip70PaymentRequest.bip70Url_);
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
                                this.bip70Url_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (Bip70PaymentRequest.class) {
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

    public static Bip70PaymentRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Bip70PaymentRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
