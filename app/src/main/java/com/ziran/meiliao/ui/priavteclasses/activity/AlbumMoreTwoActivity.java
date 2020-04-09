package com.ziran.meiliao.ui.priavteclasses.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.bean.AlbumTwoMoreBean;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.priavteclasses.adapter.AlbumListLandSpaceAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.PRACTICE_GETMOREALBUM;

/**
 * 芳芳 on 2019/1/30.
 */

public class AlbumMoreTwoActivity extends CommonHttpActivity<CommonPresenter, CommonModel> implements CommonContract

        .View<AlbumTwoMoreBean>  {
    private static HeadData mData;
    @Bind(R.id.recyclerView)
    public RecyclerView recyclerView;
    @Bind(R.id.ntb_title)
    public NormalTitleBar ntb;
    private AlbumListLandSpaceAdapter adapter;

    public static void startAction(Context mContext, HeadData headData) {
        Intent intent = new Intent(mContext, AlbumMoreTwoActivity.class);
        mData=headData;
        mContext.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_album_more;
    }

    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }

    @Override
    public void initView() {
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleText(mData.getTitle());
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("tagId", mData.getId()+"");
        mPresenter.postData(PRACTICE_GETMOREALBUM,defMap,AlbumTwoMoreBean.class);
    }

    @Override
    public void returnData(AlbumTwoMoreBean result) {
        AlbumTwoMoreBean.DataBean data = result.getData();
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2,GridLayoutManager.VERTICAL,false));
        adapter = new AlbumListLandSpaceAdapter(mContext, data.getDetailList());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AlbumListLandSpaceAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position, String url) {
            }
        });
    }
}
