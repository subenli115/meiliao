package com.ziran.meiliao.ui.main.adapter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.DynamicBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.priavteclasses.activity.UserHomeActivity;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

/**
 * Created by Administrator on 2017/2/8.
 */

public class DynamicListAdapter extends MultiItemRecycleViewAdapter<DynamicBean.DataBean.RecordsBean> {

    public DynamicListAdapter(Context context) {
        super(context, new CouponMultiItemType());
    }

    @Override
    public void convert(ViewHolderHelper holder, DynamicBean.DataBean.RecordsBean itemData, int position) {
        Glide.with(mContext).load(itemData.getAvatar()).into(holder.getImageView(R.id.iv_item_sjk_act_img));
        holder.setText(R.id.tv_name,itemData.getNickname());
        if(itemData.getIntroduce()!=null){
            holder.setText(R.id.tv_signature,itemData.getIntroduce().toString());
        }else {
            holder.setVisible(R.id.tv_signature,false);
        }
        holder.setText(R.id.tv_name,itemData.getNickname());
        if(itemData.getIsReal()==null||itemData.getIsReal().equals("0")){
            holder.setVisible(R.id.tv_real,false);
            holder.setVisible(R.id.iv_real_name,false);
            holder.setVisible(R.id.iv_real_person,false);
        }
        if(itemData.getSex() == 2){
            holder.setImageResource(R.id.iv_age,R.mipmap.icon_home_sex_woman);
            holder.setText(R.id.tv_age,itemData.getAge()+"岁");
            holder.setTextColor(R.id.tv_age,R.color.textColor_dynamic4);
            holder.setBackgroundRes(R.id.all_age,R.drawable.normal_bg_red_age);
        }
        if(itemData.getConstellation()==null||itemData.getConstellation().equals("")){
            holder.setVisible(R.id.tv_constellation,false);
        }else {
            holder.setText(R.id.tv_constellation,itemData.getConstellation()+"");
        }
        if(itemData.getHeight()==null||itemData.getHeight().equals("")){
            holder.setVisible(R.id.tv_height,false);
        }else {
            holder.setText(R.id.tv_height,Math.round((Double) itemData.getHeight())+"cm");
        }
        if(itemData.getShape()==null||itemData.getShape().equals("")){
            holder.setVisible(R.id.tv_shape,false);
        }else {
            holder.setText(R.id.tv_shape,itemData.getShape()+"");
        }
        holder.setOnClickListener(R.id.tv_other1, view -> {
            follow(holder,itemData.getId());
        });

        RecyclerView recyclerView = holder.getView(R.id.recyclerView);
        String homepageImages = itemData.getHomepageImages();
        if(homepageImages!=null&&homepageImages.length()>0){
            holder.setVisible(R.id.tv_signature,false);
            recyclerView.setVisibility(View.VISIBLE);
            DynamicHomeImgAdapter dynamicHomeImgAdapter = new DynamicHomeImgAdapter(itemData.getVideo(),itemData.getVideoCover(),homepageImages,mContext, false);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);//禁止滑动
            recyclerView.setAdapter(dynamicHomeImgAdapter);
        }
        holder.itemView.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                UserHomeActivity.startAction(itemData.getId());
            }
        });
    }

    private void follow(ViewHolderHelper holder, String id) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("followUserId", id);
        defMap.put("userId", MyAPP.getUserId());
        OkHttpClientManager.postAsyncAddHead(ApiKey.ADMIN_USERFOLLOW_ADD, defMap, "", new
                NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {

                    @Override
                    public void onSuccess(StringDataV2Bean listBean) {
                        holder.setBackgroundRes(R.id.tv_other1,R.drawable.normal_bg_gray_tran);
                        holder.setText(R.id.tv_other1,"已关注");
                        holder.setTextColor(R.id.tv_other1,R.color.textColor_dynamic2);
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }


    private static class CouponMultiItemType implements MultiItemTypeSupport<DynamicBean.DataBean.RecordsBean> {

        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case 1:
                    return R.layout.item_main_sjk_act_left_new;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, DynamicBean.DataBean.RecordsBean bean) {
            return 1;
        }
    }
}
