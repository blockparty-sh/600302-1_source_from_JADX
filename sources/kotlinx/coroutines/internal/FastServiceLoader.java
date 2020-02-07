package kotlinx.coroutines.internal;

import androidx.core.app.NotificationCompat;
import java.io.BufferedReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0005\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00060\u000bH\u0002¢\u0006\u0002\u0010\fJ/\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00060\u000e\"\u0004\b\u0000\u0010\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00060\u000b2\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\u000fJ/\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00060\u000e\"\u0004\b\u0000\u0010\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00060\u000b2\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\u0011J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J,\u0010\u0018\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0019*\u00020\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u0002H\u00190\u001cH\b¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001e"}, mo37405d2 = {"Lkotlinx/coroutines/internal/FastServiceLoader;", "", "()V", "PREFIX", "", "getProviderInstance", "S", "name", "loader", "Ljava/lang/ClassLoader;", "service", "Ljava/lang/Class;", "(Ljava/lang/String;Ljava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Object;", "load", "", "load$kotlinx_coroutines_core", "loadProviders", "loadProviders$kotlinx_coroutines_core", "parse", "url", "Ljava/net/URL;", "parseFile", "r", "Ljava/io/BufferedReader;", "use", "R", "Ljava/util/jar/JarFile;", "block", "Lkotlin/Function1;", "(Ljava/util/jar/JarFile;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: FastServiceLoader.kt */
public final class FastServiceLoader {
    public static final FastServiceLoader INSTANCE = new FastServiceLoader();
    private static final String PREFIX = "META-INF/services/";

    private FastServiceLoader() {
    }

    @NotNull
    public final <S> List<S> load$kotlinx_coroutines_core(@NotNull Class<S> cls, @NotNull ClassLoader classLoader) {
        Intrinsics.checkParameterIsNotNull(cls, NotificationCompat.CATEGORY_SERVICE);
        Intrinsics.checkParameterIsNotNull(classLoader, "loader");
        try {
            return loadProviders$kotlinx_coroutines_core(cls, classLoader);
        } catch (Throwable unused) {
            ServiceLoader load = ServiceLoader.load(cls, classLoader);
            Intrinsics.checkExpressionValueIsNotNull(load, "ServiceLoader.load(service, loader)");
            return CollectionsKt.toList(load);
        }
    }

    @NotNull
    public final <S> List<S> loadProviders$kotlinx_coroutines_core(@NotNull Class<S> cls, @NotNull ClassLoader classLoader) {
        Intrinsics.checkParameterIsNotNull(cls, NotificationCompat.CATEGORY_SERVICE);
        Intrinsics.checkParameterIsNotNull(classLoader, "loader");
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX);
        sb.append(cls.getName());
        Enumeration resources = classLoader.getResources(sb.toString());
        Intrinsics.checkExpressionValueIsNotNull(resources, "urls");
        ArrayList list = Collections.list(resources);
        Intrinsics.checkExpressionValueIsNotNull(list, "java.util.Collections.list(this)");
        Iterable<URL> iterable = list;
        Collection arrayList = new ArrayList();
        for (URL url : iterable) {
            FastServiceLoader fastServiceLoader = INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(url, "it");
            CollectionsKt.addAll(arrayList, (Iterable<? extends T>) fastServiceLoader.parse(url));
        }
        Set set = CollectionsKt.toSet((List) arrayList);
        if (!set.isEmpty()) {
            Iterable<String> iterable2 = set;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
            for (String providerInstance : iterable2) {
                arrayList2.add(INSTANCE.getProviderInstance(providerInstance, classLoader, cls));
            }
            return (List) arrayList2;
        }
        throw new IllegalArgumentException("No providers were loaded with FastServiceLoader".toString());
    }

    private final <S> S getProviderInstance(String str, ClassLoader classLoader, Class<S> cls) {
        Class cls2 = Class.forName(str, false, classLoader);
        if (cls.isAssignableFrom(cls2)) {
            return cls.cast(cls2.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected service of class ");
        sb.append(cls);
        sb.append(", but found ");
        sb.append(cls2);
        throw new IllegalArgumentException(sb.toString().toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0064, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006b, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x006c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006d, code lost:
        if (r7 == null) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006f, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0070, code lost:
        kotlin.ExceptionsKt.addSuppressed(r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0073, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<java.lang.String> parse(java.net.URL r7) {
        /*
            r6 = this;
            java.lang.String r0 = r7.toString()
            java.lang.String r1 = "url.toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            r1 = 0
            r2 = 2
            r3 = 0
            java.lang.String r4 = "jar"
            boolean r4 = kotlin.text.StringsKt.startsWith$default(r0, r4, r1, r2, r3)
            if (r4 == 0) goto L_0x0074
            java.lang.String r7 = "jar:file:"
            java.lang.String r7 = kotlin.text.StringsKt.substringAfter$default(r0, r7, r3, r2, r3)
            r4 = 33
            java.lang.String r7 = kotlin.text.StringsKt.substringBefore$default(r7, r4, r3, r2, r3)
            java.lang.String r4 = "!/"
            java.lang.String r0 = kotlin.text.StringsKt.substringAfter$default(r0, r4, r3, r2, r3)
            java.util.jar.JarFile r2 = new java.util.jar.JarFile
            r2.<init>(r7, r1)
            r7 = r3
            java.lang.Throwable r7 = (java.lang.Throwable) r7
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0066 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0066 }
            java.util.zip.ZipEntry r5 = new java.util.zip.ZipEntry     // Catch:{ Throwable -> 0x0066 }
            r5.<init>(r0)     // Catch:{ Throwable -> 0x0066 }
            java.io.InputStream r0 = r2.getInputStream(r5)     // Catch:{ Throwable -> 0x0066 }
            java.lang.String r5 = "UTF-8"
            r4.<init>(r0, r5)     // Catch:{ Throwable -> 0x0066 }
            java.io.Reader r4 = (java.io.Reader) r4     // Catch:{ Throwable -> 0x0066 }
            r1.<init>(r4)     // Catch:{ Throwable -> 0x0066 }
            java.io.Closeable r1 = (java.io.Closeable) r1     // Catch:{ Throwable -> 0x0066 }
            java.lang.Throwable r3 = (java.lang.Throwable) r3     // Catch:{ Throwable -> 0x0066 }
            r0 = r1
            java.io.BufferedReader r0 = (java.io.BufferedReader) r0     // Catch:{ Throwable -> 0x005d }
            kotlinx.coroutines.internal.FastServiceLoader r4 = INSTANCE     // Catch:{ Throwable -> 0x005d }
            java.util.List r0 = r4.parseFile(r0)     // Catch:{ Throwable -> 0x005d }
            kotlin.p016io.CloseableKt.closeFinally(r1, r3)     // Catch:{ Throwable -> 0x0066 }
            r2.close()     // Catch:{ Throwable -> 0x0059 }
            return r0
        L_0x0059:
            r7 = move-exception
            throw r7
        L_0x005b:
            r0 = move-exception
            goto L_0x0060
        L_0x005d:
            r0 = move-exception
            r3 = r0
            throw r3     // Catch:{ all -> 0x005b }
        L_0x0060:
            kotlin.p016io.CloseableKt.closeFinally(r1, r3)     // Catch:{ Throwable -> 0x0066 }
            throw r0     // Catch:{ Throwable -> 0x0066 }
        L_0x0064:
            r0 = move-exception
            goto L_0x0068
        L_0x0066:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0064 }
        L_0x0068:
            r2.close()     // Catch:{ Throwable -> 0x006c }
            throw r0
        L_0x006c:
            r0 = move-exception
            if (r7 != 0) goto L_0x0070
            throw r0
        L_0x0070:
            kotlin.ExceptionsKt.addSuppressed(r7, r0)
            throw r7
        L_0x0074:
            java.io.BufferedReader r0 = new java.io.BufferedReader
            java.io.InputStreamReader r1 = new java.io.InputStreamReader
            java.io.InputStream r7 = r7.openStream()
            r1.<init>(r7)
            java.io.Reader r1 = (java.io.Reader) r1
            r0.<init>(r1)
            java.io.Closeable r0 = (java.io.Closeable) r0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r7 = r0
            java.io.BufferedReader r7 = (java.io.BufferedReader) r7     // Catch:{ Throwable -> 0x0097 }
            kotlinx.coroutines.internal.FastServiceLoader r1 = INSTANCE     // Catch:{ Throwable -> 0x0097 }
            java.util.List r7 = r1.parseFile(r7)     // Catch:{ Throwable -> 0x0097 }
            kotlin.p016io.CloseableKt.closeFinally(r0, r3)
            return r7
        L_0x0095:
            r7 = move-exception
            goto L_0x009a
        L_0x0097:
            r7 = move-exception
            r3 = r7
            throw r3     // Catch:{ all -> 0x0095 }
        L_0x009a:
            kotlin.p016io.CloseableKt.closeFinally(r0, r3)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.FastServiceLoader.parse(java.net.URL):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        kotlin.jvm.internal.InlineMarker.finallyStart(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001e, code lost:
        kotlin.jvm.internal.InlineMarker.finallyEnd(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0021, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0022, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0023, code lost:
        if (r0 == null) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0025, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0026, code lost:
        kotlin.ExceptionsKt.addSuppressed(r0, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0029, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <R> R use(@org.jetbrains.annotations.NotNull java.util.jar.JarFile r3, kotlin.jvm.functions.Function1<? super java.util.jar.JarFile, ? extends R> r4) {
        /*
            r2 = this;
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r1 = 1
            java.lang.Object r4 = r4.invoke(r3)     // Catch:{ Throwable -> 0x0016 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r3.close()     // Catch:{ Throwable -> 0x0012 }
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            return r4
        L_0x0012:
            r3 = move-exception
            throw r3
        L_0x0014:
            r4 = move-exception
            goto L_0x0018
        L_0x0016:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0014 }
        L_0x0018:
            kotlin.jvm.internal.InlineMarker.finallyStart(r1)
            r3.close()     // Catch:{ Throwable -> 0x0022 }
            kotlin.jvm.internal.InlineMarker.finallyEnd(r1)
            throw r4
        L_0x0022:
            r3 = move-exception
            if (r0 != 0) goto L_0x0026
            throw r3
        L_0x0026:
            kotlin.ExceptionsKt.addSuppressed(r0, r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.FastServiceLoader.use(java.util.jar.JarFile, kotlin.jvm.functions.Function1):java.lang.Object");
    }

    private final List<String> parseFile(BufferedReader bufferedReader) {
        boolean z;
        Set linkedHashSet = new LinkedHashSet();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return CollectionsKt.toList(linkedHashSet);
            }
            String substringBefore$default = StringsKt.substringBefore$default(readLine, "#", (String) null, 2, (Object) null);
            if (substringBefore$default != null) {
                String obj = StringsKt.trim((CharSequence) substringBefore$default).toString();
                CharSequence charSequence = obj;
                boolean z2 = false;
                int i = 0;
                while (true) {
                    if (i >= charSequence.length()) {
                        z = true;
                        break;
                    }
                    char charAt = charSequence.charAt(i);
                    if (!(charAt == '.' || Character.isJavaIdentifierPart(charAt))) {
                        z = false;
                        break;
                    }
                    i++;
                }
                if (z) {
                    if (charSequence.length() > 0) {
                        z2 = true;
                    }
                    if (z2) {
                        linkedHashSet.add(obj);
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Illegal service provider class name: ");
                    sb.append(obj);
                    throw new IllegalArgumentException(sb.toString().toString());
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }
    }
}
