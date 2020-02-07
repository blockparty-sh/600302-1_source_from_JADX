package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import org.apache.commons.collections4.BoundedMap;

public class LRUMap<K, V> extends AbstractLinkedMap<K, V> implements BoundedMap<K, V>, Serializable, Cloneable {
    protected static final int DEFAULT_MAX_SIZE = 100;
    private static final long serialVersionUID = -612114643488955218L;
    private transient int maxSize;
    private boolean scanUntilRemovable;

    /* access modifiers changed from: protected */
    public boolean removeLRU(LinkEntry<K, V> linkEntry) {
        return true;
    }

    public LRUMap() {
        this(100, 0.75f, false);
    }

    public LRUMap(int i) {
        this(i, 0.75f);
    }

    public LRUMap(int i, int i2) {
        this(i, i2, 0.75f);
    }

    public LRUMap(int i, boolean z) {
        this(i, 0.75f, z);
    }

    public LRUMap(int i, float f) {
        this(i, f, false);
    }

    public LRUMap(int i, int i2, float f) {
        this(i, i2, f, false);
    }

    public LRUMap(int i, float f, boolean z) {
        this(i, i, f, z);
    }

    public LRUMap(int i, int i2, float f, boolean z) {
        super(i2, f);
        if (i < 1) {
            throw new IllegalArgumentException("LRUMap max size must be greater than 0");
        } else if (i2 <= i) {
            this.maxSize = i;
            this.scanUntilRemovable = z;
        } else {
            throw new IllegalArgumentException("LRUMap initial size must not be greather than max size");
        }
    }

    public LRUMap(Map<? extends K, ? extends V> map) {
        this(map, false);
    }

    public LRUMap(Map<? extends K, ? extends V> map, boolean z) {
        this(map.size(), 0.75f, z);
        putAll(map);
    }

    public V get(Object obj) {
        return get(obj, true);
    }

    public V get(Object obj, boolean z) {
        LinkEntry entry = getEntry(obj);
        if (entry == null) {
            return null;
        }
        if (z) {
            moveToMRU(entry);
        }
        return entry.getValue();
    }

    /* access modifiers changed from: protected */
    public void moveToMRU(LinkEntry<K, V> linkEntry) {
        if (linkEntry.after != this.header) {
            this.modCount++;
            if (linkEntry.before != null) {
                linkEntry.before.after = linkEntry.after;
                linkEntry.after.before = linkEntry.before;
                linkEntry.after = this.header;
                linkEntry.before = this.header.before;
                this.header.before.after = linkEntry;
                this.header.before = linkEntry;
                return;
            }
            throw new IllegalStateException("Entry.before is null. Please check that your keys are immutable, and that you have used synchronization properly. If so, then please report this to dev@commons.apache.org as a bug.");
        } else if (linkEntry == this.header) {
            throw new IllegalStateException("Can't move header to MRU (please report this to dev@commons.apache.org)");
        }
    }

    /* access modifiers changed from: protected */
    public void updateEntry(HashEntry<K, V> hashEntry, V v) {
        moveToMRU((LinkEntry) hashEntry);
        hashEntry.setValue(v);
    }

