package com.ziran.meiliao.widget;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/11 18:56
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/11$
 * @updateDes ${TODO}
 */

public interface IMyMediaController {
    void setTitle(String title);
    void changeScreen();
    boolean isFullScreen();
    void reset();
    void showShikan(boolean visible);
    void setWatchCount(int watchCount);
}
