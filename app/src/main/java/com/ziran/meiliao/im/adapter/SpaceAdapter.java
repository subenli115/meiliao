package com.ziran.meiliao.im.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.main.adapter.DynamicHomeImgAdapter;
import com.ziran.meiliao.ui.me.activity.EditUserInfoActivity;

/**
 * Created by Administrator on 2017/2/8.
 */

public class SpaceAdapter extends MultiItemRecycleViewAdapter<String> {


    public SpaceAdapter(Context context) {
        super(context, new CouponMultiItemType());
    }

    @Override
    public void convert(ViewHolderHelper holder, String s, int position) {

        Glide.with(mContext).load(s).into((ImageView) holder.getView(R.id.iv));
    }


    private static class CouponMultiItemType implements MultiItemTypeSupport<String> {


        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case 1:
                    return R.layout.item_simple;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position,String bean) {
            return 1;
        }
    }
}
