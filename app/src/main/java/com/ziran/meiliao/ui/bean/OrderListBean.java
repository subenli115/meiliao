package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/10 15:53
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/10$
 * @updateDes ${TODO}
 */

public class OrderListBean extends Result {

    /**
     * data : {"crownFundCreateOrderList":[{"picture":"https://www.dgli.net/resource/images/qcourselibrary/index_act_banner1228.png",
     * "createTime":1512815020000,"title":"报名 | 医学及心理学中的正念3","price":2000,"status":{"statusType":0,"statusMsg":"审核中"},"courserId":1,
     * "crowdFundId":24,"orderId":"cfc57211512815020120"},{"picture":"https://www.dgli
     * .net/resource/images/qcourselibrary/index_act_banner1228.png","createTime":1512815312000,"title":"报名 | 医学及心理学中的正念3","price":2000,
     * "status":{"statusType":1,"statusMsg":"进行中"},"courserId":1,"crowdFundId":25,"orderId":"cfc45741512815312715"},
     * {"picture":"https://www.dgli.net/resource/images/qcourselibrary/index_act_banner1228.png","createTime":1512874016000,"title":"报名 |
     * 医学及心理学中的正念1","price":2200,"status":{"statusType":0,"statusMsg":"审核中"},"courserId":2,"crowdFundId":26,
     * "orderId":"cfc47371512874016540"}],"crownFundBuyOrderList":[]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<OrderDetailBean> crownFundCreateOrderList;
        private List<OrderDetailBean> crownFundBuyOrderList;
        private List<OrderDetailBean> missionOrderList;

        public List<OrderDetailBean> getMissionOrderList() {
            return missionOrderList;
        }

        public void setMissionOrderList(List<OrderDetailBean> missionOrderList) {
            this.missionOrderList = missionOrderList;
        }

        public List<OrderDetailBean> getCrownFundCreateOrderList() {
            return crownFundCreateOrderList;
        }

        public void setCrownFundCreateOrderList(List<OrderDetailBean> crownFundCreateOrderList) {
            this.crownFundCreateOrderList = crownFundCreateOrderList;
        }

        public List<OrderDetailBean> getCrownFundBuyOrderList() {
            return crownFundBuyOrderList;
        }

        public void setCrownFundBuyOrderList(List<OrderDetailBean> crownFundBuyOrderList) {
            this.crownFundBuyOrderList = crownFundBuyOrderList;
        }
    }
}
