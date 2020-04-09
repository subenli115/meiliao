package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.security.AES;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/19 10:07
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/19$
 * @updateDes ${TODO}
 */

public class ZhuanLanBigInBean extends Result {

    /**
     * data : {"author":{"teaIntro":"","name":"陈"},"title":"如何成为装逼之王","isGz":false,"price":9,"isBuy":true,
     * "dir":[{"startTime":"2017-07-31","progress":"0%","duration":"00:23:50","detail":"","title":"第一堂课 知己知彼","studyStatus":0,"st":false,
     * "type":1,"isNewest":false,"url":"7O2Wuz2ugoKz7iFekYidz46Qmfcb2NrPzry+8U1r3As5JxBWmX4L18EXLQ6P0gwibLHOSjkhMsvL\n4NM
     * /5wzyrJCpAiulQwBcMwfrlQEy0DM=","studyCount":6}],"userCoin":"8420","pic":"http://www.dgli
     * .net:8888/resource/images/subscription/35.png","needCoin":90}
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
        return "ZhuanLanBigInBean{" + "data=" + data + '}';
    }

    public static class DataBean extends ShareBean {
        /**
         * author : {"teaIntro":"","name":"陈"}
         * title : 如何成为装逼之王
         * isGz : false
         * price : 9.0
         * isBuy : true
         * dir : [{"startTime":"2017-07-31","progress":"0%","duration":"00:23:50","detail":"","title":"第一堂课 知己知彼","studyStatus":0,
         * "st":false,"type":1,"isNewest":false,"url":"7O2Wuz2ugoKz7iFekYidz46Qmfcb2NrPzry+8U1r3As5JxBWmX4L18EXLQ6P0gwibLHOSjkhMsvL\n4NM
         * /5wzyrJCpAiulQwBcMwfrlQEy0DM=","studyCount":6}]
         * userCoin : 8420
         * pic : http://www.dgli.net:8888/resource/images/subscription/35.png
         * needCoin : 90
         */

        private AuthorBean author;
        private String title;
        private boolean isGz;
        private double price;
        private boolean isBuy;
        private int userCoin;
        private String pic;
        private int needCoin;
        private List<DirBean> dir;
        private String detail;
        private String prefix;

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getDetail() {
            return detail;
        }


        public void setDetail(String detail) {
            this.detail = detail;
        }

        @Override
        public String toString() {
            return "DataBean{" + "author=" + author + ", title='" + title + '\'' + ", isGz=" + isGz + ", price=" + price + ", isBuy=" +
                    isBuy + ", userCoin='" + userCoin + '\'' + ", pic='" + pic + '\'' + ", needCoin=" + needCoin + ", dir=" + dir + '}';
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public boolean isIsBuy() {
            return isBuy;
        }

        public void setIsBuy(boolean isBuy) {
            this.isBuy = isBuy;
        }

        public int getUserCoin() {
            return userCoin;
        }

        public void setUserCoin(int userCoin) {
            this.userCoin = userCoin;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getNeedCoin() {
            return needCoin;
        }

        public void setNeedCoin(int needCoin) {
            this.needCoin = needCoin;
        }

        public List<DirBean> getDir() {
            return dir;
        }

        public void setDir(List<DirBean> dir) {
            this.dir = dir;
        }


        public static class DirBean {
            /**
             * startTime : 2017-07-31
             * progress : 0%
             * duration : 00:23:50
             * detail :
             * title : 第一堂课 知己知彼
             * studyStatus : 0
             * st : false
             * type : 1
             *
             * isNewest : false
             * url : 7O2Wuz2ugoKz7iFekYidz46Qmfcb2NrPzry+8U1r3As5JxBWmX4L18EXLQ6P0gwibLHOSjkhMsvL
             * 4NM/5wzyrJCpAiulQwBcMwfrlQEy0DM=
             * studyCount : 6
             */


            private String detail;
            private boolean check;
            private String mouth;
            private String day;

            private String progress;
            private int studyStatus;
            private int status;
            private int tag;
            private String targetId;
            private int type;
            private String roundPic;
            private boolean isNewest;
            private String url;
            private int studyCount;
            private String startTime;
            private String title;
            private String duration;
            private boolean st;


            public int getTag() {
                return tag;
            }

            public void setTag(int tag) {
                this.tag = tag;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setMouth(String mouth) {
                this.mouth = mouth;
            }

            public String getMouth() {
                return mouth;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public String getTargetId() {
                return targetId;
            }

            public void setTargetId(String targetId) {
                this.targetId = targetId;
            }

            public String getRoundPic() {
                return roundPic;
            }

            public void setRoundPic(String roundPic) {
                this.roundPic = roundPic;
            }

            public boolean isCheck() {
                return check;
            }

            public void setCheck(boolean check) {
                this.check = check;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getProgress() {
                return progress;
            }

            public void setProgress(String progress) {
                this.progress = progress;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
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

            public int getStudyStatus() {
                return studyStatus;
            }

            public void setStudyStatus(int studyStatus) {
                this.studyStatus = studyStatus;
            }

            public boolean isSt() {
                return st;
            }

            public void setSt(boolean st) {
                this.st = st;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public boolean isIsNewest() {
                return isNewest;
            }

            public void setIsNewest(boolean isNewest) {
                this.isNewest = isNewest;
            }

            public String getUrl() {
                return url;
            }

            public String getAESUrl() {
                return EmptyUtils.isEmpty(url) ? "" : AES.get().decrypt(url);
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getStudyCount() {
                return studyCount;
            }

            @Override
            public String toString() {
                return "DirBean{" + "targetId='" + targetId + '\'' + ", startTime='" + startTime + '\'' + ", progress='" + progress +
                        '\'' + ", duration='" + duration + '\'' + ", detail='" + detail + '\'' + ", title='" + title + '\'' + ", " +
                        "studyStatus=" + studyStatus + ", st=" + st + ", type=" + type + ", isNewest=" + isNewest + ", url='" + url +
                        '\'' + ", studyCount=" + studyCount + ", check=" + check + ", roundPic='" + roundPic + '\'' + '}';
            }

            public void setStudyCount(int studyCount) {
                this.studyCount = studyCount;
            }


        }
    }
}
