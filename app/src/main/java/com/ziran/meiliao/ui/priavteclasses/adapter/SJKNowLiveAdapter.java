package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.adapter.helper.NowLiveHelper;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;

/**
 * Created by Administrator on 2017/1/14.
 */

public class SJKNowLiveAdapter extends CommonRecycleViewAdapter<SJKSingeLiveData> {

    public SJKNowLiveAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(final ViewHolderHelper helper, SJKSingeLiveData bean, int position) {
        NowLiveHelper.convert(helper, bean, position, null);

    }
}
