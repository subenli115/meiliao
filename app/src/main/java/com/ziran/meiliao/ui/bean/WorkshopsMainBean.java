package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/5 18:26
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/5$
 * @updateDes ${TODO}
 */

public class WorkshopsMainBean extends Result {


    /**
     * data : {"qcourseLibraryTypeList":["减压","正念"],"missionBuiltList":[{"createTime":1511937194000,"targetMembers":40,
     * "accessToken":"184468de7149a3b9564ababa133b6b38214468","status":1,"shareTitle":"报名 | 医学及心理学中的正念3",
     * "sharePic":"index_act_banner1228.png","authorMembers":3,"authorId":[10,11,12],"id":1,"picture":"https://www.dgli
     * .net/resource/images/qcourselibrary/index_act_banner1228.png","title":"报名 | 医学及心理学中的正念3","shareUrl":"","officePrice":50000,
     * "totalTime":"1天1晚"}],"famousTeacherList":[{"picture":"https://www.dgli
     * .net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png","id":10,"createTime":1512098525000,
     * "accessToken":"184468de7149a3b9564ababa133b6b38214468","status":1,"courseMap":{"3":"报名 | 医学及心理学中的正念2","2":"报名 | 医学及心理学中的正念1",
     * "1":"报名 | 医学及心理学中的正念3"},"name":"李燕蕙","courseId":[1,2,3],"courseMembers":3,"inrto":"简介简介简介"},{"picture":"https://www.dgli
     * .net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png","id":11,"createTime":1512098536000,
     * "accessToken":"184468de7149a3b9564ababa133b6b38214468","status":1,"courseMap":{"3":"报名 | 医学及心理学中的正念2","2":"报名 | 医学及心理学中的正念1",
     * "1":"报名 | 医学及心理学中的正念3"},"name":"李燕蕙","courseId":[1,2,3],"courseMembers":3,"inrto":"简介简介简介"},{"picture":"https://www.dgli
     * .net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png","id":12,"createTime":1512098533000,
     * "accessToken":"184468de7149a3b9564ababa133b6b38214468","status":1,"courseMap":{"3":"报名 | 医学及心理学中的正念2","2":"报名 | 医学及心理学中的正念1",
     * "1":"报名 | 医学及心理学中的正念3"},"name":"李燕蕙","courseId":[1,2,3],"courseMembers":3,"inrto":"简介简介简介"}],
     * "crownFundList":[{"createTime":1511871192000,"targetMembers":50,"accessToken":"184468de7149a3b9564ababa133b6b38214468","staus":1,
     * "status":1,"supportMembers":50,"leftTime":43,"endTime":1512023287000,"url":"https://www.dgli
     * .net/crowdFundDetail?id=null&accessToken=184468de7149a3b9564ababa133b6b38214468","intro":"报名 | 医学及心理学中的正念\u2014\u2014正念减压","id":1,
     * "picture":"https://www.dgli.net/resource/images/qcourselibrary/https://www.dgli
     * .net/resource/images/qcourselibrary/index_act_banner1228.png","startTime":1511936884000,"title":"报名 | 医学及心理学中的正念3",
     * "headImg":"index_act_banner1228.png","avgPrice":50,"address":"广州星光映景","able":1,"totalTime":"1天1晚"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

//        private List<PicsBean> pics;
//        private List<String> qcourseLibraryTypeList;
//        private List<Test.DataBean.MissionBuiltListBean> missionBuiltList;
//        private List<FamousTeacherListBean> famousTeacherList;
//        private List<CrownFundListBean> crownFundList;
//        private List<ActisBean> actis;

        private List<String> qcourseLibraryTypeList;
        private List<CourseLibraryTeamBean> missionBuiltList;
        private List<FamousTeacherListBean> famousTeacherList;
        private List<ActisData> actis;
        private List<CrownFundListBean> crownFundList;  //众筹列表
        private List<PicsBean> pics;


        public List<ActisData> getActis() {
            return actis;
        }

        public List<PicsBean> getPics() {
            return pics;
        }

        public void setPics(List<PicsBean> pics) {
            this.pics = pics;
        }

        public void setActis(List<ActisData> actis) {
            this.actis = actis;
        }

        public List<String> getQcourseLibraryTypeList() {
            return qcourseLibraryTypeList;
        }

        public void setQcourseLibraryTypeList(List<String> qcourseLibraryTypeList) {
            this.qcourseLibraryTypeList = qcourseLibraryTypeList;
        }

        public List<CourseLibraryTeamBean> getMissionBuiltList() {
            return missionBuiltList;
        }

        public void setMissionBuiltList(List<CourseLibraryTeamBean> missionBuiltList) {
            this.missionBuiltList = missionBuiltList;
        }

        public List<FamousTeacherListBean> getFamousTeacherList() {
            return famousTeacherList;
        }

        public void setFamousTeacherList(List<FamousTeacherListBean> famousTeacherList) {
            this.famousTeacherList = famousTeacherList;
        }

        public List<CrownFundListBean> getCrownFundList() {
            return crownFundList;
        }

        public void setCrownFundList(List<CrownFundListBean> crownFundList) {
            this.crownFundList = crownFundList;
        }
    }
}
