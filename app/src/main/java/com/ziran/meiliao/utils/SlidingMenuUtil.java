package com.ziran.meiliao.utils;

import com.ziran.meiliao.widget.SlidingMenu;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/27 16:36
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/27$
 * @updateDes ${TODO}
 */

public class SlidingMenuUtil implements SlidingMenu.SlidingMenuListener {
    private SlidingMenu mOpenMenu;
    private SlidingMenu mScrollingMenu;
    @Override
    public void closeOpenMenu() {
        if (isOpenMenu()) {
            mOpenMenu.closeMenu();
            mOpenMenu = null;
        }
    }

    @Override
    public boolean isOpenMenu() {
        return mOpenMenu != null && mOpenMenu.isOpen();
    }

    @Override
    public void holdOpenMenu(SlidingMenu openMenu) {
        mOpenMenu = openMenu;
    }

    @Override
    public SlidingMenu getScrollingMenu() {
        return mScrollingMenu;
    }

    @Override
    public void setScrollingMenu(SlidingMenu scrollingMenu) {
        mScrollingMenu = scrollingMenu;

    }

}
