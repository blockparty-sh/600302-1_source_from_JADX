package com.leanplum;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import com.leanplum.internal.CollectionUtil;
import com.leanplum.internal.FileManager;
import com.leanplum.internal.Log;
import com.leanplum.internal.ResourceQualifiers;
import com.leanplum.internal.ResourceQualifiers.Qualifier;
import com.leanplum.internal.Util;
import com.leanplum.internal.VarCache;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class LeanplumResources extends Resources {
    public LeanplumResources(Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
    }

    /* access modifiers changed from: 0000 */
    public <T> Var<T> getOverrideResource(int i) {
        Qualifier[] values;
        try {
            String resourceEntryName = getResourceEntryName(i);
            String resourceTypeName = getResourceTypeName(i);
            if (FileManager.resources == null) {
                return null;
            }
            HashMap hashMap = (HashMap) CollectionUtil.uncheckedCast(FileManager.resources.objectForKeyPath(new Object[0]));
            HashMap hashMap2 = new HashMap();
            synchronized (VarCache.valuesFromClient) {
                for (String str : hashMap.keySet()) {
                    if (str.toLowerCase().startsWith(resourceTypeName)) {
                        String str2 = null;
                        for (String str3 : ((HashMap) CollectionUtil.uncheckedCast(hashMap.get(str))).keySet()) {
                            String replace = str3.replace("\\.", ".");
                            int lastIndexOf = replace.lastIndexOf(46);
                            if (lastIndexOf >= 0) {
                                replace = replace.substring(0, lastIndexOf);
                            }
                            if (replace.equals(resourceEntryName)) {
                                str2 = str3;
                            }
                        }
                        if (str2 != null) {
                            hashMap2.put(str, str2);
                        }
                    }
                }
            }
            HashMap hashMap3 = new HashMap();
            for (String str4 : hashMap2.keySet()) {
                hashMap3.put(str4, ResourceQualifiers.fromFolder(str4));
            }
            Configuration configuration = getConfiguration();
            DisplayMetrics displayMetrics = getDisplayMetrics();
            Set hashSet = new HashSet();
            for (String str5 : hashMap2.keySet()) {
                ResourceQualifiers resourceQualifiers = (ResourceQualifiers) hashMap3.get(str5);
                for (Qualifier qualifier : resourceQualifiers.qualifiers.keySet()) {
                    if (qualifier.getFilter().isMatch(resourceQualifiers.qualifiers.get(qualifier), configuration, displayMetrics)) {
                        hashSet.add(str5);
                    }
                }
            }
            Set<String> set = hashSet;
            for (Qualifier qualifier2 : Qualifier.values()) {
                HashMap hashMap4 = new HashMap();
                for (String str6 : set) {
                    Object obj = ((ResourceQualifiers) hashMap3.get(str6)).qualifiers.get(qualifier2);
                    if (obj != null) {
                        hashMap4.put(str6, obj);
                    }
                }
                Map bestMatch = qualifier2.getFilter().bestMatch(hashMap4, configuration, displayMetrics);
                if (!bestMatch.isEmpty()) {
                    set = bestMatch.keySet();
                }
            }
            if (!hashMap2.isEmpty()) {
                String str7 = (String) ((Entry) hashMap2.entrySet().iterator().next()).getValue();
                StringBuilder sb = new StringBuilder();
                sb.append("__Android Resources.");
                sb.append(str7);
                sb.append(".");
                sb.append((String) hashMap2.get(str7));
                return VarCache.getVariable(sb.toString());
            }
            return null;
        } catch (Exception e) {
            Log.m280e("Error getting resource", e);
        }
    }

    public Drawable getDrawable(int i) throws NotFoundException {
        try {
            Var overrideResource = getOverrideResource(i);
            if (overrideResource != null) {
                int overrideResId = overrideResource.overrideResId();
                if (overrideResId != 0) {
                    return super.getDrawable(overrideResId);
                }
                if (!overrideResource.stringValue.equals(overrideResource.defaultValue())) {
                    Drawable createFromStream = Drawable.createFromStream(overrideResource.stream(), overrideResource.fileValue());
                    if (createFromStream != null) {
                        return createFromStream;
                    }
                }
            }
        } catch (Throwable th) {
            Util.handleException(th);
        }
        return super.getDrawable(i);
    }
}
