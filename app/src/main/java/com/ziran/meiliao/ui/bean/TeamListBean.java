package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/6 17:37
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/6$
 * @updateDes ${TODO}
 */

public class TeamListBean extends Result {

    /**
     * data : {"missionBuiltList":[{"picture":"https://www.dgli.net/resource/images/qcourselibrary/index_act_banner1228.png","id":1,
     * "createTime":1511937194000,"targetMembers":40,"title":"报名 | 医学及心理学中的正念3","authorMembers":3,"officePrice":50000,"url":"https://www
     * .dgli.net/missionBuilt/missionBuiltDetail?accessToken=1846935ac7a6bfe2115bd228628d9bd13468cd&id=1&tag=null&typeId=null",
     * "totalTime":"1天1晚"},{"picture":"https://www.dgli.net/resource/images/qcourselibrary/index_act_banner1228.png","id":4,
     * "createTime":1511937194000,"targetMembers":40,"title":"报名 | 医学及心理学中的正念3","authorMembers":3,"officePrice":50000,"url":"https://www
     * .dgli.net/missionBuilt/missionBuiltDetail?accessToken=1846935ac7a6bfe2115bd228628d9bd13468cd&id=4&tag=null&typeId=null",
     * "totalTime":"1天1晚"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CourseLibraryTeamBean> missionBuiltList;

        public List<CourseLibraryTeamBean> getMissionBuiltList() {
            return missionBuiltList;
        }

        public void setMissionBuiltList(List<CourseLibraryTeamBean> missionBuiltList) {
            this.missionBuiltList = missionBuiltList;
        }

        @Override
        public String toString() {
            return "DataBean{" + "missionBuiltList=" + missionBuiltList + '}';
        }
    }

    @Override
    public String toString() {
        return "TeamListBean{" + "data=" + data + '}';
    }
}
