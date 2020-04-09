package com.ziran.meiliao.ui.bean;


import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * 练习记录 on 2018/10/30.
 */

public class PracticeRecordBean extends Result {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {


        /**
         * exerciseList : [{"days":1,"daysExerciseList":[{"id":1,"days":1,"status":0,"name":"身体扫描","type_id":1,"books_id":1},{"id":2,"days":1,"status":0,"name":"专注：九点练习","type_id":2,"books_id":1},{"id":3,"days":1,"status":0,"name":"正念饮食","type_id":3,"books_id":1},{"id":4,"days":1,"status":0,"name":"3分钟呼吸空间","type_id":1,"books_id":1},{"id":5,"days":1,"status":0,"name":"正念伸展","type_id":1,"books_id":1}]},{"days":2,"daysExerciseList":[{"id":6,"days":2,"status":0,"name":"身体扫描","type_id":1,"books_id":1},{"id":7,"days":2,"status":0,"name":"专注：九点练习","type_id":2,"books_id":1},{"id":9,"days":2,"status":0,"name":"3分钟呼吸空间","type_id":1,"books_id":1},{"id":10,"days":2,"status":0,"name":"正念伸展","type_id":1,"books_id":1},{"id":8,"days":2,"status":1,"name":"正念饮食","type_id":3,"books_id":1}]},{"days":3,"daysExerciseList":[{"id":11,"days":3,"status":0,"name":"身体扫描","type_id":1,"books_id":1},{"id":12,"days":3,"status":0,"name":"专注：九点练习","type_id":2,"books_id":1},{"id":13,"days":3,"status":0,"name":"正念饮食","type_id":3,"books_id":1},{"id":14,"days":3,"status":0,"name":"3分钟呼吸空间","type_id":1,"books_id":1},{"id":15,"days":3,"status":0,"name":"正念伸展","type_id":1,"books_id":1}]},{"days":4,"daysExerciseList":[{"id":17,"days":4,"status":1,"name":"专注：九点练习","type_id":2,"books_id":1},{"id":20,"days":4,"status":1,"name":"正念伸展","type_id":1,"books_id":1},{"id":19,"days":4,"status":1,"name":"3分钟呼吸空间","type_id":1,"books_id":1},{"id":18,"days":4,"status":1,"name":"正念饮食","type_id":3,"books_id":1},{"id":16,"days":4,"status":0,"name":"身体扫描","type_id":1,"books_id":1}]},{"days":5,"daysExerciseList":[{"id":21,"days":5,"status":0,"name":"身体扫描","type_id":1,"books_id":1},{"id":22,"days":5,"status":0,"name":"专注：九点练习","type_id":2,"books_id":1},{"id":23,"days":5,"status":0,"name":"正念饮食","type_id":3,"books_id":1},{"id":24,"days":5,"status":0,"name":"3分钟呼吸空间","type_id":1,"books_id":1},{"id":25,"days":5,"status":0,"name":"正念伸展","type_id":1,"books_id":1}]},{"days":6,"daysExerciseList":[{"id":26,"days":6,"status":0,"name":"身体扫描","type_id":1,"books_id":1},{"id":27,"days":6,"status":0,"name":"专注：九点练习","type_id":2,"books_id":1},{"id":28,"days":6,"status":0,"name":"正念饮食","type_id":3,"books_id":1},{"id":29,"days":6,"status":0,"name":"3分钟呼吸空间","type_id":1,"books_id":1},{"id":30,"days":6,"status":0,"name":"正念伸展","type_id":1,"books_id":1}]},{"days":7,"daysExerciseList":[{"id":31,"days":7,"status":0,"name":"身体扫描","type_id":1,"books_id":1},{"id":32,"days":7,"status":0,"name":"专注：九点练习","type_id":2,"books_id":1},{"id":33,"days":7,"status":0,"name":"正念饮食","type_id":3,"books_id":1},{"id":34,"days":7,"status":0,"name":"3分钟呼吸空间","type_id":1,"books_id":1},{"id":35,"days":7,"status":0,"name":"正念伸展","type_id":1,"books_id":1}]}]
         * percent : 0
         */

        private String percent;
        private List<ExerciseListBean> exerciseList;

        public String getPercent() {
            return percent;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }

        public List<ExerciseListBean> getExerciseList() {
            return exerciseList;
        }

        public void setExerciseList(List<ExerciseListBean> exerciseList) {
            this.exerciseList = exerciseList;
        }

        public static class ExerciseListBean {
            /**
             * days : 1
             * daysExerciseList : [{"id":1,"days":1,"status":0,"name":"身体扫描","type_id":1,"books_id":1},{"id":2,"days":1,"status":0,"name":"专注：九点练习","type_id":2,"books_id":1},{"id":3,"days":1,"status":0,"name":"正念饮食","type_id":3,"books_id":1},{"id":4,"days":1,"status":0,"name":"3分钟呼吸空间","type_id":1,"books_id":1},{"id":5,"days":1,"status":0,"name":"正念伸展","type_id":1,"books_id":1}]
             */

            private int days;
            private List<DaysExerciseListBean> daysExerciseList;

            public int getDays() {
                return days;
            }

            public void setDays(int days) {
                this.days = days;
            }

            public List<DaysExerciseListBean> getDaysExerciseList() {
                return daysExerciseList;
            }

            public void setDaysExerciseList(List<DaysExerciseListBean> daysExerciseList) {
                this.daysExerciseList = daysExerciseList;
            }

            public static class DaysExerciseListBean {
                /**
                 * id : 1
                 * days : 1
                 * status : 0
                 * name : 身体扫描
                 * type_id : 1
                 * books_id : 1
                 */

                private int id;
                private int days;
                private int status;
                private String name;
                private int type_id;
                private int books_id;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getDays() {
                    return days;
                }

                public void setDays(int days) {
                    this.days = days;
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

                public int getType_id() {
                    return type_id;
                }

                public void setType_id(int type_id) {
                    this.type_id = type_id;
                }

                public int getBooks_id() {
                    return books_id;
                }

                public void setBooks_id(int books_id) {
                    this.books_id = books_id;
                }
            }
        }
    }

}
