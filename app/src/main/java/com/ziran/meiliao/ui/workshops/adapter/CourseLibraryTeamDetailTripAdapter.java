package com.ziran.meiliao.ui.workshops.adapter;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.constant.AppConstant;
import com.ziran.meiliao.ui.bean.TeamDetailTripBean;
import com.ziran.meiliao.ui.bean.MultiItemBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/12/8 9:44
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/12/8$
 * @updateDes ${TODO}
 */

public class CourseLibraryTeamDetailTripAdapter extends MultiItemRecycleViewAdapter<MultiItemBean> implements AppConstant.TeamDetail {

    public CourseLibraryTeamDetailTripAdapter(Context context) {
        super(context, new CourseLibraryTeamDetailTripMultiItemType());
        setHeadCount(0);
    }

    @Override
    public void convert(ViewHolderHelper helper, MultiItemBean bean, int position) {
        switch (getItemViewType(position)) {
            case TYPE_DAY:
                helper.setText(R.id.tv_day, bean.getTitle());
                break;
            case TYPE_NORMAL:
                Object obj = bean.getObj();
                if (obj instanceof TeamDetailTripBean) {
                    TeamDetailTripBean tripBean = (TeamDetailTripBean) obj;
                    helper.setTextAndVis(R.id.tv_title, tripBean.getTitle());
                    helper.setTextAndVis(R.id.tv_time, tripBean.getTime());
                    helper.setTextAndVis(R.id.tv_content, tripBean.getContent());
                    helper.setImageResource(R.id.iv_icon, getResId(tripBean.getType()));
                    helper.setImageUrlTarget(R.id.iv_pic, tripBean.getPic(), R.mipmap.ic_loading_square);
                    helper.setVisible(R.id.view_line, tripBean.isShowLine());
                    helper.setVisible(R.id.space, tripBean.isShowLine());
                    if (EmptyUtils.isNotEmpty(tripBean.getPic())) {
                        helper.setImageUrlTarget(R.id.iv_pic, tripBean.getPic(), R.mipmap.ic_loading_square);
                    }
                    if (EmptyUtils.isEmpty(tripBean.getContent()) && EmptyUtils.isEmpty(tripBean.getTime()) && EmptyUtils.isEmpty
                            (tripBean.getPic()) && tripBean.isShowLine()) {
                        helper.getView(R.id.tv_time).setVisibility(View.INVISIBLE);
                    }
                    helper.setVisible(R.id.iv_pic, EmptyUtils.isNotEmpty(tripBean.getPic()));
                }
                break;
        }
    }

    private int getResId(int type) {
        switch (type) {
            case ICON_TYPE_ADDRESS:
                return R.mipmap.workshops_ic_schedule; //地点
            case ICON_TYPE_RITE:
                return R.mipmap.workshops_ic_act;      //开营仪式
            case ICON_TYPE_HOURSE:
                return R.mipmap.workshops_ic_building;//房子
            case ICON_TYPE_HOTEL:
                return R.mipmap.workshops_ic_food; //酒店
        }
        return 0;
    }

    private static class CourseLibraryTeamDetailTripMultiItemType implements MultiItemTypeSupport<MultiItemBean> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_DAY:
                    return R.layout.item_course_library_team_detail_trip_day;
                case TYPE_NORMAL:
                    return R.layout.item_course_library_team_detail_trip_normal;
            }
            return -1;
        }

        @Override
        public int getItemViewType(int position, MultiItemBean bean) {
            return bean.getItemType();
        }
    }
}
