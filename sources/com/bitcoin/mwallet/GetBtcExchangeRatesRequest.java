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

public final class GetBtcExchangeRatesRequest extends GeneratedMessageLite<GetBtcExchangeRatesRequest, Builder> implements GetBtcExchangeRatesRequestOrBuilder {
    /* access modifiers changed from: private */
    public static final GetBtcExchangeRatesRequest DEFAULT_INSTANCE = new GetBtcExchangeRatesRequest();
    private static volatile Parser<GetBtcExchangeRatesRequest> PARSER;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<GetBtcExchangeRatesRequest, Builder> implements GetBtcExchangeRatesRequestOrBuilder {
        private Builder() {
            super(GetBtcExchangeRatesRequest.DEFAULT_INSTANCE);
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
    }

    private GetBtcExchangeRatesRequest() {
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        this.memoizedSerializedSize = 0;
        return 0;
    }

    public static GetBtcExchangeRatesRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (GetBtcExchangeRatesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static GetBtcExchangeRatesRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetBtcExchangeRatesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static GetBtcExchangeRatesRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (GetBtcExchangeRatesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static GetBtcExchangeRatesRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetBtcExchangeRatesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static GetBtcExchangeRatesRequest parseFrom(InputStream inputStream) throws IOException {
        return (GetBtcExchangeRatesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetBtcExchangeRatesRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetBtcExchangeRatesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetBtcExchangeRatesRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (GetBtcExchangeRatesRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetBtcExchangeRatesRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetBtcExchangeRatesRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetBtcExchangeRatesRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (GetBtcExchangeRatesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static GetBtcExchangeRatesRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetBtcExchangeRatesRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GetBtcExchangeRatesRequest getBtcExchangeRatesRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(getBtcExchangeRatesRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new GetBtcExchangeRatesRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                GetBtcExchangeRatesRequest getBtcExchangeRatesRequest = (GetBtcExchangeRatesRequest) obj2;
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z = false;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag == 0 || !codedInputStream.skipField(readTag)) {
                            z = true;
                        }
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
                    synchronized (GetBtcExchangeRatesRequest.class) {
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

    public static GetBtcExchangeRatesRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GetBtcExchangeRatesRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
