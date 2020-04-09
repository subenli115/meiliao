package com.ziran.meiliao.ui.decompressionmuseum.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.CategoryBean;

/**
 * 导聆分类内容的适配器
 */

public class CategoryDetailAdapter extends CommonRecycleViewAdapter<CategoryBean.DataBean> {

    public CategoryDetailAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolderHelper helper, CategoryBean.DataBean bean, int position) {
        ImageView iv = helper.getView(R.id.iv_jyg_category_img);
        ImageLoaderUtils.displayTager(mContext,iv,bean.getPicture(),R.mipmap.ic_loading_square_big);
        helper.setText(R.id.tv_jyg_category_title, bean.getTitle());
        boolean showSubTitle = EmptyUtils.isNotEmpty(bean.getFbt());
        helper.setVisible(R.id.tv_jyg_category_subTitle, showSubTitle);
        if (showSubTitle){
            helper.setText(R.id.tv_jyg_category_subTitle, bean.getFbt());
        }
        helper.setText(R.id.tv_jyg_category_descript, bean.getDescript());
        helper.setText(R.id.tv_jyg_category_anchor, bean.getName());
    }
}
