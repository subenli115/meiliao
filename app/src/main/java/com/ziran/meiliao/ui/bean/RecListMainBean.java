package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class RecListMainBean extends Result {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean  {
        /**
         * recList : [{"id":1,"recordId":1,"uploadTime":1560932612000,"lock":false,"title":"前言：挑战与应对","orders":1,"recordUrl":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonCIapxkcg8DoidS++9DBUAo"},{"id":2,"recordId":1,"uploadTime":1560932707000,"lock":false,"title":"第一章：了解你的工具-正念（上）","orders":2,"recordUrl":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonB8qPGT4FzffxfVYpTOl5sJVxQYN1oRaStP\nMeZnZbBTwg=="},{"id":3,"recordId":1,"uploadTime":1560932728000,"lock":true,"title":"第一章：了解你的工具-正念（下）","orders":3,"recordUrl":""},{"id":4,"recordId":1,"uploadTime":1560932744000,"lock":true,"title":"第二章：自我觉察（上）","orders":4,"recordUrl":""},{"id":5,"recordId":1,"uploadTime":1560933449000,"lock":true,"title":"第二章：自我觉察（下）","orders":5,"recordUrl":""},{"id":6,"recordId":1,"uploadTime":1560933487000,"lock":true,"title":"第三章：自我管理（上）","orders":6,"recordUrl":""},{"id":7,"recordId":1,"uploadTime":1560933515000,"lock":true,"title":"第三章：自我管理（下）","orders":7,"recordUrl":""},{"id":8,"recordId":1,"uploadTime":1560933573000,"lock":true,"title":"第四章：同理心&人际交往（上）","orders":8,"recordUrl":""},{"id":9,"recordId":1,"uploadTime":1560933642000,"lock":true,"title":"第四章：同理心&人际交往（下）","orders":9,"recordUrl":""}]
         * toptip : 已更新9讲
         * tip : 提醒：5月20-6月17限时免费观看，之后观看请到【研修社】-【专栏】中付费观看
         */

        private String toptip;
        private String tip;
        private List<RecListBean> recList;

        public String getToptip() {
            return toptip;
        }

        public void setToptip(String toptip) {
            this.toptip = toptip;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public List<RecListBean> getRecList() {
            return recList;
        }

        public void setRecList(List<RecListBean> recList) {
            this.recList = recList;
        }

        public static class RecListBean {
            /**
             * id : 1
             * recordId : 1
             * uploadTime : 1560932612000
             * lock : false
             * title : 前言：挑战与应对
             * orders : 1
             * recordUrl : wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonCIapxkcg8DoidS++9DBUAo
             */

            private int id;
            private int recordId;
            private long uploadTime;
            private boolean lock;

            public boolean isIsclick() {
                return isclick;
            }

            public void setIsclick(boolean isclick) {
                this.isclick = isclick;
            }

            private boolean isclick;
            private String title;
            private int orders;
            private String recordUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRecordId() {
                return recordId;
            }

            public void setRecordId(int recordId) {
                this.recordId = recordId;
            }

            public long getUploadTime() {
                return uploadTime;
            }

            public void setUploadTime(long uploadTime) {
                this.uploadTime = uploadTime;
            }

            public boolean isLock() {
                return lock;
            }

            public void setLock(boolean lock) {
                this.lock = lock;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getOrders() {
                return orders;
            }

            public void setOrders(int orders) {
                this.orders = orders;
            }

            public String getRecordUrl() {
                return recordUrl;
            }

            public void setRecordUrl(String recordUrl) {
                this.recordUrl = recordUrl;
            }
        }


//        @Override
//        public String toString() {
//            return "DataBean{" +
//                    "extension='" + extension + '\'' +
//                    ", protocol='" + protocol + '\'' +
//                    ", e_shareDescript='" + e_shareDescript + '\'' +
//                    ", shareDescript='" + shareDescript + '\'' +
//                    ", e_sharePicture='" + e_sharePicture + '\'' +
//                    ", ios=" + ios +
//                    ", e_shareUrl='" + e_shareUrl + '\'' +
//                    ", e_shareTitle='" + e_shareTitle + '\'' +
//                    ", shareUrl='" + shareUrl + '\'' +
//                    ", shareTitle='" + shareTitle + '\'' +
//                    ", sharePicture='" + sharePicture + '\'' +
//                    '}';
//        }
//
//        @Override
//        public int describeContents() {
//            return 0;
//        }
//
//        @Override
//        public void writeToParcel(Parcel dest, int flags) {
//            dest.writeString(this.extension);
//            dest.writeString(this.protocol);
//            dest.writeString(this.e_shareDescript);
//            dest.writeString(this.shareDescript);
//            dest.writeString(this.e_sharePicture);
//            dest.writeByte(this.ios ? (byte) 1 : (byte) 0);
//            dest.writeString(this.e_shareUrl);
//            dest.writeString(this.e_shareTitle);
//            dest.writeString(this.shareUrl);
//            dest.writeString(this.shareTitle);
//            dest.writeString(this.sharePicture);
//        }
//
//        public DataBean() {
//        }
//
//        protected DataBean(Parcel in) {
//            this.extension = in.readString();
//            this.protocol = in.readString();
//            this.e_shareDescript = in.readString();
//            this.shareDescript = in.readString();
//            this.e_sharePicture = in.readString();
//            this.ios = in.readByte() != 0;
//            this.e_shareUrl = in.readString();
//            this.e_shareTitle = in.readString();
//            this.shareUrl = in.readString();
//            this.shareTitle = in.readString();
//            this.sharePicture = in.readString();
//        }

//        public static final Parcelable.Creator<ResBean.DataBean> CREATOR = new Parcelable.Creator<ResBean.DataBean>() {
//            @Override
//            public ResBean.DataBean createFromParcel(Parcel source) {
//                return new ResBean.DataBean(source);
//            }
//
//            @Override
//            public ResBean.DataBean[] newArray(int size) {
//                return new ResBean.DataBean[size];
//            }
//        };
    }

    @Override
    public String toString() {
        return "ResBean{" +
                "data=" + data +
                '}';
    }











}
