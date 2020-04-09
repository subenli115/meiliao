package com.ziran.meiliao.ui.bean;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/18 17:54
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/18$
 * @updateDes ${TODO}
 */

public class RecListMapBean {
//    private List<SearchItemBean> qcourseLibrarys;
//    private int missionBuiltCount;
//    private int famousTeacherCount;
//    private int albumCount;
//    private int activityCount;
//    private int courseCount;
//    private int crowdFundCount;
//    private int qcourseLibraryCount ;
//    private List<SearchItemBean> crowdFunds;
//    private List<SearchItemBean> album;
//    private List<SearchItemBean> course;
//    private List<SearchItemBean> famousTeachers;
//    private List<SearchItemBean> missionBuilts;
//    private List<SearchItemBean> activity;
    private List<SearchItemBean> recQcourseLibraryList;
    private List<SearchItemBean> recFamousTeacherList;
    private List<SearchItemBean> recMissionBuiltList;
    private List<SearchItemBean> recCrowdFundList;


    @Override
    public String toString() {
        return "RecListMapBean{" + "recQcourseLibraryList=" + recQcourseLibraryList + ", recFamousTeacherList=" + recFamousTeacherList +
                ", recMissionBuiltList=" + recMissionBuiltList + ", recCrowdFundList=" + recCrowdFundList + '}';
    }

    public List<SearchItemBean> getRecQcourseLibraryList() {
        return recQcourseLibraryList;
    }

    public void setRecQcourseLibraryList(List<SearchItemBean> recQcourseLibraryList) {
        this.recQcourseLibraryList = recQcourseLibraryList;
    }

    public List<SearchItemBean> getRecFamousTeacherList() {
        return recFamousTeacherList;
    }

    public void setRecFamousTeacherList(List<SearchItemBean> recFamousTeacherList) {
        this.recFamousTeacherList = recFamousTeacherList;
    }

    public List<SearchItemBean> getRecMissionBuiltList() {
        return recMissionBuiltList;
    }

    public void setRecMissionBuiltList(List<SearchItemBean> recMissionBuiltList) {
        this.recMissionBuiltList = recMissionBuiltList;
    }

    public List<SearchItemBean> getRecCrowdFundList() {
        return recCrowdFundList;
    }

    public void setRecCrowdFundList(List<SearchItemBean> recCrowdFundList) {
        this.recCrowdFundList = recCrowdFundList;
    }
}
