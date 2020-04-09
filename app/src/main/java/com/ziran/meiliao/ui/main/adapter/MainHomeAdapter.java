package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
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
import com.ziran.meiliao.ui.main.listener.OnClickMoreListener;
import com.ziran.meiliao.ui.priavteclasses.util.CountDownUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/8 15:02
 * @des ${首页适配器}
 * @updateAuthor $Author$
 * @updateDate 2017/6/8$
 * @updateDes ${TODO}
 */


public class MainHomeAdapter extends MultiItemRecycleViewAdapter<Object> {

    public MainHomeAdapter(Context context, MultiItemTypeSupport<Object> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    @Override
    public void convert(ViewHolderHelper holder, Object bean, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case HeadData.Type.TITLE:
                HeadData dataBean = EmptyUtils.parseObject(bean);
                holder.setText(R.id.tv_item_main_home_title, dataBean.getTitle());
                holder.setVisible(R.id.view_top, dataBean.isShowDivler());
                holder.setOnClickListener(R.id.tv_item_main_home_more, new OnClickMoreListener(dataBean, AppConstant.RXTag
                        .MAIN_HOME_MORE_CLICK));
                break;
            case HeadData.Type.GONGZUOFANG_LEFT:
                WordshopsHelper.convert(holder, (ActisData) bean, position, false);
                break;
            case HeadData.Type.GONGZUOFANG_TOP:
                WordshopsHelper.convert(holder, (ActisData) bean, position, true);
                break;
            case HeadData.Type.ZHIBO:
                ZhiBoData zhiBoData = EmptyUtils.parseObject(bean);
                holder.setText(R.id.tv_item_main_home_zhibo_title, zhiBoData.getTitle());
                CountDownUtil.get().execute(holder, R.id.tv_item_main_home_zhibo_cown_time, zhiBoData.getCountDown(), null);
                break;
            case HeadData.Type.ZHUANLAN:
                SubscriptionHelper.convert(holder, bean);
                break;
        }
    }


    public static class MainHomeMultiItemType implements MultiItemTypeSupport<Object> {

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case HeadData.Type.ZHIBO:
                    return R.layout.item_main_home_zhibo;
                case HeadData.Type.GONGZUOFANG_LEFT:
                    return R.layout.item_main_sjk_act_left;
//                    return R.layout.item_main_home_course;
                case HeadData.Type.GONGZUOFANG_TOP:
                    return R.layout.item_main_sjk_act_top;
                case HeadData.Type.TITLE:
                    return R.layout.item_main_home_title;
                case HeadData.Type.ZHUANLAN:
                    return R.layout.item_sjk_recommend_zhuanlan;

            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, Object dataBean) {
            if (dataBean instanceof HeadData) {
                return HeadData.Type.TITLE;
            } else if (dataBean instanceof ZhiBoData) {
                return HeadData.Type.ZHIBO;
            } else if (dataBean instanceof SpecColumnData) {
                return HeadData.Type.ZHUANLAN;
            } else if (dataBean instanceof ActisData) {
                return ((ActisData) dataBean).getStatus() == 1 ? HeadData.Type.GONGZUOFANG_TOP : HeadData.Type.GONGZUOFANG_LEFT;
            }
            return 0;
        }
    }


}
