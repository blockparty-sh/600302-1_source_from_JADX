package com.bitcoin.bitcoink;

import com.htc.htcwalletsdk.Utils.JsonParser.JsonDataKey_signMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\b\b\u0018\u0000 \u00152\u00020\u0001:\u0002\u0015\u0016B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000J\u0010\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\b\u0010\u0014\u001a\u00020\u000bH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/DerivationPath;", "", "path", "", "Lcom/bitcoin/bitcoink/DerivationPath$Path;", "(Ljava/util/List;)V", "getPath", "()Ljava/util/List;", "append", "extraPath", "asString", "", "mPrefix", "", "component1", "copy", "equals", "other", "hashCode", "", "toString", "Companion", "Path", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: DerivationPath.kt */
public final class DerivationPath {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<Path> path;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/DerivationPath$Companion;", "", "()V", "addressPath", "Lcom/bitcoin/bitcoink/DerivationPath;", "x", "", "y", "parse", "path", "", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: DerivationPath.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final DerivationPath parse(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, JsonDataKey_signMessage.path);
            List split$default = StringsKt.split$default((CharSequence) str, new String[]{"m/"}, false, 0, 6, (Object) null);
            Iterable<String> split$default2 = StringsKt.split$default((CharSequence) (String) (1 <= CollectionsKt.getLastIndex(split$default) ? split$default.get(1) : (String) split$default.get(0)), new String[]{"/"}, false, 0, 6, (Object) null);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default2, 10));
            for (String split$default3 : split$default2) {
                List split$default4 = StringsKt.split$default((CharSequence) split$default3, new String[]{"'"}, false, 0, 6, (Object) null);
                if (split$default4.size() <= 2) {
                    arrayList.add(new Path(Integer.parseInt((String) split$default4.get(0)), split$default4.size() == 2));
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("path=");
                    sb.append(str);
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            return new DerivationPath((List) arrayList);
        }

        @NotNull
        public final DerivationPath addressPath(int i, int i2) {
            return new DerivationPath(CollectionsKt.listOf(new Path(i, false), new Path(i2, false)));
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/DerivationPath$Path;", "", "path", "", "hardened", "", "(IZ)V", "getHardened", "()Z", "getPath", "()I", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: DerivationPath.kt */
    public static final class Path {
        private final boolean hardened;
        private final int path;

        @NotNull
        public static /* synthetic */ Path copy$default(Path path2, int i, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = path2.path;
            }
            if ((i2 & 2) != 0) {
                z = path2.hardened;
            }
            return path2.copy(i, z);
        }

        public final int component1() {
            return this.path;
        }

        public final boolean component2() {
            return this.hardened;
        }

        @NotNull
        public final Path copy(int i, boolean z) {
            return new Path(i, z);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof Path) {
                    Path path2 = (Path) obj;
                    if (this.path == path2.path) {
                        if (this.hardened == path2.hardened) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = this.path * 31;
            boolean z = this.hardened;
            if (z) {
                z = true;
            }
            return i + (z ? 1 : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Path(path=");
            sb.append(this.path);
            sb.append(", hardened=");
            sb.append(this.hardened);
            sb.append(")");
            return sb.toString();
        }

        public Path(int i, boolean z) {
            this.path = i;
            this.hardened = z;
        }

        public final boolean getHardened() {
            return this.hardened;
        }

        public final int getPath() {
            return this.path;
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<com.bitcoin.bitcoink.DerivationPath$Path>, for r1v0, types: [java.util.List] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.bitcoin.bitcoink.DerivationPath copy$default(com.bitcoin.bitcoink.DerivationPath r0, java.util.List<com.bitcoin.bitcoink.DerivationPath.Path> r1, int r2, java.lang.Object r3) {
        /*
            r2 = r2 & 1
            if (r2 == 0) goto L_0x0006
            java.util.List<com.bitcoin.bitcoink.DerivationPath$Path> r1 = r0.path
        L_0x0006:
            com.bitcoin.bitcoink.DerivationPath r0 = r0.copy(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.bitcoink.DerivationPath.copy$default(com.bitcoin.bitcoink.DerivationPath, java.util.List, int, java.lang.Object):com.bitcoin.bitcoink.DerivationPath");
    }

    @NotNull
    public final List<Path> component1() {
        return this.path;
    }

    @NotNull
    public final DerivationPath copy(@NotNull List<Path> list) {
        Intrinsics.checkParameterIsNotNull(list, JsonDataKey_signMessage.path);
        return new DerivationPath(list);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.path, (java.lang.Object) ((com.bitcoin.bitcoink.DerivationPath) r2).path) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.bitcoin.bitcoink.DerivationPath
            if (r0 == 0) goto L_0x0013
            com.bitcoin.bitcoink.DerivationPath r2 = (com.bitcoin.bitcoink.DerivationPath) r2
            java.util.List<com.bitcoin.bitcoink.DerivationPath$Path> r0 = r1.path
            java.util.List<com.bitcoin.bitcoink.DerivationPath$Path> r2 = r2.path
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.bitcoink.DerivationPath.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        List<Path> list = this.path;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public DerivationPath(@NotNull List<Path> list) {
        Intrinsics.checkParameterIsNotNull(list, JsonDataKey_signMessage.path);
        this.path = list;
    }

    @NotNull
    public final List<Path> getPath() {
        return this.path;
    }

    @NotNull
    public final DerivationPath append(@NotNull DerivationPath derivationPath) {
        Intrinsics.checkParameterIsNotNull(derivationPath, "extraPath");
        List arrayList = new ArrayList();
        arrayList.addAll(this.path);
        arrayList.addAll(derivationPath.path);
        return new DerivationPath(CollectionsKt.toList(arrayList));
    }

    @NotNull
    public static /* synthetic */ String asString$default(DerivationPath derivationPath, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return derivationPath.asString(z);
    }

    @NotNull
    public final String asString(boolean z) {
        String str = "";
        String str2 = z ? "m/" : str;
        Iterable<Path> iterable = this.path;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Path path2 : iterable) {
            String str3 = path2.getHardened() ? "'" : str;
            StringBuilder sb = new StringBuilder();
            sb.append(path2.getPath());
            sb.append(str3);
            arrayList.add(sb.toString());
        }
        return CollectionsKt.joinToString$default((List) arrayList, "/", str2, null, 0, null, null, 60, null);
    }

    @NotNull
    public String toString() {
        return asString$default(this, false, 1, null);
    }
}
