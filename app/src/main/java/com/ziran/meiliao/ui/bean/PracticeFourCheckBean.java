package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2018/9/18.
 */

public class PracticeFourCheckBean extends Result{


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {

        private String picture;
        private int id;
        private int itemId;
        private String itemTitle;
        private List<AnswerListBean> answerList;
        private String bgPic;

        public String getBgPic() {
            return bgPic;
        }

        public void setBgPic(String bgPic) {
            this.bgPic = bgPic;
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

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public String getItemTitle() {
            return itemTitle;
        }

        public void setItemTitle(String itemTitle) {
            this.itemTitle = itemTitle;
        }

        public List<AnswerListBean> getAnswerList() {
            return answerList;
        }

        public void setAnswerList(List<AnswerListBean> answerList) {
            this.answerList = answerList;
        }

        public static class AnswerListBean {
            /**
             * questionId : 1
             * answerDetail : 不想回答,并且丢一个草泥马给你
             * questionDetail : 这是第一个问题
             */

            private int questionId;
            private String answerDetail;
            private String questionDetail;
            private String noticeDetail;
            public String getNoticeDetail() {
                return noticeDetail;
            }

            public void setNoticeDetail(String noticeDetail) {
                this.noticeDetail = noticeDetail;
            }


            public int getQuestionId() {
                return questionId;
            }

            public void setQuestionId(int questionId) {
                this.questionId = questionId;
            }

            public String getAnswerDetail() {
                return answerDetail;
            }

            public void setAnswerDetail(String answerDetail) {
                this.answerDetail = answerDetail;
            }

            public String getQuestionDetail() {
                return questionDetail;
            }

            public void setQuestionDetail(String questionDetail) {
                this.questionDetail = questionDetail;
            }
        }
    }

}
