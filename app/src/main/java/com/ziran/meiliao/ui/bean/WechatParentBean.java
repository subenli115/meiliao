package com.ziran.meiliao.ui.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WechatParentBean {

    private Date date;

    public List<WechatListDataBean.DataBean.RecordsBean> getList() {
        return list;
    }

    public Date getTime() {
        return date;
    }

    public void setTime(Date date) {
        this.date = date;
    }

    public void setList(List<WechatListDataBean.DataBean.RecordsBean> list) {
        this.list = list;
    }

    private List<WechatListDataBean.DataBean.RecordsBean> list=new ArrayList<>();

}
