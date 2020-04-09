package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/30 16:24
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/30$
 * @updateDes ${TODO}
 */

public class SpceColumnBean extends Result {

    /**
     * data : {"author":{"headImg":"1d969df56ea24dbab3c803a8f1cfb647.jpeg","teaIntro":"强大源于装逼","name":"陈博士"},"buyCount":1,
     * "detail":"许多朋友为装B装的不好而痛苦。虽然有人推荐看格调，但只看了书，不精通，却也无法装的痛快淋漓。现在，我教大家一个简单易学的办法。\r\n原则（1）：能用英文绝不用汉语。说东西的时候一定要把一样东西的牌子和产地都一起说出来，不论有多么别扭。\r
     * \n正常人版：\r\n一个穿着拖鞋的委琐男，去商店买了瓶一块钱的乐百氏矿泉水和5角钱的纸巾，然后喝了一口，擦擦嘴，开始考虑一会是去地下商场买那条12块的大裤衩呢？还是13块的大裤衩呢？\r\n　　\r\n装B版：\r\n
     * 下午的时候，觉得有些渴了。就去买了Robust的纯净水。轻轻的喝一口，味道的确比Wahaha的要好些。看来我最钟爱的牌子，果然是没错的。用Pure的Handkerchief擦了擦嘴角以后，我开始在两个同样的钟爱的品牌中摇摆不定：是去买佳侬新出的Wolfboy
     * 限量版呢，还是选择 Keaoor的呢？\r\n原则（2）：凡是能说译名的，有舶来音的，绝对不说中文名。能用外国的单位不用中国的单位！\r\n　　","title":"如何成为装逼之王","isGz":false,"price":199,
     * "shareDescript":"许多朋友为装B装的不好而痛苦。虽然有人推荐看格调，但只看了书，不精通，却也无法装的痛快淋漓。现在，我","shareUrl":"http://www.baidu.com","shareTitle":"如何成为装逼之王",
     * "pic":"35.png","seeCount":266,"refreshList":[{"id":3,"createTime":1504076926000,"title":"如何正确的装逼?","tag":"audio"},{"id":4,
     * "createTime":1504077259000,"title":"第一堂课 知己知彼","tag":"course"}]}
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
         * author : {"headImg":"1d969df56ea24dbab3c803a8f1cfb647.jpeg","teaIntro":"强大源于装逼","name":"陈博士"}
         * buyCount : 1
         * detail : 许多朋友为装B装的不好而痛苦。虽然有人推荐看格调，但只看了书，不精通，却也无法装的痛快淋漓。现在，我教大家一个简单易学的办法。
         * 原则（1）：能用英文绝不用汉语。说东西的时候一定要把一样东西的牌子和产地都一起说出来，不论有多么别扭。
         * 正常人版：
         * 一个穿着拖鞋的委琐男，去商店买了瓶一块钱的乐百氏矿泉水和5角钱的纸巾，然后喝了一口，擦擦嘴，开始考虑一会是去地下商场买那条12块的大裤衩呢？还是13块的大裤衩呢？
         * <p>
         * 装B版：
         * 下午的时候，觉得有些渴了。就去买了Robust的纯净水。轻轻的喝一口，味道的确比Wahaha的要好些。看来我最钟爱的牌子，果然是没错的。用Pure的Handkerchief
         * 擦了擦嘴角以后，我开始在两个同样的钟爱的品牌中摇摆不定：是去买佳侬新出的Wolfboy限量版呢，还是选择 Keaoor的呢？
         * 原则（2）：凡是能说译名的，有舶来音的，绝对不说中文名。能用外国的单位不用中国的单位！
         * <p>
         * title : 如何成为装逼之王
         * isGz : false
         * price : 199
         * pic : 35.png
         * seeCount : 266
         * refreshList : [{"id":3,"createTime":1504076926000,"title":"如何正确的装逼?","tag":"audio"},{"id":4,"createTime":1504077259000,
         * "title":"第一堂课 知己知彼","tag":"course"}]
         */


        private List<ZhuanLanBigInBean.DataBean.DirBean> dir;
        private AuthorBean author;
        private SearchAllResultBean.DataBean recListMap;
        private SearchAllResultBean.DataBean recMap;
        private String subscribeWord;

        private int memberPrice;
        private int isGetCertificate;
        private String courseHtml;

        public String getCourseHtml() {
            return courseHtml;
        }

        public void setCourseHtml(String courseHtml) {
            this.courseHtml = courseHtml;
        }
        private boolean isGz;
        private String detail;
        private int finishTimes;
        private boolean isBuy;
        private int userCoin;
        private long endTime;
        private String title;
        private int courseNumbers;
        private int needCoin;
        private String secondWorld;
        private int buyCount;
        private int totalProgress;
        private String thirdWorld;
        private String firstWorld;
        private long beginTime;
        private String pic;
        private int seeCount;
        private float price;
        private int isGetCoupon;
        private int shareTimes;

        private String htmlLink;

        public String getHtmlLink() {
            return htmlLink;
        }

        public void setHtmlLink(String htmlLink) {
            this.htmlLink = htmlLink;
        }

