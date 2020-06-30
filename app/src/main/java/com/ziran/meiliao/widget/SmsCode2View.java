package com.ziran.meiliao.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.compressorutils.RegexUtils;
import com.ziran.meiliao.common.sms.SmsObserver;
import com.ziran.meiliao.envet.SimpleTextWatcher;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

/**
 * author 吴祖清
 * create  2017/3/31 10
 * des     验证码组合控件
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */

public class SmsCode2View extends LinearLayout implements View.OnFocusChangeListener {

    public static final int TYPE_POST_LOGIN = 0;
    private int TYPE_POST = TYPE_POST_LOGIN;
    /**
     * 根元素
     */
    private View rootView;
    /**
     * 编辑框
     */
    private EditText etSmsCode;
    /**
     * 获取验证码的按钮
     */
    private TextView
            tvSmsGetCode;
    /**
     * 检测获取焦点的空间
     */
    private TextView tvPhone, tvPwd;
    /**
     * 短信接收的观察者
     */
    private SmsObserver observer;

    /**
     * 标记是否注册短信观察者
     */
    private boolean isRegistSms;
    /**
     * 吐丝内容
     */
    private String toast = getContext().getString(R.string.res_getcode);
    private String codeNumber = "+86";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                tvSmsGetCode.setEnabled(true);
                tvSmsGetCode.setText(toast);
                return;
            }
            tvSmsGetCode.setText(msg.what + "s");
        }
    };
    /**
     * 自动填写短信验证码回调
     */
    private OnSmsCallBack mOnSmsCallBack;

    /**
     * 失去焦点监听
     */
    private OnNotFocusListener onNotFocusListener;
    private String mPhone;

    public SmsCode2View(Context context) {
        this(context, null);
    }

    public SmsCode2View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmsCode2View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setTextSize(float size) {
        etSmsCode.setTextSize(size);

    }

    /**
     * 初始化控件
     */
    private void initView() {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.include_sms_code2, this, true);
        etSmsCode = (EditText) findViewById(R.id.et_sms_code);
        tvSmsGetCode = (TextView) findViewById(R.id.tv_sms_getcode);
        tvSmsGetCode.setOnClickListener(new OnNoDoubleClickListener() {
            private Map<String, String> codeMap;

            @Override
            protected void onNoDoubleClick(View v) {

                if (tvPhone != null) {

                    String phone = tvPhone.getText().toString();
                    if(mPhone.length()>0){
                        if (!RegexUtils.regexMoble(mPhone, codeNumber)) {
                            return;
                        }
                    }else {
                        if (!RegexUtils.regexMoble(phone, codeNumber)) {
                            return;
                        }
                    }
                    if (mOnSmsCallBack != null) {
//                        registerSms();
                        if(mPhone.length()>0){
                            codeMap = MapUtils.getCodeMap("", mPhone, "false");
                        }else{
                             codeMap = MapUtils.getCodeMap("", phone, "false");
                        }
                        mOnSmsCallBack.call(TYPE_POST, codeMap);
                    }
                }
            }
        });
    }

    public void setbg(String phone) {
        mPhone=phone;
        etSmsCode.setHint("请输入");
    }

    //注册短信观察者
    public void registerSms() {
        if (isRegistSms) return;

        Handler mHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == SmsObserver.MSG_RECEIVED_CODE) {
                    String code = (String) msg.obj;
                    /**
                     * 更新UI：实现自动填写短信验证码
                     */
//                    etSmsCode.setText(code);
                    if (tvPwd != null) {
                        KeyBordUtil.reqFocus(tvPwd);
                    }
                }
            }
        };
        observer = new SmsObserver(getContext(), mHandler);
        observer.register();
        isRegistSms = true;
    }

    /**
     * 注销短信观察者
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (observer != null) {
            observer.unRegister();
            observer = null;
        }
    }

    /**
     * 设置获取验证码按钮不可点击
     */
    public void reset() {
        tvSmsGetCode.setEnabled(false);
    }

    public void startDjs() {
        reset();
        new Thread(getCodeTask).start();
    }

    public EditText getEtitTextCode() {
        return etSmsCode;
    }

    public TextView getTvSmsGetCode() {
        return tvSmsGetCode;
    }

    public String getCode() {
        return etSmsCode.getText().toString();
    }

    Runnable getCodeTask = new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = 60; i >= 0; i--) {
                    if(handler!=null){
                        handler.sendEmptyMessage(i);
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };


    public void setOnNotFocusListener(OnNotFocusListener onNotFocusListener) {
        this.onNotFocusListener = onNotFocusListener;
    }

    public void setOnSmsCallBack(OnSmsCallBack callBack) {
        this.mOnSmsCallBack = callBack;
    }

    public void setTvPwd(EditText tvPwd) {
        this.tvPwd = tvPwd;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            if (onNotFocusListener != null && tvPhone.getText().toString().length() > 6) {
                onNotFocusListener.notFocus(false);
            }
        }
    }

    public void bindBtn(final TextView btn) {
        getEtitTextCode().addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btn.setEnabled(EmptyUtils.isNotEmpty(s.toString()) && s.length() >= 4 && tvPhone.getText().length() >= 10);
            }
        });
    }

    /**
     * 自动填写短信的回调
     */
    public interface OnSmsCallBack {
        void call(int type, Map<String, String> codeMap);
    }

    /**
     * 失去焦点监听
     */
    public interface OnNotFocusListener {
        void notFocus(boolean focus);
    }

    public void setTvPhone(TextView textView) {
        this.tvPhone = textView;
    }

    public void setEvPhone(EditText textView) {

        this.tvPhone = textView;
    }

    public void setOnEditActionListener(TextView.OnEditorActionListener onEditActionListener, CharSequence label, int actionId) {
        if (null != onEditActionListener) {
            etSmsCode.setImeOptions(actionId);
            etSmsCode.setOnEditorActionListener(onEditActionListener);
            etSmsCode.setImeActionLabel(label, actionId);
        }

    }

    public void setFocusListener(OnNotFocusListener onNotFocusListener) {
        if (tvPhone != null) {
            tvPhone.setOnFocusChangeListener(this);
        }
        if (tvPwd != null) {
            tvPwd.setOnFocusChangeListener(this);
        }
        if (etSmsCode != null) {
            etSmsCode.setOnFocusChangeListener(this);
        }
        setOnNotFocusListener(onNotFocusListener);
    }

    private CodeNumberCallBack mCodeNumberCallBack;

    public void setCodeNumberCallBack(CodeNumberCallBack codeNumberCallBack) {
        mCodeNumberCallBack = codeNumberCallBack;
    }

    public static interface CodeNumberCallBack {
        String getCodeNumber();
    }
}
