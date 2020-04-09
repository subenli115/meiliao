package com.ziran.meiliao.ui.bean;

/**
 * Created by Administrator on 2018/9/27.
 */

public class PracticeQuestionBean {


    /**
     * questionId : xx
     * questionDetail : xxx
     * answerDetail : xxx
     */

    private String questionId;
    private String questionDetail;
    private String answerDetail;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public void setQuestionDetail(String questionDetail) {
        this.questionDetail = questionDetail;
    }

    public String getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(String answerDetail) {
        this.answerDetail = answerDetail;
    }
}
