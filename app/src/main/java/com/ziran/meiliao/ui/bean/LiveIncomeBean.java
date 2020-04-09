package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/5/24 18:04
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/5/24$
 * @updateDes ${TODO}
 */

public class LiveIncomeBean extends Result {

    /**
     * data : {"ableMoney":336.40000000000003,"totalIncome":444.40000000000003,"incomeList":[{"time":1519438636000,"title":"慈心静观练习系列之无特定对象的慈心练习一","incomePrice":"200",
     * "incomeId":96},{"time":1519438636000,"title":"慈心静观练习系列之无特定对象的慈心练习一","incomePrice":"100","incomeId":96}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {


        /**
         * ableMoney : 336.40000000000003
         * totalIncome : 444.40000000000003
         * incomeList : [{"time":1519438636000,"title":"慈心静观练习系列之无特定对象的慈心练习一","incomePrice":"200","incomeId":96},{"time":1519438636000,"title":"慈心静观练习系列之无特定对象的慈心练习一",
         * "incomePrice":"100","incomeId":96}]
         */

        private double ableMoney;
        private double totalIncome;
        private List<IncomeListBean> incomeList;

        public double getAbleMoney() {
            return ableMoney;
        }

        public void setAbleMoney(double ableMoney) {
            this.ableMoney = ableMoney;
        }

        public double getTotalIncome() {
            return totalIncome;
        }

        public void setTotalIncome(double totalIncome) {
            this.totalIncome = totalIncome;
        }

        public List<IncomeListBean> getIncomeList() {
            return incomeList;
        }

        public void setIncomeList(List<IncomeListBean> incomeList) {
            this.incomeList = incomeList;
        }

        public static class IncomeListBean implements Parcelable {

            /**
             * time : 1519438636000
             * title : 慈心静观练习系列之无特定对象的慈心练习一
             * incomePrice : 200
             * incomeId : 96
             */

            private long time;
            private String title;
            private String incomePrice;
            private int incomeId;

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getIncomePrice() {
                return incomePrice;
            }

            public void setIncomePrice(String incomePrice) {
                this.incomePrice = incomePrice;
            }

            public int getIncomeId() {
                return incomeId;
            }

            public void setIncomeId(int incomeId) {
                this.incomeId = incomeId;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeLong(this.time);
                dest.writeString(this.title);
                dest.writeString(this.incomePrice);
                dest.writeInt(this.incomeId);
            }

            public IncomeListBean() {
            }

            protected IncomeListBean(Parcel in) {
                this.time = in.readLong();
                this.title = in.readString();
                this.incomePrice = in.readString();
                this.incomeId = in.readInt();
            }

            public static final Parcelable.Creator<IncomeListBean> CREATOR = new Parcelable.Creator<IncomeListBean>() {
                @Override
                public IncomeListBean createFromParcel(Parcel source) {
                    return new IncomeListBean(source);
                }

                @Override
                public IncomeListBean[] newArray(int size) {
                    return new IncomeListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(this.ableMoney);
            dest.writeDouble(this.totalIncome);
            dest.writeTypedList(this.incomeList);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.ableMoney = in.readDouble();
            this.totalIncome = in.readDouble();
            this.incomeList = in.createTypedArrayList(IncomeListBean.CREATOR);
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
