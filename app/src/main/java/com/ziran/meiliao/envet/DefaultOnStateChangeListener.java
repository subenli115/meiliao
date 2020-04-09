package com.ziran.meiliao.envet;

import com.ziran.meiliao.widget.SlideLayoutNew;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/24 14:41
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/24$
 * @updateDes ${TODO}
 */

public    class DefaultOnStateChangeListener implements SlideLayoutNew.OnStateChangeListener {
    private static SlideLayoutNew slideLayout = null;
    @Override
    public void onOpen(SlideLayoutNew layout) {
        slideLayout = layout;
    }

    @Override
    public void onMove(SlideLayoutNew layout) {
        if (slideLayout != null && slideLayout != layout) {
            slideLayout.closeMenu();
        }
    }

    @Override
    public void onClose(SlideLayoutNew layout) {
        if (slideLayout == layout) {
            slideLayout = null;
        }
    }
}