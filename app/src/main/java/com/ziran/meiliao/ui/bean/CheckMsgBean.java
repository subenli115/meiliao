package com.ziran.meiliao.ui.bean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/28 19:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/28$
 * @updateDes ${TODO}
 */

public class CheckMsgBean {
    private int id;
    private boolean isMustNeed;
    private boolean isCheck;
    private String title;
    private String key;
    public int getId() {
        return id;
    }

    public CheckMsgBean() {
    }

    public CheckMsgBean(boolean isCheck, String title) {
        this.isCheck = isCheck;
        this.title = title;
    }
    public CheckMsgBean(boolean isCheck, String title,String key) {
        this.isCheck = isCheck;
        this.title = title;
        this.key = key;
    }

    public CheckMsgBean(boolean isMustNeed, boolean isCheck, String title, String key) {
        this.isMustNeed = isMustNeed;
        this.isCheck = isCheck;
        this.title = title;
        this.key = key;
    }

    public CheckMsgBean(int id, boolean isCheck, String title) {
        this.id = id;
        this.isCheck = isCheck;
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public boolean isMustNeed() {
        return isMustNeed;
    }

    public void setMustNeed(boolean mustNeed) {
        isMustNeed = mustNeed;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "CheckMsgBean{" + "id=" + id + ", isMustNeed=" + isMustNeed + ", isCheck=" + isCheck + ", title='" + title + '\'' + ", " +
                "key='" + key + '\'' + '}';
    }
}
