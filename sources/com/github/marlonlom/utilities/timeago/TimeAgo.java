package com.github.marlonlom.utilities.timeago;

import com.github.marlonlom.utilities.timeago.TimeAgoMessages.Builder;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00032\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, mo37405d2 = {"Lcom/github/marlonlom/utilities/timeago/TimeAgo;", "", "()V", "Companion", "DistancePredicate", "Periods", "ta_library"}, mo37406k = 1, mo37407mv = {1, 1, 11})
/* compiled from: TimeAgo.kt */
public final class TimeAgo {
    public static final Companion Companion = new Companion(null);

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00060\u0004j\u0002`\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0002J(\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001a\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\u0013"}, mo37405d2 = {"Lcom/github/marlonlom/utilities/timeago/TimeAgo$Companion;", "", "()V", "buildTimeagoText", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "resources", "Lcom/github/marlonlom/utilities/timeago/TimeAgoMessages;", "dim", "", "getTimeDistanceInMinutes", "time", "handlePeriodKeyAsPlural", "", "periodKey", "pluralKey", "value", "", "using", "ta_library"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: TimeAgo.kt */
    public static final class Companion {
        @JvmStatic
        @NotNull
        @JvmOverloads
        public final String using(long j) {
            return using$default(this, j, null, 2, null);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        @JvmOverloads
        public static /* bridge */ /* synthetic */ String using$default(Companion companion, long j, TimeAgoMessages timeAgoMessages, int i, Object obj) {
            if ((i & 2) != 0) {
                timeAgoMessages = new Builder().defaultLocale().build();
            }
            return companion.using(j, timeAgoMessages);
        }

        @JvmStatic
        @NotNull
        @JvmOverloads
        public final String using(long j, @NotNull TimeAgoMessages timeAgoMessages) {
            Intrinsics.checkParameterIsNotNull(timeAgoMessages, "resources");
            Companion companion = this;
            String sb = companion.buildTimeagoText(timeAgoMessages, companion.getTimeDistanceInMinutes(j)).toString();
            Intrinsics.checkExpressionValueIsNotNull(sb, "timeAgo.toString()");
            return sb;
        }

        private final StringBuilder buildTimeagoText(TimeAgoMessages timeAgoMessages, long j) {
            String str;
            String str2;
            StringBuilder sb = new StringBuilder();
            Periods findByDistanceMinutes = Periods.Companion.findByDistanceMinutes(j);
            if (findByDistanceMinutes != null) {
                String propertyKey = findByDistanceMinutes.getPropertyKey();
                String str3 = "ml.timeago.oneday.future";
                switch (findByDistanceMinutes) {
                    case XMINUTES_PAST:
                        sb.append(timeAgoMessages.getPropertyValue(propertyKey, Long.valueOf(j)));
                        break;
                    case XHOURS_PAST:
                        sb.append(handlePeriodKeyAsPlural(timeAgoMessages, "ml.timeago.aboutanhour.past", propertyKey, Math.round((float) (j / ((long) 60)))));
                        break;
                    case XDAYS_PAST:
                        sb.append(handlePeriodKeyAsPlural(timeAgoMessages, "ml.timeago.oneday.past", propertyKey, Math.round((float) (j / ((long) 1440)))));
                        break;
                    case XMONTHS_PAST:
                        sb.append(handlePeriodKeyAsPlural(timeAgoMessages, "ml.timeago.aboutamonth.past", propertyKey, Math.round((float) (j / ((long) 43200)))));
                        break;
                    case XYEARS_PAST:
                        sb.append(timeAgoMessages.getPropertyValue(propertyKey, Integer.valueOf(Math.round((float) (j / ((long) 525600))))));
                        break;
                    case XMINUTES_FUTURE:
                        sb.append(timeAgoMessages.getPropertyValue(propertyKey, Float.valueOf(Math.abs((float) j))));
                        break;
                    case XHOURS_FUTURE:
                        int abs = Math.abs(Math.round(((float) j) / 60.0f));
                        if (abs == 24) {
                            str = timeAgoMessages.getPropertyValue(str3);
                        } else {
                            str = handlePeriodKeyAsPlural(timeAgoMessages, "ml.timeago.aboutanhour.future", propertyKey, abs);
                        }
                        sb.append(str);
                        break;
                    case XDAYS_FUTURE:
                        sb.append(handlePeriodKeyAsPlural(timeAgoMessages, str3, propertyKey, Math.abs(Math.round(((float) j) / 1440.0f))));
                        break;
                    case XMONTHS_FUTURE:
                        int abs2 = Math.abs(Math.round(((float) j) / 43200.0f));
                        if (abs2 == 12) {
                            str2 = timeAgoMessages.getPropertyValue("ml.timeago.aboutayear.future");
                        } else {
                            str2 = handlePeriodKeyAsPlural(timeAgoMessages, "ml.timeago.aboutamonth.future", propertyKey, abs2);
                        }
                        sb.append(str2);
                        break;
                    case XYEARS_FUTURE:
                        sb.append(timeAgoMessages.getPropertyValue(propertyKey, Integer.valueOf(Math.abs(Math.round(((float) j) / 525600.0f)))));
                        break;
                    default:
                        sb.append(timeAgoMessages.getPropertyValue(propertyKey));
                        break;
                }
            }
            return sb;
        }

        private final String handlePeriodKeyAsPlural(TimeAgoMessages timeAgoMessages, String str, String str2, int i) {
            if (i == 1) {
                return timeAgoMessages.getPropertyValue(str);
            }
            return timeAgoMessages.getPropertyValue(str2, Integer.valueOf(i));
        }

        private final long getTimeDistanceInMinutes(long j) {
            return (long) Math.round((float) (((System.currentTimeMillis() - j) / ((long) 1000)) / ((long) 60)));
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\bb\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, mo37405d2 = {"Lcom/github/marlonlom/utilities/timeago/TimeAgo$DistancePredicate;", "", "validateDistanceMinutes", "", "distance", "", "ta_library"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: TimeAgo.kt */
    private interface DistancePredicate {
        boolean validateDistanceMinutes(long j);
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\b\u0001\u0018\u0000 \"2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\"B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!¨\u0006#"}, mo37405d2 = {"Lcom/github/marlonlom/utilities/timeago/TimeAgo$Periods;", "", "propertyKey", "", "predicate", "Lcom/github/marlonlom/utilities/timeago/TimeAgo$DistancePredicate;", "(Ljava/lang/String;ILjava/lang/String;Lcom/github/marlonlom/utilities/timeago/TimeAgo$DistancePredicate;)V", "getPropertyKey", "()Ljava/lang/String;", "NOW", "ONEMINUTE_PAST", "XMINUTES_PAST", "ABOUTANHOUR_PAST", "XHOURS_PAST", "ONEDAY_PAST", "XDAYS_PAST", "ABOUTAMONTH_PAST", "XMONTHS_PAST", "ABOUTAYEAR_PAST", "OVERAYEAR_PAST", "ALMOSTTWOYEARS_PAST", "XYEARS_PAST", "ONEMINUTE_FUTURE", "XMINUTES_FUTURE", "ABOUTANHOUR_FUTURE", "XHOURS_FUTURE", "ONEDAY_FUTURE", "XDAYS_FUTURE", "ABOUTAMONTH_FUTURE", "XMONTHS_FUTURE", "ABOUTAYEAR_FUTURE", "OVERAYEAR_FUTURE", "ALMOSTTWOYEARS_FUTURE", "XYEARS_FUTURE", "Companion", "ta_library"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: TimeAgo.kt */
    private enum Periods {
        NOW("ml.timeago.now", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j == 0;
            }
        }),
        ONEMINUTE_PAST("ml.timeago.oneminute.past", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j == 1;
            }
        }),
        XMINUTES_PAST("ml.timeago.xminutes.past", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j >= ((long) 2) && j <= ((long) 44);
            }
        }),
        ABOUTANHOUR_PAST("ml.timeago.aboutanhour.past", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j >= ((long) 45) && j <= ((long) 89);
            }
        }),
        XHOURS_PAST("ml.timeago.xhours.past", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j >= ((long) 90) && j <= ((long) 1439);
            }
        }),
        ONEDAY_PAST("ml.timeago.oneday.past", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j >= ((long) 1440) && j <= ((long) 2519);
            }
        }),
        XDAYS_PAST("ml.timeago.xdays.past", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j >= ((long) 2520) && j <= ((long) 43199);
            }
        }),
        ABOUTAMONTH_PAST("ml.timeago.aboutamonth.past", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j >= ((long) 43200) && j <= ((long) 86399);
            }
        }),
        XMONTHS_PAST("ml.timeago.xmonths.past", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j >= ((long) 86400) && j <= ((long) 525599);
            }
        }),
        ABOUTAYEAR_PAST("ml.timeago.aboutayear.past", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j >= ((long) 525600) && j <= ((long) 655199);
            }
        }),
        OVERAYEAR_PAST("ml.timeago.overayear.past", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j >= ((long) 655200) && j <= ((long) 914399);
            }
        }),
        ALMOSTTWOYEARS_PAST("ml.timeago.almosttwoyears.past", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j >= ((long) 914400) && j <= ((long) 1051199);
            }
        }),
        XYEARS_PAST("ml.timeago.xyears.past", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return Math.round((float) (j / ((long) 525600))) > 1;
            }
        }),
        ONEMINUTE_FUTURE("ml.timeago.oneminute.future", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return ((int) j) == -1;
            }
        }),
        XMINUTES_FUTURE("ml.timeago.xminutes.future", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j <= ((long) -2) && j >= ((long) -44);
            }
        }),
        ABOUTANHOUR_FUTURE("ml.timeago.aboutanhour.future", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j <= ((long) -45) && j >= ((long) -89);
            }
        }),
        XHOURS_FUTURE("ml.timeago.xhours.future", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j <= ((long) -90) && j >= ((long) -1439);
            }
        }),
        ONEDAY_FUTURE("ml.timeago.oneday.future", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j <= ((long) -1440) && j >= ((long) -2519);
            }
        }),
        XDAYS_FUTURE("ml.timeago.xdays.future", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j <= ((long) -2520) && j >= ((long) -43199);
            }
        }),
        ABOUTAMONTH_FUTURE("ml.timeago.aboutamonth.future", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j <= ((long) -43200) && j >= ((long) -86399);
            }
        }),
        XMONTHS_FUTURE("ml.timeago.xmonths.future", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j <= ((long) -86400) && j >= ((long) -525599);
            }
        }),
        ABOUTAYEAR_FUTURE("ml.timeago.aboutayear.future", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j <= ((long) -525600) && j >= ((long) -655199);
            }
        }),
        OVERAYEAR_FUTURE("ml.timeago.overayear.future", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j <= ((long) -655200) && j >= ((long) -914399);
            }
        }),
        ALMOSTTWOYEARS_FUTURE("ml.timeago.almosttwoyears.future", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return j <= ((long) -914400) && j >= ((long) -1051199);
            }
        }),
        XYEARS_FUTURE("ml.timeago.xyears.future", new DistancePredicate() {
            public boolean validateDistanceMinutes(long j) {
                return Math.round((float) (j / ((long) 525600))) < -1;
            }
        });
        
        public static final Companion Companion = null;
        /* access modifiers changed from: private */
        public final DistancePredicate predicate;
        @NotNull
        private final String propertyKey;

        @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/github/marlonlom/utilities/timeago/TimeAgo$Periods$Companion;", "", "()V", "findByDistanceMinutes", "Lcom/github/marlonlom/utilities/timeago/TimeAgo$Periods;", "distanceMinutes", "", "ta_library"}, mo37406k = 1, mo37407mv = {1, 1, 11})
        /* compiled from: TimeAgo.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @Nullable
            public final Periods findByDistanceMinutes(long j) {
                Periods[] values;
                for (Periods periods : Periods.values()) {
                    if (periods.predicate.validateDistanceMinutes(j)) {
                        return periods;
                    }
                }
                return null;
            }
        }

        protected Periods(@NotNull String str, @NotNull DistancePredicate distancePredicate) {
            Intrinsics.checkParameterIsNotNull(str, "propertyKey");
            Intrinsics.checkParameterIsNotNull(distancePredicate, "predicate");
            this.propertyKey = str;
            this.predicate = distancePredicate;
        }

        @NotNull
        public final String getPropertyKey() {
            return this.propertyKey;
        }

        static {
            Companion = new Companion(null);
        }
    }

    @JvmStatic
    @NotNull
    @JvmOverloads
    public static final String using(long j) {
        return Companion.using$default(Companion, j, null, 2, null);
    }

    @JvmStatic
    @NotNull
    @JvmOverloads
    public static final String using(long j, @NotNull TimeAgoMessages timeAgoMessages) {
        return Companion.using(j, timeAgoMessages);
    }

    private TimeAgo() {
    }
}
