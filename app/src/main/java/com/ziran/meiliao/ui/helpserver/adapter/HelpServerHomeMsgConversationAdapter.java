package com.ziran.meiliao.ui.helpserver.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/2/2 10:53
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/2/2$
 * @updateDes ${TODO}
 */

public class HelpServerHomeMsgConversationAdapter  extends CommonRecycleViewAdapter<String>{
    public HelpServerHomeMsgConversationAdapter(Context context) {
        super(context, R.layout.item_help_server_home_conversation);
    }

    @Override
    public void convert(ViewHolderHelper helper, String s, int position) {

    }
}
