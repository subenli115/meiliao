package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/26 10:51
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/26$
 * @updateDes ${TODO}
 */

public class GetTicketBean extends Result {


    /**
     * data : {"total":5,"shareDescript":"描述xx","shareUrl":"http://www.psytap
     * .com:8888/courseTicket/shareTicket?courseId=45&identity=219de5e1c4df89e372390b5478c215","shareTitle":"标题","sharePic":"",
     * "pic":"http://www.psytap.com/resource/images/course/ticket/temp1.png","validTimes":0}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean  extends ShareBean{
        /**
         * total : 5
         * shareDescript : 描述xx
         * shareUrl : http://www.psytap.com:8888/courseTicket/shareTicket?courseId=45&identity=219de5e1c4df89e372390b5478c215
         * shareTitle : 标题
         * sharePic :
         * pic : http://www.psytap.com/resource/images/course/ticket/temp1.png
         * validTimes : 0
         */

        private int total;
        private String pic;
        private int validTimes;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getValidTimes() {
            return validTimes;
        }

        public void setValidTimes(int validTimes) {
            this.validTimes = validTimes;
        }
    }
}
