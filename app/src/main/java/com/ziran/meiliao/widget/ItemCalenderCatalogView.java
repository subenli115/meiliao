package com.ziran.meiliao.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;


/**
 * author 吴祖清
 * create  2017/3/31 10
 * des
 * <p>
 * updateAuthor
 * updateDate
 * updateDes
 */


public class ItemCalenderCatalogView extends LinearLayout {
    private TextView mMouthTextView;
    private TextView mDayTextView;
    private ImageView ivStudy;
    private View rootView;
    public ItemCalenderCatalogView(Context context) {
        this(context, null);
    }

    public ItemCalenderCatalogView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemCalenderCatalogView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        rootView =    LayoutInflater.from(context).inflate(R.layout.custom_calender_catalog_view, this, true);
        mMouthTextView = ViewUtil.getView(rootView, R.id.tv_item_sjk_live_profile_calender_mouth);
        mDayTextView = ViewUtil.getView(rootView, R.id.tv_item_sjk_live_profile_calender_date);
        ivStudy = ViewUtil.getView(rootView, R.id.iv_item_sjk_live_profile_calender_study);
    }


    public void setDate(String mouth, String date, boolean isShowMouth) {
        mDayTextView.setText(date);
        if (isShowMouth && EmptyUtils.isNotEmpty(mouth)) {
            mMouthTextView.setText(mouth+"月");
            mMouthTextView.setVisibility(VISIBLE);
        } else {
            mMouthTextView.setVisibility(INVISIBLE);
        }

    }

    private StudyStatus mStudyStatus;

    public void setStudyStatus(StudyStatus studyStatus) {
        mStudyStatus = studyStatus;
        switch (studyStatus) {
            case STUDY_STATUS_UNREAD:
                mMouthTextView.setTextColor(getColor(R.color.textColor_666));
                mDayTextView.setTextColor(getColor(R.color.textColor_999));
                ivStudy.setImageResource(R.mipmap.course_icon_nostart);
                break;
            case STUDY_STATUS_READED:
                mMouthTextView.setTextColor(getColor(R.color.textColor_teshe));
                mDayTextView.setTextColor(getColor(R.color.textColor_teshe));
                ivStudy.setImageResource(R.mipmap.course_icon_start);
                break;
        }
    }

    public void setCheck(boolean isCheck) {
        if (isCheck) {
            mDayTextView.setBackgroundResource(R.mipmap.course_bg_date);
            mDayTextView.setTextColor(getColor(R.color.white));
        } else {
            mDayTextView.setBackgroundResource(0);
            mDayTextView.setTextColor(getColor(mStudyStatus == StudyStatus.STUDY_STATUS_UNREAD ? R.color.textColor_999 : R.color
                    .textColor_teshe));
        }
    }

    public enum StudyStatus {
        STUDY_STATUS_UNREAD, STUDY_STATUS_READED
    }

    public int getColor(int color) {
        return getResources().getColor(color);
    }
}
