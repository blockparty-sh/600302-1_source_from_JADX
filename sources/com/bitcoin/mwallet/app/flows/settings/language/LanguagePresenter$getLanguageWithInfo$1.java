package com.bitcoin.mwallet.app.flows.settings.language;

import com.bitcoin.mwallet.core.preferences.Language;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0018\u00010\u00012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\n¢\u0006\u0004\b\t\u0010\n"}, mo37405d2 = {"<anonymous>", "", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/preferences/Language;", "", "languages", "", "selectedLanguage", "", "invoke", "([Lcom/bitcoin/mwallet/core/preferences/Language;Ljava/lang/String;)Ljava/util/List;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: LanguagePresenter.kt */
final class LanguagePresenter$getLanguageWithInfo$1 extends Lambda implements Function2<Language[], String, List<? extends Pair<? extends Language, ? extends Boolean>>> {
    public static final LanguagePresenter$getLanguageWithInfo$1 INSTANCE = new LanguagePresenter$getLanguageWithInfo$1();

    LanguagePresenter$getLanguageWithInfo$1() {
        super(2);
    }

    @Nullable
    public final List<Pair<Language, Boolean>> invoke(@Nullable Language[] languageArr, @Nullable String str) {
        if (languageArr == null) {
            return null;
        }
        Collection arrayList = new ArrayList(languageArr.length);
        for (Language language : languageArr) {
            arrayList.add(new Pair(language, Boolean.valueOf(Intrinsics.areEqual((Object) str, (Object) language.name()))));
        }
        return (List) arrayList;
    }
}
