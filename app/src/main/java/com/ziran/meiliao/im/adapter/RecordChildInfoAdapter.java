package com.ziran.meiliao.im.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aliyun.player.AliPlayer;
import com.aliyun.player.AliPlayerFactory;
import com.aliyun.player.IPlayer;
import com.aliyun.player.bean.ErrorInfo;
import com.aliyun.player.bean.InfoBean;
import com.aliyun.player.nativeclass.TrackInfo;
import com.aliyun.player.source.VidAuth;
import com.aliyun.player.source.VidSts;
import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.AliAuthBean;
import com.ziran.meiliao.ui.bean.AliUploadFileBean;
import com.ziran.meiliao.ui.bean.AliVideoInfoBean;
import com.ziran.meiliao.ui.bean.SpaceRecommendBean;
import com.ziran.meiliao.ui.bean.WechatListDataBean;
import com.ziran.meiliao.ui.main.activity.DynamicDeatilsActivity;
import com.ziran.meiliao.ui.main.fragment.DynamicAllAudioOneFragment;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.pupop.DeletePopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
    private AliPlayer aliyunVodPlayer;
    private VidAuth vidAuth;


    public void setItemClickListener(ItemClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public void onClickNinePhotoItem(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {
        mCurrentClickNpl = ninePhotoLayout;
        photoPreviewWrapper();
    }

    @Override
    public void onClickExpand(BGANinePhotoLayout ninePhotoLayout, View view, int position, String model, List<String> models) {

    }


    private void photoPreviewWrapper() {
        if (mCurrentClickNpl == null) {
            return;
        }
        BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(context)
                .saveImgDir(null); // 保存图片的目录，如果传 null，则没有保存图片功能

            photoPreviewIntentBuilder.previewPhotos(mCurrentClickNpl.getData())
                    .currentPosition(mCurrentClickNpl.getCurrentClickItemPosition()); // 当前预览图片的索引
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        WechatListDataBean.DataBean.RecordsBean info = list.get(position);
        if (info.getStatus() == 0) {
            holder.allPrivate.setVisibility(View.VISIBLE);
        } else {
            holder.allPrivate.setVisibility(View.GONE);
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(info.getCreateTime());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            holder.tvTime.setText(String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)));
        } catch (ParseException e) {
            e.printStackTrace();
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
        if(info.getContent()!=null&&info.getContent().equals("")){
            holder.tvContent.setVisibility(View.GONE);
        }else {
            holder.tvContent.setText(info.getContent());
        }
        if(info.getAddress()!=null&&info.getAddress().length()>0){
            holder.tvAddress.setText(info.getAddress());
        }else {
            holder.all.setVisibility(View.GONE);
        }
        if(holder.all.getVisibility()==View.GONE&&holder.allPrivate.getVisibility()==View.GONE){
            holder.arlInfo.setVisibility(View.GONE);
        }

        if(info.getEnclosureType()==1){
            holder.layoutNine.setVisibility(View.GONE);
            Glide.with(context).load(info.getImages()).override(360,457).centerCrop().into(holder.ivVideo);
            holder.arlVideo.setVisibility(View.VISIBLE);
        }else if(info.getEnclosureType()==2){
            if(info.getDuration()!=null){
                holder.tvSoundTime.setText(info.getDuration());
            }
            holder.arlSound.setVisibility(View.VISIBLE);
            holder.layoutNine.setVisibility(View.GONE);
        }
        if(!mIsSelf.equals("1")){
            holder.ivMore.setVisibility(View.GONE);
        }else {
            holder.ivMore.setOnClickListener(v -> {
                DeletePopupWindow pop = new DeletePopupWindow(context, info.getId(), RecordChildInfoAdapter.this, position);
                PopupWindowUtil.show((Activity) context, pop);
            });
        }
        holder.itemView.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                DynamicDeatilsActivity.startAction(context,info.getId());
            }
        });
    }









    //  删除数据
    public void removeData(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        if (position != list.size()) {
            notifyItemChanged(position, list.size() - position);
        }
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
        TextView tvAddress,tvTime,tvSoundTime;
        AutoLinearLayout allPrivate,all;
        ImageView ivMore,ivVideo;
        AutoRelativeLayout arlInfo,arlSound,arlVideo;
        SurfaceView surfaceVIew;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            layoutNine = (BGANinePhotoLayout) itemView.findViewById(R.id.npl_item_moment_photos);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            ivMore = (ImageView) itemView.findViewById(R.id.iv_more);
            allPrivate = (AutoLinearLayout) itemView.findViewById(R.id.all_private);
            all = (AutoLinearLayout) itemView.findViewById(R.id.all);
            arlInfo = (AutoRelativeLayout) itemView.findViewById(R.id.arl_info);
            arlSound = (AutoRelativeLayout) itemView.findViewById(R.id.arl_sound);
            arlVideo = (AutoRelativeLayout) itemView.findViewById(R.id.arl_video);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            surfaceVIew = (SurfaceView) itemView.findViewById(R.id.surfaceVIew);
            ivVideo = (ImageView) itemView.findViewById(R.id.iv_video);
            tvSoundTime = (TextView) itemView.findViewById(R.id.tv_sound_time);
        }
    }


}
