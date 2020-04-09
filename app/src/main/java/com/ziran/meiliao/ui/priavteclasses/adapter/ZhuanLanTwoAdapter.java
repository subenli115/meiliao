package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.priavteclasses.activity.ZhuanLanDetailActivity;
import com.ziran.meiliao.ui.priavteclasses.bean.NewZhuanLanData;
import com.ziran.meiliao.widget.GlideRoundTransform;

/**
 * Created by Administrator on 2019/1/31.
 */

public class ZhuanLanTwoAdapter extends MultiItemRecycleViewAdapter<NewZhuanLanData.DataBean>{


        public static final int TYPE_TOP = 11;
         private final Activity mActivity;

    public ZhuanLanTwoAdapter(Context context,  Activity activity,MultiItemTypeSupport<NewZhuanLanData.DataBean> multiItemTypeSupport) {
            super(context, multiItemTypeSupport);
            mContext=context;
            mActivity=activity;
        }

        @Override
        public void convert(final ViewHolderHelper helper, final NewZhuanLanData.DataBean bean, final int position) {

            Glide.with(mContext).load(bean.getPicture()).transform(new GlideRoundTransform(mContext)).into(helper.getImageView(R.id.iv_item_sjk_act_img));
            ((TextView)helper.getView(R.id.tv_title)).setText(bean.getTitle());
            ((TextView)helper.getView(R.id.tv_teacher_name)).setText(bean.getTeacher());
            ((TextView)helper.getView(R.id.tv_js)).setText(bean.getSubscriptionNum()+"节");
            ((TextView)helper.getView(R.id.tv_content)).setText(bean.getDetail());
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
;                    ZhuanLanDetailActivity.startAction(mContext,bean.getSubscriptionId(),bean.isBuy(),bean.getHtmlLink(),mActivity,10,bean.getSubscriptionNum());
                }
            });


        }

        public static class ActivityMultiItemType implements MultiItemTypeSupport<NewZhuanLanData.DataBean> {

            public ActivityMultiItemType() {
            }

            @Override
            public int getLayoutId(int itemType) {
                switch (itemType) {
                    case TYPE_TOP:
                        return R.layout.item_main_sjk_act_left_new_three;
                }
                return -1;
            }

            @Override
            public int getItemViewType(int position, NewZhuanLanData.DataBean newZhuanLanData) {
                return TYPE_TOP;
            }

        }
}
