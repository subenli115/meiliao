package com.ziran.meiliao.ui.main.adapter;

import com.freegeek.android.materialbanner.holder.ViewHolderCreator;

public class SimpleStringViewHolderCreator implements ViewHolderCreator {
    private  String mIsVideo;

    public SimpleStringViewHolderCreator() {
    }

    public SimpleStringViewHolderCreator(String isVideo) {
       mIsVideo=isVideo;
    }
    public SimpleStringHolder createHolder() {
        return new SimpleStringHolder(mIsVideo);
    }
}
