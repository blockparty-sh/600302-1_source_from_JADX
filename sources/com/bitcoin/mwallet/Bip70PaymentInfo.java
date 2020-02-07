package com.bitcoin.mwallet;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.MethodToInvoke;
import com.google.protobuf.Internal.ProtobufList;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import org.bitcoinj.script.ScriptOpCodes;

public final class Bip70PaymentInfo extends GeneratedMessageLite<Bip70PaymentInfo, Builder> implements Bip70PaymentInfoOrBuilder {
    public static final int CONVERSION_ASSETS_FIELD_NUMBER = 9;
    public static final int CONVERSION_RATE_FIELD_NUMBER = 8;
    public static final int CREATE_TIME_FIELD_NUMBER = 14;
    /* access modifiers changed from: private */
    public static final Bip70PaymentInfo DEFAULT_INSTANCE = new Bip70PaymentInfo();
    public static final int EMAIL_FIELD_NUMBER = 11;
    public static final int EXPIRY_TIME_FIELD_NUMBER = 15;
    public static final int INVOICE_AMOUNT_FIELD_NUMBER = 5;
    public static final int INVOICE_CURRENCY_FIELD_NUMBER = 4;
    public static final int ITEM_DESC_FIELD_NUMBER = 10;
    public static final int MERCHANT_NAME_FIELD_NUMBER = 2;
    public static final int MERCHANT_PROCESSOR_FIELD_NUMBER = 1;
    public static final int MERCHANT_WEBSITE_FIELD_NUMBER = 12;
    public static final int OUTPUTS_FIELD_NUMBER = 16;
    private static volatile Parser<Bip70PaymentInfo> PARSER = null;
    public static final int PAYMENT_AMOUNT_FIELD_NUMBER = 7;
    public static final int PAYMENT_CURRENCY_FIELD_NUMBER = 6;
    public static final int PHONE_FIELD_NUMBER = 13;
    public static final int VERIFICATION_FIELD_NUMBER = 3;
    private int bitField0_;
    private String conversionAssets_;
    private String conversionRate_;
    private long createTime_;
    private String email_;
    private long expiryTime_;
    private String invoiceAmount_;
    private String invoiceCurrency_;
    private String itemDesc_;
    private String merchantName_;
    private String merchantProcessor_;
    private String merchantWebsite_;
    private ProtobufList<Bip70Output> outputs_ = emptyProtobufList();
    private String paymentAmount_;
    private String paymentCurrency_;
    private String phone_;
    private int verification_;

    public static final class Builder extends com.google.protobuf.GeneratedMessageLite.Builder<Bip70PaymentInfo, Builder> implements Bip70PaymentInfoOrBuilder {
        private Builder() {
            super(Bip70PaymentInfo.DEFAULT_INSTANCE);
        }

        public String getMerchantProcessor() {
            return ((Bip70PaymentInfo) this.instance).getMerchantProcessor();
        }

        public ByteString getMerchantProcessorBytes() {
            return ((Bip70PaymentInfo) this.instance).getMerchantProcessorBytes();
        }

        public Builder setMerchantProcessor(String str) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setMerchantProcessor(str);
            return this;
        }

