package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2017/3/7.
 */

public class AlbumGainBean extends Result {


    /**
     * data : {"free":false,"qrcode":"/xx/fuck.html","pg":"https://www.psytap.com/wpyx_longjg/static/home/images/shareAlbum/bg.png","number":1,"exist":false}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean extends ShareBean implements Parcelable {


        /**
         * free : false
         * qrcode : /xx/fuck.html
         * pg : https://www.psytap.com/wpyx_longjg/static/home/images/shareAlbum/bg.png
         * number : 1
         * exist : false
         */

        private boolean free;
        private String qrcode;
        private String bg;
        private int number;
        private boolean exist;



        @Override
        public String toString() {
            return "DataBean{" +
                    "free=" + free +
                    ", qrcode='" + qrcode + '\'' +
                    ", bg='" + bg + '\'' +
                    ", number=" + number +
                    ", exist=" + exist +
                    '}';
        }

        public boolean isFree() {
            return free;
        }

        public void setFree(boolean free) {
            this.free = free;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public String getBg() {
            return bg;
        }

        public void setBg(String bg) {
            this.bg = bg;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean isExist() {
            return exist;
        }

        public void setExist(boolean exist) {
            this.exist = exist;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.free ? (byte) 1 : (byte) 0);
            dest.writeString(this.qrcode);
            dest.writeString(this.bg);
            dest.writeInt(this.number);
            dest.writeByte(this.exist ? (byte) 1 : (byte) 0);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.free = in.readByte() != 0;
            this.qrcode = in.readString();
            this.bg = in.readString();
            this.number = in.readInt();
            this.exist = in.readByte() != 0;
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
