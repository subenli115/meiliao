package com.ziran.meiliao.ui.main.listener;

import android.view.View;

import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.HeadData;

/**
 * @author 吴祖清
 * @version 1.0
 * @createDate 2017/8/26 17:05
 * @des ${TODO}
 * @updateAuthor #author
 * @updateDate 2017/8/26
 * @updateDes ${TODO}
 */

public class OnClickMoreListener implements View.OnClickListener {
    private HeadData mHeadData;
    private String tag;
    public OnClickMoreListener(HeadData headData) {
        mHeadData = headData;
    }
    public OnClickMoreListener(HeadData headData,String tag) {
        mHeadData = headData;
        this.tag =tag;
    }

    @Override
    public void onClick(View v) {
        if (EmptyUtils.isNotEmpty(tag)){
            RxManagerUtil.post(tag,mHeadData);
        }else{
            RxManagerUtil.post(AppConstant.RXTag.CATEGORY_MORE_CLICK,mHeadData);
        }
    }
}
