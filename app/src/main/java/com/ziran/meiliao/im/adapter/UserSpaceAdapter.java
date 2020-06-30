package com.ziran.meiliao.im.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.ziran.meiliao.R;
import com.ziran.meiliao.im.activity.ReleaseWechatActivity;
import com.ziran.meiliao.im.activity.WechatActivity;
import com.ziran.meiliao.im.model.AppBean;
import com.ziran.meiliao.ui.bean.GiftsReceivedBean;
import com.ziran.meiliao.ui.bean.MeSpaceBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.ziran.meiliao.widget.MyGridView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/7/11.
 */
public class UserSpaceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int REQUEST_CODE_A = 2;
    private  List<GiftsReceivedBean.DataBean.RecordsBean> mGiftBean;
    private List<String> spaceList;
    private UserBean.DataBean mUserBean;
    private final boolean mIsSelf;
    private Context mContext;
    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;
    private LayoutInflater mInflater;
    private MeGiftAdapter adapter;

    public UserSpaceAdapter(Context context, List<String> space, UserBean.DataBean userBean, List<GiftsReceivedBean.DataBean.RecordsBean> giftBean, boolean isSelf) {
        spaceList = space;
        mContext = context;
        mUserBean = userBean;
        mGiftBean=giftBean;
        mInflater = LayoutInflater.from(context);
        mIsSelf = isSelf;
    }


   public void  update(List<String> space, UserBean.DataBean dataBean, List<GiftsReceivedBean.DataBean.RecordsBean> giftBean){
       spaceList = space;
       mUserBean = dataBean;
       if(mGiftBean==null){
           mGiftBean=giftBean;
       }else {
           mGiftBean.clear();
           mGiftBean.addAll(giftBean);
       }
       notifyDataSetChanged();
   }

    public enum ItemType {
        ITEM1, ITEM2
    }


    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType item布局的类型索引
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemType.ITEM1.ordinal()) {   //创建第一种holder
            return new RecyclerViewHolder1(mInflater.inflate(R.layout.recycler_view_item_dynamic, parent, false));
        } else if (viewType == ItemType.ITEM2.ordinal()) {
            return new RecyclerViewHolder2(mInflater.inflate(R.layout.recycler_view_item_gift, parent, false));
        }
        return null;
    }

    /**
     * 绑定数据至holder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //根据holder的类型不同，显示不同的数据
        if (holder instanceof RecyclerViewHolder1) {
            if (!mIsSelf) {
                ((RecyclerViewHolder1) holder).tvDynamic.setVisibility(View.GONE);
            }
            if (spaceList.size() == 0 && mIsSelf) {
                ((RecyclerViewHolder1) holder).ivSelect.setVisibility(View.VISIBLE);
                ((RecyclerViewHolder1) holder).ivSelect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (Utils.isFastDoubleClick()) {
                            return;
                        }else{
                            //弹出Toast或者Dialog
                            ReleaseWechatActivity.startAction(REQUEST_CODE_A);
                        }
                    }
                });
                ((RecyclerViewHolder1) holder).recyclerview.setVisibility(View.GONE);
            } else if(spaceList.size() == 0&&!mIsSelf){
                ((RecyclerViewHolder1) holder).tvEmpty.setVisibility(View.VISIBLE);
                ((RecyclerViewHolder1) holder).recyclerview.setVisibility(View.GONE);
            }else {
                ((RecyclerViewHolder1) holder).tvEmpty.setVisibility(View.GONE);
                ((RecyclerViewHolder1) holder).recyclerview.setVisibility(View.VISIBLE);
            }
            Log.e("onBindViewHolder",""+spaceList.size());

            final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
            ((RecyclerViewHolder1) holder).recyclerview.setLayoutManager(layoutManager);

            ((RecyclerViewHolder1) holder).recyclerview.setHasFixedSize(true);
            ((RecyclerViewHolder1) holder).recyclerview.setNestedScrollingEnabled(false);//禁止滑动
            ((RecyclerViewHolder1) holder).recyclerview.setAdapter(speaceAdapter);
            ((RecyclerViewHolder1) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //动态列表
                    if (Utils.isFastDoubleClick()) {
                        return;
                    }else{
                        //弹出Toast或者Dialog
                        WechatActivity.startAction(mContext,mUserBean.getHomepageImages(), mUserBean.getAvatar(),mUserBean.getId(), mIsSelf);
                    }
                }
            });
            ((RecyclerViewHolder1) holder).tvDynamic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //发布动态
                    if (Utils.isFastDoubleClick()) {
                        return;
                    }else{
                        //弹出Toast或者Dialog
                        ReleaseWechatActivity.startAction(REQUEST_CODE_A);
                    }

                }
            });
        } else if (holder instanceof RecyclerViewHolder2) {
            if(mGiftBean==null||mGiftBean.size()==0){
                ((RecyclerViewHolder2) holder).itemView.setVisibility(View.GONE);
            }else {
                ((RecyclerViewHolder2) holder).itemView.setVisibility(View.VISIBLE);
            }
            adapter = new MeGiftAdapter(mContext, mGiftBean);
            adapter.setOnItemClickListener(new MeGiftAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ViewGroup container, Object item, int position) {
                    }
                });
            ((RecyclerViewHolder2) holder).gridView.setAdapter(adapter);
            adapter.update(mContext,mGiftBean);
        }
    }
    RecyclerView.Adapter speaceAdapter= new RecyclerView.Adapter() {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mInflater.inflate(R.layout.item_simple, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder vh = (ViewHolder) holder;
            Glide.with(mContext).load(spaceList.get(position)).into(vh.iv);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //动态列表
                    if (Utils.isFastDoubleClick()) {
                        return;
                    }else{
                        WechatActivity.startAction(mContext,mUserBean.getHomepageImages(), mUserBean.getAvatar(), mUserBean.getId(),mIsSelf);
                        //弹出Toast或者Dialog
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return spaceList.size();

        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv;

            public ViewHolder(View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.iv);
            }

        }
    };

    @Override
    public int getItemCount() {
        return 2;
    }

    /**
     * 根据当前的位置索引加载不同的item布局
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int num = position % 2;
        if (num == 0) {
            return ItemType.ITEM1.ordinal();
        } else {
            return ItemType.ITEM2.ordinal();
        }
    }

    /**
     * 第一种item对应的holder
     */
    public static class RecyclerViewHolder1 extends RecyclerView.ViewHolder {

        RecyclerView recyclerview;
        TextView tvDynamic, tvEmpty;
        ImageView ivMore, ivSelect;

        /**
         * @param itemView 表示根布局视图
         */
        public RecyclerViewHolder1(View itemView) {
            super(itemView);
            recyclerview = (RecyclerView) itemView.findViewById(R.id.recyclerView);
            ivMore = (ImageView) itemView.findViewById(R.id.iv_more);
            tvDynamic = (TextView) itemView.findViewById(R.id.tv_dynamic);
            tvEmpty = (TextView) itemView.findViewById(R.id.tv_empty);
            ivSelect = (ImageView) itemView.findViewById(R.id.iv_select);

        }
    }

    /**
     * 第2种item对应的holder
     */
    public static class RecyclerViewHolder2 extends RecyclerView.ViewHolder {

        MyGridView gridView;

        /**
         * @param itemView 表示根布局视图
         */
        public RecyclerViewHolder2(View itemView) {
            super(itemView);
            gridView = (MyGridView) itemView.findViewById(R.id.gridView);
        }
    }


}