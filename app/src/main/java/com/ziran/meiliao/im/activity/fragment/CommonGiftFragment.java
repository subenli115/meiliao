package com.ziran.meiliao.im.activity.fragment;

import android.os.Bundle;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.im.adapter.CommonGiftAdapter;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.CommGiftListBean;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_GIFTBYRECEIVEUSERID;
import static com.ziran.meiliao.constant.ApiKey.ADMIN_GIFTRECORD_GETGIFTBYID;


/**
 * Created by Administrator on 2019/1/31.
 */
public class CommonGiftFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract

        .View<CommGiftListBean> {
    private static final int REQUEST_CODE_A = 2;
    private String tagId;
    private CommonGiftAdapter adapter;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntb;
    private String userId;
    private String type;

    @Override
    public void returnData(CommGiftListBean result) {
        updateData(result.getData().getRecords());
    }



    @Override
    public CommonRecycleViewAdapter getAdapter() {
        adapter = new CommonGiftAdapter(getContext(), mRxManager,null, new CommonGiftAdapter.ActivityMultiItemType() {
        });
        return adapter;
    }

    @Override
    protected void initView() {
         type = getIntentExtra("type");

        super.initView();
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_common_list;
    }

    @Override
    protected void initBundle(Bundle extras) {
        try {

        } catch (Exception e) {
        }
    }


    @Override
    protected void loadData() {
        if(type.equals("")){
            Map<String, String> map = MapUtils.getDefMap(true);
            map.put("receiveUserId", MyAPP.getUserId());
            map.put("current", page + "");
            mPresenter.getData(ADMIN_GIFTBYRECEIVEUSERID, map, CommGiftListBean.class);

        }else {
            Map<String, String> map = MapUtils.getDefMap(true);
            map.put("objectId", type);
            map.put("type","2");
            map.put("current", page + "");
            mPresenter.getData(ADMIN_GIFTRECORD_GETGIFTBYID, map, CommGiftListBean.class);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}