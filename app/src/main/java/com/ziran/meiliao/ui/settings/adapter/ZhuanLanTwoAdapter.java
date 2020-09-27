package com.ziran.meiliao.ui.settings.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.analytics.AnalyticsConfig;
import com.zhy.autolayout.AutoLinearLayout;
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
import com.ziran.meiliao.ui.bean.MyFollowBean;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.priavteclasses.activity.UserHomeActivity;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.GlideRoundTransform;

import java.util.Map;


/**
 * Created by Administrator on 2019/1/31.
 */

public class ZhuanLanTwoAdapter extends MultiItemRecycleViewAdapter<MyFollowBean.DataBean.RecordsBean> {


        public static final int TYPE_TOP = 11;
         private final Activity mActivity;
          private final String mPosition;
    private final View mBg;
    private View contentView1;
    private boolean isCheck;

     public ZhuanLanTwoAdapter(Context context, Activity activity, MultiItemTypeSupport<MyFollowBean.DataBean.RecordsBean> multiItemTypeSupport, String position, View bg) {
            super(context, multiItemTypeSupport);
            mContext=context;
            mActivity=activity;
            mPosition=position;
            mBg=bg;
     }

        @Override
        public void convert(final ViewHolderHelper helper, final MyFollowBean.DataBean.RecordsBean bean, final int position) {
            Glide.with(mContext).load(bean.getAvatar()).transform(new GlideRoundTransform(mContext)).into(helper.getImageView(R.id.iv_head));
            ((TextView)helper.getView(R.id.tv_sign)).setText(bean.getIntroduce());
            ((TextView)helper.getView(R.id.tv_name)).setText(bean.getNickname());
            if(mPosition.equals("0")){
                helper.setText(R.id.tv_right,"取消关注");
            }else if(mPosition.equals("1")){
                helper.setText(R.id.tv_right,"互相关注");
                helper.setBackgroundRes(R.id.tv_right,R.drawable.normal_bg_bule_tran);
                helper.setTextColor(R.id.tv_right,R.color.textColor_main_bule);
            }else {
                helper.setText(R.id.tv_right,"移除粉丝");
            }

            if(!mPosition.equals("1")){
                helper.getView(R.id.tv_right).setOnClickListener(view -> {
                    showPopAgainWindow(position,bean);


                });
            }

            helper.itemView.setOnClickListener(new OnNoDoubleClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    if(mPosition.equals("2")){
                        UserHomeActivity.startAction(bean.getUserId());
                    }else {
                        UserHomeActivity.startAction(bean.getFollowUserId());
                    }
                }
            });

        }

    private void put(MyFollowBean.DataBean.RecordsBean bean,int position) {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        if(mPosition.equals("0")){
            defMap.put("userId", MyAPP.getUserId());
            defMap.put("followUserId",bean.getFollowUserId());
            defMap.put("isBlacklist", "0");
        }else if(mPosition.equals("2")){
            defMap.put("userId",bean.getUserId());
            defMap.put("followUserId",MyAPP.getUserId());
            if(isCheck){
                defMap.put("isBlacklist", "1");
            }else {
                defMap.put("isBlacklist", "0");
            }
        }
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USERFOLLOW_DEL, defMap, new NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
            @Override
            protected void onSuccess(StringDataV2Bean result) {
                mDatas.remove(position);
                notifyDataSetChanged();
                ToastUitl.show("操作成功",0);
            }

            @Override
            public void onError(String msg, int code) {
                super.onError(msg, code);
                ToastUitl.showShort(msg);
            }
        });
    }

    private void showPopAgainWindow(int position, MyFollowBean.DataBean.RecordsBean bean) {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView1 = LayoutInflater.from(mContext).inflate(R.layout.pop_again_apply, null);
        contentView1.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView1,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.MATCH_PARENT, true);

        popupWindow.setTouchable(true);
        //设置成不被键盘挡住
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(mBg, Gravity.CENTER, 0, 0);
        TextView tvqd = contentView1.findViewById(R.id.tv_qd1);
        TextView tvqx = contentView1.findViewById(R.id.tv_qx1);
        TextView tvTitle = contentView1.findViewById(R.id.tv_title);
        TextView etReason = contentView1.findViewById(R.id.et_reason);
        CheckBox checkBox = contentView1.findViewById(R.id.checkBox);
        LinearLayout linearLayout = contentView1.findViewById(R.id.ll_check);
        if(mPosition.equals("0")){
            tvTitle.setText("取消关注");
            etReason.setText("确定取消对"+bean.getNickname()+"的关注吗");
        }else if(mPosition.equals("2")){
            tvTitle.setText("移除粉丝");
            etReason.setText("确定要移除"+bean.getNickname()+"吗");
            linearLayout.setVisibility(View.VISIBLE);
            checkBox.setOnCheckedChangeListener((compoundButton, b) -> isCheck=b);
        }
        tvqd.setOnClickListener(view -> {
            put(bean,position);
            popupWindow.dismiss();
        });
        tvqx.setOnClickListener(view -> popupWindow.dismiss());
    }
        public static class ActivityMultiItemType implements MultiItemTypeSupport<MyFollowBean.DataBean.RecordsBean> {

            public ActivityMultiItemType() {
            }

            @Override
            public int getLayoutId(int itemType) {
                switch (itemType) {
                    case TYPE_TOP:
                        return R.layout.item_user_follow;
                }
                return -1;
            }

            @Override
            public int getItemViewType(int position, MyFollowBean.DataBean.RecordsBean newZhuanLanData) {
                return TYPE_TOP;
            }

        }
}
