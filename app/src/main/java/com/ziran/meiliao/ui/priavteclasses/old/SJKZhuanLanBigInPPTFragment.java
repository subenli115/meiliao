package com.ziran.meiliao.ui.priavteclasses.old;

import android.os.Message;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.imagePager.BigImagePagerActivity;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.PPTBean;
import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.SubscribePPTAdapter;
import com.ziran.meiliao.ui.priavteclasses.util.ZhuanLanMusicDataConfig;
import com.ziran.meiliao.utils.MapUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.functions.Action1;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/7 14:00
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/8/7$
 * @updateDes ${TODO}
 */

public class SJKZhuanLanBigInPPTFragment extends CommonRefreshFragment<CommonPresenter,CommonModel>
        implements CommonContract.View<PPTBean> {


    private String prefix;
    private SubscribePPTAdapter mSubscribePPTAdapter;
    private SJKZhuanLanBigInFragment mAudioFragment;
    private String type;
    @Override
    protected void initView() {
        super.initView();
        mListMap = new HashMap<>();
        mRxManager.on(AppConstant.RXTag.BIG_IN_TAG, new Action1<Message>() {
            @Override
            public void call(Message msg) {
                switch (msg.what) {
                    case 3:
                        checkIsBuy();
                        break;
                    case 1:
                        prefix = ((ZhuanLanBigInBean.DataBean)msg.obj).getPrefix();
                        mSubscribePPTAdapter.setPrefix(prefix);
                        break;
                }
            }
        });
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false);
        setEmptyState(0);
        mSubscribePPTAdapter = new SubscribePPTAdapter(getContext(), R.layout.item_sjk_subscribe_audio_ppt);
        return mSubscribePPTAdapter;
    }

    public void setEmptyState(int state) {
        switch (state) {
            case 0:
                loadedTip.setEmptyMsg("购买课程方可开启PPT", R.mipmap.course_ic_ppt);
                break;
            case 1:
                loadedTip.setEmptyMsg("该课程暂无PPT", R.mipmap.ic_empty_nocontent);
                break;
        }
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        List<String> list = mListMap.get(ZhuanLanMusicDataConfig.getTargetId());
        if (EmptyUtils.isNotEmpty(list)) {
            BigImagePagerActivity.startImagePagerActivity(getActivity(), list, position, prefix);
        }
    }

    @Override
    protected void loadData() {
        checkIsBuy();
    }

    private boolean checkIsBuy() {
        if (mAudioFragment!=null){
            if (mAudioFragment.isBuy()){
                setEmptyState(1);
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.empty);
                return true;
            }else{
                setEmptyState(0);
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.empty);
                return false;
            }
        }
        return false;
    }
    private Map<String,String> reqMap;
    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            if (mAudioFragment!=null){
                if (mAudioFragment.isBuy()){
                    if (EmptyUtils.isEmpty(reqMap)){
                        reqMap = MapUtils.getDefMap(true);
                        reqMap.put("type",type);
                    }
                    if (mListMap.containsKey( ZhuanLanMusicDataConfig.getTargetId())){
                        mAdapter.getPageBean().setRefresh(true);
                        updateData(mListMap.get(ZhuanLanMusicDataConfig.getTargetId()));
                    }else{
                        reqMap.put(AppConstant.ExtraKey.TARGET_ID, ZhuanLanMusicDataConfig.getTargetId());
                        mPresenter.getData(ApiKey.SUBSCRIPTION_GET_COURSE_PPT, reqMap,PPTBean.class);
                        loadedTip.setLoadingTip(LoadingTip.LoadStatus.finish);
                    }
                }
            }
        }
    }

    private Map<String,List<String>> mListMap;

    public void setAudioFragment(SJKZhuanLanBigInFragment audioFragment,String type) {
        mAudioFragment = audioFragment;
        this.type = type;
    }

    @Override
    public void returnData(PPTBean result) {
        mSubscribePPTAdapter.setPrefix(result.getData().getPrefix());
        mAdapter.getPageBean().setRefresh(true);
        if (!mListMap.containsKey(ZhuanLanMusicDataConfig.getTargetId())){
            mListMap.put(ZhuanLanMusicDataConfig.getTargetId(),result.getData().getPpt());
        }
        updateData(result.getData().getPpt());
    }
}
