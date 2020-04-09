package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.MessageBean;

/**
 *  减压馆-消息
 * Created by Administrator on 2017/1/14.
 */

public class GFMessageAdapter extends CommonRecycleViewAdapter<MessageBean.DataBean> {

    public GFMessageAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, MessageBean.DataBean bean, int position) {
        if (EmptyUtils.isEmpty(bean)) return;
        ImageView view = helper.getView(R.id.iv_item_jyg_message_img);
        helper.setText(R.id.tv_item_jyg_message_title,bean.getTitle());
        helper.setText(R.id.tv_item_jyg_message_time,bean.getCreateTime());
        ImageLoaderUtils.display(mContext,view,bean.getPicture(),R.mipmap.ic_loading_square_big);
    }
}
