package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.AlbumClassifyBean;
import com.ziran.meiliao.ui.bean.AlbumTagBean;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.priavteclasses.activity.AlbumMoreTwoActivity;
import com.ziran.meiliao.ui.priavteclasses.adapter.AlbumClassIfyAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.functions.Action1;

import static com.ziran.meiliao.constant.ApiKey.ALBUM_GETALBUMCATALOG;

/**
 * Created by Administrator on 2019/2/13.
 */

public class AlbumClassifyFragment extends CommonRefreshFragment<CommonPresenter,CommonModel> implements CommonContract
        .View<AlbumClassifyBean>{


    @Override
    public void initPresenter() {
        if (mModel != null && mPresenter != null) {
            mPresenter.setVM(this, mModel);
        }
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_album_classify;
    }

    @Override
    public void initView() {
        super.initView();

        mRxManager.on(AppConstant.RXTag.MAIN_HOME_MORE_CLICK, new Action1<HeadData>() {
            @Override
            public void call(HeadData headData) {
                AlbumMoreTwoActivity.startAction(getContext(),headData);
        }
        });
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) ;
        iRecyclerView.setLayoutManager(linearLayoutManager);
        return new AlbumClassIfyAdapter(getContext());
    }

    @Override
    protected void loadData() {
        Map<String, String> defMap = MapUtils.getDefMap(true);
        defMap.put("page",String.valueOf(page));
        mPresenter.postData(ALBUM_GETALBUMCATALOG,defMap,AlbumClassifyBean.class);
    }


    @Override
    public void returnData(AlbumClassifyBean result) {
        List list = new ArrayList();
        List<AlbumClassifyBean.DataBean.TagListBean> tagList = result.getData().getTagList();
        for(int i=0;i<tagList.size();i++){
            if (EmptyUtils.isNotEmpty(tagList.get(i).getNextTagList())) {
                HeadData headData = HeadData.create(HeadData.Type.TITLE, tagList.get(i).getName(), false);
                headData.setId(tagList.get(i).getId());
                list.add(headData);
                AlbumTagBean albumTagBean = new AlbumTagBean();
                List<AlbumClassifyBean.DataBean.TagListBean.NextTagListBean> nextTagList = tagList.get(i).getNextTagList();
                for(int k=0;k<nextTagList.size();k++){
                    nextTagList.get(k).setName(tagList.get(i).getName());
                }
                albumTagBean.setNextTagList(tagList.get(i).getNextTagList());
                list.add(albumTagBean);
            }
        }
        updateData(list);
    }

}
