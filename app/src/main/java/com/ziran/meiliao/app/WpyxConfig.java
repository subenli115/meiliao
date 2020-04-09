package com.ziran.meiliao.app;

import android.graphics.Point;
import android.os.Bundle;

import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.entry.MusicEntry;
import com.ziran.meiliao.ui.bean.ResBean;
import com.ziran.meiliao.ui.bean.UserVipV1Bean;
import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;
import com.ziran.meiliao.ui.priavteclasses.bean.ZhuanLanData;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class WpyxConfig {

    public static String portVersion="1";
    /**
     * 当前用户的手机
     */
    private static String phone;
    /**
     * 需要升级的版本
     */
    private static String lastVersion;
    /**
     * 是否检查版本更新
     */
    private static boolean isCheckUpdateVersion;

    /**
     * 推广大使的对象
     */
    private static ResBean sResBean;

    private static List<UserVipV1Bean.DataBean> sUserVipV1Bean;
    private static ZhuanLanData sZhuanLanData;
    private static boolean sMusicPanelShow = false;
    private static Point sStartPoint;
    private static String sAdvertDetail;
    private static CharSequence sAppDetail;
    private static List<ZhuanLanBigInBean.DataBean.DirBean> msZhuanLanBigData;
    private static String sHotWord;
    private static boolean isBuy;
    public static String shareUrl;
    public static String pic;
    private static String mdownUrl;
    private static Bundle bundle;
    private static String url="";

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        WpyxConfig.url = url;
    }

    public static Bundle getBundle() {
        return bundle;
    }

    public static void setBundle(Bundle bundle) {
        WpyxConfig.bundle = bundle;
    }

    public static boolean isIsBuy(boolean buy) {
        return isBuy;
    }
    public static void setIsBuy(boolean isBuy) {
        WpyxConfig.isBuy = isBuy;
    }
    public static boolean isDowning=true;

    public static String getSubscriptionId() {
        return subscriptionId;
    }

    public static void setSubscriptionId(String subscriptionId) {
        WpyxConfig.subscriptionId = subscriptionId;
    }

    public static String subscriptionId;
    public static MusicEntry getMusicEntry() {
        return musicEntry;
    }

    public static void setMusicEntry(MusicEntry musicEntry) {
        WpyxConfig.musicEntry = musicEntry;
    }

    public static MusicEntry musicEntry;

    public static List<ZhuanLanBigInBean.DataBean.DirBean> getsZhuanLanBigData() {
        return msZhuanLanBigData;
    }
    public static void setsZhuanLanBigData(List<ZhuanLanBigInBean.DataBean.DirBean> sZhuanLanBigData) {
        msZhuanLanBigData = sZhuanLanBigData;
    }



    public static String getLastVersion() {
        return lastVersion;
    }

    public static void setLastVersion(String lastVersion) {
        WpyxConfig.lastVersion = lastVersion;
    }

    public static String getPhone() {
        return phone;
    }


    public static String setPhone(String phone) {
        WpyxConfig.phone = phone;
        return phone;
    }

    public static boolean isCheckUpdate() {
        return isCheckUpdateVersion;
    }

    public static void setIsCheckUpdateVersion(boolean isCheckUpdateVersion) {
        WpyxConfig.isCheckUpdateVersion = isCheckUpdateVersion;
    }

    public static void setResBean(ResBean result) {
        WpyxConfig.sResBean = result;
    }

    public static ResBean getResBean() {
        return sResBean;
    }

    public static String getUserAgreement() {

        return sResBean == null ? "" : sResBean.getData() == null ? "" : sResBean.getData().getProtocol();
    }


    public static List<UserVipV1Bean.DataBean> getUserVipV1Bean() {
        return sUserVipV1Bean;
    }

    public static void setUserVipV1Bean(List<UserVipV1Bean.DataBean> userVipV1Bean) {
        sUserVipV1Bean = userVipV1Bean;
    }

    public static String getNofityLianXi() {
        return MyAPP.getUserInfo().getPhone() + AppConstant.SPKey.NOTIFY_LIAN_XI;
    }

    public static void setZhuanLanData(ZhuanLanData zhuanLanData) {
        sZhuanLanData = zhuanLanData;
    }

    public static ZhuanLanData getZhuanLanData() {
        return sZhuanLanData;
    }

    public static void setMusicPanelHide(boolean musicPanelShow) {
        sMusicPanelShow = musicPanelShow;
    }

    public static boolean isMusicPanelHide() {
        return sMusicPanelShow;
    }

    private static boolean isUpdateNotes = false;

    public static void setUpdateNotes(boolean update) {
        isUpdateNotes = update;
    }

    public static boolean isUpdateNotes() {
        return isUpdateNotes;
    }

    public static void setStartPoint(Point startPoint) {
        sStartPoint = startPoint;
    }

    public static Point getStartPoint() {
        return sStartPoint;
    }

    public static boolean checkBuy(String targetId) {
        try {
            if(isBuy||msZhuanLanBigData.get(0).isSt()==true){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void setAdvertDetail(String advertDetail) {
        sAdvertDetail = advertDetail;
    }

    public static String getAdvertDetail() {
        return sAdvertDetail;
    }

    public static CharSequence getAppDetail() {
        return sAppDetail;
    }

    public static void setAppDetail(CharSequence appDetail) {
        sAppDetail = appDetail;
    }

    public static void setHotWord(String hotWord) {
        sHotWord = hotWord;
    }

    public static String getHotWord() {
        return sHotWord;
    }

    public static void setDownUrl(String downUrl) {
        mdownUrl = downUrl;
    }
    public static String getDownUrl(){
        return mdownUrl;
    }

}
