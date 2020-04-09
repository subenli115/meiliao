package com.ziran.meiliao.ui.settings.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.UserCenterShareBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.NewQRCodeUtils;
import com.ziran.meiliao.widget.MoreImageView;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.Bind;
import butterknife.OnClick;

import static com.ziran.meiliao.constant.ApiKey.USERCENTER_SHAREURL;

/**
 *
 * 邀请好友
 */
public class InviteFriendsActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract.ActionView<Result, Result> {



    @Bind(R.id.ntb)
    public NormalTitleBar ntb;
    @Bind(R.id.arl_first)
    AutoRelativeLayout arlBottom;
    @Bind(R.id.iv_share_exercise_close)
    ImageView ivClose;
    @Bind(R.id.iv_code)
    ImageView ivCode;

    @Bind(R.id.arl_main)
    AutoRelativeLayout arlMain;
    @Bind(R.id.moreImageView)
    MoreImageView moreImageView;
    private SHARE_MEDIA mShareMedia;
    private UserCenterShareBean.DataBean data;

    @Override
    public int getLayoutId() {
        return R.layout.ac_share_invitation_friend;

    }


    @Override
    public void initPresenter() {
            mPresenter.setVM(this, mModel);
    }



    public static void startAction(Context mContext) {
        Intent intent = new Intent(mContext, InviteFriendsActivity.class);
        mContext.startActivity(intent);
    }


    @OnClick({ R.id.tv_yq,R.id.iv_share_exercise_close})

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_yq:  //邀请好友
                ivClose.setVisibility(View.VISIBLE);
                arlBottom.setVisibility(View.VISIBLE);
                arlMain.setAlpha(0.5f);
                break;
            case R.id.iv_share_exercise_close:
                ivClose.setVisibility(View.INVISIBLE);
                arlBottom.setVisibility(View.INVISIBLE);
                arlMain.setAlpha(1.0f);
                break;

        }
    }

    @Override
    public void initView() {
        ntb.setVerLineVisiable(false);
        ntb.setNewTitleText("邀请好友得奖励");
        ntb.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InvitationRecordActivity.startAction(mContext);
            }
        });
        ntb.setBackGroundColor(R.color.transparent);


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
        ShareUtil.addCallBack(mUMShareListener);
        mPresenter.getData(USERCENTER_SHAREURL,MapUtils.getDefMap(true), UserCenterShareBean.class);
    }
    private UMShareListener mUMShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            ivClose.setVisibility(View.INVISIBLE);
            arlBottom.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };
    private void share(SHARE_MEDIA shareMedia) {
        mShareMedia=shareMedia;
        ShareUtil.shareWeb1(mContext, mShareMedia, data.getShareDescribe(), data.getSharePicture(), data.getShareUrl(), data.getShareTitle(), data.getShareDescribe());
    }
    /**
     * 在二维码中间添加Logo图案
     */
    private Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }

        if (logo == null) {
            return src;
        }

        // 获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }

        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        // logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);

            canvas.save();
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        return bitmap;
    }
    @Override
    public void returnData(Result result) {


        UserCenterShareBean bean = (UserCenterShareBean) result;
         data = bean.getData();
        Bitmap qrImage = NewQRCodeUtils.createQRImage(data.getShareUrl(), ivCode);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Bitmap bitmap1 = addLogo(qrImage, bitmap);

        if (qrImage != null) {
            ivCode.setImageBitmap(bitmap1);
        }
    }

    @Override
    public void returnAction(Result result) {

    }

}
