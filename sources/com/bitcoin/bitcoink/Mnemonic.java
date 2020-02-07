package com.bitcoin.bitcoink;

import com.facebook.stetho.common.Utf8Charset;
import java.io.InputStream;
import java.security.SecureRandom;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.UnaryOperator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.bitcoinj.crypto.MnemonicCode;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010*\n\u0002\b\u0006\b\b\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0002\u0010\u0005J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001HÆ\u0003J\u0011\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0002H\u0003J\u0017\u0010\u0018\u001a\u00020\u00162\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001aH\u0001J\u0019\u0010\u001b\u001a\u00020\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\u0011\u0010\u001f\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u0011H\u0003J\t\u0010!\u001a\u00020\u0011HÖ\u0001J\u0011\u0010\"\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0002H\u0001J\t\u0010#\u001a\u00020\u0016H\u0001J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00020%H\u0003J\u0011\u0010&\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0002H\u0001J\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00020(H\u0001J\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00020(2\u0006\u0010 \u001a\u00020\u0011H\u0001J\u001f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u0011H\u0001J\t\u0010,\u001a\u00020\u0002HÖ\u0001R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00020\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u0011X\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006."}, mo37405d2 = {"Lcom/bitcoin/bitcoink/Mnemonic;", "", "", "mnemonic", "(Ljava/lang/String;)V", "(Ljava/util/List;)V", "masterKey", "Lcom/bitcoin/bitcoink/PrivateKey;", "getMasterKey", "()Lcom/bitcoin/bitcoink/PrivateKey;", "masterKey$delegate", "Lkotlin/Lazy;", "getMnemonic", "()Ljava/util/List;", "normalizedMnemonic", "kotlin.jvm.PlatformType", "size", "", "getSize", "()I", "component1", "contains", "", "element", "containsAll", "elements", "", "copy", "equals", "other", "", "get", "index", "hashCode", "indexOf", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "subList", "fromIndex", "toIndex", "toString", "Companion", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Mnemonic.kt */
public final class Mnemonic implements List<String>, KMappedMarker {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Mnemonic.class), "masterKey", "getMasterKey()Lcom/bitcoin/bitcoink/PrivateKey;"))};
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final Lazy mnemonic$delegate = LazyKt.lazy(Mnemonic$Companion$mnemonic$2.INSTANCE);
    /* access modifiers changed from: private */
    public static final Lazy random$delegate = LazyKt.lazy(Mnemonic$Companion$random$2.INSTANCE);
    @NotNull
    private final Lazy masterKey$delegate;
    @NotNull
    private final List<String> mnemonic;
    /* access modifiers changed from: private */
    public final List<String> normalizedMnemonic;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000fR\u001b\u0010\u0003\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/Mnemonic$Companion;", "", "()V", "mnemonic", "Lorg/bitcoinj/crypto/MnemonicCode;", "getMnemonic", "()Lorg/bitcoinj/crypto/MnemonicCode;", "mnemonic$delegate", "Lkotlin/Lazy;", "random", "Ljava/security/SecureRandom;", "getRandom", "()Ljava/security/SecureRandom;", "random$delegate", "generate", "Lcom/bitcoin/bitcoink/Mnemonic;", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Mnemonic.kt */
    public static final class Companion {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "mnemonic", "getMnemonic()Lorg/bitcoinj/crypto/MnemonicCode;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "random", "getRandom()Ljava/security/SecureRandom;"))};

        private final MnemonicCode getMnemonic() {
            Lazy access$getMnemonic$cp = Mnemonic.mnemonic$delegate;
            Companion companion = Mnemonic.Companion;
            KProperty kProperty = $$delegatedProperties[0];
            return (MnemonicCode) access$getMnemonic$cp.getValue();
        }

        private final SecureRandom getRandom() {
            Lazy access$getRandom$cp = Mnemonic.random$delegate;
            Companion companion = Mnemonic.Companion;
            KProperty kProperty = $$delegatedProperties[1];
            return (SecureRandom) access$getRandom$cp.getValue();
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Mnemonic generate() {
            Companion companion = this;
            List mnemonic = companion.getMnemonic().toMnemonic(companion.getRandom().generateSeed(16));
            Intrinsics.checkExpressionValueIsNotNull(mnemonic, "mnemonic.toMnemonic(\n   …Y_BITS / 8)\n            )");
            return new Mnemonic(mnemonic);
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<java.lang.String>, for r1v0, types: [java.util.List] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.bitcoin.bitcoink.Mnemonic copy$default(com.bitcoin.bitcoink.Mnemonic r0, java.util.List<java.lang.String> r1, int r2, java.lang.Object r3) {
        /*
            r2 = r2 & 1
            if (r2 == 0) goto L_0x0006
            java.util.List<java.lang.String> r1 = r0.mnemonic
        L_0x0006:
            com.bitcoin.bitcoink.Mnemonic r0 = r0.copy(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.bitcoink.Mnemonic.copy$default(com.bitcoin.bitcoink.Mnemonic, java.util.List, int, java.lang.Object):com.bitcoin.bitcoink.Mnemonic");
    }

    public /* synthetic */ void add(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void add(int i, String str) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean add(String str) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(int i, Collection<? extends String> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection<? extends String> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    public final List<String> component1() {
        return this.mnemonic;
    }

    public boolean contains(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "element");
        return this.mnemonic.contains(str);
    }

    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "elements");
        return this.mnemonic.containsAll(collection);
    }

    @NotNull
    public final Mnemonic copy(@NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "mnemonic");
        return new Mnemonic(list);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.mnemonic, (java.lang.Object) ((com.bitcoin.bitcoink.Mnemonic) r2).mnemonic) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.bitcoin.bitcoink.Mnemonic
            if (r0 == 0) goto L_0x0013
            com.bitcoin.bitcoink.Mnemonic r2 = (com.bitcoin.bitcoink.Mnemonic) r2
            java.util.List<java.lang.String> r0 = r1.mnemonic
            java.util.List<java.lang.String> r2 = r2.mnemonic
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 0
            return r2
        L_0x0015:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.bitcoink.Mnemonic.equals(java.lang.Object):boolean");
    }

    @NotNull
    public String get(int i) {
        Object obj = this.mnemonic.get(i);
        Intrinsics.checkExpressionValueIsNotNull(obj, "get(...)");
        return (String) obj;
    }

    @NotNull
    public final PrivateKey getMasterKey() {
        Lazy lazy = this.masterKey$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (PrivateKey) lazy.getValue();
    }

    public int getSize() {
        return this.mnemonic.size();
    }

    public int hashCode() {
        List<String> list = this.mnemonic;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public int indexOf(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "element");
        return this.mnemonic.indexOf(str);
    }

    public boolean isEmpty() {
        return this.mnemonic.isEmpty();
    }

    @NotNull
    public Iterator<String> iterator() {
        return this.mnemonic.iterator();
    }

    public int lastIndexOf(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "element");
        return this.mnemonic.lastIndexOf(str);
    }

    @NotNull
    public ListIterator<String> listIterator() {
        return this.mnemonic.listIterator();
    }

    @NotNull
    public ListIterator<String> listIterator(int i) {
        return this.mnemonic.listIterator(i);
    }

    public String remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void replaceAll(UnaryOperator<String> unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public /* synthetic */ Object set(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public String set(int i, String str) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void sort(Comparator<? super String> comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    public List<String> subList(int i, int i2) {
        return this.mnemonic.subList(i, i2);
    }

    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    public <T> T[] toArray(T[] tArr) {
        return CollectionToArray.toArray(this, tArr);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mnemonic(mnemonic=");
        sb.append(this.mnemonic);
        sb.append(")");
        return sb.toString();
    }

    public Mnemonic(@NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "mnemonic");
        this.mnemonic = list;
        Iterable<String> iterable = this.mnemonic;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String normalize : iterable) {
            arrayList.add(Normalizer.normalize(normalize, Form.NFD));
        }
        this.normalizedMnemonic = (List) arrayList;
        MnemonicCodeCustom.INSTANCE = new MnemonicCodeCustom((InputStream) WordList.Companion.correctList(this.normalizedMnemonic).getFirst(), null, Utf8Charset.NAME);
        try {
            MnemonicCodeCustom.INSTANCE.check(this.normalizedMnemonic);
            this.masterKey$delegate = LazyKt.lazy(new Mnemonic$masterKey$2(this));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof String) {
            return contains((String) obj);
        }
        return false;
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof String) {
            return indexOf((String) obj);
        }
        return -1;
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof String) {
            return lastIndexOf((String) obj);
        }
        return -1;
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    @NotNull
    public final List<String> getMnemonic() {
        return this.mnemonic;
    }

    public Mnemonic(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "mnemonic");
        this(StringsKt.split$default((CharSequence) StringsKt.replace$default(str, "　", " ", false, 4, (Object) null), new String[]{" "}, false, 0, 6, (Object) null));
    }
}
