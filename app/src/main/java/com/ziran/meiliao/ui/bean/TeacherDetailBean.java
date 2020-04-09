package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/12 11:30
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/12$
 * @updateDes ${TODO}
 */

public class TeacherDetailBean extends Result {

    /**
     * data : {"famousTeacherMap":{"id":10,"shareDescript":"简介简介简介","name":"李燕蕙","shareTitle":"简介简介简介","shareUrl":"https://www.dgli
     * .net/famousteachers/famousTeachersList?accessToken=426556e5db41eaeeeaf8f646c79d751cc089a&page=null&pageSize=null",
     * "sharePic":"course_teacher_liyanhui.png","courseDetailList":[{"picture":"index_act_banner1228.png","targetMembers":40,"title":"报名
     * | 医学及心理学中的正念2","url":"https://www.dgli.net/missionbuilt/missionBuiltDetail?accessToken=426556e5db41eaeeeaf8f646c79d751cc089a
     * &courseId=3&tagId=2&typeId=null","totalTime":"1天1晚"},{"picture":"index_act_banner1228.png","targetMembers":40,"title":"报名 |
     * 医学及心理学中的正念2","url":"https://www.dgli.net/missionbuilt/missionBuiltDetail?accessToken=426556e5db41eaeeeaf8f646c79d751cc089a
     * &courseId=3&tagId=2&typeId=null","totalTime":"1天1晚"},{"picture":"index_act_banner1228.png","targetMembers":40,"title":"报名 |
     * 医学及心理学中的正念2","url":"https://www.dgli.net/missionbuilt/missionBuiltDetail?accessToken=426556e5db41eaeeeaf8f646c79d751cc089a
     * &courseId=3&tagId=2&typeId=null","totalTime":"1天1晚"}],"intro":"简介简介简介"}}
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
         * famousTeacherMap : {"id":10,"shareDescript":"简介简介简介","name":"李燕蕙","shareTitle":"简介简介简介","shareUrl":"https://www.dgli
         * .net/famousteachers/famousTeachersList?accessToken=426556e5db41eaeeeaf8f646c79d751cc089a&page=null&pageSize=null",
         * "sharePic":"course_teacher_liyanhui.png","courseDetailList":[{"picture":"index_act_banner1228.png","targetMembers":40,
         * "title":"报名 | 医学及心理学中的正念2","url":"https://www.dgli
         * .net/missionbuilt/missionBuiltDetail?accessToken=426556e5db41eaeeeaf8f646c79d751cc089a&courseId=3&tagId=2&typeId=null",
         * "totalTime":"1天1晚"},{"picture":"index_act_banner1228.png","targetMembers":40,"title":"报名 | 医学及心理学中的正念2","url":"https://www
         * .dgli.net/missionbuilt/missionBuiltDetail?accessToken=426556e5db41eaeeeaf8f646c79d751cc089a&courseId=3&tagId=2&typeId=null",
         * "totalTime":"1天1晚"},{"picture":"index_act_banner1228.png","targetMembers":40,"title":"报名 | 医学及心理学中的正念2","url":"https://www
         * .dgli.net/missionbuilt/missionBuiltDetail?accessToken=426556e5db41eaeeeaf8f646c79d751cc089a&courseId=3&tagId=2&typeId=null",
         * "totalTime":"1天1晚"}],"intro":"简介简介简介"}
         */

        private FamousTeacherMapBean famousTeacherMap;
        private RecListMap recMap;

        public RecListMap getRecMap() {
            return recMap;
        }

        public void setRecMap(RecListMap recMap) {
            this.recMap = recMap;
        }

        public FamousTeacherMapBean getFamousTeacherMap() {
            return famousTeacherMap;
        }

        public void setFamousTeacherMap(FamousTeacherMapBean famousTeacherMap) {
            this.famousTeacherMap = famousTeacherMap;
        }

        public static class RecListMap{
            private List<SearchItemBean> qcourseLibrarys;
            private List<SearchItemBean> crowdFunds;            //0k
            private List<AlbumListBean> album;                 //ok
            private List<SpecColumnData> course;                //ok
            private List<SearchItemBean> famousTeachers;        //ok
            private List<SearchItemBean> missionBuilts;         //ok
            private List<ActisData> activity;              //ok

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

            public List<AlbumListBean> getAlbum() {
                return album;
            }

            public void setAlbum(List<AlbumListBean> album) {
                this.album = album;
            }

            public List<SpecColumnData> getCourse() {
                return course;
            }

            public void setCourse(List<SpecColumnData> course) {
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

            public List<ActisData> getActivity() {
                return activity;
            }

            public void setActivity(List<ActisData> activity) {
                this.activity = activity;
            }
        }

        public static class FamousTeacherMapBean  extends ShareBean{
            /**
             * id : 10
             * shareDescript : 简介简介简介
             * name : 李燕蕙
             * shareTitle : 简介简介简介
             * shareUrl : https://www.dgli.net/famousteachers/famousTeachersList?accessToken=426556e5db41eaeeeaf8f646c79d751cc089a&page
             * =null&pageSize=null
             * sharePic : course_teacher_liyanhui.png
             * courseDetailList : [{"picture":"index_act_banner1228.png","targetMembers":40,"title":"报名 | 医学及心理学中的正念2","url":"https://www
             * .dgli.net/missionbuilt/missionBuiltDetail?accessToken=426556e5db41eaeeeaf8f646c79d751cc089a&courseId=3&tagId=2&typeId=null
             * ","totalTime":"1天1晚"},{"picture":"index_act_banner1228.png","targetMembers":40,"title":"报名 | 医学及心理学中的正念2",
             * "url":"https://www.dgli.net/missionbuilt/missionBuiltDetail?accessToken=426556e5db41eaeeeaf8f646c79d751cc089a&courseId=3
             * &tagId=2&typeId=null","totalTime":"1天1晚"},{"picture":"index_act_banner1228.png","targetMembers":40,"title":"报名 |
             * 医学及心理学中的正念2","url":"https://www.dgli.net/missionbuilt/missionBuiltDetail?accessToken=426556e5db41eaeeeaf8f646c79d751cc089a
             * &courseId=3&tagId=2&typeId=null","totalTime":"1天1晚"}]
             * intro : 简介简介简介
             */

            private int id;
            private String name;
            private String intro;
            private List<CourseLibraryTeamBean> courseDetailList;
            private int isCollect;
            private String picture;
            private int courseNumbers;
            private List<ActisData> activityList;

            public int getCourseNumbers() {
                return courseNumbers;
            }

            public void setCourseNumbers(int courseNumbers) {
                this.courseNumbers = courseNumbers;
            }

            public List<ActisData> getActivityList() {
                return activityList;
            }

            public void setActivityList(List<ActisData> activityList) {
                this.activityList = activityList;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public int isCollect() {
                return isCollect;
            }
            public void toggleCollect() {
                isCollect = isCollect == 0 ? 1 : 0;
            }
            public void setCollect(int collect) {
                isCollect = collect;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }


            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }



            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public List<CourseLibraryTeamBean> getCourseDetailList() {
                return courseDetailList;
            }

            public void setCourseDetailList(List<CourseLibraryTeamBean> courseDetailList) {
                this.courseDetailList = courseDetailList;
            }

            public String getPicture() {
                return picture;
            }
        }
    }
}
