package com.ziran.meiliao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/19 10:34
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/19$
 * @updateDes ${TODO}
 */

public class PhoneCodeView extends LinearLayout {
    private View rootView;
    private TextView tvAreaCode;
    private EditText etPhone;

    public PhoneCodeView(Context context) {
        this(context, null);
    }

    public PhoneCodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public PhoneCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rootView = LayoutInflater.from(context).inflate(R.layout.customview_phone_code, this, true);
        tvAreaCode = ViewUtil.getView(rootView, R.id.tv_areacode);
        etPhone = ViewUtil.getView(rootView, R.id.et_phone);
    }

    public void setAreaCode(String code) {
        if (EmptyUtils.isNotEmpty(code)) {
            tvAreaCode.setText(code);
        }
    }

    public EditText getEtPhone() {
        return etPhone;
    }

    public void setPhoneText(String text) {
        etPhone.setText(text);
        etPhone.setSelection(text.length());
    }

    public String getPhoneText() {
        return etPhone.getText().toString();
    }

    public void setTvAreaCodeClickListener(View.OnClickListener onClickListener) {
        tvAreaCode.setOnClickListener(onClickListener);
    }


}
