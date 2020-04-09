package com.ziran.meiliao.ui.decompressionmuseum.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonwidget.NormalTitleBar;
import com.ziran.meiliao.ui.base.CommonHttpActivity;
import com.ziran.meiliao.ui.decompressionmuseum.fragment.RecordFragment3;

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

public class RecordActivity extends CommonHttpActivity {

    @Bind(R.id.iv_mindfulness_hall_bg)
    ImageView ivMindfulnessHallBg;
    @Bind(R.id.iv_record_calendar_or_chart)
    ImageView ivRecordCalenderOrChart;
    @Bind(R.id.ntb_title)
    NormalTitleBar ntbTitle;


    private RecordFragment3 mRecordFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_record;
    }
    @Override
    public void initView() {
        super.initView();
        mRecordFragment = new RecordFragment3();
        initFragment(mRecordFragment);
        ntbTitle.setTitleWeizhi();
        ntbTitle.setNewTitleText("练习统计");
        ntbTitle.setTitleColor(Color.BLACK);
    }
    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }
    @OnClick({R.id.iv_mindfulness_hall_close, R.id.iv_record_calendar_or_chart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_mindfulness_hall_close:
                finish();
                break;
            case R.id.iv_record_calendar_or_chart:
                ivRecordCalenderOrChart.setImageResource(mRecordFragment.change()?R.mipmap.me_mdflnes_ic_data:R.mipmap.me_mdflnes_ic_date);
                break;
        }
    }
}
