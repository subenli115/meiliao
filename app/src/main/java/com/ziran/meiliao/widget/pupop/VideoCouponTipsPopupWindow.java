package com.ziran.meiliao.widget.pupop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.widget.ItemGroupView;

import java.util.Map;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class VideoCouponTipsPopupWindow extends BasePopupWindow {

    private final ItemGroupView tv_me_main_new_choose;
    private final RxManager rxManager;
    private String setSex;


    public VideoCouponTipsPopupWindow(Context context, ItemGroupView tv_me_main_new_choose, RxManager mRxManager) {
        super(context);
        mContext=context;
        rxManager=mRxManager;
        this.tv_me_main_new_choose=tv_me_main_new_choose;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_video_coupon_tips;
    }

    @Override
    protected void initViews(View contentView) {
        touchDismiss(R.id.touch_outside);
        setOnClickListener(R.id.tv_popuw_buy_video_coupon_tips_free);
        setOnClickListener(R.id.tv_popuw_buy_video_coupon_tips_buy);
        setOnClickListener(R.id.tv_popuw_buy_video_coupon_tips_cancel);
    }


    public void setSex(String sex){
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id", MyAPP.getUserId());
        if(sex.equals("(男)")){
            sex="1";
        }else {
            sex="2";
        }
        defMap.put("preference",sex);
        OkHttpClientManager.putAsyncAddHead(ApiKey.ADMIN_USER_UPDATE, defMap, new
                NewRequestCallBack<UserBean>(UserBean.class) {
                    @Override
                    public void onSuccess(UserBean result) {
                        ToastUitl.showShort("修改成功");
                        MyAPP.setmUserBean(result.getData());
                        rxManager.post("updateCard","");
                    }

                    @Override
                    public void onError(String msg, int code) {
                        ToastUitl.showShort(msg);
                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_popuw_buy_video_coupon_tips_buy:
                 setSex = MyAPP.getmUserBean().getSex() == 1 ? "(男)" : "(女)";
                tv_me_main_new_choose.setRigthText("同性"+setSex);
                setSex(setSex);
                break;
            case R.id.tv_popuw_buy_video_coupon_tips_free:
                 setSex = MyAPP.getmUserBean().getSex() == 1 ? "(女)" : "(男)";
                tv_me_main_new_choose.setRigthText("异性"+setSex);
                setSex(setSex);
                break;
        }
        dismiss();
    }
    private String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


}