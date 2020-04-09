package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/6/13 11:51
 * @des ${TODO}
 * @updateAuthor #author
 * @updateDate 2017/6/13
 * @updateDes ${TODO}
 */

public class PurseListCoinBean extends Result {


    /**
     * data : {"pro":"http://www.psytap.com/userHome/payProtocol","list":[{"id":1,"title":"充值正念币","rmb":500,"coin":5000,"type":"coin"},
     * {"id":2,"title":"充值正念币","rmb":200,"coin":2000,"type":"coin"},{"id":3,"title":"充值正念币","rmb":100,"coin":1000,"type":"coin"},{"id":4,
     * "title":"充值正念币","rmb":50,"coin":500,"type":"coin"},{"id":5,"title":"充值正念币","rmb":30,"coin":300,"type":"coin"},{"id":6,
     * "title":"充值正念币","rmb":10,"coin":100,"type":"coin"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pro : http://www.psytap.com/userHome/payProtocol
         * list : [{"id":1,"title":"充值正念币","rmb":500,"coin":5000,"type":"coin"},{"id":2,"title":"充值正念币","rmb":200,"coin":2000,
         * "type":"coin"},{"id":3,"title":"充值正念币","rmb":100,"coin":1000,"type":"coin"},{"id":4,"title":"充值正念币","rmb":50,"coin":500,
         * "type":"coin"},{"id":5,"title":"充值正念币","rmb":30,"coin":300,"type":"coin"},{"id":6,"title":"充值正念币","rmb":10,"coin":100,
         * "type":"coin"}]
         */

        private String pro;
        private List<ListBean> list;

        public String getPro() {
            return pro;
        }

        public void setPro(String pro) {
            this.pro = pro;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * title : 充值正念币
             * rmb : 500.0
             * coin : 5000
             * type : coin
             */
            private boolean isSelect;
            private String  id;
            private String title;
            private float rmb;
            private int coin;
            private String type;
            private int jifen;

            public int getJifen() {
                return jifen;
            }

            public void setJifen(int jifen) {
                this.jifen = jifen;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public float getRmb() {
                return rmb;
            }

            public void setRmb(float rmb) {
                this.rmb = rmb;
            }

            public int getCoin() {
                return coin;
            }

            public void setCoin(int coin) {
                this.coin = coin;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
