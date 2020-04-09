package com.ziran.meiliao.ui.priavteclasses.fragment;

import android.os.Message;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.imagePager.BigImagePagerActivity;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.SubscribeAudioDataBean;
import com.ziran.meiliao.ui.priavteclasses.adapter.SubscribePPTAdapter;
import com.ziran.meiliao.ui.priavteclasses.model.BuyCallBack;

import java.util.List;

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

public class SJKSubscribePPTFragment extends CommonRefreshFragment {


    private List<String> mStringList;
    private BuyCallBack mBuyCallBack;
    private String prefix;
    private SubscribePPTAdapter mSubscribePPTAdapter;

    @Override
    protected void initView() {
        super.initView();
        mRxManager.on(AppConstant.RXTag.SUBSCRIBE_UPDATE, new Action1<Message>() {
            @Override
            public void call(Message msg) {
                switch (msg.what) {
                    case 2:
                       checkIsBuy();
                        break;
                    case 10:
                        SubscribeAudioDataBean.DataBean bean = (SubscribeAudioDataBean.DataBean) msg.obj;
                         prefix = bean.getPrefix();

                        mSubscribePPTAdapter.setPrefix(bean.getPrefix());
                        mStringList = bean.getPpt();
                        checkIsBuy();
                        break;
                }
            }
        });
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        setRecyclerEnabled(false);
        loadedTip.setEmptyMsg("购买课程方可开启PPT", R.mipmap.course_ic_ppt);
        mSubscribePPTAdapter = new SubscribePPTAdapter(getContext(), R.layout.item_sjk_subscribe_audio_ppt);
        return mSubscribePPTAdapter;
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        super.onItemClick(parent, view, o, position);
        if (EmptyUtils.isNotEmpty(mStringList)){
            BigImagePagerActivity.startImagePagerActivity(getActivity(),mStringList,position,prefix);
        }
    }

    @Override
    protected void loadData() {
        checkIsBuy();
    }

    private void checkIsBuy() {
        if (mBuyCallBack==null) return;
//        if (mBuyCallBack.getIsBuy()) {
            if (EmptyUtils.isNotEmpty(mStringList)){
                updateData(mStringList);
            }else{
                loadedTip.setEmptyMsg("该课程没有PPT",R.mipmap.ic_empty_nocontent);
                loadedTip.setLoadingTip(LoadingTip.LoadStatus.empty);
            }
//        } else {
//            loadedTip.setLoadingTip(LoadingTip.LoadStatus.empty);
//        }
    }


    public void setBuyCallBack(BuyCallBack buyCallBack) {
        mBuyCallBack = buyCallBack;
    }
}
