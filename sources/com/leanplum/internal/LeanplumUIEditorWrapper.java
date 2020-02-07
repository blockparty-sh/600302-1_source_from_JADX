package com.leanplum.internal;

import android.app.Activity;
import com.leanplum.Leanplum;
import com.leanplum.LeanplumEditorMode;
import com.leanplum.LeanplumUIEditor;
import com.leanplum.internal.Constants.ClassUtil;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LeanplumUIEditorWrapper implements LeanplumUIEditor {
    private static LeanplumUIEditorWrapper instance;
    private static LeanplumUIEditor interfaceEditorSingleton;

    protected LeanplumUIEditorWrapper() {
    }

    static {
        Class cls;
        Method method;
        try {
            cls = Class.forName(ClassUtil.UI_INTERFACE_EDITOR);
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls != null) {
            try {
                method = cls.getMethod("getInstance", new Class[0]);
            } catch (Throwable th) {
                Util.handleException(th);
                method = null;
            }
            if (method != null) {
                try {
                    interfaceEditorSingleton = (LeanplumUIEditor) method.invoke(null, new Object[0]);
                    if (interfaceEditorSingleton != null) {
                        interfaceEditorSingleton.allowInterfaceEditing(Boolean.valueOf(Constants.isDevelopmentModeEnabled));
                    }
                } catch (IllegalAccessException e) {
                    Util.handleException(e);
                } catch (InvocationTargetException e2) {
                    Util.handleException(e2);
                } catch (Throwable th2) {
                    Util.handleException(th2);
                }
            }
        }
    }

    public static LeanplumUIEditorWrapper getInstance() {
        if (instance == null) {
            instance = new LeanplumUIEditorWrapper();
        }
        return instance;
    }

    public static boolean isUIEditorAvailable() {
        return interfaceEditorSingleton != null;
    }

    public void allowInterfaceEditing(Boolean bool) {
        LeanplumUIEditor leanplumUIEditor = interfaceEditorSingleton;
        if (leanplumUIEditor != null) {
            leanplumUIEditor.allowInterfaceEditing(bool);
        }
    }

    public void applyInterfaceEdits(Activity activity) {
        LeanplumUIEditor leanplumUIEditor = interfaceEditorSingleton;
        if (leanplumUIEditor != null && activity != null) {
            leanplumUIEditor.applyInterfaceEdits(activity);
        }
    }

    public void startUpdating() {
        LeanplumUIEditor leanplumUIEditor = interfaceEditorSingleton;
        if (leanplumUIEditor != null) {
            leanplumUIEditor.startUpdating();
        }
        Leanplum.countAggregator().incrementCount("start_updating_ui");
    }

    public void stopUpdating() {
        LeanplumUIEditor leanplumUIEditor = interfaceEditorSingleton;
        if (leanplumUIEditor != null) {
            leanplumUIEditor.stopUpdating();
        }
    }

    public void sendUpdate() {
        LeanplumUIEditor leanplumUIEditor = interfaceEditorSingleton;
        if (leanplumUIEditor != null) {
            leanplumUIEditor.sendUpdate();
        }
        Leanplum.countAggregator().incrementCount("send_update_ui");
    }

    public void sendUpdateDelayed(int i) {
        LeanplumUIEditor leanplumUIEditor = interfaceEditorSingleton;
        if (leanplumUIEditor != null) {
            leanplumUIEditor.sendUpdateDelayed(i);
        }
    }

    public void sendUpdateDelayedDefault() {
        LeanplumUIEditor leanplumUIEditor = interfaceEditorSingleton;
        if (leanplumUIEditor != null) {
            leanplumUIEditor.sendUpdateDelayedDefault();
        }
    }

    public LeanplumEditorMode getMode() {
        LeanplumUIEditor leanplumUIEditor = interfaceEditorSingleton;
        if (leanplumUIEditor != null) {
            return leanplumUIEditor.getMode();
        }
        return null;
    }

    public void setMode(LeanplumEditorMode leanplumEditorMode) {
        LeanplumUIEditor leanplumUIEditor = interfaceEditorSingleton;
        if (leanplumUIEditor != null) {
            leanplumUIEditor.setMode(leanplumEditorMode);
        }
    }
}
