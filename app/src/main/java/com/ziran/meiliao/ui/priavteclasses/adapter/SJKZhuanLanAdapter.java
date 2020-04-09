package com.ziran.meiliao.ui.priavteclasses.adapter;

import android.content.Context;
import android.widget.TextView;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.commonutils.ViewUtil;
import com.ziran.meiliao.common.compressorutils.EmptyUtils;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.ziran.meiliao.common.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.ziran.meiliao.envet.CalendarState;
import com.ziran.meiliao.ui.bean.SpceColumnBean;
import com.ziran.meiliao.ui.bean.ZhuanLanBigInBean;
import com.ziran.meiliao.widget.ItemCalenderCatalogView;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/8 15:02
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/8$
 * @updateDes ${TODO}
 */


public class SJKZhuanLanAdapter extends MultiItemRecycleViewAdapter<Object> implements CalendarState {

    private static boolean isCal;
    private SpceColumnBean.DataBean  dataBean;
    public SJKZhuanLanAdapter(Context context) {
        super(context, new SJKZhuanLanMultiItemTypeSupport());
        isCal = true;
    }

    public SJKZhuanLanAdapter(Context context, SpceColumnBean.DataBean dataBean) {
        super(context, new SJKZhuanLanMultiItemTypeSupport() );
        isCal = false;
        this.dataBean = dataBean;
    }

    @Override
    public void convert(ViewHolderHelper holder, Object obj, int position) {
        try {
            switch (getItemViewType(position)) {
                case TYPE_DATA:
                    ZhuanLanBigInBean.DataBean.DirBean bean = EmptyUtils.parseObject(obj);
                    if (bean.isCheck()) {
                        currentPosition = position;
                    }
                    if (isCal) {
                        cal(holder, bean, position);
                    } else {
                        holder.setText(R.id.tv_item_sjk_zhuanlan_title, bean.getTitle());
                        boolean isStudyFinish = "100".equals(bean.getProgress());
                        holder.setVisible(R.id.iv_study_finish,isStudyFinish);
                        holder.setTextColor(R.id.tv_item_sjk_zhuanlan_title,bean.getStatus()==0?R.color.textColor_666:R.color.textColor_333);
                        if (isStudyFinish || (dataBean!=null && dataBean.isBuy()) ){
                            holder.setVisible(R.id.tv_free,false);
                        }else{
                            holder.setVisible(R.id.tv_free,bean.isSt());
                        }
                    }
                    break;
                case TYPE_HEAD:
                    SpceColumnBean.DataBean mDataBean = EmptyUtils.parseObject(obj);
                    holder.setVisible(R.id.tv_trailer_intro, false);
                    holder.setVisible(R.id.iv_tralier_headPic, true);
                    holder.setImageUrl(R.id.iv_tralier_headPic, mDataBean.getPic(), R.mipmap.ic_loading_square_big);
                    holder.setText(R.id.tv_trailer_title, mDataBean.getTitle());
                    holder.setText(R.id.tv_trailer_teaIntro, mDataBean.getAuthor().getTeaIntro());
                    TextView tv = holder.getView(R.id.tv_trailer_course_detail);
                    ViewUtil.setHtmlText(tv, mDataBean.getDetail());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cal(ViewHolderHelper helper, ZhuanLanBigInBean.DataBean.DirBean bean, int position) {
        ItemCalenderCatalogView itemCalenderCatalogView = helper.getView(R.id.itemCalenderCatalogView);
        itemCalenderCatalogView.setStudyStatus(bean.getStatus() != 0 ? ItemCalenderCatalogView.StudyStatus.STUDY_STATUS_READED :
                ItemCalenderCatalogView.StudyStatus.STUDY_STATUS_UNREAD);
        itemCalenderCatalogView.setCheck(bean.isCheck());
        itemCalenderCatalogView.setDate(bean.getMouth(), bean.getDay(), true);
    }

    public void changeStyle() {
        isCal = !isCal;
    }

    public void onItemClick(int position) {
        if (position >= 0 && position < getItemCount() && currentPosition != position) {
            ((ZhuanLanBigInBean.DataBean.DirBean) get(currentPosition)).setCheck(false);
            ((ZhuanLanBigInBean.DataBean.DirBean) get(position)).setCheck(true);
            notifyItemChanged(currentPosition);
            notifyItemChanged(position);
        }
    }

    private static class SJKZhuanLanMultiItemTypeSupport implements MultiItemTypeSupport<Object> {
        @Override
        public int getLayoutId(int itemType) {
            switch (itemType) {
                case TYPE_HEAD:
                    return R.layout.headerview_trailer;
                case TYPE_DATA:
                    if (isCal) {
                        return R.layout.item_sjk_live_detail_profile_calender;
                    } else {
                        return R.layout.item_sjk_zhuanlan;
                    }
            }
            return 0;
        }

        @Override
        public int getItemViewType(int position, Object bean) {
            if (bean instanceof ZhuanLanBigInBean.DataBean.DirBean) {
                return TYPE_DATA;
            } else {
                return TYPE_HEAD;
            }
        }
    }


}
