package com.ziran.meiliao.ui.workshops.presenter;

import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.envet.NewRequestCallBack;
import com.ziran.meiliao.ui.bean.MultiItemBean;
import com.ziran.meiliao.ui.bean.WorkshopsMainBean;
import com.ziran.meiliao.ui.workshops.adapter.WorkshopsMainAdapter;
import com.ziran.meiliao.ui.workshops.contract.QJGMainContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * des:图片列表
 * Created by xsf
 * on 2016.09.12:01
 */
public class QJGMainPresenter extends QJGMainContract.Presenter {


    @Override
    public void getData(String url, Map<String, String> params) {
        mModel.post(url, params, new NewRequestCallBack<WorkshopsMainBean>(WorkshopsMainBean.class) {
            @Override
            protected void onSuccess(WorkshopsMainBean result) {
//                List<String> img = QJGDataUtil.getImg();
                List<MultiItemBean> data = new ArrayList<>();
                data.add(new MultiItemBean(WorkshopsMainAdapter.TYPE_HEAD_VIEW, result.getData().getPics()));
                WorkshopsMainBean.DataBean resultData = result.getData();
                if (EmptyUtils.isNotEmpty(resultData.getMissionBuiltList())){
                    data.add(new MultiItemBean(WorkshopsMainAdapter.TYPE_LIST_VIEW, resultData.getMissionBuiltList(), "课程推荐"));
                }
                if (EmptyUtils.isNotEmpty(resultData.getCrownFundList())){
                    data.add(new MultiItemBean(WorkshopsMainAdapter.TYPE_LIST_VIEW, resultData.getCrownFundList(), "众筹课程"));
                }

                if (EmptyUtils.isNotEmpty(resultData.getFamousTeacherList())){
                    data.add(new MultiItemBean(WorkshopsMainAdapter.TYPE_LIST_VIEW, resultData.getFamousTeacherList(), "名师教场"));
                }
                if (EmptyUtils.isNotEmpty(resultData.getActis())){
                    data.add(new MultiItemBean(WorkshopsMainAdapter.TYPE_LIST_VIEW, resultData.getActis(), "工作坊"));
                }
                mView.showData(data);
            }
        });
    }
}
