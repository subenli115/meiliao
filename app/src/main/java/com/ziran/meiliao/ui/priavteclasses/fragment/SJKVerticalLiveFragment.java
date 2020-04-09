package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.GiftBean;
import com.ziran.meiliao.ui.bean.GiveGiftResultBean;
import com.ziran.meiliao.ui.priavteclasses.contract.SJKFullLiveContract;
import com.ziran.meiliao.ui.priavteclasses.presenter.SJKCoursePresenter;
import com.ziran.meiliao.ui.priavteclasses.util.SJKRoomHelper;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.ChatCustomView;
import com.ziran.meiliao.widget.KeyboardHeightLayout;
import com.ziran.meiliao.widget.livegift.GiftControl;
import com.ziran.meiliao.widget.livegift.GiftFrameLayout;
import com.ziran.meiliao.widget.livegift.GiftModel;
import com.ziran.meiliao.widget.pupop.GiveGiftPopupWindow;
import com.ziran.meiliao.widget.pupop.PopupWindowUtil;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import cn.rongcloud.live.LiveKit;
import cn.rongcloud.live.ui.message.GiftMessage;
import rx.functions.Action1;


/**
 * 私家课-活动Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKVerticalLiveFragment extends SJKVerticalLiveBaseFragment<SJKCoursePresenter, CommonModel> implements SJKFullLiveContract
        .View {


    @Bind(R.id.iv_sjk_fulllive_message)
    ImageView ivFullLiveMessage;

    @Bind(R.id.chatCustomView)
    ChatCustomView mChatCustomView;
    @Bind(R.id.gift_layout1)
    GiftFrameLayout mGiftFrameLayout1;
    @Bind(R.id.gift_layout2)
    GiftFrameLayout mGiftFrameLayout2;
    private GiftControl giftControl;
    private GiftModel giftModel;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sjk_vertical_live;
    }

    private SJKRoomHelper mSJKRoomHelper;


    @Override
    protected void initOther() {
        super.initOther();
        giftControl = new GiftControl(mGiftFrameLayout1, mGiftFrameLayout2);
        mChatCustomView.setChatListViewWidth(getResources().getDimensionPixelSize(R.dimen.chat_width));
        mSJKRoomHelper = new SJKRoomHelper(mChatCustomView);
        mRxManager.on(AppConstant.RXTag.SEND, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                showKeyBoard(false);
            }
        });
        mRxManager.on(AppConstant.RXTag.LIVE_OVER, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                //直播已结束
                ToastUitl.showShort("直播已结束,谢谢观看");
                HandlerUtil.runMain(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
        mRxManager.on(AppConstant.RXTag.USER_COUNT, new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                mPresenter.postUserCount(ApiKey.COURSE_GETCHRM_SUERCOUNT, MapUtils.getCourseMap(courseId));
            }
        });
        mRxManager.on(AppConstant.RXTag.GIVE_GIFT, new Action1<GiftBean.DataBean>() {
            @Override
            public void call(GiftBean.DataBean giftBean) {
                giftModel = new GiftModel(giftBean.getName(), giftBean.getName(), 1, giftBean.getPath(), "1", MyAPP.getUserInfo()
                        .getNickName(), StringUtils.headImg(), System.currentTimeMillis());
                giftControl.loadGift(giftModel);
                LiveKit.sendMessage(new GiftMessage(giftBean.getPath(),  "送给老师" + giftBean .getName()));
                Map<String, String> stringMap = MapUtils.getOnlyCan("giftId", giftBean.getGiftId());
                stringMap.put("courseId",courseId);
                mPresenter.postGiveGift(ApiKey.COURSE_GIVE_GIFT,stringMap);
            }
        });
        mRxManager.on(AppConstant.RXTag.BALANCE, new Action1<String>() {
            @Override
            public void call(String balance) {
                mDataBean.setUserCoin(Integer.parseInt(balance));
            }
        });
        mKeyboardHeightLayout.setViewGroup(mChatCustomView);
        mKeyboardHeightLayout.setOnKeyboardShowingListener(new KeyboardHeightLayout.OnKeyboardShowingListener() {
            @Override
            public void onKeyboardShowing(boolean isShowing) {
                if (!isShowing) {
                    mSJKRoomHelper.showInputPanel(false);
                    llSjkFullliveBottom.setVisibility(View.VISIBLE);
                    mSJKRoomHelper.setShowKeyboard(false);
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSJKRoomHelper.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        mSJKRoomHelper.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        mSJKRoomHelper.onResume();
    }

    @OnClick({R.id.iv_sjk_fulllive_gift, R.id.iv_sjk_fulllive_message})
    public void onViewClicked1(View view) {
        try {
            switch (view.getId()) {
                case R.id.iv_sjk_fulllive_message:
                    if (mDataBean.isHasBuy()) {
                        showKeyBoard(true);
                    } else {
                        ToastUitl.showShort("尚未购买该课程,该功能暂未能使用");
                    }
                    break;
                case R.id.iv_sjk_fulllive_gift:
                    if (EmptyUtils.isNotEmpty(giftList)) {
                        showGiftDialog();
                    } else {
                        mPresenter.listGift(ApiKey.COURSE_LIST_GIFT, MapUtils.getDefMap(false));
                    }

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    GiveGiftPopupWindow giveGiftPopupwindow;

    private void showGiftDialog() {
        giveGiftPopupwindow = new GiveGiftPopupWindow(getContext());
        giveGiftPopupwindow.setBalance(mDataBean.getUserCoin());
        giveGiftPopupwindow.setDatas(giftList);
        giveGiftPopupwindow.addBottomMargin();
        PopupWindowUtil.showTop(getActivity(), giveGiftPopupwindow);
    }

    @Override
    protected void close() {
        if (mSJKRoomHelper.isShowKeyboard()) {
            mSJKRoomHelper.showKeyboard(false);
        } else {
            finish();
        }
    }

    public void showKeyBoard(boolean show) {
        mSJKRoomHelper.showInputPanel(show);
        llSjkFullliveBottom.setVisibility(show ? View.GONE : View.VISIBLE);
        mSJKRoomHelper.showKeyboard(show);
    }

    public boolean onBackAction() {
        if (mSJKRoomHelper.isShowKeyboard()) {
            mSJKRoomHelper.showKeyboard(false);
            return true;
        }
        return super.onBackAction();
    }

    @Override
    protected void startPlay() {
        super.startPlay();
        mSJKRoomHelper.onFragmentVisibleChange(true);
    }

    @Override
    public void setUserCount(String userCount) {
        mLveAvatarView.setContent("直播", userCount);
    }

    @Override
    public void onPayResult(String url) {
        super.onPayResult(url);
        mVideoPlayerHelper.startPlay(mVideoPlayerHelper.getCurrentVideoPath(mDataBean));
        mSJKRoomHelper.onFragmentVisibleChange(true);
    }

    private List<GiftBean.DataBean> giftList;

    @Override
    public void showListGift(List<GiftBean.DataBean> beanList) {
        giftList = beanList;
        showGiftDialog();
    }

    @Override
    public void giveGiftResult(GiveGiftResultBean.DataBean result) {
        mDataBean.setUserCoin(result.getYue());
        if (giveGiftPopupwindow != null) {
            giveGiftPopupwindow.setBalance(result.getYue());
        }
    }

}
