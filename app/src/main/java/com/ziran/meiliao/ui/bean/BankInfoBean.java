package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/1 11:22
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/1$
 * @updateDes ${TODO}
 */

public class BankInfoBean extends Result implements Parcelable {



    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {


        /**
         * id : 6
         * bankPicture : http://www.dgli.net:8888/resource/images/banks/ABC.png
         * phone : 0987654321
         * name : 小欧
         * bankName : 中国农业银行
         * bankCardType : 储蓄卡
         * bankCardNo : 212412412
         */

        private int id;
        private String bankPicture;
        private String phone;
        private String name;
        private String bankName;
        private String bankCardType;
        private String bankCardNo;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBankPicture() {
            return bankPicture;
        }

        public void setBankPicture(String bankPicture) {
            this.bankPicture = bankPicture;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankCardType() {
            return bankCardType;
        }

        public void setBankCardType(String bankCardType) {
            this.bankCardType = bankCardType;
        }

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.bankPicture);
            dest.writeString(this.phone);
            dest.writeString(this.name);
            dest.writeString(this.bankName);
            dest.writeString(this.bankCardType);
            dest.writeString(this.bankCardNo);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.bankPicture = in.readString();
            this.phone = in.readString();
            this.name = in.readString();
            this.bankName = in.readString();
            this.bankCardType = in.readString();
            this.bankCardNo = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public BankInfoBean() {
    }

    protected BankInfoBean(Parcel in) {
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Parcelable.Creator<BankInfoBean> CREATOR = new Parcelable.Creator<BankInfoBean>() {
        @Override
        public BankInfoBean createFromParcel(Parcel source) {
            return new BankInfoBean(source);
        }

        @Override
        public BankInfoBean[] newArray(int size) {
            return new BankInfoBean[size];
        }
    };
}
