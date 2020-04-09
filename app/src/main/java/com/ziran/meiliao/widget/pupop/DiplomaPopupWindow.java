package com.ziran.meiliao.widget.pupop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.ui.bean.StudyFinishBean;
import com.ziran.meiliao.utils.HtmlUtil;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${专栏学习毕业证弹窗}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class DiplomaPopupWindow extends BasePopupWindow {

    private View flContentContainer;
    private TextView tvUserName;
    private ImageView ivAvatar;
    private TextView tvIntro;
    private TextView tvPrice;

    public DiplomaPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_diploma;
    }

    @Override
    protected void initViews(View contentView) {
        flContentContainer = getView(R.id.fl_content);
        tvUserName = getView(R.id.tv_user_name);
        ivAvatar = getView(R.id.iv_user_avatar);
        tvIntro = getView(R.id.tv_intro);
        tvPrice = getView(R.id.tv_price);
//        MoreImageView moreImageView = getView(R.id.moreImageView);
        setOnClickListener(R.id.iv_popup_close);
        View view = ViewUtil.getView(contentView, R.id.moreImageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform);
                dismiss();
            }
        });
//        moreImageView.setOnMoreImageViewClickListener(new MoreImageView.OnMoreImageViewClickListener() {
//            @Override
//            public void onClick(int index) {
//                switch (index) {
//                    case 0:
//                        share(SHARE_MEDIA.WEIXIN.toSnsPlatform().mPlatform);
//                        break;
//                    case 1:
//                        share(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform);
//                        break;
//                    case 2:
//                        share(SHARE_MEDIA.SINA.toSnsPlatform().mPlatform);
//                        break;
//                    case 3:
//                        share(SHARE_MEDIA.QQ.toSnsPlatform().mPlatform);
//                        break;
//                    case 4:
//                        share(SHARE_MEDIA.QZONE.toSnsPlatform().mPlatform);
//                        break;
//                }
//                dismiss();
//            }
//        });
    }

    private void share(SHARE_MEDIA shareMedia) {
        String path = ViewUtil.myShot(flContentContainer);
        ShareUtil.shareWeb(mContext, shareMedia, getShareDescript(), path, getRealUrl(), getShareTitle(), getShareDescript());
    }

    private String shareTitle;
    private String shareDescript;
    private String shareUrl;
    private String sharePic;

    @NonNull
    private String getRealUrl() {
        if (getShareUrl().contains("?")) {
            return getShareUrl() + "&isShare=1";
        }
        return getShareUrl() + "?isShare=1";
    }

    public String getSharePic() {
        return sharePic;
    }

    public void setSharePic(String sharePic) {
        this.sharePic = sharePic;
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

    public void onClick(View view) {
        dismiss();
    }

    public void setData(StudyFinishBean.DataBean bean, int price, String intro) {
        ViewUtil.setText(tvUserName, bean.getNickName());
        ImageLoaderUtils.displayCircle(mContext, ivAvatar, bean.getHeadImg());

        if (price>0){
            ViewUtil.setText(tvIntro, HtmlUtil.format("恭喜您完成《%s》的学习，并获得全年地面工作坊价值", intro));
            ViewUtil.setText(tvPrice, HtmlUtil.format("%d元 优惠券", price));
            tvPrice.setVisibility(View.VISIBLE);
        }else{
            ViewUtil.setText(tvIntro, HtmlUtil.format("恭喜您完成《%s》的学习", intro));
            tvPrice.setVisibility(View.GONE);
        }
        setShareUrl(bean.getShareUrl());
        setShareDescript(bean.getShareDescript());
        setShareTitle(bean.getShareTitle());
        setSharePic(bean.getSharePic());
        show();
    }
}