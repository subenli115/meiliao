package com.ziran.meiliao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.baseapp.AppManager;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.commonwidget.OnNoDoubleClickListener;
import com.ziran.meiliao.ui.decompressionmuseum.activity.RecordActivity;
import com.ziran.meiliao.ui.decompressionmuseum.activity.RecordCourseActivity;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/5/8 17:52
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/5/8$
 * @updateDes ${TODO}
 */

public class CustomNumbers1View extends LinearLayout {

    private View rootView;
    private TextView tvDays, tvTimes, tvCourses;
    private TextView tvDaysLabel, tvTimesLabel, tvCoursesLabel;

    private View line1, line2;

    public CustomNumbers1View(Context context) {
        this(context, null);
    }

    public CustomNumbers1View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomNumbers1View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rootView = LayoutInflater.from(context).inflate(R.layout.custom_numbers, this, true);
        tvDays = ViewUtil.getView(rootView, R.id.tv_finish_days);
        tvDaysLabel = ViewUtil.getView(rootView, R.id.tv_finish_days_label);
        tvTimes = ViewUtil.getView(rootView, R.id.tv_finish_times);
        tvTimesLabel = ViewUtil.getView(rootView, R.id.tv_total_time_label);
        tvCourses = ViewUtil.getView(rootView, R.id.tv_finish_courses);
        tvCoursesLabel = ViewUtil.getView(rootView, R.id.tv_mdflnes_count);
        line1 = ViewUtil.getView(rootView, R.id.mind_line_1);
        line2 = ViewUtil.getView(rootView, R.id.mind_line_2);
        ViewUtil.setOnClickListener(rootView, R.id.ll_left, mClickListener);
        ViewUtil.setOnClickListener(rootView, R.id.ll_mid, mClickListener);
        ViewUtil.setOnClickListener(rootView, R.id.ll_right, mClickListener);
    }

    private OnNoDoubleClickListener mClickListener = new OnNoDoubleClickListener() {
        @Override
        protected void onNoDoubleClick(View v) {
//            if (!clickFinishActivity) return;
            switch (v.getId()) {
                case R.id.ll_left:
                    break;
                case R.id.ll_mid:
                    AppManager.getAppManager().jumpToActivity(RecordActivity.class, clickFinishActivity);
                    break;
                case R.id.ll_right:
                    AppManager.getAppManager().jumpToActivity(RecordCourseActivity.class, clickFinishActivity);
                    break;
            }
        }
    };
    private boolean clickFinishActivity = true;

    public void clickFinish(boolean canClick) {
        clickFinishActivity = canClick;
    }

    public void setDatas(int days, int times, int courses, boolean showMindLine) {
        ViewUtil.setText(tvDays, String.valueOf(days));
        ViewUtil.setText(tvTimes, String.valueOf(times));
        ViewUtil.setText(tvCourses, String.valueOf(courses));
        ViewUtil.setViewsShow(showMindLine ? VISIBLE : GONE, line1, line2);
    }

    public void setDatas(int days, String times, int courses, boolean showMindLine) {
        ViewUtil.setText(tvDays, String.valueOf(days));
        ViewUtil.setText(tvTimes, times);
        ViewUtil.setText(tvCourses, String.valueOf(courses));
        ViewUtil.setViewsShow(showMindLine ? VISIBLE : GONE, line1, line2);
    }


    public void setTextColor(int color){
        tvDaysLabel.setTextColor(color);
        tvTimesLabel.setTextColor(color);
        tvCoursesLabel.setTextColor(color);
    }
}
