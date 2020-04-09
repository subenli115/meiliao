package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/5 15:06
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/5$
 * @updateDes ${TODO}
 */

public class SearchAllResultBean extends Result {

    /**
     * data : {"activityCount":4,"course":[],"courseCount":0,"album":[{"picture":"http://www.psytap.com/resource/images/album/zf/27.png",
     * "id":27,"title":"音乐减压放松系列","name":"高天","descript":"音乐治疗学教授","listenCount":149},{"picture":"http://www.psytap
     * .com/resource/images/album/zf/18.1.png","id":18,"title":"完全减压放松法","name":"杨建铭","descript":"心理学教授","listenCount":269},
     * {"picture":"http://www.psytap.com/resource/images/album/zf/16.png","id":16,"title":"睡眠的海洋","name":" ","descript":" ",
     * "listenCount":312}],"activity":[{"picture":"https://www.psytap.com/resource/images/activity/1/me_list_active_pic1@3x.png","id":1,
     * "title":"报名 | 医学及心理学中的正念\u2014\u2014正念减压四日工作坊","address":"广州市水荫路119号广东文投创工场","beginTime":"2017-04-28","endTime":"2017-05-01"},
     * {"picture":"https://www.psytap.com/resource/images/activity/2/me_list_active_pic1@3x.png","id":2,"title":"报名 |
     * 医学及心理学中的正念\u2014\u2014正念减压四日工作坊","address":"北京市东四环中路78号大成国际中心","beginTime":"2017-05-27","endTime":"2017-05-30"},
     * {"picture":"https://www.psytap.com/resource/images/activity/3/me_list_active_pic1@3x.png","id":3,"title":"报名 |
     * 医学及心理学中的正念\u2014\u2014正念减压四日工作坊","address":"杭州市古墩路紫金广场福泉书院","beginTime":"2017-07-08","endTime":"2017-07-11"}],"albumCount":3}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * activityCount : 4
         * course : []
         * courseCount : 0
         * album : [{"picture":"http://www.psytap.com/resource/images/album/zf/27.png","id":27,"title":"音乐减压放松系列","name":"高天",
         * "descript":"音乐治疗学教授","listenCount":149},{"picture":"http://www.psytap.com/resource/images/album/zf/18.1.png","id":18,
         * "title":"完全减压放松法","name":"杨建铭","descript":"心理学教授","listenCount":269},{"picture":"http://www.psytap
         * .com/resource/images/album/zf/16.png","id":16,"title":"睡眠的海洋","name":" ","descript":" ","listenCount":312}]
         * activity : [{"picture":"https://www.psytap.com/resource/images/activity/1/me_list_active_pic1@3x.png","id":1,"title":"报名 |
         * 医学及心理学中的正念\u2014\u2014正念减压四日工作坊","address":"广州市水荫路119号广东文投创工场","beginTime":"2017-04-28","endTime":"2017-05-01"},
         * {"picture":"https://www.psytap.com/resource/images/activity/2/me_list_active_pic1@3x.png","id":2,"title":"报名 |
         * 医学及心理学中的正念\u2014\u2014正念减压四日工作坊","address":"北京市东四环中路78号大成国际中心","beginTime":"2017-05-27","endTime":"2017-05-30"},
         * {"picture":"https://www.psytap.com/resource/images/activity/3/me_list_active_pic1@3x.png","id":3,"title":"报名 |
         * 医学及心理学中的正念\u2014\u2014正念减压四日工作坊","address":"杭州市古墩路紫金广场福泉书院","beginTime":"2017-07-08","endTime":"2017-07-11"}]
         * albumCount : 3
         */

        private int missionBuiltCount;
        private int famousTeacherCount;
        private int albumCount;
        private int activityCount;
        private RecListMapBean recListMap;
        private int courseCount;
        private int crowdFundCount;
        private int qcourseLibraryCount;
        private List<SearchItemBean> qcourseLibrarys;
        private List<SearchItemBean> crowdFunds;            //0k
        private List<SearchItemBean> album;                 //ok
        private List<SearchItemBean> course;                //ok
        private List<SearchItemBean> famousTeachers;        //ok
        private List<SearchItemBean> missionBuilts;         //ok
        private List<SearchItemBean> activity;              //ok


        public int getMissionBuiltCount() {
            return missionBuiltCount;
        }

        public void setMissionBuiltCount(int missionBuiltCount) {
            this.missionBuiltCount = missionBuiltCount;
        }

        public int getFamousTeacherCount() {
            return famousTeacherCount;
        }

        public void setFamousTeacherCount(int famousTeacherCount) {
            this.famousTeacherCount = famousTeacherCount;
        }

        public int getAlbumCount() {
            return albumCount;
        }

        public void setAlbumCount(int albumCount) {
            this.albumCount = albumCount;
        }

        public int getActivityCount() {
            return activityCount;
        }

        public void setActivityCount(int activityCount) {
            this.activityCount = activityCount;
        }

        public RecListMapBean getRecListMap() {
            return recListMap;
        }

        public void setRecListMap(RecListMapBean recListMap) {
            this.recListMap = recListMap;
        }

        public int getCourseCount() {
            return courseCount;
        }

        public void setCourseCount(int courseCount) {
            this.courseCount = courseCount;
        }

        public int getCrowdFundCount() {
            return crowdFundCount;
        }

        public void setCrowdFundCount(int crowdFundCount) {
            this.crowdFundCount = crowdFundCount;
        }

        public int getQcourseLibraryCount() {
            return qcourseLibraryCount;
        }

        public void setQcourseLibraryCount(int qcourseLibraryCount) {
            this.qcourseLibraryCount = qcourseLibraryCount;
        }

        public List<SearchItemBean> getQcourseLibrarys() {
            return qcourseLibrarys;
        }

        public void setQcourseLibrarys(List<SearchItemBean> qcourseLibrarys) {
            this.qcourseLibrarys = qcourseLibrarys;
        }

        public List<SearchItemBean> getCrowdFunds() {
            return crowdFunds;
        }

        public void setCrowdFunds(List<SearchItemBean> crowdFunds) {
            this.crowdFunds = crowdFunds;
        }

        public List<SearchItemBean> getAlbum() {
            return album;
        }

        public void setAlbum(List<SearchItemBean> album) {
            this.album = album;
        }

        public List<SearchItemBean> getCourse() {
            return course;
        }

        public void setCourse(List<SearchItemBean> course) {
            this.course = course;
        }

        public List<SearchItemBean> getFamousTeachers() {
            return famousTeachers;
        }

        public void setFamousTeachers(List<SearchItemBean> famousTeachers) {
            this.famousTeachers = famousTeachers;
        }

        public List<SearchItemBean> getMissionBuilts() {
            return missionBuilts;
        }

        public void setMissionBuilts(List<SearchItemBean> missionBuilts) {
            this.missionBuilts = missionBuilts;
        }

        public List<SearchItemBean> getActivity() {
            return activity;
        }

        public void setActivity(List<SearchItemBean> activity) {
            this.activity = activity;
        }

    }
}
