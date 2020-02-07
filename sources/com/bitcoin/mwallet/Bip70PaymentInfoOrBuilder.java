package com.bitcoin.mwallet;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLiteOrBuilder;
import java.util.List;

public interface Bip70PaymentInfoOrBuilder extends MessageLiteOrBuilder {
    String getConversionAssets();

    ByteString getConversionAssetsBytes();

    String getConversionRate();

    ByteString getConversionRateBytes();

    long getCreateTime();

    String getEmail();

    ByteString getEmailBytes();

    long getExpiryTime();

    String getInvoiceAmount();

    ByteString getInvoiceAmountBytes();

    String getInvoiceCurrency();

    ByteString getInvoiceCurrencyBytes();

    String getItemDesc();

    ByteString getItemDescBytes();

    String getMerchantName();

    ByteString getMerchantNameBytes();

    String getMerchantProcessor();

    ByteString getMerchantProcessorBytes();

    String getMerchantWebsite();

    ByteString getMerchantWebsiteBytes();

    Bip70Output getOutputs(int i);

    int getOutputsCount();

    List<Bip70Output> getOutputsList();

    String getPaymentAmount();

    ByteString getPaymentAmountBytes();

    String getPaymentCurrency();

    ByteString getPaymentCurrencyBytes();

    String getPhone();

    ByteString getPhoneBytes();

    int getVerification();
}
