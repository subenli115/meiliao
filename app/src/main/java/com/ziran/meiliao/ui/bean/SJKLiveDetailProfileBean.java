package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.security.AES;
import com.ziran.meiliao.entry.VideoSectionEntry;

import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */

public class SJKLiveDetailProfileBean extends Result {


    /**
     * data : {"chrmId":3,"detail":"本次五日正念止语静修营，旨在为学习过正念初阶的伙伴，以及喜爱静观的朋友创造修习机会。 \r\n　　五日正念止语静修是一段严格的、高强度的静观练习，可以带来丰硕的回报。在这个五日静修中，你将沉浸于正念练习中，学习有系统的方法，循序渐进地了解压力的真相，开发超越压力苦迫的心智能力。换言之，是籍由直接觉照身心现象，透过亲身的经验，培育正念的觉知力，乃至洞察身心实相的理解力。给身心全然的滋养，更深的对自己和世界的领悟和理解，并增强你作为正念老师的承诺。 \r\n　　课程着重于实作的引导与练习后经验的讨论。在这五天中，当我们细致小心地去探索我们的心念时，老师们会讲解练习的原则和应用，设有问答互动环节，以及培育深刻共享的那份投入。在这个五天中，系统性的冥想指导会带领你进入止静，并帮助你善巧地与所呈现的种种现象工作。这是内在觉知和发现的旅途，蕴涵着巨大的转化的可能。","chrmUserId":"0d88f49ad56c4ceac58f24185172f054","likeCount":1,"shareTitle":"历史1号","sharePic":"","hasBuy":true,"isCollect":true,"type":"course","url":"wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonD1nffoi8GAOUUiKAahDTt4","ableUsedCoupon":false,"picture":"https://www.psytap.com/wpyx_longjg/resource/images/activity/a1/active_banner.png","author":{"name":"测试"},"vip":"vip","duration":"01:30:00","title":"历史1号","price":0.01,"chrmUserToken":"wfc1O3o7ltu8iuWbI6nn0ib92i28VOt4/OSL4jTlr7cXoOJ8Asp5NxI/EJ38wnN1dwya5+/BrjywIAyhNMP6zlp08e2b16Ved53zRrK0/VlLw0/43UMWBGNfEqROz/DudirbDzWEeFc=","dir":[{"isCur":false,"duration":"01:30:00","title":"预告","courseId":1,"isHis":false,"url":""},{"isCur":false,"duration":"01:30:00","title":"直播","courseId":2,"isHis":false,"url":""},{"isCur":true,"duration":"01:30:00","title":"历史1号","courseId":3,"isHis":true,"url":""},{"isCur":false,"duration":"02:00:00","title":"历史2号","courseId":4,"isHis":true,"url":""},{"isCur":false,"duration":"01:00:00","title":"历史3号","courseId":5,"isHis":true,"url":""}],"shareDescript":"本次五日正念止语静修营，旨在为学习过正念初阶的伙伴，以及喜爱静观的朋友创造修习机会。 \r\n　　五日正","isLike":true,"watchCount":268,"shareUrl":"http://www.psytap.com/wpyx_longjg/page/content/shareCourse.html?courseId=3&shareUser=vUMRVcWbR/u/lVNIOHAsxCKxAvoxhzhnbyPx78kCOhW2hJEK0v1tNk1VMggA6Wgn","shareTimes":15}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean extends ShareBean {
        /**
         * chrmId : 3
         * detail : 本次五日正念止语静修营，旨在为学习过正念初阶的伙伴，以及喜爱静观的朋友创造修习机会。
         * 　　五日正念止语静修是一段严格的、高强度的静观练习，可以带来丰硕的回报。在这个五日静修中，你将沉浸于正念练习中，学习有系统的方法，循序渐进地了解压力的真相，开发超越压力苦迫的心智能力。换言之，是籍由直接觉照身心现象，透过亲身的经验，培育正念的觉知力，乃至洞察身心实相的理解力。给身心全然的滋养，更深的对自己和世界的领悟和理解，并增强你作为正念老师的承诺。
         * 　　课程着重于实作的引导与练习后经验的讨论。在这五天中，当我们细致小心地去探索我们的心念时，老师们会讲解练习的原则和应用，设有问答互动环节，以及培育深刻共享的那份投入。在这个五天中，系统性的冥想指导会带领你进入止静，并帮助你善巧地与所呈现的种种现象工作。这是内在觉知和发现的旅途，蕴涵着巨大的转化的可能。
         * chrmUserId : 0d88f49ad56c4ceac58f24185172f054
         * likeCount : 1
         * shareTitle : 历史1号
         * sharePic :
         * hasBuy : true
         * isCollect : true
         * type : course
         * url : wWdi62+Z6I0tJ4orIpTpz+PLDT8ITQrvHj8oxiHuonD1nffoi8GAOUUiKAahDTt4
         * ableUsedCoupon : false
         * picture : https://www.psytap.com/wpyx_longjg/resource/images/activity/a1/active_banner.png
         * author : {"name":"测试"}
         * vip : vip
         * duration : 01:30:00
         * title : 历史1号
         * price : 0.01
         * chrmUserToken : wfc1O3o7ltu8iuWbI6nn0ib92i28VOt4/OSL4jTlr7cXoOJ8Asp5NxI/EJ38wnN1dwya5+/BrjywIAyhNMP6zlp08e2b16Ved53zRrK0/VlLw0/43UMWBGNfEqROz/DudirbDzWEeFc=
         * dir : [{"isCur":false,"duration":"01:30:00","title":"预告","courseId":1,"isHis":false,"url":""},{"isCur":false,"duration":"01:30:00","title":"直播","courseId":2,"isHis":false,"url":""},{"isCur":true,"duration":"01:30:00","title":"历史1号","courseId":3,"isHis":true,"url":""},{"isCur":false,"duration":"02:00:00","title":"历史2号","courseId":4,"isHis":true,"url":""},{"isCur":false,"duration":"01:00:00","title":"历史3号","courseId":5,"isHis":true,"url":""}]
         * shareDescript : 本次五日正念止语静修营，旨在为学习过正念初阶的伙伴，以及喜爱静观的朋友创造修习机会。
         * 　　五日正
         * isLike : true
         * watchCount : 268
         * shareUrl : http://www.psytap.com/wpyx_longjg/page/content/shareCourse.html?courseId=3&shareUser=vUMRVcWbR/u/lVNIOHAsxCKxAvoxhzhnbyPx78kCOhW2hJEK0v1tNk1VMggA6Wgn
         * shareTimes : 15
         */

