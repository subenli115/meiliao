package com.ziran.meiliao.ui.bean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/11 15:20
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/11$
 * @updateDes ${TODO}
 */

public class HeadData {
    private int id;
    private String title;
    private boolean showDivler;
    private int lastIndex = -1;
    private int size;
    private int firstIndex;

    private String moreText;
    private boolean showMoreTitle = true;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(int lastIndex) {
        this.lastIndex = lastIndex;
    }

    public HeadData(int id, String title, boolean showDivler, String moreText) {
        this.id = id;
        this.title = title;
        this.showDivler = showDivler;
        this.moreText = moreText;
    }

    @Override
    public String toString() {

        return "HeadData{" + "title='" + title + '\'' + ", id=" + id + ", showDivler=" + showDivler + ", showMoreTitle=" + showMoreTitle
                + ", moreText='" + moreText + '\'' + '}';
    }

    public String getTitle() {
        return title;
    }

    public String getMoreText() {
        return moreText;
    }

    public void setMoreText(String moreText) {
        this.moreText = moreText;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isShowDivler() {
        return showDivler;
    }

    public void setShowDivler(boolean showDivler) {
        this.showDivler = showDivler;
    }

    public boolean isShowMoreTitle() {
        return showMoreTitle;
    }

    public void setShowMoreTitle(boolean showMoreTitle) {
        this.showMoreTitle = showMoreTitle;
    }

    public HeadData(int id, String title, boolean showDivler) {
        this.title = title;
        this.id = id;
        this.showDivler = showDivler;
    }
    public HeadData(int id,String key, String title, boolean showDivler) {
        this.key = key;
        this.title = title;
        this.id = id;
        this.showDivler = showDivler;
    }

    public static HeadData create(int id, String title, boolean showDivler) {
        return new HeadData(id, title, showDivler);
    }

    public static HeadData create(int id, String title, boolean showDivler, boolean showMoreTitle) {
        return new HeadData(id, title, showDivler, showMoreTitle);
    }

    public HeadData(int id, String title, boolean showDivler, boolean showMoreTitle) {
        this.title = title;
        this.id = id;
        this.showDivler = showDivler;
        this.showMoreTitle = showMoreTitle;
    }

    public interface Type {
        int  TOP_OTHER=0x2210;
        int  TOP=0x2211;
        int ZHIBO = 0x2212;
        int COURSE = 0x2213;
        int TITLE = 0x2214;
        int DAPAI = 0x2215;
        int ZHUANLAN = 0x2216;
        int GONGZUOFANG = 0x2217;
        int GONGZUOFANG_LEFT = 0x2218;
        int GONGZUOFANG_TOP = 0x2219;
        int ALBUM = 0x2220;
        int BOOTCAMP=0x2221;

    }
}