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

public final class GetUtxosResponse extends GeneratedMessageLite<GetUtxosResponse, Builder> implements GetUtxosResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final GetUtxosResponse DEFAULT_INSTANCE = new GetUtxosResponse();
    public static final int ERROR_FIELD_NUMBER = 2;
    private static volatile Parser<GetUtxosResponse> PARSER = null;
    public static final int UTXOS_FIELD_NUMBER = 1;
    private int responseCase_ = 0;
    private Object response_;

    /* renamed from: com.bitcoin.mwallet.GetUtxosResponse$1 */
    static /* synthetic */ class C09301 {
        static final /* synthetic */ int[] $SwitchMap$com$bitcoin$mwallet$GetUtxosResponse$ResponseCase = new int[ResponseCase.values().length];

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
                com.bitcoin.mwallet.GetUtxosResponse$ResponseCase[] r0 = com.bitcoin.mwallet.GetUtxosResponse.ResponseCase.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$bitcoin$mwallet$GetUtxosResponse$ResponseCase = r0
                r0 = 1
                int[] r1 = $SwitchMap$com$bitcoin$mwallet$GetUtxosResponse$ResponseCase     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.bitcoin.mwallet.GetUtxosResponse$ResponseCase r2 = com.bitcoin.mwallet.GetUtxosResponse.ResponseCase.UTXOS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = $SwitchMap$com$bitcoin$mwallet$GetUtxosResponse$ResponseCase     // Catch:{ NoSuchFieldError -> 0x001f }
                com.bitcoin.mwallet.GetUtxosResponse$ResponseCase r3 = com.bitcoin.mwallet.GetUtxosResponse.ResponseCase.ERROR     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = $SwitchMap$com$bitcoin$mwallet$GetUtxosResponse$ResponseCase     // Catch:{ NoSuchFieldError -> 0x002a }
                com.bitcoin.mwallet.GetUtxosResponse$ResponseCase r4 = com.bitcoin.mwallet.GetUtxosResponse.ResponseCase.RESPONSE_NOT_SET     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                f149xa1df5c61 = r3
                int[] r3 = f149xa1df5c61     // Catch:{ NoSuchFieldError -> 0x003d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x003d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                int[] r0 = f149xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0047 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0047 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0047 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0047 }
            L_0x0047:
                int[] r0 = f149xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0051 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x0051 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0051 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0051 }
            L_0x0051:
                int[] r0 = f149xa1df5c61     // Catch:{ NoSuchFieldError -> 0x005c }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x005c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                int[] r0 = f149xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0067 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0067 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0067 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0067 }
            L_0x0067:
                int[] r0 = f149xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0072 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x0072 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0072 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0072 }
            L_0x0072:
                int[] r0 = f149xa1df5c61     // Catch:{ NoSuchFieldError -> 0x007d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x007d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007d }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007d }
            L_0x007d:
                int[] r0 = f149xa1df5c61     // Catch:{ NoSuchFieldError -> 0x0089 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0089 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0089 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0089 }
            L_0x0089:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.GetUtxosResponse.C09301.<clinit>():void");
        }
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<GetUtxosResponse, Builder> implements GetUtxosResponseOrBuilder {
        /* synthetic */ Builder(C09301 r1) {
            this();
        }

        private Builder() {
            super(GetUtxosResponse.DEFAULT_INSTANCE);
        }

        public ResponseCase getResponseCase() {
            return ((GetUtxosResponse) this.instance).getResponseCase();
        }

        public Builder clearResponse() {
            copyOnWrite();
            ((GetUtxosResponse) this.instance).clearResponse();
            return this;
        }

        public WalletUtxosList getUtxos() {
            return ((GetUtxosResponse) this.instance).getUtxos();
        }

        public Builder setUtxos(WalletUtxosList walletUtxosList) {
            copyOnWrite();
            ((GetUtxosResponse) this.instance).setUtxos(walletUtxosList);
            return this;
        }

        public Builder setUtxos(Builder builder) {
            copyOnWrite();
            ((GetUtxosResponse) this.instance).setUtxos(builder);
            return this;
        }

        public Builder mergeUtxos(WalletUtxosList walletUtxosList) {
            copyOnWrite();
            ((GetUtxosResponse) this.instance).mergeUtxos(walletUtxosList);
            return this;
        }

        public Builder clearUtxos() {
            copyOnWrite();
            ((GetUtxosResponse) this.instance).clearUtxos();
            return this;
        }

        public ResponseError getError() {
            return ((GetUtxosResponse) this.instance).getError();
        }

        public Builder setError(ResponseError responseError) {
            copyOnWrite();
            ((GetUtxosResponse) this.instance).setError(responseError);
            return this;
        }

        public Builder setError(com.bitcoin.mwallet.ResponseError.Builder builder) {
            copyOnWrite();
            ((GetUtxosResponse) this.instance).setError(builder);
            return this;
        }

        public Builder mergeError(ResponseError responseError) {
            copyOnWrite();
            ((GetUtxosResponse) this.instance).mergeError(responseError);
            return this;
        }

        public Builder clearError() {
            copyOnWrite();
            ((GetUtxosResponse) this.instance).clearError();
            return this;
        }
    }

    public enum ResponseCase implements EnumLite {
        UTXOS(1),
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
                return UTXOS;
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

    public static final class WalletUtxosList extends GeneratedMessageLite<WalletUtxosList, Builder> implements WalletUtxosListOrBuilder {
        /* access modifiers changed from: private */
        public static final WalletUtxosList DEFAULT_INSTANCE = new WalletUtxosList();
        private static volatile Parser<WalletUtxosList> PARSER = null;
        public static final int UTXOS_FIELD_NUMBER = 1;
        private ProtobufList<WalletUtxos> utxos_ = emptyProtobufList();

        public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<WalletUtxosList, Builder> implements WalletUtxosListOrBuilder {
            /* synthetic */ Builder(C09301 r1) {
                this();
            }

            private Builder() {
                super(WalletUtxosList.DEFAULT_INSTANCE);
            }

            public List<WalletUtxos> getUtxosList() {
                return Collections.unmodifiableList(((WalletUtxosList) this.instance).getUtxosList());
            }

            public int getUtxosCount() {
                return ((WalletUtxosList) this.instance).getUtxosCount();
            }

            public WalletUtxos getUtxos(int i) {
                return ((WalletUtxosList) this.instance).getUtxos(i);
            }

            public Builder setUtxos(int i, WalletUtxos walletUtxos) {
                copyOnWrite();
                ((WalletUtxosList) this.instance).setUtxos(i, walletUtxos);
                return this;
            }

            public Builder setUtxos(int i, com.bitcoin.mwallet.WalletUtxos.Builder builder) {
                copyOnWrite();
                ((WalletUtxosList) this.instance).setUtxos(i, builder);
                return this;
            }

            public Builder addUtxos(WalletUtxos walletUtxos) {
                copyOnWrite();
                ((WalletUtxosList) this.instance).addUtxos(walletUtxos);
                return this;
            }

            public Builder addUtxos(int i, WalletUtxos walletUtxos) {
                copyOnWrite();
                ((WalletUtxosList) this.instance).addUtxos(i, walletUtxos);
                return this;
            }

            public Builder addUtxos(com.bitcoin.mwallet.WalletUtxos.Builder builder) {
                copyOnWrite();
                ((WalletUtxosList) this.instance).addUtxos(builder);
                return this;
            }

            public Builder addUtxos(int i, com.bitcoin.mwallet.WalletUtxos.Builder builder) {
                copyOnWrite();
                ((WalletUtxosList) this.instance).addUtxos(i, builder);
                return this;
            }

            public Builder addAllUtxos(Iterable<? extends WalletUtxos> iterable) {
                copyOnWrite();
                ((WalletUtxosList) this.instance).addAllUtxos(iterable);
                return this;
            }

            public Builder clearUtxos() {
                copyOnWrite();
                ((WalletUtxosList) this.instance).clearUtxos();
                return this;
            }

            public Builder removeUtxos(int i) {
                copyOnWrite();
                ((WalletUtxosList) this.instance).removeUtxos(i);
                return this;
            }
        }

        private WalletUtxosList() {
        }

        public List<WalletUtxos> getUtxosList() {
            return this.utxos_;
        }

        public List<? extends WalletUtxosOrBuilder> getUtxosOrBuilderList() {
            return this.utxos_;
        }

        public int getUtxosCount() {
            return this.utxos_.size();
        }

        public WalletUtxos getUtxos(int i) {
            return (WalletUtxos) this.utxos_.get(i);
        }

        public WalletUtxosOrBuilder getUtxosOrBuilder(int i) {
            return (WalletUtxosOrBuilder) this.utxos_.get(i);
        }

        private void ensureUtxosIsMutable() {
            if (!this.utxos_.isModifiable()) {
                this.utxos_ = GeneratedMessageLite.mutableCopy(this.utxos_);
            }
        }

        /* access modifiers changed from: private */
        public void setUtxos(int i, WalletUtxos walletUtxos) {
            if (walletUtxos != null) {
                ensureUtxosIsMutable();
                this.utxos_.set(i, walletUtxos);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void setUtxos(int i, com.bitcoin.mwallet.WalletUtxos.Builder builder) {
            ensureUtxosIsMutable();
            this.utxos_.set(i, builder.build());
        }

        /* access modifiers changed from: private */
        public void addUtxos(WalletUtxos walletUtxos) {
            if (walletUtxos != null) {
                ensureUtxosIsMutable();
                this.utxos_.add(walletUtxos);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void addUtxos(int i, WalletUtxos walletUtxos) {
            if (walletUtxos != null) {
                ensureUtxosIsMutable();
                this.utxos_.add(i, walletUtxos);
                return;
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public void addUtxos(com.bitcoin.mwallet.WalletUtxos.Builder builder) {
            ensureUtxosIsMutable();
            this.utxos_.add(builder.build());
        }

        /* access modifiers changed from: private */
        public void addUtxos(int i, com.bitcoin.mwallet.WalletUtxos.Builder builder) {
            ensureUtxosIsMutable();
            this.utxos_.add(i, builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllUtxos(Iterable<? extends WalletUtxos> iterable) {
            ensureUtxosIsMutable();
            AbstractMessageLite.addAll(iterable, this.utxos_);
        }

        /* access modifiers changed from: private */
        public void clearUtxos() {
            this.utxos_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeUtxos(int i) {
            ensureUtxosIsMutable();
            this.utxos_.remove(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.utxos_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.utxos_.get(i));
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.utxos_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.utxos_.get(i3));
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static WalletUtxosList parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (WalletUtxosList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static WalletUtxosList parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (WalletUtxosList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static WalletUtxosList parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (WalletUtxosList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static WalletUtxosList parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (WalletUtxosList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static WalletUtxosList parseFrom(InputStream inputStream) throws IOException {
            return (WalletUtxosList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static WalletUtxosList parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WalletUtxosList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static WalletUtxosList parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (WalletUtxosList) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static WalletUtxosList parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WalletUtxosList) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static WalletUtxosList parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (WalletUtxosList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static WalletUtxosList parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (WalletUtxosList) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(WalletUtxosList walletUtxosList) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(walletUtxosList);
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (methodToInvoke) {
                case NEW_MUTABLE_INSTANCE:
                    return new WalletUtxosList();
                case IS_INITIALIZED:
                    return DEFAULT_INSTANCE;
                case MAKE_IMMUTABLE:
                    this.utxos_.makeImmutable();
                    return null;
                case NEW_BUILDER:
                    return new Builder(null);
                case VISIT:
                    this.utxos_ = ((Visitor) obj).visitList(this.utxos_, ((WalletUtxosList) obj2).utxos_);
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
                                    if (!this.utxos_.isModifiable()) {
                                        this.utxos_ = GeneratedMessageLite.mutableCopy(this.utxos_);
                                    }
                                    this.utxos_.add(codedInputStream.readMessage(WalletUtxos.parser(), extensionRegistryLite));
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
                        synchronized (WalletUtxosList.class) {
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

        public static WalletUtxosList getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<WalletUtxosList> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    public interface WalletUtxosListOrBuilder extends MessageLiteOrBuilder {
        WalletUtxos getUtxos(int i);

        int getUtxosCount();

        List<WalletUtxos> getUtxosList();
    }

    private GetUtxosResponse() {
    }

    public ResponseCase getResponseCase() {
        return ResponseCase.forNumber(this.responseCase_);
    }

    /* access modifiers changed from: private */
    public void clearResponse() {
        this.responseCase_ = 0;
        this.response_ = null;
    }

    public WalletUtxosList getUtxos() {
        if (this.responseCase_ == 1) {
            return (WalletUtxosList) this.response_;
        }
        return WalletUtxosList.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setUtxos(WalletUtxosList walletUtxosList) {
        if (walletUtxosList != null) {
            this.response_ = walletUtxosList;
            this.responseCase_ = 1;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setUtxos(Builder builder) {
        this.response_ = builder.build();
        this.responseCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void mergeUtxos(WalletUtxosList walletUtxosList) {
        if (this.responseCase_ != 1 || this.response_ == WalletUtxosList.getDefaultInstance()) {
            this.response_ = walletUtxosList;
        } else {
            this.response_ = ((Builder) WalletUtxosList.newBuilder((WalletUtxosList) this.response_).mergeFrom(walletUtxosList)).buildPartial();
        }
        this.responseCase_ = 1;
    }

    /* access modifiers changed from: private */
    public void clearUtxos() {
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
            codedOutputStream.writeMessage(1, (WalletUtxosList) this.response_);
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
            i2 = 0 + CodedOutputStream.computeMessageSize(1, (WalletUtxosList) this.response_);
        }
        if (this.responseCase_ == 2) {
            i2 += CodedOutputStream.computeMessageSize(2, (ResponseError) this.response_);
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static GetUtxosResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (GetUtxosResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static GetUtxosResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetUtxosResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static GetUtxosResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (GetUtxosResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static GetUtxosResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GetUtxosResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static GetUtxosResponse parseFrom(InputStream inputStream) throws IOException {
        return (GetUtxosResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetUtxosResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetUtxosResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetUtxosResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (GetUtxosResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static GetUtxosResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetUtxosResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static GetUtxosResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (GetUtxosResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static GetUtxosResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (GetUtxosResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GetUtxosResponse getUtxosResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(getUtxosResponse);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new GetUtxosResponse();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                return null;
            case NEW_BUILDER:
                return new Builder(null);
            case VISIT:
                Visitor visitor = (Visitor) obj;
                GetUtxosResponse getUtxosResponse = (GetUtxosResponse) obj2;
                int i = C09301.$SwitchMap$com$bitcoin$mwallet$GetUtxosResponse$ResponseCase[getUtxosResponse.getResponseCase().ordinal()];
                if (i == 1) {
                    if (this.responseCase_ == 1) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofMessage(z, this.response_, getUtxosResponse.response_);
                } else if (i == 2) {
                    if (this.responseCase_ == 2) {
                        z = true;
                    }
                    this.response_ = visitor.visitOneofMessage(z, this.response_, getUtxosResponse.response_);
                } else if (i == 3) {
                    if (this.responseCase_ != 0) {
                        z = true;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == MergeFromVisitor.INSTANCE) {
                    int i2 = getUtxosResponse.responseCase_;
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
                            if (readTag == 10) {
                                Builder builder = this.responseCase_ == 1 ? (Builder) ((WalletUtxosList) this.response_).toBuilder() : null;
                                this.response_ = codedInputStream.readMessage(WalletUtxosList.parser(), extensionRegistryLite);
                                if (builder != null) {
                                    builder.mergeFrom((WalletUtxosList) this.response_);
                                    this.response_ = builder.buildPartial();
                                }
                                this.responseCase_ = 1;
                            } else if (readTag == 18) {
                                com.bitcoin.mwallet.ResponseError.Builder builder2 = this.responseCase_ == 2 ? (com.bitcoin.mwallet.ResponseError.Builder) ((ResponseError) this.response_).toBuilder() : null;
                                this.response_ = codedInputStream.readMessage(ResponseError.parser(), extensionRegistryLite);
                                if (builder2 != null) {
                                    builder2.mergeFrom((ResponseError) this.response_);
                                    this.response_ = builder2.buildPartial();
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
                    synchronized (GetUtxosResponse.class) {
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

    public static GetUtxosResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GetUtxosResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
