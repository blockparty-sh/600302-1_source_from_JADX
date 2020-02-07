package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "BarcodeCreator")
@Reserved({1})
public class Barcode extends AbstractSafeParcelable {
    public static final int ALL_FORMATS = 0;
    public static final int AZTEC = 4096;
    public static final int CALENDAR_EVENT = 11;
    public static final int CODABAR = 8;
    public static final int CODE_128 = 1;
    public static final int CODE_39 = 2;
    public static final int CODE_93 = 4;
    public static final int CONTACT_INFO = 1;
    public static final Creator<Barcode> CREATOR = new zzb();
    public static final int DATA_MATRIX = 16;
    public static final int DRIVER_LICENSE = 12;
    public static final int EAN_13 = 32;
    public static final int EAN_8 = 64;
    public static final int EMAIL = 2;
    public static final int GEO = 10;
    public static final int ISBN = 3;
    public static final int ITF = 128;
    public static final int PDF417 = 2048;
    public static final int PHONE = 4;
    public static final int PRODUCT = 5;
    public static final int QR_CODE = 256;
    public static final int SMS = 6;
    public static final int TEXT = 7;
    public static final int UPC_A = 512;
    public static final int UPC_E = 1024;
    public static final int URL = 8;
    public static final int WIFI = 9;
    @Field(mo19055id = 13)
    public CalendarEvent calendarEvent;
    @Field(mo19055id = 14)
    public ContactInfo contactInfo;
    @Field(mo19055id = 6)
    public Point[] cornerPoints;
    @Field(mo19055id = 4)
    public String displayValue;
    @Field(mo19055id = 15)
    public DriverLicense driverLicense;
    @Field(mo19055id = 7)
    public Email email;
    @Field(mo19055id = 2)
    public int format;
    @Field(mo19055id = 12)
    public GeoPoint geoPoint;
    @Field(mo19055id = 8)
    public Phone phone;
    @Field(mo19055id = 3)
    public String rawValue;
    @Field(mo19055id = 9)
    public Sms sms;
    @Field(mo19055id = 11)
    public UrlBookmark url;
    @Field(mo19055id = 5)
    public int valueFormat;
    @Field(mo19055id = 10)
    public WiFi wifi;

    @Class(creator = "AddressCreator")
    @Reserved({1})
    public static class Address extends AbstractSafeParcelable {
        public static final Creator<Address> CREATOR = new zza();
        public static final int HOME = 2;
        public static final int UNKNOWN = 0;
        public static final int WORK = 1;
        @Field(mo19055id = 3)
        public String[] addressLines;
        @Field(mo19055id = 2)
        public int type;

        public Address() {
        }

        @Constructor
        public Address(@Param(mo19058id = 2) int i, @Param(mo19058id = 3) String[] strArr) {
            this.type = i;
            this.addressLines = strArr;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 2, this.type);
            SafeParcelWriter.writeStringArray(parcel, 3, this.addressLines, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "CalendarDateTimeCreator")
    @Reserved({1})
    public static class CalendarDateTime extends AbstractSafeParcelable {
        public static final Creator<CalendarDateTime> CREATOR = new zzd();
        @Field(mo19055id = 4)
        public int day;
        @Field(mo19055id = 5)
        public int hours;
        @Field(mo19055id = 8)
        public boolean isUtc;
        @Field(mo19055id = 6)
        public int minutes;
        @Field(mo19055id = 3)
        public int month;
        @Field(mo19055id = 9)
        public String rawValue;
        @Field(mo19055id = 7)
        public int seconds;
        @Field(mo19055id = 2)
        public int year;

        public CalendarDateTime() {
        }

