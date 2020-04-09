package com.ziran.meiliao.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.utils.StringUtils;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/8 14:18
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/8$
 * @updateDes ${TODO}
 */

public class CustomStudyPanelView extends RelativeLayout {

    TextView tvStudyPanelTitle;
    TextView tvStudyPanelDes;
    TextView tvStudyPanelSt;
    PlayPauseView ivStudyPanelPlay;


    public CustomStudyPanelView(Context context) {
        this(context, null);
    }

    public CustomStudyPanelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomStudyPanelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.custom_study_panel, this, true);
        tvStudyPanelTitle = ViewUtil.getView(this, R.id.tv_custom_study_panel_title);
        tvStudyPanelDes = ViewUtil.getView(this, R.id.tv_custom_study_panel_des);
        tvStudyPanelSt = ViewUtil.getView(this, R.id.tv_custom_study_panel_st);
        ivStudyPanelPlay = ViewUtil.getView(this, R.id.tv_custom_study_panel_play);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        HomePageMusicManager.get().setListenerState(false, this);
    }


    public void setTvTitle(String title) {
        ViewUtil.setText(tvStudyPanelTitle, title);
    }



    public void setResDes(String url, String title, String duration, int watchCount, int studyStatus, boolean stVis, boolean isBuy) {
        ViewUtil.setText(tvStudyPanelTitle, title);
        String tag = studyStatus == 0 ? "未学习" : (studyStatus == 1 ? "学习中" : "已学习");
        tvStudyPanelTitle.setTextColor(getResources().getColor("已学习".equals(tag)?R.color.textColor_teshe:R.color.textColor_333));
        duration = EmptyUtils.isEmpty(duration)?"":duration+" / ";
        ViewUtil.setText(tvStudyPanelDes, StringUtils.format("%s%d人学习 / %s", duration, watchCount, tag));
        if (isBuy) {
            if (showPlay) {
                ivStudyPanelPlay.setImageResource(R.mipmap.player_play);
                ivStudyPanelPlay.setVisibility(VISIBLE);
            }
        } else {
            tvStudyPanelSt.setVisibility(stVis ? VISIBLE : GONE);
            if (showPlay) {
                ivStudyPanelPlay.setVisibility(stVis ? VISIBLE : GONE);
                if (stVis) {
                    ivStudyPanelPlay.setImageResource(R.mipmap.player_play);
                }
            }
        }

    }

    public void setPlayClick(View.OnClickListener listener) {
        if (listener != null) {
            ivStudyPanelPlay.setOnClickListener(listener);
        }
    }

    private boolean showPlay;

    public void setPlayShowState(int vis) {
        showPlay = (vis == VISIBLE);
        ivStudyPanelPlay.setVisibility(vis);
    }


    public void updatePlayState(boolean isPlaying) {
        ivStudyPanelPlay.toggle(isPlaying);
    }
}
