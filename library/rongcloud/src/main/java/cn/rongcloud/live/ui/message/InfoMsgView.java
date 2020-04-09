package cn.rongcloud.live.ui.message;

import android.content.Context;

import io.rong.imlib.model.MessageContent;
import io.rong.message.InformationNotificationMessage;

public class InfoMsgView extends BaseMsgView {



    public InfoMsgView(Context context) {
        super(context);
    }

    @Override
    public void setContent(MessageContent msgContent) {
        InformationNotificationMessage msg = (InformationNotificationMessage) msgContent;
        setText(msgContent,msg.getUserInfo().getName(),msg.getMessage());
    }
}
