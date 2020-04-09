package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.adapter.helper.TrailerHelper;
import com.ziran.meiliao.ui.bean.SJKSingeLiveData;

/**
 * Created by Administrator on 2017/1/14.
 */

public class SJKTrailerAdapter extends CommonRecycleViewAdapter<SJKSingeLiveData> {

    public SJKTrailerAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(final ViewHolderHelper helper, SJKSingeLiveData bean, int position) {
        boolean showVip = EmptyUtils.isNotEmpty(bean.getVip());

        TrailerHelper.convert(helper,bean,showVip);

    }
}
