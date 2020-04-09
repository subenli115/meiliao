package com.ziran.meiliao.ui.decompressionmuseum.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.LoadingDialog;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.base.ShareActivity;
import com.ziran.meiliao.ui.bean.AlbumGainBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.NewQRCodeUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.MoreImageView;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;

import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/3/4.
 */

public class ShareAlbumActivity extends ShareActivity implements MoreImageView.OnMoreImageViewClickListener {

    @Bind(R.id.iv_share_album_bg)
    ImageView ivShareAlbumBg;
    @Bind(R.id.tv_share_album_ewm)
    ImageView tvShareAlbumEwm;
    @Bind(R.id.tv_share_album_tltle)
    TextView tvShareAlbumUserName;
    @Bind(R.id.tv_share_album_share_title)
    TextView tvShareTitle;
    @Bind(R.id.tv_share_album_userHead)
    ImageView tvShareAlbumUserHead;
    @Bind(R.id.rl_share_album_screenshot)
    RelativeLayout rlShareAlbumScreenshot;
    @Bind(R.id.ll_share_album_share)
    LinearLayout llShareAlbumShare;
    @Bind(R.id.rl_share_album_layout)
    View rl_layout;
    @Bind(R.id.iv_share_album_share_shot)
    ImageView ivShareShot;

    @Bind(R.id.moreImageView)
    MoreImageView mMoreImageView;

    //截取屏幕返回的路径
    private String filePath;

    private String albumId = "";
    public static void startAction(Context context, Bundle bundle){
        Intent intent = new Intent(context,ShareAlbumActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_jyg_share_album;
    }

    @Override
    public void initPresenter() {
        if (mPresenter!=null)mPresenter.setVM(this,mModel);
    }

    private AlbumGainBean.DataBean dataBean;


    @Override
    public void initView() {
        Bundle bundle = getBundle(getIntent());
        if (bundle != null) {
            mMoreImageView.setOnMoreImageViewClickListener(this);
            dataBean = bundle.getParcelable(AppConstant.ExtraKey.ALBUM_GAIN);
            String albumTitle = bundle.getString(AppConstant.ExtraKey.ALBUM_TITLE);
            albumId = bundle.getString(AppConstant.SPKey.ALBUM_ID);
            if (dataBean != null) {
                if (dataBean.isExist()) {
                    rl_layout.setVisibility(View.GONE);
                    ivShareShot.setVisibility(View.VISIBLE);
                    ImageLoaderUtils.displayTager(this, ivShareShot, dataBean.getBg(), R.mipmap.ic_loading_square_small);
                } else {
                    ImageLoaderUtils.displayTager(this, ivShareAlbumBg, dataBean.getBg(), R.mipmap.ic_loading_square_small);
                    tvShareAlbumEwm.setImageBitmap(NewQRCodeUtils.createQRImage(dataBean.getQrcode(), tvShareAlbumEwm));
                    if (EmptyUtils.isNotEmpty(albumTitle)) {
                        tvShareTitle.setText(String.format(getString(R.string.csan_album_title), albumTitle));
                    }
                    ImageLoaderUtils.displayCircle(this, tvShareAlbumUserHead, StringUtils.headImg());
                    tvShareAlbumUserName.setText(String.format(getString(R.string.togeter_lister), MyAPP.getUserInfo().getNickName()));
                }
            }
        }
    }

    @OnClick(R.id.iv_share_album_close)
    public void onClick(View view) {
        finish();
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {
        isShowDialog = true;
//        startProgressDialog("加载中");
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        super.onResult(share_media);
        finish();
    }

    @Override
    public void onClick(int index) {
        switch (index) {
            case 0:
                share(SHARE_MEDIA.WEIXIN.toSnsPlatform().mPlatform);
                break;
            case 1:
                share(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform);
                break;
            case 2:
                share(SHARE_MEDIA.SINA.toSnsPlatform().mPlatform);
                break;
            case 3:
                share(SHARE_MEDIA.QQ.toSnsPlatform().mPlatform);
                break;
            case 4:
                share(SHARE_MEDIA.QZONE.toSnsPlatform().mPlatform);
                break;
        }
    }

    private void share(final SHARE_MEDIA shareMedia) {
        filePath = ViewUtil.myShot(rlShareAlbumScreenshot);
        final Map<String, String> number = MapUtils.getOnlyCan("number", dataBean.getNumber());
        if (EmptyUtils.isNotEmpty(albumId)) {
            number.put("albumId", albumId);
        }
        OkHttpClientManager.postContentAndFiles(ApiKey.ALBUM_UPLOAD_POSTER, number, FileUtil.str2File(filePath), new
                NewRequestCallBack<AlbumGainBean>(AlbumGainBean.class) {
                    @Override
                    public void onSuccess(AlbumGainBean result) {
                        if (EmptyUtils.isNotEmpty(result.getData())) {
                            AlbumGainBean.DataBean data = result.getData();
                            mRxManager.post(AppConstant.RXTag.GIVE_ALBUM,true);
                            LoadingDialog.cancelDialogForLoading();
                            ShareUtil.shareWeb(ShareAlbumActivity.this, shareMedia, data.getShareDescript(), null, data.getSharePagePath()+"&isShare=1",
                                    data.getShareTitle(), data.getShareDescript());
                        }
                        FileUtil.delete(filePath);
                    }
                });
    }
}
