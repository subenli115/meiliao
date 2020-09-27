package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.bean.BillDetailsBean;
import com.ziran.meiliao.ui.bean.HomeRecommendBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.me.activity.EditUserInfoActivity;
import com.ziran.meiliao.ui.me.activity.HomeImageSelectActivity;
import com.ziran.meiliao.ui.me.activity.SetRealPersonActivity;
import com.ziran.meiliao.ui.me.activity.SetRealPersonResultActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.UserHomeActivity;
import com.ziran.meiliao.utils.GlideCacheUtil;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.ziran.meiliao.widget.RoundTransform;

/**
 * Created by Administrator on 2017/2/8.
 */

public class HomeRecommendAdapter extends MultiItemRecycleViewAdapter<UserBean.DataBean> {

    private final int mType;
    private boolean isSelf;

    public HomeRecommendAdapter(Context context, int type) {
        super(context, new CouponMultiItemType());
        mType=type;
    }

    @Override
    public void convert(ViewHolderHelper holder, UserBean.DataBean itemData, int position) {
        if(itemData.getId().equals(MyAPP.getUserId())){
           isSelf=true;
           holder.setOnClickListener(R.id.arl_content,view -> {
               EditUserInfoActivity.startAction();
           });
            holder.setOnClickListener(R.id.iv_delete,view -> {
                mDatas.remove(position);
                notifyItemRemoved(position);
                if (position != mDatas.size()) {
                    notifyItemChanged(position, mDatas.size() - position);
                }
            });
        }else {
            isSelf=false;
        }
        Glide.with(mContext).load(itemData.getAvatar()).into(holder.getImageView(R.id.iv_item_sjk_act_img));
        if(isSelf){
            holder.setOnClickListener(R.id.iv_item_sjk_act_img, new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    if(itemData.getIsReal()==null||itemData.getIsReal().equals("0")){
                        SetRealPersonActivity.startAction(4);
                    }else {
                        SetRealPersonResultActivity.startAction();
                    }
                }
            });
        }
        holder.setText(R.id.tv_name,itemData.getNickname());
        if(!isSelf){
            holder.setVisible(R.id.tv_other1,false);
            holder.setVisible(R.id.tv_other,true);
            if(mType==2){
                holder.setVisible(R.id.tv_other2,true);
                holder.setVisible(R.id.tv_other,false);
                if(itemData.getDistance()!=null){
                    if(itemData.getDistance().equals("0.0")){
                        holder.setText(R.id.tv_other2,0+"m");
                    }else {
                        if(Double.parseDouble(itemData.getDistance())>1){
                            holder.setText(R.id.tv_other2,itemData.getDistance()+"km");
                        }else {
                            holder.setText(R.id.tv_other2,(int)(Double.parseDouble(itemData.getDistance())*1000)+"m");
                        }
                    }
                }
            }else {
                if(itemData.getStatus().equals("0")){
                    holder.setText(R.id.tv_other,"在线");
                    holder.setVisible(R.id.tv_other,true);
                    holder.setTextColor(R.id.tv_other,R.color.textColor_dynamic5);
                    holder.setBackgroundRes(R.id.tv_other,R.drawable.normal_bg_green_4d);
                }else {
                    holder.setVisible(R.id.tv_other,false);
                }
            }
        }
        if(itemData.getIsReal()==null||itemData.getIsReal().equals("0")){
            if(isSelf){
                holder.setVisible(R.id.tv_real,true);
                holder.setText(R.id.tv_real,"未认证");
                holder.setBackgroundRes(R.id.tv_real,R.drawable.background_gradient_rich);
            }else {
                holder.setVisible(R.id.tv_real,false);
            }
            holder.setVisible(R.id.iv_real_name,false);
            holder.setVisible(R.id.iv_real_person,false);
        }
        if(itemData.getSex() == 2){
            holder.setImageResource(R.id.iv_age,R.mipmap.icon_home_sex_woman);
            holder.setTextColor(R.id.tv_age,R.color.textColor_dynamic4);
            holder.setBackgroundRes(R.id.all_age,R.drawable.normal_bg_red_age);
            holder.setText(R.id.tv_age,itemData.getAge()+"岁");
        }else {
            holder.setImageResource(R.id.iv_age,R.mipmap.icon_home_sex_man);
            holder.setTextColor(R.id.tv_age,R.color.textColor_dynamic3);
            holder.setBackgroundRes(R.id.all_age,R.drawable.normal_bg_bule_address);
            holder.setText(R.id.tv_age,itemData.getAge()+"岁");
        }
        if(itemData.getConstellation()==null||itemData.getConstellation().equals("")){
            holder.setVisible(R.id.tv_constellation,false);
        }else {
            holder.setVisible(R.id.tv_constellation,true);
            holder.setText(R.id.tv_constellation,itemData.getConstellation()+"");
        }
        if(itemData.getHeight()==null||itemData.getHeight().equals("")){
            holder.setVisible(R.id.tv_height,false);
        }else {
            holder.setVisible(R.id.tv_height,true);
            holder.setText(R.id.tv_height,Math.round((Double) itemData.getHeight())+"cm");
        }
        if(itemData.getShape()==null||itemData.getShape().equals("")){
            holder.setVisible(R.id.tv_shape,false);
        }else {
            holder.setVisible(R.id.tv_shape,true);
            holder.setText(R.id.tv_shape,itemData.getShape()+"");
        }

        if(itemData.getIntroduce()!=null&&itemData.getIntroduce().length()>0){
            holder.setText(R.id.tv_signature,itemData.getIntroduce());

        }
        RecyclerView recyclerView = holder.getView(R.id.recyclerView);
        String homepageImages = itemData.getHomepageImages();
        if(homepageImages!=null&&homepageImages.length()>0){
            holder.setVisible(R.id.tv_signature,false);
            recyclerView.setVisibility(View.VISIBLE);
            DynamicHomeImgAdapter dynamicHomeImgAdapter = new DynamicHomeImgAdapter(itemData.getVideo(),itemData.getVideoCover(),homepageImages,mContext,isSelf);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);//禁止滑动
            recyclerView.setAdapter(dynamicHomeImgAdapter);
        }else {
            holder.setVisible(R.id.tv_signature,true);
            recyclerView.setVisibility(View.GONE);
            if(isSelf){
                holder.setVisible(R.id.tv_signature,false);
                holder.setVisible(R.id.all_data,true);
                holder.setOnClickListener(R.id.all_data, new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        HomeImageSelectActivity.startAction();
                    }
                });
            }
        }

        holder.itemView.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                UserHomeActivity.startAction(itemData.getId());
            }
        });
    }


    private static class CouponMultiItemType implements MultiItemTypeSupport<UserBean.DataBean> {


        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case 1:
                    return R.layout.item_main_sjk_act_left_new;
                case 2:
                    return R.layout.item_rich_info;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position,UserBean.DataBean bean) {
            return bean.getId().equals(MyAPP.getUserId()) ? 2 : 1;
        }
    }
}
