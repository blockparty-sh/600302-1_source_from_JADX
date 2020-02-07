package org.bitcoin.paymentchannel;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.EnumDescriptor;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.GeneratedMessage.BuilderParent;
import com.google.protobuf.GeneratedMessage.FieldAccessorTable;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.htc.htcwalletsdk.Export.RESULT;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import org.bitcoinj.protocols.channels.PaymentChannelClient;

public final class Protos {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_paymentchannels_ClientVersion_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_paymentchannels_ClientVersion_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_paymentchannels_Error_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(10));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_paymentchannels_Error_fieldAccessorTable = new FieldAccessorTable(internal_static_paymentchannels_Error_descriptor, new String[]{"Code", "Explanation", "ExpectedValue"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_paymentchannels_Initiate_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(3));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_paymentchannels_Initiate_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_paymentchannels_PaymentAck_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(8));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_paymentchannels_PaymentAck_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_paymentchannels_ProvideContract_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(6));
    /* access modifiers changed from: private */

    /* renamed from: internal_static_paymentchannels_ProvideContract_fieldAccessorTable */
    public static FieldAccessorTable f793x97030b71;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_paymentchannels_ProvideRefund_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(4));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_paymentchannels_ProvideRefund_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_paymentchannels_ReturnRefund_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(5));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_paymentchannels_ReturnRefund_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_paymentchannels_ServerVersion_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_paymentchannels_ServerVersion_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_paymentchannels_Settlement_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(9));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_paymentchannels_Settlement_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_paymentchannels_TwoWayChannelMessage_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    /* access modifiers changed from: private */

    /* renamed from: internal_static_paymentchannels_TwoWayChannelMessage_fieldAccessorTable */
    public static FieldAccessorTable f794xb0010c2d = new FieldAccessorTable(internal_static_paymentchannels_TwoWayChannelMessage_descriptor, new String[]{"Type", "ClientVersion", "ServerVersion", "Initiate", "ProvideRefund", "ReturnRefund", "ProvideContract", "UpdatePayment", "PaymentAck", "Settlement", "Error"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_paymentchannels_UpdatePayment_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(7));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_paymentchannels_UpdatePayment_fieldAccessorTable;

    public static final class ClientVersion extends GeneratedMessage implements ClientVersionOrBuilder {
        public static final int MAJOR_FIELD_NUMBER = 1;
        public static final int MINOR_FIELD_NUMBER = 2;
        public static Parser<ClientVersion> PARSER = new AbstractParser<ClientVersion>() {
            public ClientVersion parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ClientVersion(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PREVIOUS_CHANNEL_CONTRACT_HASH_FIELD_NUMBER = 3;
        public static final int TIME_WINDOW_SECS_FIELD_NUMBER = 4;
        private static final ClientVersion defaultInstance = new ClientVersion(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int major_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int minor_;
        /* access modifiers changed from: private */
        public ByteString previousChannelContractHash_;
        /* access modifiers changed from: private */
        public long timeWindowSecs_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements ClientVersionOrBuilder {
            private int bitField0_;
            private int major_;
            private int minor_;
            private ByteString previousChannelContractHash_;
            private long timeWindowSecs_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_paymentchannels_ClientVersion_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_paymentchannels_ClientVersion_fieldAccessorTable.ensureFieldAccessorsInitialized(ClientVersion.class, Builder.class);
            }

            private Builder() {
                this.previousChannelContractHash_ = ByteString.EMPTY;
                this.timeWindowSecs_ = PaymentChannelClient.DEFAULT_TIME_WINDOW;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.previousChannelContractHash_ = ByteString.EMPTY;
                this.timeWindowSecs_ = PaymentChannelClient.DEFAULT_TIME_WINDOW;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                ClientVersion.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                ClientVersion.super.clear();
                this.major_ = 0;
                this.bitField0_ &= -2;
                this.minor_ = 0;
                this.bitField0_ &= -3;
                this.previousChannelContractHash_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                this.timeWindowSecs_ = PaymentChannelClient.DEFAULT_TIME_WINDOW;
                this.bitField0_ &= -9;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_paymentchannels_ClientVersion_descriptor;
            }

            public ClientVersion getDefaultInstanceForType() {
                return ClientVersion.getDefaultInstance();
            }

            public ClientVersion build() {
                ClientVersion buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public ClientVersion buildPartial() {
                ClientVersion clientVersion = new ClientVersion((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                clientVersion.major_ = this.major_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                clientVersion.minor_ = this.minor_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                clientVersion.previousChannelContractHash_ = this.previousChannelContractHash_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                clientVersion.timeWindowSecs_ = this.timeWindowSecs_;
                clientVersion.bitField0_ = i2;
                onBuilt();
                return clientVersion;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof ClientVersion) {
                    return mergeFrom((ClientVersion) message);
                }
                ClientVersion.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ClientVersion clientVersion) {
                if (clientVersion == ClientVersion.getDefaultInstance()) {
                    return this;
                }
                if (clientVersion.hasMajor()) {
                    setMajor(clientVersion.getMajor());
                }
                if (clientVersion.hasMinor()) {
                    setMinor(clientVersion.getMinor());
                }
                if (clientVersion.hasPreviousChannelContractHash()) {
                    setPreviousChannelContractHash(clientVersion.getPreviousChannelContractHash());
                }
                if (clientVersion.hasTimeWindowSecs()) {
                    setTimeWindowSecs(clientVersion.getTimeWindowSecs());
                }
                mergeUnknownFields(clientVersion.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                return hasMajor();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ClientVersion clientVersion;
                ClientVersion clientVersion2 = null;
                try {
                    ClientVersion clientVersion3 = (ClientVersion) ClientVersion.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (clientVersion3 != null) {
                        mergeFrom(clientVersion3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    clientVersion = (ClientVersion) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    clientVersion2 = clientVersion;
                }
                if (clientVersion2 != null) {
                    mergeFrom(clientVersion2);
                }
                throw th;
            }

            public boolean hasMajor() {
                return (this.bitField0_ & 1) == 1;
            }

            public int getMajor() {
                return this.major_;
            }

            public Builder setMajor(int i) {
                this.bitField0_ |= 1;
                this.major_ = i;
                onChanged();
                return this;
            }

            public Builder clearMajor() {
                this.bitField0_ &= -2;
                this.major_ = 0;
                onChanged();
                return this;
            }

            public boolean hasMinor() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getMinor() {
                return this.minor_;
            }

            public Builder setMinor(int i) {
                this.bitField0_ |= 2;
                this.minor_ = i;
                onChanged();
                return this;
            }

            public Builder clearMinor() {
                this.bitField0_ &= -3;
                this.minor_ = 0;
                onChanged();
                return this;
            }

            public boolean hasPreviousChannelContractHash() {
                return (this.bitField0_ & 4) == 4;
            }

            public ByteString getPreviousChannelContractHash() {
                return this.previousChannelContractHash_;
            }

            public Builder setPreviousChannelContractHash(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.previousChannelContractHash_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearPreviousChannelContractHash() {
                this.bitField0_ &= -5;
                this.previousChannelContractHash_ = ClientVersion.getDefaultInstance().getPreviousChannelContractHash();
                onChanged();
                return this;
            }

            public boolean hasTimeWindowSecs() {
                return (this.bitField0_ & 8) == 8;
            }

            public long getTimeWindowSecs() {
                return this.timeWindowSecs_;
            }

            public Builder setTimeWindowSecs(long j) {
                this.bitField0_ |= 8;
                this.timeWindowSecs_ = j;
                onChanged();
                return this;
            }

            public Builder clearTimeWindowSecs() {
                this.bitField0_ &= -9;
                this.timeWindowSecs_ = PaymentChannelClient.DEFAULT_TIME_WINDOW;
                onChanged();
                return this;
            }
        }

        private ClientVersion(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ClientVersion(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ClientVersion getDefaultInstance() {
            return defaultInstance;
        }

        public ClientVersion getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$ClientVersion] */
        private ClientVersion(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.major_ = codedInputStream.readInt32();
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.minor_ = codedInputStream.readInt32();
                        } else if (readTag == 26) {
                            this.bitField0_ |= 4;
                            this.previousChannelContractHash_ = codedInputStream.readBytes();
                        } else if (readTag == 32) {
                            this.bitField0_ |= 8;
                            this.timeWindowSecs_ = codedInputStream.readUInt64();
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
            return Protos.internal_static_paymentchannels_ClientVersion_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_paymentchannels_ClientVersion_fieldAccessorTable.ensureFieldAccessorsInitialized(ClientVersion.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<ClientVersion> getParserForType() {
            return PARSER;
        }

        public boolean hasMajor() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getMajor() {
            return this.major_;
        }

        public boolean hasMinor() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getMinor() {
            return this.minor_;
        }

        public boolean hasPreviousChannelContractHash() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getPreviousChannelContractHash() {
            return this.previousChannelContractHash_;
        }

        public boolean hasTimeWindowSecs() {
            return (this.bitField0_ & 8) == 8;
        }

        public long getTimeWindowSecs() {
            return this.timeWindowSecs_;
        }

        private void initFields() {
            this.major_ = 0;
            this.minor_ = 0;
            this.previousChannelContractHash_ = ByteString.EMPTY;
            this.timeWindowSecs_ = PaymentChannelClient.DEFAULT_TIME_WINDOW;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasMajor()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.major_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.minor_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, this.previousChannelContractHash_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeUInt64(4, this.timeWindowSecs_);
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
                i2 = 0 + CodedOutputStream.computeInt32Size(1, this.major_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeInt32Size(2, this.minor_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeBytesSize(3, this.previousChannelContractHash_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeUInt64Size(4, this.timeWindowSecs_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static ClientVersion parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ClientVersion) PARSER.parseFrom(byteString);
        }

        public static ClientVersion parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ClientVersion) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ClientVersion parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ClientVersion) PARSER.parseFrom(bArr);
        }

        public static ClientVersion parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ClientVersion) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ClientVersion parseFrom(InputStream inputStream) throws IOException {
            return (ClientVersion) PARSER.parseFrom(inputStream);
        }

        public static ClientVersion parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ClientVersion) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static ClientVersion parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ClientVersion) PARSER.parseDelimitedFrom(inputStream);
        }

        public static ClientVersion parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ClientVersion) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static ClientVersion parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ClientVersion) PARSER.parseFrom(codedInputStream);
        }

        public static ClientVersion parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ClientVersion) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ClientVersion clientVersion) {
            return newBuilder().mergeFrom(clientVersion);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface ClientVersionOrBuilder extends MessageOrBuilder {
        int getMajor();

        int getMinor();

        ByteString getPreviousChannelContractHash();

        long getTimeWindowSecs();

        boolean hasMajor();

        boolean hasMinor();

        boolean hasPreviousChannelContractHash();

        boolean hasTimeWindowSecs();
    }

    public static final class Error extends GeneratedMessage implements ErrorOrBuilder {
        public static final int CODE_FIELD_NUMBER = 1;
        public static final int EXPECTED_VALUE_FIELD_NUMBER = 3;
        public static final int EXPLANATION_FIELD_NUMBER = 2;
        public static Parser<Error> PARSER = new AbstractParser<Error>() {
            public Error parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Error(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Error defaultInstance = new Error(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ErrorCode code_;
        /* access modifiers changed from: private */
        public long expectedValue_;
        /* access modifiers changed from: private */
        public Object explanation_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements ErrorOrBuilder {
            private int bitField0_;
            private ErrorCode code_;
            private long expectedValue_;
            private Object explanation_;

            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_paymentchannels_Error_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_paymentchannels_Error_fieldAccessorTable.ensureFieldAccessorsInitialized(Error.class, Builder.class);
            }

            private Builder() {
                this.code_ = ErrorCode.OTHER;
                this.explanation_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.code_ = ErrorCode.OTHER;
                this.explanation_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                Error.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                Error.super.clear();
                this.code_ = ErrorCode.OTHER;
                this.bitField0_ &= -2;
                this.explanation_ = "";
                this.bitField0_ &= -3;
                this.expectedValue_ = 0;
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_paymentchannels_Error_descriptor;
            }

            public Error getDefaultInstanceForType() {
                return Error.getDefaultInstance();
            }

            public Error build() {
                Error buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Error buildPartial() {
                Error error = new Error((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                error.code_ = this.code_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                error.explanation_ = this.explanation_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                error.expectedValue_ = this.expectedValue_;
                error.bitField0_ = i2;
                onBuilt();
                return error;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof Error) {
                    return mergeFrom((Error) message);
                }
                Error.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Error error) {
                if (error == Error.getDefaultInstance()) {
                    return this;
                }
                if (error.hasCode()) {
                    setCode(error.getCode());
                }
                if (error.hasExplanation()) {
                    this.bitField0_ |= 2;
                    this.explanation_ = error.explanation_;
                    onChanged();
                }
                if (error.hasExpectedValue()) {
                    setExpectedValue(error.getExpectedValue());
                }
                mergeUnknownFields(error.getUnknownFields());
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Error error;
                Error error2 = null;
                try {
                    Error error3 = (Error) Error.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (error3 != null) {
                        mergeFrom(error3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    error = (Error) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    error2 = error;
                }
                if (error2 != null) {
                    mergeFrom(error2);
                }
                throw th;
            }

            public boolean hasCode() {
                return (this.bitField0_ & 1) == 1;
            }

            public ErrorCode getCode() {
                return this.code_;
            }

            public Builder setCode(ErrorCode errorCode) {
                if (errorCode != null) {
                    this.bitField0_ |= 1;
                    this.code_ = errorCode;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearCode() {
                this.bitField0_ &= -2;
                this.code_ = ErrorCode.OTHER;
                onChanged();
                return this;
            }

            public boolean hasExplanation() {
                return (this.bitField0_ & 2) == 2;
            }

            public String getExplanation() {
                Object obj = this.explanation_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.explanation_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getExplanationBytes() {
                Object obj = this.explanation_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.explanation_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setExplanation(String str) {
                if (str != null) {
                    this.bitField0_ |= 2;
                    this.explanation_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearExplanation() {
                this.bitField0_ &= -3;
                this.explanation_ = Error.getDefaultInstance().getExplanation();
                onChanged();
                return this;
            }

            public Builder setExplanationBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.explanation_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public boolean hasExpectedValue() {
                return (this.bitField0_ & 4) == 4;
            }

            public long getExpectedValue() {
                return this.expectedValue_;
            }

            public Builder setExpectedValue(long j) {
                this.bitField0_ |= 4;
                this.expectedValue_ = j;
                onChanged();
                return this;
            }

            public Builder clearExpectedValue() {
                this.bitField0_ &= -5;
                this.expectedValue_ = 0;
                onChanged();
                return this;
            }
        }

        public enum ErrorCode implements ProtocolMessageEnum {
            TIMEOUT(0, 1),
            SYNTAX_ERROR(1, 2),
            NO_ACCEPTABLE_VERSION(2, 3),
            BAD_TRANSACTION(3, 4),
            TIME_WINDOW_UNACCEPTABLE(4, 5),
            CHANNEL_VALUE_TOO_LARGE(5, 6),
            MIN_PAYMENT_TOO_LARGE(6, 7),
            OTHER(7, 8);
            
            public static final int BAD_TRANSACTION_VALUE = 4;
            public static final int CHANNEL_VALUE_TOO_LARGE_VALUE = 6;
            public static final int MIN_PAYMENT_TOO_LARGE_VALUE = 7;
            public static final int NO_ACCEPTABLE_VERSION_VALUE = 3;
            public static final int OTHER_VALUE = 8;
            public static final int SYNTAX_ERROR_VALUE = 2;
            public static final int TIMEOUT_VALUE = 1;
            public static final int TIME_WINDOW_UNACCEPTABLE_VALUE = 5;
            private static final ErrorCode[] VALUES = null;
            private static EnumLiteMap<ErrorCode> internalValueMap;
            private final int index;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<ErrorCode>() {
                    public ErrorCode findValueByNumber(int i) {
                        return ErrorCode.valueOf(i);
                    }
                };
                VALUES = values();
            }

            public final int getNumber() {
                return this.value;
            }

            public static ErrorCode valueOf(int i) {
                switch (i) {
                    case 1:
                        return TIMEOUT;
                    case 2:
                        return SYNTAX_ERROR;
                    case 3:
                        return NO_ACCEPTABLE_VERSION;
                    case 4:
                        return BAD_TRANSACTION;
                    case 5:
                        return TIME_WINDOW_UNACCEPTABLE;
                    case 6:
                        return CHANNEL_VALUE_TOO_LARGE;
                    case 7:
                        return MIN_PAYMENT_TOO_LARGE;
                    case 8:
                        return OTHER;
                    default:
                        return null;
                }
            }

            public static EnumLiteMap<ErrorCode> internalGetValueMap() {
                return internalValueMap;
            }

            public final EnumValueDescriptor getValueDescriptor() {
                return (EnumValueDescriptor) getDescriptor().getValues().get(this.index);
            }

            public final EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final EnumDescriptor getDescriptor() {
                return (EnumDescriptor) Error.getDescriptor().getEnumTypes().get(0);
            }

            public static ErrorCode valueOf(EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            private ErrorCode(int i, int i2) {
                this.index = i;
                this.value = i2;
            }
        }

        private Error(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Error(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Error getDefaultInstance() {
            return defaultInstance;
        }

        public Error getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$Error] */
        private Error(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            int readEnum = codedInputStream.readEnum();
                            ErrorCode valueOf = ErrorCode.valueOf(readEnum);
                            if (valueOf == null) {
                                newBuilder.mergeVarintField(1, readEnum);
                            } else {
                                this.bitField0_ |= 1;
                                this.code_ = valueOf;
                            }
                        } else if (readTag == 18) {
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 2;
                            this.explanation_ = readBytes;
                        } else if (readTag == 24) {
                            this.bitField0_ |= 4;
                            this.expectedValue_ = codedInputStream.readUInt64();
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
            return Protos.internal_static_paymentchannels_Error_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_paymentchannels_Error_fieldAccessorTable.ensureFieldAccessorsInitialized(Error.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Error> getParserForType() {
            return PARSER;
        }

        public boolean hasCode() {
            return (this.bitField0_ & 1) == 1;
        }

        public ErrorCode getCode() {
            return this.code_;
        }

        public boolean hasExplanation() {
            return (this.bitField0_ & 2) == 2;
        }

        public String getExplanation() {
            Object obj = this.explanation_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.explanation_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getExplanationBytes() {
            Object obj = this.explanation_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.explanation_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasExpectedValue() {
            return (this.bitField0_ & 4) == 4;
        }

        public long getExpectedValue() {
            return this.expectedValue_;
        }

        private void initFields() {
            this.code_ = ErrorCode.OTHER;
            this.explanation_ = "";
            this.expectedValue_ = 0;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeEnum(1, this.code_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, getExplanationBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeUInt64(3, this.expectedValue_);
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
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.code_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, getExplanationBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeUInt64Size(3, this.expectedValue_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static Error parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Error) PARSER.parseFrom(byteString);
        }

        public static Error parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Error) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Error parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Error) PARSER.parseFrom(bArr);
        }

        public static Error parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Error) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Error parseFrom(InputStream inputStream) throws IOException {
            return (Error) PARSER.parseFrom(inputStream);
        }

        public static Error parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Error) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Error parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Error) PARSER.parseDelimitedFrom(inputStream);
        }

        public static Error parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Error) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static Error parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Error) PARSER.parseFrom(codedInputStream);
        }

        public static Error parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Error) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Error error) {
            return newBuilder().mergeFrom(error);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface ErrorOrBuilder extends MessageOrBuilder {
        ErrorCode getCode();

        long getExpectedValue();

        String getExplanation();

        ByteString getExplanationBytes();

        boolean hasCode();

        boolean hasExpectedValue();

        boolean hasExplanation();
    }

    public static final class Initiate extends GeneratedMessage implements InitiateOrBuilder {
        public static final int EXPIRE_TIME_SECS_FIELD_NUMBER = 3;
        public static final int MIN_ACCEPTED_CHANNEL_SIZE_FIELD_NUMBER = 2;
        public static final int MIN_PAYMENT_FIELD_NUMBER = 4;
        public static final int MULTISIG_KEY_FIELD_NUMBER = 1;
        public static Parser<Initiate> PARSER = new AbstractParser<Initiate>() {
            public Initiate parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Initiate(codedInputStream, extensionRegistryLite);
            }
        };
        private static final Initiate defaultInstance = new Initiate(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public long expireTimeSecs_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public long minAcceptedChannelSize_;
        /* access modifiers changed from: private */
        public long minPayment_;
        /* access modifiers changed from: private */
        public ByteString multisigKey_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements InitiateOrBuilder {
            private int bitField0_;
            private long expireTimeSecs_;
            private long minAcceptedChannelSize_;
            private long minPayment_;
            private ByteString multisigKey_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_paymentchannels_Initiate_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_paymentchannels_Initiate_fieldAccessorTable.ensureFieldAccessorsInitialized(Initiate.class, Builder.class);
            }

            private Builder() {
                this.multisigKey_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.multisigKey_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                Initiate.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                Initiate.super.clear();
                this.multisigKey_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.minAcceptedChannelSize_ = 0;
                this.bitField0_ &= -3;
                this.expireTimeSecs_ = 0;
                this.bitField0_ &= -5;
                this.minPayment_ = 0;
                this.bitField0_ &= -9;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_paymentchannels_Initiate_descriptor;
            }

            public Initiate getDefaultInstanceForType() {
                return Initiate.getDefaultInstance();
            }

            public Initiate build() {
                Initiate buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Initiate buildPartial() {
                Initiate initiate = new Initiate((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                initiate.multisigKey_ = this.multisigKey_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                initiate.minAcceptedChannelSize_ = this.minAcceptedChannelSize_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                initiate.expireTimeSecs_ = this.expireTimeSecs_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                initiate.minPayment_ = this.minPayment_;
                initiate.bitField0_ = i2;
                onBuilt();
                return initiate;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof Initiate) {
                    return mergeFrom((Initiate) message);
                }
                Initiate.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Initiate initiate) {
                if (initiate == Initiate.getDefaultInstance()) {
                    return this;
                }
                if (initiate.hasMultisigKey()) {
                    setMultisigKey(initiate.getMultisigKey());
                }
                if (initiate.hasMinAcceptedChannelSize()) {
                    setMinAcceptedChannelSize(initiate.getMinAcceptedChannelSize());
                }
                if (initiate.hasExpireTimeSecs()) {
                    setExpireTimeSecs(initiate.getExpireTimeSecs());
                }
                if (initiate.hasMinPayment()) {
                    setMinPayment(initiate.getMinPayment());
                }
                mergeUnknownFields(initiate.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasMultisigKey() && hasMinAcceptedChannelSize() && hasExpireTimeSecs() && hasMinPayment()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Initiate initiate;
                Initiate initiate2 = null;
                try {
                    Initiate initiate3 = (Initiate) Initiate.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (initiate3 != null) {
                        mergeFrom(initiate3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    initiate = (Initiate) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    initiate2 = initiate;
                }
                if (initiate2 != null) {
                    mergeFrom(initiate2);
                }
                throw th;
            }

            public boolean hasMultisigKey() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getMultisigKey() {
                return this.multisigKey_;
            }

            public Builder setMultisigKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.multisigKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearMultisigKey() {
                this.bitField0_ &= -2;
                this.multisigKey_ = Initiate.getDefaultInstance().getMultisigKey();
                onChanged();
                return this;
            }

            public boolean hasMinAcceptedChannelSize() {
                return (this.bitField0_ & 2) == 2;
            }

            public long getMinAcceptedChannelSize() {
                return this.minAcceptedChannelSize_;
            }

            public Builder setMinAcceptedChannelSize(long j) {
                this.bitField0_ |= 2;
                this.minAcceptedChannelSize_ = j;
                onChanged();
                return this;
            }

            public Builder clearMinAcceptedChannelSize() {
                this.bitField0_ &= -3;
                this.minAcceptedChannelSize_ = 0;
                onChanged();
                return this;
            }

            public boolean hasExpireTimeSecs() {
                return (this.bitField0_ & 4) == 4;
            }

            public long getExpireTimeSecs() {
                return this.expireTimeSecs_;
            }

            public Builder setExpireTimeSecs(long j) {
                this.bitField0_ |= 4;
                this.expireTimeSecs_ = j;
                onChanged();
                return this;
            }

            public Builder clearExpireTimeSecs() {
                this.bitField0_ &= -5;
                this.expireTimeSecs_ = 0;
                onChanged();
                return this;
            }

            public boolean hasMinPayment() {
                return (this.bitField0_ & 8) == 8;
            }

            public long getMinPayment() {
                return this.minPayment_;
            }

            public Builder setMinPayment(long j) {
                this.bitField0_ |= 8;
                this.minPayment_ = j;
                onChanged();
                return this;
            }

            public Builder clearMinPayment() {
                this.bitField0_ &= -9;
                this.minPayment_ = 0;
                onChanged();
                return this;
            }
        }

        private Initiate(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Initiate(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Initiate getDefaultInstance() {
            return defaultInstance;
        }

        public Initiate getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$Initiate] */
        private Initiate(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            this.bitField0_ |= 1;
                            this.multisigKey_ = codedInputStream.readBytes();
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.minAcceptedChannelSize_ = codedInputStream.readUInt64();
                        } else if (readTag == 24) {
                            this.bitField0_ |= 4;
                            this.expireTimeSecs_ = codedInputStream.readUInt64();
                        } else if (readTag == 32) {
                            this.bitField0_ |= 8;
                            this.minPayment_ = codedInputStream.readUInt64();
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
            return Protos.internal_static_paymentchannels_Initiate_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_paymentchannels_Initiate_fieldAccessorTable.ensureFieldAccessorsInitialized(Initiate.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Initiate> getParserForType() {
            return PARSER;
        }

        public boolean hasMultisigKey() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getMultisigKey() {
            return this.multisigKey_;
        }

        public boolean hasMinAcceptedChannelSize() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getMinAcceptedChannelSize() {
            return this.minAcceptedChannelSize_;
        }

        public boolean hasExpireTimeSecs() {
            return (this.bitField0_ & 4) == 4;
        }

        public long getExpireTimeSecs() {
            return this.expireTimeSecs_;
        }

        public boolean hasMinPayment() {
            return (this.bitField0_ & 8) == 8;
        }

        public long getMinPayment() {
            return this.minPayment_;
        }

        private void initFields() {
            this.multisigKey_ = ByteString.EMPTY;
            this.minAcceptedChannelSize_ = 0;
            this.expireTimeSecs_ = 0;
            this.minPayment_ = 0;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasMultisigKey()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasMinAcceptedChannelSize()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasExpireTimeSecs()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasMinPayment()) {
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
                codedOutputStream.writeBytes(1, this.multisigKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeUInt64(2, this.minAcceptedChannelSize_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeUInt64(3, this.expireTimeSecs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeUInt64(4, this.minPayment_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.multisigKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeUInt64Size(2, this.minAcceptedChannelSize_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeUInt64Size(3, this.expireTimeSecs_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeUInt64Size(4, this.minPayment_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static Initiate parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Initiate) PARSER.parseFrom(byteString);
        }

        public static Initiate parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Initiate) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Initiate parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Initiate) PARSER.parseFrom(bArr);
        }

        public static Initiate parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Initiate) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Initiate parseFrom(InputStream inputStream) throws IOException {
            return (Initiate) PARSER.parseFrom(inputStream);
        }

        public static Initiate parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Initiate) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Initiate parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Initiate) PARSER.parseDelimitedFrom(inputStream);
        }

        public static Initiate parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Initiate) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static Initiate parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Initiate) PARSER.parseFrom(codedInputStream);
        }

        public static Initiate parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Initiate) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Initiate initiate) {
            return newBuilder().mergeFrom(initiate);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface InitiateOrBuilder extends MessageOrBuilder {
        long getExpireTimeSecs();

        long getMinAcceptedChannelSize();

        long getMinPayment();

        ByteString getMultisigKey();

        boolean hasExpireTimeSecs();

        boolean hasMinAcceptedChannelSize();

        boolean hasMinPayment();

        boolean hasMultisigKey();
    }

    public static final class PaymentAck extends GeneratedMessage implements PaymentAckOrBuilder {
        public static final int INFO_FIELD_NUMBER = 1;
        public static Parser<PaymentAck> PARSER = new AbstractParser<PaymentAck>() {
            public PaymentAck parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PaymentAck(codedInputStream, extensionRegistryLite);
            }
        };
        private static final PaymentAck defaultInstance = new PaymentAck(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ByteString info_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements PaymentAckOrBuilder {
            private int bitField0_;
            private ByteString info_;

            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_paymentchannels_PaymentAck_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_paymentchannels_PaymentAck_fieldAccessorTable.ensureFieldAccessorsInitialized(PaymentAck.class, Builder.class);
            }

            private Builder() {
                this.info_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.info_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                PaymentAck.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                PaymentAck.super.clear();
                this.info_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_paymentchannels_PaymentAck_descriptor;
            }

            public PaymentAck getDefaultInstanceForType() {
                return PaymentAck.getDefaultInstance();
            }

            public PaymentAck build() {
                PaymentAck buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public PaymentAck buildPartial() {
                PaymentAck paymentAck = new PaymentAck((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = 1;
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                paymentAck.info_ = this.info_;
                paymentAck.bitField0_ = i;
                onBuilt();
                return paymentAck;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof PaymentAck) {
                    return mergeFrom((PaymentAck) message);
                }
                PaymentAck.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PaymentAck paymentAck) {
                if (paymentAck == PaymentAck.getDefaultInstance()) {
                    return this;
                }
                if (paymentAck.hasInfo()) {
                    setInfo(paymentAck.getInfo());
                }
                mergeUnknownFields(paymentAck.getUnknownFields());
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PaymentAck paymentAck;
                PaymentAck paymentAck2 = null;
                try {
                    PaymentAck paymentAck3 = (PaymentAck) PaymentAck.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (paymentAck3 != null) {
                        mergeFrom(paymentAck3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    paymentAck = (PaymentAck) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    paymentAck2 = paymentAck;
                }
                if (paymentAck2 != null) {
                    mergeFrom(paymentAck2);
                }
                throw th;
            }

            public boolean hasInfo() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getInfo() {
                return this.info_;
            }

            public Builder setInfo(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.info_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearInfo() {
                this.bitField0_ &= -2;
                this.info_ = PaymentAck.getDefaultInstance().getInfo();
                onChanged();
                return this;
            }
        }

        private PaymentAck(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private PaymentAck(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static PaymentAck getDefaultInstance() {
            return defaultInstance;
        }

        public PaymentAck getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$PaymentAck] */
        private PaymentAck(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            this.bitField0_ |= 1;
                            this.info_ = codedInputStream.readBytes();
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
            return Protos.internal_static_paymentchannels_PaymentAck_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_paymentchannels_PaymentAck_fieldAccessorTable.ensureFieldAccessorsInitialized(PaymentAck.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<PaymentAck> getParserForType() {
            return PARSER;
        }

        public boolean hasInfo() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getInfo() {
            return this.info_;
        }

        private void initFields() {
            this.info_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.info_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.info_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static PaymentAck parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PaymentAck) PARSER.parseFrom(byteString);
        }

        public static PaymentAck parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PaymentAck) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PaymentAck parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PaymentAck) PARSER.parseFrom(bArr);
        }

        public static PaymentAck parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PaymentAck) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PaymentAck parseFrom(InputStream inputStream) throws IOException {
            return (PaymentAck) PARSER.parseFrom(inputStream);
        }

        public static PaymentAck parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PaymentAck) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static PaymentAck parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PaymentAck) PARSER.parseDelimitedFrom(inputStream);
        }

        public static PaymentAck parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PaymentAck) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static PaymentAck parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PaymentAck) PARSER.parseFrom(codedInputStream);
        }

        public static PaymentAck parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PaymentAck) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PaymentAck paymentAck) {
            return newBuilder().mergeFrom(paymentAck);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface PaymentAckOrBuilder extends MessageOrBuilder {
        ByteString getInfo();

        boolean hasInfo();
    }

    public static final class ProvideContract extends GeneratedMessage implements ProvideContractOrBuilder {
        public static final int CLIENT_KEY_FIELD_NUMBER = 3;
        public static final int INITIAL_PAYMENT_FIELD_NUMBER = 2;
        public static Parser<ProvideContract> PARSER = new AbstractParser<ProvideContract>() {
            public ProvideContract parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProvideContract(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TX_FIELD_NUMBER = 1;
        private static final ProvideContract defaultInstance = new ProvideContract(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ByteString clientKey_;
        /* access modifiers changed from: private */
        public UpdatePayment initialPayment_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString tx_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements ProvideContractOrBuilder {
            private int bitField0_;
            private ByteString clientKey_;
            private SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> initialPaymentBuilder_;
            private UpdatePayment initialPayment_;
            private ByteString tx_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_paymentchannels_ProvideContract_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.f793x97030b71.ensureFieldAccessorsInitialized(ProvideContract.class, Builder.class);
            }

            private Builder() {
                this.tx_ = ByteString.EMPTY;
                this.initialPayment_ = UpdatePayment.getDefaultInstance();
                this.clientKey_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.tx_ = ByteString.EMPTY;
                this.initialPayment_ = UpdatePayment.getDefaultInstance();
                this.clientKey_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (ProvideContract.alwaysUseFieldBuilders) {
                    getInitialPaymentFieldBuilder();
                }
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                ProvideContract.super.clear();
                this.tx_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.initialPaymentBuilder_;
                if (singleFieldBuilder == null) {
                    this.initialPayment_ = UpdatePayment.getDefaultInstance();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -3;
                this.clientKey_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_paymentchannels_ProvideContract_descriptor;
            }

            public ProvideContract getDefaultInstanceForType() {
                return ProvideContract.getDefaultInstance();
            }

            public ProvideContract build() {
                ProvideContract buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public ProvideContract buildPartial() {
                ProvideContract provideContract = new ProvideContract((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                provideContract.tx_ = this.tx_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.initialPaymentBuilder_;
                if (singleFieldBuilder == null) {
                    provideContract.initialPayment_ = this.initialPayment_;
                } else {
                    provideContract.initialPayment_ = singleFieldBuilder.build();
                }
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                provideContract.clientKey_ = this.clientKey_;
                provideContract.bitField0_ = i2;
                onBuilt();
                return provideContract;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof ProvideContract) {
                    return mergeFrom((ProvideContract) message);
                }
                ProvideContract.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProvideContract provideContract) {
                if (provideContract == ProvideContract.getDefaultInstance()) {
                    return this;
                }
                if (provideContract.hasTx()) {
                    setTx(provideContract.getTx());
                }
                if (provideContract.hasInitialPayment()) {
                    mergeInitialPayment(provideContract.getInitialPayment());
                }
                if (provideContract.hasClientKey()) {
                    setClientKey(provideContract.getClientKey());
                }
                mergeUnknownFields(provideContract.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasTx() && hasInitialPayment() && getInitialPayment().isInitialized()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ProvideContract provideContract;
                ProvideContract provideContract2 = null;
                try {
                    ProvideContract provideContract3 = (ProvideContract) ProvideContract.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (provideContract3 != null) {
                        mergeFrom(provideContract3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    provideContract = (ProvideContract) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    provideContract2 = provideContract;
                }
                if (provideContract2 != null) {
                    mergeFrom(provideContract2);
                }
                throw th;
            }

            public boolean hasTx() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getTx() {
                return this.tx_;
            }

            public Builder setTx(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.tx_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearTx() {
                this.bitField0_ &= -2;
                this.tx_ = ProvideContract.getDefaultInstance().getTx();
                onChanged();
                return this;
            }

            public boolean hasInitialPayment() {
                return (this.bitField0_ & 2) == 2;
            }

            public UpdatePayment getInitialPayment() {
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.initialPaymentBuilder_;
                if (singleFieldBuilder == null) {
                    return this.initialPayment_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setInitialPayment(UpdatePayment updatePayment) {
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.initialPaymentBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(updatePayment);
                } else if (updatePayment != null) {
                    this.initialPayment_ = updatePayment;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setInitialPayment(Builder builder) {
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.initialPaymentBuilder_;
                if (singleFieldBuilder == null) {
                    this.initialPayment_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeInitialPayment(UpdatePayment updatePayment) {
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.initialPaymentBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 2) != 2 || this.initialPayment_ == UpdatePayment.getDefaultInstance()) {
                        this.initialPayment_ = updatePayment;
                    } else {
                        this.initialPayment_ = UpdatePayment.newBuilder(this.initialPayment_).mergeFrom(updatePayment).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(updatePayment);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearInitialPayment() {
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.initialPaymentBuilder_;
                if (singleFieldBuilder == null) {
                    this.initialPayment_ = UpdatePayment.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            public Builder getInitialPaymentBuilder() {
                this.bitField0_ |= 2;
                onChanged();
                return getInitialPaymentFieldBuilder().getBuilder();
            }

            public UpdatePaymentOrBuilder getInitialPaymentOrBuilder() {
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.initialPaymentBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.initialPayment_;
            }

            private SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> getInitialPaymentFieldBuilder() {
                if (this.initialPaymentBuilder_ == null) {
                    this.initialPaymentBuilder_ = new SingleFieldBuilder<>(getInitialPayment(), getParentForChildren(), isClean());
                    this.initialPayment_ = null;
                }
                return this.initialPaymentBuilder_;
            }

            public boolean hasClientKey() {
                return (this.bitField0_ & 4) == 4;
            }

            public ByteString getClientKey() {
                return this.clientKey_;
            }

            public Builder setClientKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.clientKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearClientKey() {
                this.bitField0_ &= -5;
                this.clientKey_ = ProvideContract.getDefaultInstance().getClientKey();
                onChanged();
                return this;
            }
        }

        private ProvideContract(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ProvideContract(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ProvideContract getDefaultInstance() {
            return defaultInstance;
        }

        public ProvideContract getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [org.bitcoin.paymentchannel.Protos$ProvideContract, com.google.protobuf.MessageLite] */
        private ProvideContract(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            this.bitField0_ |= 1;
                            this.tx_ = codedInputStream.readBytes();
                        } else if (readTag == 18) {
                            Builder builder = null;
                            if ((this.bitField0_ & 2) == 2) {
                                builder = this.initialPayment_.toBuilder();
                            }
                            this.initialPayment_ = (UpdatePayment) codedInputStream.readMessage(UpdatePayment.PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.initialPayment_);
                                this.initialPayment_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (readTag == 26) {
                            this.bitField0_ |= 4;
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
            return Protos.internal_static_paymentchannels_ProvideContract_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.f793x97030b71.ensureFieldAccessorsInitialized(ProvideContract.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<ProvideContract> getParserForType() {
            return PARSER;
        }

        public boolean hasTx() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getTx() {
            return this.tx_;
        }

        public boolean hasInitialPayment() {
            return (this.bitField0_ & 2) == 2;
        }

        public UpdatePayment getInitialPayment() {
            return this.initialPayment_;
        }

        public UpdatePaymentOrBuilder getInitialPaymentOrBuilder() {
            return this.initialPayment_;
        }

        public boolean hasClientKey() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getClientKey() {
            return this.clientKey_;
        }

        private void initFields() {
            this.tx_ = ByteString.EMPTY;
            this.initialPayment_ = UpdatePayment.getDefaultInstance();
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
            if (!hasTx()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasInitialPayment()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!getInitialPayment().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else {
                this.memoizedIsInitialized = 1;
                return true;
            }
        }

        /* JADX WARNING: type inference failed for: r0v8, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$UpdatePayment] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v8, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$UpdatePayment]
          assigns: [org.bitcoin.paymentchannel.Protos$UpdatePayment]
          uses: [com.google.protobuf.MessageLite]
          mth insns count: 19
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void writeTo(com.google.protobuf.CodedOutputStream r3) throws java.io.IOException {
            /*
                r2 = this;
                r2.getSerializedSize()
                int r0 = r2.bitField0_
                r1 = 1
                r0 = r0 & r1
                if (r0 != r1) goto L_0x000e
                com.google.protobuf.ByteString r0 = r2.tx_
                r3.writeBytes(r1, r0)
            L_0x000e:
                int r0 = r2.bitField0_
                r1 = 2
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0019
                org.bitcoin.paymentchannel.Protos$UpdatePayment r0 = r2.initialPayment_
                r3.writeMessage(r1, r0)
            L_0x0019:
                int r0 = r2.bitField0_
                r1 = 4
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0025
                r0 = 3
                com.google.protobuf.ByteString r1 = r2.clientKey_
                r3.writeBytes(r0, r1)
            L_0x0025:
                com.google.protobuf.UnknownFieldSet r0 = r2.getUnknownFields()
                r0.writeTo(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoin.paymentchannel.Protos.ProvideContract.writeTo(com.google.protobuf.CodedOutputStream):void");
        }

        /* JADX WARNING: type inference failed for: r1v11, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$UpdatePayment] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v11, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$UpdatePayment]
          assigns: [org.bitcoin.paymentchannel.Protos$UpdatePayment]
          uses: [com.google.protobuf.MessageLite]
          mth insns count: 27
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int getSerializedSize() {
            /*
                r3 = this;
                int r0 = r3.memoizedSerializedSize
                r1 = -1
                if (r0 == r1) goto L_0x0006
                return r0
            L_0x0006:
                r0 = 0
                int r1 = r3.bitField0_
                r2 = 1
                r1 = r1 & r2
                if (r1 != r2) goto L_0x0014
                com.google.protobuf.ByteString r1 = r3.tx_
                int r1 = com.google.protobuf.CodedOutputStream.computeBytesSize(r2, r1)
                int r0 = r0 + r1
            L_0x0014:
                int r1 = r3.bitField0_
                r2 = 2
                r1 = r1 & r2
                if (r1 != r2) goto L_0x0021
                org.bitcoin.paymentchannel.Protos$UpdatePayment r1 = r3.initialPayment_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r2, r1)
                int r0 = r0 + r1
            L_0x0021:
                int r1 = r3.bitField0_
                r2 = 4
                r1 = r1 & r2
                if (r1 != r2) goto L_0x002f
                r1 = 3
                com.google.protobuf.ByteString r2 = r3.clientKey_
                int r1 = com.google.protobuf.CodedOutputStream.computeBytesSize(r1, r2)
                int r0 = r0 + r1
            L_0x002f:
                com.google.protobuf.UnknownFieldSet r1 = r3.getUnknownFields()
                int r1 = r1.getSerializedSize()
                int r0 = r0 + r1
                r3.memoizedSerializedSize = r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoin.paymentchannel.Protos.ProvideContract.getSerializedSize():int");
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static ProvideContract parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ProvideContract) PARSER.parseFrom(byteString);
        }

        public static ProvideContract parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ProvideContract) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ProvideContract parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ProvideContract) PARSER.parseFrom(bArr);
        }

        public static ProvideContract parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ProvideContract) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ProvideContract parseFrom(InputStream inputStream) throws IOException {
            return (ProvideContract) PARSER.parseFrom(inputStream);
        }

        public static ProvideContract parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProvideContract) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static ProvideContract parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProvideContract) PARSER.parseDelimitedFrom(inputStream);
        }

        public static ProvideContract parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProvideContract) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static ProvideContract parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProvideContract) PARSER.parseFrom(codedInputStream);
        }

        public static ProvideContract parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProvideContract) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ProvideContract provideContract) {
            return newBuilder().mergeFrom(provideContract);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface ProvideContractOrBuilder extends MessageOrBuilder {
        ByteString getClientKey();

        UpdatePayment getInitialPayment();

        UpdatePaymentOrBuilder getInitialPaymentOrBuilder();

        ByteString getTx();

        boolean hasClientKey();

        boolean hasInitialPayment();

        boolean hasTx();
    }

    public static final class ProvideRefund extends GeneratedMessage implements ProvideRefundOrBuilder {
        public static final int MULTISIG_KEY_FIELD_NUMBER = 1;
        public static Parser<ProvideRefund> PARSER = new AbstractParser<ProvideRefund>() {
            public ProvideRefund parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ProvideRefund(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TX_FIELD_NUMBER = 2;
        private static final ProvideRefund defaultInstance = new ProvideRefund(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString multisigKey_;
        /* access modifiers changed from: private */
        public ByteString tx_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements ProvideRefundOrBuilder {
            private int bitField0_;
            private ByteString multisigKey_;
            private ByteString tx_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_paymentchannels_ProvideRefund_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_paymentchannels_ProvideRefund_fieldAccessorTable.ensureFieldAccessorsInitialized(ProvideRefund.class, Builder.class);
            }

            private Builder() {
                this.multisigKey_ = ByteString.EMPTY;
                this.tx_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.multisigKey_ = ByteString.EMPTY;
                this.tx_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                ProvideRefund.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                ProvideRefund.super.clear();
                this.multisigKey_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.tx_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_paymentchannels_ProvideRefund_descriptor;
            }

            public ProvideRefund getDefaultInstanceForType() {
                return ProvideRefund.getDefaultInstance();
            }

            public ProvideRefund build() {
                ProvideRefund buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public ProvideRefund buildPartial() {
                ProvideRefund provideRefund = new ProvideRefund((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                provideRefund.multisigKey_ = this.multisigKey_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                provideRefund.tx_ = this.tx_;
                provideRefund.bitField0_ = i2;
                onBuilt();
                return provideRefund;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof ProvideRefund) {
                    return mergeFrom((ProvideRefund) message);
                }
                ProvideRefund.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ProvideRefund provideRefund) {
                if (provideRefund == ProvideRefund.getDefaultInstance()) {
                    return this;
                }
                if (provideRefund.hasMultisigKey()) {
                    setMultisigKey(provideRefund.getMultisigKey());
                }
                if (provideRefund.hasTx()) {
                    setTx(provideRefund.getTx());
                }
                mergeUnknownFields(provideRefund.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasMultisigKey() && hasTx()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ProvideRefund provideRefund;
                ProvideRefund provideRefund2 = null;
                try {
                    ProvideRefund provideRefund3 = (ProvideRefund) ProvideRefund.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (provideRefund3 != null) {
                        mergeFrom(provideRefund3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    provideRefund = (ProvideRefund) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    provideRefund2 = provideRefund;
                }
                if (provideRefund2 != null) {
                    mergeFrom(provideRefund2);
                }
                throw th;
            }

            public boolean hasMultisigKey() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getMultisigKey() {
                return this.multisigKey_;
            }

            public Builder setMultisigKey(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.multisigKey_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearMultisigKey() {
                this.bitField0_ &= -2;
                this.multisigKey_ = ProvideRefund.getDefaultInstance().getMultisigKey();
                onChanged();
                return this;
            }

            public boolean hasTx() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getTx() {
                return this.tx_;
            }

            public Builder setTx(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.tx_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearTx() {
                this.bitField0_ &= -3;
                this.tx_ = ProvideRefund.getDefaultInstance().getTx();
                onChanged();
                return this;
            }
        }

        private ProvideRefund(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ProvideRefund(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ProvideRefund getDefaultInstance() {
            return defaultInstance;
        }

        public ProvideRefund getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$ProvideRefund] */
        private ProvideRefund(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            this.bitField0_ |= 1;
                            this.multisigKey_ = codedInputStream.readBytes();
                        } else if (readTag == 18) {
                            this.bitField0_ |= 2;
                            this.tx_ = codedInputStream.readBytes();
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
            return Protos.internal_static_paymentchannels_ProvideRefund_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_paymentchannels_ProvideRefund_fieldAccessorTable.ensureFieldAccessorsInitialized(ProvideRefund.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<ProvideRefund> getParserForType() {
            return PARSER;
        }

        public boolean hasMultisigKey() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getMultisigKey() {
            return this.multisigKey_;
        }

        public boolean hasTx() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getTx() {
            return this.tx_;
        }

        private void initFields() {
            this.multisigKey_ = ByteString.EMPTY;
            this.tx_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasMultisigKey()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasTx()) {
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
                codedOutputStream.writeBytes(1, this.multisigKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.tx_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.multisigKey_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.tx_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static ProvideRefund parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ProvideRefund) PARSER.parseFrom(byteString);
        }

        public static ProvideRefund parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ProvideRefund) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ProvideRefund parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ProvideRefund) PARSER.parseFrom(bArr);
        }

        public static ProvideRefund parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ProvideRefund) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ProvideRefund parseFrom(InputStream inputStream) throws IOException {
            return (ProvideRefund) PARSER.parseFrom(inputStream);
        }

        public static ProvideRefund parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProvideRefund) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static ProvideRefund parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ProvideRefund) PARSER.parseDelimitedFrom(inputStream);
        }

        public static ProvideRefund parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProvideRefund) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static ProvideRefund parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ProvideRefund) PARSER.parseFrom(codedInputStream);
        }

        public static ProvideRefund parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ProvideRefund) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ProvideRefund provideRefund) {
            return newBuilder().mergeFrom(provideRefund);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface ProvideRefundOrBuilder extends MessageOrBuilder {
        ByteString getMultisigKey();

        ByteString getTx();

        boolean hasMultisigKey();

        boolean hasTx();
    }

    public static final class ReturnRefund extends GeneratedMessage implements ReturnRefundOrBuilder {
        public static Parser<ReturnRefund> PARSER = new AbstractParser<ReturnRefund>() {
            public ReturnRefund parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ReturnRefund(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SIGNATURE_FIELD_NUMBER = 1;
        private static final ReturnRefund defaultInstance = new ReturnRefund(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString signature_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements ReturnRefundOrBuilder {
            private int bitField0_;
            private ByteString signature_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_paymentchannels_ReturnRefund_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_paymentchannels_ReturnRefund_fieldAccessorTable.ensureFieldAccessorsInitialized(ReturnRefund.class, Builder.class);
            }

            private Builder() {
                this.signature_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.signature_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                ReturnRefund.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                ReturnRefund.super.clear();
                this.signature_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_paymentchannels_ReturnRefund_descriptor;
            }

            public ReturnRefund getDefaultInstanceForType() {
                return ReturnRefund.getDefaultInstance();
            }

            public ReturnRefund build() {
                ReturnRefund buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public ReturnRefund buildPartial() {
                ReturnRefund returnRefund = new ReturnRefund((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = 1;
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                returnRefund.signature_ = this.signature_;
                returnRefund.bitField0_ = i;
                onBuilt();
                return returnRefund;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof ReturnRefund) {
                    return mergeFrom((ReturnRefund) message);
                }
                ReturnRefund.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ReturnRefund returnRefund) {
                if (returnRefund == ReturnRefund.getDefaultInstance()) {
                    return this;
                }
                if (returnRefund.hasSignature()) {
                    setSignature(returnRefund.getSignature());
                }
                mergeUnknownFields(returnRefund.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                return hasSignature();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ReturnRefund returnRefund;
                ReturnRefund returnRefund2 = null;
                try {
                    ReturnRefund returnRefund3 = (ReturnRefund) ReturnRefund.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (returnRefund3 != null) {
                        mergeFrom(returnRefund3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    returnRefund = (ReturnRefund) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    returnRefund2 = returnRefund;
                }
                if (returnRefund2 != null) {
                    mergeFrom(returnRefund2);
                }
                throw th;
            }

            public boolean hasSignature() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getSignature() {
                return this.signature_;
            }

            public Builder setSignature(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.signature_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearSignature() {
                this.bitField0_ &= -2;
                this.signature_ = ReturnRefund.getDefaultInstance().getSignature();
                onChanged();
                return this;
            }
        }

        private ReturnRefund(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ReturnRefund(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ReturnRefund getDefaultInstance() {
            return defaultInstance;
        }

        public ReturnRefund getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$ReturnRefund] */
        private ReturnRefund(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 10) {
                            this.bitField0_ |= 1;
                            this.signature_ = codedInputStream.readBytes();
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
            return Protos.internal_static_paymentchannels_ReturnRefund_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_paymentchannels_ReturnRefund_fieldAccessorTable.ensureFieldAccessorsInitialized(ReturnRefund.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<ReturnRefund> getParserForType() {
            return PARSER;
        }

        public boolean hasSignature() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getSignature() {
            return this.signature_;
        }

        private void initFields() {
            this.signature_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasSignature()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.signature_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(1, this.signature_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static ReturnRefund parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ReturnRefund) PARSER.parseFrom(byteString);
        }

        public static ReturnRefund parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReturnRefund) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ReturnRefund parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ReturnRefund) PARSER.parseFrom(bArr);
        }

        public static ReturnRefund parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ReturnRefund) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ReturnRefund parseFrom(InputStream inputStream) throws IOException {
            return (ReturnRefund) PARSER.parseFrom(inputStream);
        }

        public static ReturnRefund parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReturnRefund) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static ReturnRefund parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ReturnRefund) PARSER.parseDelimitedFrom(inputStream);
        }

        public static ReturnRefund parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReturnRefund) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static ReturnRefund parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ReturnRefund) PARSER.parseFrom(codedInputStream);
        }

        public static ReturnRefund parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ReturnRefund) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ReturnRefund returnRefund) {
            return newBuilder().mergeFrom(returnRefund);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface ReturnRefundOrBuilder extends MessageOrBuilder {
        ByteString getSignature();

        boolean hasSignature();
    }

    public static final class ServerVersion extends GeneratedMessage implements ServerVersionOrBuilder {
        public static final int MAJOR_FIELD_NUMBER = 1;
        public static final int MINOR_FIELD_NUMBER = 2;
        public static Parser<ServerVersion> PARSER = new AbstractParser<ServerVersion>() {
            public ServerVersion parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new ServerVersion(codedInputStream, extensionRegistryLite);
            }
        };
        private static final ServerVersion defaultInstance = new ServerVersion(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public int major_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int minor_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements ServerVersionOrBuilder {
            private int bitField0_;
            private int major_;
            private int minor_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_paymentchannels_ServerVersion_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_paymentchannels_ServerVersion_fieldAccessorTable.ensureFieldAccessorsInitialized(ServerVersion.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                ServerVersion.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                ServerVersion.super.clear();
                this.major_ = 0;
                this.bitField0_ &= -2;
                this.minor_ = 0;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_paymentchannels_ServerVersion_descriptor;
            }

            public ServerVersion getDefaultInstanceForType() {
                return ServerVersion.getDefaultInstance();
            }

            public ServerVersion build() {
                ServerVersion buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public ServerVersion buildPartial() {
                ServerVersion serverVersion = new ServerVersion((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                serverVersion.major_ = this.major_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                serverVersion.minor_ = this.minor_;
                serverVersion.bitField0_ = i2;
                onBuilt();
                return serverVersion;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof ServerVersion) {
                    return mergeFrom((ServerVersion) message);
                }
                ServerVersion.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(ServerVersion serverVersion) {
                if (serverVersion == ServerVersion.getDefaultInstance()) {
                    return this;
                }
                if (serverVersion.hasMajor()) {
                    setMajor(serverVersion.getMajor());
                }
                if (serverVersion.hasMinor()) {
                    setMinor(serverVersion.getMinor());
                }
                mergeUnknownFields(serverVersion.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                return hasMajor();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                ServerVersion serverVersion;
                ServerVersion serverVersion2 = null;
                try {
                    ServerVersion serverVersion3 = (ServerVersion) ServerVersion.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (serverVersion3 != null) {
                        mergeFrom(serverVersion3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    serverVersion = (ServerVersion) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    serverVersion2 = serverVersion;
                }
                if (serverVersion2 != null) {
                    mergeFrom(serverVersion2);
                }
                throw th;
            }

            public boolean hasMajor() {
                return (this.bitField0_ & 1) == 1;
            }

            public int getMajor() {
                return this.major_;
            }

            public Builder setMajor(int i) {
                this.bitField0_ |= 1;
                this.major_ = i;
                onChanged();
                return this;
            }

            public Builder clearMajor() {
                this.bitField0_ &= -2;
                this.major_ = 0;
                onChanged();
                return this;
            }

            public boolean hasMinor() {
                return (this.bitField0_ & 2) == 2;
            }

            public int getMinor() {
                return this.minor_;
            }

            public Builder setMinor(int i) {
                this.bitField0_ |= 2;
                this.minor_ = i;
                onChanged();
                return this;
            }

            public Builder clearMinor() {
                this.bitField0_ &= -3;
                this.minor_ = 0;
                onChanged();
                return this;
            }
        }

        private ServerVersion(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ServerVersion(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ServerVersion getDefaultInstance() {
            return defaultInstance;
        }

        public ServerVersion getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$ServerVersion] */
        private ServerVersion(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.major_ = codedInputStream.readInt32();
                        } else if (readTag == 16) {
                            this.bitField0_ |= 2;
                            this.minor_ = codedInputStream.readInt32();
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
            return Protos.internal_static_paymentchannels_ServerVersion_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_paymentchannels_ServerVersion_fieldAccessorTable.ensureFieldAccessorsInitialized(ServerVersion.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<ServerVersion> getParserForType() {
            return PARSER;
        }

        public boolean hasMajor() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getMajor() {
            return this.major_;
        }

        public boolean hasMinor() {
            return (this.bitField0_ & 2) == 2;
        }

        public int getMinor() {
            return this.minor_;
        }

        private void initFields() {
            this.major_ = 0;
            this.minor_ = 0;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasMajor()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.major_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.minor_);
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
                i2 = 0 + CodedOutputStream.computeInt32Size(1, this.major_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeInt32Size(2, this.minor_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static ServerVersion parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ServerVersion) PARSER.parseFrom(byteString);
        }

        public static ServerVersion parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ServerVersion) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static ServerVersion parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ServerVersion) PARSER.parseFrom(bArr);
        }

        public static ServerVersion parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ServerVersion) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static ServerVersion parseFrom(InputStream inputStream) throws IOException {
            return (ServerVersion) PARSER.parseFrom(inputStream);
        }

        public static ServerVersion parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ServerVersion) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static ServerVersion parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ServerVersion) PARSER.parseDelimitedFrom(inputStream);
        }

        public static ServerVersion parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ServerVersion) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static ServerVersion parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ServerVersion) PARSER.parseFrom(codedInputStream);
        }

        public static ServerVersion parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ServerVersion) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ServerVersion serverVersion) {
            return newBuilder().mergeFrom(serverVersion);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface ServerVersionOrBuilder extends MessageOrBuilder {
        int getMajor();

        int getMinor();

        boolean hasMajor();

        boolean hasMinor();
    }

    public static final class Settlement extends GeneratedMessage implements SettlementOrBuilder {
        public static Parser<Settlement> PARSER = new AbstractParser<Settlement>() {
            public Settlement parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Settlement(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int TX_FIELD_NUMBER = 3;
        private static final Settlement defaultInstance = new Settlement(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString tx_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements SettlementOrBuilder {
            private int bitField0_;
            private ByteString tx_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_paymentchannels_Settlement_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_paymentchannels_Settlement_fieldAccessorTable.ensureFieldAccessorsInitialized(Settlement.class, Builder.class);
            }

            private Builder() {
                this.tx_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.tx_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                Settlement.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                Settlement.super.clear();
                this.tx_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_paymentchannels_Settlement_descriptor;
            }

            public Settlement getDefaultInstanceForType() {
                return Settlement.getDefaultInstance();
            }

            public Settlement build() {
                Settlement buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Settlement buildPartial() {
                Settlement settlement = new Settlement((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = 1;
                if ((this.bitField0_ & 1) != 1) {
                    i = 0;
                }
                settlement.tx_ = this.tx_;
                settlement.bitField0_ = i;
                onBuilt();
                return settlement;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof Settlement) {
                    return mergeFrom((Settlement) message);
                }
                Settlement.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Settlement settlement) {
                if (settlement == Settlement.getDefaultInstance()) {
                    return this;
                }
                if (settlement.hasTx()) {
                    setTx(settlement.getTx());
                }
                mergeUnknownFields(settlement.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                return hasTx();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Settlement settlement;
                Settlement settlement2 = null;
                try {
                    Settlement settlement3 = (Settlement) Settlement.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (settlement3 != null) {
                        mergeFrom(settlement3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    settlement = (Settlement) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    settlement2 = settlement;
                }
                if (settlement2 != null) {
                    mergeFrom(settlement2);
                }
                throw th;
            }

            public boolean hasTx() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getTx() {
                return this.tx_;
            }

            public Builder setTx(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.tx_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearTx() {
                this.bitField0_ &= -2;
                this.tx_ = Settlement.getDefaultInstance().getTx();
                onChanged();
                return this;
            }
        }

        private Settlement(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Settlement(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Settlement getDefaultInstance() {
            return defaultInstance;
        }

        public Settlement getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$Settlement] */
        private Settlement(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            initFields();
            com.google.protobuf.UnknownFieldSet.Builder newBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    int readTag = codedInputStream.readTag();
                    if (readTag != 0) {
                        if (readTag == 26) {
                            this.bitField0_ |= 1;
                            this.tx_ = codedInputStream.readBytes();
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
            return Protos.internal_static_paymentchannels_Settlement_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_paymentchannels_Settlement_fieldAccessorTable.ensureFieldAccessorsInitialized(Settlement.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Settlement> getParserForType() {
            return PARSER;
        }

        public boolean hasTx() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getTx() {
            return this.tx_;
        }

        private void initFields() {
            this.tx_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasTx()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(3, this.tx_);
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
                i2 = 0 + CodedOutputStream.computeBytesSize(3, this.tx_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static Settlement parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Settlement) PARSER.parseFrom(byteString);
        }

        public static Settlement parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Settlement) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Settlement parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Settlement) PARSER.parseFrom(bArr);
        }

        public static Settlement parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Settlement) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Settlement parseFrom(InputStream inputStream) throws IOException {
            return (Settlement) PARSER.parseFrom(inputStream);
        }

        public static Settlement parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Settlement) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Settlement parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Settlement) PARSER.parseDelimitedFrom(inputStream);
        }

        public static Settlement parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Settlement) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static Settlement parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Settlement) PARSER.parseFrom(codedInputStream);
        }

        public static Settlement parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Settlement) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Settlement settlement) {
            return newBuilder().mergeFrom(settlement);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface SettlementOrBuilder extends MessageOrBuilder {
        ByteString getTx();

        boolean hasTx();
    }

    public static final class TwoWayChannelMessage extends GeneratedMessage implements TwoWayChannelMessageOrBuilder {
        public static final int CLIENT_VERSION_FIELD_NUMBER = 2;
        public static final int ERROR_FIELD_NUMBER = 10;
        public static final int INITIATE_FIELD_NUMBER = 4;
        public static Parser<TwoWayChannelMessage> PARSER = new AbstractParser<TwoWayChannelMessage>() {
            public TwoWayChannelMessage parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new TwoWayChannelMessage(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PAYMENT_ACK_FIELD_NUMBER = 11;
        public static final int PROVIDE_CONTRACT_FIELD_NUMBER = 7;
        public static final int PROVIDE_REFUND_FIELD_NUMBER = 5;
        public static final int RETURN_REFUND_FIELD_NUMBER = 6;
        public static final int SERVER_VERSION_FIELD_NUMBER = 3;
        public static final int SETTLEMENT_FIELD_NUMBER = 9;
        public static final int TYPE_FIELD_NUMBER = 1;
        public static final int UPDATE_PAYMENT_FIELD_NUMBER = 8;
        private static final TwoWayChannelMessage defaultInstance = new TwoWayChannelMessage(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public ClientVersion clientVersion_;
        /* access modifiers changed from: private */
        public Error error_;
        /* access modifiers changed from: private */
        public Initiate initiate_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public PaymentAck paymentAck_;
        /* access modifiers changed from: private */
        public ProvideContract provideContract_;
        /* access modifiers changed from: private */
        public ProvideRefund provideRefund_;
        /* access modifiers changed from: private */
        public ReturnRefund returnRefund_;
        /* access modifiers changed from: private */
        public ServerVersion serverVersion_;
        /* access modifiers changed from: private */
        public Settlement settlement_;
        /* access modifiers changed from: private */
        public MessageType type_;
        private final UnknownFieldSet unknownFields;
        /* access modifiers changed from: private */
        public UpdatePayment updatePayment_;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements TwoWayChannelMessageOrBuilder {
            private int bitField0_;
            private SingleFieldBuilder<ClientVersion, Builder, ClientVersionOrBuilder> clientVersionBuilder_;
            private ClientVersion clientVersion_;
            private SingleFieldBuilder<Error, Builder, ErrorOrBuilder> errorBuilder_;
            private Error error_;
            private SingleFieldBuilder<Initiate, Builder, InitiateOrBuilder> initiateBuilder_;
            private Initiate initiate_;
            private SingleFieldBuilder<PaymentAck, Builder, PaymentAckOrBuilder> paymentAckBuilder_;
            private PaymentAck paymentAck_;
            private SingleFieldBuilder<ProvideContract, Builder, ProvideContractOrBuilder> provideContractBuilder_;
            private ProvideContract provideContract_;
            private SingleFieldBuilder<ProvideRefund, Builder, ProvideRefundOrBuilder> provideRefundBuilder_;
            private ProvideRefund provideRefund_;
            private SingleFieldBuilder<ReturnRefund, Builder, ReturnRefundOrBuilder> returnRefundBuilder_;
            private ReturnRefund returnRefund_;
            private SingleFieldBuilder<ServerVersion, Builder, ServerVersionOrBuilder> serverVersionBuilder_;
            private ServerVersion serverVersion_;
            private SingleFieldBuilder<Settlement, Builder, SettlementOrBuilder> settlementBuilder_;
            private Settlement settlement_;
            private MessageType type_;
            private SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> updatePaymentBuilder_;
            private UpdatePayment updatePayment_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_paymentchannels_TwoWayChannelMessage_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.f794xb0010c2d.ensureFieldAccessorsInitialized(TwoWayChannelMessage.class, Builder.class);
            }

            private Builder() {
                this.type_ = MessageType.CLIENT_VERSION;
                this.clientVersion_ = ClientVersion.getDefaultInstance();
                this.serverVersion_ = ServerVersion.getDefaultInstance();
                this.initiate_ = Initiate.getDefaultInstance();
                this.provideRefund_ = ProvideRefund.getDefaultInstance();
                this.returnRefund_ = ReturnRefund.getDefaultInstance();
                this.provideContract_ = ProvideContract.getDefaultInstance();
                this.updatePayment_ = UpdatePayment.getDefaultInstance();
                this.paymentAck_ = PaymentAck.getDefaultInstance();
                this.settlement_ = Settlement.getDefaultInstance();
                this.error_ = Error.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.type_ = MessageType.CLIENT_VERSION;
                this.clientVersion_ = ClientVersion.getDefaultInstance();
                this.serverVersion_ = ServerVersion.getDefaultInstance();
                this.initiate_ = Initiate.getDefaultInstance();
                this.provideRefund_ = ProvideRefund.getDefaultInstance();
                this.returnRefund_ = ReturnRefund.getDefaultInstance();
                this.provideContract_ = ProvideContract.getDefaultInstance();
                this.updatePayment_ = UpdatePayment.getDefaultInstance();
                this.paymentAck_ = PaymentAck.getDefaultInstance();
                this.settlement_ = Settlement.getDefaultInstance();
                this.error_ = Error.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (TwoWayChannelMessage.alwaysUseFieldBuilders) {
                    getClientVersionFieldBuilder();
                    getServerVersionFieldBuilder();
                    getInitiateFieldBuilder();
                    getProvideRefundFieldBuilder();
                    getReturnRefundFieldBuilder();
                    getProvideContractFieldBuilder();
                    getUpdatePaymentFieldBuilder();
                    getPaymentAckFieldBuilder();
                    getSettlementFieldBuilder();
                    getErrorFieldBuilder();
                }
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                TwoWayChannelMessage.super.clear();
                this.type_ = MessageType.CLIENT_VERSION;
                this.bitField0_ &= -2;
                SingleFieldBuilder<ClientVersion, Builder, ClientVersionOrBuilder> singleFieldBuilder = this.clientVersionBuilder_;
                if (singleFieldBuilder == null) {
                    this.clientVersion_ = ClientVersion.getDefaultInstance();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -3;
                SingleFieldBuilder<ServerVersion, Builder, ServerVersionOrBuilder> singleFieldBuilder2 = this.serverVersionBuilder_;
                if (singleFieldBuilder2 == null) {
                    this.serverVersion_ = ServerVersion.getDefaultInstance();
                } else {
                    singleFieldBuilder2.clear();
                }
                this.bitField0_ &= -5;
                SingleFieldBuilder<Initiate, Builder, InitiateOrBuilder> singleFieldBuilder3 = this.initiateBuilder_;
                if (singleFieldBuilder3 == null) {
                    this.initiate_ = Initiate.getDefaultInstance();
                } else {
                    singleFieldBuilder3.clear();
                }
                this.bitField0_ &= -9;
                SingleFieldBuilder<ProvideRefund, Builder, ProvideRefundOrBuilder> singleFieldBuilder4 = this.provideRefundBuilder_;
                if (singleFieldBuilder4 == null) {
                    this.provideRefund_ = ProvideRefund.getDefaultInstance();
                } else {
                    singleFieldBuilder4.clear();
                }
                this.bitField0_ &= -17;
                SingleFieldBuilder<ReturnRefund, Builder, ReturnRefundOrBuilder> singleFieldBuilder5 = this.returnRefundBuilder_;
                if (singleFieldBuilder5 == null) {
                    this.returnRefund_ = ReturnRefund.getDefaultInstance();
                } else {
                    singleFieldBuilder5.clear();
                }
                this.bitField0_ &= -33;
                SingleFieldBuilder<ProvideContract, Builder, ProvideContractOrBuilder> singleFieldBuilder6 = this.provideContractBuilder_;
                if (singleFieldBuilder6 == null) {
                    this.provideContract_ = ProvideContract.getDefaultInstance();
                } else {
                    singleFieldBuilder6.clear();
                }
                this.bitField0_ &= -65;
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder7 = this.updatePaymentBuilder_;
                if (singleFieldBuilder7 == null) {
                    this.updatePayment_ = UpdatePayment.getDefaultInstance();
                } else {
                    singleFieldBuilder7.clear();
                }
                this.bitField0_ &= -129;
                SingleFieldBuilder<PaymentAck, Builder, PaymentAckOrBuilder> singleFieldBuilder8 = this.paymentAckBuilder_;
                if (singleFieldBuilder8 == null) {
                    this.paymentAck_ = PaymentAck.getDefaultInstance();
                } else {
                    singleFieldBuilder8.clear();
                }
                this.bitField0_ &= -257;
                SingleFieldBuilder<Settlement, Builder, SettlementOrBuilder> singleFieldBuilder9 = this.settlementBuilder_;
                if (singleFieldBuilder9 == null) {
                    this.settlement_ = Settlement.getDefaultInstance();
                } else {
                    singleFieldBuilder9.clear();
                }
                this.bitField0_ &= -513;
                SingleFieldBuilder<Error, Builder, ErrorOrBuilder> singleFieldBuilder10 = this.errorBuilder_;
                if (singleFieldBuilder10 == null) {
                    this.error_ = Error.getDefaultInstance();
                } else {
                    singleFieldBuilder10.clear();
                }
                this.bitField0_ &= RESULT.E_TEEKM_JSON_OUT_ADDR_LEN;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_paymentchannels_TwoWayChannelMessage_descriptor;
            }

            public TwoWayChannelMessage getDefaultInstanceForType() {
                return TwoWayChannelMessage.getDefaultInstance();
            }

            public TwoWayChannelMessage build() {
                TwoWayChannelMessage buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public TwoWayChannelMessage buildPartial() {
                TwoWayChannelMessage twoWayChannelMessage = new TwoWayChannelMessage((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                twoWayChannelMessage.type_ = this.type_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                SingleFieldBuilder<ClientVersion, Builder, ClientVersionOrBuilder> singleFieldBuilder = this.clientVersionBuilder_;
                if (singleFieldBuilder == null) {
                    twoWayChannelMessage.clientVersion_ = this.clientVersion_;
                } else {
                    twoWayChannelMessage.clientVersion_ = singleFieldBuilder.build();
                }
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                SingleFieldBuilder<ServerVersion, Builder, ServerVersionOrBuilder> singleFieldBuilder2 = this.serverVersionBuilder_;
                if (singleFieldBuilder2 == null) {
                    twoWayChannelMessage.serverVersion_ = this.serverVersion_;
                } else {
                    twoWayChannelMessage.serverVersion_ = singleFieldBuilder2.build();
                }
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                SingleFieldBuilder<Initiate, Builder, InitiateOrBuilder> singleFieldBuilder3 = this.initiateBuilder_;
                if (singleFieldBuilder3 == null) {
                    twoWayChannelMessage.initiate_ = this.initiate_;
                } else {
                    twoWayChannelMessage.initiate_ = singleFieldBuilder3.build();
                }
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                SingleFieldBuilder<ProvideRefund, Builder, ProvideRefundOrBuilder> singleFieldBuilder4 = this.provideRefundBuilder_;
                if (singleFieldBuilder4 == null) {
                    twoWayChannelMessage.provideRefund_ = this.provideRefund_;
                } else {
                    twoWayChannelMessage.provideRefund_ = singleFieldBuilder4.build();
                }
                if ((i & 32) == 32) {
                    i2 |= 32;
                }
                SingleFieldBuilder<ReturnRefund, Builder, ReturnRefundOrBuilder> singleFieldBuilder5 = this.returnRefundBuilder_;
                if (singleFieldBuilder5 == null) {
                    twoWayChannelMessage.returnRefund_ = this.returnRefund_;
                } else {
                    twoWayChannelMessage.returnRefund_ = singleFieldBuilder5.build();
                }
                if ((i & 64) == 64) {
                    i2 |= 64;
                }
                SingleFieldBuilder<ProvideContract, Builder, ProvideContractOrBuilder> singleFieldBuilder6 = this.provideContractBuilder_;
                if (singleFieldBuilder6 == null) {
                    twoWayChannelMessage.provideContract_ = this.provideContract_;
                } else {
                    twoWayChannelMessage.provideContract_ = singleFieldBuilder6.build();
                }
                if ((i & 128) == 128) {
                    i2 |= 128;
                }
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder7 = this.updatePaymentBuilder_;
                if (singleFieldBuilder7 == null) {
                    twoWayChannelMessage.updatePayment_ = this.updatePayment_;
                } else {
                    twoWayChannelMessage.updatePayment_ = singleFieldBuilder7.build();
                }
                if ((i & 256) == 256) {
                    i2 |= 256;
                }
                SingleFieldBuilder<PaymentAck, Builder, PaymentAckOrBuilder> singleFieldBuilder8 = this.paymentAckBuilder_;
                if (singleFieldBuilder8 == null) {
                    twoWayChannelMessage.paymentAck_ = this.paymentAck_;
                } else {
                    twoWayChannelMessage.paymentAck_ = singleFieldBuilder8.build();
                }
                if ((i & 512) == 512) {
                    i2 |= 512;
                }
                SingleFieldBuilder<Settlement, Builder, SettlementOrBuilder> singleFieldBuilder9 = this.settlementBuilder_;
                if (singleFieldBuilder9 == null) {
                    twoWayChannelMessage.settlement_ = this.settlement_;
                } else {
                    twoWayChannelMessage.settlement_ = singleFieldBuilder9.build();
                }
                if ((i & 1024) == 1024) {
                    i2 |= 1024;
                }
                SingleFieldBuilder<Error, Builder, ErrorOrBuilder> singleFieldBuilder10 = this.errorBuilder_;
                if (singleFieldBuilder10 == null) {
                    twoWayChannelMessage.error_ = this.error_;
                } else {
                    twoWayChannelMessage.error_ = singleFieldBuilder10.build();
                }
                twoWayChannelMessage.bitField0_ = i2;
                onBuilt();
                return twoWayChannelMessage;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof TwoWayChannelMessage) {
                    return mergeFrom((TwoWayChannelMessage) message);
                }
                TwoWayChannelMessage.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(TwoWayChannelMessage twoWayChannelMessage) {
                if (twoWayChannelMessage == TwoWayChannelMessage.getDefaultInstance()) {
                    return this;
                }
                if (twoWayChannelMessage.hasType()) {
                    setType(twoWayChannelMessage.getType());
                }
                if (twoWayChannelMessage.hasClientVersion()) {
                    mergeClientVersion(twoWayChannelMessage.getClientVersion());
                }
                if (twoWayChannelMessage.hasServerVersion()) {
                    mergeServerVersion(twoWayChannelMessage.getServerVersion());
                }
                if (twoWayChannelMessage.hasInitiate()) {
                    mergeInitiate(twoWayChannelMessage.getInitiate());
                }
                if (twoWayChannelMessage.hasProvideRefund()) {
                    mergeProvideRefund(twoWayChannelMessage.getProvideRefund());
                }
                if (twoWayChannelMessage.hasReturnRefund()) {
                    mergeReturnRefund(twoWayChannelMessage.getReturnRefund());
                }
                if (twoWayChannelMessage.hasProvideContract()) {
                    mergeProvideContract(twoWayChannelMessage.getProvideContract());
                }
                if (twoWayChannelMessage.hasUpdatePayment()) {
                    mergeUpdatePayment(twoWayChannelMessage.getUpdatePayment());
                }
                if (twoWayChannelMessage.hasPaymentAck()) {
                    mergePaymentAck(twoWayChannelMessage.getPaymentAck());
                }
                if (twoWayChannelMessage.hasSettlement()) {
                    mergeSettlement(twoWayChannelMessage.getSettlement());
                }
                if (twoWayChannelMessage.hasError()) {
                    mergeError(twoWayChannelMessage.getError());
                }
                mergeUnknownFields(twoWayChannelMessage.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (!hasType()) {
                    return false;
                }
                if (hasClientVersion() && !getClientVersion().isInitialized()) {
                    return false;
                }
                if (hasServerVersion() && !getServerVersion().isInitialized()) {
                    return false;
                }
                if (hasInitiate() && !getInitiate().isInitialized()) {
                    return false;
                }
                if (hasProvideRefund() && !getProvideRefund().isInitialized()) {
                    return false;
                }
                if (hasReturnRefund() && !getReturnRefund().isInitialized()) {
                    return false;
                }
                if (hasProvideContract() && !getProvideContract().isInitialized()) {
                    return false;
                }
                if (hasUpdatePayment() && !getUpdatePayment().isInitialized()) {
                    return false;
                }
                if (!hasSettlement() || getSettlement().isInitialized()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                TwoWayChannelMessage twoWayChannelMessage;
                TwoWayChannelMessage twoWayChannelMessage2 = null;
                try {
                    TwoWayChannelMessage twoWayChannelMessage3 = (TwoWayChannelMessage) TwoWayChannelMessage.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (twoWayChannelMessage3 != null) {
                        mergeFrom(twoWayChannelMessage3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    twoWayChannelMessage = (TwoWayChannelMessage) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    twoWayChannelMessage2 = twoWayChannelMessage;
                }
                if (twoWayChannelMessage2 != null) {
                    mergeFrom(twoWayChannelMessage2);
                }
                throw th;
            }

            public boolean hasType() {
                return (this.bitField0_ & 1) == 1;
            }

            public MessageType getType() {
                return this.type_;
            }

            public Builder setType(MessageType messageType) {
                if (messageType != null) {
                    this.bitField0_ |= 1;
                    this.type_ = messageType;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearType() {
                this.bitField0_ &= -2;
                this.type_ = MessageType.CLIENT_VERSION;
                onChanged();
                return this;
            }

            public boolean hasClientVersion() {
                return (this.bitField0_ & 2) == 2;
            }

            public ClientVersion getClientVersion() {
                SingleFieldBuilder<ClientVersion, Builder, ClientVersionOrBuilder> singleFieldBuilder = this.clientVersionBuilder_;
                if (singleFieldBuilder == null) {
                    return this.clientVersion_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setClientVersion(ClientVersion clientVersion) {
                SingleFieldBuilder<ClientVersion, Builder, ClientVersionOrBuilder> singleFieldBuilder = this.clientVersionBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(clientVersion);
                } else if (clientVersion != null) {
                    this.clientVersion_ = clientVersion;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setClientVersion(Builder builder) {
                SingleFieldBuilder<ClientVersion, Builder, ClientVersionOrBuilder> singleFieldBuilder = this.clientVersionBuilder_;
                if (singleFieldBuilder == null) {
                    this.clientVersion_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeClientVersion(ClientVersion clientVersion) {
                SingleFieldBuilder<ClientVersion, Builder, ClientVersionOrBuilder> singleFieldBuilder = this.clientVersionBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 2) != 2 || this.clientVersion_ == ClientVersion.getDefaultInstance()) {
                        this.clientVersion_ = clientVersion;
                    } else {
                        this.clientVersion_ = ClientVersion.newBuilder(this.clientVersion_).mergeFrom(clientVersion).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(clientVersion);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearClientVersion() {
                SingleFieldBuilder<ClientVersion, Builder, ClientVersionOrBuilder> singleFieldBuilder = this.clientVersionBuilder_;
                if (singleFieldBuilder == null) {
                    this.clientVersion_ = ClientVersion.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            public Builder getClientVersionBuilder() {
                this.bitField0_ |= 2;
                onChanged();
                return getClientVersionFieldBuilder().getBuilder();
            }

            public ClientVersionOrBuilder getClientVersionOrBuilder() {
                SingleFieldBuilder<ClientVersion, Builder, ClientVersionOrBuilder> singleFieldBuilder = this.clientVersionBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.clientVersion_;
            }

            private SingleFieldBuilder<ClientVersion, Builder, ClientVersionOrBuilder> getClientVersionFieldBuilder() {
                if (this.clientVersionBuilder_ == null) {
                    this.clientVersionBuilder_ = new SingleFieldBuilder<>(getClientVersion(), getParentForChildren(), isClean());
                    this.clientVersion_ = null;
                }
                return this.clientVersionBuilder_;
            }

            public boolean hasServerVersion() {
                return (this.bitField0_ & 4) == 4;
            }

            public ServerVersion getServerVersion() {
                SingleFieldBuilder<ServerVersion, Builder, ServerVersionOrBuilder> singleFieldBuilder = this.serverVersionBuilder_;
                if (singleFieldBuilder == null) {
                    return this.serverVersion_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setServerVersion(ServerVersion serverVersion) {
                SingleFieldBuilder<ServerVersion, Builder, ServerVersionOrBuilder> singleFieldBuilder = this.serverVersionBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(serverVersion);
                } else if (serverVersion != null) {
                    this.serverVersion_ = serverVersion;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setServerVersion(Builder builder) {
                SingleFieldBuilder<ServerVersion, Builder, ServerVersionOrBuilder> singleFieldBuilder = this.serverVersionBuilder_;
                if (singleFieldBuilder == null) {
                    this.serverVersion_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeServerVersion(ServerVersion serverVersion) {
                SingleFieldBuilder<ServerVersion, Builder, ServerVersionOrBuilder> singleFieldBuilder = this.serverVersionBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 4) != 4 || this.serverVersion_ == ServerVersion.getDefaultInstance()) {
                        this.serverVersion_ = serverVersion;
                    } else {
                        this.serverVersion_ = ServerVersion.newBuilder(this.serverVersion_).mergeFrom(serverVersion).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(serverVersion);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder clearServerVersion() {
                SingleFieldBuilder<ServerVersion, Builder, ServerVersionOrBuilder> singleFieldBuilder = this.serverVersionBuilder_;
                if (singleFieldBuilder == null) {
                    this.serverVersion_ = ServerVersion.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -5;
                return this;
            }

            public Builder getServerVersionBuilder() {
                this.bitField0_ |= 4;
                onChanged();
                return getServerVersionFieldBuilder().getBuilder();
            }

            public ServerVersionOrBuilder getServerVersionOrBuilder() {
                SingleFieldBuilder<ServerVersion, Builder, ServerVersionOrBuilder> singleFieldBuilder = this.serverVersionBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.serverVersion_;
            }

            private SingleFieldBuilder<ServerVersion, Builder, ServerVersionOrBuilder> getServerVersionFieldBuilder() {
                if (this.serverVersionBuilder_ == null) {
                    this.serverVersionBuilder_ = new SingleFieldBuilder<>(getServerVersion(), getParentForChildren(), isClean());
                    this.serverVersion_ = null;
                }
                return this.serverVersionBuilder_;
            }

            public boolean hasInitiate() {
                return (this.bitField0_ & 8) == 8;
            }

            public Initiate getInitiate() {
                SingleFieldBuilder<Initiate, Builder, InitiateOrBuilder> singleFieldBuilder = this.initiateBuilder_;
                if (singleFieldBuilder == null) {
                    return this.initiate_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setInitiate(Initiate initiate) {
                SingleFieldBuilder<Initiate, Builder, InitiateOrBuilder> singleFieldBuilder = this.initiateBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(initiate);
                } else if (initiate != null) {
                    this.initiate_ = initiate;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setInitiate(Builder builder) {
                SingleFieldBuilder<Initiate, Builder, InitiateOrBuilder> singleFieldBuilder = this.initiateBuilder_;
                if (singleFieldBuilder == null) {
                    this.initiate_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeInitiate(Initiate initiate) {
                SingleFieldBuilder<Initiate, Builder, InitiateOrBuilder> singleFieldBuilder = this.initiateBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 8) != 8 || this.initiate_ == Initiate.getDefaultInstance()) {
                        this.initiate_ = initiate;
                    } else {
                        this.initiate_ = Initiate.newBuilder(this.initiate_).mergeFrom(initiate).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(initiate);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder clearInitiate() {
                SingleFieldBuilder<Initiate, Builder, InitiateOrBuilder> singleFieldBuilder = this.initiateBuilder_;
                if (singleFieldBuilder == null) {
                    this.initiate_ = Initiate.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -9;
                return this;
            }

            public Builder getInitiateBuilder() {
                this.bitField0_ |= 8;
                onChanged();
                return getInitiateFieldBuilder().getBuilder();
            }

            public InitiateOrBuilder getInitiateOrBuilder() {
                SingleFieldBuilder<Initiate, Builder, InitiateOrBuilder> singleFieldBuilder = this.initiateBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.initiate_;
            }

            private SingleFieldBuilder<Initiate, Builder, InitiateOrBuilder> getInitiateFieldBuilder() {
                if (this.initiateBuilder_ == null) {
                    this.initiateBuilder_ = new SingleFieldBuilder<>(getInitiate(), getParentForChildren(), isClean());
                    this.initiate_ = null;
                }
                return this.initiateBuilder_;
            }

            public boolean hasProvideRefund() {
                return (this.bitField0_ & 16) == 16;
            }

            public ProvideRefund getProvideRefund() {
                SingleFieldBuilder<ProvideRefund, Builder, ProvideRefundOrBuilder> singleFieldBuilder = this.provideRefundBuilder_;
                if (singleFieldBuilder == null) {
                    return this.provideRefund_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setProvideRefund(ProvideRefund provideRefund) {
                SingleFieldBuilder<ProvideRefund, Builder, ProvideRefundOrBuilder> singleFieldBuilder = this.provideRefundBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(provideRefund);
                } else if (provideRefund != null) {
                    this.provideRefund_ = provideRefund;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                return this;
            }

            public Builder setProvideRefund(Builder builder) {
                SingleFieldBuilder<ProvideRefund, Builder, ProvideRefundOrBuilder> singleFieldBuilder = this.provideRefundBuilder_;
                if (singleFieldBuilder == null) {
                    this.provideRefund_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 16;
                return this;
            }

            public Builder mergeProvideRefund(ProvideRefund provideRefund) {
                SingleFieldBuilder<ProvideRefund, Builder, ProvideRefundOrBuilder> singleFieldBuilder = this.provideRefundBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 16) != 16 || this.provideRefund_ == ProvideRefund.getDefaultInstance()) {
                        this.provideRefund_ = provideRefund;
                    } else {
                        this.provideRefund_ = ProvideRefund.newBuilder(this.provideRefund_).mergeFrom(provideRefund).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(provideRefund);
                }
                this.bitField0_ |= 16;
                return this;
            }

            public Builder clearProvideRefund() {
                SingleFieldBuilder<ProvideRefund, Builder, ProvideRefundOrBuilder> singleFieldBuilder = this.provideRefundBuilder_;
                if (singleFieldBuilder == null) {
                    this.provideRefund_ = ProvideRefund.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -17;
                return this;
            }

            public Builder getProvideRefundBuilder() {
                this.bitField0_ |= 16;
                onChanged();
                return getProvideRefundFieldBuilder().getBuilder();
            }

            public ProvideRefundOrBuilder getProvideRefundOrBuilder() {
                SingleFieldBuilder<ProvideRefund, Builder, ProvideRefundOrBuilder> singleFieldBuilder = this.provideRefundBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.provideRefund_;
            }

            private SingleFieldBuilder<ProvideRefund, Builder, ProvideRefundOrBuilder> getProvideRefundFieldBuilder() {
                if (this.provideRefundBuilder_ == null) {
                    this.provideRefundBuilder_ = new SingleFieldBuilder<>(getProvideRefund(), getParentForChildren(), isClean());
                    this.provideRefund_ = null;
                }
                return this.provideRefundBuilder_;
            }

            public boolean hasReturnRefund() {
                return (this.bitField0_ & 32) == 32;
            }

            public ReturnRefund getReturnRefund() {
                SingleFieldBuilder<ReturnRefund, Builder, ReturnRefundOrBuilder> singleFieldBuilder = this.returnRefundBuilder_;
                if (singleFieldBuilder == null) {
                    return this.returnRefund_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setReturnRefund(ReturnRefund returnRefund) {
                SingleFieldBuilder<ReturnRefund, Builder, ReturnRefundOrBuilder> singleFieldBuilder = this.returnRefundBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(returnRefund);
                } else if (returnRefund != null) {
                    this.returnRefund_ = returnRefund;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                return this;
            }

            public Builder setReturnRefund(Builder builder) {
                SingleFieldBuilder<ReturnRefund, Builder, ReturnRefundOrBuilder> singleFieldBuilder = this.returnRefundBuilder_;
                if (singleFieldBuilder == null) {
                    this.returnRefund_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 32;
                return this;
            }

            public Builder mergeReturnRefund(ReturnRefund returnRefund) {
                SingleFieldBuilder<ReturnRefund, Builder, ReturnRefundOrBuilder> singleFieldBuilder = this.returnRefundBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 32) != 32 || this.returnRefund_ == ReturnRefund.getDefaultInstance()) {
                        this.returnRefund_ = returnRefund;
                    } else {
                        this.returnRefund_ = ReturnRefund.newBuilder(this.returnRefund_).mergeFrom(returnRefund).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(returnRefund);
                }
                this.bitField0_ |= 32;
                return this;
            }

            public Builder clearReturnRefund() {
                SingleFieldBuilder<ReturnRefund, Builder, ReturnRefundOrBuilder> singleFieldBuilder = this.returnRefundBuilder_;
                if (singleFieldBuilder == null) {
                    this.returnRefund_ = ReturnRefund.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -33;
                return this;
            }

            public Builder getReturnRefundBuilder() {
                this.bitField0_ |= 32;
                onChanged();
                return getReturnRefundFieldBuilder().getBuilder();
            }

            public ReturnRefundOrBuilder getReturnRefundOrBuilder() {
                SingleFieldBuilder<ReturnRefund, Builder, ReturnRefundOrBuilder> singleFieldBuilder = this.returnRefundBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.returnRefund_;
            }

            private SingleFieldBuilder<ReturnRefund, Builder, ReturnRefundOrBuilder> getReturnRefundFieldBuilder() {
                if (this.returnRefundBuilder_ == null) {
                    this.returnRefundBuilder_ = new SingleFieldBuilder<>(getReturnRefund(), getParentForChildren(), isClean());
                    this.returnRefund_ = null;
                }
                return this.returnRefundBuilder_;
            }

            public boolean hasProvideContract() {
                return (this.bitField0_ & 64) == 64;
            }

            public ProvideContract getProvideContract() {
                SingleFieldBuilder<ProvideContract, Builder, ProvideContractOrBuilder> singleFieldBuilder = this.provideContractBuilder_;
                if (singleFieldBuilder == null) {
                    return this.provideContract_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setProvideContract(ProvideContract provideContract) {
                SingleFieldBuilder<ProvideContract, Builder, ProvideContractOrBuilder> singleFieldBuilder = this.provideContractBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(provideContract);
                } else if (provideContract != null) {
                    this.provideContract_ = provideContract;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder setProvideContract(Builder builder) {
                SingleFieldBuilder<ProvideContract, Builder, ProvideContractOrBuilder> singleFieldBuilder = this.provideContractBuilder_;
                if (singleFieldBuilder == null) {
                    this.provideContract_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder mergeProvideContract(ProvideContract provideContract) {
                SingleFieldBuilder<ProvideContract, Builder, ProvideContractOrBuilder> singleFieldBuilder = this.provideContractBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 64) != 64 || this.provideContract_ == ProvideContract.getDefaultInstance()) {
                        this.provideContract_ = provideContract;
                    } else {
                        this.provideContract_ = ProvideContract.newBuilder(this.provideContract_).mergeFrom(provideContract).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(provideContract);
                }
                this.bitField0_ |= 64;
                return this;
            }

            public Builder clearProvideContract() {
                SingleFieldBuilder<ProvideContract, Builder, ProvideContractOrBuilder> singleFieldBuilder = this.provideContractBuilder_;
                if (singleFieldBuilder == null) {
                    this.provideContract_ = ProvideContract.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -65;
                return this;
            }

            public Builder getProvideContractBuilder() {
                this.bitField0_ |= 64;
                onChanged();
                return getProvideContractFieldBuilder().getBuilder();
            }

            public ProvideContractOrBuilder getProvideContractOrBuilder() {
                SingleFieldBuilder<ProvideContract, Builder, ProvideContractOrBuilder> singleFieldBuilder = this.provideContractBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.provideContract_;
            }

            private SingleFieldBuilder<ProvideContract, Builder, ProvideContractOrBuilder> getProvideContractFieldBuilder() {
                if (this.provideContractBuilder_ == null) {
                    this.provideContractBuilder_ = new SingleFieldBuilder<>(getProvideContract(), getParentForChildren(), isClean());
                    this.provideContract_ = null;
                }
                return this.provideContractBuilder_;
            }

            public boolean hasUpdatePayment() {
                return (this.bitField0_ & 128) == 128;
            }

            public UpdatePayment getUpdatePayment() {
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.updatePaymentBuilder_;
                if (singleFieldBuilder == null) {
                    return this.updatePayment_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setUpdatePayment(UpdatePayment updatePayment) {
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.updatePaymentBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(updatePayment);
                } else if (updatePayment != null) {
                    this.updatePayment_ = updatePayment;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 128;
                return this;
            }

            public Builder setUpdatePayment(Builder builder) {
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.updatePaymentBuilder_;
                if (singleFieldBuilder == null) {
                    this.updatePayment_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 128;
                return this;
            }

            public Builder mergeUpdatePayment(UpdatePayment updatePayment) {
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.updatePaymentBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 128) != 128 || this.updatePayment_ == UpdatePayment.getDefaultInstance()) {
                        this.updatePayment_ = updatePayment;
                    } else {
                        this.updatePayment_ = UpdatePayment.newBuilder(this.updatePayment_).mergeFrom(updatePayment).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(updatePayment);
                }
                this.bitField0_ |= 128;
                return this;
            }

            public Builder clearUpdatePayment() {
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.updatePaymentBuilder_;
                if (singleFieldBuilder == null) {
                    this.updatePayment_ = UpdatePayment.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -129;
                return this;
            }

            public Builder getUpdatePaymentBuilder() {
                this.bitField0_ |= 128;
                onChanged();
                return getUpdatePaymentFieldBuilder().getBuilder();
            }

            public UpdatePaymentOrBuilder getUpdatePaymentOrBuilder() {
                SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> singleFieldBuilder = this.updatePaymentBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.updatePayment_;
            }

            private SingleFieldBuilder<UpdatePayment, Builder, UpdatePaymentOrBuilder> getUpdatePaymentFieldBuilder() {
                if (this.updatePaymentBuilder_ == null) {
                    this.updatePaymentBuilder_ = new SingleFieldBuilder<>(getUpdatePayment(), getParentForChildren(), isClean());
                    this.updatePayment_ = null;
                }
                return this.updatePaymentBuilder_;
            }

            public boolean hasPaymentAck() {
                return (this.bitField0_ & 256) == 256;
            }

            public PaymentAck getPaymentAck() {
                SingleFieldBuilder<PaymentAck, Builder, PaymentAckOrBuilder> singleFieldBuilder = this.paymentAckBuilder_;
                if (singleFieldBuilder == null) {
                    return this.paymentAck_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setPaymentAck(PaymentAck paymentAck) {
                SingleFieldBuilder<PaymentAck, Builder, PaymentAckOrBuilder> singleFieldBuilder = this.paymentAckBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(paymentAck);
                } else if (paymentAck != null) {
                    this.paymentAck_ = paymentAck;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder setPaymentAck(Builder builder) {
                SingleFieldBuilder<PaymentAck, Builder, PaymentAckOrBuilder> singleFieldBuilder = this.paymentAckBuilder_;
                if (singleFieldBuilder == null) {
                    this.paymentAck_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder mergePaymentAck(PaymentAck paymentAck) {
                SingleFieldBuilder<PaymentAck, Builder, PaymentAckOrBuilder> singleFieldBuilder = this.paymentAckBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 256) != 256 || this.paymentAck_ == PaymentAck.getDefaultInstance()) {
                        this.paymentAck_ = paymentAck;
                    } else {
                        this.paymentAck_ = PaymentAck.newBuilder(this.paymentAck_).mergeFrom(paymentAck).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(paymentAck);
                }
                this.bitField0_ |= 256;
                return this;
            }

            public Builder clearPaymentAck() {
                SingleFieldBuilder<PaymentAck, Builder, PaymentAckOrBuilder> singleFieldBuilder = this.paymentAckBuilder_;
                if (singleFieldBuilder == null) {
                    this.paymentAck_ = PaymentAck.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -257;
                return this;
            }

            public Builder getPaymentAckBuilder() {
                this.bitField0_ |= 256;
                onChanged();
                return getPaymentAckFieldBuilder().getBuilder();
            }

            public PaymentAckOrBuilder getPaymentAckOrBuilder() {
                SingleFieldBuilder<PaymentAck, Builder, PaymentAckOrBuilder> singleFieldBuilder = this.paymentAckBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.paymentAck_;
            }

            private SingleFieldBuilder<PaymentAck, Builder, PaymentAckOrBuilder> getPaymentAckFieldBuilder() {
                if (this.paymentAckBuilder_ == null) {
                    this.paymentAckBuilder_ = new SingleFieldBuilder<>(getPaymentAck(), getParentForChildren(), isClean());
                    this.paymentAck_ = null;
                }
                return this.paymentAckBuilder_;
            }

            public boolean hasSettlement() {
                return (this.bitField0_ & 512) == 512;
            }

            public Settlement getSettlement() {
                SingleFieldBuilder<Settlement, Builder, SettlementOrBuilder> singleFieldBuilder = this.settlementBuilder_;
                if (singleFieldBuilder == null) {
                    return this.settlement_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setSettlement(Settlement settlement) {
                SingleFieldBuilder<Settlement, Builder, SettlementOrBuilder> singleFieldBuilder = this.settlementBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(settlement);
                } else if (settlement != null) {
                    this.settlement_ = settlement;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 512;
                return this;
            }

            public Builder setSettlement(Builder builder) {
                SingleFieldBuilder<Settlement, Builder, SettlementOrBuilder> singleFieldBuilder = this.settlementBuilder_;
                if (singleFieldBuilder == null) {
                    this.settlement_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 512;
                return this;
            }

            public Builder mergeSettlement(Settlement settlement) {
                SingleFieldBuilder<Settlement, Builder, SettlementOrBuilder> singleFieldBuilder = this.settlementBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 512) != 512 || this.settlement_ == Settlement.getDefaultInstance()) {
                        this.settlement_ = settlement;
                    } else {
                        this.settlement_ = Settlement.newBuilder(this.settlement_).mergeFrom(settlement).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(settlement);
                }
                this.bitField0_ |= 512;
                return this;
            }

            public Builder clearSettlement() {
                SingleFieldBuilder<Settlement, Builder, SettlementOrBuilder> singleFieldBuilder = this.settlementBuilder_;
                if (singleFieldBuilder == null) {
                    this.settlement_ = Settlement.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -513;
                return this;
            }

            public Builder getSettlementBuilder() {
                this.bitField0_ |= 512;
                onChanged();
                return getSettlementFieldBuilder().getBuilder();
            }

            public SettlementOrBuilder getSettlementOrBuilder() {
                SingleFieldBuilder<Settlement, Builder, SettlementOrBuilder> singleFieldBuilder = this.settlementBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.settlement_;
            }

            private SingleFieldBuilder<Settlement, Builder, SettlementOrBuilder> getSettlementFieldBuilder() {
                if (this.settlementBuilder_ == null) {
                    this.settlementBuilder_ = new SingleFieldBuilder<>(getSettlement(), getParentForChildren(), isClean());
                    this.settlement_ = null;
                }
                return this.settlementBuilder_;
            }

            public boolean hasError() {
                return (this.bitField0_ & 1024) == 1024;
            }

            public Error getError() {
                SingleFieldBuilder<Error, Builder, ErrorOrBuilder> singleFieldBuilder = this.errorBuilder_;
                if (singleFieldBuilder == null) {
                    return this.error_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setError(Error error) {
                SingleFieldBuilder<Error, Builder, ErrorOrBuilder> singleFieldBuilder = this.errorBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(error);
                } else if (error != null) {
                    this.error_ = error;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1024;
                return this;
            }

            public Builder setError(Builder builder) {
                SingleFieldBuilder<Error, Builder, ErrorOrBuilder> singleFieldBuilder = this.errorBuilder_;
                if (singleFieldBuilder == null) {
                    this.error_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 1024;
                return this;
            }

            public Builder mergeError(Error error) {
                SingleFieldBuilder<Error, Builder, ErrorOrBuilder> singleFieldBuilder = this.errorBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 1024) != 1024 || this.error_ == Error.getDefaultInstance()) {
                        this.error_ = error;
                    } else {
                        this.error_ = Error.newBuilder(this.error_).mergeFrom(error).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(error);
                }
                this.bitField0_ |= 1024;
                return this;
            }

            public Builder clearError() {
                SingleFieldBuilder<Error, Builder, ErrorOrBuilder> singleFieldBuilder = this.errorBuilder_;
                if (singleFieldBuilder == null) {
                    this.error_ = Error.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= RESULT.E_TEEKM_JSON_OUT_ADDR_LEN;
                return this;
            }

            public Builder getErrorBuilder() {
                this.bitField0_ |= 1024;
                onChanged();
                return getErrorFieldBuilder().getBuilder();
            }

            public ErrorOrBuilder getErrorOrBuilder() {
                SingleFieldBuilder<Error, Builder, ErrorOrBuilder> singleFieldBuilder = this.errorBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.error_;
            }

            private SingleFieldBuilder<Error, Builder, ErrorOrBuilder> getErrorFieldBuilder() {
                if (this.errorBuilder_ == null) {
                    this.errorBuilder_ = new SingleFieldBuilder<>(getError(), getParentForChildren(), isClean());
                    this.error_ = null;
                }
                return this.errorBuilder_;
            }
        }

        public enum MessageType implements ProtocolMessageEnum {
            CLIENT_VERSION(0, 1),
            SERVER_VERSION(1, 2),
            INITIATE(2, 3),
            PROVIDE_REFUND(3, 4),
            RETURN_REFUND(4, 5),
            PROVIDE_CONTRACT(5, 6),
            CHANNEL_OPEN(6, 7),
            UPDATE_PAYMENT(7, 8),
            PAYMENT_ACK(8, 11),
            CLOSE(9, 9),
            ERROR(10, 10);
            
            public static final int CHANNEL_OPEN_VALUE = 7;
            public static final int CLIENT_VERSION_VALUE = 1;
            public static final int CLOSE_VALUE = 9;
            public static final int ERROR_VALUE = 10;
            public static final int INITIATE_VALUE = 3;
            public static final int PAYMENT_ACK_VALUE = 11;
            public static final int PROVIDE_CONTRACT_VALUE = 6;
            public static final int PROVIDE_REFUND_VALUE = 4;
            public static final int RETURN_REFUND_VALUE = 5;
            public static final int SERVER_VERSION_VALUE = 2;
            public static final int UPDATE_PAYMENT_VALUE = 8;
            private static final MessageType[] VALUES = null;
            private static EnumLiteMap<MessageType> internalValueMap;
            private final int index;
            private final int value;

            static {
                internalValueMap = new EnumLiteMap<MessageType>() {
                    public MessageType findValueByNumber(int i) {
                        return MessageType.valueOf(i);
                    }
                };
                VALUES = values();
            }

            public final int getNumber() {
                return this.value;
            }

            public static MessageType valueOf(int i) {
                switch (i) {
                    case 1:
                        return CLIENT_VERSION;
                    case 2:
                        return SERVER_VERSION;
                    case 3:
                        return INITIATE;
                    case 4:
                        return PROVIDE_REFUND;
                    case 5:
                        return RETURN_REFUND;
                    case 6:
                        return PROVIDE_CONTRACT;
                    case 7:
                        return CHANNEL_OPEN;
                    case 8:
                        return UPDATE_PAYMENT;
                    case 9:
                        return CLOSE;
                    case 10:
                        return ERROR;
                    case 11:
                        return PAYMENT_ACK;
                    default:
                        return null;
                }
            }

            public static EnumLiteMap<MessageType> internalGetValueMap() {
                return internalValueMap;
            }

            public final EnumValueDescriptor getValueDescriptor() {
                return (EnumValueDescriptor) getDescriptor().getValues().get(this.index);
            }

            public final EnumDescriptor getDescriptorForType() {
                return getDescriptor();
            }

            public static final EnumDescriptor getDescriptor() {
                return (EnumDescriptor) TwoWayChannelMessage.getDescriptor().getEnumTypes().get(0);
            }

            public static MessageType valueOf(EnumValueDescriptor enumValueDescriptor) {
                if (enumValueDescriptor.getType() == getDescriptor()) {
                    return VALUES[enumValueDescriptor.getIndex()];
                }
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }

            private MessageType(int i, int i2) {
                this.index = i;
                this.value = i2;
            }
        }

        private TwoWayChannelMessage(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private TwoWayChannelMessage(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static TwoWayChannelMessage getDefaultInstance() {
            return defaultInstance;
        }

        public TwoWayChannelMessage getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage, com.google.protobuf.MessageLite] */
        /* JADX WARNING: type inference failed for: r4v0 */
        /* JADX WARNING: type inference failed for: r4v2, types: [org.bitcoin.paymentchannel.Protos$ClientVersion$Builder] */
        /* JADX WARNING: type inference failed for: r4v3, types: [org.bitcoin.paymentchannel.Protos$ClientVersion$Builder] */
        /* JADX WARNING: type inference failed for: r4v4, types: [org.bitcoin.paymentchannel.Protos$ServerVersion$Builder] */
        /* JADX WARNING: type inference failed for: r4v5, types: [org.bitcoin.paymentchannel.Protos$ServerVersion$Builder] */
        /* JADX WARNING: type inference failed for: r4v6, types: [org.bitcoin.paymentchannel.Protos$Initiate$Builder] */
        /* JADX WARNING: type inference failed for: r4v7, types: [org.bitcoin.paymentchannel.Protos$Initiate$Builder] */
        /* JADX WARNING: type inference failed for: r4v8, types: [org.bitcoin.paymentchannel.Protos$ProvideRefund$Builder] */
        /* JADX WARNING: type inference failed for: r4v9, types: [org.bitcoin.paymentchannel.Protos$ProvideRefund$Builder] */
        /* JADX WARNING: type inference failed for: r4v10, types: [org.bitcoin.paymentchannel.Protos$ReturnRefund$Builder] */
        /* JADX WARNING: type inference failed for: r4v11, types: [org.bitcoin.paymentchannel.Protos$ReturnRefund$Builder] */
        /* JADX WARNING: type inference failed for: r4v12, types: [org.bitcoin.paymentchannel.Protos$ProvideContract$Builder] */
        /* JADX WARNING: type inference failed for: r4v13, types: [org.bitcoin.paymentchannel.Protos$ProvideContract$Builder] */
        /* JADX WARNING: type inference failed for: r4v14, types: [org.bitcoin.paymentchannel.Protos$UpdatePayment$Builder] */
        /* JADX WARNING: type inference failed for: r4v15, types: [org.bitcoin.paymentchannel.Protos$UpdatePayment$Builder] */
        /* JADX WARNING: type inference failed for: r4v16, types: [org.bitcoin.paymentchannel.Protos$Settlement$Builder] */
        /* JADX WARNING: type inference failed for: r4v17, types: [org.bitcoin.paymentchannel.Protos$Settlement$Builder] */
        /* JADX WARNING: type inference failed for: r4v18, types: [org.bitcoin.paymentchannel.Protos$Error$Builder] */
        /* JADX WARNING: type inference failed for: r4v19, types: [org.bitcoin.paymentchannel.Protos$Error$Builder] */
        /* JADX WARNING: type inference failed for: r4v20, types: [org.bitcoin.paymentchannel.Protos$PaymentAck$Builder] */
        /* JADX WARNING: type inference failed for: r4v21, types: [org.bitcoin.paymentchannel.Protos$PaymentAck$Builder] */
        /* JADX WARNING: type inference failed for: r4v22 */
        /* JADX WARNING: type inference failed for: r4v23 */
        /* JADX WARNING: type inference failed for: r4v24 */
        /* JADX WARNING: type inference failed for: r4v25 */
        /* JADX WARNING: type inference failed for: r4v26 */
        /* JADX WARNING: type inference failed for: r4v27 */
        /* JADX WARNING: type inference failed for: r4v28 */
        /* JADX WARNING: type inference failed for: r4v29 */
        /* JADX WARNING: type inference failed for: r4v30 */
        /* JADX WARNING: type inference failed for: r4v31 */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r4v0
          assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], org.bitcoin.paymentchannel.Protos$ServerVersion$Builder, org.bitcoin.paymentchannel.Protos$ClientVersion$Builder, org.bitcoin.paymentchannel.Protos$Initiate$Builder, org.bitcoin.paymentchannel.Protos$ProvideRefund$Builder, org.bitcoin.paymentchannel.Protos$ReturnRefund$Builder, org.bitcoin.paymentchannel.Protos$ProvideContract$Builder, org.bitcoin.paymentchannel.Protos$UpdatePayment$Builder, org.bitcoin.paymentchannel.Protos$Settlement$Builder, org.bitcoin.paymentchannel.Protos$Error$Builder, org.bitcoin.paymentchannel.Protos$PaymentAck$Builder]
          uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], org.bitcoin.paymentchannel.Protos$ClientVersion$Builder, org.bitcoin.paymentchannel.Protos$ServerVersion$Builder, org.bitcoin.paymentchannel.Protos$Initiate$Builder, org.bitcoin.paymentchannel.Protos$ProvideRefund$Builder, org.bitcoin.paymentchannel.Protos$ReturnRefund$Builder, org.bitcoin.paymentchannel.Protos$ProvideContract$Builder, org.bitcoin.paymentchannel.Protos$UpdatePayment$Builder, org.bitcoin.paymentchannel.Protos$Settlement$Builder, org.bitcoin.paymentchannel.Protos$Error$Builder, org.bitcoin.paymentchannel.Protos$PaymentAck$Builder]
          mth insns count: 229
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 11 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private TwoWayChannelMessage(com.google.protobuf.CodedInputStream r6, com.google.protobuf.ExtensionRegistryLite r7) throws com.google.protobuf.InvalidProtocolBufferException {
            /*
                r5 = this;
                r5.<init>()
                r0 = -1
                r5.memoizedIsInitialized = r0
                r5.memoizedSerializedSize = r0
                r5.initFields()
                com.google.protobuf.UnknownFieldSet$Builder r0 = com.google.protobuf.UnknownFieldSet.newBuilder()
                r1 = 0
            L_0x0010:
                if (r1 != 0) goto L_0x020a
                int r2 = r6.readTag()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r3 = 1
                r4 = 0
                switch(r2) {
                    case 0: goto L_0x01e3;
                    case 8: goto L_0x01cb;
                    case 18: goto L_0x01a1;
                    case 26: goto L_0x0177;
                    case 34: goto L_0x014c;
                    case 42: goto L_0x0121;
                    case 50: goto L_0x00f6;
                    case 58: goto L_0x00cb;
                    case 66: goto L_0x00a0;
                    case 74: goto L_0x0075;
                    case 82: goto L_0x004b;
                    case 90: goto L_0x0021;
                    default: goto L_0x001b;
                }     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x001b:
                boolean r2 = r5.parseUnknownField(r6, r0, r7, r2)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                goto L_0x01e6
            L_0x0021:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r3 = 256(0x100, float:3.59E-43)
                r2 = r2 & r3
                if (r2 != r3) goto L_0x002e
                org.bitcoin.paymentchannel.Protos$PaymentAck r2 = r5.paymentAck_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$PaymentAck$Builder r4 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x002e:
                com.google.protobuf.Parser<org.bitcoin.paymentchannel.Protos$PaymentAck> r2 = org.bitcoin.paymentchannel.Protos.PaymentAck.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                com.google.protobuf.MessageLite r2 = r6.readMessage(r2, r7)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$PaymentAck r2 = (org.bitcoin.paymentchannel.Protos.PaymentAck) r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.paymentAck_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                if (r4 == 0) goto L_0x0045
                org.bitcoin.paymentchannel.Protos$PaymentAck r2 = r5.paymentAck_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r4.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$PaymentAck r2 = r4.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.paymentAck_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x0045:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r2 = r2 | r3
                r5.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                goto L_0x0010
            L_0x004b:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r3 = 1024(0x400, float:1.435E-42)
                r2 = r2 & r3
                if (r2 != r3) goto L_0x0058
                org.bitcoin.paymentchannel.Protos$Error r2 = r5.error_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$Error$Builder r4 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x0058:
                com.google.protobuf.Parser<org.bitcoin.paymentchannel.Protos$Error> r2 = org.bitcoin.paymentchannel.Protos.Error.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                com.google.protobuf.MessageLite r2 = r6.readMessage(r2, r7)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$Error r2 = (org.bitcoin.paymentchannel.Protos.Error) r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.error_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                if (r4 == 0) goto L_0x006f
                org.bitcoin.paymentchannel.Protos$Error r2 = r5.error_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r4.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$Error r2 = r4.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.error_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x006f:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r2 = r2 | r3
                r5.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                goto L_0x0010
            L_0x0075:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r3 = 512(0x200, float:7.175E-43)
                r2 = r2 & r3
                if (r2 != r3) goto L_0x0082
                org.bitcoin.paymentchannel.Protos$Settlement r2 = r5.settlement_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$Settlement$Builder r4 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x0082:
                com.google.protobuf.Parser<org.bitcoin.paymentchannel.Protos$Settlement> r2 = org.bitcoin.paymentchannel.Protos.Settlement.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                com.google.protobuf.MessageLite r2 = r6.readMessage(r2, r7)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$Settlement r2 = (org.bitcoin.paymentchannel.Protos.Settlement) r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.settlement_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                if (r4 == 0) goto L_0x0099
                org.bitcoin.paymentchannel.Protos$Settlement r2 = r5.settlement_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r4.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$Settlement r2 = r4.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.settlement_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x0099:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r2 = r2 | r3
                r5.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                goto L_0x0010
            L_0x00a0:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r3 = 128(0x80, float:1.794E-43)
                r2 = r2 & r3
                if (r2 != r3) goto L_0x00ad
                org.bitcoin.paymentchannel.Protos$UpdatePayment r2 = r5.updatePayment_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$UpdatePayment$Builder r4 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x00ad:
                com.google.protobuf.Parser<org.bitcoin.paymentchannel.Protos$UpdatePayment> r2 = org.bitcoin.paymentchannel.Protos.UpdatePayment.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                com.google.protobuf.MessageLite r2 = r6.readMessage(r2, r7)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$UpdatePayment r2 = (org.bitcoin.paymentchannel.Protos.UpdatePayment) r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.updatePayment_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                if (r4 == 0) goto L_0x00c4
                org.bitcoin.paymentchannel.Protos$UpdatePayment r2 = r5.updatePayment_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r4.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$UpdatePayment r2 = r4.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.updatePayment_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x00c4:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r2 = r2 | r3
                r5.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                goto L_0x0010
            L_0x00cb:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r3 = 64
                r2 = r2 & r3
                if (r2 != r3) goto L_0x00d8
                org.bitcoin.paymentchannel.Protos$ProvideContract r2 = r5.provideContract_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ProvideContract$Builder r4 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x00d8:
                com.google.protobuf.Parser<org.bitcoin.paymentchannel.Protos$ProvideContract> r2 = org.bitcoin.paymentchannel.Protos.ProvideContract.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                com.google.protobuf.MessageLite r2 = r6.readMessage(r2, r7)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ProvideContract r2 = (org.bitcoin.paymentchannel.Protos.ProvideContract) r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.provideContract_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                if (r4 == 0) goto L_0x00ef
                org.bitcoin.paymentchannel.Protos$ProvideContract r2 = r5.provideContract_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r4.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ProvideContract r2 = r4.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.provideContract_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x00ef:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r2 = r2 | r3
                r5.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                goto L_0x0010
            L_0x00f6:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r3 = 32
                r2 = r2 & r3
                if (r2 != r3) goto L_0x0103
                org.bitcoin.paymentchannel.Protos$ReturnRefund r2 = r5.returnRefund_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ReturnRefund$Builder r4 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x0103:
                com.google.protobuf.Parser<org.bitcoin.paymentchannel.Protos$ReturnRefund> r2 = org.bitcoin.paymentchannel.Protos.ReturnRefund.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                com.google.protobuf.MessageLite r2 = r6.readMessage(r2, r7)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ReturnRefund r2 = (org.bitcoin.paymentchannel.Protos.ReturnRefund) r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.returnRefund_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                if (r4 == 0) goto L_0x011a
                org.bitcoin.paymentchannel.Protos$ReturnRefund r2 = r5.returnRefund_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r4.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ReturnRefund r2 = r4.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.returnRefund_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x011a:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r2 = r2 | r3
                r5.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                goto L_0x0010
            L_0x0121:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r3 = 16
                r2 = r2 & r3
                if (r2 != r3) goto L_0x012e
                org.bitcoin.paymentchannel.Protos$ProvideRefund r2 = r5.provideRefund_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ProvideRefund$Builder r4 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x012e:
                com.google.protobuf.Parser<org.bitcoin.paymentchannel.Protos$ProvideRefund> r2 = org.bitcoin.paymentchannel.Protos.ProvideRefund.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                com.google.protobuf.MessageLite r2 = r6.readMessage(r2, r7)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ProvideRefund r2 = (org.bitcoin.paymentchannel.Protos.ProvideRefund) r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.provideRefund_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                if (r4 == 0) goto L_0x0145
                org.bitcoin.paymentchannel.Protos$ProvideRefund r2 = r5.provideRefund_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r4.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ProvideRefund r2 = r4.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.provideRefund_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x0145:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r2 = r2 | r3
                r5.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                goto L_0x0010
            L_0x014c:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r3 = 8
                r2 = r2 & r3
                if (r2 != r3) goto L_0x0159
                org.bitcoin.paymentchannel.Protos$Initiate r2 = r5.initiate_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$Initiate$Builder r4 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x0159:
                com.google.protobuf.Parser<org.bitcoin.paymentchannel.Protos$Initiate> r2 = org.bitcoin.paymentchannel.Protos.Initiate.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                com.google.protobuf.MessageLite r2 = r6.readMessage(r2, r7)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$Initiate r2 = (org.bitcoin.paymentchannel.Protos.Initiate) r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.initiate_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                if (r4 == 0) goto L_0x0170
                org.bitcoin.paymentchannel.Protos$Initiate r2 = r5.initiate_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r4.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$Initiate r2 = r4.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.initiate_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x0170:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r2 = r2 | r3
                r5.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                goto L_0x0010
            L_0x0177:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r3 = 4
                r2 = r2 & r3
                if (r2 != r3) goto L_0x0183
                org.bitcoin.paymentchannel.Protos$ServerVersion r2 = r5.serverVersion_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ServerVersion$Builder r4 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x0183:
                com.google.protobuf.Parser<org.bitcoin.paymentchannel.Protos$ServerVersion> r2 = org.bitcoin.paymentchannel.Protos.ServerVersion.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                com.google.protobuf.MessageLite r2 = r6.readMessage(r2, r7)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ServerVersion r2 = (org.bitcoin.paymentchannel.Protos.ServerVersion) r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.serverVersion_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                if (r4 == 0) goto L_0x019a
                org.bitcoin.paymentchannel.Protos$ServerVersion r2 = r5.serverVersion_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r4.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ServerVersion r2 = r4.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.serverVersion_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x019a:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r2 = r2 | r3
                r5.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                goto L_0x0010
            L_0x01a1:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r3 = 2
                r2 = r2 & r3
                if (r2 != r3) goto L_0x01ad
                org.bitcoin.paymentchannel.Protos$ClientVersion r2 = r5.clientVersion_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ClientVersion$Builder r4 = r2.toBuilder()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x01ad:
                com.google.protobuf.Parser<org.bitcoin.paymentchannel.Protos$ClientVersion> r2 = org.bitcoin.paymentchannel.Protos.ClientVersion.PARSER     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                com.google.protobuf.MessageLite r2 = r6.readMessage(r2, r7)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ClientVersion r2 = (org.bitcoin.paymentchannel.Protos.ClientVersion) r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.clientVersion_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                if (r4 == 0) goto L_0x01c4
                org.bitcoin.paymentchannel.Protos$ClientVersion r2 = r5.clientVersion_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r4.mergeFrom(r2)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$ClientVersion r2 = r4.buildPartial()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.clientVersion_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
            L_0x01c4:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r2 = r2 | r3
                r5.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                goto L_0x0010
            L_0x01cb:
                int r2 = r6.readEnum()     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r4 = org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.MessageType.valueOf(r2)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                if (r4 != 0) goto L_0x01da
                r0.mergeVarintField(r3, r2)     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                goto L_0x0010
            L_0x01da:
                int r2 = r5.bitField0_     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r2 = r2 | r3
                r5.bitField0_ = r2     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                r5.type_ = r4     // Catch:{ InvalidProtocolBufferException -> 0x01fa, IOException -> 0x01eb }
                goto L_0x0010
            L_0x01e3:
                r1 = 1
                goto L_0x0010
            L_0x01e6:
                if (r2 != 0) goto L_0x0010
                goto L_0x01e3
            L_0x01e9:
                r6 = move-exception
                goto L_0x0200
            L_0x01eb:
                r6 = move-exception
                com.google.protobuf.InvalidProtocolBufferException r7 = new com.google.protobuf.InvalidProtocolBufferException     // Catch:{ all -> 0x01e9 }
                java.lang.String r6 = r6.getMessage()     // Catch:{ all -> 0x01e9 }
                r7.<init>(r6)     // Catch:{ all -> 0x01e9 }
                com.google.protobuf.InvalidProtocolBufferException r6 = r7.setUnfinishedMessage(r5)     // Catch:{ all -> 0x01e9 }
                throw r6     // Catch:{ all -> 0x01e9 }
            L_0x01fa:
                r6 = move-exception
                com.google.protobuf.InvalidProtocolBufferException r6 = r6.setUnfinishedMessage(r5)     // Catch:{ all -> 0x01e9 }
                throw r6     // Catch:{ all -> 0x01e9 }
            L_0x0200:
                com.google.protobuf.UnknownFieldSet r7 = r0.build()
                r5.unknownFields = r7
                r5.makeExtensionsImmutable()
                throw r6
            L_0x020a:
                com.google.protobuf.UnknownFieldSet r6 = r0.build()
                r5.unknownFields = r6
                r5.makeExtensionsImmutable()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.<init>(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):void");
        }

        public static final Descriptor getDescriptor() {
            return Protos.internal_static_paymentchannels_TwoWayChannelMessage_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.f794xb0010c2d.ensureFieldAccessorsInitialized(TwoWayChannelMessage.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<TwoWayChannelMessage> getParserForType() {
            return PARSER;
        }

        public boolean hasType() {
            return (this.bitField0_ & 1) == 1;
        }

        public MessageType getType() {
            return this.type_;
        }

        public boolean hasClientVersion() {
            return (this.bitField0_ & 2) == 2;
        }

        public ClientVersion getClientVersion() {
            return this.clientVersion_;
        }

        public ClientVersionOrBuilder getClientVersionOrBuilder() {
            return this.clientVersion_;
        }

        public boolean hasServerVersion() {
            return (this.bitField0_ & 4) == 4;
        }

        public ServerVersion getServerVersion() {
            return this.serverVersion_;
        }

        public ServerVersionOrBuilder getServerVersionOrBuilder() {
            return this.serverVersion_;
        }

        public boolean hasInitiate() {
            return (this.bitField0_ & 8) == 8;
        }

        public Initiate getInitiate() {
            return this.initiate_;
        }

        public InitiateOrBuilder getInitiateOrBuilder() {
            return this.initiate_;
        }

        public boolean hasProvideRefund() {
            return (this.bitField0_ & 16) == 16;
        }

        public ProvideRefund getProvideRefund() {
            return this.provideRefund_;
        }

        public ProvideRefundOrBuilder getProvideRefundOrBuilder() {
            return this.provideRefund_;
        }

        public boolean hasReturnRefund() {
            return (this.bitField0_ & 32) == 32;
        }

        public ReturnRefund getReturnRefund() {
            return this.returnRefund_;
        }

        public ReturnRefundOrBuilder getReturnRefundOrBuilder() {
            return this.returnRefund_;
        }

        public boolean hasProvideContract() {
            return (this.bitField0_ & 64) == 64;
        }

        public ProvideContract getProvideContract() {
            return this.provideContract_;
        }

        public ProvideContractOrBuilder getProvideContractOrBuilder() {
            return this.provideContract_;
        }

        public boolean hasUpdatePayment() {
            return (this.bitField0_ & 128) == 128;
        }

        public UpdatePayment getUpdatePayment() {
            return this.updatePayment_;
        }

        public UpdatePaymentOrBuilder getUpdatePaymentOrBuilder() {
            return this.updatePayment_;
        }

        public boolean hasPaymentAck() {
            return (this.bitField0_ & 256) == 256;
        }

        public PaymentAck getPaymentAck() {
            return this.paymentAck_;
        }

        public PaymentAckOrBuilder getPaymentAckOrBuilder() {
            return this.paymentAck_;
        }

        public boolean hasSettlement() {
            return (this.bitField0_ & 512) == 512;
        }

        public Settlement getSettlement() {
            return this.settlement_;
        }

        public SettlementOrBuilder getSettlementOrBuilder() {
            return this.settlement_;
        }

        public boolean hasError() {
            return (this.bitField0_ & 1024) == 1024;
        }

        public Error getError() {
            return this.error_;
        }

        public ErrorOrBuilder getErrorOrBuilder() {
            return this.error_;
        }

        private void initFields() {
            this.type_ = MessageType.CLIENT_VERSION;
            this.clientVersion_ = ClientVersion.getDefaultInstance();
            this.serverVersion_ = ServerVersion.getDefaultInstance();
            this.initiate_ = Initiate.getDefaultInstance();
            this.provideRefund_ = ProvideRefund.getDefaultInstance();
            this.returnRefund_ = ReturnRefund.getDefaultInstance();
            this.provideContract_ = ProvideContract.getDefaultInstance();
            this.updatePayment_ = UpdatePayment.getDefaultInstance();
            this.paymentAck_ = PaymentAck.getDefaultInstance();
            this.settlement_ = Settlement.getDefaultInstance();
            this.error_ = Error.getDefaultInstance();
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasType()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (hasClientVersion() && !getClientVersion().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (hasServerVersion() && !getServerVersion().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (hasInitiate() && !getInitiate().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (hasProvideRefund() && !getProvideRefund().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (hasReturnRefund() && !getReturnRefund().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (hasProvideContract() && !getProvideContract().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (hasUpdatePayment() && !getUpdatePayment().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasSettlement() || getSettlement().isInitialized()) {
                this.memoizedIsInitialized = 1;
                return true;
            } else {
                this.memoizedIsInitialized = 0;
                return false;
            }
        }

        /* JADX WARNING: type inference failed for: r1v10, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$PaymentAck] */
        /* JADX WARNING: type inference failed for: r1v11, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$Error] */
        /* JADX WARNING: type inference failed for: r1v12, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$Settlement] */
        /* JADX WARNING: type inference failed for: r0v26, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$UpdatePayment] */
        /* JADX WARNING: type inference failed for: r1v13, types: [org.bitcoin.paymentchannel.Protos$ProvideContract, com.google.protobuf.MessageLite] */
        /* JADX WARNING: type inference failed for: r1v14, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$ReturnRefund] */
        /* JADX WARNING: type inference failed for: r1v15, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$ProvideRefund] */
        /* JADX WARNING: type inference failed for: r0v30, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$Initiate] */
        /* JADX WARNING: type inference failed for: r2v1, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$ServerVersion] */
        /* JADX WARNING: type inference failed for: r0v32, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$ClientVersion] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v10, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$PaymentAck]
          assigns: [org.bitcoin.paymentchannel.Protos$PaymentAck]
          uses: [com.google.protobuf.MessageLite]
          mth insns count: 60
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 10 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void writeTo(com.google.protobuf.CodedOutputStream r4) throws java.io.IOException {
            /*
                r3 = this;
                r3.getSerializedSize()
                int r0 = r3.bitField0_
                r1 = 1
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0012
                org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r0 = r3.type_
                int r0 = r0.getNumber()
                r4.writeEnum(r1, r0)
            L_0x0012:
                int r0 = r3.bitField0_
                r1 = 2
                r0 = r0 & r1
                if (r0 != r1) goto L_0x001d
                org.bitcoin.paymentchannel.Protos$ClientVersion r0 = r3.clientVersion_
                r4.writeMessage(r1, r0)
            L_0x001d:
                int r0 = r3.bitField0_
                r1 = 4
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0029
                r0 = 3
                org.bitcoin.paymentchannel.Protos$ServerVersion r2 = r3.serverVersion_
                r4.writeMessage(r0, r2)
            L_0x0029:
                int r0 = r3.bitField0_
                r2 = 8
                r0 = r0 & r2
                if (r0 != r2) goto L_0x0035
                org.bitcoin.paymentchannel.Protos$Initiate r0 = r3.initiate_
                r4.writeMessage(r1, r0)
            L_0x0035:
                int r0 = r3.bitField0_
                r1 = 16
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0042
                r0 = 5
                org.bitcoin.paymentchannel.Protos$ProvideRefund r1 = r3.provideRefund_
                r4.writeMessage(r0, r1)
            L_0x0042:
                int r0 = r3.bitField0_
                r1 = 32
                r0 = r0 & r1
                if (r0 != r1) goto L_0x004f
                r0 = 6
                org.bitcoin.paymentchannel.Protos$ReturnRefund r1 = r3.returnRefund_
                r4.writeMessage(r0, r1)
            L_0x004f:
                int r0 = r3.bitField0_
                r1 = 64
                r0 = r0 & r1
                if (r0 != r1) goto L_0x005c
                r0 = 7
                org.bitcoin.paymentchannel.Protos$ProvideContract r1 = r3.provideContract_
                r4.writeMessage(r0, r1)
            L_0x005c:
                int r0 = r3.bitField0_
                r1 = 128(0x80, float:1.794E-43)
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0068
                org.bitcoin.paymentchannel.Protos$UpdatePayment r0 = r3.updatePayment_
                r4.writeMessage(r2, r0)
            L_0x0068:
                int r0 = r3.bitField0_
                r1 = 512(0x200, float:7.175E-43)
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0076
                r0 = 9
                org.bitcoin.paymentchannel.Protos$Settlement r1 = r3.settlement_
                r4.writeMessage(r0, r1)
            L_0x0076:
                int r0 = r3.bitField0_
                r1 = 1024(0x400, float:1.435E-42)
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0084
                r0 = 10
                org.bitcoin.paymentchannel.Protos$Error r1 = r3.error_
                r4.writeMessage(r0, r1)
            L_0x0084:
                int r0 = r3.bitField0_
                r1 = 256(0x100, float:3.59E-43)
                r0 = r0 & r1
                if (r0 != r1) goto L_0x0092
                r0 = 11
                org.bitcoin.paymentchannel.Protos$PaymentAck r1 = r3.paymentAck_
                r4.writeMessage(r0, r1)
            L_0x0092:
                com.google.protobuf.UnknownFieldSet r0 = r3.getUnknownFields()
                r0.writeTo(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.writeTo(com.google.protobuf.CodedOutputStream):void");
        }

        /* JADX WARNING: type inference failed for: r2v10, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$PaymentAck] */
        /* JADX WARNING: type inference failed for: r2v11, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$Error] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$Settlement] */
        /* JADX WARNING: type inference failed for: r1v31, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$UpdatePayment] */
        /* JADX WARNING: type inference failed for: r2v13, types: [org.bitcoin.paymentchannel.Protos$ProvideContract, com.google.protobuf.MessageLite] */
        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$ReturnRefund] */
        /* JADX WARNING: type inference failed for: r2v15, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$ProvideRefund] */
        /* JADX WARNING: type inference failed for: r1v39, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$Initiate] */
        /* JADX WARNING: type inference failed for: r3v1, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$ServerVersion] */
        /* JADX WARNING: type inference failed for: r1v43, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$ClientVersion] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v10, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$PaymentAck]
          assigns: [org.bitcoin.paymentchannel.Protos$PaymentAck]
          uses: [com.google.protobuf.MessageLite]
          mth insns count: 76
        	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
         */
        /* JADX WARNING: Unknown variable types count: 10 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int getSerializedSize() {
            /*
                r4 = this;
                int r0 = r4.memoizedSerializedSize
                r1 = -1
                if (r0 == r1) goto L_0x0006
                return r0
            L_0x0006:
                r0 = 0
                int r1 = r4.bitField0_
                r2 = 1
                r1 = r1 & r2
                if (r1 != r2) goto L_0x0018
                org.bitcoin.paymentchannel.Protos$TwoWayChannelMessage$MessageType r1 = r4.type_
                int r1 = r1.getNumber()
                int r1 = com.google.protobuf.CodedOutputStream.computeEnumSize(r2, r1)
                int r0 = r0 + r1
            L_0x0018:
                int r1 = r4.bitField0_
                r2 = 2
                r1 = r1 & r2
                if (r1 != r2) goto L_0x0025
                org.bitcoin.paymentchannel.Protos$ClientVersion r1 = r4.clientVersion_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r2, r1)
                int r0 = r0 + r1
            L_0x0025:
                int r1 = r4.bitField0_
                r2 = 4
                r1 = r1 & r2
                if (r1 != r2) goto L_0x0033
                r1 = 3
                org.bitcoin.paymentchannel.Protos$ServerVersion r3 = r4.serverVersion_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r1, r3)
                int r0 = r0 + r1
            L_0x0033:
                int r1 = r4.bitField0_
                r3 = 8
                r1 = r1 & r3
                if (r1 != r3) goto L_0x0041
                org.bitcoin.paymentchannel.Protos$Initiate r1 = r4.initiate_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r2, r1)
                int r0 = r0 + r1
            L_0x0041:
                int r1 = r4.bitField0_
                r2 = 16
                r1 = r1 & r2
                if (r1 != r2) goto L_0x0050
                r1 = 5
                org.bitcoin.paymentchannel.Protos$ProvideRefund r2 = r4.provideRefund_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r1, r2)
                int r0 = r0 + r1
            L_0x0050:
                int r1 = r4.bitField0_
                r2 = 32
                r1 = r1 & r2
                if (r1 != r2) goto L_0x005f
                r1 = 6
                org.bitcoin.paymentchannel.Protos$ReturnRefund r2 = r4.returnRefund_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r1, r2)
                int r0 = r0 + r1
            L_0x005f:
                int r1 = r4.bitField0_
                r2 = 64
                r1 = r1 & r2
                if (r1 != r2) goto L_0x006e
                r1 = 7
                org.bitcoin.paymentchannel.Protos$ProvideContract r2 = r4.provideContract_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r1, r2)
                int r0 = r0 + r1
            L_0x006e:
                int r1 = r4.bitField0_
                r2 = 128(0x80, float:1.794E-43)
                r1 = r1 & r2
                if (r1 != r2) goto L_0x007c
                org.bitcoin.paymentchannel.Protos$UpdatePayment r1 = r4.updatePayment_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r3, r1)
                int r0 = r0 + r1
            L_0x007c:
                int r1 = r4.bitField0_
                r2 = 512(0x200, float:7.175E-43)
                r1 = r1 & r2
                if (r1 != r2) goto L_0x008c
                r1 = 9
                org.bitcoin.paymentchannel.Protos$Settlement r2 = r4.settlement_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r1, r2)
                int r0 = r0 + r1
            L_0x008c:
                int r1 = r4.bitField0_
                r2 = 1024(0x400, float:1.435E-42)
                r1 = r1 & r2
                if (r1 != r2) goto L_0x009c
                r1 = 10
                org.bitcoin.paymentchannel.Protos$Error r2 = r4.error_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r1, r2)
                int r0 = r0 + r1
            L_0x009c:
                int r1 = r4.bitField0_
                r2 = 256(0x100, float:3.59E-43)
                r1 = r1 & r2
                if (r1 != r2) goto L_0x00ac
                r1 = 11
                org.bitcoin.paymentchannel.Protos$PaymentAck r2 = r4.paymentAck_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r1, r2)
                int r0 = r0 + r1
            L_0x00ac:
                com.google.protobuf.UnknownFieldSet r1 = r4.getUnknownFields()
                int r1 = r1.getSerializedSize()
                int r0 = r0 + r1
                r4.memoizedSerializedSize = r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoin.paymentchannel.Protos.TwoWayChannelMessage.getSerializedSize():int");
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static TwoWayChannelMessage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (TwoWayChannelMessage) PARSER.parseFrom(byteString);
        }

        public static TwoWayChannelMessage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TwoWayChannelMessage) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static TwoWayChannelMessage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (TwoWayChannelMessage) PARSER.parseFrom(bArr);
        }

        public static TwoWayChannelMessage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TwoWayChannelMessage) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static TwoWayChannelMessage parseFrom(InputStream inputStream) throws IOException {
            return (TwoWayChannelMessage) PARSER.parseFrom(inputStream);
        }

        public static TwoWayChannelMessage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TwoWayChannelMessage) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static TwoWayChannelMessage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TwoWayChannelMessage) PARSER.parseDelimitedFrom(inputStream);
        }

        public static TwoWayChannelMessage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TwoWayChannelMessage) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static TwoWayChannelMessage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TwoWayChannelMessage) PARSER.parseFrom(codedInputStream);
        }

        public static TwoWayChannelMessage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TwoWayChannelMessage) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(TwoWayChannelMessage twoWayChannelMessage) {
            return newBuilder().mergeFrom(twoWayChannelMessage);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface TwoWayChannelMessageOrBuilder extends MessageOrBuilder {
        ClientVersion getClientVersion();

        ClientVersionOrBuilder getClientVersionOrBuilder();

        Error getError();

        ErrorOrBuilder getErrorOrBuilder();

        Initiate getInitiate();

        InitiateOrBuilder getInitiateOrBuilder();

        PaymentAck getPaymentAck();

        PaymentAckOrBuilder getPaymentAckOrBuilder();

        ProvideContract getProvideContract();

        ProvideContractOrBuilder getProvideContractOrBuilder();

        ProvideRefund getProvideRefund();

        ProvideRefundOrBuilder getProvideRefundOrBuilder();

        ReturnRefund getReturnRefund();

        ReturnRefundOrBuilder getReturnRefundOrBuilder();

        ServerVersion getServerVersion();

        ServerVersionOrBuilder getServerVersionOrBuilder();

        Settlement getSettlement();

        SettlementOrBuilder getSettlementOrBuilder();

        MessageType getType();

        UpdatePayment getUpdatePayment();

        UpdatePaymentOrBuilder getUpdatePaymentOrBuilder();

        boolean hasClientVersion();

        boolean hasError();

        boolean hasInitiate();

        boolean hasPaymentAck();

        boolean hasProvideContract();

        boolean hasProvideRefund();

        boolean hasReturnRefund();

        boolean hasServerVersion();

        boolean hasSettlement();

        boolean hasType();

        boolean hasUpdatePayment();
    }

    public static final class UpdatePayment extends GeneratedMessage implements UpdatePaymentOrBuilder {
        public static final int CLIENT_CHANGE_VALUE_FIELD_NUMBER = 1;
        public static final int INFO_FIELD_NUMBER = 3;
        public static Parser<UpdatePayment> PARSER = new AbstractParser<UpdatePayment>() {
            public UpdatePayment parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new UpdatePayment(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SIGNATURE_FIELD_NUMBER = 2;
        private static final UpdatePayment defaultInstance = new UpdatePayment(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public long clientChangeValue_;
        /* access modifiers changed from: private */
        public ByteString info_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString signature_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements UpdatePaymentOrBuilder {
            private int bitField0_;
            private long clientChangeValue_;
            private ByteString info_;
            private ByteString signature_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_paymentchannels_UpdatePayment_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_paymentchannels_UpdatePayment_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdatePayment.class, Builder.class);
            }

            private Builder() {
                this.signature_ = ByteString.EMPTY;
                this.info_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.signature_ = ByteString.EMPTY;
                this.info_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                UpdatePayment.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                UpdatePayment.super.clear();
                this.clientChangeValue_ = 0;
                this.bitField0_ &= -2;
                this.signature_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                this.info_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_paymentchannels_UpdatePayment_descriptor;
            }

            public UpdatePayment getDefaultInstanceForType() {
                return UpdatePayment.getDefaultInstance();
            }

            public UpdatePayment build() {
                UpdatePayment buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public UpdatePayment buildPartial() {
                UpdatePayment updatePayment = new UpdatePayment((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                updatePayment.clientChangeValue_ = this.clientChangeValue_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                updatePayment.signature_ = this.signature_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                updatePayment.info_ = this.info_;
                updatePayment.bitField0_ = i2;
                onBuilt();
                return updatePayment;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof UpdatePayment) {
                    return mergeFrom((UpdatePayment) message);
                }
                UpdatePayment.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(UpdatePayment updatePayment) {
                if (updatePayment == UpdatePayment.getDefaultInstance()) {
                    return this;
                }
                if (updatePayment.hasClientChangeValue()) {
                    setClientChangeValue(updatePayment.getClientChangeValue());
                }
                if (updatePayment.hasSignature()) {
                    setSignature(updatePayment.getSignature());
                }
                if (updatePayment.hasInfo()) {
                    setInfo(updatePayment.getInfo());
                }
                mergeUnknownFields(updatePayment.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasClientChangeValue() && hasSignature()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                UpdatePayment updatePayment;
                UpdatePayment updatePayment2 = null;
                try {
                    UpdatePayment updatePayment3 = (UpdatePayment) UpdatePayment.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (updatePayment3 != null) {
                        mergeFrom(updatePayment3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    updatePayment = (UpdatePayment) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    updatePayment2 = updatePayment;
                }
                if (updatePayment2 != null) {
                    mergeFrom(updatePayment2);
                }
                throw th;
            }

            public boolean hasClientChangeValue() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getClientChangeValue() {
                return this.clientChangeValue_;
            }

            public Builder setClientChangeValue(long j) {
                this.bitField0_ |= 1;
                this.clientChangeValue_ = j;
                onChanged();
                return this;
            }

            public Builder clearClientChangeValue() {
                this.bitField0_ &= -2;
                this.clientChangeValue_ = 0;
                onChanged();
                return this;
            }

            public boolean hasSignature() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getSignature() {
                return this.signature_;
            }

            public Builder setSignature(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.signature_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearSignature() {
                this.bitField0_ &= -3;
                this.signature_ = UpdatePayment.getDefaultInstance().getSignature();
                onChanged();
                return this;
            }

            public boolean hasInfo() {
                return (this.bitField0_ & 4) == 4;
            }

            public ByteString getInfo() {
                return this.info_;
            }

            public Builder setInfo(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.info_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearInfo() {
                this.bitField0_ &= -5;
                this.info_ = UpdatePayment.getDefaultInstance().getInfo();
                onChanged();
                return this;
            }
        }

        private UpdatePayment(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private UpdatePayment(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static UpdatePayment getDefaultInstance() {
            return defaultInstance;
        }

        public UpdatePayment getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoin.paymentchannel.Protos$UpdatePayment] */
        private UpdatePayment(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.clientChangeValue_ = codedInputStream.readUInt64();
                        } else if (readTag == 18) {
                            this.bitField0_ |= 2;
                            this.signature_ = codedInputStream.readBytes();
                        } else if (readTag == 26) {
                            this.bitField0_ |= 4;
                            this.info_ = codedInputStream.readBytes();
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
            return Protos.internal_static_paymentchannels_UpdatePayment_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_paymentchannels_UpdatePayment_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdatePayment.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<UpdatePayment> getParserForType() {
            return PARSER;
        }

        public boolean hasClientChangeValue() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getClientChangeValue() {
            return this.clientChangeValue_;
        }

        public boolean hasSignature() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getSignature() {
            return this.signature_;
        }

        public boolean hasInfo() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getInfo() {
            return this.info_;
        }

        private void initFields() {
            this.clientChangeValue_ = 0;
            this.signature_ = ByteString.EMPTY;
            this.info_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasClientChangeValue()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!hasSignature()) {
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
                codedOutputStream.writeUInt64(1, this.clientChangeValue_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.signature_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, this.info_);
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
                i2 = 0 + CodedOutputStream.computeUInt64Size(1, this.clientChangeValue_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.signature_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeBytesSize(3, this.info_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static UpdatePayment parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (UpdatePayment) PARSER.parseFrom(byteString);
        }

        public static UpdatePayment parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdatePayment) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static UpdatePayment parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (UpdatePayment) PARSER.parseFrom(bArr);
        }

        public static UpdatePayment parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (UpdatePayment) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static UpdatePayment parseFrom(InputStream inputStream) throws IOException {
            return (UpdatePayment) PARSER.parseFrom(inputStream);
        }

        public static UpdatePayment parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdatePayment) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static UpdatePayment parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (UpdatePayment) PARSER.parseDelimitedFrom(inputStream);
        }

        public static UpdatePayment parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdatePayment) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static UpdatePayment parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (UpdatePayment) PARSER.parseFrom(codedInputStream);
        }

        public static UpdatePayment parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (UpdatePayment) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(UpdatePayment updatePayment) {
            return newBuilder().mergeFrom(updatePayment);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface UpdatePaymentOrBuilder extends MessageOrBuilder {
        long getClientChangeValue();

        ByteString getInfo();

        ByteString getSignature();

        boolean hasClientChangeValue();

        boolean hasInfo();

        boolean hasSignature();
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
    }

    private Protos() {
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0014paymentchannel.proto\u0012\u000fpaymentchannels\"°\u0006\n\u0014TwoWayChannelMessage\u0012?\n\u0004type\u0018\u0001 \u0002(\u000e21.paymentchannels.TwoWayChannelMessage.MessageType\u00126\n\u000eclient_version\u0018\u0002 \u0001(\u000b2\u001e.paymentchannels.ClientVersion\u00126\n\u000eserver_version\u0018\u0003 \u0001(\u000b2\u001e.paymentchannels.ServerVersion\u0012+\n\binitiate\u0018\u0004 \u0001(\u000b2\u0019.paymentchannels.Initiate\u00126\n\u000eprovide_refund\u0018\u0005 \u0001(\u000b2\u001e.paymentchannels.ProvideRefund\u00124\n\rreturn_refund\u0018\u0006 \u0001(\u000b2\u001d.paymentchannels.ReturnRefund\u0012:\n\u0010", "provide_contract\u0018\u0007 \u0001(\u000b2 .paymentchannels.ProvideContract\u00126\n\u000eupdate_payment\u0018\b \u0001(\u000b2\u001e.paymentchannels.UpdatePayment\u00120\n\u000bpayment_ack\u0018\u000b \u0001(\u000b2\u001b.paymentchannels.PaymentAck\u0012/\n\nsettlement\u0018\t \u0001(\u000b2\u001b.paymentchannels.Settlement\u0012%\n\u0005error\u0018\n \u0001(\u000b2\u0016.paymentchannels.Error\"Í\u0001\n\u000bMessageType\u0012\u0012\n\u000eCLIENT_VERSION\u0010\u0001\u0012\u0012\n\u000eSERVER_VERSION\u0010\u0002\u0012\f\n\bINITIATE\u0010\u0003\u0012\u0012\n\u000ePROVIDE_REFUND\u0010\u0004\u0012\u0011\n\rRETURN_REFUND\u0010\u0005\u0012\u0014\n\u0010PROVIDE_CONTRACT\u0010\u0006\u0012\u0010\n\fCHANNEL_OPEN\u0010\u0007\u0012", "\u0012\n\u000eUPDATE_PAYMENT\u0010\b\u0012\u000f\n\u000bPAYMENT_ACK\u0010\u000b\u0012\t\n\u0005CLOSE\u0010\t\u0012\t\n\u0005ERROR\u0010\n\"y\n\rClientVersion\u0012\r\n\u0005major\u0018\u0001 \u0002(\u0005\u0012\u0010\n\u0005minor\u0018\u0002 \u0001(\u0005:\u00010\u0012&\n\u001eprevious_channel_contract_hash\u0018\u0003 \u0001(\f\u0012\u001f\n\u0010time_window_secs\u0018\u0004 \u0001(\u0004:\u000586340\"0\n\rServerVersion\u0012\r\n\u0005major\u0018\u0001 \u0002(\u0005\u0012\u0010\n\u0005minor\u0018\u0002 \u0001(\u0005:\u00010\"r\n\bInitiate\u0012\u0014\n\fmultisig_key\u0018\u0001 \u0002(\f\u0012!\n\u0019min_accepted_channel_size\u0018\u0002 \u0002(\u0004\u0012\u0018\n\u0010expire_time_secs\u0018\u0003 \u0002(\u0004\u0012\u0013\n\u000bmin_payment\u0018\u0004 \u0002(\u0004\"1\n\rProvideRefund\u0012\u0014\n\fmultisig_key\u0018\u0001 \u0002(\f\u0012\n\n\u0002tx\u0018\u0002 \u0002(\f\"!", "\n\fReturnRefund\u0012\u0011\n\tsignature\u0018\u0001 \u0002(\f\"j\n\u000fProvideContract\u0012\n\n\u0002tx\u0018\u0001 \u0002(\f\u00127\n\u000finitial_payment\u0018\u0002 \u0002(\u000b2\u001e.paymentchannels.UpdatePayment\u0012\u0012\n\nclient_key\u0018\u0003 \u0001(\f\"M\n\rUpdatePayment\u0012\u001b\n\u0013client_change_value\u0018\u0001 \u0002(\u0004\u0012\u0011\n\tsignature\u0018\u0002 \u0002(\f\u0012\f\n\u0004info\u0018\u0003 \u0001(\f\"\u001a\n\nPaymentAck\u0012\f\n\u0004info\u0018\u0001 \u0001(\f\"\u0018\n\nSettlement\u0012\n\n\u0002tx\u0018\u0003 \u0002(\f\"©\u0002\n\u0005Error\u00125\n\u0004code\u0018\u0001 \u0001(\u000e2 .paymentchannels.Error.ErrorCode:\u0005OTHER\u0012\u0013\n\u000bexplanation\u0018\u0002 \u0001(\t\u0012\u0016\n\u000eexpected_value\u0018\u0003 \u0001(\u0004\"»\u0001\n\tErrorCode\u0012\u000b", "\n\u0007TIMEOUT\u0010\u0001\u0012\u0010\n\fSYNTAX_ERROR\u0010\u0002\u0012\u0019\n\u0015NO_ACCEPTABLE_VERSION\u0010\u0003\u0012\u0013\n\u000fBAD_TRANSACTION\u0010\u0004\u0012\u001c\n\u0018TIME_WINDOW_UNACCEPTABLE\u0010\u0005\u0012\u001b\n\u0017CHANNEL_VALUE_TOO_LARGE\u0010\u0006\u0012\u0019\n\u0015MIN_PAYMENT_TOO_LARGE\u0010\u0007\u0012\t\n\u0005OTHER\u0010\bB$\n\u001aorg.bitcoin.paymentchannelB\u0006Protos"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                Protos.descriptor = fileDescriptor;
                return null;
            }
        });
        String str = "Minor";
        String str2 = "Major";
        internal_static_paymentchannels_ClientVersion_fieldAccessorTable = new FieldAccessorTable(internal_static_paymentchannels_ClientVersion_descriptor, new String[]{str2, str, "PreviousChannelContractHash", "TimeWindowSecs"});
        internal_static_paymentchannels_ServerVersion_fieldAccessorTable = new FieldAccessorTable(internal_static_paymentchannels_ServerVersion_descriptor, new String[]{str2, str});
        String str3 = "MultisigKey";
        internal_static_paymentchannels_Initiate_fieldAccessorTable = new FieldAccessorTable(internal_static_paymentchannels_Initiate_descriptor, new String[]{str3, "MinAcceptedChannelSize", "ExpireTimeSecs", "MinPayment"});
        String str4 = "Tx";
        internal_static_paymentchannels_ProvideRefund_fieldAccessorTable = new FieldAccessorTable(internal_static_paymentchannels_ProvideRefund_descriptor, new String[]{str3, str4});
        String str5 = "Signature";
        internal_static_paymentchannels_ReturnRefund_fieldAccessorTable = new FieldAccessorTable(internal_static_paymentchannels_ReturnRefund_descriptor, new String[]{str5});
        f793x97030b71 = new FieldAccessorTable(internal_static_paymentchannels_ProvideContract_descriptor, new String[]{str4, "InitialPayment", "ClientKey"});
        String str6 = "Info";
        internal_static_paymentchannels_UpdatePayment_fieldAccessorTable = new FieldAccessorTable(internal_static_paymentchannels_UpdatePayment_descriptor, new String[]{"ClientChangeValue", str5, str6});
        internal_static_paymentchannels_PaymentAck_fieldAccessorTable = new FieldAccessorTable(internal_static_paymentchannels_PaymentAck_descriptor, new String[]{str6});
        internal_static_paymentchannels_Settlement_fieldAccessorTable = new FieldAccessorTable(internal_static_paymentchannels_Settlement_descriptor, new String[]{str4});
    }
}
