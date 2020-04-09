package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.security.AES;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/4 18:17
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/4$
 * @updateDes ${TODO}
 */

public class TeacherGoinBean extends Result implements Parcelable {


    /**
     * data : {"rmtp":"iohdMs8UIxafn5FXw/WpHpmA5yWsSXev5kTg8jAEL5fz/852HANffjt9j73TInI23VeoVGFJP3kr\nJUgjnvMfiKLFVdpMTyp/hJBValQrFG3w
     * +x7EqLgBUYMFawPcy9ColC2o8l1N4FzPz6jv5kYkjlfW\nq2kuy7yr6wqUFnC/v0pkdVBEaVNhCX9irUt4cq9/","chrmRoom":{"chrmId":36,
     * "chrmUserId":"514ca3edce63b3b0e89b16ac59a8f4a7",
     * "chrmUserToken":"OxCnC2xkk0fTiqpIH/P7NjxdO6aSlBnOa0dibZTisLBcvRR1mIAyd8LPZ0UNuJcnkTCXL+FCqtWqiu+rK
     * +6aDKTsgRxVoXRQ5xygcJLfRRGmzJR0RwZjm3uH/Uzo79y3"}}
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
         * rmtp : iohdMs8UIxafn5FXw/WpHpmA5yWsSXev5kTg8jAEL5fz/852HANffjt9j73TInI23VeoVGFJP3kr
         * JUgjnvMfiKLFVdpMTyp/hJBValQrFG3w+x7EqLgBUYMFawPcy9ColC2o8l1N4FzPz6jv5kYkjlfW
         * q2kuy7yr6wqUFnC/v0pkdVBEaVNhCX9irUt4cq9/
         * chrmRoom : {"chrmId":36,"chrmUserId":"514ca3edce63b3b0e89b16ac59a8f4a7",
         * "chrmUserToken":"OxCnC2xkk0fTiqpIH/P7NjxdO6aSlBnOa0dibZTisLBcvRR1mIAyd8LPZ0UNuJcnkTCXL+FCqtWqiu+rK
         * +6aDKTsgRxVoXRQ5xygcJLfRRGmzJR0RwZjm3uH/Uzo79y3"}
         */

        private String rmtp;
        private ChrmRoomBean chrmRoom;

        public String getRmtp() {
            if (EmptyUtils.isNotEmpty(rmtp) && rmtp.contains("rtmp")){
                return rmtp;
            }
            return AES.get().decrypt(rmtp);
        }

        public void setRmtp(String rmtp) {
            this.rmtp = rmtp;
        }

        public ChrmRoomBean getChrmRoom() {
            return chrmRoom;
        }

        public void setChrmRoom(ChrmRoomBean chrmRoom) {
            this.chrmRoom = chrmRoom;
        }

        public static class ChrmRoomBean implements Parcelable {

            /**
             * chrmId : 36
             * chrmUserId : 514ca3edce63b3b0e89b16ac59a8f4a7
             * chrmUserToken : OxCnC2xkk0fTiqpIH/P7NjxdO6aSlBnOa0dibZTisLBcvRR1mIAyd8LPZ0UNuJcnkTCXL+FCqtWqiu+rK
             * +6aDKTsgRxVoXRQ5xygcJLfRRGmzJR0RwZjm3uH/Uzo79y3
             */

            private String  chrmId;
            private String chrmUserId;
            private String chrmUserToken;

            public String getChrmId() {
                return chrmId;
            }

            public void setChrmId(String chrmId) {
                this.chrmId = chrmId;
            }

            public String getChrmUserId() {
                return chrmUserId;
            }

            public void setChrmUserId(String chrmUserId) {
                this.chrmUserId = chrmUserId;
            }

            public String getChrmUserToken() {
                return chrmUserToken;
            }

            public void setChrmUserToken(String chrmUserToken) {
                this.chrmUserToken = chrmUserToken;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.chrmId);
                dest.writeString(this.chrmUserId);
                dest.writeString(this.chrmUserToken);
            }

            public ChrmRoomBean() {
            }

            protected ChrmRoomBean(Parcel in) {
                this.chrmId = in.readString();
                this.chrmUserId = in.readString();
                this.chrmUserToken = in.readString();
            }

            public static final Creator<ChrmRoomBean> CREATOR = new Creator<ChrmRoomBean>() {
                @Override
                public ChrmRoomBean createFromParcel(Parcel source) {
                    return new ChrmRoomBean(source);
                }

                @Override
                public ChrmRoomBean[] newArray(int size) {
                    return new ChrmRoomBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.rmtp);
            dest.writeParcelable(this.chrmRoom, flags);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.rmtp = in.readString();
            this.chrmRoom = in.readParcelable(ChrmRoomBean.class.getClassLoader());
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
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
        dest.writeParcelable(this.data, flags);
    }

    public TeacherGoinBean() {
    }

    protected TeacherGoinBean(Parcel in) {
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<TeacherGoinBean> CREATOR = new Parcelable.Creator<TeacherGoinBean>() {
        @Override
        public TeacherGoinBean createFromParcel(Parcel source) {
            return new TeacherGoinBean(source);
        }

        @Override
        public TeacherGoinBean[] newArray(int size) {
            return new TeacherGoinBean[size];
        }
    };
}
