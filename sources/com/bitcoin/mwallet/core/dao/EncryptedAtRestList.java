package com.bitcoin.mwallet.core.dao;

import com.leanplum.internal.Constants.Kinds;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/dao/EncryptedAtRestList;", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "list", "", "(Ljava/util/List;)V", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: EncryptedAtRestList.kt */
public final class EncryptedAtRestList extends ArrayList<String> {
    public EncryptedAtRestList(@NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, Kinds.ARRAY);
        super(list);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof String) {
            return contains((String) obj);
        }
        return false;
    }

    public /* bridge */ boolean contains(String str) {
        return super.contains(str);
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof String) {
            return indexOf((String) obj);
        }
        return -1;
    }

    public /* bridge */ int indexOf(String str) {
        return super.indexOf(str);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof String) {
            return lastIndexOf((String) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(String str) {
        return super.lastIndexOf(str);
    }

    public final /* bridge */ String remove(int i) {
        return removeAt(i);
    }

    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof String) {
            return remove((String) obj);
        }
        return false;
    }

    public /* bridge */ boolean remove(String str) {
        return super.remove(str);
    }

    public /* bridge */ String removeAt(int i) {
        return (String) super.remove(i);
    }

    public final /* bridge */ int size() {
        return getSize();
    }
}
