package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.im.activity.ChatActivity;
import com.ziran.meiliao.im.activity.CommonNoticeActivity;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.ui.bean.DynamicBean;
import com.ziran.meiliao.ui.bean.HomeMenuBean;
import com.ziran.meiliao.widget.GlideRoundTransform;

/**
 * Created by Administrator on 2017/2/8.
 */

public class MessageNoticeAdapter extends MultiItemRecycleViewAdapter<HomeMenuBean.DataBean> {

    public MessageNoticeAdapter(Context context) {
        super(context, new CouponMultiItemType());
    }
    private void gotoChatActivity(Context mContext,String userId) {
        Intent intent = new Intent();
        intent.putExtra(JGApplication.CONV_TITLE, "在线客服");
        intent.putExtra("targetId", userId);
        intent.putExtra("targetAppKey", MeiliaoConfig.IM_APPKEY);
        intent.putExtra("draft", "");
        intent.setClass(mContext, ChatActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void convert(ViewHolderHelper helper, HomeMenuBean.DataBean bean, int position) {
            helper.setText(R.id.tv_sign,"请点击查看详情");
        if(bean.getMenuName()!=null){
            helper.setText(R.id.tv_name,bean.getMenuName()+"");
        }
        Glide.with(mContext).load(bean.getMenuUrl()).into((ImageView) helper.getView(R.id.iv_head));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bean.getMenuName().equals("在线客服")&&bean.getRelationship().length()>2){
                    gotoChatActivity(mContext,bean.getRelationship());
                }else {
                    CommonNoticeActivity.startAction(mContext,bean.getRelationship());
                    helper.setVisible(R.id.all_unread_number,false);

                }
            }
        });
        if(bean.getUpdateTime()!=null){
            helper.setText(R.id.tv_time,TimeUtil.convertToShowStr(bean.getUpdateTime()));
        }
        if(bean.getNumber()>0){
            helper.setVisible(R.id.all_unread_number,true);
            helper.setText(R.id.all_unread_number,bean.getNumber()+"");
        }
        helper.setVisible(R.id.iv_real_name,false);
        helper.setVisible(R.id.iv_real_person,false);
    }


    private static class CouponMultiItemType implements MultiItemTypeSupport<HomeMenuBean.DataBean> {

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case 1:
                    return R.layout.item_common_msg;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, HomeMenuBean.DataBean bean) {
            return 1;
        }
    }
}
