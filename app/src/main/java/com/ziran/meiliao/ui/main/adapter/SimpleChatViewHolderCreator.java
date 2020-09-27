package com.ziran.meiliao.ui.main.adapter;

import com.freegeek.android.materialbanner.holder.ViewHolderCreator;

public class SimpleChatViewHolderCreator implements ViewHolderCreator {
    public SimpleChatViewHolderCreator() {

    }

    public SimpleChatViewHolderCreator(String isVideo) {

    }
    public SimpleStringChatHolder createHolder() {
        return new SimpleStringChatHolder();
    }
}
