package com.bitcoin.mwallet.core.preferences;

import com.leanplum.internal.Constants.Keys;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/preferences/Language;", "", "locale", "Ljava/util/Locale;", "(Ljava/lang/String;ILjava/util/Locale;)V", "getLocale", "()Ljava/util/Locale;", "English", "Spanish", "French", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: LanguagePreference.kt */
public enum Language {
    English(new Locale(LocaleCode.ENGLISH)),
    Spanish(new Locale(LocaleCode.SPANISH)),
    French(new Locale(LocaleCode.FRENCH));
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    @NotNull
    public static final Language DEFAULT = null;
    @NotNull
    private final Locale locale;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/preferences/Language$Companion;", "", "()V", "DEFAULT", "Lcom/bitcoin/mwallet/core/preferences/Language;", "getDEFAULT", "()Lcom/bitcoin/mwallet/core/preferences/Language;", "fromLanguageString", "name", "", "fromLocale", "locale", "Ljava/util/Locale;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: LanguagePreference.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Language getDEFAULT() {
            return Language.DEFAULT;
        }

        @NotNull
        public final Language fromLocale(@NotNull Locale locale) {
            Language language;
            Intrinsics.checkParameterIsNotNull(locale, Keys.LOCALE);
            Language[] values = Language.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    language = null;
                    break;
                }
                language = values[i];
                if (Intrinsics.areEqual((Object) language.getLocale().getLanguage(), (Object) locale.getLanguage())) {
                    break;
                }
                i++;
            }
            return language != null ? language : getDEFAULT();
        }

        @NotNull
        public final Language fromLanguageString(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "name");
            return Language.valueOf(str);
        }
    }

    private Language(Locale locale2) {
        this.locale = locale2;
    }

    @NotNull
    public final Locale getLocale() {
        return this.locale;
    }

    static {
        Companion = new Companion(null);
        DEFAULT = English;
    }
}
