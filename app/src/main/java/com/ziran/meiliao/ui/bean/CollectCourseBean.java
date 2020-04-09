package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/16 17:24
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/16$
 * @updateDes ${TODO}
 */

public class CollectCourseBean extends Result {

    private List<SearchItemBean> data;

    public List<SearchItemBean> getData() {
        return data;
    }

    public void setData(List<SearchItemBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CollectCourseBean{" + "data=" + data + '}';
    }
}
