package com.ziran.meiliao.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/4/18 17:18
 * @des     Android捕获监听Home键、最近任务列表键
 * @updateAuthor $Author$
 * @updateDate 2017/4/18$
 * @updateDes ${TODO}
 */

public class ClostSystemDialogReceiver  extends BroadcastReceiver{

    private final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
            String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);

            if (reason == null) return;

            // Home键
            if (reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
                Toast.makeText(context.getApplicationContext(), "按了Home键", Toast.LENGTH_SHORT).show();
            }

            // 最近任务列表键
            if (reason.equals(SYSTEM_DIALOG_REASON_RECENT_APPS)) {
                Toast.makeText(context.getApplicationContext(), "按了最近任务列表", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
