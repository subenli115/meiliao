package com.ziran.meiliao.im.activity.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.AutoRelativeLayout;
import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonwidget.GlideCircleWithBorder;
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
import com.ziran.meiliao.widget.GlideCircleTransform;

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
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.toolbar1)
    Toolbar toolbar1;
    @Bind(R.id.arl_content)
    AutoRelativeLayout arl_content;
    @Bind(R.id.iv_head)
    ImageView ivHead;
    @Bind(R.id.iv_bg)
    ImageView ivBg;
    @Bind(R.id.nsv)
    NestedScrollView nsv;
    private List<WechatParentBean> parentBeanList;
    List<WechatListDataBean.DataBean.RecordsBean> list1;
    private String isSelf;
    private String userId;
    private boolean isResult=false;

    @Override
    public void returnData(WechatListDataBean result) {
        List<WechatListDataBean.DataBean.RecordsBean> records = result.getData().getRecords();
        if(records.size()==0&&!isSelf.equals("1")){
                iRecyclerView.setHeadView(LayoutInflater.from(getContext()).inflate(R.layout.item_wechat_empty, null));
        }else {
            if(isResult){
                mAdapter.replaceAll(dayList(records));
                isResult=false;
            }else {
                updateData(dayList(records));
            }
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
                Log.e("timetime", "" + time);
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
        wechatParentAdapter = new WechatParentAdapter(getContext(), mRxManager,null, new WechatParentAdapter.ActivityMultiItemType() {
        }, isSelf);
        return wechatParentAdapter;
    }

    @Override
    protected void initView() {
        isSelf = getIntentExtra(getActivity().getIntent(), "isSelf");
        userId = getIntentExtra(getActivity().getIntent(), "userId");
        String homepageImages = getIntentExtra(getActivity().getIntent(), "homepageImages");
        String avatar = getIntentExtra(getActivity().getIntent(), "avatar");
        super.initView();
        Context context = getContext();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(homepageImages!=null){}
        Glide.with(context).load(homepageImages).into(ivBg);
        Glide.with(context).load(avatar).error(R.drawable.jmui_head_icon).into(ivHead);
        if (isSelf!=null&&isSelf.equals("1")) {
            View headView = LayoutInflater.from(context).inflate(R.layout.item_head_wechat, null);
            ImageView iv = (ImageView) headView.findViewById(R.id.iv_add);
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ReleaseWechatActivity.startAction(REQUEST_CODE_A);
                }
            });
            iRecyclerView.setHeadView(headView);
        }

        setAvatorChange();
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

    /**
     * 根据百分比改变颜色透明度
     */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }
    /**
     * 渐变toolbar背景
     */
    private void setAvatorChange() {
        nsv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float percent =scrollY * 1.0f/(arl_content.getY()-toolbar1.getHeight());
                Rect scrollRect = new Rect();
                nsv.getHitRect(scrollRect);
                //子控件在可视范围内（至少有一个像素在可视范围内）
                if (toolbar.getLocalVisibleRect(scrollRect)) {
                    toolbar1.setVisibility(View.GONE);
                } else {
                    toolbar1.setVisibility(View.VISIBLE);
                }
                if(percent>1){
                    percent=1;
                }
                int i = changeAlpha(Color.parseColor("#FAFAFA"),percent);
                toolbar1.setBackgroundColor(i);
            }
        });
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_wechat_home;
    }

    @Override
    protected void initBundle(Bundle extras) {
        try {

        } catch (Exception e) {
        }
    }


    @Override
    protected void loadData() {
        Map<String, String> map = MapUtils.getDefMap(true);
        map.put("userId", userId);
        map.put("size", "10");
        map.put("current", page + "");
        mPresenter.getData(ADMIN_SPACE_APPPAGE, map, WechatListDataBean.class);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}