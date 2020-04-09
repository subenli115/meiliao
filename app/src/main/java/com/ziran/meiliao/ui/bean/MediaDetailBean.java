package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/5/25 11:19
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/5/25$
 * @updateDes ${TODO}
 */

public class MediaDetailBean extends Result {

    /**
     * data : {"detail":"    正念减压自我练习系列，特別为忙碌的您所设置，大多数的练习都是10来分钟，少数的练习为20多分钟。20个单一主題式练习，让您轻松卻有系統地学习，正念减压训练课程中各种自我照顾的良方。本系列不只是概念的传递，更是实践的落地，就是希望您从自我实做中大量获益。\r\n\r\n
     * 正念，是落实活在当下的能力，\r\n      是平衡身与心的能力，\r\n      是温柔自我对待的能力，\r\n      是提升专注力与工作效能的能力，\r\n      是促进自我身心健康喜悅的能力。\r\n
     * \r\n但，正念，需要经常练习，甚至是每天练习，這整个系列希望协助您养成练习的习惯。透过每次的练习，安然与自我同在，开发內在的资源，滋养并丰厚自己。\r\n \r\n【备注】 \r\n1. 本系列不是正念减压课程的替代，在正念减压课程中仍有许多精彩丰富的地方于此无法呈现，因此若有机会接受正规训练，君梅老师是相当鼓励的。 \r\n2.
     * 每个系列的练习前后多少有点连贯性，因此，最好还是从第一个开始练习。但系列之间的练习则无次序之分。 \r\n3. 建议练习的方式，每一个练习至少连续做3-7天，真的品尝到该练习的滋味后，再换到下一个练习。当然也可以持续前一个练习，例如饮食静观练习一周后，即便改练习行走静观，都可以自行持续进行饮食静观的练习，沒有冲突的。 \r\n4.
     * 这是个有方向却无特定目标的自我修炼历程，不需要冲快，不需要做给別人看，也不需要获得別人的赞赏。自己的获益才是最实际的，也才是持续练习的最大动力。 \r\n","ppt":["https://www.dgli.net/resource/images/course/01.jpg","https://www.dgli
     * .net/resource/images/course/02.jpg","https://www.dgli.net/resource/images/course/03.jpg","https://www.dgli.net/resource/images/course/04.jpg","https://www.dgli
     * .net/resource/images/course/05.jpg"],"teacherName":"胡君梅","url":"bc1WlEDCqibieAHE+1T7BFtlaZcEIIeokr3Ywx1ARwSGNjALFhNeRhEWveVf73/i"}
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
         * detail :     正念减压自我练习系列，特別为忙碌的您所设置，大多数的练习都是10来分钟，少数的练习为20多分钟。20个单一主題式练习，让您轻松卻有系統地学习，正念减压训练课程中各种自我照顾的良方。本系列不只是概念的传递，更是实践的落地，就是希望您从自我实做中大量获益。

         正念，是落实活在当下的能力，
         是平衡身与心的能力，
         是温柔自我对待的能力，
         是提升专注力与工作效能的能力，
         是促进自我身心健康喜悅的能力。

         但，正念，需要经常练习，甚至是每天练习，這整个系列希望协助您养成练习的习惯。透过每次的练习，安然与自我同在，开发內在的资源，滋养并丰厚自己。

         【备注】
         1. 本系列不是正念减压课程的替代，在正念减压课程中仍有许多精彩丰富的地方于此无法呈现，因此若有机会接受正规训练，君梅老师是相当鼓励的。
         2. 每个系列的练习前后多少有点连贯性，因此，最好还是从第一个开始练习。但系列之间的练习则无次序之分。
         3. 建议练习的方式，每一个练习至少连续做3-7天，真的品尝到该练习的滋味后，再换到下一个练习。当然也可以持续前一个练习，例如饮食静观练习一周后，即便改练习行走静观，都可以自行持续进行饮食静观的练习，沒有冲突的。
         4. 这是个有方向却无特定目标的自我修炼历程，不需要冲快，不需要做给別人看，也不需要获得別人的赞赏。自己的获益才是最实际的，也才是持续练习的最大动力。

         * ppt : ["https://www.dgli.net/resource/images/course/01.jpg","https://www.dgli.net/resource/images/course/02.jpg","https://www.dgli.net/resource/images/course/03.jpg",
         * "https://www.dgli.net/resource/images/course/04.jpg","https://www.dgli.net/resource/images/course/05.jpg"]
         * teacherName : 胡君梅
         * url : bc1WlEDCqibieAHE+1T7BFtlaZcEIIeokr3Ywx1ARwSGNjALFhNeRhEWveVf73/i
         */

        private String detail;
        private String teacherName;
        private String url;
        private int userCoin;
        private int buyCount;
        private List<String> ppt;
        private boolean isBuy;
        private boolean isCollect;
        private boolean isLike;

        public boolean isCollect() {
            return isCollect;
        }

        public void setCollect(boolean collect) {
            isCollect = collect;
        }

        public boolean isLike() {
            return isLike;
        }

        public void setLike(boolean like) {
            isLike = like;
        }

        public int getUserCoin() {
            return userCoin;
        }

        public void setUserCoin(int userCoin) {
            this.userCoin = userCoin;
        }

        public int getBuyCount() {
            return buyCount;
        }

        public void setBuyCount(int buyCount) {
            this.buyCount = buyCount;
        }

        public boolean isBuy() {
            return isBuy;
        }

        public void setBuy(boolean buy) {
            isBuy = buy;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getPpt() {
            return ppt;
        }

        public void setPpt(List<String> ppt) {
            this.ppt = ppt;
        }
    }
}
