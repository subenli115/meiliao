package com.ziran.meiliao.ui.main.util;

import android.content.Context;

import com.ziran.meiliao.app.WpyxConfig;
import com.ziran.meiliao.common.commonutils.JsonUtils;
import com.ziran.meiliao.common.commonutils.SPUtils;
import com.ziran.meiliao.ui.bean.DailyMindBean;
import com.ziran.meiliao.utils.AlarmUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/28 14:12
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/28$
 * @updateDes ${TODO}
 */

public class DailyMindUtil {
    public static void initNotify(Context context){
        try {
            String js = SPUtils.getString(WpyxConfig.getNofityLianXi());
            DailyMindBean.DataBean dataBean = JsonUtils.fromJsonToType(js, DailyMindBean.DataBean.class);
            if (dataBean==null) return;
            if (dataBean.isAble()){
                AlarmUtil alarmUtil = new AlarmUtil();
                alarmUtil.setAlarm(context,-1, dataBean.getTimePoint(),-1,-1,AlarmUtil.FROM_NPTIFY_MO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
