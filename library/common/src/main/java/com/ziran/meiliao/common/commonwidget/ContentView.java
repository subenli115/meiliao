package com.ziran.meiliao.common.commonwidget;

import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ziran.meiliao.common.R;
import com.ziran.meiliao.common.commonutils.LogUtils;
import com.ziran.meiliao.common.commonutils.ViewUtil;


/**
 * Created by Administrator on 2017/1/6.
 */

public class ContentView extends RelativeLayout {
    public static final int TYPE_PHONE = 0;
    public static final int TYPE_EMAIL = 1;
    private TextView tvTitle;
    private EditText et;
    private Dialog dialog;
    private OnClickListener mListener;
    private int CURR_TYPE = TYPE_PHONE;

    public ContentView(Context context) {
        this(context,null);
    }

    public ContentView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View contentView = View.inflate(getContext(), R.layout.dialog_edit, this);
        tvTitle = ViewUtil.getView(this, R.id.title);
        et = ViewUtil.getView(this, R.id.dialog_edit);
        Button btnOk = ViewUtil.getView(this, R.id.ok);
        Button btnCancle = ViewUtil.getView(this, R.id.cancel);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(et.getText().toString());
                }
                dismiss();
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });
        LogUtils.logd("initView");
    }

    private void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setHint(String hint) {
        et.setHint(hint);
    }

    public void setContent(String content) {
        et.setText(content);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public void setType(int type) {
        switch (type) {
            case TYPE_PHONE:
//                et.setInputType(InputType.TYPE_CLASS_PHONE);
                setHint("请输入用户名");
                setTitle("正在编辑用户名");
                CURR_TYPE = TYPE_PHONE;
                break;
            case TYPE_EMAIL:
                setHint("请输入邮箱地址");
                setTitle("正在编辑用户邮箱");
//                et.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                CURR_TYPE = TYPE_EMAIL;
                break;
        }
    }

    public void setOnClickListener(OnClickListener mListener) {
        this.mListener = mListener;
    }

    public interface OnClickListener {
        void onClick(String result);
    }
}
