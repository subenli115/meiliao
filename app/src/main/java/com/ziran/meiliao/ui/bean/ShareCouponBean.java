package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2017/3/2.
 */

public class ShareCouponBean extends Result {


    private ShareBean data;

    public ShareBean getData() {
        return data;
    }

    public void setData(ShareBean data) {
        this.data = data;
    }

}
