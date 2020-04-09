package com.ziran.meiliao.ui.bean;

import java.util.List;

/**
 * 父recycler on 2018/8/16.
 */

public class RecordParentInfoBean {
    private String title;

    private List<RecordChildInfoBean> menuList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RecordChildInfoBean> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<RecordChildInfoBean> menuList) {
        this.menuList = menuList;
    }
}
