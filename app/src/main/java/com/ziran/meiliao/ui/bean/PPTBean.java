package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/21 15:32
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/21$
 * @updateDes ${TODO}
 */

public class PPTBean extends Result {

    /**
     * data : {"prefix":"http://www.dgli.net:8888/resource/images/course/","ppt":["35.png","35.png","35.png","35.png","35.png","35.png"]}
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
         * prefix : http://www.dgli.net:8888/resource/images/course/
         * ppt : ["35.png","35.png","35.png","35.png","35.png","35.png"]
         */

        private String prefix;
        private List<String> ppt;

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public List<String> getPpt() {
            return ppt;
        }

        public void setPpt(List<String> ppt) {
            this.ppt = ppt;
        }
    }
}
