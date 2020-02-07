package com.leanplum;

import android.text.TextUtils;
import com.leanplum.callbacks.VariableCallback;
import com.leanplum.internal.Constants;
import com.leanplum.internal.Constants.Kinds;
import com.leanplum.internal.Constants.Values;
import com.leanplum.internal.FileManager;
import com.leanplum.internal.FileManager.DownloadFileResult;
import com.leanplum.internal.LeanplumInternal;
import com.leanplum.internal.Log;
import com.leanplum.internal.OsHandler;
import com.leanplum.internal.Util;
import com.leanplum.internal.VarCache;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Var<T> {
    private static boolean printedCallbackWarning;
    /* access modifiers changed from: private */
    public byte[] data;
    private T defaultValue;
    private boolean fileIsPending;
    private final List<VariableCallback<T>> fileReadyHandlers = new ArrayList();
    private boolean hadStarted;
    /* access modifiers changed from: private */
    public String hash;
    /* access modifiers changed from: private */
    public boolean isAsset;
    private boolean isInternal;
    public boolean isResource;
    private String kind;
    private String name;
    private String[] nameComponents;
    private Double numberValue;
    private int overrideResId;
    /* access modifiers changed from: private */
    public int size;
    public String stringValue;
    private T value;
    private final List<VariableCallback<T>> valueChangedHandlers = new ArrayList();
    private boolean valueIsInAssets = false;

    private interface VarInitializer<T> {
        void init(Var<T> var);
    }

    private void warnIfNotStarted() {
        if (!this.isInternal && !Leanplum.hasStarted() && !printedCallbackWarning) {
            StringBuilder sb = new StringBuilder();
            sb.append("Leanplum hasn't finished retrieving values from the server. You should use a callback to make sure the value for '");
            sb.append(this.name);
            sb.append("' is ready. Otherwise, your app may not use the most up-to-date value.");
            Log.m284w(sb.toString());
            printedCallbackWarning = true;
        }
    }

    private static <T> Var<T> define(String str, T t, String str2, VarInitializer<T> varInitializer) {
        String str3 = null;
        if (TextUtils.isEmpty(str)) {
            Log.m280e("Empty name parameter provided.");
            return null;
        }
        Var<T> variable = VarCache.getVariable(str);
        if (variable != null) {
            return variable;
        }
        boolean hasCalledStart = LeanplumInternal.hasCalledStart();
        String str4 = Values.RESOURCES_VARIABLE;
        if (hasCalledStart && !str.startsWith(str4)) {
            StringBuilder sb = new StringBuilder();
            sb.append("You should not create new variables after calling start (name=");
            sb.append(str);
            sb.append(")");
            Log.m284w(sb.toString());
        }
        Var<T> var = new Var<>();
        try {
            var.name = str;
            var.nameComponents = VarCache.getNameComponents(str);
            var.defaultValue = t;
            var.value = t;
            var.kind = str2;
            if (str.startsWith(str4)) {
                var.isInternal = true;
            }
            if (varInitializer != null) {
                varInitializer.init(var);
            }
            var.cacheComputedValues();
            VarCache.registerVariable(var);
            if ("file".equals(var.kind)) {
                String str5 = var.stringValue;
                if (var.defaultValue() != null) {
                    str3 = var.defaultValue().toString();
                }
                VarCache.registerFile(str5, str3, var.defaultStream(), var.isResource, var.hash, var.size);
            }
            var.update();
        } catch (Throwable th) {
            Util.handleException(th);
        }
        return var;
    }

    public static <T> Var<T> define(String str, T t) {
        return define(str, t, VarCache.kindFromValue(t), null);
    }

    public static <T> Var<T> define(String str, T t, String str2) {
        return define(str, t, str2, null);
    }

    public static Var<Integer> defineColor(String str, int i) {
        return define(str, Integer.valueOf(i), Kinds.COLOR, null);
    }

    public static Var<String> defineFile(String str, String str2) {
        return define(str, str2, "file", null);
    }

    public static Var<String> defineAsset(String str, String str2) {
        return define(str, str2, "file", new VarInitializer<String>() {
            public void init(Var<String> var) {
                var.isAsset = true;
            }
        });
    }

    public static Var<String> defineResource(String str, int i) {
        return define(str, Util.generateResourceNameFromId(i), "file", new VarInitializer<String>() {
            public void init(Var<String> var) {
                var.isResource = true;
            }
        });
    }

    public static Var<String> defineResource(String str, String str2, final int i, final String str3, final byte[] bArr) {
        return define(str, str2, "file", new VarInitializer<String>() {
            public void init(Var<String> var) {
                var.isResource = true;
                var.size = i;
                var.hash = str3;
                var.data = bArr;
            }
        });
    }

    protected Var() {
    }

    public String name() {
        return this.name;
    }

    public String[] nameComponents() {
        return this.nameComponents;
    }

    public String kind() {
        return this.kind;
    }

    public T defaultValue() {
        return this.defaultValue;
    }

    public T value() {
        warnIfNotStarted();
        return this.value;
    }

    public int overrideResId() {
        return this.overrideResId;
    }

    public void setOverrideResId(int i) {
        this.overrideResId = i;
    }

    private void cacheComputedValues() {
        T t = this.value;
        if (t instanceof String) {
            this.stringValue = (String) t;
            try {
                this.numberValue = Double.valueOf(this.stringValue);
            } catch (NumberFormatException unused) {
                this.numberValue = null;
            }
        } else if (t instanceof Number) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(this.value);
            this.stringValue = sb.toString();
            this.numberValue = Double.valueOf(((Number) this.value).doubleValue());
            T t2 = this.defaultValue;
            if (t2 instanceof Byte) {
                this.value = Byte.valueOf(((Number) this.value).byteValue());
            } else if (t2 instanceof Short) {
                this.value = Short.valueOf(((Number) this.value).shortValue());
            } else if (t2 instanceof Integer) {
                this.value = Integer.valueOf(((Number) this.value).intValue());
            } else if (t2 instanceof Long) {
                this.value = Long.valueOf(((Number) this.value).longValue());
            } else if (t2 instanceof Float) {
                this.value = Float.valueOf(((Number) this.value).floatValue());
            } else if (t2 instanceof Double) {
                this.value = Double.valueOf(((Number) this.value).doubleValue());
            } else if (t2 instanceof Character) {
                this.value = Character.valueOf((char) ((Number) this.value).intValue());
            }
        } else if (t == null || (t instanceof Iterable) || (t instanceof Map)) {
            this.stringValue = null;
            this.numberValue = null;
        } else {
            this.stringValue = t.toString();
            this.numberValue = null;
        }
    }

    public void update() {
        T t = this.value;
        this.value = VarCache.getMergedValueFromComponentArray(this.nameComponents);
        if (this.value != null || t != null) {
            T t2 = this.value;
            if (t2 == null || t == null || !t2.equals(t) || !this.hadStarted) {
                cacheComputedValues();
                String str = "file";
                if (VarCache.silent() && this.name.startsWith(Values.RESOURCES_VARIABLE) && str.equals(this.kind) && !this.fileIsPending) {
                    triggerFileIsReady();
                }
                if (!VarCache.silent()) {
                    if (Leanplum.hasStarted()) {
                        triggerValueChanged();
                    }
                    if (str.equals(this.kind)) {
                        if (!Constants.isNoop()) {
                            DownloadFileResult maybeDownloadFile = FileManager.maybeDownloadFile(this.isResource, this.stringValue, (String) this.defaultValue, null, new Runnable() {
                                public void run() {
                                    Var.this.triggerFileIsReady();
                                }
                            });
                            this.valueIsInAssets = false;
                            if (maybeDownloadFile == DownloadFileResult.DOWNLOADING) {
                                this.fileIsPending = true;
                            } else if (maybeDownloadFile == DownloadFileResult.EXISTS_IN_ASSETS) {
                                this.valueIsInAssets = true;
                            }
                        }
                        if (Leanplum.hasStarted() && !this.fileIsPending) {
                            triggerFileIsReady();
                        }
                    }
                    if (Leanplum.hasStarted()) {
                        this.hadStarted = true;
                    }
                }
            }
        }
    }

    private void triggerValueChanged() {
        synchronized (this.valueChangedHandlers) {
            for (VariableCallback variableCallback : this.valueChangedHandlers) {
                variableCallback.setVariable(this);
                OsHandler.getInstance().post(variableCallback);
            }
        }
    }

    public void addValueChangedHandler(VariableCallback<T> variableCallback) {
        if (variableCallback == null) {
            Log.m280e("Invalid handler parameter provided.");
            return;
        }
        synchronized (this.valueChangedHandlers) {
            this.valueChangedHandlers.add(variableCallback);
        }
        if (Leanplum.hasStarted()) {
            variableCallback.handle(this);
        }
    }

    public void removeValueChangedHandler(VariableCallback<T> variableCallback) {
        synchronized (this.valueChangedHandlers) {
            this.valueChangedHandlers.remove(variableCallback);
        }
    }

    /* access modifiers changed from: private */
    public void triggerFileIsReady() {
        synchronized (this.fileReadyHandlers) {
            this.fileIsPending = false;
            for (VariableCallback variableCallback : this.fileReadyHandlers) {
                variableCallback.setVariable(this);
                OsHandler.getInstance().post(variableCallback);
            }
        }
    }

    public void addFileReadyHandler(VariableCallback<T> variableCallback) {
        if (variableCallback == null) {
            Log.m280e("Invalid handler parameter provided.");
            return;
        }
        synchronized (this.fileReadyHandlers) {
            this.fileReadyHandlers.add(variableCallback);
        }
        if (Leanplum.hasStarted() && !this.fileIsPending) {
            variableCallback.handle(this);
        }
    }

    public void removeFileReadyHandler(VariableCallback<T> variableCallback) {
        if (variableCallback == null) {
            Log.m280e("Invalid handler parameter provided.");
            return;
        }
        synchronized (this.fileReadyHandlers) {
            this.fileReadyHandlers.remove(variableCallback);
        }
    }

    public String fileValue() {
        try {
            warnIfNotStarted();
            if ("file".equals(this.kind)) {
                return FileManager.fileValue(this.stringValue, (String) this.defaultValue, Boolean.valueOf(this.valueIsInAssets));
            }
        } catch (Throwable th) {
            Util.handleException(th);
        }
        return null;
    }

    public Object objectForKeyPath(Object... objArr) {
        try {
            warnIfNotStarted();
            ArrayList arrayList = new ArrayList();
            Collections.addAll(arrayList, this.nameComponents);
            if (objArr != null && objArr.length > 0) {
                Collections.addAll(arrayList, objArr);
            }
            return VarCache.getMergedValueFromComponentArray(arrayList.toArray(new Object[arrayList.size()]));
        } catch (Throwable th) {
            Util.handleException(th);
            return null;
        }
    }

    public int count() {
        try {
            warnIfNotStarted();
            Object mergedValueFromComponentArray = VarCache.getMergedValueFromComponentArray(this.nameComponents);
            if (mergedValueFromComponentArray instanceof List) {
                return ((List) mergedValueFromComponentArray).size();
            }
            LeanplumInternal.maybeThrowException(new UnsupportedOperationException("This variable is not a list."));
            return 0;
        } catch (Throwable th) {
            Util.handleException(th);
            return 0;
        }
    }

    public Number numberValue() {
        warnIfNotStarted();
        return this.numberValue;
    }

    public String stringValue() {
        warnIfNotStarted();
        return this.stringValue;
    }

    public InputStream stream() {
        try {
            if (!"file".equals(this.kind)) {
                return null;
            }
            warnIfNotStarted();
            InputStream stream = FileManager.stream(this.isResource, Boolean.valueOf(this.isAsset), Boolean.valueOf(this.valueIsInAssets), fileValue(), (String) this.defaultValue, this.data);
            return stream == null ? defaultStream() : stream;
        } catch (Throwable th) {
            Util.handleException(th);
            return null;
        }
    }

    private InputStream defaultStream() {
        try {
            if (!"file".equals(this.kind)) {
                return null;
            }
            return FileManager.stream(this.isResource, Boolean.valueOf(this.isAsset), Boolean.valueOf(this.valueIsInAssets), (String) this.defaultValue, (String) this.defaultValue, this.data);
        } catch (Throwable th) {
            Util.handleException(th);
            return null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Var(");
        sb.append(this.name);
        sb.append(")=");
        sb.append(this.value);
        return sb.toString();
    }
}
