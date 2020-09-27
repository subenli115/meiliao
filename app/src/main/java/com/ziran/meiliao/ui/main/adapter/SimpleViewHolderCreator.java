package com.ziran.meiliao.ui.main.adapter;

import com.freegeek.android.materialbanner.holder.ViewHolderCreator;

public class SimpleViewHolderCreator implements ViewHolderCreator {
    public SimpleViewHolderCreator() {
    }

    public SimpleHolder createHolder() {
        return new SimpleHolder();
    }
}
