package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class TextBlock implements Text {
    private Point[] cornerPoints;
    private zzae[] zzev;
    private List<Line> zzew;
    private String zzex;
    private Rect zzey;

    TextBlock(SparseArray<zzae> sparseArray) {
        this.zzev = new zzae[sparseArray.size()];
        int i = 0;
        while (true) {
            zzae[] zzaeArr = this.zzev;
            if (i < zzaeArr.length) {
                zzaeArr[i] = (zzae) sparseArray.valueAt(i);
                i++;
            } else {
                return;
            }
        }
    }

    public String getLanguage() {
        zzae[] zzaeArr;
        String str = this.zzex;
        if (str != null) {
            return str;
        }
        HashMap hashMap = new HashMap();
        for (zzae zzae : this.zzev) {
            hashMap.put(zzae.zzex, Integer.valueOf((hashMap.containsKey(zzae.zzex) ? ((Integer) hashMap.get(zzae.zzex)).intValue() : 0) + 1));
        }
        this.zzex = (String) ((Entry) Collections.max(hashMap.entrySet(), new zza(this))).getKey();
        String str2 = this.zzex;
        if (str2 == null || str2.isEmpty()) {
            this.zzex = "und";
        }
        return this.zzex;
    }

    public String getValue() {
        zzae[] zzaeArr = this.zzev;
        if (zzaeArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(zzaeArr[0].zzfg);
        for (int i = 1; i < this.zzev.length; i++) {
            sb.append("\n");
            sb.append(this.zzev[i].zzfg);
        }
        return sb.toString();
    }

    public Point[] getCornerPoints() {
        TextBlock textBlock;
        zzae[] zzaeArr;
        TextBlock textBlock2 = this;
        if (textBlock2.cornerPoints == null) {
            char c = 0;
            if (textBlock2.zzev.length == 0) {
                textBlock2.cornerPoints = new Point[0];
            } else {
                int i = Integer.MAX_VALUE;
                int i2 = 0;
                int i3 = Integer.MAX_VALUE;
                int i4 = Integer.MIN_VALUE;
                int i5 = Integer.MIN_VALUE;
                while (true) {
                    zzaeArr = textBlock2.zzev;
                    if (i2 >= zzaeArr.length) {
                        break;
                    }
                    zzy zzy = zzaeArr[i2].zzfd;
                    zzy zzy2 = textBlock2.zzev[c].zzfd;
                    int i6 = -zzy2.left;
                    int i7 = -zzy2.top;
                    double sin = Math.sin(Math.toRadians((double) zzy2.zzfb));
                    int i8 = i6;
                    double cos = Math.cos(Math.toRadians((double) zzy2.zzfb));
                    Point[] pointArr = new Point[4];
                    pointArr[c] = new Point(zzy.left, zzy.top);
                    pointArr[c].offset(i8, i7);
                    int i9 = i;
                    int i10 = (int) ((((double) pointArr[c].x) * cos) + (((double) pointArr[c].y) * sin));
                    int i11 = (int) ((((double) (-pointArr[0].x)) * sin) + (((double) pointArr[0].y) * cos));
                    pointArr[0].x = i10;
                    pointArr[0].y = i11;
                    pointArr[1] = new Point(zzy.width + i10, i11);
                    pointArr[2] = new Point(zzy.width + i10, zzy.height + i11);
                    pointArr[3] = new Point(i10, i11 + zzy.height);
                    i = i9;
                    for (int i12 = 0; i12 < 4; i12++) {
                        Point point = pointArr[i12];
                        i = Math.min(i, point.x);
                        i4 = Math.max(i4, point.x);
                        i3 = Math.min(i3, point.y);
                        i5 = Math.max(i5, point.y);
                    }
                    i2++;
                    c = 0;
                    textBlock2 = this;
                }
                int i13 = i;
                zzy zzy3 = zzaeArr[0].zzfd;
                int i14 = zzy3.left;
                int i15 = zzy3.top;
                double sin2 = Math.sin(Math.toRadians((double) zzy3.zzfb));
                double cos2 = Math.cos(Math.toRadians((double) zzy3.zzfb));
                int i16 = i13;
                Point[] pointArr2 = {new Point(i16, i3), new Point(i4, i3), new Point(i4, i5), new Point(i16, i5)};
                for (int i17 = 0; i17 < 4; i17++) {
                    int i18 = (int) ((((double) pointArr2[i17].x) * sin2) + (((double) pointArr2[i17].y) * cos2));
                    pointArr2[i17].x = (int) ((((double) pointArr2[i17].x) * cos2) - (((double) pointArr2[i17].y) * sin2));
                    pointArr2[i17].y = i18;
                    pointArr2[i17].offset(i14, i15);
                }
                textBlock = this;
                textBlock.cornerPoints = pointArr2;
                return textBlock.cornerPoints;
            }
        }
        textBlock = textBlock2;
        return textBlock.cornerPoints;
    }

    public List<? extends Text> getComponents() {
        zzae[] zzaeArr = this.zzev;
        if (zzaeArr.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzew == null) {
            this.zzew = new ArrayList(zzaeArr.length);
            for (zzae line : this.zzev) {
                this.zzew.add(new Line(line));
            }
        }
        return this.zzew;
    }

    public Rect getBoundingBox() {
        if (this.zzey == null) {
            this.zzey = zzc.zza((Text) this);
        }
        return this.zzey;
    }
}
