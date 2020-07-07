package com.ziran.meiliao.widget;

        import android.content.Context;
        import android.text.InputFilter;
        import android.text.Spanned;
        import android.util.AttributeSet;

        import androidx.appcompat.widget.AppCompatEditText;

        import com.ziran.meiliao.common.commonutils.ToastUitl;

        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class ForbidEmojiEditText extends AppCompatEditText {

    public ForbidEmojiEditText(Context context) {
        super(context);
    }

    public ForbidEmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForbidEmojiEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setFilters(InputFilter[] filters) {
        InputFilter emojiFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                                       int dstart, int dend) {
                Pattern emoji = Pattern.compile(
                        "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                        Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
                Matcher emojiMatcher = emoji.matcher(source);
                if (emojiMatcher.find()) {
                    ToastUitl.showShort("暂不支持表情符号");
                    return "";
                }
                return null;
            }
        };
        super.setFilters(new InputFilter[]{emojiFilter});
    }
}