package com.ziran.meiliao.ui.bean;

import com.ziran.meiliao.common.okhttp.Result;

/**
 * Created by Administrator on 2017/3/2.
 */

public class ShareCouponBean extends Result {

    /**
     * data : {"shareImgPath":"https://www.psytap.com/wpyx_longjg/static/home/images/shareCoupon/56a976d47cfb4e8b89cd9484abffa604.jpeg","shareDescript":"5P医学优惠券优惠券。。。。。。","shareTitle":"领取优惠券","sharePic":"https://www.psytap.com/wpyx_longjg/1.png","sharePagePath":"https://www.psytap.com/wpyx_longjg/page/shareCoupon.html?token=Iq4DiEljixS8ZNAxIvzRS31O+PzGKSoMdUQPSu/B18KvgXYimCMokuVqda9LOWD1"}
     */

    private ShareBean data;

    public ShareBean getData() {
        return data;
    }

    public void setData(ShareBean data) {
        this.data = data;
    }

}
