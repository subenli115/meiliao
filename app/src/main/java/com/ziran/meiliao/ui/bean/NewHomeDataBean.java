package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2019/1/14.
 */

public class NewHomeDataBean extends Result{
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        public String getHotWord() {
            return hotWord;
        }

        public void setHotWord(String hotWord) {
            this.hotWord = hotWord;
        }

        public String getHotAlbumWord() {
            return hotAlbumWord;
        }

        public void setHotAlbumWord(String hotAlbumWord) {
            this.hotAlbumWord = hotAlbumWord;
        }

        /**
         * recAlbum : {"picture":"https://www.dgli.net/resource/images/album/1.png","title":"进行中","albumName":"静观减压引导音频(粤语版)","isBuy":1,"albumId":82,"practiceStatus":1,"musicList":[{"musicId":792,"musicUrl":"http://ojlzx3sl8.bkt.clouddn.com/zhaoling2.mp3","musicName":"静坐练习","practiceStatus":0},{"musicId":793,"musicUrl":"http://ojlzx3sl8.bkt.clouddn.com/zhaoling3.mp3","musicName":"伸展练习(一)","practiceStatus":0},{"musicId":794,"musicUrl":"http://ojlzx3sl8.bkt.clouddn.com/zhaoling4.mp3","musicName":"伸展练习(二)","practiceStatus":0},{"musicId":795,"musicUrl":"http://ojlzx3sl8.bkt.clouddn.com/zhaoling5.mp3","musicName":"伸展练习(一)","practiceStatus":0},{"musicId":795,"musicUrl":"http://ojlzx3sl8.bkt.clouddn.com/zhaoling6.mp3","musicName":"伸展练习(二)","practiceStatus":0}]}
         * subscription : [{"subscriptionNum":3,"picture":"https://www.dgli.net/resource/images/subscription/course_banner_li.png","detail":"这是第一段测试文字，本来我想跟你说很多很多话，但是一切尽在不言中","title":"测试专栏1","teacher":"测试作者1","subscriptionId":1},{"subscriptionNum":10,"picture":"https://www.dgli.net/resource/images/subscription/course_banner_fangweilian.png","detail":"这是第二段测试文字，本来我想跟你说很多很多话，但是一切尽在不言中","title":"测试专栏2","teacher":"测试作者2","subscriptionId":2},{"subscriptionNum":1,"picture":"https://www.dgli.net/resource/images/subscription/course_banner_luoshuer.png","detail":"我是个码农，但是，我想拯救世界，我的心意是：世界和平","title":"测试专栏3","teacher":"测试作者3","subscriptionId":3},{"subscriptionNum":5,"picture":"https://www.dgli.net/resource/images/subscription/course_banner_sunyujing.png","detail":"后来，我发现，其实，我拯救不了世界，甚至自己都拯救不了","title":"测试专栏4","teacher":"测试作者4","subscriptionId":4}]
         * practice : [{"startTime":1547792671965,"picture":"https://www.dgli.net/resource/images/practiceActivity/1.png","id":1,"detail":"https://dgli.net/page/static/practiceBooks/MBSR/index.html","update_time":1535536462000,"status":1,"name":"MBSR八周练习","create_time":1535536464000,"booksId":1}]
         * hotAlbum : [{"picture":"https://www.dgli.net/resource/images/album/1.png","teacherIntro":"5p医学老实人","albumName":"测试专辑1","albumId":1,"subHead":"测试副标题1","teacherName":"欧其鹏1"},{"picture":"https://www.dgli.net/resource/images/album/2.png","teacherIntro":"5p医学老实人","albumName":"测试专辑2","albumId":2,"subHead":"测试副标题2","teacherName":"欧其鹏2"},{"picture":"https://www.dgli.net/resource/images/album/3.png","teacherIntro":"5p医学老实人","albumName":"测试专辑3","albumId":3,"subHead":"测试副标题3","teacherName":"欧其鹏3"},{"picture":"https://www.dgli.net/resource/images/album/4.png","teacherIntro":"5p医学老实人","albumName":"测试专辑4","albumId":4,"subHead":"测试副标题4","teacherName":"欧其鹏4"},{"picture":"https://www.dgli.net/resource/images/album/5.png","teacherIntro":"5p医学老实人","albumName":"测试专辑5","albumId":5,"subHead":"测试副标题5","teacherName":"欧其鹏5"},{"picture":"https://www.dgli.net/resource/images/album/6.png","teacherIntro":"5p医学老实人","albumName":"测试专辑6","albumId":6,"subHead":"测试副标题6","teacherName":"欧其鹏6"},{"picture":"https://www.dgli.net/resource/images/album/7.png","teacherIntro":"5p医学老实人","albumName":"测试专辑7","albumId":7,"subHead":"测试副标题7","teacherName":"欧其鹏7"},{"picture":"https://www.dgli.net/resource/images/album/8.png","teacherIntro":"5p医学老实人","albumName":"测试专辑8","albumId":8,"subHead":"测试副标题8","teacherName":"欧其鹏8"}]
         * recActivity : [{}]
         * activity : [{"picture":"https://www.dgli.net/resource/images/subscription/course_banner_li.png","activityId":36,"time":"2019-01-18","price":2800,"remain":7,"address":"欧其鹏家里","tag":"热门","teacher":"测试作者1","activityName":"测试课程1"},{"picture":"https://www.dgli.net/resource/images/subscription/course_banner_li.png","activityId":38,"time":"2019-01-18","price":38000,"remain":2,"address":"欧其鹏家里","tag":"热门","teacher":"测试作者2","activityName":"测试课程2"},{"picture":"https://www.dgli.net/resource/images/subscription/course_banner_li.png","activityId":39,"time":"2019-01-18","price":28000,"remain":12,"address":"欧其鹏家里","tag":"","teacher":"测试作者3","activityName":"测试课程3"}]
         */

