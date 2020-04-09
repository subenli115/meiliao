package com.ziran.meiliao.ui.bean;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/22 18:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/22$
 * @updateDes ${TODO}
 */

public class MultiItemBean {
    public MultiItemBean(int itemType, List data) {
        this.itemType = itemType;
        this.data = data;
    }

    public MultiItemBean(int itemType) {
        this.itemType = itemType;
    }

    public MultiItemBean(int itemType, Object obj) {
        this.itemType = itemType;
        this.obj = obj;
    }
    public MultiItemBean(int itemType, String title) {
        this.itemType = itemType;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private int itemType;
    private List data;
    private String title;
    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public MultiItemBean(int itemType, List data, String title) {
        this.itemType = itemType;
        this.data = data;
        this.title = title;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MultiItemBean{" + "itemType=" + itemType + ", data=" + data + ", title='" + title + '\'' + ", obj=" + obj + '}';
    }
}
