package com.leanplum;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.leanplum.callbacks.InboxChangedCallback;
import com.leanplum.callbacks.InboxSyncedCallback;
import com.leanplum.callbacks.VariablesChangedCallback;
import com.leanplum.internal.AESCrypt;
import com.leanplum.internal.CollectionUtil;
import com.leanplum.internal.Constants;
import com.leanplum.internal.Constants.Defaults;
import com.leanplum.internal.Constants.Keys;
import com.leanplum.internal.Constants.Methods;
import com.leanplum.internal.Constants.Params;
import com.leanplum.internal.JsonConverter;
import com.leanplum.internal.Log;
import com.leanplum.internal.OsHandler;
import com.leanplum.internal.RequestOld;
import com.leanplum.internal.RequestOld.ErrorCallback;
import com.leanplum.internal.RequestOld.ResponseCallback;
import com.leanplum.internal.Util;
import com.leanplum.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONObject;

public class LeanplumInbox {
    static Set<String> downloadedImageUrls;
    private static LeanplumInbox instance = new LeanplumInbox();
    static boolean isInboxImagePrefetchingEnabled = true;
    private final List<InboxChangedCallback> changedCallbacks;
    private volatile boolean didLoad;
    private volatile Map<String, LeanplumInboxMessage> messages;
    private final List<InboxSyncedCallback> syncedCallbacks;
    private volatile int unreadCount;
    private final Object updatingLock;

    private LeanplumInbox() {
        this.didLoad = false;
        this.updatingLock = new Object();
        this.unreadCount = 0;
        this.messages = new HashMap();
        this.didLoad = false;
        this.changedCallbacks = new ArrayList();
        this.syncedCallbacks = new ArrayList();
        downloadedImageUrls = new HashSet();
    }

    public static void disableImagePrefetching() {
        isInboxImagePrefetchingEnabled = false;
    }

    public int count() {
        return this.messages.size();
    }

    public int unreadCount() {
        return this.unreadCount;
    }

    public List<String> messagesIds() {
        ArrayList arrayList = new ArrayList(this.messages.keySet());
        try {
            Collections.sort(arrayList, new Comparator<String>() {
                public int compare(String str, String str2) {
                    LeanplumInboxMessage messageForId = LeanplumInbox.this.messageForId(str);
                    if (messageForId == null) {
                        return -1;
                    }
                    LeanplumInboxMessage messageForId2 = LeanplumInbox.this.messageForId(str2);
                    if (messageForId2 == null) {
                        return 1;
                    }
                    Date deliveryTimestamp = messageForId.getDeliveryTimestamp();
                    if (deliveryTimestamp == null) {
                        return -1;
                    }
                    Date deliveryTimestamp2 = messageForId2.getDeliveryTimestamp();
                    if (deliveryTimestamp2 == null) {
                        return 1;
                    }
                    return deliveryTimestamp.compareTo(deliveryTimestamp2);
                }
            });
        } catch (Throwable th) {
            Util.handleException(th);
        }
        return arrayList;
    }

    public List<LeanplumInboxMessage> allMessages() {
        return allMessages(new ArrayList());
    }

    public List<LeanplumInboxMessage> unreadMessages() {
        return unreadMessages(new ArrayList());
    }

    public LeanplumInboxMessage messageForId(String str) {
        return (LeanplumInboxMessage) this.messages.get(str);
    }

    public void addChangedHandler(InboxChangedCallback inboxChangedCallback) {
        synchronized (this.changedCallbacks) {
            this.changedCallbacks.add(inboxChangedCallback);
        }
        if (this.didLoad) {
            inboxChangedCallback.inboxChanged();
        }
    }

    public void removeChangedHandler(InboxChangedCallback inboxChangedCallback) {
        synchronized (this.changedCallbacks) {
            this.changedCallbacks.remove(inboxChangedCallback);
        }
    }

    public void addSyncedHandler(InboxSyncedCallback inboxSyncedCallback) {
        synchronized (this.syncedCallbacks) {
            this.syncedCallbacks.add(inboxSyncedCallback);
        }
    }