        private int chrmId;
        private String detail;
        private int status;
        private String chrmUserId;
        private String levelDetail;
        private int likeCount;
        private boolean hasBuy;
        private boolean isCollect;
        private String type;
        private String url;
        private String picture;
        private AuthorBean author;
        private String vip;
        private String duration;
        private String title;
        private float price;
        private String chrmUserToken;
        private boolean isLike;
        private int watchCount;
        private int shareTimes;
        private List<VideoSectionEntry> dir;
        private String courseId;
        private boolean ableUsedCoupon;
        private float usedCouponPrice;
        private String  usedCouponId;
        private float faceValue;
        private boolean shikan;
        private int userCoin;
        private int needCoin;
        private boolean hasTick;
        private int memberPrice;
        public int getMemberPrice() {
            return memberPrice;
        }

        public void setMemberPrice(int memberPrice) {
            this.memberPrice = memberPrice;
        }

        private SubscriptionBean subscription;

        public String getLevelDetail() {
            return levelDetail;
        }

        public void setLevelDetail(String levelDetail) {
            this.levelDetail = levelDetail;
        }

        public SubscriptionBean getSubscription() {
            return subscription;
        }

        public void setSubscription(SubscriptionBean subscription) {
            this.subscription = subscription;
        }

        public boolean isHasTick() {
            return hasTick;
        }

        public void setHasTick(boolean hasTick) {
            this.hasTick = hasTick;
        }

        private List<UserTickBean> userTick;

        public static class UserTickBean {
            private String tickId;
            private String title;
            private String descript;
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getTickId() {
                return tickId;
            }

            public void setTickId(String tickId) {
                this.tickId = tickId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescript() {
                return descript;
            }

            public void setDescript(String descript) {
                this.descript = descript;
            }

            @Override
            public String toString() {
                return "UserTickBean{" + "tickId='" + tickId + '\'' + ", title='" + title + '\'' + ", descript='" + descript + '\'' + '}';
            }
        }

        public List<UserTickBean> getUserTick() {
            return userTick;
        }

