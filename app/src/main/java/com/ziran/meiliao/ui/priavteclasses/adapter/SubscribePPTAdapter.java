package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.utils.StringUtils;

/**
 * 减压馆-消息
 * Created by Administrator on 2017/1/14.
 */

public class SubscribePPTAdapter extends CommonRecycleViewAdapter<String> {


    public SubscribePPTAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(final ViewHolderHelper helper, final String bean, int position) {
        ImageView convertView = (ImageView) helper.getConvertView();
        ImageLoaderUtils.displayTager(mContext, convertView, StringUtils.format("%s%s", prefix, bean), R.mipmap.ic_loading_square_big);
    }

    private String prefix;

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
