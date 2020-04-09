package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.bean.AlbumBgBean;
import com.ziran.meiliao.ui.bean.AlbumMoreBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.EmotionAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2019/1/11.
 */

public class EmotionActivity extends BaseActivity {
    @Bind(R.id.ntb_title)
    public NormalTitleBar ntb;
    @Bind(R.id.recyclerView)
    public RecyclerView recyclerView;
    private EmotionAdapter adapter;
    ArrayList<AlbumBgBean> list;
    private ArrayList<AlbumMoreBean.DataBean.TagListBean.NextTagListBean> nextTagList;

    public static void startAction(Context mContext, List<AlbumMoreBean.DataBean.TagListBean.NextTagListBean> nextTagList) {

        Intent intent = new Intent(mContext, EmotionActivity.class);
        intent.putParcelableArrayListExtra("nextTagList", (ArrayList<? extends Parcelable>) nextTagList);
        mContext.startActivity(intent);
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_emotion_big;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public void initView() {
        if(getIntent()!=null){
            nextTagList = getIntent().getParcelableArrayListExtra("nextTagList");
        }
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText(getString(R.string.all_emotion));
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,3,GridLayoutManager.VERTICAL,false));
        adapter = new EmotionAdapter(mContext, nextTagList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new EmotionAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, String url) {
            }
        });
    }
}
