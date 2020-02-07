package com.leanplum;

import com.leanplum.internal.FileManager;
import com.leanplum.internal.Socket;
import com.leanplum.internal.Util;
import com.leanplum.internal.VarCache;
import java.util.List;
import java.util.Map;

public class UIEditorBridge {
    public static void setInterfaceUpdateBlock(CacheUpdateBlock cacheUpdateBlock) {
        VarCache.onInterfaceUpdate(cacheUpdateBlock);
    }

    public static void setEventsUpdateBlock(CacheUpdateBlock cacheUpdateBlock) {
        VarCache.onEventsUpdate(cacheUpdateBlock);
    }

    public static List<Map<String, Object>> getUpdateRuleDiffs() {
        return VarCache.getUpdateRuleDiffs();
    }

    public static List<Map<String, Object>> getEventRuleDiffs() {
        return VarCache.getEventRuleDiffs();
    }

    public static boolean isSocketConnected() {
        return Socket.getInstance() != null && Socket.getInstance().isConnected();
    }

    public static <T> void socketSendEvent(String str, Map<String, T> map) {
        if (Socket.getInstance() != null && str != null) {
            Socket.getInstance().sendEvent(str, map);
        }
    }

    public static String fileRelativeToDocuments(String str) {
        return FileManager.fileRelativeToDocuments(str);
    }

    public static void handleException(Throwable th) {
        Util.handleException(th);
    }
}