        public int getMemberPrice() {
            return memberPrice;
        }

        public void setMemberPrice(int memberPrice) {
            this.memberPrice = memberPrice;
        }
        public String getLevelDetail() {
            return levelDetail;
        }

        public void setLevelDetail(String levelDetail) {
            this.levelDetail = levelDetail;
        }

        private String levelDetail;
        public String getSubscribeWord() {
            return subscribeWord;
        }

        public void setSubscribeWord(String subscribeWord) {
            this.subscribeWord = subscribeWord;
        }

        public String getFirstWorld() {
            return firstWorld;
        }

        public void setFirstWorld(String firstWorld) {
            this.firstWorld = firstWorld;
        }

        public String getSecondWorld() {
            return secondWorld;
        }

        public void setSecondWorld(String secondWorld) {
            this.secondWorld = secondWorld;
        }

        public String getThirdWorld() {
            return thirdWorld;
        }

        public void setThirdWorld(String thirdWorld) {
            this.thirdWorld = thirdWorld;
        }

        public SearchAllResultBean.DataBean getRecListMap() {
            return recListMap;
        }

        public void setRecListMap(SearchAllResultBean.DataBean recListMap) {
            this.recListMap = recListMap;
        }

        public int getIsGetCoupon() {
            return isGetCoupon;
        }

        public void setIsGetCoupon(int isGetCoupon) {
            this.isGetCoupon = isGetCoupon;
        }

        public int getIsGetCertificate() {
            return isGetCertificate;
        }

        public void setIsGetCertificate(int isGetCertificate) {
            this.isGetCertificate = isGetCertificate;
        }

        public int getFinishTimes() {
            return finishTimes;
        }

        public void setFinishTimes(int finishTimes) {
            this.finishTimes = finishTimes;
        }

        public int getShareTimes() {
            return shareTimes;
        }


        public void setShareTimes(int shareTimes) {
            this.shareTimes = shareTimes;
        }

        public int isGetCertificate() {
            return isGetCertificate;
        }

        public void setGetCertificate(int getCertificate) {
            isGetCertificate = getCertificate;
        }

        public int getTotalProgress() {
            return totalProgress;
        }

        public void setTotalProgress(int totalProgress) {
            this.totalProgress = totalProgress;
        }

        public boolean isGz() {
            return isGz;
        }

        public void setGz(boolean gz) {
            isGz = gz;
        }

        public long getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(long beginTime) {
            this.beginTime = beginTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public SearchAllResultBean.DataBean getRecMap() {
            return recMap;
        }

        public void setRecMap(SearchAllResultBean.DataBean recMap) {
            this.recMap = recMap;
        }

        public int getCourseNumbers() {
            return courseNumbers;
        }

        public void setCourseNumbers(int courseNumbers) {
            this.courseNumbers = courseNumbers;
        }

        public static class RecMapBean {

        }

        public List<ZhuanLanBigInBean.DataBean.DirBean> getDir() {
            return dir;
        }

        public void setDir(List<ZhuanLanBigInBean.DataBean.DirBean> dir) {
            this.dir = dir;
        }

        public boolean isBuy() {
            return isBuy;
        }

        public void setBuy(boolean buy) {
            isBuy = buy;
        }

        public int getNeedCoin() {
            return needCoin;
        }

        public void setNeedCoin(int needCoin) {
            this.needCoin = needCoin;
        }

        public int getUserCoin() {
            return userCoin;
        }

        public void setUserCoin(int userCoin) {
            this.userCoin = userCoin;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public int getBuyCount() {
            return buyCount;
        }

        public void setBuyCount(int buyCount) {
            this.buyCount = buyCount;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isIsGz() {
            return isGz;
        }

        public void setIsGz(boolean isGz) {
            this.isGz = isGz;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getSeeCount() {
            return seeCount;
        }

        public void setSeeCount(int seeCount) {
            this.seeCount = seeCount;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "dir=" + dir +
                    ", author=" + author +
                    ", recListMap=" + recListMap +
                    ", recMap=" + recMap +
                    ", subscribeWord='" + subscribeWord + '\'' +
                    ", isGetCertificate=" + isGetCertificate +
                    ", isGz=" + isGz +
                    ", detail='" + detail + '\'' +
                    ", finishTimes=" + finishTimes +
                    ", isBuy=" + isBuy +
                    ", userCoin=" + userCoin +
                    ", endTime=" + endTime +
                    ", title='" + title + '\'' +
                    ", courseNumbers=" + courseNumbers +
                    ", needCoin=" + needCoin +
                    ", secondWorld='" + secondWorld + '\'' +
                    ", buyCount=" + buyCount +
                    ", totalProgress=" + totalProgress +
                    ", thirdWorld='" + thirdWorld + '\'' +
                    ", firstWorld='" + firstWorld + '\'' +
                    ", beginTime=" + beginTime +
                    ", pic='" + pic + '\'' +
                    ", seeCount=" + seeCount +
                    ", price=" + price +
                    ", isGetCoupon=" + isGetCoupon +
                    ", shareTimes=" + shareTimes +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "SpceColumnBean{" +
                "data=" + data +
                '}';
    }
}