        private String hotAlbumWord;
        private String hotWord;
        private RecAlbumBean recAlbum;
        private List<SubscriptionBean> subscription;
        private List<PracticeBean> practice;
        private List<HotAlbumBean> hotAlbum;
        private List<RecActivityBean> recActivity;
        private List<ActivityBean> activity;

        public RecAlbumBean getRecAlbum() {
            return recAlbum;
        }

        public void setRecAlbum(RecAlbumBean recAlbum) {
            this.recAlbum = recAlbum;
        }

        public List<SubscriptionBean> getSubscription() {
            return subscription;
        }

        public void setSubscription(List<SubscriptionBean> subscription) {
            this.subscription = subscription;
        }

        public List<PracticeBean> getPractice() {
            return practice;
        }

        public void setPractice(List<PracticeBean> practice) {
            this.practice = practice;
        }

        public List<HotAlbumBean> getHotAlbum() {
            return hotAlbum;
        }

        public void setHotAlbum(List<HotAlbumBean> hotAlbum) {
            this.hotAlbum = hotAlbum;
        }

        public List<RecActivityBean> getRecActivity() {
            return recActivity;
        }

        public void setRecActivity(List<RecActivityBean> recActivity) {
            this.recActivity = recActivity;
        }

        public List<ActivityBean> getActivity() {
            return activity;
        }

        public void setActivity(List<ActivityBean> activity) {
            this.activity = activity;
        }

        public static class RecAlbumBean {

            /**
             * picture : https://www.dgli.net/resource/images/album/1.png
             * title : 进行中
             * albumName : 静观减压引导音频(粤语版)
             * albumId : 82
             * musicId : 792
             * musicUrl : http://ojlzx3sl8.bkt.clouddn.com/zhaoling2.mp3
             * musicName : 静坐练习
             * practiceStatus : 0
             * musicList : [{"musicId":792,"musicUrl":"http://ojlzx3sl8.bkt.clouddn.com/zhaoling2.mp3","musicName":"静坐练习","practiceStatus":0},{"musicId":793,"musicUrl":"http://ojlzx3sl8.bkt.clouddn.com/zhaoling3.mp3","musicName":"伸展练习(一)","practiceStatus":0},{"musicId":794,"musicUrl":"http://ojlzx3sl8.bkt.clouddn.com/zhaoling4.mp3","musicName":"伸展练习(二)","practiceStatus":0},{"musicId":795,"musicUrl":"http://ojlzx3sl8.bkt.clouddn.com/zhaoling5.mp3","musicName":"伸展练习(一)","practiceStatus":0},{"musicId":795,"musicUrl":"http://ojlzx3sl8.bkt.clouddn.com/zhaoling6.mp3","musicName":"伸展练习(二)","practiceStatus":0}]
             */

