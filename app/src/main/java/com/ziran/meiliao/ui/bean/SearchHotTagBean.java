package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/5 15:03
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/5$
 * @updateDes ${TODO}
 */

public class SearchHotTagBean extends Result{

    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
