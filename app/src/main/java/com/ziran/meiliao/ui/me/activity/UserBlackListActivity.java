package com.ziran.meiliao.ui.me.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.common.commonwidget.LoadingTip;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.im.utils.ToastUtil;
import com.ziran.meiliao.ui.bean.UserBean;
import com.ziran.meiliao.ui.me.adapter.BlackListAdapter;
import com.ziran.meiliao.ui.settings.activity.BindPhoneActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.DownloadAvatarCallback;
import cn.jpush.im.android.api.callback.GetAvatarBitmapCallback;
import cn.jpush.im.android.api.callback.GetBlacklistCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;


/**
 * 我的界面
 * Created by Administrator on 2017/1/4.
 */

public class UserBlackListActivity extends BaseActivity {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntb;
    @Bind(R.id.tv_num)
    TextView tvNum;
    @Bind(R.id.loadedTip)
    LoadingTip loadedTip;



    public static void startAction(Context activity) {
        Intent intent = new Intent(activity, UserBlackListActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_user_blacklist;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

        JMessageClient.getBlacklist(new GetBlacklistCallback() {
            @Override
            public void gotResult(int i, String s, List<UserInfo> list) {
                if(list.size()==0){
                    loadedTip.setEmptyMsg("什么都没有~",R.mipmap.load_empty_bg);
                }else {
                    tvNum.setVisibility(View.VISIBLE);
                }
                BlackListAdapter mAdapter = new BlackListAdapter(list, mContext);
                mAdapter.setItemClickListener(new BlackListAdapter.OnItemClickListener() {
                    @Override
                    public void itemClick(int position, int itemId) {
                        List<String> name = new ArrayList<>();
                        name.add(list.get(position).getUserName());
                        JMessageClient.delUsersFromBlacklist(name, new BasicCallback() {
                            @Override
                            public void gotResult(int responseCode, String responseMessage) {
                                if (responseCode == 0) {
                                    list.remove(position);
                                    mAdapter.updateData(list, mContext);
                                    tvNum.setText("已拉黑人数 " + list.size());
                                    if(list.size()==0){
                                        loadedTip.setEmptyMsg("什么都没有~",R.mipmap.load_empty_bg);

                                    }
                                } else {
                                    ToastUtil.shortToast(mContext, "移除失败");
                                }
                            }
                        });

                    }
                });
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                recyclerView.setAdapter(mAdapter);
                tvNum.setText("已拉黑人数 " + list.size());
            }
        });
        ntb.setTitleText("黑名单");
    }

}