package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Detector.Processor;
import java.util.HashSet;

public class MultiProcessor<T> implements Processor<T> {
    /* access modifiers changed from: private */
    public int zzal;
    /* access modifiers changed from: private */
    public Factory<T> zzaz;
    private SparseArray<zza> zzba;

    public static class Builder<T> {
        private MultiProcessor<T> zzbb = new MultiProcessor<>();

        public Builder(Factory<T> factory) {
            if (factory != null) {
                this.zzbb.zzaz = factory;
                return;
            }
            throw new IllegalArgumentException("No factory supplied.");
        }

        public Builder<T> setMaxGapFrames(int i) {
            if (i >= 0) {
                this.zzbb.zzal = i;
                return this;
            }
            StringBuilder sb = new StringBuilder(28);
            sb.append("Invalid max gap: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }

        public MultiProcessor<T> build() {
            return this.zzbb;
        }
    }

    public interface Factory<T> {
        Tracker<T> create(T t);
    }

    class zza {
        /* access modifiers changed from: private */
        public Tracker<T> zzak;
        /* access modifiers changed from: private */
        public int zzao;

        private zza(MultiProcessor multiProcessor) {
            this.zzao = 0;
        }
    }

    public void release() {
        for (int i = 0; i < this.zzba.size(); i++) {
            ((zza) this.zzba.valueAt(i)).zzak.onDone();
        }
        this.zzba.clear();
    }

    public void receiveDetections(Detections<T> detections) {
        SparseArray detectedItems = detections.getDetectedItems();
        for (int i = 0; i < detectedItems.size(); i++) {
            int keyAt = detectedItems.keyAt(i);
            Object valueAt = detectedItems.valueAt(i);
            if (this.zzba.get(keyAt) == null) {
                zza zza2 = new zza();
                zza2.zzak = this.zzaz.create(valueAt);
                zza2.zzak.onNewItem(keyAt, valueAt);
                this.zzba.append(keyAt, zza2);
            }
        }
        SparseArray detectedItems2 = detections.getDetectedItems();
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i2 = 0; i2 < this.zzba.size(); i2++) {
            int keyAt2 = this.zzba.keyAt(i2);
            if (detectedItems2.get(keyAt2) == null) {
                zza zza3 = (zza) this.zzba.valueAt(i2);
                zza3.zzao = zza3.zzao + 1;
                if (zza3.zzao >= this.zzal) {
                    zza3.zzak.onDone();
                    hashSet.add(Integer.valueOf(keyAt2));
                } else {
                    zza3.zzak.onMissing(detections);
                }
            }
        }
        for (Integer intValue : hashSet) {
            this.zzba.delete(intValue.intValue());
        }
        SparseArray detectedItems3 = detections.getDetectedItems();
        for (int i3 = 0; i3 < detectedItems3.size(); i3++) {
            int keyAt3 = detectedItems3.keyAt(i3);
            Object valueAt2 = detectedItems3.valueAt(i3);
            zza zza4 = (zza) this.zzba.get(keyAt3);
            zza4.zzao = 0;
            zza4.zzak.onUpdate(detections, valueAt2);
        }
    }

    private MultiProcessor() {
        this.zzba = new SparseArray<>();
        this.zzal = 3;
    }
}
