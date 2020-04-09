package cn.rongcloud.live.ui.message;

import android.content.Context;
import android.graphics.Color;

import com.bumptech.glide.Glide;

import io.rong.imlib.model.MessageContent;

public class GiftMsgView extends BaseMsgView {



    public GiftMsgView(Context context) {
        super(context);

    }

    @Override
    public void setContent(MessageContent msgContent) {
        GiftMessage msg = (GiftMessage) msgContent;
        mTextView.setText(String.format("%s%s", msg.getUserInfo().getName(), msg.getContent()));
        ivGiftIcon.setVisibility(VISIBLE);
        mTextView.setTextColor(Color.parseColor("#FFC464"));
        Glide.with(getContext()).load(msg.getType()).into(ivGiftIcon);
    }
}
