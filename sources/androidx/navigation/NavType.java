package androidx.navigation;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.leanplum.internal.Constants.Kinds;
import com.microsoft.appcenter.ingestion.models.properties.BooleanTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import java.io.Serializable;

public abstract class NavType<T> {
    @NonNull
    public static final NavType<boolean[]> BoolArrayType = new NavType<boolean[]>(true) {
        @NonNull
        public String getName() {
            return "boolean[]";
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable boolean[] zArr) {
            bundle.putBooleanArray(str, zArr);
        }

        public boolean[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (boolean[]) bundle.get(str);
        }

        @NonNull
        public boolean[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    @NonNull
    public static final NavType<Boolean> BoolType = new NavType<Boolean>(false) {
        @NonNull
        public String getName() {
            return BooleanTypedProperty.TYPE;
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull Boolean bool) {
            bundle.putBoolean(str, bool.booleanValue());
        }

        public Boolean get(@NonNull Bundle bundle, @NonNull String str) {
            return (Boolean) bundle.get(str);
        }

        @NonNull
        public Boolean parseValue(@NonNull String str) {
            if ("true".equals(str)) {
                return Boolean.valueOf(true);
            }
            if ("false".equals(str)) {
                return Boolean.valueOf(false);
            }
            throw new IllegalArgumentException("A boolean NavType only accepts \"true\" or \"false\" values.");
        }
    };
    @NonNull
    public static final NavType<float[]> FloatArrayType = new NavType<float[]>(true) {
        @NonNull
        public String getName() {
            return "float[]";
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable float[] fArr) {
            bundle.putFloatArray(str, fArr);
        }

        public float[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (float[]) bundle.get(str);
        }

        @NonNull
        public float[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    @NonNull
    public static final NavType<Float> FloatType = new NavType<Float>(false) {
        @NonNull
        public String getName() {
            return Kinds.FLOAT;
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull Float f) {
            bundle.putFloat(str, f.floatValue());
        }

        public Float get(@NonNull Bundle bundle, @NonNull String str) {
            return (Float) bundle.get(str);
        }

        @NonNull
        public Float parseValue(@NonNull String str) {
            return Float.valueOf(Float.parseFloat(str));
        }
    };
    @NonNull
    public static final NavType<int[]> IntArrayType = new NavType<int[]>(true) {
        @NonNull
        public String getName() {
            return "integer[]";
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable int[] iArr) {
            bundle.putIntArray(str, iArr);
        }

        public int[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (int[]) bundle.get(str);
        }

        @NonNull
        public int[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    @NonNull
    public static final NavType<Integer> IntType = new NavType<Integer>(false) {
        @NonNull
        public String getName() {
            return Kinds.INT;
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull Integer num) {
            bundle.putInt(str, num.intValue());
        }

        public Integer get(@NonNull Bundle bundle, @NonNull String str) {
            return (Integer) bundle.get(str);
        }

        @NonNull
        public Integer parseValue(@NonNull String str) {
            if (str.startsWith("0x")) {
                return Integer.valueOf(Integer.parseInt(str.substring(2), 16));
            }
            return Integer.valueOf(Integer.parseInt(str));
        }
    };
    @NonNull
    public static final NavType<long[]> LongArrayType = new NavType<long[]>(true) {
        @NonNull
        public String getName() {
            return "long[]";
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable long[] jArr) {
            bundle.putLongArray(str, jArr);
        }

        public long[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (long[]) bundle.get(str);
        }

        @NonNull
        public long[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    @NonNull
    public static final NavType<Long> LongType = new NavType<Long>(false) {
        @NonNull
        public String getName() {
            return LongTypedProperty.TYPE;
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull Long l) {
            bundle.putLong(str, l.longValue());
        }

        public Long get(@NonNull Bundle bundle, @NonNull String str) {
            return (Long) bundle.get(str);
        }

        @NonNull
        public Long parseValue(@NonNull String str) {
            if (str.endsWith("L")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.startsWith("0x")) {
                return Long.valueOf(Long.parseLong(str.substring(2), 16));
            }
            return Long.valueOf(Long.parseLong(str));
        }
    };
    @NonNull
    public static final NavType<Integer> ReferenceType = new NavType<Integer>(false) {
        @NonNull
        public String getName() {
            return "reference";
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @NonNull @AnyRes Integer num) {
            bundle.putInt(str, num.intValue());
        }

        @AnyRes
        public Integer get(@NonNull Bundle bundle, @NonNull String str) {
            return (Integer) bundle.get(str);
        }

        @NonNull
        public Integer parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("References don't support parsing string values.");
        }
    };
    @NonNull
    public static final NavType<String[]> StringArrayType = new NavType<String[]>(true) {
        @NonNull
        public String getName() {
            return "string[]";
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable String[] strArr) {
            bundle.putStringArray(str, strArr);
        }

        public String[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (String[]) bundle.get(str);
        }

        @NonNull
        public String[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }
    };
    @NonNull
    public static final NavType<String> StringType = new NavType<String>(true) {
        @NonNull
        public String getName() {
            return "string";
        }

        @NonNull
        public String parseValue(@NonNull String str) {
            return str;
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable String str2) {
            bundle.putString(str, str2);
        }

        public String get(@NonNull Bundle bundle, @NonNull String str) {
            return (String) bundle.get(str);
        }
    };
    private final boolean mNullableAllowed;

    public static final class EnumType<D extends Enum> extends SerializableType<D> {
        @NonNull
        private final Class<D> mType;

        public EnumType(@NonNull Class<D> cls) {
            super(false, cls);
            if (cls.isEnum()) {
                this.mType = cls;
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(cls);
            sb.append(" is not an Enum type.");
            throw new IllegalArgumentException(sb.toString());
        }

        @NonNull
        public D parseValue(@NonNull String str) {
            for (D d : (Enum[]) this.mType.getEnumConstants()) {
                D d2 = (Enum) d;
                if (d2.name().equals(str)) {
                    return d2;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Enum value ");
            sb.append(str);
            sb.append(" not found for type ");
            sb.append(this.mType.getName());
            sb.append(".");
            throw new IllegalArgumentException(sb.toString());
        }

        @NonNull
        public String getName() {
            return this.mType.getName();
        }
    }

    public static final class ParcelableArrayType<D extends Parcelable> extends NavType<D[]> {
        @NonNull
        private final Class<D[]> mArrayType;

        public ParcelableArrayType(@NonNull Class<D> cls) {
            super(true);
            if (Parcelable.class.isAssignableFrom(cls)) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[L");
                    sb.append(cls.getName());
                    sb.append(";");
                    this.mArrayType = Class.forName(sb.toString());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(cls);
                sb2.append(" does not implement Parcelable.");
                throw new IllegalArgumentException(sb2.toString());
            }
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable D[] dArr) {
            this.mArrayType.cast(dArr);
            bundle.putParcelableArray(str, dArr);
        }

        @Nullable
        public D[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (Parcelable[]) bundle.get(str);
        }

        @NonNull
        public D[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @NonNull
        public String getName() {
            return this.mArrayType.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mArrayType.equals(((ParcelableArrayType) obj).mArrayType);
        }

        public int hashCode() {
            return this.mArrayType.hashCode();
        }
    }

    public static final class ParcelableType<D> extends NavType<D> {
        @NonNull
        private final Class<D> mType;

        public ParcelableType(@NonNull Class<D> cls) {
            super(true);
            if (Parcelable.class.isAssignableFrom(cls) || Serializable.class.isAssignableFrom(cls)) {
                this.mType = cls;
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(cls);
            sb.append(" does not implement Parcelable or Serializable.");
            throw new IllegalArgumentException(sb.toString());
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable D d) {
            this.mType.cast(d);
            if (d == null || (d instanceof Parcelable)) {
                bundle.putParcelable(str, (Parcelable) d);
            } else if (d instanceof Serializable) {
                bundle.putSerializable(str, (Serializable) d);
            }
        }

        @Nullable
        public D get(@NonNull Bundle bundle, @NonNull String str) {
            return bundle.get(str);
        }

        @NonNull
        public D parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Parcelables don't support default values.");
        }

        @NonNull
        public String getName() {
            return this.mType.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mType.equals(((ParcelableType) obj).mType);
        }

        public int hashCode() {
            return this.mType.hashCode();
        }
    }

    public static final class SerializableArrayType<D extends Serializable> extends NavType<D[]> {
        @NonNull
        private final Class<D[]> mArrayType;

        public SerializableArrayType(@NonNull Class<D> cls) {
            super(true);
            if (Serializable.class.isAssignableFrom(cls)) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("[L");
                    sb.append(cls.getName());
                    sb.append(";");
                    this.mArrayType = Class.forName(sb.toString());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(cls);
                sb2.append(" does not implement Serializable.");
                throw new IllegalArgumentException(sb2.toString());
            }
        }

        /* JADX WARNING: type inference failed for: r4v0, types: [D[], java.lang.Object, java.io.Serializable] */
        /* JADX WARNING: Incorrect type for immutable var: ssa=D[], code=null, for r4v0, types: [D[], java.lang.Object, java.io.Serializable] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void put(@androidx.annotation.NonNull android.os.Bundle r2, @androidx.annotation.NonNull java.lang.String r3, @androidx.annotation.Nullable D[] r4) {
            /*
                r1 = this;
                java.lang.Class<D[]> r0 = r1.mArrayType
                r0.cast(r4)
                r2.putSerializable(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavType.SerializableArrayType.put(android.os.Bundle, java.lang.String, java.io.Serializable[]):void");
        }

        @Nullable
        public D[] get(@NonNull Bundle bundle, @NonNull String str) {
            return (Serializable[]) bundle.get(str);
        }

        @NonNull
        public D[] parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Arrays don't support default values.");
        }

        @NonNull
        public String getName() {
            return this.mArrayType.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mArrayType.equals(((SerializableArrayType) obj).mArrayType);
        }

        public int hashCode() {
            return this.mArrayType.hashCode();
        }
    }

    public static class SerializableType<D extends Serializable> extends NavType<D> {
        @NonNull
        private final Class<D> mType;

        public SerializableType(@NonNull Class<D> cls) {
            super(true);
            if (!Serializable.class.isAssignableFrom(cls)) {
                StringBuilder sb = new StringBuilder();
                sb.append(cls);
                sb.append(" does not implement Serializable.");
                throw new IllegalArgumentException(sb.toString());
            } else if (!cls.isEnum()) {
                this.mType = cls;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(cls);
                sb2.append(" is an Enum. You should use EnumType instead.");
                throw new IllegalArgumentException(sb2.toString());
            }
        }

        SerializableType(boolean z, @NonNull Class<D> cls) {
            super(z);
            if (Serializable.class.isAssignableFrom(cls)) {
                this.mType = cls;
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(cls);
            sb.append(" does not implement Serializable.");
            throw new IllegalArgumentException(sb.toString());
        }

        public void put(@NonNull Bundle bundle, @NonNull String str, @Nullable D d) {
            this.mType.cast(d);
            bundle.putSerializable(str, d);
        }

        @Nullable
        public D get(@NonNull Bundle bundle, @NonNull String str) {
            return (Serializable) bundle.get(str);
        }

        @NonNull
        public D parseValue(@NonNull String str) {
            throw new UnsupportedOperationException("Serializables don't support default values.");
        }

        @NonNull
        public String getName() {
            return this.mType.getName();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mType.equals(((SerializableType) obj).mType);
        }

        public int hashCode() {
            return this.mType.hashCode();
        }
    }

    @Nullable
    public abstract T get(@NonNull Bundle bundle, @NonNull String str);

    @NonNull
    public abstract String getName();

    @NonNull
    public abstract T parseValue(@NonNull String str);

    public abstract void put(@NonNull Bundle bundle, @NonNull String str, @Nullable T t);

    NavType(boolean z) {
        this.mNullableAllowed = z;
    }

    public boolean isNullableAllowed() {
        return this.mNullableAllowed;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public T parseAndPut(@NonNull Bundle bundle, @NonNull String str, @NonNull String str2) {
        T parseValue = parseValue(str2);
        put(bundle, str, parseValue);
        return parseValue;
    }

    @NonNull
    public String toString() {
        return getName();
    }

    @NonNull
    public static NavType<?> fromArgType(@Nullable String str, @Nullable String str2) {
        String str3;
        if (IntType.getName().equals(str)) {
            return IntType;
        }
        if (IntArrayType.getName().equals(str)) {
            return IntArrayType;
        }
        if (LongType.getName().equals(str)) {
            return LongType;
        }
        if (LongArrayType.getName().equals(str)) {
            return LongArrayType;
        }
        if (BoolType.getName().equals(str)) {
            return BoolType;
        }
        if (BoolArrayType.getName().equals(str)) {
            return BoolArrayType;
        }
        if (StringType.getName().equals(str)) {
            return StringType;
        }
        if (StringArrayType.getName().equals(str)) {
            return StringArrayType;
        }
        if (FloatType.getName().equals(str)) {
            return FloatType;
        }
        if (FloatArrayType.getName().equals(str)) {
            return FloatArrayType;
        }
        if (ReferenceType.getName().equals(str)) {
            return ReferenceType;
        }
        if (str == null || str.isEmpty()) {
            return StringType;
        }
        try {
            if (!str.startsWith(".") || str2 == null) {
                str3 = str;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append(str);
                str3 = sb.toString();
            }
            if (str.endsWith("[]")) {
                str3 = str3.substring(0, str3.length() - 2);
                Class cls = Class.forName(str3);
                if (Parcelable.class.isAssignableFrom(cls)) {
                    return new ParcelableArrayType(cls);
                }
                if (Serializable.class.isAssignableFrom(cls)) {
                    return new SerializableArrayType(cls);
                }
            } else {
                Class cls2 = Class.forName(str3);
                if (Parcelable.class.isAssignableFrom(cls2)) {
                    return new ParcelableType(cls2);
                }
                if (Enum.class.isAssignableFrom(cls2)) {
                    return new EnumType(cls2);
                }
                if (Serializable.class.isAssignableFrom(cls2)) {
                    return new SerializableType(cls2);
                }
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str3);
            sb2.append(" is not Serializable or Parcelable.");
            throw new IllegalArgumentException(sb2.toString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: type inference failed for: r1v6, types: [androidx.navigation.NavType, androidx.navigation.NavType<java.lang.Float>] */
    /* JADX WARNING: type inference failed for: r1v7, types: [androidx.navigation.NavType<java.lang.Long>, androidx.navigation.NavType] */
    /* JADX WARNING: type inference failed for: r1v8, types: [androidx.navigation.NavType, androidx.navigation.NavType<java.lang.Integer>] */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:3|4|5) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:6|7|8) */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:9|10|11) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        BoolType.parseValue(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return BoolType;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        return StringType;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
        LongType.parseValue(r1);
        r1 = LongType;
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000f, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0010, code lost:
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        FloatType.parseValue(r1);
        r1 = FloatType;
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        return r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0008 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0010 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0018 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 3 */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static androidx.navigation.NavType inferFromValue(@androidx.annotation.NonNull java.lang.String r1) {
        /*
            androidx.navigation.NavType<java.lang.Integer> r0 = IntType     // Catch:{ IllegalArgumentException -> 0x0008 }
            r0.parseValue(r1)     // Catch:{ IllegalArgumentException -> 0x0008 }
            androidx.navigation.NavType<java.lang.Integer> r1 = IntType     // Catch:{ IllegalArgumentException -> 0x0008 }
            return r1
        L_0x0008:
            androidx.navigation.NavType<java.lang.Long> r0 = LongType     // Catch:{ IllegalArgumentException -> 0x0010 }
            r0.parseValue(r1)     // Catch:{ IllegalArgumentException -> 0x0010 }
            androidx.navigation.NavType<java.lang.Long> r1 = LongType     // Catch:{ IllegalArgumentException -> 0x0010 }
            return r1
        L_0x0010:
            androidx.navigation.NavType<java.lang.Float> r0 = FloatType     // Catch:{ IllegalArgumentException -> 0x0018 }
            r0.parseValue(r1)     // Catch:{ IllegalArgumentException -> 0x0018 }
            androidx.navigation.NavType<java.lang.Float> r1 = FloatType     // Catch:{ IllegalArgumentException -> 0x0018 }
            return r1
        L_0x0018:
            androidx.navigation.NavType<java.lang.Boolean> r0 = BoolType     // Catch:{ IllegalArgumentException -> 0x0020 }
            r0.parseValue(r1)     // Catch:{ IllegalArgumentException -> 0x0020 }
            androidx.navigation.NavType<java.lang.Boolean> r1 = BoolType     // Catch:{ IllegalArgumentException -> 0x0020 }
            return r1
        L_0x0020:
            androidx.navigation.NavType<java.lang.String> r1 = StringType
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.navigation.NavType.inferFromValue(java.lang.String):androidx.navigation.NavType");
    }

    @NonNull
    static NavType inferFromValueType(@Nullable Object obj) {
        if (obj instanceof Integer) {
            return IntType;
        }
        if (obj instanceof int[]) {
            return IntArrayType;
        }
        if (obj instanceof Long) {
            return LongType;
        }
        if (obj instanceof long[]) {
            return LongArrayType;
        }
        if (obj instanceof Float) {
            return FloatType;
        }
        if (obj instanceof float[]) {
            return FloatArrayType;
        }
        if (obj instanceof Boolean) {
            return BoolType;
        }
        if (obj instanceof boolean[]) {
            return BoolArrayType;
        }
        if ((obj instanceof String) || obj == null) {
            return StringType;
        }
        if (obj instanceof String[]) {
            return StringArrayType;
        }
        if (obj.getClass().isArray() && Parcelable.class.isAssignableFrom(obj.getClass().getComponentType())) {
            return new ParcelableArrayType(obj.getClass().getComponentType());
        }
        if (obj.getClass().isArray() && Serializable.class.isAssignableFrom(obj.getClass().getComponentType())) {
            return new SerializableArrayType(obj.getClass().getComponentType());
        }
        if (obj instanceof Parcelable) {
            return new ParcelableType(obj.getClass());
        }
        if (obj instanceof Enum) {
            return new EnumType(obj.getClass());
        }
        if (obj instanceof Serializable) {
            return new SerializableType(obj.getClass());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Object of type ");
        sb.append(obj.getClass().getName());
        sb.append(" is not supported for navigation arguments.");
        throw new IllegalArgumentException(sb.toString());
    }
}
