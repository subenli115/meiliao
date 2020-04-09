package com.ziran.meiliao.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2019/1/28.
 */

public class AlbumMoreBean  extends Result {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public interface Type {
        int TITLE = 0x2214;
        int HEAL= 0x2215;
        int ZHUANZHU=0x2216;
        int LATELY=0x2217;
        int INSOMNIA=0x2218;
    }
    public static class DataBean {

        /**
         * tagList : [{"picture":"https://www.dgli.net/resource/images/album/albumTag/sos.png","id":1,"name":"情绪sos","nextTagList":[{"nextTagId":1,"nextTagName":"愤怒","nextTagPicture":"https://www.dgli.net/resource/images/album/albumTag/anger.png"},{"nextTagId":2,"nextTagName":"恐惧","nextTagPicture":"https://www.dgli.net/resource/images/album/albumTag/terrible.png"},{"nextTagId":3,"nextTagName":"悲伤","nextTagPicture":"https://www.dgli.net/resource/images/album/albumTag/sad.png"},{"nextTagId":4,"nextTagName":"焦虑","nextTagPicture":"https://www.dgli.net/resource/images/album/albumTag/bored.png"},{"nextTagId":5,"nextTagName":"惊恐","nextTagPicture":"https://www.dgli.net/resource/images/album/albumTag/fear.png"},{"nextTagId":6,"nextTagName":"思慕","nextTagPicture":"https://www.dgli.net/resource/images/album/albumTag/miss.png"}]},{"picture":"https://www.dgli.net/resource/images/album/albumTag/sos.png","id":2,"name":"减压","nextTagList":[]},{"picture":"https://www.dgli.net/resource/images/album/albumTag/sos.png","id":3,"name":"助眠","nextTagList":[]}]
         * albumList : [{"tagName":"减压","detailList":[{"picture":"https://www.dgli.net/resource/images/album/1.png","teacherIntro":"5p医学老实人","albumName":"测试专辑1","albumId":1,"tagName":"热门","subHead":"测试副标题1","teacherName":"欧其鹏1","tagId":"1"},{"picture":"https://www.dgli.net/resource/images/album/2.png","teacherIntro":"5p医学老实人","albumName":"测试专辑2","albumId":2,"tagName":"热门","subHead":"测试副标题2","teacherName":"欧其鹏2","tagId":"1"}],"tagId":1},{"tagName":"助眠","detailList":[{"picture":"https://www.dgli.net/resource/images/album/3.png","teacherIntro":"5p医学老实人","albumName":"测试专辑3","albumId":3,"tagName":"推荐","subHead":"测试副标题3","teacherName":"欧其鹏3","tagId":"2"},{"picture":"https://www.dgli.net/resource/images/album/4.png","teacherIntro":"5p医学老实人","albumName":"测试专辑4","albumId":4,"tagName":"推荐","subHead":"测试副标题4","teacherName":"欧其鹏4","tagId":"2"}],"tagId":2},{"tagName":"专注","detailList":[{"picture":"https://www.dgli.net/resource/images/album/5.png","teacherIntro":"5p医学老实人","albumName":"测试专辑5","albumId":5,"tagName":"猜你喜欢","subHead":"测试副标题5","teacherName":"欧其鹏5","tagId":"3"},{"picture":"https://www.dgli.net/resource/images/album/6.png","teacherIntro":"5p医学老实人","albumName":"测试专辑6","albumId":6,"tagName":"猜你喜欢","subHead":"测试副标题6","teacherName":"欧其鹏6","tagId":"3"}],"tagId":3},{"tagName":"疗愈","detailList":[{"picture":"https://www.dgli.net/resource/images/album/7.png","teacherIntro":"5p医学老实人","albumName":"测试专辑7","albumId":7,"tagName":"","subHead":"测试副标题7","teacherName":"欧其鹏7","tagId":""},{"picture":"https://www.dgli.net/resource/images/album/8.png","teacherIntro":"5p医学老实人","albumName":"测试专辑8","albumId":8,"tagName":"","subHead":"测试副标题8","teacherName":"欧其鹏8","tagId":""}],"tagId":4}]
         * recAlbumMusic : {"picture":"https://www.dgli.net/resource/images/albumPush/music_morning1.png","duration":"00:5:20","shareDescript":"宽恕是放下过去的事，知道即使有些事是错的，唯有重新开始，才能继续活下去。","name":"晨起祈愿文","musicId":740,"notice":"宽恕是放下过去的事，知道即使有些事是错的，唯有重新开始，才能继续活下去。","url":"http://ojlzx3sl8.bkt.clouddn.com/41d8f25edeff4f5ca040d2a817744bdeyulqaf.mp3"}
         */

        private RecAlbumMusicBean recAlbumMusic;
        private List<TagListBean> tagList;
        private List<AlbumListBean> albumList;

        public RecAlbumMusicBean getRecAlbumMusic() {
            return recAlbumMusic;
        }

        public void setRecAlbumMusic(RecAlbumMusicBean recAlbumMusic) {
            this.recAlbumMusic = recAlbumMusic;
        }

        public List<TagListBean> getTagList() {
            return tagList;
        }

        public void setTagList(List<TagListBean> tagList) {
            this.tagList = tagList;
        }

        public List<AlbumListBean> getAlbumList() {
            return albumList;
        }

        public void setAlbumList(List<AlbumListBean> albumList) {
            this.albumList = albumList;
        }

