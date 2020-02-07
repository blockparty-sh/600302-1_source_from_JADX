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

public final class RegisterRegionRequest extends GeneratedMessageLite<RegisterRegionRequest, Builder> implements RegisterRegionRequestOrBuilder {
    /* access modifiers changed from: private */
    public static final RegisterRegionRequest DEFAULT_INSTANCE = new RegisterRegionRequest();
    private static volatile Parser<RegisterRegionRequest> PARSER = null;
    public static final int REGISTRATION_FIELD_NUMBER = 1;
    public static final int USER_LANGUAGE_FIELD_NUMBER = 2;
    private int bitField0_;
    private ProtobufList<RegionRegistration> registration_ = emptyProtobufList();
    private String userLanguage_ = "";

    /* renamed from: com.bitcoin.mwallet.RegisterRegionRequest$1 */
    static /* synthetic */ class C10311 {

        /* renamed from: $SwitchMap$com$bitcoin$mwallet$RegisterRegionRequest$RegionRegistration$IdCase */
        static final /* synthetic */ int[] f182x2790796f = new int[IdCase.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|(2:19|20)|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(25:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|(2:19|20)|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|9|10|11|12|13|14|15|16|17|18|19|20|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|1|2|3|5|6|7|9|10|11|12|13|14|15|16|17|18|19|20|21|23|24|25|26|27|28|30) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0075 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x007f */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f183xa1df5c61 = r0
                r0 = 1
                int[] r1 = f183xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r2 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = f183xa1df5c61     // Catch:{ NoSuchFieldError -> 0x001f }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = f183xa1df5c61     // Catch:{ NoSuchFieldError -> 0x002a }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r3 = f183xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r5 = 4
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r3 = f183xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r5 = 5
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r3 = f183xa1df5c61     // Catch:{ NoSuchFieldError -> 0x004b }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x004b }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r5 = 6
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r3 = f183xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r5 = 7
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r3 = f183xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r5 = 8
                r3[r4] = r5     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                com.bitcoin.mwallet.RegisterRegionRequest$RegionRegistration$IdCase[] r3 = com.bitcoin.mwallet.RegisterRegionRequest.RegionRegistration.IdCase.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                f182x2790796f = r3
                int[] r3 = f182x2790796f     // Catch:{ NoSuchFieldError -> 0x0075 }
                com.bitcoin.mwallet.RegisterRegionRequest$RegionRegistration$IdCase r4 = com.bitcoin.mwallet.RegisterRegionRequest.RegionRegistration.IdCase.SINGLE_SIG_WALLET_ID     // Catch:{ NoSuchFieldError -> 0x0075 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0075 }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x0075 }
            L_0x0075:
                int[] r0 = f182x2790796f     // Catch:{ NoSuchFieldError -> 0x007f }
                com.bitcoin.mwallet.RegisterRegionRequest$RegionRegistration$IdCase r3 = com.bitcoin.mwallet.RegisterRegionRequest.RegionRegistration.IdCase.COPAYER_ID     // Catch:{ NoSuchFieldError -> 0x007f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x007f }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x007f }
            L_0x007f:
                int[] r0 = f182x2790796f     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.bitcoin.mwallet.RegisterRegionRequest$RegionRegistration$IdCase r1 = com.bitcoin.mwallet.RegisterRegionRequest.RegionRegistration.IdCase.ID_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.RegisterRegionRequest.C10311.<clinit>():void");
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<RegisterRegionRequest, Builder> implements RegisterRegionRequestOrBuilder {
        /* synthetic */ Builder(C10311 r1) {
            this();
        }

        private Builder() {
            super(RegisterRegionRequest.DEFAULT_INSTANCE);
        }

        public List<RegionRegistration> getRegistrationList() {
            return Collections.unmodifiableList(((RegisterRegionRequest) this.instance).getRegistrationList());
        }

        public int getRegistrationCount() {
            return ((RegisterRegionRequest) this.instance).getRegistrationCount();
        }

        public RegionRegistration getRegistration(int i) {
            return ((RegisterRegionRequest) this.instance).getRegistration(i);
        }

        public Builder setRegistration(int i, RegionRegistration regionRegistration) {
            copyOnWrite();
            ((RegisterRegionRequest) this.instance).setRegistration(i, regionRegistration);
            return this;
        }

        public Builder setRegistration(int i, Builder builder) {
            copyOnWrite();
            ((RegisterRegionRequest) this.instance).setRegistration(i, builder);
            return this;
        }

        public Builder addRegistration(RegionRegistration regionRegistration) {
            copyOnWrite();
            ((RegisterRegionRequest) this.instance).addRegistration(regionRegistration);
            return this;
        }

        public Builder addRegistration(int i, RegionRegistration regionRegistration) {
            copyOnWrite();
            ((RegisterRegionRequest) this.instance).addRegistration(i, regionRegistration);
            return this;
        }

        public Builder addRegistration(Builder builder) {
            copyOnWrite();
            ((RegisterRegionRequest) this.instance).addRegistration(builder);
            return this;
        }

        public Builder addRegistration(int i, Builder builder) {
            copyOnWrite();
            ((RegisterRegionRequest) this.instance).addRegistration(i, builder);
            return this;
        }

        public Builder addAllRegistration(Iterable<? extends RegionRegistration> iterable) {
            copyOnWrite();
            ((RegisterRegionRequest) this.instance).addAllRegistration(iterable);
            return this;
        }

        public Builder clearRegistration() {
            copyOnWrite();
            ((RegisterRegionRequest) this.instance).clearRegistration();
            return this;
        }

        public Builder removeRegistration(int i) {
            copyOnWrite();
            ((RegisterRegionRequest) this.instance).removeRegistration(i);
            return this;
        }

        public String getUserLanguage() {
            return ((RegisterRegionRequest) this.instance).getUserLanguage();
        }

        public ByteString getUserLanguageBytes() {
            return ((RegisterRegionRequest) this.instance).getUserLanguageBytes();
        }

        public Builder setUserLanguage(String str) {
            copyOnWrite();
            ((RegisterRegionRequest) this.instance).setUserLanguage(str);
            return this;
        }

        public Builder clearUserLanguage() {
            copyOnWrite();
            ((RegisterRegionRequest) this.instance).clearUserLanguage();
            return this;
        }

        public Builder setUserLanguageBytes(ByteString byteString) {
            copyOnWrite();
            ((RegisterRegionRequest) this.instance).setUserLanguageBytes(byteString);
            return this;
        }
    }

    public static final class RegionRegistration extends GeneratedMessageLite<RegionRegistration, Builder> implements RegionRegistrationOrBuilder {
        public static final int COPAYER_ID_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final RegionRegistration DEFAULT_INSTANCE = new RegionRegistration();
        private static volatile Parser<RegionRegistration> PARSER = null;
        public static final int SINGLE_SIG_WALLET_ID_FIELD_NUMBER = 1;
        private int idCase_ = 0;
        private Object id_;

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<RegionRegistration, Builder> implements RegionRegistrationOrBuilder {
            /* synthetic */ Builder(C10311 r1) {
                this();
            }

            private Builder() {
                super(RegionRegistration.DEFAULT_INSTANCE);
            }

            public IdCase getIdCase() {
                return ((RegionRegistration) this.instance).getIdCase();
            }

            public Builder clearId() {
                copyOnWrite();
                ((RegionRegistration) this.instance).clearId();
                return this;
            }

            public String getSingleSigWalletId() {
                return ((RegionRegistration) this.instance).getSingleSigWalletId();
            }

            public ByteString getSingleSigWalletIdBytes() {
                return ((RegionRegistration) this.instance).getSingleSigWalletIdBytes();
            }

            public Builder setSingleSigWalletId(String str) {
                copyOnWrite();
                ((RegionRegistration) this.instance).setSingleSigWalletId(str);
                return this;
            }

            public Builder clearSingleSigWalletId() {
                copyOnWrite();
                ((RegionRegistration) this.instance).clearSingleSigWalletId();
                return this;
            }

            public Builder setSingleSigWalletIdBytes(ByteString byteString) {
                copyOnWrite();
                ((RegionRegistration) this.instance).setSingleSigWalletIdBytes(byteString);
                return this;
            }

            public String getCopayerId() {
                return ((RegionRegistration) this.instance).getCopayerId();
            }

            public ByteString getCopayerIdBytes() {
                return ((RegionRegistration) this.instance).getCopayerIdBytes();
            }

            public Builder setCopayerId(String str) {
                copyOnWrite();
                ((RegionRegistration) this.instance).setCopayerId(str);
                return this;
            }

            public Builder clearCopayerId() {
                copyOnWrite();
                ((RegionRegistration) this.instance).clearCopayerId();
                return this;
            }

            public Builder setCopayerIdBytes(ByteString byteString) {
                copyOnWrite();
                ((RegionRegistration) this.instance).setCopayerIdBytes(byteString);
                return this;
            }
        }

        public enum IdCase implements EnumLite {
            SINGLE_SIG_WALLET_ID(1),
            COPAYER_ID(2),
            ID_NOT_SET(0);
            
            private final int value;

            private IdCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static IdCase valueOf(int i) {
                return forNumber(i);
            }

            public static IdCase forNumber(int i) {
                if (i == 0) {
                    return ID_NOT_SET;
                }
                if (i == 1) {
                    return SINGLE_SIG_WALLET_ID;
                }
                if (i != 2) {
                    return null;
                }
                return COPAYER_ID;
            }

            public int getNumber() {
                return this.value;
            }
        }

        private RegionRegistration() {
        }

        public IdCase getIdCase() {
            return IdCase.forNumber(this.idCase_);
        }

        /* access modifiers changed from: private */
        public void clearId() {
            this.idCase_ = 0;
            this.id_ = null;
        }

        public String getSingleSigWalletId() {
            return this.idCase_ == 1 ? (String) this.id_ : "";
        }

        public ByteString getSingleSigWalletIdBytes() {
            return ByteString.copyFromUtf8(this.idCase_ == 1 ? (String) this.id_ : "");
        }

        /* access modifiers changed from: private */
        public void setSingleSigWalletId(String str) {
            if (str != null) {
                this.idCase_ = 1;
                this.id_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearSingleSigWalletId() {
            if (this.idCase_ == 1) {
                this.idCase_ = 0;
                this.id_ = null;
            }
        }

        /* access modifiers changed from: private */
        public void setSingleSigWalletIdBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.idCase_ = 1;
                this.id_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public String getCopayerId() {
            return this.idCase_ == 2 ? (String) this.id_ : "";
        }

        public ByteString getCopayerIdBytes() {
            return ByteString.copyFromUtf8(this.idCase_ == 2 ? (String) this.id_ : "");
        }

        /* access modifiers changed from: private */
        public void setCopayerId(String str) {
            if (str != null) {
                this.idCase_ = 2;
                this.id_ = str;
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void clearCopayerId() {
            if (this.idCase_ == 2) {
                this.idCase_ = 0;
                this.id_ = null;
            }
        }

        /* access modifiers changed from: private */
        public void setCopayerIdBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.idCase_ = 2;
                this.id_ = byteString.toStringUtf8();
                return;
            }
            throw new NullPointerException();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.idCase_ == 1) {
                codedOutputStream.writeString(1, getSingleSigWalletId());
            }
            if (this.idCase_ == 2) {
                codedOutputStream.writeString(2, getCopayerId());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.idCase_ == 1) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getSingleSigWalletId());
            }
            if (this.idCase_ == 2) {
                i2 += CodedOutputStream.computeStringSize(2, getCopayerId());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static RegionRegistration parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (RegionRegistration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static RegionRegistration parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RegionRegistration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static RegionRegistration parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (RegionRegistration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static RegionRegistration parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RegionRegistration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static RegionRegistration parseFrom(InputStream inputStream) throws IOException {
            return (RegionRegistration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RegionRegistration parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RegionRegistration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RegionRegistration parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RegionRegistration) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RegionRegistration parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RegionRegistration) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RegionRegistration parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RegionRegistration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static RegionRegistration parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RegionRegistration) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RegionRegistration regionRegistration) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(regionRegistration);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new RegionRegistration();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    Visitor visitor = (Visitor) obj;
                    RegionRegistration regionRegistration = (RegionRegistration) obj2;
                    int i = C10311.f182x2790796f[regionRegistration.getIdCase().ordinal()];
                    if (i == 1) {
                        if (this.idCase_ == 1) {
                            z = true;
                        }
                        this.id_ = visitor.visitOneofString(z, this.id_, regionRegistration.id_);
                    } else if (i == 2) {
                        if (this.idCase_ == 2) {
                            z = true;
                        }
                        this.id_ = visitor.visitOneofString(z, this.id_, regionRegistration.id_);
                    } else if (i == 3) {
                        if (this.idCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == MergeFromVisitor.INSTANCE) {
                        int i2 = regionRegistration.idCase_;
                        if (i2 != 0) {
                            this.idCase_ = i2;
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
                                if (readTag == 10) {
                                    String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                    this.idCase_ = 1;
                                    this.id_ = readStringRequireUtf8;
                                } else if (readTag == 18) {
                                    String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                                    this.idCase_ = 2;
                                    this.id_ = readStringRequireUtf82;
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
                        synchronized (RegionRegistration.class) {
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

        public static RegionRegistration getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RegionRegistration> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface RegionRegistrationOrBuilder extends MessageLiteOrBuilder {
        String getCopayerId();

        ByteString getCopayerIdBytes();

        IdCase getIdCase();

        String getSingleSigWalletId();

        ByteString getSingleSigWalletIdBytes();
    }

    private RegisterRegionRequest() {
    }

    public List<RegionRegistration> getRegistrationList() {
        return this.registration_;
    }

    public List<? extends RegionRegistrationOrBuilder> getRegistrationOrBuilderList() {
        return this.registration_;
    }

    public int getRegistrationCount() {
        return this.registration_.size();
    }

    public RegionRegistration getRegistration(int i) {
        return (RegionRegistration) this.registration_.get(i);
    }

    public RegionRegistrationOrBuilder getRegistrationOrBuilder(int i) {
        return (RegionRegistrationOrBuilder) this.registration_.get(i);
    }

    private void ensureRegistrationIsMutable() {
        if (!this.registration_.isModifiable()) {
            this.registration_ = GeneratedMessageLite.mutableCopy(this.registration_);
        }
    }

    /* access modifiers changed from: private */
    public void setRegistration(int i, RegionRegistration regionRegistration) {
        if (regionRegistration != null) {
            ensureRegistrationIsMutable();
            this.registration_.set(i, regionRegistration);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setRegistration(int i, Builder builder) {
        ensureRegistrationIsMutable();
        this.registration_.set(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addRegistration(RegionRegistration regionRegistration) {
        if (regionRegistration != null) {
            ensureRegistrationIsMutable();
            this.registration_.add(regionRegistration);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addRegistration(int i, RegionRegistration regionRegistration) {
        if (regionRegistration != null) {
            ensureRegistrationIsMutable();
            this.registration_.add(i, regionRegistration);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addRegistration(Builder builder) {
        ensureRegistrationIsMutable();
        this.registration_.add(builder.build());
    }

    /* access modifiers changed from: private */
    public void addRegistration(int i, Builder builder) {
        ensureRegistrationIsMutable();
        this.registration_.add(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllRegistration(Iterable<? extends RegionRegistration> iterable) {
        ensureRegistrationIsMutable();
        AbstractMessageLite.addAll(iterable, this.registration_);
    }

    /* access modifiers changed from: private */
    public void clearRegistration() {
        this.registration_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeRegistration(int i) {
        ensureRegistrationIsMutable();
        this.registration_.remove(i);
    }

    public String getUserLanguage() {
        return this.userLanguage_;
    }

    public ByteString getUserLanguageBytes() {
        return ByteString.copyFromUtf8(this.userLanguage_);
    }

    /* access modifiers changed from: private */
    public void setUserLanguage(String str) {
        if (str != null) {
            this.userLanguage_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearUserLanguage() {
        this.userLanguage_ = getDefaultInstance().getUserLanguage();
    }

    /* access modifiers changed from: private */
    public void setUserLanguageBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.userLanguage_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.registration_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.registration_.get(i));
        }
        if (!this.userLanguage_.isEmpty()) {
            codedOutputStream.writeString(2, getUserLanguage());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.registration_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.registration_.get(i3));
        }
        if (!this.userLanguage_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getUserLanguage());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static RegisterRegionRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RegisterRegionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static RegisterRegionRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RegisterRegionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static RegisterRegionRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RegisterRegionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static RegisterRegionRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RegisterRegionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static RegisterRegionRequest parseFrom(InputStream inputStream) throws IOException {
        return (RegisterRegionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RegisterRegionRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RegisterRegionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RegisterRegionRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (RegisterRegionRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static RegisterRegionRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RegisterRegionRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static RegisterRegionRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (RegisterRegionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static RegisterRegionRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (RegisterRegionRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(RegisterRegionRequest registerRegionRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(registerRegionRequest);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new RegisterRegionRequest();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.registration_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                Visitor visitor = (Visitor) obj;
                RegisterRegionRequest registerRegionRequest = (RegisterRegionRequest) obj2;
                this.registration_ = visitor.visitList(this.registration_, registerRegionRequest.registration_);
                this.userLanguage_ = visitor.visitString(!this.userLanguage_.isEmpty(), this.userLanguage_, true ^ registerRegionRequest.userLanguage_.isEmpty(), registerRegionRequest.userLanguage_);
                if (visitor == MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= registerRegionRequest.bitField0_;
                }
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
                                if (!this.registration_.isModifiable()) {
                                    this.registration_ = GeneratedMessageLite.mutableCopy(this.registration_);
                                }
                                this.registration_.add(codedInputStream.readMessage(RegionRegistration.parser(), extensionRegistryLite));
                            } else if (readTag == 18) {
                                this.userLanguage_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (RegisterRegionRequest.class) {
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

    public static RegisterRegionRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RegisterRegionRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
