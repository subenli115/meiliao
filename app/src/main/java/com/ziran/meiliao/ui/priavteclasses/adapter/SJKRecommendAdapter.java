package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.adapter.helper.SubscriptionHelper;
import com.ziran.meiliao.ui.adapter.helper.WordshopsHelper;
import com.ziran.meiliao.ui.bean.ActisData;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.bean.SpecColumnData;
import com.ziran.meiliao.ui.bean.ZhiBoData;
import com.ziran.meiliao.ui.priavteclasses.util.CountDownUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/8 15:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/8$
 * @updateDes ${TODO}
 */


public class SJKRecommendAdapter extends MultiItemRecycleViewAdapter<Object> {

    public static final int TYPE_GZF_LEFT = 55555;
    public static final int TYPE_GZF_TOP = 55556;

    public SJKRecommendAdapter(Context context) {
        super(context, new SJKRecommendMultiItemType());
    }

    @Override
    public void convert(ViewHolderHelper holder, Object bean, final int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case HeadData.Type.TITLE:
                final HeadData dataBean = EmptyUtils.parseObject(bean);
                holder.setText(R.id.tv_item_main_home_title, dataBean.getTitle());
                holder.setVisible(R.id.tv_item_main_home_more, dataBean.isShowMoreTitle());
                holder.setVisible(R.id.view_top, dataBean.isShowDivler());
                holder.setOnClickListener(R.id.tv_item_main_home_more, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RxManagerUtil.post(AppConstant.RXTag.CATEGORY_MORE_CLICK, dataBean.getId());
                    }
                });

                break;
            case HeadData.Type.ZHUANLAN:
                SubscriptionHelper.convert(holder, bean);
                break;
            case HeadData.Type.ZHIBO:
                ZhiBoData zhiBoData = EmptyUtils.parseObject(bean);
                holder.setText(R.id.tv_item_main_home_zhibo_title, zhiBoData.getTitle());
                CountDownUtil.get().execute(holder, R.id.tv_item_main_home_zhibo_cown_time, zhiBoData.getCountDown(), null);
                break;
            case TYPE_GZF_LEFT:
                WordshopsHelper.convert(holder, (ActisData) bean, position, false);
                break;
            case TYPE_GZF_TOP:
                WordshopsHelper.convert(holder, (ActisData) bean, position, true);
                break;
        }
    }


    private static class SJKRecommendMultiItemType implements MultiItemTypeSupport<Object> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case HeadData.Type.ZHIBO:
                    return R.layout.item_main_home_zhibo;
                case HeadData.Type.ZHUANLAN:
                    return R.layout.item_sjk_recommend_zhuanlan;
                case HeadData.Type.TITLE:
                    return R.layout.item_main_home_title;
                case TYPE_GZF_LEFT:
                    return R.layout.item_main_sjk_act_left;
                case TYPE_GZF_TOP:
                    return R.layout.item_main_sjk_act_top;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, Object dataBean) {
            if (dataBean instanceof HeadData) {
                return HeadData.Type.TITLE;
            } else if (dataBean instanceof SpecColumnData) {
                return HeadData.Type.ZHUANLAN;
            } else if (dataBean instanceof ZhiBoData) {
                return HeadData.Type.ZHIBO;
            } else if (dataBean instanceof ActisData) {
                if (((ActisData) dataBean).getStatus() == 1) {
                    return TYPE_GZF_TOP;
                } else {
                    return TYPE_GZF_LEFT;
                }

            }
            return 0;
        }
    }

}
