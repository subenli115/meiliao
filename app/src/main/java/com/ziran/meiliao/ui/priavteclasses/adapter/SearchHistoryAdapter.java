package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.abslistview.CommonAblistViewAdapter;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.SearchEntry;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/5 13:43
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/5$
 * @updateDes ${TODO}
 */


public class SearchHistoryAdapter extends CommonAblistViewAdapter<SearchEntry> {

    public SearchHistoryAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper holder, final SearchEntry item, final int position) {
        holder.setText(R.id.tv_search_history_title, item.getContent());
        holder.setOnClickListener(R.id.iv_search_history_delete, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               removeAt(position);
                DbCore.getDaoSession().getSearchEntryDao().delete(item);
            }
        });
    }
}
