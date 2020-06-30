package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

public class ReportListBean extends Result {


    /**
     * code : 0
     * msg :
     * data : [{"id":101,"dictId":37,"value":"01","label":"发布色情/违法等不良信息","type":"report","description":"色情、血腥暴恐、自杀自残、含有毒品/违禁品信息、肖像和内容侵权、侵犯公民个人信息","sort":1,"createTime":"2020-06-23 15:48:16","updateTime":"2020-06-23 15:48:16","remarks":"","delFlag":"0","tenantId":1},{"id":102,"dictId":37,"value":"02","label":"欺诈骗钱","type":"report","description":"网络兼职/刷单诈骗、金融欺诈/红包欺诈","sort":2,"createTime":"2020-06-23 15:48:31","updateTime":"2020-06-23 15:48:31","remarks":"","delFlag":"0","tenantId":1},{"id":103,"dictId":37,"value":"03","label":"广告骚扰","type":"report","description":"广告、人身攻击、侮辱谩骂、诽谤","sort":3,"createTime":"2020-06-23 15:48:56","updateTime":"2020-06-23 15:48:56","remarks":"","delFlag":"0","tenantId":1},{"id":104,"dictId":37,"value":"04","label":"其他","type":"report","description":"涉政、涉及未成年、头像/资料作假等","sort":4,"createTime":"2020-06-23 15:49:14","updateTime":"2020-06-23 15:49:14","remarks":"","delFlag":"0","tenantId":1}]
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 101
         * dictId : 37
         * value : 01
         * label : 发布色情/违法等不良信息
         * type : report
         * description : 色情、血腥暴恐、自杀自残、含有毒品/违禁品信息、肖像和内容侵权、侵犯公民个人信息
         * sort : 1
         * createTime : 2020-06-23 15:48:16
         * updateTime : 2020-06-23 15:48:16
         * remarks :
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