        public Builder clearMerchantProcessor() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearMerchantProcessor();
            return this;
        }

        public Builder setMerchantProcessorBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setMerchantProcessorBytes(byteString);
            return this;
        }

        public String getMerchantName() {
            return ((Bip70PaymentInfo) this.instance).getMerchantName();
        }

        public ByteString getMerchantNameBytes() {
            return ((Bip70PaymentInfo) this.instance).getMerchantNameBytes();
        }

        public Builder setMerchantName(String str) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setMerchantName(str);
            return this;
        }

        public Builder clearMerchantName() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearMerchantName();
            return this;
        }

        public Builder setMerchantNameBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setMerchantNameBytes(byteString);
            return this;
        }

        public int getVerification() {
            return ((Bip70PaymentInfo) this.instance).getVerification();
        }

        public Builder setVerification(int i) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setVerification(i);
            return this;
        }

        public Builder clearVerification() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearVerification();
            return this;
        }

        public String getInvoiceCurrency() {
            return ((Bip70PaymentInfo) this.instance).getInvoiceCurrency();
        }

        public ByteString getInvoiceCurrencyBytes() {
            return ((Bip70PaymentInfo) this.instance).getInvoiceCurrencyBytes();
        }

        public Builder setInvoiceCurrency(String str) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setInvoiceCurrency(str);
            return this;
        }

        public Builder clearInvoiceCurrency() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearInvoiceCurrency();
            return this;
        }

        public Builder setInvoiceCurrencyBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setInvoiceCurrencyBytes(byteString);
            return this;
        }

        public String getInvoiceAmount() {
            return ((Bip70PaymentInfo) this.instance).getInvoiceAmount();
        }

        public ByteString getInvoiceAmountBytes() {
            return ((Bip70PaymentInfo) this.instance).getInvoiceAmountBytes();
        }

        public Builder setInvoiceAmount(String str) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setInvoiceAmount(str);
            return this;
        }

        public Builder clearInvoiceAmount() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearInvoiceAmount();
            return this;
        }

        public Builder setInvoiceAmountBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setInvoiceAmountBytes(byteString);
            return this;
        }

        public String getPaymentCurrency() {
            return ((Bip70PaymentInfo) this.instance).getPaymentCurrency();
        }

        public ByteString getPaymentCurrencyBytes() {
            return ((Bip70PaymentInfo) this.instance).getPaymentCurrencyBytes();
        }

        public Builder setPaymentCurrency(String str) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setPaymentCurrency(str);
            return this;
        }

        public Builder clearPaymentCurrency() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearPaymentCurrency();
            return this;
        }

        public Builder setPaymentCurrencyBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setPaymentCurrencyBytes(byteString);
            return this;
        }

        public String getPaymentAmount() {
            return ((Bip70PaymentInfo) this.instance).getPaymentAmount();
        }

        public ByteString getPaymentAmountBytes() {
            return ((Bip70PaymentInfo) this.instance).getPaymentAmountBytes();
        }

        public Builder setPaymentAmount(String str) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setPaymentAmount(str);
            return this;
        }

        public Builder clearPaymentAmount() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearPaymentAmount();
            return this;
        }

        public Builder setPaymentAmountBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setPaymentAmountBytes(byteString);
            return this;
        }

        public String getConversionRate() {
            return ((Bip70PaymentInfo) this.instance).getConversionRate();
        }

        public ByteString getConversionRateBytes() {
            return ((Bip70PaymentInfo) this.instance).getConversionRateBytes();
        }

        public Builder setConversionRate(String str) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setConversionRate(str);
            return this;
        }

        public Builder clearConversionRate() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearConversionRate();
            return this;
        }

        public Builder setConversionRateBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setConversionRateBytes(byteString);
            return this;
        }

        public String getConversionAssets() {
            return ((Bip70PaymentInfo) this.instance).getConversionAssets();
        }

        public ByteString getConversionAssetsBytes() {
            return ((Bip70PaymentInfo) this.instance).getConversionAssetsBytes();
        }

        public Builder setConversionAssets(String str) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setConversionAssets(str);
            return this;
        }

        public Builder clearConversionAssets() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearConversionAssets();
            return this;
        }

        public Builder setConversionAssetsBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setConversionAssetsBytes(byteString);
            return this;
        }

        public String getItemDesc() {
            return ((Bip70PaymentInfo) this.instance).getItemDesc();
        }

        public ByteString getItemDescBytes() {
            return ((Bip70PaymentInfo) this.instance).getItemDescBytes();
        }

        public Builder setItemDesc(String str) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setItemDesc(str);
            return this;
        }

        public Builder clearItemDesc() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearItemDesc();
            return this;
        }

        public Builder setItemDescBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setItemDescBytes(byteString);
            return this;
        }

        public String getEmail() {
            return ((Bip70PaymentInfo) this.instance).getEmail();
        }

        public ByteString getEmailBytes() {
            return ((Bip70PaymentInfo) this.instance).getEmailBytes();
        }

        public Builder setEmail(String str) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setEmail(str);
            return this;
        }

        public Builder clearEmail() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearEmail();
            return this;
        }

        public Builder setEmailBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setEmailBytes(byteString);
            return this;
        }

        public String getMerchantWebsite() {
            return ((Bip70PaymentInfo) this.instance).getMerchantWebsite();
        }

        public ByteString getMerchantWebsiteBytes() {
            return ((Bip70PaymentInfo) this.instance).getMerchantWebsiteBytes();
        }

        public Builder setMerchantWebsite(String str) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setMerchantWebsite(str);
            return this;
        }

        public Builder clearMerchantWebsite() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearMerchantWebsite();
            return this;
        }

        public Builder setMerchantWebsiteBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setMerchantWebsiteBytes(byteString);
            return this;
        }

        public String getPhone() {
            return ((Bip70PaymentInfo) this.instance).getPhone();
        }

        public ByteString getPhoneBytes() {
            return ((Bip70PaymentInfo) this.instance).getPhoneBytes();
        }

        public Builder setPhone(String str) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setPhone(str);
            return this;
        }

        public Builder clearPhone() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearPhone();
            return this;
        }

        public Builder setPhoneBytes(ByteString byteString) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setPhoneBytes(byteString);
            return this;
        }

        public long getCreateTime() {
            return ((Bip70PaymentInfo) this.instance).getCreateTime();
        }

        public Builder setCreateTime(long j) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setCreateTime(j);
            return this;
        }

        public Builder clearCreateTime() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearCreateTime();
            return this;
        }

        public long getExpiryTime() {
            return ((Bip70PaymentInfo) this.instance).getExpiryTime();
        }

        public Builder setExpiryTime(long j) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setExpiryTime(j);
            return this;
        }

        public Builder clearExpiryTime() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearExpiryTime();
            return this;
        }

        public List<Bip70Output> getOutputsList() {
            return Collections.unmodifiableList(((Bip70PaymentInfo) this.instance).getOutputsList());
        }

        public int getOutputsCount() {
            return ((Bip70PaymentInfo) this.instance).getOutputsCount();
        }

        public Bip70Output getOutputs(int i) {
            return ((Bip70PaymentInfo) this.instance).getOutputs(i);
        }

        public Builder setOutputs(int i, Bip70Output bip70Output) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setOutputs(i, bip70Output);
            return this;
        }

        public Builder setOutputs(int i, com.bitcoin.mwallet.Bip70Output.Builder builder) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).setOutputs(i, builder);
            return this;
        }

        public Builder addOutputs(Bip70Output bip70Output) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).addOutputs(bip70Output);
            return this;
        }

        public Builder addOutputs(int i, Bip70Output bip70Output) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).addOutputs(i, bip70Output);
            return this;
        }

        public Builder addOutputs(com.bitcoin.mwallet.Bip70Output.Builder builder) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).addOutputs(builder);
            return this;
        }

        public Builder addOutputs(int i, com.bitcoin.mwallet.Bip70Output.Builder builder) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).addOutputs(i, builder);
            return this;
        }

        public Builder addAllOutputs(Iterable<? extends Bip70Output> iterable) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).addAllOutputs(iterable);
            return this;
        }

        public Builder clearOutputs() {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).clearOutputs();
            return this;
        }

        public Builder removeOutputs(int i) {
            copyOnWrite();
            ((Bip70PaymentInfo) this.instance).removeOutputs(i);
            return this;
        }
    }

    private Bip70PaymentInfo() {
        String str = "";
        this.merchantProcessor_ = str;
        this.merchantName_ = str;
        this.invoiceCurrency_ = str;
        this.invoiceAmount_ = str;
        this.paymentCurrency_ = str;
        this.paymentAmount_ = str;
        this.conversionRate_ = str;
        this.conversionAssets_ = str;
        this.itemDesc_ = str;
        this.email_ = str;
        this.merchantWebsite_ = str;
        this.phone_ = str;
    }

    public String getMerchantProcessor() {
        return this.merchantProcessor_;
    }

    public ByteString getMerchantProcessorBytes() {
        return ByteString.copyFromUtf8(this.merchantProcessor_);
    }

    /* access modifiers changed from: private */
    public void setMerchantProcessor(String str) {
        if (str != null) {
            this.merchantProcessor_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearMerchantProcessor() {
        this.merchantProcessor_ = getDefaultInstance().getMerchantProcessor();
    }

    /* access modifiers changed from: private */
    public void setMerchantProcessorBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.merchantProcessor_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getMerchantName() {
        return this.merchantName_;
    }

    public ByteString getMerchantNameBytes() {
        return ByteString.copyFromUtf8(this.merchantName_);
    }

    /* access modifiers changed from: private */
    public void setMerchantName(String str) {
        if (str != null) {
            this.merchantName_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearMerchantName() {
        this.merchantName_ = getDefaultInstance().getMerchantName();
    }

    /* access modifiers changed from: private */
    public void setMerchantNameBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.merchantName_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public int getVerification() {
        return this.verification_;
    }

    /* access modifiers changed from: private */
    public void setVerification(int i) {
        this.verification_ = i;
    }

    /* access modifiers changed from: private */
    public void clearVerification() {
        this.verification_ = 0;
    }

    public String getInvoiceCurrency() {
        return this.invoiceCurrency_;
    }

    public ByteString getInvoiceCurrencyBytes() {
        return ByteString.copyFromUtf8(this.invoiceCurrency_);
    }

    /* access modifiers changed from: private */
    public void setInvoiceCurrency(String str) {
        if (str != null) {
            this.invoiceCurrency_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearInvoiceCurrency() {
        this.invoiceCurrency_ = getDefaultInstance().getInvoiceCurrency();
    }

    /* access modifiers changed from: private */
    public void setInvoiceCurrencyBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.invoiceCurrency_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getInvoiceAmount() {
        return this.invoiceAmount_;
    }

    public ByteString getInvoiceAmountBytes() {
        return ByteString.copyFromUtf8(this.invoiceAmount_);
    }

    /* access modifiers changed from: private */
    public void setInvoiceAmount(String str) {
        if (str != null) {
            this.invoiceAmount_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearInvoiceAmount() {
        this.invoiceAmount_ = getDefaultInstance().getInvoiceAmount();
    }

    /* access modifiers changed from: private */
    public void setInvoiceAmountBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.invoiceAmount_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getPaymentCurrency() {
        return this.paymentCurrency_;
    }

    public ByteString getPaymentCurrencyBytes() {
        return ByteString.copyFromUtf8(this.paymentCurrency_);
    }

    /* access modifiers changed from: private */
    public void setPaymentCurrency(String str) {
        if (str != null) {
            this.paymentCurrency_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearPaymentCurrency() {
        this.paymentCurrency_ = getDefaultInstance().getPaymentCurrency();
    }

    /* access modifiers changed from: private */
    public void setPaymentCurrencyBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.paymentCurrency_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getPaymentAmount() {
        return this.paymentAmount_;
    }

    public ByteString getPaymentAmountBytes() {
        return ByteString.copyFromUtf8(this.paymentAmount_);
    }

    /* access modifiers changed from: private */
    public void setPaymentAmount(String str) {
        if (str != null) {
            this.paymentAmount_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearPaymentAmount() {
        this.paymentAmount_ = getDefaultInstance().getPaymentAmount();
    }

    /* access modifiers changed from: private */
    public void setPaymentAmountBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.paymentAmount_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getConversionRate() {
        return this.conversionRate_;
    }

    public ByteString getConversionRateBytes() {
        return ByteString.copyFromUtf8(this.conversionRate_);
    }

    /* access modifiers changed from: private */
    public void setConversionRate(String str) {
        if (str != null) {
            this.conversionRate_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearConversionRate() {
        this.conversionRate_ = getDefaultInstance().getConversionRate();
    }

    /* access modifiers changed from: private */
    public void setConversionRateBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.conversionRate_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getConversionAssets() {
        return this.conversionAssets_;
    }

    public ByteString getConversionAssetsBytes() {
        return ByteString.copyFromUtf8(this.conversionAssets_);
    }

    /* access modifiers changed from: private */
    public void setConversionAssets(String str) {
        if (str != null) {
            this.conversionAssets_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearConversionAssets() {
        this.conversionAssets_ = getDefaultInstance().getConversionAssets();
    }

    /* access modifiers changed from: private */
    public void setConversionAssetsBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.conversionAssets_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getItemDesc() {
        return this.itemDesc_;
    }

    public ByteString getItemDescBytes() {
        return ByteString.copyFromUtf8(this.itemDesc_);
    }

    /* access modifiers changed from: private */
    public void setItemDesc(String str) {
        if (str != null) {
            this.itemDesc_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearItemDesc() {
        this.itemDesc_ = getDefaultInstance().getItemDesc();
    }

    /* access modifiers changed from: private */
    public void setItemDescBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.itemDesc_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getEmail() {
        return this.email_;
    }

    public ByteString getEmailBytes() {
        return ByteString.copyFromUtf8(this.email_);
    }

    /* access modifiers changed from: private */
    public void setEmail(String str) {
        if (str != null) {
            this.email_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearEmail() {
        this.email_ = getDefaultInstance().getEmail();
    }

    /* access modifiers changed from: private */
    public void setEmailBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.email_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getMerchantWebsite() {
        return this.merchantWebsite_;
    }

    public ByteString getMerchantWebsiteBytes() {
        return ByteString.copyFromUtf8(this.merchantWebsite_);
    }

    /* access modifiers changed from: private */
    public void setMerchantWebsite(String str) {
        if (str != null) {
            this.merchantWebsite_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearMerchantWebsite() {
        this.merchantWebsite_ = getDefaultInstance().getMerchantWebsite();
    }

    /* access modifiers changed from: private */
    public void setMerchantWebsiteBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.merchantWebsite_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public String getPhone() {
        return this.phone_;
    }

    public ByteString getPhoneBytes() {
        return ByteString.copyFromUtf8(this.phone_);
    }

    /* access modifiers changed from: private */
    public void setPhone(String str) {
        if (str != null) {
            this.phone_ = str;
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void clearPhone() {
        this.phone_ = getDefaultInstance().getPhone();
    }

    /* access modifiers changed from: private */
    public void setPhoneBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.phone_ = byteString.toStringUtf8();
            return;
        }
        throw new NullPointerException();
    }

    public long getCreateTime() {
        return this.createTime_;
    }

    /* access modifiers changed from: private */
    public void setCreateTime(long j) {
        this.createTime_ = j;
    }

    /* access modifiers changed from: private */
    public void clearCreateTime() {
        this.createTime_ = 0;
    }

    public long getExpiryTime() {
        return this.expiryTime_;
    }

    /* access modifiers changed from: private */
    public void setExpiryTime(long j) {
        this.expiryTime_ = j;
    }

    /* access modifiers changed from: private */
    public void clearExpiryTime() {
        this.expiryTime_ = 0;
    }

    public List<Bip70Output> getOutputsList() {
        return this.outputs_;
    }

    public List<? extends Bip70OutputOrBuilder> getOutputsOrBuilderList() {
        return this.outputs_;
    }

    public int getOutputsCount() {
        return this.outputs_.size();
    }

    public Bip70Output getOutputs(int i) {
        return (Bip70Output) this.outputs_.get(i);
    }

    public Bip70OutputOrBuilder getOutputsOrBuilder(int i) {
        return (Bip70OutputOrBuilder) this.outputs_.get(i);
    }

    private void ensureOutputsIsMutable() {
        if (!this.outputs_.isModifiable()) {
            this.outputs_ = GeneratedMessageLite.mutableCopy(this.outputs_);
        }
    }

    /* access modifiers changed from: private */
    public void setOutputs(int i, Bip70Output bip70Output) {
        if (bip70Output != null) {
            ensureOutputsIsMutable();
            this.outputs_.set(i, bip70Output);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void setOutputs(int i, com.bitcoin.mwallet.Bip70Output.Builder builder) {
        ensureOutputsIsMutable();
        this.outputs_.set(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addOutputs(Bip70Output bip70Output) {
        if (bip70Output != null) {
            ensureOutputsIsMutable();
            this.outputs_.add(bip70Output);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addOutputs(int i, Bip70Output bip70Output) {
        if (bip70Output != null) {
            ensureOutputsIsMutable();
            this.outputs_.add(i, bip70Output);
            return;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: private */
    public void addOutputs(com.bitcoin.mwallet.Bip70Output.Builder builder) {
        ensureOutputsIsMutable();
        this.outputs_.add(builder.build());
    }

    /* access modifiers changed from: private */
    public void addOutputs(int i, com.bitcoin.mwallet.Bip70Output.Builder builder) {
        ensureOutputsIsMutable();
        this.outputs_.add(i, builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllOutputs(Iterable<? extends Bip70Output> iterable) {
        ensureOutputsIsMutable();
        AbstractMessageLite.addAll(iterable, this.outputs_);
    }

    /* access modifiers changed from: private */
    public void clearOutputs() {
        this.outputs_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeOutputs(int i) {
        ensureOutputsIsMutable();
        this.outputs_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.merchantProcessor_.isEmpty()) {
            codedOutputStream.writeString(1, getMerchantProcessor());
        }
        if (!this.merchantName_.isEmpty()) {
            codedOutputStream.writeString(2, getMerchantName());
        }
        int i = this.verification_;
        if (i != 0) {
            codedOutputStream.writeInt32(3, i);
        }
        if (!this.invoiceCurrency_.isEmpty()) {
            codedOutputStream.writeString(4, getInvoiceCurrency());
        }
        if (!this.invoiceAmount_.isEmpty()) {
            codedOutputStream.writeString(5, getInvoiceAmount());
        }
        if (!this.paymentCurrency_.isEmpty()) {
            codedOutputStream.writeString(6, getPaymentCurrency());
        }
        if (!this.paymentAmount_.isEmpty()) {
            codedOutputStream.writeString(7, getPaymentAmount());
        }
        if (!this.conversionRate_.isEmpty()) {
            codedOutputStream.writeString(8, getConversionRate());
        }
        if (!this.conversionAssets_.isEmpty()) {
            codedOutputStream.writeString(9, getConversionAssets());
        }
        if (!this.itemDesc_.isEmpty()) {
            codedOutputStream.writeString(10, getItemDesc());
        }
        if (!this.email_.isEmpty()) {
            codedOutputStream.writeString(11, getEmail());
        }
        if (!this.merchantWebsite_.isEmpty()) {
            codedOutputStream.writeString(12, getMerchantWebsite());
        }
        if (!this.phone_.isEmpty()) {
            codedOutputStream.writeString(13, getPhone());
        }
        long j = this.createTime_;
        if (j != 0) {
            codedOutputStream.writeInt64(14, j);
        }
        long j2 = this.expiryTime_;
        if (j2 != 0) {
            codedOutputStream.writeInt64(15, j2);
        }
        for (int i2 = 0; i2 < this.outputs_.size(); i2++) {
            codedOutputStream.writeMessage(16, (MessageLite) this.outputs_.get(i2));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !this.merchantProcessor_.isEmpty() ? CodedOutputStream.computeStringSize(1, getMerchantProcessor()) + 0 : 0;
        if (!this.merchantName_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(2, getMerchantName());
        }
        int i2 = this.verification_;
        if (i2 != 0) {
            computeStringSize += CodedOutputStream.computeInt32Size(3, i2);
        }
        if (!this.invoiceCurrency_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(4, getInvoiceCurrency());
        }
        if (!this.invoiceAmount_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(5, getInvoiceAmount());
        }
        if (!this.paymentCurrency_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(6, getPaymentCurrency());
        }
        if (!this.paymentAmount_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(7, getPaymentAmount());
        }
        if (!this.conversionRate_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(8, getConversionRate());
        }
        if (!this.conversionAssets_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(9, getConversionAssets());
        }
        if (!this.itemDesc_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(10, getItemDesc());
        }
        if (!this.email_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(11, getEmail());
        }
        if (!this.merchantWebsite_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(12, getMerchantWebsite());
        }
        if (!this.phone_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(13, getPhone());
        }
        long j = this.createTime_;
        if (j != 0) {
            computeStringSize += CodedOutputStream.computeInt64Size(14, j);
        }
        long j2 = this.expiryTime_;
        if (j2 != 0) {
            computeStringSize += CodedOutputStream.computeInt64Size(15, j2);
        }
        for (int i3 = 0; i3 < this.outputs_.size(); i3++) {
            computeStringSize += CodedOutputStream.computeMessageSize(16, (MessageLite) this.outputs_.get(i3));
        }
        this.memoizedSerializedSize = computeStringSize;
        return computeStringSize;
    }

    public static Bip70PaymentInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Bip70PaymentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Bip70PaymentInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Bip70PaymentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Bip70PaymentInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Bip70PaymentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Bip70PaymentInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Bip70PaymentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Bip70PaymentInfo parseFrom(InputStream inputStream) throws IOException {
        return (Bip70PaymentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Bip70PaymentInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Bip70PaymentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Bip70PaymentInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Bip70PaymentInfo) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Bip70PaymentInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Bip70PaymentInfo) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Bip70PaymentInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Bip70PaymentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Bip70PaymentInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Bip70PaymentInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Bip70PaymentInfo bip70PaymentInfo) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(bip70PaymentInfo);
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (methodToInvoke) {
            case NEW_MUTABLE_INSTANCE:
                return new Bip70PaymentInfo();
            case IS_INITIALIZED:
                return DEFAULT_INSTANCE;
            case MAKE_IMMUTABLE:
                this.outputs_.makeImmutable();
                return null;
            case NEW_BUILDER:
                return new Builder();
            case VISIT:
                Visitor visitor = (Visitor) obj;
                Bip70PaymentInfo bip70PaymentInfo = (Bip70PaymentInfo) obj2;
                this.merchantProcessor_ = visitor.visitString(!this.merchantProcessor_.isEmpty(), this.merchantProcessor_, !bip70PaymentInfo.merchantProcessor_.isEmpty(), bip70PaymentInfo.merchantProcessor_);
                this.merchantName_ = visitor.visitString(!this.merchantName_.isEmpty(), this.merchantName_, !bip70PaymentInfo.merchantName_.isEmpty(), bip70PaymentInfo.merchantName_);
                this.verification_ = visitor.visitInt(this.verification_ != 0, this.verification_, bip70PaymentInfo.verification_ != 0, bip70PaymentInfo.verification_);
                this.invoiceCurrency_ = visitor.visitString(!this.invoiceCurrency_.isEmpty(), this.invoiceCurrency_, !bip70PaymentInfo.invoiceCurrency_.isEmpty(), bip70PaymentInfo.invoiceCurrency_);
                this.invoiceAmount_ = visitor.visitString(!this.invoiceAmount_.isEmpty(), this.invoiceAmount_, !bip70PaymentInfo.invoiceAmount_.isEmpty(), bip70PaymentInfo.invoiceAmount_);
                this.paymentCurrency_ = visitor.visitString(!this.paymentCurrency_.isEmpty(), this.paymentCurrency_, !bip70PaymentInfo.paymentCurrency_.isEmpty(), bip70PaymentInfo.paymentCurrency_);
                this.paymentAmount_ = visitor.visitString(!this.paymentAmount_.isEmpty(), this.paymentAmount_, !bip70PaymentInfo.paymentAmount_.isEmpty(), bip70PaymentInfo.paymentAmount_);
                this.conversionRate_ = visitor.visitString(!this.conversionRate_.isEmpty(), this.conversionRate_, !bip70PaymentInfo.conversionRate_.isEmpty(), bip70PaymentInfo.conversionRate_);
                this.conversionAssets_ = visitor.visitString(!this.conversionAssets_.isEmpty(), this.conversionAssets_, !bip70PaymentInfo.conversionAssets_.isEmpty(), bip70PaymentInfo.conversionAssets_);
                this.itemDesc_ = visitor.visitString(!this.itemDesc_.isEmpty(), this.itemDesc_, !bip70PaymentInfo.itemDesc_.isEmpty(), bip70PaymentInfo.itemDesc_);
                this.email_ = visitor.visitString(!this.email_.isEmpty(), this.email_, !bip70PaymentInfo.email_.isEmpty(), bip70PaymentInfo.email_);
                this.merchantWebsite_ = visitor.visitString(!this.merchantWebsite_.isEmpty(), this.merchantWebsite_, !bip70PaymentInfo.merchantWebsite_.isEmpty(), bip70PaymentInfo.merchantWebsite_);
                this.phone_ = visitor.visitString(!this.phone_.isEmpty(), this.phone_, !bip70PaymentInfo.phone_.isEmpty(), bip70PaymentInfo.phone_);
                this.createTime_ = visitor.visitLong(this.createTime_ != 0, this.createTime_, bip70PaymentInfo.createTime_ != 0, bip70PaymentInfo.createTime_);
                this.expiryTime_ = visitor.visitLong(this.expiryTime_ != 0, this.expiryTime_, bip70PaymentInfo.expiryTime_ != 0, bip70PaymentInfo.expiryTime_);
                this.outputs_ = visitor.visitList(this.outputs_, bip70PaymentInfo.outputs_);
                if (visitor == MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= bip70PaymentInfo.bitField0_;
                }
                return this;
            case MERGE_FROM_STREAM:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        switch (readTag) {
                            case 0:
                                z = true;
                                break;
                            case 10:
                                this.merchantProcessor_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 18:
                                this.merchantName_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 24:
                                this.verification_ = codedInputStream.readInt32();
                                break;
                            case 34:
                                this.invoiceCurrency_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 42:
                                this.invoiceAmount_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 50:
                                this.paymentCurrency_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 58:
                                this.paymentAmount_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 66:
                                this.conversionRate_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 74:
                                this.conversionAssets_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 82:
                                this.itemDesc_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 90:
                                this.email_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 98:
                                this.merchantWebsite_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 106:
                                this.phone_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 112:
                                this.createTime_ = codedInputStream.readInt64();
                                break;
                            case 120:
                                this.expiryTime_ = codedInputStream.readInt64();
                                break;
                            case ScriptOpCodes.OP_SIZE /*130*/:
                                if (!this.outputs_.isModifiable()) {
                                    this.outputs_ = GeneratedMessageLite.mutableCopy(this.outputs_);
                                }
                                this.outputs_.add(codedInputStream.readMessage(Bip70Output.parser(), extensionRegistryLite));
                                break;
                            default:
                                if (codedInputStream.skipField(readTag)) {
                                    break;
                                }
                                z = true;
                                break;
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
                    synchronized (Bip70PaymentInfo.class) {
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

    public static Bip70PaymentInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Bip70PaymentInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
