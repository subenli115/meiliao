package com.ziran.meiliao.ui.NewDecompressionmuseum.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.NewDecompressionmuseum.contract.PracticeWorkBookContract;
import com.ziran.meiliao.ui.NewDecompressionmuseum.presenter.PracticeWorkBookPresenter;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.bean.JdxShareBean;
import com.ziran.meiliao.ui.bean.PracticeCalendarBean;
import com.ziran.meiliao.ui.bean.PracticeHeadBean;
import com.ziran.meiliao.ui.bean.PracticeJoinBean;
import com.ziran.meiliao.ui.bean.PracticeStartBean;
import com.ziran.meiliao.ui.bean.PracticeStatusBean;
import com.ziran.meiliao.ui.bean.PracticeWorkBookBean;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_INFO;
import static com.ziran.meiliao.constant.ApiKey.PRACTIEACTIVITY_JOIN;

/**
 * 加入课程界面
 */
public class MbsrWorkbookMainActivity extends CommonHttpActivity<PracticeWorkBookPresenter, CommonModel> implements PracticeWorkBookContract.View  {
    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;
    @Bind(R.id.iv_me_main_avatar)
    ImageView ivMeMainAvatar;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.tv_course_is)
    TextView tvCourseIs;
    @Bind(R.id.tv_choose_tutor)
    TextView tvChooseTutor;
    @Bind(R.id.arl_top)
    AutoRelativeLayout arlTop;
    @Bind(R.id.tv_add_course)
    TextView tvAddCourse;
    @Bind(R.id.webView)
    WebView webView;

    @Bind(R.id.iv_head)
    ImageView ivHead;
    private Map<String, String> stringMap;
    private String bookId;
    private String num;

    @Override
    public int getLayoutId() {
        return R.layout.activity_jdx_add_course;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }
    public static void startAction(Context context,String BookId) {
        Intent intent = new Intent();
        intent.putExtra("BookId",BookId+"");
        intent.setClass(context,MbsrWorkbookMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        if (tvUserName != null && MyAPP.getUserInfo() != null) {
            tvUserName.setText(MyAPP.getUserInfo().getNickName());
        }
        super.onResume();
    }

    @Override
    public void initView() {
        if(getIntent()!=null){
            Intent intent = getIntent();
            boolean  course = intent.getBooleanExtra("course",true);
             bookId = intent.getStringExtra("BookId");
             num = intent.getStringExtra("num");
            if(!course){
                tvCourseIs.setText("已加入课程");
            }else{
                tvAddCourse.setVisibility(View.VISIBLE);

            }
        }
        ntbTitle.setNewTitleText("MBSR八周练习册");
        ntbTitle.setLeftImagSrc(R.mipmap.exercise_back);
        ntbTitle.setTitleWeizhi();
        ntbTitle.setBackGroundColor(R.color.transparent);
        ntbTitle.setTvLeftVisiable(true, true);
        stringMap = MapUtils.getDefMap(true);
        stringMap.put("id", bookId);
        stringMap.put("status","1");
        ImageLoaderUtils.displayCircle(getBaseContext(), ivMeMainAvatar, StringUtils.headImg(), R.mipmap.ic_user_pic);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        WebSettings ws = webView.getSettings();
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.getSettings().setDomStorageEnabled(true);//对H5支持
        webView.getSettings().setAllowFileAccess(true);
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("id",bookId);

        mPresenter.getPracticeWookBookData(PRACTIEACTIVITY_INFO, defMap);
    }

    @OnClick({R.id.tv_add_course})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_course:
                //加入课程
                mPresenter.getPracticeJoin(PRACTIEACTIVITY_JOIN, stringMap);
                break;
        }
    }
    public static void startAction(Context context,String num,String bookId) {
        if (CheckUtil.check(context)) {
            Intent intent = new Intent(context, MbsrWorkbookMainActivity.class);
            intent.putExtra("course",false);
            intent.putExtra("num",num);
            context.startActivity(intent);
        }
    }



    @Override
    public void showWookBookData(PracticeWorkBookBean.DataBean result) {
        String booksDetail = result.getBooksDetail();
        tvChooseTutor.setText(result.getGuideMusic());
        Glide.with(mContext).load(result.getPicture()).error(R.color.orange).into(ivHead);
        webView.loadUrl(booksDetail);
    }

    @Override
    public void showPracticeJoinData(PracticeJoinBean.DataBean result) {
        PracticeTaskActivity.startAction(mContext,bookId,"1");
        finish();
    }

    @Override
    public void showPracticeStartData(PracticeStartBean.DataBean result) {

    }

    @Override
    public void showPracticeCalendarData(PracticeCalendarBean.DataBean result) {

    }

    @Override
    public void showPracticeHeadData(PracticeHeadBean.DataBean result) {

    }

    @Override
    public void showPracticeStatusData(JdxShareBean.DataBean result) {

    }


    @Override
    public void showPracticeCalendarStatusData(PracticeStatusBean.DataBean result) {

    }
}
