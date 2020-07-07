package com.ziran.meiliao.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.IConstants;
import com.ziran.meiliao.utils.HandlerUtil;
import com.ziran.meiliao.utils.ProgressUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/4/15 10:16
 * @des ${音乐播放状态广播}
 * @updateAuthor $Author$
 * @updateDate 2017/4/15$
 * @updateDes ${TODO}
 */

public class MusicPlayStateBroadcast extends BroadcastReceiver implements IConstants{
    int state = -2;
    @Override
    public void onReceive(Context context, Intent intent) {
    }


}
