package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.security.AES;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/11 11:03
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/11$
 * @updateDes ${TODO}
 */

public class HomeDataBean extends Result {

    /**
     * data : {"pics":[{"picture":"http://www.psytap.com:8888/resource/images/activity/6.png","id":6,"shareDescript":null,
     * "link":"http://www.psytap.com:8888/activity/webIndex?id=6","shareTitle":null,"sharePic":"http://www.psytap
     * .com:8888/resource/images/activity/6.png","type":"h5"},{"picture":"http://www.psytap.com:8888/resource/images/activity/7.png",
     * "id":7,"shareDescript":null,"link":"http://www.psytap.com:8888/activity/webIndex?id=7","shareTitle":null,"sharePic":"http://www
     * .psytap.com:8888/resource/images/activity/7.png","type":"h5"},{"picture":"http://www.psytap
     * .com:8888/resource/images/activity/8.1.png","id":8,"shareDescript":null,"link":"http://www.psytap
     * .com:8888/activity/webIndex?id=8","shareTitle":null,"sharePic":"http://www.psytap.com:8888/resource/images/activity/8.1.png",
     * "type":"h5"},{"picture":"http://www.psytap.com:8888/resource/images/activity/7.png","id":7,"shareDescript":null,"link":"http://www
     * .psytap.com:8888/activity/webIndex?id=7","shareTitle":null,"sharePic":"http://www.psytap.com:8888/resource/images/activity/7.png",
     * "type":"h5"}],"news":[{"id":1,"title":"我很帅?????","url":"http://www.baidu.com"},{"id":2,"title":"我非常帅","url":"http://www.baidu
     * .com"},{"id":3,"title":"我超级帅","url":"http://www.baidu.com"},{"id":4,"title":"我怎么这么帅？","url":"http://www.baidu.com"},{"id":5,
     * "title":"太帅怎么解决","url":"http://www.baidu.com"}],"zhibo":[{"id":49,"title":"第二堂课 沟通，不保证和谐；但不沟通，一定不和谐","status":0,
     * "countDown":1503137580000}],"recMusic":[{"picture":"http://www.psytap.com:8888/resource/images/albumPush/137.png","title":"海底轮",
     * "name":" ","descript":"身体是可见的灵魂，而灵魂是不可见的身体","musicId":137,
     * "url":"wHMNdIEZ+lEuXc+/b9mEMt6Vzl9uFxQNffWExDMscD+jK5zuTrDRLOGhKph99gGMJMC/Q7Pyp+qY\nftbqHUFO0rfNt9Klx0YGTVWblDrBwUo="}],
     * "actis":[{"host":null,"countDown":1498097196000,"status":1,"tag":2,"shareTitle":"报名 | 医学及心理学中的正念\u2014\u2014正念减压四日工作坊",
     * "sharePic":"http://www.psytap.com:8888/resource/images/activity/4/me_list_active_pic1@3x.png","beginTime":"2017-06-22",
     * "isCollect":false,"endTime":"2017-06-25","url":"http://www.psytap.com:8888/activity/webIndex?id=4","id":4,"picture":"http://www
     * .psytap.com:8888/resource/images/activity/4.1.png","title":"报名 | 医学及心理学中的正念\u2014\u2014正念减压四日工作坊","price":"3500.00",
     * "address":"深圳市中心","shareDescript":null,"signup":"http://www.psytap.com:8888/activity/signUp?id=4"},{"host":null,
     * "countDown":1499220080000,"status":1,"tag":2,"shareTitle":"报名 | 五日正念止语静修营开班啦","sharePic":"http://www.psytap
     * .com:8888/resource/images/activity/5/me_list_active_pic1@3x.png","beginTime":"2017-07-05","isCollect":false,
     * "endTime":"2017-07-09","url":"http://www.psytap.com:8888/activity/webIndex?id=5","id":5,"picture":"http://www.psytap
     * .com:8888/resource/images/activity/5.png","title":"报名 | 五日正念止语静修营开班啦","price":"3200.00","address":"广州\u201c南堤菏苑\u201d",
     * "shareDescript":"    在缺少意识的地方，想法占领了全部。若一直沉浸在自己的想法中，如何感到快乐？正念止语是平衡脑海中喋喋不休的自我意识的最佳渠道。\r\n
     * 在忙碌的当下，感受呼吸；在奔波的旅途，暂且歇息。来吧，我们在广州市郊的\u201c南堤荷苑\u201d。这里蛙鸟啼鸣、夏日清凉；这里有一群探索生命真谛的同行者，更有一位修学有成的导师\u2014\u2014何孟玲。微笑、呼吸、慢慢来\u2026\u2026
     * 与尘世风景相忘，轻轻一碰大自然，整个世界就亲昵起来。","signup":"http://www.psytap.com:8888/activity/signUp?id=5"},{"host":null,"countDown":1499479439000,
     * "status":1,"tag":2,"shareTitle":"报名 | 医学及心理学中的正念\u2014\u2014正念减压四日工作坊","sharePic":"http://www.psytap
     * .com:8888/resource/images/activity/3/me_list_active_pic1@3x.png","beginTime":"2017-07-08","isCollect":false,
     * "endTime":"2017-07-11","url":"http://www.psytap.com:8888/activity/webIndex?id=3","id":3,"picture":"http://www.psytap
     * .com:8888/resource/images/activity/3.1.png","title":"报名 | 医学及心理学中的正念\u2014\u2014正念减压四日工作坊","price":"3500.00",
     * "address":"杭州市古墩路紫金广场福泉书院","shareDescript":null,"signup":"http://www.psytap.com:8888/activity/signUp?id=3"},{"host":null,
     * "countDown":1503465555000,"status":1,"tag":1,"shareTitle":"报名 | 正念引导师培训与认证","sharePic":"http://www.psytap
     * .com:8888/resource/images/activity/7/me_list_active_pic1@3x.png","beginTime":"2017-08-23","isCollect":false,
     * "endTime":"2017-08-27","url":"http://www.psytap.com:8888/activity/webIndex?id=7","id":7,"picture":"http://www.psytap
     * .com:8888/resource/images/activity/7.png","title":"报名 | 正念引导师培训与认证","price":"3000.00","address":"广州从化翠岛度假村","shareDescript":null,
     * "signup":"http://www.psytap.com:8888/activity/signUp?id=7"},{"host":null,"countDown":1504784399000,"status":1,"tag":1,
     * "shareTitle":"报名 | 正念之舟：面向助人者的正念认知疗法（MBCT）体验工作坊","sharePic":"http://www.psytap
     * .com:8888/resource/images/activity/8/me_list_active_pic1@3x.png","beginTime":"2017-09-07","isCollect":false,
     * "endTime":"2017-09-10","url":"http://www.psytap.com:8888/activity/webIndex?id=8","id":8,"picture":"http://www.psytap
     * .com:8888/resource/images/activity/8.1.png","title":"报名 | 正念之舟：面向助人者的正念认知疗法（MBCT）体验工作坊","price":"3000.00",
     * "address":"广州越秀区水荫路119号广东文投创工场","shareDescript":null,"signup":"http://www.psytap.com:8888/activity/signUp?id=8"},{"host":null,
     * "countDown":1506828906000,"status":1,"tag":1,"shareTitle":"报名 | 医学及心理学中的正念\u2014\u2014正念减压四日工作坊","sharePic":"http://www.psytap
     * .com:8888/resource/images/activity/9/me_list_active_pic1@3x.png","beginTime":"2017-10-01","isCollect":false,
     * "endTime":"2017-10-04","url":"http://www.psytap.com:8888/activity/webIndex?id=9","id":9,"picture":"http://www.psytap
     * .com:8888/resource/images/activity/9.png","title":"报名 | 医学及心理学中的正念\u2014\u2014正念减压四日工作坊","price":"3000.00","address":"深圳梧桐山臻园",
     * "shareDescript":"你的日常生活是否充满了忙碌、压力、冲突、计划、思虑，由此引发了焦虑、抑郁、愤怒、失眠、身体疼痛等各种情绪和生理问题？","signup":"http://www.psytap
     * .com:8888/activity/signUp?id=9"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<PicsBean> pics;
        private List<NewsBean> news;
        private List<ZhiBoData> zhibo;
        private List<RecMusicBean> recMusic;
        private List<ActisData> actis;
        private List<SpecColumnData> subscription;
        private String hotWord;

        public String getHotWord() {
            return hotWord;
        }

        public void setHotWord(String hotWord) {
            this.hotWord = hotWord;
        }


        public List<SpecColumnData> getSubscription() {
            return subscription;
        }

        public void setSubscription(List<SpecColumnData> subscription) {
            this.subscription = subscription;
        }

        public List<PicsBean> getPics() {
            return pics;
        }

        public void setPics(List<PicsBean> pics) {
            this.pics = pics;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public List<ZhiBoData> getZhibo() {
            return zhibo;
        }

        public void setZhibo(List<ZhiBoData> zhibo) {
            this.zhibo = zhibo;
        }

        public List<RecMusicBean> getRecMusic() {
            return recMusic;
        }

        public void setRecMusic(List<RecMusicBean> recMusic) {
            this.recMusic = recMusic;
        }

        public List<ActisData> getActis() {
            return actis;
        }

        public void setActis(List<ActisData> actis) {
            this.actis = actis;
        }


        public static class NewsBean  extends ShareBean{
            /**
             * id : 1
             * title : 我很帅?????
             * url : http://www.baidu.com
             */