        public void setUserTick(List<UserTickBean> userTick) {
            this.userTick = userTick;
        }

        public boolean isShikan() {
            return shikan;
        }

        public int getUserCoin() {
            return userCoin;
        }

        public void setUserCoin(int userCoin) {
            this.userCoin = userCoin;
        }

        public int getNeedCoin() {
            return needCoin;
        }

        public void setNeedCoin(int needCoin) {
            this.needCoin = needCoin;
        }

        public void setShikan(boolean shikan) {
            this.shikan = shikan;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public float getFaceValue() {
            return faceValue;
        }

        public void setFaceValue(float faceValue) {
            this.faceValue = faceValue;
        }

        public float getUsedCouponPrice() {
            return usedCouponPrice;
        }

        public void setUsedCouponPrice(float usedCouponPrice) {
            this.usedCouponPrice = usedCouponPrice;
        }

        public String getUsedCouponId() {
            return usedCouponId;
        }

        public void setUsedCouponId(String usedCouponId) {
            this.usedCouponId = usedCouponId;
        }

        public boolean isLike() {
            return isLike;
        }

        public void setLike(boolean like) {
            isLike = like;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public boolean isCollect() {
            return isCollect;
        }

        public void setCollect(boolean collect) {
            isCollect = collect;
        }

        public int getChrmId() {
            return chrmId;
        }

        public void setChrmId(int chrmId) {
            this.chrmId = chrmId;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getChrmUserId() {
            return chrmUserId;
        }

        public void setChrmUserId(String chrmUserId) {
            this.chrmUserId = chrmUserId;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }



        public boolean isHasBuy() {
            return hasBuy;
        }

        public void setHasBuy(boolean hasBuy) {
            this.hasBuy = hasBuy;
        }

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return AES.get().decrypt(url);
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isAbleUsedCoupon() {
            return ableUsedCoupon;
        }

        public void setAbleUsedCoupon(boolean ableUsedCoupon) {
            this.ableUsedCoupon = ableUsedCoupon;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public String getVip() {
            return vip;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public String getChrmUserToken() {
            return chrmUserToken;
        }

        public String getAESChrmUserToken() {
            return AES.get().decrypt(chrmUserToken);
        }

        public void setChrmUserToken(String chrmUserToken) {
            this.chrmUserToken = chrmUserToken;
        }

        public boolean isIsLike() {
            return isLike;
        }

        public void setIsLike(boolean isLike) {
            this.isLike = isLike;
        }

        public int getWatchCount() {
            return watchCount;
        }

        public void setWatchCount(int watchCount) {
            this.watchCount = watchCount;
        }

        public int getShareTimes() {
            return shareTimes;
        }

        public void setShareTimes(int shareTimes) {
            this.shareTimes = shareTimes;
        }

        public List<VideoSectionEntry> getDir() {
            return dir;
        }

        public void setDir(List<VideoSectionEntry> dir) {
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "DataBean{" + "chrmId=" + chrmId + ", detail='" + detail + '\'' + ", status=" + status + ", chrmUserId='" + chrmUserId
                    + '\'' + ", likeCount=" + likeCount + ", hasBuy=" + hasBuy + ", isCollect=" + isCollect + ", type='" + type + '\'' +
                    ", url='" + url + '\'' + ", picture='" + picture + '\'' + ", author=" + author + ", vip='" + vip + '\'' + ", " +
                    "duration='" + duration + '\'' + ", title='" + title + '\'' + ", price=" + price + ", chrmUserToken='" +
                    chrmUserToken + '\'' + ", isLike=" + isLike + ", watchCount=" + watchCount + ", shareTimes=" + shareTimes + ", dir="
                    + dir + ", courseId='" + courseId + '\'' + ", ableUsedCoupon=" + ableUsedCoupon + ", usedCouponPrice=" +
                    usedCouponPrice + ", usedCouponId='" + usedCouponId + '\'' + ", faceValue=" + faceValue + ", shikan=" + shikan + ", " +
                    "userCoin=" + userCoin + ", needCoin=" + needCoin + ", hasTick=" + hasTick + ", subscription=" + subscription + ", " +
                    "userTick=" + userTick + '}';
        }
    }

    @Override
    public String toString() {
        return "SJKLiveDetailProfileBean{" +
                "data=" + data +
                '}';
    }
}
