package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/7 9:54
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/7$
 * @updateDes ${TODO}
 */

public class CrowdFundingDetailBean extends Result {

    /**
     * data : {"createTime":1511871192000,"targetMembers":50,"accessToken":"1849248caa7b626fd1ae5eeceb6e9fd9253b65","staus":1,"status":1,
     * "supportMembers":20,"shareTitle":"报名 | 医学及心理学中的正念3","sharePic":"https://www.dgli
     * .net/resource/images/qcourselibrary/index_act_banner1228.png","leftTime":41,"endTime":1512023287000,"intro":"报名 |
     * 医学及心理学中的正念\u2014\u2014正念减压","id":1,"picture":"https://www.dgli.net/resource/images/qcourselibrary/index_act_banner1228.png",
     * "startTime":1511936884000,"title":"报名 | 医学及心理学中的正念3","headImg":"index_act_banner1228.png","avgPrice":50,"address":"广州星光映景",
     * "able":1,"shareUrl":"https://www.dgli.net/crowdFundDetail?id=1&accessToken=1849248caa7b626fd1ae5eeceb6e9fd9253b65",
     * "othersNeeded":{"needed":{"sex":0,"phone":1,"email":0,"age":0,"name":1,"servicePhone":1,"remarks":0}},"totalTime":"1天1晚"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean extends TopBean {
        /**
         * createTime : 1511871192000
         * targetMembers : 50
         * accessToken : 1849248caa7b626fd1ae5eeceb6e9fd9253b65
         * staus : 1
         * status : 1
         * supportMembers : 20
         * shareTitle : 报名 | 医学及心理学中的正念3
         * sharePic : https://www.dgli.net/resource/images/qcourselibrary/index_act_banner1228.png
         * leftTime : 41
         * endTime : 1512023287000
         * intro : 报名 | 医学及心理学中的正念——正念减压
         * id : 1
         * picture : https://www.dgli.net/resource/images/qcourselibrary/index_act_banner1228.png
         * startTime : 1511936884000
         * title : 报名 | 医学及心理学中的正念3
         * headImg : index_act_banner1228.png
         * avgPrice : 50.0
         * address : 广州星光映景
         * able : 1
         * shareUrl : https://www.dgli.net/crowdFundDetail?id=1&accessToken=1849248caa7b626fd1ae5eeceb6e9fd9253b65
         * othersNeeded : {"needed":{"sex":0,"phone":1,"email":0,"age":0,"name":1,"servicePhone":1,"remarks":0}}
         * totalTime : 1天1晚
         */
        private long createTime;
        private String shareTitle;
        private String sharePic;
        private String headImg;
        private String shareDescript;
        private int able;
        private String shareUrl;
        private String detail;
        private AuthorBean userInfo;
        private boolean isCreater;
        private int isCollect;
        private int isShare;

        public int getIsShare() {
            return isShare;
        }

        public void setIsShare(int isShare) {
            this.isShare = isShare;
        }

        public int isCollect() {
            return isCollect;
        }

        public void setCollect(int collect) {
            isCollect = collect;
        }
        public void toggleCollect() {
            isCollect = isCollect == 0 ? 1 : 0;
        }
        public boolean isCreater() {
            return isCreater;
        }

        public void setCreater(boolean creater) {
            isCreater = creater;
        }

        public String getShareDescript() {
            return shareDescript;
        }

        public AuthorBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(AuthorBean userInfo) {
            this.userInfo = userInfo;
        }

        public void setShareDescript(String shareDescript) {
            this.shareDescript = shareDescript;
        }


        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        private OthersNeededBean othersNeeded;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getShareTitle() {
            return shareTitle;
        }

        public void setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
        }

        public String getSharePic() {
            return sharePic;
        }

        public void setSharePic(String sharePic) {
            this.sharePic = sharePic;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }


        public int getAble() {
            return able;
        }

        public void setAble(int able) {
            this.able = able;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public OthersNeededBean getOthersNeeded() {
            return othersNeeded;
        }

        public void setOthersNeeded(OthersNeededBean othersNeeded) {
            this.othersNeeded = othersNeeded;
        }

        public static class OthersNeededBean implements Parcelable {
            @Override
            public String toString() {
                return "OthersNeededBean{" + "sex=" + sex + ", phone=" + phone + ", email=" + email + ", age=" + age + ", name=" + name +
                        ", remarks=" + remarks + '}';
            }

            /**
             * sex : 0
             * phone : 1
             * email : 0
             * age : 0
             * name : 1
             * servicePhone : 1
             * remarks : 0
             */

            private int sex;
            private int phone;
            private int email;
            private int age;
            private int name;
            private int remarks;

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getPhone() {
                return phone;
            }

            public void setPhone(int phone) {
                this.phone = phone;
            }

            public int getEmail() {
                return email;
            }

            public void setEmail(int email) {
                this.email = email;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public int getName() {
                return name;
            }

            public void setName(int name) {
                this.name = name;
            }

            public int getRemarks() {
                return remarks;
            }

            public void setRemarks(int remarks) {
                this.remarks = remarks;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.sex);
                dest.writeInt(this.phone);
                dest.writeInt(this.email);
                dest.writeInt(this.age);
                dest.writeInt(this.name);
                dest.writeInt(this.remarks);
            }

            public OthersNeededBean() {
            }

            protected OthersNeededBean(Parcel in) {
                this.sex = in.readInt();
                this.phone = in.readInt();
                this.email = in.readInt();
                this.age = in.readInt();
                this.name = in.readInt();
                this.remarks = in.readInt();
            }

            public static final Parcelable.Creator<OthersNeededBean> CREATOR = new Parcelable.Creator<OthersNeededBean>() {
                @Override
                public OthersNeededBean createFromParcel(Parcel source) {
                    return new OthersNeededBean(source);
                }

                @Override
                public OthersNeededBean[] newArray(int size) {
                    return new OthersNeededBean[size];
                }
            };
        }
    }
}
