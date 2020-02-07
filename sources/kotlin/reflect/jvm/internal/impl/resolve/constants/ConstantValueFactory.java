package kotlin.reflect.jvm.internal.impl.resolve.constants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ConstantValueFactory.kt */
public final class ConstantValueFactory {
    public static final ConstantValueFactory INSTANCE = new ConstantValueFactory();

    private ConstantValueFactory() {
    }

    @NotNull
    public final ArrayValue createArrayValue(@NotNull List<? extends ConstantValue<?>> list, @NotNull KotlinType kotlinType) {
        Intrinsics.checkParameterIsNotNull(list, "value");
        Intrinsics.checkParameterIsNotNull(kotlinType, "type");
        return new ArrayValue(list, new ConstantValueFactory$createArrayValue$1(kotlinType));
    }

    @Nullable
    public final ConstantValue<?> createConstantValue(@Nullable Object obj) {
        if (obj instanceof Byte) {
            return new ByteValue<>(((Number) obj).byteValue());
        }
        if (obj instanceof Short) {
            return new ShortValue<>(((Number) obj).shortValue());
        }
        if (obj instanceof Integer) {
            return new IntValue<>(((Number) obj).intValue());
        }
        if (obj instanceof Long) {
            return new LongValue<>(((Number) obj).longValue());
        }
        if (obj instanceof Character) {
            return new CharValue<>(((Character) obj).charValue());
        }
        if (obj instanceof Float) {
            return new FloatValue<>(((Number) obj).floatValue());
        }
        if (obj instanceof Double) {
            return new DoubleValue<>(((Number) obj).doubleValue());
        }
        if (obj instanceof Boolean) {
            return new BooleanValue<>(((Boolean) obj).booleanValue());
        }
        if (obj instanceof String) {
            return new StringValue<>((String) obj);
        }
        if (obj instanceof byte[]) {
            return createArrayValue(ArraysKt.toList((byte[]) obj), PrimitiveType.BYTE);
        }
        if (obj instanceof short[]) {
            return createArrayValue(ArraysKt.toList((short[]) obj), PrimitiveType.SHORT);
        }
        if (obj instanceof int[]) {
            return createArrayValue(ArraysKt.toList((int[]) obj), PrimitiveType.INT);
        }
        if (obj instanceof long[]) {
            return createArrayValue(ArraysKt.toList((long[]) obj), PrimitiveType.LONG);
        }
        if (obj instanceof char[]) {
            return createArrayValue(ArraysKt.toList((char[]) obj), PrimitiveType.CHAR);
        }
        if (obj instanceof float[]) {
            return createArrayValue(ArraysKt.toList((float[]) obj), PrimitiveType.FLOAT);
        }
        if (obj instanceof double[]) {
            return createArrayValue(ArraysKt.toList((double[]) obj), PrimitiveType.DOUBLE);
        }
        if (obj instanceof boolean[]) {
            return createArrayValue(ArraysKt.toList((boolean[]) obj), PrimitiveType.BOOLEAN);
        }
        if (obj == null) {
            return new NullValue<>();
        }
        return null;
    }

    private final ArrayValue createArrayValue(List<?> list, PrimitiveType primitiveType) {
        Iterable<Object> list2 = CollectionsKt.toList(list);
        Collection arrayList = new ArrayList();
        for (Object createConstantValue : list2) {
            ConstantValue createConstantValue2 = createConstantValue(createConstantValue);
            if (createConstantValue2 != null) {
                arrayList.add(createConstantValue2);
            }
        }
        return new ArrayValue((List) arrayList, new ConstantValueFactory$createArrayValue$3(primitiveType));
    }
}
