package org.bitcoinj.utils;

import com.google.protobuf.ByteString;
import java.util.Map;
import javax.annotation.Nullable;

public interface TaggableObject {
    ByteString getTag(String str);

    Map<String, ByteString> getTags();

    @Nullable
    ByteString maybeGetTag(String str);

    void setTag(String str, ByteString byteString);
}