    /* access modifiers changed from: protected */
    public void addMapping(int i, int i2, K k, V v) {
        K k2 = k;
        V v2 = v;
        if (isFull()) {
            LinkEntry<K, V> linkEntry = this.header.after;
            boolean z = false;
            String str = " Please check that your keys are immutable, and that you have used synchronization properly. If so, then please report this to dev@commons.apache.org as a bug.";
            String str2 = " maxSize=";
            String str3 = " size=";
            String str4 = " value=";
            String str5 = " key=";
            String str6 = " header.before";
            if (this.scanUntilRemovable) {
                while (true) {
                    if (linkEntry == this.header || linkEntry == null) {
                        break;
                    } else if (removeLRU(linkEntry)) {
                        z = true;
                        break;
                    } else {
                        linkEntry = linkEntry.after;
                    }
                }
                if (linkEntry == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Entry.after=null, header.after");
                    sb.append(this.header.after);
                    sb.append(str6);
                    sb.append(this.header.before);
                    sb.append(str5);
                    sb.append(k);
                    sb.append(str4);
                    sb.append(v2);
                    sb.append(str3);
                    sb.append(this.size);
                    sb.append(str2);
                    sb.append(this.maxSize);
                    sb.append(str);
                    throw new IllegalStateException(sb.toString());
                }
            } else {
                z = removeLRU(linkEntry);
            }
            LinkEntry<K, V> linkEntry2 = linkEntry;
            if (!z) {
                super.addMapping(i, i2, k, v);
            } else if (linkEntry2 != null) {
                reuseMapping(linkEntry2, i, i2, k, v);
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("reuse=null, header.after=");
                sb2.append(this.header.after);
                sb2.append(str6);
                sb2.append(this.header.before);
                sb2.append(str5);
                sb2.append(k);
                sb2.append(str4);
                sb2.append(v2);
                sb2.append(str3);
                sb2.append(this.size);
                sb2.append(str2);
                sb2.append(this.maxSize);
                sb2.append(str);
                throw new IllegalStateException(sb2.toString());
            }
        } else {
            super.addMapping(i, i2, k, v);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034 A[Catch:{ NullPointerException -> 0x0076 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025 A[Catch:{ NullPointerException -> 0x0076 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void reuseMapping(org.apache.commons.collections4.map.AbstractLinkedMap.LinkEntry<K, V> r11, int r12, int r13, K r14, V r15) {
        /*
            r10 = this;
            java.lang.String r0 = " Please check that your keys are immutable, and that you have used synchronization properly. If so, then please report this to dev@commons.apache.org as a bug."
            java.lang.String r1 = " maxSize="
            java.lang.String r2 = " size="
            java.lang.String r3 = " value="
            java.lang.String r4 = " key="
            r5 = 1
            int r6 = r11.hashCode     // Catch:{ NullPointerException -> 0x0076 }
            org.apache.commons.collections4.map.AbstractHashedMap$HashEntry[] r7 = r10.data     // Catch:{ NullPointerException -> 0x0076 }
            int r7 = r7.length     // Catch:{ NullPointerException -> 0x0076 }
            int r6 = r10.hashIndex(r6, r7)     // Catch:{ NullPointerException -> 0x0076 }
            org.apache.commons.collections4.map.AbstractHashedMap$HashEntry[] r7 = r10.data     // Catch:{ NullPointerException -> 0x0076 }
            r7 = r7[r6]     // Catch:{ NullPointerException -> 0x0076 }
            r8 = 0
        L_0x0019:
            r9 = r8
            r8 = r7
            r7 = r9
            if (r8 == r11) goto L_0x0023
            if (r8 == 0) goto L_0x0023
            org.apache.commons.collections4.map.AbstractHashedMap$HashEntry<K, V> r7 = r8.next     // Catch:{ NullPointerException -> 0x0076 }
            goto L_0x0019
        L_0x0023:
            if (r8 == 0) goto L_0x0034
            int r8 = r10.modCount     // Catch:{ NullPointerException -> 0x0076 }
            int r8 = r8 + r5
            r10.modCount = r8     // Catch:{ NullPointerException -> 0x0076 }
            r10.removeEntry(r11, r6, r7)     // Catch:{ NullPointerException -> 0x0076 }
            r10.reuseEntry(r11, r12, r13, r14, r15)     // Catch:{ NullPointerException -> 0x0076 }
            r10.addEntry(r11, r12)     // Catch:{ NullPointerException -> 0x0076 }
            return
        L_0x0034:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch:{ NullPointerException -> 0x0076 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x0076 }
            r13.<init>()     // Catch:{ NullPointerException -> 0x0076 }
            java.lang.String r8 = "Entry.next=null, data[removeIndex]="
            r13.append(r8)     // Catch:{ NullPointerException -> 0x0076 }
            org.apache.commons.collections4.map.AbstractHashedMap$HashEntry[] r8 = r10.data     // Catch:{ NullPointerException -> 0x0076 }
            r6 = r8[r6]     // Catch:{ NullPointerException -> 0x0076 }
            r13.append(r6)     // Catch:{ NullPointerException -> 0x0076 }
            java.lang.String r6 = " previous="
            r13.append(r6)     // Catch:{ NullPointerException -> 0x0076 }
            r13.append(r7)     // Catch:{ NullPointerException -> 0x0076 }
            r13.append(r4)     // Catch:{ NullPointerException -> 0x0076 }
            r13.append(r14)     // Catch:{ NullPointerException -> 0x0076 }
            r13.append(r3)     // Catch:{ NullPointerException -> 0x0076 }
            r13.append(r15)     // Catch:{ NullPointerException -> 0x0076 }
            r13.append(r2)     // Catch:{ NullPointerException -> 0x0076 }
            int r6 = r10.size     // Catch:{ NullPointerException -> 0x0076 }
            r13.append(r6)     // Catch:{ NullPointerException -> 0x0076 }
            r13.append(r1)     // Catch:{ NullPointerException -> 0x0076 }
            int r6 = r10.maxSize     // Catch:{ NullPointerException -> 0x0076 }
            r13.append(r6)     // Catch:{ NullPointerException -> 0x0076 }
            r13.append(r0)     // Catch:{ NullPointerException -> 0x0076 }
            java.lang.String r13 = r13.toString()     // Catch:{ NullPointerException -> 0x0076 }
            r12.<init>(r13)     // Catch:{ NullPointerException -> 0x0076 }
            throw r12     // Catch:{ NullPointerException -> 0x0076 }
        L_0x0076:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r6 = "NPE, entry="
            r13.append(r6)
            r13.append(r11)
            java.lang.String r6 = " entryIsHeader="
            r13.append(r6)
            org.apache.commons.collections4.map.AbstractLinkedMap$LinkEntry r6 = r10.header
            if (r11 != r6) goto L_0x0090
            goto L_0x0091
        L_0x0090:
            r5 = 0
        L_0x0091:
            r13.append(r5)
            r13.append(r4)
            r13.append(r14)
            r13.append(r3)
            r13.append(r15)
            r13.append(r2)
            int r11 = r10.size
            r13.append(r11)
            r13.append(r1)
            int r11 = r10.maxSize
            r13.append(r11)
            r13.append(r0)
            java.lang.String r11 = r13.toString()
            r12.<init>(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.map.LRUMap.reuseMapping(org.apache.commons.collections4.map.AbstractLinkedMap$LinkEntry, int, int, java.lang.Object, java.lang.Object):void");
    }

    public boolean isFull() {
        return this.size >= this.maxSize;
    }

    public int maxSize() {
        return this.maxSize;
    }

    public boolean isScanUntilRemovable() {
        return this.scanUntilRemovable;
    }

    public LRUMap<K, V> clone() {
        return (LRUMap) super.clone();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        doWriteObject(objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        doReadObject(objectInputStream);
    }

    /* access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.maxSize);
        super.doWriteObject(objectOutputStream);
    }

    /* access modifiers changed from: protected */
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.maxSize = objectInputStream.readInt();
        super.doReadObject(objectInputStream);
    }
}
