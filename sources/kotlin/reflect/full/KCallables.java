package kotlin.reflect.full;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KCallable;
import kotlin.reflect.KParameter;
import kotlin.reflect.KParameter.Kind;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u001a\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0007\"$\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"$\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00028FX\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006\"(\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b*\u0006\u0012\u0002\b\u00030\u00028FX\u0004¢\u0006\f\u0012\u0004\b\f\u0010\u0004\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"}, mo37405d2 = {"extensionReceiverParameter", "Lkotlin/reflect/KParameter;", "Lkotlin/reflect/KCallable;", "extensionReceiverParameter$annotations", "(Lkotlin/reflect/KCallable;)V", "getExtensionReceiverParameter", "(Lkotlin/reflect/KCallable;)Lkotlin/reflect/KParameter;", "instanceParameter", "instanceParameter$annotations", "getInstanceParameter", "valueParameters", "", "valueParameters$annotations", "getValueParameters", "(Lkotlin/reflect/KCallable;)Ljava/util/List;", "findParameterByName", "name", "", "kotlin-reflect-api"}, mo37406k = 2, mo37407mv = {1, 1, 11})
@JvmName(name = "KCallables")
/* compiled from: KCallables.kt */
public final class KCallables {
    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void extensionReceiverParameter$annotations(KCallable kCallable) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void instanceParameter$annotations(KCallable kCallable) {
    }

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void valueParameters$annotations(KCallable kCallable) {
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r4v1, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v2
      assigns: []
      uses: []
      mth insns count: 27
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.KParameter getInstanceParameter(@org.jetbrains.annotations.NotNull kotlin.reflect.KCallable<?> r8) {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.util.List r8 = r8.getParameters()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r8 = r8.iterator()
            r0 = 0
            r1 = 0
            r3 = r1
            r2 = 0
        L_0x0013:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L_0x0034
            java.lang.Object r4 = r8.next()
            r5 = r4
            kotlin.reflect.KParameter r5 = (kotlin.reflect.KParameter) r5
            kotlin.reflect.KParameter$Kind r5 = r5.getKind()
            kotlin.reflect.KParameter$Kind r6 = kotlin.reflect.KParameter.Kind.INSTANCE
            r7 = 1
            if (r5 != r6) goto L_0x002b
            r5 = 1
            goto L_0x002c
        L_0x002b:
            r5 = 0
        L_0x002c:
            if (r5 == 0) goto L_0x0013
            if (r2 == 0) goto L_0x0031
            goto L_0x0038
        L_0x0031:
            r3 = r4
            r2 = 1
            goto L_0x0013
        L_0x0034:
            if (r2 != 0) goto L_0x0037
            goto L_0x0038
        L_0x0037:
            r1 = r3
        L_0x0038:
            kotlin.reflect.KParameter r1 = (kotlin.reflect.KParameter) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.full.KCallables.getInstanceParameter(kotlin.reflect.KCallable):kotlin.reflect.KParameter");
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r4v1, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v2
      assigns: []
      uses: []
      mth insns count: 27
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.KParameter getExtensionReceiverParameter(@org.jetbrains.annotations.NotNull kotlin.reflect.KCallable<?> r8) {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.util.List r8 = r8.getParameters()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r8 = r8.iterator()
            r0 = 0
            r1 = 0
            r3 = r1
            r2 = 0
        L_0x0013:
            boolean r4 = r8.hasNext()
            if (r4 == 0) goto L_0x0034
            java.lang.Object r4 = r8.next()
            r5 = r4
            kotlin.reflect.KParameter r5 = (kotlin.reflect.KParameter) r5
            kotlin.reflect.KParameter$Kind r5 = r5.getKind()
            kotlin.reflect.KParameter$Kind r6 = kotlin.reflect.KParameter.Kind.EXTENSION_RECEIVER
            r7 = 1
            if (r5 != r6) goto L_0x002b
            r5 = 1
            goto L_0x002c
        L_0x002b:
            r5 = 0
        L_0x002c:
            if (r5 == 0) goto L_0x0013
            if (r2 == 0) goto L_0x0031
            goto L_0x0038
        L_0x0031:
            r3 = r4
            r2 = 1
            goto L_0x0013
        L_0x0034:
            if (r2 != 0) goto L_0x0037
            goto L_0x0038
        L_0x0037:
            r1 = r3
        L_0x0038:
            kotlin.reflect.KParameter r1 = (kotlin.reflect.KParameter) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.full.KCallables.getExtensionReceiverParameter(kotlin.reflect.KCallable):kotlin.reflect.KParameter");
    }

    @NotNull
    public static final List<KParameter> getValueParameters(@NotNull KCallable<?> kCallable) {
        Intrinsics.checkParameterIsNotNull(kCallable, "$receiver");
        Iterable parameters = kCallable.getParameters();
        Collection arrayList = new ArrayList();
        for (Object next : parameters) {
            if (((KParameter) next).getKind() == Kind.VALUE) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r3v1, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r4v0 */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v2
      assigns: []
      uses: []
      mth insns count: 26
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    @kotlin.SinceKotlin(version = "1.1")
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.KParameter findParameterByName(@org.jetbrains.annotations.NotNull kotlin.reflect.KCallable<?> r5, @org.jetbrains.annotations.NotNull java.lang.String r6) {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            java.util.List r5 = r5.getParameters()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
            r0 = 0
            r1 = 0
            r2 = r0
        L_0x0017:
            boolean r3 = r5.hasNext()
            if (r3 == 0) goto L_0x0034
            java.lang.Object r3 = r5.next()
            r4 = r3
            kotlin.reflect.KParameter r4 = (kotlin.reflect.KParameter) r4
            java.lang.String r4 = r4.getName()
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r6)
            if (r4 == 0) goto L_0x0017
            if (r1 == 0) goto L_0x0031
            goto L_0x0038
        L_0x0031:
            r1 = 1
            r2 = r3
            goto L_0x0017
        L_0x0034:
            if (r1 != 0) goto L_0x0037
            goto L_0x0038
        L_0x0037:
            r0 = r2
        L_0x0038:
            kotlin.reflect.KParameter r0 = (kotlin.reflect.KParameter) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.full.KCallables.findParameterByName(kotlin.reflect.KCallable, java.lang.String):kotlin.reflect.KParameter");
    }
}
