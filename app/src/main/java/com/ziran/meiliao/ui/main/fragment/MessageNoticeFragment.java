package com.ziran.meiliao.ui.main.fragment;

import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.common.okhttp.Result;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.DynamicBean;
import com.ziran.meiliao.ui.bean.HomeMenuBean;
import com.ziran.meiliao.ui.bean.NoticeNumsBean;
import com.ziran.meiliao.ui.main.adapter.MessageNoticeAdapter;
import com.ziran.meiliao.utils.MapUtils;

import java.util.List;
import java.util.Map;

/**
 *
 * 消息通知
 *
 */
public class MessageNoticeFragment extends CommonRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<Result> {


    private List<HomeMenuBean.DataBean> data;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_community_follow;
    }

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg("什么都没有~",R.mipmap.load_empty_bg);
        iRecyclerView.setLoadMoreEnabled(false);
        iRecyclerView.setRefreshEnabled(false);
        return new MessageNoticeAdapter(getContext());
    }

    @Override
    protected void loadData() {
        Map<String, String> defMap1 = MapUtils.getDefMap(true);
        defMap1.put("type", "2");
        mPresenter.getData(ApiKey.ADMIN_APPMENU_PAGE, defMap1, HomeMenuBean.class);
    }


    @Override
    public void returnData(Result result) {
        if(result instanceof HomeMenuBean){
             data = ((HomeMenuBean) result).getData();
             String serviceId = data.get(0).getRelationship();

            Map<String, String> defMap = MapUtils.getDefMap(true);
                defMap.put("noticeUserId", MyAPP.getUserId());
                mPresenter.getData(ApiKey.ADMIN_NEWSNOTICE_LIST, defMap, NoticeNumsBean.class);
        }else if(result instanceof NoticeNumsBean){
            if(data!=null&&data.size()>0){
                List<NoticeNumsBean.DataBean> data1 =  ((NoticeNumsBean) result).getData();
                if(data1.size()>0){
                    for(int i=0;i<data.size();i++){
                        for(int k=0;k<data1.size();k++){
                            if(data.get(i).getRelationship().equals(data1.get(k).getType())){
                                data.get(i).setUpdateTime(data1.get(k).getCreateTime());
                                data.get(i).setNumber(data1.get(k).getNumber());
                            }
                        }
                    }
                }
                updateData(data);
            }
        }
    }
}