package com.leanplum.internal;

import java.util.Map;

public abstract class BaseActionContext {
    protected Map<String, Object> args;
    private boolean isPreview = false;
    protected boolean isRooted = true;
    protected String messageId = null;
    protected String originalMessageId = null;
    protected int priority;

    public BaseActionContext(String str, String str2) {
        this.messageId = str;
        this.originalMessageId = str2;
    }

    /* access modifiers changed from: 0000 */
    public void setIsRooted(boolean z) {
        this.isRooted = z;
    }

    /* access modifiers changed from: 0000 */
    public void setIsPreview(boolean z) {
        this.isPreview = z;
    }

    /* access modifiers changed from: 0000 */
    public boolean isPreview() {
        return this.isPreview;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public String getOriginalMessageId() {
        return this.originalMessageId;
    }

    public int getPriority() {
        return this.priority;
    }

    public Map<String, Object> getArgs() {
        return this.args;
    }
}
