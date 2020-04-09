package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/10 10:03
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/10$
 * @updateDes ${TODO}
 */

public class TjGzfAdapter extends CommonRecycleViewAdapter<String > {
    public TjGzfAdapter(Context context) {
        super(context, R.layout.item_zl_tj);
        setHeadCount(0);
    }

    @Override
    public void convert(ViewHolderHelper helper, String s, int position) {

    }
}
