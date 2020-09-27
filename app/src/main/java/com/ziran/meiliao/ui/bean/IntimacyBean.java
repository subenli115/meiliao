package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class IntimacyBean extends Result {


    /**
     * data : {"id":"1c2cf4cec47d1f826e35cbc822d67c39","userId":"c4beb6acb3635a3210536ae0a292638b","toUserId":"a7c7e5eea40e08f7c7fcb6dcfd86c352","nickname":null,"toNickname":null,"total":2,"alias":"萍水相逢","level":"1","levelImg":"https://zrwlmeiliao.oss-cn-shanghai.aliyuncs.com/icon/1.png","jurisdiction":[{"id":105,"dictId":38,"value":"1","label":"萍水相逢","type":"intimacy_dict","description":"https://zrwlmeiliao.oss-cn-shanghai.aliyuncs.com/icon/1.png","sort":1,"createTime":"2020-07-01 16:52:32","updateTime":"2020-08-12 16:28:43","remarks":"0","delFlag":"0","tenantId":1}]}
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
         * id : 1c2cf4cec47d1f826e35cbc822d67c39
         * userId : c4beb6acb3635a3210536ae0a292638b
         * toUserId : a7c7e5eea40e08f7c7fcb6dcfd86c352
         * nickname : null
         * toNickname : null
         * total : 2
         * alias : 萍水相逢
         * level : 1
         * levelImg : https://zrwlmeiliao.oss-cn-shanghai.aliyuncs.com/icon/1.png
         * jurisdiction : [{"id":105,"dictId":38,"value":"1","label":"萍水相逢","type":"intimacy_dict","description":"https://zrwlmeiliao.oss-cn-shanghai.aliyuncs.com/icon/1.png","sort":1,"createTime":"2020-07-01 16:52:32","updateTime":"2020-08-12 16:28:43","remarks":"0","delFlag":"0","tenantId":1}]
         */

        private String id;
        private String userId;
        private String toUserId;
        private Object nickname;
        private Object toNickname;
        private int total;
        private String alias;
        private String level;
        private String levelImg;
        private List<JurisdictionBean> jurisdiction;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getToUserId() {
            return toUserId;
        }

        public void setToUserId(String toUserId) {
            this.toUserId = toUserId;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getToNickname() {
            return toNickname;
        }

        public void setToNickname(Object toNickname) {
            this.toNickname = toNickname;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getLevelImg() {
            return levelImg;
        }

        public void setLevelImg(String levelImg) {
            this.levelImg = levelImg;
        }

        public List<JurisdictionBean> getJurisdiction() {
            return jurisdiction;
        }

        public void setJurisdiction(List<JurisdictionBean> jurisdiction) {
            this.jurisdiction = jurisdiction;
        }

        public static class JurisdictionBean {
            /**
             * id : 105
             * dictId : 38
             * value : 1
             * label : 萍水相逢
             * type : intimacy_dict
             * description : https://zrwlmeiliao.oss-cn-shanghai.aliyuncs.com/icon/1.png
             * sort : 1
             * createTime : 2020-07-01 16:52:32
             * updateTime : 2020-08-12 16:28:43
             * remarks : 0
             * delFlag : 0
             * tenantId : 1
             */

            private int id;
            private int dictId;
            private String value;
            private String label;
            private String type;
            private String description;
            private int sort;
            private String createTime;
            private String updateTime;
            private String remarks;
            private String delFlag;
            private int tenantId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getDictId() {
                return dictId;
            }

            public void setDictId(int dictId) {
                this.dictId = dictId;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public int getTenantId() {
                return tenantId;
            }

            public void setTenantId(int tenantId) {
                this.tenantId = tenantId;
            }
        }
    }
}
