package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/22 18:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/22$
 * @updateDes ${TODO}
 */

public class ActivityListBean extends Result {

    private List<SearchItemBean> data;

    public List<SearchItemBean> getData() {
        return data;
    }

    public void setData(List<SearchItemBean> data) {
        this.data = data;
    }

}
