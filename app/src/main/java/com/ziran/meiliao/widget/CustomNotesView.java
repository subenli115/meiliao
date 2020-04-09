package com.ziran.meiliao.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/8 17:52
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/8$
 * @updateDes ${TODO}
 */

public class CustomNotesView extends LinearLayout {

    private TextView tvTitle;
    private ForbidEmojiEditText etContent;

    public CustomNotesView(Context context) {
        this(context, null);
    }

    public CustomNotesView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomNotesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_notes, this, true);
        tvTitle = ViewUtil.getView(this, R.id.tv_title);
        etContent = ViewUtil.getView(this, R.id.et_content);
        tvTitle.setText(title);
    }

    private String title;

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomNotesView);
        title = typedArray.getString(R.styleable.CustomNotesView_cnv_title);
        typedArray.recycle();
    }

    public String getContent() {
        return etContent.getText().toString().trim();
    }


    public void showContentView(int visible) {

        if (visible == etContent.getVisibility()) return;
        etContent.setVisibility(visible);
        etContent.requestFocus();
    }
    public EditText getEtContent(){
        return etContent;
    }
    public void setTitle(String title) {
        ViewUtil.setText(tvTitle, title);
    }

    public void setContent(String content) {
        ViewUtil.setText(etContent, content);
    }
}
