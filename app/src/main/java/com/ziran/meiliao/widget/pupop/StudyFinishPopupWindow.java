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
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${专栏单个课程学习完成弹窗}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class StudyFinishPopupWindow extends BasePopupWindow {

    private View flContentContainer;
    private TextView tvUserName;
    private ImageView ivAvatar;
    private TextView tvIntro;
    private TextView tvTitle;
    private ImageView ivRqCode;

    public StudyFinishPopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_stydu_finish;
    }

    @Override
    protected void initViews(View contentView) {
        flContentContainer = getView(R.id.fl_content);
        tvUserName = getView(R.id.tv_user_name);
        ivAvatar = getView(R.id.iv_user_avatar);
        tvIntro = getView(R.id.tv_intro);
        tvTitle = getView(R.id.tv_title);
        ivRqCode = getView(R.id.iv_rq_code);
        setOnClickListener(R.id.iv_popup_close);
        View view = ViewUtil.getView(contentView, R.id.moreImageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(SHARE_MEDIA.WEIXIN_CIRCLE.toSnsPlatform().mPlatform);
                dismiss();
            }
        });

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

    public void setData(StudyFinishBean.DataBean bean) {
        ViewUtil.setText(tvUserName, bean.getNickName());
        ImageLoaderUtils.displayCircle(mContext, ivAvatar,bean.getHeadImg());
        ViewUtil.setText(tvIntro, bean.getTitle());
        ViewUtil.setText(tvTitle, bean.getCourseName());
        setShareUrl(bean.getShareUrl());
        setShareDescript(bean.getShareDescript());
        setShareTitle(bean.getShareTitle());
        setSharePic(bean.getSharePicture());
        ImageLoaderUtils.display(mContext,ivRqCode,bean.getQrCode());
        show();
    }



}