package cn.rongcloud.live.ui.message;

import android.content.Context;
import android.graphics.Color;

import io.rong.imlib.model.MessageContent;
import io.rong.message.TextMessage;

public class TextMsgView extends BaseMsgView {


    public TextMsgView(Context context) {
        super(context);
    }

    @Override
    public void setContent(MessageContent msgContent) {
        TextMessage msg = (TextMessage) msgContent;
        ivGiftIcon.setVisibility(GONE);
        if (msg.getUserInfo()!=null){
            if ("NOTIFYCATION".equals(msg.getExtra())){
                mTextView.setText(msg.getContent());
                mTextView.setTextColor(Color.parseColor("#D5FFAA"));
            }else{
                setText(msgContent, msg.getUserInfo().getName(), msg.getContent());
            }
        }
    }
}
