package com.leanplum.internal;

import androidx.annotation.VisibleForTesting;
import com.leanplum.Leanplum;
import java.util.HashSet;
import java.util.Set;

public class FeatureFlagManager {
    public static final String FEATURE_FLAG_REQUEST_REFACTOR = "request_refactor";
    public static final FeatureFlagManager INSTANCE = new FeatureFlagManager();
    private Set<String> enabledFeatureFlags = new HashSet();

    @VisibleForTesting
    FeatureFlagManager() {
    }

    public void setEnabledFeatureFlags(Set<String> set) {
        this.enabledFeatureFlags = set;
    }

    public Boolean isFeatureFlagEnabled(String str) {
        Leanplum.countAggregator().incrementCount("is_feature_flag_enabled");
        return Boolean.valueOf(this.enabledFeatureFlags.contains(str));
    }
}
