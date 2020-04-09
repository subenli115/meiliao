package com.ziran.meiliao.ui.priavteclasses.activity;

import android.os.Bundle;

import com.ziran.meiliao.R;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.ShareActivity;
import com.ziran.meiliao.ui.priavteclasses.fragment.NoBuyZLFragment;
import com.ziran.meiliao.ui.priavteclasses.fragment.ZLAllAudioFragment;
import com.ziran.meiliao.utils.MapUtils;

import java.util.Map;


public class NoBuyZLActivity extends ShareActivity {

    private int back;
    private String isBuy;

    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initPresenter() {

    }
    private int formType;
    @Override
    protected void initBundle(Bundle extras) {
        try {
            specColumnId = extras.getString(AppConstant.ExtraKey.SUBSCRIPTION_ID);
            formType = extras.getInt(AppConstant.ExtraKey.FROM_TYPE,2);
             back = extras.getInt("back",0);
             isBuy = extras.getString("isbuy");
        } catch (Exception e) {
            specColumnId = "";
            e.printStackTrace();
        } finally {
            stringMap = MapUtils.getOnlyCan(AppConstant.ExtraKey.SUBSCRIPTION_ID, specColumnId);
            stringMap.put("courseId", specColumnId);
        }
    }

    String specColumnId;
    private Map<String, String> stringMap;
    @Override
    public void initView() {
        if(back==1){
            Bundle bundle = new Bundle();
            bundle.putInt("back",back);
            initFragment(new NoBuyZLFragment(),bundle);
            return;
        }
        if (isBuy.equals("1")){
            initFragment(new ZLAllAudioFragment());
        }else{
            initFragment(new NoBuyZLFragment());
    }
    }
}
