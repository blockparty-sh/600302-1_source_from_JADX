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

public final class CheckHealthResponse extends GeneratedMessageLite<CheckHealthResponse, Builder> implements CheckHealthResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final CheckHealthResponse DEFAULT_INSTANCE = new CheckHealthResponse();
    private static volatile Parser<CheckHealthResponse> PARSER = null;
    public static final int REGION_FIELD_NUMBER = 1;
    private String region_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<CheckHealthResponse, Builder> implements CheckHealthResponseOrBuilder {
        private Builder() {
            super(CheckHealthResponse.DEFAULT_INSTANCE);
        }

        public String getRegion() {
            return ((CheckHealthResponse) this.instance).getRegion();
        }

        public ByteString getRegionBytes() {
            return ((CheckHealthResponse) this.instance).getRegionBytes();
        }

        public Builder setRegion(String str) {
            copyOnWrite();
            ((CheckHealthResponse) this.instance).setRegion(str);
            return this;
        }

        public Builder clearRegion() {
            copyOnWrite();
            ((CheckHealthResponse) this.instance).clearRegion();
            return this;
        }

        public Builder setRegionBytes(ByteString byteString) {
            copyOnWrite();
            ((CheckHealthResponse) this.instance).setRegionBytes(byteString);
            return this;
        }
    }

    private CheckHealthResponse() {
    }

    public String getRegion() {
        return this.region_;
    }

    public ByteString getRegionBytes() {
        return ByteString.copyFromUtf8(this.region_);
    }

    /* access modifiers changed from: private */
    public void setRegion(String str) {
        if (str != null) {
            this.region_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearRegion() {
        this.region_ = getDefaultInstance().getRegion();
    }

    /* access modifiers changed from: private */
    public void setRegionBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.region_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.region_.isEmpty()) {
            codedOutputStream.writeString(1, getRegion());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.region_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getRegion());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static CheckHealthResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CheckHealthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static CheckHealthResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CheckHealthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static CheckHealthResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CheckHealthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static CheckHealthResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CheckHealthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static CheckHealthResponse parseFrom(InputStream inputStream) throws IOException {
        return (CheckHealthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CheckHealthResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CheckHealthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CheckHealthResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CheckHealthResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CheckHealthResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CheckHealthResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CheckHealthResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CheckHealthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static CheckHealthResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CheckHealthResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CheckHealthResponse checkHealthResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(checkHealthResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new CheckHealthResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                CheckHealthResponse checkHealthResponse = (CheckHealthResponse) obj2;
                this.region_ = ((Visitor) obj).visitString(!this.region_.isEmpty(), this.region_, true ^ checkHealthResponse.region_.isEmpty(), checkHealthResponse.region_);
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
                                this.region_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (CheckHealthResponse.class) {
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

    public static CheckHealthResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CheckHealthResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
