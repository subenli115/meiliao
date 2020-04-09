package com.ziran.meiliao.common.base;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/13 13:03
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/13$
 * @updateDes ${TODO}
 */

public interface BaseTip {
    void startProgressDialog();
    /**
     * 开启加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg);
    /**
     * 停止加载进度条
     */
    public void stopProgressDialog();


    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text);


    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId);


    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId);

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text);

    /**
     * 网络访问错误提醒
     */
    public void showNetErrorTip();

    public void showNetErrorTip(String error);
}
