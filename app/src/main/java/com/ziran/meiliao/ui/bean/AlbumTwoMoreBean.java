package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2019/1/28.
 */

public class AlbumTwoMoreBean extends Result {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * detailList : [{"picture":"https://www.dgli.net/resource/images/album/1.png","teacherIntro":"5p医学老实人1","albumName":"测试专辑1","albumId":1,"tagName":"热门","subHead":"测试副标题1","teacherName":"欧其鹏1","tagId":1},{"picture":"https://www.dgli.net/resource/images/album/2.png","teacherIntro":"5p医学老实人2","albumName":"测试专辑2","albumId":2,"tagName":"热门","subHead":"测试副标题2","teacherName":"欧其鹏2","tagId":1},{"picture":"https://www.dgli.net/resource/images/album/3.png","teacherIntro":"5p医学老实人3","albumName":"测试专辑3","tagName":"推荐","subHead":"测试副标题3","teacherName":"欧其鹏3"},{"picture":"https://www.dgli.net/resource/images/album/4.png","teacherIntro":"5p医学老实人4","albumName":"测试专辑4","albumId":4,"tagName":"推荐","subHead":"测试副标题4","teacherName":"欧其鹏4","tagId":2}]
         * tagId : 1
         */

        private int tagId;
        private List<DetailListBean> detailList;

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
             * teacherIntro : 5p医学老实人1
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
            private int tagId;

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

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
            }
        }
    }


}
