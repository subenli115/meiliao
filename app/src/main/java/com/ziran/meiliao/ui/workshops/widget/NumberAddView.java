package com.ziran.meiliao.ui.workshops.widget;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.envet.SimpleTextWatcher;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/20 16:51
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/20$
 * @updateDes ${TODO}
 */

public class NumberAddView extends LinearLayout {

    @Bind(R.id.et_number)
    EditText etNumber;

    public NumberAddView(Context context) {
        this(context, null);
    }

    public NumberAddView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberAddView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.custom_number_add, this, true);
        ButterKnife.bind(this, this);
//        setFitter(10);
        etNumber.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mOnNumberChangeListener!=null){
                    mOnNumberChangeListener.onNumberChange( getNumber());
                }
            }
        });
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_add, R.id.iv_reduce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add:
                etNumber.setText(newNumber(true));
                break;
            case R.id.iv_reduce:
                etNumber.setText(newNumber(false));
                break;
        }
    }

    public String newNumber(boolean isAdd) {
        int number = getNumber();
        if (isAdd) {
            number++;
            number = maxNumber < number ? maxNumber : number;

        } else {
            number--;
            number = number < 1 ? 1 : number;
        }
        return String.valueOf(number);
    }

    private int maxNumber=10;
    private OnNumberChangeListener mOnNumberChangeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        mOnNumberChangeListener = onNumberChangeListener;
    }

    public interface OnNumberChangeListener{
        void onNumberChange(int newNumber);
    }
    public void setFitter(int max) {
        this.maxNumber = max;
        etNumber.setFilters(new InputFilter[]{new InputFilterMinMax(1,max)});
    }

    public int getNumber() {
        if (EmptyUtils.isEmpty(etNumber.getText().toString())){
            return 1;
        }
        return Integer.parseInt(etNumber.getText().toString());
    }

    public EditText getEtNumber(){
        return etNumber;
    }
    private class InputFilterMinMax implements InputFilter {
        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input)) return null;
            } catch (Exception nfe) {
            }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}
