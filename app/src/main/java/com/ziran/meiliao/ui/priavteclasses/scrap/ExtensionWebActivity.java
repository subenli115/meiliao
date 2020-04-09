//package com.ziran.meiliao.ui.priavteclasses.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.ziran.meiliao.R;
//import com.ziran.meiliao.constant.AppConstant;
//import com.ziran.meiliao.ui.base.CommonWebActivity;
//import com.ziran.meiliao.utils.CheckUtil;
//
///**
// * Created by Administrator on 2017/3/20.
// */
//
//public class ExtensionWebActivity extends CommonWebActivity {
//
//
//
//    public static void startAction(Context context, String url) {
//        if (CheckUtil.check(context)) {
//            Intent intent = new Intent(context, ExtensionWebActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putString(AppConstant.SPKey.EXTRAS_URL, url);
//            intent.putExtras(bundle);
//            context.startActivity(intent);
//        }
//    }
//    @Override
//    public void initView() {
//        super.initView();
//        tvHeadviewTitle.setText(R.string.act_detail);
//    }
//
//    @Override
//    protected void initBundle(Bundle extras) {
//        super.initBundle(extras);
//    }
//}
