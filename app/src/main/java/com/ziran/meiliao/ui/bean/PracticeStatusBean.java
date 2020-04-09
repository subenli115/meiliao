package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * 练习状态 on 2018/9/17.
 */

public class PracticeStatusBean extends Result{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {


        private List<ExerciseListBean> exerciseList;

        public List<ExerciseListBean> getExerciseList() {
            return exerciseList;
        }

        public void setExerciseList(List<ExerciseListBean> exerciseList) {
            this.exerciseList = exerciseList;
        }

        public static class ExerciseListBean {
            /**
             * practiceStatus : 0
             */

            private int practiceStatus;

            public int getPracticeStatus() {
                return practiceStatus;
            }

            public void setPracticeStatus(int practiceStatus) {
                this.practiceStatus = practiceStatus;
            }
        }
    }
}
