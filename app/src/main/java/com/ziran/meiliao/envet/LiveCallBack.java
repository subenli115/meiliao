package com.ziran.meiliao.envet;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/11 11:19
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/11$
 * @updateDes ${TODO}
 */

public interface LiveCallBack {
    void postShiKan();

    void showBuyTip();

    boolean isBuyCourse();

    void historyShiKan();
}
