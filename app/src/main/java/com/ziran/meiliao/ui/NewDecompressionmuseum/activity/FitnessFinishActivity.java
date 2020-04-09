package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.FintessDetailBean;
import com.ziran.meiliao.ui.bean.FitnessSavePracticeBean;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.PrefUtils;
import com.ziran.meiliao.widget.GlideRoundTransform;
import com.ziran.meiliao.widget.MoreImageView;
import com.ziran.meiliao.widget.pupop.SharePopupWindow;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;


public class FitnessFinishActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {

    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.main)
    AutoRelativeLayout  main;
    @Bind(R.id.iv_top)
    ImageView ivTop;
     @Bind(R.id.tv_finish)
     TextView tvFinish;
    @Bind(R.id.iv_user_avatar)
    ImageView ivUserVatar;
    @Bind(R.id.tv_time_finish)
    TextView tvTimeFinish;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.iv_icon)
    ImageView ivIcon;
    @Bind(R.id.iv_icon_two)
    ImageView ivIconTwo;
    @Bind(R.id.tv_title_one)
    TextView tvTitle;
    @Bind(R.id.tv_title_two)
    TextView tvTitleTwo;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_time_two)
    TextView tvTimeTwo;
    @Bind(R.id.tv_duration)
    TextView tvDuration;
    @Bind(R.id.tv_count)
    TextView tvCount;

    private AutoRelativeLayout firstPop;
    private SHARE_MEDIA mShareMedia;
    private String userPracticeCount;
    Boolean isFirstPractice;
    private FitnessSavePracticeBean.DataBean savePracticeBean;
    private FintessDetailBean.DataBean.JsCourseBean jsBean;
    private FitnessSavePracticeBean.DataBean.MusicsBean musicsBean;
    private FitnessSavePracticeBean.DataBean.MusicsBean musicsBeanTwo;
    private long practiceTime;


    @Override
    public int getLayoutId() {
        return R.layout.ac_fitness_exe_finish;
    }

    @Override
    public void returnData(Result result) {

    }

    @Override
    public void returnAction(Result result) {

    }
    public static void startAction(Context context, Boolean isFirstPractice, FitnessSavePracticeBean bean, FintessDetailBean.DataBean.JsCourseBean jsBean, int userPracticeCount) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, FitnessFinishActivity.class);
            intent.putExtra("isFirstPractice",isFirstPractice);
            intent.putExtra("SavePracticeBean",bean.getData());
            intent.putExtra("jsBean",jsBean);
            intent.putExtra("userPracticeCount",userPracticeCount+"");

            context.startActivity(intent);
        }
    }

    @Override
    public void initView() {
        super.initView();
        if(getIntent()!=null){
             isFirstPractice = getIntent().getBooleanExtra("isFirstPractice",false);
            userPracticeCount = getIntent().getStringExtra("userPracticeCount");
            savePracticeBean =(FitnessSavePracticeBean.DataBean) getIntent().getSerializableExtra("SavePracticeBean");
            jsBean = (FintessDetailBean.DataBean.JsCourseBean)getIntent().getSerializableExtra("jsBean");
        }
        long fitnessTime = PrefUtils.getLong("FitnessStartTime", 0, mContext);
         practiceTime = System.currentTimeMillis() - fitnessTime;
        ntbTitle.setBackGroundColor(R.color.transparent);
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                main.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                if(isFirstPractice){
                    showPopFirst();
                }

            }
        });
        initData();
    }
    /**
          * 日期格式字符串转换成时间戳
          *
          * @param dateStr
          *            字符串日期
          * @param format
          *            如：yyyy-MM-dd HH:mm:ss
          *
          * @return
          */
    public static String timeStamp2Date(String time) {
        Long timeLong = Long.parseLong(time);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");//要转换的时间格式
        Date date;
        try {
            date = sdf.parse(sdf.format(timeLong));
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void initData() {
        ImageLoaderUtils.displayCircle(getBaseContext(), ivUserVatar, MyAPP.getUserInfo().getHeadImg(), R.mipmap.ic_user_pic);
        tvUserName.setText(MyAPP.getUserInfo().getNickName());
        if(savePracticeBean!=null&&savePracticeBean.getMusics()!=null){
            musicsBean = savePracticeBean.getMusics().get(0);
            musicsBeanTwo = savePracticeBean.getMusics().get(1);
        }
        if(musicsBeanTwo!=null){
            Glide.with(mContext).load(musicsBeanTwo.getImage()).transform(new GlideRoundTransform(mContext)).into(ivIconTwo);
            tvTitleTwo.setText(musicsBeanTwo.getName());
            tvTimeTwo.setText(musicsBeanTwo.getDuration());
        }
        if(musicsBean!=null){
            Glide.with(mContext).load(musicsBean.getImage()).transform(new GlideRoundTransform(mContext)).into(ivIcon);
            tvTitle.setText(musicsBean.getName());
            tvTime.setText(musicsBean.getDuration());
        }
        tvDuration.setText(timeStamp2Date(practiceTime+""));

        tvCount.setText("第"+userPracticeCount+"次");
        tvFinish.setText(jsBean.getName()+"练习完成");
        Glide.with(mContext).load(jsBean.getDetailPic()).into(ivTop);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        tvTimeFinish.setText(simpleDateFormat.format(date)+"完成练习");

    }


    @OnClick({R.id.arl_one,R.id.arl_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arl_one:
                FitnessPlayActivity.startAction(mContext,musicsBean);
                break;
            case R.id.arl_two:
                FitnessPlayActivity.startAction(mContext,musicsBeanTwo);

                break;
        }
    }



    SharePopupWindow mSharePopupWindow;
    private void share() {
        SharePopupWindow.showPopup(mContext, mSharePopupWindow, jsBean.getShareTitle(), jsBean.getShareDesc(), jsBean
                .getShareUrl(),jsBean.getSharePic());
    }



    /**
     *
     *  购买成功弹窗
     */
    private void showPopFirst() {
        // 一个自定义的布局，作为显示的内容
        int[] location = new int[2];
        firstPop = (AutoRelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.pop_fitness_medal, null);
        firstPop.getLocationOnScreen(location);
        final PopupWindow popupWindow = new PopupWindow(firstPop,
                AutoLinearLayout.LayoutParams.MATCH_PARENT, AutoLinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        popupWindow.setFocusable(true);// 点击空白处时，隐藏掉pop窗口
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(main, Gravity.CENTER,0,0);
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        final ImageView tvClose = firstPop.findViewById(R.id.iv_share_close);
        final TextView tvShare = firstPop.findViewById(R.id.tv_share);
        final AutoRelativeLayout arlFirst = firstPop.findViewById(R.id.arl_first);
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                share();
            }
        });
        MoreImageView moreImageView = firstPop.findViewById(R.id.moreImageView);
        moreImageView.setOnMoreImageViewClickListener(new MoreImageView.OnMoreImageViewClickListener() {
            @Override
            public void onClick(int index) {
                switch (index) {
                    case 0:
                        share(SHARE_MEDIA.WEIXIN.toSnsPlatform().mPlatform);
                        break;
                    case 1:
                        share(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform);
                        break;
                }
            }
        });
    }
    private void share(SHARE_MEDIA shareMedia) {
//        mShareMedia=shareMedia;
//        ShareUtil.shareWeb1(mContext, mShareMedia, shareBean.getShareDesc(), shareBean.getSharePicture(), shareBean.getShareUrl(), shareBean.getShareTitle(), shareBean.getShareDesc());
    }
}
