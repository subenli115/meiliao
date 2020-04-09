package com.ziran.meiliao.ui.decompressionmuseum.activity;

import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.decompressionmuseum.fragment.RecordCourseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/8/10 14:57
 * @des ${正念练习界面}
 * @updateAuthor $Author$
 * @updateDate 2017/8/10$
 * @updateDes ${TODO}
 */

public class RecordCourseActivity extends CommonHttpActivity {

    @Bind(R.id.iv_mindfulness_hall_bg)
    ImageView ivMindfulnessHallBg;

    private RecordCourseFragment mRecordFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_record_course;
    }

    @Override
    public void initView() {
        super.initView();
        mRecordFragment = new RecordCourseFragment();
        initFragment(mRecordFragment);
    }
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }
    @OnClick({R.id.iv_mindfulness_hall_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mindfulness_hall_close:
                finish();
                break;
        }
    }
}