            private int id;
            private String title;
            private String url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public String toString() {
                return "NewsBean{" + "id=" + id + ", title='" + title + '\'' + ", url='" + url + '\'' + '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" + "pics=" + pics + ", news=" + news + ", zhibo=" + zhibo + ", recMusic=" + recMusic + ", actis=" + actis +
                    ", subscription=" + subscription + ", hotWord='" + hotWord + '\'' + '}';
        }

        public static class RecMusicBean {

            private String picture;
            private String title;
            private String name;
            private String descript;

            private String duration;

            private int musicId;
            private String url;
            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDescript() {
                return descript;
            }

            public void setDescript(String descript) {
                this.descript = descript;
            }

            public int getMusicId() {
                return musicId;
            }

            public void setMusicId(int musicId) {
                this.musicId = musicId;
            }

            public String getUrl() {
                if (EmptyUtils.isEmpty(url)) return "";
                return AES.get().decrypt(url);
            }

            public void setUrl(String url) {
                this.url = url;
            }

            @Override
            public String toString() {
                return "RecMusicBean{" + "picture='" + picture + '\'' + ", title='" + title + '\'' + ", name='" + name + '\'' + ", " +
                        "descript='" + descript + '\'' + ", musicId=" + musicId + ", url='" + url + '\'' + '}';
            }
        }

    }
}
