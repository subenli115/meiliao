package com.citypicker.citylist.sortlistview;

import android.os.Parcel;
import android.os.Parcelable;

public class SortModel implements Parcelable {

    private String name;   //显示的数据
    private String sortLetters;  //显示数据拼音的首字母
    private String codeNumber;
    private String code;



    public String getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getData() {
        return getCodeNumber();
//        return String.format("%s%s", getName(), getCodeNumber());
    }

    public static SortModel getDefault() {
        SortModel cityItem = new SortModel();
        cityItem.setCodeNumber("+86");
        cityItem.setName("中国大陆");
        cityItem.setCode("CN");
        cityItem.setSortLetters("Z");
        return cityItem;
    }

    public SortModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.sortLetters);
        dest.writeString(this.codeNumber);
        dest.writeString(this.code);
    }

    protected SortModel(Parcel in) {
        this.name = in.readString();
        this.sortLetters = in.readString();
        this.codeNumber = in.readString();
        this.code = in.readString();
    }

    public static final Creator<SortModel> CREATOR = new Creator<SortModel>() {
        @Override
        public SortModel createFromParcel(Parcel source) {
            return new SortModel(source);
        }

        @Override
        public SortModel[] newArray(int size) {
            return new SortModel[size];
        }
    };
}
