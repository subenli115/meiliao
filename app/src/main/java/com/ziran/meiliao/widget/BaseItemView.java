package com.ziran.meiliao.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.citypicker.citylist.widget.ClearEditText;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baserx.RxManagerUtil;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.commonutils.ImageLoaderUtils;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.common.commonutils.ToastUitl;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.percent.PercentRelativeLayout;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/9 18:03
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/9$
 * @updateDes ${TODO}
 */

public class BaseItemView extends RelativeLayout {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    TextView tvContent;
    EditText etContent;
    private ImageView ivContent;
    @Bind(R.id.fl_right)
    LinearLayout llRightContainer;
    @Bind(R.id.fl_content)
    LinearLayout flContentContainer;


    TextView tvRightText;
    ImageView ivForword;
    View viewLine;
    private int maxLines = 1;
    int contentColor;
    private float contentSize;
    private int contentInputType;
    private int contentImageSize, contentMarginLeft = -1;

    public BaseItemView(Context context) {
        this(context, null);
    }


    public BaseItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private View rootView;
    private int clickID;
    private int rightClickID;
    private boolean mustNeedContent;
    private String hint;
    private int contentType;
    private int contentImeOptions;

    public BaseItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        rootView = LayoutInflater.from(context).inflate(R.layout.custom_base_item, this, true);
        ButterKnife.bind(this, this);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BaseItemView);
        Drawable drawable;
        if (array.hasValue(R.styleable.BaseItemView_biv_background)) {
            drawable = array.getDrawable(R.styleable.BaseItemView_biv_background);
        } else {
            drawable = getResources().getDrawable(R.drawable.selector_white_gray);
        }
        setBackgroundDrawable(drawable);

        contentType = array.getInt(R.styleable.BaseItemView_biv_content_type, 0);
        contentImeOptions = array.getInt(R.styleable.BaseItemView_biv_content_ime, 5);
        if (array.hasValue(R.styleable.BaseItemView_biv_content_margin_left)) {
            contentMarginLeft = array.getDimensionPixelOffset(R.styleable.BaseItemView_biv_content_margin_left, 0);
        }
        mustNeedContent = array.getBoolean(R.styleable.BaseItemView_biv_content_must_need, false);
        hint = array.getString(R.styleable.BaseItemView_biv_content_hint);
        maxLines = array.getInt(R.styleable.BaseItemView_biv_content_max_line, 1);
        contentSize = array.getFloat(R.styleable.BaseItemView_biv_content_size, 0);
        switch (contentType) {
            case 0:
                contentColor = array.getColor(R.styleable.BaseItemView_biv_content_color, Color.parseColor("#333333"));
                createContentText();
                break;
            case 1:
                contentInputType = array.getInt(R.styleable.BaseItemView_biv_content_input_type, -1);
                createEditText(hint);
                break;
            case 2:
                contentImageSize = array.getDimensionPixelSize(R.styleable.BaseItemView_biv_content_image_size, DisplayUtil.dip2px
                        (getResources(), 40));
                Drawable arrayDrawable = array.getDrawable(R.styleable.BaseItemView_biv_content_image_res);
                createContentImage(arrayDrawable);
                break;
            case 3:
                createContentCheckBox();
                break;
            case 4:
                contentColor = array.getColor(R.styleable.BaseItemView_biv_content_color, Color.parseColor("#333333"));
                createContentText();
                createRightCheckBox();
                break;
        }

        if (array.hasValue(R.styleable.BaseItemView_biv_title)) {
            tvTitle.setText(array.getString(R.styleable.BaseItemView_biv_title));
        }
        if (array.hasValue(R.styleable.BaseItemView_biv_title_size)) {
            tvTitle.setTextSize(array.getFloat(R.styleable.BaseItemView_biv_title_size, 16f));
        }
        if (array.hasValue(R.styleable.BaseItemView_biv_title_color)) {
            tvTitle.setTextColor(array.getColor(R.styleable.BaseItemView_biv_title_color, Color.parseColor("#666666")));
        }
        if (array.hasValue(R.styleable.BaseItemView_biv_title_width)) {
            tvTitle.getLayoutParams().width = array.getDimensionPixelSize(R.styleable.BaseItemView_biv_title_width, DisplayUtil.dip2px
                    (getResources(), 100));
        }

        if (array.hasValue(R.styleable.BaseItemView_biv_right_text)) {
            createRightText();
            setRightText(array.getString(R.styleable.BaseItemView_biv_right_text));
            if (array.hasValue(R.styleable.BaseItemView_biv_right_color)) {
                tvRightText.setTextColor(array.getColor(R.styleable.BaseItemView_biv_right_color, Color.parseColor("#45b000")));
            }
            if (array.hasValue(R.styleable.BaseItemView_biv_right_text_size)) {
                tvRightText.setTextSize(array.getFloat(R.styleable.BaseItemView_biv_right_text_size, 16f));
            }
        }
        boolean showForWord = array.getBoolean(R.styleable.BaseItemView_biv_forword_show, true);
        if (showForWord) {
            createRightImg();
        }
        boolean showDivider = array.getBoolean(R.styleable.BaseItemView_biv_divider_show, true);
        if (showDivider) {
            createBottomLine();
        }
        if (array.hasValue(R.styleable.BaseItemView_biv_click_id)) {
            clickID = array.getInt(R.styleable.BaseItemView_biv_click_id, -1);
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    RxManagerUtil.post(AppConstant.RXTag.BASE_ITEM_VIEW_CLICK_ID, clickID);
                }
            });
        }
        if (tvRightText != null && array.hasValue(R.styleable.BaseItemView_biv_right_click_id)) {
            rightClickID = array.getInt(R.styleable.BaseItemView_biv_right_click_id, -1);
            tvRightText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    RxManagerUtil.post(AppConstant.RXTag.BASE_ITEM_VIEW_CLICK_ID, rightClickID);
                }
            });
        }
        array.recycle();
    }

    private BaseItemHelper mBaseItemHelper;

    private void createContentCheckBox() {
        if (mBaseItemHelper == null) {
            mBaseItemHelper = new BaseItemHelper(getContext());
            mBaseItemHelper.createContent(flContentContainer);
        }
    }

    public String getSex() {
        if (mBaseItemHelper != null) {
            return mBaseItemHelper.getSex();
        }
        return "";
    }

    private void createEditText(String hints) {
        hint=hints;
        if (maxLines == 1) {
            etContent = new ClearEditText(getContext());
        } else {
            etContent = new EditText(getContext());
        }
        etContent.setMaxLines(maxLines);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        etContent.setGravity(Gravity.CENTER_VERTICAL);
        contentSize = contentSize > 0 ? contentSize : 14f;
        etContent.setTextSize(contentSize);
        setTextHint(etContent);
        etContent.setBackgroundColor(Color.TRANSPARENT);
        etContent.setHintTextColor(getResources().getColor(R.color.textColor_hint));
        etContent.setTextColor(getResources().getColor(R.color.textColor_333));
        if (maxLines == 1) {
            etContent.setImeOptions(contentImeOptions);
            etContent.setSingleLine(true);
            etContent.setImeActionLabel(contentImeOptions == 4 ? "发送" : contentImeOptions == 5 ? "下一项" : contentImeOptions == 6 ? "完成" :
                    "none", contentImeOptions);
        }
        flContentContainer.addView(etContent, params);
        switch (contentInputType) {
            case 0:
                etContent.setInputType(InputType.TYPE_CLASS_TEXT);
                break;//text
            case 1:
                etContent.setInputType(InputType.TYPE_CLASS_PHONE);
                break;//phone
            case 2:
                etContent.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;//number
            case 3:
                etContent.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;//email
        }
    }

    private void setTextHint(TextView tv) {
        if (mustNeedContent) {
            tv.setHint(hint == null ? "必填" : hint);
        } else {
            tv.setHint(hint);
        }
    }

    private void createContentText() {
        if (tvContent != null) return;
        tvContent = new TextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams
                .WRAP_CONTENT);
        params.gravity = Gravity.CENTER_VERTICAL;
        tvContent.setGravity(Gravity.CENTER_VERTICAL);

        tvContent.setMaxLines(maxLines);
        tvContent.setEllipsize(TextUtils.TruncateAt.END);
        tvContent.setTextColor(contentColor);
        tvContent.setHintTextColor(getResources().getColor(R.color.textColor_hint));

        if (contentSize>0){
            tvContent.setTextSize(contentSize);
        }else{
            tvContent.setTextSize(16f);
        }

        flContentContainer.addView(tvContent, params);
        setTextHint(tvContent);
    }

    private void createContentImage(Drawable drawable) {
        if (ivContent != null) return;
        ivContent = new ImageView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(contentImageSize, contentImageSize);
        params.gravity = Gravity.CENTER_VERTICAL;
        ivContent.setImageResource(R.mipmap.ic_areacode_arr);
        if (drawable != null) {
            ivContent.setImageDrawable(drawable);
        }
        flContentContainer.addView(ivContent, params);
    }


    private void createBottomLine() {
        if (viewLine != null) return;
        viewLine = new View(getContext());
        viewLine.setBackgroundColor(getResources().getColor(R.color.divider1));
        LayoutParams params = new PercentRelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(getResources
                (), 0.7f));
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.leftMargin = DisplayUtil.dip2px(getResources(), 12);
        addView(viewLine, params);
    }


    private void createRightText() {
        if (tvRightText != null) return;
        tvRightText = new TextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams
                .MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        tvRightText.setGravity(Gravity.CENTER);
        llRightContainer.addView(tvRightText, params);
    }

    private SmoothCheckBox mSmoothCheckBox;

    private void createRightCheckBox() {
        if (mSmoothCheckBox != null) return;
        mSmoothCheckBox = new SmoothCheckBox(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams
                .WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        llRightContainer.addView(mSmoothCheckBox, params);
    }

    public void setRightCheckBoxListener(SmoothCheckBox.OnCheckedChangeListener listener) {
        if (mSmoothCheckBox != null) {
            mSmoothCheckBox.setOnCheckedChangeListener(listener);
        }
    }

    public boolean isRightCheck() {
        return mSmoothCheckBox != null && mSmoothCheckBox.isChecked();
    }

    public void setOnRightClickListener(OnClickListener listener) {
        if (tvRightText != null) {
            tvRightText.setOnClickListener(listener);
        }
    }

    public void put(String key, Map<String, String> map) {
        if (EmptyUtils.isNotEmpty(getContent()) && EmptyUtils.isNotEmpty(map) && isShown()) {
            map.put(key, getContent());
        }
    }

    public void setImageUrl(String avatar) {
        if (ivContent != null) {
            ImageLoaderUtils.displayCircle(getContext(), ivContent, avatar, R.drawable.jmui_head_icon);
        }
    }

    public void reqFocus() {
        if (etContent != null) {
            KeyBordUtil.reqFocus(etContent);
        }
    }

    private interface OnRightClickListener {
        void onClick(View v);
    }

    private void createRightImg() {
        if (ivForword != null) return;
        ivForword = new ImageView(getContext());
        ivForword.setImageResource(R.mipmap.ic_arrow_gray);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams
                .WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        llRightContainer.addView(ivForword, params);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (contentMarginLeft != -1) ((LayoutParams) flContentContainer.getLayoutParams()).leftMargin = contentMarginLeft;
    }

    public void setTitle(CharSequence title) {
        ViewUtil.setText(tvTitle, title);
    }

    public void setRightText(CharSequence rightText) {
        ViewUtil.setText(tvRightText, rightText);
    }

    public void setContent(CharSequence content) {
//        createContentText();
        createEditText(content.toString());
        if (tvContent != null) {
            Log.e("setContent","CharSequence2");
            tvContent.setText(content);
        } else if (etContent != null) {
            Log.e("setContent","CharSequence1"+content.toString());
            etContent.setText(content.toString());
            etContent.setSelection(content.length()-1);
        }
//        ViewUtil.setText(tvContent, content);
    }

    public void setForWordState(int visible) {
        createRightImg();
        ivForword.setVisibility(visible);
    }

    public void setRightTextState(int visible) {
        createRightText();
        tvRightText.setVisibility(visible);
    }

    public void setDividerState(int visible) {
        createBottomLine();
        viewLine.setVisibility(visible);
    }

    public ImageView getImage() {
        return ivContent;
    }

    public TextView getRightText() {
        return tvRightText;
    }

    public String getContent() {
        if (EmptyUtils.isNotEmpty(tvContent)) {
            return tvContent.getText().toString();
        } else if (EmptyUtils.isNotEmpty(etContent)) {
            return etContent.getText().toString();
        } else if (contentType == 3) {
            return getSex();
        }
        return "";
    }

    public void setRightTextClickListener(OnClickListener listener) {
        if (tvRightText == null) throw new NullPointerException("tvRightText must not null");
        tvRightText.setOnClickListener(listener);
    }

    @Override
    protected void onDetachedFromWindow() {
        ButterKnife.unbind(this);
        super.onDetachedFromWindow();
    }

    public boolean checkNull(String tips) {
        if (EmptyUtils.isEmpty(getContent())) {
            ToastUitl.showShort(tips);
            return false;
        }
        return true;
    }

    public void hideKeyBoard() {
        if (etContent != null) KeyBordUtil.hideSoftKeyboard(etContent);
    }

    public int getClickID() {
        return clickID;
    }

    public void setClickID(int clickID) {
        this.clickID = clickID;
    }

    public int getRightClickID() {
        return rightClickID;
    }

    public void setRightClickID(int rightClickID) {
        this.rightClickID = rightClickID;
    }
}
