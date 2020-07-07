package com.ziran.meiliao.ui.main.fragment;

import android.graphics.Bitmap;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.app.MeiliaoConfig;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.common.commonutils.TimeUtil;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.commonwidget.ViewPagerFixed;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.common.receiver.NetUtil;
import com.ziran.meiliao.common.viewpager.CardPagerAdapter;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.db.DbCore;
import com.ziran.meiliao.entry.CachePageEntry;
import com.ziran.meiliao.entry.GainSpreadDayEmtry;
import com.ziran.meiliao.entry.UserInfo;
import com.ziran.meiliao.envet.OnLoadDataListener;
import com.ziran.meiliao.ui.base.CommonHttpFragment;
import com.ziran.meiliao.ui.bean.CheckVipLevelBean;
import com.ziran.meiliao.ui.bean.GainSpreadBean;
import com.ziran.meiliao.ui.bean.ResBean;
import com.ziran.meiliao.ui.bean.TrailerBean;
import com.ziran.meiliao.ui.bean.UserLevelBean;
import com.ziran.meiliao.ui.main.activity.MainNewActivity;
import com.ziran.meiliao.ui.main.activity.NewLoginActivity;
import com.ziran.meiliao.ui.main.adapter.MeViewPagerAdapter;
import com.ziran.meiliao.ui.main.contract.MainMeContract;
import com.ziran.meiliao.ui.main.model.MainMeModel;
import com.ziran.meiliao.ui.main.presenter.MainMePresenter;
import com.ziran.meiliao.ui.settings.activity.FeekBackActivity;
import com.ziran.meiliao.ui.settings.activity.MessageActivity;
import com.ziran.meiliao.ui.settings.activity.NewUserInfoActivity;
import com.ziran.meiliao.ui.settings.activity.SettingsActivity;
import com.ziran.meiliao.ui.settings.activity.WalletActivity;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.utils.UpdateManager;
import com.ziran.meiliao.widget.ItemGroupView;
import com.ziran.meiliao.widget.ObservableScrollView;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.jpush.android.service.DownloadActivity;
import rx.functions.Action1;


/**
 * 主界面我的页面
 */
