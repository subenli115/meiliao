package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.DisplayUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.envet.CalendarState;
import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;
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

public class SJKZhuanLanBigInAudioNewAdapter extends MultiItemRecycleViewAdapter<ZhuanLanBigInBean.DataBean.DirBean> implements CalendarState {
    private static boolean isCal;
    private final int paddingRight;

    public SJKZhuanLanBigInAudioNewAdapter(Context context) {
        super(context, new BigInMultiItemType());
        isCal = true;
        paddingRight = DisplayUtil.dip2px(6);
    }

    @Override
    public void convert(ViewHolderHelper helper, ZhuanLanBigInBean.DataBean.DirBean bean, int position) {
        if (bean.isCheck()) {
            currentPosition = position;
        }
        try {
            if (isCal) {
                cal(helper, bean, position);
            } else {
                helper.setText(R.id.tv_item_sjk_zhuanlan_bigin_audio_title, bean.getTitle());
                helper.setTextColor(R.id.tv_item_sjk_zhuanlan_bigin_audio_title, bean.isCheck() ? R.color.textColor_teshe : R.color
                        .textColor_333);
                TextView tvIcon = helper.getView(R.id.tv_item_sjk_zhuanlan_bigin_audio_icon);
                tvIcon.setText(String.valueOf(position + 1));
                switch (bean.getStudyStatus()) {
                    case 0: //历史课程
                        helper.setBackgroundRes(R.id.tv_sjk_profile_icon, R.mipmap.course_lessonbg3);
                        tvIcon.setPadding(0, 0, paddingRight, 0);
                        tvIcon.setTextColor(mContext.getResources().getColor(R.color.textColor_teshe));
                        break;
                    case 1: //直播中
                        helper.setBackgroundRes(R.id.tv_sjk_profile_icon, R.mipmap.course_lessonbg2);
                        tvIcon.setPadding(0, 0, paddingRight, 0);
                        tvIcon.setTextColor(Color.WHITE);
                        break;
                    case 2: //预告
                        helper.setBackgroundRes(R.id.tv_sjk_profile_icon, R.mipmap.course_lessonbg);
                        tvIcon.setTextColor(Color.WHITE);
                        tvIcon.setPadding(0, 0, 0, 6);
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cal(ViewHolderHelper helper, ZhuanLanBigInBean.DataBean.DirBean bean, int position) {
        ItemCalenderCatalogView itemCalenderCatalogView = helper.getView(R.id.itemCalenderCatalogView);
        String month = "", date = "";
        String startTime = bean.getStartTime();
        if (EmptyUtils.isNotEmpty(startTime)) {
            month = startTime.substring(5, 7);
            date = startTime.substring(8, 10);
        }
        itemCalenderCatalogView.setStudyStatus(bean.getStatus() != 0 ? ItemCalenderCatalogView.StudyStatus.STUDY_STATUS_READED :
                ItemCalenderCatalogView.StudyStatus.STUDY_STATUS_UNREAD);
        itemCalenderCatalogView.setCheck(bean.isCheck());
        itemCalenderCatalogView.setDate(month, date, true);
    }

    public void onItemClick(int position) {
        if (position >= 0 && position < getItemCount() && currentPosition != position) {
            get(currentPosition).setCheck(false);
            get(position).setCheck(true);
            notifyItemChanged(currentPosition);
            notifyItemChanged(position);
        }
    }

    public void changeStyle() {
        isCal = !isCal;
    }

    private static class BigInMultiItemType implements MultiItemTypeSupport<ZhuanLanBigInBean.DataBean.DirBean> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_DATA:
                    if (isCal) {
                        return R.layout.item_sjk_live_detail_profile_calender;
                    } else {
                        return R.layout.item_sjk_zhuanlan_bigin_audio;
                    }
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, ZhuanLanBigInBean.DataBean.DirBean dataBean) {
            return TYPE_DATA;
        }
    }
}
