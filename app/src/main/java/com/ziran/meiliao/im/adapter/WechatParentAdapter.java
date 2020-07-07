package com.ziran.meiliao.im.adapter;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.WechatParentBean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Administrator on 2019/1/31.
 */
public class WechatParentAdapter extends MultiItemRecycleViewAdapter<WechatParentBean> {


    public static final int TYPE_TOP = 11;
    private final Activity mActivity;
    private final String mIsSelf;
    private final RxManager mRxManager;

    public WechatParentAdapter(Context context, RxManager rxManager, Activity activity, MultiItemTypeSupport<WechatParentBean> multiItemTypeSupport, String isSelf) {
        super(context, multiItemTypeSupport);
        mContext = context;
        mActivity = activity;
        mIsSelf=isSelf;
        mRxManager=rxManager;
    }

    @Override
    public void convert(final ViewHolderHelper helper, final WechatParentBean bean, final int position) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bean.getTime());

        ((TextView) helper.getView(R.id.tv_day)).setText(calendar.get(Calendar.DATE) + "");
        ((TextView) helper.getView(R.id.tv_month)).setText((calendar.get(Calendar.MONTH) +1) + "月");
            ((RecyclerView) helper.getView(R.id.menu_info_recyclerview)).setLayoutManager(new LinearLayoutManager(mContext));
            RecordChildInfoAdapter recordChildInfoAdapter = new RecordChildInfoAdapter(mContext,mRxManager, bean.getList(),mIsSelf,this,position);
            ((RecyclerView) helper.getView(R.id.menu_info_recyclerview)).setAdapter(recordChildInfoAdapter);

    }
    //  删除数据
    public void removeData(int position) {
        mDatas.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
        mRxManager.post(AppConstant.RXTag.UPDATE_USER, "");
    }

    public static class ActivityMultiItemType implements MultiItemTypeSupport<WechatParentBean> {

        public ActivityMultiItemType() {
        }

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_TOP:
                    return R.layout.item_wechat_record_day_data;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, WechatParentBean newZhuanLanData) {
            return TYPE_TOP;
        }

    }
}
