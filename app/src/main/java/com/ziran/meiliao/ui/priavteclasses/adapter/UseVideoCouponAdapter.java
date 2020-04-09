package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.graphics.Color;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/15 10:59
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/15$
 * @updateDes ${TODO}
 */

public class UseVideoCouponAdapter extends CommonRecycleViewAdapter<SJKLiveDetailProfileBean.DataBean.UserTickBean> {
    public UseVideoCouponAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    private int selectPos;

    @Override
    public void convert(ViewHolderHelper helper, SJKLiveDetailProfileBean.DataBean.UserTickBean items, int position) {
        if (items.isSelect()) {
            selectPos = position;
            helper.setBackgroundRes(R.id.background, R.drawable.video_coupon_pre);
            helper.setTextColorNew(R.id.tv_use_video_coupon_title, Color.parseColor("#FFA008"));
            helper.setTextColorNew(R.id.tv_use_video_coupon_des, Color.parseColor("#333333"));
        } else {
            helper.setBackgroundRes(R.id.background, R.drawable.video_coupon_norm);
            helper.setTextColorNew(R.id.tv_use_video_coupon_title, Color.parseColor("#7E000000"));
            helper.setTextColorNew(R.id.tv_use_video_coupon_des, Color.argb(26,0,0,0));
        }
        helper.setText(R.id.tv_use_video_coupon_title, items.getTitle());
        helper.setText(R.id.tv_use_video_coupon_des, items.getDescript());
    }

    public SJKLiveDetailProfileBean.DataBean.UserTickBean getSelect() {
        return get(selectPos);
    }

    public void changeSelect(int position) {
        if (selectPos != -1 && selectPos != position) {
            get(selectPos).setSelect(false);
            get(position).setSelect(true);
            notifyDataSetChanged();
        }
    }
}
