package com.ziran.meiliao.im.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.AutoLinearLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.WechatListDataBean;
import com.ziran.meiliao.widget.pupop.DeletePopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGANinePhotoLayout;


/**
 * 内部的RecyclerView
 * 内容为：
 * imageView + textView
 * Created by gaoshiwei on 2017/9/19.
 */

public class RecordChildInfoAdapter extends RecyclerView.Adapter<RecordChildInfoAdapter.ViewHolder> implements BGANinePhotoLayout.Delegate {

    private final String mIsSelf;
    private final WechatParentAdapter wechatParentAdapter;
    private final int mPosition;
    private final RxManager mRxManager;
    private Context context;
    private List<WechatListDataBean.DataBean.RecordsBean> list; // List 集合（里面是image+text）
    protected ItemClickListener mOnItemClickListener;
    private BGANinePhotoLayout mCurrentClickNpl;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public void onClickNinePhotoItem(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {
        mCurrentClickNpl = ninePhotoLayout;
        photoPreviewWrapper();
    }

    private void photoPreviewWrapper() {
        if (mCurrentClickNpl == null) {
            return;
        }
        BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(context)
                .saveImgDir(null); // 保存图片的目录，如果传 null，则没有保存图片功能

        if (mCurrentClickNpl.getItemCount() == 1) {
            // 预览单张图片
            photoPreviewIntentBuilder.previewPhoto(mCurrentClickNpl.getCurrentClickItem());
        } else if (mCurrentClickNpl.getItemCount() > 1) {
            // 预览多张图片
            photoPreviewIntentBuilder.previewPhotos(mCurrentClickNpl.getData())
                    .currentPosition(mCurrentClickNpl.getCurrentClickItemPosition()); // 当前预览图片的索引
        }
        context.startActivity(photoPreviewIntentBuilder.build());

    }

    public interface ItemClickListener {
        void itemClick(int position, String id, String typeId);
    }

    /**
     * 构造函数
     * @param context
     * @param list
     * @param wechatParentAdapter
     * @param position
     */
    public RecordChildInfoAdapter(Context context, RxManager rxManager, List<WechatListDataBean.DataBean.RecordsBean> list, String isSelf, WechatParentAdapter wechatParentAdapter, int position) {
        this.context = context;
        this.list = list;
        this.mPosition=position;
        this.wechatParentAdapter=wechatParentAdapter;
        this.mIsSelf=isSelf;
        mRxManager=rxManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wechat_record_day_info, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        WechatListDataBean.DataBean.RecordsBean info = list.get(position);
        if (info.getStatus() == 0) {
            holder.allPrivate.setVisibility(View.VISIBLE);
        } else {
            holder.allPrivate.setVisibility(View.GONE);
        }
        if (info.getImages() != null && info.getImages().length() > 0) {
            String[] imageStrings = info.getImages().split(",");
            ArrayList<String> photos = new ArrayList<>();
            Collections.addAll(photos, imageStrings);
            holder.layoutNine.setData(photos);
        } else {
            holder.layoutNine.setVisibility(View.GONE);
        }
        holder.layoutNine.setDelegate(RecordChildInfoAdapter.this);
        if(info.getContent().equals("")){
            holder.tvContent.setVisibility(View.GONE);
        }else {
            holder.tvContent.setText(info.getContent());
        }
        if(info.getAddress()!=null&&info.getAddress().length()>0){
            holder.tvAddress.setText(info.getAddress());
        }else {
            holder.all.setVisibility(View.GONE);
        }
        if(!mIsSelf.equals("1")){
            holder.ivMore.setVisibility(View.GONE);
        }else {
            holder.ivMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeletePopupWindow pop = new DeletePopupWindow(context, info.getId(), RecordChildInfoAdapter.this, position);
                    PopupWindowUtil.show((Activity) context, pop);

                }
            });
        }
    }

    //  删除数据
    public void removeData(int position) {
        list.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
        if(list.size()==0){
            wechatParentAdapter.removeData(mPosition);
        }
        mRxManager.post(AppConstant.RXTag.UPDATE_USER, "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * static ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;
        BGANinePhotoLayout layoutNine;
        TextView tvAddress;
        AutoLinearLayout allPrivate,all;
        ImageView ivMore;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            layoutNine = (BGANinePhotoLayout) itemView.findViewById(R.id.npl_item_moment_photos);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            ivMore = (ImageView) itemView.findViewById(R.id.iv_more);
            allPrivate = (AutoLinearLayout) itemView.findViewById(R.id.all_private);
            all = (AutoLinearLayout) itemView.findViewById(R.id.all);


        }
    }


}
