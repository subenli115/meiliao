package com.ziran.meiliao.common.irecyclerview.widget;


import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ziran.meiliao.common.R;


/**
 * Created by aspsine on 16/3/14.
 */
public class LoadMoreFooterView extends FrameLayout {

    private Status mStatus;

    private View mLoadingView;

    private View mErrorView;

    private View mTheEndView;

    private OnRetryListener mOnRetryListener;

    public LoadMoreFooterView(Context context) {
        this(context, null);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.layout_irecyclerview_load_more_footer_view, this, true);

        mLoadingView = findViewById(R.id.loadingView);
        mErrorView = findViewById(R.id.errorView);
        mTheEndView = findViewById(R.id.theEndView);

        mErrorView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRetryListener != null) {
                    mOnRetryListener.onRetry(LoadMoreFooterView.this);
                }
            }
        });

        setStatus(Status.GONE);
    }

    public void setOnRetryListener(OnRetryListener listener) {
        this.mOnRetryListener = listener;
    }

    public Status getStatus() {
        return mStatus;
    }

    public void setStatus(Status status) {
        this.mStatus = status;
        change();
    }
    public void setErrorMsg(String  msg){
        if (!TextUtils.isEmpty(msg) && mErrorView instanceof TextView){
            ((TextView) mErrorView).setText(msg);
        }
    }
    public boolean canLoadMore() {
//        LogUtils.logd("mStatus:"+mStatus);
        return mStatus == Status.GONE || mStatus == Status.ERROR;
    }

    private void change() {
        switch (mStatus) {
            case GONE:
//                changeViewState(null,mLoadingView,mErrorView,mTheEndView);
                mLoadingView.setVisibility(GONE);
                mErrorView.setVisibility(GONE);
                mTheEndView.setVisibility(GONE);
                break;

            case LOADING:
                changeViewState(mLoadingView,mErrorView,mTheEndView);
//                mLoadingView.setVisibility(VISIBLE);
//                mErrorView.setVisibility(GONE);
//                mTheEndView.setVisibility(GONE);
                break;

            case ERROR:
                changeViewState(mErrorView,mLoadingView,mTheEndView);
//                mLoadingView.setVisibility(GONE);
//                mErrorView.setVisibility(VISIBLE);
//                mTheEndView.setVisibility(GONE);
                break;

            case THE_END:
                changeViewState(mTheEndView,mErrorView,mLoadingView);
//                mLoadingView.setVisibility(GONE);
//                mErrorView.setVisibility(GONE);
//                mTheEndView.setVisibility(VISIBLE);
                break;
        }
    }

    public void changeViewState(View showView,View... goneViews){
        if (!checkViewVisibility(showView)){
            showView.setVisibility(VISIBLE);
        }
        if (goneViews!=null && goneViews.length>0){
            for(int i=0;i<goneViews.length;i++){
                if (checkViewVisibility(goneViews[i] )){
                    goneViews[i].setVisibility(GONE);
                }
            }
        }
    }

    /**
     *     检查改View是否显示
     * @param view      需要检查的View
     * @return          (true)显示(false)不显示
     */
    public boolean checkViewVisibility(View view){
        if (view ==null || view.getVisibility()==GONE){
            return false;
        }
        return  true;
    }
    public enum Status {
        GONE, LOADING, ERROR, THE_END
    }

    public interface OnRetryListener {
        void onRetry(LoadMoreFooterView view);
    }

}
