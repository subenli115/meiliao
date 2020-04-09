package com.ziran.meiliao.ui.decompressionmuseum.fragment;

import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.envet.MyOnScrollListener;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.AlbumListBean;
import com.ziran.meiliao.ui.bean.AlbumRecBean;
import com.ziran.meiliao.ui.bean.HeadData;
import com.ziran.meiliao.ui.decompressionmuseum.activity.AlbumDetailActivity;
import com.ziran.meiliao.ui.decompressionmuseum.adapter.CategoryRecommendAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.MusicPanelFloatManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rx.functions.Action1;

/**
 * 减压馆-推荐Fragment
 * Created by Administrator on 2017/1/7.
 */

public class CategoryRecommendFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract.View<AlbumRecBean> {
   //入口(首页或正念练习)
    private int fromType;
    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();
        cacheData = new ConcurrentHashMap<>();
        sizeData = new ConcurrentHashMap<>();
        fromType = getActivity().getIntent().getIntExtra(AppConstant.SPKey.ALBUM_FLAG,0);
        setRecyclerEnabled(false, true);
        mRxManager.on(AppConstant.RXTag.CATEGORY_MORE_CLICK, new Action1<HeadData>() {
            @Override
            public void call(HeadData headData) { //点击换一批
                int headId = headData.getId();
                List<AlbumListBean> albumListBeen = new ArrayList<>(cacheData.get(headId)); // 0 , 3 ,6
                List mAdapterAll = mAdapter.getAll();
                int firstIndex = headId ==0?1:sizeData.get(headId-1) +1;
                int size = albumListBeen.size()>5?3:albumListBeen.size()-3;
                for (int i = 0; i < size; i++) {
                    albumListBeen.remove(mAdapterAll.get(firstIndex+i));
                }
                Collections.shuffle(albumListBeen);//打乱顺序
                for (int i = 0; i < size; i++) {
                    mAdapter.replaceAt(firstIndex+i,albumListBeen.get(i));
                }
            }
        });
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        iRecyclerView.addOnScrollListener(new MyOnScrollListener(AppConstant.RXTag.HOME_MUSIC_PLANE_SHOW_OR_HIDE));
        return new CategoryRecommendAdapter(getContext(), new CategoryRecommendAdapter.ReMultiItemTypeSupport());
    }

    /**
     * 加载数据
     */
    @Override
    protected void loadData() {
        mPresenter.getData(ApiKey.ALBUM_GET_REC_DATA, MapUtils.getDefMap(false), AlbumRecBean.class);
    }

    @Override
    public void onRefresh() {
        cacheData.clear();
        sizeData.clear();
        super.onRefresh();
    }

    /**
     *  item点击监听
     */
    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        int itemViewType = mAdapter.getItemViewType(position);
        switch (itemViewType) {
            case MultiItemRecycleViewAdapter.TYPE_DATA:
                if (!CheckUtil.check(getContext(), getView())) return;
                AlbumListBean albumListBean = (AlbumListBean) o;
                AlbumDetailActivity.startAction(getActivity(), String.valueOf(albumListBean.getId()), fromType);
                break;
        }
    }
    //缓存数据
    public Map<Integer,List<AlbumListBean>> cacheData ;
    private Map<Integer,Integer> sizeData;
    @Override
    public void returnData(AlbumRecBean result) {
        List<AlbumRecBean.DataBean> resultData = result.getData();
        List list  = new ArrayList();
        HeadData headData;
        int size;
        try {
            for (int i = 0; i < resultData.size(); i++) {
                AlbumRecBean.DataBean dataBean = resultData.get(i);
                size = dataBean.getAlbumList().size();
                headData = new HeadData(i, dataBean.getName(), i!=0, "换一批");
                headData.setLastIndex(2);
                headData.setShowMoreTitle(size>3);
                size = size<3?size:3;
                if (i==0){
                    sizeData.put(i,size+1);
                }else{
                    sizeData.put(i,(sizeData.get(i-1)+size+1));
                }
                headData.setSize(size);
                list.add(headData);
                cacheData.put(i,dataBean.getAlbumList());
                list.addAll(dataBean.getAlbumList().subList(0,size));
            }
        }catch ( Exception e){
            e.printStackTrace();
        }
        updateData(list);
    }
    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible  ) {
            MusicPanelFloatManager.getInstance().setIsShowingAnimation(true);
            MusicPanelFloatManager.getInstance().updatePlayState();
        }
    }
}
