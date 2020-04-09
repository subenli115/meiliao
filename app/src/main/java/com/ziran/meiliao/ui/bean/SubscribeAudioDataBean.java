package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.security.AES;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/31 9:50
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/31$
 * @updateDes ${TODO}
 */

public class SubscribeAudioDataBean extends Result {


    /**
     * data : {"createTime":"2017-08-30","detail":"群主说过,人生的成功来自百分之九十九的汗水和百分之一的装逼;防装逼胜于防川;什么是幸福?我装逼,你看着,那我就比你幸福,你装逼,我看着,那你就比我幸福。",
     * "buyCount":0,"likeCount":3,"shareTitle":"如何正确的装逼?","sharePic":"http://www.dgli.net:8888/resource/images/audio/35.png",
     * "pic":"http://www.dgli.net:8888/resource/images/audio/35.png","isCollect":true,"seeCount":{"id":2,"audioId":1,"count":476,
     * "lastUpdateTime":1504770426000,"createTime":1504077252000},"url":"http://www.psytap.com/resource/music/albumMusic/1hkyvat.mp3",
     * "title":"如何正确的装逼?","duration":"00:30:24","shareDescript":"群主说过,人生的成功来自百分之九十九的汗水和百分之一的装逼;","isLike":true,"shareUrl":"http://www
     * .baidu.com"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SubscribeAudioDataBean{" + "data=" + data + '}';
    }

    public static class DataBean extends ShareBean {
        /**
         * createTime : 2017-08-30
         * detail : 群主说过,人生的成功来自百分之九十九的汗水和百分之一的装逼;防装逼胜于防川;什么是幸福?我装逼,你看着,那我就比你幸福,你装逼,我看着,那你就比我幸福。
         * buyCount : 0
         * likeCount : 3
         * shareTitle : 如何正确的装逼?
         * sharePic : http://www.dgli.net:8888/resource/images/audio/35.png
         * pic : http://www.dgli.net:8888/resource/images/audio/35.png
         * isCollect : true
         * seeCount : {"id":2,"audioId":1,"count":476,"lastUpdateTime":1504770426000,"createTime":1504077252000}
         * url : http://www.psytap.com/resource/music/albumMusic/1hkyvat.mp3
         * title : 如何正确的装逼?
         * duration : 00:30:24
         * shareDescript : 群主说过,人生的成功来自百分之九十九的汗水和百分之一的装逼;
         * isLike : true
         * shareUrl : http://www.baidu.com
         */

        private String createTime;
        private String detail;
        private int buyCount;
        private int likeCount;
        private String pic;
        private String seeCount;
        private String duration;
        private int status;
        private int tag;
        private boolean isCollect;
        private String url;
        private String title;
        private int buyTimes;
        private boolean isLike;
        private String prefix;
        private int seeTimes;
        private List<String> ppt;
        private SubscriptionBean subscription;
       private String   roundPic;
        private boolean ableComment;
        public String getRoundPic() {
         if (  EmptyUtils.isEmpty(roundPic)) return "";
            if ( roundPic.contains("http")){
                return roundPic;
            }
            return AES.get().decrypt(roundPic);
        }

        public boolean isAbleComment() {
            return ableComment;
        }

        public void setAbleComment(boolean ableComment) {
            this.ableComment = ableComment;
        }

        public void setRoundPic(String roundPic) {
            this.roundPic = roundPic;
        }

        public SubscriptionBean getSubscription() {
            return subscription;
        }

        public void setSubscription(SubscriptionBean subscription) {
            this.subscription = subscription;
        }

        public String getPrefix() {
            return prefix;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTag() {
            return tag;
        }

        public void setTag(int tag) {
            this.tag = tag;
        }

        public int getBuyTimes() {
            return buyTimes;
        }

        public void setBuyTimes(int buyTimes) {
            this.buyTimes = buyTimes;
        }

        public int getSeeTimes() {
            return seeTimes;
        }

        public void setSeeTimes(int seeTimes) {
            this.seeTimes = seeTimes;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public List<String> getPpt() {
            return ppt;
        }

        public void setPpt(List<String> ppt) {
            this.ppt = ppt;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getBuyCount() {
            return buyCount;
        }

        public void setBuyCount(int buyCount) {
            this.buyCount = buyCount;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }



        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public boolean isIsCollect() {
            return isCollect;
        }

        public void setIsCollect(boolean isCollect) {
            this.isCollect = isCollect;
        }

        public String getSeeCount() {
            return seeCount;
        }

        public void setSeeCount(String seeCount) {
            this.seeCount = seeCount;
        }

        public String getUrl() {
            return url;
        }

        public String getAESUrl() {
            return AES.get().decrypt(url).trim();
        }

        public String getAESUrlDub() {
            return AES.get().decrypt(AES.get().decrypt(url));
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public boolean isIsLike() {
            return isLike;
        }
        public void setLikeTogle(){
            this.isLike = !isLike;
        }
        public void setCollectTogle(){
            this.isCollect = !isCollect;
        }
        public void setIsLike(boolean isLike) {
            this.isLike = isLike;
        }



        public static class SeeCountBean {
            @Override
            public String toString() {
                return "SeeCountBean{" + "id=" + id + ", audioId=" + audioId + ", count=" + count + ", lastUpdateTime=" + lastUpdateTime
                        + ", createTime=" + createTime + '}';
            }

            /**
             * id : 2
             * audioId : 1
             * count : 476
             * lastUpdateTime : 1504770426000
             * createTime : 1504077252000
             */

            private int id;
            private int audioId;
            private int count;
            private long lastUpdateTime;
            private long createTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAudioId() {
                return audioId;
            }

            public void setAudioId(int audioId) {
                this.audioId = audioId;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public long getLastUpdateTime() {
                return lastUpdateTime;
            }

            public void setLastUpdateTime(long lastUpdateTime) {
                this.lastUpdateTime = lastUpdateTime;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }

        @Override
        public String toString() {
            return "DataBean{" + "createTime='" + createTime + '\'' + ", detail='" + detail + '\'' + ", buyCount=" + buyCount + ", " +
                    "likeCount=" + likeCount + ", pic='" + pic + '\'' + ", seeCount='" + seeCount + '\'' + ", duration='" + duration +
                    '\'' + ", status=" + status + ", tag=" + tag + ", isCollect=" + isCollect + ", url='" + url + '\'' + ", title='" +
                    title + '\'' + ", buyTimes=" + buyTimes + ", isLike=" + isLike + ", prefix='" + prefix + '\'' + ", shareUrl='" +
                    "" + '\'' + ", seeTimes=" + seeTimes + ", ppt=" + ppt + ", subscription=" + subscription + ", roundPic='" +
                    roundPic + '\'' + ", ableComment=" + ableComment + '}';
        }
    }
}
