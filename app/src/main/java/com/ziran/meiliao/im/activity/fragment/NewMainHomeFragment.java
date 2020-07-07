package com.ziran.meiliao.im.activity.fragment;


import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManager;
import com.ziran.meiliao.common.commonutils.ACache;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.im.activity.ChatActivity;
import com.ziran.meiliao.im.activity.OtherUserHomeActivity;
import com.ziran.meiliao.im.activity.RecommedPreviewActivity;
import com.ziran.meiliao.im.application.JGApplication;
import com.ziran.meiliao.ui.bean.StringDataV2Bean;
import com.ziran.meiliao.ui.bean.RecommendUserBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.Utils;
import com.ziran.meiliao.widget.CardAdapter;
import com.ziran.meiliao.widget.CardSlidePanel;
import com.ziran.meiliao.widget.GlideCircleTransform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.functions.Action1;


/**
 * 找朋友 on 2017/2/20.
 */

public class NewMainHomeFragment extends BaseFragment {
    private FragmentActivity mContext;
    private View mRootView;
    private AutoRelativeLayout arlBg;
    private View contentView;
    private CardSlidePanel slidePanel;
    private RecommendUserBean.DataBean currentBean;
    private int mIndex;
    private RxManager mRxManager;
    private int current;
    private ACache mCache;
    private Gson g;
    private TextView tvPreview;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
        g = new Gson();
        mCache = ACache.get(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        mRootView = layoutInflater.inflate(R.layout.fragment_main_home_im,
                (ViewGroup) getActivity().findViewById(R.id.main_view), false);
        mRxManager = new RxManager();
         tvPreview = (TextView)mRootView.findViewById(R.id.tv_preview);
        mRxManager.on("updateCard", new Action1<String>() {
            @Override
            public void call(String balance) {
                dataList.clear();
                current = 0;
                getRecommendUserData();
            }
        });
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup p = (ViewGroup) mRootView.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRxManager.clear();
    }

    private CardSlidePanel.CardSwitchListener cardSwitchListener;


    public List<RecommendUserBean.DataBean> dataList = new ArrayList<>();

