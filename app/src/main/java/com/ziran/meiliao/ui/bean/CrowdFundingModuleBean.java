package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/6 20:06
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/6$
 * @updateDes ${TODO}
 */

public class CrowdFundingModuleBean extends Result {

    private List<CrowdFundingPreviewBean> data;

    public List<CrowdFundingPreviewBean> getData() {
        return data;
    }

    public void setData(List<CrowdFundingPreviewBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CrowdFundingModuleBean{" + "data=" + data + '}';
    }
}
