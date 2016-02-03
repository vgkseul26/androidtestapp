package com.usv.androidtestapp.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Record implements Parcelable{
    private Uri photo;
    private String text;

    public Record(Uri photo, String text) {
        this.photo = photo;
        this.text = text;
    }

    public Uri getPhoto() {
        return photo;
    }

    public String getText() {
        return text;
    }

    public void setPhotoId(Uri photo) {
        this.photo = photo;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;

        Record record = (Record) o;

        if (photo != record.photo) return false;
        return !(text != null ? !text.equals(record.text) : record.text != null);

    }

    @Override
    public int hashCode() {
        int result = photo.hashCode();
        result = 31 * result + text.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Record{" +
                "photoId=" + photo +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeStringArray(new String[] {
                this.photo.toString(),
                this.text});
    }

    protected Record(Parcel parcel) {
        String[] data = new String[2];

        parcel.readStringArray(data);
        this.photo = Uri.parse(data[0]);
        this.text = data[1];
    }

    public static final Parcelable.Creator<Record> CREATOR = new Parcelable.Creator<Record>() {
        public Record createFromParcel(Parcel parcel) {
            return new Record(parcel);
        }

        public Record [] newArray(int size) {
            return new Record[size];
        }
    };
}
