package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

import java.util.List;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/8/20 13:19
 * @des ${TODO}
 * @updateAuthor #author
 * @updateDate 2017/8/20
 * @updateDes ${TODO}
 */

public class SpecColumnBean extends Result {



    private boolean isMax;
    /**
     * id : 1
     * headImg : http://www.dgli.net:8888/upload/images/userHeadImg/2e9fea79d7f743fca57b8d1f22e76cb4.jpeg
     * tag : 15点前更新
     * name : 摩拜汽车
     * descript : 这是很好的汽车
     */

    private List<SpecColumnData> data;

    public boolean isIsMax() {
        return isMax;
    }

    public void setIsMax(boolean isMax) {
        this.isMax = isMax;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public List<SpecColumnData> getData() {
        return data;
    }

    public void setData(List<SpecColumnData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SpecColumnBean{" + "isMax=" + isMax + ", data=" + data + '}';
    }
}
