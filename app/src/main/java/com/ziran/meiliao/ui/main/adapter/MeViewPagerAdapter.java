package com.ziran.meiliao.ui.main.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.viewpager.CardPagerAdapter;
import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.bean.TrailerBean;

/**
 *  我的界面消息显示适配器
 */

public class MeViewPagerAdapter extends CardPagerAdapter<TrailerBean.DataBean> {


    @Override
    public int getAdapterResourse() {
        return R.layout.item_main_me_viewpager_adapter;
    }


    @Override
    public void bind(TrailerBean.DataBean item, View view, int position) {
        ImageView iv_img = ViewUtil.getView(view, R.id.iv_viewpager_me_img);
        TextView tv_title = ViewUtil.getView(view, R.id.tv_viewpager_me_title);
        TextView tv_author = ViewUtil.getView(view, R.id.tv_viewpager_me_antor);
        TextView tv_pro = ViewUtil.getView(view, R.id.tv_viewpager_me_pro);
        try {
            ImageLoaderUtils.display(iv_img.getContext(), iv_img, item.getPicture());
            tv_pro.setText(item.getDescript());
            tv_title.setText(item.getTitle());
            tv_author.setText(item.getAuthor().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
