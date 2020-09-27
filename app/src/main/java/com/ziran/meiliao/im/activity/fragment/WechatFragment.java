package com.ziran.meiliao.im.activity.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.CommonRecycleViewAdapter;
import com.ziran.meiliao.im.activity.ReleaseWechatActivity;
import com.ziran.meiliao.im.adapter.WechatParentAdapter;
import com.ziran.meiliao.ui.base.CommonContract;
import com.ziran.meiliao.ui.base.CommonModel;
import com.ziran.meiliao.ui.base.CommonPresenter;
import com.ziran.meiliao.ui.base.CommonRefreshFragment;
import com.ziran.meiliao.ui.bean.WechatListDataBean;
import com.ziran.meiliao.ui.bean.WechatParentBean;
import com.ziran.meiliao.utils.MapUtils;
import com.ziran.meiliao.utils.NewCacheUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

import static com.ziran.meiliao.constant.ApiKey.ADMIN_SPACE_APPPAGE;


/**
 * Created by Administrator on 2019/1/31.
 */
public class WechatFragment extends CommonRefreshFragment<CommonPresenter, CommonModel> implements CommonContract

        .View<WechatListDataBean> {
    private static final int REQUEST_CODE_A = 2;
    private String tagId;
    private WechatParentAdapter wechatParentAdapter;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntb;
    private List<WechatParentBean> parentBeanList;
    List<WechatListDataBean.DataBean.RecordsBean> list1;
    private String isSelf;
    private String userId;
    private boolean isResult=false;
    private NewCacheUtil newCacheUtil;
    private List dataList;

    @Override
    public void returnData(WechatListDataBean result) {
        List<WechatListDataBean.DataBean.RecordsBean> records = result.getData().getRecords();
            if(isResult){
                mAdapter.replaceAll(dayList(records));
                isResult=false;
            }else {
                updateData(dayList(records));
        }

    }

    //获取日期列表
    public List<WechatParentBean> dayList(List<WechatListDataBean.DataBean.RecordsBean> list) {
        parentBeanList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> listTemp = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            try {
                Date date = sdf.parse(list.get(i).getCreateTime());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String time = calendar.get(Calendar.YEAR) + "年" + calendar.get(Calendar.MONTH) + "月" + calendar.get(Calendar.DATE);
                if (!listTemp.contains(time)) {
                    listTemp.add(time);
                    WechatParentBean wechatParentBean = new WechatParentBean();
                    wechatParentBean.setTime(date);
                    List<WechatListDataBean.DataBean.RecordsBean> list1 = new ArrayList<>();
                    list1.add(list.get(i));
                    wechatParentBean.setList(list1);
                    parentBeanList.add(wechatParentBean);
                } else {
                    for (int k = 0; k < listTemp.size(); k++) {
                        if (listTemp.get(k).equals(time)) {
                            WechatParentBean wechatParentBean = parentBeanList.get(k);
                            if (wechatParentBean.getList() == null) {
                                list1 = new ArrayList<>();
                            } else {
                                list1 = wechatParentBean.getList();
                            }
                            list1.add(list.get(i));
                            if (list1.size() == 2) {
                            }
                            wechatParentBean.setList(list1);
                            parentBeanList.set(k, wechatParentBean);
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return parentBeanList;
    }


    @Override
    public CommonRecycleViewAdapter getAdapter() {
        loadedTip.setEmptyMsg("TA还没有发动态哦",R.mipmap.icon_wechat_empty);
        wechatParentAdapter = new WechatParentAdapter(getContext(), mRxManager,null, new WechatParentAdapter.ActivityMultiItemType() {
        }, isSelf);
        return wechatParentAdapter;
    }

    @Override
    protected void initView() {
         newCacheUtil = new NewCacheUtil(getContext());
        dataList = newCacheUtil.getDataList("wechat"+userId, WechatParentBean.class);
        isSelf = getIntentExtra(getActivity().getIntent(), "isSelf");
        userId = getIntentExtra(getActivity().getIntent(), "userId");
        super.initView();
        if(!isSelf.equals("1")){
            ntb.setRightImagVisibility(false);
        }
        ntb.setOnRightImagListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReleaseWechatActivity.startAction(REQUEST_CODE_A);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_A: //返回的结果是来自于Activity B
                if (resultCode == Activity.RESULT_OK) {
                    page = 1;
                    loadData();
                    isResult=true;
                }

            default:
                break;
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_wechat_home;
    }

    @Override
    protected void initBundle(Bundle extras) {
    }


    @Override
    protected void loadData() {
            Map<String, String> map = MapUtils.getDefMap(true);
            map.put("userId", userId);
            map.put("current", page + "");
            map.put("size", "10");
            mPresenter.getData(ADMIN_SPACE_APPPAGE, map, WechatListDataBean.class);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}