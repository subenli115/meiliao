package com.ziran.meiliao.ui.settings.activity;

import android.support.v4.app.Fragment;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.settings.fragment.CollectActivityFragment;
import com.ziran.meiliao.ui.settings.fragment.CollectAlbumFragment;
import com.ziran.meiliao.ui.settings.fragment.CollectBootCampFragment;
import com.ziran.meiliao.ui.settings.fragment.CollectCourseFragment;
import com.ziran.meiliao.ui.settings.fragment.CollectMusicFragment;

import java.util.List;


/**
 * 收藏界面
 * Created by Administrator on 2017/1/4.
 */

public class MeCollectActivity extends CommonDeleteActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_me_collect;
    }

    @Override
    public void initView() {
        super.initView();
        ntb.setTitleText(getString(R.string.myfavorites));
        tips = "取消收藏";
        btnDelete.setText("取消收藏");
    }

    @Override
    protected void initFragments(List<Fragment> fragments) {
        fragments.add(new CollectMusicFragment());
        fragments.add(new CollectAlbumFragment());
        fragments.add(new CollectCourseFragment());
        fragments.add(new CollectActivityFragment());
        fragments.add(new CollectBootCampFragment());
    }


    @Override
    public int getTabsArrayId() {
        return R.array.follow_tabs;
    }

}
