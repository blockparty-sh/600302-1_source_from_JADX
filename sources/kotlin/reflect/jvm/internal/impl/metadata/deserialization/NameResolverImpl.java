package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.LinkedList;
import java.util.List;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.QualifiedNameTable.QualifiedName.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.StringTable;
import org.jetbrains.annotations.NotNull;

/* compiled from: NameResolverImpl.kt */
public final class NameResolverImpl implements NameResolver {
    private final QualifiedNameTable qualifiedNames;
    private final StringTable strings;

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Kind.values().length];

        static {
            $EnumSwitchMapping$0[Kind.CLASS.ordinal()] = 1;
            $EnumSwitchMapping$0[Kind.PACKAGE.ordinal()] = 2;
            $EnumSwitchMapping$0[Kind.LOCAL.ordinal()] = 3;
        }
    }

    public NameResolverImpl(@NotNull StringTable stringTable, @NotNull QualifiedNameTable qualifiedNameTable) {
        Intrinsics.checkParameterIsNotNull(stringTable, "strings");
        Intrinsics.checkParameterIsNotNull(qualifiedNameTable, "qualifiedNames");
        this.strings = stringTable;
        this.qualifiedNames = qualifiedNameTable;
    }

    @NotNull
    public String getString(int i) {
        String string = this.strings.getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "strings.getString(index)");
        return string;
    }

    @NotNull
    public String getQualifiedClassName(int i) {
        Triple traverseIds = traverseIds(i);
        List list = (List) traverseIds.component1();
        String joinToString$default = CollectionsKt.joinToString$default((List) traverseIds.component2(), ".", null, null, 0, null, null, 62, null);
        if (list.isEmpty()) {
            return joinToString$default;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(CollectionsKt.joinToString$default(list, "/", null, null, 0, null, null, 62, null));
        sb.append('/');
        sb.append(joinToString$default);
        return sb.toString();
    }

    public boolean isLocalClassName(int i) {
        return ((Boolean) traverseIds(i).getThird()).booleanValue();
    }

    private final Triple<List<String>, List<String>, Boolean> traverseIds(int i) {
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        boolean z = false;
        while (i != -1) {
            QualifiedName qualifiedName = this.qualifiedNames.getQualifiedName(i);
            StringTable stringTable = this.strings;
            Intrinsics.checkExpressionValueIsNotNull(qualifiedName, "proto");
            String string = stringTable.getString(qualifiedName.getShortName());
            Kind kind = qualifiedName.getKind();
            if (kind == null) {
                Intrinsics.throwNpe();
            }
            int i2 = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
            if (i2 == 1) {
                linkedList2.addFirst(string);
            } else if (i2 == 2) {
                linkedList.addFirst(string);
            } else if (i2 == 3) {
                linkedList2.addFirst(string);
                z = true;
            }
            i = qualifiedName.getParentQualifiedName();
        }
        return new Triple<>(linkedList, linkedList2, Boolean.valueOf(z));
    }
}
