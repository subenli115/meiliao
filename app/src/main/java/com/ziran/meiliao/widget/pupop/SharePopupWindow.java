package com.ziran.meiliao.widget.pupop;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.okhttp.OkHttpClientManager;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.AlbumBean;
import com.ziran.meiliao.ui.bean.MediaAndTextBean;
import com.ziran.meiliao.ui.bean.NewMediaAndTextBean;
import com.ziran.meiliao.ui.bean.PracticeHeadBean;
import com.ziran.meiliao.ui.bean.PracticeThreeDetailCheckBean;
import com.ziran.meiliao.ui.bean.ShareBean;
import com.ziran.meiliao.ui.bean.SpceColumnBean;
import com.ziran.meiliao.widget.MoreImageView;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.ShareUtil;

import java.util.Map;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/7 11:33
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/7$
 * @updateDes ${TODO}
 */


public class SharePopupWindow extends BasePopupWindow {

    private static String zhibourl;
    private View contentViewPop;
    private View background;

    public SharePopupWindow(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.popupw_share;
    }

    @Override
    protected void initViews(View contentView) {
        touchDismiss(R.id.background);
        setOnClickListener(R.id.btn_cancel);
        animView = getView(R.id.layout_custom_container);
        background = getView(R.id.background);
        animView.setClickable(false);
        MoreImageView moreImageView = getView(R.id.moreImageView);
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
                dismiss();
            }
        });
        setDurtion(400);
    }
    @Override
    public void onClick(View v) {
        dismiss();
    }


    @Override
    protected int getAnimStyle() {
        return R.style.SimpleAnimation;
    }

    private String shareTitle;
    private String shareDescript;
    private String shareUrl;

    private void share(SHARE_MEDIA shareMedia) {
        ShareUtil.shareWeb1(mContext, shareMedia, getShareDescript(), sharePic, getRealUrl(), getShareTitle(), getShareDescript());
        if (mCallBack!=null)mCallBack.call();
    }


    private ViewUtil.BaseCallBack mCallBack ;

    public void setCallBack(ViewUtil.BaseCallBack callBack) {
        mCallBack = callBack;
    }

    @NonNull
    private String getRealUrl() {
        if (!isNeedRealUrl()){
            return getShareUrl();
        }
        if (getShareUrl().contains("?")) {
            return getShareUrl() + "&isShare=1";
        }
        return getShareUrl() + "?isShare=1";
    }

    public String getShareTitle() {
        return shareTitle;
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

    public String getShareUrl() {
        return shareUrl;
    }
    private boolean needRealUrl = true;

    public boolean isNeedRealUrl() {
        return needRealUrl;
    }

    public void setNeedRealUrl(boolean needRealUrl) {
        this.needRealUrl = needRealUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    private String sharePic;

    public void setSharePic(String picture) {
        this.sharePic = picture;
    }

    public static void showPopup(Context activity, SharePopupWindow popupWindow, ShareBean shareBean) {
        showPopup(activity, popupWindow, shareBean.getShareTitle(), shareBean.getShareDescript(), shareBean.getShareUrl(),shareBean.getSharePic());
    }
    public static void showPopup(Context activity, SharePopupWindow popupWindow, ShareBean shareBean,String zhibo) {

        showPopup1(activity, popupWindow, shareBean.getShareTitle(), shareBean.getShareDescript(), shareBean.getShareUrl(),shareBean.getSharePic(),zhibo);
    }

    private static void showPopup1(Context activity, SharePopupWindow popupWindow, String shareTitle, String shareDescript, String shareUrl, String sharePic, String zhibo) {

        if (popupWindow == null) {
            popupWindow = new SharePopupWindow(activity);
            LogUtils.logd("popupWindow:" + popupWindow + " shareTitle:" + shareTitle + "   shareDescript" + shareDescript + "  shareUrl:"
                    + shareUrl+"pic"+sharePic+"            "+zhibo);

        }
        zhibourl=zhibo;
        popupWindow.setSharePic(sharePic);
        popupWindow.setShareTitle(shareTitle);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE); //解决pop被底部导航遮挡的问题
        popupWindow.setShareUrl(shareUrl);
        popupWindow.setShareDescript(shareDescript);
        if (activity instanceof Activity) {
            popupWindow.show((Activity) activity);
        } else {
            popupWindow.show();
        }
    }

    public static void showPopup(Context activity, SharePopupWindow popupWindow, PracticeHeadBean.DataBean musicEntry) {
        showPopup(activity, popupWindow, musicEntry.getShareTitle(), musicEntry.getShareDescribe(), musicEntry.getShareUrl(),musicEntry.getSharePic());
    }
    public static void showPopup(Context activity, SharePopupWindow popupWindow, AlbumBean ab) {
        showPopup(activity, popupWindow, ab.getShareTitle(), ab.getShareDescript(), ab.getShareUrl(),ab.getSharePic());
    }
    public static void showPopup(Context activity, SharePopupWindow popupWindow, MusicEntry musicEntry) {
        showPopup(activity, popupWindow, musicEntry.getShareTitle(), musicEntry.getShareDescript(), musicEntry.getShareUrl(),musicEntry.getSharePic());
    }
    public static void showPopup(Context activity, SharePopupWindow popupWindow, MediaAndTextBean.DataBean data) {
        showPopup(activity, popupWindow, data.getShareTitle(), data.getShareDescript(), data.getShareUrl(),data.getSharePic());
    }
    public static void showPopup(Context activity, SharePopupWindow popupWindow, NewMediaAndTextBean.DataBean data) {
        showPopup(activity, popupWindow, data.getShareTitle(), data.getShareDescript(), data.getShareUrl(),data.getSharePic());
    }
    public static void showPopup(Context activity, SharePopupWindow popupWindow, PracticeThreeDetailCheckBean.DataBean data) {
        showPopup(activity, popupWindow, data.getShareTitle(), data.getShareDescribe(), data.getShareUrl(),data.getSharePic());
    }
    public static void showPopup(Context activity, SharePopupWindow popupWindow, SpceColumnBean.DataBean data) {
        showPopup(activity, popupWindow, data.getShareTitle(), data.getShareDescript(), data.getShareUrl(),data.getSharePic());
    }

    public static void showPopup(Context activity, SharePopupWindow popupWindow, String shareTitle, String shareDescript, String shareUrl,String sharePic) {
        if (popupWindow == null) {
            popupWindow = new SharePopupWindow(activity);
            LogUtils.logd("popupWindow:" + popupWindow + " shareTitle:" + shareTitle + "   shareDescript" + shareDescript + "  shareUrl:"
                    + shareUrl+"pic"+sharePic);
        }
        popupWindow.setSharePic(sharePic);
        popupWindow.setShareTitle(shareTitle);
        popupWindow.setShareUrl(shareUrl);
        popupWindow.setShareDescript(shareDescript);
        if (activity instanceof Activity) {
            popupWindow.show((Activity) activity);
        } else {
            popupWindow.show();
        }
    }

    public void setShareBean(ShareBean shareBean) {
        setShareTitle(shareBean.getShareTitle());
        setShareDescript(shareBean.getShareDescript());
        setShareUrl(shareBean.getShareUrl());
    }

    public void openSaveShare(final String api, final Map map, final int isShare) {
        setCallBack(new ViewUtil.BaseCallBack() {
            @Override
            public void call() {
                Log.e("zhibourl",""+zhibourl);
                if (isShare==1) return;
                OkHttpClientManager.postAsync(api, map, new NewRequestCallBack<Result   >(Result.class) {
                    @Override
                    protected void onSuccess(Result result) {
//                        Log.e("zhibourl",""+zhibourl);
//                        if(zhibourl!=null&&zhibourl.length()>0){
//                            DefWebActivity.startAction(mContext,zhibourl,"");
//                        }
                    }
                });
            }
        });
    }
}