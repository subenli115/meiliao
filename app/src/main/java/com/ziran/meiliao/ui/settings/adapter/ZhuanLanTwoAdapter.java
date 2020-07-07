package com.ziran.meiliao.ui.settings.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.bean.MyFollowBean;
import com.ziran.meiliao.widget.GlideRoundTransform;

/**
 * Created by Administrator on 2019/1/31.
 */

public class ZhuanLanTwoAdapter extends MultiItemRecycleViewAdapter<MyFollowBean.DataBean.RecordsBean> {


        public static final int TYPE_TOP = 11;
         private final Activity mActivity;

    public ZhuanLanTwoAdapter(Context context, Activity activity, MultiItemTypeSupport<MyFollowBean.DataBean.RecordsBean> multiItemTypeSupport) {
            super(context, multiItemTypeSupport);
            mContext=context;
            mActivity=activity;
        }

        @Override
        public void convert(final ViewHolderHelper helper, final MyFollowBean.DataBean.RecordsBean bean, final int position) {

            Glide.with(mContext).load(bean.getAvatar()).transform(new GlideRoundTransform(mContext)).into(helper.getImageView(R.id.iv_head));
            ((TextView)helper.getView(R.id.tv_sign)).setText(bean.getIntroduce());
            ((TextView)helper.getView(R.id.tv_name)).setText(bean.getNickname());
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });


        }

        public static class ActivityMultiItemType implements MultiItemTypeSupport<MyFollowBean.DataBean.RecordsBean> {

            public ActivityMultiItemType() {
            }

            @Override
            public int getLayoutId(int itemType) {
                switch (itemType) {
                    case TYPE_TOP:
                        return R.layout.item_user_follow;
                }
                return -1;
            }

            @Override
            public int getItemViewType(int position, MyFollowBean.DataBean.RecordsBean newZhuanLanData) {
                return TYPE_TOP;
            }

        }
}
