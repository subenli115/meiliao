package com.ziran.meiliao.ui.settings.adapter;

import android.content.Context;
import android.view.View;

import com.ziran.meiliao.R;
import com.ziran.meiliao.common.irecyclerview.universaladapter.ViewHolderHelper;
import com.ziran.meiliao.common.irecyclerview.universaladapter.abslistview.CommonAblistViewAdapter;
import com.ziran.meiliao.ui.bean.PointsListBean;

/**
 * @author 吴祖清
 * @version $Rev$
 * @createTime 2017/6/6 15:35
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate 2017/6/6$
 * @updateDes ${TODO}
 */


public class AmountPointsAdapter extends CommonAblistViewAdapter<PointsListBean.DataBean.ScoreListBean> {

    private int mPoint;

    public AmountPointsAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    private int selectPosition;

    @Override
    public void convert(ViewHolderHelper holder, PointsListBean.DataBean.ScoreListBean item, int position) {
        if (item.isSelect()) {
            holder.setBackgroundRes(R.id.ll_recharge_bg, R.drawable.shape_grid_amount_select);
            holder.setTextColor(R.id.tv_recharge_amount, R.color.textColor_teshe);
            selectPosition = position;
            holder.getView(R.id.tv_points).setVisibility(View.VISIBLE);
        } else {
            holder.setTextColor(R.id.tv_recharge_amount, R.color.textColor_999);
            holder.setBackgroundRes(R.id.ll_recharge_bg, R.drawable.shape_grid_amount);
            holder.getView(R.id.tv_points).setVisibility(View.INVISIBLE);
        }
        holder.setText(R.id.tv_recharge_gold, String.format("%d积分", item.getScore()));
        if (item.getCoin()>1){
            holder.setText(R.id.tv_recharge_amount, item.getCoin()+"金币");
        }else{
            holder.setText(R.id.tv_recharge_amount, item.getCoin()+"金币");
        }
        if(item.getScore()>mPoint){

            holder.setText(R.id.tv_points,"积分不足");
        }else {
            holder.getView(R.id.tv_points).setVisibility(View.GONE);
        }
    }
    public void update(int point){
        mPoint=point;
        notifyDataSetChanged();

    }

    public void changeSelect(int position) {
        if (selectPosition != -1 && selectPosition != position) {
            getItem(selectPosition).setSelect(false);
            get(position).setSelect(true);
            notifyDataSetChanged();
        }
    }

    public PointsListBean.DataBean.ScoreListBean getSelect() {
        return getItem(selectPosition);
    }
}
