package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.entry.VideoSectionEntry;
import com.ziran.meiliao.envet.CalendarState;
import com.ziran.meiliao.widget.ItemCalenderCatalogView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/9/18 17:40
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/9/18$
 * @updateDes ${TODO}
 */

public class SJKLiveDetailProfileNewAdapter extends MultiItemRecycleViewAdapter<VideoSectionEntry> implements CalendarState {
    private static boolean isCal;
    private final int paddingRight;

    public SJKLiveDetailProfileNewAdapter(Context context) {
        super(context, new LiveDetailProfileMultiItemType());
        isCal = false;
        paddingRight = DisplayUtil.dip2px(6);
    }

    @Override
    public void convert(ViewHolderHelper helper, VideoSectionEntry bean, int position) {
        if (bean.isSelect()) {
            currentPosition = position;
        }
        try {
            if (isCal) {
                cal(helper, bean, position);
            } else {
                helper.setVisible(R.id.tv_sjk_profile_tag, bean.getTag() == 1);
                helper.setText(R.id.tv_sjk_profile_title, bean.getTitle());
                TextView tvTitle = helper.getView(R.id.tv_sjk_profile_title);
                TextView tvIcon = helper.getView(R.id.tv_sjk_profile_icon);
                tvIcon.setText(String.valueOf(position + 1));
                switch (bean.getTag()) {
                    case 0: //历史课程
                        helper.setBackgroundRes(R.id.tv_sjk_profile_icon, R.mipmap.course_lessonbg3);
                        tvIcon.setPadding(0, 0, paddingRight, 0);
                        tvIcon.setTextColor(mContext.getResources().getColor(R.color.textColor_teshe));
                        break;
                    case 1: //直播中
                        helper.setBackgroundRes(R.id.tv_sjk_profile_icon, R.mipmap.course_lessonbg2);
                        if (currentPosition == -1) {
                            currentPosition = position;
                            bean.setSelect(true);
                        }
                        tvIcon.setPadding(0, 0, paddingRight, 0);
                        tvIcon.setTextColor(Color.WHITE);
                        break;
                    case 2: //预告
                        helper.setBackgroundRes(R.id.tv_sjk_profile_icon, R.mipmap.course_lessonbg);
                        tvIcon.setTextColor(Color.WHITE);
                        tvIcon.setPadding(0, 0, 0, 6);
                        break;
                }
                tvTitle.setTextColor(getColor(bean.isSelect() ? R.color.textColor_teshe : R.color.textColor_666));
            }
        } catch (Exception e)

        {
            e.printStackTrace();
        }
    }

    private void cal(ViewHolderHelper helper, VideoSectionEntry bean, int position) {
        ItemCalenderCatalogView calenderCatalogView = helper.getView(R.id.itemCalenderCatalogView);
        calenderCatalogView.setDate(bean.getMonth(), bean.getDate(), bean.isShowMonth());
        calenderCatalogView.setStudyStatus(bean.getTag() == 0 ? ItemCalenderCatalogView.StudyStatus.STUDY_STATUS_READED :
                ItemCalenderCatalogView.StudyStatus.STUDY_STATUS_UNREAD);
        calenderCatalogView.setCheck(bean.isSelect());
    }

    public void onItemClick(int position) {
        if (position >= 0 && position < getItemCount() && currentPosition != position) {
            get(currentPosition).setSelect(false);
            get(position).setSelect(true);
            notifyItemChanged(currentPosition);
            notifyItemChanged(position);
        }
    }
    @Override
    public void changeStyle() {
        isCal = !isCal;
    }

    private static class LiveDetailProfileMultiItemType implements MultiItemTypeSupport<VideoSectionEntry> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_DATA:
                    if (isCal) {
                        return R.layout.item_sjk_live_detail_profile_calender;
                    } else {
                        return R.layout.item_sjk_live_detail_profile_default;
                    }
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, VideoSectionEntry dataBean) {
            return TYPE_DATA;
        }
    }
}