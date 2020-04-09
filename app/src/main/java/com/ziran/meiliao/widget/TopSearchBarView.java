package com.ziran.meiliao.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.citypicker.citylist.widget.ClearEditText;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.KeyBordUtil;
import com.ziran.meiliao.envet.SimpleTextWatcher;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/11/29 16:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/11/29$
 * @updateDes ${TODO}
 */

public class TopSearchBarView extends LinearLayout {
    @Bind(R.id.et_crowd_funding_choose_topic_search)
    ClearEditText etCrowdFundingChooseTopicSearch;
    @Bind(R.id.et_crowd_funding_choose_topic_cancel)
    TextView etCrowdFundingChooseTopicCancel;

    public TopSearchBarView(Context context) {
        this(context, null);
    }

    public TopSearchBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    private  boolean isCancel = true;
    public TopSearchBarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.custom_top_search, this, true);
        ButterKnife.bind(this,this);
        etCrowdFundingChooseTopicSearch.addTextChangedListener(new SimpleTextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==0 && !isCancel){
                    etCrowdFundingChooseTopicCancel.setText("取消");
                    isCancel = true;
                }else if (s.length()>0 && isCancel){
                    isCancel = false;
                    etCrowdFundingChooseTopicCancel.setText("搜索");
                }
            }
        });
    }

    @OnClick(R.id.et_crowd_funding_choose_topic_cancel)
    public void onViewClicked() {
        if (isCancel){
            ((Activity)getContext()).finish();
        }else{
            if (mOnSearchListener!=null){
                mOnSearchListener.search(etCrowdFundingChooseTopicSearch.getText().toString());
            }

        }
    }
    private onSearchListener mOnSearchListener;

    public void setOnSearchListener(onSearchListener onSearchListener) {
        mOnSearchListener = onSearchListener;
    }

    public interface onSearchListener{
        void search(String newValue);
    }

    public  String getContent(){
        return etCrowdFundingChooseTopicSearch.getText().toString().trim();
    }
    @Override
    protected void onDetachedFromWindow() {
        ButterKnife.unbind(this);
        super.onDetachedFromWindow();

    }

    public void hideKeyBoard() {
        KeyBordUtil.hideSoftKeyboard(etCrowdFundingChooseTopicSearch);
    }
}
