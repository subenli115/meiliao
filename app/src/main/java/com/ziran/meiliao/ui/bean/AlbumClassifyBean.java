package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * Created by Administrator on 2019/2/1.
 */

public class AlbumClassifyBean extends Result{
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private List<TagListBean> tagList;

        public List<TagListBean> getTagList() {
            return tagList;
        }

        public void setTagList(List<TagListBean> tagList) {
            this.tagList = tagList;
        }

        public static class TagListBean {
            /**
             * id : 100
             * name : 功能
             * nextTagList : [{"nextTagId":1,"nextTagName":"减压"},{"nextTagId":2,"nextTagName":"睡眠"},{"nextTagId":3,"nextTagName":"专注"},{"nextTagId":4,"nextTagName":"情绪管理"},{"nextTagId":5,"nextTagName":"幸福"},{"nextTagId":6,"nextTagName":"疗愈"},{"nextTagId":7,"nextTagName":"个人成长"},{"nextTagId":8,"nextTagName":"人际关系"},{"nextTagId":9,"nextTagName":"分娩养育"},{"nextTagId":11,"nextTagName":"领导力"},{"nextTagId":11,"nextTagName":"领导力"}]
             */

            private int id;
            private String name;
            private List<NextTagListBean> nextTagList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<NextTagListBean> getNextTagList() {
                return nextTagList;
            }

            public void setNextTagList(List<NextTagListBean> nextTagList) {
                this.nextTagList = nextTagList;
            }

            public static class NextTagListBean {
                /**
                 * nextTagId : 1
                 * nextTagName : 减压
                 */
                private boolean isSelected;
                public boolean isSelected() {
                    return isSelected;
                }
                public void setSelected(boolean selected) {
                    isSelected = selected;
                }
                private int nextTagId;
                private String nextTagName;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                private String name;

                public int getNextTagId() {
                    return nextTagId;
                }

                public void setNextTagId(int nextTagId) {
                    this.nextTagId = nextTagId;
                }

                public String getNextTagName() {
                    return nextTagName;
                }

                public void setNextTagName(String nextTagName) {
                    this.nextTagName = nextTagName;
                }
            }
        }
    }



}
