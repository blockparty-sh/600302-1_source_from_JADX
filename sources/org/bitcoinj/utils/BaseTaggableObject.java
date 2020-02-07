package org.bitcoinj.utils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.protobuf.ByteString;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class BaseTaggableObject implements TaggableObject {
    @Nullable
    protected Map<String, ByteString> tags;

    @Nullable
    public synchronized ByteString maybeGetTag(String str) {
        if (this.tags == null) {
            return null;
        }
        return (ByteString) this.tags.get(str);
    }

    public ByteString getTag(String str) {
        ByteString maybeGetTag = maybeGetTag(str);
        if (maybeGetTag != null) {
            return maybeGetTag;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown tag ");
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }

    public synchronized void setTag(String str, ByteString byteString) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(byteString);
        if (this.tags == null) {
            this.tags = new HashMap();
        }
        this.tags.put(str, byteString);
    }

    public synchronized Map<String, ByteString> getTags() {
        if (this.tags != null) {
            return Maps.newHashMap(this.tags);
        }
        return Maps.newHashMap();
    }
}
