package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.ui.bean.NewMediaAndTextBean;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;



public class ZLAudioTwoAdapter extends CommonRecycleViewAdapter<NewMediaAndTextBean.DataBean.DirBean> {

    private ViewGroup.MarginLayoutParams mMParams;
    private RecyclerView view;
    private AutoRelativeLayout arl_parent;
    private ImageView tv_select;
    private ImageView iv_pull;
    protected ItemClickListener mOnItemClickListener;
    private ZLChildInfoAdapter recordChildInfoAdapter;
    private LinearLayoutManager layoutManager1;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void itemClick1(int mPosition,View  view, NewMediaAndTextBean.DataBean.DirBean t,int position);
    }
    public ZLAudioTwoAdapter(Context context ) {
        super(context, R.layout.layout_treerecycler_item);
    }


    @Override
    public void onBindViewHolder(final ViewHolderHelper holder, final int position) {
        holder.updatePosition(position);
        //添加动画
        addAnimation(holder);
        arl_parent =(AutoRelativeLayout) holder.getView(R.id.arl_parent);
        if(mDatas.size()==1){
            arl_parent.setVisibility(View.GONE);
        }
        TextView tv = (TextView) holder.getView(R.id.parent_name);
        tv.setText(mDatas.get(position).getPreTitle());
        view = (RecyclerView) holder.getView(R.id.menu_info_recyclerview);
        arl_parent.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view1) {
                 AutoLinearLayout parent = (AutoLinearLayout) view1.getParent();
                 RecyclerView rv = (RecyclerView) parent.findViewById(R.id.menu_info_recyclerview);
                 tv_select=(ImageView)view1.findViewById(R.id.tv_select);
                 iv_pull= (ImageView)view1.findViewById(R.id.iv_pull);
                 if(rv.getVisibility()==View.VISIBLE){
                     rv.setVisibility(View.GONE);
                     tv_select.setBackgroundResource(R.mipmap.zlcd_noselect);
                     iv_pull.setBackgroundResource(R.mipmap.zl_bottom);
                 }else{
                     iv_pull.setBackgroundResource(R.mipmap.zl_top);
                     tv_select.setBackgroundResource(R.mipmap.zlcd_select);
                     rv.setVisibility(View.VISIBLE);
                 }
             }
         });



            layoutManager1=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
            view.setLayoutManager(layoutManager1);
             recordChildInfoAdapter = new ZLChildInfoAdapter(mContext,mDatas.get(position).getPrevList());
            recordChildInfoAdapter.setItemClickListener(new ZLChildInfoAdapter.ItemClickListener() {
                @Override
                public void itemClick(int mPosition, View  view) {
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.itemClick1(mPosition, view, mDatas.get(position), position);
                        notifyDataSetChanged();
                    }

                }
            });
            view.setAdapter(recordChildInfoAdapter);
    }
    public void reverse(boolean isOrder){
        layoutManager1.setStackFromEnd(isOrder);//列表再底部开始展示，反转后由上面开始展示
        layoutManager1.setReverseLayout(isOrder);//列表翻转
        view.setLayoutManager(layoutManager1);
        view.setAdapter(recordChildInfoAdapter);
        recordChildInfoAdapter.notifyDataSetChanged();
    }


    @Override
    public void convert(ViewHolderHelper helper, NewMediaAndTextBean.DataBean.DirBean dirBean, int position) {


    }


    @Override
    public ViewHolderHelper onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewHolderHelper viewHolder = ViewHolderHelper.get(mContext, null, parent, mLayoutId, -1);
        if (getWidthRatio() < 1f) {
            int width = (int) (DisplayUtil.getScreenWidth(mContext) * getWidthRatio());
            mMParams = new ViewGroup.MarginLayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (getMarginValue() > 0) {
                mMParams.rightMargin = DisplayUtil.dip2px(mContext.getResources(), getMarginValue());
            }
        }
        if (mMParams != null && viewHolder != null) {
            viewHolder.getConvertView().setLayoutParams(mMParams);
        }
        return viewHolder;
    }
}
