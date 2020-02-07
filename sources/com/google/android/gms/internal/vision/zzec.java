package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzec;
import com.google.android.gms.internal.vision.zzed;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class zzec<MessageType extends zzec<MessageType, BuilderType>, BuilderType extends zzed<MessageType, BuilderType>> implements zzhf {
    private static boolean zzrj = false;
    protected int zzri = 0;

    public final zzeo zzce() {
        try {
            zzev zzaj = zzeo.zzaj(zzeq());
            zzb(zzaj.zzdp());
            return zzaj.zzdo();
        } catch (IOException e) {
            String str = "ByteString";
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + str.length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append(str);
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzeq()];
            zzfe zzg = zzfe.zzg(bArr);
            zzb(zzg);
            zzg.zzea();
            return bArr;
        } catch (IOException e) {
            String str = "byte array";
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + str.length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append(str);
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: 0000 */
    public int zzcf() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: 0000 */
    public void zzy(int i) {
        throw new UnsupportedOperationException();
    }

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzga.checkNotNull(iterable);
        String str = " is null.";
        String str2 = "Element at index ";
        if (iterable instanceof zzgo) {
            List zzft = ((zzgo) iterable).zzft();
            zzgo zzgo = (zzgo) list;
            int size = list.size();
            for (Object next : zzft) {
                if (next == null) {
                    int size2 = zzgo.size() - size;
                    StringBuilder sb = new StringBuilder(37);
                    sb.append(str2);
                    sb.append(size2);
                    sb.append(str);
                    String sb2 = sb.toString();
                    for (int size3 = zzgo.size() - 1; size3 >= size; size3--) {
                        zzgo.remove(size3);
                    }
                    throw new NullPointerException(sb2);
                } else if (next instanceof zzeo) {
                    zzgo.zzc((zzeo) next);
                } else {
                    zzgo.add((String) next);
                }
            }
        } else if (iterable instanceof zzhr) {
            list.addAll((Collection) iterable);
        } else {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
            }
            int size4 = list.size();
            for (Object next2 : iterable) {
                if (next2 == null) {
                    int size5 = list.size() - size4;
                    StringBuilder sb3 = new StringBuilder(37);
                    sb3.append(str2);
                    sb3.append(size5);
                    sb3.append(str);
                    String sb4 = sb3.toString();
                    for (int size6 = list.size() - 1; size6 >= size4; size6--) {
                        list.remove(size6);
                    }
                    throw new NullPointerException(sb4);
                }
                list.add(next2);
            }
        }
    }
}
