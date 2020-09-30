package com.ziran.meiliao.im.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.im.activity.CommonGiftActivity;
import com.ziran.meiliao.im.activity.ReleaseWechatActivity;
import com.ziran.meiliao.im.activity.WechatActivity;
import com.ziran.meiliao.ui.bean.GiftsReceivedBean;
import com.ziran.meiliao.ui.bean.SelectBean;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.me.activity.EditUserInfoActivity;
import com.ziran.meiliao.ui.me.activity.HobbySelectActivity;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.MyGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Administrator on 2016/7/11.
 */
public class UserSpaceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int REQUEST_CODE_A = 2;
    private List<GiftsReceivedBean.DataBean.RecordsBean> mGiftBean;
    private List<String> spaceList;
    private UserBean.DataBean mUserBean;
    private final boolean mIsSelf;
    private Context mContext;
    /**
     * 空数据时，显示空布局类型
     */
    private LayoutInflater mInflater;
    private MeGiftAdapter adapter;
    private List<String> mInfoLists;
    private List<SelectBean> mSelectBeans;
    private List<String> strings;
    private int count=4;

    public UserSpaceAdapter(Context context, List<String> space, UserBean.DataBean userBean, List<GiftsReceivedBean.DataBean.RecordsBean> giftBean, List<String> infoLists,boolean isSelf, List<SelectBean> selectBeans) {
        spaceList = space;
        mContext = context;
        mUserBean = userBean;
        mInfoLists = infoLists;
        mGiftBean = giftBean;
        mInflater = LayoutInflater.from(context);
        mIsSelf = isSelf;
        mSelectBeans = selectBeans;
    }


    public void update(List<String> space, UserBean.DataBean dataBean, List<GiftsReceivedBean.DataBean.RecordsBean> giftBean, List<String> infoLists, List<SelectBean> selectBeans) {
        spaceList = space;
        mUserBean = dataBean;
        mInfoLists = infoLists;
            mGiftBean = giftBean;
            mSelectBeans = selectBeans;
                notifyDataSetChanged();
    }

    public enum ItemType {
        ITEM_DYNAMIC, ITEM_GIFT, ITEM_HOBBY, ITEM_INFO
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
        if (viewType == ItemType.ITEM_DYNAMIC.ordinal()) {   //创建第一种holder
            /**
             * 动态
             */
            return new RecyclerViewHolder1(mInflater.inflate(R.layout.recycler_view_item_dynamic, parent, false));
        } else if (viewType == ItemType.ITEM_INFO.ordinal()) {
            /**
             * 基本信息
             */
            return new RecyclerViewHolder3(mInflater.inflate(R.layout.recycler_view_item_info, parent, false));
        } else if (viewType == ItemType.ITEM_GIFT.ordinal()) {
            /**
             * 礼物
             */
            return new RecyclerViewHolder2(mInflater.inflate(R.layout.recycler_view_item_gift, parent, false));
        } else if (viewType == ItemType.ITEM_HOBBY.ordinal()) {
            /**
             * 兴趣爱好
             */
            return new RecyclerViewHolder4(mInflater.inflate(R.layout.recycler_view_item_hobby, parent, false));
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
            /**
             * 动态
             */
            if (!mIsSelf) {
                ((RecyclerViewHolder1) holder).tvDynamic.setVisibility(View.GONE);
            }
            ((RecyclerViewHolder1) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //动态列表
                    if (Utils.isFastDoubleClick()) {
                        return;
                    }else{
                        //弹出Toast或者Dialog
                        if(mUserBean!=null){
                            WechatActivity.startAction(mContext,mUserBean.getId(), mIsSelf);
                        }
                    }
                }
            });
            if (spaceList!=null&&spaceList.size() == 0 && mIsSelf) {
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
            } else if(spaceList!=null&&spaceList.size() == 0&&!mIsSelf){
                ((RecyclerViewHolder1) holder).tvEmpty.setVisibility(View.VISIBLE);
                ((RecyclerViewHolder1) holder).recyclerview.setVisibility(View.GONE);
            }else {
                ((RecyclerViewHolder1) holder).tvEmpty.setVisibility(View.GONE);
                ((RecyclerViewHolder1) holder).ivSelect.setVisibility(View.GONE);
                ((RecyclerViewHolder1) holder).recyclerview.setVisibility(View.VISIBLE);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
                layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
                ((RecyclerViewHolder1) holder).recyclerview.setLayoutManager(layoutManager);
                ((RecyclerViewHolder1) holder).recyclerview.setHasFixedSize(true);
                ((RecyclerViewHolder1) holder).recyclerview.setNestedScrollingEnabled(false);//禁止滑动
                ((RecyclerViewHolder1) holder).recyclerview.setAdapter(speaceAdapter);
            }

        } else if (holder instanceof RecyclerViewHolder2) {
            if(!mIsSelf){
                ((RecyclerViewHolder2) holder).ivMore.setVisibility(View.GONE);
            }else {
                ((RecyclerViewHolder2) holder).itemView.setOnClickListener(new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        CommonGiftActivity.startAction(mContext, "");
                    }

                });
            }
            if(mGiftBean==null||mGiftBean.size()==0){
                ((RecyclerViewHolder2) holder).itemView.setVisibility(View.GONE);
            }else {
                ((RecyclerViewHolder2) holder).itemView.setVisibility(View.VISIBLE);
            }
            adapter = new MeGiftAdapter(mContext, mGiftBean);
            ((RecyclerViewHolder2) holder).gridView.setAdapter(adapter);
            adapter.update(mContext,mGiftBean);
        }else if(holder instanceof RecyclerViewHolder3){
            if(!mIsSelf){
                ((RecyclerViewHolder3) holder).ivMore.setVisibility(View.GONE);
            }else {
                ((RecyclerViewHolder3) holder).itemView.setOnClickListener(new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        EditUserInfoActivity.startAction();
                    }

                });
            }
            if(mInfoLists==null){
                ((RecyclerViewHolder3) holder).itemView.setVisibility(View.GONE);
            }else {
                ((RecyclerViewHolder3) holder).itemView.setVisibility(View.VISIBLE);
                TagAdapter   mAdapter=  new TagAdapter<String>(mInfoLists) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s)
                    {
                        AutoLinearLayout tv = (AutoLinearLayout) mInflater.inflate(R.layout.item_tag_bg_dt2,
                                ((RecyclerViewHolder3) holder).flowlayoutInfo, false);
                        ((TextView)tv.getChildAt(0)).setText(s);
                        return tv;
                    }
                };
                ((RecyclerViewHolder3) holder).flowlayoutInfo.setAdapter(mAdapter);
            }
        }else if(holder instanceof RecyclerViewHolder4){
            if(!mIsSelf){
                ((RecyclerViewHolder4) holder).ivMore.setVisibility(View.GONE);
            }else {
                ((RecyclerViewHolder4) holder).itemView.setOnClickListener(new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        HobbySelectActivity.startAction();
                    }

                });
            }
            if(mSelectBeans==null||mSelectBeans.size()==0){
                ((RecyclerViewHolder4) holder).itemView.setVisibility(View.GONE);
            }else {
                    ((RecyclerViewHolder4) holder).ivAdd.setVisibility(View.GONE);
                    ((RecyclerViewHolder4) holder).itemView.setVisibility(View.VISIBLE);
                    ((RecyclerViewHolder4) holder).recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
                    ((RecyclerViewHolder4) holder).recyclerview.setHasFixedSize(true);
                    if(mGiftBean==null||mGiftBean.size()==0){
                        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams)((RecyclerViewHolder4) holder).recyclerview.getLayoutParams();
                        p.setMargins(0,0, 0,119);
                        ((RecyclerViewHolder4) holder).recyclerview.setLayoutParams(p);
                    }


                    ((RecyclerViewHolder4) holder).recyclerview.setNestedScrollingEnabled(false);//禁止滑动
                    ((RecyclerViewHolder4) holder).recyclerview.setAdapter(hoobyAdapter);
            }
        }
    }

    RecyclerView.Adapter speaceAdapter= new RecyclerView.Adapter() {
        private static final int NORMAL_VIEW = 0;
        private static final int FOOT_VIEW = 1;
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == NORMAL_VIEW) {
                return new ViewHolder(mInflater.inflate(R.layout.item_simple, parent, false));
            }else {
                return  new ViewHolder(mInflater.inflate(R.layout.item_load_empty, parent, false));
            }
        }
        //重写一下getItemViewType,返回两个我们自己定义的参数，美滋滋~
        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1) {
                return FOOT_VIEW;
            }
            return NORMAL_VIEW;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder vh = (ViewHolder) holder;
            if (getItemViewType(position) == NORMAL_VIEW) {
                Glide.with(mContext).load(spaceList.get(position)).into(vh.iv);
            }
            vh.itemView.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    if(mUserBean!=null){
                        WechatActivity.startAction(mContext,mUserBean.getId(), mIsSelf);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            if(spaceList==null){
                return 0;
            }else {
                if(spaceList.size()>7){
                    return 8;
                }else {
                    return spaceList.size()+1;
                }
            }
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView iv;

            public ViewHolder(View itemView) {
                super(itemView);
                iv = itemView.findViewById(R.id.iv);
            }

        }
    };

    RecyclerView.Adapter hoobyAdapter= new RecyclerView.Adapter() {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(mInflater.inflate(R.layout.item_hobby_home, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder vh = (ViewHolder) holder;
            Glide.with(mContext).load(mSelectBeans.get(position).getImg()).into(vh.ivType);
            vh.tvTitle.setText(mSelectBeans.get(position).getName());
            SelectBean selectBean = mSelectBeans.get(position);
            String[] split = selectBean.getText().split(",");
            if(split!=null){
                 strings = Arrays.asList(split);
                 if(strings.get(0).equals("")){
                     vh.itemView.setVisibility(View.GONE);
                 }
            }else {
                if(selectBean.getText().length()>0){
                    strings=new ArrayList<>();
                    strings.add(selectBean.getText());
                }else {
                    vh.itemView.setVisibility(View.GONE);
                }
            }
            if(vh.flowlayout.getAdapter()!=null){
                vh.flowlayout.getAdapter().notifyDataChanged();
            }else {
                TagAdapter   mAdapter=  new TagAdapter<String>(strings) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s)
                    {
                        final LayoutInflater mInflater = LayoutInflater.from(mContext);
                        AutoLinearLayout tv = (AutoLinearLayout) mInflater.inflate(R.layout.item_tag_bg_dt2,
                                vh.flowlayout, false);
                        ((TextView)tv.getChildAt(0)).setText(s);
                        return tv;
                    }
                };
                vh.flowlayout.setAdapter(mAdapter);
            }
        }

        @Override
        public int getItemCount() {
            return mSelectBeans.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView ivType;
            TagFlowLayout flowlayout;
            TextView tvTitle;
            public ViewHolder(View itemView) {
                super(itemView);
                ivType = itemView.findViewById(R.id.iv_type);
                flowlayout = itemView.findViewById(R.id.flowlayout);
                tvTitle = itemView.findViewById(R.id.tv_title);
            }

        }
    };


    @Override
    public int getItemCount() {
        if(mSelectBeans==null||mSelectBeans.size()==0||mGiftBean==null||mGiftBean.size()==0){
            return 3;
        }else {
            return 4;
        }
    }

    /**
     * 根据当前的位置索引加载不同的item布局
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int num = position ;
        if (num == 0) {
            return ItemType.ITEM_DYNAMIC.ordinal();
        } else if(num==1){
            return ItemType.ITEM_INFO.ordinal();
        }else if(num==2){
            return ItemType.ITEM_HOBBY.ordinal();
        }else {
            return ItemType.ITEM_GIFT.ordinal();
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
            recyclerview =  itemView.findViewById(R.id.recyclerView);
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
        ImageView ivMore;
        /**
         * @param itemView 表示根布局视图
         */
        public RecyclerViewHolder2(View itemView) {
            super(itemView);
            gridView = (MyGridView) itemView.findViewById(R.id.gridView);
            ivMore = (ImageView) itemView.findViewById(R.id.iv_more);
        }
    }

    /**
     * 第3种item对应的holder
     */
    public static class RecyclerViewHolder3 extends RecyclerView.ViewHolder {

        TagFlowLayout flowlayoutInfo;
        ImageView ivMore;
        /**
         * @param itemView 表示根布局视图
         */
        public RecyclerViewHolder3(View itemView) {
            super(itemView);
            flowlayoutInfo = (TagFlowLayout) itemView.findViewById(R.id.flowlayout);
            ivMore = (ImageView) itemView.findViewById(R.id.iv_more);
        }
    }
    /**
     * 第4种item对应的holder
     */
    public static class RecyclerViewHolder4 extends RecyclerView.ViewHolder {

        ImageView ivMore,ivAdd;
        RecyclerView recyclerview;
        /**
         * @param itemView 表示根布局视图
         */
        public RecyclerViewHolder4(View itemView) {
            super(itemView);
            ivAdd = (ImageView) itemView.findViewById(R.id.iv_add);
            ivMore = (ImageView) itemView.findViewById(R.id.iv_more);
            recyclerview = (RecyclerView) itemView.findViewById(R.id.recyclerView);
        }
    }

}