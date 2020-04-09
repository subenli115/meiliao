package com.umeng.soexample;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.media.UMusic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 友盟分享
 * Created by Administrator on 2017/3/13.
 */

public class ShareUtil {

    /**
     * "纯图片本地";
     * <p>
     * UMImage image = new UMImage(ShareActivity.this, "imageurl");//网络图片
     * UMImage image = new UMImage(ShareActivity.this, file);//本地文件
     * UMImage image = new UMImage(ShareActivity.this, R.drawable.xxx);//资源文件
     * UMImage image = new UMImage(ShareActivity.this, bitmap);//bitmap文件
     * UMImage image = new UMImage(ShareActivity.this, byte[]);//字节流
     *
     * @param context    分享的Activity
     * @param shareMedia
     */
    public static void shareImage(Context context, SHARE_MEDIA shareMedia, String text, File file, String imageUrl, Object thumb) {
        UMImage umImage = null;
        if (file != null) {
            umImage = new UMImage(context, file);
        } else if (!TextUtils.isEmpty(imageUrl)) {
            umImage = new UMImage(context, imageUrl);
        }
        umImage.setThumb(setThumb(context, thumb));
        share(context, shareMedia, text, umImage);
    }

    /**
     * 分享链接
     *
     * @param context    分享的Activity
     * @param shareMedia
     */
    public static void shareWeb(Context context, SHARE_MEDIA shareMedia, String text, Object thumb, String shareUrl,
                                String title, String description) {
        UMWeb umWeb = new UMWeb(shareUrl);
        umWeb.setTitle(title);
        umWeb.setThumb(setThumb(context, thumb));
        umWeb.setDescription(description);
        share(context, shareMedia, text, umWeb);
    }
    /**
     * 分享链接
     *
     * @param context    分享的Activity
     * @param shareMedia
     */
    public static void shareWeb1(Context context, SHARE_MEDIA shareMedia, String text, String thumb, String shareUrl,
                                String title, String description) {
        UMWeb umWeb = new UMWeb(shareUrl);
        umWeb.setTitle(title);
        umWeb.setThumb(setThumb(context, thumb));
        umWeb.setDescription(description);
        share(context, shareMedia, text, umWeb);
    }
    private static String filePath;

    private static UMImage setThumb(Context context, Object thumb) {
        if (thumb != null && thumb instanceof String) {
            filePath = thumb.toString();


            return new UMImage(context, thumb.toString());
        } else if (thumb != null && thumb instanceof Integer) {
            filePath = null;
            return new UMImage(context, Integer.parseInt(thumb.toString()));
        } else {
            filePath = null;
            return new UMImage(context, R.mipmap.ic_launcher);
        }
    }

    /**
     * "纯图片本地";
     *
     * @param context    分享的Activity
     * @param shareMedia
     */
    public static void shareMusic(Context context, SHARE_MEDIA shareMedia, String text, Object thumb, String musicUrl,
                                  String title, String description) {
        UMusic umWeb = new UMusic(musicUrl);
        umWeb.setTitle(title);  //音乐的标题
        umWeb.setThumb(setThumb(context, thumb));
        umWeb.setDescription(description); //音乐的描述
        share(context, shareMedia, text, umWeb);
    }

    /**
     * "纯图片本地";
     *
     * @param context    分享的Activity
     * @param shareMedia
     */
    public static void shareVideo(Context context, SHARE_MEDIA shareMedia, String text, Object thumb, String videoUrl,
                                  String title, String description) {
        UMVideo umWeb = new UMVideo(videoUrl);
        umWeb.setTitle(title);  //音乐的标题
        umWeb.setThumb(setThumb(context, thumb));
        umWeb.setDescription(description); //音乐的描述
        share(context, shareMedia, text, umWeb);
    }

    private static void share(Context context, SHARE_MEDIA shareMedia, String text, Object umWeb) {
        ShareAction shareAction = new ShareAction((Activity) context);
        if (!TextUtils.isEmpty(text)) {
            shareAction.withText(text);
        }
        if (umWeb != null) {
            if (umWeb instanceof UMWeb) {
                shareAction.withMedia((UMWeb) umWeb);
            }
            if (umWeb instanceof UMVideo) {
                shareAction.withMedia((UMVideo) umWeb);
            }
            if (umWeb instanceof UMusic) {
                shareAction.withMedia((UMusic) umWeb);
            }
            if (umWeb instanceof UMImage) {
                shareAction.withMedia((UMImage) umWeb);
            }
        }
        shareAction.setPlatform(shareMedia)
                .setCallback(shareListener).share();
    }

    public static void addCallBack(UMShareListener listener) {
        if (!umShareListeners.contains(listener))
            umShareListeners.add(listener);
    }

    public static void removeCallBack(UMShareListener umAuthListener) {
        umShareListeners.remove(umAuthListener);
    }

    private static List<UMShareListener> umShareListeners = new ArrayList<>();
    private static UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            if (umShareListeners.size() > 0) {
                for (int i = 0; i < umShareListeners.size(); i++) {
                    umShareListeners.get(i).onStart(platform);
                }
            }
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            if (umShareListeners.size() > 0) {
                for (int i = 0; i < umShareListeners.size(); i++) {
                    umShareListeners.get(i).onResult(platform);
                }
            }
            deleteFile();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (umShareListeners.size() > 0) {
                for (int i = 0; i < umShareListeners.size(); i++) {
                    umShareListeners.get(i).onError(platform, t);
                }
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            if (umShareListeners.size() > 0) {
                for (int i = 0; i < umShareListeners.size(); i++) {
                    umShareListeners.get(i).onCancel(platform);
                }
            }
            deleteFile();
        }
    };

    private static void deleteFile() {
        if (!TextUtils.isEmpty(filePath)){
            File file = new File(filePath);
            if (file.isFile() && file.exists()){
                file.delete();
            }
            filePath = null;
        }
    }


}
