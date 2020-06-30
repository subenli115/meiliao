package com.ziran.meiliao.ui.bean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/3 16:12
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/3$
 * @updateDes ${TODO}
 */

public  class PicsBean  extends ShareBean{
    /**
     * link : 这是一个路径
     * type : h5
     */

    private String link;
    private String type;
    private String picture;
    private String id;
    private boolean isCollect;

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public String getLink() {
        return link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String url) {
        this.picture = url;
    }

    @Override
    public String toString() {
        return "PicsBean{" + "link='" + link + '\'' + ", type='" + type + '\'' + ", picture='" + picture + '\'' + '}';
    }
}