        @Constructor
        public CalendarDateTime(@Param(mo19058id = 2) int i, @Param(mo19058id = 3) int i2, @Param(mo19058id = 4) int i3, @Param(mo19058id = 5) int i4, @Param(mo19058id = 6) int i5, @Param(mo19058id = 7) int i6, @Param(mo19058id = 8) boolean z, @Param(mo19058id = 9) String str) {
            this.year = i;
            this.month = i2;
            this.day = i3;
            this.hours = i4;
            this.minutes = i5;
            this.seconds = i6;
            this.isUtc = z;
            this.rawValue = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 2, this.year);
            SafeParcelWriter.writeInt(parcel, 3, this.month);
            SafeParcelWriter.writeInt(parcel, 4, this.day);
            SafeParcelWriter.writeInt(parcel, 5, this.hours);
            SafeParcelWriter.writeInt(parcel, 6, this.minutes);
            SafeParcelWriter.writeInt(parcel, 7, this.seconds);
            SafeParcelWriter.writeBoolean(parcel, 8, this.isUtc);
            SafeParcelWriter.writeString(parcel, 9, this.rawValue, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "CalendarEventCreator")
    @Reserved({1})
    public static class CalendarEvent extends AbstractSafeParcelable {
        public static final Creator<CalendarEvent> CREATOR = new zze();
        @Field(mo19055id = 3)
        public String description;
        @Field(mo19055id = 8)
        public CalendarDateTime end;
        @Field(mo19055id = 4)
        public String location;
        @Field(mo19055id = 5)
        public String organizer;
        @Field(mo19055id = 7)
        public CalendarDateTime start;
        @Field(mo19055id = 6)
        public String status;
        @Field(mo19055id = 2)
        public String summary;

        public CalendarEvent() {
        }

        @Constructor
        public CalendarEvent(@Param(mo19058id = 2) String str, @Param(mo19058id = 3) String str2, @Param(mo19058id = 4) String str3, @Param(mo19058id = 5) String str4, @Param(mo19058id = 6) String str5, @Param(mo19058id = 7) CalendarDateTime calendarDateTime, @Param(mo19058id = 8) CalendarDateTime calendarDateTime2) {
            this.summary = str;
            this.description = str2;
            this.location = str3;
            this.organizer = str4;
            this.status = str5;
            this.start = calendarDateTime;
            this.end = calendarDateTime2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 2, this.summary, false);
            SafeParcelWriter.writeString(parcel, 3, this.description, false);
            SafeParcelWriter.writeString(parcel, 4, this.location, false);
            SafeParcelWriter.writeString(parcel, 5, this.organizer, false);
            SafeParcelWriter.writeString(parcel, 6, this.status, false);
            SafeParcelWriter.writeParcelable(parcel, 7, this.start, i, false);
            SafeParcelWriter.writeParcelable(parcel, 8, this.end, i, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "ContactInfoCreator")
    @Reserved({1})
    public static class ContactInfo extends AbstractSafeParcelable {
        public static final Creator<ContactInfo> CREATOR = new zzf();
        @Field(mo19055id = 8)
        public Address[] addresses;
        @Field(mo19055id = 6)
        public Email[] emails;
        @Field(mo19055id = 2)
        public PersonName name;
        @Field(mo19055id = 3)
        public String organization;
        @Field(mo19055id = 5)
        public Phone[] phones;
        @Field(mo19055id = 4)
        public String title;
        @Field(mo19055id = 7)
        public String[] urls;

        public ContactInfo() {
        }

