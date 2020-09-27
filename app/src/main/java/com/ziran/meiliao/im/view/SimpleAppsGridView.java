package com.ziran.meiliao.im.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.ziran.meiliao.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonwidget.FilterTextView;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.activity.ChatActivity;
import com.ziran.meiliao.im.adapter.AppsAdapter;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.ui.bean.RechargeBean;
import com.ziran.meiliao.ui.bean.RecommendUserBean;
import com.ziran.meiliao.ui.bean.UserAccountBean;
import com.ziran.meiliao.ui.settings.activity.RechargeActivity;
import com.ziran.meiliao.utils.DownloadUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.Utils;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.CustomContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_GIFTRECORD_ADD;

public class SimpleAppsGridView extends RelativeLayout implements View.OnClickListener {

    protected View view;
    private AppsAdapter adapter;
    private TextView tvBalance;
    private String mGold="";
    private String mUserId;
    public ChatActivity mChatActivity;
    private Conversation mConv;
    private String mNickName="";
    private String spaceId;


    public SimpleAppsGridView(Context context) {
        this(context, null);
    }

    public SimpleAppsGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.view_apps, this);
        init();
        getCommodityList();

    }

    public void getCommodityList() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("status", "0");
        defMap.put("type", "1");
        defMap.put("size", "30");
        defMap.put("current","1");
        OkHttpClientManager.getAsyncMore(ApiKey.ACCOUNT_COMMODITY_PAGE, defMap, new
                NewRequestCallBack<RechargeBean>(RechargeBean.class) {
                    @Override
                    public void onSuccess(RechargeBean result) {
                        adapter.update(result.getData().getRecords());
                    }

                    @Override
                    public void onError(String msg, int code) {

                    }
                });
    }

    protected void init() {
        GridView gv_apps = (GridView) view.findViewById(R.id.gv_apps);
        ImageView ivRecharge = (ImageView) view.findViewById(R.id.iv_recharge);
        tvBalance = (TextView) view.findViewById(R.id.tv_popuw_give_gift_balance);
        FilterTextView tvGive = (FilterTextView) view.findViewById(R.id.tv_popuw_give_gift_give);
        List<RechargeBean.DataBean.RecordsBean> mAppBeanList = new ArrayList<>();
        adapter = new AppsAdapter(getContext(), mAppBeanList);
        adapter.setOnItemClickListener(new AppsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ViewGroup container, Object item, int position) {
                adapter.changeCheck(position);
            }
        });
        gv_apps.setAdapter(adapter);
        ivRecharge.setOnClickListener(this);
        tvGive.setOnClickListener(this);
    }


    public void setBalance(String gold, String userId, Activity chatActivity) {
        mGold = gold;
        tvBalance.setText(mGold);
        mUserId=userId;
        if(chatActivity!=null){
            mChatActivity=(ChatActivity) chatActivity;
        }
    }

    @Override
    public void onClick(View v) {
        if (Utils.isFastDoubleClick()) {
            return;
        }
        switch (v.getId()) {
            case R.id.iv_recharge:
                RechargeActivity.startAction(getContext(), "");
                break;
            case R.id.tv_popuw_give_gift_give:

                RechargeBean.DataBean.RecordsBean mGiftAdapterSelect = adapter.getSelect();
                if (mGiftAdapterSelect == null) {
                    ToastUitl.showShort("您还没有选中礼物");
                } else if (Integer.parseInt(mGold) < mGiftAdapterSelect.getPrice()) {
                    ToastUitl.showShort("您的ML币不够,请前往充值");

                } else {
                    if(mUserId.equals(MyAPP.getUserId())){
                        ToastUitl.showShort("自己不能给自己送礼");
                        return;
                    }else {
                        GiveGift(mGiftAdapterSelect);
                    }
                }
                //送礼物
                break;
        }
    }
    /**
     *
     * 送礼物
     * @param bean
     */
    private void GiveGift(RechargeBean.DataBean.RecordsBean bean)  {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("giveUserId", MyAPP.getUserId());
        defMap.put("receiveUserId", mUserId);
        defMap.put("giftId", bean.getId()+"");
        if(spaceId!=null&&spaceId.length()>0){
            defMap.put("objectId", spaceId);
            defMap.put("type", "2");
        }
        OkHttpClientManager.postAsyncAddHead(ADMIN_GIFTRECORD_ADD, defMap, "", new
                NewRequestCallBack<UserAccountBean>(UserAccountBean.class) {

                    @Override
                    public void onSuccess(UserAccountBean bean1)  {
                        int gold = MyAPP.saveMoney(bean1);
                        tvBalance.setText(gold+"");
                        RxManagerUtil.post(AppConstant.RXTag.UPDATE_OTHERUSER, "");
                        ToastUitl.showShort("赠送成功");
                        mConv = JMessageClient.getSingleConversation(mUserId, MeiliaoConfig.IM_APPKEY);
                        CustomContent   content = new CustomContent();
                        content.setStringValue("text","送你一个"+bean.getName());
                        content.setStringValue("img",bean.getImages());
                        if(bean.getAnimationImages()==null){
                            content.setStringValue("svga","");
                        }else {
                            content.setStringValue("svga",bean.getAnimationImages());
                        }
                        Message msg = mConv.createSendMessage(content);
                        if(mChatActivity!=null){
                            mChatActivity.handleSendMsg(msg);
                        }
                            }

                    @Override
                    public void onError(String msg, int code) {
                    }
                });
    }

    public void setNickName(String nickName) {
        mNickName=nickName;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId=spaceId;
    }
}
