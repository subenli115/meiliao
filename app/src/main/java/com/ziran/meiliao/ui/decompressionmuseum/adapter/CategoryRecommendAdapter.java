package com.ziran.meiliao.ui.decompressionmuseum.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.bean.AlbumListBean;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.main.listener.OnClickMoreListener;
import com.ziran.meiliao.utils.StringUtils;

/**
 *  导聆推荐分类的适配器
 */

public class CategoryRecommendAdapter extends MultiItemRecycleViewAdapter<Object> {


    public CategoryRecommendAdapter(Context context, MultiItemTypeSupport<Object> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    @Override
    public void convert(ViewHolderHelper helper, Object bean, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_HEAD:  //标题
                final HeadData headData = EmptyUtils.parseObject(bean);
                helper.setText(R.id.tv_item_main_home_title, headData.getTitle());
                helper.setText(R.id.tv_item_main_home_more, headData.getMoreText());
                helper.setOnClickListener(R.id.tv_item_main_home_more, new OnClickMoreListener(headData));
                helper.setVisible(R.id.tv_item_main_home_more,  headData.getSize()==3 && headData.isShowMoreTitle());
                helper.setVisible(R.id.view_top, headData.isShowDivler());
                break;
            case TYPE_DATA:  //item
                AlbumListBean albumListBean = EmptyUtils.parseObject(bean);
                ImageView iv = helper.getView(R.id.iv_jyg_recommend_img);
                helper.setText(R.id.tv_jyg_recommend_title, albumListBean.getTitle());
                helper.setText(R.id.tv_jyg_recommend_anchor, albumListBean.getAuthor().getName());
                helper.setText(R.id.tv_jyg_recommend_lable, albumListBean.getVip());
                helper.setText(R.id.tv_jyg_recommend_profile, albumListBean.getDetail());
                helper.setText(R.id.tv_jyg_listen_count, StringUtils.format(StringUtils.getText(R.string.lister_count), albumListBean
                        .getListenCount()));
                ImageLoaderUtils.display(mContext, iv, albumListBean.getPicture() + "", R.mipmap.ic_loading_rectangle);
                break;
        }
    }

    public static class ReMultiItemTypeSupport implements MultiItemTypeSupport<Object> {
        @Override
        public int getLayoutId(int itemType) {
            if (itemType == TYPE_HEAD) {
                return R.layout.item_main_home_title;
            } else {
                return R.layout.item_main_jyg_recommend;
            }
        }
        @Override
        public int getItemViewType(int position, Object bean) {
            if (bean instanceof HeadData) {
                return TYPE_HEAD;
            }
            return TYPE_DATA;
        }
    }
}
