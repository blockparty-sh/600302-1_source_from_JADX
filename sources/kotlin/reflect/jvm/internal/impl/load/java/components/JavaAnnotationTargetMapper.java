package kotlin.reflect.jvm.internal.impl.load.java.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.KotlinRetention;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.KotlinTarget;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaEnumValueAnnotationArgument;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ArrayValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.EnumValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaAnnotationMapper.kt */
public final class JavaAnnotationTargetMapper {
    public static final JavaAnnotationTargetMapper INSTANCE = new JavaAnnotationTargetMapper();
    private static final Map<String, KotlinRetention> retentionNameList = MapsKt.mapOf(TuplesKt.m309to("RUNTIME", KotlinRetention.RUNTIME), TuplesKt.m309to("CLASS", KotlinRetention.BINARY), TuplesKt.m309to("SOURCE", KotlinRetention.SOURCE));
    private static final Map<String, EnumSet<KotlinTarget>> targetNameLists = MapsKt.mapOf(TuplesKt.m309to("PACKAGE", EnumSet.noneOf(KotlinTarget.class)), TuplesKt.m309to("TYPE", EnumSet.of(KotlinTarget.CLASS, KotlinTarget.FILE)), TuplesKt.m309to("ANNOTATION_TYPE", EnumSet.of(KotlinTarget.ANNOTATION_CLASS)), TuplesKt.m309to("TYPE_PARAMETER", EnumSet.of(KotlinTarget.TYPE_PARAMETER)), TuplesKt.m309to("FIELD", EnumSet.of(KotlinTarget.FIELD)), TuplesKt.m309to("LOCAL_VARIABLE", EnumSet.of(KotlinTarget.LOCAL_VARIABLE)), TuplesKt.m309to("PARAMETER", EnumSet.of(KotlinTarget.VALUE_PARAMETER)), TuplesKt.m309to("CONSTRUCTOR", EnumSet.of(KotlinTarget.CONSTRUCTOR)), TuplesKt.m309to("METHOD", EnumSet.of(KotlinTarget.FUNCTION, KotlinTarget.PROPERTY_GETTER, KotlinTarget.PROPERTY_SETTER)), TuplesKt.m309to("TYPE_USE", EnumSet.of(KotlinTarget.TYPE)));

    private JavaAnnotationTargetMapper() {
    }

    @NotNull
    public final Set<KotlinTarget> mapJavaTargetArgumentByName(@Nullable String str) {
        EnumSet enumSet = (EnumSet) targetNameLists.get(str);
        return enumSet != null ? enumSet : SetsKt.emptySet();
    }

    @NotNull
    public final ConstantValue<?> mapJavaTargetArguments$descriptors_jvm(@NotNull List<? extends JavaAnnotationArgument> list) {
        Intrinsics.checkParameterIsNotNull(list, "arguments");
        Iterable iterable = list;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            if (next instanceof JavaEnumValueAnnotationArgument) {
                arrayList.add(next);
            }
        }
        Iterable<JavaEnumValueAnnotationArgument> iterable2 = (List) arrayList;
        Collection arrayList2 = new ArrayList();
        for (JavaEnumValueAnnotationArgument javaEnumValueAnnotationArgument : iterable2) {
            JavaAnnotationTargetMapper javaAnnotationTargetMapper = INSTANCE;
            Name entryName = javaEnumValueAnnotationArgument.getEntryName();
            CollectionsKt.addAll(arrayList2, (Iterable<? extends T>) javaAnnotationTargetMapper.mapJavaTargetArgumentByName(entryName != null ? entryName.asString() : null));
        }
        Iterable<KotlinTarget> iterable3 = (List) arrayList2;
        Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
        for (KotlinTarget kotlinTarget : iterable3) {
            ClassId classId = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.annotationTarget);
            Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(KotlinB…Q_NAMES.annotationTarget)");
            Name identifier = Name.identifier(kotlinTarget.name());
            Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(kotlinTarget.name)");
            arrayList3.add(new EnumValue(classId, identifier));
        }
        return new ArrayValue<>((List) arrayList3, JavaAnnotationTargetMapper$mapJavaTargetArguments$1.INSTANCE);
    }

    @Nullable
    public final ConstantValue<?> mapJavaRetentionArgument$descriptors_jvm(@Nullable JavaAnnotationArgument javaAnnotationArgument) {
        ConstantValue<?> constantValue = null;
        if (!(javaAnnotationArgument instanceof JavaEnumValueAnnotationArgument)) {
            javaAnnotationArgument = null;
        }
        JavaEnumValueAnnotationArgument javaEnumValueAnnotationArgument = (JavaEnumValueAnnotationArgument) javaAnnotationArgument;
        if (javaEnumValueAnnotationArgument != null) {
            Map<String, KotlinRetention> map = retentionNameList;
            Name entryName = javaEnumValueAnnotationArgument.getEntryName();
            KotlinRetention kotlinRetention = (KotlinRetention) map.get(entryName != null ? entryName.asString() : null);
            if (kotlinRetention != null) {
                ClassId classId = ClassId.topLevel(KotlinBuiltIns.FQ_NAMES.annotationRetention);
                Intrinsics.checkExpressionValueIsNotNull(classId, "ClassId.topLevel(KotlinB…AMES.annotationRetention)");
                Name identifier = Name.identifier(kotlinRetention.name());
                Intrinsics.checkExpressionValueIsNotNull(identifier, "Name.identifier(retention.name)");
                constantValue = new EnumValue<>(classId, identifier);
            }
        }
        return constantValue;
    }
}
