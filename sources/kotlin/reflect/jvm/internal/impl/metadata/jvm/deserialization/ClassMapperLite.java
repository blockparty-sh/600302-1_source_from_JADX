package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import androidx.exifinterface.media.ExifInterface;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

/* compiled from: ClassMapperLite.kt */
public final class ClassMapperLite {
    public static final ClassMapperLite INSTANCE = new ClassMapperLite();
    private static final Map<String, String> map;

    static {
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        List listOf = CollectionsKt.listOf("Boolean", "Z", "Char", "C", "Byte", "B", "Short", ExifInterface.LATITUDE_SOUTH, "Int", "I", "Float", "F", "Long", "J", "Double", "D");
        IntProgression step = RangesKt.step((IntProgression) CollectionsKt.getIndices(listOf), 2);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if (step2 <= 0 ? first >= last : first <= last) {
            while (true) {
                StringBuilder sb = new StringBuilder();
                String str = "kotlin/";
                sb.append(str);
                sb.append((String) listOf.get(first));
                int i = first + 1;
                linkedHashMap.put(sb.toString(), listOf.get(i));
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append((String) listOf.get(first));
                sb2.append("Array");
                String sb3 = sb2.toString();
                StringBuilder sb4 = new StringBuilder();
                sb4.append('[');
                sb4.append((String) listOf.get(i));
                linkedHashMap.put(sb3, sb4.toString());
                if (first == last) {
                    break;
                }
                first += step2;
            }
        }
        linkedHashMap.put("kotlin/Unit", ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        ClassMapperLite$map$1$1 classMapperLite$map$1$1 = new ClassMapperLite$map$1$1(linkedHashMap);
        classMapperLite$map$1$1.invoke("Any", "java/lang/Object");
        classMapperLite$map$1$1.invoke("Nothing", "java/lang/Void");
        classMapperLite$map$1$1.invoke("Annotation", "java/lang/annotation/Annotation");
        for (String str2 : CollectionsKt.listOf("String", "CharSequence", "Throwable", "Cloneable", "Number", "Comparable", "Enum")) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("java/lang/");
            sb5.append(str2);
            classMapperLite$map$1$1.invoke(str2, sb5.toString());
        }
        for (String str3 : CollectionsKt.listOf("Iterator", "Collection", "List", "Set", "Map", "ListIterator")) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append("collections/");
            sb6.append(str3);
            String sb7 = sb6.toString();
            StringBuilder sb8 = new StringBuilder();
            String str4 = "java/util/";
            sb8.append(str4);
            sb8.append(str3);
            classMapperLite$map$1$1.invoke(sb7, sb8.toString());
            StringBuilder sb9 = new StringBuilder();
            sb9.append("collections/Mutable");
            sb9.append(str3);
            String sb10 = sb9.toString();
            StringBuilder sb11 = new StringBuilder();
            sb11.append(str4);
            sb11.append(str3);
            classMapperLite$map$1$1.invoke(sb10, sb11.toString());
        }
        String str5 = "java/lang/Iterable";
        classMapperLite$map$1$1.invoke("collections/Iterable", str5);
        classMapperLite$map$1$1.invoke("collections/MutableIterable", str5);
        String str6 = "java/util/Map$Entry";
        classMapperLite$map$1$1.invoke("collections/Map.Entry", str6);
        classMapperLite$map$1$1.invoke("collections/MutableMap.MutableEntry", str6);
        for (int i2 = 0; i2 <= 22; i2++) {
            StringBuilder sb12 = new StringBuilder();
            sb12.append("Function");
            sb12.append(i2);
            String sb13 = sb12.toString();
            StringBuilder sb14 = new StringBuilder();
            sb14.append("kotlin/jvm/functions/Function");
            sb14.append(i2);
            classMapperLite$map$1$1.invoke(sb13, sb14.toString());
            StringBuilder sb15 = new StringBuilder();
            sb15.append("reflect/KFunction");
            sb15.append(i2);
            classMapperLite$map$1$1.invoke(sb15.toString(), "kotlin/reflect/KFunction");
        }
        for (String str7 : CollectionsKt.listOf("Char", "Byte", "Short", "Int", "Float", "Long", "Double", "String", "Enum")) {
            StringBuilder sb16 = new StringBuilder();
            sb16.append(str7);
            sb16.append(".Companion");
            String sb17 = sb16.toString();
            StringBuilder sb18 = new StringBuilder();
            sb18.append("kotlin/jvm/internal/");
            sb18.append(str7);
            sb18.append("CompanionObject");
            classMapperLite$map$1$1.invoke(sb17, sb18.toString());
        }
        map = linkedHashMap;
    }

    private ClassMapperLite() {
    }

    @JvmStatic
    @NotNull
    public static final String mapClass(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "classId");
        String str2 = (String) map.get(str);
        if (str2 != null) {
            return str2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Matrix.MATRIX_TYPE_RANDOM_LT);
        sb.append(StringsKt.replace$default(str, '.', (char) Typography.dollar, false, 4, (Object) null));
        sb.append(';');
        return sb.toString();
    }
}
