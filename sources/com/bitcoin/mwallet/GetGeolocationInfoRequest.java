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

public final class GetGeolocationInfoRequest extends GeneratedMessageLite<GetGeolocationInfoRequest, Builder> implements GetGeolocationInfoRequestOrBuilder {
    /* access modifiers changed from: private */
    public static final GetGeolocationInfoRequest DEFAULT_INSTANCE = new GetGeolocationInfoRequest();
    public static final int IP_FIELD_NUMBER = 1;
    private static volatile Parser<GetGeolocationInfoRequest> PARSER;
    private String ip_ = "";

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<GetGeolocationInfoRequest, Builder> implements GetGeolocationInfoRequestOrBuilder {
        private Builder() {
            super(GetGeolocationInfoRequest.DEFAULT_INSTANCE);
        }

        public String getIp() {
            return ((GetGeolocationInfoRequest) this.instance).getIp();
        }

        public ByteString getIpBytes() {
            return ((GetGeolocationInfoRequest) this.instance).getIpBytes();
        }

        public Builder setIp(String str) {
            copyOnWrite();
            ((GetGeolocationInfoRequest) this.instance).setIp(str);
            return this;
        }

        public Builder clearIp() {
            copyOnWrite();
            ((GetGeolocationInfoRequest) this.instance).clearIp();
            return this;
        }

        public Builder setIpBytes(ByteString byteString) {
            copyOnWrite();
            ((GetGeolocationInfoRequest) this.instance).setIpBytes(byteString);
            return this;
        }
    }

    private GetGeolocationInfoRequest() {
    }

    public String getIp() {
        return this.ip_;
    }

    public ByteString getIpBytes() {
        return ByteString.copyFromUtf8(this.ip_);
    }

    /* access modifiers changed from: private */
    public void setIp(String str) {
        if (str != null) {
            this.ip_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearIp() {
        this.ip_ = getDefaultInstance().getIp();
    }

    /* access modifiers changed from: private */
    public void setIpBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.ip_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.ip_.isEmpty()) {
            codedOutputStream.writeString(1, getIp());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.ip_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getIp());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static GetGeolocationInfoRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (GetGeolocationInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static GetGeolocationInfoRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetGeolocationInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static GetGeolocationInfoRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (GetGeolocationInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static GetGeolocationInfoRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetGeolocationInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static GetGeolocationInfoRequest parseFrom(InputStream inputStream) throws IOException {
        return (GetGeolocationInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetGeolocationInfoRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetGeolocationInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetGeolocationInfoRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (GetGeolocationInfoRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetGeolocationInfoRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetGeolocationInfoRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetGeolocationInfoRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (GetGeolocationInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static GetGeolocationInfoRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetGeolocationInfoRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GetGeolocationInfoRequest getGeolocationInfoRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(getGeolocationInfoRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new GetGeolocationInfoRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                GetGeolocationInfoRequest getGeolocationInfoRequest = (GetGeolocationInfoRequest) obj2;
                this.ip_ = ((Visitor) obj).visitString(!this.ip_.isEmpty(), this.ip_, true ^ getGeolocationInfoRequest.ip_.isEmpty(), getGeolocationInfoRequest.ip_);
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
                                this.ip_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (GetGeolocationInfoRequest.class) {
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

    public static GetGeolocationInfoRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GetGeolocationInfoRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
