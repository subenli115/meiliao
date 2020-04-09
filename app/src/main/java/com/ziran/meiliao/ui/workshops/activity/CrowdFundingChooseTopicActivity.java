package com.ziran.meiliao.ui.workshops.activity;

import android.app.Activity;
import android.content.Intent;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.workshops.fragment.CrowdFundingChooseTeacherFragment;
import com.ziran.meiliao.ui.workshops.fragment.CrowdFundingChooseTopicFragment;
import com.ziran.meiliao.ui.workshops.fragment.TeamChooseTeacherFragment;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/10 14:57
 * @des ${我要众筹选择课题界面}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */

public class CrowdFundingChooseTopicActivity extends CommonHttpActivity  implements AppConstant.SearchId{

    public static void startAction(String form){
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent  =new Intent(activity,CrowdFundingChooseTopicActivity.class);
        intent.putExtra(AppConstant.ExtraKey.FROM_TYPE,form);
        activity.startActivity(intent);
    }

    public static void startAction(String form,String id,String condition){
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent  =new Intent(activity,CrowdFundingChooseTopicActivity.class);
        intent.putExtra(AppConstant.ExtraKey.FROM_TYPE,form);
        intent.putExtra(AppConstant.ExtraKey.FROM_CONDITION,condition);
        intent.putExtra(AppConstant.ExtraKey.FROM_ID,id);
        activity.startActivity(intent);
    }
    public static void startAction(String form,String id,String condition,boolean canClose){
        Activity activity = AppManager.getAppManager().currentActivity();
        Intent intent  =new Intent(activity,CrowdFundingChooseTopicActivity.class);
        intent.putExtra(AppConstant.ExtraKey.FROM_TYPE,form);
        intent.putExtra(AppConstant.ExtraKey.FROM_CAN_CLOSE,canClose);
        intent.putExtra(AppConstant.ExtraKey.FROM_CONDITION,condition);
        intent.putExtra(AppConstant.ExtraKey.FROM_ID,id);
        activity.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_one_layout;
    }

    @Override
    public void initView() {
        super.initView();
        String form = getIntentExtra(getIntent(), AppConstant.ExtraKey.FROM_TYPE);
        if (TYPE_FORM_TOPIC.equals(form)){
            initFragment(new CrowdFundingChooseTopicFragment());
        }else if (TYPE_FORM_TEACHER.equals(form)){
            initFragment(new CrowdFundingChooseTeacherFragment());
        }else if (TYPE_FORM_HOME_TEACHER_LIST.equals(form)){
            initFragment(new TeamChooseTeacherFragment());
        }
    }

}
