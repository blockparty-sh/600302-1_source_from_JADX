package com.leanplum.internal;

import com.leanplum.Leanplum;
import java.util.Map;

public class RequestFactory {
    private static final String API_METHOD_ADVANCE = "advance";
    private static final String API_METHOD_DELETE_INBOX_MESSAGE = "deleteNewsfeedMessage";
    private static final String API_METHOD_DOWNLOAD_FILE = "downloadFile";
    private static final String API_METHOD_GET_INBOX_MESSAGES = "getNewsfeedMessages";
    private static final String API_METHOD_GET_VARS = "getVars";
    private static final String API_METHOD_GET_VIEW_CONTROLLER_VERSIONS_LIST = "getViewControllerVersionsList";
    private static final String API_METHOD_HEARTBEAT = "heartbeat";
    private static final String API_METHOD_LOG = "log";
    private static final String API_METHOD_MARK_INBOX_MESSAGE_AS_READ = "markNewsfeedMessageAsRead";
    private static final String API_METHOD_MULTI = "multi";
    private static final String API_METHOD_PAUSE_SESSION = "pauseSession";
    private static final String API_METHOD_PAUSE_STATE = "pauseState";
    private static final String API_METHOD_REGISTER_FOR_DEVELOPMENT = "registerDevice";
    private static final String API_METHOD_RESTART = "restart";
    private static final String API_METHOD_RESUME_SESSION = "resumeSession";
    private static final String API_METHOD_RESUME_STATE = "resumeState";
    private static final String API_METHOD_SAVE_VIEW_CONTROLLER_IMAGE = "saveInterfaceImage";
    private static final String API_METHOD_SAVE_VIEW_CONTROLLER_VERSION = "saveInterface";
    private static final String API_METHOD_SET_DEVICE_ATTRIBUTES = "setDeviceAttributes";
    private static final String API_METHOD_SET_TRAFFIC_SOURCE_INFO = "setTrafficSourceInfo";
    private static final String API_METHOD_SET_USER_ATTRIBUTES = "setUserAttributes";
    private static final String API_METHOD_SET_VARS = "setVars";
    private static final String API_METHOD_START = "start";
    private static final String API_METHOD_STOP = "stop";
    private static final String API_METHOD_TRACK = "track";
    private static final String API_METHOD_UPLOAD_FILE = "uploadFile";
    public static RequestFactory defaultFactory;
    private CountAggregator countAggregator;
    private FeatureFlagManager featureFlagManager;

    public static synchronized RequestFactory getInstance() {
        RequestFactory requestFactory;
        synchronized (RequestFactory.class) {
            if (defaultFactory == null) {
                defaultFactory = new RequestFactory();
                defaultFactory.countAggregator = Leanplum.countAggregator();
                defaultFactory.featureFlagManager = Leanplum.featureFlagManager();
            }
            requestFactory = defaultFactory;
        }
        return requestFactory;
    }

    public RequestOld createRequest(String str, String str2, Map<String, Object> map) {
        Leanplum.countAggregator().incrementCount("createRequest");
        return new RequestOld(str, str2, map);
    }

    public Requesting startWithParams(Map<String, Object> map) {
        return createPostForApiMethod("start", map);
    }

    public Requesting getVarsWithParams(Map<String, Object> map) {
        return createPostForApiMethod("getVars", map);
    }

    public Requesting setVarsWithParams(Map<String, Object> map) {
        return createPostForApiMethod("setVars", map);
    }

    public Requesting stopWithParams(Map<String, Object> map) {
        return createPostForApiMethod("stop", map);
    }

    public Requesting restartWithParams(Map<String, Object> map) {
        return createPostForApiMethod(API_METHOD_RESTART, map);
    }

    public Requesting trackWithParams(Map<String, Object> map) {
        return createPostForApiMethod("track", map);
    }

    public Requesting advanceWithParams(Map<String, Object> map) {
        return createPostForApiMethod("advance", map);
    }

    public Requesting pauseSessionWithParams(Map<String, Object> map) {
        return createPostForApiMethod("pauseSession", map);
    }

    public Requesting pauseStateWithParams(Map<String, Object> map) {
        return createPostForApiMethod("pauseState", map);
    }

    public Requesting resumeSessionWithParams(Map<String, Object> map) {
        return createPostForApiMethod("resumeSession", map);
    }

    public Requesting resumeStateWithParams(Map<String, Object> map) {
        return createPostForApiMethod("resumeState", map);
    }

    public Requesting multiWithParams(Map<String, Object> map) {
        return createPostForApiMethod("multi", map);
    }

    public Requesting registerDeviceWithParams(Map<String, Object> map) {
        return createPostForApiMethod("registerDevice", map);
    }

    public Requesting setUserAttributesWithParams(Map<String, Object> map) {
        return createPostForApiMethod("setUserAttributes", map);
    }

    public Requesting setDeviceAttributesWithParams(Map<String, Object> map) {
        return createPostForApiMethod("setDeviceAttributes", map);
    }

    public Requesting setTrafficSourceInfoWithParams(Map<String, Object> map) {
        return createPostForApiMethod("setTrafficSourceInfo", map);
    }

    public Requesting uploadFileWithParams(Map<String, Object> map) {
        return createPostForApiMethod("uploadFile", map);
    }

    public Requesting downloadFileWithParams(Map<String, Object> map) {
        return createPostForApiMethod("downloadFile", map);
    }

    public Requesting heartbeatWithParams(Map<String, Object> map) {
        return createPostForApiMethod("heartbeat", map);
    }

    public Requesting saveInterfaceWithParams(Map<String, Object> map) {
        return createPostForApiMethod(API_METHOD_SAVE_VIEW_CONTROLLER_VERSION, map);
    }

    public Requesting saveInterfaceImageWithParams(Map<String, Object> map) {
        return createPostForApiMethod(API_METHOD_SAVE_VIEW_CONTROLLER_IMAGE, map);
    }

    public Requesting getViewControllerVersionsListWithParams(Map<String, Object> map) {
        return createPostForApiMethod(API_METHOD_GET_VIEW_CONTROLLER_VERSIONS_LIST, map);
    }

    public Requesting logWithParams(Map<String, Object> map) {
        return createPostForApiMethod("log", map);
    }

    public Requesting getNewsfeedMessagesWithParams(Map<String, Object> map) {
        return createPostForApiMethod("getNewsfeedMessages", map);
    }

    public Requesting markNewsfeedMessageAsReadWithParams(Map<String, Object> map) {
        return createPostForApiMethod("markNewsfeedMessageAsRead", map);
    }

    public Requesting deleteNewsfeedMessagesWithParams(Map<String, Object> map) {
        return createPostForApiMethod("deleteNewsfeedMessage", map);
    }

    private Requesting createGetForApiMethod(String str, Map<String, Object> map) {
        if (shouldReturnLPRequestClass().booleanValue()) {
            return Request.get(str, map);
        }
        return RequestOld.get(str, map);
    }

    private Requesting createPostForApiMethod(String str, Map<String, Object> map) {
        if (shouldReturnLPRequestClass().booleanValue()) {
            return Request.post(str, map);
        }
        return RequestOld.post(str, map);
    }

    private Boolean shouldReturnLPRequestClass() {
        FeatureFlagManager featureFlagManager2 = Leanplum.featureFlagManager();
        Leanplum.featureFlagManager();
        return featureFlagManager2.isFeatureFlagEnabled(FeatureFlagManager.FEATURE_FLAG_REQUEST_REFACTOR);
    }
}
