package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseFragment;

/**
 * 减压馆-分类Fragment
 * Created by Administrator on 2017/1/7.
 */

public class PracticeBgVideoFragment extends BaseFragment {

    private static final String PLAYER_PATH = "PLAYER_PATH";
    private static final String INDEX = "INDEX";
    private int currentIndex;
    private String playPath;
    private OnPracticeFinishListener mOnPracticeFinishListener;

    public static PracticeBgVideoFragment newInstance(int index, String url, View.OnClickListener listener, OnPracticeFinishListener onPracticeFinishListener) {
        PracticeBgVideoFragment fragment = new PracticeBgVideoFragment();
        fragment.setOnPracticeFinishListener(onPracticeFinishListener);
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        bundle.putString(PLAYER_PATH, url);
        fragment.setArguments(bundle);
        fragment.setOnClickListener(listener);
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_practice_bg_video;
    }

    @Override
    public void initPresenter() {

    }

    private View.OnClickListener mOnClickListener;

    public void setOnClickListener(View.OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            playPath = arguments.getString(PLAYER_PATH);
            Log.e("arguments", "" + playPath);
            currentIndex = arguments.getInt(INDEX);
        }
        String playPath = getArguments().getString(PLAYER_PATH);
        if(playPath.contains("http")){
        }else {

        }
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(v);
                }
            }
        });
    }



    public void setOnPracticeFinishListener(OnPracticeFinishListener onPracticeFinishListener) {
        mOnPracticeFinishListener = onPracticeFinishListener;
    }

    public interface OnPracticeFinishListener {
        boolean onPracticeFinish(int index);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}




