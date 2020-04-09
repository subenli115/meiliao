package cn.rongcloud.live.ui.message;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.rongcloud.live.LiveKit;
import cn.rongcloud.live.R;
import io.rong.imlib.model.MessageContent;

public abstract class BaseMsgView extends LinearLayout {

    protected TextView mTextView;
    protected ImageView ivGiftIcon;
    private int mTextColorType;

    public BaseMsgView(Context context) {
        super(context);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.msg_text_view, this);
        mTextView = (TextView) view.findViewById(R.id.tv_content);
        ivGiftIcon = (ImageView) findViewById(R.id.iv_gift_icon);
    }

    public abstract void setContent(MessageContent msgContent);

    public void setText(MessageContent msgContent, String name, String content) {
        try {
            if (msgContent.getUserInfo().getUserId().equals(LiveKit.getCurrentUser().getUserId())) {
                mTextView.setText(createSendHtml(name, content));
            } else {
                mTextView.setText(createTargetHtml(name, content));
            }
//            textView.setText(EmojiManager.parse(textView.getText().toString(), textView.getTextSize()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Spanned createSendHtml(String username, String content) {
        return createHtml(username, content, mTextColorType != 1 ? "#FFE1AF" : "#FFA008");
    }

    protected Spanned createTargetHtml(String username, String content) {

        return createHtml(username, content, mTextColorType != 1 ? "#D5FFAA" : "45B000");
    }

    private Spanned createHtml(String username, String content, String color) {
        String html = "<span><span><font color=" + color + ">" + username + "&nbsp;:&nbsp;" + "</span>" + content + "</span>";
        return Html.fromHtml(html);
    }

    public void setTextColorType(int textColorType) {
        mTextColorType = textColorType;
    }
}
