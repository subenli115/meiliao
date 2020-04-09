package com.ziran.meiliao.ui.priavteclasses.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.AuthorBean;
import com.ziran.meiliao.ui.bean.LiveIncomeBean;
import com.ziran.meiliao.ui.bean.LiveRoomBean;
import com.ziran.meiliao.ui.priavteclasses.fragment.OpenHistoryFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.OpenLiveListFragment;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import rx.functions.Action1;


public class LiveRoomActivity extends BaseActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<LiveRoomBean, LiveIncomeBean> {


    @Bind(R.id.rl_sjk_live_room_bg)
    View ivSjkLiveRoomBg;
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.iv_sjk_live_room_bg)
    ImageView ivSjkLiveRoomGg;
    @Bind(R.id.iv_sjk_live_room_avatar)
    ImageView ivSjkLiveRoomAvatar;
    @Bind(R.id.tv_sjk_live_room_fans_count)
    TextView tvSjkLiveRoomFansCount;
    @Bind(R.id.tv_sjk_live_room_profit_amount)
    TextView getTvSjkLiveRoomProfitAmount;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sjk_live_room;
    }

    @Override
    public void initPresenter() {
        if (mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void initView() {
        SetTranslanteBar();
        ntb.setTitleText(StringUtils.format("%s直播间后台", MyAPP.getUserInfo().getNickName()));
        ntb.setTvLeftVisiable(true, true);
        ntb.setBackGroundColor(R.color.transparent);


        mPresenter.postData(ApiKey.LIVE_USER_LIVE_HOME, MapUtils.getDefMap(true), LiveRoomBean.class);
        mPresenter.postAction(ApiKey.LIVE_LIVE_INCOME, MapUtils.getDefMap(true), LiveIncomeBean.class);

        mRxManager.on(ApiKey.LIVE_GET_MONEY_SUPPLY, new Action1<String>() {
            @Override
            public void call(String s) {
                mLiveIncomeData.setAbleMoney(Double.parseDouble(s));
//                bundle.putString("","");
            }
        });
    }

    private OpenLiveListFragment mToBeOpenFragment;
    private OpenHistoryFragment mOpenHistoryFragment;

    @OnClick({R.id.iv_sjk_live_room_avatar, R.id.tv_sjk_live_room_open_list, R.id.tv_sjk_live_room_fans_count, R.id.tv_sjk_live_room_profit_amount, R.id
            .tv_sjk_live_room_course_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sjk_live_room_avatar:
                startActivity(TeacherDetailActivity.class, putBundle());
                break;
            case R.id.tv_sjk_live_room_open_list:
                change(true);
                break;
            case R.id.tv_sjk_live_room_course_list:
                change(false);
                break;
            case R.id.tv_sjk_live_room_fans_count:
                startActivity(FansListActivity.class);
                break;
            case R.id.tv_sjk_live_room_profit_amount:
                startActivity(ProfitActivity.class, putBundle());
                break;
        }
    }

    private boolean showToBeOpen;

    private void change(boolean first) {
        if (showToBeOpen == first) return;
        showToBeOpen = first;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.show(first ? mToBeOpenFragment : mOpenHistoryFragment).hide(first ? mOpenHistoryFragment : mToBeOpenFragment).commit();
    }

    Bundle bundle;
    public Bundle putBundle() {
//        if (bundle!=null) return bundle;
         bundle = new Bundle();
        AuthorBean authorBean = new AuthorBean();
        authorBean.setId(Long.valueOf(mDataBean.getTeacherId()));
        authorBean.setName(mDataBean.getTeacherName());
        authorBean.setHeadImg(mDataBean.getPicture());
        if (mLiveIncomeData != null) {
            authorBean.setAvailableCash(String.valueOf((int)mLiveIncomeData.getAbleMoney()));
            authorBean.setIncome(String.valueOf((int)mLiveIncomeData.getTotalIncome()));
            bundle.putParcelable("liveIncome", mLiveIncomeData);
        }
        bundle.putParcelable(AppConstant.ExtraKey.AUTHOR_DATA, authorBean);
        return bundle;
    }

    private LiveRoomBean.DataBean mDataBean;

    @Override
    public void returnData(LiveRoomBean result) {
        mDataBean = result.getData();
        ImageLoaderUtils.displayCircle(this, ivSjkLiveRoomAvatar, mDataBean.getPicture(), R.mipmap.ic_user_pic);
        ImageLoaderUtils.display(this, ivSjkLiveRoomGg, mDataBean.getPicture(), R.mipmap.ic_loading_square_big);
        tvSjkLiveRoomFansCount.setText(getHtmlString(mDataBean.getFansNumbers(), "粉丝"));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        mToBeOpenFragment = new OpenLiveListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) mDataBean.getHeraldList());
        mToBeOpenFragment.setArguments(bundle);
        bundle = new Bundle();
        bundle.putParcelableArrayList("list", (ArrayList<? extends Parcelable>) mDataBean.getHistoryList());
        mOpenHistoryFragment = new OpenHistoryFragment();
        mOpenHistoryFragment.setArguments(bundle);
        ft.add(R.id.frameLayout, mToBeOpenFragment).add(R.id.frameLayout, mOpenHistoryFragment).show(mToBeOpenFragment).hide(mOpenHistoryFragment).commit();
        showToBeOpen = true;
    }

    public Spanned getHtmlString(Object res, String tag) {
        String html = "<span><span><font color=" + "#333333" + ">" + res + "<br/>" + "</span>" + tag + "</span>";
        return Html.fromHtml(html);
    }

    private LiveIncomeBean.DataBean mLiveIncomeData;

    @Override
    public void returnAction(LiveIncomeBean result) {
        mLiveIncomeData = result.getData();
        getTvSjkLiveRoomProfitAmount.setText(getHtmlString((int) mLiveIncomeData.getTotalIncome(), "收益"));
    }
}
