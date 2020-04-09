package com.ziran.meiliao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;

import cn.rongcloud.live.ui.widget.ChatListView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/8 17:52
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/8$
 * @updateDes ${TODO}
 */

public class ChatCustomView extends LinearLayout {

    private View rootView;
    private ChatListView mChatListView;
    public ChatCustomView(Context context) {
        this(context, null);
    }

    public ChatCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rootView = LayoutInflater.from(context).inflate(R.layout.inculde_sjk_fulllive_chat, this, true);
        mChatListView = ViewUtil.getView(rootView,R.id.chatListView);
    }

    public void setChatListViewWidth(int width){
        mChatListView.getLayoutParams().width = width;
        mChatListView.requestLayout();
    }
}
