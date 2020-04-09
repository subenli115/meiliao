package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.widget.MoreImageView;
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


public class SimpleSharePopupWindow extends BasePopupWindow {
    private String shareTitle;
    private String shareDescript;
    private String shareUrl;
    private String sharePic;

    public SimpleSharePopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_simple_share;
    }

    @Override
    protected void initViews(View contentView) {
        MoreImageView moreImageView = ViewUtil.getView(contentView, R.id.moreImageView);

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
        });
        setOnClickListener(R.id.background);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    private void share(final SHARE_MEDIA shareMedia) {
        LogUtils.logd("shareMedia");
        ShareUtil.shareWeb(mContext, shareMedia, getShareDescript(), null, getShareUrl(), getShareTitle(), getShareDescript());
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public void setShareUrlAddOne(String shareUrl) {
        this.shareUrl = shareUrl + "&isShare=1";
    }

    public String getShareTitle() {
        return EmptyUtils.isEmpty(shareTitle) ? "" : shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareDescript() {
        return EmptyUtils.isEmpty(shareDescript) ? "" : shareDescript;
    }

    public void setShareDescript(String shareDescript) {
        this.shareDescript = shareDescript;
    }

    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
    }
}