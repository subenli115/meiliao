package com.ziran.meiliao.ui.priavteclasses.util;

import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.priavteclasses.activity.SubscribeAudioActivity;

import rx.functions.Action1;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2018/1/9 17:39
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2018/1/9$
 * @updateDes ${TODO}
 */

public class ZLStudyUtil {
    private static String audioId;
    private static String subscriptionId;
    private static String intro;

    private static boolean isStudyFinish;
    public static void register(){
        RxManagerUtil.on(AppConstant.RXTag.MPS_COMPLETION, new Action1<String>() {
            @Override
            public void call(String aBoolean) {
                if (EmptyUtils.isNotEmpty(audioId)){
                    SubscribeAudioActivity.startAction(AppManager.getAppManager().currentActivity(),subscriptionId ,audioId);
                    isStudyFinish = true;
                    unRegister();
                }
            }
        });
    }

    public static boolean isStudyFinish() {
        return isStudyFinish;
    }

    public static void setIsStudyFinish(boolean isStudyFinish) {
        ZLStudyUtil.isStudyFinish = isStudyFinish;
    }

    public static void unRegister(){
        RxManagerUtil.clear();
        audioId = null;
    }
    public static String getAudioId() {
        return audioId;
    }

    public static String getIntro() {
        return intro;
    }

    public static void setIntro(String intro) {
        ZLStudyUtil.intro = intro;
    }

    public static String getSubscriptionId() {
        return subscriptionId;
    }

    public static void setSubscriptionId(String subscriptionId) {
        ZLStudyUtil.subscriptionId = subscriptionId;
    }

    public static void setAudioId(String audioId) {
        ZLStudyUtil.audioId = audioId;
        register();
    }

}
