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
import com.htc.htcwalletsdk.Export.RESULT;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ClientState {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    /* access modifiers changed from: private */

    /* renamed from: internal_static_paymentchannels_StoredClientPaymentChannel_descriptor */
    public static final Descriptor f806x8f2ff88b = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    /* access modifiers changed from: private */

    /* renamed from: internal_static_paymentchannels_StoredClientPaymentChannel_fieldAccessorTable */
    public static FieldAccessorTable f807xe04f6409 = new FieldAccessorTable(f806x8f2ff88b, new String[]{"Id", "ContractTransaction", "RefundTransaction", "MyPublicKey", "MyKey", "ValueToMe", "RefundFees", "CloseTransactionHash", "MajorVersion", "ExpiryTime", "ServerKey"});
    /* access modifiers changed from: private */

    /* renamed from: internal_static_paymentchannels_StoredClientPaymentChannels_descriptor */
    public static final Descriptor f808x6bbd92de = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    /* access modifiers changed from: private */

    /* renamed from: internal_static_paymentchannels_StoredClientPaymentChannels_fieldAccessorTable */
    public static FieldAccessorTable f809x1cd2fb5c = new FieldAccessorTable(f808x6bbd92de, new String[]{"Channels"});

    public static final class StoredClientPaymentChannel extends GeneratedMessage implements StoredClientPaymentChannelOrBuilder {
        public static final int CLOSETRANSACTIONHASH_FIELD_NUMBER = 7;
        public static final int CONTRACTTRANSACTION_FIELD_NUMBER = 2;
        public static final int EXPIRYTIME_FIELD_NUMBER = 10;
        public static final int ID_FIELD_NUMBER = 1;
        public static final int MAJORVERSION_FIELD_NUMBER = 9;
        public static final int MYKEY_FIELD_NUMBER = 4;
        public static final int MYPUBLICKEY_FIELD_NUMBER = 8;
        public static Parser<StoredClientPaymentChannel> PARSER = new AbstractParser<StoredClientPaymentChannel>() {
            public StoredClientPaymentChannel parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new StoredClientPaymentChannel(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int REFUNDFEES_FIELD_NUMBER = 6;
        public static final int REFUNDTRANSACTION_FIELD_NUMBER = 3;
        public static final int SERVERKEY_FIELD_NUMBER = 11;
        public static final int VALUETOME_FIELD_NUMBER = 5;
        private static final StoredClientPaymentChannel defaultInstance = new StoredClientPaymentChannel(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ByteString closeTransactionHash_;
        /* access modifiers changed from: private */
        public ByteString contractTransaction_;
        /* access modifiers changed from: private */
        public long expiryTime_;
        /* access modifiers changed from: private */
        public ByteString id_;
        /* access modifiers changed from: private */
        public int majorVersion_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString myKey_;
        /* access modifiers changed from: private */
        public ByteString myPublicKey_;
        /* access modifiers changed from: private */
        public long refundFees_;
        /* access modifiers changed from: private */
        public ByteString refundTransaction_;
        /* access modifiers changed from: private */
        public ByteString serverKey_;
        private final UnknownFieldSet unknownFields;
        /* access modifiers changed from: private */
        public long valueToMe_;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements StoredClientPaymentChannelOrBuilder {
            private int bitField0_;
            private ByteString closeTransactionHash_;
            private ByteString contractTransaction_;
            private long expiryTime_;
            private ByteString id_;
            private int majorVersion_;
            private ByteString myKey_;
            private ByteString myPublicKey_;
            private long refundFees_;
            private ByteString refundTransaction_;
            private ByteString serverKey_;
            private long valueToMe_;

            public static final Descriptor getDescriptor() {
                return ClientState.f806x8f2ff88b;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return ClientState.f807xe04f6409.ensureFieldAccessorsInitialized(StoredClientPaymentChannel.class, Builder.class);
            }

            private Builder() {
                this.id_ = ByteString.EMPTY;
                this.contractTransaction_ = ByteString.EMPTY;
                this.refundTransaction_ = ByteString.EMPTY;
                this.myPublicKey_ = ByteString.EMPTY;
                this.myKey_ = ByteString.EMPTY;
                this.closeTransactionHash_ = ByteString.EMPTY;
                this.majorVersion_ = 1;
                this.serverKey_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.id_ = ByteString.EMPTY;
                this.contractTransaction_ = ByteString.EMPTY;
                this.refundTransaction_ = ByteString.EMPTY;
                this.myPublicKey_ = ByteString.EMPTY;
                this.myKey_ = ByteString.EMPTY;
                this.closeTransactionHash_ = ByteString.EMPTY;
                this.majorVersion_ = 1;
                this.serverKey_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                StoredClientPaymentChannel.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                StoredClientPaymentChannel.super.clear();
                this.id_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.contractTransaction_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.refundTransaction_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                this.myPublicKey_ = ByteString.EMPTY;
                this.bitField0_ &= -9;
                this.myKey_ = ByteString.EMPTY;
                this.bitField0_ &= -17;
                this.valueToMe_ = 0;
                this.bitField0_ &= -33;
                this.refundFees_ = 0;
                this.bitField0_ &= -65;
                this.closeTransactionHash_ = ByteString.EMPTY;
                this.bitField0_ &= -129;
                this.majorVersion_ = 1;
                this.bitField0_ &= -257;
                this.expiryTime_ = 0;
                this.bitField0_ &= -513;
                this.serverKey_ = ByteString.EMPTY;
                this.bitField0_ &= RESULT.E_TEEKM_JSON_OUT_ADDR_LEN;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return ClientState.f806x8f2ff88b;
            }

            public StoredClientPaymentChannel getDefaultInstanceForType() {
                return StoredClientPaymentChannel.getDefaultInstance();
            }

            public StoredClientPaymentChannel build() {
                StoredClientPaymentChannel buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public StoredClientPaymentChannel buildPartial() {
                StoredClientPaymentChannel storedClientPaymentChannel = new StoredClientPaymentChannel((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                storedClientPaymentChannel.id_ = this.id_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                storedClientPaymentChannel.contractTransaction_ = this.contractTransaction_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                storedClientPaymentChannel.refundTransaction_ = this.refundTransaction_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                storedClientPaymentChannel.myPublicKey_ = this.myPublicKey_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                storedClientPaymentChannel.myKey_ = this.myKey_;
                if ((i & 32) == 32) {
                    i2 |= 32;
                }
                storedClientPaymentChannel.valueToMe_ = this.valueToMe_;
                if ((i & 64) == 64) {
                    i2 |= 64;
                }
                storedClientPaymentChannel.refundFees_ = this.refundFees_;
                if ((i & 128) == 128) {
                    i2 |= 128;
                }
                storedClientPaymentChannel.closeTransactionHash_ = this.closeTransactionHash_;
                if ((i & 256) == 256) {
                    i2 |= 256;
                }
                storedClientPaymentChannel.majorVersion_ = this.majorVersion_;
                if ((i & 512) == 512) {
                    i2 |= 512;
                }
                storedClientPaymentChannel.expiryTime_ = this.expiryTime_;
                if ((i & 1024) == 1024) {
                    i2 |= 1024;
                }
                storedClientPaymentChannel.serverKey_ = this.serverKey_;
                storedClientPaymentChannel.bitField0_ = i2;
                onBuilt();
                return storedClientPaymentChannel;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof StoredClientPaymentChannel) {
                    return mergeFrom((StoredClientPaymentChannel) message);
                }
                StoredClientPaymentChannel.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(StoredClientPaymentChannel storedClientPaymentChannel) {
                if (storedClientPaymentChannel == StoredClientPaymentChannel.getDefaultInstance()) {
                    return this;
                }
                if (storedClientPaymentChannel.hasId()) {
                    setId(storedClientPaymentChannel.getId());
                }
                if (storedClientPaymentChannel.hasContractTransaction()) {
                    setContractTransaction(storedClientPaymentChannel.getContractTransaction());
                }
                if (storedClientPaymentChannel.hasRefundTransaction()) {
                    setRefundTransaction(storedClientPaymentChannel.getRefundTransaction());
                }
                if (storedClientPaymentChannel.hasMyPublicKey()) {
                    setMyPublicKey(storedClientPaymentChannel.getMyPublicKey());
                }
                if (storedClientPaymentChannel.hasMyKey()) {
                    setMyKey(storedClientPaymentChannel.getMyKey());
                }
                if (storedClientPaymentChannel.hasValueToMe()) {
                    setValueToMe(storedClientPaymentChannel.getValueToMe());
                }
                if (storedClientPaymentChannel.hasRefundFees()) {
                    setRefundFees(storedClientPaymentChannel.getRefundFees());
                }
                if (storedClientPaymentChannel.hasCloseTransactionHash()) {
                    setCloseTransactionHash(storedClientPaymentChannel.getCloseTransactionHash());
                }
                if (storedClientPaymentChannel.hasMajorVersion()) {
                    setMajorVersion(storedClientPaymentChannel.getMajorVersion());
                }
                if (storedClientPaymentChannel.hasExpiryTime()) {
                    setExpiryTime(storedClientPaymentChannel.getExpiryTime());
                }
                if (storedClientPaymentChannel.hasServerKey()) {
                    setServerKey(storedClientPaymentChannel.getServerKey());
                }
                mergeUnknownFields(storedClientPaymentChannel.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasId() && hasContractTransaction() && hasRefundTransaction() && hasMyPublicKey() && hasMyKey() && hasValueToMe() && hasRefundFees()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                StoredClientPaymentChannel storedClientPaymentChannel;
                StoredClientPaymentChannel storedClientPaymentChannel2 = null;
                try {
                    StoredClientPaymentChannel storedClientPaymentChannel3 = (StoredClientPaymentChannel) StoredClientPaymentChannel.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (storedClientPaymentChannel3 != null) {
                        mergeFrom(storedClientPaymentChannel3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    storedClientPaymentChannel = (StoredClientPaymentChannel) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    storedClientPaymentChannel2 = storedClientPaymentChannel;
                }
                if (storedClientPaymentChannel2 != null) {
                    mergeFrom(storedClientPaymentChannel2);
                }
                throw th;
            }

            public boolean hasId() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getId() {
                return this.id_;
            }

            public Builder setId(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.id_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearId() {
                this.bitField0_ &= -2;
                this.id_ = StoredClientPaymentChannel.getDefaultInstance().getId();
                onChanged();
                return this;
            }

            public boolean hasContractTransaction() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getContractTransaction() {
                return this.contractTransaction_;
            }

            public Builder setContractTransaction(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.contractTransaction_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearContractTransaction() {
                this.bitField0_ &= -3;
                this.contractTransaction_ = StoredClientPaymentChannel.getDefaultInstance().getContractTransaction();
                onChanged();
                return this;
            }

            public boolean hasRefundTransaction() {
                return (this.bitField0_ & 4) == 4;
            }

            public ByteString getRefundTransaction() {
                return this.refundTransaction_;
            }

            public Builder setRefundTransaction(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.refundTransaction_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearRefundTransaction() {
                this.bitField0_ &= -5;
                this.refundTransaction_ = StoredClientPaymentChannel.getDefaultInstance().getRefundTransaction();
                onChanged();
                return this;
            }

            public boolean hasMyPublicKey() {
                return (this.bitField0_ & 8) == 8;
            }

            public ByteString getMyPublicKey() {
                return this.myPublicKey_;
            }

            public Builder setMyPublicKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 8;
                    this.myPublicKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearMyPublicKey() {
                this.bitField0_ &= -9;
                this.myPublicKey_ = StoredClientPaymentChannel.getDefaultInstance().getMyPublicKey();
                onChanged();
                return this;
            }

            public boolean hasMyKey() {
                return (this.bitField0_ & 16) == 16;
            }

            public ByteString getMyKey() {
                return this.myKey_;
            }

            public Builder setMyKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 16;
                    this.myKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearMyKey() {
                this.bitField0_ &= -17;
                this.myKey_ = StoredClientPaymentChannel.getDefaultInstance().getMyKey();
                onChanged();
                return this;
            }

            public boolean hasValueToMe() {
                return (this.bitField0_ & 32) == 32;
            }

            public long getValueToMe() {
                return this.valueToMe_;
            }

            public Builder setValueToMe(long j) {
                this.bitField0_ |= 32;
                this.valueToMe_ = j;
                onChanged();
                return this;
            }

            public Builder clearValueToMe() {
                this.bitField0_ &= -33;
                this.valueToMe_ = 0;
                onChanged();
                return this;
            }

            public boolean hasRefundFees() {
                return (this.bitField0_ & 64) == 64;
            }

            public long getRefundFees() {
                return this.refundFees_;
            }

            public Builder setRefundFees(long j) {
                this.bitField0_ |= 64;
                this.refundFees_ = j;
                onChanged();
                return this;
            }

            public Builder clearRefundFees() {
                this.bitField0_ &= -65;
                this.refundFees_ = 0;
                onChanged();
                return this;
            }

            public boolean hasCloseTransactionHash() {
                return (this.bitField0_ & 128) == 128;
            }

            public ByteString getCloseTransactionHash() {
                return this.closeTransactionHash_;
            }

            public Builder setCloseTransactionHash(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 128;
                    this.closeTransactionHash_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearCloseTransactionHash() {
                this.bitField0_ &= -129;
                this.closeTransactionHash_ = StoredClientPaymentChannel.getDefaultInstance().getCloseTransactionHash();
                onChanged();
                return this;
            }

            public boolean hasMajorVersion() {
                return (this.bitField0_ & 256) == 256;
            }

            public int getMajorVersion() {
                return this.majorVersion_;
            }

            public Builder setMajorVersion(int i) {
                this.bitField0_ |= 256;
                this.majorVersion_ = i;
                onChanged();
                return this;
            }

            public Builder clearMajorVersion() {
                this.bitField0_ &= -257;
                this.majorVersion_ = 1;
                onChanged();
                return this;
            }

            public boolean hasExpiryTime() {
                return (this.bitField0_ & 512) == 512;
            }

            public long getExpiryTime() {
                return this.expiryTime_;
            }

            public Builder setExpiryTime(long j) {
                this.bitField0_ |= 512;
                this.expiryTime_ = j;
                onChanged();
                return this;
            }

            public Builder clearExpiryTime() {
                this.bitField0_ &= -513;
                this.expiryTime_ = 0;
                onChanged();
                return this;
            }

            public boolean hasServerKey() {
                return (this.bitField0_ & 1024) == 1024;
            }

            public ByteString getServerKey() {
                return this.serverKey_;
            }

            public Builder setServerKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1024;
                    this.serverKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearServerKey() {
                this.bitField0_ &= RESULT.E_TEEKM_JSON_OUT_ADDR_LEN;
                this.serverKey_ = StoredClientPaymentChannel.getDefaultInstance().getServerKey();
                onChanged();
                return this;
            }
        }

        private StoredClientPaymentChannel(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private StoredClientPaymentChannel(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static StoredClientPaymentChannel getDefaultInstance() {
            return defaultInstance;
        }

        public StoredClientPaymentChannel getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r4v0, types: [com.google.protobuf.MessageLite, org.bitcoinj.protocols.channels.ClientState$StoredClientPaymentChannel] */
        private StoredClientPaymentChannel(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    switch (readTag) {
                        case 0:
                            z = true;
                            break;
                        case 10:
                            this.bitField0_ |= 1;
                            this.id_ = codedInputStream.readBytes();
                            break;
                        case 18:
                            this.bitField0_ |= 2;
                            this.contractTransaction_ = codedInputStream.readBytes();
                            break;
                        case 26:
                            this.bitField0_ |= 4;
                            this.refundTransaction_ = codedInputStream.readBytes();
                            break;
                        case 34:
                            this.bitField0_ |= 16;
                            this.myKey_ = codedInputStream.readBytes();
                            break;
                        case 40:
                            this.bitField0_ |= 32;
                            this.valueToMe_ = codedInputStream.readUInt64();
                            break;
                        case 48:
                            this.bitField0_ |= 64;
                            this.refundFees_ = codedInputStream.readUInt64();
                            break;
                        case 58:
                            this.bitField0_ |= 128;
                            this.closeTransactionHash_ = codedInputStream.readBytes();
                            break;
                        case 66:
                            this.bitField0_ |= 8;
                            this.myPublicKey_ = codedInputStream.readBytes();
                            break;
                        case 72:
                            this.bitField0_ |= 256;
                            this.majorVersion_ = codedInputStream.readUInt32();
                            break;
                        case 80:
                            this.bitField0_ |= 512;
                            this.expiryTime_ = codedInputStream.readUInt64();
                            break;
                        case 90:
                            this.bitField0_ |= 1024;
                            this.serverKey_ = codedInputStream.readBytes();
                            break;
                        default:
                            if (parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                                break;
                            }
                            z = true;
                            break;
                    }
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
            return ClientState.f806x8f2ff88b;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ClientState.f807xe04f6409.ensureFieldAccessorsInitialized(StoredClientPaymentChannel.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<StoredClientPaymentChannel> getParserForType() {
            return PARSER;
        }

        public boolean hasId() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getId() {
            return this.id_;
        }

        public boolean hasContractTransaction() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getContractTransaction() {
            return this.contractTransaction_;
        }

        public boolean hasRefundTransaction() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getRefundTransaction() {
            return this.refundTransaction_;
        }

        public boolean hasMyPublicKey() {
            return (this.bitField0_ & 8) == 8;
        }

        public ByteString getMyPublicKey() {
            return this.myPublicKey_;
        }

        public boolean hasMyKey() {
            return (this.bitField0_ & 16) == 16;
        }

        public ByteString getMyKey() {
            return this.myKey_;
        }

        public boolean hasValueToMe() {
            return (this.bitField0_ & 32) == 32;
        }

        public long getValueToMe() {
            return this.valueToMe_;
        }

        public boolean hasRefundFees() {
            return (this.bitField0_ & 64) == 64;
        }

        public long getRefundFees() {
            return this.refundFees_;
        }

        public boolean hasCloseTransactionHash() {
            return (this.bitField0_ & 128) == 128;
        }

        public ByteString getCloseTransactionHash() {
            return this.closeTransactionHash_;
        }

        public boolean hasMajorVersion() {
            return (this.bitField0_ & 256) == 256;
        }

        public int getMajorVersion() {
            return this.majorVersion_;
        }

        public boolean hasExpiryTime() {
            return (this.bitField0_ & 512) == 512;
        }

        public long getExpiryTime() {
            return this.expiryTime_;
        }

        public boolean hasServerKey() {
            return (this.bitField0_ & 1024) == 1024;
        }

        public ByteString getServerKey() {
            return this.serverKey_;
        }

        private void initFields() {
            this.id_ = ByteString.EMPTY;
            this.contractTransaction_ = ByteString.EMPTY;
            this.refundTransaction_ = ByteString.EMPTY;
            this.myPublicKey_ = ByteString.EMPTY;
            this.myKey_ = ByteString.EMPTY;
            this.valueToMe_ = 0;
            this.refundFees_ = 0;
            this.closeTransactionHash_ = ByteString.EMPTY;
            this.majorVersion_ = 1;
            this.expiryTime_ = 0;
            this.serverKey_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasId()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasContractTransaction()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasRefundTransaction()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasMyPublicKey()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasMyKey()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasValueToMe()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasRefundFees()) {
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
                codedOutputStream.writeBytes(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.contractTransaction_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, this.refundTransaction_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBytes(4, this.myKey_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeUInt64(5, this.valueToMe_);
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeUInt64(6, this.refundFees_);
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeBytes(7, this.closeTransactionHash_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBytes(8, this.myPublicKey_);
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeUInt32(9, this.majorVersion_);
            }
            if ((this.bitField0_ & 512) == 512) {
                codedOutputStream.writeUInt64(10, this.expiryTime_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                codedOutputStream.writeBytes(11, this.serverKey_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.id_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.contractTransaction_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeBytesSize(3, this.refundTransaction_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeBytesSize(4, this.myKey_);
            }
            if ((this.bitField0_ & 32) == 32) {
                i2 += CodedOutputStream.computeUInt64Size(5, this.valueToMe_);
            }
            if ((this.bitField0_ & 64) == 64) {
                i2 += CodedOutputStream.computeUInt64Size(6, this.refundFees_);
            }
            if ((this.bitField0_ & 128) == 128) {
                i2 += CodedOutputStream.computeBytesSize(7, this.closeTransactionHash_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeBytesSize(8, this.myPublicKey_);
            }
            if ((this.bitField0_ & 256) == 256) {
                i2 += CodedOutputStream.computeUInt32Size(9, this.majorVersion_);
            }
            if ((this.bitField0_ & 512) == 512) {
                i2 += CodedOutputStream.computeUInt64Size(10, this.expiryTime_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                i2 += CodedOutputStream.computeBytesSize(11, this.serverKey_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return ClientState.super.writeReplace();
        }

        public static StoredClientPaymentChannel parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StoredClientPaymentChannel) PARSER.parseFrom(byteString);
        }

        public static StoredClientPaymentChannel parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StoredClientPaymentChannel) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static StoredClientPaymentChannel parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StoredClientPaymentChannel) PARSER.parseFrom(bArr);
        }

        public static StoredClientPaymentChannel parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StoredClientPaymentChannel) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static StoredClientPaymentChannel parseFrom(InputStream inputStream) throws IOException {
            return (StoredClientPaymentChannel) PARSER.parseFrom(inputStream);
        }

        public static StoredClientPaymentChannel parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StoredClientPaymentChannel) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static StoredClientPaymentChannel parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StoredClientPaymentChannel) PARSER.parseDelimitedFrom(inputStream);
        }

        public static StoredClientPaymentChannel parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StoredClientPaymentChannel) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static StoredClientPaymentChannel parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StoredClientPaymentChannel) PARSER.parseFrom(codedInputStream);
        }

        public static StoredClientPaymentChannel parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StoredClientPaymentChannel) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(StoredClientPaymentChannel storedClientPaymentChannel) {
            return newBuilder().mergeFrom(storedClientPaymentChannel);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface StoredClientPaymentChannelOrBuilder extends MessageOrBuilder {
        ByteString getCloseTransactionHash();

        ByteString getContractTransaction();

        long getExpiryTime();

        ByteString getId();

        int getMajorVersion();

        ByteString getMyKey();

        ByteString getMyPublicKey();

        long getRefundFees();

        ByteString getRefundTransaction();

        ByteString getServerKey();

        long getValueToMe();

        boolean hasCloseTransactionHash();

        boolean hasContractTransaction();

        boolean hasExpiryTime();

        boolean hasId();

        boolean hasMajorVersion();

        boolean hasMyKey();

        boolean hasMyPublicKey();

        boolean hasRefundFees();

        boolean hasRefundTransaction();

        boolean hasServerKey();

        boolean hasValueToMe();
    }

    public static final class StoredClientPaymentChannels extends GeneratedMessage implements StoredClientPaymentChannelsOrBuilder {
        public static final int CHANNELS_FIELD_NUMBER = 1;
        public static Parser<StoredClientPaymentChannels> PARSER = new AbstractParser<StoredClientPaymentChannels>() {
            public StoredClientPaymentChannels parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new StoredClientPaymentChannels(codedInputStream, extensionRegistryLite);
            }
        };
        private static final StoredClientPaymentChannels defaultInstance = new StoredClientPaymentChannels(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public List<StoredClientPaymentChannel> channels_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements StoredClientPaymentChannelsOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> channelsBuilder_;
            private List<StoredClientPaymentChannel> channels_;

            public static final Descriptor getDescriptor() {
                return ClientState.f808x6bbd92de;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return ClientState.f809x1cd2fb5c.ensureFieldAccessorsInitialized(StoredClientPaymentChannels.class, Builder.class);
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
                if (StoredClientPaymentChannels.alwaysUseFieldBuilders) {
                    getChannelsFieldBuilder();
                }
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                StoredClientPaymentChannels.super.clear();
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
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
                return ClientState.f808x6bbd92de;
            }

            public StoredClientPaymentChannels getDefaultInstanceForType() {
                return StoredClientPaymentChannels.getDefaultInstance();
            }

            public StoredClientPaymentChannels build() {
                StoredClientPaymentChannels buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public StoredClientPaymentChannels buildPartial() {
                StoredClientPaymentChannels storedClientPaymentChannels = new StoredClientPaymentChannels((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    if ((i & 1) == 1) {
                        this.channels_ = Collections.unmodifiableList(this.channels_);
                        this.bitField0_ &= -2;
                    }
                    storedClientPaymentChannels.channels_ = this.channels_;
                } else {
                    storedClientPaymentChannels.channels_ = repeatedFieldBuilder.build();
                }
                onBuilt();
                return storedClientPaymentChannels;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof StoredClientPaymentChannels) {
                    return mergeFrom((StoredClientPaymentChannels) message);
                }
                StoredClientPaymentChannels.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(StoredClientPaymentChannels storedClientPaymentChannels) {
                if (storedClientPaymentChannels == StoredClientPaymentChannels.getDefaultInstance()) {
                    return this;
                }
                if (this.channelsBuilder_ == null) {
                    if (!storedClientPaymentChannels.channels_.isEmpty()) {
                        if (this.channels_.isEmpty()) {
                            this.channels_ = storedClientPaymentChannels.channels_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureChannelsIsMutable();
                            this.channels_.addAll(storedClientPaymentChannels.channels_);
                        }
                        onChanged();
                    }
                } else if (!storedClientPaymentChannels.channels_.isEmpty()) {
                    if (this.channelsBuilder_.isEmpty()) {
                        this.channelsBuilder_.dispose();
                        RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = null;
                        this.channelsBuilder_ = null;
                        this.channels_ = storedClientPaymentChannels.channels_;
                        this.bitField0_ &= -2;
                        if (StoredClientPaymentChannels.alwaysUseFieldBuilders) {
                            repeatedFieldBuilder = getChannelsFieldBuilder();
                        }
                        this.channelsBuilder_ = repeatedFieldBuilder;
                    } else {
                        this.channelsBuilder_.addAllMessages(storedClientPaymentChannels.channels_);
                    }
                }
                mergeUnknownFields(storedClientPaymentChannels.getUnknownFields());
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
                StoredClientPaymentChannels storedClientPaymentChannels;
                StoredClientPaymentChannels storedClientPaymentChannels2 = null;
                try {
                    StoredClientPaymentChannels storedClientPaymentChannels3 = (StoredClientPaymentChannels) StoredClientPaymentChannels.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (storedClientPaymentChannels3 != null) {
                        mergeFrom(storedClientPaymentChannels3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    storedClientPaymentChannels = (StoredClientPaymentChannels) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    storedClientPaymentChannels2 = storedClientPaymentChannels;
                }
                if (storedClientPaymentChannels2 != null) {
                    mergeFrom(storedClientPaymentChannels2);
                }
                throw th;
            }

            private void ensureChannelsIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.channels_ = new ArrayList(this.channels_);
                    this.bitField0_ |= 1;
                }
            }

            public List<StoredClientPaymentChannel> getChannelsList() {
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.channels_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getChannelsCount() {
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.channels_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public StoredClientPaymentChannel getChannels(int i) {
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (StoredClientPaymentChannel) this.channels_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setChannels(int i, StoredClientPaymentChannel storedClientPaymentChannel) {
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, storedClientPaymentChannel);
                } else if (storedClientPaymentChannel != null) {
                    ensureChannelsIsMutable();
                    this.channels_.set(i, storedClientPaymentChannel);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setChannels(int i, Builder builder) {
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureChannelsIsMutable();
                    this.channels_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addChannels(StoredClientPaymentChannel storedClientPaymentChannel) {
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(storedClientPaymentChannel);
                } else if (storedClientPaymentChannel != null) {
                    ensureChannelsIsMutable();
                    this.channels_.add(storedClientPaymentChannel);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addChannels(int i, StoredClientPaymentChannel storedClientPaymentChannel) {
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, storedClientPaymentChannel);
                } else if (storedClientPaymentChannel != null) {
                    ensureChannelsIsMutable();
                    this.channels_.add(i, storedClientPaymentChannel);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addChannels(Builder builder) {
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
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
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureChannelsIsMutable();
                    this.channels_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllChannels(Iterable<? extends StoredClientPaymentChannel> iterable) {
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
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
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
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
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
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

            public StoredClientPaymentChannelOrBuilder getChannelsOrBuilder(int i) {
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (StoredClientPaymentChannelOrBuilder) this.channels_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends StoredClientPaymentChannelOrBuilder> getChannelsOrBuilderList() {
                RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> repeatedFieldBuilder = this.channelsBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.channels_);
            }

            public Builder addChannelsBuilder() {
                return getChannelsFieldBuilder().addBuilder(StoredClientPaymentChannel.getDefaultInstance());
            }

            public Builder addChannelsBuilder(int i) {
                return getChannelsFieldBuilder().addBuilder(i, StoredClientPaymentChannel.getDefaultInstance());
            }

            public List<Builder> getChannelsBuilderList() {
                return getChannelsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<StoredClientPaymentChannel, Builder, StoredClientPaymentChannelOrBuilder> getChannelsFieldBuilder() {
                if (this.channelsBuilder_ == null) {
                    List<StoredClientPaymentChannel> list = this.channels_;
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

        private StoredClientPaymentChannels(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private StoredClientPaymentChannels(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static StoredClientPaymentChannels getDefaultInstance() {
            return defaultInstance;
        }

        public StoredClientPaymentChannels getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [org.bitcoinj.protocols.channels.ClientState$StoredClientPaymentChannels, com.google.protobuf.MessageLite] */
        private StoredClientPaymentChannels(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.channels_.add(codedInputStream.readMessage(StoredClientPaymentChannel.PARSER, extensionRegistryLite));
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
            return ClientState.f808x6bbd92de;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return ClientState.f809x1cd2fb5c.ensureFieldAccessorsInitialized(StoredClientPaymentChannels.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<StoredClientPaymentChannels> getParserForType() {
            return PARSER;
        }

        public List<StoredClientPaymentChannel> getChannelsList() {
            return this.channels_;
        }

        public List<? extends StoredClientPaymentChannelOrBuilder> getChannelsOrBuilderList() {
            return this.channels_;
        }

        public int getChannelsCount() {
            return this.channels_.size();
        }

        public StoredClientPaymentChannel getChannels(int i) {
            return (StoredClientPaymentChannel) this.channels_.get(i);
        }

        public StoredClientPaymentChannelOrBuilder getChannelsOrBuilder(int i) {
            return (StoredClientPaymentChannelOrBuilder) this.channels_.get(i);
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
            return ClientState.super.writeReplace();
        }

        public static StoredClientPaymentChannels parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (StoredClientPaymentChannels) PARSER.parseFrom(byteString);
        }

        public static StoredClientPaymentChannels parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StoredClientPaymentChannels) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static StoredClientPaymentChannels parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (StoredClientPaymentChannels) PARSER.parseFrom(bArr);
        }

        public static StoredClientPaymentChannels parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (StoredClientPaymentChannels) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static StoredClientPaymentChannels parseFrom(InputStream inputStream) throws IOException {
            return (StoredClientPaymentChannels) PARSER.parseFrom(inputStream);
        }

        public static StoredClientPaymentChannels parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StoredClientPaymentChannels) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static StoredClientPaymentChannels parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (StoredClientPaymentChannels) PARSER.parseDelimitedFrom(inputStream);
        }

        public static StoredClientPaymentChannels parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StoredClientPaymentChannels) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static StoredClientPaymentChannels parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (StoredClientPaymentChannels) PARSER.parseFrom(codedInputStream);
        }

        public static StoredClientPaymentChannels parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (StoredClientPaymentChannels) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(StoredClientPaymentChannels storedClientPaymentChannels) {
            return newBuilder().mergeFrom(storedClientPaymentChannels);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface StoredClientPaymentChannelsOrBuilder extends MessageOrBuilder {
        StoredClientPaymentChannel getChannels(int i);

        int getChannelsCount();

        List<StoredClientPaymentChannel> getChannelsList();

        StoredClientPaymentChannelOrBuilder getChannelsOrBuilder(int i);

        List<? extends StoredClientPaymentChannelOrBuilder> getChannelsOrBuilderList();
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
    }

    private ClientState() {
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n storedclientpaymentchannel.proto\u0012\u000fpaymentchannels\"\\\n\u001bStoredClientPaymentChannels\u0012=\n\bchannels\u0018\u0001 \u0003(\u000b2+.paymentchannels.StoredClientPaymentChannel\"\u0002\n\u001aStoredClientPaymentChannel\u0012\n\n\u0002id\u0018\u0001 \u0002(\f\u0012\u001b\n\u0013contractTransaction\u0018\u0002 \u0002(\f\u0012\u0019\n\u0011refundTransaction\u0018\u0003 \u0002(\f\u0012\u0013\n\u000bmyPublicKey\u0018\b \u0002(\f\u0012\r\n\u0005myKey\u0018\u0004 \u0002(\f\u0012\u0011\n\tvalueToMe\u0018\u0005 \u0002(\u0004\u0012\u0012\n\nrefundFees\u0018\u0006 \u0002(\u0004\u0012\u001c\n\u0014closeTransactionHash\u0018\u0007 \u0001(\f\u0012\u0017\n\fmajorVersion\u0018\t \u0001(\r:\u00011\u0012\u0012\n\nexpiryTime\u0018\n \u0001(\u0004\u0012\u0011\n\tse", "rverKey\u0018\u000b \u0001(\fB.\n\u001forg.bitcoinj.protocols.channelsB\u000bClientState"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                ClientState.descriptor = fileDescriptor;
                return null;
            }
        });
    }
}
