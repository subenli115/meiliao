package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/30 18:25
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/30$
 * @updateDes ${TODO}
 */

public class SJKMoreBean extends Result {
    private List<SJKSingeLiveData> data;

    public List<SJKSingeLiveData> getData() {
        return data;
    }

    public void setData(List<SJKSingeLiveData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SJKMoreBean{" + "data=" + data + '}';
    }
}
