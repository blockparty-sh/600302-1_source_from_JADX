package org.bitcoinj.protocols.channels;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.BuilderParent;
import com.google.protobuf.GeneratedMessage.FieldAccessorTable;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ServerState {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    /* access modifiers changed from: private */

    /* renamed from: internal_static_paymentchannels_StoredServerPaymentChannel_descriptor */
    public static final Descriptor f813x43281413 = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    /* access modifiers changed from: private */

    /* renamed from: internal_static_paymentchannels_StoredServerPaymentChannel_fieldAccessorTable */
    public static FieldAccessorTable f814x18577791 = new FieldAccessorTable(f813x43281413, new String[]{"BestValueToMe", "BestValueSignature", "RefundTransactionUnlockTimeSecs", "ContractTransaction", "ClientOutput", "MyKey", "MajorVersion", "ClientKey"});
    /* access modifiers changed from: private */

    /* renamed from: internal_static_paymentchannels_StoredServerPaymentChannels_descriptor */
    public static final Descriptor f815x36c8e856 = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    /* access modifiers changed from: private */

    /* renamed from: internal_static_paymentchannels_StoredServerPaymentChannels_fieldAccessorTable */
    public static FieldAccessorTable f816xe5cd58d4 = new FieldAccessorTable(f815x36c8e856, new String[]{"Channels"});

    public static final class StoredServerPaymentChannel extends GeneratedMessage implements StoredServerPaymentChannelOrBuilder {
        public static final int BESTVALUESIGNATURE_FIELD_NUMBER = 2;
        public static final int BESTVALUETOME_FIELD_NUMBER = 1;
        public static final int CLIENTKEY_FIELD_NUMBER = 8;
        public static final int CLIENTOUTPUT_FIELD_NUMBER = 5;
        public static final int CONTRACTTRANSACTION_FIELD_NUMBER = 4;
        public static final int MAJORVERSION_FIELD_NUMBER = 7;
        public static final int MYKEY_FIELD_NUMBER = 6;
        public static Parser<StoredServerPaymentChannel> PARSER = new AbstractParser<StoredServerPaymentChannel>() {
            public StoredServerPaymentChannel parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new StoredServerPaymentChannel(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int REFUNDTRANSACTIONUNLOCKTIMESECS_FIELD_NUMBER = 3;
        private static final StoredServerPaymentChannel defaultInstance = new StoredServerPaymentChannel(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public ByteString bestValueSignature_;
        /* access modifiers changed from: private */
        public long bestValueToMe_;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ByteString clientKey_;
        /* access modifiers changed from: private */
        public ByteString clientOutput_;
        /* access modifiers changed from: private */
        public ByteString contractTransaction_;
        /* access modifiers changed from: private */
        public int majorVersion_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString myKey_;
        /* access modifiers changed from: private */
        public long refundTransactionUnlockTimeSecs_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements StoredServerPaymentChannelOrBuilder {
            private ByteString bestValueSignature_;
            private long bestValueToMe_;
            private int bitField0_;
            private ByteString clientKey_;
            private ByteString clientOutput_;
            private ByteString contractTransaction_;
            private int majorVersion_;
            private ByteString myKey_;
            private long refundTransactionUnlockTimeSecs_;

            public static final Descriptor getDescriptor() {
                return ServerState.f813x43281413;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return ServerState.f814x18577791.ensureFieldAccessorsInitialized(StoredServerPaymentChannel.class, Builder.class);
            }

            private Builder() {
                this.bestValueSignature_ = ByteString.EMPTY;
                this.contractTransaction_ = ByteString.EMPTY;
                this.clientOutput_ = ByteString.EMPTY;
                this.myKey_ = ByteString.EMPTY;
                this.majorVersion_ = 1;
                this.clientKey_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.bestValueSignature_ = ByteString.EMPTY;
                this.contractTransaction_ = ByteString.EMPTY;
                this.clientOutput_ = ByteString.EMPTY;
                this.myKey_ = ByteString.EMPTY;
                this.majorVersion_ = 1;
                this.clientKey_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                StoredServerPaymentChannel.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                StoredServerPaymentChannel.super.clear();
                this.bestValueToMe_ = 0;
                this.bitField0_ &= -2;
                this.bestValueSignature_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.refundTransactionUnlockTimeSecs_ = 0;
                this.bitField0_ &= -5;
                this.contractTransaction_ = ByteString.EMPTY;
                this.bitField0_ &= -9;
                this.clientOutput_ = ByteString.EMPTY;
                this.bitField0_ &= -17;
                this.myKey_ = ByteString.EMPTY;
                this.bitField0_ &= -33;
                this.majorVersion_ = 1;
                this.bitField0_ &= -65;
                this.clientKey_ = ByteString.EMPTY;
                this.bitField0_ &= -129;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return ServerState.f813x43281413;
            }

            public StoredServerPaymentChannel getDefaultInstanceForType() {
                return StoredServerPaymentChannel.getDefaultInstance();
            }

            public StoredServerPaymentChannel build() {
                StoredServerPaymentChannel buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public StoredServerPaymentChannel buildPartial() {
                StoredServerPaymentChannel storedServerPaymentChannel = new StoredServerPaymentChannel((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                storedServerPaymentChannel.bestValueToMe_ = this.bestValueToMe_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                storedServerPaymentChannel.bestValueSignature_ = this.bestValueSignature_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                storedServerPaymentChannel.refundTransactionUnlockTimeSecs_ = this.refundTransactionUnlockTimeSecs_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                storedServerPaymentChannel.contractTransaction_ = this.contractTransaction_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                storedServerPaymentChannel.clientOutput_ = this.clientOutput_;
                if ((i & 32) == 32) {
                    i2 |= 32;
                }
                storedServerPaymentChannel.myKey_ = this.myKey_;
                if ((i & 64) == 64) {
                    i2 |= 64;
                }
                storedServerPaymentChannel.majorVersion_ = this.majorVersion_;
                if ((i & 128) == 128) {
                    i2 |= 128;
                }
                storedServerPaymentChannel.clientKey_ = this.clientKey_;
                storedServerPaymentChannel.bitField0_ = i2;
                onBuilt();
                return storedServerPaymentChannel;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof StoredServerPaymentChannel) {
                    return mergeFrom((StoredServerPaymentChannel) message);
                }
                StoredServerPaymentChannel.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(StoredServerPaymentChannel storedServerPaymentChannel) {
                if (storedServerPaymentChannel == StoredServerPaymentChannel.getDefaultInstance()) {
                    return this;
                }
                if (storedServerPaymentChannel.hasBestValueToMe()) {
                    setBestValueToMe(storedServerPaymentChannel.getBestValueToMe());
                }
                if (storedServerPaymentChannel.hasBestValueSignature()) {
                    setBestValueSignature(storedServerPaymentChannel.getBestValueSignature());
                }
                if (storedServerPaymentChannel.hasRefundTransactionUnlockTimeSecs()) {
                    setRefundTransactionUnlockTimeSecs(storedServerPaymentChannel.getRefundTransactionUnlockTimeSecs());
                }
                if (storedServerPaymentChannel.hasContractTransaction()) {
                    setContractTransaction(storedServerPaymentChannel.getContractTransaction());
                }
                if (storedServerPaymentChannel.hasClientOutput()) {
                    setClientOutput(storedServerPaymentChannel.getClientOutput());
                }
                if (storedServerPaymentChannel.hasMyKey()) {
                    setMyKey(storedServerPaymentChannel.getMyKey());
                }
                if (storedServerPaymentChannel.hasMajorVersion()) {
                    setMajorVersion(storedServerPaymentChannel.getMajorVersion());
                }
                if (storedServerPaymentChannel.hasClientKey()) {
                    setClientKey(storedServerPaymentChannel.getClientKey());
                }
                mergeUnknownFields(storedServerPaymentChannel.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasBestValueToMe() && hasRefundTransactionUnlockTimeSecs() && hasContractTransaction() && hasMyKey()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                StoredServerPaymentChannel storedServerPaymentChannel;
                StoredServerPaymentChannel storedServerPaymentChannel2 = null;
                try {
                    StoredServerPaymentChannel storedServerPaymentChannel3 = (StoredServerPaymentChannel) StoredServerPaymentChannel.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (storedServerPaymentChannel3 != null) {
                        mergeFrom(storedServerPaymentChannel3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    storedServerPaymentChannel = (StoredServerPaymentChannel) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    storedServerPaymentChannel2 = storedServerPaymentChannel;
                }
                if (storedServerPaymentChannel2 != null) {
                    mergeFrom(storedServerPaymentChannel2);
                }
                throw th;
            }

            public boolean hasBestValueToMe() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getBestValueToMe() {
                return this.bestValueToMe_;
            }

            public Builder setBestValueToMe(long j) {
                this.bitField0_ |= 1;
                this.bestValueToMe_ = j;
                onChanged();
                return this;
            }

            public Builder clearBestValueToMe() {
                this.bitField0_ &= -2;
                this.bestValueToMe_ = 0;
                onChanged();
                return this;
            }

            public boolean hasBestValueSignature() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getBestValueSignature() {
                return this.bestValueSignature_;
            }

            public Builder setBestValueSignature(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.bestValueSignature_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearBestValueSignature() {
                this.bitField0_ &= -3;
                this.bestValueSignature_ = StoredServerPaymentChannel.getDefaultInstance().getBestValueSignature();
                onChanged();
                return this;
            }

            public boolean hasRefundTransactionUnlockTimeSecs() {
                return (this.bitField0_ & 4) == 4;
            }

            public long getRefundTransactionUnlockTimeSecs() {
                return this.refundTransactionUnlockTimeSecs_;
            }

            public Builder setRefundTransactionUnlockTimeSecs(long j) {
                this.bitField0_ |= 4;
                this.refundTransactionUnlockTimeSecs_ = j;
                onChanged();
                return this;
            }

            public Builder clearRefundTransactionUnlockTimeSecs() {
                this.bitField0_ &= -5;
                this.refundTransactionUnlockTimeSecs_ = 0;
                onChanged();
                return this;
            }

            public boolean hasContractTransaction() {
                return (this.bitField0_ & 8) == 8;
            }

            public ByteString getContractTransaction() {
                return this.contractTransaction_;
            }

            public Builder setContractTransaction(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 8;
                    this.contractTransaction_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearContractTransaction() {
                this.bitField0_ &= -9;
                this.contractTransaction_ = StoredServerPaymentChannel.getDefaultInstance().getContractTransaction();
                onChanged();
                return this;
            }

            public boolean hasClientOutput() {
                return (this.bitField0_ & 16) == 16;
            }

            public ByteString getClientOutput() {
                return this.clientOutput_;
            }

            public Builder setClientOutput(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 16;
                    this.clientOutput_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearClientOutput() {
                this.bitField0_ &= -17;
                this.clientOutput_ = StoredServerPaymentChannel.getDefaultInstance().getClientOutput();
                onChanged();
                return this;
            }

            public boolean hasMyKey() {
                return (this.bitField0_ & 32) == 32;
            }

            public ByteString getMyKey() {
                return this.myKey_;
            }

            public Builder setMyKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 32;
                    this.myKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearMyKey() {
                this.bitField0_ &= -33;
                this.myKey_ = StoredServerPaymentChannel.getDefaultInstance().getMyKey();
                onChanged();
                return this;
            }

            public boolean hasMajorVersion() {
                return (this.bitField0_ & 64) == 64;
            }

            public int getMajorVersion() {
                return this.majorVersion_;
            }

            public Builder setMajorVersion(int i) {
                this.bitField0_ |= 64;
                this.majorVersion_ = i;
                onChanged();
                return this;
            }

            public Builder clearMajorVersion() {
                this.bitField0_ &= -65;
                this.majorVersion_ = 1;
                onChanged();
                return this;
            }

            public boolean hasClientKey() {
                return (this.bitField0_ & 128) == 128;
            }

            public ByteString getClientKey() {
                return this.clientKey_;
            }

            public Builder setClientKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 128;
                    this.clientKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearClientKey() {
                this.bitField0_ &= -129;
                this.clientKey_ = StoredServerPaymentChannel.getDefaultInstance().getClientKey();
                onChanged();
                return this;
            }
        }

        private StoredServerPaymentChannel(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private StoredServerPaymentChannel(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static StoredServerPaymentChannel getDefaultInstance() {
            return defaultInstance;
        }

        public StoredServerPaymentChannel getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.protocols.channels.ServerState$StoredServerPaymentChannel] */
        private StoredServerPaymentChannel(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 8) {
                            this.bitField0_ |= 1;
                            this.bestValueToMe_ = codedInputStream.readUInt64();
                        } else if (readTag == 18) {
                            this.bitField0_ |= 2;
                            this.bestValueSignature_ = codedInputStream.readBytes();
                        } else if (readTag == 24) {
                            this.bitField0_ |= 4;
                            this.refundTransactionUnlockTimeSecs_ = codedInputStream.readUInt64();
                        } else if (readTag == 34) {
                            this.bitField0_ |= 8;
                            this.contractTransaction_ = codedInputStream.readBytes();
                        } else if (readTag == 42) {
                            this.bitField0_ |= 16;
                            this.clientOutput_ = codedInputStream.readBytes();
                        } else if (readTag == 50) {
                            this.bitField0_ |= 32;
                            this.myKey_ = codedInputStream.readBytes();
                        } else if (readTag == 56) {
                            this.bitField0_ |= 64;
                            this.majorVersion_ = codedInputStream.readUInt32();
                        } else if (readTag == 66) {
                            this.bitField0_ |= 128;
                            this.clientKey_ = codedInputStream.readBytes();
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return ServerState.f813x43281413;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ServerState.f814x18577791.ensureFieldAccessorsInitialized(StoredServerPaymentChannel.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<StoredServerPaymentChannel> getParserForType() {
            return PARSER;
        }

        public boolean hasBestValueToMe() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getBestValueToMe() {
            return this.bestValueToMe_;
        }

        public boolean hasBestValueSignature() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getBestValueSignature() {
            return this.bestValueSignature_;
        }

        public boolean hasRefundTransactionUnlockTimeSecs() {
            return (this.bitField0_ & 4) == 4;
        }

        public long getRefundTransactionUnlockTimeSecs() {
            return this.refundTransactionUnlockTimeSecs_;
        }

        public boolean hasContractTransaction() {
            return (this.bitField0_ & 8) == 8;
        }

        public ByteString getContractTransaction() {
            return this.contractTransaction_;
        }

        public boolean hasClientOutput() {
            return (this.bitField0_ & 16) == 16;
        }

        public ByteString getClientOutput() {
            return this.clientOutput_;
        }

        public boolean hasMyKey() {
            return (this.bitField0_ & 32) == 32;
        }

        public ByteString getMyKey() {
            return this.myKey_;
        }

        public boolean hasMajorVersion() {
            return (this.bitField0_ & 64) == 64;
        }

        public int getMajorVersion() {
            return this.majorVersion_;
        }

        public boolean hasClientKey() {
            return (this.bitField0_ & 128) == 128;
        }

        public ByteString getClientKey() {
            return this.clientKey_;
        }

        private void initFields() {
            this.bestValueToMe_ = 0;
            this.bestValueSignature_ = ByteString.EMPTY;
            this.refundTransactionUnlockTimeSecs_ = 0;
            this.contractTransaction_ = ByteString.EMPTY;
            this.clientOutput_ = ByteString.EMPTY;
            this.myKey_ = ByteString.EMPTY;
            this.majorVersion_ = 1;
            this.clientKey_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasBestValueToMe()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasRefundTransactionUnlockTimeSecs()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasContractTransaction()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasMyKey()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else {
                this.memoizedIsInitialized = 1;
                return true;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeUInt64(1, this.bestValueToMe_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.bestValueSignature_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeUInt64(3, this.refundTransactionUnlockTimeSecs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBytes(4, this.contractTransaction_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBytes(5, this.clientOutput_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeBytes(6, this.myKey_);
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeUInt32(7, this.majorVersion_);
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeBytes(8, this.clientKey_);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeUInt64Size(1, this.bestValueToMe_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.bestValueSignature_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeUInt64Size(3, this.refundTransactionUnlockTimeSecs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeBytesSize(4, this.contractTransaction_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeBytesSize(5, this.clientOutput_);
            }
            if ((this.bitField0_ & 32) == 32) {
                i2 += CodedOutputStream.computeBytesSize(6, this.myKey_);
            }
            if ((this.bitField0_ & 64) == 64) {
                i2 += CodedOutputStream.computeUInt32Size(7, this.majorVersion_);
            }
            if ((this.bitField0_ & 128) == 128) {
                i2 += CodedOutputStream.computeBytesSize(8, this.clientKey_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return ServerState.super.writeReplace();
        }

        public static StoredServerPaymentChannel parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StoredServerPaymentChannel) PARSER.parseFrom(byteString);
        }

        public static StoredServerPaymentChannel parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StoredServerPaymentChannel) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static StoredServerPaymentChannel parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StoredServerPaymentChannel) PARSER.parseFrom(bArr);
        }

        public static StoredServerPaymentChannel parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StoredServerPaymentChannel) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static StoredServerPaymentChannel parseFrom(InputStream inputStream) throws IOException {
            return (StoredServerPaymentChannel) PARSER.parseFrom(inputStream);
        }

        public static StoredServerPaymentChannel parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StoredServerPaymentChannel) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static StoredServerPaymentChannel parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StoredServerPaymentChannel) PARSER.parseDelimitedFrom(inputStream);
        }

        public static StoredServerPaymentChannel parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StoredServerPaymentChannel) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static StoredServerPaymentChannel parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StoredServerPaymentChannel) PARSER.parseFrom(codedInputStream);
        }

        public static StoredServerPaymentChannel parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StoredServerPaymentChannel) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(StoredServerPaymentChannel storedServerPaymentChannel) {
            return newBuilder().mergeFrom(storedServerPaymentChannel);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface StoredServerPaymentChannelOrBuilder extends MessageOrBuilder {
        ByteString getBestValueSignature();

        long getBestValueToMe();

        ByteString getClientKey();

        ByteString getClientOutput();

        ByteString getContractTransaction();

        int getMajorVersion();

        ByteString getMyKey();

        long getRefundTransactionUnlockTimeSecs();

        boolean hasBestValueSignature();

        boolean hasBestValueToMe();

        boolean hasClientKey();

        boolean hasClientOutput();

        boolean hasContractTransaction();

        boolean hasMajorVersion();

        boolean hasMyKey();

        boolean hasRefundTransactionUnlockTimeSecs();
    }

    public static final class StoredServerPaymentChannels extends GeneratedMessage implements StoredServerPaymentChannelsOrBuilder {
        public static final int CHANNELS_FIELD_NUMBER = 1;
        public static Parser<StoredServerPaymentChannels> PARSER = new AbstractParser<StoredServerPaymentChannels>() {
            public StoredServerPaymentChannels parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new StoredServerPaymentChannels(codedInputStream, extensionRegistryLite);
            }
        };
        private static final StoredServerPaymentChannels defaultInstance = new StoredServerPaymentChannels(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public List<StoredServerPaymentChannel> channels_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements StoredServerPaymentChannelsOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> channelsBuilder_;
            private List<StoredServerPaymentChannel> channels_;

            public static final Descriptor getDescriptor() {
                return ServerState.f815x36c8e856;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return ServerState.f816xe5cd58d4.ensureFieldAccessorsInitialized(StoredServerPaymentChannels.class, Builder.class);
            }

            private Builder() {
                this.channels_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.channels_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (StoredServerPaymentChannels.alwaysUseFieldBuilders) {
                    getChannelsFieldBuilder();
                }
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                StoredServerPaymentChannels.super.clear();
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.channels_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return ServerState.f815x36c8e856;
            }

            public StoredServerPaymentChannels getDefaultInstanceForType() {
                return StoredServerPaymentChannels.getDefaultInstance();
            }

            public StoredServerPaymentChannels build() {
                StoredServerPaymentChannels buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public StoredServerPaymentChannels buildPartial() {
                StoredServerPaymentChannels storedServerPaymentChannels = new StoredServerPaymentChannels((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    if ((i & 1) == 1) {
                        this.channels_ = Collections.unmodifiableList(this.channels_);
                        this.bitField0_ &= -2;
                    }
                    storedServerPaymentChannels.channels_ = this.channels_;
                } else {
                    storedServerPaymentChannels.channels_ = repeatedFieldBuilder.build();
                }
                onBuilt();
                return storedServerPaymentChannels;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof StoredServerPaymentChannels) {
                    return mergeFrom((StoredServerPaymentChannels) message);
                }
                StoredServerPaymentChannels.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(StoredServerPaymentChannels storedServerPaymentChannels) {
                if (storedServerPaymentChannels == StoredServerPaymentChannels.getDefaultInstance()) {
                    return this;
                }
                if (this.channelsBuilder_ == null) {
                    if (!storedServerPaymentChannels.channels_.isEmpty()) {
                        if (this.channels_.isEmpty()) {
                            this.channels_ = storedServerPaymentChannels.channels_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureChannelsIsMutable();
                            this.channels_.addAll(storedServerPaymentChannels.channels_);
                        }
                        onChanged();
                    }
                } else if (!storedServerPaymentChannels.channels_.isEmpty()) {
                    if (this.channelsBuilder_.isEmpty()) {
                        this.channelsBuilder_.dispose();
                        RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = null;
                        this.channelsBuilder_ = null;
                        this.channels_ = storedServerPaymentChannels.channels_;
                        this.bitField0_ &= -2;
                        if (StoredServerPaymentChannels.alwaysUseFieldBuilders) {
                            repeatedFieldBuilder = getChannelsFieldBuilder();
                        }
                        this.channelsBuilder_ = repeatedFieldBuilder;
                    } else {
                        this.channelsBuilder_.addAllMessages(storedServerPaymentChannels.channels_);
                    }
                }
                mergeUnknownFields(storedServerPaymentChannels.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getChannelsCount(); i++) {
                    if (!getChannels(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                StoredServerPaymentChannels storedServerPaymentChannels;
                StoredServerPaymentChannels storedServerPaymentChannels2 = null;
                try {
                    StoredServerPaymentChannels storedServerPaymentChannels3 = (StoredServerPaymentChannels) StoredServerPaymentChannels.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (storedServerPaymentChannels3 != null) {
                        mergeFrom(storedServerPaymentChannels3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    storedServerPaymentChannels = (StoredServerPaymentChannels) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    storedServerPaymentChannels2 = storedServerPaymentChannels;
                }
                if (storedServerPaymentChannels2 != null) {
                    mergeFrom(storedServerPaymentChannels2);
                }
                throw th;
            }

            private void ensureChannelsIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.channels_ = new ArrayList(this.channels_);
                    this.bitField0_ |= 1;
                }
            }

            public List<StoredServerPaymentChannel> getChannelsList() {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.channels_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getChannelsCount() {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.channels_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public StoredServerPaymentChannel getChannels(int i) {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (StoredServerPaymentChannel) this.channels_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setChannels(int i, StoredServerPaymentChannel storedServerPaymentChannel) {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, storedServerPaymentChannel);
                } else if (storedServerPaymentChannel != null) {
                    ensureChannelsIsMutable();
                    this.channels_.set(i, storedServerPaymentChannel);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setChannels(int i, Builder builder) {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureChannelsIsMutable();
                    this.channels_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addChannels(StoredServerPaymentChannel storedServerPaymentChannel) {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(storedServerPaymentChannel);
                } else if (storedServerPaymentChannel != null) {
                    ensureChannelsIsMutable();
                    this.channels_.add(storedServerPaymentChannel);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addChannels(int i, StoredServerPaymentChannel storedServerPaymentChannel) {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, storedServerPaymentChannel);
                } else if (storedServerPaymentChannel != null) {
                    ensureChannelsIsMutable();
                    this.channels_.add(i, storedServerPaymentChannel);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addChannels(Builder builder) {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureChannelsIsMutable();
                    this.channels_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builder.build());
                }
                return this;
            }

            public Builder addChannels(int i, Builder builder) {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureChannelsIsMutable();
                    this.channels_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllChannels(Iterable<? extends StoredServerPaymentChannel> iterable) {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureChannelsIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.channels_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearChannels() {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.channels_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeChannels(int i) {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureChannelsIsMutable();
                    this.channels_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(i);
                }
                return this;
            }

            public Builder getChannelsBuilder(int i) {
                return getChannelsFieldBuilder().getBuilder(i);
            }

            public StoredServerPaymentChannelOrBuilder getChannelsOrBuilder(int i) {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (StoredServerPaymentChannelOrBuilder) this.channels_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends StoredServerPaymentChannelOrBuilder> getChannelsOrBuilderList() {
                RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.channels_);
            }

            public Builder addChannelsBuilder() {
                return getChannelsFieldBuilder().addBuilder(StoredServerPaymentChannel.getDefaultInstance());
            }

            public Builder addChannelsBuilder(int i) {
                return getChannelsFieldBuilder().addBuilder(i, StoredServerPaymentChannel.getDefaultInstance());
            }

            public List<Builder> getChannelsBuilderList() {
                return getChannelsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<StoredServerPaymentChannel, Builder, StoredServerPaymentChannelOrBuilder> getChannelsFieldBuilder() {
                if (this.channelsBuilder_ == null) {
                    List<StoredServerPaymentChannel> list = this.channels_;
                    boolean z = true;
                    if ((this.bitField0_ & 1) != 1) {
                        z = false;
                    }
                    this.channelsBuilder_ = new RepeatedFieldBuilder<>(list, z, getParentForChildren(), isClean());
                    this.channels_ = null;
                }
                return this.channelsBuilder_;
            }
        }

        private StoredServerPaymentChannels(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private StoredServerPaymentChannels(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static StoredServerPaymentChannels getDefaultInstance() {
            return defaultInstance;
        }

        public StoredServerPaymentChannels getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.protocols.channels.ServerState$StoredServerPaymentChannels] */
        private StoredServerPaymentChannels(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            if (!z2 || !true) {
                                this.channels_ = new ArrayList();
                                z2 |= true;
                            }
                            this.channels_.add(codedInputStream.readMessage(StoredServerPaymentChannel.PARSER, extensionRegistryLite));
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 && true) {
                        this.channels_ = Collections.unmodifiableList(this.channels_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.channels_ = Collections.unmodifiableList(this.channels_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return ServerState.f815x36c8e856;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ServerState.f816xe5cd58d4.ensureFieldAccessorsInitialized(StoredServerPaymentChannels.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<StoredServerPaymentChannels> getParserForType() {
            return PARSER;
        }

        public List<StoredServerPaymentChannel> getChannelsList() {
            return this.channels_;
        }

        public List<? extends StoredServerPaymentChannelOrBuilder> getChannelsOrBuilderList() {
            return this.channels_;
        }

        public int getChannelsCount() {
            return this.channels_.size();
        }

        public StoredServerPaymentChannel getChannels(int i) {
            return (StoredServerPaymentChannel) this.channels_.get(i);
        }

        public StoredServerPaymentChannelOrBuilder getChannelsOrBuilder(int i) {
            return (StoredServerPaymentChannelOrBuilder) this.channels_.get(i);
        }

        private void initFields() {
            this.channels_ = Collections.emptyList();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getChannelsCount(); i++) {
                if (!getChannels(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            for (int i = 0; i < this.channels_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.channels_.get(i));
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.channels_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.channels_.get(i3));
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return ServerState.super.writeReplace();
        }

        public static StoredServerPaymentChannels parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StoredServerPaymentChannels) PARSER.parseFrom(byteString);
        }

        public static StoredServerPaymentChannels parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StoredServerPaymentChannels) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static StoredServerPaymentChannels parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StoredServerPaymentChannels) PARSER.parseFrom(bArr);
        }

        public static StoredServerPaymentChannels parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StoredServerPaymentChannels) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static StoredServerPaymentChannels parseFrom(InputStream inputStream) throws IOException {
            return (StoredServerPaymentChannels) PARSER.parseFrom(inputStream);
        }

        public static StoredServerPaymentChannels parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StoredServerPaymentChannels) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static StoredServerPaymentChannels parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StoredServerPaymentChannels) PARSER.parseDelimitedFrom(inputStream);
        }

        public static StoredServerPaymentChannels parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StoredServerPaymentChannels) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static StoredServerPaymentChannels parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StoredServerPaymentChannels) PARSER.parseFrom(codedInputStream);
        }

        public static StoredServerPaymentChannels parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StoredServerPaymentChannels) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(StoredServerPaymentChannels storedServerPaymentChannels) {
            return newBuilder().mergeFrom(storedServerPaymentChannels);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface StoredServerPaymentChannelsOrBuilder extends MessageOrBuilder {
        StoredServerPaymentChannel getChannels(int i);

        int getChannelsCount();

        List<StoredServerPaymentChannel> getChannelsList();

        StoredServerPaymentChannelOrBuilder getChannelsOrBuilder(int i);

        List<? extends StoredServerPaymentChannelOrBuilder> getChannelsOrBuilderList();
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
    }

    private ServerState() {
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n storedserverpaymentchannel.proto\u0012\u000fpaymentchannels\"\\\n\u001bStoredServerPaymentChannels\u0012=\n\bchannels\u0018\u0001 \u0003(\u000b2+.paymentchannels.StoredServerPaymentChannel\"æ\u0001\n\u001aStoredServerPaymentChannel\u0012\u0015\n\rbestValueToMe\u0018\u0001 \u0002(\u0004\u0012\u001a\n\u0012bestValueSignature\u0018\u0002 \u0001(\f\u0012'\n\u001frefundTransactionUnlockTimeSecs\u0018\u0003 \u0002(\u0004\u0012\u001b\n\u0013contractTransaction\u0018\u0004 \u0002(\f\u0012\u0014\n\fclientOutput\u0018\u0005 \u0001(\f\u0012\r\n\u0005myKey\u0018\u0006 \u0002(\f\u0012\u0017\n\fmajorVersion\u0018\u0007 \u0001(\r:\u00011\u0012\u0011\n\tclientKey\u0018\b \u0001(\fB.\n\u001forg.bitcoinj.proto", "cols.channelsB\u000bServerState"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                ServerState.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
