package com.pic.service.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page implements Parcelable {

    @SerializedName("current_page")
    private int currentPage;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_items")
    private int totalItems;
    @SerializedName("photos")
    private List<Photo> photos;


    protected Page(Parcel in) {
        this.currentPage = in.readInt();
        this.totalPages = in.readInt();
        this.totalItems = in.readInt();
        this.photos = new ArrayList<Photo>();
        in.readTypedList(this.photos, Photo.CREATOR);
    }

    public static final Creator<Page> CREATOR = new Creator<Page>() {
        @Override
        public Page createFromParcel(Parcel in) {
            return new Page(in);
        }

        @Override
        public Page[] newArray(int size) {
            return new Page[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.currentPage);
        dest.writeInt(this.totalPages);
        dest.writeInt(this.totalItems);
        dest.writeTypedList(this.photos);
    }
}
