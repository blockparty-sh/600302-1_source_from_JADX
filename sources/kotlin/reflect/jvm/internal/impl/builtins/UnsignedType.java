package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import org.jetbrains.annotations.NotNull;

/* compiled from: UnsignedType.kt */
public enum UnsignedType {
    UBYTE(r2),
    USHORT(r2),
    UINT(r2),
    ULONG(r2);
    
    @NotNull
    private final ClassId arrayClassId;
    @NotNull
    private final Name arrayTypeName;
    @NotNull
    private final ClassId classId;
    @NotNull
    private final Name typeName;

    protected UnsignedType(ClassId classId2) {
        Intrinsics.checkParameterIsNotNull(classId2, "classId");
        this.classId = classId2;
        this.typeName = this.classId.getShortClassName();
        StringBuilder sb = new StringBuilder();
        sb.append(this.typeName.asString());
        sb.append("Array");
        this.arrayTypeName = Name.identifier(sb.toString());
        this.arrayClassId = new ClassId(this.classId.getPackageFqName(), this.arrayTypeName);
    }

    @NotNull
    public final ClassId getClassId() {
        return this.classId;
    }

    @NotNull
    public final Name getTypeName() {
        return this.typeName;
    }

    @NotNull
    public final ClassId getArrayClassId() {
        return this.arrayClassId;
    }
}
