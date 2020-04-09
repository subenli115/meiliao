package com.ziran.meiliao.ui.bean;


import com.ziran.meiliao.common.okhttp.Result;


public class MemberDetails extends Result {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

            public static class DataBean {


                /**
                 * levelRule : {"nextScoreNeed":500,"nextLevelDetail":"白银会员","score":0}
                 * levelRights : {"levelRuleUrl":"http://dgli.net/page/static/level/levelRule.html","activityDiscount":1,"vipService":0,"signChannel":0,"albumDiscount":1,"subDiscount":1}
                 * levelDetail : 普通会员
                 */

                private LevelRuleBean levelRule;
                private LevelRightsBean levelRights;
                private String levelDetail;

                public LevelRuleBean getLevelRule() {
                    return levelRule;
                }

                public void setLevelRule(LevelRuleBean levelRule) {
                    this.levelRule = levelRule;
                }

                public LevelRightsBean getLevelRights() {
                    return levelRights;
                }

                public void setLevelRights(LevelRightsBean levelRights) {
                    this.levelRights = levelRights;
                }

                public String getLevelDetail() {
                    return levelDetail;
                }

                public void setLevelDetail(String levelDetail) {
                    this.levelDetail = levelDetail;
                }

                public static class LevelRuleBean {
                    /**
                     * nextScoreNeed : 500
                     * nextLevelDetail : 白银会员
                     * score : 0
                     */

                    private int nextScoreNeed;
                    private String nextLevelDetail;
                    private int score;

                    public int getNextScoreNeed() {
                        return nextScoreNeed;
                    }

                    public void setNextScoreNeed(int nextScoreNeed) {
                        this.nextScoreNeed = nextScoreNeed;
                    }

                    public String getNextLevelDetail() {
                        return nextLevelDetail;
                    }

                    public void setNextLevelDetail(String nextLevelDetail) {
                        this.nextLevelDetail = nextLevelDetail;
                    }

                    public int getScore() {
                        return score;
                    }

                    public void setScore(int score) {
                        this.score = score;
                    }
                }

                public static class LevelRightsBean {
                    /**
                     * levelRuleUrl : http://dgli.net/page/static/level/levelRule.html
                     * activityDiscount : 1
                     * vipService : 0
                     * signChannel : 0
                     * albumDiscount : 1
                     * subDiscount : 1
                     */

                    private String levelRuleUrl;
                    private int activityDiscount;
                    private int vipService;
                    private int signChannel;
                    private int albumDiscount;
                    private int subDiscount;

                    public String getLevelRuleUrl() {
                        return levelRuleUrl;
                    }

                    public void setLevelRuleUrl(String levelRuleUrl) {
                        this.levelRuleUrl = levelRuleUrl;
                    }

                    public int getActivityDiscount() {
                        return activityDiscount;
                    }

                    public void setActivityDiscount(int activityDiscount) {
                        this.activityDiscount = activityDiscount;
                    }

                    public int getVipService() {
                        return vipService;
                    }

                    public void setVipService(int vipService) {
                        this.vipService = vipService;
                    }

                    public int getSignChannel() {
                        return signChannel;
                    }

                    public void setSignChannel(int signChannel) {
                        this.signChannel = signChannel;
                    }

                    public int getAlbumDiscount() {
                        return albumDiscount;
                    }

                    public void setAlbumDiscount(int albumDiscount) {
                        this.albumDiscount = albumDiscount;
                    }

                    public int getSubDiscount() {
                        return subDiscount;
                    }

                    public void setSubDiscount(int subDiscount) {
                        this.subDiscount = subDiscount;
                    }
                }
            }

}
