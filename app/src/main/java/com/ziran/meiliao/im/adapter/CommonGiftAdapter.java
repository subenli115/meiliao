package com.ziran.meiliao.im.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.bean.CommGiftListBean;
import com.ziran.meiliao.ui.bean.CommonListBean;
import com.ziran.meiliao.ui.priavteclasses.activity.UserHomeActivity;

/**
 * Created by Administrator on 2019/1/31.
 */
public class CommonGiftAdapter extends MultiItemRecycleViewAdapter<CommGiftListBean.DataBean.RecordsBean> {


    public static final int TYPE_TOP = 11;
    private final Activity mActivity;
    private final RxManager mRxManager;


    public CommonGiftAdapter(Context context, RxManager rxManager, Activity activity, MultiItemTypeSupport<CommGiftListBean.DataBean.RecordsBean> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
        mContext = context;
        mActivity = activity;
        mRxManager=rxManager;
    }

    @Override
    public void convert(final ViewHolderHelper helper, final CommGiftListBean.DataBean.RecordsBean bean, final int position) {
            helper.setText(R.id.tv_sign,"送了一个"+bean.getGiftName());
        if(bean.getGiveUserName()!=null){
            helper.setText(R.id.tv_name,bean.getGiveUserName()+"");
        }
        Glide.with(mContext).load(bean.getAvatar()).into((ImageView) helper.getView(R.id.iv_head));
        if(bean.getIsReal()==null||bean.getIsReal().equals("0")){
            helper.setVisible(R.id.iv_real_name,false);
            helper.setVisible(R.id.iv_real_person,false);
        }
        helper.setText(R.id.tv_time, TimeUtil.convertToShowStr(bean.getCreateTime()));
        helper.itemView.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                UserHomeActivity.startAction(bean.getGiveUserId());
            }
        });
    }

    public static class ActivityMultiItemType implements MultiItemTypeSupport<CommGiftListBean.DataBean.RecordsBean> {

        public ActivityMultiItemType() {
        }

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_TOP:
                    return R.layout.item_common_msg;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, CommGiftListBean.DataBean.RecordsBean newZhuanLanData) {
            return TYPE_TOP;
        }

    }
}
