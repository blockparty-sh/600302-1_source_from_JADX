package org.bitcoin.protocols.payments;

import com.google.common.net.HttpHeaders;
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
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bitcoinj.core.NetworkParameters;

public final class Protos {
    /* access modifiers changed from: private */
    public static FileDescriptor descriptor;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_payments_Output_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(0));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_payments_Output_fieldAccessorTable = new FieldAccessorTable(internal_static_payments_Output_descriptor, new String[]{"Amount", "Script"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_payments_PaymentACK_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(5));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_payments_PaymentACK_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_payments_PaymentDetails_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(1));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_payments_PaymentDetails_fieldAccessorTable = new FieldAccessorTable(internal_static_payments_PaymentDetails_descriptor, new String[]{"Network", "Outputs", "Time", HttpHeaders.EXPIRES, "Memo", "PaymentUrl", "MerchantData"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_payments_PaymentRequest_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(2));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_payments_PaymentRequest_fieldAccessorTable = new FieldAccessorTable(internal_static_payments_PaymentRequest_descriptor, new String[]{"PaymentDetailsVersion", "PkiType", "PkiData", "SerializedPaymentDetails", "Signature"});
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_payments_Payment_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(4));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_payments_Payment_fieldAccessorTable;
    /* access modifiers changed from: private */
    public static final Descriptor internal_static_payments_X509Certificates_descriptor = ((Descriptor) getDescriptor().getMessageTypes().get(3));
    /* access modifiers changed from: private */
    public static FieldAccessorTable internal_static_payments_X509Certificates_fieldAccessorTable = new FieldAccessorTable(internal_static_payments_X509Certificates_descriptor, new String[]{"Certificate"});

    public static final class Output extends GeneratedMessage implements OutputOrBuilder {
        public static final int AMOUNT_FIELD_NUMBER = 1;
        public static Parser<Output> PARSER = new AbstractParser<Output>() {
            public Output parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Output(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int SCRIPT_FIELD_NUMBER = 2;
        private static final Output defaultInstance = new Output(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public long amount_;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString script_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements OutputOrBuilder {
            private long amount_;
            private int bitField0_;
            private ByteString script_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_payments_Output_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_payments_Output_fieldAccessorTable.ensureFieldAccessorsInitialized(Output.class, Builder.class);
            }

            private Builder() {
                this.script_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.script_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                Output.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                Output.super.clear();
                this.amount_ = 0;
                this.bitField0_ &= -2;
                this.script_ = ByteString.EMPTY;
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_payments_Output_descriptor;
            }

            public Output getDefaultInstanceForType() {
                return Output.getDefaultInstance();
            }

            public Output build() {
                Output buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Output buildPartial() {
                Output output = new Output((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                output.amount_ = this.amount_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                output.script_ = this.script_;
                output.bitField0_ = i2;
                onBuilt();
                return output;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof Output) {
                    return mergeFrom((Output) message);
                }
                Output.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Output output) {
                if (output == Output.getDefaultInstance()) {
                    return this;
                }
                if (output.hasAmount()) {
                    setAmount(output.getAmount());
                }
                if (output.hasScript()) {
                    setScript(output.getScript());
                }
                mergeUnknownFields(output.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                return hasScript();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Output output;
                Output output2 = null;
                try {
                    Output output3 = (Output) Output.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (output3 != null) {
                        mergeFrom(output3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    output = (Output) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    output2 = output;
                }
                if (output2 != null) {
                    mergeFrom(output2);
                }
                throw th;
            }

            public boolean hasAmount() {
                return (this.bitField0_ & 1) == 1;
            }

            public long getAmount() {
                return this.amount_;
            }

            public Builder setAmount(long j) {
                this.bitField0_ |= 1;
                this.amount_ = j;
                onChanged();
                return this;
            }

            public Builder clearAmount() {
                this.bitField0_ &= -2;
                this.amount_ = 0;
                onChanged();
                return this;
            }

            public boolean hasScript() {
                return (this.bitField0_ & 2) == 2;
            }

            public ByteString getScript() {
                return this.script_;
            }

            public Builder setScript(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.script_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearScript() {
                this.bitField0_ &= -3;
                this.script_ = Output.getDefaultInstance().getScript();
                onChanged();
                return this;
            }
        }

        private Output(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Output(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Output getDefaultInstance() {
            return defaultInstance;
        }

        public Output getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoin.protocols.payments.Protos$Output] */
        private Output(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.amount_ = codedInputStream.readUInt64();
                        } else if (readTag == 18) {
                            this.bitField0_ |= 2;
                            this.script_ = codedInputStream.readBytes();
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
            return Protos.internal_static_payments_Output_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_payments_Output_fieldAccessorTable.ensureFieldAccessorsInitialized(Output.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Output> getParserForType() {
            return PARSER;
        }

        public boolean hasAmount() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getAmount() {
            return this.amount_;
        }

        public boolean hasScript() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getScript() {
            return this.script_;
        }

        private void initFields() {
            this.amount_ = 0;
            this.script_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasScript()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeUInt64(1, this.amount_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.script_);
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
                i2 = 0 + CodedOutputStream.computeUInt64Size(1, this.amount_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.script_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static Output parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Output) PARSER.parseFrom(byteString);
        }

        public static Output parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Output) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Output parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Output) PARSER.parseFrom(bArr);
        }

        public static Output parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Output) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Output parseFrom(InputStream inputStream) throws IOException {
            return (Output) PARSER.parseFrom(inputStream);
        }

        public static Output parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Output) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Output parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Output) PARSER.parseDelimitedFrom(inputStream);
        }

        public static Output parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Output) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static Output parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Output) PARSER.parseFrom(codedInputStream);
        }

        public static Output parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Output) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Output output) {
            return newBuilder().mergeFrom(output);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface OutputOrBuilder extends MessageOrBuilder {
        long getAmount();

        ByteString getScript();

        boolean hasAmount();

        boolean hasScript();
    }

    public static final class Payment extends GeneratedMessage implements PaymentOrBuilder {
        public static final int MEMO_FIELD_NUMBER = 4;
        public static final int MERCHANT_DATA_FIELD_NUMBER = 1;
        public static Parser<Payment> PARSER = new AbstractParser<Payment>() {
            public Payment parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Payment(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int REFUND_TO_FIELD_NUMBER = 3;
        public static final int TRANSACTIONS_FIELD_NUMBER = 2;
        private static final Payment defaultInstance = new Payment(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public Object memo_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString merchantData_;
        /* access modifiers changed from: private */
        public List<Output> refundTo_;
        /* access modifiers changed from: private */
        public List<ByteString> transactions_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements PaymentOrBuilder {
            private int bitField0_;
            private Object memo_;
            private ByteString merchantData_;
            private RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> refundToBuilder_;
            private List<Output> refundTo_;
            private List<ByteString> transactions_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_payments_Payment_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_payments_Payment_fieldAccessorTable.ensureFieldAccessorsInitialized(Payment.class, Builder.class);
            }

            private Builder() {
                this.merchantData_ = ByteString.EMPTY;
                this.transactions_ = Collections.emptyList();
                this.refundTo_ = Collections.emptyList();
                this.memo_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.merchantData_ = ByteString.EMPTY;
                this.transactions_ = Collections.emptyList();
                this.refundTo_ = Collections.emptyList();
                this.memo_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (Payment.alwaysUseFieldBuilders) {
                    getRefundToFieldBuilder();
                }
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                Payment.super.clear();
                this.merchantData_ = ByteString.EMPTY;
                this.bitField0_ &= -2;
                this.transactions_ = Collections.emptyList();
                this.bitField0_ &= -3;
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.refundTo_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                } else {
                    repeatedFieldBuilder.clear();
                }
                this.memo_ = "";
                this.bitField0_ &= -9;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_payments_Payment_descriptor;
            }

            public Payment getDefaultInstanceForType() {
                return Payment.getDefaultInstance();
            }

            public Payment build() {
                Payment buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public Payment buildPartial() {
                Payment payment = new Payment((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                payment.merchantData_ = this.merchantData_;
                if ((this.bitField0_ & 2) == 2) {
                    this.transactions_ = Collections.unmodifiableList(this.transactions_);
                    this.bitField0_ &= -3;
                }
                payment.transactions_ = this.transactions_;
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder == null) {
                    if ((this.bitField0_ & 4) == 4) {
                        this.refundTo_ = Collections.unmodifiableList(this.refundTo_);
                        this.bitField0_ &= -5;
                    }
                    payment.refundTo_ = this.refundTo_;
                } else {
                    payment.refundTo_ = repeatedFieldBuilder.build();
                }
                if ((i & 8) == 8) {
                    i2 |= 2;
                }
                payment.memo_ = this.memo_;
                payment.bitField0_ = i2;
                onBuilt();
                return payment;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof Payment) {
                    return mergeFrom((Payment) message);
                }
                Payment.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Payment payment) {
                if (payment == Payment.getDefaultInstance()) {
                    return this;
                }
                if (payment.hasMerchantData()) {
                    setMerchantData(payment.getMerchantData());
                }
                if (!payment.transactions_.isEmpty()) {
                    if (this.transactions_.isEmpty()) {
                        this.transactions_ = payment.transactions_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureTransactionsIsMutable();
                        this.transactions_.addAll(payment.transactions_);
                    }
                    onChanged();
                }
                if (this.refundToBuilder_ == null) {
                    if (!payment.refundTo_.isEmpty()) {
                        if (this.refundTo_.isEmpty()) {
                            this.refundTo_ = payment.refundTo_;
                            this.bitField0_ &= -5;
                        } else {
                            ensureRefundToIsMutable();
                            this.refundTo_.addAll(payment.refundTo_);
                        }
                        onChanged();
                    }
                } else if (!payment.refundTo_.isEmpty()) {
                    if (this.refundToBuilder_.isEmpty()) {
                        this.refundToBuilder_.dispose();
                        RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = null;
                        this.refundToBuilder_ = null;
                        this.refundTo_ = payment.refundTo_;
                        this.bitField0_ &= -5;
                        if (Payment.alwaysUseFieldBuilders) {
                            repeatedFieldBuilder = getRefundToFieldBuilder();
                        }
                        this.refundToBuilder_ = repeatedFieldBuilder;
                    } else {
                        this.refundToBuilder_.addAllMessages(payment.refundTo_);
                    }
                }
                if (payment.hasMemo()) {
                    this.bitField0_ |= 8;
                    this.memo_ = payment.memo_;
                    onChanged();
                }
                mergeUnknownFields(payment.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                for (int i = 0; i < getRefundToCount(); i++) {
                    if (!getRefundTo(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                Payment payment;
                Payment payment2 = null;
                try {
                    Payment payment3 = (Payment) Payment.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (payment3 != null) {
                        mergeFrom(payment3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    payment = (Payment) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    payment2 = payment;
                }
                if (payment2 != null) {
                    mergeFrom(payment2);
                }
                throw th;
            }

            public boolean hasMerchantData() {
                return (this.bitField0_ & 1) == 1;
            }

            public ByteString getMerchantData() {
                return this.merchantData_;
            }

            public Builder setMerchantData(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.merchantData_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearMerchantData() {
                this.bitField0_ &= -2;
                this.merchantData_ = Payment.getDefaultInstance().getMerchantData();
                onChanged();
                return this;
            }

            private void ensureTransactionsIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.transactions_ = new ArrayList(this.transactions_);
                    this.bitField0_ |= 2;
                }
            }

            public List<ByteString> getTransactionsList() {
                return Collections.unmodifiableList(this.transactions_);
            }

            public int getTransactionsCount() {
                return this.transactions_.size();
            }

            public ByteString getTransactions(int i) {
                return (ByteString) this.transactions_.get(i);
            }

            public Builder setTransactions(int i, ByteString byteString) {
                if (byteString != null) {
                    ensureTransactionsIsMutable();
                    this.transactions_.set(i, byteString);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addTransactions(ByteString byteString) {
                if (byteString != null) {
                    ensureTransactionsIsMutable();
                    this.transactions_.add(byteString);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addAllTransactions(Iterable<? extends ByteString> iterable) {
                ensureTransactionsIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.transactions_);
                onChanged();
                return this;
            }

            public Builder clearTransactions() {
                this.transactions_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
                return this;
            }

            private void ensureRefundToIsMutable() {
                if ((this.bitField0_ & 4) != 4) {
                    this.refundTo_ = new ArrayList(this.refundTo_);
                    this.bitField0_ |= 4;
                }
            }

            public List<Output> getRefundToList() {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.refundTo_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getRefundToCount() {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.refundTo_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public Output getRefundTo(int i) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (Output) this.refundTo_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setRefundTo(int i, Output output) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, output);
                } else if (output != null) {
                    ensureRefundToIsMutable();
                    this.refundTo_.set(i, output);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setRefundTo(int i, Builder builder) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureRefundToIsMutable();
                    this.refundTo_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addRefundTo(Output output) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(output);
                } else if (output != null) {
                    ensureRefundToIsMutable();
                    this.refundTo_.add(output);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addRefundTo(int i, Output output) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, output);
                } else if (output != null) {
                    ensureRefundToIsMutable();
                    this.refundTo_.add(i, output);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addRefundTo(Builder builder) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureRefundToIsMutable();
                    this.refundTo_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builder.build());
                }
                return this;
            }

            public Builder addRefundTo(int i, Builder builder) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureRefundToIsMutable();
                    this.refundTo_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllRefundTo(Iterable<? extends Output> iterable) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureRefundToIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.refundTo_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearRefundTo() {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.refundTo_ = Collections.emptyList();
                    this.bitField0_ &= -5;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeRefundTo(int i) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureRefundToIsMutable();
                    this.refundTo_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(i);
                }
                return this;
            }

            public Builder getRefundToBuilder(int i) {
                return getRefundToFieldBuilder().getBuilder(i);
            }

            public OutputOrBuilder getRefundToOrBuilder(int i) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (OutputOrBuilder) this.refundTo_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends OutputOrBuilder> getRefundToOrBuilderList() {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.refundToBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.refundTo_);
            }

            public Builder addRefundToBuilder() {
                return getRefundToFieldBuilder().addBuilder(Output.getDefaultInstance());
            }

            public Builder addRefundToBuilder(int i) {
                return getRefundToFieldBuilder().addBuilder(i, Output.getDefaultInstance());
            }

            public List<Builder> getRefundToBuilderList() {
                return getRefundToFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> getRefundToFieldBuilder() {
                if (this.refundToBuilder_ == null) {
                    this.refundToBuilder_ = new RepeatedFieldBuilder<>(this.refundTo_, (this.bitField0_ & 4) == 4, getParentForChildren(), isClean());
                    this.refundTo_ = null;
                }
                return this.refundToBuilder_;
            }

            public boolean hasMemo() {
                return (this.bitField0_ & 8) == 8;
            }

            public String getMemo() {
                Object obj = this.memo_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.memo_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getMemoBytes() {
                Object obj = this.memo_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.memo_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setMemo(String str) {
                if (str != null) {
                    this.bitField0_ |= 8;
                    this.memo_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearMemo() {
                this.bitField0_ &= -9;
                this.memo_ = Payment.getDefaultInstance().getMemo();
                onChanged();
                return this;
            }

            public Builder setMemoBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 8;
                    this.memo_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }
        }

        private Payment(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Payment(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Payment getDefaultInstance() {
            return defaultInstance;
        }

        public Payment getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r8v0, types: [com.google.protobuf.MessageLite, org.bitcoin.protocols.payments.Protos$Payment] */
        private Payment(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.bitField0_ |= 1;
                            this.merchantData_ = codedInputStream.readBytes();
                        } else if (readTag == 18) {
                            if (!(z2 & true)) {
                                this.transactions_ = new ArrayList();
                                z2 |= true;
                            }
                            this.transactions_.add(codedInputStream.readBytes());
                        } else if (readTag == 26) {
                            if (!(z2 & true)) {
                                this.refundTo_ = new ArrayList();
                                z2 |= true;
                            }
                            this.refundTo_.add(codedInputStream.readMessage(Output.PARSER, extensionRegistryLite));
                        } else if (readTag == 34) {
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 2;
                            this.memo_ = readBytes;
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.transactions_ = Collections.unmodifiableList(this.transactions_);
                    }
                    if (z2 & true) {
                        this.refundTo_ = Collections.unmodifiableList(this.refundTo_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.transactions_ = Collections.unmodifiableList(this.transactions_);
            }
            if (z2 & true) {
                this.refundTo_ = Collections.unmodifiableList(this.refundTo_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return Protos.internal_static_payments_Payment_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_payments_Payment_fieldAccessorTable.ensureFieldAccessorsInitialized(Payment.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<Payment> getParserForType() {
            return PARSER;
        }

        public boolean hasMerchantData() {
            return (this.bitField0_ & 1) == 1;
        }

        public ByteString getMerchantData() {
            return this.merchantData_;
        }

        public List<ByteString> getTransactionsList() {
            return this.transactions_;
        }

        public int getTransactionsCount() {
            return this.transactions_.size();
        }

        public ByteString getTransactions(int i) {
            return (ByteString) this.transactions_.get(i);
        }

        public List<Output> getRefundToList() {
            return this.refundTo_;
        }

        public List<? extends OutputOrBuilder> getRefundToOrBuilderList() {
            return this.refundTo_;
        }

        public int getRefundToCount() {
            return this.refundTo_.size();
        }

        public Output getRefundTo(int i) {
            return (Output) this.refundTo_.get(i);
        }

        public OutputOrBuilder getRefundToOrBuilder(int i) {
            return (OutputOrBuilder) this.refundTo_.get(i);
        }

        public boolean hasMemo() {
            return (this.bitField0_ & 2) == 2;
        }

        public String getMemo() {
            Object obj = this.memo_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.memo_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getMemoBytes() {
            Object obj = this.memo_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.memo_ = copyFromUtf8;
            return copyFromUtf8;
        }

        private void initFields() {
            this.merchantData_ = ByteString.EMPTY;
            this.transactions_ = Collections.emptyList();
            this.refundTo_ = Collections.emptyList();
            this.memo_ = "";
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            for (int i = 0; i < getRefundToCount(); i++) {
                if (!getRefundTo(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.merchantData_);
            }
            for (int i = 0; i < this.transactions_.size(); i++) {
                codedOutputStream.writeBytes(2, (ByteString) this.transactions_.get(i));
            }
            for (int i2 = 0; i2 < this.refundTo_.size(); i2++) {
                codedOutputStream.writeMessage(3, (MessageLite) this.refundTo_.get(i2));
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(4, getMemoBytes());
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBytesSize(1, this.merchantData_) + 0 : 0;
            int i2 = 0;
            for (int i3 = 0; i3 < this.transactions_.size(); i3++) {
                i2 += CodedOutputStream.computeBytesSizeNoTag((ByteString) this.transactions_.get(i3));
            }
            int size = computeBytesSize + i2 + (getTransactionsList().size() * 1);
            for (int i4 = 0; i4 < this.refundTo_.size(); i4++) {
                size += CodedOutputStream.computeMessageSize(3, (MessageLite) this.refundTo_.get(i4));
            }
            if ((this.bitField0_ & 2) == 2) {
                size += CodedOutputStream.computeBytesSize(4, getMemoBytes());
            }
            int serializedSize = size + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static Payment parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Payment) PARSER.parseFrom(byteString);
        }

        public static Payment parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Payment) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Payment parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Payment) PARSER.parseFrom(bArr);
        }

        public static Payment parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Payment) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Payment parseFrom(InputStream inputStream) throws IOException {
            return (Payment) PARSER.parseFrom(inputStream);
        }

        public static Payment parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Payment) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static Payment parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Payment) PARSER.parseDelimitedFrom(inputStream);
        }

        public static Payment parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Payment) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static Payment parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Payment) PARSER.parseFrom(codedInputStream);
        }

        public static Payment parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Payment) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Payment payment) {
            return newBuilder().mergeFrom(payment);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public static final class PaymentACK extends GeneratedMessage implements PaymentACKOrBuilder {
        public static final int MEMO_FIELD_NUMBER = 2;
        public static Parser<PaymentACK> PARSER = new AbstractParser<PaymentACK>() {
            public PaymentACK parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PaymentACK(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PAYMENT_FIELD_NUMBER = 1;
        private static final PaymentACK defaultInstance = new PaymentACK(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public Object memo_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public Payment payment_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements PaymentACKOrBuilder {
            private int bitField0_;
            private Object memo_;
            private SingleFieldBuilder<Payment, Builder, PaymentOrBuilder> paymentBuilder_;
            private Payment payment_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_payments_PaymentACK_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_payments_PaymentACK_fieldAccessorTable.ensureFieldAccessorsInitialized(PaymentACK.class, Builder.class);
            }

            private Builder() {
                this.payment_ = Payment.getDefaultInstance();
                this.memo_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.payment_ = Payment.getDefaultInstance();
                this.memo_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (PaymentACK.alwaysUseFieldBuilders) {
                    getPaymentFieldBuilder();
                }
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                PaymentACK.super.clear();
                SingleFieldBuilder<Payment, Builder, PaymentOrBuilder> singleFieldBuilder = this.paymentBuilder_;
                if (singleFieldBuilder == null) {
                    this.payment_ = Payment.getDefaultInstance();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -2;
                this.memo_ = "";
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_payments_PaymentACK_descriptor;
            }

            public PaymentACK getDefaultInstanceForType() {
                return PaymentACK.getDefaultInstance();
            }

            public PaymentACK build() {
                PaymentACK buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public PaymentACK buildPartial() {
                PaymentACK paymentACK = new PaymentACK((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                SingleFieldBuilder<Payment, Builder, PaymentOrBuilder> singleFieldBuilder = this.paymentBuilder_;
                if (singleFieldBuilder == null) {
                    paymentACK.payment_ = this.payment_;
                } else {
                    paymentACK.payment_ = singleFieldBuilder.build();
                }
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                paymentACK.memo_ = this.memo_;
                paymentACK.bitField0_ = i2;
                onBuilt();
                return paymentACK;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof PaymentACK) {
                    return mergeFrom((PaymentACK) message);
                }
                PaymentACK.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PaymentACK paymentACK) {
                if (paymentACK == PaymentACK.getDefaultInstance()) {
                    return this;
                }
                if (paymentACK.hasPayment()) {
                    mergePayment(paymentACK.getPayment());
                }
                if (paymentACK.hasMemo()) {
                    this.bitField0_ |= 2;
                    this.memo_ = paymentACK.memo_;
                    onChanged();
                }
                mergeUnknownFields(paymentACK.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (hasPayment() && getPayment().isInitialized()) {
                    return true;
                }
                return false;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PaymentACK paymentACK;
                PaymentACK paymentACK2 = null;
                try {
                    PaymentACK paymentACK3 = (PaymentACK) PaymentACK.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (paymentACK3 != null) {
                        mergeFrom(paymentACK3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    paymentACK = (PaymentACK) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    paymentACK2 = paymentACK;
                }
                if (paymentACK2 != null) {
                    mergeFrom(paymentACK2);
                }
                throw th;
            }

            public boolean hasPayment() {
                return (this.bitField0_ & 1) == 1;
            }

            public Payment getPayment() {
                SingleFieldBuilder<Payment, Builder, PaymentOrBuilder> singleFieldBuilder = this.paymentBuilder_;
                if (singleFieldBuilder == null) {
                    return this.payment_;
                }
                return singleFieldBuilder.getMessage();
            }

            public Builder setPayment(Payment payment) {
                SingleFieldBuilder<Payment, Builder, PaymentOrBuilder> singleFieldBuilder = this.paymentBuilder_;
                if (singleFieldBuilder != null) {
                    singleFieldBuilder.setMessage(payment);
                } else if (payment != null) {
                    this.payment_ = payment;
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setPayment(Builder builder) {
                SingleFieldBuilder<Payment, Builder, PaymentOrBuilder> singleFieldBuilder = this.paymentBuilder_;
                if (singleFieldBuilder == null) {
                    this.payment_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builder.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergePayment(Payment payment) {
                SingleFieldBuilder<Payment, Builder, PaymentOrBuilder> singleFieldBuilder = this.paymentBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 1) != 1 || this.payment_ == Payment.getDefaultInstance()) {
                        this.payment_ = payment;
                    } else {
                        this.payment_ = Payment.newBuilder(this.payment_).mergeFrom(payment).buildPartial();
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(payment);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearPayment() {
                SingleFieldBuilder<Payment, Builder, PaymentOrBuilder> singleFieldBuilder = this.paymentBuilder_;
                if (singleFieldBuilder == null) {
                    this.payment_ = Payment.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -2;
                return this;
            }

            public Builder getPaymentBuilder() {
                this.bitField0_ |= 1;
                onChanged();
                return getPaymentFieldBuilder().getBuilder();
            }

            public PaymentOrBuilder getPaymentOrBuilder() {
                SingleFieldBuilder<Payment, Builder, PaymentOrBuilder> singleFieldBuilder = this.paymentBuilder_;
                if (singleFieldBuilder != null) {
                    return singleFieldBuilder.getMessageOrBuilder();
                }
                return this.payment_;
            }

            private SingleFieldBuilder<Payment, Builder, PaymentOrBuilder> getPaymentFieldBuilder() {
                if (this.paymentBuilder_ == null) {
                    this.paymentBuilder_ = new SingleFieldBuilder<>(getPayment(), getParentForChildren(), isClean());
                    this.payment_ = null;
                }
                return this.paymentBuilder_;
            }

            public boolean hasMemo() {
                return (this.bitField0_ & 2) == 2;
            }

            public String getMemo() {
                Object obj = this.memo_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.memo_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getMemoBytes() {
                Object obj = this.memo_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.memo_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setMemo(String str) {
                if (str != null) {
                    this.bitField0_ |= 2;
                    this.memo_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearMemo() {
                this.bitField0_ &= -3;
                this.memo_ = PaymentACK.getDefaultInstance().getMemo();
                onChanged();
                return this;
            }

            public Builder setMemoBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.memo_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }
        }

        private PaymentACK(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private PaymentACK(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static PaymentACK getDefaultInstance() {
            return defaultInstance;
        }

        public PaymentACK getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r5v0, types: [com.google.protobuf.MessageLite, org.bitcoin.protocols.payments.Protos$PaymentACK] */
        private PaymentACK(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            Builder builder = null;
                            if ((this.bitField0_ & 1) == 1) {
                                builder = this.payment_.toBuilder();
                            }
                            this.payment_ = (Payment) codedInputStream.readMessage(Payment.PARSER, extensionRegistryLite);
                            if (builder != null) {
                                builder.mergeFrom(this.payment_);
                                this.payment_ = builder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (readTag == 18) {
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 2;
                            this.memo_ = readBytes;
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
            return Protos.internal_static_payments_PaymentACK_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_payments_PaymentACK_fieldAccessorTable.ensureFieldAccessorsInitialized(PaymentACK.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<PaymentACK> getParserForType() {
            return PARSER;
        }

        public boolean hasPayment() {
            return (this.bitField0_ & 1) == 1;
        }

        public Payment getPayment() {
            return this.payment_;
        }

        public PaymentOrBuilder getPaymentOrBuilder() {
            return this.payment_;
        }

        public boolean hasMemo() {
            return (this.bitField0_ & 2) == 2;
        }

        public String getMemo() {
            Object obj = this.memo_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.memo_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getMemoBytes() {
            Object obj = this.memo_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.memo_ = copyFromUtf8;
            return copyFromUtf8;
        }

        private void initFields() {
            this.payment_ = Payment.getDefaultInstance();
            this.memo_ = "";
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasPayment()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else if (!getPayment().isInitialized()) {
                this.memoizedIsInitialized = 0;
                return false;
            } else {
                this.memoizedIsInitialized = 1;
                return true;
            }
        }

        /* JADX WARNING: type inference failed for: r0v6, types: [com.google.protobuf.MessageLite, org.bitcoin.protocols.payments.Protos$Payment] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v6, types: [com.google.protobuf.MessageLite, org.bitcoin.protocols.payments.Protos$Payment]
          assigns: [org.bitcoin.protocols.payments.Protos$Payment]
          uses: [com.google.protobuf.MessageLite]
          mth insns count: 14
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
                org.bitcoin.protocols.payments.Protos$Payment r0 = r2.payment_
                r3.writeMessage(r1, r0)
            L_0x000e:
                int r0 = r2.bitField0_
                r1 = 2
                r0 = r0 & r1
                if (r0 != r1) goto L_0x001b
                com.google.protobuf.ByteString r0 = r2.getMemoBytes()
                r3.writeBytes(r1, r0)
            L_0x001b:
                com.google.protobuf.UnknownFieldSet r0 = r2.getUnknownFields()
                r0.writeTo(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoin.protocols.payments.Protos.PaymentACK.writeTo(com.google.protobuf.CodedOutputStream):void");
        }

        /* JADX WARNING: type inference failed for: r1v9, types: [com.google.protobuf.MessageLite, org.bitcoin.protocols.payments.Protos$Payment] */
        /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v9, types: [com.google.protobuf.MessageLite, org.bitcoin.protocols.payments.Protos$Payment]
          assigns: [org.bitcoin.protocols.payments.Protos$Payment]
          uses: [com.google.protobuf.MessageLite]
          mth insns count: 21
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
                org.bitcoin.protocols.payments.Protos$Payment r1 = r3.payment_
                int r1 = com.google.protobuf.CodedOutputStream.computeMessageSize(r2, r1)
                int r0 = r0 + r1
            L_0x0014:
                int r1 = r3.bitField0_
                r2 = 2
                r1 = r1 & r2
                if (r1 != r2) goto L_0x0023
                com.google.protobuf.ByteString r1 = r3.getMemoBytes()
                int r1 = com.google.protobuf.CodedOutputStream.computeBytesSize(r2, r1)
                int r0 = r0 + r1
            L_0x0023:
                com.google.protobuf.UnknownFieldSet r1 = r3.getUnknownFields()
                int r1 = r1.getSerializedSize()
                int r0 = r0 + r1
                r3.memoizedSerializedSize = r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoin.protocols.payments.Protos.PaymentACK.getSerializedSize():int");
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static PaymentACK parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PaymentACK) PARSER.parseFrom(byteString);
        }

        public static PaymentACK parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PaymentACK) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PaymentACK parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PaymentACK) PARSER.parseFrom(bArr);
        }

        public static PaymentACK parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PaymentACK) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PaymentACK parseFrom(InputStream inputStream) throws IOException {
            return (PaymentACK) PARSER.parseFrom(inputStream);
        }

        public static PaymentACK parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PaymentACK) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static PaymentACK parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PaymentACK) PARSER.parseDelimitedFrom(inputStream);
        }

        public static PaymentACK parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PaymentACK) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static PaymentACK parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PaymentACK) PARSER.parseFrom(codedInputStream);
        }

        public static PaymentACK parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PaymentACK) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PaymentACK paymentACK) {
            return newBuilder().mergeFrom(paymentACK);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface PaymentACKOrBuilder extends MessageOrBuilder {
        String getMemo();

        ByteString getMemoBytes();

        Payment getPayment();

        PaymentOrBuilder getPaymentOrBuilder();

        boolean hasMemo();

        boolean hasPayment();
    }

    public static final class PaymentDetails extends GeneratedMessage implements PaymentDetailsOrBuilder {
        public static final int EXPIRES_FIELD_NUMBER = 4;
        public static final int MEMO_FIELD_NUMBER = 5;
        public static final int MERCHANT_DATA_FIELD_NUMBER = 7;
        public static final int NETWORK_FIELD_NUMBER = 1;
        public static final int OUTPUTS_FIELD_NUMBER = 2;
        public static Parser<PaymentDetails> PARSER = new AbstractParser<PaymentDetails>() {
            public PaymentDetails parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PaymentDetails(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PAYMENT_URL_FIELD_NUMBER = 6;
        public static final int TIME_FIELD_NUMBER = 3;
        private static final PaymentDetails defaultInstance = new PaymentDetails(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        /* access modifiers changed from: private */
        public long expires_;
        /* access modifiers changed from: private */
        public Object memo_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public ByteString merchantData_;
        /* access modifiers changed from: private */
        public Object network_;
        /* access modifiers changed from: private */
        public List<Output> outputs_;
        /* access modifiers changed from: private */
        public Object paymentUrl_;
        /* access modifiers changed from: private */
        public long time_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements PaymentDetailsOrBuilder {
            private int bitField0_;
            private long expires_;
            private Object memo_;
            private ByteString merchantData_;
            private Object network_;
            private RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> outputsBuilder_;
            private List<Output> outputs_;
            private Object paymentUrl_;
            private long time_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_payments_PaymentDetails_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_payments_PaymentDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(PaymentDetails.class, Builder.class);
            }

            private Builder() {
                this.network_ = NetworkParameters.PAYMENT_PROTOCOL_ID_MAINNET;
                this.outputs_ = Collections.emptyList();
                String str = "";
                this.memo_ = str;
                this.paymentUrl_ = str;
                this.merchantData_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.network_ = NetworkParameters.PAYMENT_PROTOCOL_ID_MAINNET;
                this.outputs_ = Collections.emptyList();
                String str = "";
                this.memo_ = str;
                this.paymentUrl_ = str;
                this.merchantData_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (PaymentDetails.alwaysUseFieldBuilders) {
                    getOutputsFieldBuilder();
                }
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                PaymentDetails.super.clear();
                this.network_ = NetworkParameters.PAYMENT_PROTOCOL_ID_MAINNET;
                this.bitField0_ &= -2;
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.outputs_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                } else {
                    repeatedFieldBuilder.clear();
                }
                this.time_ = 0;
                this.bitField0_ &= -5;
                this.expires_ = 0;
                this.bitField0_ &= -9;
                String str = "";
                this.memo_ = str;
                this.bitField0_ &= -17;
                this.paymentUrl_ = str;
                this.bitField0_ &= -33;
                this.merchantData_ = ByteString.EMPTY;
                this.bitField0_ &= -65;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_payments_PaymentDetails_descriptor;
            }

            public PaymentDetails getDefaultInstanceForType() {
                return PaymentDetails.getDefaultInstance();
            }

            public PaymentDetails build() {
                PaymentDetails buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public PaymentDetails buildPartial() {
                PaymentDetails paymentDetails = new PaymentDetails((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                paymentDetails.network_ = this.network_;
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder == null) {
                    if ((this.bitField0_ & 2) == 2) {
                        this.outputs_ = Collections.unmodifiableList(this.outputs_);
                        this.bitField0_ &= -3;
                    }
                    paymentDetails.outputs_ = this.outputs_;
                } else {
                    paymentDetails.outputs_ = repeatedFieldBuilder.build();
                }
                if ((i & 4) == 4) {
                    i2 |= 2;
                }
                paymentDetails.time_ = this.time_;
                if ((i & 8) == 8) {
                    i2 |= 4;
                }
                paymentDetails.expires_ = this.expires_;
                if ((i & 16) == 16) {
                    i2 |= 8;
                }
                paymentDetails.memo_ = this.memo_;
                if ((i & 32) == 32) {
                    i2 |= 16;
                }
                paymentDetails.paymentUrl_ = this.paymentUrl_;
                if ((i & 64) == 64) {
                    i2 |= 32;
                }
                paymentDetails.merchantData_ = this.merchantData_;
                paymentDetails.bitField0_ = i2;
                onBuilt();
                return paymentDetails;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof PaymentDetails) {
                    return mergeFrom((PaymentDetails) message);
                }
                PaymentDetails.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PaymentDetails paymentDetails) {
                if (paymentDetails == PaymentDetails.getDefaultInstance()) {
                    return this;
                }
                if (paymentDetails.hasNetwork()) {
                    this.bitField0_ |= 1;
                    this.network_ = paymentDetails.network_;
                    onChanged();
                }
                if (this.outputsBuilder_ == null) {
                    if (!paymentDetails.outputs_.isEmpty()) {
                        if (this.outputs_.isEmpty()) {
                            this.outputs_ = paymentDetails.outputs_;
                            this.bitField0_ &= -3;
                        } else {
                            ensureOutputsIsMutable();
                            this.outputs_.addAll(paymentDetails.outputs_);
                        }
                        onChanged();
                    }
                } else if (!paymentDetails.outputs_.isEmpty()) {
                    if (this.outputsBuilder_.isEmpty()) {
                        this.outputsBuilder_.dispose();
                        RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = null;
                        this.outputsBuilder_ = null;
                        this.outputs_ = paymentDetails.outputs_;
                        this.bitField0_ &= -3;
                        if (PaymentDetails.alwaysUseFieldBuilders) {
                            repeatedFieldBuilder = getOutputsFieldBuilder();
                        }
                        this.outputsBuilder_ = repeatedFieldBuilder;
                    } else {
                        this.outputsBuilder_.addAllMessages(paymentDetails.outputs_);
                    }
                }
                if (paymentDetails.hasTime()) {
                    setTime(paymentDetails.getTime());
                }
                if (paymentDetails.hasExpires()) {
                    setExpires(paymentDetails.getExpires());
                }
                if (paymentDetails.hasMemo()) {
                    this.bitField0_ |= 16;
                    this.memo_ = paymentDetails.memo_;
                    onChanged();
                }
                if (paymentDetails.hasPaymentUrl()) {
                    this.bitField0_ |= 32;
                    this.paymentUrl_ = paymentDetails.paymentUrl_;
                    onChanged();
                }
                if (paymentDetails.hasMerchantData()) {
                    setMerchantData(paymentDetails.getMerchantData());
                }
                mergeUnknownFields(paymentDetails.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                if (!hasTime()) {
                    return false;
                }
                for (int i = 0; i < getOutputsCount(); i++) {
                    if (!getOutputs(i).isInitialized()) {
                        return false;
                    }
                }
                return true;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PaymentDetails paymentDetails;
                PaymentDetails paymentDetails2 = null;
                try {
                    PaymentDetails paymentDetails3 = (PaymentDetails) PaymentDetails.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (paymentDetails3 != null) {
                        mergeFrom(paymentDetails3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    paymentDetails = (PaymentDetails) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    paymentDetails2 = paymentDetails;
                }
                if (paymentDetails2 != null) {
                    mergeFrom(paymentDetails2);
                }
                throw th;
            }

            public boolean hasNetwork() {
                return (this.bitField0_ & 1) == 1;
            }

            public String getNetwork() {
                Object obj = this.network_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.network_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getNetworkBytes() {
                Object obj = this.network_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.network_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setNetwork(String str) {
                if (str != null) {
                    this.bitField0_ |= 1;
                    this.network_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearNetwork() {
                this.bitField0_ &= -2;
                this.network_ = PaymentDetails.getDefaultInstance().getNetwork();
                onChanged();
                return this;
            }

            public Builder setNetworkBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 1;
                    this.network_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            private void ensureOutputsIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.outputs_ = new ArrayList(this.outputs_);
                    this.bitField0_ |= 2;
                }
            }

            public List<Output> getOutputsList() {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return Collections.unmodifiableList(this.outputs_);
                }
                return repeatedFieldBuilder.getMessageList();
            }

            public int getOutputsCount() {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return this.outputs_.size();
                }
                return repeatedFieldBuilder.getCount();
            }

            public Output getOutputs(int i) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (Output) this.outputs_.get(i);
                }
                return repeatedFieldBuilder.getMessage(i);
            }

            public Builder setOutputs(int i, Output output) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.setMessage(i, output);
                } else if (output != null) {
                    ensureOutputsIsMutable();
                    this.outputs_.set(i, output);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder setOutputs(int i, Builder builder) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureOutputsIsMutable();
                    this.outputs_.set(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.setMessage(i, builder.build());
                }
                return this;
            }

            public Builder addOutputs(Output output) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(output);
                } else if (output != null) {
                    ensureOutputsIsMutable();
                    this.outputs_.add(output);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addOutputs(int i, Output output) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder != null) {
                    repeatedFieldBuilder.addMessage(i, output);
                } else if (output != null) {
                    ensureOutputsIsMutable();
                    this.outputs_.add(i, output);
                    onChanged();
                } else {
                    throw new NullPointerException();
                }
                return this;
            }

            public Builder addOutputs(Builder builder) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureOutputsIsMutable();
                    this.outputs_.add(builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(builder.build());
                }
                return this;
            }

            public Builder addOutputs(int i, Builder builder) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureOutputsIsMutable();
                    this.outputs_.add(i, builder.build());
                    onChanged();
                } else {
                    repeatedFieldBuilder.addMessage(i, builder.build());
                }
                return this;
            }

            public Builder addAllOutputs(Iterable<? extends Output> iterable) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureOutputsIsMutable();
                    com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.outputs_);
                    onChanged();
                } else {
                    repeatedFieldBuilder.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearOutputs() {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder == null) {
                    this.outputs_ = Collections.emptyList();
                    this.bitField0_ &= -3;
                    onChanged();
                } else {
                    repeatedFieldBuilder.clear();
                }
                return this;
            }

            public Builder removeOutputs(int i) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder == null) {
                    ensureOutputsIsMutable();
                    this.outputs_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilder.remove(i);
                }
                return this;
            }

            public Builder getOutputsBuilder(int i) {
                return getOutputsFieldBuilder().getBuilder(i);
            }

            public OutputOrBuilder getOutputsOrBuilder(int i) {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder == null) {
                    return (OutputOrBuilder) this.outputs_.get(i);
                }
                return repeatedFieldBuilder.getMessageOrBuilder(i);
            }

            public List<? extends OutputOrBuilder> getOutputsOrBuilderList() {
                RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> repeatedFieldBuilder = this.outputsBuilder_;
                if (repeatedFieldBuilder != null) {
                    return repeatedFieldBuilder.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.outputs_);
            }

            public Builder addOutputsBuilder() {
                return getOutputsFieldBuilder().addBuilder(Output.getDefaultInstance());
            }

            public Builder addOutputsBuilder(int i) {
                return getOutputsFieldBuilder().addBuilder(i, Output.getDefaultInstance());
            }

            public List<Builder> getOutputsBuilderList() {
                return getOutputsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilder<Output, Builder, OutputOrBuilder> getOutputsFieldBuilder() {
                if (this.outputsBuilder_ == null) {
                    this.outputsBuilder_ = new RepeatedFieldBuilder<>(this.outputs_, (this.bitField0_ & 2) == 2, getParentForChildren(), isClean());
                    this.outputs_ = null;
                }
                return this.outputsBuilder_;
            }

            public boolean hasTime() {
                return (this.bitField0_ & 4) == 4;
            }

            public long getTime() {
                return this.time_;
            }

            public Builder setTime(long j) {
                this.bitField0_ |= 4;
                this.time_ = j;
                onChanged();
                return this;
            }

            public Builder clearTime() {
                this.bitField0_ &= -5;
                this.time_ = 0;
                onChanged();
                return this;
            }

            public boolean hasExpires() {
                return (this.bitField0_ & 8) == 8;
            }

            public long getExpires() {
                return this.expires_;
            }

            public Builder setExpires(long j) {
                this.bitField0_ |= 8;
                this.expires_ = j;
                onChanged();
                return this;
            }

            public Builder clearExpires() {
                this.bitField0_ &= -9;
                this.expires_ = 0;
                onChanged();
                return this;
            }

            public boolean hasMemo() {
                return (this.bitField0_ & 16) == 16;
            }

            public String getMemo() {
                Object obj = this.memo_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.memo_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getMemoBytes() {
                Object obj = this.memo_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.memo_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setMemo(String str) {
                if (str != null) {
                    this.bitField0_ |= 16;
                    this.memo_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearMemo() {
                this.bitField0_ &= -17;
                this.memo_ = PaymentDetails.getDefaultInstance().getMemo();
                onChanged();
                return this;
            }

            public Builder setMemoBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 16;
                    this.memo_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public boolean hasPaymentUrl() {
                return (this.bitField0_ & 32) == 32;
            }

            public String getPaymentUrl() {
                Object obj = this.paymentUrl_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.paymentUrl_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPaymentUrlBytes() {
                Object obj = this.paymentUrl_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.paymentUrl_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setPaymentUrl(String str) {
                if (str != null) {
                    this.bitField0_ |= 32;
                    this.paymentUrl_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearPaymentUrl() {
                this.bitField0_ &= -33;
                this.paymentUrl_ = PaymentDetails.getDefaultInstance().getPaymentUrl();
                onChanged();
                return this;
            }

            public Builder setPaymentUrlBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 32;
                    this.paymentUrl_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public boolean hasMerchantData() {
                return (this.bitField0_ & 64) == 64;
            }

            public ByteString getMerchantData() {
                return this.merchantData_;
            }

            public Builder setMerchantData(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 64;
                    this.merchantData_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearMerchantData() {
                this.bitField0_ &= -65;
                this.merchantData_ = PaymentDetails.getDefaultInstance().getMerchantData();
                onChanged();
                return this;
            }
        }

        private PaymentDetails(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private PaymentDetails(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static PaymentDetails getDefaultInstance() {
            return defaultInstance;
        }

        public PaymentDetails getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r8v0, types: [org.bitcoin.protocols.payments.Protos$PaymentDetails, com.google.protobuf.MessageLite] */
        private PaymentDetails(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ = 1 | this.bitField0_;
                            this.network_ = readBytes;
                        } else if (readTag == 18) {
                            if (!(z2 & true)) {
                                this.outputs_ = new ArrayList();
                                z2 |= true;
                            }
                            this.outputs_.add(codedInputStream.readMessage(Output.PARSER, extensionRegistryLite));
                        } else if (readTag == 24) {
                            this.bitField0_ |= 2;
                            this.time_ = codedInputStream.readUInt64();
                        } else if (readTag == 32) {
                            this.bitField0_ |= 4;
                            this.expires_ = codedInputStream.readUInt64();
                        } else if (readTag == 42) {
                            ByteString readBytes2 = codedInputStream.readBytes();
                            this.bitField0_ |= 8;
                            this.memo_ = readBytes2;
                        } else if (readTag == 50) {
                            ByteString readBytes3 = codedInputStream.readBytes();
                            this.bitField0_ |= 16;
                            this.paymentUrl_ = readBytes3;
                        } else if (readTag == 58) {
                            this.bitField0_ |= 32;
                            this.merchantData_ = codedInputStream.readBytes();
                        } else if (!parseUnknownField(codedInputStream, newBuilder, extensionRegistryLite, readTag)) {
                        }
                    }
                    z = true;
                } catch (InvalidProtocolBufferException e) {
                    throw e.setUnfinishedMessage(this);
                } catch (IOException e2) {
                    throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                } catch (Throwable th) {
                    if (z2 & true) {
                        this.outputs_ = Collections.unmodifiableList(this.outputs_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 & true) {
                this.outputs_ = Collections.unmodifiableList(this.outputs_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return Protos.internal_static_payments_PaymentDetails_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_payments_PaymentDetails_fieldAccessorTable.ensureFieldAccessorsInitialized(PaymentDetails.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<PaymentDetails> getParserForType() {
            return PARSER;
        }

        public boolean hasNetwork() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getNetwork() {
            Object obj = this.network_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.network_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getNetworkBytes() {
            Object obj = this.network_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.network_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public List<Output> getOutputsList() {
            return this.outputs_;
        }

        public List<? extends OutputOrBuilder> getOutputsOrBuilderList() {
            return this.outputs_;
        }

        public int getOutputsCount() {
            return this.outputs_.size();
        }

        public Output getOutputs(int i) {
            return (Output) this.outputs_.get(i);
        }

        public OutputOrBuilder getOutputsOrBuilder(int i) {
            return (OutputOrBuilder) this.outputs_.get(i);
        }

        public boolean hasTime() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getTime() {
            return this.time_;
        }

        public boolean hasExpires() {
            return (this.bitField0_ & 4) == 4;
        }

        public long getExpires() {
            return this.expires_;
        }

        public boolean hasMemo() {
            return (this.bitField0_ & 8) == 8;
        }

        public String getMemo() {
            Object obj = this.memo_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.memo_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getMemoBytes() {
            Object obj = this.memo_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.memo_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasPaymentUrl() {
            return (this.bitField0_ & 16) == 16;
        }

        public String getPaymentUrl() {
            Object obj = this.paymentUrl_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.paymentUrl_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getPaymentUrlBytes() {
            Object obj = this.paymentUrl_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.paymentUrl_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasMerchantData() {
            return (this.bitField0_ & 32) == 32;
        }

        public ByteString getMerchantData() {
            return this.merchantData_;
        }

        private void initFields() {
            this.network_ = NetworkParameters.PAYMENT_PROTOCOL_ID_MAINNET;
            this.outputs_ = Collections.emptyList();
            this.time_ = 0;
            this.expires_ = 0;
            String str = "";
            this.memo_ = str;
            this.paymentUrl_ = str;
            this.merchantData_ = ByteString.EMPTY;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            if (!hasTime()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            for (int i = 0; i < getOutputsCount(); i++) {
                if (!getOutputs(i).isInitialized()) {
                    this.memoizedIsInitialized = 0;
                    return false;
                }
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, getNetworkBytes());
            }
            for (int i = 0; i < this.outputs_.size(); i++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.outputs_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeUInt64(3, this.time_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeUInt64(4, this.expires_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBytes(5, getMemoBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBytes(6, getPaymentUrlBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeBytes(7, this.merchantData_);
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeBytesSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeBytesSize(1, getNetworkBytes()) + 0 : 0;
            for (int i2 = 0; i2 < this.outputs_.size(); i2++) {
                computeBytesSize += CodedOutputStream.computeMessageSize(2, (MessageLite) this.outputs_.get(i2));
            }
            if ((this.bitField0_ & 2) == 2) {
                computeBytesSize += CodedOutputStream.computeUInt64Size(3, this.time_);
            }
            if ((this.bitField0_ & 4) == 4) {
                computeBytesSize += CodedOutputStream.computeUInt64Size(4, this.expires_);
            }
            if ((this.bitField0_ & 8) == 8) {
                computeBytesSize += CodedOutputStream.computeBytesSize(5, getMemoBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                computeBytesSize += CodedOutputStream.computeBytesSize(6, getPaymentUrlBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                computeBytesSize += CodedOutputStream.computeBytesSize(7, this.merchantData_);
            }
            int serializedSize = computeBytesSize + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static PaymentDetails parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PaymentDetails) PARSER.parseFrom(byteString);
        }

        public static PaymentDetails parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PaymentDetails) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PaymentDetails parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PaymentDetails) PARSER.parseFrom(bArr);
        }

        public static PaymentDetails parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PaymentDetails) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PaymentDetails parseFrom(InputStream inputStream) throws IOException {
            return (PaymentDetails) PARSER.parseFrom(inputStream);
        }

        public static PaymentDetails parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PaymentDetails) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static PaymentDetails parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PaymentDetails) PARSER.parseDelimitedFrom(inputStream);
        }

        public static PaymentDetails parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PaymentDetails) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static PaymentDetails parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PaymentDetails) PARSER.parseFrom(codedInputStream);
        }

        public static PaymentDetails parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PaymentDetails) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PaymentDetails paymentDetails) {
            return newBuilder().mergeFrom(paymentDetails);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface PaymentDetailsOrBuilder extends MessageOrBuilder {
        long getExpires();

        String getMemo();

        ByteString getMemoBytes();

        ByteString getMerchantData();

        String getNetwork();

        ByteString getNetworkBytes();

        Output getOutputs(int i);

        int getOutputsCount();

        List<Output> getOutputsList();

        OutputOrBuilder getOutputsOrBuilder(int i);

        List<? extends OutputOrBuilder> getOutputsOrBuilderList();

        String getPaymentUrl();

        ByteString getPaymentUrlBytes();

        long getTime();

        boolean hasExpires();

        boolean hasMemo();

        boolean hasMerchantData();

        boolean hasNetwork();

        boolean hasPaymentUrl();

        boolean hasTime();
    }

    public interface PaymentOrBuilder extends MessageOrBuilder {
        String getMemo();

        ByteString getMemoBytes();

        ByteString getMerchantData();

        Output getRefundTo(int i);

        int getRefundToCount();

        List<Output> getRefundToList();

        OutputOrBuilder getRefundToOrBuilder(int i);

        List<? extends OutputOrBuilder> getRefundToOrBuilderList();

        ByteString getTransactions(int i);

        int getTransactionsCount();

        List<ByteString> getTransactionsList();

        boolean hasMemo();

        boolean hasMerchantData();
    }

    public static final class PaymentRequest extends GeneratedMessage implements PaymentRequestOrBuilder {
        public static Parser<PaymentRequest> PARSER = new AbstractParser<PaymentRequest>() {
            public PaymentRequest parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PaymentRequest(codedInputStream, extensionRegistryLite);
            }
        };
        public static final int PAYMENT_DETAILS_VERSION_FIELD_NUMBER = 1;
        public static final int PKI_DATA_FIELD_NUMBER = 3;
        public static final int PKI_TYPE_FIELD_NUMBER = 2;
        public static final int SERIALIZED_PAYMENT_DETAILS_FIELD_NUMBER = 4;
        public static final int SIGNATURE_FIELD_NUMBER = 5;
        private static final PaymentRequest defaultInstance = new PaymentRequest(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        /* access modifiers changed from: private */
        public int paymentDetailsVersion_;
        /* access modifiers changed from: private */
        public ByteString pkiData_;
        /* access modifiers changed from: private */
        public Object pkiType_;
        /* access modifiers changed from: private */
        public ByteString serializedPaymentDetails_;
        /* access modifiers changed from: private */
        public ByteString signature_;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements PaymentRequestOrBuilder {
            private int bitField0_;
            private int paymentDetailsVersion_;
            private ByteString pkiData_;
            private Object pkiType_;
            private ByteString serializedPaymentDetails_;
            private ByteString signature_;

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_payments_PaymentRequest_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_payments_PaymentRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(PaymentRequest.class, Builder.class);
            }

            private Builder() {
                this.paymentDetailsVersion_ = 1;
                this.pkiType_ = "none";
                this.pkiData_ = ByteString.EMPTY;
                this.serializedPaymentDetails_ = ByteString.EMPTY;
                this.signature_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.paymentDetailsVersion_ = 1;
                this.pkiType_ = "none";
                this.pkiData_ = ByteString.EMPTY;
                this.serializedPaymentDetails_ = ByteString.EMPTY;
                this.signature_ = ByteString.EMPTY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                PaymentRequest.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                PaymentRequest.super.clear();
                this.paymentDetailsVersion_ = 1;
                this.bitField0_ &= -2;
                this.pkiType_ = "none";
                this.bitField0_ &= -3;
                this.pkiData_ = ByteString.EMPTY;
                this.bitField0_ &= -5;
                this.serializedPaymentDetails_ = ByteString.EMPTY;
                this.bitField0_ &= -9;
                this.signature_ = ByteString.EMPTY;
                this.bitField0_ &= -17;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_payments_PaymentRequest_descriptor;
            }

            public PaymentRequest getDefaultInstanceForType() {
                return PaymentRequest.getDefaultInstance();
            }

            public PaymentRequest build() {
                PaymentRequest buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public PaymentRequest buildPartial() {
                PaymentRequest paymentRequest = new PaymentRequest((com.google.protobuf.GeneratedMessage.Builder) this);
                int i = this.bitField0_;
                int i2 = 1;
                if ((i & 1) != 1) {
                    i2 = 0;
                }
                paymentRequest.paymentDetailsVersion_ = this.paymentDetailsVersion_;
                if ((i & 2) == 2) {
                    i2 |= 2;
                }
                paymentRequest.pkiType_ = this.pkiType_;
                if ((i & 4) == 4) {
                    i2 |= 4;
                }
                paymentRequest.pkiData_ = this.pkiData_;
                if ((i & 8) == 8) {
                    i2 |= 8;
                }
                paymentRequest.serializedPaymentDetails_ = this.serializedPaymentDetails_;
                if ((i & 16) == 16) {
                    i2 |= 16;
                }
                paymentRequest.signature_ = this.signature_;
                paymentRequest.bitField0_ = i2;
                onBuilt();
                return paymentRequest;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof PaymentRequest) {
                    return mergeFrom((PaymentRequest) message);
                }
                PaymentRequest.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PaymentRequest paymentRequest) {
                if (paymentRequest == PaymentRequest.getDefaultInstance()) {
                    return this;
                }
                if (paymentRequest.hasPaymentDetailsVersion()) {
                    setPaymentDetailsVersion(paymentRequest.getPaymentDetailsVersion());
                }
                if (paymentRequest.hasPkiType()) {
                    this.bitField0_ |= 2;
                    this.pkiType_ = paymentRequest.pkiType_;
                    onChanged();
                }
                if (paymentRequest.hasPkiData()) {
                    setPkiData(paymentRequest.getPkiData());
                }
                if (paymentRequest.hasSerializedPaymentDetails()) {
                    setSerializedPaymentDetails(paymentRequest.getSerializedPaymentDetails());
                }
                if (paymentRequest.hasSignature()) {
                    setSignature(paymentRequest.getSignature());
                }
                mergeUnknownFields(paymentRequest.getUnknownFields());
                return this;
            }

            public final boolean isInitialized() {
                return hasSerializedPaymentDetails();
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                PaymentRequest paymentRequest;
                PaymentRequest paymentRequest2 = null;
                try {
                    PaymentRequest paymentRequest3 = (PaymentRequest) PaymentRequest.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (paymentRequest3 != null) {
                        mergeFrom(paymentRequest3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    paymentRequest = (PaymentRequest) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    paymentRequest2 = paymentRequest;
                }
                if (paymentRequest2 != null) {
                    mergeFrom(paymentRequest2);
                }
                throw th;
            }

            public boolean hasPaymentDetailsVersion() {
                return (this.bitField0_ & 1) == 1;
            }

            public int getPaymentDetailsVersion() {
                return this.paymentDetailsVersion_;
            }

            public Builder setPaymentDetailsVersion(int i) {
                this.bitField0_ |= 1;
                this.paymentDetailsVersion_ = i;
                onChanged();
                return this;
            }

            public Builder clearPaymentDetailsVersion() {
                this.bitField0_ &= -2;
                this.paymentDetailsVersion_ = 1;
                onChanged();
                return this;
            }

            public boolean hasPkiType() {
                return (this.bitField0_ & 2) == 2;
            }

            public String getPkiType() {
                Object obj = this.pkiType_;
                if (obj instanceof String) {
                    return (String) obj;
                }
                ByteString byteString = (ByteString) obj;
                String stringUtf8 = byteString.toStringUtf8();
                if (byteString.isValidUtf8()) {
                    this.pkiType_ = stringUtf8;
                }
                return stringUtf8;
            }

            public ByteString getPkiTypeBytes() {
                Object obj = this.pkiType_;
                if (!(obj instanceof String)) {
                    return (ByteString) obj;
                }
                ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.pkiType_ = copyFromUtf8;
                return copyFromUtf8;
            }

            public Builder setPkiType(String str) {
                if (str != null) {
                    this.bitField0_ |= 2;
                    this.pkiType_ = str;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearPkiType() {
                this.bitField0_ &= -3;
                this.pkiType_ = PaymentRequest.getDefaultInstance().getPkiType();
                onChanged();
                return this;
            }

            public Builder setPkiTypeBytes(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 2;
                    this.pkiType_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public boolean hasPkiData() {
                return (this.bitField0_ & 4) == 4;
            }

            public ByteString getPkiData() {
                return this.pkiData_;
            }

            public Builder setPkiData(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 4;
                    this.pkiData_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearPkiData() {
                this.bitField0_ &= -5;
                this.pkiData_ = PaymentRequest.getDefaultInstance().getPkiData();
                onChanged();
                return this;
            }

            public boolean hasSerializedPaymentDetails() {
                return (this.bitField0_ & 8) == 8;
            }

            public ByteString getSerializedPaymentDetails() {
                return this.serializedPaymentDetails_;
            }

            public Builder setSerializedPaymentDetails(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 8;
                    this.serializedPaymentDetails_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearSerializedPaymentDetails() {
                this.bitField0_ &= -9;
                this.serializedPaymentDetails_ = PaymentRequest.getDefaultInstance().getSerializedPaymentDetails();
                onChanged();
                return this;
            }

            public boolean hasSignature() {
                return (this.bitField0_ & 16) == 16;
            }

            public ByteString getSignature() {
                return this.signature_;
            }

            public Builder setSignature(ByteString byteString) {
                if (byteString != null) {
                    this.bitField0_ |= 16;
                    this.signature_ = byteString;
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder clearSignature() {
                this.bitField0_ &= -17;
                this.signature_ = PaymentRequest.getDefaultInstance().getSignature();
                onChanged();
                return this;
            }
        }

        private PaymentRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private PaymentRequest(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static PaymentRequest getDefaultInstance() {
            return defaultInstance;
        }

        public PaymentRequest getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [com.google.protobuf.MessageLite, org.bitcoin.protocols.payments.Protos$PaymentRequest] */
        private PaymentRequest(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.paymentDetailsVersion_ = codedInputStream.readUInt32();
                        } else if (readTag == 18) {
                            ByteString readBytes = codedInputStream.readBytes();
                            this.bitField0_ |= 2;
                            this.pkiType_ = readBytes;
                        } else if (readTag == 26) {
                            this.bitField0_ |= 4;
                            this.pkiData_ = codedInputStream.readBytes();
                        } else if (readTag == 34) {
                            this.bitField0_ |= 8;
                            this.serializedPaymentDetails_ = codedInputStream.readBytes();
                        } else if (readTag == 42) {
                            this.bitField0_ |= 16;
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
            return Protos.internal_static_payments_PaymentRequest_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_payments_PaymentRequest_fieldAccessorTable.ensureFieldAccessorsInitialized(PaymentRequest.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<PaymentRequest> getParserForType() {
            return PARSER;
        }

        public boolean hasPaymentDetailsVersion() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getPaymentDetailsVersion() {
            return this.paymentDetailsVersion_;
        }

        public boolean hasPkiType() {
            return (this.bitField0_ & 2) == 2;
        }

        public String getPkiType() {
            Object obj = this.pkiType_;
            if (obj instanceof String) {
                return (String) obj;
            }
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.pkiType_ = stringUtf8;
            }
            return stringUtf8;
        }

        public ByteString getPkiTypeBytes() {
            Object obj = this.pkiType_;
            if (!(obj instanceof String)) {
                return (ByteString) obj;
            }
            ByteString copyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.pkiType_ = copyFromUtf8;
            return copyFromUtf8;
        }

        public boolean hasPkiData() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getPkiData() {
            return this.pkiData_;
        }

        public boolean hasSerializedPaymentDetails() {
            return (this.bitField0_ & 8) == 8;
        }

        public ByteString getSerializedPaymentDetails() {
            return this.serializedPaymentDetails_;
        }

        public boolean hasSignature() {
            return (this.bitField0_ & 16) == 16;
        }

        public ByteString getSignature() {
            return this.signature_;
        }

        private void initFields() {
            this.paymentDetailsVersion_ = 1;
            this.pkiType_ = "none";
            this.pkiData_ = ByteString.EMPTY;
            this.serializedPaymentDetails_ = ByteString.EMPTY;
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
            if (!hasSerializedPaymentDetails()) {
                this.memoizedIsInitialized = 0;
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeUInt32(1, this.paymentDetailsVersion_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, getPkiTypeBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, this.pkiData_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBytes(4, this.serializedPaymentDetails_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBytes(5, this.signature_);
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
                i2 = 0 + CodedOutputStream.computeUInt32Size(1, this.paymentDetailsVersion_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, getPkiTypeBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeBytesSize(3, this.pkiData_);
            }
            if ((this.bitField0_ & 8) == 8) {
                i2 += CodedOutputStream.computeBytesSize(4, this.serializedPaymentDetails_);
            }
            if ((this.bitField0_ & 16) == 16) {
                i2 += CodedOutputStream.computeBytesSize(5, this.signature_);
            }
            int serializedSize = i2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static PaymentRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PaymentRequest) PARSER.parseFrom(byteString);
        }

        public static PaymentRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PaymentRequest) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PaymentRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PaymentRequest) PARSER.parseFrom(bArr);
        }

        public static PaymentRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PaymentRequest) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PaymentRequest parseFrom(InputStream inputStream) throws IOException {
            return (PaymentRequest) PARSER.parseFrom(inputStream);
        }

        public static PaymentRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PaymentRequest) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static PaymentRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PaymentRequest) PARSER.parseDelimitedFrom(inputStream);
        }

        public static PaymentRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PaymentRequest) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static PaymentRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PaymentRequest) PARSER.parseFrom(codedInputStream);
        }

        public static PaymentRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PaymentRequest) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(PaymentRequest paymentRequest) {
            return newBuilder().mergeFrom(paymentRequest);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface PaymentRequestOrBuilder extends MessageOrBuilder {
        int getPaymentDetailsVersion();

        ByteString getPkiData();

        String getPkiType();

        ByteString getPkiTypeBytes();

        ByteString getSerializedPaymentDetails();

        ByteString getSignature();

        boolean hasPaymentDetailsVersion();

        boolean hasPkiData();

        boolean hasPkiType();

        boolean hasSerializedPaymentDetails();

        boolean hasSignature();
    }

    public static final class X509Certificates extends GeneratedMessage implements X509CertificatesOrBuilder {
        public static final int CERTIFICATE_FIELD_NUMBER = 1;
        public static Parser<X509Certificates> PARSER = new AbstractParser<X509Certificates>() {
            public X509Certificates parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new X509Certificates(codedInputStream, extensionRegistryLite);
            }
        };
        private static final X509Certificates defaultInstance = new X509Certificates(true);
        private static final long serialVersionUID = 0;
        /* access modifiers changed from: private */
        public List<ByteString> certificate_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder> implements X509CertificatesOrBuilder {
            private int bitField0_;
            private List<ByteString> certificate_;

            public final boolean isInitialized() {
                return true;
            }

            public static final Descriptor getDescriptor() {
                return Protos.internal_static_payments_X509Certificates_descriptor;
            }

            /* access modifiers changed from: protected */
            public FieldAccessorTable internalGetFieldAccessorTable() {
                return Protos.internal_static_payments_X509Certificates_fieldAccessorTable.ensureFieldAccessorsInitialized(X509Certificates.class, Builder.class);
            }

            private Builder() {
                this.certificate_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(BuilderParent builderParent) {
                super(builderParent);
                this.certificate_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                X509Certificates.alwaysUseFieldBuilders;
            }

            /* access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            public Builder clear() {
                X509Certificates.super.clear();
                this.certificate_ = Collections.emptyList();
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clone() {
                return create().mergeFrom(buildPartial());
            }

            public Descriptor getDescriptorForType() {
                return Protos.internal_static_payments_X509Certificates_descriptor;
            }

            public X509Certificates getDefaultInstanceForType() {
                return X509Certificates.getDefaultInstance();
            }

            public X509Certificates build() {
                X509Certificates buildPartial = buildPartial();
                if (buildPartial.isInitialized()) {
                    return buildPartial;
                }
                throw newUninitializedMessageException(buildPartial);
            }

            public X509Certificates buildPartial() {
                X509Certificates x509Certificates = new X509Certificates((com.google.protobuf.GeneratedMessage.Builder) this);
                if ((this.bitField0_ & 1) == 1) {
                    this.certificate_ = Collections.unmodifiableList(this.certificate_);
                    this.bitField0_ &= -2;
                }
                x509Certificates.certificate_ = this.certificate_;
                onBuilt();
                return x509Certificates;
            }

            public Builder mergeFrom(Message message) {
                if (message instanceof X509Certificates) {
                    return mergeFrom((X509Certificates) message);
                }
                X509Certificates.super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(X509Certificates x509Certificates) {
                if (x509Certificates == X509Certificates.getDefaultInstance()) {
                    return this;
                }
                if (!x509Certificates.certificate_.isEmpty()) {
                    if (this.certificate_.isEmpty()) {
                        this.certificate_ = x509Certificates.certificate_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureCertificateIsMutable();
                        this.certificate_.addAll(x509Certificates.certificate_);
                    }
                    onChanged();
                }
                mergeUnknownFields(x509Certificates.getUnknownFields());
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                X509Certificates x509Certificates;
                X509Certificates x509Certificates2 = null;
                try {
                    X509Certificates x509Certificates3 = (X509Certificates) X509Certificates.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
                    if (x509Certificates3 != null) {
                        mergeFrom(x509Certificates3);
                    }
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    x509Certificates = (X509Certificates) e.getUnfinishedMessage();
                    throw e;
                } catch (Throwable th) {
                    th = th;
                    x509Certificates2 = x509Certificates;
                }
                if (x509Certificates2 != null) {
                    mergeFrom(x509Certificates2);
                }
                throw th;
            }

            private void ensureCertificateIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.certificate_ = new ArrayList(this.certificate_);
                    this.bitField0_ |= 1;
                }
            }

            public List<ByteString> getCertificateList() {
                return Collections.unmodifiableList(this.certificate_);
            }

            public int getCertificateCount() {
                return this.certificate_.size();
            }

            public ByteString getCertificate(int i) {
                return (ByteString) this.certificate_.get(i);
            }

            public Builder setCertificate(int i, ByteString byteString) {
                if (byteString != null) {
                    ensureCertificateIsMutable();
                    this.certificate_.set(i, byteString);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addCertificate(ByteString byteString) {
                if (byteString != null) {
                    ensureCertificateIsMutable();
                    this.certificate_.add(byteString);
                    onChanged();
                    return this;
                }
                throw new NullPointerException();
            }

            public Builder addAllCertificate(Iterable<? extends ByteString> iterable) {
                ensureCertificateIsMutable();
                com.google.protobuf.AbstractMessageLite.Builder.addAll(iterable, this.certificate_);
                onChanged();
                return this;
            }

            public Builder clearCertificate() {
                this.certificate_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
                return this;
            }
        }

        private X509Certificates(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private X509Certificates(boolean z) {
            this.memoizedIsInitialized = -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static X509Certificates getDefaultInstance() {
            return defaultInstance;
        }

        public X509Certificates getDefaultInstanceForType() {
            return defaultInstance;
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [com.google.protobuf.MessageLite, org.bitcoin.protocols.payments.Protos$X509Certificates] */
        private X509Certificates(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.certificate_ = new ArrayList();
                                z2 |= true;
                            }
                            this.certificate_.add(codedInputStream.readBytes());
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
                        this.certificate_ = Collections.unmodifiableList(this.certificate_);
                    }
                    this.unknownFields = newBuilder.build();
                    makeExtensionsImmutable();
                    throw th;
                }
            }
            if (z2 && true) {
                this.certificate_ = Collections.unmodifiableList(this.certificate_);
            }
            this.unknownFields = newBuilder.build();
            makeExtensionsImmutable();
        }

        public static final Descriptor getDescriptor() {
            return Protos.internal_static_payments_X509Certificates_descriptor;
        }

        /* access modifiers changed from: protected */
        public FieldAccessorTable internalGetFieldAccessorTable() {
            return Protos.internal_static_payments_X509Certificates_fieldAccessorTable.ensureFieldAccessorsInitialized(X509Certificates.class, Builder.class);
        }

        static {
            defaultInstance.initFields();
        }

        public Parser<X509Certificates> getParserForType() {
            return PARSER;
        }

        public List<ByteString> getCertificateList() {
            return this.certificate_;
        }

        public int getCertificateCount() {
            return this.certificate_.size();
        }

        public ByteString getCertificate(int i) {
            return (ByteString) this.certificate_.get(i);
        }

        private void initFields() {
            this.certificate_ = Collections.emptyList();
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
            for (int i = 0; i < this.certificate_.size(); i++) {
                codedOutputStream.writeBytes(1, (ByteString) this.certificate_.get(i));
            }
            getUnknownFields().writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.certificate_.size(); i3++) {
                i2 += CodedOutputStream.computeBytesSizeNoTag((ByteString) this.certificate_.get(i3));
            }
            int size = 0 + i2 + (getCertificateList().size() * 1) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size;
            return size;
        }

        /* access modifiers changed from: protected */
        public Object writeReplace() throws ObjectStreamException {
            return Protos.super.writeReplace();
        }

        public static X509Certificates parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (X509Certificates) PARSER.parseFrom(byteString);
        }

        public static X509Certificates parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (X509Certificates) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static X509Certificates parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (X509Certificates) PARSER.parseFrom(bArr);
        }

        public static X509Certificates parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (X509Certificates) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static X509Certificates parseFrom(InputStream inputStream) throws IOException {
            return (X509Certificates) PARSER.parseFrom(inputStream);
        }

        public static X509Certificates parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (X509Certificates) PARSER.parseFrom(inputStream, extensionRegistryLite);
        }

        public static X509Certificates parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (X509Certificates) PARSER.parseDelimitedFrom(inputStream);
        }

        public static X509Certificates parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (X509Certificates) PARSER.parseDelimitedFrom(inputStream, extensionRegistryLite);
        }

        public static X509Certificates parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (X509Certificates) PARSER.parseFrom(codedInputStream);
        }

        public static X509Certificates parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (X509Certificates) PARSER.parseFrom(codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(X509Certificates x509Certificates) {
            return newBuilder().mergeFrom(x509Certificates);
        }

        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* access modifiers changed from: protected */
        public Builder newBuilderForType(BuilderParent builderParent) {
            return new Builder(builderParent);
        }
    }

    public interface X509CertificatesOrBuilder extends MessageOrBuilder {
        ByteString getCertificate(int i);

        int getCertificateCount();

        List<ByteString> getCertificateList();
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
    }

    private Protos() {
    }

    public static FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n\u0014paymentrequest.proto\u0012\bpayments\"+\n\u0006Output\u0012\u0011\n\u0006amount\u0018\u0001 \u0001(\u0004:\u00010\u0012\u000e\n\u0006script\u0018\u0002 \u0002(\f\"£\u0001\n\u000ePaymentDetails\u0012\u0015\n\u0007network\u0018\u0001 \u0001(\t:\u0004main\u0012!\n\u0007outputs\u0018\u0002 \u0003(\u000b2\u0010.payments.Output\u0012\f\n\u0004time\u0018\u0003 \u0002(\u0004\u0012\u000f\n\u0007expires\u0018\u0004 \u0001(\u0004\u0012\f\n\u0004memo\u0018\u0005 \u0001(\t\u0012\u0013\n\u000bpayment_url\u0018\u0006 \u0001(\t\u0012\u0015\n\rmerchant_data\u0018\u0007 \u0001(\f\"\u0001\n\u000ePaymentRequest\u0012\"\n\u0017payment_details_version\u0018\u0001 \u0001(\r:\u00011\u0012\u0016\n\bpki_type\u0018\u0002 \u0001(\t:\u0004none\u0012\u0010\n\bpki_data\u0018\u0003 \u0001(\f\u0012\"\n\u001aserialized_payment_details\u0018\u0004 \u0002(\f\u0012\u0011\n\tsignature\u0018\u0005 \u0001(\f\"'\n\u0010X", "509Certificates\u0012\u0013\n\u000bcertificate\u0018\u0001 \u0003(\f\"i\n\u0007Payment\u0012\u0015\n\rmerchant_data\u0018\u0001 \u0001(\f\u0012\u0014\n\ftransactions\u0018\u0002 \u0003(\f\u0012#\n\trefund_to\u0018\u0003 \u0003(\u000b2\u0010.payments.Output\u0012\f\n\u0004memo\u0018\u0004 \u0001(\t\">\n\nPaymentACK\u0012\"\n\u0007payment\u0018\u0001 \u0002(\u000b2\u0011.payments.Payment\u0012\f\n\u0004memo\u0018\u0002 \u0001(\tB(\n\u001eorg.bitcoin.protocols.paymentsB\u0006Protos"}, new FileDescriptor[0], new InternalDescriptorAssigner() {
            public ExtensionRegistry assignDescriptors(FileDescriptor fileDescriptor) {
                Protos.descriptor = fileDescriptor;
                return null;
            }
        });
        String str = "Memo";
        internal_static_payments_Payment_fieldAccessorTable = new FieldAccessorTable(internal_static_payments_Payment_descriptor, new String[]{"MerchantData", "Transactions", "RefundTo", str});
        internal_static_payments_PaymentACK_fieldAccessorTable = new FieldAccessorTable(internal_static_payments_PaymentACK_descriptor, new String[]{"Payment", str});
    }
}
