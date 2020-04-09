package com.ziran.meiliao.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.IConstants;
import com.ziran.meiliao.service.MusicControl;
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
        if (intent.getAction().equals(MusicControl.BROADCAST_NAME)) {
            int playState = intent.getIntExtra(PLAY_STATE_NAME, MPS_NOFILE);
            String url = intent.getStringExtra(PLAY_MUSIC_URL);
            switch (playState) {
                case MPS_COMPLETION:
                    state = -2;
                    break;
                case MPS_PREPARE_OTHER:
                    state = 8;
                    break;
                case MPS_PLAYING:
                    if (state == 8) {
                        RxManagerUtil.post(AppConstant.RXTag.EXERCISE_PLAY, HandlerUtil.obj(ProgressUtil.WHAT_SEEK_TO,url));
                        state = -2;
                    }
            }
        }
    }


}
