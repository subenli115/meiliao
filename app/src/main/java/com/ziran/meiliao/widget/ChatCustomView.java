package com.ziran.meiliao.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.im.utils.keyboard.widget.EmoticonsEditText;


/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/8 17:52
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/8$
 * @updateDes ${TODO}
 */

public class ChatCustomView extends LinearLayout implements  EmoticonsEditText.OnBackKeyClickListener{

    private View rootView;
    private ImageView btnGift;
    private EmoticonsEditText etChat;
    private View mLyKvml;

    public ChatCustomView(Context context) {
        this(context, null);
    }

    public ChatCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rootView = LayoutInflater.from(context).inflate(R.layout.item_comment_bottom, this, true);
        initView();
    }

    private void initView() {
        etChat = ViewUtil.getView(rootView,R.id.et_chat);
        mLyKvml = ViewUtil.getView(rootView,R.id.ly_kvml);
        etChat.setOnBackKeyClickListener(this);
        btnGift.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {

            }
        });

        etChat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    public void onBackKeyClick() {

    }




    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getKeyCode()) {
        }
        return super.dispatchKeyEvent(event);
    }

}
