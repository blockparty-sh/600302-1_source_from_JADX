package kotlin.reflect.jvm.internal.impl.load.kotlin;

import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUseSiteTarget;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationWithTarget;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass.AnnotationArgumentVisitor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Annotation;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValueFactory;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.LongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ShortValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UByteValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UIntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ULongValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.UShortValue;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationDeserializer;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: BinaryClassAnnotationAndConstantLoaderImpl.kt */
public final class BinaryClassAnnotationAndConstantLoaderImpl extends AbstractBinaryClassAnnotationAndConstantLoader<AnnotationDescriptor, ConstantValue<?>, AnnotationWithTarget> {
    private final AnnotationDeserializer annotationDeserializer = new AnnotationDeserializer(this.module, this.notFoundClasses);
    private final ModuleDescriptor module;
    private final NotFoundClasses notFoundClasses;

    public BinaryClassAnnotationAndConstantLoaderImpl(@NotNull ModuleDescriptor moduleDescriptor, @NotNull NotFoundClasses notFoundClasses2, @NotNull StorageManager storageManager, @NotNull KotlinClassFinder kotlinClassFinder) {
        Intrinsics.checkParameterIsNotNull(moduleDescriptor, "module");
        Intrinsics.checkParameterIsNotNull(notFoundClasses2, "notFoundClasses");
        Intrinsics.checkParameterIsNotNull(storageManager, "storageManager");
        Intrinsics.checkParameterIsNotNull(kotlinClassFinder, "kotlinClassFinder");
        super(storageManager, kotlinClassFinder);
        this.module = moduleDescriptor;
        this.notFoundClasses = notFoundClasses2;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public AnnotationDescriptor loadTypeAnnotation(@NotNull Annotation annotation, @NotNull NameResolver nameResolver) {
        Intrinsics.checkParameterIsNotNull(annotation, "proto");
        Intrinsics.checkParameterIsNotNull(nameResolver, "nameResolver");
        return this.annotationDeserializer.deserializeAnnotation(annotation, nameResolver);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public ConstantValue<?> loadConstant(@NotNull String str, @NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(str, "desc");
        Intrinsics.checkParameterIsNotNull(obj, "initializer");
        boolean z = false;
        if (StringsKt.contains$default((CharSequence) "ZBCS", (CharSequence) str, false, 2, (Object) null)) {
            int intValue = ((Integer) obj).intValue();
            int hashCode = str.hashCode();
            if (hashCode != 66) {
                if (hashCode != 67) {
                    if (hashCode != 83) {
                        if (hashCode == 90 && str.equals("Z")) {
                            if (intValue != 0) {
                                z = true;
                            }
                            obj = Boolean.valueOf(z);
                        }
                    } else if (str.equals(ExifInterface.LATITUDE_SOUTH)) {
                        obj = Short.valueOf((short) intValue);
                    }
                } else if (str.equals("C")) {
                    obj = Character.valueOf((char) intValue);
                }
            } else if (str.equals("B")) {
                obj = Byte.valueOf((byte) intValue);
            }
            throw new AssertionError(str);
        }
        return ConstantValueFactory.INSTANCE.createConstantValue(obj);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public ConstantValue<?> transformToUnsignedConstant(@NotNull ConstantValue<?> constantValue) {
        Intrinsics.checkParameterIsNotNull(constantValue, "constant");
        if (constantValue instanceof ByteValue) {
            return new UByteValue<>(((Number) ((ByteValue) constantValue).getValue()).byteValue());
        }
        if (constantValue instanceof ShortValue) {
            return new UShortValue<>(((Number) ((ShortValue) constantValue).getValue()).shortValue());
        }
        if (constantValue instanceof IntValue) {
            return new UIntValue<>(((Number) ((IntValue) constantValue).getValue()).intValue());
        }
        return constantValue instanceof LongValue ? new ULongValue<>(((Number) ((LongValue) constantValue).getValue()).longValue()) : constantValue;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public List<AnnotationWithTarget> loadPropertyAnnotations(@NotNull List<? extends AnnotationDescriptor> list, @NotNull List<? extends AnnotationDescriptor> list2, @NotNull AnnotationUseSiteTarget annotationUseSiteTarget) {
        Intrinsics.checkParameterIsNotNull(list, "propertyAnnotations");
        Intrinsics.checkParameterIsNotNull(list2, "fieldAnnotations");
        Intrinsics.checkParameterIsNotNull(annotationUseSiteTarget, "fieldUseSiteTarget");
        Iterable<AnnotationDescriptor> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (AnnotationDescriptor annotationWithTarget : iterable) {
            arrayList.add(new AnnotationWithTarget(annotationWithTarget, null));
        }
        Collection collection = (List) arrayList;
        Iterable<AnnotationDescriptor> iterable2 = list2;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (AnnotationDescriptor annotationWithTarget2 : iterable2) {
            arrayList2.add(new AnnotationWithTarget(annotationWithTarget2, annotationUseSiteTarget));
        }
        return CollectionsKt.plus(collection, (Iterable<? extends T>) (List) arrayList2);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public List<AnnotationWithTarget> transformAnnotations(@NotNull List<? extends AnnotationDescriptor> list) {
        Intrinsics.checkParameterIsNotNull(list, "annotations");
        Iterable<AnnotationDescriptor> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (AnnotationDescriptor annotationWithTarget : iterable) {
            arrayList.add(new AnnotationWithTarget(annotationWithTarget, null));
        }
        return (List) arrayList;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public AnnotationArgumentVisitor loadAnnotation(@NotNull ClassId classId, @NotNull SourceElement sourceElement, @NotNull List<AnnotationDescriptor> list) {
        Intrinsics.checkParameterIsNotNull(classId, "annotationClassId");
        Intrinsics.checkParameterIsNotNull(sourceElement, Param.SOURCE);
        Intrinsics.checkParameterIsNotNull(list, "result");
        return new BinaryClassAnnotationAndConstantLoaderImpl$loadAnnotation$1(this, resolveClass(classId), list, sourceElement);
    }

    private final ClassDescriptor resolveClass(ClassId classId) {
        return FindClassInModuleKt.findNonGenericClassAcrossDependencies(this.module, classId, this.notFoundClasses);
    }
}