        public static class RecAlbumMusicBean {
            /**
             * picture : https://www.dgli.net/resource/images/albumPush/music_morning1.png
             * duration : 00:5:20
             * shareDescript : 宽恕是放下过去的事，知道即使有些事是错的，唯有重新开始，才能继续活下去。
             * name : 晨起祈愿文
             * musicId : 740
             * notice : 宽恕是放下过去的事，知道即使有些事是错的，唯有重新开始，才能继续活下去。
             * url : http://ojlzx3sl8.bkt.clouddn.com/41d8f25edeff4f5ca040d2a817744bdeyulqaf.mp3
             */

            private String picture;
            private String duration;
            private String name;
            private int musicId;
            private String notice;
            private String url;
            /**
             * shareUrl :
             * shareTitle : 测试推荐音频
             * sharePic : https://www.dgli.net/resource/images/albumPush/music_morning1.png
             * shareDescript  : 宽恕是放下过去的事，知道即使有些事是错的，唯有重新开始，才能继续活下去。
             */

            private String shareUrl;
            private String shareTitle;
            private String sharePic;
            private String shareDescript;

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }


            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getMusicId() {
                return musicId;
            }

            public void setMusicId(int musicId) {
                this.musicId = musicId;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
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

            public String getShareDescript() {
                return shareDescript;
            }

            public void setShareDescript(String shareDescript) {
                this.shareDescript = shareDescript;
            }
        }

        public static class TagListBean {
            /**
             * picture : https://www.dgli.net/resource/images/album/albumTag/sos.png
             * id : 1
             * name : 情绪sos
             * nextTagList : [{"nextTagId":1,"nextTagName":"愤怒","nextTagPicture":"https://www.dgli.net/resource/images/album/albumTag/anger.png"},{"nextTagId":2,"nextTagName":"恐惧","nextTagPicture":"https://www.dgli.net/resource/images/album/albumTag/terrible.png"},{"nextTagId":3,"nextTagName":"悲伤","nextTagPicture":"https://www.dgli.net/resource/images/album/albumTag/sad.png"},{"nextTagId":4,"nextTagName":"焦虑","nextTagPicture":"https://www.dgli.net/resource/images/album/albumTag/bored.png"},{"nextTagId":5,"nextTagName":"惊恐","nextTagPicture":"https://www.dgli.net/resource/images/album/albumTag/fear.png"},{"nextTagId":6,"nextTagName":"思慕","nextTagPicture":"https://www.dgli.net/resource/images/album/albumTag/miss.png"}]
             */

            private String picture;
            private int id;
            private int tagId;

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
            }

            private String name;
            private List<NextTagListBean> nextTagList;

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<NextTagListBean> getNextTagList() {
                return nextTagList;
            }

            public void setNextTagList(List<NextTagListBean> nextTagList) {
                this.nextTagList = nextTagList;
            }

            public static class NextTagListBean implements Parcelable {
                /**
                 * nextTagId : 1
                 * nextTagName : 愤怒
                 * nextTagPicture : https://www.dgli.net/resource/images/album/albumTag/anger.png
                 */

                private int nextTagId;
                private String nextTagName;
                private String nextTagPicture;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                private String url;

                public static final Creator<NextTagListBean> CREATOR = new Creator<NextTagListBean>() {
                    @Override
                    public NextTagListBean createFromParcel(Parcel in) {
                        return new NextTagListBean(in);
                    }

                    @Override
                    public NextTagListBean[] newArray(int size) {
                        return new NextTagListBean[size];
                    }
                };

                public int getNextTagId() {
                    return nextTagId;
                }

                public void setNextTagId(int nextTagId) {
                    this.nextTagId = nextTagId;
                }

                public String getNextTagName() {
                    return nextTagName;
                }

                public void setNextTagName(String nextTagName) {
                    this.nextTagName = nextTagName;
                }

                public String getNextTagPicture() {
                    return nextTagPicture;
                }

                public void setNextTagPicture(String nextTagPicture) {
                    this.nextTagPicture = nextTagPicture;
                }

                @Override
                public int describeContents() {
                    return 0;
                }


                protected NextTagListBean(Parcel in) {
                    this.nextTagName = in.readString();
                    this.nextTagId = in.readInt();
                    this.nextTagPicture = in.readString();
                    this.url=in.readString();
                }

                @Override
                public void writeToParcel(Parcel dest, int i) {

                    dest.writeString(this.nextTagName);
                    dest.writeInt(this.nextTagId);
                    dest.writeString(this.nextTagPicture);
                    dest.writeString(this.url);
                }
            }
        }

        public static class AlbumListBean {
            /**
             * tagName : 减压
             * detailList : [{"picture":"https://www.dgli.net/resource/images/album/1.png","teacherIntro":"5p医学老实人","albumName":"测试专辑1","albumId":1,"tagName":"热门","subHead":"测试副标题1","teacherName":"欧其鹏1","tagId":"1"},{"picture":"https://www.dgli.net/resource/images/album/2.png","teacherIntro":"5p医学老实人","albumName":"测试专辑2","albumId":2,"tagName":"热门","subHead":"测试副标题2","teacherName":"欧其鹏2","tagId":"1"}]
             * tagId : 1
             */

            private String tagName;
            private int tagId;
            private List<DetailListBean> detailList;

            public String getTagName() {
                return tagName;
            }

            public void setTagName(String tagName) {
                this.tagName = tagName;
            }

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
            }

            public List<DetailListBean> getDetailList() {
                return detailList;
            }

            public void setDetailList(List<DetailListBean> detailList) {
                this.detailList = detailList;
            }

            public static class DetailListBean {
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
        }


    }
}
