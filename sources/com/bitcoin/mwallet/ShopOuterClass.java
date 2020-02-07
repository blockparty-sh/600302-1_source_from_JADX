package com.bitcoin.mwallet;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.ProtobufList;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class ShopOuterClass {

    /* renamed from: com.bitcoin.mwallet.ShopOuterClass$1 */
    static /* synthetic */ class C10401 {

        /* renamed from: $SwitchMap$com$bitcoin$mwallet$ShopOuterClass$ReportShopResponse$ResponseCase */
        static final /* synthetic */ int[] f192x46c9b5a0 = new int[ResponseCase.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0047 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0051 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0067 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0072 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007d */
        static {
            /*
                com.bitcoin.mwallet.ShopOuterClass$ReportShopResponse$ResponseCase[] r0 = com.bitcoin.mwallet.ShopOuterClass.ReportShopResponse.ResponseCase.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f192x46c9b5a0 = r0
                r0 = 1
                int[] r1 = f192x46c9b5a0     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.bitcoin.mwallet.ShopOuterClass$ReportShopResponse$ResponseCase r2 = com.bitcoin.mwallet.ShopOuterClass.ReportShopResponse.ResponseCase.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f192x46c9b5a0     // Catch:{ NoSuchFieldError -> 0x001f }
                com.bitcoin.mwallet.ShopOuterClass$ReportShopResponse$ResponseCase r3 = com.bitcoin.mwallet.ShopOuterClass.ReportShopResponse.ResponseCase.ERROR     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f192x46c9b5a0     // Catch:{ NoSuchFieldError -> 0x002a }
                com.bitcoin.mwallet.ShopOuterClass$ReportShopResponse$ResponseCase r4 = com.bitcoin.mwallet.ShopOuterClass.ReportShopResponse.ResponseCase.RESPONSE_NOT_SET     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                f193xa1df5c61 = r3
                int[] r3 = f193xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x003d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                int[] r0 = f193xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0047 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0047 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0047 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0047 }
            L_0x0047:
                int[] r0 = f193xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0051 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x0051 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0051 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0051 }
            L_0x0051:
                int[] r0 = f193xa1df5c61     // Catch:{ NoSuchFieldError -> 0x005c }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x005c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                int[] r0 = f193xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0067 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0067 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0067 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0067 }
            L_0x0067:
                int[] r0 = f193xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0072 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x0072 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0072 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0072 }
            L_0x0072:
                int[] r0 = f193xa1df5c61     // Catch:{ NoSuchFieldError -> 0x007d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x007d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007d }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007d }
            L_0x007d:
                int[] r0 = f193xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.ShopOuterClass.C10401.<clinit>():void");
        }
    }

    public static final class Location extends GeneratedMessageLite<Location, Builder> implements LocationOrBuilder {
        /* access modifiers changed from: private */
        public static final Location DEFAULT_INSTANCE = new Location();
        public static final int LAT_FIELD_NUMBER = 1;
        public static final int LNG_FIELD_NUMBER = 2;
        private static volatile Parser<Location> PARSER;
        private String lat_;
        private String lng_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<Location, Builder> implements LocationOrBuilder {
            /* synthetic */ Builder(C10401 r1) {
                this();
            }

            private Builder() {
                super(Location.DEFAULT_INSTANCE);
            }

            public String getLat() {
                return ((Location) this.instance).getLat();
            }

            public ByteString getLatBytes() {
                return ((Location) this.instance).getLatBytes();
            }

            public Builder setLat(String str) {
                copyOnWrite();
                ((Location) this.instance).setLat(str);
                return this;
            }

            public Builder clearLat() {
                copyOnWrite();
                ((Location) this.instance).clearLat();
                return this;
            }

            public Builder setLatBytes(ByteString byteString) {
                copyOnWrite();
                ((Location) this.instance).setLatBytes(byteString);
                return this;
            }

            public String getLng() {
                return ((Location) this.instance).getLng();
            }

            public ByteString getLngBytes() {
                return ((Location) this.instance).getLngBytes();
            }

            public Builder setLng(String str) {
                copyOnWrite();
                ((Location) this.instance).setLng(str);
                return this;
            }

            public Builder clearLng() {
                copyOnWrite();
                ((Location) this.instance).clearLng();
                return this;
            }

            public Builder setLngBytes(ByteString byteString) {
                copyOnWrite();
                ((Location) this.instance).setLngBytes(byteString);
                return this;
            }
        }

        private Location() {
            String str = "";
            this.lat_ = str;
            this.lng_ = str;
        }

        public String getLat() {
            return this.lat_;
        }

        public ByteString getLatBytes() {
            return ByteString.copyFromUtf8(this.lat_);
        }

        /* access modifiers changed from: private */
        public void setLat(String str) {
            if (str != null) {
                this.lat_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearLat() {
            this.lat_ = getDefaultInstance().getLat();
        }

        /* access modifiers changed from: private */
        public void setLatBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.lat_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public String getLng() {
            return this.lng_;
        }

        public ByteString getLngBytes() {
            return ByteString.copyFromUtf8(this.lng_);
        }

        /* access modifiers changed from: private */
        public void setLng(String str) {
            if (str != null) {
                this.lng_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearLng() {
            this.lng_ = getDefaultInstance().getLng();
        }

        /* access modifiers changed from: private */
        public void setLngBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.lng_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.lat_.isEmpty()) {
                codedOutputStream.writeString(1, getLat());
            }
            if (!this.lng_.isEmpty()) {
                codedOutputStream.writeString(2, getLng());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.lat_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getLat());
            }
            if (!this.lng_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getLng());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static Location parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Location) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Location parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Location) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Location parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Location) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Location parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Location) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Location parseFrom(InputStream inputStream) throws IOException {
            return (Location) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Location parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Location) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Location parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Location) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Location parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Location) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Location parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Location) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Location parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Location) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Location location) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(location);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new Location();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    Location location = (Location) obj2;
                    this.lat_ = visitor.visitString(!this.lat_.isEmpty(), this.lat_, !location.lat_.isEmpty(), location.lat_);
                    this.lng_ = visitor.visitString(!this.lng_.isEmpty(), this.lng_, true ^ location.lng_.isEmpty(), location.lng_);
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
                                    this.lat_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    this.lng_ = codedInputStream.readStringRequireUtf8();
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
                        synchronized (Location.class) {
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

        public static Location getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Location> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface LocationOrBuilder extends MessageLiteOrBuilder {
        String getLat();

        ByteString getLatBytes();

        String getLng();

        ByteString getLngBytes();
    }

    public static final class ReportShopRequest extends GeneratedMessageLite<ReportShopRequest, Builder> implements ReportShopRequestOrBuilder {
        /* access modifiers changed from: private */
        public static final ReportShopRequest DEFAULT_INSTANCE = new ReportShopRequest();
        private static volatile Parser<ReportShopRequest> PARSER = null;
        public static final int REASON_FIELD_NUMBER = 2;
        public static final int SHOP_ID_FIELD_NUMBER = 1;
        private String reason_;
        private String shopId_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<ReportShopRequest, Builder> implements ReportShopRequestOrBuilder {
            /* synthetic */ Builder(C10401 r1) {
                this();
            }

            private Builder() {
                super(ReportShopRequest.DEFAULT_INSTANCE);
            }

            public String getShopId() {
                return ((ReportShopRequest) this.instance).getShopId();
            }

            public ByteString getShopIdBytes() {
                return ((ReportShopRequest) this.instance).getShopIdBytes();
            }

            public Builder setShopId(String str) {
                copyOnWrite();
                ((ReportShopRequest) this.instance).setShopId(str);
                return this;
            }

            public Builder clearShopId() {
                copyOnWrite();
                ((ReportShopRequest) this.instance).clearShopId();
                return this;
            }

            public Builder setShopIdBytes(ByteString byteString) {
                copyOnWrite();
                ((ReportShopRequest) this.instance).setShopIdBytes(byteString);
                return this;
            }

            public String getReason() {
                return ((ReportShopRequest) this.instance).getReason();
            }

            public ByteString getReasonBytes() {
                return ((ReportShopRequest) this.instance).getReasonBytes();
            }

            public Builder setReason(String str) {
                copyOnWrite();
                ((ReportShopRequest) this.instance).setReason(str);
                return this;
            }

            public Builder clearReason() {
                copyOnWrite();
                ((ReportShopRequest) this.instance).clearReason();
                return this;
            }

            public Builder setReasonBytes(ByteString byteString) {
                copyOnWrite();
                ((ReportShopRequest) this.instance).setReasonBytes(byteString);
                return this;
            }
        }

        private ReportShopRequest() {
            String str = "";
            this.shopId_ = str;
            this.reason_ = str;
        }

        public String getShopId() {
            return this.shopId_;
        }

        public ByteString getShopIdBytes() {
            return ByteString.copyFromUtf8(this.shopId_);
        }

        /* access modifiers changed from: private */
        public void setShopId(String str) {
            if (str != null) {
                this.shopId_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearShopId() {
            this.shopId_ = getDefaultInstance().getShopId();
        }

        /* access modifiers changed from: private */
        public void setShopIdBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.shopId_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public String getReason() {
            return this.reason_;
        }

        public ByteString getReasonBytes() {
            return ByteString.copyFromUtf8(this.reason_);
        }

        /* access modifiers changed from: private */
        public void setReason(String str) {
            if (str != null) {
                this.reason_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearReason() {
            this.reason_ = getDefaultInstance().getReason();
        }

        /* access modifiers changed from: private */
        public void setReasonBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.reason_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.shopId_.isEmpty()) {
                codedOutputStream.writeString(1, getShopId());
            }
            if (!this.reason_.isEmpty()) {
                codedOutputStream.writeString(2, getReason());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.shopId_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getShopId());
            }
            if (!this.reason_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getReason());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static ReportShopRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ReportShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ReportShopRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReportShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ReportShopRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ReportShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ReportShopRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReportShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ReportShopRequest parseFrom(InputStream inputStream) throws IOException {
            return (ReportShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReportShopRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReportShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReportShopRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ReportShopRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReportShopRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReportShopRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReportShopRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ReportShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ReportShopRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReportShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ReportShopRequest reportShopRequest) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(reportShopRequest);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new ReportShopRequest();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    ReportShopRequest reportShopRequest = (ReportShopRequest) obj2;
                    this.shopId_ = visitor.visitString(!this.shopId_.isEmpty(), this.shopId_, !reportShopRequest.shopId_.isEmpty(), reportShopRequest.shopId_);
                    this.reason_ = visitor.visitString(!this.reason_.isEmpty(), this.reason_, true ^ reportShopRequest.reason_.isEmpty(), reportShopRequest.reason_);
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
                                    this.shopId_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    this.reason_ = codedInputStream.readStringRequireUtf8();
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
                        synchronized (ReportShopRequest.class) {
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

        public static ReportShopRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ReportShopRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface ReportShopRequestOrBuilder extends MessageLiteOrBuilder {
        String getReason();

        ByteString getReasonBytes();

        String getShopId();

        ByteString getShopIdBytes();
    }

    public static final class ReportShopResponse extends GeneratedMessageLite<ReportShopResponse, Builder> implements ReportShopResponseOrBuilder {
        /* access modifiers changed from: private */
        public static final ReportShopResponse DEFAULT_INSTANCE = new ReportShopResponse();
        public static final int ERROR_FIELD_NUMBER = 2;
        private static volatile Parser<ReportShopResponse> PARSER = null;
        public static final int SUCCESS_FIELD_NUMBER = 1;
        private int responseCase_ = 0;
        private Object response_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<ReportShopResponse, Builder> implements ReportShopResponseOrBuilder {
            /* synthetic */ Builder(C10401 r1) {
                this();
            }

            private Builder() {
                super(ReportShopResponse.DEFAULT_INSTANCE);
            }

            public ResponseCase getResponseCase() {
                return ((ReportShopResponse) this.instance).getResponseCase();
            }

            public Builder clearResponse() {
                copyOnWrite();
                ((ReportShopResponse) this.instance).clearResponse();
                return this;
            }

            public boolean getSuccess() {
                return ((ReportShopResponse) this.instance).getSuccess();
            }

            public Builder setSuccess(boolean z) {
                copyOnWrite();
                ((ReportShopResponse) this.instance).setSuccess(z);
                return this;
            }

            public Builder clearSuccess() {
                copyOnWrite();
                ((ReportShopResponse) this.instance).clearSuccess();
                return this;
            }

            public ResponseError getError() {
                return ((ReportShopResponse) this.instance).getError();
            }

            public Builder setError(ResponseError responseError) {
                copyOnWrite();
                ((ReportShopResponse) this.instance).setError(responseError);
                return this;
            }

            public Builder setError(com.bitcoin.mwallet.ResponseError.Builder builder) {
                copyOnWrite();
                ((ReportShopResponse) this.instance).setError(builder);
                return this;
            }

            public Builder mergeError(ResponseError responseError) {
                copyOnWrite();
                ((ReportShopResponse) this.instance).mergeError(responseError);
                return this;
            }

            public Builder clearError() {
                copyOnWrite();
                ((ReportShopResponse) this.instance).clearError();
                return this;
            }
        }

        public enum ResponseCase implements EnumLite {
            SUCCESS(1),
            ERROR(2),
            RESPONSE_NOT_SET(0);
            
            private final int value;

            private ResponseCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static ResponseCase valueOf(int i) {
                return forNumber(i);
            }

            public static ResponseCase forNumber(int i) {
                if (i == 0) {
                    return RESPONSE_NOT_SET;
                }
                if (i == 1) {
                    return SUCCESS;
                }
                if (i != 2) {
                    return null;
                }
                return ERROR;
            }

            public int getNumber() {
                return this.value;
            }
        }

        private ReportShopResponse() {
        }

        public ResponseCase getResponseCase() {
            return ResponseCase.forNumber(this.responseCase_);
        }

        /* access modifiers changed from: private */
        public void clearResponse() {
            this.responseCase_ = 0;
            this.response_ = null;
        }

        public boolean getSuccess() {
            if (this.responseCase_ == 1) {
                return ((Boolean) this.response_).booleanValue();
            }
            return false;
        }

        /* access modifiers changed from: private */
        public void setSuccess(boolean z) {
            this.responseCase_ = 1;
            this.response_ = Boolean.valueOf(z);
        }

        /* access modifiers changed from: private */
        public void clearSuccess() {
            if (this.responseCase_ == 1) {
                this.responseCase_ = 0;
                this.response_ = null;
            }
        }

        public ResponseError getError() {
            if (this.responseCase_ == 2) {
                return (ResponseError) this.response_;
            }
            return ResponseError.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setError(ResponseError responseError) {
            if (responseError != null) {
                this.response_ = responseError;
                this.responseCase_ = 2;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void setError(com.bitcoin.mwallet.ResponseError.Builder builder) {
            this.response_ = builder.build();
            this.responseCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeError(ResponseError responseError) {
            if (this.responseCase_ != 2 || this.response_ == ResponseError.getDefaultInstance()) {
                this.response_ = responseError;
            } else {
                this.response_ = ((com.bitcoin.mwallet.ResponseError.Builder) ResponseError.newBuilder((ResponseError) this.response_).mergeFrom(responseError)).buildPartial();
            }
            this.responseCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearError() {
            if (this.responseCase_ == 2) {
                this.responseCase_ = 0;
                this.response_ = null;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.responseCase_ == 1) {
                codedOutputStream.writeBool(1, ((Boolean) this.response_).booleanValue());
            }
            if (this.responseCase_ == 2) {
                codedOutputStream.writeMessage(2, (ResponseError) this.response_);
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.responseCase_ == 1) {
                i2 = 0 + CodedOutputStream.computeBoolSize(1, ((Boolean) this.response_).booleanValue());
            }
            if (this.responseCase_ == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, (ResponseError) this.response_);
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static ReportShopResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ReportShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ReportShopResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReportShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ReportShopResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ReportShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ReportShopResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReportShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ReportShopResponse parseFrom(InputStream inputStream) throws IOException {
            return (ReportShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReportShopResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReportShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReportShopResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ReportShopResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ReportShopResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReportShopResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ReportShopResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ReportShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ReportShopResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReportShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ReportShopResponse reportShopResponse) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(reportShopResponse);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new ReportShopResponse();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    ReportShopResponse reportShopResponse = (ReportShopResponse) obj2;
                    int i = C10401.f192x46c9b5a0[reportShopResponse.getResponseCase().ordinal()];
                    if (i == 1) {
                        if (this.responseCase_ == 1) {
                            z = true;
                        }
                        this.response_ = visitor.visitOneofBoolean(z, this.response_, reportShopResponse.response_);
                    } else if (i == 2) {
                        if (this.responseCase_ == 2) {
                            z = true;
                        }
                        this.response_ = visitor.visitOneofMessage(z, this.response_, reportShopResponse.response_);
                    } else if (i == 3) {
                        if (this.responseCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == MergeFromVisitor.INSTANCE) {
                        int i2 = reportShopResponse.responseCase_;
                        if (i2 != 0) {
                            this.responseCase_ = i2;
                        }
                    }
                    return this;
                case MERGE_FROM_STREAM:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.responseCase_ = 1;
                                    this.response_ = Boolean.valueOf(codedInputStream.readBool());
                                } else if (readTag == 18) {
                                    com.bitcoin.mwallet.ResponseError.Builder builder = this.responseCase_ == 2 ? (com.bitcoin.mwallet.ResponseError.Builder) ((ResponseError) this.response_).toBuilder() : null;
                                    this.response_ = codedInputStream.readMessage(ResponseError.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom((ResponseError) this.response_);
                                        this.response_ = builder.buildPartial();
                                    }
                                    this.responseCase_ = 2;
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
                        synchronized (ReportShopResponse.class) {
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

        public static ReportShopResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ReportShopResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface ReportShopResponseOrBuilder extends MessageLiteOrBuilder {
        ResponseError getError();

        ResponseCase getResponseCase();

        boolean getSuccess();
    }

    public static final class Shop extends GeneratedMessageLite<Shop, Builder> implements ShopOrBuilder {
        public static final int CATEGORY_FIELD_NUMBER = 6;
        /* access modifiers changed from: private */
        public static final Shop DEFAULT_INSTANCE = new Shop();
        public static final int ID_FIELD_NUMBER = 2;
        public static final int INDEX_FIELD_NUMBER = 1;
        public static final int ISVERIFIED_FIELD_NUMBER = 8;
        public static final int LOCATION_FIELD_NUMBER = 7;
        private static volatile Parser<Shop> PARSER = null;
        public static final int SHORT_DESCRIPTION_FIELD_NUMBER = 4;
        public static final int TITLE_FIELD_NUMBER = 3;
        public static final int URL_FIELD_NUMBER = 5;
        private String category_;
        private String id_;
        private int index_;
        private boolean isVerified_;
        private Location location_;
        private String shortDescription_;
        private String title_;
        private String url_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<Shop, Builder> implements ShopOrBuilder {
            /* synthetic */ Builder(C10401 r1) {
                this();
            }

            private Builder() {
                super(Shop.DEFAULT_INSTANCE);
            }

            public int getIndex() {
                return ((Shop) this.instance).getIndex();
            }

            public Builder setIndex(int i) {
                copyOnWrite();
                ((Shop) this.instance).setIndex(i);
                return this;
            }

            public Builder clearIndex() {
                copyOnWrite();
                ((Shop) this.instance).clearIndex();
                return this;
            }

            public String getId() {
                return ((Shop) this.instance).getId();
            }

            public ByteString getIdBytes() {
                return ((Shop) this.instance).getIdBytes();
            }

            public Builder setId(String str) {
                copyOnWrite();
                ((Shop) this.instance).setId(str);
                return this;
            }

            public Builder clearId() {
                copyOnWrite();
                ((Shop) this.instance).clearId();
                return this;
            }

            public Builder setIdBytes(ByteString byteString) {
                copyOnWrite();
                ((Shop) this.instance).setIdBytes(byteString);
                return this;
            }

            public String getTitle() {
                return ((Shop) this.instance).getTitle();
            }

            public ByteString getTitleBytes() {
                return ((Shop) this.instance).getTitleBytes();
            }

            public Builder setTitle(String str) {
                copyOnWrite();
                ((Shop) this.instance).setTitle(str);
                return this;
            }

            public Builder clearTitle() {
                copyOnWrite();
                ((Shop) this.instance).clearTitle();
                return this;
            }

            public Builder setTitleBytes(ByteString byteString) {
                copyOnWrite();
                ((Shop) this.instance).setTitleBytes(byteString);
                return this;
            }

            public String getShortDescription() {
                return ((Shop) this.instance).getShortDescription();
            }

            public ByteString getShortDescriptionBytes() {
                return ((Shop) this.instance).getShortDescriptionBytes();
            }

            public Builder setShortDescription(String str) {
                copyOnWrite();
                ((Shop) this.instance).setShortDescription(str);
                return this;
            }

            public Builder clearShortDescription() {
                copyOnWrite();
                ((Shop) this.instance).clearShortDescription();
                return this;
            }

            public Builder setShortDescriptionBytes(ByteString byteString) {
                copyOnWrite();
                ((Shop) this.instance).setShortDescriptionBytes(byteString);
                return this;
            }

            public String getUrl() {
                return ((Shop) this.instance).getUrl();
            }

            public ByteString getUrlBytes() {
                return ((Shop) this.instance).getUrlBytes();
            }

            public Builder setUrl(String str) {
                copyOnWrite();
                ((Shop) this.instance).setUrl(str);
                return this;
            }

            public Builder clearUrl() {
                copyOnWrite();
                ((Shop) this.instance).clearUrl();
                return this;
            }

            public Builder setUrlBytes(ByteString byteString) {
                copyOnWrite();
                ((Shop) this.instance).setUrlBytes(byteString);
                return this;
            }

            public String getCategory() {
                return ((Shop) this.instance).getCategory();
            }

            public ByteString getCategoryBytes() {
                return ((Shop) this.instance).getCategoryBytes();
            }

            public Builder setCategory(String str) {
                copyOnWrite();
                ((Shop) this.instance).setCategory(str);
                return this;
            }

            public Builder clearCategory() {
                copyOnWrite();
                ((Shop) this.instance).clearCategory();
                return this;
            }

            public Builder setCategoryBytes(ByteString byteString) {
                copyOnWrite();
                ((Shop) this.instance).setCategoryBytes(byteString);
                return this;
            }

            public boolean hasLocation() {
                return ((Shop) this.instance).hasLocation();
            }

            public Location getLocation() {
                return ((Shop) this.instance).getLocation();
            }

            public Builder setLocation(Location location) {
                copyOnWrite();
                ((Shop) this.instance).setLocation(location);
                return this;
            }

            public Builder setLocation(Builder builder) {
                copyOnWrite();
                ((Shop) this.instance).setLocation(builder);
                return this;
            }

            public Builder mergeLocation(Location location) {
                copyOnWrite();
                ((Shop) this.instance).mergeLocation(location);
                return this;
            }

            public Builder clearLocation() {
                copyOnWrite();
                ((Shop) this.instance).clearLocation();
                return this;
            }

            public boolean getIsVerified() {
                return ((Shop) this.instance).getIsVerified();
            }

            public Builder setIsVerified(boolean z) {
                copyOnWrite();
                ((Shop) this.instance).setIsVerified(z);
                return this;
            }

            public Builder clearIsVerified() {
                copyOnWrite();
                ((Shop) this.instance).clearIsVerified();
                return this;
            }
        }

        private Shop() {
            String str = "";
            this.id_ = str;
            this.title_ = str;
            this.shortDescription_ = str;
            this.url_ = str;
            this.category_ = str;
        }

        public int getIndex() {
            return this.index_;
        }

        /* access modifiers changed from: private */
        public void setIndex(int i) {
            this.index_ = i;
        }

        /* access modifiers changed from: private */
        public void clearIndex() {
            this.index_ = 0;
        }

        public String getId() {
            return this.id_;
        }

        public ByteString getIdBytes() {
            return ByteString.copyFromUtf8(this.id_);
        }

        /* access modifiers changed from: private */
        public void setId(String str) {
            if (str != null) {
                this.id_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearId() {
            this.id_ = getDefaultInstance().getId();
        }

        /* access modifiers changed from: private */
        public void setIdBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.id_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public String getTitle() {
            return this.title_;
        }

        public ByteString getTitleBytes() {
            return ByteString.copyFromUtf8(this.title_);
        }

        /* access modifiers changed from: private */
        public void setTitle(String str) {
            if (str != null) {
                this.title_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearTitle() {
            this.title_ = getDefaultInstance().getTitle();
        }

        /* access modifiers changed from: private */
        public void setTitleBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.title_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public String getShortDescription() {
            return this.shortDescription_;
        }

        public ByteString getShortDescriptionBytes() {
            return ByteString.copyFromUtf8(this.shortDescription_);
        }

        /* access modifiers changed from: private */
        public void setShortDescription(String str) {
            if (str != null) {
                this.shortDescription_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearShortDescription() {
            this.shortDescription_ = getDefaultInstance().getShortDescription();
        }

        /* access modifiers changed from: private */
        public void setShortDescriptionBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.shortDescription_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public String getUrl() {
            return this.url_;
        }

        public ByteString getUrlBytes() {
            return ByteString.copyFromUtf8(this.url_);
        }

        /* access modifiers changed from: private */
        public void setUrl(String str) {
            if (str != null) {
                this.url_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearUrl() {
            this.url_ = getDefaultInstance().getUrl();
        }

        /* access modifiers changed from: private */
        public void setUrlBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.url_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public String getCategory() {
            return this.category_;
        }

        public ByteString getCategoryBytes() {
            return ByteString.copyFromUtf8(this.category_);
        }

        /* access modifiers changed from: private */
        public void setCategory(String str) {
            if (str != null) {
                this.category_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearCategory() {
            this.category_ = getDefaultInstance().getCategory();
        }

        /* access modifiers changed from: private */
        public void setCategoryBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.category_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public boolean hasLocation() {
            return this.location_ != null;
        }

        public Location getLocation() {
            Location location = this.location_;
            return location == null ? Location.getDefaultInstance() : location;
        }

        /* access modifiers changed from: private */
        public void setLocation(Location location) {
            if (location != null) {
                this.location_ = location;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void setLocation(Builder builder) {
            this.location_ = (Location) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeLocation(Location location) {
            Location location2 = this.location_;
            if (location2 == null || location2 == Location.getDefaultInstance()) {
                this.location_ = location;
            } else {
                this.location_ = (Location) ((Builder) Location.newBuilder(this.location_).mergeFrom(location)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearLocation() {
            this.location_ = null;
        }

        public boolean getIsVerified() {
            return this.isVerified_;
        }

        /* access modifiers changed from: private */
        public void setIsVerified(boolean z) {
            this.isVerified_ = z;
        }

        /* access modifiers changed from: private */
        public void clearIsVerified() {
            this.isVerified_ = false;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.index_;
            if (i != 0) {
                codedOutputStream.writeUInt32(1, i);
            }
            if (!this.id_.isEmpty()) {
                codedOutputStream.writeString(2, getId());
            }
            if (!this.title_.isEmpty()) {
                codedOutputStream.writeString(3, getTitle());
            }
            if (!this.shortDescription_.isEmpty()) {
                codedOutputStream.writeString(4, getShortDescription());
            }
            if (!this.url_.isEmpty()) {
                codedOutputStream.writeString(5, getUrl());
            }
            if (!this.category_.isEmpty()) {
                codedOutputStream.writeString(6, getCategory());
            }
            if (this.location_ != null) {
                codedOutputStream.writeMessage(7, getLocation());
            }
            boolean z = this.isVerified_;
            if (z) {
                codedOutputStream.writeBool(8, z);
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.index_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, i3);
            }
            if (!this.id_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getId());
            }
            if (!this.title_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(3, getTitle());
            }
            if (!this.shortDescription_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(4, getShortDescription());
            }
            if (!this.url_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(5, getUrl());
            }
            if (!this.category_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(6, getCategory());
            }
            if (this.location_ != null) {
                i2 += CodedOutputStream.computeMessageSize(7, getLocation());
            }
            boolean z = this.isVerified_;
            if (z) {
                i2 += CodedOutputStream.computeBoolSize(8, z);
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static Shop parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Shop) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Shop parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Shop) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Shop parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Shop) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Shop parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Shop) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Shop parseFrom(InputStream inputStream) throws IOException {
            return (Shop) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Shop parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Shop) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Shop parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Shop) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Shop parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Shop) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Shop parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Shop) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Shop parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Shop) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Shop shop) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(shop);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new Shop();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    Shop shop = (Shop) obj2;
                    boolean z2 = this.index_ != 0;
                    int i = this.index_;
                    if (shop.index_ != 0) {
                        z = true;
                    }
                    this.index_ = visitor.visitInt(z2, i, z, shop.index_);
                    this.id_ = visitor.visitString(!this.id_.isEmpty(), this.id_, !shop.id_.isEmpty(), shop.id_);
                    this.title_ = visitor.visitString(!this.title_.isEmpty(), this.title_, !shop.title_.isEmpty(), shop.title_);
                    this.shortDescription_ = visitor.visitString(!this.shortDescription_.isEmpty(), this.shortDescription_, !shop.shortDescription_.isEmpty(), shop.shortDescription_);
                    this.url_ = visitor.visitString(!this.url_.isEmpty(), this.url_, !shop.url_.isEmpty(), shop.url_);
                    this.category_ = visitor.visitString(!this.category_.isEmpty(), this.category_, !shop.category_.isEmpty(), shop.category_);
                    this.location_ = (Location) visitor.visitMessage(this.location_, shop.location_);
                    boolean z3 = this.isVerified_;
                    boolean z4 = shop.isVerified_;
                    this.isVerified_ = visitor.visitBoolean(z3, z3, z4, z4);
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
                                    this.index_ = codedInputStream.readUInt32();
                                } else if (readTag == 18) {
                                    this.id_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 26) {
                                    this.title_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 34) {
                                    this.shortDescription_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 42) {
                                    this.url_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 50) {
                                    this.category_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 58) {
                                    Builder builder = this.location_ != null ? (Builder) this.location_.toBuilder() : null;
                                    this.location_ = (Location) codedInputStream.readMessage(Location.parser(), extensionRegistryLite);
                                    if (builder != null) {
                                        builder.mergeFrom(this.location_);
                                        this.location_ = (Location) builder.buildPartial();
                                    }
                                } else if (readTag == 64) {
                                    this.isVerified_ = codedInputStream.readBool();
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
                        synchronized (Shop.class) {
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

        public static Shop getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Shop> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface ShopOrBuilder extends MessageLiteOrBuilder {
        String getCategory();

        ByteString getCategoryBytes();

        String getId();

        ByteString getIdBytes();

        int getIndex();

        boolean getIsVerified();

        Location getLocation();

        String getShortDescription();

        ByteString getShortDescriptionBytes();

        String getTitle();

        ByteString getTitleBytes();

        String getUrl();

        ByteString getUrlBytes();

        boolean hasLocation();
    }

    public static final class ShopRequest extends GeneratedMessageLite<ShopRequest, Builder> implements ShopRequestOrBuilder {
        /* access modifiers changed from: private */
        public static final ShopRequest DEFAULT_INSTANCE = new ShopRequest();
        private static volatile Parser<ShopRequest> PARSER;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<ShopRequest, Builder> implements ShopRequestOrBuilder {
            /* synthetic */ Builder(C10401 r1) {
                this();
            }

            private Builder() {
                super(ShopRequest.DEFAULT_INSTANCE);
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        }

        private ShopRequest() {
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            this.memoizedSerializedSize = 0;
            return 0;
        }

        public static ShopRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ShopRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ShopRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ShopRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ShopRequest parseFrom(InputStream inputStream) throws IOException {
            return (ShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ShopRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ShopRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ShopRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ShopRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ShopRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ShopRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ShopRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ShopRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ShopRequest shopRequest) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(shopRequest);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new ShopRequest();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    ShopRequest shopRequest = (ShopRequest) obj2;
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
                        synchronized (ShopRequest.class) {
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

        public static ShopRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ShopRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface ShopRequestOrBuilder extends MessageLiteOrBuilder {
    }

    public static final class ShopResponse extends GeneratedMessageLite<ShopResponse, Builder> implements ShopResponseOrBuilder {
        /* access modifiers changed from: private */
        public static final ShopResponse DEFAULT_INSTANCE = new ShopResponse();
        public static final int ITEMS_FIELD_NUMBER = 1;
        private static volatile Parser<ShopResponse> PARSER;
        private ProtobufList<Shop> items_ = emptyProtobufList();

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<ShopResponse, Builder> implements ShopResponseOrBuilder {
            /* synthetic */ Builder(C10401 r1) {
                this();
            }

            private Builder() {
                super(ShopResponse.DEFAULT_INSTANCE);
            }

            public List<Shop> getItemsList() {
                return Collections.unmodifiableList(((ShopResponse) this.instance).getItemsList());
            }

            public int getItemsCount() {
                return ((ShopResponse) this.instance).getItemsCount();
            }

            public Shop getItems(int i) {
                return ((ShopResponse) this.instance).getItems(i);
            }

            public Builder setItems(int i, Shop shop) {
                copyOnWrite();
                ((ShopResponse) this.instance).setItems(i, shop);
                return this;
            }

            public Builder setItems(int i, Builder builder) {
                copyOnWrite();
                ((ShopResponse) this.instance).setItems(i, builder);
                return this;
            }

            public Builder addItems(Shop shop) {
                copyOnWrite();
                ((ShopResponse) this.instance).addItems(shop);
                return this;
            }

            public Builder addItems(int i, Shop shop) {
                copyOnWrite();
                ((ShopResponse) this.instance).addItems(i, shop);
                return this;
            }

            public Builder addItems(Builder builder) {
                copyOnWrite();
                ((ShopResponse) this.instance).addItems(builder);
                return this;
            }

            public Builder addItems(int i, Builder builder) {
                copyOnWrite();
                ((ShopResponse) this.instance).addItems(i, builder);
                return this;
            }

            public Builder addAllItems(Iterable<? extends Shop> iterable) {
                copyOnWrite();
                ((ShopResponse) this.instance).addAllItems(iterable);
                return this;
            }

            public Builder clearItems() {
                copyOnWrite();
                ((ShopResponse) this.instance).clearItems();
                return this;
            }

            public Builder removeItems(int i) {
                copyOnWrite();
                ((ShopResponse) this.instance).removeItems(i);
                return this;
            }
        }

        private ShopResponse() {
        }

        public List<Shop> getItemsList() {
            return this.items_;
        }

        public List<? extends ShopOrBuilder> getItemsOrBuilderList() {
            return this.items_;
        }

        public int getItemsCount() {
            return this.items_.size();
        }

        public Shop getItems(int i) {
            return (Shop) this.items_.get(i);
        }

        public ShopOrBuilder getItemsOrBuilder(int i) {
            return (ShopOrBuilder) this.items_.get(i);
        }

        private void ensureItemsIsMutable() {
            if (!this.items_.isModifiable()) {
                this.items_ = GeneratedMessageLite.mutableCopy(this.items_);
            }
        }

        /* access modifiers changed from: private */
        public void setItems(int i, Shop shop) {
            if (shop != null) {
                ensureItemsIsMutable();
                this.items_.set(i, shop);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void setItems(int i, Builder builder) {
            ensureItemsIsMutable();
            this.items_.set(i, builder.build());
        }

        /* access modifiers changed from: private */
        public void addItems(Shop shop) {
            if (shop != null) {
                ensureItemsIsMutable();
                this.items_.add(shop);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void addItems(int i, Shop shop) {
            if (shop != null) {
                ensureItemsIsMutable();
                this.items_.add(i, shop);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void addItems(Builder builder) {
            ensureItemsIsMutable();
            this.items_.add(builder.build());
        }

        /* access modifiers changed from: private */
        public void addItems(int i, Builder builder) {
            ensureItemsIsMutable();
            this.items_.add(i, builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllItems(Iterable<? extends Shop> iterable) {
            ensureItemsIsMutable();
            AbstractMessageLite.addAll(iterable, this.items_);
        }

        /* access modifiers changed from: private */
        public void clearItems() {
            this.items_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeItems(int i) {
            ensureItemsIsMutable();
            this.items_.remove(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.items_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.items_.get(i));
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.items_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.items_.get(i3));
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static ShopResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ShopResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ShopResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ShopResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ShopResponse parseFrom(InputStream inputStream) throws IOException {
            return (ShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ShopResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ShopResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ShopResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ShopResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ShopResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ShopResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ShopResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ShopResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ShopResponse shopResponse) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(shopResponse);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new ShopResponse();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.items_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    this.items_ = ((Visitor) obj).visitList(this.items_, ((ShopResponse) obj2).items_);
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
                                    if (!this.items_.isModifiable()) {
                                        this.items_ = GeneratedMessageLite.mutableCopy(this.items_);
                                    }
                                    this.items_.add(codedInputStream.readMessage(Shop.parser(), extensionRegistryLite));
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
                        synchronized (ShopResponse.class) {
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

        public static ShopResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ShopResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface ShopResponseOrBuilder extends MessageLiteOrBuilder {
        Shop getItems(int i);

        int getItemsCount();

        List<Shop> getItemsList();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ShopOuterClass() {
    }
}
