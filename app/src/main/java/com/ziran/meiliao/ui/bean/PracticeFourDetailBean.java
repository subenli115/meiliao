package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * 第四种 on 2018/9/18.
 */

public class PracticeFourDetailBean extends Result{


    /**
     * picture : default.png
     * id : 1
     * questionList : [{"questionId":1,"answer":"不想回答","questionDetail":"这是第一个问题"},{"questionId":2,"answer":"不想回答","questionDetail":"这是第一个问题"},{"questionId":3,"answer":"不想回答","questionDetail":"这是第一个问题"}]
     * itemId : 1
     * itemTitle : 身体扫描
     */



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
        private List<QuestionListBean> questionList;

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

        public List<QuestionListBean> getQuestionList() {
            return questionList;
        }

        public void setQuestionList(List<QuestionListBean> questionList) {
            this.questionList = questionList;
        }

        public static class QuestionListBean {
            /**
             * questionId : 1
             * answer : 不想回答
             * questionDetail : 这是第一个问题
             */

            private int questionId;
            private String answer;
            private String questionDetail;

            public int getQuestionId() {
                return questionId;
            }

            public void setQuestionId(int questionId) {
                this.questionId = questionId;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
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
