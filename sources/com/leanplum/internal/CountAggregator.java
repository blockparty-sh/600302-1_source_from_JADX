package com.leanplum.internal;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.leanplum.internal.Constants.Methods;
import com.leanplum.internal.Constants.Params;
import com.leanplum.internal.Constants.Values;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class CountAggregator {
    private final Map<String, Integer> counts = new HashMap();
    private Set<String> enabledCounters = new HashSet();

    public void setEnabledCounters(Set<String> set) {
        this.enabledCounters = set;
    }

    public void incrementCount(@NonNull String str) {
        incrementCount(str, 1);
    }

    public void incrementCount(@NonNull String str, int i) {
        if (this.enabledCounters.contains(str)) {
            Integer valueOf = Integer.valueOf(0);
            if (this.counts.containsKey(str)) {
                valueOf = (Integer) this.counts.get(str);
            }
            this.counts.put(str, Integer.valueOf(valueOf.intValue() + i));
        }
    }

    @VisibleForTesting
    public Map<String, Integer> getAndClearCounts() {
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.counts);
        this.counts.clear();
        return hashMap;
    }

    @VisibleForTesting
    public Map<String, Object> makeParams(@NonNull String str, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", Values.SDK_COUNT);
        hashMap.put("name", str);
        hashMap.put(Params.COUNT, Integer.valueOf(i));
        return hashMap;
    }

    public void sendAllCounts() {
        for (Entry entry : getAndClearCounts().entrySet()) {
            try {
                RequestOld.post(Methods.LOG, makeParams((String) entry.getKey(), ((Integer) entry.getValue()).intValue())).sendEventually();
            } catch (Throwable th) {
                Log.e("Leanplum", "Unable to send count.", th);
            }
        }
    }

    public Map<String, Integer> getCounts() {
        return this.counts;
    }
}
