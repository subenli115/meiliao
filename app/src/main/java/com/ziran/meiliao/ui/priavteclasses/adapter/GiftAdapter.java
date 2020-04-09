package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.GiftBean;

/**
 * 减压馆-消息
 * Created by Administrator on 2017/1/14.
 */

public class GiftAdapter extends CommonRecycleViewAdapter<GiftBean.DataBean> {

    public GiftAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, GiftBean.DataBean bean, int position) {
        View convertView = helper.getConvertView();
        if (bean.isSelect()) {
            selectPos = position;
            convertView.setBackgroundResource(R.mipmap.course_bg_gift);
        } else {
            convertView.setBackgroundDrawable(null);
        }
        ImageView view = helper.getView(R.id.iv_sjk_live_gift_pic);
        helper.setText(R.id.iv_sjk_live_gift_goin, String.valueOf(bean.getNeedCoin()));
        ImageLoaderUtils.display(mContext, view, bean.getPath());
    }

    private int selectPos = -1;

    public void changeCheck(int position) {
        if (selectPos == position) return;
        if (selectPos != -1) {
            get(selectPos).setSelect(false);
        }
        get(position).setSelect(true);
        notifyDataSetChanged();
    }

    public GiftBean.DataBean getSelect() {
        if (selectPos == -1) return null;
        return get(selectPos);
    }
}
