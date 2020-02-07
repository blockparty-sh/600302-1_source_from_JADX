package com.journeyapps.barcodescanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import com.google.zxing.PlanarYUVLuminanceSource;
import java.io.ByteArrayOutputStream;

public class SourceData {
    private Rect cropRect;
    private byte[] data;
    private int dataHeight;
    private int dataWidth;
    private int imageFormat;
    private int rotation;

    public SourceData(byte[] bArr, int i, int i2, int i3, int i4) {
        this.data = bArr;
        this.dataWidth = i;
        this.dataHeight = i2;
        this.rotation = i4;
        this.imageFormat = i3;
        if (i * i2 > bArr.length) {
            StringBuilder sb = new StringBuilder();
            sb.append("Image data does not match the resolution. ");
            sb.append(i);
            sb.append("x");
            sb.append(i2);
            sb.append(" > ");
            sb.append(bArr.length);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public Rect getCropRect() {
        return this.cropRect;
    }

    public void setCropRect(Rect rect) {
        this.cropRect = rect;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getDataWidth() {
        return this.dataWidth;
    }

    public int getDataHeight() {
        return this.dataHeight;
    }

    public boolean isRotated() {
        return this.rotation % 180 != 0;
    }

    public int getImageFormat() {
        return this.imageFormat;
    }

    public PlanarYUVLuminanceSource createSource() {
        byte[] rotateCameraPreview = rotateCameraPreview(this.rotation, this.data, this.dataWidth, this.dataHeight);
        if (isRotated()) {
            PlanarYUVLuminanceSource planarYUVLuminanceSource = new PlanarYUVLuminanceSource(rotateCameraPreview, this.dataHeight, this.dataWidth, this.cropRect.left, this.cropRect.top, this.cropRect.width(), this.cropRect.height(), false);
            return planarYUVLuminanceSource;
        }
        PlanarYUVLuminanceSource planarYUVLuminanceSource2 = new PlanarYUVLuminanceSource(rotateCameraPreview, this.dataWidth, this.dataHeight, this.cropRect.left, this.cropRect.top, this.cropRect.width(), this.cropRect.height(), false);
        return planarYUVLuminanceSource2;
    }

    public Bitmap getBitmap() {
        return getBitmap(1);
    }

    public Bitmap getBitmap(int i) {
        return getBitmap(this.cropRect, i);
    }

    private Bitmap getBitmap(Rect rect, int i) {
        if (isRotated()) {
            rect = new Rect(rect.top, rect.left, rect.bottom, rect.right);
        }
        YuvImage yuvImage = new YuvImage(this.data, this.imageFormat, this.dataWidth, this.dataHeight, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(rect, 90, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Options options = new Options();
        options.inSampleSize = i;
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);
        if (this.rotation == 0) {
            return decodeByteArray;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) this.rotation);
        return Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, false);
    }

    public static byte[] rotateCameraPreview(int i, byte[] bArr, int i2, int i3) {
        if (i == 0) {
            return bArr;
        }
        if (i == 90) {
            return rotateCW(bArr, i2, i3);
        }
        if (i != 180) {
            return i != 270 ? bArr : rotateCCW(bArr, i2, i3);
        }
        return rotate180(bArr, i2, i3);
    }

    public static byte[] rotateCW(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[(i * i2)];
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            for (int i5 = i2 - 1; i5 >= 0; i5--) {
                bArr2[i3] = bArr[(i5 * i) + i4];
                i3++;
            }
        }
        return bArr2;
    }

    public static byte[] rotate180(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[i3];
        int i4 = i3 - 1;
        for (int i5 = 0; i5 < i3; i5++) {
            bArr2[i4] = bArr[i5];
            i4--;
        }
        return bArr2;
    }

    public static byte[] rotateCCW(byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        byte[] bArr2 = new byte[i3];
        int i4 = i3 - 1;
        for (int i5 = 0; i5 < i; i5++) {
            for (int i6 = i2 - 1; i6 >= 0; i6--) {
                bArr2[i4] = bArr[(i6 * i) + i5];
                i4--;
            }
        }
        return bArr2;
    }
}
