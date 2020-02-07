package com.leanplum.internal;

public class APIConfig {
    private String accessKey;
    private String appId;
    private final CountAggregator countAggregator;
    private final FeatureFlagManager featureFlagManager;
    private String token;

    public APIConfig(FeatureFlagManager featureFlagManager2, CountAggregator countAggregator2) {
        this.featureFlagManager = featureFlagManager2;
        this.countAggregator = countAggregator2;
    }

    public void setAppId(String str, String str2) {
        this.appId = str;
        this.accessKey = str2;
        this.countAggregator.incrementCount("set_app_id");
    }

    public void loadToken(String str) {
        this.token = str;
    }
}
