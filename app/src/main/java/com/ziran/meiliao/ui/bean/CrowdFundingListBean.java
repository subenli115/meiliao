package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/6 17:25
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/6$
 * @updateDes ${TODO}
 */

public class CrowdFundingListBean extends Result {


    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CrownFundListBean> crownFundList;

        public List<CrownFundListBean> getCrownFundList() {
            return crownFundList;
        }

        public void setCrownFundList(List<CrownFundListBean> crownFundList) {
            this.crownFundList = crownFundList;
        }
    }
}
