package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/6 17:38
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/6$
 * @updateDes ${TODO}
 */

public class TeacherListBean extends Result {


    /**
     * data : {"famousTeacherList":[{"picture":"https://www.dgli.net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png",
     * "id":10,"createTime":1512098525000,"accessToken":"1849248caa7b626fd1ae5eeceb6e9fd9253b65","name":"李燕蕙","courseMembers":3,
     * "intro":"简介简介简介"},{"picture":"https://www.dgli.net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png","id":11,
     * "createTime":1512098536000,"accessToken":"1849248caa7b626fd1ae5eeceb6e9fd9253b65","name":"李燕蕙","courseMembers":3,
     * "intro":"简介简介简介"},{"picture":"https://www.dgli.net/resource/images/qcourseauthorpics/course_teacher_liyanhui.png","id":12,
     * "createTime":1512098533000,"accessToken":"1849248caa7b626fd1ae5eeceb6e9fd9253b65","name":"李燕蕙","courseMembers":3,"intro":"简介简介简介"}]}
     */

    private DataBean data;



    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<FamousTeacherListBean> famousTeacherList;

        public List<FamousTeacherListBean> getFamousTeacherList() {
            return famousTeacherList;
        }

        public void setFamousTeacherList(List<FamousTeacherListBean> famousTeacherListX) {
            this.famousTeacherList = famousTeacherListX;
        }
    }
}
