package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/31 10:09
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/31$
 * @updateDes ${TODO}
 */

public class SubscribeCommentListBean extends Result {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : 楼上很帅
         * id : 7
         * createTime : 2017-08-30 15:36
         * praiseCount : 1
         * headImg : http://www.dgli.net:8888/upload/images/userHeadImg/e43fa8b6d8024b1ea7129ca0318e2270.jpeg
         * nickName : 陈景辉
         * isOwn : false
         */

        private String content;
        private int id = -2;
        private String createTime;
        private int praiseCount;
        private String headImg;
        private String nickName;
        private boolean isOwn;
        private boolean isPraise;

        public boolean isPraise() {
            return isPraise;
        }

        public void setPraise(boolean praise) {
            isPraise = praise;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getPraiseCount() {
            return praiseCount;
        }

        public void setPraiseCount(int praiseCount) {
            this.praiseCount = praiseCount;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public boolean isIsOwn() {
            return isOwn;
        }

        public void setIsOwn(boolean isOwn) {
            this.isOwn = isOwn;
        }

        @Override
        public String toString() {
            return "DataBean{" + "content='" + content + '\'' + ", id=" + id + ", createTime='" + createTime + '\'' + ", praiseCount=" +
                    praiseCount + ", headImg='" + headImg + '\'' + ", nickName='" + nickName + '\'' + ", isOwn=" + isOwn + ", isPraise=" + isPraise + '}';
        }
    }

    @Override
    public String toString() {
        return "SubscribeCommentListBean{" + "data=" + data + '}';
    }
}
