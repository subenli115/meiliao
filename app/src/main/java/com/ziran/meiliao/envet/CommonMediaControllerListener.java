package com.ziran.meiliao.envet;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/11 10:56
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/11$
 * @updateDes ${TODO}
 */

public interface CommonMediaControllerListener {
    void changeScreen(boolean isFull);

    void onBack();

    void onShare();
}