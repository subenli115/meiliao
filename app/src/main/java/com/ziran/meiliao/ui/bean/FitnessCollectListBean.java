package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class FitnessCollectListBean extends Result {



    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private List<CollectListBean> collectList;

        public List<CollectListBean> getCollectList() {
            return collectList;
        }

        public void setCollectList(List<CollectListBean> collectList) {
            this.collectList = collectList;
        }

        public static class CollectListBean {
            /**
             * collectTime : 1569319008000
             * description : 五禽戏是一种中国导引术,是由模仿5种动物的动作而形成的强身功法。“导引”是一项以肢体运动为主,配合呼吸吐纳的养生方式,源于上古的舞蹈动作。春秋战国时期,导引术获得长足的发展,出现了 “熊经”“鸟伸”等术势。五禽戏模仿的5种动物分别为虎、鹿、熊、猿、鸟。所以五禽戏中任何一戏的演练，既能主治对应脏腑的疾患，又能兼治其他各脏的疾病，达到祛病强身，延年益寿的作用。
             * name : 五禽戏
             * pic : http://ojlzx3sl8.bkt.clouddn.com/js_1_home.png
             * courseId : 1
             * jieshu : 11
             */

            private long collectTime;
            private String description;
            private String name;
            private String pic;
            private int courseId;
            private int jieshu;

            public long getCollectTime() {
                return collectTime;
            }

            public void setCollectTime(long collectTime) {
                this.collectTime = collectTime;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public int getJieshu() {
                return jieshu;
            }

            public void setJieshu(int jieshu) {
                this.jieshu = jieshu;
            }
        }
    }
}
