package com.leanplum.annotations;

import android.text.TextUtils;
import com.leanplum.Var;
import com.leanplum.callbacks.VariableCallback;
import com.leanplum.internal.Constants.Kinds;
import com.leanplum.internal.Log;
import com.microsoft.appcenter.ingestion.models.properties.BooleanTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.DoubleTypedProperty;
import com.microsoft.appcenter.ingestion.models.properties.LongTypedProperty;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class Parser {
    private static <T> void defineVariable(Object obj, String str, T t, String str2, final Field field) {
        final Var define = Var.define(str, t, str2);
        final boolean z = obj != null;
        final WeakReference weakReference = new WeakReference(obj);
        define.addValueChangedHandler(new VariableCallback<T>() {
            public void handle(Var<T> var) {
                String str = "Leanplum";
                Object obj = weakReference.get();
                if (!z || obj != null) {
                    Field field = field;
                    if (field != null) {
                        try {
                            boolean isAccessible = field.isAccessible();
                            if (!isAccessible) {
                                field.setAccessible(true);
                            }
                            field.set(obj, define.value());
                            if (!isAccessible) {
                                field.setAccessible(false);
                            }
                        } catch (IllegalArgumentException e) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Invalid value ");
                            sb.append(define.value());
                            sb.append(" for field ");
                            sb.append(define.name());
                            Log.m280e(str, sb.toString(), e);
                        } catch (IllegalAccessException e2) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Error setting value for field ");
                            sb2.append(define.name());
                            Log.m280e(str, sb2.toString(), e2);
                        }
                        return;
                    }
                }
                define.removeValueChangedHandler(this);
            }
        });
    }

    private static void defineFileVariable(Object obj, String str, String str2, final Field field) {
        final Var defineFile = Var.defineFile(str, str2);
        final boolean z = obj != null;
        final WeakReference weakReference = new WeakReference(obj);
        defineFile.addFileReadyHandler(new VariableCallback<String>() {
            public void handle(Var<String> var) {
                String str = "Leanplum";
                Object obj = weakReference.get();
                if (!z || obj != null) {
                    Field field = field;
                    if (field != null) {
                        try {
                            boolean isAccessible = field.isAccessible();
                            if (!isAccessible) {
                                field.setAccessible(true);
                            }
                            field.set(obj, defineFile.fileValue());
                            if (!isAccessible) {
                                field.setAccessible(false);
                            }
                        } catch (IllegalArgumentException e) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Invalid value ");
                            sb.append((String) defineFile.value());
                            sb.append(" for field ");
                            sb.append(defineFile.name());
                            Log.m280e(str, sb.toString(), e);
                        } catch (IllegalAccessException e2) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Error setting value for field ");
                            sb2.append(defineFile.name());
                            Log.m280e(str, sb2.toString(), e2);
                        }
                        return;
                    }
                }
                defineFile.removeFileReadyHandler(this);
            }
        });
    }

    public static void parseVariables(Object... objArr) {
        try {
            for (Object obj : objArr) {
                parseVariablesHelper(obj, obj.getClass());
            }
        } catch (Throwable th) {
            Log.m280e("Leanplum", "Error parsing variables", th);
        }
    }

    public static void parseVariablesForClasses(Class<?>... clsArr) {
        try {
            for (Class<?> parseVariablesHelper : clsArr) {
                parseVariablesHelper(null, parseVariablesHelper);
            }
        } catch (Throwable th) {
            Log.m280e("Leanplum", "Error parsing variables", th);
        }
    }

    private static void parseVariablesHelper(Object obj, Class<?> cls) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields;
        boolean z;
        String str;
        String str2;
        String str3;
        for (Field field : cls.getFields()) {
            if (field.isAnnotationPresent(Variable.class)) {
                Variable variable = (Variable) field.getAnnotation(Variable.class);
                str = variable.group();
                str2 = variable.name();
                z = false;
            } else if (field.isAnnotationPresent(File.class)) {
                File file = (File) field.getAnnotation(File.class);
                str = file.group();
                str2 = file.name();
                z = true;
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = field.getName();
            }
            if (!TextUtils.isEmpty(str)) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(".");
                sb.append(str2);
                str2 = sb.toString();
            }
            Class type = field.getType();
            String cls2 = type.toString();
            boolean equals = cls2.equals("int");
            String str4 = Kinds.INT;
            if (equals) {
                defineVariable(obj, str2, Integer.valueOf(field.getInt(obj)), str4, field);
            } else if (cls2.equals("byte")) {
                defineVariable(obj, str2, Byte.valueOf(field.getByte(obj)), str4, field);
            } else if (cls2.equals("short")) {
                defineVariable(obj, str2, Short.valueOf(field.getShort(obj)), str4, field);
            } else if (cls2.equals(LongTypedProperty.TYPE)) {
                defineVariable(obj, str2, Long.valueOf(field.getLong(obj)), str4, field);
            } else if (cls2.equals("char")) {
                defineVariable(obj, str2, Character.valueOf(field.getChar(obj)), str4, field);
            } else {
                String str5 = Kinds.FLOAT;
                if (cls2.equals(str5)) {
                    defineVariable(obj, str2, Float.valueOf(field.getFloat(obj)), str5, field);
                } else if (cls2.equals(DoubleTypedProperty.TYPE)) {
                    defineVariable(obj, str2, Double.valueOf(field.getDouble(obj)), str5, field);
                } else if (cls2.equals(BooleanTypedProperty.TYPE)) {
                    defineVariable(obj, str2, Boolean.valueOf(field.getBoolean(obj)), Kinds.BOOLEAN, field);
                } else {
                    String str6 = "Variable ";
                    if (type.isPrimitive()) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str6);
                        sb2.append(str2);
                        sb2.append(" is an unsupported primitive type.");
                        Log.m280e(sb2.toString());
                    } else if (type.isArray()) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str6);
                        sb3.append(str2);
                        sb3.append(" should be a List instead of an Array.");
                        Log.m280e(sb3.toString());
                    } else if (type.isAssignableFrom(List.class)) {
                        defineVariable(obj, str2, field.get(obj), Kinds.ARRAY, field);
                    } else if (type.isAssignableFrom(Map.class)) {
                        defineVariable(obj, str2, field.get(obj), Kinds.DICTIONARY, field);
                    } else {
                        Object obj2 = field.get(obj);
                        if (obj2 == null) {
                            str3 = null;
                        } else {
                            str3 = obj2.toString();
                        }
                        if (z) {
                            defineFileVariable(obj, str2, str3, field);
                        } else {
                            defineVariable(obj, str2, str3, "string", field);
                        }
                    }
                }
            }
        }
    }
}
