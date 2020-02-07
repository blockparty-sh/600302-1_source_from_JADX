package com.bitcoin.mwallet.app.flows.settings.language;

import com.bitcoin.mwallet.core.preferences.Language;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/language/LanguageInteractor;", "", "()V", "getAvailableLanguages", "", "Lcom/bitcoin/mwallet/core/preferences/Language;", "()[Lcom/bitcoin/mwallet/core/preferences/Language;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LanguageInteractor.kt */
public final class LanguageInteractor {
    @NotNull
    public final Language[] getAvailableLanguages() {
        Language[] values = Language.values();
        Collection arrayList = new ArrayList(values.length);
        for (Language add : values) {
            arrayList.add(add);
        }
        Object[] array = ((List) arrayList).toArray(new Language[0]);
        if (array != null) {
            return (Language[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
