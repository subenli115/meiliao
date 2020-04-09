package com.ziran.meiliao.ui.decompressionmuseum.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.app.MyAPP;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.constant.IConstants;
import com.ziran.meiliao.ui.base.ShareActivity;
import com.ziran.meiliao.ui.decompressionmuseum.fragment.AlbumPlayerListFragment;
import com.ziran.meiliao.ui.decompressionmuseum.fragment.AlbumPlayerNewFragment;
import com.ziran.meiliao.utils.DownloadUtil;
import com.ziran.meiliao.utils.ImageAnimation;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/1/20.
 */

public class AlbumPlayerActivity extends ShareActivity {


    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    private AlbumPlayerNewFragment audioPlayerFragment;
    private AlbumPlayerListFragment audioPlayerListFragment;


    public static void startAction(Context context, String albumId) {
        Intent intent = new Intent(context, AlbumPlayerActivity.class);
        if (EmptyUtils.isNotEmpty(albumId)) {
            intent.putExtra(AppConstant.SPKey.ALBUM_ID, albumId);
        }
        context.startActivity(intent);
    }

    public static void startAction(Context context, String albumId,boolean isHome) {
        Intent intent = new Intent(context, AlbumPlayerActivity.class);
        if (EmptyUtils.isNotEmpty(albumId)) {
            intent.putExtra(AppConstant.SPKey.ALBUM_ID, albumId);
            intent.putExtra("isHome",isHome);
        }
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_jyg_album_player;
    }

    @Override
    public void initPresenter() {
    }

    public  void setTitleBar(boolean isVisit) {
        if(isVisit){
            ntb.setVisibility(View.VISIBLE);
        }else {
            ntb.setVisibility(View.GONE);
        }
    }

    @Override
    public void initView() {
        DownloadUtil.isPlayerActivity = true;
        ntb.setTvLeftVisiable(true, true);
        ntb.setTitleIcon(R.mipmap.ic_jyg_player_nav_down);
        ntb.setTitleOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showList(!isShowList);
            }
        });
        int from = getIntentExtra(getIntent(), IConstants.FLAG, -5);
        if (from != -5) {
            MyAPP.mServiceManager.setClickFrom(from);
        }

        showList(isShowList);

    }



    boolean isShowList = false;

    public void setTitle(String title) {
        ntb.setTitleText(title);
    }

    public void showList(boolean chage) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (chage) {
            if (audioPlayerListFragment == null) {
                audioPlayerListFragment = new AlbumPlayerListFragment();
                ft.add(R.id.frameLayout, audioPlayerListFragment, "AlbumPlayerListFragment");
            } else {
                audioPlayerListFragment.notifyDataSetChanged();
                ft.show(audioPlayerListFragment);
            }
            ntb.setTitleIcon(R.mipmap.ic_jyg_player_nav_up);
            if (audioPlayerFragment != null) ft.hide(audioPlayerFragment);
        } else {
            if (audioPlayerFragment == null) {
                audioPlayerFragment = new AlbumPlayerNewFragment();
                ft.add(R.id.frameLayout, audioPlayerFragment, "AlbumPlayerNewFragment");
            } else {
                ft.show(audioPlayerFragment);
            }
            ntb.setTitleIcon(R.mipmap.ic_jyg_player_nav_down);
            ImageAnimation.get().onDestroy(false);
            if (audioPlayerListFragment != null) ft.hide(audioPlayerListFragment);
        }
        ft.commit();
        this.isShowList = chage;
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DownloadUtil.isPlayerActivity = false;
    }
}
