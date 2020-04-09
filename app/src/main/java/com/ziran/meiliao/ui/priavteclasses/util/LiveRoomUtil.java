package com.ziran.meiliao.ui.priavteclasses.util;

import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.ui.bean.SJKLiveDetailProfileBean;
import com.ziran.meiliao.ui.bean.TeacherGoinBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/5 12:13
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/5$
 * @updateDes ${TODO}
 */

public class LiveRoomUtil {
    private static LiveRoomUtil INSTANT = new LiveRoomUtil();

    private LiveRoomUtil() {
    }

    public static LiveRoomUtil get() {
        return INSTANT;
    }

    public SJKLiveDetailProfileBean.DataBean dataBean;
    public TeacherGoinBean.DataBean teacherDataBean;
    private static boolean needConn = true;

    public SJKLiveDetailProfileBean.DataBean getDataBean() {
        return dataBean;
    }

    public TeacherGoinBean.DataBean getTeacherDataBean() {
        return teacherDataBean;
    }


    public void onDestroy(SJKLiveDetailProfileBean.DataBean dataBean) {
        this.dataBean = dataBean;
        this.teacherDataBean = null;
        needConn = true;
    }

    public void setTeacherDataBean(TeacherGoinBean.DataBean dataBean) {
        this.teacherDataBean = dataBean;
        this.dataBean = null;

    }

    public String getRoomId() {
        String chrmId = "";
        if (dataBean != null) {
            return String.valueOf(dataBean.getChrmId());
        }
        if (teacherDataBean != null) {
            return teacherDataBean.getChrmRoom().getChrmId();
        }
        return chrmId;
    }
    public void printData(){
        LogUtils.logd("dataBean:"+ dataBean);
    }
    public String getRoomToken() {
        String chrmUsetToken = "";
        if (dataBean != null) {
//            return String.valueOf(dataBean.getAESChrmUserToken());
            return dataBean.getChrmUserToken();
        }
        if (teacherDataBean != null) {
            return teacherDataBean.getChrmRoom().getChrmUserToken();
//            return teacherDataBean.getChrmRoom().getChrmUserToken();
        }
        return chrmUsetToken;
    }


    public void setNeedConn(boolean needConn) {
        LiveRoomUtil.needConn = needConn;
    }

    public static boolean isNeedConn() {
        return needConn;
    }
}
