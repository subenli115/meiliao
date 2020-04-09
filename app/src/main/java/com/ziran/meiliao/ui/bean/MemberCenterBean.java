package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

public class MemberCenterBean  extends Result {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * smallPic : http://120.79.212.227:8081/resource/images/vip/small_top.png
         * minScore : 10001
         * level : 5
         * maxScore :
         * levelPicture : http://120.79.212.227:8081/resource/images/vip/member_vip_top.png
         * infoComplete : false
         * userName : 用户8649939￥＄6
         * memberLink :
         * userPicture : http://120.79.212.227:8081/upload/images/userHeadImg/defaultU.png
         * currentScore : 999720
         */

        private String smallPic;
        private String minScore;
        private int level;
        private String maxScore;
        private String levelPicture;
        private boolean infoComplete;
        private String userName;
        private String memberLink;
        private String userPicture;
        private int currentScore;

        public String getSmallPic() {
            return smallPic;
        }

        public void setSmallPic(String smallPic) {
            this.smallPic = smallPic;
        }

        public String getMinScore() {
            return minScore;
        }

        public void setMinScore(String minScore) {
            this.minScore = minScore;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getMaxScore() {
            return maxScore;
        }

        public void setMaxScore(String maxScore) {
            this.maxScore = maxScore;
        }

        public String getLevelPicture() {
            return levelPicture;
        }

        public void setLevelPicture(String levelPicture) {
            this.levelPicture = levelPicture;
        }

        public boolean isInfoComplete() {
            return infoComplete;
        }

        public void setInfoComplete(boolean infoComplete) {
            this.infoComplete = infoComplete;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getMemberLink() {
            return memberLink;
        }

        public void setMemberLink(String memberLink) {
            this.memberLink = memberLink;
        }

        public String getUserPicture() {
            return userPicture;
        }

        public void setUserPicture(String userPicture) {
            this.userPicture = userPicture;
        }

        public int getCurrentScore() {
            return currentScore;
        }

        public void setCurrentScore(int currentScore) {
            this.currentScore = currentScore;
        }
    }

}