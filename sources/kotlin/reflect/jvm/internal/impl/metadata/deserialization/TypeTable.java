package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.C3063Type;
import org.jetbrains.annotations.NotNull;

/* compiled from: TypeTable.kt */
public final class TypeTable {
    @NotNull
    private final List<C3063Type> types;

    public TypeTable(@NotNull kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.TypeTable typeTable) {
        Intrinsics.checkParameterIsNotNull(typeTable, "typeTable");
        TypeTable typeTable2 = this;
        List<C3063Type> typeList = typeTable.getTypeList();
        if (typeTable.hasFirstNullable()) {
            int firstNullable = typeTable.getFirstNullable();
            List typeList2 = typeTable.getTypeList();
            Intrinsics.checkExpressionValueIsNotNull(typeList2, "typeTable.typeList");
            Iterable<C3063Type> iterable = typeList2;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            int i = 0;
            for (C3063Type type : iterable) {
                int i2 = i + 1;
                if (i >= firstNullable) {
                    type = type.toBuilder().setNullable(true).build();
                }
                arrayList.add(type);
                i = i2;
            }
            typeList = (List) arrayList;
        } else {
            Intrinsics.checkExpressionValueIsNotNull(typeList, "originalTypes");
        }
        this.types = typeList;
    }

    @NotNull
    public final C3063Type get(int i) {
        return (C3063Type) this.types.get(i);
    }
}