    public void removeSyncedHandler(InboxSyncedCallback inboxSyncedCallback) {
        synchronized (this.syncedCallbacks) {
            this.syncedCallbacks.remove(inboxSyncedCallback);
        }
    }

    static LeanplumInbox getInstance() {
        return instance;
    }

    /* access modifiers changed from: 0000 */
    public boolean isInboxImagePrefetchingEnabled() {
        return isInboxImagePrefetchingEnabled;
    }

    /* access modifiers changed from: 0000 */
    public void updateUnreadCount(int i) {
        this.unreadCount = i;
        save();
        triggerChanged();
    }

    /* access modifiers changed from: 0000 */
    public void update(Map<String, LeanplumInboxMessage> map, int i, boolean z) {
        try {
            synchronized (this.updatingLock) {
                this.unreadCount = i;
                if (map != null) {
                    this.messages = map;
                }
            }
            this.didLoad = true;
            if (z) {
                save();
            }
            triggerChanged();
        } catch (Throwable th) {
            Util.handleException(th);
        }
    }

    /* access modifiers changed from: 0000 */
    public void removeMessage(String str) {
        int i = this.unreadCount;
        LeanplumInboxMessage messageForId = messageForId(str);
        if (messageForId != null && !messageForId.isRead()) {
            i--;
        }
        this.messages.remove(str);
        update(this.messages, i, true);
        if (!Constants.isNoop()) {
            HashMap hashMap = new HashMap();
            hashMap.put(Params.INBOX_MESSAGE_ID, str);
            RequestOld.post(Methods.DELETE_INBOX_MESSAGE, hashMap).send();
        }
    }

