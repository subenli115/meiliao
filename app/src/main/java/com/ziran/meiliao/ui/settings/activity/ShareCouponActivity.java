package com.ziran.meiliao.ui.settings.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.FileUtil;
import com.ziran.meiliao.entry.UserInfo;
import com.ziran.meiliao.ui.base.ShareActivity;
import com.ziran.meiliao.ui.bean.GainCouponBean;
import com.ziran.meiliao.ui.bean.ShareBean;
import com.ziran.meiliao.ui.bean.ShareCouponBean;
import com.ziran.meiliao.ui.settings.contract.ShareCouponContract;
import com.ziran.meiliao.ui.settings.model.ShareCouponModel;
import com.ziran.meiliao.ui.settings.presenter.ShareCouponPresenter;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.NewQRCodeUtils;
import com.ziran.meiliao.utils.StringUtils;
import com.ziran.meiliao.widget.MoreImageView;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 优惠劵界面
 * Created by Administrator on 2017/1/4.
 */

public class ShareCouponActivity extends ShareActivity<ShareCouponPresenter, ShareCouponModel> implements ShareCouponContract.View, 
        MoreImageView.OnMoreImageViewClickListener {

    @Bind(R.id.tv_getcoupon_ewm)
    ImageView tvGetcouponEwm;
    @Bind(R.id.iv_getcoupon_bg)
    ImageView iv_getcoupon_bg;
    @Bind(R.id.iv_getcoupon_1)
    ImageView ivGetcoupon1;
    @Bind(R.id.iv_getcoupon_2)
    ImageView ivGetcoupon2;
    @Bind(R.id.iv_getcoupon_3)
    ImageView ivGetcoupon3;
    @Bind(R.id.rl_getcoupon_screenshot)
    RelativeLayout rlGetcouponScreenshot;
    @Bind(R.id.rl_getcoupon_ok)
    RelativeLayout rlGetcouponOk;
    @Bind(R.id.tv_getcoupon_share_text)
    TextView tvGetcouponShareText;
    @Bind(R.id.ll_getcoupon_share)
    LinearLayout llGetcouponShare;
    @Bind(R.id.iv_getcoupon_userhead)
    ImageView ivGetcouponUserhead;
    @Bind(R.id.tv_getcoupon_username)
    TextView tvGetcouponUsername;
    @Bind(R.id.tv_getcoupon_price)
    TextView tvGetcouponPrice;
    @Bind(R.id.tv_getcoupon_prompt)
    TextView tvPrompt;
    @Bind(R.id.rl_screeshot_layout)
    View rl_layout;
    @Bind(R.id.iv_getcoupon_exist_hb)
    ImageView ivGetCouponHb;
    @Bind(R.id.moreImageView)
    MoreImageView mMoreImageView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_me_gain_coupon;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        UserInfo userInfo = MyAPP.getUserInfo();
        if (userInfo != null) {
            tvGetcouponUsername.setText(StringUtils.format(getString(R.string.hi_im), userInfo.getNickName()));
            ImageLoaderUtils.displayCircle(this, ivGetcouponUserhead, StringUtils.headImg());
        }
        mMoreImageView.setOnMoreImageViewClickListener(this);
        mPresenter.getGoinCoupon(MapUtils.getDefMap(true));
    }

    private String filePath;

    @OnClick({R.id.iv_getcoupon_ok, R.id.iv_getcoupon_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_getcoupon_ok:
                rlGetcouponOk.setVisibility(View.GONE);
                llGetcouponShare.setVisibility(View.VISIBLE);
                break;
            case R.id.iv_getcoupon_close:
                finish();
                break;
        }
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {
        super.onResult(share_media);
        finish();
    }

    private SHARE_MEDIA shareMedia;

    private void share(final SHARE_MEDIA shareMedia) {
        if (resultData == null) return;
        if (resultData.isExist()) {
            ShareUtil.shareWeb(ShareCouponActivity.this, shareMedia, resultData.getShareDescript(), null, resultData.getSharePagePath()+"&isShare=1",
                    resultData.getShareTitle(), resultData.getShareDescript());
            return;
        }
        filePath = ViewUtil.myShot(rlGetcouponScreenshot);
        this.shareMedia = shareMedia;
        mPresenter.postContentAndFiles(MapUtils.getOnlyCan("number", resultData.getNumber()), FileUtil.str2File(filePath));
    }

    private GainCouponBean.DataBean resultData;


    private void loadImg(ImageView imageView, int i, List<String> pics) {
        try {
            ImageLoaderUtils.display(this, imageView, pics.get(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onStart(SHARE_MEDIA share_media) {
//        startProgressDialog("加载中");
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

    @Override
    public void showUploadFile(ShareCouponBean result) {
        if (EmptyUtils.isNotEmpty(result.getData())) {
            ShareBean data = result.getData();
            ShareUtil.shareWeb(ShareCouponActivity.this, shareMedia, data.getShareDescript(), filePath, data.getSharePagePath(), data
                    .getShareTitle(), data.getShareDescript());
        }
    }

    @Override
    public void showGoinCoupon(GainCouponBean result) {
        if (EmptyUtils.isNotEmpty(result.getData())) {
            resultData = result.getData();
            if (resultData.isExist()) {
                ImageLoaderUtils.displayTager(this, ivGetCouponHb, resultData.getShareImgPath(), R.mipmap.ic_loading_square_small);
                rl_layout.setVisibility(View.GONE);
                ivGetCouponHb.setVisibility(View.VISIBLE);
                tvPrompt.setText(resultData.getMsg());
                return;
            }
            ivGetCouponHb.setVisibility(View.GONE);
            ImageLoaderUtils.display(this, iv_getcoupon_bg, resultData.getBg());
            loadImg(ivGetcoupon1, 0, resultData.getPics());
            loadImg(ivGetcoupon2, 1, resultData.getPics());
            loadImg(ivGetcoupon3, 2, resultData.getPics());
            tvGetcouponPrice.setText(StringUtils.format("%s元", resultData.getFaceValue()));
            tvPrompt.setText(resultData.getMsg());
            Bitmap qrImage = NewQRCodeUtils.createQRImage(resultData.getQrcode(), tvGetcouponEwm);
            if (qrImage != null) {
                tvGetcouponEwm.setImageBitmap(qrImage);
            }
        }
    }
}
