package com.ziran.meiliao.ui.bean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/20 11:18
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/20$
 * @updateDes ${TODO}
 */

public class CrowdFundingPreviewBean {
    private int type;
    private String name;//标题
    private String title = "";//标题
    private String intro;//内容
    private int index;
    private String picture;
    private String sponsor;//


    public CrowdFundingPreviewBean(int type, int index, String title, String intro) {
        this.type = type;
        this.title = title;
        this.intro = intro;
        this.index = index;
    }

    public CrowdFundingPreviewBean( int type,int index, String title, String name, String intro, String picture) {
        this.type = type;
        this.name = name;
        this.title = title;
        this.intro = intro;
        this.index = index;
        this.picture = picture;
    }


    public int getIndex() {
        return index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIndex(int index) {
        this.index = index;
    }



    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }



    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    @Override
    public String toString() {
        return "CrowdFundingPreviewBean{" + "type=" + type + ", name='" + name + '\'' + ", title='" + title + '\'' + ", intro='" + intro
                + '\'' + ", index=" + index + ", picture='" + picture + '\'' + ", sponsor='" + sponsor + '\'' + '}';
    }
}
