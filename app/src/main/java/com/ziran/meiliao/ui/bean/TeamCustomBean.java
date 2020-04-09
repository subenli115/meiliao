package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/6 11:54
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/6$
 * @updateDes ${TODO}
 */

public class TeamCustomBean extends Result {

    /**
     * data : {"destinationList":["减压","睡眠"],"teacherList":["李燕蕙"],"typetList":["减压","正念"]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> destinationList;
        private List<String> teacherList;
        private List<String> typetList;

        public List<String> getDestinationList() {
            return destinationList;
        }

        public void setDestinationList(List<String> destinationList) {
            this.destinationList = destinationList;
        }

        public List<String> getTeacherList() {
            return teacherList;
        }

        public void setTeacherList(List<String> teacherList) {
            this.teacherList = teacherList;
        }

        public List<String> getTypetList() {
            return typetList;
        }

        public void setTypetList(List<String> typetList) {
            this.typetList = typetList;
        }

        @Override
        public String toString() {
            return "DataBean{" + "destinationList=" + destinationList + ", teacherList=" + teacherList + ", typetList=" + typetList + '}';
        }
    }

    @Override
    public String toString() {
        return "TeamCustomBean{" + "data=" + data + '}';
    }
}