    private void showPopWindow() {

        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_main_like, null);
        contentView.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(contentView,
                AutoLinearLayout.LayoutParams.WRAP_CONTENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(arlBg, Gravity.CENTER, 0, 0);
        TextView tvChat = contentView.findViewById(R.id.tv_chat);
        ImageView ivOther = contentView.findViewById(R.id.iv_other);
        ImageView ivSelf = contentView.findViewById(R.id.iv_self);
        Glide.with(mContext).load(MyAPP.getmUserBean().getAvatar()).transform(new GlideCircleTransform(mContext)).into(ivSelf);
        Glide.with(mContext).load(dataList.get(mIndex).getAvatar()).transform(new GlideCircleTransform(mContext)).into(ivOther);
        setBackgroundAlpha(0.5f);
        tvChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isFastDoubleClick()) {
                    return;
                } else {
                    //弹出Toast或者Dialog
                    gotoChatActivity(mIndex);
                    popupWindow.dismiss();
                }
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
    }


    private void gotoChatActivity(int index) {
        if (dataList != null) {
            Intent intent = new Intent();
            RecommendUserBean.DataBean dataBean = dataList.get(index);
            intent.putExtra(JGApplication.CONV_TITLE, dataBean.getNickname());
            intent.putExtra("targetId", dataBean.getId());
            intent.putExtra("targetAppKey", MeiliaoConfig.IM_APPKEY);
            intent.putExtra("draft", "");
            intent.setClass(mContext, ChatActivity.class);
            mContext.startActivity(intent);
        }
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = (getActivity()).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        arlBg.setAlpha(bgAlpha);
    }

    private void initView() {
       getRecommendUserData();
        tvPreview.setOnClickListener(view -> {
            RecommedPreviewActivity.startAction();
        });
        slidePanel = mRootView.findViewById(R.id.image_slide_panel);
        arlBg = mRootView.findViewById(R.id.arl_bg);
        // 1. 左右滑动监听
        cardSwitchListener = new CardSlidePanel.CardSwitchListener() {

            @Override
            public void onShow(int index) {
                mIndex = index;
            }

            @Override
            public void onCardVanish(int isShowing, int flyType) {
                userSign(flyType);
                Log.e("isShowingisShowing",isShowing+"        "+dataList.size());
                if (isShowing+8  == dataList.size()) {
                    getRecommendUserData();
                }
            }
        };
        slidePanel.setCardSwitchListener(cardSwitchListener);
        slidePanel.setAdapter(adapter);
    }

    private void userSign(int flyType) {
        //标记喜欢
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("userId", MyAPP.getUserId());
        defMap.put("signUserId", dataList.get(mIndex).getId());
        if (flyType == 1) {
            flyType = 1;
        } else {
            flyType = 2;
        }
        defMap.put("type", flyType + "");
        OkHttpClientManager.postAsyncAddHead(ApiKey.ADMIN_USERSIGN_ADD, defMap, "", new
                NewRequestCallBack<StringDataV2Bean>(StringDataV2Bean.class) {
                    @Override
                    public void onSuccess(StringDataV2Bean bean) {
                    }

                    @Override
                    public void onError(String msg, int code) {
                        if (code == 2000) {
                            showPopWindow();
                        }
                    }
                });

    }

    private void getRecommendUserData() {
        current++;
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id", MyAPP.getUserId());
        defMap.put("size", "10");
        defMap.put("current", current + "");
        OkHttpClientManager.getAsyncMore(ApiKey.ADMIN_USER_RECOMMENDUSERPGE, defMap, new
                NewRequestCallBack<RecommendUserBean>(RecommendUserBean.class) {
                    @Override
                    public void onSuccess(RecommendUserBean result) {
                        if (result.getData().size() == 0) {
                            current = 0;
                            getRecommendUserData();
                            return;
                        }
                            dataList.addAll(result.getData());
                            adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(String msg, int code) {
                        current = 0;
                        getRecommendUserData();
                    }
                });
    }

    CardAdapter adapter = new CardAdapter() {
        @Override
        public int getLayoutId() {
            return R.layout.item_card;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public void bindView(View view, int index) {
            Object tag = view.getTag();
            ViewHolder viewHolder;
            if (null != tag) {
                viewHolder = (ViewHolder) tag;
            } else {
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            }

            viewHolder.bindData(dataList.get(index));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.isFastDoubleClick()) {
                        return;
                    } else {
                        //弹出Toast或者Dialog
                        OtherUserHomeActivity.startAction(dataList.get(index).getId());
                    }
                }
            });
            View view1 = view.findViewById(R.id.iv_chat);
            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utils.isFastDoubleClick()) {
                        return;
                    } else {
                        //弹出Toast或者Dialog
                        gotoChatActivity(index);
                    }
                }
            });
        }

        @Override
        public RecommendUserBean.DataBean getItem(int index) {
            if (dataList != null && dataList.size() != 0 && dataList.size() > index) {
                return dataList.get(index);
            }
            return null;
        }

        @Override
        public Rect obtainDraggableArea(View view) {
            // 可滑动区域定制，该函数只会调用一次
            View contentView = view.findViewById(R.id.card_item_content);
            View topLayout = view.findViewById(R.id.card_top_layout);
            View bottomLayout = view.findViewById(R.id.card_bottom_layout);
            int left = view.getLeft() + contentView.getPaddingLeft() + topLayout.getPaddingLeft();
            int right = view.getRight() - contentView.getPaddingRight() - topLayout.getPaddingRight();
            int top = view.getTop() + contentView.getPaddingTop() + topLayout.getPaddingTop();
            int bottom = view.getBottom() - contentView.getPaddingBottom() - bottomLayout.getPaddingBottom();
            return new Rect(left, top, right, bottom);
        }
    };


    class ViewHolder {
        ImageView imageView, ivReal;
        TextView userNameTv, userSexTv, userAgeTv, userRegionTv;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.card_image_view);
            userNameTv = (TextView) view.findViewById(R.id.card_user_name);
            userSexTv = (TextView) view.findViewById(R.id.tv_sex);
            userAgeTv = (TextView) view.findViewById(R.id.tv_age);
            userRegionTv = (TextView) view.findViewById(R.id.tv_region);
            ivReal = (ImageView) view.findViewById(R.id.iv_real_name);
        }

        public void bindData(RecommendUserBean.DataBean itemData) {
            Glide.with(getContext()).load(itemData.getRecommendImages()).into(imageView);
            userNameTv.setText(itemData.getNickname());
            if (itemData.getIdCard() == null || itemData.getIdCard().equals("")) {
                ivReal.setVisibility(View.GONE);
            }
            userAgeTv.setText(itemData.getAge() + "");
            userRegionTv.setText(itemData.getRegion() + "");
            if (itemData.getSex() == 1) {
                userSexTv.setText("男");
            } else {
                userSexTv.setText("女");
            }
        }
    }


}
