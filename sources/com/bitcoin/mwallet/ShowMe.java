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

public final class ShowMe extends GeneratedMessageLite<ShowMe, Builder> implements ShowMeOrBuilder {
    /* access modifiers changed from: private */
    public static final ShowMe DEFAULT_INSTANCE = new ShowMe();
    private static volatile Parser<ShowMe> PARSER;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<ShowMe, Builder> implements ShowMeOrBuilder {
        private Builder() {
            super(ShowMe.DEFAULT_INSTANCE);
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
    }

    private ShowMe() {
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        this.memoizedSerializedSize = 0;
        return 0;
    }

    public static ShowMe parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ShowMe) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ShowMe parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ShowMe) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ShowMe parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ShowMe) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ShowMe parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ShowMe) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ShowMe parseFrom(InputStream inputStream) throws IOException {
        return (ShowMe) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ShowMe parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ShowMe) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ShowMe parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ShowMe) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ShowMe parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ShowMe) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ShowMe parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ShowMe) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ShowMe parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ShowMe) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ShowMe showMe) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(showMe);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new ShowMe();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                ShowMe showMe = (ShowMe) obj2;
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
                    synchronized (ShowMe.class) {
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

    public static ShowMe getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ShowMe> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