public class NewMainMeFragment extends CommonHttpFragment<MainMePresenter, MainMeModel> implements MainMeContract.View {

    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.rl_me_level)
    RelativeLayout rlmelevel;
    @Bind(R.id.tv_main_me_new_username)
    TextView tvUserName;
    @Bind(R.id.progressBarHorizontal)
    ProgressBar progressBarHorizontal;
    @Bind(R.id.tv_main_me_new_message_empty)
    TextView tvMessageEmpty;
    @Bind(R.id.itemView_push)
    ItemGroupView itemViewPush;

    @Bind(R.id.viewpager)
    ViewPagerFixed viewPager;
    private MeViewPagerAdapter mCardAdapter;
    @Bind(R.id.iv_me_main_isvip)
    ImageView ivIsVip;
    @Bind(R.id.iv_me_main_avatar)
    ImageView ivMeMainAvatar;
    @Bind(R.id.tv_me_main_new_live_room)
    ItemGroupView igvLiveRoom;
    @Bind(R.id.scrollView)
    ObservableScrollView mScrollView;
    @Bind(R.id.iv_me_level)
    ImageView ivMeLevel;
    @Bind(R.id.tv_me_level)
    TextView tvMeLevel;
    @Bind(R.id.tv_progress)
    TextView tv_progress;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main_me_new;
    }

    private boolean isInitView;

    @Override
    protected void initView() {
        if (!isInitView) {
            //设置标题栏
            ntb.setVerLineVisiable(false);
            ntb.setTitleVisibility(true);
            ntb.setTitleText("我的");
            ntb.setTitleColor(getResources().getColor(R.color.white));
            ntb.setRightImagSrc(R.mipmap.new_me_setting);
            ntb.setTitleWeizhi();
            ntb.setOnivBackImagListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!MyAPP.isLogin(getContext())) { //如果没有登录则跳转到登录界面
                        return;
                    }
                    finish();
                  startActivity(MainNewActivity.class);
                }
            });
            itemViewPush.setRigthTextSize();
            ntb.setOnRightImagListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!MyAPP.isLogin(getContext())) { //如果没有登录则跳转到登录界面
                        return;
                    }
                    startActivity(SettingsActivity.class);
                }
            });
            //设置标题栏颜色
            ntb.setBackGroundColor(R.color.transparent);
            //加载用户信息
            loadUser();
            //订阅更新用户头像

            mRxManager.on(AppConstant.RXTag.UPDATE_USER, new Action1<String>() {
                @Override
                public void call(String s) {
                    if ("head".equals(s))
                        ImageLoaderUtils.displayCircle(getContext(), ivMeMainAvatar, StringUtils.headImg(), R.mipmap.ic_user_pic);
                    if ("login".equals(s)) {
                        loadUser();
                    }
                }
            });
            //订阅更新用户头像
            mRxManager.on(AppConstant.RXTag.UPDATE_TRAILER, new Action1<Boolean>() {
                @Override
                public void call(Boolean s) {
                    updateTrailer();
                }
            });
            //网络状态发生变化时执行,用于没有加载预告数据时使用
            mRxManager.on(AppConstant.RXTag.NETWORK_CHANGE_STATE, new Action1<Integer>() {
                @Override
                public void call(Integer integer) {
                    switch (integer) {
                        case NetUtil.NETWORK_MOBILE:
                        case NetUtil.NETWORK_WIFI:
                            //如果没有加载预告数据,则加载
                            if (!isLoadViewPagerData) {
                                loadViewPagerData();
                            }
                            break;
                    }
                }
            });
            HandlerUtil.runMain(new Runnable() {
                @Override
                public void run() {
                    //最新版本检查
                    new UpdateManager(getContext()).checkUpdate();
                    // 请求是否有赠送推广音频数据
                }
            });
            isInitView = true;

            //专辑推荐
            mRxManager.on(AppConstant.RXTag.GET_GAIN_SPREAD, new Action1<Boolean>() {
                @Override
                public void call(Boolean s) {
                    String dayStr = TimeUtil.getStringByFormat(System.currentTimeMillis());
                    List<GainSpreadDayEmtry> emtries = DbCore.getDaoSession().getGainSpreadDayEmtryDao().queryRaw("where DAY_TIME = ?",
                            new String[]{dayStr});
                    if (emtries.size() == 0) {
                        GainSpreadDayEmtry dayEmtry = new GainSpreadDayEmtry();
                        dayEmtry.setDayTime(dayStr);
                        dayEmtry.setIsShow(true);
                        DbCore.getDaoSession().insertOrReplace(dayEmtry);
                        mPresenter.getGainSpread(MapUtils.getDefMap(true));
                    } else {
                        GainSpreadDayEmtry spreadDayEmtry = emtries.get(0);
                        if (!spreadDayEmtry.getIsShow()) {
                            spreadDayEmtry.setIsShow(true);
                            DbCore.getDaoSession().insertOrReplace(spreadDayEmtry);
                            mPresenter.getGainSpread(MapUtils.getDefMap(true));
                        }
                    }
                }
            });
        }
        mScrollView.openScroll();
    }

    //执行消息更新请求
    private void updateTrailer() {
        mPresenter.getTrialer(MapUtils.getDefMap(true));
    }

    private TrailerBean.DataBean mTrailerData;

    //初始化预告控件
    private void initViewPager() {
        if (mCardAdapter == null) {
            mCardAdapter = new MeViewPagerAdapter();
            mCardAdapter.setOnItemClickListener(new CardPagerAdapter.OnItemClickListener<TrailerBean.DataBean>() {
                @Override
                public void onItemClick(ViewGroup container, TrailerBean.DataBean item, int position) {
                    mTrailerData = item;
                }
            });
        }
    }

    private boolean isLoadViewPagerData;

    //加载预告数据
    private void loadViewPagerData() {
        if (NetUtil.getNetWorkState(getContext()) == NetUtil.NETWORK_NONE) {
            TrailerBean trailerBean = CachePageEntry.loadData(AppConstant.CACHE_PAGE_MAIN_ME_TRAILER, TrailerBean.class);
            setTrailerResult(trailerBean);
        } else {
            updateTrailer();
        }
    }

    private void setTrailerResult(TrailerBean result) {
        initViewPager();
        if (EmptyUtils.isNotEmpty(result.getData())) {
            viewPager.setVisibility(View.VISIBLE);
            mCardAdapter.addCardItems(result.getData());
            viewPager.setAdapter(mCardAdapter);
            viewPager.setOffscreenPageLimit(3);
            isLoadViewPagerData = true;
            tvMessageEmpty.setVisibility(View.GONE);
        } else {
            viewPager.setVisibility(View.GONE);
//            tvMessageEmpty.setVisibility(View.VISIBLE);
        }
    }

    //用户头像版本值
    private String headVersionKey;

    private void loadUser() {
        //加载用户信息
        MyAPP.loadUserInfo(new OnLoadDataListener<UserInfo>() {
            @Override
            public void loadSuccess(UserInfo userInfo) {
                if(userInfo!=null){
                    tvUserName.setText(userInfo.getNickName());

                    }
                if (StringUtils.getText(R.string.guest_name).equals(userInfo.getNickName())) {
                    ivMeMainAvatar.setImageResource(R.mipmap.ic_user_pic);
                    tvUserName.setText("游客");
                    viewPager.setVisibility(View.GONE);
                    ivIsVip.setVisibility(View.GONE);
                    return;
                }
                igvLiveRoom.setVisibility(userInfo.isTeacher() ? View.VISIBLE : View.GONE);
                headVersionKey = StringUtils.getHeadVersionKey();
                int imgVersion = SPUtils.getInt(headVersionKey);
                if (userInfo.getHeadImgVersion() == imgVersion && new File(StringUtils.headImg()).exists()) {
                    //从本地文件加载
                    ImageLoaderUtils.displayCircle(getContext(), ivMeMainAvatar, StringUtils.headImg(), R.mipmap.ic_user_pic);
                } else {//从服务器下载用户头像
                    ImageLoaderUtils.displayCircle(getContext(), ivMeMainAvatar, userInfo.getHeadImg(), R.mipmap.ic_user_pic);
                    if (MyAPP.getUserInfo().getHeadImgVersion() > 0) {
                        mPresenter.downloadHeadImage(userInfo.getHeadImg(), StringUtils.headImg());
                        SPUtils.setInt(headVersionKey, userInfo.getHeadImgVersion());
                    }
                }
                if (MyAPP.getDeviceToken() != null) {

                    mPresenter.postRecordToken(MapUtils.getOnlyCan(AppConstant.ExtraKey.DEVICE_TOKEN, MyAPP.getDeviceToken()));
                }
                //初始化消息提示控件
                initViewPager();
                //加载数据
                loadViewPagerData();
                if (NetUtil.getNetWorkState(getContext()) != NetUtil.NETWORK_NONE) {
                    //执行检查用户VIP请求
                    mPresenter.postCheckLevel(MapUtils.getOnlyCan("vipId", "1"));
                }
//                DailyMindUtil.initNotify(getContext());
            }
        });
        mPresenter.getMemberLevel(MapUtils.getDefMap(true));
    }


    @Override
    public void onResume() {
        super.onResume();
        if (tvUserName != null && MyAPP.getUserInfo() != null) {
            tvUserName.setText(MyAPP.getUserInfo().getNickName());
        }
    }

    //返回下载用户头像的结果
    @Override
    public void showUploadHeadImg(Result result) {
        SPUtils.setInt(headVersionKey, MyAPP.getUserInfo().getHeadImgVersion());
    }

    @Override
    public void showUpdateTrialer(TrailerBean result) {
        if (EmptyUtils.isNotEmpty(result)) {
            CachePageEntry.insertData(AppConstant.CACHE_PAGE_MAIN_ME_TRAILER, result);
            setTrailerResult(result);
        }
    }

    //返回用户VIP信息的结果
    @Override
    public void showCheckLevel(CheckVipLevelBean result) {
    }

    @Override
    public void showUserHomeRes(ResBean result) {
        MeiliaoConfig.setResBean(result);
    }

    //显示推广专辑的窗口
    @Override
    public void gainSpread(final GainSpreadBean.DataBean bean) {
    }

    @Override
    public void showResUrl(ResBean.DataBean result) {

        SharePopupWindow.showPopup(getActivity(), mSharePopupWindow, result.getShareTitle(), result.getShareDescribe(), result
                .getShareUrl(),result.getSharePicture());
        mSharePopupWindow.setNeedRealUrl(false);
    }
    //设置会员等级
    @Override
    public void showUserLevel(UserLevelBean result) {
        UserLevelBean.DataBean data = result.getData();
        if(data!=null){

        tvMeLevel.setText(data.getLevel());
        Glide.with(getContext())
                .load(data.getPic())
                .asBitmap() //这句不能少，否则下面的方法会报错
                .centerCrop()
                .into(new BitmapImageViewTarget(ivMeLevel) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        ivMeLevel.setImageDrawable(circularBitmapDrawable);
                    }
                });
        tv_progress.setText(data.getNextLevelPercent()+"%");
        progressBarHorizontal.setProgress(data.getNextLevelPercent());
        }
    }


    //点击监听
    @OnClick({R.id.iv_me_main_avatar, R.id.iv_main_me_new_vip, R.id.tv_me_main_new_download, R.id.tv_me_main__new_feek_back, R.id
            .tv_main_me_new_recharge_or_balance_query, R.id.tv_main_me_new_coupon, R.id.tv_me_main_new_follow, R.id
            .tv_main_me_new_message_bar, R.id.tv_me_main_new_live_room, R.id.tv_me_main_new_message,R.id.itemView_push,R.id.rl_me_level,R.id.itemView_medal})
    public void onClick22(View view) {
        if (!MyAPP.isLogin(getContext())) { //如果没有登录则跳转到登录界面
            return;
        }
        switch (view.getId()) {
            case R.id.iv_me_main_avatar:
                //跳转到个人资料界面
                if ("游客".equals(MyAPP.getUserInfo().getNickName())) {
                    if (CheckUtil.check(getContext(), view)) {
                        NewLoginActivity.startAction(getContext());
                    }
                } else {
                    NewUserInfoActivity.startAction(getContext());
                }
                break;
            case R.id.iv_main_me_new_vip:
                //跳转到加入会员
                if (CheckUtil.check(getContext(), view)) {
                }
                break;
            case R.id.tv_me_main_new_message:
                //跳转到消息详情页面
                if (CheckUtil.check(getContext(), view)) {
                    MessageActivity.startAction(getContext(), -1);
                }
                break;
            case R.id.tv_main_me_new_coupon:
                //跳转到加入会员
                break;
            //充值或余额查询
            case R.id.tv_main_me_new_recharge_or_balance_query:
                if (CheckUtil.check(getContext(), view)) {
                    WalletActivity.startAction(getContext());
                }
                break;
            case R.id.tv_me_main_new_follow:
                //跳转到收藏界面
                break;
            case R.id.itemView_push:
                sharePush(view);
                break;
            case R.id.tv_me_main_new_download:
                //跳转到已下载界面
                startActivity(DownloadActivity.class);
                break;

            case R.id.tv_me_main__new_feek_back:
                //跳转到意见反馈界面
                startActivity(FeekBackActivity.class);
//                DefVideoActivity.startAction(getContext(),"http://ojlzx3sl8.bkt.clouddn.com/0.mp4");
                break;

            case R.id.rl_me_level:
                //跳转到会员详情

                break;
            case R.id.itemView_medal:
                //跳转到会员详情
                break;
        }
    }
    SharePopupWindow mSharePopupWindow;

    private void sharePush(View view) {

        if (CheckUtil.check(getContext(), view) ) {
            mPresenter.getResUrl(MapUtils.getDefMap(true));

        }
    }












}
