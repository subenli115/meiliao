package com.ziran.meiliao.ui.helpserver.fragment;

import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.helpserver.adapter.HelpServerHomeMsgConversationAdapter;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/4 14:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/4$
 * @updateDes ${TODO}
 */

public class HelpServerHomeMsgConversationFragment extends CommonRefreshFragment {


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        return new HelpServerHomeMsgConversationAdapter(getContext());
    }

    @Override
    protected void loadData() {

    }


}
