package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.leanplum.core.BuildConfig;
import java.util.Map;

public final class UPCAWriter implements Writer {
    private final EAN13Writer subWriter = new EAN13Writer();

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return encode(str, barcodeFormat, i, i2, null);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.UPC_A) {
            EAN13Writer eAN13Writer = this.subWriter;
            StringBuilder sb = new StringBuilder(BuildConfig.BUILD_NUMBER);
            sb.append(str);
            return eAN13Writer.encode(sb.toString(), BarcodeFormat.EAN_13, i, i2, map);
        }
        StringBuilder sb2 = new StringBuilder("Can only encode UPC-A, but got ");
        sb2.append(barcodeFormat);
        throw new IllegalArgumentException(sb2.toString());
    }
}
