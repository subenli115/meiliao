package com.ziran.meiliao.ui.priavteclasses.activity;



import com.ziran.meiliao.R;
import com.ziran.meiliao.common.base.BaseActivity;
import com.ziran.meiliao.ui.priavteclasses.fragment.ZLAllAudioFragment;


public class ZLAllAudioActivity extends BaseActivity {


    private ZLAllAudioFragment zlAllAudioFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        zlAllAudioFragment = new ZLAllAudioFragment();
        initFragment(zlAllAudioFragment);
    }


}
