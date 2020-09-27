package com.ziran.meiliao.ui.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.im.adapter.RecordChildInfoAdapter;
import com.ziran.meiliao.ui.bean.CommentListBean;
import com.ziran.meiliao.ui.bean.MediaAndTextBean;
import com.ziran.meiliao.ui.main.fragment.DynamicAllAudioOneFragment;
import com.ziran.meiliao.ui.priavteclasses.activity.UserHomeActivity;
import com.ziran.meiliao.widget.pupop.DeletePopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;

public class ZLAudioOneAdapter extends CommonRecycleViewAdapter<CommentListBean.DataBean.RecordsBean> {
    private final DynamicAllAudioOneFragment mFragment;
    private String leftString;

    public ZLAudioOneAdapter(Context context, DynamicAllAudioOneFragment fragment) {
        super(context, R.layout.item_zl_audio_one);
        mFragment=fragment;
    }

  public void  removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
        if (position != mDatas.size()) {
            notifyItemChanged(position, mDatas.size() - position);
        }
      mFragment.updateView(mDatas,position);
    }

    @Override
    public void convert(ViewHolderHelper helper, CommentListBean.DataBean.RecordsBean bean, int position) {
        if(bean!=null){
            if(bean.getCommentUserId().equals(MyAPP.getUserId())){
                helper.setVisible(R.id.all_comment, false);
                helper.setVisible(R.id.tv_report, false);
                helper.setVisible(R.id.tv_delete, true);
                helper.setOnClickListener(R.id.tv_delete, new OnNoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View v) {
                        DeletePopupWindow pop = new DeletePopupWindow(mContext,bean.getId(), ZLAudioOneAdapter.this, position);
                        PopupWindowUtil.show((Activity) mContext, pop);
                    }
                });
            }else {
                helper.setVisible(R.id.tv_report, true);
                helper.setVisible(R.id.tv_delete, false);
                helper.setVisible(R.id.all_comment, true);
            }
            SpannableStringBuilder ssb = new SpannableStringBuilder(bean.getContent());
            if(bean.getContent().substring(0,1).equals("@")){
                leftString=bean.getContent().split(" ")[0];
                ssb.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.bule_name)),0,leftString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            helper.setText(R.id.tv_item_title,bean.getNickname());
            helper.setText(R.id.tv_content,ssb);
            Glide.with(mContext).load(bean.getAvatar()).into((ImageView) helper.getView(R.id.iv_head));

            if(bean.getCreateTime()!=null&&bean.getCreateTime().length()>0){
                helper.setText(R.id.tv_time,TimeUtil.convertToShowStr(bean.getCreateTime()));
        }
            helper.setOnClickListener(R.id.all_comment, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFragment.setEkBar(bean.getNickname(),bean.getReceiveUserId());

                }
            });
            helper.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {

                    mFragment.ekbar(helper.itemView);
                    return false;
                }

            });
            helper.itemView.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    UserHomeActivity.startAction(bean.getCommentUserId());
                }
            });
        }
    }
}
