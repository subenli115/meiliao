package com.ziran.meiliao.ui.priavteclasses.util;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.RoundImageView;
import com.ziran.meiliao.common.irecyclerview.IRecyclerView;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.MediaDetailBean;
import com.ziran.meiliao.ui.bean.RecordListBean;
import com.ziran.meiliao.ui.priavteclasses.activity.ZhuanLanCommentActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.PptAdapter;
import com.ziran.meiliao.widget.PlayPauseView;

import java.util.ArrayList;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/5/25 11:21
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/5/25$
 * @updateDes ${TODO}
 */

public class ZLImageTextHeadUtil {
    RoundImageView mIvHeadviewImg;
    ImageView mIvHeadviewBack;
    TextView mTvHeadviewTitle;
    TextView mTvHeadviewName;
    ImageView mIvZlCover;
    PlayPauseView mPlayPauseView;
    public ImageView mIvZlDownload;
    TextView mTvZlTitle;
    TextView mTvZlTimeAndSize;
    RelativeLayout mRlMusicPanelContainer;
    TextView mTvZlIntro;
    private View headView;
    private Context mContext;
    private View footerView;
    private RecyclerView mRecyclerView;
    private PptAdapter mAdapter;
    private String prefix;
    private WebView webView;


    public ZLImageTextHeadUtil(Context context) {
        mContext = context;
    }

    public View getHeadView() {
        if (headView == null) {
            headView = LayoutInflater.from(mContext).inflate(R.layout.headerview_text_img, null);
            mIvHeadviewImg = ViewUtil.getView(headView,R.id.iv_headview_img);
            mIvHeadviewBack = ViewUtil.getView(headView,R.id.iv_headview_back);
            webView = ViewUtil.getView(headView, R.id.webView);
            mTvHeadviewTitle = ViewUtil.getView(headView,R.id.tv_headview_title);
            mTvHeadviewName = ViewUtil.getView(headView,R.id.tv_headview_name);
            mRecyclerView = ViewUtil.getView(headView,R.id.recyclerView);
            mIvZlCover = ViewUtil.getView(headView,R.id.iv_headview_music_panel_zl__cover);
            mPlayPauseView = ViewUtil.getView(headView,R.id.playPauseView);
            mIvZlDownload = ViewUtil.getView(headView,R.id.iv_headview_music_panel_zl_download);
            mTvZlTitle = ViewUtil.getView(headView,R.id.tv_headview_music_panel_zl_title);
            mTvZlTimeAndSize = ViewUtil.getView(headView,R.id.tv_headview_music_panel_zl_time_and_size);
            mRlMusicPanelContainer = ViewUtil.getView(headView,R.id.rl_headview_music_panel_zl_music_panel_container);
            mTvZlIntro = ViewUtil.getView(headView,R.id.tv_zl_intro);
            ViewUtil.getView(headView, R.id.tv_head_sjk_subscribe_edit_comment).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        RxManagerUtil.post(AppConstant.RXTag.SUBSCRIBE_COMMENT_FRAGMENT_SHOW_OR_HIDE, true);
                }
            });
        }
        return headView;
    }
    public void bindTarget(IRecyclerView iRecyclerView) {
        if (iRecyclerView != null && headView != null) {
            iRecyclerView.setHeadView(headView);
        }
    }

    public void initWebView(String courseHtml) {
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

        if(courseHtml!=null&courseHtml.length()>0){
                        webView.loadUrl(courseHtml);
                        webView.setVisibility(View.VISIBLE);
                        mTvZlIntro.setVisibility(View.GONE);
            }else {


            }
    }
    public void setData(MediaDetailBean.DataBean data, RecordListBean.DataBean recordData, ArrayList<String> ppt, String prefix) {
        ImageLoaderUtils.display(mIvHeadviewImg,recordData.getPic());
        ImageLoaderUtils.displayCircle(mContext,mIvZlCover,recordData.getPic());
        ViewUtil.setText(mTvHeadviewTitle,recordData.getTitle());
        ViewUtil.setText(mTvZlTitle,recordData.getTitle());
        ViewUtil.setText(mTvHeadviewName, data.getTeacherName());
        ViewUtil.setText(mTvZlIntro,data.getDetail());
        this.prefix=prefix;
        if(ppt!=null){
            mRecyclerView.setLayoutManager( new LinearLayoutManager(mContext));
//如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
            mRecyclerView.setHasFixedSize(true);
//创建并设置Adapter
            mAdapter = new PptAdapter(ppt,mContext,prefix);
            mRecyclerView.setAdapter(mAdapter);
        }

    }
    public void setVideo() {
        mRlMusicPanelContainer.setVisibility(View.GONE);
    }
    public void setOnClickListener(View.OnClickListener listener){
        if (listener!=null && headView!=null){
            mIvHeadviewBack.setOnClickListener(listener);
            mIvZlDownload.setOnClickListener(listener);
            mPlayPauseView.setOnClickListener(listener);
        }
    }

    public void changeState(String musicUrl) {
        if(MyAPP.mServiceManager.currentPlayUrl!=null){
            if(MyAPP.mServiceManager.currentPlayUrl.equals(musicUrl) ){
                mPlayPauseView.toggle(MyAPP.mServiceManager.isPlaying());
            }
        }
    }

    public void hideMusicPanel() {


        mRlMusicPanelContainer.setVisibility(View.GONE);
        mIvHeadviewImg.setVisibility(View.GONE);
        mIvHeadviewBack.setVisibility(View.GONE);
    }   private TextView tvFooter;
    public void bindFooterView(IRecyclerView iRecyclerView, CharSequence text, final String targetId, final String targetKey){
        if (footerView==null){
            footerView =  LayoutInflater.from(mContext).inflate(R.layout.footerview_text,null);
            tvFooter = ViewUtil.getView(footerView,R.id.tv_title);
            footerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ZhuanLanCommentActivity.startAction(mContext,targetKey,targetId);
                }
            });
        }
        tvFooter.setText(text);
        iRecyclerView.setFooterView(footerView);
    }
}
