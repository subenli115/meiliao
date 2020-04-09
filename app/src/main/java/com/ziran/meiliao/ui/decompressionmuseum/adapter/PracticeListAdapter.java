package com.ziran.meiliao.ui.decompressionmuseum.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.PracticeListBean;
import com.ziran.meiliao.utils.HtmlUtil;

/**
 * 播放界面音乐列表适配器
 */

public class PracticeListAdapter extends CommonRecycleViewAdapter<PracticeListBean.DataBean> {

    private Drawable mAlbumDrawable, mNormalDrawable;

    public PracticeListAdapter(Context context) {
        super(context, R.layout.activity_pratice_list);
        mAlbumDrawable = context.getResources().getDrawable(R.drawable.selector_radius_white);
        mNormalDrawable = context.getResources().getDrawable(R.drawable.selector_radius_stoke_white);
    }

    @Override
    public void convert(ViewHolderHelper helper, final PracticeListBean.DataBean dataBean, int position) {
        helper.setText(R.id.tv_title, dataBean.getTitle());
        helper.setText(R.id.tv_time, HtmlUtil.format("%s  %s", TimeUtil.formatHHMMSS(dataBean.getDuration()), dataBean.getCreateTime()));
        View view = helper.getView(R.id.rl_bg);
        boolean isAlbum = EmptyUtils.isNotEmpty(dataBean.getAlbumId());
        ViewUtil.setBackgroundDrawable(view, isAlbum ? mAlbumDrawable : mNormalDrawable);
        helper.setImageUrl(R.id.iv_pic,dataBean.getPicture(),R.mipmap.ic_loading_rectangle);
//        helper.setTextColor(R.id.tv_title, isAlbum ? R.color.textColor_333 : R.color.white);
//        helper.setTextColor(R.id.tv_time, isAlbum ? R.color.textColor_666 : R.color.white);
    }
}
