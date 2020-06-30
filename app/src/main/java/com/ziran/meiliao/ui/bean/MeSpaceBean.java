package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class MeSpaceBean extends Result {





        /**
         * code : 0
         * msg :
         * data : ["http://meiliao.ziran518.com/c438eab55b2541b6b14a65fb4f685339.jpg","http://meiliao.ziran518.com/c438eab55b2541b6b14a65fb4f685339.jpg","http://meiliao.ziran518.com/c438eab55b2541b6b14a65fb4f685339.jpg","http://meiliao.ziran518.com/c438eab55b2541b6b14a65fb4f685339.jpg","http://meiliao.ziran518.com/c438eab55b2541b6b14a65fb4f685339.jpg"]
         */

        private List<String> data;

        public List<String> getData() {
            return data;
        }

        public void setData(List<String> data) {
            this.data = data;
        }

}
