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

public final class YesShow extends GeneratedMessageLite<YesShow, Builder> implements YesShowOrBuilder {
    /* access modifiers changed from: private */
    public static final YesShow DEFAULT_INSTANCE = new YesShow();
    private static volatile Parser<YesShow> PARSER = null;
    public static final int SIGNATURE_FIELD_NUMBER = 1;
    private String signature_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<YesShow, Builder> implements YesShowOrBuilder {
        private Builder() {
            super(YesShow.DEFAULT_INSTANCE);
        }

        public String getSignature() {
            return ((YesShow) this.instance).getSignature();
        }

        public ByteString getSignatureBytes() {
            return ((YesShow) this.instance).getSignatureBytes();
        }

        public Builder setSignature(String str) {
            copyOnWrite();
            ((YesShow) this.instance).setSignature(str);
            return this;
        }

        public Builder clearSignature() {
            copyOnWrite();
            ((YesShow) this.instance).clearSignature();
            return this;
        }

        public Builder setSignatureBytes(ByteString byteString) {
            copyOnWrite();
            ((YesShow) this.instance).setSignatureBytes(byteString);
            return this;
        }
    }

    private YesShow() {
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
        if (!this.signature_.isEmpty()) {
            codedOutputStream.writeString(1, getSignature());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.signature_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getSignature());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static YesShow parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (YesShow) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static YesShow parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (YesShow) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static YesShow parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (YesShow) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static YesShow parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (YesShow) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static YesShow parseFrom(InputStream inputStream) throws IOException {
        return (YesShow) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static YesShow parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (YesShow) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static YesShow parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (YesShow) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static YesShow parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (YesShow) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static YesShow parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (YesShow) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static YesShow parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (YesShow) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(YesShow yesShow) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(yesShow);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new YesShow();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                YesShow yesShow = (YesShow) obj2;
                this.signature_ = ((Visitor) obj).visitString(!this.signature_.isEmpty(), this.signature_, true ^ yesShow.signature_.isEmpty(), yesShow.signature_);
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
                    synchronized (YesShow.class) {
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

    public static YesShow getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<YesShow> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
