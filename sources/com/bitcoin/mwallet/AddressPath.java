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

public final class AddressPath extends GeneratedMessageLite<AddressPath, Builder> implements AddressPathOrBuilder {
    /* access modifiers changed from: private */
    public static final AddressPath DEFAULT_INSTANCE = new AddressPath();
    private static volatile Parser<AddressPath> PARSER = null;
    public static final int X_FIELD_NUMBER = 1;
    public static final int Y_FIELD_NUMBER = 2;

    /* renamed from: x_ */
    private int f100x_;

    /* renamed from: y_ */
    private int f101y_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<AddressPath, Builder> implements AddressPathOrBuilder {
        private Builder() {
            super(AddressPath.DEFAULT_INSTANCE);
        }

        public int getX() {
            return ((AddressPath) this.instance).getX();
        }

        public Builder setX(int i) {
            copyOnWrite();
            ((AddressPath) this.instance).setX(i);
            return this;
        }

        public Builder clearX() {
            copyOnWrite();
            ((AddressPath) this.instance).clearX();
            return this;
        }

        public int getY() {
            return ((AddressPath) this.instance).getY();
        }

        public Builder setY(int i) {
            copyOnWrite();
            ((AddressPath) this.instance).setY(i);
            return this;
        }

        public Builder clearY() {
            copyOnWrite();
            ((AddressPath) this.instance).clearY();
            return this;
        }
    }

    private AddressPath() {
    }

    public int getX() {
        return this.f100x_;
    }

    /* access modifiers changed from: private */
    public void setX(int i) {
        this.f100x_ = i;
    }

    /* access modifiers changed from: private */
    public void clearX() {
        this.f100x_ = 0;
    }

    public int getY() {
        return this.f101y_;
    }

    /* access modifiers changed from: private */
    public void setY(int i) {
        this.f101y_ = i;
    }

    /* access modifiers changed from: private */
    public void clearY() {
        this.f101y_ = 0;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        int i = this.f100x_;
        if (i != 0) {
            codedOutputStream.writeUInt32(1, i);
        }
        int i2 = this.f101y_;
        if (i2 != 0) {
            codedOutputStream.writeUInt32(2, i2);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        int i3 = this.f100x_;
        if (i3 != 0) {
            i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
        }
        int i4 = this.f101y_;
        if (i4 != 0) {
            i2 += CodedOutputStream.computeUInt32Size(2, i4);
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static AddressPath parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AddressPath) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AddressPath parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AddressPath) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AddressPath parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AddressPath) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AddressPath parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AddressPath) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AddressPath parseFrom(InputStream inputStream) throws IOException {
        return (AddressPath) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AddressPath parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AddressPath) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AddressPath parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AddressPath) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AddressPath parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AddressPath) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AddressPath parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AddressPath) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AddressPath parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AddressPath) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AddressPath addressPath) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(addressPath);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new AddressPath();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                AddressPath addressPath = (AddressPath) obj2;
                this.f100x_ = visitor.visitInt(this.f100x_ != 0, this.f100x_, addressPath.f100x_ != 0, addressPath.f100x_);
                boolean z2 = this.f101y_ != 0;
                int i = this.f101y_;
                if (addressPath.f101y_ != 0) {
                    z = true;
                }
                this.f101y_ = visitor.visitInt(z2, i, z, addressPath.f101y_);
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
                                this.f100x_ = codedInputStream.readUInt32();
                            } else if (readTag == 16) {
                                this.f101y_ = codedInputStream.readUInt32();
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
                    synchronized (AddressPath.class) {
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

    public static AddressPath getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AddressPath> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
