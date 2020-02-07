package com.github.marlonlom.utilities.timeago;

import com.leanplum.internal.Constants.Keys;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\u0018\u0000 \f2\u00020\u0001:\u0002\u000b\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J'\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\t\"\u00020\u0001¢\u0006\u0002\u0010\nR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, mo37405d2 = {"Lcom/github/marlonlom/utilities/timeago/TimeAgoMessages;", "", "()V", "bundle", "Ljava/util/ResourceBundle;", "getPropertyValue", "", "property", "values", "", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "Builder", "Companion", "ta_library"}, mo37406k = 1, mo37407mv = {1, 1, 11})
/* compiled from: TimeAgoMessages.kt */
public final class TimeAgoMessages {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    public static final String MESSAGES = MESSAGES;
    /* access modifiers changed from: private */
    public ResourceBundle bundle;

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0000J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u000eR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000f"}, mo37405d2 = {"Lcom/github/marlonlom/utilities/timeago/TimeAgoMessages$Builder;", "", "()V", "innerBundle", "Ljava/util/ResourceBundle;", "getInnerBundle", "()Ljava/util/ResourceBundle;", "setInnerBundle", "(Ljava/util/ResourceBundle;)V", "build", "Lcom/github/marlonlom/utilities/timeago/TimeAgoMessages;", "defaultLocale", "withLocale", "locale", "Ljava/util/Locale;", "ta_library"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: TimeAgoMessages.kt */
    public static final class Builder {
        @Nullable
        private ResourceBundle innerBundle;

        @Nullable
        public final ResourceBundle getInnerBundle() {
            return this.innerBundle;
        }

        public final void setInnerBundle(@Nullable ResourceBundle resourceBundle) {
            this.innerBundle = resourceBundle;
        }

        @NotNull
        public final TimeAgoMessages build() {
            TimeAgoMessages timeAgoMessages = new TimeAgoMessages(null);
            timeAgoMessages.bundle = this.innerBundle;
            return timeAgoMessages;
        }

        @NotNull
        public final Builder defaultLocale() {
            this.innerBundle = ResourceBundle.getBundle(TimeAgoMessages.MESSAGES);
            return this;
        }

        @NotNull
        public final Builder withLocale(@NotNull Locale locale) {
            Intrinsics.checkParameterIsNotNull(locale, Keys.LOCALE);
            this.innerBundle = ResourceBundle.getBundle(TimeAgoMessages.MESSAGES, locale);
            return this;
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo37405d2 = {"Lcom/github/marlonlom/utilities/timeago/TimeAgoMessages$Companion;", "", "()V", "MESSAGES", "", "ta_library"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: TimeAgoMessages.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public /* synthetic */ TimeAgoMessages(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private TimeAgoMessages() {
    }

    @NotNull
    public final String getPropertyValue(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "property");
        ResourceBundle resourceBundle = this.bundle;
        if (resourceBundle == null) {
            Intrinsics.throwNpe();
        }
        String string = resourceBundle.getString(str);
        Intrinsics.checkExpressionValueIsNotNull(string, "bundle!!.getString(property)");
        return string;
    }

    @NotNull
    public final String getPropertyValue(@NotNull String str, @NotNull Object... objArr) {
        Intrinsics.checkParameterIsNotNull(str, "property");
        Intrinsics.checkParameterIsNotNull(objArr, "values");
        String format = MessageFormat.format(getPropertyValue(str), Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "MessageFormat.format(propertyVal, *values)");
        return format;
    }
}
