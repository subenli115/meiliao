package com.ziran.meiliao.ui.settings.fragment;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.constant.ApiKey;
import com.ziran.meiliao.entry.ClassBean;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.settings.activity.BuyOrderDetailsActivity;
import com.ziran.meiliao.ui.settings.adapter.BuyClassAdapter;
import com.ziran.meiliao.utils.CheckUtil;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;


public class BuyClassFragment extends CommonRefreshFragment<CommonPresenter, CommonModel>
        implements CommonContract.View<ClassBean> {

    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg(getString(R.string.emtry_class), R.mipmap.ic_empty_course);
        return new BuyClassAdapter(getContext(), R.layout.item_main_me_class_buy);
    }

    @Override
    protected void loadData() {
        Map<String, String> onlyPage = MapUtils.getOnlyPage(page);
        onlyPage.put("size","11");
        mPresenter.postData(ApiKey.ACTIVITY_GETACSUCLIST, onlyPage, ClassBean.class);
    }

    @Override
    public void returnData(ClassBean result) {
        updateData(result.getData().getList());
    }

    @Override
    public void onItemClick(ViewGroup parent, View view, Object o, int position) {
        if (!CheckUtil.check(getContext(),getView())) return;
        ClassBean.DataBean.ListBean dataBean = EmptyUtils.parseObject(o);
        if (EmptyUtils.isNotEmpty(dataBean)) {
            Intent intent=new Intent(getContext(), BuyOrderDetailsActivity.class);
            intent.putExtra("ClassBean",dataBean);
            startActivity(intent);

        }
    }
}
