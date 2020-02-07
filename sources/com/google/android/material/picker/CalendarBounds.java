package com.google.android.material.picker;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import androidx.annotation.RestrictTo;
import androidx.annotation.RestrictTo.Scope;
import java.util.Arrays;

@RestrictTo({Scope.LIBRARY_GROUP})
public final class CalendarBounds implements Parcelable {
    public static final Creator<CalendarBounds> CREATOR = new Creator<CalendarBounds>() {
        public CalendarBounds createFromParcel(Parcel parcel) {
            return CalendarBounds.create((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()));
        }

        public CalendarBounds[] newArray(int i) {
            return new CalendarBounds[i];
        }
    };
    private final Month current;
    private final Month end;
    private final Month start;

    public int describeContents() {
        return 0;
    }

    private CalendarBounds(Month month, Month month2, Month month3) {
        this.start = month;
        this.end = month2;
        this.current = month3;
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        } else if (month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
    }

    public static CalendarBounds create(Month month, Month month2, Month month3) {
        return new CalendarBounds(month, month2, month3);
    }

    public static CalendarBounds create(Month month, Month month2) {
        Month month3 = Month.today();
        if (month2.compareTo(month3) < 0 || month3.compareTo(month) < 0) {
            return new CalendarBounds(month, month2, month);
        }
        return new CalendarBounds(month, month2, Month.today());
    }

    public Month getStart() {
        return this.start;
    }

    public Month getEnd() {
        return this.end;
    }

    public Month getCurrent() {
        return this.current;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CalendarBounds)) {
            return false;
        }
        CalendarBounds calendarBounds = (CalendarBounds) obj;
        if (!this.start.equals(calendarBounds.start) || !this.end.equals(calendarBounds.end) || !this.current.equals(calendarBounds.current)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.start, this.end, this.current});
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.start, 0);
        parcel.writeParcelable(this.end, 0);
        parcel.writeParcelable(this.current, 0);
    }
}
