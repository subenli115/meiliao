package com.ziran.meiliao.ui.workshops.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.utils.HtmlUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/9 9:46
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/9$
 * @updateDes ${TODO}
 */

public class ProgressTipsView extends RelativeLayout {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.simpleProgressView)
    SimpleProgressView mSimpleProgressView;
    @Bind(R.id.tv_leftText)
    TextView tvLeftText;
    @Bind(R.id.tv_rightText)
    TextView tvRightText;

    public ProgressTipsView(Context context) {
        this(context, null);
    }

    public ProgressTipsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressTipsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.custom_progress_tips, this, true);
        ButterKnife.bind(this, this);
    }


    public void setProgress(int progress) {
        if (mSimpleProgressView!=null)
        mSimpleProgressView.setProgress(progress);
    }

    public void setTitle(String text) {
        ViewUtil.setText(tvTitle, text);
    }

    public void setLeftText(String text) {
        ViewUtil.setText(tvLeftText, HtmlUtil.progressTips("剩余", text, "天"));
    }

    public void setParms(int title,int left,int right){
        setTitle(String.format("%d人支持",title));
        setLeftText(String.valueOf(left));
        setRightText(String.valueOf(right));
        if (right>0){
            setProgress(title*100/right);
        }
    }
    public void setRightText(String text) {
        ViewUtil.setText(tvRightText, HtmlUtil.progressTips("目标", text, "人"));
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.unbind(this);
    }
}
