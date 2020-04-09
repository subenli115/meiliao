package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * 练习册日历 on 2018/9/14.
 */

public class PracticeCalendarBean extends Result{



    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }



    public static class DataBean {

        /**
         * exerciseList : [{"picture":"http://www.dgli.net/resource/images/practiceActivity/1.png","itemName":"身体扫描","itemStatus":0,"practiceStatus":1,"itemId":1,"typeId":1},{"picture":"http://www.dgli.net/resource/images/practiceActivity/2.png","itemName":"身体继续扫描","itemStatus":0,"practiceStatus":1,"itemId":2,"typeId":3}]
         * id : 1
         * startTime : null
         * firstStatus : null
         * days : 1
         * status : 0
         * notice : 提示语提示语提示语
         */

        private int id;
        private Object startTime;
        private Object firstStatus;
        private int days;
        private int status;
        private String notice;
        private String weekDays;
        private String optionNotice;

        public String getOptionNotice() {
            return optionNotice;
        }

        public void setOptionNotice(String optionNotice) {
            this.optionNotice = optionNotice;
        }

        public String getWeekDays() {
            return weekDays;
        }

        public void setWeekDays(String weekDays) {
            this.weekDays = weekDays;
        }
        private List<ExerciseListBean> exerciseList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public Object getFirstStatus() {
            return firstStatus;
        }

        public void setFirstStatus(Object firstStatus) {
            this.firstStatus = firstStatus;
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

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public List<ExerciseListBean> getExerciseList() {
            return exerciseList;
        }

        public void setExerciseList(List<ExerciseListBean> exerciseList) {
            this.exerciseList = exerciseList;
        }

        public static class ExerciseListBean {
            /**
             * picture : http://www.dgli.net/resource/images/practiceActivity/1.png
             * itemName : 身体扫描
             * itemStatus : 0
             * practiceStatus : 1
             * itemId : 1
             * typeId : 1
             */

            private String picture;
            private String itemName;
            private int itemStatus;
            private int practiceStatus;
            private int itemId;
            private int typeId;
            private int useMusic;
            private int isOption;


            public int getUseMusic() {
                return useMusic;
            }

            public void setUseMusic(int useMusic) {
                this.useMusic = useMusic;
            }

            public int getIsOption() {
                return isOption;
            }

            public void setIsOption(int isOption) {
                this.isOption = isOption;
            }


            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getItemName() {
                return itemName;
            }

            public void setItemName(String itemName) {
                this.itemName = itemName;
            }

            public int getItemStatus() {
                return itemStatus;
            }

            public void setItemStatus(int itemStatus) {
                this.itemStatus = itemStatus;
            }

            public int getPracticeStatus() {
                return practiceStatus;
            }

            public void setPracticeStatus(int practiceStatus) {
                this.practiceStatus = practiceStatus;
            }

            public int getItemId() {
                return itemId;
            }

            public void setItemId(int itemId) {
                this.itemId = itemId;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }
        }
    }
    }




