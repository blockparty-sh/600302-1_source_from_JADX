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

public final class LocationInfo extends GeneratedMessageLite<LocationInfo, Builder> implements LocationInfoOrBuilder {
    public static final int BUY_BITCOIN_ALLOWED_FIELD_NUMBER = 2;
    public static final int COUNTRY_CODE_FIELD_NUMBER = 1;
    /* access modifiers changed from: private */
    public static final LocationInfo DEFAULT_INSTANCE = new LocationInfo();
    public static final int IP_FIELD_NUMBER = 3;
    private static volatile Parser<LocationInfo> PARSER;
    private boolean buyBitcoinAllowed_;
    private String countryCode_;
    private String ip_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<LocationInfo, Builder> implements LocationInfoOrBuilder {
        private Builder() {
            super(LocationInfo.DEFAULT_INSTANCE);
        }

        public String getCountryCode() {
            return ((LocationInfo) this.instance).getCountryCode();
        }

        public ByteString getCountryCodeBytes() {
            return ((LocationInfo) this.instance).getCountryCodeBytes();
        }

        public Builder setCountryCode(String str) {
            copyOnWrite();
            ((LocationInfo) this.instance).setCountryCode(str);
            return this;
        }

        public Builder clearCountryCode() {
            copyOnWrite();
            ((LocationInfo) this.instance).clearCountryCode();
            return this;
        }

        public Builder setCountryCodeBytes(ByteString byteString) {
            copyOnWrite();
            ((LocationInfo) this.instance).setCountryCodeBytes(byteString);
            return this;
        }

        public boolean getBuyBitcoinAllowed() {
            return ((LocationInfo) this.instance).getBuyBitcoinAllowed();
        }

        public Builder setBuyBitcoinAllowed(boolean z) {
            copyOnWrite();
            ((LocationInfo) this.instance).setBuyBitcoinAllowed(z);
            return this;
        }

        public Builder clearBuyBitcoinAllowed() {
            copyOnWrite();
            ((LocationInfo) this.instance).clearBuyBitcoinAllowed();
            return this;
        }

        public String getIp() {
            return ((LocationInfo) this.instance).getIp();
        }

        public ByteString getIpBytes() {
            return ((LocationInfo) this.instance).getIpBytes();
        }

        public Builder setIp(String str) {
            copyOnWrite();
            ((LocationInfo) this.instance).setIp(str);
            return this;
        }

        public Builder clearIp() {
            copyOnWrite();
            ((LocationInfo) this.instance).clearIp();
            return this;
        }

        public Builder setIpBytes(ByteString byteString) {
            copyOnWrite();
            ((LocationInfo) this.instance).setIpBytes(byteString);
            return this;
        }
    }

    private LocationInfo() {
        String str = "";
        this.countryCode_ = str;
        this.ip_ = str;
    }

    public String getCountryCode() {
        return this.countryCode_;
    }

    public ByteString getCountryCodeBytes() {
        return ByteString.copyFromUtf8(this.countryCode_);
    }

    /* access modifiers changed from: private */
    public void setCountryCode(String str) {
        if (str != null) {
            this.countryCode_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearCountryCode() {
        this.countryCode_ = getDefaultInstance().getCountryCode();
    }

    /* access modifiers changed from: private */
    public void setCountryCodeBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.countryCode_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public boolean getBuyBitcoinAllowed() {
        return this.buyBitcoinAllowed_;
    }

    /* access modifiers changed from: private */
    public void setBuyBitcoinAllowed(boolean z) {
        this.buyBitcoinAllowed_ = z;
    }

    /* access modifiers changed from: private */
    public void clearBuyBitcoinAllowed() {
        this.buyBitcoinAllowed_ = false;
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
        if (!this.countryCode_.isEmpty()) {
            codedOutputStream.writeString(1, getCountryCode());
        }
        boolean z = this.buyBitcoinAllowed_;
        if (z) {
            codedOutputStream.writeBool(2, z);
        }
        if (!this.ip_.isEmpty()) {
            codedOutputStream.writeString(3, getIp());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.countryCode_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getCountryCode());
        }
        boolean z = this.buyBitcoinAllowed_;
        if (z) {
            i2 += CodedOutputStream.computeBoolSize(2, z);
        }
        if (!this.ip_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(3, getIp());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static LocationInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (LocationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static LocationInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LocationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static LocationInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (LocationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static LocationInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (LocationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static LocationInfo parseFrom(InputStream inputStream) throws IOException {
        return (LocationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static LocationInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LocationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static LocationInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (LocationInfo) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static LocationInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LocationInfo) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static LocationInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (LocationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static LocationInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (LocationInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(LocationInfo locationInfo) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(locationInfo);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new LocationInfo();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                LocationInfo locationInfo = (LocationInfo) obj2;
                this.countryCode_ = visitor.visitString(!this.countryCode_.isEmpty(), this.countryCode_, !locationInfo.countryCode_.isEmpty(), locationInfo.countryCode_);
                boolean z = this.buyBitcoinAllowed_;
                boolean z2 = locationInfo.buyBitcoinAllowed_;
                this.buyBitcoinAllowed_ = visitor.visitBoolean(z, z, z2, z2);
                this.ip_ = visitor.visitString(!this.ip_.isEmpty(), this.ip_, true ^ locationInfo.ip_.isEmpty(), locationInfo.ip_);
                MergeFromVisitor mergeFromVisitor = MergeFromVisitor.INSTANCE;
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                boolean z3 = false;
                while (!z3) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                this.countryCode_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 16) {
                                this.buyBitcoinAllowed_ = codedInputStream.readBool();
                            } else if (readTag == 26) {
                                this.ip_ = codedInputStream.readStringRequireUtf8();
                            } else if (!codedInputStream.skipField(readTag)) {
                            }
                        }
                        z3 = true;
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
                    synchronized (LocationInfo.class) {
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

    public static LocationInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<LocationInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
