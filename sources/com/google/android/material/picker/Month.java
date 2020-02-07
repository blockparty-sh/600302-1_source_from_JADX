package com.google.android.material.picker;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

final class Month implements Comparable<Month>, Parcelable {
    public static final Creator<Month> CREATOR = new Creator<Month>() {
        public Month createFromParcel(Parcel parcel) {
            return Month.create(parcel.readInt(), parcel.readInt());
        }

        public Month[] newArray(int i) {
            return new Month[i];
        }
    };
    private static final SimpleDateFormat longNameFormat = new SimpleDateFormat("MMMM, yyyy", Locale.getDefault());
    private final Calendar calendar;
    final int daysInMonth = this.calendar.getActualMaximum(5);
    final int daysInWeek = this.calendar.getMaximum(7);
    private final String longName = longNameFormat.format(this.calendar.getTime());
    final int month;
    final int year;

    public int describeContents() {
        return 0;
    }

    private Month(Calendar calendar2) {
        this.calendar = calendar2;
        this.calendar.set(5, 1);
        this.month = calendar2.get(2);
        this.year = calendar2.get(1);
    }

    static Month create(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.clear();
        instance.set(1, i);
        instance.set(2, i2);
        return new Month(instance);
    }

    static Month today() {
        Calendar instance = Calendar.getInstance();
        return create(instance.get(1), instance.get(2));
    }

    /* access modifiers changed from: 0000 */
    public int daysFromStartOfWeekToFirstOfMonth() {
        int firstDayOfWeek = this.calendar.get(7) - this.calendar.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.daysInWeek : firstDayOfWeek;
    }

    /* access modifiers changed from: 0000 */
    public Calendar getDay(int i) {
        Calendar calendar2 = (Calendar) this.calendar.clone();
        calendar2.set(5, i);
        return calendar2;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month2 = (Month) obj;
        if (!(this.month == month2.month && this.year == month2.year)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.month), Integer.valueOf(this.year)});
    }

    public int compareTo(Month month2) {
        return this.calendar.compareTo(month2.calendar);
    }

    /* access modifiers changed from: 0000 */
    public int monthsUntil(Month month2) {
        if (this.calendar instanceof GregorianCalendar) {
            return ((month2.year - this.year) * 12) + (month2.month - this.month);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    /* access modifiers changed from: 0000 */
    public Month monthsLater(int i) {
        Calendar calendar2 = (Calendar) this.calendar.clone();
        calendar2.add(2, i);
        return new Month(calendar2);
    }

    /* access modifiers changed from: 0000 */
    public String getLongName() {
        return this.longName;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.year);
        parcel.writeInt(this.month);
    }
}
