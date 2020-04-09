package com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.support;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/12 15:20
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/12$
 * @updateDes ${TODO}
 */

public abstract class SimpleOnItemClickListener<T> implements OnItemClickListener<T> {

    @Override
    public boolean onItemLongClick(ViewGroup parent, View view, T t, int position) {
        return false;
    }
}
