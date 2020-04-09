package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/5/25 11:19
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/5/25$
 * @updateDes ${TODO}
 */

public class MediaAndTextBean extends Result {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * detail : 测试详细说明测试详细说明测试详细说明测试详细说明测试详细说明测试详细说明测试详细说明
         * buyCount : 122
         * isBuy : true
         * shareTitle : 正念在成瘾群体中的应用
         * sharePic : https://www.dgli.net/resource/images/subscription/course_banner1_li.png
         * userCoin : 11345
         * targetId : 1
         * type : 2
         * roundPic : https://www.dgli.net/resource/images/subscription/tldeafult.png
         * url : bc1WlEDCqibieAHE+1T7BFtlaZcEIIeokr3Ywx1ARwTuZPwFwBuTMqRZIuOo/zjz9WDjjAJAzAUH
         2On5lwwLrw==
         * subscriptionId : 1
         * title : 第一堂课 自我关爱/正念相逢
         * duration : 00:25:26
         * shareDescript :         毒品对社会带来的伤害有目共睹，不只医疗戒治中的毒品成瘾者复发率极高，监狱毒品受刑人回
         * shareUrl : https://www.dgli.net/subscription/webIndex/tid/1/shareUser/3890/isShare/1?1=1&isShare=1
         * st : false
         */
        private boolean isCollect;
        private String detail;
        private int buyCount;
        private boolean isBuy;
        private String shareTitle;
        private String sharePic;
        private String userCoin;
        private int targetId;
        private String courseHtml;
        private int type;
        private String roundPic;
        private String url;
        private int subscriptionId;
        private String title;
        private String duration;
        private String shareDescript;
        private String shareUrl;
        private boolean st;
        public String getCourseHtml() {
            return courseHtml;
        }

        public void setCourseHtml(String courseHtml) {
            this.courseHtml = courseHtml;
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

        public boolean isIsBuy() {
            return isBuy;
        }

        public void setIsBuy(boolean isBuy) {
            this.isBuy = isBuy;
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

        public String getUserCoin() {
            return userCoin;
        }

        public void setUserCoin(String userCoin) {
            this.userCoin = userCoin;
        }

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getRoundPic() {
            return roundPic;
        }

        public void setRoundPic(String roundPic) {
            this.roundPic = roundPic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getSubscriptionId() {
            return subscriptionId;
        }

        public void setSubscriptionId(int subscriptionId) {
            this.subscriptionId = subscriptionId;
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

        public String getShareDescript() {
            return shareDescript;
        }

        public void setShareDescript(String shareDescript) {
            this.shareDescript = shareDescript;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public void setShareUrl(String shareUrl) {
            this.shareUrl = shareUrl;
        }

        public boolean isSt() {
            return st;
        }

        public void setSt(boolean st) {
            this.st = st;
        }

        public boolean isCollect() {
            return isCollect;
        }

        public void setCollect(boolean collect) {
            isCollect = collect;
        }
    }
}
