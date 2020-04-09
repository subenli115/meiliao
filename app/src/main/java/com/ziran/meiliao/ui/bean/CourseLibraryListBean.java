package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/7 18:28
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/7$
 * @updateDes ${TODO}
 */

public class CourseLibraryListBean extends Result {


    /**
     * data : {"qcourseLibrarList":[{"picture":"https://www.dgli.net/resource/images/qcourselibrary/index_act_banner1228.png","id":4,
     * "title":"报名 | 医学及心理学中的正念3","teacherbrarList":[{"authorName":"李燕蕙","authorId":"10"},{"authorName":"李燕蕙","authorId":"11"},
     * {"authorName":"李燕蕙","authorId":"12"}],"officePrice":50000,"intro":"报名 | 医学及心理学中的正念\u2014\u2014正念减压"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
//        private List<QcourseLibrarListBean> qcourseLibrarList;
        private List<SearchItemBean> dataList;
        private List<SearchItemBean> recList;


        public List<SearchItemBean> getRecList() {
            return recList;
        }

        public void setRecList(List<SearchItemBean> recList) {
            this.recList = recList;
        }

        public List<SearchItemBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<SearchItemBean> dataList) {
            this.dataList = dataList;
        }

    }
}