            private String picture;
            private String title;
            private String albumName;
            private int albumId;
            private int musicId;
            private String musicUrl;
            private String musicName;
            private int practiceStatus;
            private List<MusicListBean> musicList;

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAlbumName() {
                return albumName;
            }

            public void setAlbumName(String albumName) {
                this.albumName = albumName;
            }

            public int getAlbumId() {
                return albumId;
            }

            public void setAlbumId(int albumId) {
                this.albumId = albumId;
            }

            public int getMusicId() {
                return musicId;
            }

            public void setMusicId(int musicId) {
                this.musicId = musicId;
            }

            public String getMusicUrl() {
                return musicUrl;
            }

            public void setMusicUrl(String musicUrl) {
                this.musicUrl = musicUrl;
            }

            public String getMusicName() {
                return musicName;
            }

            public void setMusicName(String musicName) {
                this.musicName = musicName;
            }

            public int getPracticeStatus() {
                return practiceStatus;
            }

            public void setPracticeStatus(int practiceStatus) {
                this.practiceStatus = practiceStatus;
            }

            public List<MusicListBean> getMusicList() {
                return musicList;
            }

            public void setMusicList(List<MusicListBean> musicList) {
                this.musicList = musicList;
            }

            public static class MusicListBean {
                /**
                 * musicId : 792
                 * musicUrl : http://ojlzx3sl8.bkt.clouddn.com/zhaoling2.mp3
                 * musicName : 静坐练习
                 * practiceStatus : 0
                 */

                private int musicId;
                private String musicUrl;
                private String musicName;
                private int practiceStatus;

                public int getMusicId() {
                    return musicId;
                }

                public void setMusicId(int musicId) {
                    this.musicId = musicId;
                }

                public String getMusicUrl() {
                    return musicUrl;
                }

                public void setMusicUrl(String musicUrl) {
                    this.musicUrl = musicUrl;
                }

                public String getMusicName() {
                    return musicName;
                }

                public void setMusicName(String musicName) {
                    this.musicName = musicName;
                }

                public int getPracticeStatus() {
                    return practiceStatus;
                }

                public void setPracticeStatus(int practiceStatus) {
                    this.practiceStatus = practiceStatus;
                }
            }
        }

        public static class SubscriptionBean {



            /**
             * subscriptionNum : 3
             * picture : https://www.dgli.net/resource/images/subscription/course_banner_li.png
             * detail : 这是第一段测试文字，本来我想跟你说很多很多话，但是一切尽在不言中
             * title : 测试专栏1
             * teacher : 测试作者1
             * subscriptionId : 1
             */
            private String htmlLink;
            private boolean isBuy;
            private int subscriptionNum;
            private String picture;
            private String detail;
            private String title;
            private String teacher;
            private int subscriptionId;

            public String getHtmlLink() {
                return htmlLink;
            }

            public void setHtmlLink(String htmlLink) {
                this.htmlLink = htmlLink;
            }

            public boolean isBuy() {
                return isBuy;
            }

            public void setBuy(boolean buy) {
                isBuy = buy;
            }

            public int getSubscriptionNum() {
                return subscriptionNum;
            }

            public void setSubscriptionNum(int subscriptionNum) {
                this.subscriptionNum = subscriptionNum;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
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

            public String getTeacher() {
                return teacher;
            }

            public void setTeacher(String teacher) {
                this.teacher = teacher;
            }

            public int getSubscriptionId() {
                return subscriptionId;
            }

            public void setSubscriptionId(int subscriptionId) {
                this.subscriptionId = subscriptionId;
            }
        }

        public static class PracticeBean {
            /**
             * startTime : 1547792671965
             * picture : https://www.dgli.net/resource/images/practiceActivity/1.png
             * id : 1
             * detail : https://dgli.net/page/static/practiceBooks/MBSR/index.html
             * update_time : 1535536462000
             * status : 1
             * name : MBSR八周练习
             * create_time : 1535536464000
             * booksId : 1
             */

