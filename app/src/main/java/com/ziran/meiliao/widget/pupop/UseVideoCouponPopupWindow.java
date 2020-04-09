package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.OnItemClickListener;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.UseVideoCouponAdapter;

import java.util.List;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class UseVideoCouponPopupWindow extends BasePopupWindow {
    private TextView tvTitle;
    private TextView tvUsed;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private UseVideoCouponAdapter mUseVideoCouponAdapter;

    public UseVideoCouponPopupWindow(Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_video_coupon;
    }

    @Override
    protected void initViews(View contentView) {
        tvTitle = ViewUtil.getView(contentView, R.id.tv_popuw_video_coupon_title);
        tvUsed = ViewUtil.getView(contentView, R.id.tv_popuw_video_coupon_use);
        mRecyclerView = ViewUtil.getView(contentView, R.id.recyclerView);
        ViewUtil.getView(contentView, R.id.tv_popuw_video_coupon_use).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUseVideoCouponAdapter != null) {
                    SJKLiveDetailProfileBean.DataBean.UserTickBean userTickBean = mUseVideoCouponAdapter.getSelect();
                    RxManagerUtil.post(AppConstant.RXTag.USER_TICK,userTickBean);
                    dismiss();
                }
            }
        });
        touchDismiss(R.id.touch_outside);
        setBottomMargin(getView(R.id.contentView));
    }


    public void setData(List<SJKLiveDetailProfileBean.DataBean.UserTickBean> data) {
        if (mUseVideoCouponAdapter == null) {
            mUseVideoCouponAdapter = new UseVideoCouponAdapter(mContext, R.layout.item_use_video_coupon);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mRecyclerView.setAdapter(mUseVideoCouponAdapter);
            mUseVideoCouponAdapter.setHeadCount(0);
            mUseVideoCouponAdapter.setOnItemClickListener(new OnItemClickListener<SJKLiveDetailProfileBean.DataBean.UserTickBean>() {
                @Override
                public void onItemClick(ViewGroup parent, View view, SJKLiveDetailProfileBean.DataBean.UserTickBean o, int position) {
                    mUseVideoCouponAdapter.changeSelect(position);
                }

                @Override
                public boolean onItemLongClick(ViewGroup parent, View view, SJKLiveDetailProfileBean.DataBean.UserTickBean o, int position) {
                    return false;
                }
            });
        }
        tvTitle.setText(String.format("您有%d张观看券", data.size()));
        data.get(0).setSelect(true);
        mUseVideoCouponAdapter.replaceAll(data);
    }

}