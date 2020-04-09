package com.ziran.meiliao.ui.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/1/16.
 */

public class BootCampBean {

    public List<NewHomeDataBean.DataBean.PracticeBean> getBeans() {
        return beans;
    }

    public void setBeans(List<NewHomeDataBean.DataBean.PracticeBean> beans) {
        this.beans = beans;
    }

    List<NewHomeDataBean.DataBean.PracticeBean> beans;
}