            private String startTime;
            private String picture;
            private int id;
            private String detail;
            private long update_time;
            private int status;
            private String name;
            private long create_time;
            private int booksId;

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public long getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(long update_time) {
                this.update_time = update_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public int getBooksId() {
                return booksId;
            }

            public void setBooksId(int booksId) {
                this.booksId = booksId;
            }
        }

        public static class HotAlbumBean {

            /**
             * picture : https://www.dgli.net/resource/images/album/1.png
             * teacherIntro : 5p医学老实人
             * albumName : 测试专辑1
             * albumId : 1
             * tagName : 热门
             * subHead : 测试副标题1
             * teacherName : 欧其鹏1
             * tagId : 1
             */

            private String picture;
            private String teacherIntro;
            private String albumName;
            private int albumId;
            private String tagName;
            private String subHead;
            private String teacherName;
            private String tagId;

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getTeacherIntro() {
                return teacherIntro;
            }

            public void setTeacherIntro(String teacherIntro) {
                this.teacherIntro = teacherIntro;
            }

            public String getAlbumName() {
                return albumName;
            }

            public void setAlbumName(String albumName) {
                this.albumName = albumName;
            }

            public int getAlbumId() {
                return albumId;
            }

            public void setAlbumId(int albumId) {
                this.albumId = albumId;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }

            public String getSubHead() {
                return subHead;
            }

            public void setSubHead(String subHead) {
                this.subHead = subHead;
            }

            public String getTeacherName() {
                return teacherName;
            }

            public void setTeacherName(String teacherName) {
                this.teacherName = teacherName;
            }

            public String getTagId() {
                return tagId;
            }

            public void setTagId(String tagId) {
                this.tagId = tagId;
            }
        }

        public static class RecActivityBean {

            /**
             * picture : https://www.dgli.net/resource/images/activity/act_banner_bj0802.png
             * tagName : 热门
             * tagId : 1
             * url : http://dgli.net/page/static/conference/conferenceHome.html
             */

            private String picture;
            private String tagName;
            private String tagId;
            private String url;
            private String shareUrl;
            private String sharePicture;
            /**
             * detail :
             * shareTitle : 全民正念
             * recId : 1
             */

            private String detail;
            private String shareTitle;
            private String recId;

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getSharePicture() {
                return sharePicture;
            }

            public void setSharePicture(String sharePicture) {
                this.sharePicture = sharePicture;
            }

            public String getShareDescript() {
                return shareDescript;
            }

            public void setShareDescript(String shareDescript) {
                this.shareDescript = shareDescript;
            }

            private String shareDescript;

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }

            public String getTagId() {
                return tagId;
            }

            public void setTagId(String tagId) {
                this.tagId = tagId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getShareTitle() {
                return shareTitle;
            }

            public void setShareTitle(String shareTitle) {
                this.shareTitle = shareTitle;
            }

            public String getRecId() {
                return recId;
            }

            public void setRecId(String recId) {
                this.recId = recId;
            }
        }

        public static class ActivityBean {

            /**
             * picture : https://www.dgli.net/resource/images/subscription/course_banner_li.png
             * activityId : 36
             * time : 2019-01-19
             * price : 2800
             * remain : 7
             * address : 欧其鹏家里
             * tagName : 热门
             * teacher : 测试作者1
             * tagId : 1
             * activityName : 测试课程1
             */

            private String picture;
            private int activityId;
            private String time;
            private int price;
            private int remain;
            private String address;
            private String tagName;
            private String teacher;
            private String tagId;
            private String url;
            private String activityName;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public int getActivityId() {
                return activityId;
            }

            public void setActivityId(int activityId) {
                this.activityId = activityId;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getRemain() {
                return remain;
            }

            public void setRemain(int remain) {
                this.remain = remain;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }

            public String getTeacher() {
                return teacher;
            }

            public void setTeacher(String teacher) {
                this.teacher = teacher;
            }

            public String getTagId() {
                return tagId;
            }

            public void setTagId(String tagId) {
                this.tagId = tagId;
            }

            public String getActivityName() {
                return activityName;
            }

            public void setActivityName(String activityName) {
                this.activityName = activityName;
            }
        }
    }
}
