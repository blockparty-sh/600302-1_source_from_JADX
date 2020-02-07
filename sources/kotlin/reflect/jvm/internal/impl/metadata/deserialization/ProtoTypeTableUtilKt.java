package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.C3063Type;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.C3063Type.Argument;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Class;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Property;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeAlias;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeParameter;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.ValueParameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: protoTypeTableUtil.kt */
public final class ProtoTypeTableUtilKt {
    @NotNull
    public static final List<C3063Type> supertypes(@NotNull Class classR, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(classR, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        List<C3063Type> supertypeList = classR.getSupertypeList();
        if (!(!supertypeList.isEmpty())) {
            supertypeList = null;
        }
        if (supertypeList != null) {
            return supertypeList;
        }
        List supertypeIdList = classR.getSupertypeIdList();
        Intrinsics.checkExpressionValueIsNotNull(supertypeIdList, "supertypeIdList");
        Iterable<Integer> iterable = supertypeIdList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Integer num : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(num, "it");
            arrayList.add(typeTable.get(num.intValue()));
        }
        return (List) arrayList;
    }

    @Nullable
    public static final C3063Type type(@NotNull Argument argument, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(argument, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (argument.hasType()) {
            return argument.getType();
        }
        if (argument.hasTypeId()) {
            return typeTable.get(argument.getTypeId());
        }
        return null;
    }

    @Nullable
    public static final C3063Type flexibleUpperBound(@NotNull C3063Type type, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(type, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (type.hasFlexibleUpperBound()) {
            return type.getFlexibleUpperBound();
        }
        if (type.hasFlexibleUpperBoundId()) {
            return typeTable.get(type.getFlexibleUpperBoundId());
        }
        return null;
    }

    @NotNull
    public static final List<C3063Type> upperBounds(@NotNull TypeParameter typeParameter, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(typeParameter, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        List<C3063Type> upperBoundList = typeParameter.getUpperBoundList();
        if (!(!upperBoundList.isEmpty())) {
            upperBoundList = null;
        }
        if (upperBoundList != null) {
            return upperBoundList;
        }
        List upperBoundIdList = typeParameter.getUpperBoundIdList();
        Intrinsics.checkExpressionValueIsNotNull(upperBoundIdList, "upperBoundIdList");
        Iterable<Integer> iterable = upperBoundIdList;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Integer num : iterable) {
            Intrinsics.checkExpressionValueIsNotNull(num, "it");
            arrayList.add(typeTable.get(num.intValue()));
        }
        return (List) arrayList;
    }

    @NotNull
    public static final C3063Type returnType(@NotNull Function function, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(function, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (function.hasReturnType()) {
            C3063Type returnType = function.getReturnType();
            Intrinsics.checkExpressionValueIsNotNull(returnType, "returnType");
            return returnType;
        } else if (function.hasReturnTypeId()) {
            return typeTable.get(function.getReturnTypeId());
        } else {
            throw new IllegalStateException("No returnType in ProtoBuf.Function".toString());
        }
    }

    public static final boolean hasReceiver(@NotNull Function function) {
        Intrinsics.checkParameterIsNotNull(function, "$receiver");
        return function.hasReceiverType() || function.hasReceiverTypeId();
    }

    @Nullable
    public static final C3063Type receiverType(@NotNull Function function, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(function, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (function.hasReceiverType()) {
            return function.getReceiverType();
        }
        if (function.hasReceiverTypeId()) {
            return typeTable.get(function.getReceiverTypeId());
        }
        return null;
    }

    @NotNull
    public static final C3063Type returnType(@NotNull Property property, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(property, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (property.hasReturnType()) {
            C3063Type returnType = property.getReturnType();
            Intrinsics.checkExpressionValueIsNotNull(returnType, "returnType");
            return returnType;
        } else if (property.hasReturnTypeId()) {
            return typeTable.get(property.getReturnTypeId());
        } else {
            throw new IllegalStateException("No returnType in ProtoBuf.Property".toString());
        }
    }

    public static final boolean hasReceiver(@NotNull Property property) {
        Intrinsics.checkParameterIsNotNull(property, "$receiver");
        return property.hasReceiverType() || property.hasReceiverTypeId();
    }

    @Nullable
    public static final C3063Type receiverType(@NotNull Property property, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(property, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (property.hasReceiverType()) {
            return property.getReceiverType();
        }
        if (property.hasReceiverTypeId()) {
            return typeTable.get(property.getReceiverTypeId());
        }
        return null;
    }

    @NotNull
    public static final C3063Type type(@NotNull ValueParameter valueParameter, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(valueParameter, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (valueParameter.hasType()) {
            C3063Type type = valueParameter.getType();
            Intrinsics.checkExpressionValueIsNotNull(type, "type");
            return type;
        } else if (valueParameter.hasTypeId()) {
            return typeTable.get(valueParameter.getTypeId());
        } else {
            throw new IllegalStateException("No type in ProtoBuf.ValueParameter".toString());
        }
    }

    @Nullable
    public static final C3063Type varargElementType(@NotNull ValueParameter valueParameter, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(valueParameter, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (valueParameter.hasVarargElementType()) {
            return valueParameter.getVarargElementType();
        }
        if (valueParameter.hasVarargElementTypeId()) {
            return typeTable.get(valueParameter.getVarargElementTypeId());
        }
        return null;
    }

    @Nullable
    public static final C3063Type outerType(@NotNull C3063Type type, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(type, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (type.hasOuterType()) {
            return type.getOuterType();
        }
        if (type.hasOuterTypeId()) {
            return typeTable.get(type.getOuterTypeId());
        }
        return null;
    }

    @Nullable
    public static final C3063Type abbreviatedType(@NotNull C3063Type type, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(type, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (type.hasAbbreviatedType()) {
            return type.getAbbreviatedType();
        }
        if (type.hasAbbreviatedTypeId()) {
            return typeTable.get(type.getAbbreviatedTypeId());
        }
        return null;
    }

    @NotNull
    public static final C3063Type underlyingType(@NotNull TypeAlias typeAlias, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(typeAlias, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (typeAlias.hasUnderlyingType()) {
            C3063Type underlyingType = typeAlias.getUnderlyingType();
            Intrinsics.checkExpressionValueIsNotNull(underlyingType, "underlyingType");
            return underlyingType;
        } else if (typeAlias.hasUnderlyingTypeId()) {
            return typeTable.get(typeAlias.getUnderlyingTypeId());
        } else {
            throw new IllegalStateException("No underlyingType in ProtoBuf.TypeAlias".toString());
        }
    }

    @NotNull
    public static final C3063Type expandedType(@NotNull TypeAlias typeAlias, @NotNull TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(typeAlias, "$receiver");
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        if (typeAlias.hasExpandedType()) {
            C3063Type expandedType = typeAlias.getExpandedType();
            Intrinsics.checkExpressionValueIsNotNull(expandedType, "expandedType");
            return expandedType;
        } else if (typeAlias.hasExpandedTypeId()) {
            return typeTable.get(typeAlias.getExpandedTypeId());
        } else {
            throw new IllegalStateException("No expandedType in ProtoBuf.TypeAlias".toString());
        }
    }
}