    /* access modifiers changed from: 0000 */
    public void triggerChanged() {
        synchronized (this.changedCallbacks) {
            for (InboxChangedCallback post : this.changedCallbacks) {
                OsHandler.getInstance().post(post);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void triggerInboxSyncedWithStatus(boolean z) {
        synchronized (this.changedCallbacks) {
            for (InboxSyncedCallback inboxSyncedCallback : this.syncedCallbacks) {
                inboxSyncedCallback.setSuccess(z);
                OsHandler.getInstance().post(inboxSyncedCallback);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void load() {
        int i;
        if (!Constants.isNoop()) {
            SharedPreferences sharedPreferences = Leanplum.getContext().getSharedPreferences(Defaults.LEANPLUM, 0);
            if (RequestOld.token() == null) {
                update(new HashMap(), 0, false);
                return;
            }
            String decodePreference = new AESCrypt(RequestOld.appId(), RequestOld.token()).decodePreference(sharedPreferences, Defaults.INBOX_KEY, "{}");
            Map fromJson = JsonConverter.fromJson(decodePreference);
            HashMap hashMap = new HashMap();
            if (fromJson == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Could not parse newsfeed string: ");
                sb.append(decodePreference);
                Log.m280e(sb.toString());
                i = 0;
            } else {
                i = 0;
                for (Entry entry : fromJson.entrySet()) {
                    String str = (String) entry.getKey();
                    LeanplumInboxMessage createFromJsonMap = LeanplumInboxMessage.createFromJsonMap(str, (Map) CollectionUtil.uncheckedCast(entry.getValue()));
                    if (createFromJsonMap != null && createFromJsonMap.isActive()) {
                        hashMap.put(str, createFromJsonMap);
                        if (!createFromJsonMap.isRead()) {
                            i++;
                        }
                    }
                }
            }
            update(hashMap, i, false);
        }
    }

    /* access modifiers changed from: 0000 */
    public void save() {
        if (!Constants.isNoop() && RequestOld.token() != null) {
            Editor edit = Leanplum.getContext().getSharedPreferences(Defaults.LEANPLUM, 0).edit();
            HashMap hashMap = new HashMap();
            for (Entry entry : this.messages.entrySet()) {
                hashMap.put((String) entry.getKey(), ((LeanplumInboxMessage) entry.getValue()).toJsonMap());
            }
            edit.putString(Defaults.INBOX_KEY, new AESCrypt(RequestOld.appId(), RequestOld.token()).encrypt(JsonConverter.toJson(hashMap)));
            SharedPreferencesUtil.commitChanges(edit);
        }
    }

    /* access modifiers changed from: 0000 */
    public void downloadMessages() {
        if (!Constants.isNoop()) {
            RequestOld post = RequestOld.post(Methods.GET_INBOX_MESSAGES, null);
            post.onResponse(new ResponseCallback() {
                public void response(JSONObject jSONObject) {
                    String str = Keys.EXPIRATION_TIMESTAMP;
                    if (jSONObject == null) {
                        try {
                            Log.m280e("No inbox response received from the server.");
                        } catch (Throwable th) {
                            LeanplumInbox.this.triggerInboxSyncedWithStatus(false);
                            Util.handleException(th);
                        }
                    } else {
                        JSONObject optJSONObject = jSONObject.optJSONObject("newsfeedMessages");
                        if (optJSONObject == null) {
                            Log.m280e("No inbox messages found in the response from the server.", jSONObject);
                            return;
                        }
                        final HashMap hashMap = new HashMap();
                        Boolean valueOf = Boolean.valueOf(false);
                        Iterator keys = optJSONObject.keys();
                        final int i = 0;
                        while (keys.hasNext()) {
                            String str2 = (String) keys.next();
                            JSONObject jSONObject2 = optJSONObject.getJSONObject(str2);
                            Map mapFromJson = JsonConverter.mapFromJson(jSONObject2.getJSONObject(Keys.MESSAGE_DATA).getJSONObject("vars"));
                            Long valueOf2 = Long.valueOf(jSONObject2.getLong(Keys.DELIVERY_TIMESTAMP));
                            Long l = null;
                            if (jSONObject2.opt(str) != null) {
                                l = Long.valueOf(jSONObject2.getLong(str));
                            }
                            boolean z = jSONObject2.getBoolean(Keys.IS_READ);
                            LeanplumInboxMessage constructMessage = LeanplumInboxMessage.constructMessage(str2, valueOf2, l, z, mapFromJson);
                            if (constructMessage != null) {
                                valueOf = Boolean.valueOf(valueOf.booleanValue() | constructMessage.downloadImageIfPrefetchingEnabled());
                                if (!z) {
                                    i++;
                                }
                                hashMap.put(str2, constructMessage);
                            }
                        }
                        if (!valueOf.booleanValue()) {
                            LeanplumInbox.this.update(hashMap, i, true);
                            LeanplumInbox.this.triggerInboxSyncedWithStatus(true);
                            return;
                        }
                        Leanplum.addOnceVariablesChangedAndNoDownloadsPendingHandler(new VariablesChangedCallback() {
                            public void variablesChanged() {
                                LeanplumInbox.this.update(hashMap, i, true);
                                LeanplumInbox.this.triggerInboxSyncedWithStatus(true);
                            }
                        });
                    }
                }
            });
            post.onError(new ErrorCallback() {
                public void error(Exception exc) {
                    LeanplumInbox.this.triggerInboxSyncedWithStatus(false);
                }
            });
            post.sendIfConnected();
        }
    }

    private List<LeanplumInboxMessage> allMessages(List<LeanplumInboxMessage> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        try {
            for (String messageForId : messagesIds()) {
                LeanplumInboxMessage messageForId2 = messageForId(messageForId);
                if (messageForId2 != null) {
                    list.add(messageForId2);
                }
            }
        } catch (Throwable th) {
            Util.handleException(th);
        }
        Leanplum.countAggregator().incrementCount("all_messages_inbox");
        return list;
    }

    private List<LeanplumInboxMessage> unreadMessages(List<LeanplumInboxMessage> list) {
        if (list == null) {
            list = new ArrayList<>();
        }
        for (LeanplumInboxMessage leanplumInboxMessage : allMessages(null)) {
            if (!leanplumInboxMessage.isRead()) {
                list.add(leanplumInboxMessage);
            }
        }
        return list;
    }
}
