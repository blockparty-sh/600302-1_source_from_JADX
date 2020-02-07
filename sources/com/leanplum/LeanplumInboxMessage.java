package com.leanplum;

import android.net.Uri;
import android.text.TextUtils;
import com.leanplum.internal.CollectionUtil;
import com.leanplum.internal.Constants;
import com.leanplum.internal.Constants.Keys;
import com.leanplum.internal.Constants.Methods;
import com.leanplum.internal.Constants.Params;
import com.leanplum.internal.Constants.Values;
import com.leanplum.internal.FileManager;
import com.leanplum.internal.FileManager.DownloadFileResult;
import com.leanplum.internal.JsonConverter;
import com.leanplum.internal.Log;
import com.leanplum.internal.RequestOld;
import com.leanplum.internal.Util;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class LeanplumInboxMessage {
    private ActionContext context;
    private Long deliveryTimestamp;
    private Long expirationTimestamp;
    private String imageFileName;
    private String imageUrl;
    private boolean isRead;
    private String messageId;

    private LeanplumInboxMessage(String str, Long l, Long l2, boolean z, ActionContext actionContext) {
        this.messageId = str;
        this.deliveryTimestamp = l;
        this.expirationTimestamp = l2;
        this.isRead = z;
        this.context = actionContext;
        this.imageUrl = actionContext.stringNamed(Keys.INBOX_IMAGE);
        String str2 = this.imageUrl;
        if (str2 != null) {
            try {
                this.imageFileName = Util.sha256(str2);
            } catch (Exception unused) {
            }
        }
    }

    public String getImageFilePath() {
        String fileValue = FileManager.fileValue(this.imageFileName);
        if (FileManager.fileExistsAtPath(fileValue)) {
            return new File(fileValue).getAbsolutePath();
        }
        if (!LeanplumInbox.getInstance().isInboxImagePrefetchingEnabled()) {
            Log.m284w("Inbox Message image path is null because you're calling disableImagePrefetching. Consider using imageURL method or remove disableImagePrefetching.");
        }
        return null;
    }

    public Uri getImageUrl() {
        String fileValue = FileManager.fileValue(this.imageFileName);
        if (FileManager.fileExistsAtPath(fileValue)) {
            return Uri.fromFile(new File(fileValue));
        }
        if (TextUtils.isEmpty(this.imageUrl)) {
            return null;
        }
        return Uri.parse(this.imageUrl);
    }

    public JSONObject getData() {
        try {
            return JsonConverter.mapToJsonObject((Map) CollectionUtil.uncheckedCast(getContext().objectNamed(Keys.DATA)));
        } catch (Throwable unused) {
            Log.m284w("Unable to parse JSONObject for Data field of inbox message.");
            return null;
        }
    }

    public String getMessageId() {
        return this.messageId;
    }

    public String getTitle() {
        return this.context.stringNamed(Keys.TITLE);
    }

    public String getSubtitle() {
        return this.context.stringNamed(Keys.SUBTITLE);
    }

    public Date getDeliveryTimestamp() {
        Long l = this.deliveryTimestamp;
        if (l == null) {
            return null;
        }
        return new Date(l.longValue());
    }

    public Date getExpirationTimestamp() {
        Long l = this.expirationTimestamp;
        if (l == null) {
            return null;
        }
        return new Date(l.longValue());
    }

    public boolean isRead() {
        return this.isRead;
    }

    public void read() {
        try {
            if (!Constants.isNoop()) {
                if (!this.isRead) {
                    setIsRead(true);
                    LeanplumInbox.getInstance().updateUnreadCount(LeanplumInbox.getInstance().unreadCount() - 1);
                    HashMap hashMap = new HashMap();
                    hashMap.put(Params.INBOX_MESSAGE_ID, this.messageId);
                    RequestOld.post(Methods.MARK_INBOX_MESSAGE_AS_READ, hashMap).send();
                }
                this.context.runTrackedActionNamed(Values.DEFAULT_PUSH_ACTION);
            }
        } catch (Throwable th) {
            Util.handleException(th);
        }
    }

    public void remove() {
        try {
            LeanplumInbox.getInstance().removeMessage(this.messageId);
        } catch (Throwable th) {
            Util.handleException(th);
        }
    }

    static LeanplumInboxMessage createFromJsonMap(String str, Map<String, Object> map) {
        Map map2 = (Map) CollectionUtil.uncheckedCast(map.get(Keys.MESSAGE_DATA));
        Long l = (Long) CollectionUtil.uncheckedCast(map.get(Keys.DELIVERY_TIMESTAMP));
        Long l2 = (Long) CollectionUtil.uncheckedCast(map.get(Keys.EXPIRATION_TIMESTAMP));
        Boolean bool = (Boolean) CollectionUtil.uncheckedCast(map.get(Keys.IS_READ));
        return constructMessage(str, l, l2, bool != null ? bool.booleanValue() : false, map2);
    }

    static LeanplumInboxMessage constructMessage(String str, Long l, Long l2, boolean z, Map<String, Object> map) {
        if (!isValidMessageId(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Malformed inbox messageId: ");
            sb.append(str);
            Log.m280e(sb.toString());
            return null;
        }
        ActionContext actionContext = new ActionContext((String) map.get(Values.ACTION_ARG), map, str.split("##")[0]);
        actionContext.preventRealtimeUpdating();
        actionContext.update();
        LeanplumInboxMessage leanplumInboxMessage = new LeanplumInboxMessage(str, l, l2, z, actionContext);
        return leanplumInboxMessage;
    }

    /* access modifiers changed from: 0000 */
    public boolean downloadImageIfPrefetchingEnabled() {
        boolean z = false;
        if (!LeanplumInbox.isInboxImagePrefetchingEnabled) {
            return false;
        }
        if (!TextUtils.isEmpty(this.imageUrl) && !LeanplumInbox.downloadedImageUrls.contains(this.imageUrl)) {
            String str = this.imageFileName;
            String str2 = this.imageUrl;
            DownloadFileResult maybeDownloadFile = FileManager.maybeDownloadFile(true, str, str2, str2, null);
            LeanplumInbox.downloadedImageUrls.add(this.imageUrl);
            if (DownloadFileResult.DOWNLOADING == maybeDownloadFile) {
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public Map<String, Object> toJsonMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(Keys.DELIVERY_TIMESTAMP, this.deliveryTimestamp);
        hashMap.put(Keys.EXPIRATION_TIMESTAMP, this.expirationTimestamp);
        hashMap.put(Keys.MESSAGE_DATA, actionArgs());
        hashMap.put(Keys.IS_READ, Boolean.valueOf(isRead()));
        return hashMap;
    }

    /* access modifiers changed from: 0000 */
    public boolean isActive() {
        if (this.expirationTimestamp == null) {
            return true;
        }
        return new Date().before(new Date(this.expirationTimestamp.longValue()));
    }

    private static boolean isValidMessageId(String str) {
        return str.split("##").length == 2;
    }

    /* access modifiers changed from: 0000 */
    public ActionContext getContext() {
        return this.context;
    }

    private Map<String, Object> actionArgs() {
        return this.context.getArgs();
    }

    private void setIsRead(boolean z) {
        this.isRead = z;
    }
}
