package com.ziran.meiliao.ui.me.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.ui.workshops.widget.ProgressTipsView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/4 19:19
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/4$
 * @updateDes ${TODO}
 */

public class OrderTopViewUtil {
    private View mRootView;
    private int formType;
    private int color;


    private ProgressTipsView mProgressTipsView;
    private TextView tvLookDetail;

    private TextView tvState;
    private ImageView ivAvatar1;
    private View line1;
    private ImageView ivAvatar2;
    private View line2;
    private ImageView ivAvatar3;

    public OrderTopViewUtil(View activity, int formType) {
        mRootView = activity;
        this.formType = formType;
        color = activity.getResources().getColor(R.color.textColor_teshe);
        init();
    }

    public void init() {
        switch (formType) {
            case 0:
                tvLookDetail = ViewUtil.getView(mRootView, R.id.tv_look_detail);
            case 1:
                mProgressTipsView = ViewUtil.getView(mRootView, R.id.progressTipsView);
                break;
            case 2:
                ivAvatar1 = ViewUtil.getView(mRootView, R.id.iv_avatar1);
                ivAvatar2 = ViewUtil.getView(mRootView, R.id.iv_avatar2);
                ivAvatar3 = ViewUtil.getView(mRootView, R.id.iv_avatar3);
                line1 = ViewUtil.getView(mRootView, R.id.line_1);
                line2 = ViewUtil.getView(mRootView, R.id.line_2);
                tvState = ViewUtil.getView(mRootView, R.id.tv_state);
                break;
        }
    }

    public void setAvatars(String avatar1, String avatar2, String avatar3) {
        if (formType != 2) return;
        if (EmptyUtils.isNotEmpty(avatar1)) {
            ImageLoaderUtils.displayCircle(mRootView.getContext(), ivAvatar1, avatar1, R.mipmap.ic_member_pic);
            line1.setBackgroundColor(color);
        }
        if (EmptyUtils.isNotEmpty(avatar2)) {
            ImageLoaderUtils.displayCircle(mRootView.getContext(), ivAvatar2, avatar2, R.mipmap.ic_member_pic);
            line2.setBackgroundColor(color);
        }
        if (EmptyUtils.isNotEmpty(avatar3)) {
            ImageLoaderUtils.displayCircle(mRootView.getContext(), ivAvatar3, avatar3, R.mipmap.ic_member_pic);
            ViewUtil.setText(tvState, "拼团成功");
        } else {
            ViewUtil.setText(tvState, "邀请好友一起拼单...");

        }

    }

    public static void setCrowdFundingState(TextView tv, int state) {
        switch (state) {
            case 0:
                tv.setText("未处理");
                break;
            case 1:
                tv.setText("众筹中");
                break;
            case 2:
                tv.setText("众筹成功");
                break;
            case 3:
                tv.setText("众筹失败");
                break;
        }
    }

    public static void setState(TextView tv, int state) {
        setState(tv, state, true);
    }

    public static void setState(TextView tv, int state, boolean hasDrawTop) {
        switch (state) {
            case 0:
                tv.setText("未处理");
                if (hasDrawTop) tv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.myact_ic_dipose, 0, 0);
                break;
            case 1:
                tv.setText("进行中");
                if (hasDrawTop) tv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.myact_ic_time, 0, 0);
                break;
            case 2:
                tv.setText("已处理");
                if (hasDrawTop) tv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.myact_ic_success, 0, 0);
                break;
        }
    }

    public static void setState(TextView tv, int state, String statusMsg, boolean hasDrawTop) {
        switch (state) {
            case 0:
                tv.setText(statusMsg);
                if (hasDrawTop) tv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.myact_ic_dipose, 0, 0);
                break;
            case 1:
                tv.setText(statusMsg);
                if (hasDrawTop) tv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.myact_ic_time, 0, 0);
                break;
            case 2:
                tv.setText(statusMsg);
                if (hasDrawTop) tv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.myact_ic_success, 0, 0);
                break;
        }
    }
}
