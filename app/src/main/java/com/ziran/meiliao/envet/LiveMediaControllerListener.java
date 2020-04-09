package com.ziran.meiliao.envet;

import com.ziran.meiliao.widget.ChatCustomView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/11 10:57
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/11$
 * @updateDes ${TODO}
 */

public interface LiveMediaControllerListener {
    void sendMessage();

    void giveGift();

    void getChatListView(ChatCustomView chatCustomView);
}