        @Constructor
        public ContactInfo(@Param(mo19058id = 2) PersonName personName, @Param(mo19058id = 3) String str, @Param(mo19058id = 4) String str2, @Param(mo19058id = 5) Phone[] phoneArr, @Param(mo19058id = 6) Email[] emailArr, @Param(mo19058id = 7) String[] strArr, @Param(mo19058id = 8) Address[] addressArr) {
            this.name = personName;
            this.organization = str;
            this.title = str2;
            this.phones = phoneArr;
            this.emails = emailArr;
            this.urls = strArr;
            this.addresses = addressArr;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeParcelable(parcel, 2, this.name, i, false);
            SafeParcelWriter.writeString(parcel, 3, this.organization, false);
            SafeParcelWriter.writeString(parcel, 4, this.title, false);
            SafeParcelWriter.writeTypedArray(parcel, 5, this.phones, i, false);
            SafeParcelWriter.writeTypedArray(parcel, 6, this.emails, i, false);
            SafeParcelWriter.writeStringArray(parcel, 7, this.urls, false);
            SafeParcelWriter.writeTypedArray(parcel, 8, this.addresses, i, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "DriverLicenseCreator")
    @Reserved({1})
    public static class DriverLicense extends AbstractSafeParcelable {
        public static final Creator<DriverLicense> CREATOR = new zzg();
        @Field(mo19055id = 8)
        public String addressCity;
        @Field(mo19055id = 9)
        public String addressState;
        @Field(mo19055id = 7)
        public String addressStreet;
        @Field(mo19055id = 10)
        public String addressZip;
        @Field(mo19055id = 14)
        public String birthDate;
        @Field(mo19055id = 2)
        public String documentType;
        @Field(mo19055id = 13)
        public String expiryDate;
        @Field(mo19055id = 3)
        public String firstName;
        @Field(mo19055id = 6)
        public String gender;
        @Field(mo19055id = 12)
        public String issueDate;
        @Field(mo19055id = 15)
        public String issuingCountry;
        @Field(mo19055id = 5)
        public String lastName;
        @Field(mo19055id = 11)
        public String licenseNumber;
        @Field(mo19055id = 4)
        public String middleName;

        public DriverLicense() {
        }

        @Constructor
        public DriverLicense(@Param(mo19058id = 2) String str, @Param(mo19058id = 3) String str2, @Param(mo19058id = 4) String str3, @Param(mo19058id = 5) String str4, @Param(mo19058id = 6) String str5, @Param(mo19058id = 7) String str6, @Param(mo19058id = 8) String str7, @Param(mo19058id = 9) String str8, @Param(mo19058id = 10) String str9, @Param(mo19058id = 11) String str10, @Param(mo19058id = 12) String str11, @Param(mo19058id = 13) String str12, @Param(mo19058id = 14) String str13, @Param(mo19058id = 15) String str14) {
            this.documentType = str;
            this.firstName = str2;
            this.middleName = str3;
            this.lastName = str4;
            this.gender = str5;
            this.addressStreet = str6;
            this.addressCity = str7;
            this.addressState = str8;
            this.addressZip = str9;
            this.licenseNumber = str10;
            this.issueDate = str11;
            this.expiryDate = str12;
            this.birthDate = str13;
            this.issuingCountry = str14;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 2, this.documentType, false);
            SafeParcelWriter.writeString(parcel, 3, this.firstName, false);
            SafeParcelWriter.writeString(parcel, 4, this.middleName, false);
            SafeParcelWriter.writeString(parcel, 5, this.lastName, false);
            SafeParcelWriter.writeString(parcel, 6, this.gender, false);
            SafeParcelWriter.writeString(parcel, 7, this.addressStreet, false);
            SafeParcelWriter.writeString(parcel, 8, this.addressCity, false);
            SafeParcelWriter.writeString(parcel, 9, this.addressState, false);
            SafeParcelWriter.writeString(parcel, 10, this.addressZip, false);
            SafeParcelWriter.writeString(parcel, 11, this.licenseNumber, false);
            SafeParcelWriter.writeString(parcel, 12, this.issueDate, false);
            SafeParcelWriter.writeString(parcel, 13, this.expiryDate, false);
            SafeParcelWriter.writeString(parcel, 14, this.birthDate, false);
            SafeParcelWriter.writeString(parcel, 15, this.issuingCountry, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "EmailCreator")
    @Reserved({1})
    public static class Email extends AbstractSafeParcelable {
        public static final Creator<Email> CREATOR = new zzh();
        public static final int HOME = 2;
        public static final int UNKNOWN = 0;
        public static final int WORK = 1;
        @Field(mo19055id = 3)
        public String address;
        @Field(mo19055id = 5)
        public String body;
        @Field(mo19055id = 4)
        public String subject;
        @Field(mo19055id = 2)
        public int type;

        public Email() {
        }

        @Constructor
        public Email(@Param(mo19058id = 2) int i, @Param(mo19058id = 3) String str, @Param(mo19058id = 4) String str2, @Param(mo19058id = 5) String str3) {
            this.type = i;
            this.address = str;
            this.subject = str2;
            this.body = str3;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 2, this.type);
            SafeParcelWriter.writeString(parcel, 3, this.address, false);
            SafeParcelWriter.writeString(parcel, 4, this.subject, false);
            SafeParcelWriter.writeString(parcel, 5, this.body, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "GeoPointCreator")
    @Reserved({1})
    public static class GeoPoint extends AbstractSafeParcelable {
        public static final Creator<GeoPoint> CREATOR = new zzi();
        @Field(mo19055id = 2)
        public double lat;
        @Field(mo19055id = 3)
        public double lng;

        public GeoPoint() {
        }

        @Constructor
        public GeoPoint(@Param(mo19058id = 2) double d, @Param(mo19058id = 3) double d2) {
            this.lat = d;
            this.lng = d2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeDouble(parcel, 2, this.lat);
            SafeParcelWriter.writeDouble(parcel, 3, this.lng);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "PersonNameCreator")
    @Reserved({1})
    public static class PersonName extends AbstractSafeParcelable {
        public static final Creator<PersonName> CREATOR = new zzj();
        @Field(mo19055id = 5)
        public String first;
        @Field(mo19055id = 2)
        public String formattedName;
        @Field(mo19055id = 7)
        public String last;
        @Field(mo19055id = 6)
        public String middle;
        @Field(mo19055id = 4)
        public String prefix;
        @Field(mo19055id = 3)
        public String pronunciation;
        @Field(mo19055id = 8)
        public String suffix;

        public PersonName() {
        }

        @Constructor
        public PersonName(@Param(mo19058id = 2) String str, @Param(mo19058id = 3) String str2, @Param(mo19058id = 4) String str3, @Param(mo19058id = 5) String str4, @Param(mo19058id = 6) String str5, @Param(mo19058id = 7) String str6, @Param(mo19058id = 8) String str7) {
            this.formattedName = str;
            this.pronunciation = str2;
            this.prefix = str3;
            this.first = str4;
            this.middle = str5;
            this.last = str6;
            this.suffix = str7;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 2, this.formattedName, false);
            SafeParcelWriter.writeString(parcel, 3, this.pronunciation, false);
            SafeParcelWriter.writeString(parcel, 4, this.prefix, false);
            SafeParcelWriter.writeString(parcel, 5, this.first, false);
            SafeParcelWriter.writeString(parcel, 6, this.middle, false);
            SafeParcelWriter.writeString(parcel, 7, this.last, false);
            SafeParcelWriter.writeString(parcel, 8, this.suffix, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "PhoneCreator")
    @Reserved({1})
    public static class Phone extends AbstractSafeParcelable {
        public static final Creator<Phone> CREATOR = new zzk();
        public static final int FAX = 3;
        public static final int HOME = 2;
        public static final int MOBILE = 4;
        public static final int UNKNOWN = 0;
        public static final int WORK = 1;
        @Field(mo19055id = 3)
        public String number;
        @Field(mo19055id = 2)
        public int type;

        public Phone() {
        }

        @Constructor
        public Phone(@Param(mo19058id = 2) int i, @Param(mo19058id = 3) String str) {
            this.type = i;
            this.number = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 2, this.type);
            SafeParcelWriter.writeString(parcel, 3, this.number, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "SmsCreator")
    @Reserved({1})
    public static class Sms extends AbstractSafeParcelable {
        public static final Creator<Sms> CREATOR = new zzl();
        @Field(mo19055id = 2)
        public String message;
        @Field(mo19055id = 3)
        public String phoneNumber;

        public Sms() {
        }

        @Constructor
        public Sms(@Param(mo19058id = 2) String str, @Param(mo19058id = 3) String str2) {
            this.message = str;
            this.phoneNumber = str2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 2, this.message, false);
            SafeParcelWriter.writeString(parcel, 3, this.phoneNumber, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "UrlBookmarkCreator")
    @Reserved({1})
    public static class UrlBookmark extends AbstractSafeParcelable {
        public static final Creator<UrlBookmark> CREATOR = new zzm();
        @Field(mo19055id = 2)
        public String title;
        @Field(mo19055id = 3)
        public String url;

        public UrlBookmark() {
        }

        @Constructor
        public UrlBookmark(@Param(mo19058id = 2) String str, @Param(mo19058id = 3) String str2) {
            this.title = str;
            this.url = str2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 2, this.title, false);
            SafeParcelWriter.writeString(parcel, 3, this.url, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @Class(creator = "WiFiCreator")
    @Reserved({1})
    public static class WiFi extends AbstractSafeParcelable {
        public static final Creator<WiFi> CREATOR = new zzn();
        public static final int OPEN = 1;
        public static final int WEP = 3;
        public static final int WPA = 2;
        @Field(mo19055id = 4)
        public int encryptionType;
        @Field(mo19055id = 3)
        public String password;
        @Field(mo19055id = 2)
        public String ssid;

        public WiFi() {
        }

        @Constructor
        public WiFi(@Param(mo19058id = 2) String str, @Param(mo19058id = 3) String str2, @Param(mo19058id = 4) int i) {
            this.ssid = str;
            this.password = str2;
            this.encryptionType = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 2, this.ssid, false);
            SafeParcelWriter.writeString(parcel, 3, this.password, false);
            SafeParcelWriter.writeInt(parcel, 4, this.encryptionType);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public Barcode() {
    }

    @Constructor
    public Barcode(@Param(mo19058id = 2) int i, @Param(mo19058id = 3) String str, @Param(mo19058id = 4) String str2, @Param(mo19058id = 5) int i2, @Param(mo19058id = 6) Point[] pointArr, @Param(mo19058id = 7) Email email2, @Param(mo19058id = 8) Phone phone2, @Param(mo19058id = 9) Sms sms2, @Param(mo19058id = 10) WiFi wiFi, @Param(mo19058id = 11) UrlBookmark urlBookmark, @Param(mo19058id = 12) GeoPoint geoPoint2, @Param(mo19058id = 13) CalendarEvent calendarEvent2, @Param(mo19058id = 14) ContactInfo contactInfo2, @Param(mo19058id = 15) DriverLicense driverLicense2) {
        this.format = i;
        this.rawValue = str;
        this.displayValue = str2;
        this.valueFormat = i2;
        this.cornerPoints = pointArr;
        this.email = email2;
        this.phone = phone2;
        this.sms = sms2;
        this.wifi = wiFi;
        this.url = urlBookmark;
        this.geoPoint = geoPoint2;
        this.calendarEvent = calendarEvent2;
        this.contactInfo = contactInfo2;
        this.driverLicense = driverLicense2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.format);
        SafeParcelWriter.writeString(parcel, 3, this.rawValue, false);
        SafeParcelWriter.writeString(parcel, 4, this.displayValue, false);
        SafeParcelWriter.writeInt(parcel, 5, this.valueFormat);
        SafeParcelWriter.writeTypedArray(parcel, 6, this.cornerPoints, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.email, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.phone, i, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.sms, i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.wifi, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.url, i, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.geoPoint, i, false);
        SafeParcelWriter.writeParcelable(parcel, 13, this.calendarEvent, i, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.contactInfo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 15, this.driverLicense, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public Rect getBoundingBox() {
        int i = Integer.MAX_VALUE;
        int i2 = 0;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MIN_VALUE;
        int i5 = Integer.MIN_VALUE;
        while (true) {
            Point[] pointArr = this.cornerPoints;
            if (i2 >= pointArr.length) {
                return new Rect(i, i3, i4, i5);
            }
            Point point = pointArr[i2];
            i = Math.min(i, point.x);
            i4 = Math.max(i4, point.x);
            i3 = Math.min(i3, point.y);
            i5 = Math.max(i5, point.y);
            i2++;
        }
    }
}
