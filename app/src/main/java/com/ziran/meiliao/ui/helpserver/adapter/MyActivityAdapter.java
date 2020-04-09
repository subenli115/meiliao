package com.ziran.meiliao.ui.helpserver.adapter;

import android.content.Context;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.OrderDetailBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/4 14:21
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/4$
 * @updateDes ${TODO}
 */

public class MyActivityAdapter extends CommonRecycleViewAdapter<OrderDetailBean> {
    private int fromType;

    public MyActivityAdapter(Context context, int fromType) {
        super(context, R.layout.item_my_team);
        this.fromType = fromType;
    }


    @Override
    public void convert(ViewHolderHelper helper, OrderDetailBean bean, int position) {
        helper.setText(R.id.tv_item_tag,  bean.getOrderTypeName());
        helper.setText(R.id.tv_item_time, TimeUtil.getStringByFormat(bean.getCreateTime()));
        helper.setText(R.id.tv_item_title, bean.getTitle());
//        helper.setImageUrlTarget(R.id.iv_item_img, AppConstant.URL, R.mipmap.ic_loading_square);
        helper.setImageUrlTarget(R.id.iv_item_img,bean.getPicture(), R.mipmap.ic_loading_square);
        helper.setText(R.id.tv_item_state, bean.getStatusMsg());
        helper.setTextColorRes(R.id.tv_item_state, getTextColorByState(bean.getStatus()));
        helper.setBackgroundColorNew(R.id.view_line, getColorByPosition(bean.getStatus()));
//        helper.setText(R.id.tv_item_title, "10天深度睡眠");
        helper.setText(R.id.tv_item_price, String.format("¥%d", bean.getPrice()));
        helper.setText(R.id.tv_item_order_number, String.format("订单编号：%s", bean.getOrderNumber()));
    }

    private int getTextColorByState(int state) {
        return state == 2 ? R.color.textColor_teshe : R.color.textColor_teshe5;
    }

    private int getColorByPosition(int state) {
        return state == 2 ? R.color.textColor_teshe : R.color.textColor_teshe5;
//        return 0;
    }
}
