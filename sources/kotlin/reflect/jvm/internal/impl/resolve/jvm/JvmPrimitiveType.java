package kotlin.reflect.jvm.internal.impl.resolve.jvm;

import androidx.exifinterface.media.ExifInterface;
import com.leanplum.internal.Constants.Kinds;
import com.microsoft.appcenter.ingestion.models.properties.BooleanTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.PrimitiveType;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import org.jetbrains.annotations.NotNull;

public enum JvmPrimitiveType {
    BOOLEAN(PrimitiveType.BOOLEAN, BooleanTypedProperty.TYPE, "Z", "java.lang.Boolean"),
    CHAR(PrimitiveType.CHAR, "char", "C", "java.lang.Character"),
    BYTE(PrimitiveType.BYTE, "byte", "B", "java.lang.Byte"),
    SHORT(PrimitiveType.SHORT, "short", ExifInterface.LATITUDE_SOUTH, "java.lang.Short"),
    INT(PrimitiveType.INT, "int", "I", "java.lang.Integer"),
    FLOAT(PrimitiveType.FLOAT, Kinds.FLOAT, "F", "java.lang.Float"),
    LONG(PrimitiveType.LONG, LongTypedProperty.TYPE, "J", "java.lang.Long"),
    DOUBLE(PrimitiveType.DOUBLE, DoubleTypedProperty.TYPE, "D", "java.lang.Double");
    
    private static final Map<String, JvmPrimitiveType> TYPE_BY_NAME = null;
    private static final Map<PrimitiveType, JvmPrimitiveType> TYPE_BY_PRIMITIVE_TYPE = null;
    private static final Set<FqName> WRAPPERS_CLASS_NAMES = null;
    private final String desc;
    private final String name;
    private final PrimitiveType primitiveType;
    private final FqName wrapperFqName;

    static {
        int i;
        JvmPrimitiveType[] values;
        WRAPPERS_CLASS_NAMES = new HashSet();
        TYPE_BY_NAME = new HashMap();
        TYPE_BY_PRIMITIVE_TYPE = new EnumMap(PrimitiveType.class);
        for (JvmPrimitiveType jvmPrimitiveType : values()) {
            WRAPPERS_CLASS_NAMES.add(jvmPrimitiveType.getWrapperFqName());
            TYPE_BY_NAME.put(jvmPrimitiveType.getJavaKeywordName(), jvmPrimitiveType);
            TYPE_BY_PRIMITIVE_TYPE.put(jvmPrimitiveType.getPrimitiveType(), jvmPrimitiveType);
        }
    }

    @NotNull
    public static JvmPrimitiveType get(@NotNull String str) {
        JvmPrimitiveType jvmPrimitiveType = (JvmPrimitiveType) TYPE_BY_NAME.get(str);
        if (jvmPrimitiveType != null) {
            return jvmPrimitiveType;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Non-primitive type name passed: ");
        sb.append(str);
        throw new AssertionError(sb.toString());
    }

    @NotNull
    public static JvmPrimitiveType get(@NotNull PrimitiveType primitiveType2) {
        return (JvmPrimitiveType) TYPE_BY_PRIMITIVE_TYPE.get(primitiveType2);
    }

    private JvmPrimitiveType(PrimitiveType primitiveType2, String str, @NotNull String str2, @NotNull String str3) {
        this.primitiveType = primitiveType2;
        this.name = str;
        this.desc = str2;
        this.wrapperFqName = new FqName(str3);
    }

    @NotNull
    public PrimitiveType getPrimitiveType() {
        return this.primitiveType;
    }

    @NotNull
    public String getJavaKeywordName() {
        return this.name;
    }

    @NotNull
    public String getDesc() {
        return this.desc;
    }

    @NotNull
    public FqName getWrapperFqName() {
        return this.wrapperFqName;
    }
}
