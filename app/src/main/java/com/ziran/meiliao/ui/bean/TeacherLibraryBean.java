package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/7 18:30
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/7$
 * @updateDes ${TODO}
 */

public class TeacherLibraryBean extends Result {

    /**
     * data : {"famousTeacherList":[{"picture":"https://www.dgli.net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png",
     * "id":12,"name":"李燕蕙","courseMembers":3,"intro":"简介简介简介","courserList":[{"courseTitle":"报名 | 医学及心理学中的正念3","courseId":1},
     * {"courseTitle":"报名 | 医学及心理学中的正念1","courseId":2},{"courseTitle":"报名 | 医学及心理学中的正念2","courseId":3},{"courseTitle":"报名 | 医学及心理学中的正念3",
     * "courseId":1},{"courseTitle":"报名 | 医学及心理学中的正念1","courseId":2},{"courseTitle":"报名 | 医学及心理学中的正念2","courseId":3}]}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<SearchItemBean> famousTeacherList;
        private List<SearchItemBean> dataList;
        private List<SearchItemBean> recList;


        public List<SearchItemBean> getFamousTeacherList() {
            return famousTeacherList;
        }

        public void setFamousTeacherList(List<SearchItemBean> famousTeacherList) {
            this.famousTeacherList = famousTeacherList;
        }

        public List<SearchItemBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<SearchItemBean> dataList) {
            this.dataList = dataList;
        }

        public List<SearchItemBean> getRecList() {
            return recList;
        }

        public void setRecList(List<SearchItemBean> recList) {
            this.recList = recList;
        }
    }
}
