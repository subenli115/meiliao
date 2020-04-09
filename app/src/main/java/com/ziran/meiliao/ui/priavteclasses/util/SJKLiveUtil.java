package com.ziran.meiliao.ui.priavteclasses.util;

import android.content.Context;

import com.ziran.meiliao.ui.priavteclasses.activity.HorizontalHistoryActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.HorizontalLiveActivity;
import com.ziran.meiliao.ui.priavteclasses.activity.VerticalLiveActivity;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/7/4 17:00
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/7/4$
 * @updateDes ${TODO}
 */

public class SJKLiveUtil {
    public static void startActivity(Context context, String courseID, int tag, int status) {
//        LogUtils.logd("courseID"+courseID + "  tag"+tag +"  status"+status);
        if (tag == 1) { // 直播
            //需要修改
            if (0 == status) {
                HorizontalLiveActivity.startAction(context, courseID, 1);
            } else {
                VerticalLiveActivity.startAction(context, courseID, 1);
            }
        } else  if (tag ==0){ //历史
            if (0 == status) {  // 电脑
                HorizontalHistoryActivity.startAction(context, courseID, 0);
            } else {  //手机
                VerticalLiveActivity.startAction(context, courseID, 0);
            }
        }else if (tag ==2){ //预告

        }
    }
}
