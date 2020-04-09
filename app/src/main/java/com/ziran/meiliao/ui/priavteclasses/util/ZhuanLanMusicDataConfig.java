package com.ziran.meiliao.ui.priavteclasses.util;

import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/21 15:53
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/21$
 * @updateDes ${TODO}
 */

public class ZhuanLanMusicDataConfig {
    private static ZhuanLanBigInBean.DataBean.DirBean sTarget;

    public static String getTargetId() {
        return sTarget!=null? sTarget.getTargetId():"";
    }

    public static ZhuanLanBigInBean.DataBean.DirBean getTarget() {
        return sTarget;
    }

    public static void setTarget(ZhuanLanBigInBean.DataBean.DirBean target) {
        sTarget = target;
    }
}
