package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class ShareVideoCouponPopupWindow extends BasePopupWindow {

    public ShareVideoCouponPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_share_video_coupon;
    }

    @Override
    protected void initViews(View contentView) {
        setOnClickListener(R.id.iv_qq);
        setOnClickListener(R.id.iv_wechat);
        setOnClickListener(R.id.background);
    }

    @Override
    public void onClick(View v) {
        dismiss();
        switch (v.getId()) {
            case R.id.iv_qq:
                share(SHARE_MEDIA.QQ);
                break;
            case R.id.iv_wechat:
                share(SHARE_MEDIA.WEIXIN);
                break;
        }
    }

    private String shareTitle;
    private String shareDescript;
    private String shareUrl;

    private void share(SHARE_MEDIA shareMedia) {
        ShareUtil.shareWeb(mContext, shareMedia, getShareDescript(), null, getShareUrl() + "&isShare=1", getShareTitle(), getShareDescript());
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareDescript() {
        return shareDescript;
    }

    public void setShareDescript(String shareDescript) {
        this.shareDescript = shareDescript;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }
}