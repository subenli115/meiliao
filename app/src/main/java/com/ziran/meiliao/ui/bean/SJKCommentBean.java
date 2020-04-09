package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */

public class SJKCommentBean extends Result {

    private List<DataBean> data;

    @Override
    public String toString() {
        return "SJKCommentBean{" + "data=" + data + '}';
    }

    public static DataBean postComment(String content) {
        DataBean dataBean = new DataBean();
        dataBean.setContent(content);
        dataBean.setCreateTime(TimeUtil.getStringByFormat(System.currentTimeMillis(), "yyyy-MM-dd"));
        dataBean.setIsMe(true);
        dataBean.setNickName(MyAPP.getUserInfo().getNickName());
        return dataBean;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * content : 公民
         * id : 275
         * commentUserInfo : {"headImg":"","nickName":"","headImgVersion":""}
         * createTime : 2017-09-14 15:25
         * isPraise : false
         * praiseCount : 0
         * headImg : http://www.dgli.net:8888/upload/images/userHeadImg/b423e4f1ee484104aa77d9e9bbec677a.jpg
         * nickName : 用户991508
         * isOwn : false
         * isMe :
         */

        private String content;
        private int id;
        private CommentUserInfoBean commentUserInfo;
        private String createTime;
        private boolean isPraise;
        private int praiseCount;
        private String headImg;
        private String nickName;
        private boolean isOwn;
        private boolean isMe;

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

        public CommentUserInfoBean getCommentUserInfo() {
            return commentUserInfo;
        }

        public void setCommentUserInfo(CommentUserInfoBean commentUserInfo) {
            this.commentUserInfo = commentUserInfo;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isIsPraise() {
            return isPraise;
        }

        public void setIsPraise(boolean isPraise) {
            this.isPraise = isPraise;
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

        public boolean IsMe() {
            return isMe;
        }

        public void setIsMe(boolean isMe) {
            this.isMe = isMe;
        }

        public static class CommentUserInfoBean {
            /**
             * headImg :
             * nickName :
             * headImgVersion :
             */

            private String headImg;
            private String nickName;
            private String headImgVersion;

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

            public String getHeadImgVersion() {
                return headImgVersion;
            }

            public void setHeadImgVersion(String headImgVersion) {
                this.headImgVersion = headImgVersion;
            }

            @Override
            public String toString() {
                return "CommentUserInfoBean{" + "headImg='" + headImg + '\'' + ", nickName='" + nickName + '\'' + ", headImgVersion='" +
                        headImgVersion + '\'' + '}';
            }
        }

        @Override
        public String toString() {
            return "DataBean{" + "content='" + content + '\'' + ", id=" + id + ", commentUserInfo=" + commentUserInfo + ", createTime='"
                    + createTime + '\'' + ", isPraise=" + isPraise + ", praiseCount=" + praiseCount + ", headImg='" + headImg + '\'' + "," +
                    " nickName='" + nickName + '\'' + ", isOwn=" + isOwn + ", isMe=" + isMe + '}';
        }
    }
}
