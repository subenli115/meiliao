package com.ziran.meiliao.utils;

import android.widget.TextView;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     检查页面是否处于编辑删除item的状态(目前在我的收藏和我的下载界面使用)
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public class CanEditUtil {
    /**
     * 编辑的控件
     */
    private TextView mEditText;
    
    public CanEditUtil (TextView editText) {
        this.mEditText = editText;
    }
    
    /**
     * @return 是否可以编辑
     */
    public boolean isEdit () {
        return mEditText != null && "取消".equals(mEditText.getText().toString());
    }
}
