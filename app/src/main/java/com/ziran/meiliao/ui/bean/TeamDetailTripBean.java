package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/8 10:24
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/8$
 * @updateDes ${TODO}
 */

public class TeamDetailTripBean  {
    private boolean showLine;
    private String content;
    private String title;
    private String time;
    private String pic;
    private int type;

    public boolean isShowLine() {
        return showLine;
    }

    public void setShowLine(boolean showLine) {
        this.showLine = showLine;
    }

    public TeamDetailTripBean(int type, String title, String time, String content, String pic,boolean showLine) {
        this.type = type;
        this.title = title;
        this.time = time;
        this.content = content;
        this.pic = pic;
        this.showLine = showLine;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "TeamDetailTripBean{" + "type=" + type + ", title='" + title + '\'' + ", time='" + time + '\'' + ", content='" + content +
                '\'' + ", pic='" + pic + '\'' + ", showLine=" + showLine + '}';
    }

    public boolean checkShowLine() {
        return EmptyUtils.isNotEmpty(time)||EmptyUtils.isNotEmpty(pic)|| EmptyUtils.isNotEmpty(content);
    }
}
