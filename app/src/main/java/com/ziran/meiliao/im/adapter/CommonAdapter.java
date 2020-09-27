package com.ziran.meiliao.im.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.CommonListBean;
import com.ziran.meiliao.ui.bean.WechatParentBean;
import com.ziran.meiliao.ui.priavteclasses.activity.UserHomeActivity;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2019/1/31.
 */
public class CommonAdapter extends MultiItemRecycleViewAdapter<CommonListBean.DataBean.RecordsBean> {


    public static final int TYPE_TOP = 11;
    private final Activity mActivity;
    private final RxManager mRxManager;
    private final int mType;


    public CommonAdapter(Context context, RxManager rxManager, Activity activity, MultiItemTypeSupport<CommonListBean.DataBean.RecordsBean> multiItemTypeSupport, int type) {
        super(context, multiItemTypeSupport);
        mContext = context;
        mActivity = activity;
        mType=type;
        mRxManager=rxManager;
    }

    @Override
    public void convert(final ViewHolderHelper helper, final CommonListBean.DataBean.RecordsBean bean, final int position) {
        if(mType==1){
            helper.setText(R.id.tv_sign,"喜欢了你");
        }else {
            helper.setText(R.id.tv_sign,"访问了主页");
        }
        if(bean.getNickname()!=null){
            helper.setText(R.id.tv_name,bean.getNickname()+"");
        }
        Picasso.with(mContext).load(bean.getAvatar()+"").into((ImageView) helper.getView(R.id.iv_head));
        if(bean.getIsReal()==null||bean.getIsReal().equals("0")){
            helper.setVisible(R.id.iv_real_name,false);
            helper.setVisible(R.id.iv_real_person,false);
        }
        helper.setText(R.id.tv_time, TimeUtil.convertToShowStr(bean.getCreateTime()));
        helper.itemView.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                UserHomeActivity.startAction(bean.getUserId());
            }
        });
    }

    public static class ActivityMultiItemType implements MultiItemTypeSupport<CommonListBean.DataBean.RecordsBean> {

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
        public int getItemViewType(int position, CommonListBean.DataBean.RecordsBean newZhuanLanData) {
            return TYPE_TOP;
        }

    }
}
