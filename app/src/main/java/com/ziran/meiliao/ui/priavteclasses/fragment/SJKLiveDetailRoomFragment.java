package com.ziran.meiliao.ui.priavteclasses.fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragment;
import com.ziran.meiliao.envet.BuyCourseCallBack;
import com.ziran.meiliao.ui.priavteclasses.util.SJKRoomHelper;
import com.ziran.meiliao.widget.ChatCustomView;

import butterknife.Bind;

/**
 * 私家课-直播详情-简介Fragment
 * Created by Administrator on 2017/1/7.
 */

public class SJKLiveDetailRoomFragment extends BaseFragment {


    @Bind(R.id.chatCustomView)
    ChatCustomView mChatCustomView;
    private SJKRoomHelper mSJKRoomHelper;
    BuyCourseCallBack mBuyCourseCallBack;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_sjk_live_detail_room;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    protected void initView() {
        if (isFirstInit) {
            mSJKRoomHelper = new SJKRoomHelper(mChatCustomView);
            isFirstInit = false;
            mSJKRoomHelper.setShowJoinAnimant(false);
            mSJKRoomHelper.setTextColorType(1);
            if (getActivity() instanceof BuyCourseCallBack) {
                mBuyCourseCallBack = (BuyCourseCallBack) getActivity();
            }
        }
    }

    boolean isFirstInit = true;
    boolean isFristLoad = true;

    public void loadRoom() {
        if (isFristLoad) {
            mSJKRoomHelper.onFragmentVisibleChange(true);
            isFristLoad = false;
        }
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (!isVisible) {
            mSJKRoomHelper.showKeyboard(false);
        } else {
            if (mBuyCourseCallBack != null) {
                mSJKRoomHelper.showInputPanel(mBuyCourseCallBack.hasBuy());
            } else {
                mSJKRoomHelper.showInputPanel(isBuy);
            }
        }
    }


    public boolean onActionBack() {
        return false;
    }


    @Override
    public void onResume() {
        super.onResume();
        mSJKRoomHelper.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mSJKRoomHelper.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSJKRoomHelper.onDestroy();
    }

    boolean isBuy;

    public void setInputPanelVisibility(boolean show) {
        isBuy = show;
        mSJKRoomHelper.showInputPanel(show);
    }
}
